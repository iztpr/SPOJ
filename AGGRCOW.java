import java.util.Scanner;
import java.util.Arrays;
import java.io.*;
import java.lang.*;

class Main {
	static boolean valid(long[] ar, final long mid, final int k) {
		long last_pos = ar[0], count = 1;
		for(long x : ar) {
			if((x - last_pos) >= mid) {
				if(++count == k)
					return true;
				last_pos = x;
			}
		}
		return false;
	}
	
	static long minDistBinSearch(long[] ar, final int k, long lo, long hi) {
		if (lo == hi) return lo;
		long mid = lo+(hi-lo+1)/2;
		if(valid(ar, mid, k)) return minDistBinSearch(ar, k, mid, hi);
		else return minDistBinSearch(ar, k, lo, mid-1);
	}
	
	public static void main (String[] args) throws java.lang.Exception {
		Scanner s = new Scanner(System.in);
		int t = s.nextInt();
		while(t-- > 0) {
			int n = s.nextInt(), c = s.nextInt();
			long[] dist = new long[n];
			for(int i = 0; i < n; ++i)
				dist[i] = s.nextLong();
			Arrays.sort(dist);
			System.out.println(minDistBinSearch(dist, c, dist[0], dist[dist.length-1]));
		}
	}
}
