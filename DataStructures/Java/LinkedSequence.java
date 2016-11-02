/* Name: Isaac Smith
 * Assignment: Project 1
 * Date: 10/8/2016
 * Class: Data Structures
 */

public class LinkedSequence <T> implements Cloneable {
	// Invariant of the LinkedSequence class:
	//   1. Each linked sequence has a reference to the head and tail
	//      of the linked sequence, as well as a cursor and precursor
	//		which can move freely back and forth across the sequence.
	//		There is also a dummy reference that always points to the
	//		head of the linked sequence.
	//   2. For the final node of the sequence, the link part is null.
	//      Otherwise, the  link part is a reference to the
	//      next node of the list.
	private Node<T> head;
	private Node<T> tail;
	private Node<T> cursor;
	private Node<T> precursor;
	private Node<T> dummy = new Node<T>(null, null);

    /**
     * Initialize a sequence with a specified head node which may have data and
     * a link to the next node in the sequence.
     * Note that the initialLink may be the null reference, 
     * which indicates that the new node has nothing after it.
     * @param head
     *   the head of this new sequence
     * @postcondition
     *   This node contains the specified data and link to the next node.
     **/ 
	public LinkedSequence(Node<T> head) {
		this.head = head;
		this.tail = Node.getTail(head);
		this.dummy.setNext(head);
		this.cursor = head;
		this.precursor = null;
	}
	
	/**
	 * Creates a new node, adds it after the cursor if it can, and puts the specified data in it.
	 * @param data
	 *   The data that is to be stored in the new node that follows the cursor.
	 * @postcondition
	 *   If the head was null, the precusor will have been set to dummy
	 *	 If the cursor was null, but the head wasn't, then the precursor is set to the tail.
	 *   If both the head and the cursor were not null, the precursor is set to the cursor.
	 *   If the cursor was at the tail, then the tail is set after the precursor and the cursor
	 *	     is set to the tail.
	 *   If the cursor was not at the tail, then the cursor is just set after the precursor
	 *	 Regardless of what happens above, the new data will be added to the list.
 	 * @return
 	 *	 A reference to the newly added node.
	 **/
	public Node<T> addAfter(T data) {
		if(this.head == null) {
			this.precursor = this.dummy;
		} else if(this.cursor == null) {
			this.precursor = this.tail;
		} else {
			this.precursor = this.cursor;
		}
		
		if(this.cursor == this.tail) {
			this.tail = this.precursor.addNodeAfter(data);
			return this.cursor = this.tail;
		}
		return this.cursor = this.precursor.addNodeAfter(data);
	}
	
	/**
	 * Creates a new node, adds it before the cursor if it can, and puts the specified data in it.
	 * @param data
	 *   The data that is to be stored in the new node that preceeds the cursor.
	 * @postcondition
	 *   If the precusor was null, it will have been set to dummy
	 *	 This will create the node immediately behind the cursor and set the precursor to it.
	 *	 The cursor will remain in its current location.
	 *	 Regardless of what happens above, the new data will be added to the list.
 	 * @return
 	 *	 A reference to the newly added node.
	 **/
	public Node<T> addBefore(T data) {
		if(this.precursor == null)
			this.precursor = this.dummy;
		return this.precursor = this.precursor.addNodeAfter(data);
	}
	
	/**
	 * Takes another linked sequence and connects it to the tail of the current linked sequence,
	 * effectively adding all the elements from it to the current linked sequence.
	 * @param other
	 *   The other linked sequence that is to be appended to the current one.
	 * @postcondition
	 *   If the given sequence was empty or had a null head, nothing will have happened.
	 *	 If the current sequence's head was null, the other head and tail will be used
	 *	 as this sequence's head and tail.
	 *	 Otherwise, the tail of the current sequence has its next field set to the head
	 *	 of the other sequence and the tail of the current sequence is set to the other
	 *	 sequence's tail.
	 **/
	public void addAll(LinkedSequence<T> other) {
		if(other == null || other.head == null)
			return;
		
		if(this.head == null) {
			this.head = other.head;
			this.tail = other.tail;
		} else {
			this.tail.setNext(other.head);
			this.tail = other.tail;
		}
	}
	
