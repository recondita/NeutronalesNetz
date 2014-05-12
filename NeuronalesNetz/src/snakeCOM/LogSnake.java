package snakeCOM;

import snake.Snake;
import snake.Spielbrett;

public class LogSnake extends Snake
{

	SnakeControl logger;
	
	public LogSnake(int x, int y, int richtung, long warte, Spielbrett brett, SnakeControl logger,boolean wachsen)
	{
		super(x, y, richtung, warte, brett,wachsen);
		this.logger=logger;
	}

	@Override
	public void preMove()
	{
		logger.preMove();
	}
	
	public double richtungBreite()
	{
		int richtung=getRichtung();
		return (richtung & 1) * (1 - (richtung & 2));
	}
	
	public double richtungHoehe()
	{
		int richtung=getRichtung();
		return (1 - (richtung & 1)) * (1 - (richtung & 2));
	}
	
	public void setRichtung(double x, double y)
	{
		if(Math.abs(x)>Math.abs(y))
		{
			super.richtung=(x>0?1:3);
		}
		
		else
		{
			super.richtung=(y>0?0:2);
		}
	}
	

}
