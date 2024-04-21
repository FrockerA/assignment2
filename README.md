# assignment2
Custom Data Structures in Java
This project implements custom data structures in Java without using java.util.*. It includes the following data structures:

MyArrayList
MyLinkedList
MyStack
MyQueue
MyMinHeap
Features
MyArrayList
Dynamic array implementation
Supports adding, removing, and accessing elements by index
Implements the MyList interface
MyLinkedList
Doubly linked list implementation
Supports adding, removing, and accessing elements by index
Implements the MyList interface
MyStack
Stack implementation based on MyArrayList
Supports push, pop, and peek operations
MyQueue
Queue implementation based on MyLinkedList
Supports enqueue, dequeue, and peek operations
MyMinHeap
Min-heap implementation based on MyArrayList
Supports insert, extractMin, and getMin operations
Usage
MyArrayList and MyLinkedList
MyArrayList<Integer> arrayList = new MyArrayList<>();
arrayList.add(1);
arrayList.add(2);
arrayList.add(3);

MyLinkedList<String> linkedList = new MyLinkedList<>();
linkedList.add("A");
linkedList.add("B");
linkedList.add("C");
MyStack:
MyStack<String> stack = new MyStack<>();
stack.push("A");
stack.push("B");
stack.push("C");

String top = stack.peek();  // Returns "C"
String popped = stack.pop();  // Removes and returns "C"
MyQueue:
MyQueue<Integer> queue = new MyQueue<>();
queue.enqueue(1);
queue.enqueue(2);
queue.enqueue(3);

int front = queue.peek();  // Returns 1
int dequeued = queue.dequeue();  // Removes and returns 1
MyMinHeap:
MyMinHeap<Integer> heap = new MyMinHeap<>();
heap.insert(3);
heap.insert(1);
heap.insert(2);

int min = heap.getMin();  // Returns 1
int extracted = heap.extractMin();  // Removes and returns 1
