package org.dontwanttothink.LinkedLists;

import java.util.NoSuchElementException;

public class DoublyLinkedListWithTail<T> implements MyList<T, DoublyLinkedNode<T>> {
	// voy a usar un centinela porque si no mi cerebro se derrite.
	// el centinela sirve de cabeza y cola.

	DoublyLinkedNode<T> sentinel = new DoublyLinkedNode<>();

	public DoublyLinkedListWithTail() {
		sentinel.next = sentinel;
		sentinel.previous = sentinel;
	}

	@Override
	public void pushFront(T x) {
		addAfter(sentinel, x);
	}

	@Override
	public void pushBack(T x) {
		addBefore(sentinel, x);
	}

	@Override
	public T popFront() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}

		T out = sentinel.next.datum;
		erase(sentinel.next);
		return out;
	}

	@Override
	public T popBack() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}

		T out = sentinel.previous.datum;
		erase(sentinel.previous);
		return out;
	}

	@Override
	public DoublyLinkedNode<T> find(T datum) {
		DoublyLinkedNode<T> current = sentinel.next;
		while (current != sentinel) {
			if (current.datum == null ? datum == null : current.datum.equals(datum)) {
				return current;
			}

			current = current.next;
		}

		throw new NoSuchElementException();
	}

	@Override
	/**
	 * El comportamiento está indefinido si `node` es null o si no es parte de la
	 * lista.
	 */
	public void erase(DoublyLinkedNode<T> node) {
		if (node == sentinel) {
			throw new IllegalArgumentException();
		}

		node.previous.next = node.next;
		node.next.previous = node.previous;
	}

	@Override
	/**
	 * El comportamiento está indefinido si `node` es null o si no es parte de la
	 * lista.
	 */
	public void addBefore(DoublyLinkedNode<T> node, T datum) {
		DoublyLinkedNode<T> novel = new DoublyLinkedNode<>();
		novel.datum = datum;

		novel.next = node;
		novel.previous = node.previous;
		node.previous.next = novel;
		node.previous = novel;
	}

	@Override
	/**
	 * El comportamiento está indefinido si `node` es null o si no es parte de la
	 * lista.
	 */
	public void addAfter(DoublyLinkedNode<T> node, T datum) {
		addBefore(node.next, datum);
	}

	@Override
	public boolean isEmpty() {
		return sentinel.next == sentinel;
	}
}
