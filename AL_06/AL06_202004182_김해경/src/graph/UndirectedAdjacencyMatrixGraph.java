package graph;

public class UndirectedAdjacencyMatrixGraph<E extends Edge> implements Graph<E>{
	//private constants
	private static final int EDGE_EXIST = 1;
	private static final int EDGE_NONE = 0;
	
	//private instance variables
	private int _numberOfVertices;
	private int _numberOfEdges;
	private int[][] _adjacency;
	
	//기본 생성자 //상속 받는 WeightedUndirected에서 super 생성자를 call하지 않으면 super class의 기본 생성자가 호출되는데 
	//이때 기본 생성자가 없으면 오류가 남을 방지하기 위해 기본 생성자를 추가한다. 
	public  UndirectedAdjacencyMatrixGraph() {
		this.setNumberOfVertices(0);
		this.setNumberOfEdges(0);
		this.setAdjacency(null);
	}
	
	public UndirectedAdjacencyMatrixGraph(int givenNumberOfVertices){
		this.setNumberOfVertices(givenNumberOfVertices);
		this.setNumberOfEdges(0);
		this.setAdjacency(new int[givenNumberOfVertices][givenNumberOfVertices]);
		for(int tailVertex = 0; tailVertex < this.numberOfVertices(); tailVertex++) {
			for (int headVertex = 0; headVertex < this.numberOfVertices(); headVertex++){
				this.adjacency()[tailVertex][headVertex] = UndirectedAdjacencyMatrixGraph.EDGE_NONE;
			}
		}
	}
	
	//private getter &setters
	@Override
	public int numberOfVertices() {
		return this._numberOfVertices;
	}
	protected void setNumberOfVertices(int newNumberOfVertices) {
		this._numberOfVertices = newNumberOfVertices;
	}
	
	@Override
	public int numberOfEdges() {
		return this._numberOfEdges;
	}
	
	protected void setNumberOfEdges(int newNumberOfEdges){
		this._numberOfEdges = newNumberOfEdges;
	}
	
	protected int[][] adjacency(){
		return this._adjacency;
	}
	protected void setAdjacency(int[][] newAdjacency) {
		this._adjacency = newAdjacency;
	}
	
	@Override
	public boolean edgeIsValid(int aTailVertex, int aHeadVertex) {
		// TODO Auto-generated method stub
		return (this.vertexDoesExist(aTailVertex) && this.vertexDoesExist(aHeadVertex));
	}
	@Override
	public boolean edgeIsValid(E anEdge) {
		// TODO Auto-generated method stub
		if(anEdge != null) {
			if(this.edgeIsValid(anEdge.tailVertex(), anEdge.headVertex())) {
				return true;
			}
		}
		return false;
	}
	//AL4에서 사용되었던 것 그대로 나둠 
	public boolean vertexDoesExist(int aVertex) {
		return (aVertex >= 0 && aVertex < this.numberOfVertices());
	}
	
	public boolean edgeDoesExist(int tailVertex, int headVertex) {
		if (this.vertexDoesExist(tailVertex) && this.vertexDoesExist(headVertex)) {
			return (this.adjacency()[tailVertex][headVertex] == UndirectedAdjacencyMatrixGraph.EDGE_EXIST);
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
	
	//그래프가 directed / undirected에 따라 달라지고     weighted / unweighted 따라 달라진다. 
	
	public boolean addEdge(Edge anEdge) {
		if(anEdge!=null) {
			int tailVertex= anEdge.tailVertex();
			int headVertex = anEdge.headVertex();
			if(this.vertexDoesExist(tailVertex) && this.vertexDoesExist(headVertex)) {
				if(this.adjacency()[tailVertex][headVertex] == UndirectedAdjacencyMatrixGraph.EDGE_NONE) {
					this.adjacency()[tailVertex][headVertex] = UndirectedAdjacencyMatrixGraph.EDGE_EXIST;
					this.adjacency()[headVertex][tailVertex] = UndirectedAdjacencyMatrixGraph.EDGE_EXIST;
					this.setNumberOfEdges(this.numberOfEdges()+1);
					return true;
				}
			}
		}
		return false;
	}


	
}

