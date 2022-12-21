package graph;

public class PairwiseDisjointSets {
	//Constants
	private static final int INITIAL_SINGLETON_SET_SIZE = 1;
	
	//private instance variables
	private int _numberOfElements;		//원소의 개수
	private int _parentTree[];        //부모를 가리키는 정보를 저장하는 배열
	
	//생성자
	//원소의 개수가 주어진다. 주어진 원소만큼 singleton set을 만든다.
	public PairwiseDisjointSets(int givenNumberOfElements) {
		//this._numberOfElements = givenNumberOfElements;
		//this._parentTree = new int[givenNumberOfElements];
		this.setNumberOfElements(givenNumberOfElements);
		this.setParentTree(new int[this.numberOfElements()]);
		
		//parent[] 배열 초기화 // 만들어진 parent가 singleton set이라서
		for (int rootOfSingletonSet = 0; rootOfSingletonSet<this.numberOfElements(); rootOfSingletonSet++) {
			this.setSizeOfSetFor(rootOfSingletonSet, INITIAL_SINGLETON_SET_SIZE);
		}
	}
	
	//Private getter //원소 개수 
	private int numberOfElements() {
		return this._numberOfElements;
	}
	//private setter
	private void setNumberOfElements(int numberOfElements) {
		this._numberOfElements = numberOfElements;
	}
	//private getter
	private int[] parentTree() {
		return this._parentTree;
	}
	//private setter
	private void setParentTree(int[] newParentTree) {
		this._parentTree = newParentTree;
	}
	
	//추상화

	//getter(getter parentTree이용)  //parent 얻기
	private int parentOf(int aMember){
		return this.parentTree()[aMember];
	}
	//setter(getter parentTree이용)   //parent 설정 : child, parent받아서 설정해주는 함수 
	private void setParentOf(int aChildMember, int newParentMember) {
		this.parentTree()[aChildMember] = newParentMember;
	}
	
	//존재 여부 
	private boolean parentDoesExist(int aMember) {
		return (this.parentOf(aMember) >= 0);	//추상화하여 사용  //parentOf함수 사용하여 부모를 얻어옴
	}
	//getter ==> root 집합 size얻는 함수  . 즉 root인덱스의 값 
	private int sizeOfSetFor(int aRoot) {
		return -this.parentOf(aRoot);			//집합의 원소 개수를 음수로 저장해놔서 -  
	}
	//주어진 root가 속한 집합의 크기(원소 개수) 설정하기  setter
	private void setSizeOfSetFor(int aRoot, int newSize) {
		this.setParentOf(aRoot, -newSize);		//집합(root)의 크기는 음수로 하기로 정했음
	}	 					//singleton 만들 때는 newsize 1로 넘어옴
	
	public int find (int aMember) {
		int rootCandidate = aMember;
		while(this.parentDoesExist(rootCandidate)) {	//부모가 있을 때 까지 반복
			rootCandidate = this.parentOf(rootCandidate);	//다음 부모 가져옴 
		}
		//반복문 빠져나오면 root 알게 
		int root = rootCandidate;
		
		//Apply <Collapsing Rule>
		int child = aMember;
		int parent = this.parentOf(child);
		if (parent >= 0) {		//값이 0보다 큰 경우 root가 아님 children Node임 
			while(parent != root) {
				this.setParentOf(child, root);		//No Capsultation >> this._parentTree[child] = root;
				child = parent;
				parent = this.parentOf(child);      //No Capsultaion >> this._parentTree[child];
			}
		}
		return root;
	}
	
	//memberA와 memberB는 집합을 나타냄.   A와 B가 같은 집합에 속해 있으면 집합의 변화는 없도록 한다.
	public void union(int memberA, int memberB) {
		//the root index of the set which aMamber belongs to 
		int rootOfSetA = this.find(memberA);
		int rootOfSetB = this.find(memberB);
		
		//each root인덱스 값 //root인덱스 값은 children개수를 가지고 있음  
		int sizeOfSetA = this.sizeOfSetFor(rootOfSetA);//No Capsultaion >> this._parentTree[rootOfSetsA]; //parentTree배열 안의 root인덱스 값은 음수이고 root집합의 원소 개수를 가지고 있다.
		int sizeOfSetB = this.sizeOfSetFor(rootOfSetB); //B의 root를 가진 rootOfSetsB의 size를 얻어주는 getter인 sizeOfSetFor 함수를 호출한다.
		
		//Apply <Weighting Rule>
		if(sizeOfSetA <sizeOfSetB) { //root == B
			this.setParentOf(rootOfSetA, rootOfSetB);		//매개변수: child, parent
			this.setSizeOfSetFor(rootOfSetB, sizeOfSetA+sizeOfSetB);         //매개변수 : root, size
		}
		else { //root == A
			this.setParentOf(rootOfSetB, rootOfSetA);	//매개변수: child, parent
			this.setSizeOfSetFor(rootOfSetA, sizeOfSetA+sizeOfSetB);		  //매개변수 : root, size
		}
	}
	
	
}
