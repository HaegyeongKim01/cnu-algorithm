package graph;

import list.LinkedList;
import list.Iterator;

public class DirectedAdjacencyListGraph<E extends Edge> extends AdjacencyGraph<E> {

	private LinkedList<E>[] _adjacency;
	
	//getter/setter
	protected LinkedList<E>[] adjacency(){
		return this._adjacency;
	}
	protected void setAdjacency(LinkedList<E>[] newAdjacency) {
		this._adjacency = newAdjacency;
	}
	
	//protected methods
	protected LinkedList<E> neighborListOf(int aTailVertex){
		return this.adjacency()[aTailVertex];
	}
	protected int adjacencyOfEdge(int aTailVertex, int aHeadVertex) {
		//aTailVertex와 aHeadVertex는 valid하다고 가정한다. 
		Iterator<E> iterator = this.neighborIteratorOf(aTailVertex);
		while(iterator.hasNext()) {
			E neighborEdge = iterator.next();
			if(neighborEdge.headVertex() == aHeadVertex) {
				return AdjacencyGraph.EDGE_EXIST;
			}
		}
		return AdjacencyGraph.EDGE_NONE;
	}
	
	//Construcotor
	public DirectedAdjacencyListGraph() {
		this.setAdjacency(null);
		this.setNumberOfEdges(0);
		this.setNumberOfVertices(0);
	}
	@SuppressWarnings("unchecked")
	public DirectedAdjacencyListGraph(int givenNumberOfVertices) {
		this.setNumberOfVertices(givenNumberOfVertices);
		this.setAdjacency(new LinkedList[this.numberOfVertices()]);
		for(int tailVertex = 0; tailVertex < this.numberOfVertices(); tailVertex++) {
			this.adjacency()[tailVertex] = new LinkedList<E>();
		}
		this.setNumberOfEdges(0);
	}
	
	//public methods
	@Override
	public E edge(int aTailVertex, int aHeadVertex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean edgeDoesExist(int aTailVertex, int aHeadVertex) {
		// TODO Auto-generated method stub
		return (this.adjacencyOfEdge(aTailVertex, aHeadVertex) != AdjacencyGraph.EDGE_NONE);
	}
	@Override
	public boolean edgeDoesExist(E anEdge) {
		// TODO Auto-generated method stub
		if(anEdge != null) {
			return this.edgeDoesExist(anEdge.tailVertex(), anEdge.headVertex());
		}
		return false;
	}
	@Override
	public boolean addEdge(E anEdge) {
		// TODO Auto-generated method stub
		if(this.edgeIsValid(anEdge) && (!this.edgeDoesExist(anEdge))) {
			this.neighborListOf(anEdge.tailVertex()).add(anEdge);
			this.setNumberOfEdges(this.numberOfEdges()+1);
			return true;
		}
		return false;
	}

	@Override
	public Iterator<E> neighborIteratorOf(int aTailVertex) {
		// TODO Auto-generated method stub
		if(this.vertexDoesExist(aTailVertex)) {
			return this.adjacency()[aTailVertex].iterator();
		}
		return null;
	}

}
