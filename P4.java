import java.util.Scanner;

public class P4 {

    public static void main(String[] args) {
    
        Scanner s=new Scanner(System.in);

        int a=s.nextInt();
        int b=s.nextInt();

        int arr1[]=new int[a];
        int arr2[]=new int[b];

        for(int i=0;i<a;i++){

            arr1[i]=s.nextInt();
        }

        for(int i=0;i<b;i++){

            arr2[i]=s.nextInt();
        }
        s.close();
    }


    // class Solution {
    //     public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            
    //     }
    // }
}
