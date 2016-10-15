package main;
import java.util.Arrays; 
import java.util.Random;
import java.util.Scanner;

import GA.GAEncryption;
import UIInterface.MainDecryptionInterface;
import UIInterface.MainEncryptionInterface;
import ann.ANNCategorization;
import ann.ANNSwapElement;

public class MainEncryption {
	
	private static int[][] intInput;
	private static int[] gen;
	private static MainEncryptionInterface UI;
	private static char[] normalText;
	private static StringBuilder[] secretKey;
	
	
	public MainEncryption(MainEncryptionInterface uiRef, String input) {
		UI=uiRef;
		normalText = input.toCharArray();
		gen = new int[normalText.length];
		secretKey = new StringBuilder[normalText.length];
	}
	
	public void encrypt() {

		intInput = new int[normalText.length][8];
		Random rand= new Random();
		UI.secretKeyOutput.setText("");
		UI.cipherTextOutput.setText("");

		for (int index=0;index<normalText.length;index++) {
			
			int singleCharVal = (int) normalText[index];
			secretKey[index] = new StringBuilder();
			
		
			byte b = (byte)singleCharVal;//Conversion to byte
			String binaryInput = String.format("%8s",Integer.toBinaryString(b & 0xFF)).replace(" ", "0");
			String[] stringOfCharBit = binaryInput.split("");
			for(int bitOrder = 0; bitOrder < 8; bitOrder++)//Operation to parse the string numbers into integer
			{
				intInput[index][bitOrder] = Integer.parseInt(stringOfCharBit[bitOrder]);
			}
			gen[index] = rand.nextInt(10)+1;
		
			GAEncryption.runEncryption(UI,gen[index], intInput[index], secretKey[index], singleCharVal);
		}
		

		

		UI.secretKeyOutput.append(Arrays.toString(secretKey));
		UI.cipherTextOutput.append(Arrays.deepToString(intInput));
		

		UI.encryptionList.append("FINAL CIPHER is : " + Arrays.deepToString(intInput)+ "\n");
	}
	
	public int[][] getCipherText() {
		return intInput;
	}
	
	public StringBuilder[] getSecretKey() {
		return secretKey;
	}
	

	

}
