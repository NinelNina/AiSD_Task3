package ru.vsu.cs.kravtsova_n_e.task2;

public class Main {

    public static void main(String[] args) throws Exception {
        SimpleLinkedList<Integer> list = new SimpleLinkedList<>();
        list.addFirst(40);
        list.addLast(-19);
        list.addFirst(2);
        list.addLast(0);
        list.addLast(53);
        list.addLast(33);
        list.addLast(0);
        
        printLinkedList(list);

        list.sort((a, b) -> a - b);
        printLinkedList(list);

        list.sort((a, b) -> b - a);
        printLinkedList(list);
     }

     private static <T> void printLinkedList(SimpleLinkedList<T> list){
         int i = 0;
         for (T element : list){
             System.out.print((i > 0 ? ", " : "") + element);
             i++;
         }
         System.out.println();
     }

}
