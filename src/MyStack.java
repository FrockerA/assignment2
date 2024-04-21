import java.util.Iterator;

public class MyStack<T> implements Iterable<T> {
    private MyArrayList<T> list;

    public MyStack() {
        this.list = new MyArrayList<>();
    }

    public void push(T item) {
        list.add(item);
    }

    public T pop() {
        return list.remove(list.size() - 1);
    }

    public T peek() {
        return list.get(list.size() - 1);
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int size() {
        return list.size();
    }
}
