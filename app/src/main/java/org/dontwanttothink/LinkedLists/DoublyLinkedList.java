package org.dontwanttothink.LinkedLists;

import org.jspecify.annotations.Nullable;

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
	@Nullable
	public T popFront() {
		if (isEmpty()) {
			return null;
		}

		T out = head.datum;
		erase(head);
		return out;
	}

	@Override
	@Nullable
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
	@Nullable
	public DoublyLinkedNode<T> find(T datum) {
		DoublyLinkedNode<T> current = head;
		while (current != null) {
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
		if (isEmpty()) {
			return false;
		}

		if (node == head) {
			head = node.next;

			if (head != null) {
				head.previous = null;
			}
		} else {
			node.previous.next = node.next;
			node.next.previous = node.previous;
		}
		return true;
	}

	@Override
	public void addBefore(DoublyLinkedNode<T> node, T datum) {
		oaisdjfoiajdsf
	}

	@Override
	public void addAfter(DoublyLinkedNode<T> node, T datum) {
		oaijsdfoiajsdiojf
	}

	@Override
	public boolean isEmpty() {
		return head == null;
	}
}
