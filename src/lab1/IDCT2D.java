package lab1;

public class IDCT2D implements Transform {
	double[][] source;
	double[][] result;
	
		@Override
	public void setSourceData(Object src) {
		// TODO Auto-generated method stub
		
		
		source = (double[][])src;
		result = new double[source.length][source[0].length];

	}

	@Override
	public void calculate() {
		// TODO Auto-generated method stub
		int n = source.length;
		double aku, akv;
		for(int x =0; x< n; x++) 
		{
			
			for (int y = 0; y < n; y++) 
			{
				double accumulatore = 0;
				
				for (int u = 0; u < n; u++) 
				{
					for (int v = 0; v < n; v++) 
					{
						aku = Utiliy.ak(u, n);
						akv = Utiliy.ak(v, n);
						accumulatore += source[u][v]*Math.cos(((2*x+1)*u*Math.PI)/(2*n))*Math.cos(((2*y+1)*v*Math.PI)/(2*n))* aku *akv;
					}
					
				}
				result[x][y] = accumulatore;
			}
			
		}

	}

	@Override
	public Object getResult() {
		// TODO Auto-generated method stub
		return result;
	}

}
