import java.util.Arrays;
import java.util.Scanner;

import GA.GAEncryption;
import ann.ANNCategorization;
import ann.ANNSwapElement;
import ann.MainANN;

public class MainEncryptionDecryption {
	
	private static StringBuilder decrypted = new StringBuilder();
	
	public static void main(String[] args) {
		
		trainANN();
		
		
		System.out.println(" ======================" );
		final int gen = 5;//Change the generation here
		System.out.print("INPUT A STRING :  ");
		Scanner scan = new Scanner(System.in);
		char[] inputString = scan.nextLine().toCharArray();
		

		System.out.println(Arrays.toString(inputString));
		for (int index=0;index<inputString.length;index++) {
		int input = inputString[index];
		StringBuilder records = new StringBuilder();
		
		byte b = (byte)input;//Conversion to byte
		//Keep last 8 bits, bitwise AND operator to ensure positive value between 0 - 255 is retained
		//If we enter a value 4, the output will be 100, the previous 5 value is empty because its 0, hence we will get spaces, we then replace to replace white spaces to 0
		String binaryInput = String.format("%8s",Integer.toBinaryString(b & 0xFF)).replace(" ", "0");
		String[] stringInput = binaryInput.split("");
		//After splitting, the first element is definitely an empty element due to the nature of split operation, we do copyofRange to remove the first element
		//stringInput = Arrays.copyOfRange(stringInput, 1, stringInput.length);
		System.out.println(stringInput.toString());
		int[] intInput = new int[8];
		
		for(int a = 0; a < intInput.length; a++)//Operation to parse the string numbers into integer
		{
			intInput[a] = Integer.parseInt(stringInput[a]);
		}
		
		GAEncryption.runEncryption(gen, intInput, records,input);
		MainANN mainANN= new MainANN(records,gen,intInput);
		decrypted.append(mainANN.decrypt());
		}
		
		System.out.println("DECRYPTED STRING :" + decrypted.toString());
	}
	
	public static void trainANN() {


		new ANNCategorization().train();
		new ANNSwapElement().trainCrossOver();
		new ANNSwapElement().trainMutation();
	}

}
