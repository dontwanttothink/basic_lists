package org.dontwanttothink.LinkedLists;

import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

@NullMarked
public interface MyList<T, N> {
	void pushFront(T x);

	void pushBack(T x);

	@Nullable
	T popFront();

	@Nullable
	T popBack();

	@Nullable
	N find(T datum);

	boolean erase(N node);

	void addBefore(N node, T datum);

	void addAfter(N node, T datum);

	boolean isEmpty();
}
