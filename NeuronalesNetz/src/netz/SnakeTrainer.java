package netz;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.nnet.learning.BackPropagation;
import org.neuroph.util.TrainingSetImport;

public class SnakeTrainer implements Trainer
{
	DataSet trainingSet;
	public SnakeTrainer() throws NumberFormatException, FileNotFoundException, IOException
	{
			System.out.println("Lade Dataset");
            trainingSet = TrainingSetImport.importFromFile("kleinesSet", 100, 1, ",");
            System.out.println("fertig!");
	}
	
	public void train(NeuralNetwork<BackPropagation> netz)
	{
		System.out.println("Beginne das Netz zu Trainieren");
		netz.learn(trainingSet);
		System.out.println("fertig Trainiert");
	}

	public static void main(String[] agrs)
	{
		System.out.println("Erstelle Netz");
		SnakeNetz netz=SnakeNetz.newNetz(10, 10);
		System.out.println("fertig");
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
		System.out.println("fertig");
	}
}
