package DataStructure.recursion;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class HanNuoTa {
    public static void main(String[] args) {
        Stack<Integer> list1 = new Stack<>();
        Stack<Integer> list2 = new Stack<>();
        Stack<Integer> list3 = new Stack<>();
        //最终目标，在list3
        for(int i=3;i >0; i--){
            list1.push(i);
        }
        for(int temp : list1){
            System.out.print(temp + " ");
        }
    }
    public static void hanNuo(){

    }
}
