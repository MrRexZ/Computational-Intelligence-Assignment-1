public class ColdGraph extends Temperature {
	
	double start;
	double end;
	String type;
	
	double middleStart;
	
	double highCold, mediumCold, lowCold;
	
	double totalUp = 0;
	double totalDown = 0;
	double totalArea = 0;
	
	double finalTotal = 0;
	double finalTotalArea = 0;
	
	public ColdGraph(double highCold, double mediumCold, double lowCold) {
		// TODO Auto-generated constructor stub
		this.highCold = highCold;
		this.mediumCold = mediumCold;
		this.lowCold = lowCold;

		highSet();
		mediumSet();
		lowSet();
		
		Double total = (double) finalTotal/finalTotalArea;
		
		if (!total.isNaN()){
			Main.coldResult.setText(String.format("%.2f", total));
			new ColdnessGraph(total);
		}
	}

	public void lowSet(){
		if (lowCold > 0){
			//Right side down
			start = 2;
			end = 4;
			type = "negative";
			
			generateSet(start, end, lowCold, type);
			
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
	
	public void mediumSet(){
		if (mediumCold > 0){
			//Left side up
			start = 2;
			end = 4;
			type = "positive";
			
			generateSet(start, end, mediumCold, type);
			
			totalUp += (x*y);
			totalDown += y;
			totalArea += (double) 1/2 * ((x-start) * y);
			
			middleStart = x-start;
			
			// Right side down
			start = 6;
			end = 8;
			type = "negative";
			
			generateSet(start, end, mediumCold, type);
			
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
	
	public void highSet(){
		if (highCold > 0){
			//Left side up
			start = 6;
			end = 8;
			type = "positive";
			
			generateSet(start, end, highCold, type);
			
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

class ColdnessGraph extends Temperature{
	
	private double lMembership, mMembership, hMembership = 0;
	
	
	public ColdnessGraph(Double total) {
		// TODO Auto-generated constructor stub
		
		coldnessGraph(total);
		
		System.out.println(total);
		System.out.println(lMembership);
		System.out.println(mMembership);
		System.out.println(hMembership);
		
		Main.coldnessList.add("Low: " + String.format("%.2f",lMembership));
		Main.coldnessList.add("Medium: " + String.format("%.2f",mMembership));
		Main.coldnessList.add("High: " + String.format("%.2f",hMembership));
	}

	public void coldnessGraph(double x){
		super.x = x;
		
		if (x >= 0 && x <= 10){
			//Cold
			if (x >= 0 && x <= 2){
				generateSet(0, 2, "horizontal");
				setlMembership(y);
			}
	
			else if (x > 2 && x <= 4){
				generateSet(2, 4, "negative");
				setlMembership(y);
			}
	
			//Normal
			if (x >= 2 && x < 4){
				generateSet(2, 4, "positive");
				setmMembership(y);
			}
	
			else if (x >= 4 && x <= 6){
				generateSet(4, 6, "horizontal");
				setmMembership(y);
			}
	
			else if (x > 6 && x <= 8){
				generateSet(6, 8, "negative");
				setmMembership(y);
			}
	
			// Hot
			if (x >= 6 && x < 8){
				generateSet(6, 8, "positive");
				sethMembership(y);
			}
	
			else if (x >= 8 && x <= 10){
				generateSet(8, 10, "horizontal");
				sethMembership(y);
			}
		}
	}

	public double getlMembership() {
		return lMembership;
	}

	public void setlMembership(double hMembership) {
		this.lMembership = hMembership;
	}

	public double getmMembership() {
		return mMembership;
	}

	public void setmMembership(double mMembership) {
		this.mMembership = mMembership;
	}

	public double gethMembership() {
		return hMembership;
	}

	public void sethMembership(double cMembership) {
		this.hMembership = cMembership;
	}

}