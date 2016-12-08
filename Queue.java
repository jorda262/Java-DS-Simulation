package edu.century.finalProject;

import java.util.EmptyStackException;

public class Queue implements Cloneable {
	// Instance variables
	private Node head;

	// Method for adding a Node to the tail of the queue
	public void addToTail(Shopper item) {
		Node cursor;

		if (head == null) {
			head = new Node(item, head);
		} else {
			for (cursor = head; cursor != null; cursor = cursor.getLink()) {
				if (cursor.getLink() == null) {
					Node node = new Node(item, null);
					cursor.setLink(node);
					break;
				}
			}
		}
	}

	// Method for returning the size of the queue
	public int size() {
		return Node.listLength(head);
	}

	// Method for checking if the queue is empty
	public boolean isEmpty() {
		return (head == null);
	}

	// Method for removing a shopper from the front of the queue
	public Shopper removeFromHead() {
		Shopper answer;

		if (isEmpty()) {
			throw new EmptyStackException();
		}

		answer = head.getData();
		head = head.getLink();
		return answer;
	}

	// Method for cloning the queue
	public Queue clone() {
		Queue answer;

		try {
			answer = (Queue) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException("This class does not implement Cloneable.");
		}

		answer.head = (Node) Node.listCopy(head);
		return answer;
	}
}
