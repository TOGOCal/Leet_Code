public class p402 {

    /**
     * 给你一个以字符串表示的非负整数 num 和一个整数 k ，
     * 移除这个数中的 k 位数字，使得剩下的数字最小。请你以字符串形式返回这个最小的数字。
     */


    public static void main(String[] args) {

        System.out.println(new Solution().removeKdigits("1432219", 3));
    }

    static
    class Solution {

        public static final int MAXN = 100001;
        public static final char[] stack = new char[MAXN];
        public static int size;

        //使用单调栈
        public String removeKdigits(String num, int k) {
            size = 0;

            int popNum = 0;//已经弹出的数量
            char[] string = num.toCharArray();

            StringBuilder sb = new StringBuilder();

            for(int i = 0 ;i < string.length ; i++){

                char thisChar = string[i];
                while(size != 0 && stack[size - 1] > thisChar){

                    popNum++;
                    size--;

                    //执行清算，因为不能再删了
                    if(popNum == k){

                        for(int s = 0 ; s < size ; s++)
                            sb.append(stack[s]);//将栈中剩余的拷贝下来

                        for(; i < string.length ; i++)
                            sb.append(string[i]);//将本来剩下的拷贝进来

                        while(!sb.isEmpty() && sb.charAt(0) == '0')
                            sb.delete(0 , 1);

                        if(sb.isEmpty())
                            return "0";
                        return sb.toString();
                    }

                }

                stack[size++] = thisChar;//放入
            }

            for(int i = 0 ; i < size - (k - popNum) ; i++)
                sb.append(stack[i]);

            while(!sb.isEmpty() && sb.charAt(0) == '0')
                sb.delete(0 , 1);

            if(sb.isEmpty())
                return "0";
            return sb.toString();
        }
    }    


    //超出时间限制
    static
    class Solution1 {

        //方法：将小的数字尽量提前
        //在k哥数字以内，一打赏你遇到0就删除前面所有的，否则删除最小的一个属前面的所有
        public String removeKdigits(String num, int k) {

            StringBuilder sb = new StringBuilder(recursion(num.toCharArray() , 0 , k));
            //去掉前导0
            while(!sb.isEmpty() && sb.charAt(0) == '0')
                sb.delete(0 , 1);

            if(sb.isEmpty())
                return "0" ;
            return sb.toString();
        }

        public String recursion(char[] string , int nowIndex , int k){

            if(string.length - nowIndex == k)
                return "";

            if(nowIndex == string.length)
                return "";

            StringBuilder sb = new StringBuilder();
            if(k == 0){

                for(int i = nowIndex ; i < string.length ; i++)
                    sb.append(string[i]);

                return sb.toString();
            }



            //去掉前导0
            while(nowIndex <string.length && string[nowIndex] == '0')
                sb.append(string[nowIndex++]);

            //如果因为删除0导致了超出范围
            if(nowIndex == string.length){

                sb.setLength(sb.length() - k);//减去k个数字
                return sb.toString();
            }

            //在有限的位置内，决定以那个位置作为开头
            char min = '9' + 1;
            int minIndex = -1;
            //删掉k个数，例如从0开始删除，可以删掉0，1，2，所以能够开头的字符有0 1 2 3，枚举所有能开头的字符
            int i = nowIndex;
            for( ; i < k + 1 + nowIndex && i < string.length; i++){

                char begin = string[i];
                if(begin == '0'){

                    //不用怀疑，直接将0之前的全部删完就行了
                    return sb.append('0').append(recursion(string , i + 1 , k - (i - nowIndex))).toString();
                }

                if(begin < min){
                    min = begin;
                    minIndex = i;
                }
            }

            //将最小的字符前面的删去
            return sb.append(min).append(recursion(string , minIndex + 1 , k - (minIndex - nowIndex))).toString();
        }
    }
}
