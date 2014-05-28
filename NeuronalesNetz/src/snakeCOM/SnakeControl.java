package snakeCOM;

import snake.GUI;

public abstract class SnakeControl
{

	protected COMBrett brett;
	protected COMSnake snake;
	protected GUI gui;
	private static boolean wachsen = false;

	public SnakeControl()
	{
		this(true);
	}

	public SnakeControl(boolean gui)
	{
		this.brett = new COMBrett(this,gui);
		if (gui)
			this.gui = new GUI(this.brett);
	}

	public abstract void afterMove();

	public final COMSnake newSnake()
	{
		this.snake = customSnake();
		return snake;
	}

	public void start()
	{
		brett.start();
	}
	
	public COMSnake customSnake()
	{
		return new COMSnake(brett.getBreite() / 2, brett.getHoehe() / 2,
				1, 500L, this.brett, this, wachsen);
	}
	
	public abstract void verloren(int laenge);

}
