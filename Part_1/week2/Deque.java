import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
   
	private int size;
    private final Node head;
    private final Node tail;

    private class Node {
    	Item item;
    	Node prev;
    	Node next;

    	Node(Item item) {
    		this.item = item;
    	} 
    }

    public Deque() {
 		size = 0;
 		head = new Node(null);
 		tail = new Node(null);
 		head.next = tail;
 		tail.prev = head;
    }                           // construct an empty deque

    public boolean isEmpty() {
    	return size == 0;
    }                 // is the deque empty?

    public int size() {
    	return size;
    }                        // return the number of items on the deque
    
    public void addFirst(Item item) {
    	if (item == null) {
    		throw new IllegalArgumentException("Item cannot be null.");
    	}
    	Node node = new Node(item);
    	node.next = head.next;
    	head.next.prev = node;
    	node.prev = head;
    	head.next = node;
    	size++;
    }         // add the item to the front
    
    public void addLast(Item item) {
    	if (item == null) {
    		throw new IllegalArgumentException("Item cannot be null.");
    	}
    	Node node = new Node(item);
    	node.prev = tail.prev;
    	node.next = tail;
    	tail.prev.next = node;
    	tail.prev = node;
    	size++;
    }          // add the item to the end
    
    public Item removeFirst() {
    	if (size == 0) {
    		throw new NoSuchElementException("The deque is empty.");
    	}
    	Node node = head.next.next;
    	Item item = head.next.item;
    	head.next = node;
    	node.prev = head;
    	size--;
    	return item;
    }               // remove and return the item from the front
    
    public Item removeLast() {
    	if (size == 0) {
    		throw new NoSuchElementException("The deque is empty.");
    	}
    	Node node = tail.prev.prev;
    	Item item = tail.prev.item;
    	node.next = tail;
    	tail.prev = node;
    	size--;
    	return item;
    }                // remove and return the item from the end
    
    @Override
    public Iterator<Item> iterator() {
    	return new HeadFirstIterator();
    }         // return an iterator over items in order from front to end
    
    private class HeadFirstIterator implements Iterator<Item> {
    	private Node curr = head;

    	@Override
    	public boolean hasNext() {
    		return curr.next != tail;
    	}

    	@Override
    	public Item next() {
    		if (!hasNext()) {
    			throw new NoSuchElementException();
    		}
    		curr = curr.next;
    		return curr.item;
    	}

    	@Override
    	public void remove() {
    		throw new UnsupportedOperationException();
    	} 
    }

    @Override
    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	for (Item item: this) {
    		sb.append("," + item);
    	}
    	if (!sb.toString().isEmpty()){
    		sb.delete(0,1);
    	}
    	return "[" + sb.toString() + "]";
    }

    public static void main(String[] args) {

        // StdOut.println("Tests start.");

        // // Test 1: public operations
        // Deque<Integer> d1 = new Deque<>();
        // StdOut.println("Test 1A passed? " + d1.isEmpty());
        // StdOut.println("Test 1B passed? " + d1.toString().equals("[]"));
        // d1.addLast(1);
        // d1.addLast(2);
        // StdOut.println("Test 1C passed? " + d1.toString().equals("[1,2]"));
        // StdOut.println("Test 1D passed? " + (d1.size() == 2));
        // StdOut.println("Test 1E passed? " + (d1.iterator().next() == 1));
        // d1.addFirst(0);
        // StdOut.println("Test 1F passed? " + d1.toString().equals("[0,1,2]"));
        // d1.removeLast();
        // StdOut.println("Test 1G passed? " + d1.toString().equals("[0,1]"));
        // d1.removeFirst();
        // StdOut.println("Test 1H passed? " + d1.toString().equals("[1]"));
        // d1.removeFirst();
        // StdOut.println("Test 1I passed? " + d1.toString().equals("[]"));
        // StdOut.println("Test 1J passed? " + d1.isEmpty());
        // StdOut.println("Test 1H passed? " + !d1.iterator().hasNext());

        // // Test 2: exceptions
        // Deque<Integer> d2 = new Deque<>();
        // try {
        //     d2.removeFirst();
        //     StdOut.println("Test 2A passed? " + false);
        // } catch (Exception e) {
        //     boolean result = e instanceof NoSuchElementException;
        //     StdOut.println("Test 2A passed? " + result);
        // }
        // try {
        //     d2.removeLast();
        //     StdOut.println("Test 2B passed? " + false);
        // } catch (Exception e) {
        //     boolean result = e instanceof NoSuchElementException;
        //     StdOut.println("Test 2B passed? " + result);
        // }
        // try {
        //     d2.addFirst(null);
        //     StdOut.println("Test 2C passed? " + false);
        // } catch (Exception e) {
        //     boolean result = e instanceof IllegalArgumentException;
        //     StdOut.println("Test 2C passed? " + result); 
        // }
        // try {
        //     d2.addLast(null);
        //     StdOut.println("Test 2D passed? " + false); 
        // } catch (Exception e) {
        //     boolean result = e instanceof IllegalArgumentException;
        //     StdOut.println("Test 2D passed? " + result);
        // }
        // try {
        //     d2.iterator().remove();
        //     StdOut.println("Test 2F passed? " + false);
        // } catch (Exception e) {
        //     boolean result = e instanceof UnsupportedOperationException;
        //     StdOut.println("Test 2F passed? " + result);
        // }
        // try {
        //     d2.iterator().next();
        //     StdOut.println("Test 2G passed? " + false);
        // } catch (Exception e) {
        //     boolean result = e instanceof NoSuchElementException;
        //     StdOut.println("Test 2G passed? " + result);
        // }

        // // Test 3: types
        // Deque<String> d3a = new Deque<>();
        // d3a.addFirst("Hello Algorithm");
        // StdOut.println("Test 3A passed? " + true);
        // Deque<Double> d3b = new Deque<>();
        // d3b.addLast(3.1415926);
        // StdOut.println("Test 3B passed? " + true);

        // StdOut.println("Tests finished.");
    }
}