import java.util.Scanner;

public class p14 {
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        int num = s.nextInt();
        s.nextLine();

        String strs[] = new String[num];

        for (int i = 0; i < num; i++) {

            strs[i] = s.nextLine();
        }

        Solution so = new Solution();
        System.out.println(so.longestCommonPrefix(strs));
        s.close();
    }

    public static class Solution {
        //前缀树
        public String longestCommonPrefix(String[] strs) {

            if(strs == null || strs.length == 0){

                return "";
            }

            if(strs.length == 1){

                return strs[0];
            }

            int minLength=Integer.MAX_VALUE;

            for(String str:strs){

                minLength = Math.min(minLength , str.length());
            }


            StringBuilder sb = new StringBuilder();
            for(int i=0;i<minLength;i++){

                char c = strs[0].charAt(i);

                for(int ii = 1 ;ii < strs.length ;ii++){

                    if(c != strs[ii].charAt(i)){

                        return sb.toString();
                    }
                }

                sb.append(c);

            }

            return sb.toString();
        }



    }
}

/*
public static class Tree{
            Node begin;
            int maxDepth;

            Tree(){

                begin = new Node();
            }

            public void buildTree(String str){

                char[] s = str.toCharArray();

                Node nowNode = begin;
                nowNode.visitTime++;
                for(char c: s){

                    if(nowNode.next[c-'a'] == null){

                        //没有走过就创建新的变量
                        nowNode.next[c-'a'] = new Node();



                        nowNode = nowNode.next[c-'a'];
                    }
                }

            }

        }

        public static class Node{

            int depth;//深度

            int visitTime;

            Node[] next;

            Node(){

                depth = 0;
                visitTime=0;
                next = new Node[26];
            }
        }
 */