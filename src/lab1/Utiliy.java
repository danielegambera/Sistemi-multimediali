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

}
