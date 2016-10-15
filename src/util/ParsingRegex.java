package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParsingRegex {

	
	
	
	
	public static int[][] retCipherText(String cipherText) {
	
		Matcher m = parse(cipherText, "\\[(\\d.*?\\d)\\]");
		int counter=0;
		while (m.find()) counter++;
		 m = parse(cipherText, "\\[(\\d.*?\\d)\\]");
		int[][] parsedCipherText =  new int[counter][8];
		counter=0;
		while(m.find()) {
			String[] inputCipher = m.group(1).split(", ");
			for ( int i=0;i < inputCipher.length ;i++) {
				parsedCipherText[counter][i] = (Character.getNumericValue(inputCipher[i].charAt(0)));
			}
			counter++;
		}
		
		return parsedCipherText;
		}
	
	public static String[] retSecKey (String secretKey) {
		
		Matcher m = parse(secretKey,"(\\d+)");
		ArrayList<String> buffer = new ArrayList<String>();

		String[] secKey;
		
		while(m.find()){
			buffer.add( m.group(1));
		}
		return  buffer.toArray(new String[buffer.size()]);
		
	}
		
	
	
	
	
	static Matcher parse(String input, String pattern) {
		String in = input;

		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(in);

		return m;
	}

	public static void main(String[] args) {
		String in = "[[1, 1, 0, 0, 1, 0, 0, 0], [0, 1, 1, 0, 1, 1, 0, 1], [0, 1, 0, 0, 0, 0, 0, 0]]";
		System.out.println(Arrays.deepToString(retCipherText(in)));
		
		
	
		
	}
	
	}
	
	
	
