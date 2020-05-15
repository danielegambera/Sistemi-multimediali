package lab3;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import lab1.Transform;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BufferedImage originalImage = null, processedImage = null;
		try {
			originalImage = ImageIO.read(new File("C:\\\\Users\\\\danze\\\\Desktop\\\\Immagini sample per Esercizi lab-20200507\\\\SampleCol.jpg"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		Transform myConvolution= new Convolution();
		((Convolution) myConvolution).setKernel(RasterFilters.getGaussianSharp(5, 1.2));
		myConvolution.setSourceData(originalImage);
		myConvolution.calculate();
		processedImage= (BufferedImage) myConvolution.getResult();
		
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
