public class Radix {
	@SuppressWarnings("unchecked")
	public static void radixsort(int[]data){
	  	MyLinkedList<Integer>[] buckets = new MyLinkedList[20];
	  	for (int i = 0; i < buckets.length; i++) {
	  		buckets[i] = new MyLinkedList<Integer>();
	  	}

	  	//first digit
	  	for (int i = 0; i < data.length; i++) {
	  		if (data[i] < 0) {
	  			buckets[9-(data[i]%10 * (-1))].add(data[i]);
	  		} else {
	      	buckets[data[i]%10 + 10].add(data[i]);
	      }
	    }

	    //print out buckets
	    for (int i = 0; i < buckets.length; i++) {
	    	System.out.println(buckets[i]);
	    }

	    MyLinkedList<Integer> list = new MyLinkedList();
	    //merge buckets into list
	    for (int i = 0; i < buckets.length; i++) {
	    	// System.out.println(buckets[i]);
	    	// System.out.println("LIST: "+list);
	    	if (buckets[i].size() > 0) {
	    		list.extend(buckets[i]);	
	    	}
	    }
	    System.out.println("\nLIST: "+list+"\n===========\n\n");
	    int k = 0;
	    //find max number of digits
	    for (int i = 0; i < data.length; i++) {
	    	int l = Integer.toString(data[i]).length();
	    	if (data[i] < 0) l--;
	    	if (l > k) k = l;
	    }
	    k--; //already sorted once
	    //System.out.println("HI HELLO K EQUALS "+k);

	    for (int i = 0; i < k; i++) {
	    	//empty list back into buckets
	    	for (int j = 0; j < list.size(); j++) {
	    		Integer x = list.getNode(j).getData();
	    		if (x < 0) {
	    			buckets[9-(x/((int)(Math.pow(10, i+1))) %10 * (-1))].add(x);
	    		}else {
	    			buckets[x/((int)(Math.pow(10, i+1))) %10 + 10].add(x);
	    		}
	    	}
	    	list.clear(); //fix remove later but for now clear works

	    	for (int b = 0; b < buckets.length; b++) {
		    	System.out.println(buckets[b]);
		    }
		    System.out.println("\nLIST: "+list+"\n");

	    	//buckets to list
	    	for (int n = 0; n < buckets.length; n++) {
		    	if (buckets[n].size() > 0) {
		    		list.extend(buckets[n]);	
		    	}
		    }

		    for (int b = 0; b < buckets.length; b++) {
		    	System.out.println(buckets[b]);
		    }
		    System.out.println("\nLIST: "+list+"\n");
	    }
    
	    //starting with tens place (if it exists)
	      //if number is negative buckets[9-(first digit of num)] gets num added to it
	      //else buckets[(first digit of num) + 10] gets num added to import junit.framework.TestCase;
	    //move on to next place value, repeat
	}

	public static void main(String[]args) {
	    int[] tester = new int[]{645345345, 457, 223, 32, 386, 190, 11, 74, 888, 349, -81, -90, -554, -62, -783, -785, -6, -97, -898, -9};
	    radixsort(tester);
	}
}
