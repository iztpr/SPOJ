import java.util.*;
import java.lang.*;
import java.io.*;
import java.math.*;
class BISHOPS
{
    public static void main (String[] args) throws java.lang.Exception
    {
         Scanner sc = new Scanner(System.in);
    while(sc.hasNextBigInteger())
    {
    BigInteger n = sc.nextBigInteger();
    if(n.equals(BigInteger.ONE))
    System.out.println(1);
    else
    System.out.println(n.subtract(BigInteger.ONE).multiply(BigInteger.valueOf(2)));
    }
    }
}
