package ann;

import java.util.Arrays; 

import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.core.events.LearningEvent;
import org.neuroph.core.events.LearningEventListener;
import org.neuroph.core.learning.LearningRule;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.learning.BackPropagation;
import org.neuroph.util.TransferFunctionType;

import UIInterface.MainDecryptionInterface;

public class ANNSwapElement implements LearningEventListener {

	private static final int CROSSOVER_INDEX=0;
	private static final int MUTATION_INDEX=1;
	private static final int MAX_CROSS_INPUT_DATASET = 3;
	private static final int MAX_MUT_INPUT_DATASET = 2;
	private static final int MAX_BIT = 8;
	private static int maxGeneration;
	private static int[] cipherText;
	private static double[][][] crossAndMut;

    private static MainDecryptionInterface UI;
    
    public ANNSwapElement(MainDecryptionInterface refUI) {
    	UI=refUI;
    }
	
	    public int[] run(int generation, int[] cipher,double[][][] crossMut) {
	    	


	        NeuralNetwork crossOverDecryptMLNN = NeuralNetwork.createFromFile("crossOverDecrypt.nnet");
	        NeuralNetwork mutationDecryptMLNN = NeuralNetwork.createFromFile("mutationDecrypt.nnet");
	        
	        
	    	 DataSet mutInputSet= new DataSet(MAX_MUT_INPUT_DATASET,0);
	    	 DataSet crossInputSet= new DataSet(MAX_CROSS_INPUT_DATASET,0);
	    	 
	         maxGeneration=generation;
	         crossAndMut=crossMut;
	         cipherText=cipher;
	         for (int gen=0;gen<maxGeneration;gen++) {
	        	 

	        	 UI.decryptionList.append(String.format("Generation %d of CipherText %s: \n", gen, Arrays.toString(cipher)));
	        	 double[] mutCurGen= crossAndMut[MUTATION_INDEX][gen];
	        	 double[] crossCurGen= crossAndMut[CROSSOVER_INDEX][gen];
	        	 for (int cipherIndex=0;cipherIndex<cipherText.length;cipherIndex++) {
	        	  	 swapMutation(mutInputSet, mutationDecryptMLNN, cipherIndex,mutCurGen);
	        	 }
	        	
	        	 

        		 swapCrossOver(crossInputSet, crossOverDecryptMLNN, crossCurGen);

        		 UI.decryptionList.append("=================\n");
	         }
	         return cipherText;
	    }
	    
	    public void trainCrossOver() {
	    	
	    	DataSet trainingSet = new DataSet(3, 2);
	        trainingSet.addRow(new DataSetRow(new double[]{0,0,0}, new double[]{0,0}));
	        trainingSet.addRow(new DataSetRow(new double[]{0,0,1}, new double[]{0,1}));
	        trainingSet.addRow(new DataSetRow(new double[]{1,0,0}, new double[]{1,0}));
	        trainingSet.addRow(new DataSetRow(new double[]{1,0,1}, new double[]{1,1}));
	        trainingSet.addRow(new DataSetRow(new double[]{0,1,0}, new double[]{0,0}));
	        trainingSet.addRow(new DataSetRow(new double[]{0,1,1}, new double[]{1,0}));
	        trainingSet.addRow(new DataSetRow(new double[]{1,1,0}, new double[]{0,1}));
	        trainingSet.addRow(new DataSetRow(new double[]{1,1,1}, new double[]{1,1}));
	        DataSet testSet= new DataSet(1);
	        testSet.addRow(new DataSetRow(new double[]{0}));
	        
	        MultiLayerPerceptron myMlPerceptron = new MultiLayerPerceptron(TransferFunctionType.SIGMOID, 3, 18, 2);

	        myMlPerceptron.setLearningRule(new BackPropagation());
	        
	        LearningRule learningRule = myMlPerceptron.getLearningRule();
	        learningRule.addListener((LearningEventListener) this);
	        
	        myMlPerceptron.learn(trainingSet);

	        testNeuralNetwork(myMlPerceptron, trainingSet);

	        myMlPerceptron.save("crossOverDecrypt.nnet");

	        NeuralNetwork loadedMlPerceptron = NeuralNetwork.createFromFile("crossOverDecrypt.nnet");

	    }
	    
