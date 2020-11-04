import java.util.*;
import java.math.*;
import java.io.*;
 class Main{
    public static void main(String args[]){
        String s;
        Scanner sc=new Scanner (System.in);
        int l;
        while(sc.hasNext()){
            l=sc.nextInt();
            s=sc.next();
            String mat=sc.next();
            int temp=mat.indexOf(s,0);
            if(temp==-1) System.out.println();
            while(temp!=-1){
                System.out.println(temp);
                temp=mat.indexOf(s,temp+1);
            }
        }
    }
 }
