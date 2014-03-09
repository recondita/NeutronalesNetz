package ui;

import java.util.Scanner;

import main.Main;

public class UI
{

	Main main;
	Scanner s = new Scanner(System.in);

	public UI(Main main)
	{
		this.main = main;
		System.out.println("-----NeuronalesNetzwerk-----");
	}

	public void mainMenu()
	{
		System.out.println("\n1.neus Netz erstellen\n2.Netz laden\n3.Netzt speichern\n4.NetzTrainieren");
		boolean exit = false;
		while (!exit)
		{
			try
			{
				switch (s.nextInt())
				{
				case 1:
					main.neuesNetzwerk();
					System.out.println("neues Netz erstellt");
					break;
				case 2:
					netzLaden();
					break;
				case 3:
					netzSpeichern();
					break;

				default:
					throw new Exception();
				}
			} catch (Exception e)
			{
				System.out.println("Ungueltige Eingabe");

			}
		}

	}

	private void netzLaden()
	{
		System.out.println("\nGeben sie Dateiname samt Pfad zum Gespeicherten Netz an");
		try
		{
			main.laden(s.nextLine());
			System.out.println("\nNetz geladen");
		} catch (Exception e)
		{
			System.out.println("Ungueltige Eingabe");
		}
	}

	private void netzSpeichern()
	{
		System.out.println("\nGeben sie Dateiname samt Pfad an");
		try
		{
			main.laden(s.nextLine());
			System.out.println("\nNetz gespeichert");
		} catch (Exception e)
		{
			System.out.println("Ungueltige Eingabe");
		}
	}

}
