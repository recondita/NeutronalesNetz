package genetik;

import java.util.ArrayList;

import org.neuroph.util.TransferFunctionType;

public class Genom
{

	private ArrayList<Integer> layers;
	private double learningRate;
	private double momentum;
	private long learningTime;
	private TransferFunctionType transferFunktion;
	private int dataSet;

	public Genom()
	{
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Integer> getLayers()
	{
		return (ArrayList<Integer>) layers.clone();
	}

	public double getLearningRate()
	{
		return learningRate;
	}

	public double getMomentum()
	{
		return momentum;
	}

	public long getLearningTime()
	{
		return learningTime;
	}

	public TransferFunctionType getTransferFunktion()
	{
		return transferFunktion;
	}

	public int getDataSet()
	{
		return dataSet;
	}

}