    /**
     * Moves the cursor and precursor in the sequence one 'space' forward unless it's at the end of the list.
     * @postcondition
     *   The cursor will have been moved one link forward in the sequence.
     *	 If the cursor was null, the precursor will be set to null and the cursor will be set
     *	 at the head. Else, if the cursor isn't at the tail, it will be moved one space forward
     *	 as will the precursor.
     **/ 
	public void advance() {
		if(this.cursor == null) {
			this.precursor = null;
			this.cursor = this.head;
		} else if(this.cursor != this.tail){
			this.precursor = this.cursor;
			this.cursor = this.cursor.getNext();
		}
	}
	
    /**
     * Creates a deep clone of the linked sequence and all of the elements in it
 	 * @return
 	 *	 A reference to the clone's head node.
     **/ 
	public LinkedSequence<T> clone() {
		LinkedSequence<T> clone = new LinkedSequence<T>(new Node<T>(null, null));
		clone.head = Node.listCopy(this.head);
		clone.tail = Node.listCopy(this.tail);
		clone.cursor = Node.listCopy(this.cursor);
		clone.precursor = Node.listCopy(this.precursor);
		clone.dummy = Node.listCopy(this.dummy);
		return clone;
	}
	
    /**
     * Creates a deep clone of two linked sequences and concatenates their elements together.
 	 * @return
 	 *	 A reference to the concatenated sequence's head node.
     **/ 
	public static <T> Node<T> concatenate(LinkedSequence<T> first, LinkedSequence<T> second) {
		Node<T> newHead = Node.listCopy(first.getHead());
		Node.getTail(newHead).setNext(Node.listCopy(second.getHead()));
		return newHead;
	}
	
    /**
     * Modification method to remove the node pointed to by the cursor.   
     * @postcondition
     *   The node at the cursor has been removed from the linked list.
     *   If there were further nodes after that one, they are still
     *   present on the list.
     *	 If the removed node was the head, the new head will be at the cursor.
 	 * @return
 	 *	 A reference to the removed node.
     **/
	public Node<T> removeCurrent() {
		Node<T> removedNode = this.cursor;
		boolean removedHead = this.cursor == this.head;
		this.precursor.setNext(cursor.getNext());
		this.cursor = this.cursor.getNext();
		if(removedHead)
			this.head = this.cursor;
		return removedNode;
	}
	
    /**
     * Displays all the elements in the list.
     **/
	public void displayList() {
		System.out.println(this.head.toString());
	}
	

    /**
     * Displays all the elements in the list.
     **/
	public boolean isCurrent() {
		return cursor != null;
	}

    /**
     * Displays all the elements in the list.
     **/
	public Node<T> getCurrent() {
		return this.cursor;
	}
	
    /**
     * Resets the cursor to the head of the list.
	 * @postcondition
     *	 The cursor will point to the head of the list.
     **/
	public void start() {
		this.cursor = this.dummy.getNext();
	}

	/**
	 * Accessor method for the head field.
	 * @return the head
	 */
	public final Node<T> getHead() {
		return head;
	}

	/**
	 * Mutator method for the head field.
	 * @param head the head to set
	 */
	public final void setHead(Node<T> head) {
		this.head = head;
	}

	/**
	 * Accessor method for the tail field.
	 * @return the tail
	 */
	public final Node<T> getTail() {
		return tail;
	}

	/**
	 * Mutator method for the tail field.
	 * @param tail the tail to set
	 */
	public final void setTail(Node<T> tail) {
		this.tail = tail;
	}

	/**
	 * Accessor method for the cursor field.
	 * @return the cursor
	 */
	public final Node<T> getCursor() {
		return cursor;
	}

	/**
	 * Mutator method for the cursor field.
	 * @param cursor the cursor to set
	 */
	public final void setCursor(Node<T> cursor) {
		this.cursor = cursor;
	}

	/**
	 * Accessor method for the precursor field.
	 * @return the precursor
	 */
	public final Node<T> getPrecursor() {
		return precursor;
	}

	/**
	 * Mutator method for the precursor field.
	 * @param precursor the precursor to set
	 */
	public final void setPrecursor(Node<T> precursor) {
		this.precursor = precursor;
	}
	
	
}
