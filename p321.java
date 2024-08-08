public class p321 {

    class Solution {

        //public int[] (int[] nums1, int[] nums2, int k) {
        public int[] maxNumber(int[] nums1, int[] nums2, int k) {

            int len1 = nums1.length;
            int len2 = nums2.length;

            if (k < 0 || k > len1 + len2) {
                return null;
            }


            int[][] dpHelpArr1 = createHelpArr(nums1);
            int[][] dpHelpArr2 = createHelpArr(nums2);//得到辅助数组
            int[] res = new int[k];

            for (int i = Math.max(0, k - len2); i <= Math.min(k, len1); i++) {

                int[] arr1 = getArray(dpHelpArr1, nums1, i);
                int[] arr2 = getArray(dpHelpArr2, nums2, k - i);
                int[] mergeRes = merge(arr1, arr2);

                res = bigger(res, mergeRes) ? res : mergeRes;//比较大小

            }

            return res;
        }

        private boolean bigger(int[] pre, int[] last) {

            int i = 0;
            int j = 0;
            while (i < pre.length && j < last.length && pre[i] == last[j]) {
                i++;
                j++;
            }
            return j == last.length || (i < pre.length && pre[i] > last[j]);

        }

        //合并两个数组，返回合并后的数组
        public int[] merge(int[] arr1, int[] arr2) {

            int size1 = arr1.length;
            int size2 = arr2.length;

            int[] res = new int[size1+ size2];

            int[] help = new int[size1 + size2 + 1];

            int index = 0;

            for (int k : arr1) {

                help[index] = k + 2;
                index++;
            }

            help[index] = 1;
            index ++;

            for (int k : arr2) {

                help[index] = k + 2;
                index++;
            }

            DC3 dc3 = new DC3(help,11);


            int[] rank = dc3.indexRank;

            int i = 0;
            int j = 0;
            int r = 0;
            while (i < size1 && j < size2) {
                res[r++] = rank[i] > rank[j + size1 + 1] ? arr1[i++] : arr2[j++];
            }
            while (i < size1) {
                res[r++] = arr1[i++];
            }
            while (j < size2) {
                res[r++] = arr2[j++];
            }
            return res;


        }


        public int[] getArray(int[][] dp, int[] arr, int num) {
            //进这个方法保证num的取值是有效的
//            int[] res = new int[num];
//            int index = 0;
//
//            for (int i = 0; i < num; i++) {
//
//                index = dp[index][num - i];
//                res[i] = arr[index];
//                index += 1;
//
//            }

            int[] res = new int[num];
            for (int resIndex = 0, dpRow = 0; num > 0; num--, resIndex++) {
                res[resIndex] = arr[dp[dpRow][num]];
                dpRow = dp[dpRow][num] + 1;
            }
            return res;

        }

        private int[][] createHelpArr(int[] arr) {

            int num = arr.length;

            int[][] dp = new int[arr.length][num + 1];

            //先完成基本请款填写
            //首先明确无效情况：当num > arr.length时，直接返回这个位置其实就是无效的，所以dp[i][j] 当 arr.length - i < j 时，这个位置就是无效的
            //当选取的个数是0的时候，这个也是无效的，，所以dp[][0]这一列也不用填

            for (int i = 0; i < arr.length; i++) {

                dp[i][0] = -1;
            }

            for (int n = 1; n <= num; n++) {

                dp[arr.length - n][n] = arr.length - n;
                for (int i = arr.length - n - 1; i >= 0; i--) {

                    int preIndex = dp[i + 1][n];

                    if (arr[i] >= arr[preIndex]) {

                        dp[i][n] = i;
                    } else {

                        dp[i][n] = preIndex;
                    }

                }
            }

            return dp;
        }


        public class DC3 {

            public int[] rankIndex;

            public int[] indexRank;

            public int[] height;


            //我自己添加的
            //能够传递字符串的
            public DC3(String str) {

                int max = 0;
                int[] nums = new int[str.length()];
                for (int i = 0; i < str.length(); i++) {

                    nums[i] = str.charAt(i) + 1;//为什么加一：为了让没有位置的字符填充/0，不能让原串中出现/0
                    max = Math.max(max, nums[i]);
                }

                new DC3(nums, max);
            }

            public DC3(int[] nums) {

                int max = 0;
                for (int num : nums) {

                    max = Math.max(max, num);
                }

                new DC3(nums, max);
            }

            // 构造方法的约定:
            // 数组叫nums，如果你是字符串，请转成整型数组nums
            // 数组中，最小值>=1
            // 如果不满足，处理成满足的，也不会影响使用
            // max, nums里面最大值是多少
            public DC3(int[] nums, int max) {
                rankIndex = sa(nums, max);
                indexRank = rank();
                height = height(nums);
            }

            //用于生成sa，经过预处理，将保证不会超量程访问，如果需要访问某个元素后两位，就不会超量程访问了
            //因为将量程加了3
            //然后真正用于生成的是skew方法
            private int[] sa(int[] nums, int max) {

                int n = nums.length;
                int[] arr = new int[n + 3];

                for (int i = 0; i < n; i++) {
                    arr[i] = nums[i];
                }

                return skew(arr, n, max);
            }

            //真正由于生成sa的（用于生成真正的rankIndex排名与位置的对应
            private int[] skew(int[] nums, int strLength, int maxCharValue) {
                //得到属于s几的数量，分别对应 n0 -> s0   n1 - > s1    n2 -> s2
                int n0 = (strLength + 2) / 3, n1 = (strLength + 1) / 3, n2 = strLength / 3, n02 = n0 + n2;

                //将s1和s2的所有index放入s12这个数组中
                int[] s12 = new int[n02 + 3], sa12RankIndex = new int[n02 + 3];

                for (int i = 0, p = 0; i < strLength + (n0 - n1); i++) {

                    if (0 != i % 3) {

                        s12[p++] = i;
                    }
                }

                //三次铜牌，最终结果放在了sa12中（s12和sa12来回倒
                radixPass(nums, s12, sa12RankIndex, 2, n02, maxCharValue);
                radixPass(nums, sa12RankIndex, s12, 1, n02, maxCharValue);
                radixPass(nums, s12, sa12RankIndex, 0, n02, maxCharValue);

                /**
                 * c0 , c1 , c2 相当于是用于记录后三位的比较结果的
                 * 后三位 0 +1 +2
                 */

                int name = 0, c0 = -1, c1 = -1, c2 = -1;
                for (int i = 0; i < n02; ++i) {
                    //只要有一位不相同
                    if (c0 != nums[sa12RankIndex[i]] || c1 != nums[sa12RankIndex[i] + 1] || c2 != nums[sa12RankIndex[i] + 2]) {
                        name++;

                        //记录后三位
                        c0 = nums[sa12RankIndex[i]];
                        c1 = nums[sa12RankIndex[i] + 1];
                        c2 = nums[sa12RankIndex[i] + 2];
                    }

                    //完成rankIndex的反向填充 为什么除3：s12位置准备之恩呢放s1和s2的，所以要限定位置
                    //顺便将s1的放在前面，s2的放在后面，方便进递归的时候后续下操作
                    //此后IndexRank不再是位置-排名的对应，只是一个存储容器
                    if (1 == sa12RankIndex[i] % 3) {

                        s12[sa12RankIndex[i] / 3] = name;
                    } else {//不是1就是2

                        s12[sa12RankIndex[i] / 3 + n0] = name;
                    }
                }

                //存在重复的
                if (name < n02) {

                    //调递归，将s1s2分别放在一起的排名作为数组进入递归
                    //由于都是排名，所以这个数组中存在的最大值就是name了
                    //得到了s1s2防砸一起的真正排名
                    sa12RankIndex = skew(s12, n02, name);

                    for (int i = 0; i < n02; i++) {

                        s12[sa12RankIndex[i]] = i + 1;
                    }

                } else {//没有重复的，已经严格排序了（indexRank是准确的
                    for (int i = 0; i < n02; i++) {

                        //反向得到rankIndex
                        sa12RankIndex[s12[i] - 1] = i;
                    }
                }

                /**
                 * 经过上述步骤后，真正有效的其实是s12IndexRank
                 * 因为RankIndex经过嘀咕得到的其实不是在这个意义下的rankIndex
                 * 本来的步骤是要更具indexRank生成rankIndex
                 * 再由rankIndex得到s0的第一步排序
                 */

                //根据s12生成s0的排序
                int[] s0IndexRank = new int[n0], sa0RankIndex = new int[n0];

                //第一次桶排
                for (int i = 0, j = 0; i < n02; i++) {

                    if (sa12RankIndex[i] < n0) {

                        //这里的IndexRank其实暂时充当的是rankIndex的作用
                        //因为下一次桶排会将其倒进RankIndex中
                        //为什么*3=》 在之前的s1放一起，s2放一起
                        s0IndexRank[j++] = 3 * sa12RankIndex[i];
                    }
                }

                //第二次桶排将数据成功导入rankIndex中
                radixPass(nums, s0IndexRank, sa0RankIndex, 0, n0, maxCharValue);


                int[] sa = new int[strLength];

                //k是用来搭配sa使用的
                for (int soPoint = 0, s12Point = n0 - n1, k = 0; k < strLength; k++) {
                    int i = sa12RankIndex[s12Point] < n0 ? sa12RankIndex[s12Point] * 3 + 1 : (sa12RankIndex[s12Point] - n0) * 3 + 2;
                    int j = sa0RankIndex[soPoint];
                    //这个比较是s0和s1的比较，所以只要比较s1和s2就一定能出结果
                    if (sa12RankIndex[s12Point] < n0 ? leq(nums[i], s12[sa12RankIndex[s12Point] + n0], nums[j], s12[j / 3])

                            //这个的比较是s0和s2的比较，可能需要比两次才能出结果
                            : leq(nums[i], nums[i + 1], s12[sa12RankIndex[s12Point] - n0 + 1], nums[j], nums[j + 1], s12[j / 3 + n0])) {
                        sa[k] = i;
                        s12Point++;

                        //已经遍历完了某个集合，只需要填充接下来的集合就可以了
                        if (s12Point == n02) {
                            for (k++; soPoint < n0; soPoint++, k++) {
                                sa[k] = sa0RankIndex[soPoint];
                            }
                        }
                    } else {
                        sa[k] = j;
                        soPoint++;

                        //已经遍历完了某个集合，之需要遍历接下来的集合就可以了
                        if (soPoint == n0) {
                            for (k++; s12Point < n02; s12Point++, k++) {
                                sa[k] = sa12RankIndex[s12Point] < n0 ? sa12RankIndex[s12Point] * 3 + 1 : (sa12RankIndex[s12Point] - n0) * 3 + 2;
                            }
                        }
                    }
                }
                return sa;
            }


            /**
             * @param nums   原字符串 加工的数组
             * @param input  需要进行排序的数组
             * @param output 经过本次排序的得到的数组
             * @param offset 这是第几遍排序，分别是 2 1 0 ， 就是此时桶排序需要考虑的字符位置是index + offset
             * @param n      长度限制
             * @param k      所有字符+1后的最大值，由于限定桶的大小
             */
            private void radixPass(int[] nums, int[] input, int[] output, int offset, int n, int k) {
                //所有字符+1后是k，所以能访问到k这个位置，需要将大小+1来访问到k
                int[] cnt = new int[k + 1];

                for (int i = 0; i < n; ++i) {

                    //int checkIndex = input[i] + offset; //正在检查的位置
                    //char thisChr = nums[checkIndex];
                    cnt[nums[input[i] + offset]]++;//其中的数量++
                }

                for (int i = 0, indexInResultArray = 0; i < cnt.length; ++i) {
                    int t = cnt[i];//得到字符 i 有几个数字
                    cnt[i] = indexInResultArray;//这个字符应该在结果数组中的哪个index
                    indexInResultArray += t;
                }

                /**
                 * 这个桶排为什么是这么写的：
                 * 正常写法是
                 * 遍历数组，如果数组中不是i的，就说明这个char对应的数量有这么多个
                 * 然后逐渐填充到结果容器中
                 *
                 * 但是经过这两个for循环，可以做到加速的效果
                 * 经过上面的循环可以确定每个元素对应到结果容器中的位置在哪
                 * 为什么下面的循环中index要++：
                 * 因为可能存在一个字符对应多个的情况出现
                 * 所以相同的字符下一次田中的位置是index+1的位置
                 */


                for (int i = 0; i < n; i++) {
//            int checkIndex = input[i] + offset;
//            int thisChar = nums[checkIndex];
//            int index = cnt[thisChar];
                    output[cnt[nums[input[i] + offset]]++] = input[i];
                }
            }


            private boolean leq(int a1, int a2, int b1, int b2) {
                return a1 < b1 || (a1 == b1 && a2 <= b2);
            }

            private boolean leq(int a1, int a2, int a3, int b1, int b2, int b3) {
                return a1 < b1 || (a1 == b1 && leq(a2, a3, b2, b3));
            }

            private int[] rank() {
                int n = rankIndex.length;
                int[] ans = new int[n];
                for (int i = 0; i < n; i++) {
                    ans[rankIndex[i]] = i;
                }
                return ans;
            }


            /**
             * @param s 原始字符串处理后的int数组
             * @return 这个结构中的height
             */
            private int[] height(int[] s) {
                int n = s.length;//得到字符串长度
                int[] ans = new int[n];

                //rank是排名数组，index-rank
                for (int i = 0, k = 0; i < n; i++) {
                    if (indexRank[i] != 0) {
                        if (k > 0) {
                            --k;
                        }
                        int j = rankIndex[indexRank[i] - 1];
                        while (i + k < n && j + k < n && s[i + k] == s[j + k]) {
                            ++k;
                        }
                        ans[indexRank[i]] = k;
                    }
                }
                return ans;
            }
        }


    }

}
