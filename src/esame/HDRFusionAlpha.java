package esame;

import java.awt.image.BufferedImage;

import lab1.Transform;
import lab1.Utiliy;

public class HDRFusionAlpha implements Transform {
	
	BufferedImage[] sources;
	double[] weights;
	BufferedImage result;

	@Override
	public void setSourceData(Object src) {
		sources = (BufferedImage[]) src;
		result = new BufferedImage(sources[0].getWidth(), sources[0].getHeight(), sources[0].getType());
	}
	
	public void setWeights (double[] w) {
		weights = w;
	}

	@Override
	public void calculate() {
		
		for (int x = 0; x < sources[0].getWidth(); x++) {
				for (int y = 0; y < sources[0].getHeight(); y++) {
					for (int banda = 0; banda < sources[0].getRaster().getNumBands(); banda++) {
						double sommaAlfa = 0;
						double pixel = 0;
						for (int nImmagini = 0; nImmagini < sources.length; nImmagini++) {
							pixel += sources[nImmagini].getRaster().getSample(x, y, banda)*weights[nImmagini];
							sommaAlfa += weights[nImmagini];
						}
						result.getRaster().setSample(x, y, banda, Utiliy.checkBound(pixel/sommaAlfa));
					}
				}
			}
		
		
	}

	@Override
	public Object getResult() {
		return result;
	}

}
