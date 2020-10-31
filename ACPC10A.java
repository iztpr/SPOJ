import java.util.*;
import java.lang.*;

class Solution
{
    public static void findProgression(long a, long b, long c){
        long diff1 = b-a;
        long diff2 = c-b;
        long nextNumber;
        if(diff1 == diff2){
            nextNumber = c + diff1;
            System.out.println("AP " + nextNumber);
        }else{
            nextNumber = (long)((double)c*((double)c/(double)b));
            System.out.println("GP " + nextNumber);
        }
    }

    public static void main (String[] args) throws java.lang.Exception
    {
        Scanner scan = new Scanner(System.in);

        while(true){

            int a = scan.nextInt();
            int b = scan.nextInt();
            int c = scan.nextInt();

            if(a==0 && b==0 && c==0)break;

            findProgression(a, b, c);

        }
    }
}
