package genetik;

import org.neuroph.core.data.DataSet;
import org.neuroph.util.TransferFunctionType;

public class Genom
{
	/**
	 * [0] muss zum feld passen und [length-1] muss 2 sein!
	 */
	private int[] layers;
	private double learningRate;
	private double momentum;
	private int maxIterations;
	private TransferFunctionType transferFunktion;
	private DataSet dataSet;

	public Genom()
	{
		// TODO Auto-generated constructor stub
	}
	

	public int[] getLayers()
	{
		return layers.clone();
	}

	public double getLearningRate()
	{
		return learningRate;
	}

	public double getMomentum()
	{
		return momentum;
	}

	public int getMaxIterations()
	{
		return maxIterations;
	}

	public TransferFunctionType getTransferFunktion()
	{
		return transferFunktion;
	}

	public DataSet getDataSet()
	{
		return dataSet;
	}
	

}
