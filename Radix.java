public class Radix {
	@SuppressWarnings("unchecked")
	public static void radixsort(int[]data){
	  	MyLinkedList<Integer>[] buckets = new MyLinkedList[20];
	  	for (int i = 0; i < buckets.length; i++) {
	  		buckets[i] = new MyLinkedList<Integer>();
	  	}
	  	//first digit
	  	for (int i = 0; i < data.length; i++) {
	      buckets[data[i]%10 + 10].add(data[i]);
	    }
	    //print out buckets
	    for (int i = 0; i < buckets.length; i++) {
	    	System.out.println(buckets[i]);
	    }
	    MyLinkedList<Integer> list = new MyLinkedList();
	    for (int i = 0; i < buckets.length; i++) {
	    	System.out.println(buckets[i]);
	    	System.out.println("LIST: "+list);
	    	if (buckets[i].size() > 0) {
	    		list.extend(buckets[i]);
	    		System.out.println("EXTENDED LIST: "+list);
	    	}
	    }

	    // int[] buckets2 = new int[20];
	    // for (int i = 0; i < buckets.length; i++) {
	    //   if (buckets[i] != 0) {
	    //     buckets2[(buckets[i]/10)%10 + 10] = buckets[i];
	    //   }
	    // }
	    // printArray(buckets2);
	    //starting with ones place
	      //if number is negative buckets[9-(first digit of num)] gets num added to it
	      //else buckets[(first digit of num) + 10] gets num added to import junit.framework.TestCase;
	    //move on to next place value, repeat
	}

	public static void printArray(int[] data) {
	    String s = "[";
	    for (int i = 0; i < data.length; i++) {
	      s+=data[i];
	      if (i < data.length-1) s+=", ";
	    }
	    s+="]";
	    System.out.println(s);
	}

	public static void main(String[]args) {
	    int[] tester = new int[]{45, 13, 57, 23, 32, 86, 90};
	    radixsort(tester);
	}
}
