package genetik;

import java.util.Timer;
import java.util.TimerTask;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.learning.MomentumBackpropagation;

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
		int[] layers = gen.getLayers();
		final MultiLayerPerceptron nn = new MultiLayerPerceptron(
				gen.getTransferFunktion(), layers);
		MomentumBackpropagation learningRule = (MomentumBackpropagation) nn
				.getLearningRule();
		learningRule.setLearningRate(gen.getLearningRate());
		learningRule.setMomentum(gen.getMomentum());
		learningRule.setMaxIterations(gen.getMaxIterations());
		Timer t=new Timer();
		TimerTask tt=new TimerTask(){

			@Override
			public void run()
			{
				nn.stopLearning();
			}
		};
		t.schedule(tt, 30000L);
		nn.learn(gen.getDataSet());
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
