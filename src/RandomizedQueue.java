import java.util.Iterator;


public class RandomizedQueue<Item> implements Iterable<Item> {
	// Your randomized queue implementation must support each randomized queue operation (besides creating an iterator) 
	// in constant amortized time and use space proportional to the number of items currently in the queue.
	private Object[] itemArray;
	private final int INITIAL_SIZE = 2;
	private int ARRAY_CAPACITY;

	private int size = 0;

	public RandomizedQueue()                 // construct an empty randomized queue
	{
		itemArray = (Item[]) new Object[INITIAL_SIZE];
		ARRAY_CAPACITY = INITIAL_SIZE;
		size = 0;

	}
	public boolean isEmpty()                 // is the queue empty?
	{
		return (size == 0);
	}
	public int size()                        // return the number of items on the queue
	{
		return size;
	}
	public void enqueue(Item item)           // add the item
	{
		//Throw a NullPointerException if the client attempts to add a null item;
		if(item == null)
			throw new NullPointerException("input item is null");
		if(size == ARRAY_CAPACITY)
			resizeItemArray_up();
		itemArray[size] = item;
		size++;	
	}
	private void resizeItemArray_up()
	{		
		Object[] newItemArray = (Item []) new Object[ARRAY_CAPACITY*2];
		for(int j=0; j<size; j++)
		{
			newItemArray[j] = itemArray[j];
		}
		itemArray = newItemArray;
		ARRAY_CAPACITY = ARRAY_CAPACITY*2;
	}
	private void resizeItemArray_dn()
	{

		Object[] newItemArray = (Item []) new Object[ARRAY_CAPACITY/2];
		for(int j=0; j<size; j++)
		{
			newItemArray[j] = itemArray[j];
		}
		itemArray = newItemArray;
		ARRAY_CAPACITY = ARRAY_CAPACITY/2;

	}
	public Item dequeue()                    // delete and return a random item
	{
		/*
		 * throw a java.util.NoSuchElementException if the client attempts to sample or dequeue an item from an empty randomized queue
		 */
		if(size == 0)
			throw new java.util.NoSuchElementException();

		/*
		 * generate a uniform random number between [0, size) to remove the item at that number.
		 * replace the item at that index with last element in the item array.
		 */
		int remInd = StdRandom.uniform(size);
		Item remItem = (Item) itemArray[remInd];
		itemArray[remInd] = itemArray[size-1];
		size--;

		if((ARRAY_CAPACITY >= INITIAL_SIZE) && (size<=(3*ARRAY_CAPACITY/8)))
			resizeItemArray_dn();

		return remItem;
	}
	public Item sample()                     // return (but do not delete) a random item
	{
		/*
		 * throw a java.util.NoSuchElementException if the client attempts to sample or dequeue an item from an empty randomized queue
		 */
		if(size == 0)
			throw new java.util.NoSuchElementException();

		int remInd = StdRandom.uniform(size);
		return (Item) itemArray[remInd];	

	}
	//your iterator implementation must support construction in time linear in the number of items
	//it must support the operations next() and hasNext() in constant worst-case time
	//you may use a linear amount of extra memory per iterator
	//The order of two or more iterators to the same randomized queue should be mutually independent;
	//each iterator must maintain its own random order.
	public Iterator<Item> iterator()         // return an independent iterator over items in random order
	{
		//throw an UnsupportedOperationException if the client calls the remove() method in the iterator;
		//throw a java.util.NoSuchElementException if the client calls the next() method in the iterator and there are no more items to return.
		return new RandomizedQueueIterator<Item>();
	}
	public static void main(String[] args)   // unit testing
	{
		RandomizedQueue<Integer> q = new RandomizedQueue<Integer>();
		for(int i=0;i<1024;i++)
			q.enqueue(i);
		System.out.println("size = " + q.size());
		Iterator<Integer> i1 = q.iterator();
		Iterator<Integer> i2 = q.iterator();
		
		while(i1.hasNext() && i2.hasNext()) {
			System.out.println(i1.next() + " , " + i2.next());
		}
		
		for(int i =0;i<10;i++)
			System.out.println("size = " + q.size() + " , " + q.dequeue());
		

	}

	private class RandomizedQueueIterator<Item> implements Iterator<Item>
	{
		private int[] randIndVec;
		private int currIndex;

		public RandomizedQueueIterator() {
			randIndVec = new int[size];
			for(int j=0;j<size;j++) {
				randIndVec[j] = j;
			}
			StdRandom.shuffle(randIndVec);
			currIndex = 0;			
		}

		public boolean hasNext() {

			return (currIndex < size);
		}

		public Item next() {
			/*
			 * throw a java.util.NoSuchElementException if the client calls the next() method in the iterator and there are no more items to return.
			 */
			if(currIndex == size)
				throw new java.util.NoSuchElementException();

			return (Item) itemArray[randIndVec[currIndex++]];
		}

		public void remove() {
			/*
			 * throw an UnsupportedOperationException if the client calls the remove() method in the iterator;
			 */
			throw new UnsupportedOperationException("remove not supported in this iterator");

		}


	}
}
