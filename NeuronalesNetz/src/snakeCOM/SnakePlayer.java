package snakeCOM;

import java.io.File;

import org.neuroph.core.NeuralNetwork;

/**
 * Laesst ein Neuronales Netz Snake Spielen
 */
public class SnakePlayer extends SnakeControl
{

	private NeuralNetwork<?> nn;

	public SnakePlayer(NeuralNetwork<?> nn)
	{
		this.nn = nn;
	}
	
	public SnakePlayer(NeuralNetwork<?> nn, boolean ausgabe)
	{
		super(false);
		this.nn = nn;
	}
	

	@Override
	public void afterMove()
	{
		nn.setInput(brett.toDoubleArray());
		nn.calculate();
		double[] drichtung=nn.getOutput();
		snake.setRichtung(drichtung[0],drichtung[1]);
	}

	public void verloren(int laenge)
	{
		// TODO Auto-generated method stub

	}

	public void start()
	{
		brett.start();
	}
	
	public static void main(String[] args)
	{
		new SnakePlayer(NeuralNetwork.createFromFile(new File("EVProdukt.nn"))).start();
	}

	protected void setNetwork(NeuralNetwork<?> nn)
	{
		this.nn=nn;
	}

}
