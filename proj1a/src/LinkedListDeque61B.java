import java.util.ArrayList;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T> {
    private int size = 0;
    Node<T> sentinel = new Node<T>();
    
    /**
     * Add {@code x} to the front of the deque. Assumes {@code x} is never null.
     *
     * @param x
     *         item to add
     */
    @Override
    public void addFirst(T x) {
        addFirstHelper(x, this);
        size++;
    }
    
    private void addFirstHelper(T x, LinkedListDeque61B<T> deck) {
        Node<T> newNode = new Node<>(null, null, x);
        deck = new LinkedListDeque61B<>(newNode, deck, "front");
    }
    
    /**
     * Add {@code x} to the back of the deque. Assumes {@code x} is never null.
     *
     * @param x
     *         item to add
     */
    @Override
    public void addLast(T x) {
        addLastHelper(x, this);
        size++;
    }
    
    private void addLastHelper(T x, LinkedListDeque61B<T> deck) {
        Node<T> newNode = new Node<>(null, null, x);
        deck = new LinkedListDeque61B<>(newNode, deck, "back");
    }
    
    /**
     * Returns a List copy of the deque. Does not alter the deque.
     *
     * @return a new list copy of the deque.
     */
    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        Node<T> pointer = this.sentinel;
        while (pointer.next != sentinel) {
            returnList.add(pointer.next.item);
            pointer = pointer.next;
        }
        return returnList;
    }
    
    /**
     * Returns if the deque is empty. Does not alter the deque.
     *
     * @return {@code true} if the deque has no elements, {@code false} otherwise.
     */
    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }
    
    /**
     * Returns the size of the deque. Does not alter the deque.
     *
     * @return the number of items in the deque.
     */
    @Override
    public int size() {
        return size;
    }
    
    /**
     * Remove and return the element at the front of the deque, if it exists.
     *
     * @return removed element, otherwise {@code null}.
     */
    @Override
    public T removeFirst() {
        if (sentinel.next == sentinel) {
            return null;
        }
        Node<T> pointer = sentinel.next;
        pointer.next.prev = sentinel;
        pointer.prev.next = pointer.next;
        pointer.next = null;
        pointer.prev = null;
        size--;
        return pointer.item;
    }
    
    /**
     * Remove and return the element at the back of the deque, if it exists.
     *
     * @return removed element, otherwise {@code null}.
     */
    @Override
    public T removeLast() {
        if (sentinel.prev == sentinel) {
            return null;
        }
        Node<T> pointer = sentinel.prev;
        pointer.prev.next = sentinel;
        pointer.next.prev = pointer.prev;
        pointer.prev = null;
        pointer.next = null;
        size--;
        return pointer.item;
    }
    
    /**
     * The Deque61B abstract data type does not typically have a get method,
     * but we've included this extra operation to provide you with some
     * extra programming practice. Gets the element, iteratively. Returns
     * null if index is out of bounds. Does not alter the deque.
     *
     * @param index
     *         index to get
     * @return element at {@code index} in the deque
     */
    @Override
    public T get(int index) {
        if (index >= this.size()) {
            return null;
        }
        Node<T> pointer = this.sentinel;
        int count = -1;
        while (count < index) {
            pointer = pointer.next;
            count++;
        }
        return pointer.item;
    }
    
    /**
     * This method technically shouldn't be in the interface, but it's here
     * to make testing nice. Gets an element, recursively. Returns null if
     * index is out of bounds. Does not alter the deque.
     *
     * @param index
     *         index to get
     * @return element at {@code index} in the deque
     */
    @Override
    public T getRecursive(int index) {
        if (index < 0 || index >= this.size()) {
            return null;
        }
        return getRecursive(index + 1, this.sentinel);
    }
    
    /** 假设index合法 */
    private T getRecursive(int index, Node<T> pointer) {
        if (index == 0) {
            return pointer.item;
        }
        return getRecursive(index - 1, pointer.next);
    }
    
    public LinkedListDeque61B() {
        sentinel.item = null;
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }
    
    private LinkedListDeque61B(Node<T> newNode, LinkedListDeque61B<T> deck, String dirt) {
        if (dirt.equals("front")) {
            newNode.prev = deck.sentinel;
            newNode.next = deck.sentinel.next;
            deck.sentinel.next.prev = newNode;
            deck.sentinel.next = newNode;
        } else if (dirt.equals("back")) {
            newNode.next = deck.sentinel;
            newNode.prev = deck.sentinel.prev;
            deck.sentinel.prev.next = newNode;
            deck.sentinel.prev = newNode;
        } else {
            newNode.prev = deck.sentinel;
            newNode.next = deck.sentinel.next;
            deck.sentinel.next.prev = newNode;
            deck.sentinel.next = newNode;
        }
    }
    
    private static class Node<T> {
        Node<T> prev = null;
        Node<T> next = null;
        T item = null;
        
        private Node(Node<T> prev, Node<T> next, T item) {
            this.prev = prev;
            this.next = next;
            this.item = item;
        }
        
        private Node() {
            return;
        }
    }
}
