import java.io.*;
import java.util.*;

class Spoj
{
    public static void main(String args[])throws Exception
    {
        BufferedReader bu=new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb=new StringBuilder();
        int t=Integer.parseInt(bu.readLine());
        while(t-->0)
        {
            String st[]=bu.readLine().split(" ");
            int s=Integer.parseInt(st[0]),c=Integer.parseInt(st[1]);
            int n=s,a[]=new int[100],start[]=new int[101],i;
            st=bu.readLine().split(" ");
            for(i=0;i<s;i++)
            a[i]=Integer.parseInt(st[i]);
            while(!check(a,n))  //finds out the degree of polynomial of sequence(number of cycles until sequence=1)
            {
                start[n]=a[0];
                for(i=0;i<n-1;i++)
                a[i]=a[i+1]-a[i];
                n--;
            }
            for(i=n;i<n+c;i++)  //new array being initialized
            a[i]=a[0];

            for(i=n;i<s;i++)
            {
                int sum=start[i+1],j;
                for(j=0;j<i+c;j++)
                sum+=a[j];      //sum of the sequence for current degree of polynomial(=s-n)
                for(j=i+c;j>=0;j--)
                {
                    a[j]=sum;
                    if(j>0) sum-=a[j-1];
                }
            }
            for(i=s;i<s+c;i++)
            sb.append(a[i]+" ");
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static boolean check(int a[],int n)
    {
        int i;
        for(i=0;i<n;i++)
        if(a[i]!=a[0]) return false;
        return true;    //array has all elements=1
    }
}
