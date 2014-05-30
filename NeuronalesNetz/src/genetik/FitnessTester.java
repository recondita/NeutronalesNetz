package genetik;

import org.neuroph.core.NeuralNetwork;

import snakeCOM.COMSnake;
import snakeCOM.FastSnake;
import snakeCOM.SnakePlayer;

/**
 * 
 * @author Jan Hofmeier Testet wie gut ein Neuronales Netz Snake spielt
 */
public class FitnessTester extends SnakePlayer
{
	/**
	 * Gibt die Fitnesswertung an
	 */
	private int fitness = 0;

	/**
	 * Zeit in Millisekunden die dem Netz maximal zum Trainieren zur Verfuegung
	 * stehen
	 */
	private long maxTrainTime;

	/**
	 * So oft spielt das Netz
	 */
	private int tests;

	/**
	 * Erstellt ein FitnessTester Objekt, noch ohne Neuronales Netz Die GUI des
	 * Snake Spiels ist deaktiviert. Als maximale Trainingszeit werden 5 min
	 * verwendet und das Netz spielt
	 */
	public FitnessTester()
	{
		super(null, false);
		tests = 5000;
		maxTrainTime = 30000L;
	}

	/**
	 * Erstellt ein FitnessTester Objekt, noch ohne Neuronales Netz Die GUI des
	 * Snake Spiels ist deaktiviert.
	 * 
	 * @param maxTrainTime
	 *            Zeit in Millisekunden die dem Netz maximal zum Trainieren zur
	 *            Verfuegung stehen
	 * @param tests
	 */
	public FitnessTester(long maxTrainTime, int tests)
	{
		super(null, false);
		this.maxTrainTime = maxTrainTime;
		this.tests = tests;
	}

	/**
	 * Erstellt aus dem Genom ein Netz, trainiert es ermittelt den Fittneswert
	 * davon
	 * 
	 * @param gen
	 *            Genom aus dem das Netz erstellt werden soll
	 * @return Fitnesswert - abhaengig von der Anzahl der Test-Spiele
	 */
	public int test(Genom gen)
	{
		return test(new GenNetz(gen,maxTrainTime));
	}

	/**
	 * Erstellt aus dem Genom ein Netz, trainiert es ermittelt den Fittneswert
	 * davon. Ihm werden bis zu 20% der Fittnespunkte abgezogen, je nach dem
	 * wieviel der Maximal verfügbaren Trainingszeit es verwendet.
	 * 
	 * @param gen
	 *            Genom aus dem das Netz erstellt werden soll
	 * @return Fitnesswert, mit verbrauchter Trainingszeit - abhaengig von der
	 *         Anzahl der test Spiele
	 */
	public int testWerteZeit(Genom gen)
	{
		long time = System.currentTimeMillis();
		GenNetz netz = new GenNetz(gen, maxTrainTime);
		time = System.currentTimeMillis() - time;
		int fitness = test(netz);
		fitness = (fitness * 5 - fitness * (int) (time / maxTrainTime)) / 5;
		return fitness;
	}

	/**
	 * Testet die Fitness eines trainieren Netzes
	 * 
	 * @param nn
	 *            Netz das getestet werden soll
	 * @return Fitnesswert - abhaengig von der Anzahl der test Spiele
	 */
	public int test(NeuralNetwork<?> nn)
	{
		synchronized (this)
		{
			setNetwork(nn);
			fitness = 0;
			for (int i = 0; i < tests; i++)
			{
				super.start();
			}
			return fitness;
		}
	}

	/**
	 * Ueberschreibt die Hook von "SnakControl" damit eine Schlange ohne Ohne
	 * Wartezeit zwsichen den bewegungen verwendet wird
	 */
	@Override
	public COMSnake customSnake()
	{
		return new FastSnake(brett.getBreite() / 2, brett.getHoehe() / 2, 1,
				this.brett, this, false);
	}

	/**
	 * Das ueberschreiben der verloren Methode verhindert das anzeigen der
	 * Üblichen "Spiel verloren" Meldung und addiert die Anzahl der gefressenen
	 * Aepfel, welche mit dem Parameter übergeben wird auf den Fitnesswert
	 */
	@Override
	public void verloren(int laenge)
	{
		fitness += laenge;
	}

	/**
	 * Die start Methode die von SnakeControl/SnakePlayer geerbt wird soll nicht
	 * von außen aufgerufen werden.
	 */
	public void start()
	{
		System.out.println("Was denn?");
	}
	
	/**
	 * Testet das Netz
	 * @param args Gibt den Pfad zum Netz an
	 */
	public static void main(String[] args)
	{
		System.out.println("Das Netz hat eine Fitness von: "
				+ (new FitnessTester().test(NeuralNetwork
						.createFromFile(args[0]))));
		System.exit(0);
	}
}
