package snakeCOM;

import genetik.FitnessTester;

public class FastSnake extends ControlSnake
{

	public FastSnake(int x, int y, int richtung, LogBrett brett,FitnessTester fitnessTester, boolean wachsen)
	{
		super(x,y,richtung,1L,brett,fitnessTester,wachsen);
		brett.disableSuperVerloren();
	}

	@Override
	public void start()
	{
		fPause=false;
		while(!fPause&&schritt());
	}
	
}
