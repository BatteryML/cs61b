public class LinkedListDeque<T> {
    private final GenNode sentinel;
    private int size;

    private class GenNode {
        private GenNode prev;
        private T item;
        private GenNode next;

        public GenNode(GenNode p, T i, GenNode n) {
            prev = p;
            item = i;
            next = n;
        }

        /* helper method for get recursive*/
        public T getItem(int N) {
            if (N == 0) {
                return item;
            } else {
                return next.getItem(N - 1);
            }
        }
    }

    public LinkedListDeque() {
        sentinel = new GenNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public void addFirst(T item) {
        GenNode first = new GenNode(null, item, null);
        first.next = sentinel.next;
        first.prev = sentinel;
        sentinel.next.prev = first;
        sentinel.next = first;
        size += 1;
    }

    public void addLast(T item) {
        GenNode last = new GenNode(null, item, null);
        last.prev = sentinel.prev;
        last.next = sentinel;
        sentinel.prev.next = last;
        sentinel.prev = last;
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        GenNode pointer = sentinel.next;
        if (size == 0) {
            System.out.print("");
        } else {
            while (pointer != sentinel) {
                System.out.print(pointer.item + " ");
                pointer = pointer.next;
            }
        }

    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        GenNode first = sentinel.next;
        sentinel.next = first.next;
        first.next.prev = sentinel;
        first.prev = null;
        first.next = null;
        size -= 1;
        return first.item;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        GenNode last = sentinel.prev;
        sentinel.prev = last.prev;
        last.prev.next = sentinel;
        last.prev = null;
        last.next = null;
        size -= 1;
        return last.item;
    }

    public T get(int index) {
        int N = index;
        GenNode pointer = sentinel.next;
        if (pointer == sentinel) {
            return null;
        } else {
            while (N != 0) {
                pointer = pointer.next;
                N -= 1;
            }
            return pointer.item;
        }
    }

    /* Get recursive */
    public T getRecursive(int index) {
        return sentinel.next.getItem(index);
    }
}
