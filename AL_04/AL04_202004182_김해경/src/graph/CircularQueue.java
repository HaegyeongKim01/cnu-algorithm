package graph;

public class CircularQueue<T> implements Queue<T>{
	//private Variables
	private T[] _elements;
	private int _maxLength; 	//CircularQueue는 실제 저장 가능한 원소 개수 배열의 크기 보다 하나 작은 개수가 최대 저장 개수 
	private int _front;
	private int _rear;
	
	//Private Getter / Setter           모든 setter getter가 private임!!!!!!
	//getter
	private T[] elements() {			//getter
		return this._elements;
	}
	private void setElements(T[] newElements) { 	//setter
		this._elements = newElements;
	}
	private int maxLength() {			//getter
		return this._maxLength;
	}
	private void setMaxLength(int newMaxLength) {	//setter
		this._maxLength = newMaxLength;
	}
	private void setFront(int newFront) {   //setter
		this._front = newFront;
	}
	private int front() {   //getter
		return this._front;
	}
	private void setRear(int newRear) {  //setter
		this._rear = newRear;
	}
	private int rear() {    //getter
		return this._rear;
	}
	
	//private Methods
	private int nextFront() {
		return ((this.front()+1)%this.maxLength());
	}
	private int nextRear() {
		return ((this.rear()+1)%this.maxLength());
	}
	
	//Constructor 생성자
	@SuppressWarnings("unchecked")
	public CircularQueue(int givenCapacity) {
		this.setMaxLength(givenCapacity);
		this.setElements((T[]) new Object[this.maxLength()]);
		this.reset();
	}
	
	@Override
	public void reset() {
		// TODO Auto-generated method stub
		this.setFront(0);
		this.setRear(0);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		if(this.front() <= this.rear())
			return (this.rear() - this.front());
		else {
			return (this.maxLength() + this.rear() - this.front());
		}
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return (this.front() == this.rear());
	}

	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		//return (this.nextRear() == this.front());
		return (this.front() == this.nextRear());    //CircularQueue이기에 rear 다음이 front와 같다면 full
	}

	//rear set하고 성공하면 true반환
	@Override
	public boolean add(T anElement) { 
		// TODO Auto-generated method stub
		if (this.isFull()) {
			return false;
		}	
		else {
			this.setRear(this.nextRear());
			this.elements()[this.rear()] = anElement;
			return true;
		}	
	}

	@Override
	public T remove() {
		// TODO Auto-generated method stub
		if (this.isEmpty()) {    //비어있다면..
			return null;
		}
		else {
			this.setFront(this.nextFront());   
			return this.elements()[this.front()];
		}
	}
	
}
