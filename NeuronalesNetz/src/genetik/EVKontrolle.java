package genetik;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import netz.SnakeTrainer;

import org.neuroph.util.TransferFunctionType;

import util.MyUtils;
/**
 * Kontrolliert die Evolution
 */
public class EVKontrolle
{
	private Genom[] gen;
	private Genom[] agen;
	//private FitnessTester[] fT;
	private int anzTests;
	private int generationCount = 0;
	private int maxTrainTime;
	private String saveDir;
	private SnakeTrainer trainer;
	final static int cores = Runtime.getRuntime().availableProcessors();

	/**
	 * Konstruktor f�r die EVKontrolle und Beginn der Evolution
	 * @param induvidien Die Anzahl der Individuen
	 * @param anzTests Die Anzahl der Tests
	 * @param maxTrainTime Die maximale Zeit die zum trainieren verwendet werden darf
	 * @param saveDir Der Speicherort f�r die Genome
	 * @param trainingSet Das gelogte Snake Spiel
	 * @throws IOException
	 */
	public EVKontrolle(int induvidien, int anzTests, int maxTrainTime,
			String saveDir, String trainingSet) throws IOException
	{
		this.trainer=new SnakeTrainer(trainingSet,100,2);
		this.saveDir = saveDir.endsWith(File.separator) ? saveDir
				: (saveDir + File.separator);
		this.anzTests = anzTests;
		this.maxTrainTime = maxTrainTime;
		File dir = new File(saveDir);
		if (!dir.exists())
		{
			dir.mkdir();
		}
		File info = new File(this.saveDir + "Generation.info");
		if (info.exists())
		{
			String genDir=MyUtils.readFile(info);
			generationCount=Integer.parseInt(genDir.replace("Generation_",""));
			genDir=this.saveDir+genDir;
			String[] genFiles = new File(genDir).list();
			ArrayList<Genom> genomList = new ArrayList<Genom>(genFiles.length);
			genDir = genDir + File.separator;
			for (String genFile : genFiles)
			{
				if (genFile.contains("Genom_"))
				{
					genomList.add(new Genom(MyUtils.readFile(genDir+genFile),this.trainer));
				}
			}
			gen = genomList.toArray(new Genom[genomList.size()]);
			Arrays.sort(gen);
			agen = new Genom[gen.length];

		} else
		{
			gen = new Genom[induvidien];
			agen = new Genom[induvidien];
			// fT = new FitnessTester[induvidien];
			for (int i = 0; i < gen.length; i++)
			{
				gen[i] = new Genom(new int[] { 100, 36, 4, 2 }, 0.001, 0.7,
						200, TransferFunctionType.TANH,this.trainer);
			}
			mutation();
			testeFitness();
		}
	}

	/**
	 * Zuechtet Generationen
	 * @param anzGenerationen Die Anzahl der Generationen die gezuechtet werden sollen
	 */
	public void entwickle(int anzGenerationen)
	{
		for (int i = 0; i < anzGenerationen; i++)
		{
			generationCount++;
			System.out.println("Durchlauf: " + generationCount);
			rekombination();
			mutation();
			testeFitness();
			speicherGeneration();
			System.out.println(toString());
		}
	}

	/**
	 * @return Einen String zur Uebersicht
	 */
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		for (int j = 0; j < gen.length; j++)
		{
			sb.append(gen[j].getFitness());
			sb.append("\n");
		}
		return sb.toString();
	}

	/**
	 * Kombiniert die Layer neu
	 */
	private void rekombination()
	{
		java.util.Arrays.sort(gen);
		agen = gen.clone();
		gen[2] = new Genom(agen[0], agen[1]);
		System.out.println("Genom 0 mit Genom 1");
		for (int i = 3; i < gen.length; i++)
		{
			int erst = (int) (Math.random() * agen.length);
			int zweit = (int) (Math.random() * (agen.length - 1));
			if (zweit >= erst)
				zweit++;
			gen[i] = new Genom(agen[erst], agen[zweit]);
			System.out.println("Genom " + erst + " mit Genom " + zweit);
		}
	}

	/**
	 * Mutiert die Genome
	 */
	private void mutation()
	{
		java.util.Arrays.sort(gen);
		for (int i = gen.length / 4; i < gen.length; i++) // die oberen 20%
															// sollen nicht
															// mutieren (elite)
		{
			gen[i].mutation();
		}
	}

	/**
	 * Testet die Fitness 
	 */
	private void testeFitness()
	{
		LinkedList<Integer> jobs = new LinkedList<Integer>();
		for (int i = 0; i < gen.length; i++)
			if (!gen[i].isTested())
				jobs.add(i);
		TestThread[] tt = new TestThread[cores];
		for (int i = 0; i < tt.length; i++)
		{
			tt[i] = new TestThread(jobs);
			tt[i].start();
		}
		for (int i = 0; i < tt.length; i++)
			try
			{
				tt[i].join();
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
	}

	/**
	 * f�r mutlithreading
	 */
	private class TestThread extends Thread
	{
		private LinkedList<Integer> jobs;

		public TestThread(LinkedList<Integer> jobs)
		{
			this.jobs = jobs;
		}

		public void run()
		{
			while (true)
			{
				int job;
				synchronized (jobs)
				{
					if (jobs.isEmpty())
						break;
					job = jobs.removeFirst();
				}
				gen[job].setFitness(new FitnessTester(maxTrainTime,anzTests)
						.testWerteZeit(gen[job]));
			}

		}

	}

	/**
	 * Speichert die aktuelle Generation
	 */
	public void speicherGeneration()
	{
		java.util.Arrays.sort(gen);
		StringBuffer generationDir = new StringBuffer();
		generationDir.append(saveDir);
		generationDir.append("Generation_");
		for (int i = (generationCount + "").length(); i < 6; i++)
			generationDir.append(0);
		generationDir.append(generationCount);
		File genDir = new File(generationDir.toString());
		genDir.mkdir();
		try
		{
			MyUtils.writeFile(genDir.getName(),saveDir+ "Generation.info");
		} catch (IOException e1)
		{
			e1.printStackTrace();
		}

		generationDir.append(File.separator);
		generationDir.append("Genom_");
		for (int i = 0; i < gen.length; i++)
		{
			try
			{
				MyUtils.writeFile(gen[i].toString(), generationDir.toString() + i+".gen");
			} catch (IOException e)
			{
				e.printStackTrace();
			}

		}
	}

	public static void main(String[] args)
	{
		try
		{
			EVKontrolle eK = new EVKontrolle(16, 10000, 300000, "EV","afterMove.log");
			eK.entwickle(100);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		System.exit(0);
	}

}
