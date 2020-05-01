package lab1;

public class DCT2D implements Transform {

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
		for(int u =0; u< n; u++) 
		{
			aku = Utiliy.ak(u, n);
			for (int v = 0; v < n; v++) 
			{
				akv = Utiliy.ak(v, n);
				double accumulatore = 0;
				
				for (int x = 0; x < n; x++) 
				{
					for (int y = 0; y < n; y++) 
					{
						accumulatore += source[x][y]*Math.cos(((2*x+1)*u*Math.PI)/(2*n))*Math.cos(((2*y+1)*v*Math.PI)/(2*n));
					}
					
				}
				result[u][v] = accumulatore * aku *akv;
			}
			
		}

	}

	@Override
	public Object getResult() {
		// TODO Auto-generated method stub
		return result;
	}

}
