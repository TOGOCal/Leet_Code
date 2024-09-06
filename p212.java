import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class p212 {

    /**
     * 给定一个 m x n 二维字符网格 board 和一个单词（字符串）列表 words， 返回所有二维网格上的单词 。
     * <p>
     * 单词必须按照字母顺序，通过 相邻的单元格 内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
     * 同一个单元格内的字母在一个单词中不允许被重复使用。
     */


    public static void main(String[] args) {

        char[][] board = {{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}};

        String[] words = {"oath","pea","eat","rain"};

        Solution solution = new Solution();
        List<String> res = solution.findWords(board, words);
    }




    static
    class Solution {

        Trie trie;
        int unFinished;
        String[] words;
        List<String> res;

        public List<String> findWords(char[][] board, String[] words) {

            trie = new Trie(words);
            unFinished = words.length;
            this.words = words;

            res = new ArrayList<>();


            for(int i = 0 ; i < board.length ; i++){

                for(int j = 0 ; j < board[0].length ; j++){

                    dfs(i , j , board , trie.root);
                    if(unFinished == 0){

                        return res;
                    }
                }
            }

            return res;
        }

        public void dfs(int i , int j , char[][] board , Node node){


            if(i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] == 0){

                return;
            }

            char c = board[i][j];

            if(node.next[c-'a'] == null || node.next[c-'a'].passTime == 0){

                return;
            }

            node = node.next[c-'a'];

            if(node.endIndex != -1){
                res.add(words[node.endIndex]);
                trie.delete(words[node.endIndex]);
                unFinished --;
            }




            if(unFinished == 0){

                return;
            }


            board[i][j] = 0;
            dfs(i+1 , j , board , node);
            dfs(i-1 , j , board , node);
            dfs(i , j+1 , board , node);
            dfs(i , j-1 , board , node);

            board[i][j] = c;
        }

        class Trie{

            Node root;

            public Trie(String[] strings){

                root = new Node();

                for(int i = 0 ; i < strings.length ; i++){

                    insert(strings[i] , i);
                }
            }

            public void delete(String s){

                Node node = root;
                for(char c : s.toCharArray()){

                    if(node.next[c-'a'] == null){

                        return;
                    }
                    node = node.next[c-'a'];
                    node.passTime--;
                }
                node.endIndex = -1;
            }


            private void insert(String s , int index){

                Node node = root;
                for(char c : s.toCharArray()){

                    if(node.next[c-'a'] == null){

                        node.next[c-'a'] = new Node();
                    }
                    node = node.next[c-'a'];
                    node.passTime++;
                }
                node.endIndex = index;
            }

        }

        class Node{

            Node[] next;
            int passTime;
            int endIndex;

            public Node(){

                next = new Node[26];
                passTime = 0;
                endIndex = -1;
            }
        }

    }
}
