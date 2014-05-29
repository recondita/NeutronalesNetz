package snakeCOM;

import snake.GUI;

public abstract class SnakeControl
{
	/**
	 * Brett auf dem sich die Schlange bewegen wird
	 */
	protected COMBrett brett;
	
	/**
	 * Schlange
	 */
	protected COMSnake snake;
	
	/**
	 * GUI die das Spiel anzeigt
	 */
	protected GUI gui;
	
	/**
	 * Wachstum der Schlange an/aus
	 */
	private static boolean wachsen = false;

	/**
	 * SnakeControll-Objekt mit GUI
	 */
	public SnakeControl()
	{
		this(true);
	}

	/**
	 * SnakeControl Objekt
	 * @param gui GUI an/aus
	 */
	public SnakeControl(boolean gui)
	{
		this.brett = new COMBrett(this,gui);
		if (gui)
			this.gui = new GUI(this.brett);
	}

	/**
	 * Diese Methode wird von der Schlange nach jedem Zug aufgerufen
	 */
	public abstract void afterMove();

	/**
	 * Snake Objekt, welches das Brett verwendet
	 * @return COMSnake-Objekt
	 */
	public final COMSnake newSnake()
	{
		this.snake = customSnake();
		return snake;
	}

	public void start()
	{
		brett.start();
	}
	
	/**
	 * Kann leicht "ueberschreiben" werden um eine Modifizierte COM-Snake zu verwenden
	 * @return COMSnake-Objekt
	 */
	public COMSnake customSnake()
	{
		return new COMSnake(brett.getBreite() / 2, brett.getHoehe() / 2,
				1, 500L, this.brett, this, wachsen);
	}
	
	/**
	 * Diese Methode wird aufgerufen wenn ein Spiel verloren wurde
	 * @param laenge So viele Aepfel hat die Schlange gefressen
	 */
	public abstract void verloren(int laenge);

}
