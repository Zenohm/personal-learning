import java.util.EmptyStackException;


public class LinkedStack<E> implements Cloneable {
	// Invariant of the LinkedStack class:
	// 	1. The items in the stack are stored in a linked list, with the top of the stack stored
	// 	   at the head node, down to the bottom of the stack at the final node.
	// 	2. The instance variable top is the head reference of the linked list of items.
	private Node<E> top;
    
    /**
     * Initializes an empty stack.
     */
	public LinkedStack() {
		top = null;
	}

    /**
     * Returns a reference to the had of a clone of the stack.
     */
	public LinkedStack<E> clone() {
		// Clone a LinkedStack
		LinkedStack<E> answer;
		
		try {
			answer = (LinkedStack<E>) super.clone();
		} catch(CloneNotSupportedException e) {
			// This exception should not occur. But if it does, it would probably indicate a
			// programming error that made super.clone unavailable. The most common error
			// is forgetting the "implements Cloneable" clause at the start of this class.
			throw new RuntimeException
			("This class does not implement Cloneable.");
		}

		answer.top = Node.listCopy(top); // Generic listCopy method
		return answer;
	}

    /**
     * Is this stack empty?
     * @return true if this stack is empty; false otherwise
     */
	public boolean isEmpty() {
		return top == null;
	}

    /**
     * Returns (but does not remove) the item most recently added to this stack.
     * @return the item most recently added to this stack
     * @throws java.util.NoSuchElementException if this stack is empty
     */
	public E peek() {
		if(top == null)
			// EmptyStackException is from java.util, and its constructor has no argument.
			throw new EmptyStackException();
		return top.getData();
	}

    /**
     * Removes and returns the item most recently added to this stack.
     * @return the item most recently added
     * @throws java.util.NoSuchElementException if this stack is empty
     */
    public E pop() {
		E answer;

		if(top == null)
			// EmptyStackException is from java.util, and its constructor has no argument.
			throw new EmptyStackException();
		
		answer = top.getData();
		top = top.getNext();
		return answer;
	}

	/**
     * Adds the item to this stack.
     * @param item the item to add
     */
    public void push(E item) {
		top = new Node<E>(item, top);
	}

    /**
     * Returns the number of items in the stack.
     * @return the number of items in the stack
     */
    public int size() {
		return Node.listLength(top); // Generic listLength method
	}

    /**
     * Returns a string representation of this stack.
     * @return the sequence of items in the stack in LIFO order, separated by spaces
     */
	public String toString() {
		return top.toString();
	}
}
