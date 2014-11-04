import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
	private int size = 0;
	
	private DQ_El<Item> head;
	private DQ_El<Item> tail;
	
	public Deque()                           // construct an empty deque
	{
		size = 0;
		head = null;
		tail = null;
	}
	public boolean isEmpty()                 // is the deque empty?
	{
		return (size==0);
	}
	public int size()                        // return the number of items on the deque
	{
		return size;
	}
	public void addFirst(Item item)          // insert the item at the front
	{
		//Throw a NullPointerException if the client attempts to add a null item
		if(item == null)
			throw new NullPointerException("item is null in addFirst\n");
		
		if(size == 0)
		{
			head = new DQ_El<Item>(null, null, item);
			tail = head;
		}
		else
		{
			DQ_El<Item> temp = new DQ_El<Item>(head, null, item);
			head.prev = temp;
			head = temp;
		}
		size++;

	}
	public void addLast(Item item)           // insert the item at the end
	{
		//Throw a NullPointerException if the client attempts to add a null item
		if(item == null)
			throw new NullPointerException("item is null in addLast\n");
		
		if(size == 0)
		{
			head = new DQ_El<Item>(null, null, item);
			tail = head;
		}
		else
		{
			DQ_El<Item> temp = new DQ_El<Item>(null, tail, item);
			tail.next = temp;
			tail = temp;
		}
		size++;
	}
	
	public Item removeFirst()                // delete and return the item at the front
	{
		//throw a java.util.NoSuchElementException if the client attempts to remove an item from an empty deque
		if(size == 0)
			throw new NoSuchElementException("empty deque");
		Item item = head.item;;
		if(size == 1)
		{			
			head = null;
			tail = null;
		}
		else
		{
			head = head.next;
			head.prev = null;
		}
		size--;
			
		return item;
	}
	public Item removeLast()                 // delete and return the item at the end
	{
		//throw a java.util.NoSuchElementException if the client attempts to remove an item from an empty deque
		if(size == 0)
			throw new NoSuchElementException("empty deque");
		Item item = tail.item;
		if(size == 1)
		{			
			head = null;
			tail = null;
		}
		else
		{
			tail = tail.prev;
			tail.next = null;
		}
		size--;
		
		return item;
		

	}
	public Iterator<Item> iterator()         // return an iterator over items in order from front to end
	{
		//throw an UnsupportedOperationException if the client calls the remove() method in the iterator
		//throw a java.util.NoSuchElementException if the client calls the next() method in the iterator and there are no more items to return.
		return new DequeIterator();
	}
	public static void main(String[] args)   // unit testing
	{
		Deque<Integer> d = new Deque<Integer> ();
		
		try
		{
			System.out.println(d.removeFirst());
		}
		catch (Exception e){
			System.out.println(e);
		}
		
		try
		{
			System.out.println(d.removeLast());
		}
		catch (Exception e){
			System.out.println(e);
		}
		d.addLast(1);
		d.addFirst(2);
		d.addFirst(3);
		d.addFirst(4);
		//System.out.println(d.removeLast());
		/*System.out.println(d.removeLast());
		System.out.println(d.removeFirst());
		System.out.println(d.removeLast());
		System.out.println(d.removeFirst());*/
		Iterator<Integer> i = d.iterator();
		while(i.hasNext())
			System.out.println(i.next());
		System.out.println(i.next());
		
	}

	private class DQ_El<Item>{
		private DQ_El<Item> next;
		private DQ_El<Item> prev;
		Item item;
		
		public DQ_El(DQ_El<Item> next, DQ_El<Item> prev, Item item)
		{
			this.next = next;
			this.prev = prev;
			this.item = item;
		}
	}
	private class DequeIterator implements Iterator<Item> {
        private DQ_El<Item> current = head;
        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
        }
    }
}
