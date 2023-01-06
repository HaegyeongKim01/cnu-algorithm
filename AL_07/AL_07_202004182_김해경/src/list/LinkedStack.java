package list;

public class LinkedStack<E> implements Stack<E>{

	private int _size;
	private LinkedNode<E> _top;
	
	private LinkedNode<E> top(){
		return this._top;
	}
	private void setTop(LinkedNode<E> newTop) {
		this._top = newTop;
	}
	
	public LinkedStack() {
		// TODO Auto-generated constructor stub
		this.reset();
	}

	//public methods
	@Override
	public void reset() {
		// TODO Auto-generated method stub
		this.setSize(0);
		this.setTop(null);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this._size;
	}

	private void setSize(int newSize) {
		this._size = newSize;
	}
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return (this.size() == 0);
	}

	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean push(E anElement) {
		// TODO Auto-generated method stub
		if(this.isFull()) {
			return false;
		}
		else {
			LinkedNode<E> newTop = new LinkedNode<E>(anElement, this.top());
			this.setTop(newTop);
			this.setSize(this.size()+1);
			return true;
		}
		
	}

	@Override
	public E pop() {
		// TODO Auto-generated method stub
		if(this.isEmpty()) {
			return null;
		}
		else {
			E poppedElement = this.top().element();
			this.setTop(this.top().next());
			this.setSize(this.size()-1);
			return poppedElement;
		}
		
	}

	@Override
	public E peek() {
		// TODO Auto-generated method stub
		if(this.isEmpty()){
			return null;
		}
		else {
			return this.top().element();
		}
	}

}
