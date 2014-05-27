package genetik;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.neuroph.core.data.DataSet;
import org.neuroph.util.TrainingSetImport;
import org.neuroph.util.TransferFunctionType;

public class Genom implements Comparable<Genom>
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
	private int fitness;
	private boolean isTested;
	private boolean bias;

	public boolean isTested()
	{
		return isTested;
	}

	public void setTested()
	{
		this.isTested = true;
	}

	public Genom(int[] layers, double learningRate, double momentum,
			int maxIterations, TransferFunctionType transferFunktion,
			DataSet trainingSet)
	{
		this(layers, learningRate, momentum, maxIterations, transferFunktion,
				trainingSet, false);
	}

	public boolean isBias()
	{
		return bias;
	}

	public Genom(int[] layers, double learningRate, double momentum,
			int maxIterations, TransferFunctionType transferFunktion,
			DataSet trainingSet, boolean bias)
	{
		this.layers = layers;
		this.learningRate = learningRate;
		this.momentum = momentum;
		this.maxIterations = maxIterations;
		this.transferFunktion = transferFunktion;
		this.dataSet = trainingSet;
		this.bias = bias;
	}

	public Genom(Genom gen1, Genom gen2)
	{
		Genom[] gene = { gen1, gen2 };
		java.util.Arrays.sort(gene);
		int comfit = gene[0].getFitness() + gene[1].getFitness();
		int maxfit = gene[0].getFitness();
		this.layers = gene[Math.random() * comfit <= maxfit ? 0 : 1]
				.getLayers();
		this.learningRate = gene[Math.random() * comfit <= maxfit ? 0 : 1]
				.getLearningRate();
		this.momentum = gene[Math.random() * comfit <= maxfit ? 0 : 1]
				.getMomentum();
		this.maxIterations = gene[Math.random() * comfit <= maxfit ? 0 : 1]
				.getMaxIterations();
		this.transferFunktion = gene[Math.random() * comfit <= maxfit ? 0 : 1]
				.getTransferFunktion();
		this.dataSet = gene[Math.random() * comfit <= maxfit ? 0 : 1]
				.getDataSet();
	}

	/*
	 * public Genom(Genom... gene) { this.layers = gene[(int) (Math.random() *
	 * gene.length)].getLayers(); this.learningRate = gene[(int) (Math.random()
	 * * gene.length)] .getLearningRate(); this.momentum = gene[(int)
	 * (Math.random() * gene.length)].getMomentum(); this.maxIterations =
	 * gene[(int) (Math.random() * gene.length)] .getMaxIterations();
	 * this.transferFunktion = gene[(int) (Math.random() * gene.length)]
	 * .getTransferFunktion(); this.dataSet = gene[(int) (Math.random() *
	 * gene.length)].getDataSet(); }
	 */

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

	public int getFitness()
	{
		return fitness;
	}

	public void mutation()
	{
		isTested = false;
		if ((int)(Math.random()*10)==0) //So geht das..
		{
			if ((int) (Math.random() + 0.5) == 0) //50% der Fälle
			{
				learningRate = Math.random() * 2 * learningRate + learningRate;
				if (learningRate >= 1)//wird immer true sein
				{
					learningRate = Math.random() * 0.7;//geneere zufaellige neue lerningRate
				}
			} else
			{
				learningRate = Math.random() * 2 * learningRate - learningRate;
				if (learningRate <= 0)//wird immer true sein
				{
					learningRate = Math.random() * 0.01;
				}
			}
		}
		if (Math.round(Math.random()*9)  < 1)//in 9/10 der Faelle...
		{
			if ((int) (Math.random() + 0.5) == 0)
			{								//Integer Division... hier wird 0 rauskommen
				momentum = Math.random() * (momentum / 5) + momentum;
				if (momentum >= 1) //immer false
				{
					momentum = Math.random() * 0.7;
				}//Das momentum ändert sich also nie
			} else
			{//Gleiches wie oben
				momentum = Math.random() * (momentum / 5) - momentum;
				if (momentum <= 0)
				{
					momentum = Math.random() * 0.3;
				}
			}
		}
		if (Math.round(Math.random()*9)  < 1)// 9/10... viel zu haeufig
		{
			if ((int) (Math.random() + 0.5) == 0)
			{
				int layerso[] = layers;
				layers = new int[layerso.length + 1]; //um  vergroessern
				int insert = (int) (Math.random() * 100);//<100, mach lieber <=100..
				int pos = (int) (Math.random() * (layers.length - 3)) + 1;//1<=pos<=array.length-3 (Math.random macht nur <1, aber nie 1!
				for (int i = 0; i < pos; i++)
				{
					layers[i] = layerso[i]; //kopiere bis zu pos
				}
				layers[pos] = insert;
				for (int i = pos + 1; i < layers.length; i++)
				{	//kopiere den rest
					layers[i] = layerso[i - 1];
				}
			} else
			{
				if (layers.length > 3)//wenn mehr als 3 layer...
				{
					int layerso[] = layers;
					layers = new int[layerso.length - 1];//um 1 verkleinern
					int pos = (int) (Math.random() * (layerso.length - 2)) + 1;
					for (int i = 0; i < pos; i++)
					{
						layers[i] = layerso[i];
					}
					for (int i = pos; i < layers.length; i++)
					{
						layers[i] = layerso[i + 1];
					}
				}

			}
		}
	}

	@Override
	public int compareTo(Genom arg0)
	{
		if (fitness < arg0.getFitness())
		{
			return 1;
		}
		if (fitness > arg0.getFitness())
		{
			return -1;
		}
		return 0;
	}

	void setFitness(int fitness)
	{
		this.fitness = fitness;
		isTested = true;
	}

	public static void main(String[] args)
	{
		Genom gen;
		try
		{
			gen = new Genom(new int[] { 100, 64, 36, 16, 4, 2 }, 0.001, 0.25,
					Integer.MAX_VALUE, TransferFunctionType.TANH,
					TrainingSetImport.importFromFile("spiel.log", 100, 2, ","));
			System.out
					.println(new FitnessTester().test(gen, Integer.MAX_VALUE));
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

}
