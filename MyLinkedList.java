class Node<E> {
	private E data;
	private Node next,prev;

	public Node(E x) {
		data = x;
		next = null;
		prev = null;
	}
	public Node next() {
		return next;
	}
	public Node prev() {
		return prev;
	}
	public void setNext(Node other) {
		next = other;
	}
	public void setPrev(Node other) {
		prev = other;
	}
	public Integer getData() {
		return data;
	}
	public Integer setData(E i) {
		data = i;
		return data;
	}
	public String toString() {
		String result = "";
		result+= getData();
		return result;
	}

}


public class MyLinkedList<E> {
	private int length;
	private Node start,end;

	public MyLinkedList() {
		//empty list
		length = 0;
	}

	public void clear() {
		length = 0;
	}

	public boolean add(E value) {
		//System.out.println("Length before: "+length);
		if (length == 0) { //list is empty, make a new node start and end both point to
			start = new Node(value);
			end = start;
		}
		else { //start and end both point to something
			Node n = new Node(value);
			//current last value now points to new node, vice versa
			end.setNext(n);
			n.setPrev(end);
			//new node is now the end
			end = n;
		}
		length++;
		return true;
	}

	public void add(int index, E value) {
		if (index < 0 || index > length) {
			throw new IndexOutOfBoundsException();
		}
		else if (index == length) add(value);
		//node at index before this one now points to this and vice versa
		else if (index == 0) {
			Node n = new Node(value);
			start.setPrev(n);
			n.setNext(start);
			start = n;
		}
		else {
			Node before = getNode(index - 1);
			Node after = getNode(index);
			Node n = new Node(value);
			before.setNext(n);
			n.setPrev(before);
			//node at index now points to node previously at index
			n.setNext(after);
			after.setPrev(n);
		}
		//size increases
		length++;
	}

	public int size() {
		return length;
	}

	private Node getNode(int index) {
		if (index < 0 || index >= length) {
			throw new IndexOutOfBoundsException();
		}
		Node current = start;
		//go through linked list until either you reach the end or you reach the index, then return that node
		while(current.getData() != null && index > 0) {
			current = current.next();
			index--;
		}
		return current;
	}

	public boolean contains(E value) {
		//go through list, if it's not in it return false
		Node current = start;
		while(current != null) {
			if (current.getData().equals(value)) {
				return true; //if value matches then return true
			}
			current = current.next();
		}
		return false;
	}

	public int indexOf(E value) {
		//return -1 if value does not exist
		if (!contains(value)) {
			return -1;
		}
		int i = 0;
		Node current = start;
		//loop through until you find the value, then stop and return the index
		while(current != null && !current.getData().equals(value)) {
			current = current.next();
			i++;
		}
		return i;
	}

	public E remove(int index) {
		Node n = getNode(index);
		E x = n.getData();
		//if index out of bounds throw exception
		if (index < 0 || index >= length) {
			throw new IndexOutOfBoundsException("index out of bounds");
		}
		//if last element make second to last element end
		else if (index == length - 1) {
			end = getNode(index - 1);
			getNode(index = 1).setNext(null);
		}
		//if first element make second element start
		else if (index == 0) {
			start = getNode(1);
			getNode(1).setPrev(null);
		}
		//find nodes before and after current, make them point to each other
		else {
			Node before = getNode(index - 1);
			Node after = getNode(index + 1);
			before.setNext(after);
			after.setPrev(before);
		}
		length--; //size decreases
		return x; //return value of removed node
	}

	public boolean remove(E value) {
		int i = indexOf(value); //index of current node
		//if index out of bounds throw exception
		if (i < 0 || i >= length) {
			throw new IndexOutOfBoundsException("index out of bounds");
		}
		//if last element make second to last element end
		else if (i == length - 1) {
			end = getNode(i - 1);
			getNode(i - 1).setNext(null);
		}
		//if first element make second element start
		else if (i == 0) {
			start = getNode(1);
			getNode(1).setPrev(null);
		}
		//else find nodes before and after, make them point to each other
		else {
			Node before = getNode(i - 1);
			Node after = getNode(i + 1);
			before.setNext(after);
			after.setNext(before);
		}
		length--; //size decreases
		return true;
	}

	public void extend(MyLinkedList<E> other) {
		this.length = this.size() + other.size();
		this.end.setNext(other.start);
		other.start.setPrev(this.end);
		this.end = other.end;
		other.start = null;
		other.end = null;
		other.length = 0;
	}

	public String toString() {
		String result = "[";
		Node current = start;
		while (current != null) {
			result += current.getData();
			if (current.next() != null) {
				result += ", ";
			}
			current = current.next();
		}
		result += "]";
		return result;
	}

}