package SnakeCOM;

import javax.swing.JOptionPane;

import snake.Spielbrett;

public class LogBrett extends Spielbrett
{


	private static final long serialVersionUID = 4278636303152012557L;
	private SnakeLogger sl;
	
	public LogBrett(SnakeLogger sl)
	{
		super(10,10);
		this.sl=sl;
	}
	
	@Override
	public void newSnake()
	{
		snake=sl.newSnake();
	}
	
	@Override
	public void verloren(int laenge)
	{
		boolean loop=false;
		int auswahl;
		do{
		auswahl=JOptionPane.showConfirmDialog(null, "Speichern?");
		if(auswahl>0)
			loop=0!=JOptionPane.showConfirmDialog(null, "Wirklich?");
		}while(loop);
		switch(auswahl)
		{
		case 0: sl.speichern(5);
		break;
		case 1: break;
		case 2: System.exit(0);
		}
		
		super.verloren(laenge);
	}

}
