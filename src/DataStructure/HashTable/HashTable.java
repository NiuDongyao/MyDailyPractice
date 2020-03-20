package DataStructure.HashTable;

import java.util.Scanner;

public class HashTable {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean flag = true;
        HashT ht = new HashT(8);
        while (flag){
            System.out.println("输入add添加数据");
            System.out.println("输入print展示数据");
            System.out.println("输入find查找数据");
            String input = in.next();
            switch (input){
                case "add":
                    int id = in.nextInt();
                    String name = in.next();
                    ht.addEmp(new EmpNode(id, name));
                    break;
                case "print":
                    ht.HashPrint();
                    break;
                case "find":
                    id = in.nextInt();
                    EmpNode e = ht.hashFindEmpById(id);
                    if(e != null){
                        System.out.println(e.toString());
                    }
                    break;
                case"exit":
                    flag = false;
                    break;

            }
        }
    }
}

class EmpNode{
    public int id;
    public String name;
    EmpNode next;

    public EmpNode(int id, String name) {
        this.id = id;
        this.name = name;
        this.next = null;
    }

    @Override
    public String toString() {
        return "EmpNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

class EmpLinkedList{
    private EmpNode head;

    public EmpLinkedList() {
        this.head = null;
    }

    public void add(EmpNode emp){


        if(head == null){
            head = emp;
            return;
        }
        EmpNode temp = head;
        while (true){
            if(temp.id == emp.id){
                System.out.println("该值已存在，不能存放");
                return;

            }
            if(temp.next == null){
                temp.next = emp;
                break;
            }
            temp = temp.next;
        }
    }

    public void print(int size){

        if(head == null){
            System.out.println("第"+size+"链表为空");
            return;
        }
        EmpNode temp = head;
        System.out.print("第"+size+"链表:");
        while(temp!=null){
            if(temp.next == null){
                System.out.print("id=:"+ temp.id +"name=:"+temp.name);
            }else{
                System.out.print("id=:"+ temp.id +"name=:"+temp.name+"=>");
            }
            temp = temp.next;
        }
        System.out.println();
    }
    public EmpNode findEmpById(int id){

        if(head == null){
            System.out.println("链表无数据");
            return null;
        }
        EmpNode temp = head;
        while (temp!=null){
            if(temp.id == id){
                return temp;
            }
            temp = temp.next;
        }
        System.out.println("已遍历该链表，未找到");
        return null;
    }
}

class HashT{
    EmpLinkedList[] EmpHashTable;
    int size;
    public HashT(int size) {
        EmpHashTable = new EmpLinkedList[size];
        this.size = size;
        //这里没有直接把所有链表实例化
    }

    public void addEmp(EmpNode emp){
        int count = hash(emp.id);
        if(EmpHashTable[count] == null){
            EmpHashTable[count] = new EmpLinkedList();

        }
        EmpHashTable[count].add(emp);
    }

    public void HashPrint(){
        for(int i=0; i<size; i++){
            if(EmpHashTable[i] != null){
                EmpHashTable[i].print(i);
            }
        }
    }

    public EmpNode hashFindEmpById(int id){
        if(EmpHashTable[hash(id)] == null){
            System.out.println("该链表无数据");
            return null;
        }
        return EmpHashTable[hash(id)].findEmpById(id);
    }

    public int hash(int num){
        return num % size;
    }
}