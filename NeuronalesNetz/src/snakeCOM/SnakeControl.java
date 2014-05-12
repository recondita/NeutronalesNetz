package snakeCOM;

import snake.GUI;

public abstract class SnakeControl
{

	protected LogBrett brett;
	protected LogSnake snake;
	protected GUI gui;
	private static boolean wachsen=false;
	
	public SnakeControl()
	{
		this.brett = new LogBrett(this);
		gui = new GUI(this.brett);
	}

	public abstract void preMove();
	
	public LogSnake newSnake()
	{
		this.snake = new LogSnake(brett.getBreite() / 2, brett.getHoehe() / 2,
				1, 500L, this.brett, this,wachsen);
		return snake;
	}
	
	
	public abstract void verloren(int laenge);

}
