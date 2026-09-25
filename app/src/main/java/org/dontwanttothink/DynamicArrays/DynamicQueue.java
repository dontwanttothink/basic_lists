package org.dontwanttothink.DynamicArrays;

import java.util.NoSuchElementException;

public class DynamicQueue<T> implements MyQueue<T> {
	private T[] buffer;
	private int start = 0;
	private int length = 0;

	final private static int INITIAL_CAPACITY = 2;

	@SuppressWarnings("unchecked")
	public DynamicQueue() {
		buffer = (T[]) new Object[INITIAL_CAPACITY];
	}

	@Override
	public void enqueue(T x) {
		if (isFull()) {
			growBuffer();
		}

		buffer[(start + length) % buffer.length] = x;
		++length;
		length %= buffer.length;
	}

	@Override
	public T dequeue() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}

		T out = buffer[start];

		++start;
		start %= buffer.length;

		--length;

		if (length <= buffer.length / 4) {
			shrinkBuffer();
		}

		return out;
	}

	@Override
	public T front() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}

		return buffer[(start + length) % buffer.length];
	}

	@Override
	public boolean isEmpty() {
		return length == 0;
	}

	private boolean isFull() {
		return length == buffer.length;
	}

	@Override
	public int size() {
		return length;
	}

	@Override
	public void delete(T n) {
		for (int i = 0; i < length; ++i) {
			int index = (start + i) % length;
			T item = buffer[index];
			if (item == null ? n == null : item.equals(n)) {
				int firstChunk = Math.min(buffer.length - start, length);
				if (i < firstChunk) {
					System.arraycopy(buffer, index + 1, buffer, index, buffer.length - (i + 1));
					buffer[0] = buffer[length - 1];
					System.arraycopy(buffer, 1, buffer, 0, length - firstChunk);
				} else {
					System.arraycopy(buffer, index + 1, buffer, index, length - firstChunk);
				}

				--length;

				if (length <= buffer.length / 4) {
					shrinkBuffer();
				}

				return;
			}
		}
	}

	private void copyContiguously(T[] into) {
		int firstChunk = Math.min(buffer.length - start, length);
		System.arraycopy(buffer, start, into, 0, firstChunk);
		System.arraycopy(buffer, 0, into, firstChunk, length - firstChunk);
	}

	private void shrinkBuffer() {
		@SuppressWarnings("unchecked")
		T[] smallerBuffer = (T[]) new Object[buffer.length / 2];

		copyContiguously(smallerBuffer);

		buffer = smallerBuffer;
		start = 0;
	}

	private void growBuffer() {
		@SuppressWarnings("unchecked")
		T[] biggerBuffer = (T[]) new Object[buffer.length * 2];

		copyContiguously(biggerBuffer);

		buffer = biggerBuffer;
		start = 0;
	}
}
