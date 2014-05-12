package netz;

import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.learning.MomentumBackpropagation;
import org.neuroph.util.TransferFunctionType;

public class SnakeNetz extends MultiLayerPerceptron
{

	private static final long serialVersionUID = 1L;;

	protected SnakeNetz(int... layers)
	{
		super(TransferFunctionType.TANH,layers);

	}

	public static SnakeNetz newNetz(int breite, int laenge)
	{
		/*
		final int gleich = 0;
		int[] layer = new int[gleich + 1
				+ (laenge < breite ? laenge : breite) / 2];
		for (int i = 0; i < gleich; i++)
			layer[i] = laenge * breite;
		for (int i = gleich; i < layer.length - 1; i++)
			layer[i] = (laenge - 2 * i) * (breite - 2 * i);
		layer[layer.length - 1] = 1;
		*/
		SnakeNetz netz = new SnakeNetz(100,64,36,16,4,2);
		MomentumBackpropagation learningRule = (MomentumBackpropagation) netz
				.getLearningRule();
		learningRule.setLearningRate(0.001);
		// learningRule.setMomentum(0.7);
		return netz;
	}

}
