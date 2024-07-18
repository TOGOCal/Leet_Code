import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class p76 {

    /**
     * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
     */


    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        String sStr = s.nextLine();
        String tStr = s.nextLine();

        Solution solution = new Solution();

        System.out.println(solution.minWindow(sStr, tStr));


        s.close();
    }


    static class Solution {
        public String minWindow(String s, String t) {
            int left = 0, right = 0;
            int ansL = -1;
            int minLength = Integer.MAX_VALUE;
            int[] need = new int[128];
            for (char c : t.toCharArray()) {
                need[c]++;
            }
            int[] window = new int[128];
            int count = 0;
            while (right < s.length()) {
                char r = s.charAt(right);
                if (need[r] > 0) {//所需的字符进入区间
                    if(window[r] < need[r]) {//窗口一定增加,count值只有未覆盖才增加
                        count++;
                    }

                    window[r]++;
                }

                while (count == t.length() && left <= right) {//已经完成覆盖
                    if (right - left + 1 < minLength) {
                        minLength = right - left + 1;
                        ansL = left;
                    }

                    char l = s.charAt(left);
                    if (need[l] > 0) {
                        if(need[l] == window[l]) {//影响了覆盖的情况
                            count--;

                            //直接跳出寻呼按
                            break;
                        }
                        window[l]--;//无论如何必须从窗口中去除
                    }
                    left++;
                }

                //right无论如何向前移动
                right++;
            }

            return ansL == -1 ? "" : s.substring(ansL, ansL + minLength);
        }
    }



//    static class Solution {
//
//        HashMap<Character , Integer> foundCharNumMap = new HashMap<>();
//
//        public String minWindow(String s, String t) {
//
//            if(s == null || t == null || s.isEmpty() || t.isEmpty() || t.length() > s.length()){
//
//                return "";
//            }
//
//            char[] sCrr = s.toCharArray();
//
//            char[] tCrr = t.toCharArray();
//
//
//            StringBuilder sb = new StringBuilder();
//
//            if(tCrr.length == 1){
//
//                for(int i = 0 ; i< sCrr.length;i++){
//
//                    //包含了这一个元素
//                    if(sCrr[i] == tCrr[0]){
//
//                        return sb.append(tCrr[0]).toString();
//                    }
//                }
//            }
//
//
//
//            for(char c : tCrr){
//
//                foundCharNumMap.put(c, foundCharNumMap.getOrDefault(c, 0) + 1);
//            }
//
//            int p1 = 0;
//            int p2 = 0;
//
//
//            int minLength = Integer.MAX_VALUE;
//            int firstPlace = 0;
//            int lastPlace  = 0;
//
//            //双指针，滑动窗口
//            int length = tCrr.length;
//            for(;p2 < sCrr.length;p2++){
//
//                if(foundCharNumMap.containsKey(sCrr[p2])){
//
//                    if(foundCharNumMap.get(sCrr[p2]) > 0){
//
//                        length--;
//                    }
//
//                    //不管怎样都会--
//                    foundCharNumMap.put(sCrr[p2], foundCharNumMap.get(sCrr[p2]) - 1);
//
//                    //满足了一个条件，p1向前移动，看还有没有更好
//                    if(length == 0){
//
//                        int p1Record =p1;
//
//                        for(;p1 < p2;p1++){
//
//
//                            if(foundCharNumMap.containsKey(sCrr[p1])){
//
//                                int time = foundCharNumMap.get(sCrr[p1]);
//                                if(time >= 0){
//                                    //说明p1到p2之间不能这个元素
//                                    break;//p1不能再向前找了
//                                }
//
//                                foundCharNumMap.put(sCrr[p1], foundCharNumMap.get(sCrr[p1]) + 1);
//                            }
//                        }
//
//                        //目前最小距离
//                        if(p1 != p2){
//                            if(p2 - p1 < minLength){
//
//                                firstPlace = p1;
//                                lastPlace = p2;
//                                minLength = p2 - p1;
//                            }
//                        }else{
//
//                            p1 = p1Record;
//                        }
//
//
//                    }
//
//
//                }
//
//
//
//            }//出循环后再进行p1前移操作
//
//            if(length == 0){
//
//                for(;p1 < p2;p1++){
//
//
//                    if(foundCharNumMap.containsKey(sCrr[p1])){
//
//                        int time = foundCharNumMap.get(sCrr[p1]);
//                        if(time >= 0){
//                            //说明p1到p2之间不能这个元素
//                            break;//p1不能再向前找了
//                        }
//                    }
//                }
//
//                //目前最小距离
//                if(p1 != p2){
//                    if(p2 - p1 < minLength){
//
//                        firstPlace = p1;
//                        lastPlace = p2;
//                        minLength = p2 - p1;
//                    }
//                }
//            }
//
//
//
//            if(minLength == Integer.MAX_VALUE){
//
//                return "";
//            }
//
//
//            return s.substring(firstPlace, lastPlace + 1);
//        }
//
//    }




