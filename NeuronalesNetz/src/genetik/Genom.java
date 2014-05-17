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
	
	public Genom(Genom... gene )
	{		
		this.layers=gene[(int)(Math.random()*gene.length)].getLayers();
		this.learningRate=gene[(int)(Math.random()*gene.length)].getLearningRate();
		this.momentum=gene[(int)(Math.random()*gene.length)].getMomentum();
		this.maxIterations=gene[(int)(Math.random()*gene.length)].getMaxIterations();
		this.transferFunktion=gene[(int)(Math.random()*gene.length)].getTransferFunktion();
		this.dataSet=gene[(int)(Math.random()*gene.length)].getDataSet();
	}
	
	private int[] rekombLayers(int[]... layer)
	{
		int durchSchnitt=0;
		for(int i=0; i<layer.length;i++)
			durchSchnitt+=layer[i].length;
		durchSchnitt=(int) Math.round((double)durchSchnitt/layer.length);
		int[] ret=new int[durchSchnitt];
				//TODO Sinvoll mischen?
		return ret;
	}
	
	
	public int[] getLayers()
	{
		return layers;
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
			gen = new Genom(new int[]{100,64,36,16,4,2},0.001,0.25,Integer.MAX_VALUE,TransferFunctionType.TANH,TrainingSetImport
					.importFromFile("spiel.log", 100, 2, ","));
			System.out.println(new FitnessTester().test(gen,50000));
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
