package lab3;

import java.awt.image.BufferedImage;

import lab1.Transform;
import lab1.Utiliy;

public class Convolution implements Transform {
	BufferedImage source;
	BufferedImage result;
	double [][] kernel;
	
	public void setKernel(double[][] k) {
		kernel = k;
	}
	
	public void setSourceData (Object src) {
		source = (BufferedImage) src;
		
		result= new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
	}
	
	public void calculate() 
	{
		//perform convolution and store results in result
		int N = kernel.length;
		
		for (int x = 0; x < source.getWidth(); x++) {
			for (int y = 0; y < source.getHeight(); y++) {
				for (int banda = 0; banda < source.getRaster().getNumBands(); banda++) {
					
				if (x >= N/2 && x<source.getWidth()-N/2 && y >= N/2 && y<source.getHeight()-N/2) {
						double somma = 0;
						for (int k = -N/2; k < N/2; k++) {
							for (int l = -N/2; l < N/2; l++) {
								somma += source.getRaster().getSample(x+k, y+l,banda)* kernel[k+N/2][l+N/2];
							}
						}
						result.getRaster().setSample(x, y, banda, Utiliy.checkBound(somma)); //scrive il nuovo pixel
				}
				else {
					result.getRaster().setSample(x, y, banda, source.getRaster().getSample(x, y, banda));
				}
				}
				
			}
		}
	}
	
	public Object getResult() {
		return result;
	}
}
