package ann;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class ANNHiddenLayer {

    static MutableDouble hiddenOutput = new MutableDouble(0);
    static double output;
    static int errorCount = 0;
	static double learningRate = 2.9;
	static double pre=0;
	static double[] weights = {0.9945610355985008, 3.0171805158832674, 2.310334147839607, 0.4765799627838889, 3.63169759417455, 3.284899677535622};
    private static ArrayList<ArrayList<Character>> crossPoint = new ArrayList<ArrayList<Character>>();
    private static ArrayList<Character> mutPoint = new ArrayList<Character>();


	public static void preProcess(StringBuilder string) {

		ArrayList<Character> temp = new ArrayList<Character>() ;
		for (int i=0;i<string.length();i++) {

			if (((i+1) % 3) == 0 ) {
				mutPoint.add(string.charAt(i));
				crossPoint.add((ArrayList<Character>) temp.clone());
				System.out.println(crossPoint.toString());

				temp.clear();
			}
			else	temp.add(string.charAt(i));	
				
		}
		
		/*Character[][] charac= new Character[crossPoint.size()][2];
		int z=0;
		for (ArrayList<Character> row : crossPoint){
			charac[z++]= row.toArray(new Character[row.size()]);
			System.out.println(row.toString());
		}*/
		
		Character[][] array = new Character[crossPoint.size()][];
		for (int i = 0; i < crossPoint.size(); i++) {
		    ArrayList<Character> row = crossPoint.get(i);
		    array[i] = row.toArray(new Character[row.size()]);
		}

	   // System.out.println(array[0][0]);
		
		System.out.println(String.format("CrossPoint : %s , \n MutPoint : %s",crossPoint.toString(),mutPoint.toString()));
	}
	
	
	public static void main(String[] args) {
		
		Random r = new Random();
		double low 	= -1/(Math.sqrt(2));
		double high = 1/(Math.sqrt(2));
		double[] result = new double[6];
		for(int i=0;i<result.length;i++)  {
			result[i] = low + (high-low)*r.nextDouble();
		}
		System.out.println(Arrays.toString(result));
        
        while(true){
        	double [][][] trainingData = {
                    {{0, 0,1, 0,hiddenOutput.getValue(),1},{0}},
                    {{0, 0,1, 1,hiddenOutput.getValue(),1}, {0}},
                    {{1, 0,1, 0,hiddenOutput.getValue(),1}, {1}},
                    {{1, 0,1, 1,hiddenOutput.getValue(),1}, {1}},
                    {{0, 1,1, 0,hiddenOutput.getValue(),1}, {0}},
                    {{0, 1,1, 1,hiddenOutput.getValue(),1}, {1}},
                    {{1, 1,1, 0,hiddenOutput.getValue(),1}, {0}},
                    {{1, 1,1, 1,hiddenOutput.getValue(),1}, {1}}
            };
        	
        	errorCount=0;
            for(int i=0; i < trainingData.length; i++){
                // Calculate weighted input
                double weightedSum = 0;
                
                for(int ii=0; ii <= 2; ii++) {
                    weightedSum += trainingData[i][0][ii] * weights[ii];
                }
       
                hiddenOutput.setValue(1/(1+Math.exp(-weightedSum)));
                
                weightedSum=0;
                for(int ii=3; ii < trainingData[i][0].length; ii++) {
                    weightedSum += trainingData[i][0][ii] * weights[ii];
                }
                
	            output = 1/(1+Math.exp(-weightedSum));

	          //    System.out.println("Target output: " + trainingData[i][1][0] + ", "
	         //                 + "Actual Output: " + output);
                calError(i,trainingData,weights);
                
                
            }

            if(errorCount == 0){
                System.out.println("Final weights: " + Arrays.toString(weights) );
                System.exit(0);
            }
        }
	}
	
	static void calError(int i, double[][][] trainingData, double[] weights) {
	    double error = Math.pow(trainingData[i][1][0] - output, 2)/2;
	    if (i==1){
	    System.out.println(error);
	    }
	    pre=error;
        if(error > 0.001){
            errorCount++;
        }
        
        double tempWeight= weights[4];
        for(int ii=0; ii < weights.length; ii++) {

            double firstErrorGrad	= (trainingData[i][1][0]- output)*output*(1-output);
            double secondErrorGrad =  (trainingData[i][1][0]- output)*output*(1-output) *hiddenOutput.getValue()*(1-hiddenOutput.getValue())*tempWeight;
        	if (ii>2)
        		weights[ii] -= learningRate * firstErrorGrad * trainingData[i][0][ii];
        	else
        		weights[ii] -= learningRate * secondErrorGrad * trainingData[i][0][ii];
    	}
        
        		/*
        		  
     for(int ii=0; ii < weights.length; ii++) {

            double firstErrorGrad	= output*(1-output)*error;
            double secondErrorGrad =  error*output*(1-output) *hiddenOutput.getValue()*(1-hiddenOutput.getValue())*weights[ii];
        	if (ii>2)
        		weights[ii] -= learningRate * firstErrorGrad * trainingData[i][0][ii];
        	else
        		weights[ii] -= learningRate * secondErrorGrad * trainingData[i][0][ii];
    	}
    	
    	*/
        
        
		
	}
	

	

}
