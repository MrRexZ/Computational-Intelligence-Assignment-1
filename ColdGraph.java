import java.util.ArrayList;

public class ColdGraph extends Temperature {
	
	double start;
	double end;
	String type;
	
	double middleStart;
	
	ArrayList<Double> coldList = new ArrayList<Double>();
	ArrayList<Double> normalList = new ArrayList<Double>();
	ArrayList<Double> hotList = new ArrayList<Double>();
	
	double totalUp = 0;
	double totalDown = 0;
	double totalArea = 0;
	
	double finalTotal = 0;
	double finalTotalArea = 0;
	
	public ColdGraph(ArrayList<Double> hotList, ArrayList<Double> normalList, ArrayList<Double> coldList) {
		// TODO Auto-generated constructor stub
		this.hotList = hotList;
		this.normalList = normalList;
		this.coldList = coldList;
		
		coldSet();
		normalSet();
		hotSet();
		
		Double total = (double) finalTotal/finalTotalArea;
		
		if (!total.isNaN()){
			Main.coldResult.setText(String.format("%.2f", total));
		}
	}

	public void hotSet(){
		if (hotList.size() > 0){
			//Right side down
			start = 2;
			end = 4;
			type = "negative";
			
			generateSet(start, end, hotList.get(hotList.size()-1), type);
			
			totalUp += (x*y);
			totalDown += y;
			totalArea += (double) 1/2 * ((end-x) * y);
			
			//Start
			start = 0;
			end = x;
			
			totalUp += (start*y);
			totalDown += y;
			totalArea += (double) (end-start)*y;
			
			finalTotal += (totalUp/totalDown) * totalArea;
			finalTotalArea += totalArea;
			
			totalUp = 0;
			totalDown = 0;
			totalArea = 0;
		}
	}
	
	public void normalSet(){
		if (normalList.size() > 0){
			//Left side up
			start = 2;
			end = 4;
			type = "positive";
			
			generateSet(start, end, normalList.get(normalList.size()-1), type);
			
			totalUp += (x*y);
			totalDown += y;
			totalArea += (double) 1/2 * ((x-start) * y);
			
			middleStart = x-start;
			
			// Right side down
			start = 6;
			end = 8;
			type = "negative";
			
			generateSet(start, end, normalList.get(normalList.size()-1), type);
			
			totalUp += (x*y);
			totalDown += y;
			totalArea += (double) 1/2 * ((end-x) * y);
			
			// Middle part
			start = middleStart;
			end = x;
			
			totalArea += (double) (end-start) * y;
			
			finalTotal += (totalUp/totalDown) * totalArea;
			
			finalTotalArea += totalArea;
			
			totalUp = 0;
			totalDown = 0;
			totalArea = 0;
		}
	}
	
	public void coldSet(){
		if (coldList.size() > 0){
			//Left side up
			start = 6;
			end = 8;
			type = "positive";
			
			generateSet(start, end, coldList.get(coldList.size()-1), type);
			
			totalUp += (x*y);
			totalDown += y;
			totalArea +=(double) 1/2 * ((x-start) * y);
			//horizontal
			start = x;
			end = 10;
			type = "horizontal";
			
			totalUp += (end*y);
			totalDown += y;
			totalArea += (double) (end-start)*y;
			
			finalTotal += (totalUp/totalDown) * totalArea;
			
			finalTotalArea += totalArea;
			
			totalUp = 0;
			totalDown = 0;
			totalArea = 0;
		}
	}
}
