package netz;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.nnet.learning.BackPropagation;
import org.neuroph.util.TrainingSetImport;

import ui.Abbrecher;

public class SnakeTrainer implements Trainer
{
	DataSet trainingSet;
	public SnakeTrainer() throws NumberFormatException, FileNotFoundException,
			IOException
	{
		System.out.println("Lade Dataset");
		trainingSet = TrainingSetImport
				.importFromFile("spiel.log", 100, 1, ",");
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
		SnakeNetz netz = SnakeNetz.newNetz(10, 10);
		System.out.println("fertig");
		new Autosaver(netz,saveName);
		try
		{
			new SnakeTrainer().train(netz);
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
		netz.save(saveName);
		sc.close();
		System.out.println("Ende");
	}
}
