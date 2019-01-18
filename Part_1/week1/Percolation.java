import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
  
  private final WeightedQuickUnionUF modelT;
  private final WeightedQuickUnionUF modelTB;
  private final int sideLen;
  private boolean[] stateOpen;
  private final int top;
  private final int bottom;
	


	public Percolation(int n) {
	// create n-by-n grid, with all sites blocked                
   		if (n <= 0) {
   			throw new IllegalArgumentException();
   		}
   		modelT = new WeightedQuickUnionUF(n*n + 1); // the last 2 position stands for top
      modelTB = new WeightedQuickUnionUF(n*n + 2);
      top = 0;
      bottom = n*n + 1;
   		sideLen = n;
   		
      // default of boolean array is false
      stateOpen = new boolean[n*n + 2];
      stateOpen[top] = true;
      stateOpen[bottom] = true;
   	}	

   	private int getLegalPos(int row, int col) {
   		if (!((row >= 1) && (row <= sideLen) && (col >= 1) && (col <= sideLen))) {
   			throw new IllegalArgumentException();
   		}
   		return (row - 1) * sideLen + col;
   	}

    private void tryUnion(int rowA, int colA, int rowB, int colB) {
      // assume rowA and colA are correct
      if (rowB > 0 && rowB <= sideLen && colB > 0 && colB <=sideLen && isOpen(rowB, colB)) {
        modelT.union(getLegalPos(rowA, colA), getLegalPos(rowB, colB));
        modelTB.union(getLegalPos(rowA, colA), getLegalPos(rowB, colB));
      }
    }

   	public void open(int row, int col) {
   		// open site (row, col) if it is not open already
   		int legalPos = getLegalPos(row, col);
   		stateOpen[legalPos] = true;
   		// open grid is at first line
   		if (row == 1) {
   			modelT.union(top, legalPos);
        modelTB.union(top, legalPos);
   		}
   		// open grisopenid is at last line
   		if (row == sideLen) {
   			modelTB.union(legalPos, bottom);
   		}

   		// open grid is in the middle
   		tryUnion(row, col, row - 1, col);
      tryUnion(row, col, row + 1, col);
      tryUnion(row, col, row, col - 1);
      tryUnion(row, col, row, col + 1);
   	}    

    public boolean isOpen(int row, int col) {
    	// is site (row, col) open?
    	return stateOpen[getLegalPos(row, col)];
    } 

    public boolean isFull(int row, int col) {
      // if we have a bottom index, then it is complicated to judge full for components connected to bottom 
      return modelT.connected(top, getLegalPos(row, col));
    }

    public     int numberOfOpenSites() {
    	// number of open sites
      int count = 0;
      for (int i = 1; i <= sideLen * sideLen; i++) {
        count += (stateOpen[i] ? 1 : 0);
      }
    	return count;
    }       
    public boolean percolates()	{
    	// does the system percolate?
      return modelTB.connected(top, bottom);
    }              

    private void printMap() {
      for (int i = 0; i < sideLen; i++) {
        for (int j = 0; j < sideLen; j++) {
          int legalPos = getLegalPos(i+1, j+1);
          if (stateOpen[legalPos]) {
            System.out.print("1 " + isFull(i+1, j+1) + " ");
          } else {
            System.out.print("0 " + isFull(i+1, j+1) + " ");
          }
        }
        System.out.println();
      }
      System.out.println("Connection to Top:");
      for (int i = 0; i < sideLen; i++) {
        for (int j = 0; j < sideLen; j++) {
          int legalPos = getLegalPos(i+1, j+1);
          if (stateOpen[legalPos]) {
            System.out.print("1 " + modelT.connected(legalPos, top) + " ");
          } else {
            System.out.print("0 " + modelT.connected(legalPos, top) + " ");
          }
        }
        System.out.println();
      }
    }

    public static void main(String[] args) {
    	System.out.println("*************");
      int n = 6;
    	Percolation test = new Percolation(n);
    	test.open(1, 6);
    	test.open(2, 6);
      test.open(3, 6);
    	test.open(4, 6);
      test.open(5, 6);
      test.open(5, 5);
      test.open(4, 4);
      test.open(3, 4);
      test.open(2, 4);
      test.open(2, 3);
      test.open(2, 2);
      test.open(2, 1);
      test.open(3, 1);
      test.open(4, 1);
      test.open(5, 2);
      test.open(6, 2);
      test.open(5, 4);

      test.printMap();
      System.out.println(test.percolates());


    }
}