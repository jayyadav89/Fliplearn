package reporting;


import helper.GridDrivers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import reporting.TestData;
import utils.Xslt_XlsReader;


public class WriteXMLFile extends TestData {

	Xslt_XlsReader xls= null;
	String directory = "";

	public static void main(String[] args) throws IOException{
		WriteXMLFile f = new WriteXMLFile();
		f.Initialize_ConfigVariables(args[0]);
		f.assignXLS();
		f.CreateXML();
	}

	public void assignXLS(){
		directory= System.getProperty("user.dir");
		directory = directory.replace("\\", "\\\\");
		try{
			for(int i=2;i<=TestData.globalXLS.getRowCount("Sheet1");i++){
				if(TestData.globalXLS.getCellData("Sheet1", "Refer_Sheet", i).equalsIgnoreCase("Y")){
					reporting.TestData.xlsPath= directory + "/TestCaseCreation/"+ TestData.globalXLS.getCellData("Sheet1", "Reference_Sheet_Name", i);
					xls = new Xslt_XlsReader(directory + "/TestCaseCreation/"+ TestData.globalXLS.getCellData("Sheet1", "Reference_Sheet_Name", i));
					CreateXML();
					break;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void CreateXML() throws IOException {
		if(xls==null)
			System.out.println("PLEASE ASSIGN A TEST CASE SHEET IN '''excelSheetRef.xls''' FILE");

		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("suite");
			doc.appendChild(rootElement);
			Attr attr = doc.createAttribute("name");

			File f = new File("");
			String name =f.getAbsolutePath();
			String mainName = name.substring(name.lastIndexOf("/")+1, name.length());

			//attr.setValue(mainName);
			attr.setValue("FlipLearn Education Suite");
			rootElement.setAttributeNode(attr);

			Attr attr_parallel = doc.createAttribute("parallel");
			attr_parallel.setValue(TestData.TypeofParallelExecution);
			rootElement.setAttributeNode(attr_parallel);

			Attr attr_thread = doc.createAttribute("thread-count");
			//GridDrivers gd = new GridDrivers();
			//TestData.NoofThreads = Integer.toString(gd.getConnectedDevices().size()+1);
			attr_thread.setValue(TestData.NoofThreads);
			rootElement.setAttributeNode(attr_thread);

			Element listeners = doc.createElement("listeners");
			rootElement.appendChild(listeners);
			Element listener1 = doc.createElement("listener");
			Attr list1 = doc.createAttribute("class-name");
			list1.setValue("reporting.TakeScreenshot");
			listener1.setAttributeNode(list1);
			Element listener2 = doc.createElement("listener");
			Attr list2 = doc.createAttribute("class-name");
			list2.setValue("reporting.TestNGError");
			listener2.setAttributeNode(list2);
			//Element listener3 = doc.createElement("listener");
			//Attr list3 = doc.createAttribute("class-name");
		//	list3.setValue("utils.RetryListenerClass");
			//listener3.setAttributeNode(list3);
			listeners.appendChild(listener1);
			listeners.appendChild(listener2);
			//listeners.appendChild(listener3);



			for(int l=2;l<=TestData.globalXLS.getRowCount("Sheet1");l++)
			{
				if(TestData.globalXLS.getCellData("Sheet1", "Refer_Sheet", l).equalsIgnoreCase("Y"))
				{
					xls = new Xslt_XlsReader(directory + "/TestCaseCreation/"+TestData.globalXLS.getCellData("Sheet1", "Reference_Sheet_Name", l));
				}
				else{
					continue;
				}

				int packageCounter = xls.getRowCount("PackageSheet");
				int testCaseCounter = xls.getRowCount("TestCaseSheet");

				for (int i=2;i<=packageCounter;i++){

					String moduleName = xls.getCellData("PackageSheet", "ClassFileName", i);

					Element test = doc.createElement("test");
					rootElement.appendChild(test);
					Attr attr1 = doc.createAttribute("name");
					attr1.setValue(moduleName);
					test.setAttributeNode(attr1);
					Attr attr2 = doc.createAttribute("preserve-order");
					attr2.setValue("true");
					test.setAttributeNode(attr2);

					Element classes = doc.createElement("classes");
					test.appendChild(classes);

					Element class1 = doc.createElement("class");
					classes.appendChild(class1);
					String className = xls.getCellData("PackageSheet", "PackageName", i)+"."+xls.getCellData("PackageSheet", "ClassFileName", i);
					Attr attrr = doc.createAttribute("name");
					attrr.setValue(className);
					class1.setAttributeNode(attrr);

					Element methods = doc.createElement("methods");
					class1.appendChild(methods);

					for (int j=2;j<=testCaseCounter;j++){
						String classNameWithinTags = xls.getCellData("TestCaseSheet", "PackageName", j)+"."+xls.getCellData("TestCaseSheet", "ClassFileName", j);
						if(className.equalsIgnoreCase(classNameWithinTags)){
							if(xls.getCellData("TestCaseSheet", "RunAutomation_" + TestorLive, j).equalsIgnoreCase("Yes") || xls.getCellData("TestCaseSheet", "RunAutomation_" + TestorLive, j).equalsIgnoreCase("Y")){
								Element include = doc.createElement("include");
								methods.appendChild(include);
								Attr incAtt = doc.createAttribute("name");
								incAtt.setValue(xls.getCellData("TestCaseSheet", "TestCaseID", j));
								include.setAttributeNode(incAtt);
							}
							else{
//								Element exclude = doc.createElement("exclude");
//								methods.appendChild(exclude);
//								Attr incAtt = doc.createAttribute("name");
//								incAtt.setValue(xls.getCellData("TestCaseSheet", "TestCaseID", j));
//								exclude.setAttributeNode(incAtt);
							}
						}
					}
				}
			}

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			File file = new File("testng.xml");
			StreamResult result = new StreamResult(file);
			transformer.transform(source, result);
			System.out.println("XML File saved!");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}

	public void Initialize_ConfigVariables(String testorlive){
		if(!testorlive.equalsIgnoreCase("${environment}"))
			TestorLive = testorlive.trim();


		System.out.println("TestorLive = " + TestorLive);
		System.out.println("Device_Name = " + Device_Name);

		try {
			File configfile = new File("Config.properties");
			FileWriter fileWritter = new FileWriter(configfile, true);
			BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
			bufferWritter.newLine();
			bufferWritter.append("TestorLive=" + TestorLive);
			bufferWritter.newLine();
			bufferWritter.append("Device_Name=" + Device_Name);
			bufferWritter.newLine();
			bufferWritter.close();
			fileWritter.close();		

			/*
            Properties props = new Properties();
            FileInputStream fis = new FileInputStream(configfile);
			FileOutputStream fos = new FileOutputStream(configfile);
			props.load(fis);
			props.put(key, value)
			props.setProperty("TestorLive", TestorLive);
			props.setProperty("Execution_Type", Execution_Type);
			props.setProperty("Driver_Type",Driver_Type);
			props.store(fos, "Properties File generated through ANT build.xml");
			fis.close();
			fos.close();*/

		} catch (Exception e) {
			System.out.println("Failed to Generate Config.Properties!!");
			e.printStackTrace();
		}

	}


}
