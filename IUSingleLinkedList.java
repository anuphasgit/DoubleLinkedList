import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * This class represents a singly linked list that implements IndexUnsortedList interface.
 * It provides various operations like adding and removing elements in the list.
 * 
 * @author Anup Bhattarai
 */

public class IUSingleLinkedList<T> implements IndexedUnsortedList<T>{

	private Node<T> head, tail;
	private int size;
	private int modCount;

	/**
	 * Constructs an empty Single Linked List
	 */
	public IUSingleLinkedList(){
		head = tail = null;
		size = 0;
	}

	 /**
     * Adds an element to the front of the list.
     *
     * @param element the element to be added to the front of the list
     */
	@Override
	public void addToFront(T element) {
		Node<T> newNode = new Node<T>(element);
		newNode.setNext(head);
		head = newNode;
		size++;
		if(tail == null){
			tail = newNode;
		}
		modCount++;
	}

	/**
	 *  Adds an element to the rear of the list.
	 * 
	 * @param element the element to be added to the rear of the list
	 */
	@Override
	public void addToRear(T element) {
		Node<T> newNode = new Node<T> (element);
		if(isEmpty()){
			head = newNode;
		}else{
			tail.setNext(newNode);
		}
		tail = newNode;
		size++;
		modCount++;
	}

	/**
	 * Adds an element to the rear of the list
	 * 
	 * @param element the element to be added to the rear of the list
	 */
	@Override
	public void add(T element) {
		addToRear(element);
	}

	/**
     * Adds an element after a specified target element.
     *
     * @param element the element to be added to the list
     * @param target  the element after which the new element should be added
     * @throws NoSuchElementException if the target element is not found in the list
     */
	@Override
	public void addAfter(T element, T target) {
		Node<T> targetNode = head;
		boolean foundIt = false;
		while (targetNode!=null && !foundIt){
			if(targetNode.getElement().equals(target)){
				foundIt=true;
			}else {
				targetNode=targetNode.getNext();
			}
		}
		if(!foundIt){
			throw new NoSuchElementException();
		}
		Node<T> newNode = new Node<T>(element);
		newNode.setNext(targetNode.getNext());
		targetNode.setNext(newNode);
		if(targetNode==tail){
			tail = newNode;
		}
		size++;
	}

	/**
     * Adds an element at a specified index in the list.
     *
     * @param index   the index at which the element should be added
     * @param element the element to be added to the list
     * @throws NoSuchElementException if the index is out of bounds
     */
	@Override
	public void add(int index, T element) {
		if(index < 0 || index >= size){
			throw new NoSuchElementException();
		}
		if(index == 0){
			addToFront(element);
		}else if(index == size){
			addToRear(element);
		}else{
			Node<T> newNode = new Node<T>(element);
			Node<T> current = head;
			for(int i = 0; i < index-1; i++){
				current = current.getNext();
			}
			newNode.setNext(current.getNext());
			current.setNext(newNode);
			size++;
			modCount++;
		}
	}

	/**
	 * Removes and returns the first element in the list.
	 *
	 * @return the removed element from the front of the list
	 * @throws NoSuchElementException if the list is empty
	 */
	@Override
	public T removeFirst() {
		if(isEmpty()){
			throw new NoSuchElementException();
		}
		T retVal = head.getElement();
		head = head.getNext();
		if(head == null){
			tail = null;
		}
		size--;
		modCount++;
		return retVal;
	}

	/**
	 * Removes and returns the last element in the list.
	 *
	 * @return the removed element from the rear of the list
	 * @throws NoSuchElementException if the list is empty
	 */
	@Override
	public T removeLast() {
		if(isEmpty()){
			throw new NoSuchElementException();
		}
		if(size==1){
			return removeFirst();
		}else{
			Node<T> current = head;
			while(current.getNext() != tail){
				current = current.getNext();
			}
			T removedElement = tail.getElement();
			tail = current;
			tail.setNext(null);
			size--;
			modCount++;
			return removedElement;
		}
	}

	/**
	 * Removes the first occurrence of a specified element from the list.
	 *
	 * @param element the element to be removed from the list
	 * @return the removed element
	 * @throws NoSuchElementException if the list is empty or the element is not found
	 */
	@Override
	public T remove(T element) {
		if(isEmpty()){
			throw new NoSuchElementException();
		}
		T retVal;
		if(head.getElement().equals(element)){
			retVal =  head.getElement();
			head = head.getNext();
			if(head == null){
				tail =  null;
			}
		}else {
			Node<T> preNode = head;
			boolean foundIt = false;
			while(preNode.getNext()!=null && !foundIt){
				if(preNode.getNext().getElement().equals(element)){
					foundIt = true;
				}else{
					preNode= preNode.getNext();
				}
			}
		if(!foundIt){
			throw new NoSuchElementException();
		}
		retVal = preNode.getNext().getElement();
		preNode.setNext(preNode.getNext().getNext());
		if(preNode.getNext()==null){
			tail = preNode;
		}
		}
		size--;
		modCount++;
		return retVal;
	}

