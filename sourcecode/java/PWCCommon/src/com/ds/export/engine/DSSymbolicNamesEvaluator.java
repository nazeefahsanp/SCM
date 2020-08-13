package com.ds.export.engine;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Map;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.matrixone.apps.domain.util.PropertyUtil;


public class DSSymbolicNamesEvaluator 
{
	private static final Logger LOG = Logger.getLogger("DSSymbolicNamesEvaluator");
	private static final String XML_SCHEMA = "ds_export_engine.xsd";
	private static final char START_VARIABLE = '{';
	private static final char END_VARIABLE = '}';
	private static final Map<String, Document> _docMap = new Hashtable<String, Document>();
	private static final String PWC_ENOVIA_EXTERNAL_CONFIG_FOLDER_PATH = "PWC_ENOVIA_EXTERNAL_CONFIG_FOLDER_PATH";
		
	public static synchronized Document getXMLDocument(String iTemplateName) throws Exception
	{
		LOG.info("--> IN getXMLDocument()");
		if(null == iTemplateName || iTemplateName.length() == 0)
		{
			LOG.error("The template name is not provided");
			throw new Exception("The template name is not provided");
		}
		Document domDoc = null;
		if(_docMap.containsKey(iTemplateName))
		{
			LOG.debug("XML Document already initialized");
			return _docMap.get(iTemplateName);
		}
		LOG.debug("XML Document has to be initialized");
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		InputStream in_mod = null;
		try
		{
			InputStream in_orig = null;
			//Loads the file  from Environmental Path
			String strConfigFolderPath = System.getenv( PWC_ENOVIA_EXTERNAL_CONFIG_FOLDER_PATH );
			if(strConfigFolderPath != null && !"".equals(strConfigFolderPath) && new File(strConfigFolderPath).exists()){
				LOG.debug("INFO: " + strConfigFolderPath + " folder path declared in enovia environment file");
				File configFile = new File(strConfigFolderPath + File.separator + iTemplateName);
				if (configFile.exists())
				{
					in_orig = new FileInputStream( configFile );

				}
			}
			if(null == in_orig)
			{
				in_orig =  DSSymbolicNamesEvaluator.class.getClassLoader().getResourceAsStream(iTemplateName);
				if(null == in_orig)
				{
					LOG.error("Unable to load the xml file: [" + iTemplateName + "]");
					throw new Exception("Unable to load the xml file: [" + iTemplateName + "]");
				}
				else
				{
					System.out.println("Configuration file: [" + iTemplateName+ "] loaded from web stack .../enovia/WEB-INF/classes");
					LOG.debug("Configuration file: [" + iTemplateName+ "] loaded from web stack .../enovia/WEB-INF/classes");
				}
			}
			else
			{
				System.out.println("Configuration file: [" + iTemplateName+ "] loaded from env variable: " + PWC_ENOVIA_EXTERNAL_CONFIG_FOLDER_PATH + " = " + strConfigFolderPath);
			LOG.debug("Configuration file: [" + iTemplateName+ "] loaded from env variable: " + PWC_ENOVIA_EXTERNAL_CONFIG_FOLDER_PATH + " = " + strConfigFolderPath);
			}
			in_mod = new DSSymbolicNamesEvaluator.SubstitutionInputStream(in_orig);
			StringBuffer ct_buff = new StringBuffer();
			int ch;
			while((ch = in_mod.read()) != -1)
			{
				ct_buff.append((char) ch);
			}
			
			domDoc = builder.parse(new InputSource(new StringReader(ct_buff.toString())));
			
			if(null == domDoc)
			{
				LOG.error("Failed to parse the xml content: [" + ct_buff.toString() + "]");
				throw new Exception("Failed to parse the xml input file: [" + ct_buff.toString() + "]");
			}
			//validate the xml against the schema definition
			
			//Commented the below line and added the line below by passing "com.sun.org.apache.xerces.internal.jaxp.validation.XMLSchemaFactory" explicitly
			// because the validate() was failing due to the class reference was going to a package in tomee in which the implementation is wrong.
			// By calling the com.sun.org.*** explicitly the reference is going to java library where the implementation is right.
			
			//SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI, "com.sun.org.apache.xerces.internal.jaxp.validation.XMLSchemaFactory",null);
			Schema schemaXML = schemaFactory.newSchema(DSSymbolicNamesEvaluator.class.getClassLoader().getResource(XML_SCHEMA));
			if(null == schemaXML)
			{
				LOG.error("Failed to load xml schema:[" + XML_SCHEMA + "]");
				throw new Exception ("Failed to load xml schema:[" + XML_SCHEMA + "]");
			}
			Validator validatorXML = schemaXML.newValidator();
			
			try
			{
				validatorXML.validate(new DOMSource(domDoc));
			}
			catch (SAXException saxe)
			{
				LOG.error("The evaluated xml cannot be validated against the schema definition");
				if(LOG.isDebugEnabled())
				{
					LOG.debug("The evaluated xml is: ");
					LOG.debug("=============================================================");
					LOG.debug(getStringFromXMLDocument(domDoc));
					LOG.debug("=============================================================");
				}
				//stop the execution
				throw saxe;
			}
			_docMap.put(iTemplateName, domDoc);//cache the evaluated xml
		}
		finally
		{
			if(null != in_mod)
			{
				in_mod.close();
			}
		}
		LOG.info("<-- OUT getXMLDocument()");
		return domDoc;
	}
	
	private static class SubstitutionInputStream extends FilterInputStream
	{		
		private LinkedList<Character> _buf = new LinkedList<Character>();
		
		private SubstitutionInputStream(InputStream iIn)
		{
			super(iIn);
		}
		
		@Override
		public int read() throws IOException 
		{
			if(!_buf.isEmpty())
			{
				return _buf.remove();
			}
			int ch = super.read();
			if(ch == START_VARIABLE)
			{
				StringBuffer valToReplace = new StringBuffer();
				while((ch = super.read()) != END_VARIABLE)
				{
					if(ch == START_VARIABLE || ch == END_VARIABLE)
					{
						LOG.error("Failed in DSSymbolicNamesEvaluator.SubstitutionInputStream::read()");
						throw new IOException("Failed in DSSymbolicNamesEvaluator.SubstitutionInputStream::read()");
					}
					valToReplace.append((char)(ch));
				}
				char[] arrayCharNew = PropertyUtil.getSchemaProperty( valToReplace.toString()).toCharArray();
				if(null == arrayCharNew || arrayCharNew.length == 0)
				{
					LOG.error("Failed getting the value of the symbolic name: [" + valToReplace + "]");
					throw new IOException("Failed getting the value of the symbolic name: [" + valToReplace + "]");
				}
				for(char charToReturn : arrayCharNew)
				{
					_buf.add(charToReturn);
				}
				return _buf.remove();
			}
			else
			{
				if(ch == END_VARIABLE)
				{
					LOG.error("Failed in DSSymbolicNamesEvaluator.SubstitutionInputStream::read()");
					throw new IOException("Failed in DSSymbolicNamesEvaluator.SubstitutionInputStream::read()");
				}
				else
				{
					return ch;
				}
			}
		}
	}
	
	private static String getStringFromXMLDocument(Document doc) throws Exception
	{
		String xmlString = null;
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		StreamResult result = new StreamResult(new StringWriter());
		DOMSource source = new DOMSource(doc);
		transformer.transform(source, result);
		xmlString =  result.getWriter().toString();
		return xmlString;
	}

}
