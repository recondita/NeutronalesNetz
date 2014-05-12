package netz;
import java.io.File;

import org.neuroph.core.NeuralNetwork;

public class Loader {

	public Loader() {
		// TODO Auto-generated constructor stub

	}

	public static void save(NeuralNetwork<?> neuralNetwork, String destination) {
		neuralNetwork.save(destination);
	}

	public static NeuralNetwork<?> load(String file) {
		NeuralNetwork<?> neuralNetwork = NeuralNetwork.createFromFile(new File(file));
		return neuralNetwork;
	}

}
