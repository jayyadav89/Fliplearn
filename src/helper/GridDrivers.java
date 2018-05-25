package helper;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class GridDrivers{
	public static boolean isParallelExecution;
	public String AppiumPath = "C:/Users/Appium";
	public String workingDirectory = System.getProperty("user.dir");

	public static void main(String[] args) throws IOException, InterruptedException
	{
		GridDrivers gd = new GridDrivers();
		gd.checkForParallelExecution(args[0]);
		if(!isParallelExecution)
		{
			System.out.println("No Parallel execution was requested");
			isParallelExecution = false;
			return;
		}
		List<String> devices = gd.getConnectedDevices();
		List<Integer> ports = gd.getPortsForRunning();
		int noOfDevices = devices.size();
		if(noOfDevices<1)
		{
			System.out.println("No conected device found");
			return;
		}
		for(int i=0;i<noOfDevices;i++)
		{	
			gd.createNodeConfigJsonFile(devices.get(i), ports.get(i), "nodeConfig_"+i+"");
		}
		gd.launchSeleniumGridHub();
		gd.launchConfigNodes(devices,ports);
		System.out.println("Appium Ports and Grid Succesfully Launched");
		Thread.sleep(25000);
	}

	@SuppressWarnings("unchecked")
	public void createNodeConfigJsonFile(String devicename, int port, String jsonName)
	{
		JSONObject object = new JSONObject();
		JSONArray array = new JSONArray();
		System.out.println("*****Creating Json files**************");
		JSONObject object_cap = new JSONObject();
		object_cap.put("browserName", "android");
		object_cap.put("version", "5.0");
		object_cap.put("platform", "android");
		object_cap.put("maxInstances", 5);
		object_cap.put("deviceName", devicename);
		array.add(object_cap);
		object.put("capabilities", array);

		JSONObject object_config = new JSONObject();
		object_config.put("nodeTimeout", 120);
		object_config.put("port", port);
		object_config.put("hubPort", 4444);
		object_config.put("url", "http://127.0.0.1:"+port+"/wd/hub");
		object_config.put("hub", "http://127.0.0.1:4444/grid/register");
		object_config.put("proxy", "org.openqa.grid.selenium.proxy.DefaultRemoteProxy");
		object_config.put("hubHost", "127.0.0.1");
		object_config.put("host", "127.0.0.1");
		object_config.put("nodePolling", 2000);
		object_config.put("registerCycle", 5000);
		object_config.put("register", true);
		object_config.put("cleanUpCycle", 120);
		object_config.put("timeout", 60000);
		object_config.put("maxSession", 5);
		object.put("configuration", object_config);

		String path = System.getProperty("user.dir") + "/ParallelExecution/"+jsonName+"" +".json";
		writeFile(path, object.toString());
		System.out.println("***************Json files Created**************");
	}

	public void launchSeleniumGridHub() throws IOException
	{
		String cmd;
		String path = workingDirectory + "/ParallelExecution/GridServer.bat";
		String command = "java -jar "
				+ workingDirectory
				+ "/jars/selenium-server-standalone-2.50.1.jar -host 127.0.0.1 -port 4444 -role hub -nodeTimeout 600";
		writeFile(path, command);
		System.out.println("*******Stoping Hub Port*********");
		stopPort("4444");
		System.out.println("**************Hub Port Stopped*********");
		try {
			Runtime rt = Runtime.getRuntime();
			System.out.println("Launching Selenium Grid");
			cmd = "cmd /c start /W " + workingDirectory + "/ParallelExecution/GridServer.bat";
			rt.exec(cmd);
			System.out.println("Selenium Grid Launched");
		} 
		catch (IOException e) {
			System.out.println("Selenium Grid Not Launched");
			e.printStackTrace();
		}
	}

	public void launchConfigNodes(List<String> devices, List<Integer> ports) throws IOException
	{
		int noOfDevices = devices.size();
		String command ="";String path;String cmd;
		for(int i=0;i<noOfDevices;i++)
		{
			path = workingDirectory + "/ParallelExecution/nodeConfig_"+i+"" +".bat";
			command = AppiumPath + "/node.exe " + AppiumPath + "/node_modules/appium/bin/appium.js --nodeconfig "
					+ workingDirectory + "/ParallelExecution/" + "nodeConfig_"+i+".json" + " -p "+ ports.get(i) + " -U " + devices.get(i);
			//+ " --session-override";
			writeFile(path, command);
			Runtime rt = Runtime.getRuntime();
			try {
				cmd = "cmd /c start " + workingDirectory + "/ParallelExecution/nodeConfig_"+i+"" +".bat";
				rt.exec(cmd);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public List<Integer> getPortsForRunning() throws IOException
	{
		int initialPortNumber=4723;
		List<Integer> ports = new ArrayList<Integer>();
		int size = getConnectedDevices().size();
		for(int i=0;i<size;i++)
		{
			stopPort(Integer.toString(initialPortNumber));
			ports.add(initialPortNumber);
			initialPortNumber = initialPortNumber+10;
		}
		return ports;
	}

	public List<String> getConnectedDevices()
	{
		List<String> devices = new ArrayList<String>();
		String command = "adb devices";
		String temp = null;
		try {
			Runtime rt = Runtime.getRuntime();
			Process proc = rt.exec(command);
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			String s;
			while((s = stdInput.readLine()) != null){
				if(s.contains("device") && (!s.contains("List of devices attached")))
				{
					temp= s.split("device")[0].trim();
					devices.add(temp);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return devices;
	}

	public void checkForParallelExecution(String ParallelExecution)
	{
		if(ParallelExecution.equalsIgnoreCase("yes"))
		{
			isParallelExecution = true;
		}
	}

	public static boolean parallelExecution()
	{
		if(isParallelExecution);
		{
			return true;
		}
	}

	/***********************************************************************************************
	 * Function Description : This will write any file. 
	 * For Grid we are using this to write json and bat commands for starting grid hub and nodes
	 * @author rajat.jain, date: 08-April-2016
	 * *********************************************************************************************/

	public void writeFile(String path, String content) {
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(path));
			writer.write(content);
		} catch (IOException e) {

		} finally {
			try {
				if (writer != null)
					writer.close();
			} catch (IOException e) {
			}
		}

	}


	/***********************************************************************************************
	 * Function Description : This will start grid hub and node. 
	 * Pass the command to start grid and node  
	 * @author rajat.jain, date: 08-April-2016
	 * *********************************************************************************************/

	public void StartGridHubAndNode(String command, int i) throws IOException,
	InterruptedException {
		String directory;
		directory = System.getProperty("user.dir");
		directory = directory.replace("\\", "/");
		writeFile(directory + "/" + "node" + i + ".bat", command);

		try {
			String cmmd = "cmd /c start " + directory + "/" + "node" + i
					+ ".bat";
			Process p = Runtime.getRuntime().exec(cmmd);
			i++;
		} catch (IOException ex) {
		}
	}

	/***********************************************************************************************
	 * Function Description :This will stop port.  
	 * Pass the command to start grid and node  
	 * @author rajat.jain, date: 08-April-2016
	 * *********************************************************************************************/

	public void stopPort(String port) throws IOException {
		Runtime.getRuntime().exec("cmd.exe");
		String command = "cmd /c echo off & FOR /F \"tokens=5 delims= \" %a IN ('netstat -a -n -o ^| findstr :"
				+ port + "') do taskkill /F /PID  %a";
		String s = null;
		try {
			Process p = Runtime.getRuntime().exec(command);
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(
					p.getInputStream()));
			BufferedReader stdError = new BufferedReader(new InputStreamReader(
					p.getErrorStream()));
//			while ((s = stdInput.readLine()) != null) {
//				System.out.println(s);
//			}
			//			while ((s = stdError.readLine()) != null) {
			//				System.out.println(s);
			//			}
			System.out.println("Port : "+port+" has been stopped");
		} 
		catch (IOException e) {
			System.out.println("Exception happened:While stopping port ");
			e.printStackTrace();
		}
	}

	/* Function :- This will Get the ip address of system.Require to generate jsons  
	 * author: Satya Prakash, date: 08-April-2016
	 */
	public String GetIpAddress() {
		String ipname = "";
		Enumeration<NetworkInterface> nets = null;
		try {
			nets = NetworkInterface.getNetworkInterfaces();
		} catch (SocketException e1) {
			// TODO Auto-generated catch block

			e1.printStackTrace();
		}
		for (NetworkInterface netint : Collections.list(nets)) {
			try {
				ipname = displayInterfaceInformation(netint);
			} catch (SocketException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (!ipname.equals("")) {

				break;
			}
		}
		ipname = ipname.replace("/", "");
		ipname = ipname.trim();
		System.out.println(ipname);
		return ipname;
	}

	/* Function :- Complete process to start grid and nodes. Description mentioned in code  
	 * author: Satya Prakash, date: 08-April-2016
	 */
	public void StartGrid() throws IOException, InterruptedException {

		final GridDrivers grid = new GridDrivers(); 
		List<String> devices = new ArrayList<String>();
		HashMap<String, String> data = new HashMap<String,String>();
		String directory;
		String commonResources = System.getenv("Common_Resources");
		String appiumDirectory = "C:/Appium";
		commonResources = commonResources.replace("\\", "/");
		grid.stopPort("4444"); // stop grid hub if already working
		String ip = "127.0.0.1"; // Get Ip address
		devices = getConnectedDevices();
		int numberOfDevices = devices.size();
		String device[] = new String[numberOfDevices];

		directory = System.getProperty("user.dir");
		directory = directory.replace("\\", "/");
		String command[]= new String[numberOfDevices+1]; // In this array grid command and node command will get save
		Thread[] threads = new Thread[numberOfDevices+1]; // thread to start grid and nodes

		if (numberOfDevices == 0) {
			return;
		}
		for (int i = 0; i < numberOfDevices; i++)
		{
			device[i] = devices.get(i).toString();
			int port;
			if (i == 0)
			{
				port = 4723;
			} else
			{
				port = 4723 + i * 10; //making different port numbers for different devices
			}

			// Note If You are changing hub port than you have to change it in start driver of android in generic function class
			grid.makeJson(4444, ip, port, device[i].toString().trim(),device[i]+".json");
			data.put("port"+i, port+"");
			data.put("deviceId"+i,device[i]);
			data.put("jsonFile"+i, device[i]+".json");
			File dir = new File(device[i]+"_Grid");
			dir.mkdir();
			data.put("Gridfolder" + i, device[i] + "_Grid");

			if (i == 0) {
				grid.stopPort("4723");
			} else {
				int portnum = 4723 + i * 10;
				String portToStop = portnum + "";
				grid.stopPort(portToStop);
			}
		}

		// This for loop is till i equals numberofDevices and above for loop is for i equals numberofdevices-1 		
		for (int i = 0; i <= numberOfDevices; i++) {
			if (i == 0) {
				command[i] = "java -jar "
						+ commonResources
						+ "/jars/selenium-server-standalone-2.47.1.jar -host 127.0.0.1 -port 4444 -role hub";
			} else {
				int j = i - 1;
				command[i] = appiumDirectory + "/node.exe " + appiumDirectory
						+ "/node_modules/appium/bin/appium.js --nodeconfig "
						+ directory + "/" + data.get("jsonFile" + j) + " -p "
						+ data.get("port" + j).trim() + " -U "
						+ data.get("deviceId" + j) + " --tmp " + directory
						+ "/" + data.get("Gridfolder" + j);

			}
			final String commands = command[i].toString();
			final int j = i;

			threads[i] = new Thread() {
				public void run() {

					try {
						grid.StartGridHubAndNode(commands, j);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			};
			threads[i].start();
		}
	}






	/* Function :- Used in getipaddress function for getting ip  
	 * author: Satya Prakash, date: 08-April-2016
	 */
	public String displayInterfaceInformation(NetworkInterface netint)
			throws SocketException {

		Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();

		// Realtek PCIe GBE Family Controller
		if (netint.getDisplayName().contains("WiFi")) {
			for (InetAddress inetAddress : Collections.list(inetAddresses)) {
				return inetAddress + "";
				// out.printf("InetAddress: %s\n", inetAddress);
			}
			return "";
		} else if (netint.getDisplayName().contains(
				"Realtek PCIe GBE Family Controller")) {
			for (InetAddress inetAddress : Collections.list(inetAddresses)) {
				return inetAddress + "";
				// out.printf("InetAddress: %s\n", inetAddress);
			}
			return "";
		} else {
			return "";
		}
	}



	public void makeJson(int hubPort, String ip, int nodePort, String deviceId,
			String FileName) throws FileNotFoundException, IOException {
		//		APIFunctions api = new APIFunctions();
		//		JsonObject json = new JsonObject();
		//		JsonObject json2 = new JsonObject();
		//		JsonArray json3 = new JsonArray();
		//		JsonObject json5 = new JsonObject();
		//		String url = "http://" + ip + ":" + nodePort + "/wd/hub";
		//		String hub = "http://" + ip + ":" + hubPort + "/grid/register/";
		//
		//		json.addProperty("cleanUpCycle", 2000);
		//		json.addProperty("timeout", 30000);
		//		json.addProperty("proxy",
		//				"org.openqa.grid.selenium.proxy.DefaultRemoteProxy");
		//
		//		json.addProperty("url", url);
		//		json.addProperty("hub", hub);
		//		json.addProperty("host", ip);
		//		json.addProperty("port", nodePort);
		//		json.addProperty("maxSession", 5);
		//		json.addProperty("register", true);
		//		json.addProperty("registerCycle", 5000);
		//		json.addProperty("hubPort", hubPort);
		//		json.addProperty("hubHost", ip);
		//
		//		JsonObject json1 = api.MakeJsonObjectOfKeyAndJsonObject(
		//				"configuration", json);
		//		json2.addProperty("browserName", deviceId);
		//		String version;
		//		json2.addProperty("version", "4.3");
		//		json2.addProperty("maxInstances", 5);
		//		json2.addProperty("platform", "Android");
		//		json3.add(json2);
		//		JsonObject json4 = api.MakeJsonObjectOfKeyAndJsonArray("capabilities",
		//				json3);
		//		String finalJson = api.mergeJsonObjects(json4, json1);
		//		// System.out.println(json5);
		//		String directory;
		//		directory = System.getProperty("user.dir");
		//		directory = directory.replace("\\", "/");
		//		writeFile(directory + "/" + FileName, finalJson);
		//
		//	}
	}
}