//    static class Solution {
//        public String minWindow(String s, String t) {
//
//            if(s == null || t == null || s.isEmpty() || t.isEmpty() || t.length() > s.length()){
//
//                return "";
//            }
//
//
//
//            char[] aimString =s.toCharArray();
//
//            char[] crr = t.toCharArray();
//
//            StringBuilder sb = new StringBuilder();
//
//            if(crr.length == 1){
//
//                for(int i = 0 ; i< aimString.length;i++){
//
//                    //包含了这一个元素
//                    if(aimString[i] == crr[0]){
//
//                        return sb.append(aimString[i]).toString();
//                    }
//                }
//            }
//
//
//
//            //大写在前，小写在后面
//            //由于题目保证字符串全由英文字母组成，判断条件就变得简单了
//            int[] charNum = new int[26*2];
//
//            //每一位先置位为-1
//            Arrays.fill(charNum, -1);
//
//
//            for(int i = 0;i<crr.length;i++){
//                if(crr[i]>='a'){
//                    //是小写字母
//                    if(charNum[crr[i]-'a' + 26] == -1){
//
//                        charNum[crr[i]-'a' + 26] = 1;
//                    }else{
//
//                        charNum[crr[i]-'a' + 26]++;
//                    }
//                }else{
//                    //是大写字母
//
//                    if(charNum[crr[i]-'A'] == -1){
//
//                        charNum[crr[i]-'A'] ++;
//                    }
//
//                    charNum[crr[i]-'A']++;
//                }
//            }
//
//            int[] recordCharNum = charNum.clone();
//
//
//            int length = t.length();//所有的元素
//            MyQueue queue = new MyQueue();
//
//            int i = 0;
//            for(;i<aimString.length;i++){
//
//                char c= aimString[i];
//
//                if(c>='a'){
//                    if(charNum[c-'a' + 26] != -1){
//                        //是小写字母
//                        if(charNum[c-'a' + 26] > 0){
//                            charNum[c-'a' + 26]--;
//                            length -- ;
//                        }
//                        queue.addNew(new Node(c,i));
//                    }
//
//                }else{
//
//                    if(charNum[c-'A'] != -1){
//
//                        if(charNum[c-'A'] > 0){
//                            charNum[c-'A']--;
//                            length -- ;
//                        }
//                        queue.addNew(new Node(c,i));
//                    }
//
//                }
//
//                if(length == 0){
//                    //经过这一步操作，匹配到了第一种情况
//                    i++;//这个元素已经遍历过了，且加入了集合，之后没必要遍历这个元素了
//                    break;
//                }
//
//            }
//
//            //出循环，要么找齐了所有元素
//            //要么遍历完整个循环还没找齐
//
//            if(length!=0){
//
//                return "";
//            }
//
//            //找到第一个人例子后使用滑动窗口的思想进行构建
//
//            Node firstNode = queue.outFirst();//弹出一个元素
//            char aimChar = firstNode.value;//之后匹配的目标字符
//            int firstPLace =firstNode.index;
//            int p1 = firstPLace;
//
//            int lastPlace = queue.getLast().index;
//            int p2 = lastPlace;
//
//            int minLength = lastPlace - firstPLace;
//
//
//            for(;i< t.length() ;i++){
//
//                //是小写字母
//                if(crr[i] >= 'a'){
//
//                    //只要是有效字符都加入进来
//                    queue.addNew(new Node(crr[i],i));
//
//                }else{//是大写字母
//
//                    queue.addNew(new Node(crr[i],i));
//                }
//
//                //系啊一个满足包含所有的情况
//                if(crr[i] == aimChar){
//
//                    firstNode = queue.outFirst();
//
//                    //指针指向洗一个地方了
//                    p1 = firstNode.index;
//
//                    //末尾指针指向的地方
//                    p2 = i;
//
//
//                    if(p2 - p1 < minLength){
//
//                        firstPLace = p1;
//                        lastPlace = p2;
//                    }
//
//                }
//            }
//
//            //出循环表示遍历完了所有可能的情况了
//            //下面单独处理queue中可能出现的更小情况
//            //丢掉多余的可以丢掉的情况
//
//
//
//        }
//
//
//        public class MyQueue{
//
//            Node head;
//            Node tail;
//
//            //初始化的时候使用new一个无效节点的方式来创建
//            public MyQueue(){
//
//                head = new Node((char) 0, 0);
//                tail = head;
//            }
//
//
//            public boolean isEmpty(){
//
//                return head == tail;
//            }
//
//
//
//            public void addNew(Node node){
//
//                tail.next = node;
//                tail = node;
//            }
//
//            public Node outFirst(){
//
//                //头部是无效节点
//                if(head == tail){
//
//                    return null;
//                }
//
//                Node node = head.next;
//                head = head.next;
//
//                return node;
//            }
//
//
//            public Node getLast(){
//
//                return tail;
//            }
//
//        }
//
//
//        public class Node{
//
//            char value;
//            int index;
//            Node next;
//
//            public Node(char value,int index){
//
//                this.value = value;
//                this.index = index;
//            }
//        }
//
//    }
}
