package netz;

import org.neuroph.core.NeuralNetwork;

public class Autosaver
{

	boolean weiter = true;
	final String file;
	final NeuralNetwork<?> nn;
	final Thread thread;

	/**
	 * Speichert das Netz automatisch einmal pro Minute
	 * @param neuralNetwork
	 * @param file Pfad in den das Netz gespeichert wird
	 */
	public Autosaver(NeuralNetwork<?> neuralNetwork, String pfad)
	{
		this.nn = neuralNetwork;
		this.file = pfad;
		thread = new Thread() {
			@Override
			public void run()
			{
				try
				{
					sleep(30000L);
				} catch (InterruptedException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				while (weiter)
				{					
					try
					{
						nn.save(file);
						System.out.println("AutoSave");
						sleep(60000L);
					} catch (Exception e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		thread.start();
	}

	/**
	 * Beendet den Autosaver
	 */
	public void stop()
	{
		weiter = false;
	}
}
