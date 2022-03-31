package ru.vsu.cs.kravtsova_n_e.task2;

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

    private ListNode getNode(int index){
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
