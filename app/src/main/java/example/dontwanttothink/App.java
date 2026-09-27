package example.dontwanttothink;

import java.util.Random;

import example.dontwanttothink.DynamicArrays.DynamicQueue;
import example.dontwanttothink.DynamicArrays.DynamicStack;
import example.dontwanttothink.LinkedLists.DoublyLinkedList;
import example.dontwanttothink.LinkedLists.DoublyLinkedListWithTail;
import example.dontwanttothink.LinkedLists.DoublyLinkedNode;
import example.dontwanttothink.LinkedLists.SinglyLinkedList;
import example.dontwanttothink.LinkedLists.SinglyLinkedListWithTail;
import example.dontwanttothink.LinkedLists.SinglyLinkedNode;

// Vamos a hacer mediciones básicas que muestren los órdenes de crecimiento
// asintótico.

// No todas las operaciones se realizan con exactamente n elementos en la
// estructura. Pero las diferencias son diminutas e insignificantes.

public class App {
    public static volatile Object x = 0;

    private final static int MAXIMUM_N = 1 << 26;
    private final static int ITERATIONS = 3;

    private static Random rng = new Random();

    public static boolean isPowerOfTwo(int n) {
        return (n > 0) && ((n & (n - 1)) == 0);
    }

    public static void testSinglyLinkedList() {
        IO.println("@Enlazada simplemente sin cola");
        long start, end;
        for (int it = 0; it < ITERATIONS; ++it) {
            SinglyLinkedList<Integer> singlyLinkedList = new SinglyLinkedList<>();
            SinglyLinkedNode<Integer> tail = null; // solo para llenar el arreglo

            IO.println("#Iteración " + it);
            for (int n = 0; n <= MAXIMUM_N; ++n) {
                if (isPowerOfTwo(n)) {
                    IO.println(":" + n);

                    start = System.nanoTime();
                    x = singlyLinkedList.isEmpty();
                    end = System.nanoTime();
                    IO.print(end - start + ",");

                    start = System.nanoTime();
                    singlyLinkedList.pushBack(n);
                    end = System.nanoTime();
                    IO.print(end - start + ",");

                    int randomItem = rng.nextInt(n);
                    tail = tail.next();

                    // queremos quedarnos con el elemento nuevo, n
                    singlyLinkedList.pushBack(-1);
                    start = System.nanoTime();
                    x = singlyLinkedList.popBack();
                    end = System.nanoTime();
                    IO.print(end - start + ",");

                    start = System.nanoTime();
                    singlyLinkedList.pushFront(-1);
                    end = System.nanoTime();
                    IO.print(end - start + ",");

                    start = System.nanoTime();
                    x = singlyLinkedList.popFront();
                    end = System.nanoTime();
                    IO.print(end - start + ",");

                    start = System.nanoTime();
                    SinglyLinkedNode<Integer> singlyLinkedNode = singlyLinkedList.find(randomItem);
                    end = System.nanoTime();
                    IO.print(end - start + ",");

                    start = System.nanoTime();
                    singlyLinkedList.addBefore(singlyLinkedNode, -1);
                    end = System.nanoTime();
                    IO.print(end - start + ",");
                    singlyLinkedList.erase(singlyLinkedList.find(-1));

                    start = System.nanoTime();
                    singlyLinkedList.addAfter(singlyLinkedNode, -1);
                    end = System.nanoTime();
                    IO.print(end - start + ",");
                    singlyLinkedList.erase(singlyLinkedList.find(-1));

                    start = System.nanoTime();
                    singlyLinkedList.erase(singlyLinkedNode);
                    end = System.nanoTime();
                    IO.println(end - start);

                    if (randomItem == 0) {
                        singlyLinkedList.pushFront(0);
                    } else {
                        singlyLinkedList.addAfter(singlyLinkedList.find(randomItem - 1), randomItem);
                    }
                } else {
                    if (tail == null) {
                        singlyLinkedList.pushBack(n);
                        tail = singlyLinkedList.find(n);
                    } else {
                        singlyLinkedList.addAfter(tail, n);
                        tail = tail.next();
                    }
                }
            }
        }
    }

