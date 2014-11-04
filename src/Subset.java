
public class Subset {
   public static void main(String[] args)
   {
	   int k = Integer.parseInt(args[0]);
	   RandomizedQueue<String> q = new RandomizedQueue<String>();
	   for(int j =1; j<args.length; j++) {
		   q.enqueue(args[j]);
	   }
	   
	   for(int j=0; j<k; j++) {
		   System.out.println(q.dequeue());
	   }
	   		   
   }
}
