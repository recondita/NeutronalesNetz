package SnakeCOM;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import snake.GUI;
import snake.Spielbrett;

public class SnakeLogger
{

	private Spielbrett brett;
	private LogSnake snake;
	private GUI gui;
	private LinkedList<String> templog;

	public SnakeLogger()
	{
		templog=new LinkedList<String>();
		this.brett = new LogBrett(this);
		gui = new GUI(this.brett);
		gui.start();
	}

	public void preMove()
	{

			StringBuffer sb= new StringBuffer();
			for (int x = 0; x < brett.getBreite(); x++)
			{
				for (int y = 0; y < brett.getHoehe(); y++)
				{
					int i = brett.getFeld(x, y);
					sb.append(feldToDouble(i));
					sb.append(",");
				}

			}
			sb.append((double)snake.getRichtung()/4);
			sb.append("\n");
			templog.add(sb.toString());
	}

	public double feldToDouble(int i)
	{
		if (i == 0)
			return 0.3;
		if (i >= 20 && i < 30)
			return 0.5;
		if (i >= 30 && i < 40)
			return 0.7;
		if (i == 1)
			return 0;
		if (i >= 10 && i < 20)
			return 1;
		System.out.println("Unbekanntes Objekt");
		return 0;

	}

	
	public LogSnake newSnake()
	{
		this.snake = new LogSnake(brett.getBreite() / 2, brett.getHoehe() / 2,
				1, 200L, this.brett, this);
		return snake;
	}

	public static void main(String[] args)
	{
		new SnakeLogger();
	}
	
	public void speichern(int wegschneiden)
	{
		try
		{
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File("spiel.log"),
					true));
			for(int i=0; i<wegschneiden&&!templog.isEmpty();i++)
				templog.removeLast();
			while(!templog.isEmpty())
			{
				writer.write(templog.removeFirst());
			}
			writer.close();
			templog=new LinkedList<String>();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
