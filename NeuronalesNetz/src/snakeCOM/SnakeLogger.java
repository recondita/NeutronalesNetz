package snakeCOM;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JOptionPane;

public class SnakeLogger extends SnakeControl
{
	private LinkedList<String> templog;
	private StringBuffer move = null;

	public SnakeLogger()
	{
		templog = new LinkedList<String>();
		gui.start();
	}

	public void afterMove()
	{
		if (move == null)
		{
			move = new StringBuffer();
		}
		else if(move.length()!=0)
		{
			move.append(snake.richtungBreite());// ((double)(snake.getRichtung()-2)/2)-1.0);
			move.append(",");
			move.append(snake.richtungHoehe());
			move.append("\n");
			// snake.setRichtung(snake.richtungBreite(), snake.richtungHoehe());
			templog.add(move.toString());
			move=new StringBuffer();
		}
		for (int x = 0; x < brett.getBreite(); x++)
		{
			for (int y = 0; y < brett.getHoehe(); y++)
			{
				int i = brett.getFeld(x, y);
				move.append(LogBrett.feldToDouble(i));
				move.append(",");
			}

		}
	}

	public static void main(String[] args)
	{
		new SnakeLogger();
	}

	public void speichern(int wegschneiden)
	{
		try
		{
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(
					"afterMove.log"), true));
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
		move=null;

	}

}
