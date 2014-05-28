package snakeCOM;

import snake.GUI;

public abstract class SnakeControl
{

	protected LogBrett brett;
	protected ControlSnake snake;
	protected GUI gui;
	private static boolean wachsen = false;

	public SnakeControl()
	{
		this(true);
	}

	public SnakeControl(boolean gui)
	{
		this.brett = new LogBrett(this,gui);
		if (gui)
			this.gui = new GUI(this.brett);
	}

	public abstract void afterMove();

	public final ControlSnake newSnake()
	{
		this.snake = customSnake();
		return snake;
	}

	public void start()
	{
		brett.start();
	}
	
	public ControlSnake customSnake()
	{
		return new ControlSnake(brett.getBreite() / 2, brett.getHoehe() / 2,
				1, 500L, this.brett, this, wachsen);
	}
	
	public abstract void verloren(int laenge);

}
