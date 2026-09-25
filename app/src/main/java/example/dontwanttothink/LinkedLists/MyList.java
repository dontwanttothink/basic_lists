package example.dontwanttothink.LinkedLists;

public interface MyList<T, N> {
	void pushFront(T x);

	void pushBack(T x);

	T popFront();

	T popBack();

	N find(T datum);

	void erase(N node);

	void addBefore(N node, T datum);

	void addAfter(N node, T datum);

	boolean isEmpty();
}
