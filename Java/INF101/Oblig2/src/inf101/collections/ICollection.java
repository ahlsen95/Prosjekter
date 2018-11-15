package inf101.collections;

import java.util.Iterator;

public interface ICollection<E> extends Iterable<E> {
	/**
	 * Add an element to the collection
	 * 
	 * 
	 * @param elt
	 *            The element
	 */
	void add(E elt);

	/**
	 * Check if the collection contains the given element
	 * 
	 * @param elt
	 *            The element
	 * @return True if the element is in the collection
	 */
	boolean contains(E elt);

	/**
	 * @return True if the collection is empty
	 */
	boolean isEmpty();

	// From Iterable<E>: returns an iterator
	Iterator<E> iterator();

	/**
	 * Remove an element from the collection
	 * 
	 * @return The element removed
	 * @throws IndexOutOfBoundsException
	 *             if the collection is empty
	 */
	E remove();

	/**
	 * Remove an element from the collection
	 * 
	 * Will find and remove an element which is equals() to the provided
	 * element, if any.
	 * 
	 * @param elt
	 *            The element to remove
	 * @return The element removed, or null if not found
	 */
	E remove(E elt);

	/**
	 * @return The number of elements in the collection
	 */
	int size();
}
