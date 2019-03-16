/**
 * Implements various merge style algorithms.
 * 
 * Completion time: (your completion time)
 *
 * @author Collin Connolly, Acuna, Sedgewick and Wayne
 * @verison 1.0
 */
import java.util.Random;

public class ConnollyMerging {
     
    /**
     * Entry point for sample output.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Queue q1 = new ListQueue(); q1.enqueue("T"); q1.enqueue("R"); q1.enqueue("O"); q1.enqueue("L"); q1.enqueue("E");
        Queue q2 = new ListQueue(); q2.enqueue("X"); q2.enqueue("S"); q2.enqueue("P"); q2.enqueue("M"); q2.enqueue("E"); q2.enqueue("A");        
        Queue q3 = new ListQueue(); q3.enqueue(20); q3.enqueue(17); q3.enqueue(15); q3.enqueue(12); q3.enqueue(5);
        Queue q4 = new ListQueue(); q4.enqueue(18); q4.enqueue(16); q4.enqueue(13); q4.enqueue(12); q4.enqueue(4); q4.enqueue(1);       
        
        //Q1 - sample test cases
        Queue merged1 = mergeQueues(q1, q2);
        System.out.println(merged1.toString());
        Queue merged2 = mergeQueues(q3, q4);
        System.out.println(merged2.toString());
        
        //Q2 - sample test cases
        //String[] a = {"S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        String[] a = {"S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        sort(a);
        assert isSorted(a);
        show(a);
        
        //Q3 - sample test cases
        String[] b = {"S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        shuffle(b);
        show(b);
        
        shuffle(b);
        show(b);
    }
    
    public static Queue mergeQueues(Queue<Comparable> q1, Queue<Comparable> q2) {
		
		ListQueue<Comparable> newQueue = new ListQueue();
		
		// comparing elements until one array empties
		while(!q1.isEmpty() && !q2.isEmpty()) { // q1 iterator
			if(less(q1.front(), q2.front())) {
				newQueue.enqueue(q2.dequeue());
			} else {
				newQueue.enqueue(q1.dequeue());
			}
		}
		
		// Emptying out existing elements
		while(!q1.isEmpty())
			newQueue.enqueue(q1.dequeue());
			
		while(!q2.isEmpty())
			newQueue.enqueue(q2.dequeue());
		
		return newQueue;
		}
    
    /**
     * Sorts an input object array in ascending order
     * @param a - object to be sorted
     */
    public static void sort(Comparable[] a) {
		
    	// creating sub-arrays
		Comparable[] firstHalf, lastHalf;
		
		// base case
		if (a.length == 1)
			return;
		
		// creating arrays
		int middle = a.length / 2;
		firstHalf  = new Comparable[middle];
		lastHalf = new Comparable[a.length - middle];
		
		// polulating arrays
		for (int i = 0; i < middle; i++)
			firstHalf[i] = a[i];  
		for (int i = 0; i < a.length - middle; i++)
			lastHalf[i] = a[i + middle];
		
		// Recursive splits
		sort(firstHalf);      
		sort(lastHalf);    
		
		// Merging after stack completes - I passed in the parent array
		// I hope that's fine :x
		merge(a, firstHalf, lastHalf);
	}

    /**
     *  merges 2 arrays in ascending order
     * @param c - output array
     * @param a - input array 1
     * @param b - input array 2
     */
    public static void merge(Comparable[] c, Comparable[] a, Comparable[] b) {
	//public static Comparable[] merge(Comparable[] a, Comparable[] b) {
		
		//Comparable[] c = new Comparable[a.length + b.length];
		
		// iterators
		int count = 0; // parent array
		int i = 0;	   // first half
		int j = 0;	   // last half
		
		// compare elements
		while(i < a.length && j < b.length) {	
			if(less(a[i], b[j])) {
				c[count++] = a[i++];
			} else {
				c[count++] = b[j++];
			}
		}
		
		// populate remaining
		while(i < a.length) 
			c[count++] = a[i++];	
		
		while(j < b.length) 
			c[count++] = b[j++];
	}
    
    /* =^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^=^
     * WHY DOES THIS PROGRAM RUN IN NLOGN TIME?
     * This program runs in nlogn time because the input is
     * run through n times. This input is then passed broken
     * in half log(n) times. These processes cause the time
     * taken to be n*log(n)
     */
    
    
    /**
     * Shuffles an input object randomly
     * @param a - object to be shuffled
     */
    public static void shuffle(Object[] a) {
    	// creating sub-arrays
		Object[] firstHalf, lastHalf;
		
		// base case
		if (a.length == 1)
			return;
		
		// creating arrays
		int middle = a.length / 2;
		firstHalf  = new Comparable[middle];
		lastHalf = new Comparable[a.length - middle];
		
		// polulating arrays
		for (int i = 0; i < middle; i++)
			firstHalf[i] = a[i];  
		for (int i = 0; i < a.length - middle; i++)
			lastHalf[i] = a[i + middle];
		
		// Recursive splits
		shuffle(firstHalf);      
		shuffle(lastHalf);    
		
		// Merging after stack completes - I passed in the parent array
		// I hope that's fine :x
		randomize(a, firstHalf, lastHalf);
    }
    
    /**
     * Randomizes elements in a and b to be output into c
     * @param c - output array
     * @param a - input array 1
     * @param b - input array 2
     */
	private static void randomize(Object[] c, Object[] a, Object[] b) {
		// iterators
		int count = 0; // parent array
		int i = 0;	   // first half
		int j = 0;	   // last half
		Random rand = new Random();
		
		// compare elements
		while(i < a.length && j < b.length) {	
			if(rand.nextBoolean()) {
				c[count++] = a[i++];
			} else {
				c[count++] = b[j++];
			}
		}
		
		// populate remaining
		while(i < a.length) 
			c[count++] = a[i++];	
		
		while(j < b.length) 
			c[count++] = b[j++];
	}

    //sorting helper from text
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    //sorting helper from text
    private static void show(Comparable[] a) {
        for (Comparable a1 : a)
            System.out.print(a1 + " ");

        System.out.println();
    }
    
    //sorting helper from text
    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i-1]))
                return false;
        
        return true;
    }
}