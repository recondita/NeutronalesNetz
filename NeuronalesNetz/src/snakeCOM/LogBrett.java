package snakeCOM;

import javax.swing.JOptionPane;

import snake.Spielbrett;

public class LogBrett extends Spielbrett
{


	private static final long serialVersionUID = 4278636303152012557L;
	private SnakeControl sl;
	
	public LogBrett(SnakeControl snakeControl)
	{
		super(10,10);
		this.sl=snakeControl;
	}
	
	@Override
	public void newSnake()
	{
		snake=sl.newSnake();
	}
	
	@Override
	public void verloren(int laenge)
	{
		sl.verloren(laenge);
		super.verloren(laenge);
	}

}
