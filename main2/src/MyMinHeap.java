public class MyMinHeap<T extends Comparable<T>> {
    private MyArrayList<T> list;

    public MyMinHeap() {
        list = new MyArrayList<>();
    }

    public void insert(T item) {
        list.add(item);
        heapifyUp(list.size() - 1);
    }

    public T extractMin() {
        if (isEmpty()) {
            throw new RuntimeException("Heap is empty");
        }
        T min = list.get(0);
        list.set(0, list.remove(list.size() - 1));
        heapifyDown(0);
        return min;
    }

    public T getMin() {
        if (isEmpty()) {
            throw new RuntimeException("Heap is empty");
        }
        return list.get(0);
    }

    private boolean isEmpty() {
        return list.isEmpty();
    }

    public int size() {
        return list.size();
    }

    private void heapifyUp(int index) {
        int parent = (index - 1) / 2;
        while (index > 0 && list.get(index).compareTo(list.get(parent)) < 0) {
            swap(index, parent);
            index = parent;
            parent = (index - 1) / 2;
        }
    }

    private void heapifyDown(int index) {
        int leftChild = 2 * index + 1;
        int rightChild = 2 * index + 2;
        int smallest = index;

        if (leftChild < list.size() && list.get(leftChild).compareTo(list.get(smallest)) < 0) {
            smallest = leftChild;
        }
        if (rightChild < list.size() && list.get(rightChild).compareTo(list.get(smallest)) < 0) {
            smallest = rightChild;
        }

        if (smallest != index) {
            swap(index, smallest);
            heapifyDown(smallest);
        }
    }

    private void swap(int i, int j) {
        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}
