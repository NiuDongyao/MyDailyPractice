package DataStructure.Tree;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.*;

public class huffmanTree {
    public static void main(String[] args) {
        String str = "i like java like java aaa";
        byte[] bs = str.getBytes();
        //List<TNode> list = new ArrayList<>();
        HashMap<Byte, TNode> hm = new HashMap<>();
        for(byte b : bs){
            if(hm.containsKey(b)){
                hm.get(b).count++;
            }else{
                hm.put(b, new TNode(b));
            }
        }
        System.out.println(hm.size());
        Collection<TNode> c = hm.values();
        List<TNode> list = new ArrayList<>(c);
        for(Iterator iter = list.iterator();iter.hasNext();){
            System.out.println(iter.next().toString());
        }
        System.out.println("-------------------------------");
        createHuffmanTree(list);

        HashMap<Character, String> map = new HashMap<>();
        list.get(0).bianMa(list.get(0));
        list.get(0).preOrder(list.get(0), map);
        System.out.println("-------------------------------");

        HashMap<String, Character> codesMap = new HashMap<>(); // 翻转
        for(Map.Entry<Character, String> entry : map.entrySet()){
            codesMap.put(entry.getValue(), entry.getKey());
            //System.out.println("key=" + entry.getKey() + " value=" + entry.getValue());
        }


        char[] chars = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        for(char ch : chars){
            sb.append(map.get(ch));
        }
        String newstr = sb.toString();
        System.out.println(newstr);

        List<Character> unziplist = unzip(newstr, codesMap);
        System.out.println(unziplist);
        char[] unzipchars = new char[unziplist.size()];
        for(int i=0; i<unzipchars.length; i++){
            unzipchars[i] = unziplist.get(i);
        }
        String unzipStr = new String(unzipchars);
        System.out.println(str);
        System.out.println("------------------");
        System.out.println(unzipStr);
//        int len = (newstr.length() + 7) / 8;
//        byte[] zipByte = new byte[len];
//        int index = 0;
//        for(int i=0;i<newstr.length();i+=8){
//            String substr;
//            if(newstr.length()<i+8){
//                substr = newstr.substring(i);
//            }else {
//                substr = newstr.substring(i, i+8);
//            }
//            System.out.println(substr);
//            byte b = (byte) Integer.parseInt(substr, 2);
//            zipByte[index++] = b;
//        }
//        System.out.println(Arrays.toString(zipByte));
//        for(int i=0;i< zipByte.length; i++){
//            String s = String.valueOf(zipByte[i]);
//            System.out.println(Byte.parseByte(s, 2));
//        }
        ArrayList<Object> arrayList = new ArrayList<>();

    }
    public static List<Character> unzip(String srcstr, Map<String, Character> map){
            int i = 0;
            int count = 1;
            List<Character> list = new ArrayList<>();
            while(i < srcstr.length()){
                String substr = srcstr.substring(i, i + count);
                if(map.get(substr) != null){
                    list.add(map.get(substr));
                    i = i + count;
                    count = 1;

                }else{
                    count++;
                }
            }
            return list;
    }


    public static void createHuffmanTree(List<TNode> list){
        while(list.size() > 1){
            Collections.sort(list);
            TNode node1 = list.remove(0);
            TNode node2 = list.remove(0);
            TNode parent = new TNode();
            parent.left = node1;
            node1.bianma = node1.bianma + "0";
            parent.right = node2;
            node2.bianma = node2.bianma + "1";
            parent.count = node1.count + node2.count;
            list.add(parent);
        }
    }


}

class TNode implements Comparable<TNode>{
    public byte value;
    public TNode left;
    public TNode right;
    public int count;
    public String bianma = "";

    public TNode(){

    }

    public TNode(byte value) {
        this.value = value;
        this.count = 1;
    }

    @Override
    public String toString() {
        return "TNode{" +
                "value=" + (char)value +
                ", bianma=" + bianma +
                '}';
    }

    @Override
    public int compareTo(TNode o) {
        return this.count - o.count;
    }

    public void preOrder(TNode node, Map<Character, String> map){
        //System.out.println(node.value + " " + node.count + " " + node.bianma);
        if(node.value != 0){
            map.put((char)node.value, node.bianma);
        }
        if(node.left != null){
            preOrder(node.left,map);
        }
        if(node.right != null){
            preOrder(node.right,map);
        }
    }
    public void bianMa(TNode node){
        if(node.left == null && node.right == null){
            return;
        }
        if(node.left != null){
            node.left.bianma = node.bianma + node.left.bianma;
            bianMa(node.left);
        }
        if(node.right != null){
            node.right.bianma = node.bianma + node.right.bianma;
            bianMa(node.right);
        }
    }
    //    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        TNode tNode = (TNode) o;
//        return value == tNode.value;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(value);
//    }
}