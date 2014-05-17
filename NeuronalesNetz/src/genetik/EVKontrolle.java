package genetik;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.neuroph.util.TrainingSetImport;
import org.neuroph.util.TransferFunctionType;

public class EVKontrolle {
	Genom gen[] = new Genom[8];
	Genom agen[] = new Genom[8];
	//int fit[] = new int[8];
	FitnessTester fT=new FitnessTester();

	public static void main(String[] args) {
		new EVKontrolle().init();
		System.exit(0);
	}

	public void init() {
		for (int i = 0; i < 8; i++) {
			
			try {
				gen[i] = new Genom(new int[] { 100, 36, 4, 2 }, 0.001, 0.7,
						50, TransferFunctionType.TANH,
						TrainingSetImport.importFromFile("spiel.log", 100, 2,
								","));
				//fit[i] = fT.test(gen[i],1000);
				//System.out.println(fit[i]);
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
		for(int j =0; j<8;j++){
			gen[j].mutation();
		}
		rekombination();
	}
	
	public void rekombination(){
		agen = gen.clone();
		java.util.Arrays.sort(agen);
		for(int i = 0; i < 8; i++){
			System.out.println(agen[i].getLearningRate());
		}
		for(int i = 0; i < 8; i++){
			System.out.println(agen[i].getFitness());
		}
		gen[2]= new Genom(agen[0],agen[1]);
		System.out.println("Genom 0 mit Genom 1");
		for(int i = 3; i<8;i++){
			int erst = Math.round((float)Math.random()*7);
			int zweit = Math.round((float)Math.random()*7);
			while(erst==zweit){
				zweit = Math.round((float)Math.random()*7);
			}
			gen[i]= new Genom(agen[erst],agen[zweit]);
			System.out.println("Genom " + erst + " mit Genom " + zweit);
		}
		for(int i = 0; i < 8; i++){
			System.out.println(gen[i].getLearningRate());
		}
		
		
		
	}

}
