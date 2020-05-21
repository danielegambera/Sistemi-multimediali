package lab4;

import java.awt.image.BufferedImage;

import lab1.Transform;
import lab1.Utiliy;

public class Bicubic implements Transform {
	
	BufferedImage source, result;
	double m;
	
	public void setRatio(double ratio) {
		m = ratio;
	}

	@Override
	public void setSourceData(Object src) {
		// TODO Auto-generated method stub
		source = (BufferedImage) src;
	}

	@Override
	public void calculate() {
		// TODO Auto-generated method stub
		int newWidth = (int) (source.getWidth()*m);
		int	newWeight = (int) (source.getHeight()*m);
		double a, b, i, j;
		int x1, y1;
		
		result = new BufferedImage(newWidth, newWeight, source.getType());
		
		for (int x = 0; x < result.getWidth(); x++) {
			for (int y = 0; y < result.getHeight(); y++) {
				for (int banda = 0; banda < result.getRaster().getNumBands(); banda++) {
					x1 = (int) (x/m);
					y1 = (int) (y/m);
					i = (double)x/m;
					j = (double)y/m;
					a = i-x1;
					b = j-y1;
					if (x1 > 0 && x1<source.getWidth()-2 && y1 > 0 && y1<source.getHeight()-2) {
						
						double tmp = 0;
						
						for (int k = -1; k <= 2; k++) {
							
							for (int l = -1; l <= 2; l++) {
								tmp += (source.getRaster().getSample(x1+k, y1+l, banda))* Utiliy.bicubicInterpolation(k, a) * Utiliy.bicubicInterpolation(l, b); ;
							}
							
						}
						
						result.getRaster().setSample(x, y, banda, Utiliy.checkBound(tmp));
						
					}
					else {
						result.getRaster().setSample(x, y, banda, source.getRaster().getSample(x1, y1, banda));
					}
				}
			}
		}
	}

	@Override
	public Object getResult() {
		// TODO Auto-generated method stub
		return result;
	}


}
