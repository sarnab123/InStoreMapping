import java.io.Serializable;


public class GraphEdge implements Serializable 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	GraphNode node1,node2;
	
	int traversalValue = 1;
	int offerValue = 1;
	
	public GraphEdge(GraphNode node1, GraphNode node2)
	{
		this.node1 = node1;
		this.node2 = node2;
	}

}
