package ru.vsu.cs.kravtsova_n_e.task2;

public class Main {

    public static void main(String[] args) throws Exception {
        SimpleLinkedList<Integer> list = new SimpleLinkedList<>();
        list.addFirst(2);
        list.addFirst(1);
        list.addLast(3);

        int i = 0;
        for (Integer v : list){
            System.out.print((i > 0 ? ", " : "") + v);
            i++;
        }
        System.out.println();
     }
}
