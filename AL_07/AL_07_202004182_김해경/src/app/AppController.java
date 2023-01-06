package app;

import graph.AdjacencyGraph;
import graph.WeightedEdge;
import graph.WeightedDirectedAdjacencyListGraph;
import shortestPaths.ShortestPaths;
import list.Iterator;
import list.Stack;

public class AppController {
	//Private instance variables
	private AdjacencyGraph<WeightedEdge> _graph;
	private ShortestPaths<WeightedEdge>  _shortestPaths;
	
	//getter & setter
	private AdjacencyGraph<WeightedEdge> graph(){
		return this._graph;
	}
	private void setGraph(AdjacencyGraph<WeightedEdge> newGraph) {
		this._graph = newGraph;
	}
	
	public ShortestPaths<WeightedEdge> shortestpaths(){
		return this._shortestPaths;
	}
	
	public void setShortestpaths(ShortestPaths<WeightedEdge> newShortestpaths) {
		this._shortestPaths = newShortestpaths;
	}
	
	//Constructor
	public AppController(){
		this.setGraph(null);		//AppController객체 생성 시점에, 그래프 객체는 아직 존재하지 않음       
		this.setShortestpaths(new ShortestPaths<WeightedEdge>());
	}
	
	private void inputAndMakeGraph() {
		AppView.outputLine("> 입력할 그래프의 vertex 수와 edge 수를 먼저 입력해야 합니다: ");
		int numberOfVertices = this.inputNumberOfVertices();
		this.setGraph(new WeightedDirectedAdjacencyListGraph<WeightedEdge>(numberOfVertices));
		
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
	
	//07실습
	private int inputSourceVertex() {
		int sourceVertex = AppView.inputSourceVertex();
		while(! this.graph().vertexDoesExist(sourceVertex)) {
			AppView.outputLine("[오류] 입력된 출발 vertex는 존재하지 않습니다: " + sourceVertex);
			sourceVertex = AppView.inputSourceVertex();
		}
		return sourceVertex;
	}
	
	//07실습
	private void solveAndShowShortestPaths() {
		AppView.outputLine("");
		AppView.outputLine(" > 주어진 그래프에서 최단경로를 찾습니다: ");
		if(this.graph().numberOfVertices() <= 1) {
			AppView.outputLine("[오류] vertex 수(" + this.graph().numberOfVertices() + 
							")가 너무 적어서, 최단경로 찾기를 하지 않습니다. 2개 이상이어야 합니다.");
		}
		else {
			AppView.outputLine("> 출발점을 입력해야 합니다: ");
			int sourceVertex = this.inputSourceVertex();
			if(this.shortestpaths().solve(this.graph(), sourceVertex)) {
				AppView.outputLine("");
				AppView.outputLine("> 최단 경로별 비용과 경로는 다음과 같습니다: ");
				AppView.outputLine("출발점=" + sourceVertex + ":");
				for(int destination = 0 ; destination < this.graph().numberOfVertices(); destination++) {
					if(destination != sourceVertex) {
						AppView.output(" [목적점=" + destination + "] ");
						AppView.output("최소비용=" + this.shortestpaths().minCostOfPathToDestination(destination)+", ");
						AppView.output("경로");
						Stack<Integer> pathToDestination = this.shortestpaths().pathToDestination(destination);
						while(!pathToDestination.isEmpty()) {
							AppView.output(" -> " + pathToDestination.pop());
						}
						AppView.outputLine("");
					}
				}
			}
			else {
				AppView.outputLine("[오류] 최단경로 찾기를 실패하였습니다. ");
			}
		}
	}

	private void showGraph() {
		AppView.outputLine("");
		AppView.outputLine("> 입력된 그래프는 다음과 같습니다:");
		for(int tailVertex = 0;tailVertex <this.graph().numberOfVertices();tailVertex++) {
			AppView.output("["+ tailVertex + "] ->");
			Iterator<WeightedEdge> neighborIterator=
					this.graph().neighborIteratorOf(tailVertex);
			while(neighborIterator.hasNext()) {
				WeightedEdge nextEdge = neighborIterator.next();
				AppView.output(" " + nextEdge.headVertex());
				AppView.output("(" + nextEdge.weight() + ")");
			}
			AppView.outputLine("");
		}
		
	}
	
	
	public void run() {
		AppView.outputLine("<<< 최단경로 찾기 프로그램을 시작합니다 >>>");
		this.inputAndMakeGraph();
		this.showGraph();
		
		this.solveAndShowShortestPaths();
		AppView.outputLine("");
		AppView.outputLine("<<< 최단경로 찾기 프로그램을 종료합니다>>>");
	}
}
