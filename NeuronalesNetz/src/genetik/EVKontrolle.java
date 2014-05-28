package genetik;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.neuroph.util.TrainingSetImport;
import org.neuroph.util.TransferFunctionType;

import java.lang.Thread;
import java.util.LinkedList;

public class EVKontrolle
{
	private Genom[] gen;
	private Genom[] agen;
	// private FitnessTester[] fT;
	private int anzTests;
	private int durchlaeufe = 0;
	private int maxTrainTime;
	final static int cores = Runtime.getRuntime().availableProcessors();

	public static void main(String[] args)
	{
		EVKontrolle eK = new EVKontrolle(10, 10000,300000);
		eK.entwickle(100);
		System.exit(0);
	}

	public EVKontrolle(int induvidien, int anzTests, int maxTrainTime)
	{
		this.anzTests = anzTests;
		this.maxTrainTime=maxTrainTime;
		gen = new Genom[induvidien];
		agen = new Genom[induvidien];
		// fT = new FitnessTester[induvidien];
		for (int i = 0; i < gen.length; i++)
		{
			// fT[i] = new FitnessTester();
			try
			{
				gen[i] = new Genom(new int[] { 100, 36, 4, 2 }, 0.001, 0.7, 50,
						TransferFunctionType.TANH,
						TrainingSetImport.importFromFile("spiel.log", 100, 2,
								","));
			} catch (NumberFormatException e)
			{
				e.printStackTrace();
			} catch (FileNotFoundException e)
			{
				e.printStackTrace();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		mutation();
		testeFitness();
	}

	public void entwickle(int anzGenerationen)
	{
		for (int i = 0; i < anzGenerationen; i++)
		{
			durchlaeufe++;
			System.out.println("Durchlauf: " + durchlaeufe);
			rekombination();
			mutation();
			testeFitness();
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
				gen[job].setFitness(new FitnessTester(maxTrainTime).testWerteZeit(
						gen[job], anzTests));
			}

		}

	}

}
