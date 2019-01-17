import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	private double[] thresholds;
   
    public PercolationStats(int n, int trials) {
    	if ((n <= 0) || (trials <= 0)) {
    		throw new IndexOutOfBoundsException();
    	}
    	thresholds = new double[trials];
    	for (int i = 0; i < trials; i++) {
    		Percolation curTrial = new Percolation(n);
    		int openCount = 0;
    		int row, col;
    		while(!curTrial.percolates()) {
    			do {
    				row = StdRandom.uniform(1, n+1);
    				col = StdRandom.uniform(1, n+1);
    			} while(curTrial.isOpen(row, col));
    			curTrial.open(row, col);
    			openCount++;
    		}
    		thresholds[i] = Double.valueOf(openCount)/(n*n);
    	}
    }    // perform trials independent experiments on an n-by-n grid

    public double mean() {
    	return StdStats.mean(thresholds);
    }                          // sample mean of percolation threshold

    public double stddev() {
    	return StdStats.stddev(thresholds);
    }                       // sample standard deviation of percolation threshold
    public double confidenceLo() {
    	return mean() - 1.96 * stddev() / Math.sqrt(thresholds.length);
    }                 // low  endpoint of 95% confidence interval
    public double confidenceHi() {
    	return mean() + 1.96 * stddev() / Math.sqrt(thresholds.length);
    }                 // high endpoint of 95% confidence interval

    public static void main(String[] args) {
    	int testN = Integer.parseInt(args[0]);
    	int testTrials = Integer.parseInt(args[1]);
    	PercolationStats model = new PercolationStats(testN, testTrials);
    	System.out.println("mean                    = " + model.mean());
        System.out.println("stddev                  = " + model.stddev());
        System.out.println("95% confidence interval = ( " + model.confidenceLo() + 
        											", " + model.confidenceHi() +" )");
    }       // test client (described below)
}