	    public void trainMutation() {
	    	
	    	DataSet trainingSet = new DataSet(2, 1);
	        trainingSet.addRow(new DataSetRow(new double[]{0,0}, new double[]{0}));
	        trainingSet.addRow(new DataSetRow(new double[]{0,1}, new double[]{1}));
	        trainingSet.addRow(new DataSetRow(new double[]{1,0}, new double[]{1}));
	        trainingSet.addRow(new DataSetRow(new double[]{1,1}, new double[]{0}));
	        
	        MultiLayerPerceptron myMlPerceptron = new MultiLayerPerceptron(TransferFunctionType.SIGMOID, 2, 2, 1);

	        myMlPerceptron.setLearningRule(new BackPropagation());
	        
	        LearningRule learningRule = myMlPerceptron.getLearningRule();
	        learningRule.addListener((LearningEventListener) this);
	        myMlPerceptron.learn(trainingSet);
	        testNeuralNetwork(myMlPerceptron, trainingSet);

	        myMlPerceptron.save("mutationDecrypt.nnet");

	        NeuralNetwork loadedMlPerceptron = NeuralNetwork.createFromFile("mutationDecrypt.nnet");

	    }
	    
	    public static void swapMutation(DataSet inputSet, NeuralNetwork neuNetwork, int cipherIndex, double[] mutation) {

	        inputSet.clear();
	        
	        //Add a mutation dataset
	        inputSet.addRow(new DataSetRow(new double[]{cipherText[cipherIndex], mutation[cipherIndex]}));
	       double[] output = prodOutput( neuNetwork , inputSet , 1);
	        cipherText[cipherIndex] = (int) output[0];

	     //   UI.decryptMutation.append("Decrypt Mutation: " + Arrays.toString(cipherText) + "\n");
	    }
	    
	    public static void swapCrossOver(DataSet inputSet, NeuralNetwork neuNetwork,  double[] crossOver) {

	        inputSet.clear();

	        int left=-1;
	        int right=crossOver.length;
	        while (crossOver[++left]==0);
	        while (crossOver[--right]==0);
	        
	        //Add a crossover data set
	        inputSet.addRow(new DataSetRow(new double[]{cipherText[left], 1 ,cipherText[right]}));
	       
	        double[] output=prodOutput(neuNetwork, inputSet,2);
	        
	        cipherText[left]					= (int) output[0];
	        cipherText[right]					= (int) output[1];
	        
	      //  UI.decryptCrossover.append(String.format("Decrypt CrossOver at [%s,%s] : %s\n",left,right,Arrays.toString(cipherText) ));

	    }

	    
		  public static double[] prodOutput(NeuralNetwork neuralNet, DataSet testSet, int outputNum) {

				double[] output=new double[outputNum];
			        for(DataSetRow testSetRow : testSet.getRows()) {
			            neuralNet.setInput(testSetRow.getInput());
			            neuralNet.calculate();
			            double[] networkOutput = neuralNet.getOutput().clone();

			            UI.decryptionList.append("Neural Network SwapElement Input: " + Arrays.toString( testSetRow.getInput() ) + "\n");
			       
			            	
			            for(int i = 0; i < networkOutput.length; i++){
			            	networkOutput[i] =  Math.round(networkOutput[i]);
			            }
			            output=networkOutput;
			        }
			        
			        return output;
			    }
		  
	    public static void testNeuralNetwork(NeuralNetwork neuralNet, DataSet testSet) {

	        for(DataSetRow testSetRow : testSet.getRows()) {
	            neuralNet.setInput(testSetRow.getInput());
	            neuralNet.calculate();
	            double[] networkOutput = neuralNet.getOutput();

	            	UI.swapTrainingIteration.append("Input: " + Arrays.toString( testSetRow.getInput() ) + "\n");
	            
	            for(int i = 0; i < networkOutput.length; i++){
	            	networkOutput[i] =  Math.round(networkOutput[i]);
	            }
	            
	            	UI.swapTrainingIteration.append(" Output: " + Arrays.toString(networkOutput) + "\n");
	        }
	    }
	    
	    public void handleLearningEvent(LearningEvent event) {
	        BackPropagation bp = (BackPropagation)event.getSource();
	        if (event.getEventType() != LearningEvent.Type.LEARNING_STOPPED) {
	        	UI.swapTrainingIteration.append(bp.getCurrentIteration() + ". iteration : "+ bp.getTotalNetworkError() + "\n");
	        	System.out.println(bp.getCurrentIteration() + ". iteration : "+ bp.getTotalNetworkError() + "\n");
	        }
	    }    
}
