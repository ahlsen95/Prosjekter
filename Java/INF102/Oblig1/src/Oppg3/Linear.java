package Oppg3;
import edu.princeton.cs.algs4.LinearRegression;

public class Linear {
	public static void main(String[] args){
		double[] x = {3.6989700043,4,4.903089987,5.2041199827,5.5051499783};
		double[] y1 = {-3.2885302181,-2.9554602396,-1.9444076325,-1.613593789,-1.2757585476};
		double[] y2 = {-1.4511194374,-0.8501960618,0.9549161083,1.5564009297,2.1588183223};

		LinearRegression l = new LinearRegression(x,y1);
		LinearRegression k = new LinearRegression(x,y2);

		double slope1 = l.slope();
		double intercept1 = l.intercept();
		double slope2 = k.slope();
		double intercept2 = k.intercept();
		
		System.out.println("Slope(a) = "+slope1+"  Intercept(b) = "+intercept1);
		System.out.println("(y1) Y = aX - b:");
		
		for(int i = 0; i < x.length;i++){
			System.out.println(slope1 * x[i] + intercept1);
		}
		
		System.out.println("");
		System.out.println("Slope(a) = "+slope2+"  Intercept(b) = "+intercept2);
		System.out.println("(y2) Y = aX - b:");
		
		for(int i = 0; i < x.length;i++){
			System.out.println(slope2 * x[i] + intercept2);
		}

	}
}
