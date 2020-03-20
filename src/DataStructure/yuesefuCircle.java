package DataStructure;

import java.util.Scanner;

public class yuesefuCircle {
    public static void main(String[] args) {
//        约瑟夫环测试
//        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();
//        CList list = new CList(n);
//        for (int i = 0; i < n; i++) {
//            list.add(new CNode(i));
//        }
//        list.print();
//        int num = 0;
//        CNode c1 = list.head;
//        while (num != n) {
//            c1 = list.pop(c1, 3);
//            num++;
//            list.print();
//        }
//        for(int i=0;i<n;i++){
//            System.out.print(list.a[i]+" ");
//        }
        // 栈测试
        Stack s = new Stack(3);
        System.out.println(s.isEmpty());
        s.push(new CNode(2));
        s.push(new CNode(5));
        s.push(new CNode(6));
        s.print();
        System.out.println(s.isFull());
        System.out.println(s.pop().num);
        s.print();
    }
}

class CList {
    CNode head;
    int[] a;
    int index = 0;
    public CList(int n) {
        head = new CNode(-1);
        a = new int[n];
    }

    public void add(CNode c) {
        CNode temp = head;
        while (true) {
            if (temp.next == null || temp.next.num == head.num) {
                break;
            }
            temp = temp.next;
        }
        temp.next = c;
        temp.next.next = head;
    }

    public void print() {
        CNode temp = head.next;
        while (temp.num != head.num) {
            if (temp.flag) {
                System.out.print(temp.num + " ");
            }
            temp = temp.next;
        }
        System.out.println();
    }

    public CNode pop(CNode startNode, int k) {//从startNode作为第一个开始数k个弹出
        for (int count = 0; count < k; ) { // k=3
            if (startNode.num != head.num && startNode.flag) {
                count++;
                if (count == k) {
                    break;
                }
            }
            startNode = startNode.next;
        }
        startNode.flag = false;
        //System.out.println(startNode.num);
        a[index] = startNode.num;
        index++;
        return startNode.next;
    }
}

class CNode {
    int num;
    boolean flag;
    CNode next;

    public CNode(int num) {
        this.num = num;
        this.flag = true;
    }
}

class Stack{
    private int maxSize ;
    private CNode head;
    public Stack(int maxSize){
        this.maxSize = maxSize;
        head = new CNode(-1);
    }

    public boolean isFull(){
        int count = 0;
        CNode temp = head.next;
        while(temp != null){
            count++;
            if(count == maxSize){
                return true;
            }
            temp = temp.next;
        }
        return false;

    }

    public boolean isEmpty(){
        return head.next == null;
    }

    public void push(CNode node){
        if(isFull()){
            System.out.println("栈满");
            return;
        }
        node.next = head.next;
        head.next = node;
    }

    public CNode pop(){
        if(isEmpty()){
            System.out.println("栈空");
            return head;
        }
        CNode temp = head.next;
        head.next = head.next.next;
        return temp;
    }
    public void print(){
        CNode temp = head.next;
        while(temp != null){
            System.out.print(temp.num + " ");
            temp = temp.next;
        }
    }
}