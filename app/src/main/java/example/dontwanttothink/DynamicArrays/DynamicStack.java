package example.dontwanttothink.DynamicArrays;

import java.util.NoSuchElementException;

public final class DynamicStack<T> implements MyStack<T> {
	private T[] buffer;
	private int length = 0;

	final private static int INITIAL_CAPACITY = 2;

	@SuppressWarnings("unchecked")
	public DynamicStack() {
		buffer = (T[]) new Object[INITIAL_CAPACITY];
	}

	@Override
	public void push(T x) {
		if (length >= buffer.length) {
			growBuffer();
		}

		buffer[length] = x;
		++length;
	}

	@Override
	public T pop() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}

		T out = buffer[length - 1];
		buffer[length - 1] = null;

		--length;

		if (length < buffer.length / 4) {
			shrinkBuffer();
		}

		return out;
	}

	@Override
	public T peek() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		return buffer[length - 1];
	}

	@Override
	public boolean isEmpty() {
		return length == 0;
	}

	@Override
	public int size() {
		return length;
	}

	@Override
	public void delete(T n) {
		for (int i = 0; i < length; ++i) {
			if (buffer[i] == null ? n == null : buffer[i].equals(n)) {
				System.arraycopy(buffer, i + 1, buffer, i, length - (i + 1));
				buffer[length - 1] = null;

				--length;

				if (length < buffer.length / 4) {
					shrinkBuffer();
				}

				return;
			}
		}
		throw new NoSuchElementException();
	}

	private void shrinkBuffer() {
		@SuppressWarnings("unchecked")
		T[] smallerBuffer = (T[]) new Object[buffer.length / 2];

		System.arraycopy(buffer, 0, smallerBuffer, 0, length);
		buffer = smallerBuffer;
	}

	private void growBuffer() {
		@SuppressWarnings("unchecked")
		T[] smallerBuffer = (T[]) new Object[buffer.length * 2];

		System.arraycopy(buffer, 0, smallerBuffer, 0, length);
		buffer = smallerBuffer;
	}
}
