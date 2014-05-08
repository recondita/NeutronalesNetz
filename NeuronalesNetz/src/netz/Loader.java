package netz;
import org.neuroph.core.NeuralNetwork;

public class Loader {

	public Loader() {
		// TODO Auto-generated constructor stub

	}

	public static void save(NeuralNetwork<?> neuralNetwork, String destination) {
		neuralNetwork.save(destination);
	}

	public static NeuralNetwork<?> load(String file) {
		NeuralNetwork<?> neuralNetwork = NeuralNetwork.load(file);
		return neuralNetwork;
	}

}
