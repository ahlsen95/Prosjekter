package inf101.collections;

public class SortedListHelper {

	/**
	 * Check if the elements in a collection appears in sorted order
	 * 
	 * @param coll A collection
	 * @return True if the elements are in sorted order
	 */
	public static <E extends Comparable<E>> boolean isSorted(ICollection<E> coll) {
		E previous = null;
		
		for(E e : coll){
			if(previous == null) {
				previous = e;
				continue;
			}
			if(!(e.compareTo(previous)< 0))
				return false;
			previous = e;
		}
		return true;
	}
}
