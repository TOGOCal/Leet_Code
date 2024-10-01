import java.util.HashMap;
import java.util.HashSet;

public class p299 {

    /**
     * 你在和朋友一起玩 猜数字（Bulls and Cows）游戏，该游戏规则如下：
     *
     * 写出一个秘密数字，并请朋友猜这个数字是多少。朋友每猜测一次，你就会给他一个包含下述信息的提示：
     *
     * 猜测数字中有多少位属于数字和确切位置都猜对了（称为 "Bulls"，公牛），
     * 有多少位属于数字猜对了但是位置不对（称为 "Cows"，奶牛）。也就是说，这次猜测中有多少位非公牛数字可以通过重新排列转换成公牛数字。
     * 给你一个秘密数字 secret 和朋友猜测的数字 guess ，请你返回对朋友这次猜测的提示。
     *
     * 提示的格式为 "xAyB" ，x 是公牛个数， y 是奶牛个数，A 表示公牛，B 表示奶牛。
     *
     * 请注意秘密数字和朋友猜测的数字都可能含有重复数字。
     */


    public static void main(String[] args) {

        String secret = "1807";
        String guess = "7810";


        Solution solution = new Solution();

        System.out.println(solution.getHint(secret, guess));
    }


    static
    class Solution {
        public String getHint(String secret, String guess) {

            //char[] nums = new char[26];

//            boolean[] haveThisNum = new boolean[10];
//            boolean[] haveCount = new boolean[10];


//            HashMap<Character , Integer> countNumMap = new HashMap<>();
//            HashMap<Character , Integer> correctLast = new HashMap<>();
//            HashMap<Character , Integer> guessLast = new HashMap<>();
            int[] correctLast = new int[10];
            int[] guessLast = new int[10];

            int length = secret.length();

            //用于记录所有正确的数字
            //boolean[] A = new boolean[length];


            char[] s = secret.toCharArray();
            char[] g = guess.toCharArray();

            int A = 0;
            int B = 0;

            for(int i = 0; i < Math.min(length , guess.length()); i++){

                char right = s[i];

                char guessChar = g[i];

                if(right == g[i]){

                    A ++;
                }else{

                    correctLast[right - '0'] ++;
                    guessLast[guessChar - '0'] ++;
                }

            }

            for(int i = 0; i < 10; i++){

                B += Math.min(correctLast[i] , guessLast[i]);
            }


            return A + "A" + B + 'B';

        }
    }
}
