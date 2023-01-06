package graph;

import list.Iterator;

public abstract class AdjacencyGraph<E extends Edge> implements Graph<E> {
	//this is the first class for any interface graphs/
	
	//constant
	protected static final int EDGE_EXIST = 1;
	protected static final int EDGE_NONE =0;
	
	//private instance variables
	private int _numberOfVertices;
	private int _numberOfEdges;
	
	@Override
	public int numberOfVertices() {
		return this._numberOfVertices;
	}

	@Override
	public int numberOfEdges() {
		return this._numberOfEdges;
	}
	
	protected void setNumberOfVertices(int newNumberOfVertices) {
		this._numberOfVertices = newNumberOfVertices;
	}
	
	protected void setNumberOfEdges(int newNumberOfEdges) {
		this._numberOfEdges= newNumberOfEdges;
	}
	
	//public methods
	@Override
	public boolean vertexDoesExist(int aVertex) {
		return ((aVertex >= 0) && (aVertex < this.numberOfVertices()));
	}
	
	@Override
	public boolean edgeIsValid(int aTailVertex, int aHeadVertex) {
		return (this.vertexDoesExist(aTailVertex) && this.vertexDoesExist(aHeadVertex));
	}	
	
	@Override
	public boolean edgeIsValid(E anEdge) {
		if(anEdge != null) {
			return (    this.vertexDoesExist(anEdge.tailVertex()) && 
							this.vertexDoesExist(anEdge.headVertex())	);
		}
		return false;
	}
	
	@Override
	public abstract boolean edgeDoesExist(E anEdge);
	
	@Override
	public abstract boolean edgeDoesExist(int aTailVertex, int aHeadVertex);
	@Override
	public abstract boolean addEdge(E anEdge);
	@Override
	public abstract Iterator<E> neighborIteratorOf(int aTailVertex);
	
	
	
}
