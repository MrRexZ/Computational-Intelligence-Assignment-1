import java.text.DecimalFormat;

public class Temperature {

	double y, m, x, c;
	
	DecimalFormat totalFormat = new DecimalFormat("##.00");
	
	public void generateSet(double start, double end, String type){
		m = findM(start,end,type);
		c = findC(start,end,type);
		y = findY();
	}
	
	public void generateSet(double start, double end, double y, String type){
		this.y = y;
		m = findM(start,end,type);
		c = findC(start,end,type);
		x = findX(y,m,c);
	}
	
	protected double findY(){
		return m * x + c;
	}
	
	protected double findX(double y, double m, double c){
		return (double) (y - c) / m;
	}
	
	protected double findM(double start, double end, String type){
		
		if (type.equalsIgnoreCase("horizontal")){
			return 0;
		}
		else if (type.equalsIgnoreCase("positive")){
			return 1/(end-start);
		}
		else if (type.equalsIgnoreCase("negative")){
			return -1/(end-start);
		}
		else{
			return 0;
		}
	}
	
	protected double findC(double start, double end, String type){
		if (type.equalsIgnoreCase("horizontal")){
			return 1;
		}
		else if (type.equalsIgnoreCase("positive")){
			m = findM(start,end,type);
			
			c = -m * start;
			
			return c;
		}
		else if (type.equalsIgnoreCase("negative")){
			m = -1/(end-start);
			
			c = -m * end;
			
			return c;
		}
		else{
			return 0;
		}
	}
	
}