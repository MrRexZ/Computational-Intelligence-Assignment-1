package ann;

import java.util.Arrays;

public class MainANN {

	private static StringBuilder records;
	private static int generation;
	private static double[][][] crossAndMut;
	private static int[] cipherText;
	
	
	
	public MainANN(StringBuilder s,int gen , int[] intInput) {
		records=s;
		generation=gen;
		cipherText=intInput;
		
		
		
	}
	
	public char decrypt() {
		ANNCategorization ANNcat= new ANNCategorization();
		
		ANNcat.preProcess(records);

		crossAndMut = ANNcat.run(generation);

        System.out.println("FINAL CIPHER :" + Arrays.toString(cipherText));
		ANNSwapElement ANNswap= new ANNSwapElement();
		int[] decryptedText = ANNswap.run(generation,cipherText,crossAndMut);
		
		StringBuilder text = new StringBuilder();
		for (int i=0; i< decryptedText.length; i++) {
			text.append(decryptedText[i]);
		}
		
		int decryptedChar = Integer.parseInt(text.toString(), 2);
		System.out.println((char)decryptedChar);
		return ((char) decryptedChar);
	}
}
