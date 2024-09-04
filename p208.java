public class p208 {

    /**
     * Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。
     * 这一数据结构有相当多的应用情景，例如自动补全和拼写检查。
     *
     * 请你实现 Trie 类：
     *
     * Trie() 初始化前缀树对象。
     * void insert(String word) 向前缀树中插入字符串 word 。
     * boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
     * boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。
     */

    class Trie {

        Node root;

        public Trie() {

            root = new Node();
        }

        public void insert(String word) {

            if(word == null || word.isEmpty()) return;

            Node nowNode = root;

            for(char c : word.toCharArray()){

                if(nowNode.next[c - 'a'] == null){

                    nowNode.next[c - 'a'] = new Node();
                }

                nowNode = nowNode.next[c - 'a'];
            }

            nowNode.isEnd = true;
        }

        public boolean search(String word) {

            Node nowNode = root;

            for(char c : word.toCharArray()){

                if(nowNode.next[c - 'a'] == null){

                    return false;
                }
                nowNode = nowNode.next[c - 'a'];
            }

            return nowNode.isEnd;
        }

        public boolean startsWith(String prefix) {

            Node nowNode = root;

            for(char c : prefix.toCharArray()){

                if(nowNode.next[c - 'a'] == null){

                    return false;
                }
                nowNode = nowNode.next[c - 'a'];
            }

            return true;
        }

        class Node{

            Node[] next;
            boolean isEnd;

            public Node(){

                next = new Node[26];
                isEnd = false;
            }
        }
    }

}
