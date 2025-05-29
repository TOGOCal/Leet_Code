import java.util.*;

public class p3527 {

    /**
     * 给你一个二维字符串数组 responses，其中每个 responses[i] 是一个字符串数组，表示第 i 天调查的回答结果。
     *
     * 请返回在对每个 responses[i] 中的回答 去重 后，所有天数中 最常见 的回答。
     * 如果有多个回答出现频率相同，则返回 字典序最小 的那个回答。
     */

    public static void main(String[] args) {


        String[][] res = new String[][]{{"good","ok","good","ok"},{"ok","bad","good","ok","ok"},{"good"},{"bad"}};
        List<List<String>> ans = new ArrayList<>();
        for(String[] t : res){

            List<String> d = new ArrayList<>(Arrays.asList(t));
            ans.add(d);
        }
        System.out.println(new Solution2().findCommonResponse(ans));
    }


    static
    class Solution2{

        public String findCommonResponse(List<List<String>> responses) {

            HashSet<String> temp = new HashSet<>();
            HashMap<String , Integer> map = new HashMap<>();
            for(List<String> s : responses){

                temp.addAll(s);
                for(String string : temp)
                    map.put(string , map.getOrDefault(string , 0) + 1);
                temp.clear();
            }

            int max = 0;
            String res = null;
            for(Map.Entry<String , Integer> t : map.entrySet()){

                if(t.getValue() > max){

                    max = t.getValue();
                    res = t.getKey();
                }else if(t.getValue() == max){

                    res = stringCompare(res , t.getKey());
                }
            }

            return res;
        }

        public String stringCompare(String a , String b){
            if(a == null)
                return b;
            if(b == null)
                return a;

            char[] strA = a.toCharArray();
            char[] strB = b.toCharArray();

            for(int i = 0 ; i < Math.min(a.length() , b.length()) ; i ++){

                if(strA[i] < strB[i])
                    return a;
                if(strA[i] > strB[i])
                    return b;
            }

            return a.length() > b.length() ? b : a;
        }
    }

    static
    class Solution {

        public class Node{

            Node[] next = new Node[26];
            Stack<Integer> contribute = new Stack<>();
        }

        public class Tree{

            Node head;

            Tree(){

                this.head = new Node();
                maxTime = 0;
                temp = new StringBuilder();
                res = null;
            }

            public void add(String value , int id){

                Node cur = head;

                for(char c : value.toCharArray()){

                    if(cur.next[c - 'a'] == null)
                        cur.next[c - 'a'] = new Node();
                    cur = cur.next[c - 'a'];
                }

                //由于是一个一个遍历，所以如果处于同一个回答，栈的最上面也应该是这个数
                if(cur.contribute.isEmpty()
                        || cur.contribute.peek() != id)
                    cur.contribute.push(id);

            }

            int maxTime;
            StringBuilder temp;
            String res;
            public String maxAppear(){

                dfs(head);
                return res;
            }

            private void dfs(Node cur){
                //这个位置是一个结束位置
                //if(!cur.contribute.isEmpty()){
                if(cur.contribute.size() > maxTime){

                    maxTime = cur.contribute.size();
                    res = temp.toString();
                }
                //}

                for(int i = 0 ; i < cur.next.length ; i ++){

                    if(cur.next[i] == null)
                        continue;

                    temp.append((char)(i + 'a'));
                    dfs(cur.next[i]);
                    temp.setLength(temp.length() - 1);
                }
            }
        }

        public String findCommonResponse(List<List<String>> responses) {

            Tree t = new Tree();
            for(int i = 0 ; i < responses.size() ;  i++){

                List<String> temp = responses.get(i);

                for(String v : temp)
                    t.add(v , i);
            }

            return t.maxAppear();
        }
    }
}