
import edu.princeton.cs.algs4.LinearRegression;

public class LinearCount {
	public static void main(String[] args){
		double[] x = {1000,10000,100000,1000000,10000000};
		double[] y = {0.00000266,0.0000257,0.000255,0.00244,0.0247};

		LinearRegression l = new LinearRegression(x,y);


		double slope1 = l.slope();
		double intercept1 = l.intercept();


		System.out.println("Slope(a) = "+slope1+"  Intercept(b) = "+intercept1);

	}
}

