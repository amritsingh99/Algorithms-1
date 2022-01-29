import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Percolation
 */
public class Percolation {
    private int openSites;
    private final WeightedQuickUnionUF grid;
    private final WeightedQuickUnionUF connectivity;
    private final boolean[] sites;
    private final int n;
    private final int top = 0;
    private final int bottom;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n is out of range");
        }
        this.grid = new WeightedQuickUnionUF(n * n + 2);
        this.connectivity = new WeightedQuickUnionUF(n * n + 2);
        this.sites = new boolean[n * n + 2];
        this.bottom = n * n + 1;
        this.n = n;
    }

    public void open(int row, int col) {
        if (isOpen(row, col)) {
            return;
        }
        check(row, col);
        int pos = calPosition(row, col);
        sites[pos] = true;
        ++openSites;
        if (row == top + 1) {
            grid.union(top, pos);
            connectivity.union(top, pos);
        }
        if (row == n) {
            connectivity.union(bottom, pos);
        }
        int[][] array = { { 1, 0 },
                { 0, 1 },
                { -1, 0 },
                { 0, -1 },
        };
        int deltaRow;
        int deltaCol;

        for (int i = 0; i < array.length; i++) {
            deltaRow = array[i][0];
            deltaCol = array[i][1];
            deltaRow += row;
            deltaCol += col;

            if (deltaRow >= 1 && deltaRow <= n && deltaCol >= 1 && deltaCol <= n
                    && isOpen(row, col)) {
                int tempPos = calPosition(deltaRow, deltaCol);
                if (sites[tempPos]) {
                    grid.union(pos, tempPos);
                    connectivity.union(pos, tempPos);
                }
            }
        }
    }

    public boolean isOpen(int row, int col) {
        check(row, col);
        return sites[calPosition(row, col)];
    }

    public boolean isFull(int row, int col) {
        check(row, col);
        return isOpen(row, col) && grid.find(top) == grid.find(calPosition(row, col));
    }

    public boolean percolates() {
        return connectivity.find(top) == connectivity.find(bottom);
    }

    public int numberOfOpenSites() {
        return openSites;
    }

    private int calPosition(int row, int col) {
        return this.n * (row - 1) + col;
    }

    private void check(int row, int col) {
        if (row >= 1 && row <= n && col >= 1 && col <= n) {

        } else {
            throw new IllegalArgumentException("row or col is out of the range");
        }
    }

    public static void main(String[] args) {
        int n = 20;
        int trials = 1000;
        while (trials > 0) {
            Percolation p = new Percolation(n);
            int row, col;
            int site;
            while (!p.percolates()) {
                site = StdRandom.uniform(1, n * n + 1);
                if (site % n == 0) {
                    row = site / n;
                    col = n;
                } else {
                    row = site / n + 1;
                    col = site % n;
                }
                p.open(row, col);
                // StdOut.println(row + " " + col);
            }
            trials--;
            StdOut.println("Number of sites: " + p.numberOfOpenSites());
        }
    }
}
