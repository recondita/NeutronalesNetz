package snakeCOM;

import snake.Spielbrett;

public class LogBrett extends Spielbrett
{

	private static final long serialVersionUID = 4278636303152012557L;
	private SnakeControl sl;
	private boolean superVerloren = true;
	private boolean repaint;

	public LogBrett(SnakeControl snakeControl)
	{
		super(10, 10);
		this.sl = snakeControl;
	}

	public LogBrett(SnakeControl snakeControl, boolean repaint)
	{
		super(10, 10);
		this.repaint = repaint;
		this.sl = snakeControl;
	}

	@Override
	public void newSnake()
	{
		snake = sl.newSnake();
	}

	public double[] toDoubleArray()
	{
		double[] ret = new double[getBreite() * getHoehe()*2];
		int count = 0;
		for (int x = 0; x < getBreite(); x++)
		{
			for (int y = 0; y < getHoehe(); y++)
			{
				int feld=getFeld(x, y);
				ret[count] = feldToDouble(feld);
				ret[count+ret.length/2]=(feld>=10&&feld<20)?1.0:-1.0;
				count++;
			}
		}
		return ret;
	}

	public static double feldToDouble(int i)
	{
		if(i>=10&&i<40)
			return 1;
		if (i == 0)
			return 0.0;
		/*
		if (i >= 20 && i < 30)
			return 0.5;
		if (i >= 30 && i < 40)
			return 0.5;
		if (i >= 10 && i < 20)
			return 1;*/
		if (i == 1)
			return -1;
		System.out.println("Unbekanntes Objekt");
		return 0;
	}

	public double feldAsDouble(int x, int y)
	{
		return feldToDouble(getFeld(x, y));
	}

	public void disableSuperVerloren()
	{
		this.superVerloren = false;
	}

	@Override
	public void verloren(int laenge)
	{
		sl.verloren(laenge);
		if (superVerloren)
			super.verloren(laenge);
		else
			wipe();
	}

	@Override
	public void repaint()
	{
		if (repaint)
			super.repaint();
	}

}
