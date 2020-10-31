import java.io.*;
import java.util.*;

class AIBOHP {

    public static void main(String args[])throws IOException{
     
     
     BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
     long T=Long.parseLong(br.readLine());
     
     while(T--!=0){
    
      StringBuilder str=new StringBuilder(br.readLine());
      String ostr=str.toString();
      System.out.println(ostr.length()-LCS(ostr,str.reverse().toString()));
      
     }
     
    }
    
    public static int LCS(String x,String y){
     
     int dp[][]=new int[x.length()+1][y.length()+1];
     
     for(int i=0;i<=x.length();i++){
      for(int j=0;j<=y.length();j++){
       if(i==0||j==0){
        dp[i][j]=0;
        continue;
       }
       
       if(x.charAt(i-1)==y.charAt(j-1)){
        dp[i][j]=dp[i-1][j-1]+1;
       }else{
        dp[i][j]=Math.max(dp[i][j-1],dp[i-1][j]);
       }
       
      }
     }
     return dp[x.length()][y.length()];
    }
      
}
