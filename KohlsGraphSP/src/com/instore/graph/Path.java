package com.instore.graph;

import java.util.ArrayList;
import java.util.Stack;

public class Path {
	
	public ArrayList<Stack> pathsList = new ArrayList<Stack>();
	
	public boolean findPath(int xPos, int yPos, Stack<Node> pathQueue, int[][] colorMatrix, int endX, int endY) {
		
		if (xPos == endX && yPos == endY) {
			Node node = new Node();
			node.xPos = xPos;
			node.yPos = yPos;
			node.colorValue = colorMatrix[xPos][yPos];
			pathQueue.add(node);
			return true;
		}
		if (colorMatrix[xPos][yPos] != -16777216) {
			return false;
		} else {
			boolean foundXPath = findPath(xPos++, yPos, pathQueue, colorMatrix, endX, endY);
			if (foundXPath) {
				Node node = new Node();
				node.xPos = xPos;
				node.yPos = yPos;
				node.colorValue = colorMatrix[xPos][yPos];
				pathQueue.add(node);
				return true;
			} else {
				boolean foundYPath = findPath(xPos, yPos++, pathQueue, colorMatrix, endX, endY);
				if (foundYPath) {
					Node node = new Node();
					node.xPos = xPos;
					node.yPos = yPos;
					node.colorValue = colorMatrix[xPos][yPos];
					pathQueue.add(node);
					return true;
				}
			}
			return false;
		}
		
		
	}
	
	public void findAllPathRec(int xPos, int yPos, int[][] colorMatrix, int endX, int endY, Stack<Node> queue) {
		if (xPos > endX || yPos > endY) {
			return;
		} else if (xPos == endX && yPos == endY) {
			xPos = endX;
			yPos = endY;
			Stack<Node> finalStack = new Stack<Node>(); 
			Node node = new Node();
			node.xPos = xPos;
			node.yPos = yPos;
			node.colorValue = colorMatrix[xPos][yPos];
			finalStack.addAll(queue);
			finalStack.add(node);
			pathsList.add(finalStack);
			return;
		} else if (colorMatrix[xPos][yPos] != -16777216) {
			return; 
		}
		
		Node node = new Node();
		node.xPos = xPos;
		node.yPos = yPos;
		node.colorValue = colorMatrix[xPos][yPos];
		queue.push(node);
		
		
		if (xPos <= endX) {
			int newXPos = xPos + 50;
			findAllPathRec(newXPos, yPos, colorMatrix, endX, endY, queue);
		} 
		if (yPos <= endY) {
			int newYPos = yPos + 50;
			findAllPathRec(xPos, newYPos,  colorMatrix, endX, endY, queue);
		}
		if (xPos <= endX && yPos <= endY) {
			int newXPos = xPos + 50;
			int newYPos = yPos + 50;
			findAllPathRec(newXPos, newYPos,  colorMatrix, endX, endY, queue);
		}
		queue.pop();
	}
}
