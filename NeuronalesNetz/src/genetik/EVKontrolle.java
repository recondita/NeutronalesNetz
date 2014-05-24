package genetik;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.neuroph.util.TrainingSetImport;
import org.neuroph.util.TransferFunctionType;

public class EVKontrolle
{
	private Genom[] gen;
	private Genom[] agen;
	private FitnessTester fT = new FitnessTester();
	private int anzTests;


	public static void main(String[] args)
	{
		EVKontrolle eK=new EVKontrolle(8,1000);
		eK.entwickle(10);
		System.exit(0);
	}

	public EVKontrolle(int induvidien, int anzTests)
	{
		this.anzTests=anzTests;
		gen=new Genom[induvidien];
		agen=new Genom[induvidien];
		for (int i = 0; i < gen.length; i++)
		{

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
		for(int i=0; i<anzGenerationen; i++)
		{
			rekombination();
			mutation();
			testeFitness();
			System.out.println(toString());
		}
	}
	
	public String toString()
	{
		StringBuilder sb=new StringBuilder();
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
		for (int i = 3; i < 8; i++)
		{
			int erst = (int)(Math.random() * agen.length);
			int zweit = (int)(Math.random() * agen.length-1);
			if(zweit>=erst) zweit++;
			gen[i] = new Genom(agen[erst], agen[zweit]);
			System.out.println("Genom " + erst + " mit Genom " + zweit);
		}
	}

	private void mutation()
	{
		for (int i = 2; i < gen.length; i++)
		{
			gen[i].mutation();
		}
	}
	
	private void testeFitness()
	{
		for (int j = 0; j < gen.length; j++)
		{
			gen[j].setFitness(fT.test(gen[j], anzTests));
		}
	}

}
