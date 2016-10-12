public class Rules {

	int weatherType, aircornType, type, count = 0;
	AircondGraph aircornGraph;
	WeatherGraph weatherGraph;
	double highCold, mediumCold, lowCold = 0;
	
	public Rules(AircondGraph aircondGraph, WeatherGraph weatherGraph){
		this.aircornGraph = aircondGraph;
		this.weatherGraph = weatherGraph;
		rules();
	}
	
	public void compare(double cold, String coldType){
		if (coldType.equalsIgnoreCase("low")){
			if (cold > lowCold){
				lowCold = cold;
			}
		}
		else if (coldType.equalsIgnoreCase("medium")){
			if (cold > mediumCold){
				mediumCold = cold;
			}
		}
		else if (coldType.equalsIgnoreCase("high")){
			if (cold > highCold){
				highCold = cold;
			}
		}
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
			
			addList(airSet,tempSet,coldSet);
			
			if (airTemp > weatherTemp){
				temp = weatherTemp;
			}
			else{
				temp = airTemp;
			}
			
			compare(temp,coldSet);
			
		}
	}
	
	public void fireRule(){
		new ColdGraph(highCold,mediumCold,lowCold);
	}
	
	public void rules(){
		double airTemp, weatherTemp;
		airTemp = aircornGraph.getcMembership();
		
		// if aircond is cold and weather is raining, then coldness is high
		weatherTemp = weatherGraph.getrMembership();
		firedRules(airTemp,weatherTemp,"cold","raining","high");
		
		// if aircond is cold and weather is cloudy, then coldness is high
		weatherTemp = weatherGraph.getcMembership();
		firedRules(airTemp,weatherTemp,"cold","cloudy", "high");
		
		// if aircond is cold and weather is hot, then coldness is medium
		weatherTemp = weatherGraph.gethMembership();
		firedRules(airTemp,weatherTemp,"cold","sunny", "medium");
		
		airTemp = aircornGraph.getnMembership();
		
		// if aircond is normal and weather is raining, then coldness is high
		weatherTemp = weatherGraph.getrMembership();
		firedRules(airTemp,weatherTemp,"normal","raining","high");
		
		// if aircond is normal and weather is cloudy, then coldness is medium
		weatherTemp = weatherGraph.getcMembership();
		firedRules(airTemp,weatherTemp,"normal","cloudy","medium");
		
		// if aircond is normal and weather is hot, then coldness is low
		weatherTemp = weatherGraph.gethMembership();
		firedRules(airTemp,weatherTemp,"normal","sunny","low");
		
		airTemp = aircornGraph.gethMembership();
		
		// if aircond is hot and weather is raining, then coldness is medium
		weatherTemp = weatherGraph.getrMembership();
		firedRules(airTemp,weatherTemp,"hot","raining","medium");
		
		// if aircond is hot and weather is cloudy, then coldness is low
		weatherTemp = weatherGraph.getcMembership();
		firedRules(airTemp,weatherTemp,"hot","cloudy","low");
		
		// if aircond is hot and weather is hot, then coldness is low
		weatherTemp = weatherGraph.gethMembership();
		firedRules(airTemp,weatherTemp,"hot","sunny",  "low");
		
		fireRule();
	}
}