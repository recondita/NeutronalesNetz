package SnakeCOM;

import snake.GUI;
import snake.Spielbrett;

public class SnakeLogger
{

	
	private Spielbrett brett;
	private LogSnake snake; 
	private GUI gui;
	
	
	public SnakeLogger()
	{
		this.brett=new LogBrett(this);
		gui=new GUI(this.brett);
		gui.start();
	}

	
	
	public void preMove()
	{
		System.out.println("vorZug");
	}
	
	
	public LogSnake newSnake()
	{
		this.snake=new LogSnake(brett.getBreite() / 2,brett.getHoehe() / 2,1,200L,this.brett,this);
		return snake;
	}
	
	public static void main(String[] args)
	{
		new SnakeLogger();
	}
}
