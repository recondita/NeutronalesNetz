package netz;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.util.TrainingSetImport;

public class SnakeTrainer
{
	/**
	 * Trainingset mit dem Trainiert wird
	 */
	private DataSet trainingSet;

	/**
	 * 
	 * @param trainingSet Pfad zum Trainingset mit dem trainiert werden soll
	 * @param input Anzahl der Input Neuronen
	 * @param output Anzahl der output Neuronen
	 * @throws NumberFormatException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public SnakeTrainer(String trainingSet, int input, int output)
			throws NumberFormatException, FileNotFoundException, IOException
	{
		System.out.println("Lade Dataset");
		this.trainingSet = TrainingSetImport.importFromFile(trainingSet, input,
				output, ",");
		System.out.println("fertig!");
	}

	/**
	 * Trainiert ein Neuronales Netz eine bestimmte Zeit lang
	 * @param netz Netz das trainiert werden soll
	 * @param time Zeit wie lange es trainiert werden soll
	 */
	public void train(final NeuralNetwork<?> netz, long time)
	{
		System.out.println("Beginne das Netz zu Trainieren");
		if (time > 0)
		{
			Timer t = new Timer();
			TimerTask tt = new TimerTask() {

				@Override
				public void run()
				{
					netz.stopLearning();
				}
			};
			t.schedule(tt, time);
		}
		netz.learn(trainingSet);
		System.out.println("fertig");
	}
	

	/**
	 * Main Methode um ein Netz zu Trainieren.
	 * @param agrs werden ignoriert
	 */
	public static void main(String[] agrs)
	{
		Scanner sc = new Scanner(System.in);
		System.out.print("Netz speichern unter: ");
		String saveName = sc.nextLine();
		System.out.println("Erstelle Netz");
		SnakeNetz netz = SnakeNetz.newNetz(10,10);
		System.out.println("fertig");
		System.out.println("Pfad des Trainingsets ");
		
		Autosaver as = new Autosaver(netz, saveName);
		try
		{
			new SnakeTrainer(sc.nextLine(), 100, 2).train(netz,300000);
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
		as.stop();
		netz.save(saveName);
		sc.close();
		System.out.println("Ende");
		System.exit(0);
	}
}
