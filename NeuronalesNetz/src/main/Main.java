package main;

import netz.Loader;
import netz.SimpleNetwork;
import ui.UI;

public class Main
{

	private UI ui;
	private SimpleNetwork nW;
	//private boolean unsaved = false;

	public Main()
	{
		ui = new UI(this);
	}

	public void neuesNetzwerk()
	{
		nW = new SimpleNetwork();
	}

	public void speichern(String dest)
	{
		Loader.save(nW, dest);
	}

	public void laden(String pfad)
	{
		nW = (SimpleNetwork) Loader.load(pfad);
	}

	public static void main(String[] args)
	{
		Main m=new Main();
		m.ui.mainMenu();
	}

}
