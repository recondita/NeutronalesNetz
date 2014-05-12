package snakeCOM;

import java.io.File;

import org.neuroph.core.NeuralNetwork;

public class SnakePlayer extends SnakeControl
{

	NeuralNetwork<?> nn;

	public SnakePlayer(NeuralNetwork<?> nn)
	{
		this.nn = nn;
		brett.start();
	}

	@Override
	public void preMove()
	{

		nn.setInput(brett.toDoubleArray());
		nn.calculate();
		double[] drichtung=nn.getOutput();
		System.out.println("Netz sagt: "+ drichtung[0]+" "+drichtung[1]);
		snake.setRichtung(drichtung[0],drichtung[1]);
	}

	@Override
	public void verloren(int laenge)
	{
		// TODO Auto-generated method stub

	}

	public static void main(String[] args)
	{
		new SnakePlayer(NeuralNetwork.createFromFile(new File("friss.nn")));
	}

}
