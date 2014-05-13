package genetik;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.neuroph.core.data.DataSet;
import org.neuroph.util.TrainingSetImport;
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

	public Genom(int[] layers, double learningRate, double momentum, int maxIterations, TransferFunctionType transferFunktion, DataSet trainingSet)
	{
		this.layers=layers;
		this.learningRate=learningRate;
		this.momentum=momentum;
		this.maxIterations=maxIterations;
		this.transferFunktion=transferFunktion;
		this.dataSet=trainingSet;
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
	
	public static void main(String[] args)
	{
		Genom gen;
		try
		{
			gen = new Genom(new int[]{100,36,4,2},0.001,0.7,Integer.MAX_VALUE,TransferFunctionType.TANH,TrainingSetImport
					.importFromFile("spiel.log", 100, 2, ","));
			System.out.println(FitnessTester.test(gen));
		} catch (NumberFormatException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void mutation()
	{
		
	}

}
