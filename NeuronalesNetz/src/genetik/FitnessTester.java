package genetik;

import org.neuroph.nnet.MultiLayerPerceptron;

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
		MultiLayerPerceptron nn = new GenNetz(gen);
		synchronized (this)
		{
			setNetwork(nn);
			fitness = 0;
			for (int i = 0; i < tests; i++)
			{
				super.start();
			}
			// tester.gui.dispose();
			return fitness;
		}
	}

	@Override
	public LogSnake newSnake()
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

}
