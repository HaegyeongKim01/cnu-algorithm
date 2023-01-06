package graph;

import list.Iterator;

public interface Graph<E> {		//Edge를 위한 Generic Type을 명시하였다. 그래프에 따라 edge의 type이 달라질 수 있기에
	public int numberOfVertices();
	public int numberOfEdges();
	
	public boolean vertexDoesExist(int aVertex);
	public boolean edgeDoesExist(E anEdge);
	public boolean edgeDoesExist(int aTailVertex, int aHeadVertex);
	
	//edge의 존재 여부는 검증 X , 존재할 수 있는지만을 검증한다.  //즉, 양 끝 vertex가 존재하면 된다. 
	public boolean edgeIsValid(E anEdge);   //edgeIsValid는 edge의 존재 여부는 검증No, 존재할 수 있는지 만 검증한다.
	public boolean edgeIsValid(int aTailVertex, int aHeadVertex);  //즉, 양 끝 vertex가 존재하면 된다. 
	
	
	//7주차에서 새롭게 추가 
	public E edge(int aTailVertex, int aHeadVertex);
	public boolean addEdge(E anEdge);

	//7주차에서 새롭게 추가
	public Iterator<E> neighborIteratorOf(int aTailVertex);
}
