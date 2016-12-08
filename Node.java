package edu.century.finalProject;

public class Node {
	//Instance variables
	private Shopper data;
	private Node link;

	//Constructor
	public Node(Shopper item, Node initialLink) {
		data = item;
		link = initialLink;
	}

	//Getter methods
	public Shopper getData() {
		return data;
	}

	public Node getLink() {
		return link;
	}

	//List-copy method
	public static Node listCopy(Node source) {
		Node copyHead;
		Node copyTail;

		if (source == null)
			return null;

		copyHead = new Node(source.data, null);
		copyTail = copyHead;

		while (source.link != null) {
			source = source.link;
			copyTail.addNodeAfter(source.data);
			copyTail = copyTail.link;
		}

		return copyHead;
	}

	//List-length method
	public static int listLength(Node head) {
		Node cursor;
		int answer;

		answer = 0;
		for (cursor = head; cursor != null; cursor = cursor.link)
			answer++;

		return answer;
	}

	//Setter method
	public void setLink(Node newLink) {
		link = newLink;
	}

	//Method for removing the node after the current node
	public void removeNodeAfter() {
		link = link.link;
	}

	//Method for adding a node after the current node
	public void addNodeAfter(Shopper item) {
		link = new Node(item, link);
	}
}