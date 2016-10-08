import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String args[])
	{
		final int gen = 10;
		System.out.print("Enter 8 values: ");
		Scanner single = new Scanner(System.in);
		Scanner scan = new Scanner(single.nextLine());
		int [] input = new int[8];
		StringBuilder records = new StringBuilder();
		
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
		
		records.append(Crossover(input));
		records.append(Mutation(input));
		System.out.println(records);
		
		for(int x = 0; x < gen; x++)
		{
			System.out.println("Generating generation " + (x+1) + "...");
			records.append(Crossover(input));
			records.append(Mutation(input));
			System.out.println(records);
		}
	}
	
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
	
	public static String Mutation(int[] input)
	{
		Random rand = new Random();
		int Mkey = rand.nextInt(7);
		
		input[Mkey] = 255 - input[Mkey];
		
		String theMkey = Integer.toString(Mkey);
		System.out.println(Arrays.toString(input));
		return theMkey;
	}
}
