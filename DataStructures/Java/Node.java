/* Name: Isaac Smith
 * Assignment: Project 1
 * Date: 10/8/2016
 * Class: Data Structures
 */

// Some documentation from https://www.cs.colorado.edu/~main/edu/colorado/nodes/Node.java
public class Node <T> {
	// Invariant of the Node class:
	//   1. Each node has one reference to an E Object, stored in the instance
	//      variable data.
	//   2. For the final node of a list, the link part is null.
	//      Otherwise, the  link part is a reference to the
	//      next node of the list.
    private T data;
    private Node<T> next;

    /**
     * Initialize a node with a specified initial data and link to the next
     * node. Note that the initialLink may be the null reference, 
     * which indicates that the new node has nothing after it.
     * @param initialData
     *   the initial data of this new node
     * @param initialLink
     *   a reference to the node after this new node--this reference may be null
     *   to indicate that there is no node after this new node.
     * @postcondition
     *   This node contains the specified data and link to the next node.
     **/ 
    public Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }
    
    /**
     * Accessor method to get the data from this node.   
     * @return
     *   the data from this node
     **/
    public T getData() {
    	return data;
    }
    
    /**
     * Accessor method to get a reference to the next node after this node. 
     * @return
     *   a reference to the node after this node (or the null reference if there
     *   is nothing after this node)
     **/
    public Node<T> getNext() {
    	return next;
    }
    
    /**
     * Modification method to add a new node after this node.   
     * @param element
     *   the data to place in the new node
     * @postcondition
     *   A new node has been created and placed after this node.
     *   The data for the new node is element. Any other nodes
     *   that used to be after this node are now after the new node.
     * @exception OutOfMemoryError
     *   Indicates that there is insufficient memory for a new 
     *   Node. 
     **/
     public Node<T> addNodeAfter(T element) {
        return this.next = new Node<T>(element, next);
    }
     
     /**
      * Modification method to remove the node after this node.   
      * @precondition
      *   This node must not be the tail node of the list.
      * @postcondition
      *   The node after this node has been removed from the linked list.
      *   If there were further nodes after that one, they are still
      *   present on the list.
      * @exception NullPointerException
      *   Indicates that this was the tail node of the list, so there is nothing
      *   after it to remove.
      **/
     public Node<T> removeNodeAfter() {
    	 return this.next = this.next.next;
     }   
	
 	/**
 	 * Starts at the current node and recursively displays
 	 * the data and links of all connected nodes.
 	 * If the data of the node is null, the string "dummy"
 	 * will be displayed in the data field.
 	 * If the link of the node is null, the string "null in tail"
 	 * will be displayed in the link field.
 	 **/
 	public String toString() {
 		String dataField = "dummy";
 		String linkField = "null in tail";
 		
 		if(data != null)
 			dataField = data.toString();
 		if(next != null)
 			linkField = next.toString().replaceAll("\n", "\n\t");
 		return "data:   " + dataField + "\nlink:   " + linkField;
 	}
 	
	//Static methods
	/**
	 * Copy a list.
	 * @param source
	 *   the head of a linked list that will be copied (which may be
	 *   an empty list in where source is null)
	 * @param <T>
	 *   type of an element in the list
	 * @return
	 *   The method has made a copy of the linked list starting at 
	 *   source. The return value is the head reference for the
	 *   copy. 
	 * @exception OutOfMemoryError
	 *   Indicates that there is insufficient memory for the new list.   
	 **/ 
	public static <T> Node<T> listCopy(Node<T> source) {
		Node<T> copyHead;
		Node<T> copyTail;

		// Handle the special case of the empty list.
		if (source == null)
			return null;

		// Make the first node for the newly created list.
		copyHead = new Node<T>(source.data, null);
		copyTail = copyHead;

		// Make the rest of the nodes for the newly created list.
		for(source = source.next; source != null; source = source.next)
			copyTail = copyTail.addNodeAfter(source.data);

		// Return the head reference for the new list.
		return copyHead;
	}
	
	/**
	 * Find a node at a specified position in a linked list.
	 * @param head
	 *   the head reference for a linked list (which may be an empty list in
	 *   which case the head is null)
	 * @param position
	 *   a node number
	 * @param <T>
	 *   type of an element in the list
	 * @precondition
	 *   position &gt; 0.
	 * @return
	 *   The return value is a reference to the node at the specified position in
	 *   the list. (The head node is position 1, the next node is position 2, and
	 *   so on.) If there is no such position (because the list is too short),
	 *   then the null reference is returned.
	 * @exception IllegalArgumentException
	 *   Indicates that position is zero.    
	 **/   
	public static <T> Node<T> listPosition(Node<T> head, int position) {
		Node<T> cursor;
		int i;

		if (position == 0)
			throw new IllegalArgumentException("position is zero");

		cursor = head;
		for (i = 1; (i < position) && (cursor != null); i++)
			cursor = cursor.next;

		return cursor;
	}
	
	/**
	 * Compute the number of nodes in a linked list.
	 * @param head
	 *   the head reference for a linked list (which may be an empty list
	 *   with a null head)
	 * @param <T>
	 *   type of an element in the list
	 * @return
	 *   the number of nodes in the list with the given head 
	 * @note
	 *   A wrong answer occurs for lists longer than Int.MAX_VALUE.
	 **/   
	public static <T> int listLength(Node<T> head) {
		Node<T> cursor;
		int answer = 0;

		for (cursor = head; cursor != null; cursor = cursor.next)
			answer++;

		return answer;
	}
	
	/**
	 * Get a reference to the tail node in a linked list.
	 * @param head
	 *   the head reference for a linked list (which may be an empty list
	 *   with a null head)
	 * @param <T>
	 *   type of an element in the list
	 * @return
	 *   the tail node in the linked list
	 **/  
	public static <T> Node<T> getTail(Node<T> head) {
		Node<T> cursor;
		for(cursor = head; cursor.next != null; cursor = cursor.next);
		return cursor;
	}
	
	/**
	 * Modification method to set the data in this node.   
	 * @param newData
	 *   the new data to place in this node
	 * @postcondition
	 *   The data of this node has been set to newData.
	 *   This data is allowed to be null.
	 **/
	public void setData(T newData) {
		data = newData;
	}

	/**
	 * Modification method to set the link to the next node after this node.
	 * @param newNext
	 *   a reference to the node that should appear after this node in the linked
	 *   list (or the null reference if there is no node after this node)
	 * @postcondition
	 *   The link to the node after this node has been set to newLink.
	 *   Any other node (that used to be in this link) is no longer connected to
	 *   this node.
	 **/
	public Node<T> setNext(Node<T> newNext) {                    
		return this.next = newNext;
	}

}
