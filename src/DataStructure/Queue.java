package DataStructure;

import java.util.*;

class ArrayQueue{
    private static int MAX_SIZE ;
    //private List<Integer> list;
    private int[] a;
    private int front = -1;
    private int real = 0;

    public ArrayQueue(int maxSize){
        //list = new ArrayList<>();
        this.MAX_SIZE = maxSize;
        a = new int[ this.MAX_SIZE];
    }

    public boolean add(int i){
        if(real%MAX_SIZE != front){
            if(front == -1){
                front = 0;
            }//说明是第一次添加，
            //real = (real+1)%5 ;

            a[real%MAX_SIZE] = i;
            real = real+1;
            return true;
        }else{
            System.out.println("queue is max");
        }
        return false;
    }

    public int pop(){
        if(!this.isEmpty()){
            //real--;
           //return list.remove(0);
           int temp = a[front%MAX_SIZE];
           front = front+1 ;
           return temp;
        }
        return -1;
    }
    public boolean isFull(){
        if(real - front == MAX_SIZE){
            return true;
        }
        return false;
    }
    public boolean isEmpty(){
        if(front == -1 || real == front){
            return true;
        }
        return false;
    }

    public int size(){
        //return real - front;
        return MAX_SIZE-front+real%MAX_SIZE;
        // return 0;
    }

    public void print(){
//        for(Iterator iter = list.iterator(); iter.hasNext();){
//            System.out.print(iter.next()+" ");
//        }
//        System.out.println();
        for(int i=0; i<this.size();i++){
            System.out.print(a[(front+i) %MAX_SIZE]+" ");
        }
        System.out.println();
    }
}


public class Queue {
    public static void main(String[] args) {
        ArrayQueue aq = new ArrayQueue(5);
        System.out.println(aq.isEmpty());
        aq.add(2);
        aq.add(3);
        aq.add(4);
        aq.add(6);
        System.out.println("duilie shifou yi man :"+ aq.isFull());
        aq.add(8);
        System.out.println("duilie shifou yi man :"+ aq.isFull());
       // aq.add(4);
        aq.print();
        System.out.println(aq.pop());
        aq.add(9);
        aq.add(9);
        System.out.println("duilie shifou yi man :"+ aq.isFull());
        System.out.println(aq.isEmpty());
        int size = aq.size();
        System.out.println(size);
        aq.print();
        for(int i = 0; i< size;i++){
            System.out.println(aq.pop());
            aq.print();
        }
        System.out.println(aq.isEmpty());    }
}


