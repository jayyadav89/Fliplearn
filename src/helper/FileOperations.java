package helper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import utils.LoadProperty;

public class FileOperations extends DriverSession{
	File file;
	LoadProperty loadProperty = new LoadProperty();
	String currentEnv = (String) loadProperty.getProperty("project.env");
	String fcontent = envpro.readProperty("ServerUrl");
	String fpath = System.getProperty("user.dir")+"/src/config/"+currentEnv+"/serverUrl.txt";

	public Boolean createAndWriteFile(){
		try {
			file = new File(fpath);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(fcontent);
			bw.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}


	public void addServerUrlFileInDevice()
	{
		createAndWriteFile();
		setPermessions();
		Runtime rt = Runtime.getRuntime();
		String command = "adb push "+fpath+" /";
		try	{rt.exec(command);
		System.out.println("File has been pushed");}
		catch (IOException e){e.printStackTrace();}
		finally{
			//file.delete();
		}
	}

	public void setPermessions()
	{
		Runtime rt = Runtime.getRuntime();
		String command = "adb shell && mount -o rw&&remount rootfs / && chmod 777 /mnt/sdcard && exit";
		try {rt.exec(command);}
		catch (Exception e) {}
	}

	@SuppressWarnings("resource")
	public String read(String fname){
		BufferedReader br = null;
		String response = null;
		try {
			StringBuffer output = new StringBuffer();
			br = new BufferedReader(new FileReader(fpath));
			String line = "";
			while ((line = br.readLine()) != null) {
				output.append(line +"n");
			}
			response = output.toString();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return response;
	}

	public void launchSimulator()
	{
		String path = System.getProperty("user.dir")+"/LaunchDevice.bat";
		BufferedReader stdInput;
		Process proc2;
		Runtime rt = Runtime.getRuntime();
		String command = "cmd /c start "+path+"";
		try {
			proc2= rt.exec(command);
			proc2 = rt.exec("adb devices");
			stdInput = new BufferedReader(new InputStreamReader(proc2.getInputStream()));
			System.out.println(stdInput);
			String s;
			while(!(s = stdInput.readLine()).contains(":5555"))
			{
				System.out.println(s);
				proc2 = rt.exec("adb devices");
				stdInput = new BufferedReader(new InputStreamReader(proc2.getInputStream()));
				s = stdInput.readLine();
			}
		}
		catch (Exception e) {}	
	}

	public List<String> getConnectedDevices()
	{
		List<String> devices = new ArrayList<String>();
		String command = "adb devices";
		String temp = null;
		Runtime rt = Runtime.getRuntime();
		try {
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
		System.out.println(devices.size());
		System.out.println(devices);
		return devices;
	}
	public static void main(String[] args) {
		FileOperations fop = new FileOperations();
		fop.getConnectedDevices();
	}
}