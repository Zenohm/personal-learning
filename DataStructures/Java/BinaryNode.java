/**
 * @author Isaac Smith
 * @assignment Homework 6
 * @class CS260
 * @since 10/27/2016
 */

public class BinaryNode {
    // Invariant of the BinaryNode class:
    //   1. Each node has one reference to an Integer Object, stored in the instance
    //      variable data.
    //   2. Each node has a reference to two other BinaryNodes, stored in the instance
    //      variables left and right.
    //   3. If the BinaryNode is a leaf, both its subnodes will point to null. If the node
    //      is instead a subtree, then at least one of its links will point to another BinaryNode.
    Integer data;
	BinaryNode left = null;
	BinaryNode right = null;
	
    /**
     * Initialize a node with a specified initial data and link to the left and right
     * nodes. Note that the left and right initial nodes may be null references,
     * which indicates that the left or right link nodes have nothing after them.
     * @param initialData
     *   The initial data of this new node
     * @param initialLeft
     *   A reference to the left node after this new node--this reference may be null
     *   to indicate that there is no left node after this new node.
     * @param initialRight
     *   A reference to the right node after this new node--this reference may be null
     *   to indicate that there is no right node after this new node.
     * @postcondition
     *   This node contains the specified data and link to the next two nodes.
     **/
	public BinaryNode(int initialData, BinaryNode initialLeft, BinaryNode initialRight) {
		data = initialData;
		left = initialLeft;
		right = initialRight;
	}
	
    /**
     * Initialize a node with a specified initial data and no following nodes
     * nodes. This indicates that the new node is a leaf.
     * @param initialData
     *   The initial data of this new node.
     * @postcondition
     *   This node contains the specified data and two null links.
     **/
	public BinaryNode(int initialData) {
		data = initialData;
	}

 	/**
 	 * Starts at the current node and recursively displays
 	 * the data and linked branches as a tree.
	 * If the data of the node is null, the string "null"
 	 * will be displayed in place of the data.
 	 * If the link of the node is null, the link will not
 	 * be drawn in the tree.
     * @return
     *  A string representation of the binary tree.
 	 **/
 	public String toString() {
        int branchLength = 6;
 	    StringBuilder tree = new StringBuilder();
        if(right != null)
            tree.append(right.drawTree(true, "", branchLength));
        if(data != null)
            tree.append(data);
        else
            tree.append("null");
        tree.append('\n');
        if(left != null)
            tree.append(left.drawTree(false, "", branchLength));
        return tree.toString();
 	}

    /**
     * Recursively draws the branches for a binary tree.
     * If a binary node's left or right branches are null,
     * those branches will not be drawn.
     * @param isRight
     *  Determines whether the subtree being drawn is on the
     *  right or on the left of the parent node.
     * @param indent
     *  How much to pad the string at a given level.
     * @param branchLength
     *  Determines the general horizontal length of the tree.
     * @return
     *  A string representation of either the left or right subtree.
     **/
	private String drawTree(boolean isRight,  String indent, int branchLength){
		StringBuilder tree = new StringBuilder();
        StringBuilder branch = new StringBuilder();
        StringBuilder space = new StringBuilder(" ");

        // Generate the padding for the branches and the branches themselves
        // based on the given branchLength.
        for(int i=0; i<branchLength; i++) {
            branch.append("─");
            space.append("  ");
        }

        // Generate the right-most branches first.
        if(right != null)
		    tree.append(right.drawTree(true, indent + (isRight ? " " : "│") + space, branchLength));

        // Draw out a left or right branch and the data at the end of it.
        tree.append(indent);
        tree.append(isRight ? "┌" : "└");
        tree.append(branch);
        tree.append("─");
        tree.append(branch);
        if(data != null)
            tree.append(data);
        tree.append('\n');

        // Generate the left-most branches last.
        if(left != null)
            tree.append(left.drawTree(false, indent + (isRight ? "│" : " ") + space, branchLength));
        return tree.toString();
	}

	/**
     * Generates a complete binary search tree of the specified height using
     * the given data to extrapolate the values for the data of the other nodes.
     * @precondition
     *  height >= 0, otherwise only the root of the tree will be returned.
     * @postcondition
     *  A tree where the left node always has a value less than the current node
     *  and the right node always has a value greater than the current node is
     *  created.
     * @return
     *  The root of the complete binary search tree.
     **/
	public static BinaryNode BTSFactory(int height, int data) {
		BinaryNode node = new BinaryNode(data);

		if(height-- > 0) {
			int reach = (int) Math.pow(2, height);
			node.left = BTSFactory(height, data - reach);
			node.right = BTSFactory(height, data + reach);
		}

		return node;
	}

    /**
     * Generates a complete binary search tree of the specified height and populates
     * it with all the values from 1 to 2^(height+1)-1 inclusive.
     * @precondition
     *  height >= 0, otherwise only the root of the tree will be returned.
     * @postcondition
     *  A tree where the left node always has a value less than the current node
     *  and the right node always has a value greater than the current node is
     *  created.
     * @return
     *  The root of the complete binary search tree populated with numbers from the range
     *  1 to 2^(height+1)-1 inclusive.
     **/
	public static BinaryNode BTSFactory(int height) {
		return BinaryNode.BTSFactory(height, (int) Math.pow(2,height));
	}
}
