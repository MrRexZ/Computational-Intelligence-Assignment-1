import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String args[])
	{
		final int gen = 10;
		
		//User should enter only 1 value (0-100), and system should be able to automatically convert
		//to the respective bit representation.
		
		System.out.print("Enter 8 values: ");
		Scanner single = new Scanner(System.in);
		Scanner scan = new Scanner(single.nextLine());
		int [] input = new int[8];
		StringBuilder records = new StringBuilder();
		
		
		//Should correct this loop below as per requirement stated in LINE 11.
		for(int a = 0; a < 8; a++)
		{
			if(scan.hasNext())
			{
				input[a] = scan.nextInt();
			}
			else
			{
				System.out.println("Not enough numbers are provided!");
				break;
			}
		}
		
		
		//Shouldn't these 3 lines below be inside the loop? Otherwise it will contain gen+1 generation.
		records.append(Crossover(input));
		records.append(Mutation(input));
		System.out.println(records);
		
		
		//For StringBuilder in records, you should clear the ciphertext (but not the cross-over point and mutation point.) generated 
		//from previous generation. Otherwise, the original text is visible.
		//What you can do : Create 3 stringbuilder. 1 is for original text, 1 is for crossover point, 1 is for mutation point.
		//After generation is finished, append all 3 stringbuilder contents together.
		for(int x = 0; x < gen; x++)
		{
			System.out.println("Generating generation " + (x+1) + "...");
			//Clear the stringbuilder containing texts. We only need to keep the last generation text.
			//Append the crossover point and mutation point to their RESPECTIVE stringbuilder.
			records.append(Crossover(input));
			records.append(Mutation(input));
			System.out.println(records);
		}
		
		//Append all 3 stringbuilder texts here after all generation is finished as final String.
		
		
	}
	
	
	//For this method below, intial idea of crossover should be between 2 components (key and the input). But this is fine although
	//is less secure than initial idea.
	public static String Crossover(int[] input)
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
		System.out.println(Arrays.toString(input));
		return theCkeys;
	}
	
	//Mutation should change the bit representation of 1 to 0 and 0 to 1. 
	public static String Mutation(int[] input)
	{
		Random rand = new Random();
		int Mkey = rand.nextInt(7);
		
		
		//255 below should be replaced by 1
		input[Mkey] = 255 - input[Mkey];
		
		String theMkey = Integer.toString(Mkey);
		System.out.println(Arrays.toString(input));
		return theMkey;
	}
}