    public static void testSinglyLinkedListWithTail() {
        IO.println("@Enlazada simplemente con cola");
        long start, end;
        for (int it = 0; it < ITERATIONS; ++it) {
            SinglyLinkedListWithTail<Integer> singlyLinkedListWithTail = new SinglyLinkedListWithTail<>();

            IO.println("#Iteración " + it);
            for (int n = 0; n <= MAXIMUM_N; ++n) {
                if (isPowerOfTwo(n)) {
                    IO.println(":" + n);

                    start = System.nanoTime();
                    x = singlyLinkedListWithTail.isEmpty();
                    end = System.nanoTime();
                    IO.print(end - start + ",");

                    start = System.nanoTime();
                    singlyLinkedListWithTail.pushBack(n);
                    end = System.nanoTime();
                    IO.print(end - start + ",");

                    int randomItem = rng.nextInt(n + 1);

                    // queremos quedarnos con el elemento nuevo, n
                    singlyLinkedListWithTail.pushBack(-1);
                    start = System.nanoTime();
                    x = singlyLinkedListWithTail.popBack();
                    end = System.nanoTime();
                    IO.print(end - start + ",");

                    start = System.nanoTime();
                    singlyLinkedListWithTail.pushFront(-1);
                    end = System.nanoTime();
                    IO.print(end - start + ",");

                    start = System.nanoTime();
                    x = singlyLinkedListWithTail.popFront();
                    end = System.nanoTime();
                    IO.print(end - start + ",");

                    start = System.nanoTime();
                    SinglyLinkedNode<Integer> singlyLinkedNode = singlyLinkedListWithTail.find(randomItem);
                    end = System.nanoTime();
                    IO.print(end - start + ",");

                    start = System.nanoTime();
                    singlyLinkedListWithTail.addBefore(singlyLinkedNode, -1);
                    end = System.nanoTime();
                    IO.print(end - start + ",");
                    singlyLinkedListWithTail.erase(singlyLinkedListWithTail.find(-1));

                    start = System.nanoTime();
                    singlyLinkedListWithTail.addAfter(singlyLinkedNode, -1);
                    end = System.nanoTime();
                    IO.print(end - start + ",");
                    singlyLinkedListWithTail.erase(singlyLinkedListWithTail.find(-1));

                    start = System.nanoTime();
                    singlyLinkedListWithTail.erase(singlyLinkedNode);
                    end = System.nanoTime();
                    IO.println(end - start);

                    if (randomItem == 0) {
                        singlyLinkedListWithTail.pushFront(0);
                    } else {
                        singlyLinkedListWithTail
                                .addAfter(singlyLinkedListWithTail.find(randomItem - 1), randomItem);
                    }
                } else {
                    // pushBack es O(1) con cola
                    singlyLinkedListWithTail.pushBack(n);
                }
            }
        }
    }

    public static void testDoublyLinkedList() {
        IO.println("@Enlazada doblemente sin cola");
        long start, end;
        for (int it = 0; it < ITERATIONS; ++it) {
            DoublyLinkedList<Integer> doublyLinkedList = new DoublyLinkedList<>();
            DoublyLinkedNode<Integer> tail = null; // solo para llenar el arreglo

            IO.println("#Iteración " + it);
            for (int n = 0; n <= MAXIMUM_N; ++n) {
                if (isPowerOfTwo(n)) {
                    IO.println(":" + n);

                    start = System.nanoTime();
                    x = doublyLinkedList.isEmpty();
                    end = System.nanoTime();
                    IO.print(end - start + ",");

                    start = System.nanoTime();
                    doublyLinkedList.pushBack(n);
                    end = System.nanoTime();
                    IO.print(end - start + ",");

                    int randomItem = rng.nextInt(n + 1);
                    tail = tail.next();

                    // queremos quedarnos con el elemento nuevo, n
                    doublyLinkedList.pushBack(-1);
                    start = System.nanoTime();
                    x = doublyLinkedList.popBack();
                    end = System.nanoTime();
                    IO.print(end - start + ",");

                    start = System.nanoTime();
                    doublyLinkedList.pushFront(-1);
                    end = System.nanoTime();
                    IO.print(end - start + ",");

                    start = System.nanoTime();
                    x = doublyLinkedList.popFront();
                    end = System.nanoTime();
                    IO.print(end - start + ",");

                    start = System.nanoTime();
                    DoublyLinkedNode<Integer> doublyLinkedNode = doublyLinkedList.find(randomItem);
                    end = System.nanoTime();
                    IO.print(end - start + ",");

                    start = System.nanoTime();
                    doublyLinkedList.addBefore(doublyLinkedNode, -1);
                    end = System.nanoTime();
                    IO.print(end - start + ",");
                    doublyLinkedList.erase(doublyLinkedList.find(-1));

                    start = System.nanoTime();
                    doublyLinkedList.addAfter(doublyLinkedNode, -1);
                    end = System.nanoTime();
                    IO.print(end - start + ",");
                    doublyLinkedList.erase(doublyLinkedList.find(-1));

                    start = System.nanoTime();
                    doublyLinkedList.erase(doublyLinkedNode);
                    end = System.nanoTime();
                    IO.println(end - start);

                    if (randomItem == 0) {
                        doublyLinkedList.pushFront(0);
                    } else {
                        doublyLinkedList.addAfter(doublyLinkedList.find(randomItem - 1), randomItem);
                    }
                } else {
                    if (tail == null) {
                        doublyLinkedList.pushBack(n);
                        tail = doublyLinkedList.find(n);
                    } else {
                        doublyLinkedList.addAfter(tail, n);
                        tail = tail.next();
                    }
                }
            }
        }
    }

