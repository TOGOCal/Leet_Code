import java.math.BigInteger;
import java.util.Arrays;
import java.util.Comparator;

public class p1366 {

    /**
     * 现在有一个特殊的排名系统，依据参赛团队在投票人心中的次序进行排名，每个投票者都需要按从高到低的顺序对参与排名的所有团队进行排位。
     *
     * 排名规则如下：
     *
     * 参赛团队的排名次序依照其所获「排位第一」的票的多少决定。如果存在多个团队并列的情况，将继续考虑其「排位第二」的票的数量。以此类推，直到不再存在并列的情况。
     * 如果在考虑完所有投票情况后仍然出现并列现象，则根据团队字母的字母顺序进行排名。
     * 给你一个字符串数组 votes 代表全体投票者给出的排位情况，请你根据上述排名规则对所有参赛团队进行排名。
     *
     * 请你返回能表示按排名系统 排序后 的所有团队排名的字符串。
     */

    class Solution {
        static class Node{

            char symbol;
            int[] score;
            BigInteger value;
            boolean isAppear;

            Node(char c){

                symbol = c;
                score = new int[26];
            }

            public void build(){

                StringBuilder sb = new StringBuilder();
                for(int i : score)
                    sb.append(i);

                value = new BigInteger(sb.toString());
            }
        }

        public static Node[] nodes;
        static{
            nodes = new Node[26];
            for(int i = 0 ;i < 26 ; i++){

                nodes[i] = new Node((char) ('A' + i));
            }
        }

        public String rankTeams(String[] votes) {

            Arrays.sort(nodes , repair);
            for(Node node :nodes)
                node.isAppear = false;

            for(Node n : nodes)//为什么1000：为了使得位数一致
                Arrays.fill(n.score , 1000);

            for(String s : votes){

                for(int i = 0 ; i < s.length() ;i++){

                    char value = s.charAt(i);
                    nodes[value - 'A'].isAppear = true;
                    nodes[value - 'A'].score[i]++;
                }
            }

            for(Node node : nodes)
                node.build();

            Arrays.sort(nodes , forSort);
            StringBuilder sb = new StringBuilder();
            for(Node node :nodes){
                if(node.isAppear)
                    sb.append(node.symbol);
            }

            return sb.toString();
        }

        public static ForSort forSort = new ForSort();
        public static Repair repair = new Repair();

        public static class ForSort implements Comparator<Node>{

            @Override
            public int compare(Node o1, Node o2) {

                int compare = o1.value.compareTo(o2.value);
                if(compare == 0)
                    return o1.symbol - o2.symbol;

                return -compare;
            }
        }


        public static class Repair implements Comparator<Node>{

            @Override
            public int compare(Node o1, Node o2) {
                return o1.symbol - o2.symbol;
            }
        }
    }
}
