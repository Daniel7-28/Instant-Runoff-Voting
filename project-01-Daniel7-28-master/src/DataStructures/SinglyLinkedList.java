package DataStructures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SinglyLinkedList <E> implements List<E>{

	private class SinglyLinkedListIterator<E> implements Iterator<E>{

		private Node<E> nextNode;
		
		@SuppressWarnings("unchecked")
		public SinglyLinkedListIterator() {
			this.nextNode = (Node<E>) header.getNext();		}
		@Override
		public boolean hasNext() {
			return (nextNode != null);
		}

		@Override
		public E next() {
			if(this.hasNext()) {
				E result = this.nextNode.getElement();
				this.nextNode = this.nextNode.getNext();
				return result;
			}
			else
				throw new NoSuchElementException();
		}
		
	}
	@SuppressWarnings("hiding")
	private class Node<E>{
		private Node<E> next;
		private E element;

		Node(Node<E> next, E elm){
			this.next = next;
			this.element = elm;
		}

		public Node(E elm) {
			this(null,elm);
		}

		public Node() {
			this(null, null);
		}

		public Node<E> getNext(){
			return next;
		}

		public void setNext(Node<E> next) {
			this.next = next;
		}

		public E getElement() {
			return element;
		}

		public void setElement(E elm) {
			this.element = elm;
		}

		public void clear() {
			this.element = null;
			this.next = null;
		}
	}

	private Node<E> header;
	private int currentSize;

	public SinglyLinkedList() {
		header = new Node<>();
		currentSize = 0;
	}
	@Override
	public Iterator<E> iterator() {
		return new SinglyLinkedListIterator<E>();
	}

	@Override
	public void add(E elm) {
		Node<E> currentNode, newNode;
		for(currentNode = header.getNext(); currentNode.getNext() != null; currentNode = currentNode.getNext()) {
			newNode = new Node<>(elm);
			currentNode.setNext(newNode);
			currentSize++;
		}
	}

	@Override
	public void add(int index, E elm) {
		Node<E> currentNode, newNode;
		if(index < 0 || index > size())
			throw new IndexOutOfBoundsException();
		else {
			currentNode = getNode(index-1);
			newNode = new Node<>(currentNode.getNext(),elm);
			currentNode.setNext(newNode);
			currentSize++;
		}
	}

	private Node<E> getNode(int index){
		Node<E> currentNode = header;
		if(index < 0 || index > size())
			throw new IndexOutOfBoundsException();
		for(int i = 0; i < index; i++)
			currentNode = currentNode.getNext();
		return currentNode;
	}

	@Override
	public boolean remove(E elm) {
		int i = firstIndexOf(elm);
		if(i < 0) {
			return false;
		}
		else {
			remove(i);
			return true;
		}
	}

	@Override
	public boolean remove(int index) {
		if(index < 0 || index > currentSize) 
			throw new IndexOutOfBoundsException();
		else {
			Node <E> temp = this.header;
			int currentPosition = 0;
			Node<E> target = null;
			while(currentPosition != index) {
				temp = temp.getNext();
				currentPosition++;
			}
		
			target = temp.getNext();
			temp.setNext(target.getNext());
			target.setElement(null);
			target.setNext(null);;
			this.currentSize--;
			return true;
		}
	}

	@Override
	public int removeAll(E elm) {
		int count = 0;
		while(remove(elm)) {
			count ++;
		}
		return count;
	}

	@Override
	public void clear() {
		while(!this.isEmpty()) {
			remove(0);
		}

	}

	@Override
	public boolean contains(E elm) {
		if(firstIndexOf(elm) >= 0) {
			return true;
		}
		return false;
	}

	@Override
	public int size() {
		
		return currentSize;
	}

	@Override
	public E first() {
		
		return header.getNext().getElement();
	}

	@Override
	public E last() {
		Node<E> current= header.getNext();
		while(current != null) {
			if(current.getNext() == null)
				break;
			current = current.getNext();
		}
		return current.getElement();
	}

	@Override
	public E get(int index) {
		if ((index < 0) || (index >= this.currentSize)){
			throw new IndexOutOfBoundsException();
		}
		
		Node<E> current = this.getPosition(index);
		return current.getElement();
	}

	@Override
	public void set(int index, E elm) {
		if(index < 0 || index > currentSize)
			throw new IndexOutOfBoundsException();
		
		Node<E> current = getPosition(index);
		current.setElement(elm);
	}
	
	public Node<E> getPosition(int index){
		int currentPos = 0;
		Node<E>current = header.getNext();
		
		while(currentPos < index) {
			current = current.getNext();
			currentPos++;
		}
		return current;
	}

	@Override
	public int firstIndexOf(E elm) {
		int i = 0;
		for(Node<E> current = header.getNext(); current != null; current = current.getNext(), ++i) {
			if(current.getElement().equals(elm)) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public int lastIndexOf(E elm) {
		int i = 0, result = -1;
		for(Node<E> current = header.getNext(); current != null; current = current.getNext(), ++i) {
			if(current.getElement().equals(elm)) {
				result = i;
			}
		}
		return result;
	}

	@Override
	public boolean isEmpty() {
		return (currentSize == 0);
	}
	@Override
	public void add(E[] arr) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public E[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

}