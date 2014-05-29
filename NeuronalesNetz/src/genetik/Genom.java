package genetik;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.neuroph.core.data.DataSet;
import org.neuroph.util.TrainingSetImport;
import org.neuroph.util.TransferFunctionType;
/**
 * Speichert die Attribute eines neuronalen Netzwerks
 *
 */
public class Genom implements Comparable<Genom>
{
	private int[] layers;
	private double learningRate;
	private double momentum;
	private int maxIterations;
	private TransferFunctionType transferFunktion;
	private DataSet dataSet;
	private int fitness;
	private boolean isTested;

	/**
	 * Erzeut ein Genom mit angegebenen Attribute
	 * @param layers Die Ebenen
	 * @param learningRate Die Lernrate
	 * @param momentum Das Momentum
	 * @param maxIterations Die maximale Anzahl der Iterationen
	 * @param transferFunktion Die Transferfunktion
	 * @param trainingSet Das gespeicherte log
	 */
	public Genom(int[] layers, double learningRate, double momentum,
			int maxIterations, TransferFunctionType transferFunktion,
			DataSet trainingSet)
	{
		this.layers = layers;
		this.learningRate = learningRate;
		this.momentum = momentum;
		this.maxIterations = maxIterations;
		this.transferFunktion = transferFunktion;
		this.dataSet = trainingSet;
	}

	/**
	 * Verbinde 2 Genome
	 * @param gen1 Das erste Genom
	 * @param gen2 Das zweite Genom
	 */
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

