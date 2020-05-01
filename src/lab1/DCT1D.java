package lab1;

public class DCT1D implements Transform {
	double[] source;
	double[] result;
	
	public void setSourceData (Object src) {
		source = (double[])src;
		result = new double[source.length];
	}
	
	public void calculate() {
		int n = source.length;
		double ak;
		for(int u =0; u< n; u++) 
		{
			
			ak = Utiliy.ak(u, n);
			double accumulatore = 0;
			
			for (int x = 0; x < n; x++) 
			{
				accumulatore += source[x]*Math.cos(((2*x+1)*u*Math.PI)/(2*n));
			}
			result[u] = accumulatore * ak;
		}
		
	}
	
	public Object getResult() {
		return result;
	}

}