	/**
	 * Removes and returns the element at the specified index in the list.
	 *
	 * @param index the index of the element to be removed
	 * @return the removed element
	 * @throws IndexOutOfBoundsException if the index is out of bounds
	 */
	@Override
	public T remove(int index) {
		if(index < 0 || index >= size){
			throw new IndexOutOfBoundsException();
		}
		if(index == 0){
			return removeFirst();
		}else if(index == size-1){
			return removeLast();
		}else{
			Node<T> current = head;
			for(int i = 0; i < index - 1; i++){
				current = current.getNext();
			}
			T removedElement = current.getNext().getElement();
			current.setNext(null);
			size--;
			modCount++;
			return removedElement;
		}
	}

	/**
	 * Sets the element at the specified index to a new element.
	 *
	 * @param index   the index of the element to be updated
	 * @param element the new element to set
	 * @throws IndexOutOfBoundsException if the index is out of bounds
	 */
	@Override
	public void set(int index, T element) {
		if(index<0 || index>=size){
			throw new IndexOutOfBoundsException();
		}
		Node<T> current = head;
		for(int i = 0 ; i<index ; i++){
			current = current.getNext();
		}
		current.setElement(element);
	}

	/**
	 * Returns the element at the specified index in the list.
	 *
	 * @param index the index of the element to retrieve
	 * @return the element at the specified index
	 * @throws IndexOutOfBoundsException if the index is out of bounds
	 */
	@Override
	public T get(int index) {
		if(index<0 || index > size){
			throw new IndexOutOfBoundsException();
		}
		Node<T> current = head;
		for(int i = 0; i < index; i++){
			current = current.getNext();
		}
		return current.getElement();
	}

	/**
	 * Returns the index of the first occurrence of a specified element in the list.
	 *
	 * @param element the element to search for
	 * @return the index of the first occurrence of the element, or -1 if not found
	 */
	@Override
	public int indexOf(T element) {
		int index = -1;

		Node<T> current = head;
		int currentIndex = 0;
		while (current != null && index<0){
			if(current.getElement().equals(element)){
				index = currentIndex;
			}
			currentIndex++;
			current = current.getNext();
		}
		return index;
	}

	/**
	 * Returns the first element in the list.
	 *
	 * @return the first element in the list
	 * @throws NoSuchElementException if the list is empty
	 */
	@Override
	public T first() {
		if(isEmpty()){
			throw new NoSuchElementException();
		}
		return head.getElement();
	}

	/**
	 * Returns the last element in the list.
	 *
	 * @return the last element in the list
	 * @throws NoSuchElementException if the list is empty
	 */
	@Override
	public T last() {
		if(isEmpty()){
			throw new NoSuchElementException();
		}
		return tail.getElement();

	}

	/**
	 * Checks if the list contains a specified element.
	 *
	 * @param target the element to check for in the list
	 * @return true if the element is found in the list, false otherwise
	 */
	@Override
	public boolean contains(T target) {
		return indexOf(target)>-1;
	}

	/**
	 * Checks if the list is empty.
	 *
	 * @return true if the list is empty, false otherwise
	 */
	@Override
	public boolean isEmpty() {
		return size==0;
	}

	/**
	 * Returns the current size of the list.
	 *
	 * @return the number of elements in the list
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Returns an iterator for the list.
	 *
	 * @return an iterator for traversing the elements in the list
	 */
	@Override
	public Iterator<T> iterator() {
		return new SLLIterator();
	}

	/**
	 * Returns a list iterator for the list.
	 *
	 * @return a list iterator (not implemented)
	 * @throws UnsupportedOperationException when called since list iterators are not implemented
	 */
	@Override
	public ListIterator<T> listIterator() {
		throw new UnsupportedOperationException("Unimplemented method 'listIterator'");
	}

	/**
	 * Returns a list iterator for the list starting at a specified index.
	 *
	 * @param startingIndex the index at which the list iterator should start
	 * @return a list iterator (not implemented)
	 * @throws UnsupportedOperationException when called since list iterators are not implemented
	 */
	@Override
	public ListIterator<T> listIterator(int startingIndex) {
		throw new UnsupportedOperationException("Unimplemented method 'listIterator'");
	}

	/**  
	 * Iterator for IUSingleLinkedList
	*/
	private class SLLIterator implements Iterator<T>{
		private Node<T> nextNode;
		private int iterModCount;
		private boolean canRemove;

		public SLLIterator(){
			nextNode = head;
			iterModCount = modCount;
		}

		@Override
		public boolean hasNext() {
			if(iterModCount != modCount){
				throw new ConcurrentModificationException();
			}
		return nextNode !=null;
		}

		@Override
		public T next() {
			if(!hasNext()){
				throw new NoSuchElementException();
			}
			T retVal = nextNode.getElement();
			nextNode = nextNode.getNext();
			canRemove = true;
			return retVal;
		}

		public void remove(){
			if(iterModCount != modCount){
				throw new ConcurrentModificationException();
			}
			if(!canRemove){
				throw new IllegalStateException();
			}
			canRemove = false;
			if(head.getNext()==nextNode){//removing head node 
				head = head.getNext();//or head = next node
				if(head==null){//removed only node
					tail = null;
				}
			}else{
				// find prev prev node
				Node<T> prevPrevNode = head;
				while(prevPrevNode.getNext().getNext()!= next()){
					prevPrevNode =prevPrevNode.getNext();
				}
				prevPrevNode.setNext(nextNode);
				if(prevPrevNode.getNext()==null){
					tail = prevPrevNode;
				}
			}
			modCount++;
			iterModCount++;
			size--;
		} 
	}
}