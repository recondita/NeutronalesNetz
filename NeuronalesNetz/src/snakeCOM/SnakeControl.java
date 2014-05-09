package snakeCOM;

import java.util.LinkedList;

import snake.GUI;
import snake.Spielbrett;

public abstract class SnakeControl
{

	protected Spielbrett brett;
	protected LogSnake snake;
	protected GUI gui;
	
	public SnakeControl()
	{
		this.brett = new LogBrett(this);
		gui = new GUI(this.brett);
	}

	public abstract void preMove();
	
	public LogSnake newSnake()
	{
		this.snake = new LogSnake(brett.getBreite() / 2, brett.getHoehe() / 2,
				1, 300L, this.brett, this);
		return snake;
	}
	
	public abstract void verloren(int laenge);

}
