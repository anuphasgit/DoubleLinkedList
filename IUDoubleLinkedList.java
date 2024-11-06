import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * This class represents a doubly linked list that implements IndexUnsortedList interface.
 * It provides various operations like adding and removing elements in the list.
 * 
 * @author cs221 and anup 
 */
public class IUDoubleLinkedList<T> implements IndexedUnsortedList<T>{
    private Node<T> head, tail;
    private int size;
    private int modCount;

    /**
     * Constructs an empty doubly linked list
     */
    public IUDoubleLinkedList(){
        head = tail = null;
        size = 0;
        modCount = 0;
    }

    /**Adds an elemment to the front of the list.
     * 
     * @param element the element to add
     */
    @Override
    public void addToFront(T element) {
        Node<T> newNode = new Node<> (element);
        if(isEmpty()){
            head = tail = newNode;
        }else{
            newNode.setNextNode(head);
            head.setPreviousNode(newNode);
            head = newNode;
        }
        size++;
        modCount++;
    }

    /**
     * Adds an element to the rear (end) of the list.
     *
     * @param element the element to add
     */
    @Override
    public void addToRear(T element) {
        Node<T> newNode = new Node<> (element);
        if(isEmpty()){
            head = tail = newNode;
        }else{
            newNode.setPreviousNode(tail);
            tail.setNextNode(newNode);
            tail = newNode;
        }
        size++;
        modCount++;
    }

    /**
     * Adds an element to the rear (end) of the list.
     *
     * @param element the element to add
     */
    @Override
    public void add(T element) {
        addToRear(element);
    }

    /**
     * Adds an element after the specified target element in the list.
     *
     * @param element the element to add
     * @param target  the element after which to add the new element
     */
    @Override
    public void addAfter(T element, T target) {
        Node<T> current = head;

        while(current != null && !current.getElement().equals(target)){
            current = current.getNextNode();
        }

        if(current == null){
            throw new NoSuchElementException();
        }

        Node<T> newNode = new Node<> (element);
        newNode.setPreviousNode(current);
        newNode.setNextNode(current.getNextNode());
        current.setNextNode(newNode);

        if(newNode.getNextNode() != null){
            newNode.getNextNode().setPreviousNode(newNode);
        }else{
            tail = newNode;
        }

        size++;
        modCount++;
    }

