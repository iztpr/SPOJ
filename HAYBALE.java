import java.util.*;
import java.math.*;
import java.io.*;
class haybale
{
	public static void main(String args [])	throws IOException
	{
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw=new PrintWriter(System.out, true);
			String str[]=in.readLine().split(" ");
			int n=Integer.parseInt(str[0]);
			int k=Integer.parseInt(str[1]);
			int ar[]=new int[n];
			int low=0,high=0;
			for(int i=0; i<k; i++)
			{
				String inp[]=in.readLine().split(" ");	
				low=Integer.parseInt(inp[0])-1;
				high=Integer.parseInt(inp[1])-1;
				if(high<(n-1))
				{
				ar[high+1]-=1;
				}
				ar[low]+=1;
			}
			//pw.print(ar[0]+" ");
			for(int j=1; j<n; j++)
			{
				ar[j]+=ar[j-1];
				//pw.print(ar[j]+" ");
			}
			//pw.println();
			Arrays.sort(ar);
			pw.println(ar[(n/2)]);
	}
}
