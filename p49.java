import java.util.*;

public class p49 {

    /**
     * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
     */

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        int num= s.nextInt();
        s.nextLine();

        String[] strs = new String[num];

        for(int i = 0; i < num; i++){

            strs[i] = s.nextLine();
        }

        new Solution().groupAnagrams(strs);

        s.close();
    }

        static class Solution {

            public List<List<String>> groupAnagrams(String[] strs) {

                HashMap<Integer , List<String>> foundMap = new HashMap<>();

                for (String str : strs) {

                    char[] chars = str.toCharArray();

//                    Arrays.sort(chars);
//
//                    String key = String.valueOf(chars);

                    int key = 0;

                    for(char c: chars){

                        key+= (int)c*c*c*c;
                    }

                    if (!foundMap.containsKey(key)) {

                        List<String> temp = new ArrayList<>();

                        temp.add(str);

                        foundMap.put(key, temp);
                    } else {

                        foundMap.get(key).add(str);
                    }
                }

                return new ArrayList<>(foundMap.values());


            }

        }


//    static class Solution {
//
//        List<List<String>> res = new ArrayList<>();
//        String[] found;
//        public List<List<String>> groupAnagrams(String[] strs) {
//
//            found = strs;
//
//            char[][] chars = new char[strs.length][];
//            Node[] nodes = new Node[strs.length];
//
//            for(int i = 0; i < strs.length; i++) {
//
//                chars[i] = strs[i].toCharArray();
//                chars[i] = function(chars[i]);
//                nodes[i] = new Node(chars[i],i);
//            }
//
//            quickSort(nodes,0,nodes.length-1);
//
//            return res;
//
//        }
//
//        public char[] function(char[] a){
//
//            char[] res = new char[26];
//
//            for(int i = 0; i < a.length; i++){
//
//                res[a[i] - 'a']++;
//            }
//
//            return res;
//        }
//
//        public void quickSort(Node[] nodes, int start, int end){
//
//            if(start == end){
//
//                List<String>temp = new ArrayList<>();
//                temp.add(found[ nodes[start].index ]);
//                res.add(temp);
//                return;
//            }
//
//            if(start > end){
//
//                return;
//            }
//
//            int p1 = start-1;
//            int p2 = end+1;
//
//            char[] rand = nodes[(int) (Math.random() * (end - start) + start) ].chars;
//
//            for(int i = start; i < p2; i++){
//
//                char[] temp = bigger(nodes[i].chars,rand);
//                if(temp == rand){//更大的是rand
//                    //没有创建新的，所以地址应该是一样道德
//                    p1++;
//                    swap(nodes,p1,i);
//                }else if (temp == nodes[i].chars){
//
//                    p2--;
//                    swap(nodes,p2,i);
//                    i--;
//                }
//            }
//
//            //其中p1 ~ p2之间的范围就是相等的
//            boolean key = false;
//            List<String>temp = new ArrayList<>();
//            for(int i =p1+1; i < p2; i++){
//
//                if(!key){
//
//                    key = true;
//                }
//
//                temp.add(found[ nodes[i].index ]);
//            }
//
//            if(key){
//
//                res.add(temp);
//            }
//
//            quickSort(nodes,start,p1);
//            quickSort(nodes,p2,end);
//        }
//
//
//        public void swap(Node[] nodes, int i, int j){
//
//            Node temp = nodes[i];
//            nodes[i] = nodes[j];
//            nodes[j] = temp;
//        }
//
//        public char[] bigger(char[] a, char[] b){
//
//            if(a.length != b.length){
//
//                return a.length > b.length ? a : b;
//            }
//
//            for(int i = 0; i < a.length; i++){
//
//                if(a[i] != b[i]){
//
//                    return a[i] > b[i] ? a : b;
//                }
//            }
//
//            return null;//代表相等
//
//        }
//
//
//        class Node{
//
//            char[] chars;
//            int index;
//
//
//            Node(char[] chars, int index){
//
//                this.chars = chars;
//                this.index = index;
//            }
//        }
//
//    }

//    class Solution {
//        List<List<Integer>> res = new ArrayList<>();//储存相同的编号
//        HashMap<Node ,List<Integer>> foundMap = new HashMap<>();//储存已经找到的异位词
//        public List<List<String>> groupAnagrams(String[] strs) {
//
//            List<List<String>> result = new ArrayList<>();
//
//            char[][] chars = new char[strs.length][];
//
//            for(int i = 0; i < strs.length; i++) {
//
//                chars[i] = strs[i].toCharArray();
//                Arrays.sort(chars[i]);
//            }
//
//
//            Node root = new Node();
//
//            for(int i = 0; i < strs.length; i++){
//
//                Node nowNode = root;
//                char[] str = chars[i];
//                for(char c : str){//准备添加
//
//                    int index = c - 'a';
//                    if(nowNode.next[index] == null){
//
//                        nowNode.next[index] = new Node();
//                    }
//
//                    nowNode = nowNode.next[index];//向下移动
//                }
//
//                //直到最后
//                if(nowNode.end){
//
//                    //这个点已经作为结束点出现
//                    List<Integer> list = foundMap.get(nowNode);
//                    list.add(i);//储存这个点的index
//                }else{
//                    //这个点从未作为结束点出现
//                    nowNode.end = true;
//                    List<Integer> list = new ArrayList<>();
//                    list.add(i);//储存这个点的index
//                    res.add(list);
//                    foundMap.put(nowNode,list);
//                }
//
//            }
//
//
//
//            for(List<Integer> list : foundMap.values()){
//
//                List<String> temp = new ArrayList<>();
//                for(int i : list){
//
//                    temp.add(strs[i]);
//                }
//                result.add(temp);
//            }
//
//
//            return result;
//
//        }
//
//
//        public class Node{
//
//            Node[] next;
//            boolean end;//这个点是否是结束点
//
//            Node(){
//
//                next = new Node[26];
//                end = false;
//            }
//        }
//
//    }
}
