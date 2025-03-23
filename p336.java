package from_300_to_400;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class p336 {

    /**
     * 给定一个由唯一字符串构成的 0 索引 数组 words 。
     *
     * 回文对 是一对整数 (i, j) ，满足以下条件：
     *
     * 0 <= i, j < words.length，
     * i != j ，并且
     * words[i] + words[j]（两个字符串的连接）是一个回文串。
     * 返回一个数组，它包含 words 中所有满足 回文对 条件的字符串。
     *
     * 你必须设计一个时间复杂度为 O(sum of words[i].length) 的算法。
     */

    public static void main(String[] args) {

        String[] test = new String[]{"bb","bababab","baab","abaabaa","aaba","","bbaa","aba","baa","b"};
        System.out.println(new Solution().palindromePairs(test));
    }



    static
    class Solution {

        class Node{

            char value;
            Node[] next;
            boolean isEnd;
            int whichEnd;
            Node(char value){

                this.value = value;
                next = new Node[26];
            }
        }

        Node root;

        public List<List<Integer>> get(String str , int index){

            List<List<Integer>> res = new ArrayList<>();

            //在树中寻找有没有相同的部分
            Node cur = root;
            int i = 0;
            while (i < str.length() && cur.next[str.charAt(i) - 'a'] != null){

                cur = cur.next[str.charAt(i) - 'a'];
                i++;

                if(cur.isEnd && i != str.length()){

                    String string = str.substring(i);
                    StringBuilder sb1 = new StringBuilder(string);
                    StringBuilder sb2 = new StringBuilder(string);
                    if(sb1.reverse().compareTo(sb2) == 0){
                        //剩下的部分是个回文串
                        List<Integer> r = new ArrayList<>();
                        r.add(index);
                        r.add(cur.whichEnd);
                        res.add(r);
                    }
                }
            }


            if(i == str.length()){

                if(cur.isEnd && cur.whichEnd != index){
                    List<Integer> r = new ArrayList<>();
                    r.add(cur.whichEnd);
                    r.add(index);
                    res.add(r);

                }

                //查看树下剩下的部分（对于a万一即存在a,又存在abb，需要讨论
                List<Integer> r = new ArrayList<>();
                for(Node next : cur.next)
                    if(next != null)
                        have(next , new StringBuilder() , r);

                for(Integer in : r){

                    List<Integer> R = new ArrayList<>();
                    R.add(index);
                    R.add(in);
                    res.add(R);
                }
            }

            return res;
        }

        public void have(Node cur , StringBuilder sb , List<Integer> res){

            sb.append(cur.value);
            if(cur.isEnd){

                StringBuilder r = new StringBuilder(sb);
                r = r.reverse();
                if(r.compareTo(sb) == 0){

                    //这是一个回文串
                    res.add(cur.whichEnd);
                }
            }

            for(Node next : cur.next){

                if(next != null){

                    have(next , sb , res);
                }
            }
            sb.setLength(sb.length() - 1);
        }

        public void add(StringBuilder sb , int index){

            Node cur = root;
            for(char c : sb.toString().toCharArray()){

                if(cur.next[c - 'a'] == null)
                    cur.next[c - 'a'] = new Node(c);
                cur = cur.next[c - 'a'];
            }

            cur.isEnd = true;
            cur.whichEnd = index;
        }

        public List<List<Integer>> palindromePairs(String[] words) {

            List<List<Integer>> res = new ArrayList<>();
            root = new Node(' ');

            for(int i = 0 ; i < words.length ; i ++){

                StringBuilder sb = new StringBuilder(words[i]);
                add(sb.reverse() , i);
            }

            for(int i = 0 ; i < words.length ; i ++){

                String str = words[i];

                if(str.isEmpty()){

                    List<Integer> r = new ArrayList<>();
                    for(Node next : root.next)
                        if(next != null)
                            have(next , new StringBuilder() , r);//检查本来就是回文串的

                    for(int in : r){

                        List<Integer> r1 = new ArrayList<>();
                        r1.add(i);
                        r1.add(in);
                        res.add(r1);

                        List<Integer> r2 = new ArrayList<>();
                        r2.add(in);
                        r2.add(i);
                        res.add(r2);
                    }

                    continue;
                }
                List<List<Integer>> g = get(str , i);
                res.addAll(g);
            }


            return res;
        }
    }
}
