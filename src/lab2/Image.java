package lab2;

import java.awt.image.BufferedImage;

import lab1.DCT2D;
import lab1.IDCT2D;
import lab1.Transform;
import lab1.Utiliy;

public class Image {
		
		public static double[][] loadBlock(BufferedImage originalImage, int startx, int starty, int channel) {
			double [][] DCTmatrix = new double[8][8];
				for (int i = 0; i < 8; i++) {
					for (int j = 0; j < 8; j++) {
						DCTmatrix[i][j] = originalImage.getRaster().getSample(startx+i, starty+j, channel)-128;
					}
				}
				return DCTmatrix;
			}
		
		public static double[][] performDCT(double[][] DCTMatrix) {
			Transform myDCT = new DCT2D();
			myDCT.setSourceData(DCTMatrix);
			myDCT.calculate();
			double[][] transformedData = (double[][])myDCT.getResult();
			return transformedData;
		}
		
		public static int[][] quantize(double[][] source, int[][] table) {
			int [][] output = new int[8][8];
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					output[i][j] = (int) Math.round(source[i][j]/table[i][j]);
				}
				
			}
			return output;		
		}
		
		public static double[][] dequantize(int[][] source, int [][] table) {
			double[][] output = new double[8][8];
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					output[i][j] = source[i][j]* table[i][j];
				}
			}
			return output;
		}
		
		public static double[][] IDCT(double[][] DCTMatrix) {
			Transform myDCT = new IDCT2D();
			myDCT.setSourceData(DCTMatrix);
			myDCT.calculate();
			double[][] transformedData = (double[][])myDCT.getResult();
			return transformedData;
		}
		
		public static void storeResult(BufferedImage immagine, double[][] outmatrix,int startx, int starty, int banda) {
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					outmatrix[i][j] +=128;
					Utiliy.checkBound(outmatrix[i][j]);
					immagine.getRaster().setSample(startx+i, starty+j, banda, outmatrix[i][j]);
				}
			}
		}
}