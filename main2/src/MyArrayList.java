import java.util.Iterator;

public class MyArrayList<T> implements MyList<T> {
    private Object[] elements;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public MyArrayList() {
        this.elements = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    @Override
    public void add(T item) {
        if (size == elements.length)
            resize();
        elements[size++] = item;
    }

    @Override
    public void set(int index, T item) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        elements[index] = item;
    }

    @Override
    public void add(int index, T item) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException();
        if (size == elements.length)
            resize();
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = item;
        size++;
    }

    @Override
    public void addFirst(T item) {
        add(0, item);
    }

    @Override
    public void addLast(T item) {
        add(item);
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        return (T) elements[index];
    }

    @Override
    public T getFirst() {
        if (size == 0)
            throw new IllegalStateException("List is empty");
        return (T) elements[0];
    }

    @Override
    public T getLast() {
        if (size == 0)
            throw new IllegalStateException("List is empty");
        return (T) elements[size - 1];
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();

        T removedItem = get(index);

        // Shift elements to the left to fill the gap
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }

        // Nullify the reference to the last element
        elements[size - 1] = null;
        size--;

        return removedItem;
    }

    @Override
    public void removeFirst() {
        remove(0);
    }

    @Override
    public void removeLast() {
        if (size == 0)
            throw new IllegalStateException("List is empty");
        remove(size - 1);
    }

    @Override
    public void sort() {
        // Assuming T is comparable
        java.util.Arrays.sort((T[]) elements, 0, size);
    }

    @Override
    public int indexOf(Object object) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(object))
                return i;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object object) {
        for (int i = size - 1; i >= 0; i--) {
            if (elements[i].equals(object))
                return i;
        }
        return -1;
    }

    @Override
    public boolean exists(Object object) {
        return indexOf(object) != -1;
    }

    @Override
    public Object[] toArray() {
        Object[] arr = new Object[size];
        System.arraycopy(elements, 0, arr, 0, size);
        return arr;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator<>();
    }

    @Override
    public MyList.MyIterator<T> myIterator() {
        return new MyIterator<>();
    }

    private void resize() {
        int newCapacity = elements.length * 2;
        Object[] newArray = new Object[newCapacity];
        System.arraycopy(elements, 0, newArray, 0, size);
        elements = newArray;
    }

    public class MyIterator<E> implements MyList.MyIterator<E> {
        private int cursor;

        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        @Override
        public E next() {
            if (!hasNext())
                throw new IllegalStateException();
            return (E) elements[cursor++];
        }
    }
}