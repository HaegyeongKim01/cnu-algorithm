package graph;

public class AdjacencyMatrixGraph{
	//private constants
	private static final int EDGE_EXIST = 1;
	private static final int EDGE_NONE = 0;
	
	//private instance variables
	private int _numberOfVertices;
	private int _numberOfEdges;
	private int[][] _adjacency;
	
	
	public AdjacencyMatrixGraph(int givenNumberOfVertices){
		this.setNumberOfVertices(givenNumberOfVertices);
		this.setNumberOfEdges(0);
		this.setAdjacency(new int[givenNumberOfVertices][givenNumberOfVertices]);
		for(int tailVertex = 0; tailVertex < this.numberOfVertices(); tailVertex++) {
			for (int headVertex = 0; headVertex < this.numberOfVertices(); headVertex++){
				this.adjacency()[tailVertex][headVertex] = AdjacencyMatrixGraph.EDGE_NONE;
			}
		}
	}
	
	public int numberOfVertices() {
		return this._numberOfVertices;
	}
	
	public int numberOfEdges() {
		return this._numberOfEdges;
	}
	
	//private getter &setters
	private void setNumberOfVertices(int newNumberOfVertices) {
		this._numberOfVertices = newNumberOfVertices;
	}
	private void setNumberOfEdges(int newNumberOfEdges){
		this._numberOfEdges = newNumberOfEdges;
	}
	private int[][] adjacency(){
		return this._adjacency;
	}
	private void setAdjacency(int[][] newAdjacency) {
		this._adjacency = newAdjacency;
	}
	
	public boolean vertexDoesExist(int aVertex) {
		return (aVertex >= 0 && aVertex < this.numberOfVertices());
	}
	
	public boolean edgeDoesExist(int tailVertex, int headVertex) {
		if (this.vertexDoesExist(tailVertex) && this.vertexDoesExist(headVertex)) {
			return (this.adjacency()[tailVertex][headVertex] == AdjacencyMatrixGraph.EDGE_EXIST);
		}
		else {
			return false;
		}
	}
	public boolean edgeDoesExist(Edge anEdge) {
		if (anEdge != null) {
			return this.edgeDoesExist(anEdge.tailVertex(), anEdge.headVertex());
		}
		return false;
	}
	public boolean addEdge(Edge anEdge) {
		if(anEdge!=null) {
			int tailVertex= anEdge.tailVertex();
			int headVertex = anEdge.headVertex();
			if(this.vertexDoesExist(tailVertex) && this.vertexDoesExist(headVertex)) {
				if(this.adjacency()[tailVertex][headVertex] == AdjacencyMatrixGraph.EDGE_NONE) {
					this.adjacency()[tailVertex][headVertex] = AdjacencyMatrixGraph.EDGE_EXIST;
					this.adjacency()[headVertex][tailVertex] = AdjacencyMatrixGraph.EDGE_EXIST;
					this.setNumberOfEdges(this.numberOfEdges()+1);
					return true;
				}
			}
		}
		return false;
	}
	
}

