package graph;

public class AppController {
	//Private instance variables
	private AdjacencyMatrixGraph _graph;
	private PairwiseDisjointSets _pairwiseDisjointSets;
	private Coloring 			 _coloring;
	
	//getter & setter
	private AdjacencyMatrixGraph graph() {
		return this._graph;
	}
	private void setGraph(AdjacencyMatrixGraph newGraph) {
		this._graph = newGraph;
	}
	//클래스 변수 getter
	private PairwiseDisjointSets pairwiseDisjointSets() {
		return this._pairwiseDisjointSets;
	}
	//클래스 변수 setter
	private void setPairwiseDisjointSets(PairwiseDisjointSets newPairwiseDisjointSets) {
		this._pairwiseDisjointSets = newPairwiseDisjointSets;
	}
	private Coloring coloring() {
		return this._coloring;
	}
	private void setColoring(Coloring aColoring) {
		this._coloring = aColoring;
	}
	
	//생성자
	public AppController(){
		this.setGraph(null);		//AppController객체 생성 시점에, 그래프 객체는 아직 존재하지 않음
		this.setPairwiseDisjointSets(null);	 //null로 setter 호출\\
		this.setColoring(null);          
	}
	//pairwiseDisjoint는 처음에 객체를 생성하거나 초기화할 수 없어서 vertex 알게 된 직후 초기화하는 함수
	private void initCycleDetection() {
		this.setPairwiseDisjointSets(new PairwiseDisjointSets(this.graph().numberOfVertices()));
	}
	
	private void inputAndMakeGraph() {
		AppView.outputLine("> 입력할 그래프의 vertex 수와 edge 수를 먼저 입력해야 합니다: ");
		int numberOfVertices = this.inputNumberOfVertices();
		this.setGraph(new AdjacencyMatrixGraph(numberOfVertices));
		
		int numberOfEdges = this.inputNumberOfEdges();
		AppView.outputLine("");
		AppView.outputLine("> 이제부터 edge를 주어진 수 만큼 입력합니다.");
		this.initCycleDetection();       //vertex수 알게 되어서 initCycleDetection한다.
		
		int edgeCount = 0;
		while(edgeCount < numberOfEdges) {
			Edge edge = this.inputEdge();
			if(this.graph().edgeDoesExist(edge)) {
				AppView.outputLine("[오류] 입력된 edge(" + edge.tailVertex() + "," + edge.headVertex()+"는 그래프에 이미 존재합니다.)");
			}
			else {
				edgeCount ++;
				this.graph().addEdge(edge);
				AppView.outputLine("!!새로운 edge(" + edge.tailVertex()+","+edge.headVertex()+") 가 그래프에 삽입되었습니다.");
				if(this.addedEdgeDoesMakeCycle(edge)) {
					AppView.outputLine("![Cycle] 삽입된 edge (" + edge.tailVertex() + "," +
							edge.headVertex() + ") 는 그래프에 사이클을 만들었습니다.");
				}
			}
		}
	
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
	
	private Edge inputEdge() {
		do {
			AppView.outputLine("- 입력할 edge의 두 vertex를 차례로 입력해야 합니다:");
			int tailVertex = AppView.inputTailVertex();
			int headVertex = AppView.inputHeadVertex();
			if(this.graph().vertexDoesExist(tailVertex) && this.graph().vertexDoesExist(headVertex)) {
				if(tailVertex == headVertex) {
					AppView.outputLine("[오류] 두 vertex 번호가 동일합니다.");
				}
				else {
					return (new Edge(tailVertex, headVertex));
				}
			}
			else {
				if(! this.graph().vertexDoesExist(tailVertex)) {
					AppView.outputLine("[오류] 존재하지 않는 tail vertex 입니다: " + tailVertex);
				}
				if(! this.graph().vertexDoesExist(headVertex)) {
					AppView.outputLine("[오류] 존재하지 않는 head vertex 입니다: "+headVertex);
				}
			}
		} while(true);
	
	}
	
	//cycle 검사 
	//edge가 삽입 되었을 때 cycle이 만들어지는 지를 검사하는 함수 
	private boolean addedEdgeDoesMakeCycle(Edge anAddedEdge) {
		int tailVertex = anAddedEdge.tailVertex();		//추가된 edge의 tailV getter 
		int headVertex = anAddedEdge.headVertex();		//추가된 edge의 headV getter
		int setForTailVertex = this.pairwiseDisjointSets().find(tailVertex);	//find() : root return 함
		int setForHeadVertex = this.pairwiseDisjointSets().find(headVertex);	//find() : root return 함
		if (setForTailVertex == setForHeadVertex) {
			return true;
		}else {
			this.pairwiseDisjointSets().union(setForTailVertex, setForHeadVertex); //같지 않으면 union시킴
			return false;
		}
	}	
	

	private void showGraph() {
		AppView.outputLine("");
		AppView.outputLine("> 입력된 그래프는 다음과 같습니다:");
		for(int tailVertex = 0;tailVertex <this.graph().numberOfVertices();tailVertex++) {
			AppView.output("["+ tailVertex + "] ->");
			for(int headVertex= 0 ; headVertex <this.graph().numberOfVertices();headVertex++) {
				if(this.graph().edgeDoesExist(tailVertex, headVertex)) {
					AppView.output(" " + headVertex);
				}
			}
			AppView.outputLine("");
		}
	}
	
	private void showColoring() {
		AppView.outputLine("");
		AppView.outputLine("> 각 vertex에 칠해진 색깔을 다음과 같습니다: ");
		for (int vertex = 0; vertex < this.graph().numberOfVertices(); vertex++) {
			AppView.outputLine("["+ vertex + "]" + this.coloring().vertexColor(vertex).name());  //enum에 정의된 생상 값의 이름을 string객체로 얻을 수 있다. 
		}
		
		AppView.outputLine("");
		AppView.outputLine("> 양 끝 vertex의 색이 같은 edge들은 다음과 같습니다: ");
		if(this.coloring().sameColorEdges().size() ==0) {
			AppView.outputLine("!! 모든 edge의 양 끝 vertex의 색깔이 다릅니다. ");
		}
		else {
			//Iterator<Edge> iterator = this.coloring().sameColorEdges().iterator()
			java.util.Iterator<Edge> iterator = this.coloring().sameColorEdges().iterator();
			while(iterator.hasNext()) {
				Edge sameColorEdge = iterator.next();
				AppView.output("(" + sameColorEdge.tailVertex() + "," + sameColorEdge.headVertex() + "): ");
				AppView.outputLine( this.coloring().vertexColor(sameColorEdge.tailVertex()).name());
			}
		}
		
	}
		
	
	public void run() {
		AppView.outputLine("<<< 그래프 색칠하기를 시작합니다 >>>");
		this.inputAndMakeGraph();
		this.showGraph();
		
		this.setColoring(new Coloring(this.graph()));	//coloring
		this.coloring().runColoring();
		this.showColoring();
		
		AppView.outputLine("");
		AppView.outputLine("<<< 색칠하기를 종료합니다>>>");
	}
}
