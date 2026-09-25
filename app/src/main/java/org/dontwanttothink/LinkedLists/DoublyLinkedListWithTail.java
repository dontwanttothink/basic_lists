package org.dontwanttothink.LinkedLists;

import org.jspecify.annotations.Nullable;

public class DoublyLinkedListWithTail<T> implements MyList<T, DoublyLinkedNode<T>> {
	// voy a usar un centinela porque si no mi cerebro se derrite.
	// el centinela sirve de cabeza y cola.

	DoublyLinkedNode<T> sentinel = new DoublyLinkedNode<>();

	DoublyLinkedListWithTail() {
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
	@Nullable
	public T popFront() {
		if (isEmpty()) {
			return null;
		}

		T out = sentinel.next.datum;
		erase(sentinel.next);
		return out;
	}

	@Override
	@Nullable
	public T popBack() {
		if (isEmpty()) {
			return null;
		}

		T out = sentinel.previous.datum;
		erase(sentinel.previous);
		return out;
	}

	@Override
	@Nullable
	public DoublyLinkedNode<T> find(T datum) {
		DoublyLinkedNode<T> current = sentinel.next;
		while (current != sentinel) {
			assert current.datum != null;
			if (current.datum.equals(datum)) {
				return current;
			}

			current = current.next;
		}

		return null;
	}

	@Override
	public boolean erase(DoublyLinkedNode<T> node) {
		if (node == sentinel) {
			throw new Error();
		}

		node.previous.next = node.next;
		node.next.previous = node.previous;

		return true;
	}

	@Override
	public void addBefore(DoublyLinkedNode<T> node, T datum) {
		DoublyLinkedNode<T> novel = new DoublyLinkedNode<>();
		novel.datum = datum;

		novel.next = node;
		novel.previous = node.previous;
		node.previous.next = novel;
		node.previous = novel;
	}

	@Override
	public void addAfter(DoublyLinkedNode<T> node, T datum) {
		addBefore(node.next, datum);
	}

	@Override
	public boolean isEmpty() {
		return sentinel.next == sentinel;
	}
}
