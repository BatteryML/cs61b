public class ArrayDeque<T> {
    private int nextFirst;
    private T[] items;
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
            T[] temporary = (T[]) new Object[2 * size];
            int first = nextFirst + 1;
            if (first == size) {
                first = first - size;
            }
            int last = nextLast - 1;
            if (last < 0) {
                last = size + last;
            }
            for (int i = first; i < first + size; i++) {
                int p;
                if (i < size) {
                    p = i;
                } else {
                    p = i - size;
                }
                temporary[i - first + 1] = items[p];
            }
            nextFirst = 0;
            nextLast = size + 1;
            items = temporary;
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
            T[] temporary = (T[]) new Object[2 * size];
            int first = nextFirst + 1;
            if (first == size) {
                first = first - size;
            }
            int last = nextLast - 1;
            if (last < 0) {
                last = size + last;
            }
            for (int i = first; i < first + size; i++) {
                int p;
                if (i < size) {
                    p = i;
                } else {
                    p = i - size;
                }
                temporary[i - first + 1] = items[p];
            }
            nextFirst = 0;
            nextLast = size + 1;
            items = temporary;
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
            int first = nextFirst + 1;
            if (first == items.length) {
                first = first - items.length;
            }
            for (int i = first; i < first + size; i++) {
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
        int first = nextFirst + 1;
        if (first == items.length) {
            first = first - items.length;
        }
        if (items[first] == null) {
            return null;
        } else {
            T removeditem = items[first];
            items[first] = null;
            nextFirst += 1;
            if (nextFirst == items.length) {
                nextFirst = nextFirst - items.length;
            }
            size -= 1;

            if (size < (items.length / 4) && items.length >= 16) {
                T[] temporary = (T[]) new Object[items.length / 2];
                first = nextFirst + 1;
                if (first == items.length) {
                    first = first - items.length;
                }
                int last = nextLast - 1;
                if (last < 0) {
                    last = items.length + last;
                }
                for (int i = first; i < first + size; i++) {
                    int p;
                    if (i < items.length) {
                        p = i;
                    } else {
                        p = i - items.length;
                    }
                    temporary[i - first + 1] = items[p];
                }
                nextFirst = 0;
                nextLast = size + 1;
                items = temporary;
            }
            return removeditem;
        }
    }

    public T removeLast() {
        int last = nextLast - 1;
        if (last < 0) {
            last = items.length + last;
        }
        if (items[last] == null) {
            return null;
        } else {
            T removeditem = items[last];
            items[last] = null;
            nextLast -= 1;
            if (nextLast < 0) {
                nextLast = items.length + nextLast;
            }
            size -= 1;

            if (size < (items.length / 4) && items.length >= 16) {
                T[] temporary = (T[]) new Object[items.length / 2];
                int first = nextFirst + 1;
                if (first == items.length) {
                    first = first - items.length;
                }
                last = nextLast - 1;
                if (last < 0) {
                    last = items.length + last;
                }
                for (int i = first; i < first + size; i++) {
                    int p;
                    if (i < items.length) {
                        p = i;
                    } else {
                        p = i - items.length;
                    }
                    temporary[i - first + 1] = items[p];
                }
                nextFirst = 0;
                nextLast = size + 1;
                items = temporary;
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
