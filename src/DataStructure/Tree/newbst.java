package DataStructure.Tree;

public class newbst {
    public static void main(String[] args) {
        //int[] arr = {7, 3, 10, 12, 5, 1, 9,51,66,4,87,60,11,8};
        int[] arr = {1,2,3,4};
        BST bst = new BST(new BNode(arr[0]));
        for (int i = 1; i < arr.length; i++) {
            bst.insertNode(new BNode(arr[i]));
        }
        bst.midOrder();
        System.out.println("------------------");
        bst.delNode(4);
        bst.midOrder();
    }
}

class BST{
    BNode root;

    public BST(BNode root) {
        this.root = root;
    }

    public void insertNode(BNode node){
        root.insertNode(node);
    }

    public void midOrder(){
        if(root != null){
            root.midOrder();
        }else{
            System.out.println("这是空树");
        }
    }

    public static BNode findMinNode(BNode root){//根据传入的节点的值，找到以它为跟的右子树中的最小值并返回该节点的父节点。
        if(root.right != null){
            BNode temp = root.right;
            while (temp.left != null) {
                if (temp.left.left == null) {
                    break;
                } else {
                    temp = temp.left;
                }
            }
            return temp;
        }
        return null;
    }

    public void delNode(int num){
        if (root.num == num) { // 说明删根节点
            if(root.left == null || root.right == null){
                if(root.left == null){
                    root = root.right;
                }else if(root.right == null){
                    root = root.left;
                }else{
                    System.out.println("唯一节点被删除");
                    root = null;
                }
                return;
            }
            BNode targetParent = findMinNode(root);
            BNode target = targetParent.left;
            targetParent.left = null;
            target.left = root.left;
            target.right = root.right;
            root.num = target.num;
            return;
        }
        BNode node = root;
        boolean flag = true;
        while (true) {
            if (num < node.num) {
                if (node.left == null) {// 无该节点
                    flag = false;
                    break;
                }
                if (node.left.num == num) { // 找到
                    if (node.left.left != null || node.left.right != null) { // 有子节点
                        if (node.left.left != null && node.left.right != null) {
                            // 两个子节点
                            BNode temp = node.left;
                            if(temp.right.left == null){
                                temp.right.left = node.left.left;
                                node.left = temp.right;
                            }else{
                                temp = findMinNode(temp);
                                BNode newnode = temp.left;
                                temp.left = null;
                                newnode.left = node.left.left;
                                newnode.right = node.left.right;
                                node.left = newnode;
                            }
                        } else if (node.left.left != null) { //单子节点
                            node.left = node.left.left;
                            System.out.println("删除单子节点");
                        } else {
                            node.left = node.left.right;
                            System.out.println("删除单子节点");
                        }
                    } else { // 叶子结点
                        node.left = null;
                        System.out.println("删除叶子结点");
                    }
                    return;
                } else {
                    node = node.left;
                }
            } else {
                if (node.right == null) {
                    flag = false;
                    break;
                }
                if (node.right.num == num) {
                    if (node.right.left != null || node.right.right != null) {
                        if (node.right.left != null && node.right.right != null) {

                            BNode temp = node.right;
                            if(temp.right.left == null){
                                temp.right.left = node.right.left;
                                node.right = temp.right;
                            }else{
                                temp = findMinNode(temp);
                                BNode newnode = temp.left;
                                temp.left = null;
                                newnode.left = node.right.left;
                                newnode.right = node.right.right;
                                node.right = newnode;
                            }
                        } else if (node.right.left != null) {
                            node.right = node.right.left;
                            System.out.println("删除单子结点");
                        } else {
                            node.right = node.right.right;
                            System.out.println("删除单子结点");
                        }
                    } else {
                        node.right = null;
                        System.out.println("删除叶子结点");
                    }
                    return;
                } else {
                    node = node.right;
                }
            }
        }
        if (!flag) {
            System.out.println("没有该节点");
        }
    }
}


class BNode {
    int num;
    BNode left;
    BNode right;

    public BNode(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "BSTNode{" +
                "num=" + num +
                '}';
    }

    public void midOrder() {
        if (this.left != null) {
            this.left.midOrder();
        }
        System.out.println(this.toString());
        if (this.right != null) {
            this.right.midOrder();
        }
    }

    public void insertNode(BNode node){
        if(node.num < this.num){
            if(this.left == null){
                this.left = node;
            }else{
                this.left.insertNode(node);
            }
        }else{
            if(this.right == null){
                this.right = node;
            }else{
                this.right.insertNode(node);
            }
        }
    }
}