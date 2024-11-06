/**
 * Node represents a node in a linked list.
 *
 * @author Java Foundations, mvail
 * @version 4.0
 */
public class Node<E> {
	private Node<E> next;
	private Node<E> previous;
	private E element;

	/**
  	 * Creates an empty node.
  	 */
	public Node() {
		next = null;
		previous = null;
		element = null;
	}

	/**
  	 * Creates a node storing the specified element.
 	 *
  	 * @param elem
  	 *            the element to be stored within the new node
  	 */
	public Node(E elem) {
		next = null;
		previous = null;
		element = elem;
	}

	/**
 	 * Returns the node that follows this one.
  	 *
  	 * @return the node that follows the current one
  	 */
	public Node<E> getNextNode() {
		return next;
	}

	/**
 	 * Sets the node that follows this one.
 	 *
 	 * @param node
 	 *            the node to be set to follow the current one
 	 */
	public void setNextNode(Node<E> node) {
		next = node;
	}

	/**
	 * Returns the node that preceds this one.
	 * 
	 * @return the node that preceds the currennt one
	 */
	public Node<E> getPreviousNode(){
		return previous;
	}

	/**
	 * Sets the node that preceds this one.
	 * 
	 * @param node the node to be set to preced the current one 
	 */
	public void setPreviousNode(Node<E> node){
		previous = node;
	}

	/**
 	 * Returns the element stored in this node.
 	 *
 	 * @return the element stored in this node
 	 */
	public E getElement() {
		return element;
	}

	/**
 	 * Sets the element stored in this node.
  	 *
  	 * @param elem
  	 *            the element to be stored in this node
  	 */
	public void setElement(E elem) {
		element = elem;
	}

	@Override
	public String toString() {
		return "Element: " + element.toString() + " Has next: " + (next != null) + " Has previous: " + (previous != null);
	}
}

