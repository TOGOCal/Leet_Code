import java.util.Scanner;

public class p6 {
    /**
     * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
     *
     * 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
     *
     * P A H N
     * A P L S I I G
     * Y I R
     * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
     *
     * 请你实现这个将字符串进行指定行数变换的函数：
     *
     * string convert(string s, int numRows);
     */

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        String str = s.nextLine();

        int numRows = s.nextInt();

        Solution solution = new Solution();

        System.out.println(solution.convert(str, numRows));

        s.close();

    }

    //解法2，使用矩阵
    static class Solution {
        public String convert(String s, int numRows) {

            if(numRows == 0){

                return null;
            }

            if(numRows == 1){

                return s;
            }

            int length = 2*(s.length()/(numRows-1) +1);//矩阵大概需要多少排

            char[][] chars = new char[numRows][length];
            //初始化
            for(int i = 0; i < numRows; i++){

                for(int j = 0; j < length; j++){

                    chars[i][j] = 0;
                }
            }

            int p1 = 0;//列指针
            int p2 = 0;//行指针

            boolean key = true;//决定向上还是向下

            char[] str = s.toCharArray();

            for(char c : str){
                chars[p2][p1] = c;

                if(key){

                    if(p2 == numRows-1){

                        p2 --;
                        key = !key;
                        p1++;
                    }//到达了底部
                    else{

                        p2++;
                    }
                }else{

                    if(p2 == 0){

                        p2 ++;
                        key = !key;
                        p1 ++;
                    }//到达了顶部
                    else{

                        p2 --;
                    }

                }


            }

            StringBuilder sb = new StringBuilder();

            for(int i = 0; i < numRows; i++){

                for(int j = 0; j < length; j++){

                    if(chars[i][j] != 0){

                        sb.append(chars[i][j]);
                    }
                }
            }

            return sb.toString();
        }
    }

//    static class Solution {
//        // 方法简述：
//        // 构建numRows个上下连接的双向链表，然后执行加入操作的时候指针上下移动
//
//        public String convert(String s, int numRows) {
//
//            if (numRows == 0) {
//
//                return null;
//            }
//
//            if (numRows == 1) {
//
//                return s;
//            }
//
//            Overall o = new Overall(numRows);
//
//            return o.buildString(s);
//        }
//
//        public class Overall {
//
//            MyLinkedList begin;
//            MyLinkedList p;// 指针//上下移动
//
//            Overall(int numRows) {
//                // 保证输入的这个数字不是0
//
//                begin = new MyLinkedList();
//                p = begin;
//
//                MyLinkedList now = begin;
//                for (int i = 1; i < numRows; i++) {
//                    // 构建过了begin，所以从1开始
//                    MyLinkedList l = new MyLinkedList();
//                    now.down = l;
//                    l.up = now;
//
//                    now = l;
//                } // 完成构建
//
//            }
//
//            public String buildString(String str) {
//                boolean key = true;// 决定Z变换的方向
//
//                if (str == null || str.isEmpty()) {
//
//                    return str;
//                }
//
//                char[] chars = str.toCharArray();
//                StringBuilder sb = new StringBuilder();
//
//                for (char c : chars) {
//
//                    p.add(c);
//
//                    if (key) {
//
//                        if (p.down == null) {
//
//                            p = p.up;
//                            key = false;
//                        } else {
//
//                            p = p.down;
//                        }
//                    } else {
//
//                        if (p.up == null) {
//
//                            p = p.down;
//                            key = true;
//                        } else {
//
//                            p = p.up;
//                        }
//
//                    }
//                } // 完成Z字型构建
//
//                p = begin;
//
//                while (p != null) {
//
//                    Node nowNode = p.head.next;// head和tail是无效的
//                    while (nowNode.next != null) {
//                        // 终止条件是nowNode = tail tail的next是null
//                        sb.append(nowNode.value);
//                        nowNode = nowNode.next;
//                    }
//
//                    p = p.down;
//                }
//
//                return sb.toString();
//            }
//        }
//
//        public class MyLinkedList {
//
//            Node head;
//            Node tail;
//
//            MyLinkedList up;
//            MyLinkedList down;
//
//            MyLinkedList() {
//
//                head = new Node((char) 0);
//                tail = new Node((char) 0);
//                head.next = tail;
//                tail.pre = head;
//
//                up = null;
//                down = null;
//            }
//
//            public void add(char value) {
//
//                Node newNode = new Node(value);
//
//                Node pre = tail.pre;
//
//                pre.next = newNode;
//                newNode.pre = pre;
//
//                newNode.next = tail;
//                tail.pre = newNode;
//            }
//        }
//
//        public class Node {
//
//            char value;
//
//            // Node up;
//            // Node down;
//            // //这两个属性只有每个链表的头节点是有效的
//
//            Node next;
//            Node pre;
//
//            Node(char value) {
//
//                this.value = value;
//
//                // up = null;
//                // down = null;
//
//                next = null;
//                pre = null;
//            }
//        }
//    }
}
