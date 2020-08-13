/*
 ** PWCHelpDesk.java
 **
 ** Copyright (c) 1993-2012 Dassault Systemes. All Rights Reserved.
 ** This program contains proprietary and trade secret information of
 ** Dassault Systemes.
 ** Copyright notice is precautionary only and does not evidence any actual
 ** or intended publication of such program
 ** This Java class contains method to get the data from helpdesk.xml
 ** @author DS
 ** @version Phase1.Main
 */
package com.ds.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import matrix.db.Context;
import matrix.util.StringList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.apache.log4j.Logger;

public class PWCHelpDesk {
    
	private static final Logger log = Logger.getLogger("PWCHelpDesk");
	//change this value if the code is modified (the value should be the next known delivery date)
	private static final String CODE_VERSION = "2012_30_11";
	private static final String PWC_ENOVIA_EXTERNAL_CONFIG_FOLDER_PATH = "PWC_ENOVIA_EXTERNAL_CONFIG_FOLDER_PATH";
	private static final String PWC_ENOVIA_HELPDESK_XML_FILE_NAME = "ENOVIA_HELPDESK.xml";
	
	static private StringList IPP_Person_List=new StringList();
	static private StringList RFA_Person_List=new StringList();
	static private StringList RMT_Person_List=new StringList();
	
	 public PWCHelpDesk ()throws Exception
    {
      log.debug("ctor()");
	  log.debug("code version is: " + CODE_VERSION);
    }

		/**
		 * Block to read the helpdesk xml 
		 * @param context
		 * @throws Exception
		 */
	static{
		
		log.debug("Start of PWCHelpDask:static block"); 
		try{
			InputStream is = null;
			String strConfigFolderPath = System.getenv( PWC_ENOVIA_EXTERNAL_CONFIG_FOLDER_PATH );
			
			if(strConfigFolderPath != null && !"".equals(strConfigFolderPath) && new File(strConfigFolderPath).exists()){
				log.debug("INFO: " + strConfigFolderPath + " folder path declared in enovia environment file");
				File configFile = new File(strConfigFolderPath + File.separator + PWC_ENOVIA_HELPDESK_XML_FILE_NAME);
				if (configFile.exists())
				{
					is = new FileInputStream( configFile );
				}
			}
			if(null == is)
			{
				is = PWCHelpDesk.class.getClassLoader().getResourceAsStream("PWC_ENOVIA_HELPDESK_XML_FILE_NAME");
				if (null == is)
				{
					log.error("ERROR: Cannot find the configuration file: [" + PWC_ENOVIA_HELPDESK_XML_FILE_NAME + "]");
					throw new Exception("Cannot find the configuration file: [" + PWC_ENOVIA_HELPDESK_XML_FILE_NAME + "]");
				}
				else
				{
					log.debug("Configuration file: [" + PWC_ENOVIA_HELPDESK_XML_FILE_NAME+ "] loaded from web stack .../enovia/WEB-INF/classes");
				}
			}
			else
			{
				log.debug("Configuration file: [" + PWC_ENOVIA_HELPDESK_XML_FILE_NAME+ "] loaded from env variable: " + PWC_ENOVIA_EXTERNAL_CONFIG_FOLDER_PATH + " = " + strConfigFolderPath);
			}
	
			
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			
			Document doc = docBuilder.parse(is);
			
			NodeList listOfPersons = doc.getElementsByTagName("Person");
	        int totalPersons = listOfPersons.getLength();
	       
	       for(int s=0; s<listOfPersons.getLength() ; s++)
	       {
	       	   HashMap personMap = new HashMap();
	           Node personNode = listOfPersons.item(s);
	           Element personElement = (Element)personNode;
	           Node strParentNode = personElement.getParentNode();
	           String strParentNodeName = strParentNode.getNodeName();
	           
	           NodeList nameList = personElement.getElementsByTagName("Name");
	           Element nameElement = (Element)nameList.item(0);

	           NodeList textNameList = nameElement.getChildNodes();
	           String strName = textNameList.item(0).getNodeValue().trim();
	           personMap.put("Name", strName);
	           
	           //-------
	           NodeList mailList = personElement.getElementsByTagName("Mail");
	           Element mailElement = (Element)mailList.item(0);

	           NodeList textMailList = mailElement.getChildNodes();
	           String strMail = textMailList.item(0).getNodeValue().trim();
	           personMap.put("Mail", strMail);
	           
	           //----
	           NodeList locationList = personElement.getElementsByTagName("Location");
	           Element locationElement = (Element)locationList.item(0);

	           NodeList textLocationList = locationElement.getChildNodes();
	           String strLocation = textLocationList.item(0).getNodeValue().trim();
	           personMap.put("Location", strLocation);
	         //-------
	           NodeList Contact_NumberList = personElement.getElementsByTagName("ContactNumber");
	           Element Contact_NumberEle = (Element)Contact_NumberList.item(0);

	           NodeList textCNList = Contact_NumberEle.getChildNodes();
	           String strContactNumber = textCNList.item(0).getNodeValue().trim();
	           personMap.put("ContactNumber", strContactNumber);
	           
	           if(strParentNodeName.equalsIgnoreCase("IPP"))
	           {
	        	   IPP_Person_List.add(personMap);
	           	
	           }else if(strParentNodeName.equalsIgnoreCase("RFA"))
	           {
	        	   RFA_Person_List.add(personMap);
	           	
	           }else if(strParentNodeName.equalsIgnoreCase("RMT"))
	           {
	        	   RMT_Person_List.add(personMap);
	           }
                  System.out.println("--RMT_Person_List--->" + RMT_Person_List);
	       }
	       			
		}catch(Exception e)
		{
			log.error("exception in HelpDask:static block");
			e.printStackTrace();
		}
		
	}

      public StringList getIPPList()
	   {
    	  log.debug("PWCHelpDask:getIPPList"); 
		  return IPP_Person_List;
	   }
      public StringList getRFAList()
	   {
    	  log.debug("PWCHelpDask:getRFAList"); 
		  return RFA_Person_List;
	   }
      public StringList getRMTList()
	   {
    	  log.debug("PWCHelpDask:getRMTList"); 
		  return RMT_Person_List;
	   }
		
		
	
}
