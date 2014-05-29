package snakeCOM;

import snake.Spielbrett;

/**
 * Eine Erweiterung den normlen Snake Spielbretts das Elemente zur Kommunikation
 * mit dem Neuronalen Netz beinhaltet
 */
public class COMBrett extends Spielbrett
{
	/**
	 * Eclipse wollte das
	 */
	private static final long serialVersionUID = 4278636303152012557L;

	/**
	 * SnakeControl Objekt das alles steuert.
	 */
	private SnakeControl sC;

	/**
	 * Soll die normale im Snake eingebaute Verlorenmeldung aufgerufen werden?
	 */
	private boolean superVerloren = true;

	/**
	 * Sollen repaint befehle verarbeitet werden?
	 */
	private boolean repaint;

	/**
	 * Erstellt ein Normales Spielbrett, mit repaints
	 * 
	 * @param snakeControl
	 *            SnakeControlObjekt fuer die Kommunikation
	 */
	public COMBrett(SnakeControl snakeControl)
	{
		super(10, 10);
		this.sC = snakeControl;
	}

	/**
	 * Erstellt ein Normales Spielbrett
	 * 
	 * @param snakeControl
	 *            SnakeControlObjekt fuer die Kommunikation
	 * @param repaint
	 *            Moeglichkeit repaints zu deaktivieren (da das Orginale Brett
	 *            ein JPanel ist)
	 */
	public COMBrett(SnakeControl snakeControl, boolean repaint)
	{
		super(10, 10);
		this.repaint = repaint;
		this.sC = snakeControl;
	}

	/**
	 * Es soll nicht die Standart-Snake zum Einsatz kommen, sondern eine
	 * spezielle, die das Brett von Snake Control erhaelt
	 */
	@Override
	public void newSnake()
	{
		snake = sC.newSnake();
	}

	/**
	 * Schreibt das Raster des Spielbretts in ein double Array welches von einem
	 * Neuronalen Netz als Input verwendet werden kann
	 * 
	 * @return eindimensionales Array mit den Belegungen der einzelnen Felder
	 */
	public double[] toDoubleArray()
	{
		double[] ret = new double[getBreite() * getHoehe()];
		int count = 0;
		for (int x = 0; x < getBreite(); x++)
		{
			for (int y = 0; y < getHoehe(); y++)
			{
				int feld = getFeld(x, y);
				ret[count] = feldToDouble(feld);
				count++;
			}
		}
		return ret;
	}

	/**
	 * Wandelt die Integer Werte die das normale Spielbrett verwendet in ein
	 * double Format um mit dem ein Neuronales Netz umgehen kann
	 * 
	 * @param i
	 *            normaler Integer-Wert des Feldes
	 * @return double fuer ein NN
	 */
	public static double feldToDouble(int i)
	{
		if (i == 0)
			return 0;
		if (i >= 20 && i < 30)
			return 0.5;
		if (i >= 30 && i < 40)
			return 0.5;
		if (i >= 10 && i < 20)
			return 1;
		if (i == 1)
			return -1;
		System.out.println("Unbekanntes Objekt");
		return 0;
	}

	/**
	 * Liest ein Feld als fuer ein NN "verstaendlichen" double aus
	 * 
	 * @param x
	 *            x-Kordinate
	 * @param y
	 *            y-Kordinate
	 * @return
	 */
	public double feldAsDouble(int x, int y)
	{
		return feldToDouble(getFeld(x, y));
	}

	/**
	 * Deaktiviert die Standart verloren Meldung
	 */
	public void disableSuperVerloren()
	{
		this.superVerloren = false;
	}

	/**
	 * Überschreibt die Standart verlorenMeldung um die "Botschaft" an
	 * SnakeControl weiter zu reichen.
	 * Wenn superVerloren true ist wird auch die "normale" verloren Methode aufgerufen.
	 */
	@Override
	public void verloren(int laenge)
	{
		sC.verloren(laenge);
		if (superVerloren)
			super.verloren(laenge);
		else
			wipe();
	}

	/**
	 * faengt repaints ab, wenn repaint false ist.
	 */
	@Override
	public void repaint()
	{
		if (repaint)
			super.repaint();
	}

}
