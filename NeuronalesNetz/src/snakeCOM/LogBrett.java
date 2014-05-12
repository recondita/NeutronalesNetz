package snakeCOM;

import snake.Spielbrett;

public class LogBrett extends Spielbrett
{


	private static final long serialVersionUID = 4278636303152012557L;
	private SnakeControl sl;
	private boolean superVerloren=true;
	
	
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
	
	public double[] toDoubleArray()
	{
		double[] ret=new double[getBreite()*getHoehe()];
		int count=0;
		for(int x=0; x<getBreite(); x++)
		{
			for(int y=0; y<getHoehe(); y++)
			{
				ret[count]=feldAsDouble(x,y);
				count++;
			}
		}
		return ret;
	}
	
	public static double feldToDouble(int i)
	{
		if (i == 0)
			return 0.0;
		if (i >= 20 && i < 30)
			return 0.5;
		if (i >= 30 && i < 40)
			return 0.5;
		if (i == 1)
			return -1;
		if (i >= 10 && i < 20)
			return 1;
		System.out.println("Unbekanntes Objekt");
		return 0;
	}
	
	public double feldAsDouble(int x, int y)
	{
		return feldToDouble(getFeld(x,y));
	}
	
	public void disableSuperVerloren()
	{
		this.superVerloren=false;
	}
	@Override
	public void verloren(int laenge)
	{
		sl.verloren(laenge);
		if(superVerloren)
		super.verloren(laenge);
	}

}
