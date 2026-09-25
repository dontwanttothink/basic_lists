package example.dontwanttothink.LinkedLists;

import java.util.NoSuchElementException;

public final class SinglyLinkedList<T> implements MyList<T, SinglyLinkedNode<T>> {
	private SinglyLinkedNode<T> head;

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
			return;
		}

		SinglyLinkedNode<T> current = head;
		while (current != null && current.next != node) {
			current = current.next;
		}

		current.next = node.next;
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

		SinglyLinkedNode<T> current = head;
		while (current != null && current.next != node) {
			current = current.next;
		}

		if (current == null) {
			return;
		}
		current.next = novelNode;
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

		SinglyLinkedNode<T> current = head;
		while (current.next != null) {
			current = current.next;
		}

		T out = current.datum;
		erase(current);
		return out;
	}
}
