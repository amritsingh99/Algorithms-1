import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final double mean;
    private final double std;
    private final double[] values;
    private final double CONFIDENCE_VAR = 1.96;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("n or trials is either zero or negative");
        }
        values = new double[trials];
        int counter = 0;
        int site;
        while (trials > 0) {
            Percolation p = new Percolation(n);
            // count = 0;
            int row, col;

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
            values[counter++] = (double) p.numberOfOpenSites() / (double) (n * n);
            // // StdOut.println(p.numberOfOpenSites());
            // // StdOut.println((double) p.numberOfOpenSites() / (double) (n * n));
            // break;
            // while (true) {

            // // row = StdRandom.uniform(1, n + 1);
            // // col = StdRandom.uniform(1, n + 1);
            // // while (!p.isOpen(row, col)) {
            // // p.open(row, col);
            // // count++;
            // // }
            // site = StdRandom.uniform(1, n * n + 1);
            // if (site % n == 0) {
            // row = site / n;
            // col = n;
            // } else {
            // row = site / n + 1;
            // col = site % n;
            // }
            // if (p.percolates()) {
            // values[counter++] = (double) p.numberOfOpenSites() / (double) (n * n);
            // // StdOut.println(p.numberOfOpenSites());
            // // StdOut.println((double) p.numberOfOpenSites() / (double) (n * n));
            // break;
            // }

            // if (!p.isOpen(row, col)) {
            // p.open(row, col);
            // // StdOut.println(row + " " + col);
            // }

            trials--;
        }

        this.mean = StdStats.mean(values);
        this.std = StdStats.stddev(values);

    }

    // sample mean of percolation threshold
    public double mean() {
        // double sum = 0;
        // for (int i : bag) {
        // sum += i;
        // }
        // double avg = sum / trials;
        // this.sum = sum;
        // this.mean = avg / (n * n);
        // return avg / (n * n);
        return this.mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        // double sum = 0.0;
        // for (double x : bag)
        // sum += (x - mean)*(x - mean);

        // double std = Math.sqrt(sum/(n * n-1));
        // this.std = std;
        return this.std;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean - (CONFIDENCE_VAR * std) / (Math.sqrt(values.length));
    }

    // // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean + (CONFIDENCE_VAR * std) / (Math.sqrt(values.length));
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        // int n = 5;
        // int trials = 1;
        // StdOut.println(n);

        PercolationStats test = new PercolationStats(n, trials);
        StdOut.println("mean = " + test.mean());
        StdOut.println("stddev = " + test.stddev());
        StdOut.println("95% confidence interval = [" + test.confidenceLo() + ", " +
                test.confidenceHi() + "]");
    }
}