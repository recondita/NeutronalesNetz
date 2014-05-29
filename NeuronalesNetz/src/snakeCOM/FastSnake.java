package snakeCOM;

import genetik.FitnessTester;

/**
 * Schlange ohne Pause zwischen den Zuegen
 * @author jan
 *
 */
public class FastSnake extends COMSnake
{
	/**
	 * Konstruktor
	 * 
	 * @param x
	 *            Start-X Koordinate
	 * @param y
	 *            Start-Y Koordinate
	 * @param richtung
	 *            Start-Richtung
	 * @param warte
	 *            Zeit zwischen den Zuegen (verwendet Timer und kein Thread.sleep)
	 * @param brett
	 *            Das Spielbrett auf dem sich die Schlange bewegen wird
	 * @param snakeControl
	 *            SnakeControl-Objekt zum "kommunizieren"
	 * @param wachsen
	 *            soll die Schlange wachsen wenn sie einen APfel frisst?
	 */
	public FastSnake(int x, int y, int richtung, COMBrett brett,FitnessTester fitnessTester, boolean wachsen)
	{
		super(x,y,richtung,1L,brett,fitnessTester,wachsen);
		brett.disableSuperVerloren();
	}

	/**
	 * Laesst die Schlange laufen
	 * beendet sich erst wenn das Spiel verloren ist
	 */
	@Override
	public void start()
	{
		fPause=false;
		while(!fPause&&schritt());
	}
	
}