	/**
	 * Erzeugt ein Genom aus einem String und dem DataSet
	 * @param in Der String, der das Genom repräsentiert
	 * @param trainingSet Das gespeicherte log
	 */
	public Genom(String in, DataSet trainingSet)
	{
		String[] part = in.split(",");
		String[] lay = part[0].split("\\.");
		layers = new int[lay.length];
		for (int i = 0; i < layers.length; i++)
		{
			layers[i] = Integer.parseInt(lay[i]);
		}
		learningRate = Double.parseDouble(part[1]);
		momentum = Double.parseDouble(part[2]);
		if(part[3].equals("MAX"))
			maxIterations=Integer.MAX_VALUE;
		else
		maxIterations = Integer.parseInt(part[3]);
		transferFunktion = TransferFunctionType.valueOf(part[4]);
		fitness = Integer.parseInt(part[5]);
		dataSet = trainingSet;
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

	/**
	 * Gibt die Layer zurueck
	 * @return Die Layer als int[]
	 */
	public int[] getLayers()
	{
		return layers;
	}

	/**
	 * Gibt die LearningRate zurueck
	 * @return Die LearningRate als double
	 */
	public double getLearningRate()
	{
		return learningRate;
	}

	/**
	 * Gibt das Momentum zurueck
	 * @return Dass Momentum als double
	 */
	public double getMomentum()
	{
		return momentum;
	}

	/**
	 * Gibt die maximale Anzahl der Iterationen zurueck
	 * @return Die maximale Anzahl der Iterationen
	 */
	public int getMaxIterations()
	{
		return maxIterations;
	}

	/**
	 * Gibt die Transferfunktion zurueck
	 * @return Die Transferfunktion
	 */
	public TransferFunctionType getTransferFunktion()
	{
		return transferFunktion;
	}

	/**
	 * Gibt das DataSet zurueck
	 * @return Das DataSet
	 */
	public DataSet getDataSet()
	{
		return dataSet;
	}

	/**
	 * Gibt den Fitnesswert zurueck
	 * @return Der Fitnesswert
	 */
	public int getFitness()
	{
		return fitness;
	}

	/**
	 * Gibt zurueck ob das Genom schon getestet wurde
	 * @return Den Teststaus
	 */
	public boolean isTested()
	{
		return isTested;
	}

	/**
	 * Setzt das Genom als getestet
	 */
	public void setTested()
	{
		this.isTested = true;
	}

	/**
	 * Hier werden die Attribute zufällig verändert
	 */
	public void mutation()
	{
		isTested = false;
		if ((int) (Math.random() * 10) == 0) // So geht das..
		{
			if ((int) (Math.random() + 0.5) == 0) // 50% der FÃ¤lle
			{
				learningRate = Math.random() * 2 * learningRate + learningRate;
				if (learningRate >= 1)// wird immer true sein
				{
					learningRate = Math.random() * 0.7;// geneere zufaellige
														// neue lerningRate
				}
			} else
			{
				learningRate = learningRate - Math.random() * 2 * learningRate;
				if (learningRate <= 0)// wird immer true sein
				{
					learningRate = Math.random() * 0.01;
				}
			}
		}
		if ((int) (Math.random() * 10) == 0)
		{
			if ((int) (Math.random() + 0.5) == 0)
			{
				momentum = Math.random() * (momentum / 2) + momentum;
				if (momentum >= 1)
				{
					momentum = Math.random() * 0.7;
				}
			} else
			{
				momentum = momentum - Math.random() * (momentum / 2);
				if (momentum <= 0)
				{
					momentum = Math.random() * 0.3;
				}
			}
		}
		if ((int) (Math.random() * 10) == 0)
		{
			if ((int) (Math.random() + 0.5) == 0)
			{
				int layerso[] = layers;
				layers = new int[layerso.length + 1];
				int insert = (int) (Math.random() * 201);
				int pos = (int) (Math.random() * (layers.length - 2)) + 1;
				for (int i = 0; i < pos; i++)
				{
					layers[i] = layerso[i];
				}
				layers[pos] = insert;
				for (int i = pos + 1; i < layers.length; i++)
				{
					layers[i] = layerso[i - 1];
				}
			} else
			{
				if (layers.length > 3)
				{
					int layerso[] = layers;
					layers = new int[layerso.length - 1];
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
		if ((int) (Math.random() * 10) == 0)
		{
			if ((int) (Math.random() + 0.5) == 0)
			{
				maxIterations = (int) (Math.random() * (maxIterations / 3))
						+ maxIterations;
			} else
			{
				maxIterations = maxIterations
						- (int) (Math.random() * (maxIterations / 3));
				if (maxIterations <= 0)
				{
					maxIterations = (int) (Math.random() * 200);
				}
			}
		}
		if ((int) (Math.random() * 20) == 0)
		{
			int auswahl = (int) (Math.random() * 10);
			switch (auswahl)
			{
			case 0:
				transferFunktion = TransferFunctionType.GAUSSIAN;
				break;
			case 1:
				transferFunktion = TransferFunctionType.LINEAR;
				break;
			case 2:
				transferFunktion = TransferFunctionType.LOG;
				break;
			case 3:
				transferFunktion = TransferFunctionType.RAMP;
				break;
			case 4:
				transferFunktion = TransferFunctionType.SGN;
				break;
			case 5:
				transferFunktion = TransferFunctionType.SIGMOID;
				break;
			case 6:
				transferFunktion = TransferFunctionType.SIN;
				break;
			case 7:
				transferFunktion = TransferFunctionType.STEP;
				break;
			case 8:
				transferFunktion = TransferFunctionType.TANH;
				break;
			case 9:
				transferFunktion = TransferFunctionType.TRAPEZOID;
				break;
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

	/**
	 * Setzt den Fitnesswert
	 * @param fitness Der zu setzende Fitnesswert
	 */
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
			gen = new Genom("100.36.4.2,0.0020231507795779864,0.3833402030155874,MAX,TANH,3289866",TrainingSetImport.importFromFile("afterMove.log",100,2,","));//(new int[]{ 100, 64, 36, 16, 4, 2 }, 0.001, 0.25,
					//Integer.MAX_VALUE, TransferFunctionType.TANH,
					//TrainingSetImport.importFromFile("spiel.log", 100, 2, ","));
			//System.out.println(new FitnessTester().test(gen, Integer.MAX_VALUE));
			new GenNetz(gen,1200000).save("EVProdukt.nn");
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

	public String toString()
	{
		String ret = "";
		for (int i = 0; i < layers.length; i++)
		{
			if (i != 0)
				ret += ".";
			ret += layers[i];
		}
		ret += "," + learningRate + "," + momentum + "," + maxIterations + ",";
		ret += transferFunktion.name();
		ret += ",";
		ret += fitness;
		return ret;
	}

}
