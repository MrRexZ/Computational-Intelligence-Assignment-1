package ann;

import java.util.ArrayList;
import java.util.Arrays;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.events.LearningEvent;
import org.neuroph.core.events.LearningEventListener;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.core.learning.LearningRule;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.learning.BackPropagation;
import org.neuroph.nnet.learning.MomentumBackpropagation;
import org.neuroph.util.TransferFunctionType;

import UIInterface.MainInterface;


public class ANNCategorization implements LearningEventListener {
	private static final int MAX_BIT = 8;
	private static final int MAX_INPUT_DATASET = 1;
	private static int maxGeneration;
	private ArrayList<ArrayList<Character>> crossPoint = new ArrayList<ArrayList<Character>>();
	private ArrayList<Character> mutPoint = new ArrayList<Character>();
    private static double[][] transCrossPoint;
    private static double[][] transMutPoint;	
    private static MainInterface UI;

    public ANNCategorization(MainInterface refUI) {
    	UI = refUI;
    }
    
    
	public double[][][] run(int generation) {
        NeuralNetwork categorizationMLNN = NeuralNetwork.createFromFile("categorization.nnet");
        
        
        DataSet inputSet= new DataSet(MAX_INPUT_DATASET,0);
        maxGeneration=generation;
        transCrossPoint = new double[maxGeneration][MAX_BIT];
        transMutPoint = new double[maxGeneration][MAX_BIT];
        
        for (int gen=0;gen<maxGeneration;gen++) {

        	UI.decryptionList.append("============================================\n");
        	UI.decryptionList.append("Generation: " + gen + "\n");
        	this.categorizeCrossOverPoint(inputSet, categorizationMLNN, gen);
        	this.categorizeMutationPoint(inputSet,  categorizationMLNN, gen);

        }
        
        
        double[][][] crossAndMut= new double[][][] {transCrossPoint, transMutPoint};
        return crossAndMut;
       
	}
	
	public void train() {

    	
        DataSet trainingSet = new DataSet(1, 8);
        trainingSet.addRow(new DataSetRow(new double[]{0}, new double[]{1,0,0,0,0,0,0,0}));
        trainingSet.addRow(new DataSetRow(new double[]{1}, new double[]{0,1,0,0,0,0,0,0}));
        trainingSet.addRow(new DataSetRow(new double[]{2}, new double[]{0,0,1,0,0,0,0,0}));
        trainingSet.addRow(new DataSetRow(new double[]{3}, new double[]{0,0,0,1,0,0,0,0}));
        trainingSet.addRow(new DataSetRow(new double[]{4}, new double[]{0,0,0,0,1,0,0,0}));
        trainingSet.addRow(new DataSetRow(new double[]{5}, new double[]{0,0,0,0,0,1,0,0}));
        trainingSet.addRow(new DataSetRow(new double[]{6}, new double[]{0,0,0,0,0,0,1,0}));
        trainingSet.addRow(new DataSetRow(new double[]{7}, new double[]{0,0,0,0,0,0,0,1}));
        
        DataSet testSet= new DataSet(1);
        testSet.addRow(new DataSetRow(new double[]{0}));
        
        MultiLayerPerceptron myMlPerceptron = new MultiLayerPerceptron(TransferFunctionType.SIGMOID, 1, 18, 8);

        myMlPerceptron.setLearningRule(new BackPropagation());
        

        LearningRule learningRule = myMlPerceptron.getLearningRule();
        learningRule.addListener((LearningEventListener) this);
        
        UI.categorizationTrainingIterationList.append("Training neural network...\n");
        myMlPerceptron.learn(trainingSet);

   
        UI.categorizationTrainingIterationList.append("Testing trained neural network\n");
        testNeuralNetwork(myMlPerceptron, trainingSet);

        myMlPerceptron.save("categorization.nnet");

        NeuralNetwork loadedMlPerceptron = NeuralNetwork.createFromFile("categorization.nnet");

        UI.categorizationTrainingIterationList.append("Testing loaded neural network\n");
        testNeuralNetwork(loadedMlPerceptron, testSet);
        
       
        
       
	}
	
	
	 public static void testNeuralNetwork(NeuralNetwork neuralNet, DataSet testSet) {

         for(DataSetRow testSetRow : testSet.getRows()) {
             neuralNet.setInput(testSetRow.getInput());
             neuralNet.calculate();
             double[] networkOutput = neuralNet.getOutput();

             UI.categorizationTrainingIterationList.append("Input: " + Arrays.toString( testSetRow.getInput() ) + "\n");
             for(int i = 0; i < networkOutput.length; i++){
             	networkOutput[i] =  Math.round(networkOutput[i]);
             }
             UI.categorizationTrainingIterationList.append(" Output: " + Arrays.toString(networkOutput) + "\n");
         }
     }

	 
	 public void handleLearningEvent(LearningEvent event) {
         BackPropagation bp = (BackPropagation)event.getSource();
         if (event.getEventType() != LearningEvent.Type.LEARNING_STOPPED)
        	 UI.categorizationTrainingIterationList.append(bp.getCurrentIteration() + ". iteration : "+ bp.getTotalNetworkError()+ "\n");
     }    
	 
