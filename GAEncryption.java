package GA;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import ann.ANNHiddenLayer;

public class GAEncryption {

	public static void main(String args[])
	{
		final int gen = 2;//Change the generation here
		System.out.print("Enter a value between 0 to 255: ");
		Scanner scan = new Scanner(System.in);
		String[] inputString = scan.next().split("");
		
		int input = inputString[0].charAt(0);
		System.out.println(input);
		StringBuilder records = new StringBuilder();
		
		if(input < 0 || input > 255)//Validation for the 0 - 255 value
		{
			System.out.print("Invalud input, please reenter a value between 0 to 255: ");
		//	input = scan.nextInt();
		}
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
		ANNHiddenLayer ANN= new ANNHiddenLayer();
		ANN.preProcess(records);
		
	}
	
	public static String Crossover(int[] input)//Crossover operation swaps the position of two elements in the array, stimulating swapping bits position
	{
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
		System.out.println("Crossover changes: " + Arrays.toString(input));
		return theCkeys;
	}
	
	public static String Mutation(int[] input)//Mutation operation changes the selected bit from 0 to 1 or from 1 to 0 (100% chances of happening)
	{
		Random rand = new Random();
		int Mkey = rand.nextInt(7);
		
		if(input[Mkey] == 0)
			input[Mkey] = 1;
		else
			input[Mkey] = 0;
		
		String theMkey = Integer.toString(Mkey);
		System.out.println("Mutation changes: " + Arrays.toString(input));
		return theMkey;
}

}
