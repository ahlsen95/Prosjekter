package inf101.collections;

public class MySortedList<E extends Comparable<E>> extends MyCollection<E> implements ISortedList<E> {

	@Override
	public E get(int i) {
		return list.get(i);
	}

	@Override
	public void removeFirst() {
		if(list.isEmpty()){
			list.remove(0);
			checkState(this);
		}
	}
	public void removeLast(){
		if(list.isEmpty()){
			list.remove(list.size()-1);
			checkState(this);
		}
	}
	public void add(E elt){
		for(int i=0; i < list.size(); i++){
			if(list.get(i).compareTo(elt) <= 0);
			list.add(i, elt);
		}
		checkState(this);
	}
	public E remove(){
		E e = super.remove();
		checkState(this);
		return e;
	}
	@Override
	public E remove(E elt){
		E e = super.remove(elt);
			checkState(this);
			return e;
	}
	
	public static void checkState(MySortedList msl){
		if(!SortedListHelper.isSorted(msl)){
			throw new IllegalStateException("Listen er ikke sortert!");
		}
	}

}
