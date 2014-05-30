package snakeCOM;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JOptionPane;

/**
 * Zeichnet das SNake Spiel auf
 */
public class SnakeLogger extends SnakeControl
{
	/**
	 * Hier werden die Zustaende zusammen mit der Richtung zwischengespeichert
	 */
	private LinkedList<String> templog;

	/**
	 * Zwischenspeichern den Zustandes des Feldes.
	 */
	private StringBuffer move = null;

	/**
	 * Hier rein wird die Spielaufzeichnung gespeichert
	 */
	private File log;

	/**
	 * Konstruktor
	 * 
	 * @param log
	 *            hier rein soll die AUfzeichnung gespeichert werden, falls die
	 *            Datei schon existiert, wird die neue Aufzeichnung angehaengt
	 */
	public SnakeLogger(File log)
	{
		this.log = log;
		templog = new LinkedList<String>();
		gui.start();
	}

	/**
	 * Zeichnet jeden Zustand und die darauf folgende vom Spieler gewaehlte
	 * Richtung
	 */
	public void afterMove()
	{
		if (move == null)
		{
			move = new StringBuffer();
		} else if (move.length() != 0)
		{
			move.append(snake.richtungBreite());
			move.append(",");
			move.append(snake.richtungHoehe());
			move.append("\n");
			templog.add(move.toString());
			move = new StringBuffer();
		}
		for (int x = 0; x < brett.getBreite(); x++)
		{
			for (int y = 0; y < brett.getHoehe(); y++)
			{
				move.append(brett.feldAsDouble(x, y));
				move.append(",");
			}

		}
	}

	/**
	 * Schreibt die Aufzeichnung in eine Datei, ohne die letzten paar Schritte
	 * @param wegschneiden Anzahl der Schritte (von hinten) die nicht gespeichert werden sollen
	 */
	public void speichern(int wegschneiden)
	{
		try
		{
			BufferedWriter writer = new BufferedWriter(
					new FileWriter(log, true));
			for (int i = 0; i < wegschneiden && !templog.isEmpty(); i++)
				templog.removeLast();
			while (!templog.isEmpty())
			{
				writer.write(templog.removeFirst());
			}
			writer.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Zeigt einen Dialog an ob das aufgzeichnete Spiel gespeichert werden soll.
	 * Wird automatisch aufgerufen wenn das Spiel verloren wurde
	 * @param laenge So viele Aepfel wurden gefressen
	 */
	@Override
	public void verloren(int laenge)
	{
		boolean loop;
		int auswahl;
		do
		{
			loop = false;
			auswahl = JOptionPane.showConfirmDialog(null, "Speichern?");
			if (auswahl > 0)
				loop = 0 != JOptionPane.showConfirmDialog(null, "Wirklich?");
		} while (loop);
		switch (auswahl)
		{
		case 0:
			speichern(10);
			break;
		case 1:
			break;
		case 2:
			System.exit(0);
		}
		templog.clear();
		move = null;

	}

	public static void main(String[] args)
	{
		new SnakeLogger(new File(args[0]));
	}
}
