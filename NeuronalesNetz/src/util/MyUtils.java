package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class MyUtils {
	public static String readFile(File f) {
		return readFile(f.getAbsoluteFile());
	}

	public static String readFile(String name) throws IOException {
		BufferedReader bR = new BufferedReader(new InputStreamReader(
				new FileInputStream(name), "UTF-8"));
		StringBuffer inhalt = new StringBuffer();
		String line = bR.readLine();
		while (true) {
			inhalt.append(line);
			line = bR.readLine();
			if (line == null)
				break;
			inhalt.append('\n');
		}
		bR.close();
		return inhalt.toString();
	}

	public static void writeFile(String inhalt, String name) throws IOException {
		writeFile(inhalt, name, false);
	}

	public static void writeFile(String inhalt, String name, boolean append)
			throws IOException {
		writeFile(inhalt, new File(name), append);
	}

	public static void writeFile(String inhalt, File f, boolean append)
			throws IOException {
		BufferedWriter bW = new BufferedWriter(new FileWriter(f, append));
		bW.write(inhalt);
		bW.close();
	}
}
