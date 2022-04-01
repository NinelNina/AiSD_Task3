package ru.vsu.cs.kravtsova_n_e.task2;

import java.util.Comparator;
import java.util.Iterator;

public class SimpleLinkedList<T> implements Iterable<T> {
    public static class SimpleLinkedListException extends Exception{
        public SimpleLinkedListException(String message) {
            super(message);
        }
    }

    private class ListNode{
        public T value;
        public ListNode next;

        public ListNode(T value, ListNode next) {
            this.value = value;
            this.next = next;
        }
        public ListNode(T value) {
            this(value, null);
        }
    }

    private ListNode head = null;
    private ListNode tail = null;
    private int size = 0;

    public void addFirst(T value){
        head = new ListNode(value, head);
        if (size == 0){
            tail = head;
        }
        size++;
    }

    public void addLast(T value) {
        if (size == 0) {
            head = tail = new ListNode(value);
        } else {
            tail.next = new ListNode(value);
            tail = tail.next;
        }
        size++;
    }

    private void checkEmptyError() throws SimpleLinkedListException{
        if (size == 0){
            throw new SimpleLinkedListException("List is empty");
        }
    }

    private ListNode getNode(int index) throws SimpleLinkedListException{
        if (index < 0 || index >= size){
            throw new SimpleLinkedListException("Index is incorrect");
        }
        ListNode curr = head;
        for (int i = 0; i < index; i++){
            curr = curr.next;
        }
        return curr;
    }

    public void remove(int index) throws SimpleLinkedListException {
        checkEmptyError();
        if (index < 0 || index >= size){
            throw new SimpleLinkedListException("Index is incorrect");
        }
        if (index == 0){
            removeFirst();
        } else {
          ListNode prev = getNode(index - 1);
          prev.next = prev.next.next;
          if (prev.next == null){
              tail = prev;
          }
          size--;
        }
    }

    public void removeFirst() throws SimpleLinkedListException{
        checkEmptyError();
        head = head.next;
        if (size == 1){
            tail = null;
        }
        size--;
    }

    public void removeLast() throws SimpleLinkedListException{
        checkEmptyError();
        if (size == 1){
            head = tail = null;
        } else {
            tail = getNode(size - 2);
            tail.next = null;
        }
    }

    public T get(int index) throws SimpleLinkedListException{
        checkEmptyError();
        return getNode(index).value;
    }

    public T getFirst(int index) throws SimpleLinkedListException{
        checkEmptyError();
        return head.value;
    }

    public T getLast(int index) throws SimpleLinkedListException{
        checkEmptyError();
        return tail.value;
    }

    public int size(){
        return size;
    }

    private void swapElements(int index1, int index2) throws SimpleLinkedListException{
        checkEmptyError();

        ListNode node1 = getNode(index1);
        ListNode node2 = getNode(index2);

        node1.next = node2.next;
        node2.next = node1;

        if (index1 > 0) {
            ListNode prev = getNode(index1 - 1);
            prev.next = node2;
        } else if (index1 == 0){
            head = node2;
        } else if (index2 == 0){
            head = node1;
        }

        if (index2 == size - 1){
            tail = node1;
            tail.next = null;
        } else if (index1 == size - 1){
            tail = node2;
            tail.next = null;
        }
    }

    public void sort(Comparator<T> comparator) throws Exception {
        checkEmptyError();
        int size = this.size();
        for (int i = 1; i < size - 1; i++) {
            for (int j = size - 1; j >= i; j--) {
                if (comparator.compare(this.get(j - 1), this.get(j)) > 0) {
                    this.swapElements(j - 1, j);
                }
            }
        }
    }

    @Override
    public Iterator<T> iterator() {
        class SimpleLinkedListIterator implements Iterator<T> {
            ListNode curr = head;

            @Override
            public boolean hasNext() {
                return curr != null;
            }

            @Override
            public T next() {
                T value = curr.value;
                curr = curr.next;
                return value;
            }
        }
        return new SimpleLinkedListIterator();
    }
}
