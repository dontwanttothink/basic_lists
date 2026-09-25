package org.dontwanttothink.LinkedLists;

import java.util.NoSuchElementException;

public class SinglyLinkedListWithTail<T> implements MyList<T, SinglyLinkedNode<T>> {
	SinglyLinkedNode<T> head;
	SinglyLinkedNode<T> tail;

	public boolean isEmpty() {
		return head == null;
	}

	public void pushFront(T datum) {
		SinglyLinkedNode<T> node = new SinglyLinkedNode<>();
		node.datum = datum;

		if (isEmpty()) {
			head = node;
			tail = node;
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
			tail = node;
			return;
		}

		tail.next = node;
		tail = node;
	}

	public SinglyLinkedNode<T> find(T datum) {
		SinglyLinkedNode<T> current = head;
		while (current != null && (current.datum == null ? datum != null : !current.datum.equals(datum))) {
			current = current.next;
		}

		if (current == null) {
			throw new NoSuchElementException();
		}
		return current;
	}

	/**
	 * El comportamiento está indefinido si `node` es null o si no es parte de la
	 * lista.
	 */
	public void erase(SinglyLinkedNode<T> node) {
		if (node == head) {
			head = node.next;

			if (head == null) {
				tail = null;
			}
		} else {
			SinglyLinkedNode<T> predecessor = head;
			while (predecessor != null && predecessor.next != node) {
				predecessor = predecessor.next;
			}

			if (predecessor == null) {
				return;
			}

			predecessor.next = node.next;

			if (node == tail) {
				tail = predecessor;
			}
		}

		return;
	}

	/**
	 * El comportamiento está indefinido si `node` es null o si no es parte de la
	 * lista.
	 */
	public void addBefore(SinglyLinkedNode<T> node, T datum) {
		SinglyLinkedNode<T> novelNode = new SinglyLinkedNode<>();
		novelNode.datum = datum;
		novelNode.next = node;

		if (node == head) {
			head = novelNode;
			return;
		}

		SinglyLinkedNode<T> predecessor = head;
		while (predecessor != null && predecessor.next != node) {
			predecessor = predecessor.next;
		}

		if (predecessor == null) {
			return;
		}
		predecessor.next = novelNode;
	}

	/**
	 * El comportamiento está indefinido si `node` es null o si no es parte de la
	 * lista.
	 */
	public void addAfter(SinglyLinkedNode<T> node, T datum) {
		SinglyLinkedNode<T> novelNode = new SinglyLinkedNode<>();
		novelNode.datum = datum;
		novelNode.next = node.next;

		node.next = novelNode;

		if (node == tail) {
			tail = novelNode;
		}
	}

	public T popFront() {
		if (head == null) {
			throw new NoSuchElementException();
		}

		T out = head.datum;
		erase(head);
		return out;
	}

	public T popBack() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}

		T out = tail.datum;
		erase(tail);
		return out;
	}
}
