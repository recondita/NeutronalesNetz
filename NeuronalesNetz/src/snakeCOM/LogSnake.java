package snakeCOM;

import snake.Snake;
import snake.Spielbrett;

public class LogSnake extends Snake
{

	SnakeControl logger;
	
	public LogSnake(int x, int y, int richtung, long warte, Spielbrett brett, SnakeControl logger)
	{
		super(x, y, richtung, warte, brett);
		this.logger=logger;
	}

	@Override
	public void preMove()
	{
		logger.preMove();
	}
	
	public void setRichtung(int richtung)
	{
		super.richtung=richtung;
	}

}
