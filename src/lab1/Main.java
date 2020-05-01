package lab1;

public class Main {
	public static void main(String args[]) {
		Transform myDCT = new DCT1D();
		double[] src = new double[10];
		for (int i = 0; i < src.length; i++) {
			src[i] = (int)(42*Math.random());
			System.out.println(src[i]);
		}
		System.out.println("\n");
		myDCT.setSourceData(src);
		myDCT.calculate();
		double[] transformedData1D = (double[])myDCT.getResult();
		for (double d : transformedData1D) {
			System.out.println(d);
		}
		
		System.out.println("\n");
		
		Transform myIDCT = new IDCT1D();
		myIDCT.setSourceData(transformedData1D);
		myIDCT.calculate();
		double[] transformedData1DI = (double[])myDCT.getResult();
		for (double d : transformedData1DI) {
			System.out.println(d);
		}
		/*
		System.out.println("\n");
		
		Transform myDCT2D = new DCT2D();
		double[][] src2d = new double[3][3];
		for (int i = 0; i < src2d.length; i++) {
			for (int j = 0; j < src2d.length; j++) {
				src2d[i][j] = (int)(42*Math.random());
				System.out.println(src2d[i][j]);
			}
			
		}
		myDCT2D.setSourceData(src2d);
		myDCT2D.calculate();
		double[][] transformedData2D = (double[][])myDCT2D.getResult();
		for (int i = 0; i < transformedData2D.length; i++) {
			for (int j = 0; j < transformedData2D.length; j++) {
				System.out.println(transformedData2D[i][j]);
			}
		}
		
		System.out.println("\n");
		
		Transform myIDCT2D = new IDCT2D();
		myIDCT2D.setSourceData(transformedData2D);
		myIDCT2D.calculate();
		double[][] transformedData2DI = (double[][])myIDCT2D.getResult();
		for (int i = 0; i < transformedData2DI.length; i++) {
			for (int j = 0; j < transformedData2DI.length; j++) {
				System.out.println(transformedData2DI[i][j]);
			}
		}*/
	}
}
