package com.instore;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Stack;

import javax.imageio.ImageIO;

import com.instore.graph.AdjMatrixGraph;
import com.instore.graph.Graph;
import com.instore.graph.Node;
import com.instore.graph.Path;

public class InStoreImageReader {

	public void readImageAndGenerateGraph(AdjMatrixGraph matrixGraph, String imageUrl) {
		File file = new File(imageUrl);
		
		BufferedImage bufImage;
		try {
			bufImage = ImageIO.read(file);
			
            //Whatever the file path is.
            //File outputFile = new File("./res/results.txt");
            //FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
            //OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);    
            //BufferedWriter buffWriter = new BufferedWriter(outputStreamWriter);
            
			
			matrixGraph.xPtsLength = bufImage.getWidth();
			matrixGraph.yPtsLength = bufImage.getHeight();
			matrixGraph.colorMatrix = new int[matrixGraph.yPtsLength][matrixGraph.xPtsLength];
			for (int i = 0; i < bufImage.getHeight(); i++) {
				for (int j = 0; j < bufImage.getWidth(); j++) {
					//System.out.println("Color valuees : " + " i " + i + " j " + j + " Color: " + bufImage.getRGB(j, i));
					int colorIntValue = bufImage.getRGB(j, i);
					matrixGraph.colorMatrix[i][j] = colorIntValue;
					//buffWriter.write("\n Color valuees : " + " i " + i + " j " + j + " Color: " + colorIntValue);
//					Color pixelColor = new Color (colorIntValue);
//					if (pixelColor.getRed() > 220) { 
//						System.out.println(" i " + i + " j " + j + " Color value : " + colorIntValue);
//					}
				}
				
			}
			//buffWriter.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		
	}
	
	public static void main(String[] args) {
		AdjMatrixGraph matrixGraph = new AdjMatrixGraph();
		InStoreImageReader imageReader = new InStoreImageReader();
		imageReader.readImageAndGenerateGraph(matrixGraph, "./res/edged.jpg");
		Path path = new Path();
		//path.findAllPathRec(0, 0, matrixGraph.colorMatrix, 250, 250, new Stack<Node>());
		
		
	}
}
