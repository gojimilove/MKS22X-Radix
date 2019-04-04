public class Radix {
	@SuppressWarnings("unchecked")
	public static void radixsort(int[]data){
			//PRINT unsorted data
			// System.out.print("Unsorted data: [");
			// for (int i = 0; i < data.length; i++) {
			// 	System.out.print(data[i]);
			// 	if (i < data.length-1) System.out.print(", ");
			// }
			// System.out.println("]");

	  	MyLinkedList<Integer>[] buckets = new MyLinkedList[20];
	  	for (int i = 0; i < buckets.length; i++) {
	  		buckets[i] = new MyLinkedList<Integer>();
	  	}

	  	//first digit
	  	// if number is negative buckets[9-(first digit of num)] gets num added to it
	    // else buckets[(first digit of num) + 10] gets num added to it
	  	for (int i = 0; i < data.length; i++) {
	  		if (data[i] < 0) {
	  			buckets[9-(data[i]%10 * (-1))].add(data[i]);
	  		} else {
	      	buckets[data[i]%10 + 10].add(data[i]);
	      }
	    }

	    //PRINT out buckets
	    // for (int i = 0; i < buckets.length; i++) {
	    // 	System.out.println(buckets[i]);
	    // }

	    MyLinkedList<Integer> list = new MyLinkedList();
	    //merge buckets into list
	    for (int i = 0; i < buckets.length; i++) {
	    	// System.out.println(buckets[i]);
	    	// System.out.println("LIST: "+list);
	    	if (buckets[i].size() > 0) {
	    		list.extend(buckets[i]);	
	    	}
	    }
	    
	    // System.out.println("\nLIST: "+list+"\n===========\n\n");
	    
	    int k = 0;
	    //find max number of digits
	    for (int i = 0; i < data.length; i++) {
	    	int l = Integer.toString(data[i]).length();
	    	if (data[i] < 0) l--;
	    	if (l > k) k = l;
	    }
	    k--; //already sorted once
	    // System.out.println("HI HELLO K EQUALS "+k);

	    for (int i = 0; i < k; i++) {
	    	//empty list back into buckets
	    	int len = list.size();
	    	for (int j = 0; j < len; j++) {
	    		Integer x = list.removeFront();
	    		if (x < 0) {
	    			buckets[9-(x/((int)(Math.pow(10, i+1))) %10 * (-1))].add(x);
	    		}else {
	    			buckets[x/((int)(Math.pow(10, i+1))) %10 + 10].add(x);
	    		}
	    	}
	    	// list.clear(); //fix remove later but for now clear works

	    	// for (int b = 0; b < buckets.length; b++) {
		    // 	System.out.println(buckets[b]);
		    // }
		    // System.out.println("\nLIST: "+list+"\n");

	    	//buckets to list
	    	for (int n = 0; n < buckets.length; n++) {
		    	if (buckets[n].size() > 0) {
		    		list.extend(buckets[n]);	
		    	}
		    }

		    // for (int b = 0; b < buckets.length; b++) {
		    // 	System.out.println(buckets[b]);
		    // }
		    // System.out.println("\nLIST: "+list+"\n");
	    }

	    //list back into original array
	    int len = list.size();
	    for (int i = 0; i < len; i++) {
	    	data[i] = list.removeFront();
	    }
	    
	    //print data
	  // 	System.out.print("Sorted data: [");
			// for (int i = 0; i < data.length; i++) {
			// 	System.out.print(data[i]);
			// 	if (i < data.length-1) System.out.print(", ");
			// }
			// System.out.println("]");
	}

	public static void main(String[]args) {
	    int[] tester = new int[]{645345345, 457, 223, 32, 386, 190, 11, 74, 888, 349, -81, -90, -554, -62, -783, -785, -6, -97, -898, -9};
	    //int[] tester = new int[]{0, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000};
	    radixsort(tester);
	    System.out.print("Tester: [");
			for (int i = 0; i < tester.length; i++) {
				System.out.print(tester[i]);
				if (i < tester.length-1) System.out.print(", ");
			}
			System.out.println("]");
	}
}
