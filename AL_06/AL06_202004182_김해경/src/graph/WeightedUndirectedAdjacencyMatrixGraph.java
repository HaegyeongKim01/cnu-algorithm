package graph;

public class WeightedUndirectedAdjacencyMatrixGraph<WE extends WeightedEdge> 
	extends UndirectedAdjacencyMatrixGraph<WE>
		implements SupplementForWeightedGraph<WE> 
{

	//constant
	private static final int WEIGHTED_EDGE_NONE = -1;
	
	//private Methods
	//반드시 필요한 것은 아니지만 코드의 가독성(readability)을 높이므로 정의하여 사용하기로 한다. 
	private void setWeightOfAdjacency(int aTailVertex, int aHeadVertex, int newWeight) {
		this.adjacency()[aTailVertex][aHeadVertex] = newWeight;
	}
	private void setWeightOfEdgeAsNone(int aTailvertex, int aHeadVertex) {
		this.setWeightOfAdjacency(aTailvertex, aHeadVertex, WeightedUndirectedAdjacencyMatrixGraph.WEIGHTED_EDGE_NONE);
	}
	
	//Constructor      
	//밑의 생성자만 하면 상속받은 class의 생성자는 superclass의 생성자 중의 하나를 반드시 call해야한다. 
	//밑의 생성자만 하는 경우 superclass의 생성자를 call하지 않았으므로, java는 기본 생성자를 call한 것으로 간주한다. 
	//superclass인 UndirectedAdjacencyMatrixGraph에 기본 생성자를 추가한다. 
	public WeightedUndirectedAdjacencyMatrixGraph(int givenNumberOfVertices) {
		super(); //기본 생성자라 하더라도, 이렇게 명시적으로 call을 표기하는 것이 코드의 이해를 도움 
		this.setNumberOfVertices(givenNumberOfVertices);
		this.setNumberOfEdges(0);
		this.setAdjacency(new int[givenNumberOfVertices][givenNumberOfVertices]);
		for(int tailVertex= 0 ; tailVertex <this.numberOfVertices(); tailVertex++) {
			for(int headVertex = 0 ;headVertex < this.numberOfVertices(); headVertex ++) {
				this.setWeightOfEdgeAsNone(tailVertex, headVertex);
			}
		}
	}
	
	//edgeDoesExist도 재정의 해야한다. 
	//그 이유는 adjacecy matrix에서 edge가 존재하지 않음을 표시하는 값의 정의가 바뀌었기 때문 == 상수의 값이 변했기에 
	@Override
	public boolean edgeDoesExist(int aTailVertex, int aHeadVertex) {
		if(this.vertexDoesExist(aTailVertex) && this.vertexDoesExist(aHeadVertex)) {
			return (this.adjacency()[aTailVertex][aHeadVertex] !=
					WeightedUndirectedAdjacencyMatrixGraph.WEIGHTED_EDGE_NONE); //undirectedAdjacencyMatrixGrapg와 비교하면 이 코드 값이 바뀌었음
		}else {
			return false;
		}	
	}

	@Override
	public boolean edgeDoesExist(WE anEdge) {
		if(anEdge != null) {
			return this.edgeDoesExist(anEdge.tailVertex(), anEdge.headVertex());
		}
		return false;
	}

	@Override
	public int weightOfEdge(int aTailVertex, int aHeadVertex) {
		if(this.edgeIsValid(aTailVertex, aHeadVertex)) {
			return this.adjacency()[aTailVertex][aHeadVertex];
		}
		else {
			return WeightedUndirectedAdjacencyMatrixGraph.WEIGHTED_EDGE_NONE;
		}
	}
	
	@Override
	public int weightOfEdge(WE anEdge) {
		if(anEdge != null) {
			return (this.weightOfEdge(anEdge.tailVertex(), anEdge.headVertex()));
		}
		else {
			return WeightedUndirectedAdjacencyMatrixGraph.WEIGHTED_EDGE_NONE;
		}
	}

	//그래프에 추가하는 edge의 type이 달라졌다.      //super의 addEdge()를 override한다. 
	@Override
	public boolean addEdge(WE anEdge) {
		if(anEdge != null) {
			if(this.edgeIsValid(anEdge) && !this.edgeDoesExist(anEdge)) {
				int tailVertex = anEdge.tailVertex();
				int headVertex = anEdge.headVertex();
				this.setWeightOfAdjacency(tailVertex, headVertex, anEdge.weight());
				this.setWeightOfAdjacency(headVertex, tailVertex, anEdge.weight());
				this.setNumberOfEdges(this.numberOfEdges()+1);
				return true;
			}
		}
		return false;
	}
	

}
