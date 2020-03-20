package DataStructure;

import java.util.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
/*
将中缀表达式转为后缀表达式并计算值，包含（）和整数，实现小数运算。 3+4*(5.5-5
*/

public class MidtoSip {
    public static void main(String[] args) {
        String strnew = "1+((2+3)*4)-5";
        StringBuffer transtr = new StringBuffer();
        for(int i=0;i<strnew.length();i++){
            char c =strnew.charAt(i);
            if(c!='.' && c < '0' || c> '9' ){
                transtr.append(" " + c + " ");
            }else{
                transtr.append(c);
                //System.out.println(transtr);
            }
        }
        String str = transtr.toString().trim();
        System.out.println(str);
        //String str = "1 + ( ( 2 + 3 ) * 4 ) - 5";
        List<String> list = new ArrayList<>();
        String[] s = str.split(" ");
        for(String temp : s){
            System.out.print(temp+" ");
        }
        System.out.println();
        HashMap<String, Integer> map = new HashMap<>();
        map.put("+", 0);
        map.put("-", 0);
        map.put("*", 1);
        map.put("/", 1);
        for (String temp : s) list.add(temp);
        LinkedList<String> queue = new LinkedList<>();
        Stack<String> stack = new Stack<>();
        for (Iterator iter = list.iterator(); iter.hasNext(); ) {
            String temp = (String) iter.next();
            if (temp.matches("\\d+(\\.\\d+)?")) queue.add(temp);
            else if (temp.equals("+") || temp.equals("-") || temp.equals("*") || temp.equals("/"))
                while (true) if (stack.isEmpty() || stack.peek().equals("(")) {
                    stack.push(temp);
                    break;
                } else if (map.get(temp) > map.get(stack.peek())) {
                    stack.push(temp);
                    break;
                } else queue.add(stack.pop());
            else if (temp.equals("(")) stack.push(temp);
            else if (temp.equals(")")) {
                while (!stack.peek().equals("(")) queue.add(stack.pop());
                stack.pop();
            }
        }
        while (!stack.isEmpty()) queue.add(stack.pop());
        for (String temp : queue)
            System.out.print(temp + " ");
        System.out.println("\n-------------------");


        //计算后缀
        Stack<Double> cump = new Stack<>();
        for(Iterator iter = queue.iterator();iter.hasNext();){
            String temp = (String)iter.next();
            if(temp.matches("\\d+(\\.\\d+)?")){
                cump.push(Double.parseDouble(temp));
            }else if(temp.equals("+") || temp.equals("-") || temp.equals("*") || temp.equals("/")){
                double num1 = cump.pop();
                double num2 = cump.pop();
                if(temp.equals("+")) cump.push(num1 + num2);
                else if(temp.equals("-")) cump.push(num2 - num1);
                else if(temp.equals("*")) cump.push(num1 * num2);
                else cump.push(num2 / num1);
            }
        }
        System.out.printf("%.3f",cump.pop());
    }
}
