package lab4;

import java.awt.image.BufferedImage;

import lab1.Transform;

public class NearestNeighbor implements Transform {
	
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
		
		result = new BufferedImage(newWidth, newWeight, source.getType());
		
		for (int x = 0; x < result.getWidth(); x++) {
			for (int y = 0; y < result.getHeight(); y++) {
				for (int banda = 0; banda < result.getRaster().getNumBands(); banda++) {
					int x1 =(int) (x/m);
					int y1 =(int) (y/m);
					result.getRaster().setSample(x, y, banda, source.getRaster().getSample(x1, y1, banda));
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
