package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import org.neuroph.core.NeuralNetwork;

public class Saver extends JFrame
{


	private static final long serialVersionUID = 1L;

	public Saver(final NeuralNetwork<?> nn)
	{
		JButton abbrechen=new JButton();
		setSize(200,100);
		abbrechen.setText("Trainieren abbrechen");
		abbrechen.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				nn.stopLearning();
				dispose();
				
			}});
		add(abbrechen);
		setVisible(true);
	}

}
