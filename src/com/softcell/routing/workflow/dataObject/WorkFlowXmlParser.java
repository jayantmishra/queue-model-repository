package com.softcell.routing.workflow.dataObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class WorkFlowXmlParser {

	Rule oRule;
	

	
	
	public static Rule parseWorkflowXml() 
	{
		
		InputStream is = WorkFlowXmlParser.class.getClassLoader().getResourceAsStream("sample_workflow.xml");
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			
			e.printStackTrace();
		}
		
		Document doc = null;
		try {
			doc = builder.parse(is);
		} catch (SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		doc.getDocumentElement().normalize();
		
		Element rootElement = doc.getDocumentElement();
		
		NodeList nList = doc.getElementsByTagName("Workflow");
		Rule oRule = new Rule();
		for(int i= 0; i < nList.getLength(); i++)
		{
			Node nNode= (Node) nList.item(i);
			
			Element eElement = (Element) nNode;
			WorkflowData oWorkflowData = new WorkflowData();
			oWorkflowData.setId(Integer.parseInt(eElement.getAttribute("Id")));
			oWorkflowData.setName(eElement.getAttribute("Name"));
			oWorkflowData.setDesription(eElement.getAttribute("Description"));
			oWorkflowData.setStartItemId(Integer.parseInt(eElement.getAttribute("startItem")));
			oRule.oWorkflow.put(Integer.parseInt(eElement.getAttribute("Id")), oWorkflowData);
		}
		
		nList = doc.getElementsByTagName("Node");
		for (int i=0; i< nList.getLength(); i++)
		{
			Node nNode = (Node) nList.item(i);
			Element eElement = (Element) nNode;
			
			NodeList nodeList = eElement.getElementsByTagName("Item");
			
			for (int j = 0; j < nodeList.getLength(); j++)
			{
				Element eChildElement = (Element) (Node) nodeList.item(j);
						
				NodeData oNode=  new NodeData();
				oNode.setID(Integer.parseInt(eChildElement.getAttribute("Id")));
				oNode.setType(eChildElement.getAttribute("TYPE"));
				oNode.setRuleSetId(Integer.parseInt(eChildElement.getAttribute("RuleSetId")));
				oRule.oNode.put(Integer.parseInt(eChildElement.getAttribute("Id")), oNode);
						
			}
			
		}
		nList = doc.getElementsByTagName("RuleSet");
		for (int i=0 ; i< nList.getLength(); i++)
		{
			Node nNode = (Node) nList.item(i);
			
			Element eElement = (Element) nNode;
			
			NodeList nodeList = eElement.getElementsByTagName("Action");
			for(int j=0 ; j<nodeList.getLength(); j++)
			{
				RuleSet oRuleSet = new RuleSet();
				oRuleSet.setID(Integer.parseInt(eElement.getAttribute("id")));
				
				Element eChildElement = (Element) (Node) nodeList.item(j);
				oRuleSet.actionData.setID(Integer.parseInt(eChildElement.getAttribute("ID")));
				oRuleSet.actionData.setType(eChildElement.getAttribute("type"));
				oRuleSet.actionData.setValue(Integer.parseInt(eChildElement.getAttribute("Value")));
				oRule.oRuleSet.put(Integer.parseInt(eElement.getAttribute("id")), oRuleSet);
				
			}		
		}
		
		
		return oRule;
	}
}
