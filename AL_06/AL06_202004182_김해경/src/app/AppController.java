package app;

import graph.WeightedUndirectedAdjacencyMatrixGraph;
import graph.WeightedEdge;
import kruskal.MinCostSpanningTree;
import list.List;
import list.Iterator;

public class AppController {
	//Private instance variables
	private WeightedUndirectedAdjacencyMatrixGraph<WeightedEdge> _graph;
	private MinCostSpanningTree									 _minCostSpanningTree;
	private List<WeightedEdge>									 _spanningTreeEdgeList;
	
	//getter & setter
	private WeightedUndirectedAdjacencyMatrixGraph<WeightedEdge> graph() {
		return this._graph;
	}
	private void setGraph(WeightedUndirectedAdjacencyMatrixGraph<WeightedEdge> newGraph) {
		this._graph = newGraph;
	}
	private MinCostSpanningTree minCostSpanningTree(){
		return this._minCostSpanningTree;
	}
	private void setMinCostSpanningTree(MinCostSpanningTree newMinCostSpanningTree) {
		this._minCostSpanningTree = newMinCostSpanningTree;
	}
	
	private List<WeightedEdge> spanningTreeEdgeList(){
		return this._spanningTreeEdgeList;
	}
	private void setSpanningTreeEdgeList(List<WeightedEdge> newSpanningTreeEdgeList) {
		this._spanningTreeEdgeList = newSpanningTreeEdgeList;
	}
	
	//Constructor
	public AppController(){
		this.setGraph(null);		//AppController객체 생성 시점에, 그래프 객체는 아직 존재하지 않음       
		this.setMinCostSpanningTree(null);
		this.setSpanningTreeEdgeList(null);
	}
	
	private void inputAndMakeGraph() {
		AppView.outputLine("> 입력할 그래프의 vertex 수와 edge 수를 먼저 입력해야 합니다: ");
		int numberOfVertices = this.inputNumberOfVertices();
		this.setGraph(new WeightedUndirectedAdjacencyMatrixGraph<WeightedEdge>(numberOfVertices));
		
		int numberOfEdges = this.inputNumberOfEdges();
		AppView.outputLine("");
		AppView.outputLine("> 이제부터 edge를 주어진 수 만큼 입력합니다.");
		
		int edgeCount = 0;
		while(edgeCount < numberOfEdges) {
			WeightedEdge edge = this.inputEdge();
			if(this.graph().edgeDoesExist(edge)) {
				AppView.outputLine("[오류] 입력된 edge(" + 
					edge.tailVertex() + "," + edge.headVertex()+", (" + edge.weight() + ")" + 
					") 는 그래프에 이미 존재합니다.");
			}
			else {
				edgeCount++;
				this.graph().addEdge(edge);
				AppView.outputLine("!새로운 edge (" + 
					edge.tailVertex() + ", " + edge.headVertex()+ ", (" + edge.weight() + ")" +
					") 가 그래프에 삽입되었습니다.");
			}
		}
		//밑의 코드는 while대신 for문을 사용한 것 
		/*
		for(int edgeCount = 0 ; edgeCount < numberOfEdges; edgeCount++) {
			WeightedEdge edge = this.inputEdge();
			if(this.graph().edgeDoesExist(edge)) {
				AppView.outputLine("[오류] 입력된 edge(" + 
					edge.tailVertex() + "," + edge.headVertex()+", (" + edge.weight() + ")" + 
					") 는 그래프에 이미 존재합니다.");
				edgeCount -= 1;
			}
			else {
				this.graph().addEdge(edge);
				AppView.outputLine("!새로운 edge (" + 
					edge.tailVertex() + ", " + edge.headVertex()+ ", (" + edge.weight() + ")" +
					") 가 그래프에 삽입되었습니다.");
			}
		}*/
		
	}
	
	private int inputNumberOfVertices() {
		while(true) {
			AppView.output("? Vertex수를 입력하시오: ");
			try {
				int numberOfVertices = AppView.inputInt();
				if(numberOfVertices > 0) {
					return numberOfVertices;
				}
				else {
					AppView.output("[오류] Vertex 수는 0 보다 커야 합니다.");
				}
			}
			catch(NumberFormatException e){
				AppView.outputLine("[오류] 올바른 숫자가 입력되지 않았습니다.");
			}
		}
	}
	
