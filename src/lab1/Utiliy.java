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

}
