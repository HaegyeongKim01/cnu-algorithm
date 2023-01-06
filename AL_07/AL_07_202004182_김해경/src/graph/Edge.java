package graph;

public class Edge {
	//private Instance Variable
	private int _tailVertex;
	private int _headVertex;
	
	//생성자 
	public Edge(int givenTailVertex, int givenHeadVertex) {
		//주어진 tail vertex와 head vertex를 갖는 edge객체를 얻는다. 
		this.setTailVertex(givenTailVertex);
		this.setHeadVertex(givenHeadVertex);
	}
	//생성자 그냥 this._tailVertex = givenTailVertex해도 되지만
	// 클래스 안에서 클래스 자신의 비공개 인스턴스 대신 getter/setter 사용하여 내부적으로 더욱 "캡슐화"한다.
	
	public void setTailVertex(int newTailVertex) {
		//edge의 tail vertex를 newTailVertex로 설정한다.
		//_tailVertex위한 setter
		this._tailVertex = newTailVertex;
	}
	
	public int tailVertex() {
		//edge의 tail vertex를 얻는다. 
		//_tailVertex위한 getter
		return this._tailVertex; 
	}
	
	public void setHeadVertex(int newHeadVertex) {
		//edge의 head Vertex를 newHeadVertex로 설정한다.
		//_HeadVertex위한 setter
		this._headVertex = newHeadVertex;
	}
	
	public int headVertex() {
		//edge의 head vertex를 얻는다.
		//_HeadVertex위한 getter
		return this._headVertex; 
	}
}
