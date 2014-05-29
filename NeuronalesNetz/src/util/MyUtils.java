package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class MyUtils
{

	/**
	 * Liest den Kompletten Inhalt des Files
	 * 
	 * @param f
	 *            File welches gelesen werden soll
	 * @return Inhalt der Datei
	 */
	public static String readFile(File f) throws IOException
	{
		return readFile(f.getAbsolutePath());
	}

	/**
	 * Liest den kompletten Inhalt des Files
	 * 
	 * @param name Name der Datei mit Pfad
	 * @return Inhalt der Datei
	 * @throws IOException
	 */
	public static String readFile(String name) throws IOException
	{
		BufferedReader bR = new BufferedReader(new InputStreamReader(
				new FileInputStream(name), "UTF-8"));
		StringBuffer inhalt = new StringBuffer();
		String line = bR.readLine();
		while (true)
		{
			inhalt.append(line);
			line = bR.readLine();
			if (line == null)
				break;
			inhalt.append('\n');
		}
		bR.close();
		return inhalt.toString();
	}

	/**
	 * Schreibt den String in eine Datei, fall vorhanden wird die Datei ueberschrieben
	 * @param inhalt String der in dei Datei geschrieben werden soll
	 * @param name Pfad zur Datei
	 * @throws IOException
	 */
	public static void writeFile(String inhalt, String name) throws IOException
	{
		writeFile(inhalt, name, false);
	}

	/**
	 * Schreibt den String in eine Datei
	 * @param inhalt String der in dei Datei geschrieben werden soll
	 * @param name Pfad zur Datei
	 * @param append soll der inhalt hinten an die Datein angehaengt werden, fall schon vorhanden
	 * @throws IOException
	 */
	public static void writeFile(String inhalt, String name, boolean append)
			throws IOException
	{
		writeFile(inhalt, new File(name), append);
	}

	/**
	 * Schreibt den String in eine Datei
	 * @param inhalt String der in dei Datei geschrieben werden soll
	 * @param f Datei in die geschrieben werden soll
	 * @param append soll der inhalt hinten an die Datein angehaengt werden, fall schon vorhanden
	 * @throws IOException
	 */
	public static void writeFile(String inhalt, File f, boolean append)
			throws IOException
	{
		BufferedWriter bW = new BufferedWriter(new FileWriter(f, append));
		bW.write(inhalt);
		bW.close();
	}
}
