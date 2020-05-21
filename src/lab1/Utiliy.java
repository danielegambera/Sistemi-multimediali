package lab1;

public class Utiliy {
	
	public static double ak(int k, int n) {
		double ak;
		
		if(k == 0) 
		{
			ak = Math.sqrt((double)1/(double)n);
		}
		else 
		{
			ak = Math.sqrt((double)2/(double)n);
		}
		return ak;
	}
	
	public static double checkBound(double a) {
		if(a<0) return 0;
		if (a>255) { return 255;
			
		}
		return a;
	}
	
	public static double bicubicInterpolation(int k, double h) {
		double phi = 0;
		if (k == -1) {
			phi = (-(h*h*h) + (3*h*h) - (2*h))/6;
		}
		if (k == 0) {
			phi = ((h*h*h) - (2*h*h) - h +2)/2;
		}
		if (k == 1) {
			phi = (-(h*h*h) + (h*h) + (2*h))/2;
		}
		if (k == 2) {
			phi = ((h*h*h) - h)/6;
		}
		return phi;
	}

}
