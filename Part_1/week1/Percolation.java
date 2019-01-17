import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private WeightedQuickUnionUF model;
	private int sideLen;
	private boolean[] stateOpen;
	private int top;
	private int bottom;
	


	public Percolation(int n) {
	// create n-by-n grid, with all sites blocked                
   		if (n <= 0) {
   			throw new IllegalArgumentException();
   		}
   		model = new WeightedQuickUnionUF(n*n + 2); // the last 2 position stands for top and bottom
   		sideLen = n;
   		stateOpen = new boolean[n*n];
   		for(int i = 0; i < n*n; i++) {
   			stateOpen[i] = false;
   		}
   		top = n*n;
   		bottom = n*n + 1;
   	}	

   	private int getLegalPos(int row, int col) {
   		if(!((row >= 1) && (row <= sideLen) && (col >= 1) && (col <= sideLen))) {
   			throw new IndexOutOfBoundsException();
   		}
   		return (row - 1) * sideLen + col - 1;
   	}

   	public    void open(int row, int col){
   		// open site (row, col) if it is not open already
   		int legalPos = getLegalPos(row, col);
   		stateOpen[legalPos] = true;
   		// open grid is at first line
   		if (legalPos/sideLen == 0) {
   			model.union(top, legalPos);
   		}
   		// open grid is at last line
   		if ((legalPos/sideLen == sideLen - 1) || (legalPos/sideLen == sideLen)) {
   			model.union(legalPos, bottom);
   		}

   		// open grid is in the middle
   		int[] neighbors = new int[] {legalPos - sideLen, legalPos + sideLen, legalPos - 1, legalPos + 1};
   		for (int neighbor: neighbors) {
   			if ((neighbor >= 0) && (neighbor < sideLen * sideLen) && stateOpen[neighbor]) {
   				model.union(legalPos, neighbor);
   			}
   		}
   	}    

    public boolean isOpen(int row, int col){
    	// is site (row, col) open?
    	int legalPos = getLegalPos(row, col);
    	return stateOpen[legalPos];
    } 

    public boolean isFull(int row, int col){
    	return !isOpen(row, col);
    }
    public     int numberOfOpenSites(){
    	// number of open sites
    	int count = 0;
    	for (boolean state: stateOpen) {
    		count += (state? 1 : 0);
    	}
    	return count;
    }       
    public boolean percolates()	{
    	// does the system percolate?
    	return model.connected(top, bottom);
    }              

    public static void main(String[] args) {
    	System.out.println("*************");
    	Percolation model = new Percolation(3);
    	model.open(1,2);
    	model.open(2,2);
    	model.open(3,3);
    	System.out.println(model.isFull(3,1));
    	System.out.println(model.percolates());


    }
}