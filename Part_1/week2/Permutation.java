import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.NoSuchElementException;

public class Permutation {

    public static void main(String[] args) {
    	// for (String arg: args) {
    	// 	System.out.println(arg);
    	// }
    	final int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        // String token;
        // do {
        // 	token = StdIn.readString();
        // 	queue.enqueue(token);
        // } while (token != null && token != "");
		
        while (true)  {
        	try {
        		String token = StdIn.readString();
        		queue.enqueue(token);
        	} catch (NoSuchElementException e) {
        		break;
        	}
        	
        }

        for (int i = 0; i < k; i++) {
        	// StdOut.println(queue.size());
            StdOut.println(queue.dequeue());
        }
   }
}