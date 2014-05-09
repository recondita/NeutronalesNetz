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
		double drichtung=nn.getOutput()[0];
		System.out.println("Netz sagt: "+ drichtung);
		snake.setRichtung((int) Math.round(drichtung * 4));
	}

	@Override
	public void verloren(int laenge)
	{
		// TODO Auto-generated method stub

	}

	public static void main(String[] args)
	{
		new SnakePlayer(NeuralNetwork.createFromFile(new File("klein.nn")));
	}

}
