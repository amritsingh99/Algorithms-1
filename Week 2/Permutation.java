import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Permutation
 */
public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
        int n = k;
        while (n > 0) {
            randomizedQueue.enqueue(StdIn.readString());
            n--;
        }
        while (!StdIn.isEmpty()) {
            String a = StdIn.readString();
        }

        for (int i = 0; i < k; i++) {
            System.out.println(randomizedQueue.dequeue());
        }

    }
}