package netz;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.Perceptron;


public class SimpleNetwork extends Perceptron {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SimpleNetwork() {
		super(2, 1);
	}

}