    public static void testDoublyLinkedListWithTail() {
        IO.println("@Enlazada doblemente con cola");
        long start, end;
        for (int it = 0; it < ITERATIONS; ++it) {
            DoublyLinkedListWithTail<Integer> doublyLinkedListWithTail = new DoublyLinkedListWithTail<>();

            IO.println("#Iteración " + it);
            for (int n = 0; n <= MAXIMUM_N; ++n) {
                if (isPowerOfTwo(n)) {
                    IO.println(":" + n);

                    start = System.nanoTime();
                    x = doublyLinkedListWithTail.isEmpty();
                    end = System.nanoTime();
                    IO.print(end - start + ",");

                    start = System.nanoTime();
                    doublyLinkedListWithTail.pushBack(n);
                    end = System.nanoTime();
                    IO.print(end - start + ",");

                    int randomItem = rng.nextInt(n + 1);

                    // queremos quedarnos con el elemento nuevo, n
                    doublyLinkedListWithTail.pushBack(-1);
                    start = System.nanoTime();
                    x = doublyLinkedListWithTail.popBack();
                    end = System.nanoTime();
                    IO.print(end - start + ",");

                    start = System.nanoTime();
                    doublyLinkedListWithTail.pushFront(-1);
                    end = System.nanoTime();
                    IO.print(end - start + ",");

                    start = System.nanoTime();
                    x = doublyLinkedListWithTail.popFront();
                    end = System.nanoTime();
                    IO.print(end - start);

                    start = System.nanoTime();
                    DoublyLinkedNode<Integer> doublyLinkedNode = doublyLinkedListWithTail.find(randomItem);
                    end = System.nanoTime();
                    IO.print(end - start + ",");

                    start = System.nanoTime();
                    doublyLinkedListWithTail.addBefore(doublyLinkedNode, -1);
                    end = System.nanoTime();
                    IO.print(end - start + ",");
                    doublyLinkedListWithTail.erase(doublyLinkedListWithTail.find(-1));

                    start = System.nanoTime();
                    doublyLinkedListWithTail.addAfter(doublyLinkedNode, -1);
                    end = System.nanoTime();
                    IO.print(end - start + ",");
                    doublyLinkedListWithTail.erase(doublyLinkedListWithTail.find(-1));

                    start = System.nanoTime();
                    doublyLinkedListWithTail.erase(doublyLinkedNode);
                    end = System.nanoTime();
                    IO.println(end - start);

                    if (randomItem == 0) {
                        doublyLinkedListWithTail.pushFront(0);
                    } else {
                        doublyLinkedListWithTail.addAfter(doublyLinkedListWithTail.find(randomItem - 1),
                                randomItem);
                    }
                } else {
                    // pushBack es O(1) con cola (centinela)
                    doublyLinkedListWithTail.pushBack(n);
                }
            }
        }
    }

    public static final double QUICK_FACTOR = Math.pow(2.0, 1.0 / 8);

