package list;

public class LinkedList<T> extends List<T>{
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
	
	//List<T>로 부터 상속 받은 메소드  
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this._size;
	}

	private void setSize(int newSize) {
		this._size = newSize;
	}
	//
	
	//Constructor
	public LinkedList() {
		this.setSize(0);
		this.setHead(null);
	}
	
	//public methods
	@Override
	public boolean isEmpty() {
		return (this._size ==0);
	}
	@Override
	public boolean isFull() {
		return false;
	}
	@Override
	public boolean add(T anElement) {
		LinkedNode<T> newHeadNode = new LinkedNode<T> (anElement, this.head());
		this.setHead(newHeadNode);
		this.setSize(this.size()+1);
		return true;
	}
	@Override
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
	@Override
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


	@Override
	public void reset() {  //리스트를 초기 상태로 만든다.  객체를 생성한 직후 상태와 동일하게 하면 될 것이다.
		// TODO Auto-generated method stub
		this.setSize(0);
		this.setHead(null);
		
	}
	
}
