package com.pwc.scm.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import com.opencsv.CSVReader;
import com.pwc.scm.helper.PWCSpinnerDeltaExtractorFactory;
import com.pwc.scm.spinner.PWCDeltaExtractor;

/**
 * The Class PWCDeltaExtractorUtil.
 */
public final class PWCDeltaExtractorUtils {

	/** The logger. */
	private static Logger logger = Logger.getLogger(PWCDeltaExtractorUtils.class);

	/** Configuration reference*/
	private static PWCDeltaConfiguration configuration = PWCDeltaConfiguration.getInstance();
	
	/** Placeholder to store related file content */
	private static Map<String,List<String>> relatedFileDeltaStore = new HashMap<String, List<String>>();
	
	/** DOT String */
	public final static String DOT_SEPARATOR = ".";
	
	/** DOT String */
	public final static String UNDERSCORE = "_";
	

	/**
	 * 
	 * @param targetTagPath
	 * @param parentPath
	 * @throws IOException
	 */
	public static void beginDeltaExtractionProcess(Path targetTagPath, String parentPath) throws IOException {
		logger.info("Begin Extraction :"+targetTagPath);
		if(targetTagPath.startsWith("C:\\Projects\\PWC_Projects\\Project_SCM\\deployment_review\\Build_Package\\checkoutdir\\targettag\\Spinner\\Business\\SourceFiles\\com\\dassault_systemes\\enovia\\contacts\\services\\trigger")) {
			System.err.println("TO be Stopped");
		}
		//Iterate all paths from a directory
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(targetTagPath)) {
	
			//declare configuration variables for reference
			Map<String, String> deltaConfiguration = configuration.getTransformedProperties();
			
			
	        for (Path entry : stream) {
	        	//Get File name without extension
	        	String fileName = entry.getFileName().toString().toLowerCase();
				
	        	/** Retrieve configuration Rule Key values */
	        	String confRuleKey = parentPath + UNDERSCORE + fileName; //build keys to fetch values from configuration
	        	
	        	/** RULE_VALUE as defined in configuration e.g: (AS_IS_RULE, DELTA_FILE_RULE, DELTA_FILE_CONTENT_RULE) */
	        	String confRuleValue = deltaConfiguration.get(confRuleKey); 
	        	
	        	String confClassKey = parentPath + UNDERSCORE + fileName + "_class";
	        	/** RULE_CLASS as defined in configuration e.g: (PWCSpinnerDeltaLineExtractor, PWCSpinnerDeltaFileExtractor) */
	        	String confClassValue = deltaConfiguration.get(confClassKey);
				
	        	/** Process AS IS RULE
	        	 * 
	        	 *  This rule will just copy the directories as is, the directory will not be traversed further
	        	 *  */
	        	if(confRuleValue.startsWith(PWCDeltaConfiguration.AS_IS_RULE)) {
	        		if(!entry.toFile().isDirectory()) {
	        			FileUtils.copyFile(entry.toFile(), getCorrespondingDeltaOutputFileFromTarget(entry.toFile()));	
	        		}else {
	        			FileUtils.copyDirectory(entry.toFile(), getCorrespondingDeltaOutputFileFromTarget(entry.toFile()));	
	        		}
	        		continue; //GO TO NEXT ENTRY
	        	}	
	        	
	        	/** Get RELATED_FILE rule. It is applicable only at file level, so if it is a directory skip it. */
	        	String relatedFileName = null;//getRelatedFileRule(deltaConfiguration, entry, confRuleKey, confRuleValue, fileName);

	        	
	        	/** Process DELTA_FILE_RULE 
	        	 * Call it recursively for directories.
	        	 * 
	        	 */
	        	if(confRuleValue.startsWith(PWCDeltaConfiguration.DELTA_FILE_RULE)) {
	        		if (Files.isDirectory(entry)) {
	                	beginDeltaExtractionProcess(entry, confRuleKey);
	                }else {
	                	processDeltaRule(entry, confClassValue, confClassValue, relatedFileName);
	                }
				}

	        	/** Process DELTA FILE CONTENT RULE
	        	 * Call it recursively for directories.
	        	 * 
	        	 */
	        	if(confRuleValue.startsWith(PWCDeltaConfiguration.DELTA_FILE_CONTENT_RULE)) {
	        		if (Files.isDirectory(entry)) {
	                	beginDeltaExtractionProcess(entry, confRuleKey);
	                }else {
	                	
	                	String confChangedFileClassKey = parentPath + UNDERSCORE + fileName + "_changedfile_class";
						String confNewFileClassKey = parentPath + UNDERSCORE + fileName + "_newfile_class";
			        	String confChangedFileClassValue = deltaConfiguration.get(confChangedFileClassKey);
			        	String confNewFileClassValue = deltaConfiguration.get(confNewFileClassKey);
			        				        	
			        	processDeltaRule(entry, confChangedFileClassValue, confNewFileClassValue, relatedFileName);
	                }
				}
	        }
	    }
	}



	/**
	 * This method will handle all extraction rules
	 * @param entry
	 * @param confChangedFileClassValue
	 * @param confNewFileClassValue
	 * @param relatedFileRule
	 * @throws IOException
	 */
	private static void processDeltaRule(Path entry, String confChangedFileClassValue, String confNewFileClassValue, String relatedFileRule) throws IOException {
		//related_file_rule, related_file_content_rule
		PWCDeltaExtractor deltaLineExtractor = null;
		
		/** build origin file path*/
		File targetFile = entry.toFile() ;
		File originFile = getCorrespondingOriginFromTarget(targetFile);

		
		File relatedFile = getRelatedFileObject(entry, relatedFileRule, targetFile.getName());

		/**Case 1: Check if it target file is newly added */
		if(originFile == null || !originFile.exists()) {
			deltaLineExtractor  = PWCSpinnerDeltaExtractorFactory.getSpinnerDeltaExtractor(null, targetFile, relatedFile, confNewFileClassValue);
			//newSpinnerFileCount++;
		}else {//Case 2: File changed
			/**Case 2: When File may have been modified*/
			//modifiedFileCount++;
			deltaLineExtractor  = PWCSpinnerDeltaExtractorFactory.getSpinnerDeltaExtractor(originFile, targetFile, relatedFile, confChangedFileClassValue);
		}
		
		//Execute extraction process
		deltaLineExtractor.extract();
	}



	/**
	 * Get Corresponding origin file or directory from target
	 * @param targetFile
	 * @return
	 * @throws IOException
	 */
	public static File getCorrespondingOriginFromTarget(File targetFile) throws IOException {
		String canonicalPath = targetFile.getCanonicalPath();
		String relativePath = canonicalPath.replace(configuration.getTargetTagDir(),"");
		File originFile = new File (configuration.getOriginTagDir() + File.separator + relativePath);
		return originFile;
	}
	
	
	/**
	 * Get Corresponding delta file or directory from target
	 * @param targetFile
	 * @return
	 * @throws IOException
	 */
	public static File getCorrespondingDeltaOutputFileFromTarget(File targetFile) throws IOException {
		if (targetFile == null) {
			System.err.println("target is null");
		}
		String canonicalPath = targetFile.getCanonicalPath();
		// String relativePath = canonicalPath.replace(configuration.getTargetTagDir(),"");
		String relativePath = canonicalPath.substring(canonicalPath.lastIndexOf(File.separator));
		File deltaOutputFile = new File(configuration.getDeltaOutputDir() + File.separator + relativePath);

		return deltaOutputFile;
	}


	/**
	 * @return
	 * @throws IOException
	 */
	public static String getCorrespondingDeltaOutputLocationFromTarget(String targetFileCanonicalPath) throws IOException {
		Path targetFilePath = Paths.get(targetFileCanonicalPath);
		
		
		int spinnerDirectoryIndex = targetFilePath.toString().indexOf(PWCDeltaConfiguration.SPINNER);
		int fileNameindex = targetFilePath.toString().indexOf(targetFilePath.getFileName().toString());
		String pathFromSpinnerTillFile = targetFilePath.toString().substring(spinnerDirectoryIndex, fileNameindex);
		
		String targetDeltaFileDirectory = PWCDeltaConfiguration.getInstance().getDeltaOutputDir() + File.separator + pathFromSpinnerTillFile;
		
		Path path = Paths.get(targetDeltaFileDirectory);
		
		if (!Files.exists(path)) {
		    Files.createDirectories(path);
		    logger.info("Following Directory created: "+path);
		}
		
		String targetDeltaFileName = targetDeltaFileDirectory + targetFilePath.getFileName();
		return targetDeltaFileName;
	}
	

	/**
	 * TODO: TO be fixed
	 * @param deltaConfiguration
	 * @param entry
	 * @param confRuleKey
	 * @param confRuleValue
	 * @param fileName
	 * @return
	 */
	private static String getRelatedFileRule(Map<String, String> deltaConfiguration, Path entry, String confRuleKey,
			String confRuleValue, String fileName) {
		String relatedFileRule = null;
		
		String configKeyForParent = confRuleKey.substring(0,confRuleKey.lastIndexOf(fileName)-1);
		//skip if it is directory
		if (!Files.isDirectory(entry)) {
			String[] allRulesForThisEntry = confRuleValue.split(",");
			if(allRulesForThisEntry != null && allRulesForThisEntry.length == 2) {
				
				boolean isAdditionalConfKeyProcessed= false;
				String parentConfKey= confRuleKey.substring(0,confRuleKey.lastIndexOf(fileName)-1);
				while(relatedFileRule == null && !parentConfKey.equals("root")) {
					if(allRulesForThisEntry[1].equalsIgnoreCase(PWCDeltaConfiguration.RELATED_FILE_CONTENT_RULE)) {
						if(parentConfKey.contains("pagefiles")) {
							parentConfKey = parentConfKey+".page";
		        		}else if(parentConfKey.contains("sourcefiles")) {
							parentConfKey = parentConfKey+".program";
		        		}
						
						relatedFileRule = deltaConfiguration.get(parentConfKey+".changedfile.relatedfilename");
					}else if(allRulesForThisEntry[1].equalsIgnoreCase(PWCDeltaConfiguration.RELATED_FILE_RULE)) {
						relatedFileRule = deltaConfiguration.get(parentConfKey+".changedfile.changedline.relatedfilename");
					}
					parentConfKey = confRuleKey.substring(0,confRuleKey.lastIndexOf(DOT_SEPARATOR));
				}
				
			}
			
		}
		return relatedFileRule;
	}



	/**
	 * @param entry
	 * @param relatedFileRule
	 * @param targetFileName
	 * @return
	 * @throws IOException
	 */
	private static File getRelatedFileObject(Path entry, String relatedFileRule, String targetFileName)
			throws IOException {
		File relatedFile =null;
		if(relatedFileRule !=null) {
			String relatedFileName = null;
			Path parentFolder = entry.getParent();
			String[] dirStructrure = relatedFileRule.split("/");
			for(int i=0; i < dirStructrure.length; i++) {
				String fileDirName = dirStructrure[i];
				if(fileDirName.equals(DOT_SEPARATOR + DOT_SEPARATOR)) {
					parentFolder = parentFolder.getParent();
				}else if(fileDirName.contains("$")) {
					relatedFileName = targetFileName;
				}else {
					relatedFileName = fileDirName;
				}
			}			
			relatedFile = new File(parentFolder.toFile().getCanonicalPath() + File.separator + relatedFileName);	
		}
		return relatedFile;
	}

	/**
	 * 
	 * @param iDependentFile
	 * @param iAdminObjectName
	 * @return
	 */
	public static String extractSpinnerLineByAdminObjectName(File iDependentFile, String iAdminObjectName ){
		logger.info("From this DependentFile : "+iDependentFile +" get row with this key: "+iAdminObjectName);
		//Add this file
		String rowAsString = null;
		try(Reader targetReader = new FileReader(iDependentFile)){
			
			 CSVReader csvReader = new CSVReader(targetReader, PWCDeltaExtractor.TAB_SEPERATOR);
			    String[] nextRecord;
			    while ((nextRecord = csvReader.readNext()) != null) {
			    	
			    	if(iAdminObjectName.equals(nextRecord[0])) {
			    		rowAsString = Arrays.toString(nextRecord);
			    		if(relatedFileDeltaStore.get(iDependentFile.getName()) == null) {
			    			relatedFileDeltaStore.put(iDependentFile.getName(), new ArrayList<String>());
			    		}
			    		//Added extracted content to the SpinnerPageData.xls, SpinnerProgramData.xls, SpinnerInquiryData.xls
			    		relatedFileDeltaStore.get(iDependentFile.getName()).add(rowAsString);
			    		
			    		//TODO: Do we need to break the loop once entry is found?
			    	}
			    	
			    	
			    }
			    targetReader.close();
			    csvReader.close();
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			
		}
		return rowAsString;
		
	}

	/**
	 * 
	 * @param iDependentFile
	 * @throws IOException
	 */
	public static void extractDependentFileByName(File iDependentFile) throws IOException{
		//Add this file
		FileUtils.copyFile(iDependentFile, PWCDeltaExtractorUtils.getCorrespondingDeltaOutputFileFromTarget(iDependentFile));	
	}
	
	/**
	 * @param numberofPrimaryColumns
	 * @param spinnerFileRow
	 * @return
	 */
	public static String getPrimaryKey(Integer numberofPrimaryColumns, String[] spinnerFileRow) {
		String primaryKey = null;
		if(numberofPrimaryColumns == 0) {
			primaryKey = Arrays.toString(spinnerFileRow);	
		}else {
			primaryKey = spinnerFileRow[0];
			for(int i=1; i < numberofPrimaryColumns ;i++) {
				primaryKey = primaryKey + PWCDeltaExtractor.TAB_SEPERATOR +spinnerFileRow[i];
			}
		}
		return primaryKey;
	}
	

	
	/**
	 * Checksum.
	 *
	 * @param file the file
	 * @return the string
	 */
	public static String checksum(File file) throws IOException{
		StringBuilder strDigest = new StringBuilder("0x");
		
		try (InputStream fileInputStream = new FileInputStream(file)) {

			java.security.MessageDigest md5er = MessageDigest.getInstance("MD5");
			byte[] buffer = new byte[1024];
			int read;

			do {
				read = fileInputStream.read(buffer);
				if (read > 0)
					md5er.update(buffer, 0, read);
			} while (read != -1);

			byte[] digest = md5er.digest();

			if (digest == null) {
				return null;
			}

			for (int i = 0; i < digest.length; i++) {
				strDigest = strDigest
						.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1).toUpperCase());
			}
			
		} catch (NoSuchAlgorithmException e) {
			logger.error("NoSuchAlgorithmException :"+e);
		}
		return strDigest.toString();
	}

	
	
	
	/**
	 * Read properties file.
	 *
	 * @param fileName the file name
	 * @return the properties
	 */
	public static Properties readPropertiesFile(String fileName) {
		logger.info("Resource File for Delta Configuration :- " + fileName);
		Properties properties = null;
		try {
			properties = new Properties();

			if (fileName.contains("/") || fileName.contains("\\")) {
				logger.debug("Inside If condition : " + fileName);
				properties.load(new FileInputStream(fileName));

			} else {
				logger.debug("Inside else condition : " + fileName);
				properties.load(PWCDeltaExtractorUtils.class.getResourceAsStream("/" + fileName));
			}

		} catch (FileNotFoundException exception) {
			logger.error("Exception Occurs", exception);
		} catch (IOException ioe) {
			logger.error("Exception Occurs", ioe);
		}
		return properties;
	}

	/**
	 * Configure logger.
	 *
	 * @param logFilePath the log file path
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void configureLogger(String logFilePath) throws IOException {

		Logger.getRootLogger().setLevel(Level.ALL);

		// Uncomment below two line to use File Appender
		Layout layout = new PatternLayout("%d [%t] %-5p %c{2} - %m%n");
		Logger.getRootLogger().addAppender(new FileAppender(layout, logFilePath));

		// Use it while working on JAVA IDE
		//layout = new PatternLayout("%t | %m%n");
		Logger.getRootLogger().addAppender(new ConsoleAppender(layout));

	}
	
	/**
	 * @return the relatedFileDeltaStore
	 */
	public static Map<String, List<String>> getRelatedFileDeltaStore() {
		return relatedFileDeltaStore;
	}
}