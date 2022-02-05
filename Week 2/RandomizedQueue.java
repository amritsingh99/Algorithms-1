import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private final Deque<Item> deck;

    // construct an empty randomized queue
    public RandomizedQueue() {
        deck = new Deque<Item>();
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return deck.isEmpty();
    }

    // return the number of items on the randomized queue
    public int size() {
        return deck.size();
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Cannot pass null");
        }
        int max = 100;
        int min = 1;
        int range = max - min + 1;
        int choice = (int) (Math.random() * range) + min;
        if (choice % 2 == 0) {
            deck.addFirst(item);
        } else {
            deck.addLast(item);
        }
    }

    // remove and return a random item
    public Item dequeue() {
        // return new Item;
        if (this.isEmpty()) {
            throw new NoSuchElementException("Trying to remove from Empty Deck");
        }
        Item item;
        int max = 100;
        int min = 1;
        int range = max - min + 1;
        int choice = (int) (Math.random() * range) + min;
        if (choice % 2 == 0) {
            item = deck.removeLast();
        } else {
            item = deck.removeFirst();
        }
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (deck.isEmpty()) {
            throw new NoSuchElementException("Trying to remove from Empty Deck");
        }
        Item[] a = (Item[]) new Object[deck.size()];
        int i = 0;
        for (Item element : deck) {
            a[i++] = element;
        }
        int pos = StdRandom.uniform(0, deck.size());
        Item item = a[pos];
        a = null;
        return item;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        Item[] copyItems = (Item[]) new Object[deck.size()];
        private int i;

        public RandomizedQueueIterator() {
            int j = 0;
            for (Item element : deck) {
                copyItems[j++] = element;
            }
            StdRandom.shuffle(copyItems);
        }

        public boolean hasNext() {
            return i < copyItems.length;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Empty deck");
            }
            return copyItems[i++];
        }

        public void remove() {
            throw new UnsupportedOperationException("cant use the remove method");
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        int n = 5;
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        for (int i = 0; i < n; i++)
            queue.enqueue(i);
        for (int a : queue) {
            for (int b : queue)
                StdOut.print(a + "-" + b + " ");
            StdOut.println();
        }

    }
}