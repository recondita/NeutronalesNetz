package netz;

import org.neuroph.core.NeuralNetwork;

public class Autosaver
{

	boolean weiter = true;
	final String file;
	final NeuralNetwork<?> nn;
	final Thread thread;

	public Autosaver(NeuralNetwork<?> neuralNetwork, String name)
	{
		this.nn = neuralNetwork;
		this.file = name;
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

	public void stop()
	{
		weiter = false;
	}
}
