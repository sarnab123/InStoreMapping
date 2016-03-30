import java.io.Serializable;
import java.math.BigInteger;
import java.util.HashSet;


public class GraphNode implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6809758364648392349L;

	private Position nodePosition;
	
	
	private int color;
	
	@Override
	public boolean equals(Object obj) 
	{
		GraphNode testNode = (GraphNode) obj;
		
		if(this.getNodePosition().getX().equalsIgnoreCase(testNode.getNodePosition().getX()) && this.getNodePosition().getY().equalsIgnoreCase(testNode.getNodePosition().getY()) 
				&& this.getColor() == testNode.getColor() && this.isVisited() == testNode.isVisited())
		{
			return true;
		}
		return false;
	}
	
	class Position implements Serializable
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String x;
		private String y;
		
		public Position(String x, String y)
		{
			this.x = x;
			this.y = y;
		}
		
		public String getX()
		{
			return this.x;
		}
		
		public String getY()
		{
			return this.y;
		}
		
	}
	
	private boolean isVisited = false;
	
	public void setVisited()
	{
		isVisited = true;
	}
	
	public void setNodePosition(String x, String y)
	{
		this.nodePosition = new Position(x,y);
	}
	
	public Position getNodePosition() {
		return nodePosition;
	}
	
	public boolean isVisited() {
		return isVisited;
	}
	
	public void setColor(int color) {
		this.color = color;
	}
	
	public int getColor() {
		return color;
	}

}
