package genetik;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

import org.neuroph.util.TrainingSetImport;
import org.neuroph.util.TransferFunctionType;

public class EVKontrolle
{
	private Genom[] gen;
	private Genom[] agen;
	// private FitnessTester[] fT;
	private int anzTests;
	private int generationCount = 0;
	private int maxTrainTime;
	private String saveDir;
	final static int cores = Runtime.getRuntime().availableProcessors();

	public EVKontrolle(int induvidien, int anzTests, int maxTrainTime,
			String saveDir) throws IOException
	{
		this.saveDir = (saveDir.charAt(saveDir.length() - 1) == File.separatorChar) ? saveDir
				: (saveDir + File.pathSeparatorChar);
		this.anzTests = anzTests;
		this.maxTrainTime = maxTrainTime;
		File dir = new File(saveDir);
		if (!dir.exists())
		{
			dir.mkdir();
		}
		String[] subDirs = dir.list();
		if (subDirs.toString().contains("Generation_"))
		{
			for (String subDir : subDirs)
			{
				if (subDir.contains("Generation_"))
				{
					try
					{
						int i = Integer.parseInt(subDir.replace("Generation_",
								""));
						if (i < generationCount)
							generationCount = i;
					} catch (Exception e)
					{
						e.printStackTrace();
					}
				}
			}
			String genDir = this.saveDir + "Generation_" + generationCount;
			String[] genFiles = new File(genDir).list();
			ArrayList<Genom> genomList = new ArrayList<Genom>(genFiles.length);
			genDir = genDir + File.separator;
			for (String genFile : genFiles)
			{
				if (genFile.contains("Genom_"))
				{
					BufferedReader bR = new BufferedReader(
							new InputStreamReader(new FileInputStream(genDir
									+ genFile), "UTF-8"));
					StringBuffer inhalt = new StringBuffer();
					String line = bR.readLine();
					while (line != null)
					{
						inhalt.append(line);
						line = bR.readLine();
					}
					bR.close();
					genomList.add(new Genom(inhalt.toString()));
				}
			}
			gen = genomList.toArray(new Genom[genomList.size()]);
			agen = new Genom[gen.length];

		} else
		{
			gen = new Genom[induvidien];
			agen = new Genom[induvidien];
			// fT = new FitnessTester[induvidien];
			for (int i = 0; i < gen.length; i++)
			{
				gen[i] = new Genom(new int[] { 100, 36, 4, 2 }, 0.001, 0.7,
						200, TransferFunctionType.TANH,
						TrainingSetImport.importFromFile("spiel.log", 100, 2,
								","));
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
		new File(generationDir.toString()).mkdir();
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
			EVKontrolle eK = new EVKontrolle(16, 10000, 300000, "EV");
			eK.entwickle(100);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		System.exit(0);
	}

}