	public void preProcess(StringBuilder string) {

		ArrayList<Character> temp = new ArrayList<Character>() ;
		for (int i=0;i<string.length();i++) {

			if (((i+1) % 3) == 0 ) {
				this.mutPoint.add(string.charAt(i));
				this.crossPoint.add((ArrayList<Character>) temp.clone());
				UI.decryptionList.append(crossPoint.toString()+ "\n");

				temp.clear();
			}
			else	temp.add(string.charAt(i));	
				
		}
		
		
		
	}
	
	public void categorizeCrossOverPoint(DataSet inputSet, NeuralNetwork loadedMlPerceptron, int gen) {
		        inputSet.clear();
		        inputSet.addRow(new DataSetRow(new double[]{Character.getNumericValue(this.crossPoint.get(maxGeneration-gen-1).get(0))}));
		        inputSet.addRow(new DataSetRow(new double[]{Character.getNumericValue(this.crossPoint.get(maxGeneration-gen-1).get(1))}));
		       
		        double[][] output=prodOutput(loadedMlPerceptron, inputSet,2);
		        for (int i=0; i< MAX_BIT; i++) {
		        	transCrossPoint[gen][i]=output[0][i]+output[1][i];
		        }
		        UI.decryptionList.append("Decrypt crossover output is "+ Arrays.deepToString(transCrossPoint));

	}
	
	public void categorizeMutationPoint(DataSet inputSet, NeuralNetwork loadedMlPerceptron, int gen) {
		inputSet.clear();
        inputSet.addRow(new DataSetRow(new double[]{Character.getNumericValue(this.mutPoint.get(maxGeneration-gen-1))}));
       
        double[][] output=prodOutput(loadedMlPerceptron, inputSet,1);
        
        for (int i=0; i< MAX_BIT; i++) {
        	transMutPoint[gen][i]=output[0][i];
        }
        
        UI.decryptionList.append("Decrypt mutation output is "+ Arrays.deepToString(transMutPoint));
	}
	
	  public static double[][] prodOutput(NeuralNetwork neuralNet, DataSet testSet, int inputAmount) {

		double[][] totalOutput=new double[inputAmount][MAX_BIT];
		  int counter=0;
	        for(DataSetRow testSetRow : testSet.getRows()) {
	            neuralNet.setInput(testSetRow.getInput());
	            neuralNet.calculate();
	            double[] networkOutput = neuralNet.getOutput().clone();

	            UI.decryptionList.append("Input: " + Arrays.toString( testSetRow.getInput() ) );
	            for(int i = 0; i < networkOutput.length; i++){
	            	networkOutput[i] =  Math.round(networkOutput[i]);
	            }
	            totalOutput[counter++] = networkOutput;
	            
	        }
	        
	        return totalOutput;
	    }
	    
}
