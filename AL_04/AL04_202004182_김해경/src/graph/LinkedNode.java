package graph;

public class LinkedNode<T> {
	//private instance variable
	private T _element;
	private LinkedNode<T> _next;
	
	//Getter Setter
	public T element() {   ///LinkedList에서 사용 removeAny에서 
		return this._element;
	}
	public void setElement(T newElement) {
		this._element = newElement;
	}
	public LinkedNode<T> next(){         ////LinkedList에서 사용 removeAny에서 
		return this._next;
	}
	public void setNext(LinkedNode<T> newNext) {  
		this._next = newNext;
	}
	
	//Constructor
	public LinkedNode() {
		this.setElement(null);
		this.setNext(null);
	}
	public LinkedNode(T givenElement, LinkedNode<T> givenNext) {
		this.setElement(givenElement);
		this.setNext(givenNext);
	}
}
