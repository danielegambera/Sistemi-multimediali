package lab1;

public class IDCT1D implements Transform {

	double[] source;
	double[] result;
	
	public void setSourceData (Object src) {
		source = (double[])src;
		result = new double[source.length];
	}
	
	public void calculate() {
		int n = source.length;
		double ak;
		for(int x =0; x< n; x++) 
		{

			double accumulatore = 0;
			
			for (int u = 0; u < n; u++) 
			{
				ak = Utiliy.ak(u, n);
				accumulatore += ak*source[u]*Math.cos(((2*x+1)*u*Math.PI)/(2*n));
			}
			result[x] = accumulatore;
		}
		
	}
	
	public Object getResult() {
		return result;
	}
}
