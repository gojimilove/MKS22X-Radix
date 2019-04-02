class Node<E> {
	private E data;
	private Node<E> next,prev;

	public Node(E x) {
		data = x;
		next = null;
		prev = null;
	}
	public Node<E> next() {
		return next;
	}
	public Node<E> prev() {
		return prev;
	}
	public void setNext(Node<E> other) {
		next = other;
	}
	public void setPrev(Node<E> other) {
		prev = other;
	}
	public E getData() {
		return data;
	}
	public E setData(E i) {
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
	private Node<E> start,end;

	public MyLinkedList() {
		//empty list
		length = 0;
	}

	public int size() {
		return length;
	}

	public void clear() {
		length = 0;
		start = null;
		end = null;
	}

	public Node<E> getNode(int index) {
		if (index < 0 || index >= length) {
			throw new IndexOutOfBoundsException();
		}
		Node<E> current = start;
		//go through linked list until either you reach the end or you reach the index, then return that node
		while(current.getData() != null && index > 0) {
			current = current.next();
			index--;
		}
		return current;
	}

	public boolean add(E value) {
		//System.out.println("Length before: "+length);
		if (length == 0) { //list is empty, make a new node start and end both point to
			start = new Node<E>(value);
			end = start;
		}
		else { //start and end both point to something
			Node<E> n = new Node<E>(value);
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
			Node<E> n = new Node<E>(value);
			start.setPrev(n);
			n.setNext(start);
			start = n;
		}
		else {
			Node<E> before = getNode(index - 1);
			Node<E> after = getNode(index);
			Node<E> n = new Node<E>(value);
			before.setNext(n);
			n.setPrev(before);
			//node at index now points to node previously at index
			n.setNext(after);
			after.setPrev(n);
		}
		//size increases
		length++;
	}

	public E removeFront() {
		if (length <= 0) {
			throw new IndexOutOfBoundsException("the linked list is empty");
		}
		Node<E> n = getNode(0);
		E x = n.getData();
		start = getNode(1);
		getNode(1).setPrev(null);
		length--;
		return x;
	}

	public void extend(MyLinkedList<E> other) {
		if (other.size() == 0) return;
		else if (this.size() == 0) {
			this.length = other.size();
			this.start = other.start;
			this.end = other.end;
		}
		else {
			this.length = this.size() + other.size();
			this.end.setNext(other.start);
			other.start.setPrev(this.end);
			this.end = other.end;
		}
		other.start = null;
		other.end = null;
		other.length = 0;
	}

	public String toString() {
		String result = "[";
		Node<E> current = start;
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