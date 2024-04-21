import java.util.NoSuchElementException;

public abstract class MyLinkedList<T> implements MyList<T> {
    private static class MyNode<E> {
        E item;
        MyNode<E> next;
        MyNode<E> prev;

        MyNode(MyNode<E> prev, E element, MyNode<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    private MyNode<T> head;
    private MyNode<T> tail;
    private int size;

    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void add(T item) {
        addLast(item);
    }

    @Override
    public void set(int index, T item) {
        MyNode<T> node = getNode(index);
        node.item = item;
    }

    @Override
    public T remove(int index) {
        MyNode<T> node = getNode(index);
        unlink(node);
        return node.item;
    }

    @Override
    public void add(int index, T item) {
        if (index == size) {
            addLast(item);
        } else {
            MyNode<T> succ = getNode(index);
            MyNode<T> pred = succ.prev;
            MyNode<T> newNode = new MyNode<>(pred, item, succ);
            succ.prev = newNode;
            if (pred == null) {
                head = newNode;
            } else {
                pred.next = newNode;
            }
            size++;
        }
    }

    @Override
    public void addFirst(T item) {
        MyNode<T> newNode = new MyNode<>(null, item, head);
        if (head == null) {
            tail = newNode;
        } else {
            head.prev = newNode;
        }
        head = newNode;
        size++;
    }

    @Override
    public void addLast(T item) {
        MyNode<T> newNode = new MyNode<>(tail, item, null);
        if (tail == null) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;
        size++;
    }

    @Override
    public T get(int index) {
        return getNode(index).item;
    }

    @Override
    public T getFirst() {
        if (head == null) {
            throw new RuntimeException("List is empty");
        }
        return head.item;
    }

    @Override
    public T getLast() {
        if (tail == null) {
            throw new RuntimeException("List is empty");
        }
        return tail.item;
    }

    @Override
    public void removeFirst() {
        if (head == null) {
            throw new RuntimeException("List is empty");
        }
        unlink(head);
    }

    @Override
    public void removeLast() {
        if (tail == null) {
            throw new RuntimeException("List is empty");
        }
        unlink(tail);
    }

    @Override
    public void sort() {
        // Implement sorting logic
    }

    @Override
    public int indexOf(Object object) {
        int index = 0;
        for (MyNode<T> x = head; x != null; x = x.next) {
            if (object.equals(x.item)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object object) {
        int index = size - 1;
        for (MyNode<T> x = tail; x != null; x = x.prev) {
            if (object.equals(x.item)) {
                return index;
            }
            index--;
        }
        return -1;
    }

    @Override
    public boolean exists(Object object) {
        return indexOf(object) != -1;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        int index = 0;
        for (MyNode<T> x = head; x != null; x = x.next) {
            array[index++] = x.item;
        }
        return array;
    }

    @Override
    public void clear() {
        MyNode<T> x = head;
        while (x != null) {
            MyNode<T> next = x.next;
            x.item = null;
            x.next = null;
            x.prev = null;
            x = next;
        }
        head = tail = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public MyIterator<T> myIterator() {
        return new MyLinkedListIterator();
    }

    private MyNode<T> getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        MyNode<T> x = head;
        for (int i = 0; i < index; i++) {
            x = x.next;
        }
        return x;
    }

    private void unlink(MyNode<T> node) {
        MyNode<T> pred = node.prev;
        MyNode<T> succ = node.next;

        if (pred == null) {
            head = succ;
        } else {
            pred.next = succ;
            node.prev = null;
        }

        if (succ == null) {
            tail = pred;
        } else {
            succ.prev = pred;
            node.next = null;
        }

        node.item = null;
        size--;
    }

    private class MyLinkedListIterator implements MyIterator<T> {
        private MyNode<T> cursor = head;

        @Override
        public boolean hasNext() {
            return cursor != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements");
            }
            T item = cursor.item;
            cursor = cursor.next;
            return item;
        }
    }
}
