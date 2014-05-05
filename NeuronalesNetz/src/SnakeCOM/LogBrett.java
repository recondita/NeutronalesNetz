package SnakeCOM;

import snake.Spielbrett;

public class LogBrett extends Spielbrett
{


	private static final long serialVersionUID = 4278636303152012557L;
	private SnakeLogger sl;
	
	public LogBrett(SnakeLogger sl)
	{
		this.sl=sl;
	}
	
	//@Override
	public void newSnake()
	{
		snake=sl.newSnake();
	}

}
