package example.dontwanttothink.DynamicArrays;

import java.util.NoSuchElementException;

public final class DynamicQueue<T> implements MyQueue<T> {
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
	}

	@Override
	public T dequeue() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}

		T out = buffer[start];
		buffer[start] = null;

		++start;
		start %= buffer.length;

		--length;

		if (length < buffer.length / 4) {
			shrinkBuffer();
		}

		return out;
	}

	@Override
	public T front() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}

		return buffer[start];
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
			T item = buffer[(start + i) % buffer.length];
			if (item == null ? n == null : item.equals(n)) {
				deleteIndex(i);
				return;
			}
		}
		throw new NoSuchElementException();
	}

	private void deleteIndex(int i) {
		for (int j = i; j + 1 < length; ++j) {
			buffer[(start + j) % buffer.length] = buffer[(start + j + 1) % buffer.length];
		}

		buffer[(start + length - 1) % buffer.length] = null;
		--length;

		if (length < buffer.length / 4) {
			shrinkBuffer();
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
