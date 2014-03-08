package ui;

import java.util.Scanner;

import main.Main;

public class UI
{

	Main main;

	public UI(Main main)
	{
		this.main = main;
		System.out.println("-----NeuronalesNetzwerk-----");
	}

	public void mainMenu()
	{
		System.out.println("\n1.neus Netz erstellen\n2.Netz laden\n3.Netzt speichern\n4.NetzTrainieren");
		try
		{
			Scanner s = new Scanner(System.in);
			switch (s.nextInt())
			{
			case 1:
				main.neuesNetzwerk();
				System.out.println("neues Netz erstellt");
				break;
			case 2:
				netzLaden();
				break;
			case 3: netzSpeichern();
			break;
			
			default:
				throw new Exception();
			}
		} catch (Exception e)
		{
			System.out.println("Ungueltige Eingabe");
		}

	}

	private void netzLaden()
	{

	}
	
	private void netzSpeichern()
	{
		
	}

}
