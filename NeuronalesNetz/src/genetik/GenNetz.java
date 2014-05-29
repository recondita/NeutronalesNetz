package genetik;

import java.util.Timer;
import java.util.TimerTask;

import netz.Autosaver;

import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.learning.MomentumBackpropagation;

public class GenNetz extends MultiLayerPerceptron
{
	private static final long serialVersionUID = 1L;

	public GenNetz(Genom gene)
	{
		this(gene, 30000L);
	}

	public GenNetz(Genom gene, long maxTrainTime)
	{
		this(gene, maxTrainTime, null, false);
	}

	public GenNetz(Genom gene, long maxTrainTime, String speichern,
			boolean autoSave)
	{
		super(gene.getTransferFunktion(), gene.getLayers());
		MomentumBackpropagation learningRule = (MomentumBackpropagation) getLearningRule();
		learningRule.setLearningRate(gene.getLearningRate());
		learningRule.setMomentum(gene.getMomentum());
		learningRule.setMaxIterations(gene.getMaxIterations());
		Autosaver aS=null;
		if (maxTrainTime != 0)
		{
			Timer t = new Timer();
			TimerTask tt = new TimerTask() {

				@Override
				public void run()
				{
					stopLearning();
				}
			};
			t.schedule(tt, maxTrainTime);
			if(autoSave&&speichern!=null)
			{
				aS=new Autosaver(this,speichern);
			}
			learn(gene.getDataSet());
			if(aS!=null)
				aS.stop();
		}
		if (speichern != null)
			save(speichern);
	}

}
