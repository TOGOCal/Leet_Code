import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class p71 {

    /**
     * 始终以斜杠 '/' 开头。
     * 两个目录名之间必须只有一个斜杠 '/' 。
     * 最后一个目录名（如果存在）不能 以 '/' 结尾。
     * 此外，路径仅包含从根目录到目标文件或目录的路径上的目录（即，不含 '.' 或 '..'）。
     */

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);


        System.out.println(new Solution().simplifyPath(s.nextLine()));

        s.close();
    }

    static class Solution {
        public String simplifyPath(String path) {

            String[] dir = path.split("/");

            if( dir.length == 0) return "/";


            //StringBuilder sb = new StringBuilder();
            char[] res = new char[path.length()];

            int index = 0;

            Stack<Integer> lastLength = new Stack<>();

            for(int i = 0; i < dir.length; i++){

                String str = dir[i];
                if(str.equals("..")){

                    if(!lastLength.isEmpty()){

                        int length = lastLength.pop();
                        index-=length;
                    }


                }else if(!str.isEmpty() &&!str.equals(".")){

                    if(i!=dir.length-1 && dir[i+1].equals("..")){

                        i++;
                        continue;
                    }


//                    sb.append("/");
//                    sb.append(str);
                    res[index] = '/';
                    index ++;

                    char[] strC = str.toCharArray();

                    for( char c : strC){

                        res[index] = c;
                        index ++;
                    }

                    lastLength.push(str.length()+1);
                }
            }

            //sb.append("/");
            if(index == 0) return "/";



            return String.valueOf(Arrays.copyOf(res, index + 1));
        }
    }


//    static class Solution {
//        public String simplifyPath(String path) {
//
//            String[] dir = path.split("/");
//
//            if( dir.length == 0) return "/";
//
//
//            StringBuilder sb = new StringBuilder();
//            //sb.append("/");
//
//            Stack<Integer> lastLength = new Stack<>();
//
//            for(int i = 0; i < dir.length; i++){
//
//                String str = dir[i];
//                if(str.equals("..")){
//
//                    if(!lastLength.isEmpty()){
//
//                        int length = lastLength.pop();
//                        sb.delete(sb.length() - length, sb.length());
//                    }
//
//
//                }else if(!str.isEmpty() &&!str.equals(".")){
//
//                    if(i!=dir.length-1 && dir[i+1].equals("..")){
//
//                        i++;
//                        continue;
//                    }
//                    sb.append("/");
//                    sb.append(str);
//                    lastLength.push(str.length()+1);
//                }
//            }
//
//            //sb.append("/");
//            if(sb.isEmpty()) return "/";
//
//            return sb.toString();
//        }
//    }
}
