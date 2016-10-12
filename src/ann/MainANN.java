package ann;

import java.util.Arrays;

import UIInterface.MainInterface;

public class MainANN {

	private static StringBuilder records;
	private static int generation;
	private static double[][][] crossAndMut;
	private static int[] cipherText;
	private static MainInterface UI;
	
	
	
	public MainANN(MainInterface uiRef,StringBuilder s,int gen , int[] intInput) {
		UI=uiRef;
		records=s;
		generation=gen;
		cipherText=intInput;
		
		
		
	}
	
	public char decrypt() {
		ANNCategorization ANNcat= new ANNCategorization(UI);
		
		ANNcat.preProcess(records);

		crossAndMut = ANNcat.run(generation);

		ANNSwapElement ANNswap= new ANNSwapElement(UI);
		int[] decryptedText = ANNswap.run(generation,cipherText,crossAndMut);
		
		StringBuilder text = new StringBuilder();
		for (int i=0; i< decryptedText.length; i++) {
			text.append(decryptedText[i]);
		}
		
		int decryptedChar = Integer.parseInt(text.toString(), 2);
		UI.decryptionList.append("Decrypted char : " +(char)decryptedChar + "\n");
		return ((char) decryptedChar);
	}
}
