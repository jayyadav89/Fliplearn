package reporting;

import utils.Xslt_XlsReader;


public class TestData{

	public static String TestCaseId = "";
	public static String sMessages="";
	public static String TypeofParallelExecution = "tests";  // "tests - classes"  &&  "methods - testcases"   
	public static String NoofThreads = "1";
	
	public static String TestorLive = "";
	public static String Datapath = "";
	public static String Execution_Type = "";
	public static String Device_Name="Emulator Nexus 5";

	
	public static Xslt_XlsReader globalXLS= new Xslt_XlsReader("./TestCaseCreation/excelSheetRef.xls");
	public static String xlsPath= xlsPath();
	
	private static String xlsPath(){
		String directory= System.getProperty("user.dir");
		directory = directory.replace("\\", "\\");
		String path="";
		for(int i=2;i<=globalXLS.getRowCount("Sheet1");i++){
			if(globalXLS.getCellData("Sheet1", "Refer_Sheet", i).equalsIgnoreCase("Y")){
				path= directory + "/TestCaseCreation/" + globalXLS.getCellData("Sheet1", "Reference_Sheet_Name", i) + "," + path;
			}
		}
		return path;
	}
}
