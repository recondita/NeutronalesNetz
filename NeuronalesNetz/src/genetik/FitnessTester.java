package genetik;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.nnet.MultiLayerPerceptron;

import snakeCOM.FastSnake;
import snakeCOM.LogSnake;
import snakeCOM.SnakePlayer;

public class FitnessTester extends SnakePlayer
{
	private int fitness = 0;;

	private FitnessTester(NeuralNetwork<?> nn)
	{
		super(nn);
	}

	public static int test(Genom gen)
	{
		final MultiLayerPerceptron nn = new GenNetz(gen);
		FitnessTester tester = new FitnessTester(nn);
		for(int i=0; i<10; i++)
		{
			tester.start();
		}
		tester.gui.dispose();
		return tester.getFitness();
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
		fitness+=laenge;
	}

	public int getFitness()
	{
		return fitness;
	}

}
