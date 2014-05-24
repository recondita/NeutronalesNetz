package genetik;

import java.io.File;

import org.neuroph.core.NeuralNetwork;

import snakeCOM.FastSnake;
import snakeCOM.LogSnake;
import snakeCOM.SnakePlayer;

public class FitnessTester extends SnakePlayer
{
	private int fitness = 0;

	public FitnessTester()
	{
		super(null, false);
	}

	public int test(Genom gen, int tests)
	{
		return test(new GenNetz(gen),tests);
	}

	public int test(NeuralNetwork<?> nn, int tests)
	{
		synchronized (this)
		{
			//nn.save("GenNetz1.nn");
			setNetwork(nn);
			fitness = 0;
			long time=System.currentTimeMillis();
			for (int i = 0; i < tests; i++)
			{
				super.start();
			}
			System.out.println(((System.currentTimeMillis()-time)/1000)+" Sekunden benoetigt um das Netz zu testen");
			// tester.gui.dispose();
			return fitness;
		}
	}
	
	@Override
	public LogSnake customSnake()
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