	private int inputNumberOfEdges() {
		while(true) {
			AppView.output("? Edge수를 입력하시오: ");
			try {
				int numberOfEdges = AppView.inputInt();
				if(numberOfEdges >= 0) {
					return numberOfEdges;
				}
				else {
					AppView.output("[오류] Edge 수는 0 보다 크거나 같아야 합니다.");
				}
			}
			catch(NumberFormatException e){
				AppView.outputLine("[오류] 올바른 숫자가 입력되지 않았습니다.");
			}
		}
	}
	
	private WeightedEdge inputEdge() {
		do {
			AppView.outputLine("- 입력할 edge의 두 vertex와 cost를 차례로 입력해야 합니다:");
			int tailVertex = AppView.inputTailVertex();
			int headVertex = AppView.inputHeadVertex();
			int cost = AppView.inputCost();
			if(this.graph().vertexDoesExist(tailVertex) && this.graph().vertexDoesExist(headVertex)) {
				if(tailVertex == headVertex) { //시작 vertex와 끝 vertex가 같으면 안 된다. 
					AppView.outputLine("[오류] 두 vertex 번호가 동일합니다.");
				}
				else {
					return (new WeightedEdge(tailVertex, headVertex, cost));
				}
			}
			else {
				if(! this.graph().vertexDoesExist(tailVertex)) {
					AppView.outputLine("[오류] 존재하지 않는 tail vertex 입니다: " + tailVertex);
				}
				if(! this.graph().vertexDoesExist(headVertex)) {
					AppView.outputLine("[오류] 존재하지 않는 head vertex 입니다: "+headVertex);
				}
				if(cost < 0) {
					AppView.outputLine("[오류] edge의 비용은 양수이어야 합니다: " + cost );
				}
			}
		} while(true);
	
	}

	private void showGraph() {
		AppView.outputLine("");
		AppView.outputLine("> 입력된 그래프는 다음과 같습니다:");
		for(int tailVertex = 0;tailVertex <this.graph().numberOfVertices();tailVertex++) {
			AppView.output("["+ tailVertex + "] ->");
			for(int headVertex= 0 ; headVertex <this.graph().numberOfVertices();headVertex++) {
				if(this.graph().edgeDoesExist(tailVertex, headVertex)) {
					AppView.output(" " + headVertex);
					AppView.output("(" + this.graph().weightOfEdge(tailVertex, headVertex) + ")");
				}
			}
			AppView.outputLine("");
		}
		//추가 
		AppView.outputLine("");
		AppView.outputLine("> 입력된 그래프의 Adjacency Matrix는 다음과 같습니다:");
		AppView.output("      ");
		for(int headVertex = 0 ;headVertex <this.graph().numberOfVertices(); headVertex++) {
			AppView.output(String.format(" [%1s]", headVertex));
		}
		AppView.outputLine("");
		for(int tailVertex = 0 ;tailVertex <this.graph().numberOfVertices(); tailVertex++) {
			AppView.output("[" +tailVertex+"] ->");
			for(int headVertex = 0 ;headVertex <this.graph().numberOfVertices(); headVertex++) {
				int weight = this.graph().weightOfEdge(tailVertex, headVertex);
				AppView.output(String.format("%4d", weight));
			}
			AppView.outputLine("");
			
		}
	}
	
	private void showMinCostSpanningTree() {
		AppView.outputLine("");
		AppView.outputLine("> 주어진 그래프의 최소비용 확장트리의 edge들은 다음과 같습니다: ");
		Iterator<WeightedEdge> listIterator = this.spanningTreeEdgeList().iterator();
		while(listIterator.hasNext()) {
			WeightedEdge edge = listIterator.next();
			AppView.outputLine("Tree Edge(" + edge.tailVertex() + ", "+ edge.headVertex()+
										", (" + edge.weight() + "))");
		}
	}
	
	public void run() {
		AppView.outputLine("<<< 최소비용 확장트리 찾기 프로그램을 시작합니다 >>>");
		this.inputAndMakeGraph();
		this.showGraph();
		
		AppView.outputLine("");
		AppView.outputLine("> 주어진 그래프의 최소비용 확장트리 찾기를 시작합니다 <");
		AppView.outputLine("");
		this.setMinCostSpanningTree(new MinCostSpanningTree());
		this.setSpanningTreeEdgeList(this.minCostSpanningTree().solve(this.graph()));
		if(this.spanningTreeEdgeList() == null) {
			AppView.outputLine("> 주어진 그래프의 컴포넌트가 2개 이상이어서, 최소비용 확장트리 찾기를 실패하였습니다.");
		}
		else {
			this.showMinCostSpanningTree();
		}
		
		AppView.outputLine("");
		AppView.outputLine("<<< 최소비용 확장 트리 찾기 프로그램을 종료합니다>>>");
	}
}
