import java.util.HashMap;

public class p205 {

    /**
     * 给定两个字符串 s 和 t ，判断它们是否是同构的。
     *
     * 如果 s 中的字符可以按某种映射关系替换得到 t ，那么这两个字符串是同构的。
     *
     * 每个出现的字符都应当映射到另一个字符，同时不改变字符的顺序。
     * 不同字符不能映射到同一个字符上，相同字符只能映射到同一个字符上，字符可以映射到自己本身。
     */


    public static void main(String[] args) {

        String s = "foo";
        String t = "bar";

        System.out.println(new Solution().isIsomorphic(s,t));
    }


    static
    class Solution {
        public boolean isIsomorphic(String s, String t) {

            HashMap<Character , Character> map1 = new HashMap<>();
            HashMap<Character , Character> map2 = new HashMap<>();

            char[] strS = s.toCharArray();

            char count1 = 0;
            for( int i = 0 ; i < strS.length ; i++){

                if(map1.containsKey(strS[i])){

                    strS[i] = map1.get(strS[i]);
                }else{



                    map1.put(strS[i],count1);
                    strS[i] = count1;

                    count1++;
                }
            }

            char[] strT = t.toCharArray();
            char count2 = 0;
            for( int i = 0 ; i < strT.length ; i++){

                if(map2.containsKey(strT[i])){

                    strT[i] = map2.get(strT[i]);
                }else{

                    map2.put(strT[i],count2);
                    strT[i] = count2;

                    count2++;
                }
            }

            for( int i = 0 ; i < strS.length ; i++){

                if(strS[i] != strT[i])
                    return false;
            }


            return true;
        }
    }
}
