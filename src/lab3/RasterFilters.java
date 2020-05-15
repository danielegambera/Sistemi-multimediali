package lab3;

public class RasterFilters {
	
	static double[][] getIdentity(int size){
		double [][] kernel = new double[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if ((i == size/2) && (j == size/2)) {
					kernel[i][j] = (double)1;
				}
				else {
					kernel[i][j] = (double)0;
				}
			}
		}
		return kernel;
	}
	
	static double[][] getLowPass(int size){
		double [][] kernel = new double[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				kernel[i][j] = (double) 1.0/(Math.pow(size, 2));
			}
		}
		return kernel;
	}
	
	static double[][] getHighPass(int size){
		double [][] kernel = new double[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if ((i == size/2) && (j == size/2)) {
					kernel[i][j] = (double) (Math.pow(size, 2))-1;
				}
				else {
					kernel[i][j] = (double)-1;
				}
			}
		}
		return kernel;
	}
	
	static double[][] getSharpen(int size){
		double [][] kernelIdentity = getIdentity(size);
		double [][] kernelLowPass = getLowPass(size);
		double [][] kernel = new double[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				kernel[i][j] = 2*kernelIdentity[i][j] - kernelLowPass[i][j];
			}
		}
		return kernel;
	}
	
	static double[][] getGaussian(int size, double sigma){
		double [][] kernel = new double[size][size];
		for (int i = (-size/2); i <= size/2; i++) {
			for (int j = (-size/2); j <= size/2; j++) {
				kernel[i+(size/2)][j+(size/2)] = (double) Math.pow(Math.E, -((i*i)+(j*j))/(2*Math.pow(sigma, 2)))/(2*Math.PI*sigma*sigma);
			}
		}
		return kernel;
	}
	
	static double[][] getGaussianSharp(int size, double sigma){
		double [][] kernelIdentity = getIdentity(size);
		double [][] kernelGaussian = getGaussian(size, sigma);
		double[][] kernel = new double[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				kernel[i][j] = 2*kernelIdentity[i][j] - kernelGaussian[i][j];
			}
		}
		return kernel;
	}
}
