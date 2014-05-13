package genetik;

import java.util.Timer;
import java.util.TimerTask;

import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.learning.MomentumBackpropagation;

public class GenNetz extends MultiLayerPerceptron
{
	private static final long serialVersionUID = 1L;

	public GenNetz(Genom gene)
	{
		super(gene.getTransferFunktion(),gene.getLayers());
		MomentumBackpropagation learningRule = (MomentumBackpropagation)getLearningRule();
		learningRule.setLearningRate(gene.getLearningRate());
		learningRule.setMomentum(gene.getMomentum());
		learningRule.setMaxIterations(gene.getMaxIterations());
		Timer t=new Timer();
		TimerTask tt=new TimerTask(){

			@Override
			public void run()
			{
				stopLearning();
			}
		};
		t.schedule(tt, 300000L);
		learn(gene.getDataSet());
	}

}
