package org.dontwanttothink.LinkedLists;

import org.jspecify.annotations.Nullable;

public class SinglyLinkedList<T> implements MyList<T, SinglyLinkedNode<T>> {
	SinglyLinkedNode<T> head;

	public boolean isEmpty() {
		return head == null;
	}

	public void pushFront(T datum) {
		SinglyLinkedNode<T> node = new SinglyLinkedNode<>();
		node.datum = datum;

		if (isEmpty()) {
			head = node;
		} else {
			node.next = head;
			head = node;
		}
	}

	public void pushBack(T datum) {
		SinglyLinkedNode<T> node = new SinglyLinkedNode<>();
		node.datum = datum;

		if (isEmpty()) {
			head = node;
			return;
		}

		SinglyLinkedNode<T> current = head;
		while (current.next != null) {
			current = current.next;
		}

		current.next = node;
	}

	public SinglyLinkedNode<T> find(T datum) {
		SinglyLinkedNode<T> current = head;
		while (current != null && !current.datum.equals(datum)) {
			current = current.next;
		}
		return current;
	}

	public boolean erase(SinglyLinkedNode<T> node) {
		if (node == head) {
			head = node.next;
			return true;
		}

		SinglyLinkedNode<T> current = head;
		while (current != null && current.next != node) {
			current = current.next;
		}

		if (current == null) {
			return false;
		}

		current.next = node.next;
		return true;
	}

	public void addBefore(SinglyLinkedNode<T> node, T datum) {
		SinglyLinkedNode<T> novelNode = new SinglyLinkedNode<>();
		novelNode.datum = datum;
		novelNode.next = node;

		if (node == head) {
			head = novelNode;
			return;
		}

		SinglyLinkedNode<T> current = head;
		while (current != null && current.next != node) {
			current = current.next;
		}

		if (current == null) {
			return;
		}
		current.next = novelNode;
	}

	public void addAfter(SinglyLinkedNode<T> node, T datum) {
		SinglyLinkedNode<T> novelNode = new SinglyLinkedNode<>();
		novelNode.datum = datum;
		novelNode.next = node.next;
		node.next = novelNode;
	}

	@Nullable
	public T popFront() {
		if (head == null) {
			return null;
		}

		T out = head.datum;
		erase(head);
		return out;
	}

	@Nullable
	public T popBack() {
		if (isEmpty()) {
			return null;
		}

		SinglyLinkedNode<T> current = head;
		while (current.next != null) {
			current = current.next;
		}

		T out = current.datum;
		erase(current);
		return out;
	}
}
