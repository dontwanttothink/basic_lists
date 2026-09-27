package example.dontwanttothink.LinkedLists;

public class SinglyLinkedNode<T> {
	SinglyLinkedNode<T> next;

	T datum;

	public T unwrap() {
		return datum;
	}

	public SinglyLinkedNode<T> next() {
		return next;
	}
}
