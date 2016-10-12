package GA;

import java.util.Arrays; 
import java.util.Random;
import java.util.Scanner;

import ann.ANNCategorization;
import ann.MainANN;

public class GAEncryption {

	
	public static String Crossover(int[] input)//Crossover operation swaps the position of two elements in the array, stimulating swapping bits position
	{
		

		System.out.println("Before Crossover changes: " + Arrays.toString(input));
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
		System.out.println("After Crossover changes: " + Arrays.toString(input));
		return theCkeys;
	}
	
	public static String Mutation(int[] input)//Mutation operation changes the selected bit from 0 to 1 or from 1 to 0 (100% chances of happening)
	{
		
		System.out.println("Before Mutation changes: " + Arrays.toString(input));
		Random rand = new Random();
		int Mkey = rand.nextInt(7);
		
		if(input[Mkey] == 0)
			input[Mkey] = 1;
		else
			input[Mkey] = 0;
		
		String theMkey = Integer.toString(Mkey);
		System.out.println("After Mutation changes: " + Arrays.toString(input));
		return theMkey;
	}
	
	public static void runEncryption(int gen, int[] intInput, StringBuilder records, int input) {

		
		
		for(int x = 0; x < gen; x++)//Generation starts here
		{
			System.out.println("8-bit representation of number " + input + " : " + Arrays.toString(intInput));
			System.out.println("Generating generation " + (x+1) + "...");
			records.append(Crossover(intInput));//We use stringBuilder to append both the keys into a single integer variable
			records.append(Mutation(intInput));
			System.out.println("Records of the secretkey: " + records);
			System.out.println("\n-------------------------------------------------------------\n");
		}
		

		System.out.println("Result of " + input + " : " + Arrays.toString(intInput));
		
	}

}
