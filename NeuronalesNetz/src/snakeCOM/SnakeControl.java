package snakeCOM;

import snake.GUI;

public abstract class SnakeControl
{

	protected LogBrett brett;
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
