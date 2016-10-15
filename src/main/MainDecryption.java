package main;

import UIInterface.MainDecryptionInterface; 
import ann.ANNCategorization;
import ann.ANNSwapElement;

public class MainDecryption {

	

	private static StringBuilder decrypted = new StringBuilder();
	private static int[][] cipherText;
	private static String[] secretKey;

	private static MainDecryptionInterface UI;

	public MainDecryption(MainDecryptionInterface uiRef, int[][] cipher, String[] secretK) {
		UI=uiRef;
		cipherText = cipher;
		secretKey = secretK;
	}
	
	public MainDecryption(MainDecryptionInterface uiRef) {
		UI=uiRef;
	}
	
	public void decrypt() {

		decrypted = new StringBuilder();
		for (int index=0;index<cipherText.length;index++) {
			
		decrypted.append(retDecryptedChar(secretKey[index], cipherText[index]));
		}
		
		UI.decryptedText.append(decrypted.toString());
	}
	
	public char retDecryptedChar(String singleSecretKey, int[] singleCipherText) {
			ANNCategorization ANNcat= new ANNCategorization(UI);
			
			int generation = ANNcat.preProcess(singleSecretKey);

			double[][][] crossAndMut = ANNcat.run(generation, singleCipherText);

			ANNSwapElement ANNswap= new ANNSwapElement(UI);
			int[] decryptedText = ANNswap.run(generation,singleCipherText,crossAndMut);
			
			StringBuilder binaryText = new StringBuilder();
			for (int i=0; i< decryptedText.length; i++) {
				binaryText.append(decryptedText[i]);
			}
			
			int decryptedChar = Integer.parseInt(binaryText.toString(), 2);
			UI.decryptionList.append("Decrypted char : " +(char)decryptedChar + "\n");
			return ((char) decryptedChar);
		
	}
	

	public static void trainANN(MainDecryptionInterface UI) {

		new ANNCategorization(UI).train();
		new ANNSwapElement(UI).trainCrossOver();
		new ANNSwapElement(UI).trainMutation();
	}
	
	public String getDecryptedMessage() {
		return decrypted.toString();
	}
}
