package inf101.collections;

public interface ISortedList<E extends Comparable<E>> extends ICollection<E> {
	/**
	 *  F� tak i liste.
	 * 
	 * @param i
	 * 	
	 * 	Element E
	 * 
	 * @return elementet p� plass i.
	 */
	E get(int i);
	
	/**
	 * Slette det f�rste i listen.
	 *  
	 */
	void removeFirst();
	
	/**
	 * Fjerne siste i listen.
	 * 
	 */
	void removeLast();
}
