import java.util.*;
import java.math.*;
import java.io.*;
class palin
{
	public static void main(String args [])	throws IOException
	{
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw=new PrintWriter(System.out, true);
		int t=Integer.parseInt(in.readLine());
		while(t-->0)
		{
			String str[]=in.readLine().split("");
			int len=str.length;
			int ar[]=new int[len+1];
			int k=0;
			for(int i=len-1; i>=0; i--)
			{
				ar[i]=Integer.parseInt(str[k++]);
			}
			increment(ar);
			int st=0;len=ar[len]!=0?len:len-1;
			int pr=len;
			while(st<len)
			{
				
				if(ar[st]!=ar[len])
				{
					if(ar[st]>ar[len])
					{
						int use=st+1;
						ar[use]++;
						while(ar[use]>=10)
						{
							ar[use]=0;
							ar[use+1]+=1;
							use+=1;
						}
					}
					ar[st]=ar[len];
				}
				st++;len--;
			}
			for(int j=0; j<=pr; j++)
			{pw.print(ar[j]);}
			pw.println();
		}
	}
	public static void increment(int ar[])
	{
		int i=0;
		ar[i]++;
		while(ar[i]==10)
		{
			ar[i]=0;
			ar[i+1]++;
			i++;
		}
	}
}
