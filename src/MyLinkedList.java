import java.util.Iterator;

public class MyLinkedList<T> implements MyList<T> {
    private static class MyNode<E> {
        E element;
        MyNode<E> next;
        MyNode<E> prev;

        MyNode(E element, MyNode<E> next, MyNode<E> prev) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }
    }

    private MyNode<T> head;
    private MyNode<T> tail;
    private int size;

    public MyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public void add(T item) {
        addLast(item);
    }

    @Override
    public void set(int index, T item) {
        MyNode<T> node = getNode(index);
        node.element = item;
    }

    @Override
    public T remove(int index) {
        MyNode<T> node = getNode(index);
        return unlink(node);
    }

    private T unlink(MyNode<T> node) {
        if (node == null) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        T removed = node.element;
        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            head = node.next;
        }
        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            tail = node.prev;
        }
        size--;
        return removed;
    }

    @Override
    public void add(int index, T item) {
        if (index == size) {
            addLast(item);
        } else {
            MyNode<T> node = getNode(index);
            MyNode<T> newNode = new MyNode<>(item, node, node.prev);
            if (node.prev != null) {
                node.prev.next = newNode;
            } else {
                head = newNode;
            }
            node.prev = newNode;
            size++;
        }
    }

    @Override
    public void addFirst(T item) {
        MyNode<T> newNode = new MyNode<>(item, head, null);
        if (head != null) {
            head.prev = newNode;
        }
        head = newNode;
        if (tail == null) {
            tail = newNode;
        }
        size++;
    }

    @Override
    public void addLast(T item) {
        MyNode<T> newNode = new MyNode<>(item, null, tail);
        if (tail != null) {
            tail.next = newNode;
        }
        tail = newNode;
        if (head == null) {
            head = newNode;
        }
        size++;
    }

    @Override
    public T get(int index) {
        MyNode<T> node = getNode(index);
        return node.element;
    }

    private MyNode<T> getNode(int index) {
        checkIndex(index);
        MyNode<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    @Override
    public T getFirst() {
        if (head == null) {
            return null;
        }
        return head.element;
    }

    @Override
    public T getLast() {
        if (tail == null) {
            return null;
        }
        return tail.element;
    }

    @Override
    public void removeFirst() {
        if (head != null) {
            unlink(head);
        }
    }

    @Override
    public void removeLast() {
        if (tail != null) {
            unlink(tail);
        }
    }

    @Override
    public void sort() {
        // Sorting not implemented for LinkedList
    }

    @Override
    public int indexOf(Object object) {
        MyNode<T> current = head;
        for (int i = 0; i < size; i++) {
            if (current.element.equals(object)) {
                return i;
            }
            current = current.next;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object object) {
        MyNode<T> current = tail;
        for (int i = size - 1; i >= 0; i--) {
            if (current.element.equals(object)) {
                return i;
            }
            current = current.prev;
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
        MyNode<T> current = head;
        for (int i = 0; i < size; i++) {
            arr[i] = current.element;
            current = current.next;
        }
        return arr;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public MyIterator<T> myIterator() {
        return null;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyLinkedListIterator();
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    private class MyLinkedListIterator implements Iterator<T> {
        private MyNode<T> current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new IndexOutOfBoundsException("No more elements");
            }
            T element = current.element;
            current = current.next;
            return element;
        }
    }
}