    private static void testQueue() {
        IO.println("@Cola dinámica");

        long start, end;
        for (int it = 0; it < ITERATIONS; ++it) {
            DynamicQueue<Integer> queue = new DynamicQueue<>();

            double nextPossibleQuick = 0;

            IO.println("#Iteración " + it);
            for (int n = 0; n <= MAXIMUM_N; ++n) {
                if (isPowerOfTwo(n)) {
                    IO.println(":" + n);

                    start = System.nanoTime();
                    x = queue.isEmpty();
                    end = System.nanoTime();
                    IO.print(end - start + ",");

                    start = System.nanoTime();
                    queue.enqueue(n);
                    end = System.nanoTime();
                    IO.print(end - start + ",");

                    start = System.nanoTime();
                    x = queue.dequeue();
                    end = System.nanoTime();
                    IO.print(end - start + ",");
                    queue._addBefore(0, 0);

                    start = System.nanoTime();
                    x = queue.front();
                    end = System.nanoTime();
                    IO.print(end - start + ",");

                    start = System.nanoTime();
                    x = queue.size();
                    end = System.nanoTime();
                    IO.print(end - start + ",");

                    int randomItem = rng.nextInt(n + 1);

                    start = System.nanoTime();
                    queue.delete(randomItem);
                    end = System.nanoTime();
                    IO.println(end - start);

                    if (randomItem == n) {
                        queue.enqueue(n);
                    } else {
                        queue._addBefore(randomItem, randomItem);
                    }

                    nextPossibleQuick = n * QUICK_FACTOR;
                } else if ((int) nextPossibleQuick == n) {
                    IO.print(":" + n + "q");
                    start = System.nanoTime();
                    queue.enqueue(n);
                    end = System.nanoTime();
                    IO.println(end - start);

                    nextPossibleQuick *= QUICK_FACTOR;
                } else {
                    queue.enqueue(n);
                }
            }
        }
    }

    private static void testStack() {
        IO.println("@Pila dinámica");

        long start, end;
        for (int it = 0; it < ITERATIONS; ++it) {
            DynamicStack<Integer> stack = new DynamicStack<>();

            double nextPossibleQuick = 0;

            IO.println("#Iteración " + it);
            for (int n = 0; n <= MAXIMUM_N; ++n) {
                if (isPowerOfTwo(n)) {
                    IO.println(":" + n);

                    start = System.nanoTime();
                    x = stack.isEmpty();
                    end = System.nanoTime();
                    IO.print(end - start + ",");

                    start = System.nanoTime();
                    stack.push(n);
                    end = System.nanoTime();
                    IO.print(end - start + ",");

                    start = System.nanoTime();
                    x = stack.pop();
                    end = System.nanoTime();
                    IO.print(end - start + ",");
                    stack.push(n);

                    start = System.nanoTime();
                    x = stack.peek();
                    end = System.nanoTime();
                    IO.print(end - start + ",");

                    start = System.nanoTime();
                    x = stack.size();
                    end = System.nanoTime();
                    IO.print(end - start + ",");

                    int randomItem = rng.nextInt(n + 1);

                    start = System.nanoTime();
                    stack.delete(randomItem);
                    end = System.nanoTime();
                    IO.println(end - start);

                    stack._addBefore(randomItem, randomItem);

                    nextPossibleQuick = n * QUICK_FACTOR;
                } else if ((int) nextPossibleQuick == n) {
                    IO.print(":" + n + "q");
                    start = System.nanoTime();
                    stack.push(n);
                    end = System.nanoTime();
                    IO.println(end - start);

                    nextPossibleQuick *= QUICK_FACTOR;
                } else {
                    stack.push(n);
                }
            }
        }
    }

    private static void usage() {
        IO.println("Usage: [invocation] [--sll|--sllt|--dll|--dllt|--q|--s]");
    }

    public static void main(String[] args) {
        if (args.length == 1) {
            switch (args[0]) {
                case "--sll" -> testSinglyLinkedList();
                case "--sllt" -> testSinglyLinkedListWithTail();
                case "--dll" -> testDoublyLinkedList();
                case "--dllt" -> testDoublyLinkedListWithTail();
                case "--q" -> testQueue();
                case "--s" -> testStack();
                default -> {
                    usage();
                    System.exit(1);
                }
            }
        } else {
            usage();
            System.exit(1);
        }
    }
}
