package kruskal;

public class MinPriorityQ <E extends Comparable<E>>{
	//최소 우선순위 큐
	//삭제는 큐에 들어 온 순서가 아닌, 현재 큐에 들어 있는 원소 중에서 가장 작은 값을 갖는 원소가 삭제된다.
	
	//Constants
	private static final int DEFAULT_CAPACITY = 100;   //객체 생성시 큐의 크기가 주어지지 않았을 경우의 기본 크기
	private static final int HEAP_ROOT = 1;    //Heap을 표현하는 배열에서의 이진 트리의 root 위치 
	
	//private int _size;
	private int _size;
	private int _capacity;
	private E[] _heap;
	
	//getter&setter
	public int size() {
		return this._size;
	}
	private void setSize(int newSize) {
		this._size = newSize;
	}
	public int capacity() {
		return this._capacity;
	}
	private void setCapacity(int newCapacity) {  //사용자는 큐의 크기를 생성자를 통해서만 제공하므로 private이다.
		this._capacity = newCapacity;
	}
	private E[] heap() {    //class내부에서만 사용한다.
		return this._heap;
	}
	private void setHeap(E[] newHeap) { //class 내부에서만 사용한다.
		this._heap = newHeap;
	}
	
	//Construct
	//기본 생성자
	public MinPriorityQ() {
		this(MinPriorityQ.DEFAULT_CAPACITY);
	}
	@SuppressWarnings("unchecked")
	public MinPriorityQ(int givenCapacity) {
		this.setCapacity(givenCapacity);
		this.setHeap((E[])new Comparable[givenCapacity+1]);  //배열 index 0의 위치는 사용하지 않으므로 생성할 배열의 크기는 최대용량 보다 1 더 커야한다.
		this.setSize(0);         //Comparable 생성할 배열 객체의 원소 class가 <E>. <E>는 generic type이어서 클래스 안에서 그 실체를 알 수 없다. 
								//그래서 보통은 치상위 클래스인 "object를 이용하여 배열을 생성한다. 
								//하지만 여기서는 <E>가 적어도 Comparable class임을 알고있으므로 생성할 배열의 워소의 class를 Comparable로 생성해야한다.
								//그렇지 않으면 runtime에 클래서 변환(class casting)오류가 발생한다.
	}
	
	public boolean isEmpty() {
		return (this.size() ==0);
	}
	
	public boolean isFull() {
		return (this.size() == this.capacity());
	}
	
	public boolean add(E anElement) {
		if(this.isFull()) {
			return false;
		}
		else {
			int positionForAdd = this.size()+1;
			this.setSize(positionForAdd);
			while((positionForAdd>1) &&
						(anElement.compareTo(this.heap()[positionForAdd/2])<0)) 
			{
				this.heap()[positionForAdd] = this.heap()[positionForAdd/2];
				positionForAdd = positionForAdd /2;
			}
			this.heap()[positionForAdd] = anElement;
			return true;
		}
	}
	public E min() {
		if(this.isEmpty()) {
			return null;
		}
		else {
			return this.heap()[MinPriorityQ.HEAP_ROOT];
		}
	}
		
	public E removeMin() {
		if(this.isEmpty()) {
			return null;
		}
		else {
			E rootElement = this.heap()[MinPriorityQ.HEAP_ROOT];
			this.setSize(this.size()-1);
			if(this.size() > 0) {
				//삭제 한 후에 적어도 하나의 원소가 남아있다. 
				//그러므로 마지막 위치 this.size()+1 의 원소를 떼어내어
				//root위치 this.heap()[1]로부터 아래쪽으로 새로운 위치를 찾아 내려간다. 
				E lastElement = this.heap()[this.size()+1];
				int parent = MinPriorityQ.HEAP_ROOT;
				while((parent *2)<= this.size()) {
					//child가 존재. left, right중에서 더 작은 key값을 갖는 child를 smallercChild로 한다.
					int smallerChild = parent *2;
					if(smallerChild <this.size() &&
							(this.heap()[smallerChild].compareTo(this.heap()[smallerChild+1])>0))
					{
						smallerChild++; //rightChild가 존재하고, 그 값이 작으므로, right child를 smallerChild로한다.
					}
					if(lastElement.compareTo(this.heap()[smallerChild]) <= 0) {
						break;
					}
					//child원소를 parent위치로 올려 보낸다. child위치는 새로운 parent 위치가 된다. 
					this.heap()[parent] = this.heap()[smallerChild];
					parent = smallerChild;
				} //end while
				this.heap()[parent] = lastElement;
				
			}
			return rootElement;
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
