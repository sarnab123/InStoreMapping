package com.instore.graph;

import java.util.ArrayList;
import java.util.Queue;
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
		if (xPos >= endX && yPos >= endY) {
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
		
		if (xPos < endX) {
			xPos = xPos + 1;
			if (xPos <= endX) {
				findAllPathRec(xPos, yPos, colorMatrix, endX, endY, queue);
				} else {
					xPos = endX;
				}
		}
		yPos = yPos + 1;
		if (yPos <= endY) {
			findAllPathRec(xPos, yPos,  colorMatrix, endX, endY, queue);
		} else {
			yPos = endY;
		}
		queue.pop();
	}
}
