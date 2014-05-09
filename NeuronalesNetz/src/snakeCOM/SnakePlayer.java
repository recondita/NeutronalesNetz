package snakeCOM;

import org.neuroph.core.NeuralNetwork;

public class SnakePlayer extends SnakeControl
{

	NeuralNetwork<?> nn;
	public SnakePlayer(NeuralNetwork<?> nn)
	{
		this.nn=nn;
	}
	@Override
	public void preMove()
	{
		//TODO: nn.setInput(arg0);
		
	}
	@Override
	public void verloren(int laenge)
	{
		// TODO Auto-generated method stub
		
	}
	
	

}
