package graph;

import java.util.LinkedList;

//import graph.AdjacencyMatrixGraph;

public class Coloring {
	//private Instance Variables
	private AdjacencyMatrixGraph 	_graph;				//색칠을 할 그래프
	private VertexColor[] 			_vertexColors;		//각 vertex의 color를 저장할 배열
	private int 					_startingVertex;	//색칠 출발 vertex
	private LinkedList<Edge> 		_sameColorEdges;	//끝 색이 같은 edge들의 리스트
	
	//getter setter   ==>   private
	private AdjacencyMatrixGraph graph() {
		return this._graph;
	}
	private void setGraph(AdjacencyMatrixGraph newGraph) {
		this._graph = newGraph;
	}
	private VertexColor[] vertexColors() {	//vertexColor배열을 반환
		return this._vertexColors;
	}
	private void setVertexColor(VertexColor[] newVertexColors) { //배열 color set
		this._vertexColors = newVertexColors;
	}
	private void setVertexColor (int aVertex, VertexColor newColor) {	//각각 color 인덱스 set
		this.vertexColors()[aVertex] = newColor;
	}
	private int startingVertex() {
		return this._startingVertex;
	}
	private void setStartingVertex(int newStartingVertex) {
		this._startingVertex = newStartingVertex;
	}
	private void setSameColorEdges(LinkedList<Edge> newSameColorEdges) {
		this._sameColorEdges = newSameColorEdges;
	}
	
	
	//public getter
	public VertexColor vertexColor(int aVertex) {	//private getter사용하여 ..생성된 color배열 받아온다. 
		return this.vertexColors()[aVertex];		//받아온 color배열에서 해당하는 vertex Color!! 하나를 반환
	}
	//public getter	//같은 색 Edge를 모아서 보관하고 있는 List를 반환한다. 
	public LinkedList<Edge> sameColorEdges(){
		return this._sameColorEdges;
	}
	
	//Constructor
	public Coloring(AdjacencyMatrixGraph givenGraph) {
		this.setGraph(givenGraph);		//graph 설정
		this.setVertexColor(new VertexColor[this.graph().numberOfVertices()]);
		for (int vertex =0; vertex <this.graph().numberOfVertices();vertex ++) {
			this.setVertexColor(vertex, VertexColor.NONE);
		}
		this.setSameColorEdges(new LinkedList<Edge>());
		this.setStartingVertex(0);
	}
	
	//public methods 실행실행
	public void runColoring() {
		this.paintVertices();		//모든 vertex에 색칠하기를 실행한다.
		this.findSameColorEdges();	//색칠하기를 마친 후에 양끝 vertex의 색이 동일한 edge를 찾는다.
		this.listAllComponents();	//두 개 이상의 component일 경우 독립된 component가 색칠 되지 않는 경우를 방지한다.  ++생각해볼점의 추가 구현
	}
	
	//방문시 vertexColor set!  방문처리하는 함수
	//setVertexColor() 함수 호출을 통해 VertexColor[] 배열의 해당하는 정점의 인덱스의 값을 입력받은 color매개변수로 set한다. 
	private void visitVertex(int vertex, VertexColor color) { 
		//visit: paint "vertex" as color
		this.setVertexColor(vertex, color);
	}
	
	private void paintVertices() {
		CircularQueue<Integer> bfsQueue = new CircularQueue<Integer>(this.graph().numberOfVertices());
		
		this.visitVertex(this.startingVertex(), VertexColor.RED);      //시작vertex red로 색칠 == 방문 처리 
		bfsQueue.add(this.startingVertex());
		
		while(! bfsQueue.isEmpty()) {
			int tailVertex = bfsQueue.remove();
			VertexColor headVertexColor = (this.vertexColor(tailVertex) == VertexColor.RED) ? VertexColor.BLUE : VertexColor.RED;
		
			for(int headVertex = 0; headVertex < this.graph().numberOfVertices(); headVertex++) {
				Edge visitingEdge = new Edge(tailVertex, headVertex);
				if(this.graph().edgeDoesExist(visitingEdge)) {
					if(this.vertexColor(headVertex) == VertexColor.NONE) {
						this.visitVertex(headVertex, headVertexColor);
						bfsQueue.add(headVertex);
					}
				}
			}
			
		}
	}
	
	private void listAllComponents() {
		for (int vertex = 0; vertex < this.graph().numberOfVertices(); vertex++) {
			if(this.vertexColor(vertex) == VertexColor.NONE ) {
				this.setStartingVertex(vertex);
				this.paintVertices();
			}
		}
	}
	
	//같은 color 를 가지는 edge를 찾는 함수 
	private void findSameColorEdges() {
		boolean once = true;
		for (int tailVertex = 0; tailVertex<this.graph().numberOfVertices();tailVertex++) {
			for (int headVertex = 0; headVertex<this.graph().numberOfVertices(); headVertex++) {
				Edge visitingEdge = new Edge(tailVertex, headVertex);      //3 4 
				Edge visitingEdge2 = new Edge(headVertex, tailVertex);     // 4 3
				
				if (this.graph().edgeDoesExist(visitingEdge)) {
					if(this.vertexColor(tailVertex) == this.vertexColor(headVertex)) {		
						if(this.graph().edgeDoesExist(visitingEdge2)) {
							if(once) {
								this.sameColorEdges().add(visitingEdge);
								once = false;
							}
							
						}
						else {
							this.sameColorEdges().add(visitingEdge);
						}
						
						
					}
				}
			}
		}
	}
	
	
	
}
