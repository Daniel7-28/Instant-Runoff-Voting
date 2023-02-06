package DataStructures;

import java.util.Iterator;

/**
 * ArrayList class
 * 
 * @author: Juan D. Pérez Sepúlveda
 * @version: 2.0
 * @since 2022-02-22
 */

public class ArrayList <E> implements List<E>{
	private E[] elements;
	private int currentSize;
	
	private final static int DEFAULT_SIZE = 20;
	
	@SuppressWarnings("unchecked")
	public ArrayList(int initialCapacity) {
		if(initialCapacity < 1)
			throw new IllegalArgumentException("Size must be at least 1");
		currentSize = 0;
		elements = (E[]) new Object[initialCapacity];
	}
	
	public ArrayList() {
		this(DEFAULT_SIZE);
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(E elm) {
		this.add(currentSize, elm);
	}
	
	private void reAllocate() {
		@SuppressWarnings("unchecked")
		E[] newElements = (E[]) new Object[currentSize*2];
		
		for (int i = 0; i < currentSize; i++) {
			newElements[i] = elements[i];
		}
		elements = newElements;
	}
	@Override
	public void add(E[] arr) {
		for(int i=0; i<arr.length; ++i)
			this.add(arr[i]);
		
	}
	@Override
	public void add(int index, E elm) {
		if(index < 0 || index > currentSize)
			throw new IndexOutOfBoundsException("Index must be between 0 and size -1");
		
		if(currentSize == elements.length)
			reAllocate();
		if(index == currentSize)
			elements[currentSize] = elm;
		
		else {
			for(int i = currentSize; i > index;i++) {
				elements[i] = elements[i-1];
			}
			elements[index] = elm;
		}
		currentSize++;	
	}

	@Override
	public boolean remove(E elm) {
		int pos = -1;
		for (int i = 0; i < currentSize; i++) {
			if(elements[i].equals(elm)) {
				pos = i;
				break;
			}
		}
		if(pos >= 0) {
			for(int i = pos; i < currentSize - 1; i++) {
				elements[i] = elements[i+1];
			}
			currentSize--;
			elements[currentSize] = null;
			return true;
		}
		return false;
	}

	@Override
	public boolean remove(int index) {
		if(index < 0 || index >= currentSize)
			throw new IndexOutOfBoundsException("Index must be between 0 and size -1");
	
		for(int i = index; i < currentSize - 1; i++) {
			elements[i] = elements[i+1];
		}
		elements[currentSize-1] = null;
		currentSize--;
		return true;
	}

	@Override
	public int removeAll(E elm) {
		int count = 0;
			while(remove(elm)) {
				count++;
			}
		return count;
	}

	@Override
	public void clear() {
		for (int i = 0; i < currentSize; i++) {
			elements[i] = null;
		}
		currentSize = 0;
	}

	@Override
	public boolean contains(E elm) {
		for(int i = 0; i < currentSize;i++) {
			if(elements[i].equals(elm))
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
		return elements[0];
	}

	@Override
	public E last() {
		if(isEmpty()) 
			return null;
		return elements[currentSize-1];
	}

	@Override
	public E get(int index) {
		if(index < 0 || index >= currentSize)
			throw new IndexOutOfBoundsException("Index must be between 0 and size -1");
	
		return elements[index];
	}

	@Override
	public void set(int index, E elm) {
		if(index < 0 || index >= currentSize)
			throw new IndexOutOfBoundsException("Index must be between 0 and size -1");
		
		elements[index] = elm;
	}

	@Override
	public int firstIndexOf(E elm) {
		for (int i = 0; i < currentSize; i++) {
			if(elements[i].equals(elm))
				return i;
		}
		return -1;
	}

	@Override
	public int lastIndexOf(E elm) {
		for (int i = currentSize-1; i > 0; i--) {
			if(elements[i].equals(elm))
				return i;
		}
		return -1;
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E[] toArray() {
		E[] result = (E[])new Object[currentSize];
		for(int i=0; i<currentSize; i++) {
			result[i] = elements[i];
		}
		return result;
	}

	
	
	
	
}
