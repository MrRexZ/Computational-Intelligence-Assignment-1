package GA;

import java.util.ArrayList; 
import java.util.Arrays;  
import java.util.Random;

import UIInterface.MainEncryptionInterface;


public class GAEncryption {

	private static MainEncryptionInterface UI;
	
	public static String Crossover(int[] input, int gen, char charID)//Crossover operation swaps the position of two elements in the array, stimulating swapping bits position
	{
		UI.crossoverTextArea.append(String.format("Generation %d of char '%c' :\n", gen, charID));
		UI.crossoverTextArea.append("Before Crossover changes: " + Arrays.toString(input) +"\n");
		Random rand = new Random();
		int Ckey1 = rand.nextInt(7);
		int Ckey2 = rand.nextInt(7);
		while(Ckey1 == Ckey2)
		{
			Ckey2 = rand.nextInt(7);
		}
		
		int temp = input[Ckey1];
		input[Ckey1] = input[Ckey2];
		input[Ckey2] = temp;
		
		String theCkeys = Integer.toString(Ckey1) + Integer.toString(Ckey2);
		UI.crossoverTextArea.append("After Crossover changes: " + Arrays.toString(input)+"\n=============================\n");
		return theCkeys;
	}
	
	public static String Mutation(int[] input, int gen, char charID)//Mutation operation changes the selected bit from 0 to 1 or from 1 to 0 (100% chances of happening)
	{

		UI.mutationTextArea.append(String.format("Generation %d of char '%c' :\n", gen, charID));
		UI.mutationTextArea.append("Before Mutation changes: " + Arrays.toString(input)+"\n");
		Random rand = new Random();
		int Mkey = rand.nextInt(7);
		
		if(input[Mkey] == 0)
			input[Mkey] = 1;
		else
			input[Mkey] = 0;
		
		String theMkey = Integer.toString(Mkey);
		UI.mutationTextArea.append("After Mutation changes: " + Arrays.toString(input)+"\n==============================\n");
		return theMkey;
	}
	
	public static void runEncryption(MainEncryptionInterface uiRef, int gen, int[] intInput, StringBuilder records, int singleCharVal) {
		
		UI=uiRef;
		int[] originalInput= intInput.clone();
		UI.encryptionList.append("8-bit representation of char '"+ (char) singleCharVal +"' ASCII code " + singleCharVal + " : " + Arrays.toString(intInput) + "\n");
		int generation=0;
		while(!isDifferentFourBit(intInput,originalInput))//Generation starts here
		{
			UI.encryptionList.append("-----------------------------------------\n");
			UI.encryptionList.append("Generation " + (generation+1) + "..."+"\n");
			records.append(Crossover(intInput,generation,(char) singleCharVal));//We use stringBuilder to append both the keys into a single integer variable
			records.append(Mutation(intInput,generation,(char) singleCharVal));
			UI.encryptionList.append("Records of the secretkey: " + records+"\n");
			generation++;
		}
		

		UI.encryptionList.append("Final cipher of '" + (char) singleCharVal + "' is : " + Arrays.toString(intInput)+ "\n===================================\n");
		
		
	}
	
	public static boolean isDifferentFourBit(int[] intInput,int[] originalInput) {
		int counter=0;
		for (int i=0;i<intInput.length;i++) {
			if (intInput[i]==originalInput[i]) counter++;
			if (counter>=4) return false;
		}
		return true;
	}

}
