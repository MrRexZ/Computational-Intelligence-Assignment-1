import javax.swing.JOptionPane;

public class AircondGraph extends Temperature {
	
	private double cMembership, nMembership, hMembership = 0;
	
	public AircondGraph(double x){
		super.x = x;
		
		aircondGraph();

		Main.aircondList.add("Cold: " + String.format("%.2f",cMembership));
		Main.aircondList.add("Normal: " + String.format("%.2f",nMembership));
		Main.aircondList.add("Hot: " + String.format("%.2f",hMembership));
	}
	
	public void aircondGraph(){
		
		if (x >= 16 && x <= 25){
			//Cold
			if (x >= 16 && x <= 17){
				generateSet(16, 17, "horizontal");
				setcMembership(findY());
			}
			
			else if (x > 17 && x <= 20){
				generateSet(17, 20, "negative");
				setcMembership(findY());
			}
			
			//Normal
			if (x >= 17 && x < 20){
				generateSet(17, 20, "positive");
				setnMembership(findY());
			}
			
			else if (x >= 20 && x <= 21){
				generateSet(20, 21, "horizontal");
				setnMembership(findY());
			}
			
			else if (x > 21 && x <= 24){
				generateSet(21, 24, "negative");
				setnMembership(findY());
			}
			
			// Hot
			if (x >= 21 && x < 24){
				generateSet(21, 24, "positive");
				sethMembership(findY());
			}
			
			else if (x >= 24 && x <= 25){
				generateSet(24, 25, "horizontal");
				sethMembership(findY());
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Please make sure your air-cond input range is more than or equal to 16 and less than or equal to 25","Error Air-cond Input!!",1);
		}
	}

	public double getcMembership() {
		return cMembership;
	}

	public void setcMembership(double cMembership) {
		this.cMembership = cMembership;
	}

	public double getnMembership() {
		return nMembership;
	}

	public void setnMembership(double nMembership) {
		this.nMembership = nMembership;
	}

	public double gethMembership() {
		return hMembership;
	}

	public void sethMembership(double hMembership) {
		this.hMembership = hMembership;
	}
}

