package netz;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.nnet.learning.BackPropagation;
import org.neuroph.util.TrainingSetImport;

public class SnakeTrainer implements Trainer
{
	DataSet trainingSet;
	public SnakeTrainer(String trainingSet) throws NumberFormatException, FileNotFoundException,
			IOException
	{
		System.out.println("Lade Dataset");
		this.trainingSet = TrainingSetImport
				.importFromFile(trainingSet, 200, 2, ",");
		System.out.println("fertig!");
	}

	public void train(NeuralNetwork<BackPropagation> netz)
	{
		System.out.println("Beginne das Netz zu Trainieren");
			netz.learn(trainingSet);
		System.out.println("fertig");
	}

	public static void main(String[] agrs)
	{
		Scanner sc=new Scanner(System.in);
		System.out.print("Netz speichern unter: ");
		String saveName=sc.nextLine();
		System.out.println("Erstelle Netz");
		SnakeNetz netz = SnakeNetz.newNetz();
		System.out.println("fertig");
		Autosaver as=new Autosaver(netz,saveName);
		try
		{
			new SnakeTrainer("doppelMatrix.log").train(netz);
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

		
		/*
				Scanner sc=new Scanner(System.in);
		System.out.print("Netz speichern unter: ");
		String line=sc.nextLine();
		while(!"end".equals(line.toLowerCase()))
		{
			netz.save(line);
			System.out.print("Netz speichern unter: ");
			line=sc.nextLine();
			System.out.println();
		}
		*/
		as.stop();
		netz.save(saveName);
		sc.close();
		System.out.println("Ende");
		System.exit(0);
	}
}
