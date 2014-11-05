/*************************************************************************
 * Name:
 * Email:
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new Comparator<Point> () {
    	// YOUR DEFINITION HERE
    	public int compare(Point o1, Point o2) {
    		double slope1, slope2;
			slope1 = slopeTo(o1);
			slope2 = slopeTo(o2);
			if(slope1 < slope2)
				return -1;
			else if(slope1>slope2)
				return 1;
			else
				return 0;
    		
    	}
    	
    };

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        /* YOUR CODE HERE */
    	if((this.x == that.x) && (this.y == that.y))
    		return Double.NEGATIVE_INFINITY;
    	else if(this.x == that.x)
    		return Double.POSITIVE_INFINITY;
    	else if (this.y == that.y)
    		return 0.0;
    	else
    		return  ((double)(that.y - this.y)/(that.x - this.x));
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        /* YOUR CODE HERE */
    	if(this.y == that.y)
    	{
    		return this.x - that.x;
    	}
    	else
    	{
    		return this.y - that.y;
    	}
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {
        /* YOUR CODE HERE */
    	/*Point p0 = new Point(0,0);
    	Point p1 = new Point(0,1);
    	Point p2 = new Point(1,0);
    	Point p3 = new Point(1,1);
    	
    	assert p0.slopeTo(p0) == Double.NEGATIVE_INFINITY;
    	assert p0.slopeTo(p1) == Double.POSITIVE_INFINITY;
    	assert p1.slopeTo(p0) == Double.POSITIVE_INFINITY;
    	assert p0.slopeTo(p2) == 0.0;
    	assert p0.slopeTo(p3) == 1.0;
    	
    	assert Math.signum(p0.compareTo(p1)) == -1;
    	assert Math.signum(p2.compareTo(p3)) == -1;
    	assert Math.signum(p3.compareTo(p2)) ==  1;
    	assert Math.signum(p3.compareTo(p3)) ==  0;
    	
    	assert p0.SLOPE_ORDER.compare(p1,p2) == 1;
    	assert p0.SLOPE_ORDER.compare(p0,p2) == -1;*/
    	
    	Point p4 = new Point(1234, 5678);
    	Point p5 = new Point(14000, 10000);
    	System.out.println(p4.slopeTo(p5));
    }

}




