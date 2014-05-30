package genetik;

import java.io.File;

public class EVRenamer
{

	public EVRenamer()
	{
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args)
	{
		String pfad = "EV";
		String dirs[] = new File(pfad).list();
		for (String dir : dirs)
		{
			File f = new File(pfad + File.separator + dir);
			if(f.isDirectory())
			{
				String[] gene = f.list();
				for (String gen : gene)
				{
					File genFile = new File(pfad + File.separator + dir
							+ File.separator + gen);
					if (gen.contains("Genom")&&!gen.endsWith(".gen"))
					{
						genFile.renameTo(new File(genFile.getAbsolutePath()
								+ ".gen"));
					}
				}
			}
		}
	}

	public static void rename(String pfad)
	{
		File hier = new File(pfad);
		if (hier.isDirectory())
		{

		}
	}

}
