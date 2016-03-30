import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingQueue;

import javax.imageio.ImageIO;

public class GraphSearch {
	public static void main(String[] args) {
		final GraphSearch graphSearch = new GraphSearch();

		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				graphSearch.setupData();
			}
		});

		thread.start();
	}

	private void breadthFirstSearch(GraphNode firstNode,
			HashMap<String, GraphNode> nodes) {
		LinkedBlockingQueue<GraphNode> queue = new LinkedBlockingQueue<>();

		HashSet<GraphEdge> graphEdges = new HashSet<>();

		HashMap<GraphNode, LinkedList<GraphEdge>> listOfNodes = new HashMap<>();

		if (nodes.containsKey(firstNode.getNodePosition().getX()
				+ firstNode.getNodePosition().getY())) {
			queue.add(firstNode);

		}

		while (queue != null && queue.size() > 0) {
			GraphNode targetNode = queue.peek();

			LinkedList<GraphEdge> edgeNodes = new LinkedList<>();

			if (listOfNodes != null && listOfNodes.size() > 0) {
				edgeNodes = listOfNodes.get(targetNode);
			}

			if (edgeNodes == null) {
				edgeNodes = new LinkedList<>();
			}

			Integer steps = 5;

			String rightNode = String.valueOf(Integer.parseInt(targetNode
					.getNodePosition().getX()) + 5)
					+ targetNode.getNodePosition().getY();

			GraphNode right = nodes.get(rightNode);

			if (right != null) {
				if (right.getColor() == targetNode.getColor()
						&& !right.isVisited()) {
					queue.add(right);
					GraphEdge newEdge = new GraphEdge(targetNode, right);
					System.out.println("right added == "+right.getNodePosition().getX()+" y == "+right.getNodePosition().getY());
					graphEdges.add(newEdge);
					edgeNodes.add(newEdge);
				}
			}

			String leftNode = String.valueOf(Integer.parseInt(targetNode
					.getNodePosition().getX()) - steps)
					+ targetNode.getNodePosition().getY();

			GraphNode left = nodes.get(leftNode);

			if (left != null) {
				System.out.println("left color == "+left.getColor()+" targetNode color =="+targetNode.getColor()+" is left visited"+left.isVisited());
				if (left.getColor() == targetNode.getColor()
						&& !left.isVisited()) {
					queue.add(left);
					GraphEdge newEdge = new GraphEdge(targetNode, left);
					System.out.println("left added == "+left.getNodePosition().getX()+" y == "+left.getNodePosition().getY());
					graphEdges.add(newEdge);
					edgeNodes.add(newEdge);
				}
			}

			String topNode = targetNode.getNodePosition().getX()
					+ String.valueOf(Integer.parseInt(targetNode
							.getNodePosition().getY()) + steps);

			GraphNode top = nodes.get(topNode);

			if (top != null) {

				if (top.getColor() == targetNode.getColor() && !top.isVisited()) {
					queue.add(top);
					GraphEdge newEdge = new GraphEdge(targetNode, top);
					System.out.println("top added == "+top.getNodePosition().getX()+" y == "+top.getNodePosition().getY());
					graphEdges.add(newEdge);
					edgeNodes.add(newEdge);
				}
			}

			String bottmNode = targetNode.getNodePosition().getX()
					+ String.valueOf(Integer.parseInt(targetNode
							.getNodePosition().getY()) - steps);

			GraphNode bottom = nodes.get(bottmNode);

			if (bottom != null) {
				if (bottom.getColor() == targetNode.getColor() && !bottom.isVisited()) {
					queue.add(bottom);
					GraphEdge newEdge = new GraphEdge(targetNode, bottom);
					System.out.println("bottom added == "+bottom.getNodePosition().getX()+" y == "+bottom.getNodePosition().getY());

					graphEdges.add(newEdge);
					edgeNodes.add(newEdge);
				}
			}

			listOfNodes.put(targetNode, edgeNodes);

			targetNode.setVisited();
			queue.remove(targetNode);

		}

		writeToFile(listOfNodes, "nodesList");
		writeToFile(graphEdges, "edgeList");

	}

	private void writeToFile(HashMap<?, ?> listOfNodes, String fileName) {

		File outputFile = new File(fileName + ".tmp");
		FileOutputStream fileOutputStream;
		try {
			fileOutputStream = new FileOutputStream(outputFile);
			ObjectOutputStream outputStream = new ObjectOutputStream(
					fileOutputStream);

			outputStream.writeObject(listOfNodes);
			outputStream.flush();
			outputStream.close();
			fileOutputStream.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void setupData() {
		File file = new File("./res/edged.jpg");

		HashMap<String, GraphNode> nodes = new HashMap<>();

		try {
			BufferedImage bufImage = ImageIO.read(file);

			GraphNode firstNode = new GraphNode();
			Integer x = 312;
			Integer y = 610;
			firstNode.setNodePosition(String.valueOf(x), String.valueOf(y));
			firstNode.setColor(-16777216);

			for (Integer i = 0; i < bufImage.getHeight(); i++) {
				for (Integer j = 0; j < bufImage.getWidth(); j++) {
					int color = bufImage.getRGB(j, i);

					GraphNode graphNode = new GraphNode();
					graphNode.setColor(color);
					graphNode.setNodePosition(String.valueOf(i),
							String.valueOf(j));
					if (i == 312 && j == 610) {
						System.out.println("Graph Node x =="
								+ graphNode.getNodePosition().getX() + " y=="
								+ graphNode.getNodePosition().getY()
								+ " color =" + color);
						System.out.println("firstNode Node x =="
								+ firstNode.getNodePosition().getX() + " y=="
								+ firstNode.getNodePosition().getY()
								+ " color =" + color);
						System.out.println("Is node same === "
								+ (firstNode.equals(graphNode)));
					}
					nodes.put(String.valueOf(i) + String.valueOf(j), graphNode);

				}
				// add a new line
			}

			breadthFirstSearch(firstNode, nodes);
			writeToFile(nodes, "storePixels");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void writeToFile(HashSet<?> nodes, String fileName) {

		File outputFile = new File(fileName + ".tmp");
		FileOutputStream fileOutputStream;
		try {
			fileOutputStream = new FileOutputStream(outputFile);
			ObjectOutputStream outputStream = new ObjectOutputStream(
					fileOutputStream);

			outputStream.writeObject(nodes);
			outputStream.flush();
			outputStream.close();
			fileOutputStream.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
