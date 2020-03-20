package DataStructure;
/*
*
*   单向链表的增删改测试
*
* */
public class LinkList {
    public static void main(String[] args) {
        Hero h1 = new Hero(1,"宋江","asd");
        Hero h2 = new Hero(2,"吴勇","zxc");
        Hero h3 = new Hero(3,"张三","qaz");
        Hero h4 = new Hero(4,"李四","wsx");
        List l =new List();
//        l.rankadd(h1);
//        l.rankadd(h3);
//        l.rankadd(h4);
//        l.rankadd(h2);
//        l.rankadd(h4);
        l.addByOrder(h1);
        l.addByOrder(h3);
        l.addByOrder(h4);
        l.addByOrder(h2);
        l.print();
        l.update(new Hero(5,"五月","520"));
        l.print();
    }
}

class List{
    Hero head;
    public List() {
        head = new Hero(0,"","");
    }

    public void add(Hero h){//无序添加
        Hero temp = head;
        while(true){
            if(temp.next == null){
                break;
            }
            temp = temp.next;
        }
        temp.next = h;
    }
    public void addByOrder(Hero h){//单向链表有序添加
        Hero temp = head;
        while(true){
            if(temp.next == null){
                temp.next = h;
                return;
            }
            if(h.getRank()<temp.next.getRank()){
                h.next = temp.next;
                temp.next = h;
                return;
            }
            temp = temp.next;
        }
    }
    public void update(Hero newh){//单向链表更改
        if(head.next == null){
            System.out.println("kong lian biao");
        }
        Hero temp = head;
        while(true){
            if(temp.next == null){
                System.out.println("未找到需要替换的，链表遍历完毕");
                return;
            }
            if(temp.next.getRank() == newh.getRank()){
                newh.next = temp.next.next;
                temp.next = newh;
                System.out.println("替换rank号"+newh.getRank()+"成功");
                return;
            }
            temp = temp.next;
//            if(temp.next.next == null && temp.next.getRank() == newh.getRank()){
//                temp.next = newh;
//                newh.next = null;
//            }
        }
    }
    public void rankadd(Hero h){//双向链表有序添加
        Hero temp = head;
        while(true){
            if(h.getRank()>temp.getRank()){
                //temp.next.front = temp;
                if(temp.next == null){
                    temp.next = h;
                    h.front = temp;
                    return;
                }
                temp = temp.next;
            }else if(h.getRank()<temp.getRank()){
                temp.front.next = h;
                h.front = temp.front;
                h.next = temp;
                temp.front = h;
                return;
            }else{
                System.out.println("排序相同");
                return;
            }
        }
    }

    public void print(){
        if(head.next == null){
            System.out.println("空链表");
            return;
        }
        Hero temp = head.next;
        while(true){
            System.out.println(temp);
            temp = temp.next;
            if(temp == null){
                break;
            }
        }
    }

}

class Hero{
    private int rank;
    private String name;
    private String nick;
    Hero next;
    Hero front;

    public Hero(int rank, String name, String nick) {
        this.rank = rank;
        this.name = name;
        this.nick = nick;
    }

    public int getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return "Hero{" +
                "rank=" + rank +
                ", name='" + name + '\'' +
                ", nick='" + nick + '\'' +
                '}';
    }
}