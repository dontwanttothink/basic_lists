package org.dontwanttothink.LinkedLists;

import org.jspecify.annotations.Nullable;

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
		while (current != null && current.datum != datum) {
			current = current.next;
		}
		return current;
	}

	public boolean erase(SinglyLinkedNode<T> node) {
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
				return false;
			}

			predecessor.next = node.next;

			if (node == tail) {
				tail = predecessor;
			}
		}

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

		SinglyLinkedNode<T> predecessor = head;
		while (predecessor != null && predecessor.next != node) {
			predecessor = predecessor.next;
		}

		if (predecessor == null) {
			return;
		}
		predecessor.next = novelNode;
	}

	public void addAfter(SinglyLinkedNode<T> node, T datum) {
		SinglyLinkedNode<T> novelNode = new SinglyLinkedNode<>();
		novelNode.datum = datum;
		novelNode.next = node.next;

		node.next = novelNode;

		if (node == tail) {
			tail = novelNode;
		}
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

		T out = tail.datum;
		erase(tail);
		return out;
	}
}
