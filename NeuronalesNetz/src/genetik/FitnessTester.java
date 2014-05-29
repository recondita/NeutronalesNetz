package genetik;

import java.io.File;

import org.neuroph.core.NeuralNetwork;

import snakeCOM.COMSnake;
import snakeCOM.FastSnake;
import snakeCOM.SnakePlayer;

public class FitnessTester extends SnakePlayer
{
	private int fitness = 0;
	private long maxTrainTime=3000L;

	public FitnessTester()
	{
		super(null, false);
	}
	
	public FitnessTester(long maxTrainTime)
	{
		super(null, false);
		this.maxTrainTime=maxTrainTime;
	}

	public int test(Genom gen, int tests)
	{
		return test(new GenNetz(gen),tests);
	}
	
	public int testWerteZeit(Genom gen, int tests)
	{
		long time=System.currentTimeMillis();
		GenNetz netz=new GenNetz(gen,30000L);
		time=System.currentTimeMillis()-time;
		int fitness=test(netz,tests);
		fitness=(fitness*5-fitness*(int)(time/maxTrainTime))/5;
		return fitness;
	}

	public int test(NeuralNetwork<?> nn, int tests)
	{
		synchronized (this)
		{
			//nn.save("GenNetz1.nn");
			setNetwork(nn);
			fitness = 0;
			//long time=System.currentTimeMillis();
			for (int i = 0; i < tests; i++)
			{
				super.start();
			}
			//System.out.println(((System.currentTimeMillis()-time)/1000)+" Sekunden benoetigt um das Netz zu testen");
			// tester.gui.dispose();
			return fitness;
		}
	}
	
	@Override
	public COMSnake customSnake()
	{
		return new FastSnake(brett.getBreite() / 2, brett.getHoehe() / 2, 1,
				this.brett, this, false);
	}

	@Override
	public void verloren(int laenge)
	{
		fitness += laenge;
	}
	
	public void start()
	{
		System.out.println("Was denn?");
	}
	
	public static void main(String[] args)
	{
		System.out.println("Das Netz hat eine Fitness von: "+(new FitnessTester().test(NeuralNetwork.createFromFile(new File("5min.nn")), 5000)));
	}
}
