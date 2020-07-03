import java.util.Scanner;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        //Scanner in = new Scanner(System.in);
        //int a = in.nextInt();
        //System.out.println(a);
HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
		Scanner scanner = new Scanner(System.in);
		int total = scanner.nextInt();
		int target = scanner.nextInt();
		int[] gun = new int[total];
		for(int i =0;i<total;i++) {
			gun[i]=scanner.nextInt();			
		}
		for(int j=0;j<target;j++) {
			map.put(j+1, 1);
		}
		
		int left=0;
		int count=target;
		int result=0;
		int min = total;
		for(int right=0;right<total;right++) {
				if(map.containsKey(gun[right])) {
					map.put(gun[right], map.get(gun[right])-1);
					if(map.get(gun[right])>=0) {
						count--;
					}
				}
				
				while(count==0) {
					if(right-left<min) {
						min= right-left;						
					}
					if(map.containsKey(gun[left])) {
						map.put(gun[left], map.get(gun[left])+1);
						if(map.get(gun[left])>0) count ++;
					}
					left++;
				}						
		}		
		System.out.println(min==total?-1:min+1);

    }
    
    }
