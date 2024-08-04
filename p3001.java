import java.util.Scanner;

public class p3001 {

    /**
     * 现有一个下标从 1 开始的 8 x 8 棋盘，上面有 3 枚棋子。
     * 给你 6 个整数 a 、b 、c 、d 、e 和 f ，其中：
     * (a, b) 表示白色车的位置。
     * (c, d) 表示白色象的位置。
     * (e, f) 表示黑皇后的位置。
     * 假定你只能移动白色棋子，返回捕获黑皇后所需的最少移动次数。
     */

    /**
     * 情况假定：
     * 1.用象吃
     *  只有在皇后坐标和象坐标奇偶性相同的时候才能吃
     *  1.1直接对角线
     *  1.2走两步折线
     * 2.用车吃（车一定能在两步之内吃（因为象只能档一条路
     *
     * 所以这道题相当于判断能不能一部之内吃掉
     */


    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);


        int a = s.nextInt();

        int b = s.nextInt();

        int c = s.nextInt();

        int d = s.nextInt();

        int e = s.nextInt();

        int f = s.nextInt();

        int res = new Solution().minMovesToCaptureTheQueen(a, b, c, d, e, f);


        System.out.println(res);

        s.close();

    }



    static class Solution {                        //车             象           皇后
        public int minMovesToCaptureTheQueen(int a, int b, int c, int d, int e, int f) {

            //可能可以用象吃
            if((c+d) % 2 == (e+f) % 2){

                double k;

                if(f -d != 0){

                    k = (double)(e - c) / (f - d);//象和皇后之间的斜率
                }else{

                    k = 0;
                }

                if(Math.abs(k) == 1) {//直接对角线

                    //只需要判断车的坐标在不在这个线里面就行了

                    //斜率相同的情况下判断坐标是否在线段上而不是直线上
                    if(  (b == d? 0 : (double) (a - c) / ( b -d))  == k){

                        if( c < e){

                            if( a > c && a < e){

                                return 2;
                            }else {

                                return 1;
                            }

                        }else if( c > e){

                            if( a < c && a > e){

                                return 2;
                            }else {

                                return 1;
                            }

                        }


                    }else{

                        return 1;
                    }
                }

            }



            if( a == e){
                //检查障碍
                if(c!=e){

                    return 1;
                }else{


                    if( b < f){

                        if( d > b && d < f){

                            return 2;
                        }else{

                            return 1;
                        }

                    }else if( b > f){

                        if( d < b && d > f){

                            return 2;
                        }else{

                            return 1;
                        }

                    }
                }


            }


            if( b == f){


                if(d!=f){

                    return 1;
                }else {


                    if (a < e) {

                        if (c > a && c < e) {

                            return 2;
                        } else {

                            return 1;
                        }

                    } else if (a > e) {

                        if (c < a && c > e) {

                            return 2;
                        } else {

                            return 1;
                        }

                    }
                }
            }


            return 2;
        }
    }

}
