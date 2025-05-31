package from_1000_to_1100;

import java.util.ArrayList;
import java.util.List;

public class p1078 {

    /**
     * 给出第一个词 first 和第二个词 second，考虑在某些文本 text 中可能以 "first second third" 形式出现的情况，其中 second 紧随 first 出现，third 紧随 second 出现。
     *
     * 对于每种这样的情况，将第三个词 "third" 添加到答案中，并返回答案。
     */

    class Solution {
        public String[] findOcurrences(String text, String first, String second) {


            String[] strings = text.split(" ");
            List<String> res = new ArrayList<>();
            int l = strings.length;
            for(int i = 0 ; i < l ; i ++){
                if(strings[i].equals(first) &&
                i + 1 < l && strings[i +1].equals(second)
                && i + 2 < l)
                    res.add(strings[i + 2]);
            }

            return res.toArray(new String[0]);
        }
    }
}
