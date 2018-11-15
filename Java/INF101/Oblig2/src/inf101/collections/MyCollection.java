package inf101.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Implementation of an unordered collection of elements.
 * 
 * @author Anya Helene Bagge
 *
 * @param <E> The element type
 */
public class MyCollection<E> implements ICollection<E> {
	protected final List<E> list = new ArrayList<>();

	@Override
	public void add(E elt) {
		list.add(elt);
	}

	@Override
	public boolean contains(E elt) {
		return list.contains(elt);
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public Iterator<E> iterator() {
		return list.iterator();
	}

	@Override
	public E remove() {
		return list.remove(0);
	}

	@Override
	public E remove(E elt) {
		int r = list.indexOf(elt);
		if (r >= 0)
			return list.remove(r);
		else
			return null;
	}

	@Override
	public int size() {
		return list.size();
	}
	
	public static void checkState() {
		// always OK, since list can never be null, and there are
		// nothing in particluar is required of its element
	}
}
