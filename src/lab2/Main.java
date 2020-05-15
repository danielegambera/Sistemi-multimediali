package lab2;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main {
	
	static int[][] quantizationTable= {
			{16,11,10,16,24,40,51,61},
			{12,12,14,19,26,58,60,55},
			{14,13,16,24,40,57,69,56},
			{14,17,22,29,51,87,80,62},
			{18,22,37,56,68,109,103,77},
			{24,35,55,64,81,104,113,92},
			{49,64,78,87,103,121,120,101},
			{72,92,95,98,112,100,103,99}
			};
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		String sorgenteFile = "C:\\Users\\danze\\Desktop\\Immagini sample per Esercizi lab-20200507\\Sample.jpg";
		int startx =0, starty =0;
		BufferedImage originalImage = null, processedImage = null;
		try 
		{
			originalImage = ImageIO.read(new File(sorgenteFile));
			processedImage = ImageIO.read(new File(sorgenteFile));
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		int bands = originalImage.getRaster().getNumBands();
		double[][] BlockMatrix, DCTmatrix, DequantizationMatrix, IDCTmatrix;
		int[][] DCTquantizato;
		do {
			for (int i = 0; i < bands; i++) {
				BlockMatrix = Image.loadBlock(originalImage, startx, starty, i);
				DCTmatrix = Image.performDCT(BlockMatrix);
				DCTquantizato = Image.quantize(DCTmatrix, quantizationTable);
				DequantizationMatrix = Image.dequantize(DCTquantizato, quantizationTable);
				IDCTmatrix = Image.IDCT(DequantizationMatrix);
				Image.storeResult(processedImage, IDCTmatrix, startx, starty, i);
			}
			
			startx +=8;
			if (startx >= originalImage.getWidth()) {
				startx=0;
				starty +=8;
			}
			
			
		} while(startx < originalImage.getWidth()&& starty < originalImage.getHeight() );
		
		JLabel label = new JLabel(new ImageIcon(originalImage));
		JFrame f = new JFrame("Originalpicture");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.getContentPane().add(label);f.pack();
		f.setLocation(20,20);
		f.setVisible(true);
		JLabel label2 = new JLabel(new ImageIcon(processedImage));
		JFrame f2 = new JFrame("Quantizedpicture");
		f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f2.getContentPane().add(label2);
		f2.pack();f2.setLocation(100,100);
		f2.setVisible(true);
	}
	

}
