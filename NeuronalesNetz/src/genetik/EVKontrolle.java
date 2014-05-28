package genetik;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import org.neuroph.core.data.DataSet;
import org.neuroph.util.TrainingSetImport;
import org.neuroph.util.TransferFunctionType;

public class EVKontrolle
{
	private Genom[] gen;
	private Genom[] agen;
	//private FitnessTester[] fT;
	private int anzTests;
	private int generationCount = 0;
	private int maxTrainTime;
	private String saveDir;
	private DataSet trainingSet;
	final static int cores = Runtime.getRuntime().availableProcessors();

	public EVKontrolle(int induvidien, int anzTests, int maxTrainTime,
			String saveDir, DataSet trainingSet) throws IOException
	{
		this.trainingSet=trainingSet;
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
			String genDir;
			BufferedReader infoReader = new BufferedReader(new FileReader(info));
			genDir =infoReader.readLine();
			infoReader.close();
			generationCount=Integer.parseInt(genDir.replace("Generation_",""));
			genDir=this.saveDir+genDir;
			String[] genFiles = new File(genDir).list();
			ArrayList<Genom> genomList = new ArrayList<Genom>(genFiles.length);
			genDir = genDir + File.separator;
			for (String genFile : genFiles)
			{
				if (genFile.contains("Genom_"))
				{
					BufferedReader bR = new BufferedReader(new InputStreamReader(new FileInputStream(genDir+ genFile), "UTF-8"));
					StringBuffer inhalt = new StringBuffer();
					String line = bR.readLine();
					while (line != null)
					{
						inhalt.append(line);
						line = bR.readLine();
					}
					bR.close();
					genomList.add(new Genom(inhalt.toString(),this.trainingSet));
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
						200, TransferFunctionType.TANH,this.trainingSet);
			}
			mutation();
			testeFitness();
		}
	}

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

	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		for (int j = 0; j < agen.length; j++)
		{
			sb.append(agen[j].getFitness());
			sb.append("\n");
		}
		return sb.toString();
	}

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

	private void mutation()
	{
		for (int i = gen.length / 4; i < gen.length; i++) // die oberen 20%
															// sollen nicht
															// mutieren (elite)
		{
			gen[i].mutation();
		}
	}

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
				gen[job].setFitness(new FitnessTester(maxTrainTime)
						.testWerteZeit(gen[job], anzTests));
			}

		}

	}

	public void speicherGeneration()
	{
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
			FileWriter genWriter = new FileWriter(new File(saveDir
					+ "Generation.info"));
			genWriter.write(genDir.getName());
			genWriter.close();
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
				BufferedWriter bW = new BufferedWriter(new FileWriter(new File(
						generationDir.toString() + i)));
				bW.write(gen[i].toString());
				bW.close();
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
			EVKontrolle eK = new EVKontrolle(16, 10000, 300000, "EV",TrainingSetImport.importFromFile("afterMove.log", 100, 2,
					","));
			eK.entwickle(100);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		System.exit(0);
	}

}
