package genetik;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.neuroph.util.TrainingSetImport;
import org.neuroph.util.TransferFunctionType;

public class EVKontrolle {
	Genom gen[] = new Genom[8];
	Genom agen[] = new Genom[8];
	int fit[] = new int[8];
	FitnessTester fT=new FitnessTester();

	public static void main(String[] args) {
		new EVKontrolle().init();
	}

	public void init() {
		for (int i = 0; i < 8; i++) {
			
			try {
				gen[i] = new Genom(new int[] { 100, 36, 4, 2 }, 0.001, 0.7,
						Integer.MAX_VALUE, TransferFunctionType.TANH,
						TrainingSetImport.importFromFile("kleinesSet", 100, 2,
								","));
				fit[i] = fT.test(gen[i],50);
				System.out.println(fit[i]);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void rekombination(){
		
		
	}

}