    /**
     * Adds an element at the specified index in the list.
     *
     * @param index   the index at which to add the element
     * @param element the element to add
     */
    @Override
    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        ListIterator<T> lit = listIterator(index);
        lit.add(element);
        // if(index < 0 || index > size){
        //     throw new IndexOutOfBoundsException();
        // }
        // Node<T> newNode = new Node<T> (element);
        // if(index==0){
        //     newNode.setNextNode(head);
        //     if(isEmpty()){
        //         tail = newNode;
        //     }else{
        //         head.setPreviousNode(newNode);
        //     }
        //     head = newNode;
        // }else{
        //     Node<T> prevNode = head;
        //     for(int i = 0 ; i < index-1 ; i++){
        //         prevNode = prevNode.getNextNode();
        //     }
        //     // Node<T> newNode = new Node<T> (element);
        //     newNode.setPreviousNode(prevNode);
        //     newNode.setNextNode(prevNode.getNextNode());
        //     prevNode.setNextNode(newNode);
        //     if(index == size){
        //         tail = newNode;
        //     }else{
        //         newNode.getNextNode().setPreviousNode(newNode);
        //     }
        //     size++;
        //     modCount++;
        // }
    }

    /**
     * Removes and returns the first element in the list.
     *
     * @return the first element in the list
     * @throws NoSuchElementException if the list is empty
     */
    @Override
    public T removeFirst() {
        if(isEmpty()){
            throw new NoSuchElementException();
        }

        T removedElement = head.getElement();
        head = head.getNextNode();

        if(head != null){
            head.setPreviousNode(null);
        }else{
            tail = null;
        }
        size--;
        modCount++;
        return removedElement;
    }

    /**
     * Removes and returns the last element in the list.
     *
     * @return the last element in the list
     * @throws NoSuchElementException if the list is empty
     */
    @Override
    public T removeLast() {
        if(isEmpty()){
            throw new NoSuchElementException();
        }

        T removedElement = tail.getElement();
        tail = tail.getPreviousNode();

        if(tail != null){
            tail.setNextNode(null);
        }else{
            head = null;
        }
        size--;
        modCount++;
        return removedElement;
    }

    /**
     * Removes the specified element from the list.
     *
     * @param element the element to remove
     * @return the removed element
     * @throws NoSuchElementException if the element is not found in the list
     */
    @Override
    public T remove(T element) {
        Node<T> current = head;

        while (current != null && !current.getElement().equals(element)){
            current = current.getNextNode();
        }

        if(current == null){
            throw new NoSuchElementException();
        }

        if(current == head){
            return removeFirst();
        }else if(current == tail){
            return removeLast();
        }else{
            current.getPreviousNode().setNextNode(current.getNextNode());
            current.getNextNode().setPreviousNode(current.getPreviousNode());
            size--;
            modCount++;
            return current.getElement();
        }
    }

    /**
     * Removes and returns the element at the specified index.
     *
     * @param index the index of the element to remove
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
        }else if(index == size - 1){
            return removeLast();
        }else{
            Node<T> current = head;
            for( int i = 0 ; i < index ; i++){
                current = current.getNextNode();
            }
            current.getPreviousNode().setNextNode(current.getNextNode());
            current.getNextNode().setPreviousNode(current.getPreviousNode());
            size--;
            modCount++;
            return current.getElement();
        }
    }
    
    /**
     * Sets the element at the specified index to the given value.
     *
     * @param index   the index of the element to set
     * @param element the new value for the element
     * @throws IndexOutOfBoundsException if the index is out of bounds
     */
    @Override
    public void set(int index, T element) {
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }

        Node<T> current = head;
        for(int i = 0; i < index ; i++){
            current = current.getNextNode();
        }
        current.setElement(element);
        modCount++;
    }

    /**
     * returns the element at the specified index.
     *
     * @param index the index of the element to retrieve
     * @return the element at the specified index
     * @throws IndexOutOfBoundsException if the index is out of bounds
     */
    @Override
    public T get(int index) {
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        } 

        Node<T> current = head;
        for (int i = 0; i < index; i++){
            current = current.getNextNode();
        }
        return current.getElement();
    }

    /**
     * Returns the index of the specified element in the list.
     *
     * @param element the element to search for
     * @return the index of the element, or -1 if not found
     */
    @Override
    public int indexOf(T element) {
        int index = -1;

        Node<T> current = head;
        int currentIndex = 0;
        while(current != null && index<0){
            if(current.getElement().equals(element)){
                index = currentIndex;
            }
            currentIndex++;
            current = current.getNextNode();
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
     * Checks if the list contains the specified target element.
     *
     * @param target the element to check for
     * @return true if the target element is in the list, false otherwise
     */
    @Override
    public boolean contains(T target) {
        return indexOf(target) != -1;
    }

    /**
     * Checks if the list is empty.
     *
     * @return true if the list is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of elements in the list.
     *
     * @return the number of elements in the list
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns a string representation of the elements in the dll.
     * 
     * @return a string representation of the elements
     */
    public String toString() {
        String result = "[";
        Node<T> current = head;
    
        while (current != null) {
            result += current.getElement();
            if (current.getNextNode() != null) {
                result += ", ";
            }
            current = current.getNextNode();
        }
    
        result += "]";
        return result;
    }

    /**
     * 
     * @return an iterator over the elments in the list
     */
    @Override
    public Iterator<T> iterator() {
        return new DLLIterator();
    }

    /**
     * 
     * @return a list iterator over the elements in the list
     */
    @Override
    public ListIterator<T> listIterator() {
        return new DLLIterator();

    }

    @Override
    public ListIterator<T> listIterator(int startingIndex) {
        return new DLLIterator(startingIndex);
    }

    /**
     * Iterator implementation for the double linked list
     * 
     */
    private class DLLIterator implements ListIterator<T> {
        private Node<T> nextNode;
        private int iterModCount;
        private int nextIndex;
        private Node<T> lastReturnedNode;
        
        /**
         * Default constructor for the listtester
         * 
         */
        public DLLIterator() {
            this(0);
        }
        
        /**
         * Constructs an itetator starting at the specified index in the doubly linked list.
         * 
         * @param startingIndex the starting index for the iterator
         * @throws IndexOutOfBoundsException if the starting index is out of bounds.
         */
        public DLLIterator(int startingIndex) {
            if (startingIndex < 0 || startingIndex > size) {
                throw new IndexOutOfBoundsException();
            }
            nextNode = head;
            for (int i = 0; i < startingIndex; i++) {
                nextNode = nextNode.getNextNode();
            }
            iterModCount = modCount;
            nextIndex = startingIndex;
            lastReturnedNode = null;
        }
        
        /**
         * 
         * @return true if there is an element after the current position, false otherwise
         * @throws ConcurrentModificationException if the list is modified 
         * 
         */
        @Override
        public boolean hasNext() {
            if (iterModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            return nextNode != null;
        }
        
        /**
         * @return tbe next element in the iterator
         * @throws NoSuchElementException if there is no next element
         */
        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T retVal = nextNode.getElement();
            lastReturnedNode = nextNode;
            nextNode = nextNode.getNextNode();
            nextIndex++;
            return retVal;
        }

        /**
         * @return true if there is an element before the current position, false otherwise
         * @throws ConcurrentModificationException if the list is modified
         */
        @Override
        public boolean hasPrevious() {
            if (iterModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            return nextIndex != 0;
        }
        
        /**
         * Returns the previous element in the IUDLL list iterator.
         * 
         * @return the previous element in the iterator
         * @throws NoSuchElementException if there is no previous element
         */
        @Override
        public T previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            if (nextNode != null) {
                nextNode = nextNode.getPreviousNode();
            } else {
                nextNode = tail;
            }
            nextIndex--;
            lastReturnedNode = nextNode;
            return lastReturnedNode.getElement();
        }
        
        /**
         * Returns the index of the next element in the iterator.
         * 
         * @return the index of the next element
         * @throws ConcurrentModificationException if the list is modified.
         */
        @Override
        public int nextIndex() {
            if (iterModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            return nextIndex;
        }
        
        /**
         * Returns the index of the previous element in the iterator.
         *
         * @return the index of the previous element
         * @throws ConcurrentModificationException if the list is modified.
         */
        @Override
        public int previousIndex() {
            if (iterModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            return nextIndex - 1;
        }
        
        /**
         * Removes an slement from the iterator 
         * 
         * @throws ConcurrentMOdificationException if the list is modified 
         * @throws IllegalStateException if the lastreturned element is null
         */
        @Override
        public void remove() {
            if (iterModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            if (lastReturnedNode == null) {
                throw new IllegalStateException();
            }
    
            if (lastReturnedNode == head) {
                head = lastReturnedNode.getNextNode();
            } else {
                lastReturnedNode.getPreviousNode().setNextNode(lastReturnedNode.getNextNode());
            }
    
            if (lastReturnedNode == tail) {
                tail = lastReturnedNode.getPreviousNode();
            } else {
                lastReturnedNode.getNextNode().setPreviousNode(lastReturnedNode.getPreviousNode());
            }
            if (lastReturnedNode != nextNode) {
                nextIndex--;
            } else {
                nextNode = nextNode.getNextNode();
            }
            size--;
            modCount++;
            iterModCount++;
            lastReturnedNode = null;
        }
        
        /**
         * Replaces a certain element in the list with the specidied element 
         * 
         * @throws ConcurrentModificationException if the list is modified
         * @throws IllegalStateException if the last returned element is null
         * 
         */
        @Override
        public void set(T e) {
            if (iterModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            if (lastReturnedNode == null) {
                throw new IllegalStateException();
            }
            Node<T> newNode = new Node<T>(e);
            newNode.setNextNode(lastReturnedNode.getNextNode());
            newNode.setPreviousNode(lastReturnedNode.getPreviousNode());
            if (lastReturnedNode != head) {
                lastReturnedNode.getPreviousNode().setNextNode(newNode);
            } else {
                head = newNode;
            }

            if (lastReturnedNode != tail) {
                lastReturnedNode.getNextNode().setPreviousNode(newNode);
            } else {
                tail = newNode;
            }

            if (lastReturnedNode == nextNode) {
                nextNode = nextNode.getNextNode();
            } else {
                nextIndex--;
            }

            iterModCount++;
            modCount++;

        }
        /**
         * Adds the specified element to the dll at the current position in the iterator.
         * 
         * @throws ConcurrentModificationException if the list is modified
         */
        @Override
        public void add(T e) {
            if (iterModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            Node<T> newNode = new Node<>(e);
            // general case
            if (isEmpty()) {
                head = tail = newNode;
            } else {
                if (nextNode == null) {
                    newNode.setPreviousNode(tail);
                    tail.setNextNode(newNode);
                    tail = newNode;
                } else {
                    newNode.setPreviousNode(nextNode.getPreviousNode());
                    newNode.setNextNode(nextNode);
                    nextNode.setPreviousNode(newNode);
                    if (newNode.getPreviousNode() != null) {
                        newNode.getPreviousNode().setNextNode(newNode);
                    } else {
                        head = newNode;
                    }
                }
            }
    
            nextIndex++;
            size++;
            iterModCount++;
            modCount++;
            
        }
    }
}
