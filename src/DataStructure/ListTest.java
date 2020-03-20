package DataStructure;

public class ListTest {
    public static LinkedList merge(LinkedList list1, LinkedList list2){
        LNode headnext;
        boolean flag = false;
        //假设list1的第一位小,把list1当做母链
        LNode temp1 = list1.head.next;
        LNode temp2 = list2.head.next;
        if(temp1.getRank() > temp2.getRank()){
            flag = true; // 说明list2第一个节点小，最终返回list2
            temp1 = list2.head.next;  //temp1永远在第一个节点小的链上，最后返回的也是temp1所在的链
            temp2 = list1.head.next;
        }
        while(temp2 != null && temp1.next != null){
            if(temp2.getRank() < temp1.next.getRank()){
                headnext = temp2.next;
                temp2.next = temp1.next;
                temp1.next = temp2;
                temp2 = headnext;
            }else{
                temp1 = temp1.next;
            }
        }
        if(temp1.next == null){
            temp1.next = temp2;
        }
        if(flag == true){
            return list2;
        }
        return list1;
        //从第一个节点开始遍历list2


    }

    public static void main(String[] args) {
        LNode node1 = new LNode(1,"a");
        LNode node11 = new LNode(1,"11");

        LNode node2 = new LNode(2,"b");
        LNode node3 = new LNode(3,"c");
        LNode node4 = new LNode(4,"d");
        LNode node5 = new LNode(5,"e");
        LNode node6 = new LNode(6,"f");
        LNode node7 = new LNode(7,"g");
        LNode node8 = new LNode(8,"h");
        LNode node9 = new LNode(9,"i");
        LNode node10 = new LNode(10,"j");
        LinkedList list1 = new LinkedList();
        list1.add(node1);
        list1.add(node3);
        list1.add(node4);
        list1.add(node7);
        list1.add(node10);
        list1.print();
        System.out.println("---------------------");
        LinkedList list2 = new LinkedList();
        list2.add(node11);
        list2.add(node5);
        list2.add(node6);
        list2.add(node8);
        list2.add(node9);
        list2.print();
        System.out.println("----------------------");

        //打印合并后的
        list1 = merge(list1, list2);
        list1.print();

        //System.out.println(list1.count());
        //list1.reverse();
        //list1.print();

        //System.out.println(list.SelectResNumK(1));
    }
}

class LinkedList{
    public LNode head;
    public LinkedList(){
        head = new LNode(0,"");
    }

    public void add(LNode node){
        LNode temp = head;
        while(true){
            if(temp.next == null)
                break;
            temp = temp.next;
        }
        temp.next = node;
    }

    public int count(){
        int count = 0;
        LNode temp = head.next;
        while(temp != null){
            count++;
            temp = temp.next;
        }
        return count;
    }

    public LNode SelectResNumK(int k){//查找倒数第k个节点并返回，使用差距k个节点的双指针
        int count = 0;
        LNode temp1 = head.next;
        LNode temp2 = head.next;

        while(temp2 != null){
            count++;
            if (count > k){
                temp1 = temp1.next;
            }
            temp2 = temp2.next;
        }
        return temp1;
    }

    public void reverse(){//头插法
        if(head.next == null || head.next.next == null){
            return ;
        }
        LNode temp = head.next;
        LNode next = null;
        LNode revHead = new LNode(0," ");
        while(true){
            if(temp == null)
                break;
//            head.next = temp.next;
//            temp.next = revHead.next;
//            revHead.next = temp;
//            temp = head.next;
            next = temp.next;
            temp.next = revHead.next;
            revHead.next = temp;
            temp = next;
        }
        head.next = revHead.next;
        revHead.next = null;
    }

    public void print(){
        if(head.next == null){
            System.out.println("空链表");
            return;
        }
        LNode temp = head.next;
        while(true){
            System.out.println(temp);
            temp = temp.next;
            if(temp == null){
                break;
            }
        }
    }
}
class LNode{
        private int rank;
        private String name;
        LNode next;

        public LNode(int rank, String name) {
            this.rank = rank;
            this.name = name;
        }

        public int getRank() {
            return rank;
        }

        @Override
        public String toString() {
            return "Hero{" +
                    "rank=" + rank +
                    ", name='" + name + '\'' +
                    '}';
        }

}