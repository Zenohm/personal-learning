/**
 * @author Isaac Smith
 * @assignment Homework 6
 * @class CS260
 * @since 10/27/2016
 */

public class SimpleNode {
	// Invariant of the SimpleNode class:
	//   1. Each node has one reference to a String Object, stored in the instance
	//      variable data.
	//   2. For the final node of a list, the link part is null.
	//      Otherwise, the  link part is a reference to the
	//      next node of the list.
	private String data; //generic data type data for node
	private SimpleNode link; //generic Node data type
	
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
	public SimpleNode(String data, SimpleNode link) {
		this.data = data;
		this.link = link;
	}
	
    /**
     * Accessor method to get the data from this node.
     * @return
     *   the data from this node
     **/
	public String getData() {
		return data;
	}

	/**
	 * Modification method to set the data in this node.
	 * @param newData
	 *   the new data to place in this node
	 * @postcondition
	 *   The data of this node has been set to newData.
	 *   This data is allowed to be null.
	 **/
	public void setData(String data) {
		this.data = data;
	}

    /**
     * Accessor method to get a reference to the next node after this node.
     * @return
     *   a reference to the node after this node (or the null reference if there
     *   is nothing after this node)
     **/
	public SimpleNode getLink() {
		return link;
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
	public void setLink(SimpleNode link) {
		this.link = link;
	}

    /**
     * Modification method to add a new node after this node.
     * @param element
     *   the data to place in the new node
     * @postcondition
	 *   A new node has been created and placed after this node
	 *   The data for the new node is element. Any other nodes
	 *   that used to be after this node are now after the new node
	 * @exception OutOfMemoryError
	 *   Indicates that there is insufficient memory for a new
	 *   Node.
	 **/
	public SimpleNode addNodeAfter(String element) {
	    return link = new SimpleNode(element, link);
	}
	
	/**
	 * Get a reference to the tail node in a linked list.
	 * @param head
	 *   the head reference for a linked list (which may be an empty list
	 *   with a null head)
	 * @return
	 *   the tail node in the linked list
	 **/
	public SimpleNode getTail (SimpleNode head) {
		SimpleNode cursor = head;
		for(; cursor.link != null; cursor = cursor.link);
		return cursor;
	}

    /**
     * Take the head of a singly linked list and reverse the direction of the elements' links.
     * @param head
     *   the head reference for a linked list
     * @precondition
     *  the head passed is not null and the head's link is not null
     * @postcondition
     *  the links of every element will now point to the element that came before, rather than
     *  the element that comes after
     * @return
     *  the head node of the reversed linked list
     **/
	public static SimpleNode reverse(SimpleNode head) {
		if(head == null || head.link == null)
			return head;
		
		SimpleNode last = reverse(head.link);
		head.link.link = head;
		head.link = null;
		return last;
	}

 	/**
 	 * Starts at the current node and recursively displays
 	 * the data and links of all connected nodes.
 	 * If the data of the node is null, the string "dummy"
 	 * will be displayed in the data field.
 	 * If the link of the node is null, the string "null in tail"
 	 * will be displayed in the link field.
     * @return
     *  A string representation of the singly linked list.
 	 **/
 	public String toString() {
 		String dataField = "dummy";
 		String linkField = "";

 		if(data != null)
 			dataField = data.toString();
        if(link != null)
 			linkField = " ⇒ " + link.toString();
 		return dataField + linkField;
 	}
}
