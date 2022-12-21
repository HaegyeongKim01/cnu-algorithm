package graph;

public class LinkedList<T>{
	//private instance variables
	private LinkedNode<T> _head;
	private int _size;
	
	// private  getter / setter
	private LinkedNode<T> head(){
		return this._head;
	}
	private void setHead(LinkedNode<T> newHead) {
		this._head = newHead;
	}
	private int size() {
		return this._size;
	}
	private void setSize(int newSize) {
		this._size = newSize;
	}
	
	//Constructor
	public LinkedList() {
		this.setSize(0);
		this.setHead(null);
	}
	
	//public methods
	public boolean isEmpty() {
		return (this._size ==0);
	}
	public boolean isFull() {
		return false;
	}
	public boolean add(T anElement) {
		LinkedNode<T> newHeadNode = new LinkedNode<T> (anElement, this.head());
		this.setHead(newHeadNode);
		this.setSize(this.size()+1);
		return true;
	}
	public T removeAny() {
		if(this.isEmpty()) {
			return null;
		}
		else {
			T removedElement = this.head().element();
			this.setHead(this.head().next());
			this.setSize(this.size()-1);
			return removedElement;
		}
	}
	
	public Iterator<T> iterator() {
		return (new IteratorForLinkedList());
	}
	
	public class IteratorForLinkedList implements Iterator<T> {
		//private instance variable
		private LinkedNode<T> _nextNode;
		
		//public getter setter;
		private LinkedNode<T> nextNode(){
			return this._nextNode;
		}
		private void setNextNode(LinkedNode<T> newNextNode) {
			this._nextNode = newNextNode;
		}
		
		//private Constructor
		private IteratorForLinkedList() {
			this._nextNode = LinkedList.this.head();
		}

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return (this.nextNode() != null);
		}

		@Override
		public T next() {
			// TODO Auto-generated method stub
			T nextElement = this.nextNode().element();
			this.setNextNode(this.nextNode().next());
			return nextElement;
		}
		
	}
	
}
