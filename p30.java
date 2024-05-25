import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class p30 {
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        int n = s.nextInt();
        s.nextLine();

        String[] words = new String[n];

        for(int i = 0 ; i<n ;i++){

            words[i] = s.nextLine();
        }

        Solution solution = new Solution();

        List<Integer> l =  solution.findSubstring(s.nextLine() , words);

        for(Integer i : l){

            System.out.print(i + " ");
        }

        s.close();

    }

    /**
     * 使用知识点：
     * 枚举出words所用的子串（全排列）（每个元素都可以来到某个位置
     * KMP判断字符串是否是子串
     * （KMP的时候先试试是否是只用返回一个索引位置，出错了再加所有索引位置
     * 坏了，KMP都时间超了，I give up
     */

    static class Solution {

        boolean[] check;//用于代替哈希表进行重复检查

        public List<Integer> findSubstring(String s, String[] words) {

            if(s.length() < words.length * words[0].length()){

                return new ArrayList<>();
            }

            check = new boolean[s.length()];

            List<Integer> list = new ArrayList<>();

            createString(s , 0 , words , list);

            return list;
        }

        public void createString(String str,int index,String[] words , List<Integer> list){
            //index为此时在判断哪个位置

            if (index == words.length){

                //执行判断操作并加入索引位置
                StringBuilder sb = new StringBuilder();
                for(int i = 0 ; i<words.length ;i++){

                    sb.append(words[i]);
                }

                List<Integer> res = KMP(str , sb.toString());
                //保证不会返回空

                //将索引位置加入
                for(int i : res){

                    if(!check[i]){

                        list.add(i);

                        check[i] = true;
                    }
                }

                return;
            }

            for(int  i = index ; i<words.length;i++ ){

                swap(words , index ,i);//任何一个位置的word都可以占据本来这个位置的元素

                createString(str,index + 1 , words , list);

                swap(words , index  ,i );//换回来
            }

        }

        public void swap(String[] words , int i , int j){

            String temp = words[i];

            words[i] = words[j];

            words[j] = temp;
        }

        public List<Integer> KMP(String s , String word){

            List<Integer> list = new ArrayList<>();

            int[] help = KMPHelp(word);

            char[] s1 = s.toCharArray();

            char[] s2 = word.toCharArray();

            int p1 = 0, p2 = 0;//分别指向两个字符串位置的指针

            while(p1 < s1.length){



                if(s1[p1] == s2[p2]){

                    p1++;
                    p2++;
                }else if(help[p2] == -1){
                    //这个分支暗含条件：s1[p1] != s2[p2] 且 p2==0
                    p1++;
                }else{
                    p2 = help[p2];
                    //此时的比对位置p1并不是像我的方法一样一直指向str1匹配时的开头，而是在正在进行匹配的地方，已经相当于我的i+j位置了
                }

                if(p2 == s2.length){

                    list.add(p1 - p2);
                    p1 = p1 -p2;
                    p1++;

//                    if(p1 == s1.length){
//
//                        break;
//                    }

                    p2 = 0;
                }
            }


            return list;

        }

        public int[] KMPHelp(String str){
            //生成相同最长前串的数组
            if(str.length() == 1){

                return new int[]{-1};
            }

            char[] chars = str.toCharArray();

            int[] result = new int[str.length()];

            result[0] = -1;
            result[1] = 0;
            int index = 2;
            int cn =0;

            while(index < chars.length){

                if(chars[cn] == chars[index-1]){

                    result[index++] = ++cn;
                }else if(cn > 0){

                    cn = result[cn];
                }else {

                    result[index++] = 0;
                }
            }

            return result;
        }
    }






}

