package org.dontwanttothink.LinkedLists;

public class DoublyLinkedList<T> implements MyList<T, DoublyLinkedNode<T>> {
	DoublyLinkedNode<T> head;

	@Override
	public void pushFront(T x) {
		DoublyLinkedNode<T> novel = new DoublyLinkedNode<>();
		novel.datum = x;

		if (isEmpty()) {
			head = novel;
		} else {
			head.previous = novel;
			novel.next = head;
			head = novel;
		}
	}

	@Override
	public void pushBack(T x) {
		DoublyLinkedNode<T> novel = new DoublyLinkedNode<>();
		novel.datum = x;

		if (isEmpty()) {
			head = novel;
		} else {
			DoublyLinkedNode<T> last = head;
			while (last.next != null) {
				last = last.next;
			}

			last.next = novel;
			novel.previous = last;
		}
	}

	@Override
	public T popFront() {
		if (head == null) {
			return null;
		}

		T out = head.datum;
		erase(head);
		return out;
	}

	@Override
	public T popBack() {
		if (isEmpty()) {
			return null;
		}

		DoublyLinkedNode<T> last = head;
		while (last.next != null) {
			last = last.next;
		}

		T out = last.datum;
		erase(last);
		return out;
	}

	@Override
	public DoublyLinkedNode<T> find(T datum) {
		DoublyLinkedNode<T> current = head;
		while (current != null) {
			if (current.datum == null ? datum == null : current.datum.equals(datum)) {
				return current;
			}

			current = current.next;
		}

		return null;
	}

	/**
	 * El comportamiento está indefinido si `node` es null o si no es parte de la
	 * lista.
	 */
	@Override
	public void erase(DoublyLinkedNode<T> node) {
		if (isEmpty()) {
			return;
		}

		if (node == head) {
			head = node.next;

			if (head != null) {
				head.previous = null;
			}
		} else {
			node.previous.next = node.next;

			if (node.next != null) {
				node.next.previous = node.previous;
			}
		}
	}

	/**
	 * El comportamiento está indefinido si `node` es null o si no es parte de la
	 * lista.
	 */
	@Override
	public void addBefore(DoublyLinkedNode<T> node, T datum) {
		DoublyLinkedNode<T> novel = new DoublyLinkedNode<>();
		novel.datum = datum;

		novel.next = node;

		if (node == head) {
			node.previous = novel;
			head = novel;
		} else {
			novel.previous = node.previous;
			novel.next.previous = novel;
			novel.previous.next = novel;
		}
	}

	/**
	 * El comportamiento está indefinido si `node` es null o si no es parte de la
	 * lista.
	 */
	@Override
	public void addAfter(DoublyLinkedNode<T> node, T datum) {
		DoublyLinkedNode<T> novel = new DoublyLinkedNode<>();
		novel.datum = datum;

		novel.previous = node;
		novel.next = node.next;

		if (novel.next != null) {
			novel.next.previous = novel;
		}
		novel.previous.next = novel;
	}

	@Override
	public boolean isEmpty() {
		return head == null;
	}
}
