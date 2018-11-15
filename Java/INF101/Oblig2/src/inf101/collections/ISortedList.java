package inf101.collections;

public interface ISortedList<E extends Comparable<E>> extends ICollection<E> {
	/**
	 *  Få tak i liste.
	 * 
	 * @param i
	 * 	
	 * 	Element E
	 * 
	 * @return elementet på plass i.
	 */
	E get(int i);
	
	/**
	 * Slette det første i listen.
	 *  
	 */
	void removeFirst();
	
	/**
	 * Fjerne siste i listen.
	 * 
	 */
	void removeLast();
}
