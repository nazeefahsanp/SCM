package com.pwc.scm.spinner.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.pwc.scm.spinner.PWCDeltaExtractor;
import com.pwc.scm.util.PWCDeltaConfiguration;
import com.pwc.scm.util.PWCDeltaExtractorUtils;

/**
 * 
 * @author Sanjay.Meena
 *
 */
public class PWCSpinnerDeltaLineExtractor extends PWCDeltaExtractor {

	/** The logger. */
	private static Logger logger = Logger.getLogger(PWCSpinnerDeltaLineExtractor.class);

	/** The dependent file */
	private File dependentFile;
	

	/** The constructor with origin & source file arguments. */
	public  PWCSpinnerDeltaLineExtractor(File originFile, File targetFile, File relatedFile) {
		this._originFile = originFile;
		this._targetFile = targetFile;
		this.dependentFile=relatedFile;
		
	}

	@Override
	public void extract() throws IOException  {
		
		logger.info("Extraction STARTED for the file :"+_targetFile.getCanonicalPath() );
		/* Get all content from ORIGIN TAG file in a list*/
		Map<String,String[]> originFileContent = getFileContentAsStringList(this._originFile);
		
		/* Get changed lines by Reading TARGET TAG file and look for it in the origin content list*/
		List<String[]> chnagedOrAddedLineContent = compareFileContent(this._targetFile, originFileContent);
		
		if(chnagedOrAddedLineContent.size() > 1) {
			String targetDeltaFileName = PWCDeltaExtractorUtils.getCorrespondingDeltaOutputLocationFromTarget(this._targetFile.getCanonicalPath());
			
			CSVWriter writer = new CSVWriter(new FileWriter(targetDeltaFileName), TAB_SEPERATOR, CSVWriter.NO_QUOTE_CHARACTER,CSVWriter.NO_ESCAPE_CHARACTER);
		    
			writer.writeAll(chnagedOrAddedLineContent);
		    writer.close();
			
		    //Extract Dependent File
		    if(dependentFile != null) {
		    	PWCDeltaExtractorUtils.extractDependentFileByName(dependentFile);	
		    }
		}
		if(_targetFile.getName().equals("XP_Policy.xls")) {
			System.err.println(" TO be stopped");
		}
		logger.info("Extraction finished for "+_targetFile.getName());
		
	}

	
	/**
	 * Get Spinner content in file as list
	 * @param file
	 * @return
	 * @throws IOException 
	 */
	private Map<String,String[]>  getFileContentAsStringList(File file) throws FileNotFoundException, IOException {
		/** placeholder for storing file content */
		Map<String,String[]> fileContent = new HashMap<String,String[]>();
		
		/** Get Primary Key column count from configuration*/
		Integer numberofPrimaryColumns = PWCDeltaConfiguration.getInstance().getPrimaryKeyColumnCount(file.getName());
		
		/** Read & store file content*/
		try(Reader targetReader = new FileReader(file)){
			//TAB separated file content read operation
		    CSVReader csvReader = new CSVReader(targetReader,TAB_SEPERATOR, CSVWriter.NO_QUOTE_CHARACTER,CSVWriter.NO_ESCAPE_CHARACTER);
		    
		    List<String[]> allContentFromFile = csvReader.readAll();
		    
		    //set header row
		    String[] headerRow = allContentFromFile.get(0);
		    fileContent.put( Arrays.toString(headerRow), headerRow);
		    
		    //process other rows
		    for (int rowNum = 1; rowNum < allContentFromFile.size(); rowNum++) {
				String[] spinnerFileRow = allContentFromFile.get(rowNum);
				
				/** put content in file: first column as key and entire row as value */
		    	String primaryKey = PWCDeltaExtractorUtils.getPrimaryKey(numberofPrimaryColumns, spinnerFileRow);
		    	fileContent.put(primaryKey, spinnerFileRow);
		    	
			}
		   
		    //close readers
		    targetReader.close();
		    csvReader.close();
		} 
		return fileContent;
	}

	


	/**
	 * 
	 * @param file
	 * @param originFileContentMap
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private List<String[]> compareFileContent(File file, Map<String,String[]> originFileContentMap) throws FileNotFoundException, IOException {

		//placeholder list for changed content
		List<String[]> changedOrAddedLineContent = new ArrayList<String[]>();
		
		Integer numberofPrimaryColumns = PWCDeltaConfiguration.getInstance().getPrimaryKeyColumnCount(file.getName());
		
		try(Reader targetReader = new FileReader(file)){
			//TAB separated file content read operation
			CSVReader csvReader =  new CSVReader(targetReader,TAB_SEPERATOR, CSVWriter.NO_QUOTE_CHARACTER,CSVWriter.NO_ESCAPE_CHARACTER);

		    List<String[]> allContentFromTargetFile = csvReader.readAll();
		    
		    String[] headerRow = allContentFromTargetFile.get(0);
		    
		    if(originFileContentMap.get(Arrays.toString(headerRow)) == null) {
		    	logger.error("Header row mismatch, aborting comparision for file: "+file.getName());
//		    	throw new PWCSpinnerDeltaExtractionException("");
		    }else {
		    	changedOrAddedLineContent.add(headerRow);	
		    	for (int rowNum = 1; rowNum < allContentFromTargetFile.size(); rowNum++) {
					String[] spinnerFileRow = allContentFromTargetFile.get(rowNum);
					
					String primaryKey = PWCDeltaExtractorUtils.getPrimaryKey(numberofPrimaryColumns, spinnerFileRow);

			    	String[] originFilecontent = originFileContentMap.get(primaryKey);
			    	
			    	String originFileRow = Arrays.toString(originFilecontent);
			    	String targetFileRow = Arrays.toString(spinnerFileRow);
			    	
			    	if(originFilecontent == null || !targetFileRow.equals(originFileRow )) {
			    		//CASE: NEW LINE OR CHANGED LINE :: rowAsString.substring(1,rowAsString.length()-1)
						changedOrAddedLineContent.add(spinnerFileRow);	
			    	}
				}

		    }
		    
		    targetReader.close();
		    csvReader.close();
			
		} 
		return changedOrAddedLineContent;
	}

	
	/**
	 * @return the dependentFile
	 */
	public File getDependentFile() {
		return dependentFile;
	}


	/**
	 * @param dependentFile the dependentFile to set
	 */
	public void setDependentFile(File dependentFile) {
		this.dependentFile = dependentFile;
	}

}
