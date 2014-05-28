package snakeCOM;

import genetik.FitnessTester;

public class FastSnake extends COMSnake
{

	public FastSnake(int x, int y, int richtung, COMBrett brett,FitnessTester fitnessTester, boolean wachsen)
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
