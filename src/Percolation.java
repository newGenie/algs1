/**
 * @author RaviTeja Chinta
 *
 */
public class Percolation {
	/**
	 * wQF is reference to union find object.
	 */
	private WeightedQuickUnionUF wQF; //a reference to union find object
	private final int TOP	=0;
	private final int BOTTOM;
	private int gridSize;
	private boolean[] gridOpen;
	private long numOpen;

	/**
	 * This is the constructor of the class.
	 * @param gridSize the size of grid
	 */
	public Percolation(final int gridSize) {
		if(gridSize <= 0)
			throw new IllegalArgumentException();
		
		this.gridSize = gridSize;
		
		
		wQF = new WeightedQuickUnionUF(gridSize*gridSize+2);
		gridOpen = new boolean[gridSize*gridSize + 2];
		numOpen = 0;
		
		
		BOTTOM = gridSize*gridSize+2-1;
		
		gridOpen[TOP] = true;
		gridOpen[BOTTOM] = true;
		
		/**
		 * Connect the TOP and BOTTOM to the top row and bottom row of the grid
		 */
		for (int k=1; k<=gridSize; k++){
			wQF.union(TOP, indexMap(1,k));
			wQF.union(BOTTOM, indexMap(gridSize,k));
		}
		
		
		
	}
	
	public boolean percolates() {
		return wQF.connected(TOP, BOTTOM);
	}
	public boolean isFull(int i, int j) {
		checkForIndexExceptions(i, j);
		return (isOpen(i,j) && wQF.connected(TOP, indexMap(i, j)));
	}
	public boolean isOpen(int i, int j){
		checkForIndexExceptions(i, j);
		return gridOpen[indexMap(i,j)];
	}
	
	public void open(int i, int j) {
		checkForIndexExceptions(i, j);
		/**
		 * Open a site at index i, j
		 */
		gridOpen[indexMap(i,j)] = true;
		numOpen ++;
		
		/**
		 * Connect with open sites around the new open site (top, right, bottom, left)
		 */
		if(i-1 >= 1 && isOpen(i-1,j))
			wQF.union(indexMap(i-1,j), indexMap(i,j));
		
		if(j-1 >= 1 && isOpen(i,j-1))
			wQF.union(indexMap(i,j-1), indexMap(i,j));
		
		if(i+1 <= gridSize && isOpen(i+1,j))
			wQF.union(indexMap(i+1,j), indexMap(i,j));
		
		if(j+1 <= gridSize && isOpen(i,j+1))
			wQF.union(indexMap(i,j+1), indexMap(i,j));
	}
	private void checkForIndexExceptions(int i, int j) {
		if(i<=0 || i>gridSize)
			throw new IndexOutOfBoundsException();
		if(j<=0 || j>gridSize)
			throw new IndexOutOfBoundsException();
	}
	
	private int indexMap(int i, int j) {
		return (i-1)*gridSize + j;
	}
	

	/**
	 * Main function to test the class.
	 * @param args command line args
	 */
	public static void main(final String[] args) {
		int gridSize = 3;
		int i, j;
		Percolation p = new Percolation (gridSize);
		while(p.percolates() == false){
			i = StdRandom.uniform(1, gridSize+1);
			j = StdRandom.uniform(1, gridSize+1);
			
			p.open(i,j);
			System.out.println("i="+i+" j="+j + " percolate= " + p.percolates());
		}
		
	}
}
