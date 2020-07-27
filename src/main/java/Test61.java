import java.util.Arrays;

public class Test61 {
    public static boolean isContinuous(int[] arr) {
        int zero_number = 0;
        int gap_number = 0;

        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                zero_number++;
            } else if (i+1 < arr.length && arr[i] == arr[i+1]) {
                return false;
            } else if (i+1 < arr.length && arr[i] != arr[i+1]-1) {
                gap_number += arr[i+1]-1 - arr[i];
            }
        }
        return zero_number>=gap_number?true:false;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2,7,0,5,1};
        System.out.println(isContinuous(arr));
    }

}
