
public class PercolationStats {
	
	private double[] percThVec;
	private double percThMean, percThStdDev, confLo, confHi;
	private int numTrials;
	private int gridSize;
	
	public PercolationStats(int N, int T){
		percThVec = new double[T];
		numTrials = T;
		gridSize = N;
		
		int numOpen;
		for(int k=0; k<T; k++){
			numOpen = doPercolateExp(N);
			percThVec[k] = (double) numOpen/(N*N);
		//	System.out.println(numOpen + " ,");
		}
		//System.out.println("\n");
			
		percThMean = StdStats.mean(percThVec);
		percThStdDev = StdStats.stddev(percThVec);
		confLo = percThMean - (percThStdDev*1.96/Math.sqrt(T));
		confHi = percThMean + (percThStdDev*1.96/Math.sqrt(T));
		
	}
	private int doPercolateExp(int N){
		Percolation p = new Percolation(N);
		int numOpen = 0;
		boolean[][] openGrid = new boolean[N+1][N+1];
		
		int i,j;
		while(p.percolates() == false){
			i = StdRandom.uniform(1, N+1);
			j = StdRandom.uniform(1, N+1);
			
			if(openGrid[i][j] == false){
				openGrid[i][j] = true;
				p.open(i, j);
				numOpen++;
			}
		}
		return numOpen;
		
	}
	public double mean(){
		return percThMean;
	}
	
	public double stddev(){
		return percThStdDev;
		
	}
	
	public double confidenceLo(){
		return confLo;
	}
	
	public double confidenceHi(){
		return confHi;
	}
	
	public static void main(String[] args){
		if(args.length != 2){
			System.out.println("input not understood\n");
			System.out.println("usage: javac-algs4 PercolationStats N T\n");
			return;
		}
		
		int N = Integer.parseInt(args[0]);
		int T = Integer.parseInt(args[1]);
		/*int N = 2;
		int T = 10;*/
		System.out.println("N = " + N + " T = " + T +"\n");
		
		PercolationStats ps = new PercolationStats(N, T);
		
		System.out.println("mean \t = " + ps.mean() + "\n");
		System.out.println("stdDev \t = " + ps.stddev()+ "\n");
		System.out.println("95% Confidence Interval\t =" + ps.confidenceLo() + " , " +  ps.confidenceHi() + "\n");
		
	}

}
