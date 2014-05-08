package netz;

import java.util.Arrays;
import java.util.List;

import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.learning.MomentumBackpropagation;

public class SnakeNetz extends MultiLayerPerceptron
{

	private static final long serialVersionUID = 1L;;

	protected SnakeNetz(List<Integer> layers)
	{
		super(layers);

	}

	public static SnakeNetz newNetz(int breite, int laenge)
	{
		final int gleich = 0;
		Integer[] layer = new Integer[gleich + 1
				+ (laenge < breite ? laenge : breite) / 2];
		for (int i = 0; i < 5; i++)
			layer[i] = laenge * breite;
		for (int i = gleich; i < layer.length - 1; i++)
			layer[i] = (laenge - 2 * i) * (breite - 2 * i);
		layer[layer.length - 1] = 1;
		SnakeNetz netz = new SnakeNetz(Arrays.asList(layer));
		MomentumBackpropagation learningRule = (MomentumBackpropagation) netz
				.getLearningRule();
		learningRule.setLearningRate(0.1);
		// learningRule.setMomentum(0.7);
		return netz;
	}

}
