import java.util.Arrays;


public class Brute {
	
	private static boolean isCollinear(Point p0, Point p1, Point p2, Point p3){
		
		if( (p0.slopeTo(p1) == p0.slopeTo(p2)) && (p0.slopeTo(p2) == p0.slopeTo(p3)) ) {
//			System.out.println(p0.toString() + p1.toString() + p0.slopeTo(p1) + "," + p0.slopeTo(p2) + "," + p0.slopeTo(p2) + "," + p0.slopeTo(p3));
			return true;
		}
			
		else
			return false;
	}
	
	
	private static void insertionSort(Point[] points){
		int j, k;
		
		for(j=1; j<points.length; j++) {
			k=j;
			while(k>0 && points[k].compareTo(points[k-1])<0) {
				Point temp = points[k];
				points[k] = points[k-1];
				points[k-1] = temp;
				k--;
			}
		}
	}
	
	public static void main(String[] args) {
		String fname = args[0];
		In in = new In(fname);
		
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);
		/*Point[] ps = new Point[] {new Point(StdRandom.uniform(4),StdRandom.uniform(4)), 
				new Point(StdRandom.uniform(4),StdRandom.uniform(4)),
				new Point(StdRandom.uniform(4),StdRandom.uniform(4)),
				new Point(StdRandom.uniform(4),StdRandom.uniform(4))};
		System.out.println(Arrays.toString(ps));
		insertionSort(ps);
		System.out.println(Arrays.toString(ps));
		*/
		
		int numPoints = in.readInt();
		Point[] points = new Point[numPoints];
		
		for(int i=0; i<numPoints; i++) {
			points[i] = new Point(in.readInt(),in.readInt());
		}
		insertionSort(points);
		for(Point p:points) {
			p.draw();
		}
		
		for(int i0=0; i0<numPoints; i0++) {
			for(int i1=i0+1; i1<numPoints; i1++) {
				for(int i2=i1+1; i2<numPoints; i2++) {
					for(int i3=i2+1; i3<numPoints; i3++) {
						if(isCollinear(points[i0], points[i1], points[i2], points[i3])) {
//							printInOrder(points[i0], points[i1], points[i2], points[i3]);
							System.out.println(points[i0] + " -> " + points[i1] + " -> " + points[i2] + " -> " + points[i3] );
							points[i0].drawTo(points[i1]);
							points[i1].drawTo(points[i2]);
							points[i2].drawTo(points[i3]);
						}
					}
				}
			}
		}
	}

}
