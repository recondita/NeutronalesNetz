package genetik;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.neuroph.util.TrainingSetImport;
import org.neuroph.util.TransferFunctionType;

public class EVKontrolle
{
	Genom gen[] = new Genom[8];
	Genom agen[] = new Genom[8];
	// int fit[] = new int[8];
	FitnessTester fT = new FitnessTester();
	int durchlaufe = 0;

	public static void main(String[] args)
	{
		new EVKontrolle().init();
		System.exit(0);
	}

	public void init()
	{
		for (int i = 0; i < 8; i++)
		{

			try
			{
				gen[i] = new Genom(new int[] { 100, 36, 4, 2 }, 0.001, 0.7, 50,
						TransferFunctionType.TANH,
						TrainingSetImport.importFromFile("spiel.log", 100, 2,
								","));
				// fit[i] = fT.test(gen[i],1000);
				// System.out.println(fit[i]);
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
		for (int j = 0; j < 8; j++)
		{
			gen[j].mutation();
		}
	}

	public void entwickle(int anzGenerationen)
	{
		for(int i=0; i<anzGenerationen; i++)
		{
			rekombination();
			mutation();
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
	
	public void rekombination()
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

	public void mutation()
	{
		for (int i = 2; i < 8; i++)
		{
			gen[i].mutation();
		}
	}

}
