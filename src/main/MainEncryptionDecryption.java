package main;
import java.util.Arrays;
import java.util.Scanner;

import GA.GAEncryption;
import UIInterface.MainInterface;
import ann.ANNCategorization;
import ann.ANNSwapElement;
import ann.MainANN;

public class MainEncryptionDecryption {
	
	private static StringBuilder decrypted = new StringBuilder();
	private static StringBuilder[] records;
	private static int[][] intInput;
	private static int gen;
	private static char[] inputString;
	private static MainInterface UI;
	
	
	public MainEncryptionDecryption(MainInterface uiRef, String input, int generation) {
		UI=uiRef;
		inputString = input.toCharArray();
		gen = generation;
	}
	public static void main(String[] args) {
		
		
		
		System.out.println(" ======================" );
		final int gen = 5;//Change the generation here
		System.out.print("INPUT A STRING :  ");
		

		System.out.println(Arrays.toString(inputString));

		
	}
	
	public void encrypt() {

		

		intInput = new int[inputString.length][8];
		records = new StringBuilder[inputString.length];
		for (int index=0;index<inputString.length;index++) {
			
		int singleCharVal = (int) inputString[index];
		records[index] = new StringBuilder();
		
		byte b = (byte)singleCharVal;//Conversion to byte
		//Keep last 8 bits, bitwise AND operator to ensure positive value between 0 - 255 is retained
		//If we enter a value 4, the output will be 100, the previous 5 value is empty because its 0, hence we will get spaces, we then replace to replace white spaces to 0
		String binaryInput = String.format("%8s",Integer.toBinaryString(b & 0xFF)).replace(" ", "0");
		String[] stringOfCharBit = binaryInput.split("");
		//After splitting, the first element is definitely an empty element due to the nature of split operation, we do copyofRange to remove the first element
		//stringInput = Arrays.copyOfRange(stringInput, 1, stringInput.length);
	
		
		for(int bitOrder = 0; bitOrder < 8; bitOrder++)//Operation to parse the string numbers into integer
		{
			intInput[index][bitOrder] = Integer.parseInt(stringOfCharBit[bitOrder]);
		}
		GAEncryption.runEncryption(UI,gen, intInput[index], records[index], singleCharVal);
		}
		

		UI.encryptionList.append("FINAL CIPHER is : " + Arrays.deepToString(intInput)+ "\n");
	}
	
	public void decrypt() {

		System.out.println("decrypt : ");
		decrypted = new StringBuilder();
		for (int index=0;index<inputString.length;index++) {
			
		MainANN mainANN= new MainANN(UI, records[index],gen,intInput[index]);
		
		//DON'T NEW LINE
		decrypted.append(mainANN.decrypt());
		}
		
		UI.decryptionList.append(decrypted.toString());
	}
	
	public static void trainANN() {

		new ANNCategorization(UI).train();
		new ANNSwapElement(UI).trainCrossOver();
		new ANNSwapElement(UI).trainMutation();
	}

}
