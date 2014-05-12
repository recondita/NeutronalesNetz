package snakeCOM;

import java.util.ArrayList;
import snake.Snake;
import snake.Spielbrett;

public class LogSnake extends Snake
{

	SnakeControl logger;
	ArrayList<Move> moves = new ArrayList<Move>();
	int tempZug = 0;
	Spielbrett brett;

	public LogSnake(int x, int y, int richtung, long warte, Spielbrett brett,
			SnakeControl logger, boolean wachsen)
	{
		super(x, y, richtung, warte, brett, wachsen);
		this.logger = logger;
		this.brett=brett;
	}

	@Override
	public void preMove()
	{
		logger.preMove();
		if(detectLoop())
		{
			fPause=false;
			brett.verloren(getApfelCount());
		}
	}

	@Override
	public void neuerApfel()
	{
		moves.clear();
		tempZug = 0;
	}

	public double richtungBreite()
	{
		int richtung = getRichtung();
		return (richtung & 1) * (1 - (richtung & 2));
	}

	public double richtungHoehe()
	{
		int richtung = getRichtung();
		return (1 - (richtung & 1)) * (1 - (richtung & 2));
	}

	public void setRichtung(double x, double y)
	{
		if (Math.abs(x) > Math.abs(y))
		{
			super.richtung = (x > 0 ? 1 : 3);
		}

		else
		{
			super.richtung = (y > 0 ? 0 : 2);
		}
	}

	public boolean detectLoop()
	{
		Move neu = new Move(getKopfX(), getKopfY());
		if (tempZug == 10)//nicht so oft pruefen, das kostet Zeit.
		{
			tempZug=0;
			for (int i = moves.size() - 1; i >= 0; i--)
			{
				if (neu.equals(moves.get(i)))
				{
					boolean loop=true;
					int off=i;
					for (int j = moves.size() - 1; j >= i; j--)
					{
						off--;
						if(moves.get(off).equals(moves.get(j)))
						{
							loop=false;
							break;
						}
					}
					if(loop)
						return loop;
				}
			}
		}else
		{
			tempZug++;
		}
		moves.add(neu);
		return false;
	}

	class Move
	{
		final int x;
		final int y;

		Move(int x, int y)
		{
			this.x = x;
			this.y = y;
		}

		public boolean equals(Move m)
		{
			return x == m.x && y == m.y;
		}
	}

}
