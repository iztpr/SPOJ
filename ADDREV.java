import java.util.Scanner;

public class Main {
    static int result,finalresult,firstNumber,secondNumber;

    //Can here be any problem?      
    public static void main(String[] args)throws Exception {
    // TODO Auto-generated method stub
    Scanner s=new Scanner(System.in);
    int n = s.nextInt();
    while( n > 0 ){
        n--;
        firstNumber=s.nextInt();
        secondNumber=s.nextInt();
        System.out.println(Reverse(Reverse(firstNumber)+Reverse(secondNumber)));
    }
    s.close();
    }
    public static int Reverse(int i) {   
    result=0;
    while(i!=0) {
        result=result*10+i%10;
        i=i/10;
    }   
    return result;
    }
}
