import java.util.ArrayList;
import java.util.Collections;

public class Rules {

	int weatherType, aircornType, type, count = 0;
	AircondGraph aircornGraph;
	WeatherGraph weatherGraph;
	ArrayList<Double> coldList = new ArrayList<Double>();
	ArrayList<Double> normalList = new ArrayList<Double>();
	ArrayList<Double> hotList = new ArrayList<Double>();
	
	public Rules(AircondGraph aircondGraph, WeatherGraph weatherGraph){
		this.aircornGraph = aircondGraph;
		this.weatherGraph = weatherGraph;
		rules();
	}
	
	
	public void addThenSort(ArrayList<Double> list,Double temp){
		list.add(temp);
		Collections.sort(list);
	}
	
	public void addList(String airSet, String tempSet, String coldSet){
		Main.coldList.add(String.format("Rule %d:", count));
		Main.coldList.add(String.format("If aircond temp is %s and weather is %s",airSet,tempSet));
		Main.coldList.add(String.format("Then coldness level is %s ",coldSet));
	}
	
	public void firedRules(double airTemp, double weatherTemp, String airSet, String tempSet, String coldSet){
	
		double temp;
		count++;
		if (airTemp > 0 && weatherTemp > 0){
			
			System.out.println(count);
			
			addList(airSet,tempSet,coldSet);
			
			if (airTemp > weatherTemp){
				temp = weatherTemp;
			}
			else{
				temp = airTemp;
			}
			
			System.out.println(temp);
			if (coldSet.equalsIgnoreCase("hot")){
				addThenSort(hotList,temp);
			}
			else if(coldSet.equalsIgnoreCase("normal")){
				addThenSort(normalList,temp);
			}
			else if (coldSet.equalsIgnoreCase("cold")){
				addThenSort(coldList,temp);
			}
		}
	}
	
	public void fireRule(){
		new ColdGraph(hotList,normalList,coldList);
	}
	
	public void rules(){
		double airTemp, weatherTemp;
		// if aircond is cold 
		airTemp = aircornGraph.getcMembership();
		
		// weather is raining, then coldness is high
		weatherTemp = weatherGraph.getrMembership();
		firedRules(airTemp,weatherTemp,"cold","raining","cold");
		
		// weather is cloudy, then coldness is high
		weatherTemp = weatherGraph.getcMembership();
		firedRules(airTemp,weatherTemp,"cold","cloudy", "cold");
		
		// weather is hot, then coldness is medium
		weatherTemp = weatherGraph.gethMembership();
		firedRules(airTemp,weatherTemp,"cold","hot", "normal");
		
		// if aircond is normal 
		airTemp = aircornGraph.getnMembership();
		
		// weather is raining, then coldness is high
		weatherTemp = weatherGraph.getrMembership();
		firedRules(airTemp,weatherTemp,"normal","raining","cold");
		
		// weather is cloudy, then coldness is medium
		weatherTemp = weatherGraph.getcMembership();
		firedRules(airTemp,weatherTemp,"normal","cloudy","normal");
		
		// weather is hot, then coldness is low
		weatherTemp = weatherGraph.gethMembership();
		firedRules(airTemp,weatherTemp,"normal","hot","hot");
		
		// if aircond is hot 
		airTemp = aircornGraph.gethMembership();
		
		// weather is raining, then coldness is medium
		weatherTemp = weatherGraph.getrMembership();
		firedRules(airTemp,weatherTemp,"hot","raining",  "normal");
		
		// weather is cloudy, then coldness is low
		weatherTemp = weatherGraph.getcMembership();
		firedRules(airTemp,weatherTemp,"hot","cloudy","hot");
		
		// weather is hot, then coldness is low
		weatherTemp = weatherGraph.gethMembership();
		firedRules(airTemp,weatherTemp,"hot","hot",  "hot");
		
		fireRule();
	}
}
