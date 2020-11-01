import java.io.*;
import java.util.*;
public class Main
{
  public static void main (String[] args) throws java.lang.Exception
  {  	
     Scanner r = new Scanner(System.in);
     int size = r.nextInt();
     for( int i=0  ;i<size  ;  i++)
     {      
      int num = factorize(r.nextInt());
     System.out.println(num);
     }
     }
     static int factorize(int fac)
     {
     	int test =0;
     	for(int i=1;pow(5,i)<=fac;i++)
     	{
     		test+= (fac / pow(5,i));
     	}     	
     	return test;
     }
     static int pow(int n, int p)
     {
     	int sup=1;
     	for(int i=0;i<p;i++)
     	{
     		sup*=n;
     	}
     	return sup;
     }  
}
