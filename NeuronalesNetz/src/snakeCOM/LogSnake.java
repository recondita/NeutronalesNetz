package snakeCOM;

import snake.Snake;
import snake.Spielbrett;

public class LogSnake extends Snake
{

	SnakeLogger logger;
	
	public LogSnake(int x, int y, int richtung, long warte, Spielbrett brett, SnakeLogger logger)
	{
		super(x, y, richtung, warte, brett);
		this.logger=logger;
	}

	@Override
	public void preMove()
	{
		logger.preMove();
	}

}
