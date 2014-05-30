package snakeCOM;

import java.util.ArrayList;

import snake.Snake;
import snake.Spielbrett;

/**
 * Erweiterung der Snake fuer die Kommunikation mit einem SnakeControll Objekt
 * und fuer die Steuerun durch ein NN
 */
public class COMSnake extends Snake
{
	/**
	 * SnakeControl Objekt das alles steuert.
	 */
	private SnakeControl sC;

	/**
	 * Hier werden die Positionen, auf denen die Schlange war gespeichert
	 */
	private ArrayList<Move> moves = new ArrayList<Move>();

	/**
	 * Counter fuer den Loop detector damit er nicht bei jedem Zug prueft.
	 */
	private int tempZug = 0;

	/**
	 * Referenz zum Spielbrett
	 */
	private Spielbrett brett;

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
	public COMSnake(int x, int y, int richtung, long warte, Spielbrett brett,
			SnakeControl snakeControl, boolean wachsen)
	{
		super(x, y, richtung, warte, brett, wachsen);
		this.sC = snakeControl;
		this.brett = brett;
	}

	/**
	 * Diese Methode wird nach jedem Zug Aufgerufen
	 */
	@Override
	public void afterMove()
	{
		sC.afterMove();
		if (detectLoop())
		{
			stopp();
			brett.verloren(getApfelCount());
		}
	}

	/**
	 * Diese Methode wird immer aufgerufen wenn ein Apfel gefressen wird. Hier
	 * wird die bewegungsaufzeichnung für den LoopDetector zurueck gesetzt.
	 */
	@Override
	public void neuerApfel()
	{
		moves.clear();
		tempZug = 0;
	}

	/**
	 * @return Horizontal Komponente der Richtung zurueck
	 */
	public double richtungBreite()
	{
		int richtung = getRichtung();
		return (richtung & 1) * (1 - (richtung & 2));
	}
	
	/**
	 * @return Vertikal Komponente der Richtung zurueck
	 */
	public double richtungHoehe()
	{
		int richtung = getRichtung();
		return (1 - (richtung & 1)) * (1 - (richtung & 2));
	}

	/**
	 * Setzt die Richtung die in die 2 Achsen zerlegt wurde
	 * @param x Horizontal-Komonente
	 * @param y Vertikal-Komponente
	 */
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

	/**
	 * Prueft alle 10 Schritte ob sich die Schlange "im Kreis" laueft
	 * @return Schlange befindet sich in einer Schleife?
	 */
	public boolean detectLoop()
	{
		Move neu = new Move(getKopfX(), getKopfY());
		if (tempZug == 10)// nicht so oft pruefen, das kostet Zeit.
		{
			tempZug = 0;
			for (int i = moves.size() - 1; i >= 0; i--)
			{
				if (neu.equals(moves.get(i)))
				{//Gucken ob die Schlange schonmal hier war
					boolean loop = true;
					int off = i;
					for (int j = moves.size() - 1; j >= i && off > 0; j--)
					{ //Pruefen ob auch die anderen Positionen uebereinstimmen
						off--;
						if (moves.get(off).equals(moves.get(j)))
						{
							loop = false;
							break;
						}
					}
					if (loop)
						return loop;
				}
			}
		} else
		{
			tempZug++;
		}
		moves.add(neu);
		return false;
	}

	/**
	 * Speichert eine Position
	 */
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
