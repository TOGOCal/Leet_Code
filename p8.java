import java.util.Scanner;

public class p8 {

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        String str = s.nextLine();

        System.out.println(turnInteger(str));

        s.close();
    }

    public static int turnInteger(String s){

        if(s == null || s.length() == 0){

            return 0;
        }

        s = s.trim();

        if(s.length() == 0){

            return 0;
        }

        char[] chars = s.toCharArray();

        int result;

        int key = 1;
        int i =0;

        if(chars[0] == '-'){

            key =-1;
            i++;
        }else if(chars[0] == '+'){

            i++;
        }else if(!Character.isDigit(chars[0])){

            return 0;
        }

        while(i<chars.length &&chars[i] == '0'){

            i++;
        }

        if(i >= chars.length){

            return 0;
        }
        
        StringBuilder sb = new StringBuilder();
        for(int j = i; j<chars.length; j++){

            if(!Character.isDigit(chars[j])){

                break;
            }
            sb.append(chars[j]);
        }

        if(sb.length() == 0){

            return 0;
        }

        if(outRange(sb.toString())){

            if(key == 1){

                return Integer.MAX_VALUE;
            }else{

                return Integer.MIN_VALUE;
            }
        }

        result = Integer.parseInt(sb.toString());

        return result*key;
    }

    public static boolean outRange(String str){

        String range = new String(String.valueOf(Integer.MAX_VALUE));

        if(str.length() > range.length() || (str.length() == range.length() && str.compareTo(range) > 0)){

            return true;
        }


        return false;
    }
}
