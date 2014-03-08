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
		// create training set
		DataSet trainingSet =
		new DataSet(2, 1);
		// add training data to training set (logical OR function)
		trainingSet.
		addRow (new DataSetRow (new double[]{0, 0},
		new double[]{0})); // Adds a dataSetRow
		trainingSet.
		addRow (new DataSetRow (new double[]{0, 1},
		new double[]{1}));
		trainingSet.
		addRow (new DataSetRow (new double[]{1, 0},
		new double[]{1}));
		trainingSet.
		addRow (new DataSetRow (new double[]{1, 1},
		new double[]{1}));
		// learn the training set
		learn(trainingSet);
		// save the trained network into file
	}

}
