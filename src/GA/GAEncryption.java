package GA;

import java.util.Arrays;  
import java.util.Random;

import UIInterface.MainInterface;


public class GAEncryption {

	private static MainInterface UI;
	
	public static String Crossover(int[] input)//Crossover operation swaps the position of two elements in the array, stimulating swapping bits position
	{
		
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
		UI.crossoverTextArea.append("After Crossover changes: " + Arrays.toString(input)+"\n");
		return theCkeys;
	}
	
	public static String Mutation(int[] input)//Mutation operation changes the selected bit from 0 to 1 or from 1 to 0 (100% chances of happening)
	{
		
		UI.mutationTextArea.append("Before Mutation changes: " + Arrays.toString(input)+"\n");
		Random rand = new Random();
		int Mkey = rand.nextInt(7);
		
		if(input[Mkey] == 0)
			input[Mkey] = 1;
		else
			input[Mkey] = 0;
		
		String theMkey = Integer.toString(Mkey);
		UI.mutationTextArea.append("After Mutation changes: " + Arrays.toString(input));
		return theMkey;
	}
	
	public static void runEncryption(MainInterface uiRef, int gen, int[] intInput, StringBuilder records, int singleCharVal) {
		
		UI=uiRef;
		
		UI.encryptionList.append("8-bit representation of number " + singleCharVal + " : " + Arrays.toString(intInput));
		for(int x = 0; x < gen; x++)//Generation starts here
		{

			UI.encryptionList.append("\n-------------------------------------------------------------\n");
			UI.encryptionList.append("Generating generation " + (x+1) + "..."+"\n");
			records.append(Crossover(intInput));//We use stringBuilder to append both the keys into a single integer variable
			records.append(Mutation(intInput));
			UI.encryptionList.append("Records of the secretkey: " + records+"\n");
		}
		

		UI.encryptionList.append("Final cipher of " + singleCharVal + " is : " + Arrays.toString(intInput)+ "\n");
		
	}

}
