package genetik;

import java.util.Timer;
import java.util.TimerTask;

import netz.Autosaver;

import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.learning.MomentumBackpropagation;

public class GenNetz extends MultiLayerPerceptron
{
	private static final long serialVersionUID = 1L;

	/**
	 * Erstellt aus dem Genom ein Neuronales Netz und Trainiert es 5 Minuten lang
	 * @param gene Genom aus dem das Netz generiert wird.
	 */
	public GenNetz(Genom gene)
	{
		this(gene, 30000L);
	}

	/**
	 * Erstellt aus dem Genom ein Neuronales Netz
	 * @param gene Genom aus dem das Netz generiert wird
	 * @param maxTrainTime Zeit die dem Netz maximal zum Trainieren zur Verfuegung stehen
	 */
	public GenNetz(Genom gene, long maxTrainTime)
	{
		this(gene, maxTrainTime, null, false);
	}

	/**
	 * Erstellt aus dem Genom ein Neuronales Netz
	 * @param gene Genom aus dem das Netz generiert wird
	 * @param maxTrainTime Zeit die dem Netz maximal zum Trainieren zur Verfuegung stehen
	 * @param speichern Pfad wohin es am Ende gespeichert wird
	 * @param autoSave während dem trainieren automatisch zwischenspeichern?
	 */
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
