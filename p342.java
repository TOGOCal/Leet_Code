import java.util.HashMap;
import java.util.HashSet;

public class p342 {

    /**
     * 给定一个整数，写一个函数来判断它是否是 4 的幂次方。如果是，返回 true ；否则，返回 false 。
     *
     * 整数 n 是 4 的幂次方需满足：存在整数 x 使得 n == 4x
     */

    public static void main(String[] args) {

        System.out.println(new Solution().canMeasureWater(1, 2, 3));
    }

    static
    class Solution {

        public static int HASH = 100001;

        int x , y;
        int target;

        HashSet<Integer> visit;
        public boolean canMeasureWater(int x, int y, int target) {

            this.x = x;
            this.y = y;
            this.target = target;
            visit = new HashSet<>();

            return dfs(0 , 0);
        }

        public boolean dfs(int nowX , int nowY){

            if(nowX + nowY == target){

                return true;
            }

            visit.add(getHASH(nowX , nowY));

            int _x = nowX;
            int _y = nowY;

            int canPut;
            //把 X 壶的水灌进 Y 壶，直至灌满或倒空；
            canPut = Math.min(_x , y - nowY);
            _x -= canPut;
            _y += canPut;

            if(_x == target || _y == target){

                return true;
            }

            if(!visit.contains(getHASH(_x , _y)))
                if(dfs(_x , _y))
                    return true;


            _x = nowX;
            _y = nowY;
            //把 Y 壶的水灌进 X 壶，直至灌满或倒空；
            canPut = Math.min(_y , x - nowX);
            _x += canPut;
            _y -= canPut;

            if(_x == target || _y == target){

                return true;
            }

            if(!visit.contains(getHASH(_x , _y)))
                if(dfs(_x , _y))
                    return true;

            //把 X 壶灌满；
            if(!visit.contains(getHASH(x , nowY)))
                if(dfs(x , nowY))
                    return true;

            if(!visit.contains(getHASH(nowX , y)))
                if(dfs(nowX , y))
                    return true;


            if(!visit.contains(getHASH(0 , nowY)))
                if(dfs(0 , nowY))
                    return true;

            if(!visit.contains(getHASH(nowX , 0)))
                if(dfs(nowX , 0))
                    return true;


            return false;
        }

        public int getHASH(int x  ,int y){

            return x * HASH + y;
        }

        /**
         * 把 X 壶的水灌进 Y 壶，直至灌满或倒空；
         * 把 Y 壶的水灌进 X 壶，直至灌满或倒空；
         * 把 X 壶灌满；
         * 把 Y 壶灌满；
         * 把 X 壶倒空；
         * 把 Y 壶倒空。
         */


    }


    /**
     * ax+by=z
     * 找到ab为整数一对即可
     * 有且仅当z 为a ， b 最大公约数的倍数
     */
    class Solution2 {
        public boolean canMeasureWater(int x, int y, int z) {
            if (x + y < z) {
                return false;
            }
            if (x == 0 || y == 0) {
                return z == 0 || x + y == z;
            }
            return z % steinGcd(x, y) == 0;
        }

        public static int steinGcd(int a , int b){

            if(a == b)
                return a;
            if(a == 0)
                return b;
            if(b == 0)
                return a;

            boolean aOdd = (a & 1) == 1;//a是否是奇数
            boolean bOdd = (b & 1) == 1;

            //如果a和b都是偶数
            if(!aOdd && !bOdd)
                return steinGcd(a >> 1, b >> 1) << 1;
            if(aOdd && bOdd)
                return steinGcd(Math.abs(a - b) >> 1, Math.min(a,b));
            if(aOdd)
                return steinGcd(a , b >> 1);

            return steinGcd(a >> 1 , b);
        }
    }

}
