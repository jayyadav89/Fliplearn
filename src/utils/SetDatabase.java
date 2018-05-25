package utils;

import helper.DriverSession;
import helper.GenericFunctions;

import java.awt.geom.GeneralPath;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.SQLException;
import java.util.ArrayList;
public class SetDatabase extends DriverSession{

	public static ArrayList<String> listOfQueries = null;

	public ArrayList<String> createQueries(String path)  
	{ 
		String queryLine = new String();
		StringBuffer sBuffer = new StringBuffer();
		listOfQueries = new ArrayList<String>();
		try 
		{  
			FileReader fr =  new FileReader(new File(path));       
			BufferedReader br = new BufferedReader(fr);  

			//read the SQL file line by line
			while((queryLine = br.readLine()) != null)  
			{  
				// ignore comments beginning with #
				int indexOfCommentSign = queryLine.indexOf('#');
				if(indexOfCommentSign != -1)
				{
					if(queryLine.startsWith("#"))
					{
						queryLine = new String("");
					}
					else
						queryLine = new String(queryLine.substring(0, indexOfCommentSign-1));
				}
				// ignore comments beginning with --
				indexOfCommentSign = queryLine.indexOf("--");
				if(indexOfCommentSign != -1)
				{
					if(queryLine.startsWith("--"))
					{
						queryLine = new String("");
					}
					else
					{
						queryLine = new String(queryLine.substring(0, indexOfCommentSign-1));
					}
				}
				// ignore comments surrounded by /* */
				indexOfCommentSign = queryLine.indexOf("/*");
				if(indexOfCommentSign != -1)
				{
					if(queryLine.startsWith("#"))
					{
						queryLine = new String("");
					}
					else if(indexOfCommentSign == 0)
					{
						queryLine = new String("");
					}
					else
					{	
						queryLine = new String(queryLine.substring(0, indexOfCommentSign-1));	
					}

					sBuffer.append(queryLine + " "); 
					// ignore all characters within the comment
					do
					{
						queryLine = br.readLine();
					}
					while(queryLine != null && !queryLine.contains("*/"));
					if(queryLine != null)
					{
						indexOfCommentSign = queryLine.indexOf("*/");
					}
					if(indexOfCommentSign != -1 && queryLine != null)
					{
						if(queryLine.endsWith("*/"))
						{
							queryLine = new String("");
						}
						else
							queryLine = new String(queryLine.substring(indexOfCommentSign+2, queryLine.length()-1));
					}
				}

				//  the + " " is necessary, because otherwise the content before and after a line break are concatenated
				// like e.g. a.xyz FROM becomes a.xyzFROM otherwise and can not be executed 
				if(queryLine != null)
				{
					//System.out.println("Before Adding "+queryLine);
					//System.out.println("After Adding "+sBuffer.append(queryLine + " ")); 
					sBuffer.append(queryLine + " ");
				}
				//System.out.println(queryLine.trim());
			}  
			br.close();

			// here is our splitter ! We use ";" as a delimiter for each request 
			//System.out.println("Buffer is "+sBuffer);
			String[] splittedQueries = sBuffer.toString().split(";");

			// filter out empty statements
			for(int i = 0; i<splittedQueries.length; i++)
			{
				if(!splittedQueries[i].trim().equals("") && !splittedQueries[i].trim().equals("\t"))  
				{
					//System.out.println(splittedQueries[i].trim());
					listOfQueries.add(new String(splittedQueries[i].trim()));
				}
			}
		}  
		catch(Exception e)  
		{  
			System.out.println("Error : "+e.toString());  
			e.printStackTrace();   
			System.out.println(sBuffer.toString());  
		}
		return listOfQueries;
	}

	public static void uploadSQLFile() throws SQLException
	{
		SetDatabase sb = new SetDatabase();
		ArrayList<String> queries = null;
		String[] SqlPath = {"city.sql","platforms.sql","users.sql","outlet_category.sql","outlet_category_city_map.sql"};
		String[] SqlPath1 = {"city.sql","outlet_category.sql","outlet_category_city_map.sql","platforms.sql","users.sql"};
		System.out.println(SqlPath.length);
		for(int k=SqlPath1.length-1;k>=0;k--)
		{
			SqlPath1[k] = SqlPath1[k].split("\\.")[0].trim();
			DBConnection.executeUpdate("delete from "+SqlPath1[k]+"");
			System.out.println(SqlPath1[k]+" Table has been deleted.");
		}
		for(int j=0;j<SqlPath.length;j++)
		{
			queries = sb.createQueries(GenericFunctions.getCurrentDirectory()+"/"+SqlPath[j]);
			for(String query : queries)
			{
				System.out.println("query for execution is "+query);
				if(query.contains("CREATE DATABASE"))
				{
					query = query.substring(query.indexOf("INSERT"));
				}	
				DBConnection.executeUpdate(query);
			}
		}
	}
}

