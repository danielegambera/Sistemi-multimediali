package esame;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class Main {
	
	private static BufferedImage[] originalImages;
	public static void main(String[] args) {
		
		//variabili
		Scanner scanner = new Scanner(System.in);
		int numFoto;
		BufferedImage processedImage = null;
		String numeroFoto ="";
		double tmp;
		
		//richiesta numero foto nella cartella
		do {
			System.out.println("Inserisci il numero di foto totali nella cartella: ");
			while (!scanner.hasNextInt()) {
		        System.out.println("Dato inserito nel formato errato, riprovare.");
		        scanner.next(); // this is important!
		    }
		    numFoto = scanner.nextInt();
		} while (numFoto<0);
		
		originalImages = new BufferedImage[numFoto]; 
		double[] alfa = new double[originalImages.length];
		
		//menu
		System.out.println("Seleziona la modalia' di alfa:\n1)Personalizzata\n2)Costante standard (0,5)\n3)Costante personalizzata\n4)Gaussiana\n ");
		int key = scanner.nextInt();
		switch (key) {
		case 1:
			//inserimento valori alfa personalizzato
			try {
				for (int i = 0; i < alfa.length; i++) {
					
					do {
						System.out.println("Inserisci il valore di alfa" + String.valueOf(i) + " compresi tra 0 e 1 usando la virgola:");
						while (!scanner.hasNextDouble()) {
					        System.out.println("Dato inserito nel formato errato, riprovare.");
					        scanner.next(); // this is important!
					    }
					    tmp = scanner.nextDouble();
					} while (tmp>1 || tmp<0);
					
					alfa[i] = tmp;
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				
			}
			break;
		
		case 2:
			//alfa costante predefinito
			for (int i = 0; i < alfa.length; i++) {
				alfa[i] = 0.5;
			}
			break;
			
		case 3:
			//inserimento valori alfa costante personalizzato
			try {
					do {
						System.out.println("Inserisci il valore di alfa compreso tra 0 e 1 usando la virgola:");
						while (!scanner.hasNextDouble()) {
					        System.out.println("Dato inserito nel formato errato, riprovare.");
					        scanner.next(); // this is important!
					    }
					    tmp = scanner.nextDouble();
					} while (tmp>1 || tmp<0);
					for (int i = 0; i < alfa.length; i++) {
						alfa[i] = tmp;
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				
			}
			break;
	
		case 4:
			
			do {
				System.out.println("Inserisci il valore di alfa compreso tra 0 e 1 usando la virgola:");
				while (!scanner.hasNextDouble()) {
			        System.out.println("Dato inserito nel formato errato, riprovare.");
			        scanner.next(); // this is important!
			    }
			    tmp = scanner.nextDouble();
			} while (tmp>1 || tmp<0);
			
			for (int i = (-alfa.length/2); i <= alfa.length/2; i++) {
				alfa[i + (alfa.length/2)] = (double) Math.sqrt(tmp/Math.PI)*Math.pow(Math.E, -(tmp*i*i));
				//System.out.println("valore di alfa: " + alfa[i + (alfa.length/2)]+" valore di i: " + (i + alfa.length/2));

			}
			break;

		default:
			extracted(key);
		}
		
		scanner.close();

		try {
			
			for (int i = 0; i < numFoto; i++) {
				numeroFoto = Integer.toString(i+1);
				originalImages[i] = ImageIO.read(new File(".\\foto\\"+numeroFoto+".jpg"));
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		//metodi
		HDRFusionAlpha myHdrFusionAlpha = new HDRFusionAlpha();
		myHdrFusionAlpha.setSourceData(originalImages);
		myHdrFusionAlpha.setWeights(alfa);
		myHdrFusionAlpha.calculate();
		processedImage = (BufferedImage) myHdrFusionAlpha.getResult();
		//foto 1
		JLabel label = new JLabel(new ImageIcon(originalImages[0]));
		JFrame f = new JFrame("Originalpicture");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.getContentPane().add(label);f.pack();
		f.setLocation(20,20);
		f.setVisible(true);
		//foto finale
		JLabel label2 = new JLabel(new ImageIcon(processedImage));
		JFrame f2 = new JFrame("Quantizedpicture");
		f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f2.getContentPane().add(label2);
		f2.pack();f2.setLocation(100,100);
		f2.setVisible(true);
	}
	
	private static void extracted(int key) {
		throw new IllegalArgumentException("Unexpected value: " + key);
	}
}
