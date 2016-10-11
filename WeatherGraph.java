import javax.swing.JOptionPane;

public class WeatherGraph extends Temperature {
	
	private double rMembership, cMembership, hMembership = 0;
	
	public WeatherGraph(double x){
		super.x = x;
		
		weatherGraph();
		
		
		
		Main.weatherList.add("Raining: " + String.format("%.2f",rMembership));
		Main.weatherList.add("Cloudy: " + String.format("%.2f",cMembership));
		Main.weatherList.add("Hot: " + String.format("%.2f",hMembership));
	}
	
	public void weatherGraph(){
		if (x >= 24 && x <= 33){
			// Raining
			if (x >= 24 && x <= 25){
				generateSet(24, 25, "horizontal");
				setrMembership(y);
			}
			
			else if (x > 25 && x <= 28){
				generateSet(25, 28, "negative");
				setrMembership(findY());
			}
			
			// Cloudy
			if (x >= 25 && x < 28){
				generateSet(25, 28, "positive");
				setcMembership(findY());
			}
			
			else if (x >= 28 && x <= 29){
				generateSet(28, 29, "horizontal");
				setcMembership(findY());
			}
			
			else if (x > 29 && x <= 32){
				generateSet(29, 32, "negative");
				setcMembership(findY());
				}
			
			// Hot
			if (x >= 29 && x < 32){
				generateSet(29, 32, "positive");
				sethMembership(findY());
			}
			
			else if (x >= 32 && x <= 33){
				generateSet(32, 33, "horizontal");
				sethMembership(findY());
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Please make sure your weather input range is more than or equal to 24 and less than or equal to 33","Error Weather Input!!",1);
		}
	}

	public double getrMembership() {
		return rMembership;
	}

	public void setrMembership(double rMembership) {
		this.rMembership = rMembership;
	}

	public double getcMembership() {
		return cMembership;
	}

	public void setcMembership(double cMembership) {
		this.cMembership = cMembership;
	}

	public double gethMembership() {
		return hMembership;
	}

	public void sethMembership(double hMembership) {
		this.hMembership = hMembership;
	}
	
}