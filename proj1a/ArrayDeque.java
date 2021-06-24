public class ArrayDeque<T> {
    private int nextFirst;
    public T[] items;
    private int nextLast;
    private int size;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        nextFirst = 4;
        nextLast = 5;
        size = 0;
    }

    public void addFirst(T item) {
        items[nextFirst] = item;
        nextFirst -= 1;
        size += 1;
        if (nextFirst < 0) {
            nextFirst = items.length + nextFirst;
        }
        if (size == items.length) {
            /*copy to a larger new array*/
            T[] Temporary = (T[]) new Object[2 * size];
            int First = nextFirst + 1;
            if (First == size) {
                First = First - size;
            }
            int Last = nextLast - 1;
            if (Last < 0) {
                Last = size + Last;
            }
            for (int i = First; i < First + size; i++) {
                int p;
                if (i < size) {
                    p = i;
                } else {
                    p = i - size;
                }
                Temporary[i - First + 1] = items[p];
            }
            nextFirst = 0;
            nextLast = size + 1;
            items = Temporary;
        }
    }

    public void addLast(T item) {
        items[nextLast] = item;
        nextLast += 1;
        size += 1;
        if (nextLast > items.length - 1) {
            nextLast = items.length - nextFirst;
        }
        if (size == items.length) {
            /*copy to a larger new array*/
            T[] Temporary = (T[]) new Object[2 * size];
            int First = nextFirst + 1;
            if (First == size) {
                First = First - size;
            }
            int Last = nextLast - 1;
            if (Last < 0) {
                Last = size + Last;
            }
            for (int i = First; i < First + size; i++) {
                int p;
                if (i < size) {
                    p = i;
                } else {
                    p = i - size;
                }
                Temporary[i - First + 1] = items[p];
            }
            nextFirst = 0;
            nextLast = size + 1;
            items = Temporary;
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if (size == 0) {
            System.out.print("");
        } else {
            int First = nextFirst + 1;
            if (First == items.length) {
                First = First - items.length;
            }
            for (int i = First; i < First + size; i++) {
                int p;
                if (i < items.length) {
                    p = i;
                } else {
                    p = i - items.length;
                }
                System.out.print(items[p] + " ");
            }
        }
    }

    public T removeFirst() {
        int First = nextFirst + 1;
        if (First == items.length) {
            First = First - items.length;
        }
        if (items[First] == null) {
            return null;
        } else {
            T removeditem = items[First];
            items[First] = null;
            nextFirst += 1;
            if (nextFirst == items.length) {
                nextFirst = nextFirst - items.length;
            }
            size -= 1;

            if (size < (items.length / 4) && items.length >= 16) {
                T[] Temporary = (T[]) new Object[items.length / 2];
                First = nextFirst + 1;
                if (First == items.length) {
                    First = First - items.length;
                }
                int Last = nextLast - 1;
                if (Last < 0) {
                    Last = items.length + Last;
                }
                for (int i = First; i < First + size; i++) {
                    int p;
                    if (i < items.length) {
                        p = i;
                    } else {
                        p = i - items.length;
                    }
                    Temporary[i - First + 1] = items[p];
                }
                nextFirst = 0;
                nextLast = size + 1;
                items = Temporary;
            }
            return removeditem;
        }
    }

    public T removeLast() {
        int Last = nextLast - 1;
        if (Last < 0) {
            Last = items.length + Last;
        }
        if (items[Last] == null) {
            return null;
        } else {
            T removeditem = items[Last];
            items[Last] = null;
            nextLast -= 1;
            if (nextLast < 0) {
                nextLast = items.length + nextLast;
            }
            size -= 1;

            if (size < (items.length / 4) && items.length >= 16) {
                T[] Temporary = (T[]) new Object[items.length / 2];
                int First = nextFirst + 1;
                if (First == items.length) {
                    First = First - items.length;
                }
                Last = nextLast - 1;
                if (Last < 0) {
                    Last = items.length + Last;
                }
                for (int i = First; i < First + size; i++) {
                    int p;
                    if (i < items.length) {
                        p = i;
                    } else {
                        p = i - items.length;
                    }
                    Temporary[i - First + 1] = items[p];
                }
                nextFirst = 0;
                nextLast = size + 1;
                items = Temporary;
            }
            return removeditem;
        }
    }

    public T get(int index) {
        int i = nextFirst + 1 + index;
        if (i > items.length) {
            i = i - items.length;
        }
        return items[i];
    }


}
