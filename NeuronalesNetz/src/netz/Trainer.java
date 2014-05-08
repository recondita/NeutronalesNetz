package netz;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.nnet.learning.BackPropagation;

public interface Trainer
{
	abstract void train(NeuralNetwork<BackPropagation> netz);

}
