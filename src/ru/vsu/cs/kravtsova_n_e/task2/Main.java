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

        int i = 0;
        for (Integer element : list){
            System.out.print((i > 0 ? ", " : "") + element);
            i++;
        }
        System.out.println();

        list.sort((a, b) -> a - b);

        i = 0;
        for (Integer element : list){
            System.out.print((i > 0 ? ", " : "") + element);
            i++;
        }
        System.out.println();
     }

}
