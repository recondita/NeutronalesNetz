package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MyUtils
{
	public static String readFile(File f)
	{
		return readFile(f.getAbsoluteFile());
	}
	
	public static String readFile(String name) throws IOException
	{
		BufferedReader bR = new BufferedReader(new InputStreamReader(new FileInputStream(name), "UTF-8"));
		StringBuffer inhalt = new StringBuffer();
		String line = bR.readLine();
		while (true)
		{
			inhalt.append(line);
			line = bR.readLine();
			if(line==null)
				break;
			inhalt.append('\n');
		}
		bR.close();
		return inhalt.toString();
	}

	public static void writeFile(String string, String string2)
	{
		// TODO Auto-generated method stub
		
	}
	
}
