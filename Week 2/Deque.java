import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int N = 0;

    private class Node {
        Item item;
        Node next;
        Node prev;

        public Node(Item item) {
            this.item = item;
        }
    }

    // construct an empty deque
    public Deque() {

    }

    // is the deque empty?
    public boolean isEmpty() {
        return N == 0;
    }

    // return the number of items on the deque
    public int size() {
        return N;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Cannot pass null");
        }
        Node newFirst = new Node(item);
        newFirst.next = first;
        if (first != null) {
            first.prev = newFirst;
        } else {
            newFirst.next = last;
            if (last != null) {
                last.prev = newFirst;
            } else {
                last = newFirst;
            }
        }
        if (last == null) {
            last = newFirst;
        }
        first = newFirst;
        N++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Cannot pass null");
        }
        Node newLast = new Node(item);
        newLast.prev = last;
        if (last != null) {
            last.next = newLast;
        } else {
            newLast.prev = first;
            if (first != null) {
                first.next = newLast;
            }
        }
        if (first == null) {
            first = newLast;
        }
        last = newLast;
        N++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        Item item;
        if (N == 0) {
            throw new NoSuchElementException("Trying to remove from Empty Deck");
        }
        if (N == 1) {
            N--;
            item = first.item;
            first = null;
            last = null;
            return item;
        }
        item = first.item;
        Node newFirst = first.next;
        newFirst.prev = null;
        first.next = null;
        first = newFirst;
        --N;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        Item item;
        if (N == 0) {
            throw new NoSuchElementException("Trying to remove from Empty Deck");
        }
        if (N == 1) {
            N--;
            item = last.item;
            last = null;
            first = null;
            return item;
        }
        item = last.item;
        Node newLast = last.prev;
        newLast.next = null;
        last.prev = null;
        last = newLast;
        --N;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Empty deck");
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException("cant use the remove method");
        }
    }

    private void printDeck(Deque<Integer> deck) {
        StdOut.println("Currect Deck: ");
        for (Integer i : deck) {
            StdOut.print(i + " ");
        }
        StdOut.println();
        StdOut.println("Size of deck: " + this.size());
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> deck = new Deque<Integer>();
        deck.addFirst(1);
        deck.printDeck(deck);
        deck.addFirst(2);
        deck.printDeck(deck);
        deck.addLast(3);
        deck.printDeck(deck);
        deck.addFirst(10);
        deck.printDeck(deck);
        int last = deck.removeLast();
        deck.printDeck(deck);
        int first = deck.removeFirst();
        deck.printDeck(deck);

        StdOut.println("removed from last: " + last);
        StdOut.println("removed from first: " + first);
        int[] a = { 1, 2, 3, 4, 5, 6, 7, 8 };
        for (int i = 0; i < a.length; i++) {
            deck.addFirst(a[i]);
        }
        while (!deck.isEmpty()) {
            int item = deck.removeLast();
            StdOut.print(item + " ");
        }
        Deque<Integer> deque = new Deque<>();

        deque.addFirst(1);
        StdOut.println(deque.removeLast());
        deque.isEmpty();
        deque.addFirst(4);
        deque.isEmpty();
        StdOut.println(deque.removeLast());
    }
}