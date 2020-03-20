package DataStructure.Tree;

public class Test01 {
    public static void main(String[] args) {
        Node node1 = new Node(1, "宋江");
        Node node2 = new Node(2, "吴用");
        Node node3 = new Node(3, "卢俊义");
        Node node4 = new Node(4, "林冲");
        Node node5 = new Node(5, "关胜");

//        node1.setLeft(node2);
//        node1.setRight(node3);
//        node3.setLeft(node5);
//        node3.setRight(node4);
//        BinaryTree binaryTree = new BinaryTree(node1);
//        Node find = binaryTree.preOrder(6);
//        if(find != null){
//            System.out.println("找到数据为"+find.toString());
//        }else{
//            System.out.println("未找到");
//        }
//        System.out.println("-----------------");
//        Node find1 = binaryTree.midOrder(1);
//        if(find1 != null){
//            System.out.println("找到数据为"+find1.toString());
//        }else{
//            System.out.println("未找到");
//        }
//        System.out.println("-----------------");
//        binaryTree.postOrder();
//        System.out.println("-----------------");
//        Node denode = binaryTree.deleteNode2(3);
//        if(denode != null){
//            System.out.println(denode.toString());
//        }else{
//            System.out.println("未找到");
//        }
//        System.out.println("-----------------");
//        Node find2 = binaryTree.preOrder(6);
//
//        System.out.println("-----------------");
        //int[] arr = {1,2,3,4,5,6,7,8};
        Node[] arrNode = {node1,node2,node3,node4,node5};
        arrToBinaryTree(arrNode, 0);
        BinaryTree bt = new BinaryTree(arrNode[0]);
        System.out.println("-----------------");
        bt.preOrder(5);
    }

    public static void arrToBinaryTree(Node[] arr, int index){
        if(arr.length == 0 || arr.length == 1){
            return;
        }
        System.out.print(arr[index].toString());
        int left = index * 2 + 1;
        int right = index * 2 + 2;
        if(left < arr.length){
            arr[index].setLeft(arr[left]);
            arrToBinaryTree(arr, left);
        }
        if(right < arr.length){
            arr[index].setRight(arr[right]);
            arrToBinaryTree(arr, right);
        }

    }
}


class BinaryTree{
    private Node root;

    public BinaryTree(Node root) {
        this.root = root;
    }

    public Node preOrder(int id){
        return this.root.preOrder(id);
    }

    public Node midOrder(int id){
        return this.root.midOrder(id);
    }

    public void postOrder(){
        this.root.postOrder();
    }

    public Node deleteNode(int id){
        return this.root.deleteNode(id);
    }

    public Node deleteNode2(int id){
        return this.root.deleteNode2(id);
    }
}


class Node{
    private int id;
    private String name;
    private Node left;
    private Node right;

    public Node(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public Node preOrder(int id){
        System.out.println(this.toString());
        if(this.getId() == id){
            System.out.println("已找到");
            return this;
        }
        Node temp = null;
        if(this.getLeft()!=null){
            temp = this.getLeft().preOrder(id);
            if(temp != null){
                return temp;
            }
        }
        if(this.getRight()!=null){
            temp = this.getRight().preOrder(id);
        }
        return temp;
    }

    public Node midOrder(int id){
        Node temp = null;
        if(this.getLeft()!=null){
            temp = this.getLeft().midOrder(id);
            if(temp != null){
                return temp;
            }
        }
        System.out.println(this.toString());
        if(this.getId() == id){
            return this;
        }
        if(this.getRight()!=null){
            temp = this.getRight().midOrder(id);
        }
        return temp;
    }

    public void postOrder(){
        if(this.getLeft()!=null){
            this.getLeft().postOrder();
        }
        if(this.getRight()!=null){
            this.getRight().postOrder();
        }
        System.out.println(this.toString());
    }

    public Node deleteNode(int id){
        Node temp = null;
        if(this.getLeft()!=null && this.getLeft().getId() == id){
            temp = this.getLeft();
            this.setLeft(null);
            return temp;
        }
        if(this.getRight()!=null && this.getRight().getId() == id){
            temp = this.getRight();
            this.setRight(null);
            return temp;
        }
        if(this.getLeft()!=null){
            temp = this.getLeft().deleteNode(id);
            if(temp != null){
                return temp;
            }
        }
        if(this.getRight()!=null){
            temp = this.getRight().deleteNode(id);
        }
        return temp;
    }

    public Node deleteNode2(int id){
        Node temp = null;
        if(this.getLeft()!=null && this.getLeft().getId() == id){
            temp = this.getLeft();
            if(this.getLeft().getLeft()!= null || this.getLeft().getRight()!=null){
                if(this.getLeft().getLeft()!= null && this.getLeft().getRight()!=null){
                    this.getLeft().getRight().setLeft(this.getLeft().getLeft());
                    this.setLeft(this.getLeft().getRight());
                }
            }else{
                this.setLeft(null);
            }
            return temp;
        }
        if(this.getRight()!=null && this.getRight().getId() == id){
            temp = this.getRight();
            if(this.getRight().getLeft()!= null || this.getRight().getRight()!=null){
                if(this.getRight().getLeft()!= null && this.getRight().getRight()!=null){
                    this.getRight().getRight().setLeft(this.getRight().getLeft());
                    this.setRight(this.getRight().getRight());
                }
            }else{
                this.setRight(null);
            }
            return temp;
        }
        if(this.getLeft()!=null){
            temp = this.getLeft().deleteNode2(id);
            if(temp != null){
                return temp;
            }
        }
        if(this.getRight()!=null){
            temp = this.getRight().deleteNode2(id);
        }
        return temp;
    }
}