import java.util.Scanner;
import java.util.*;
public class Main {

/*
腾讯一面 
气球游戏 求最小连续枪数命中所有颜色气球

题目一：

【气球游戏】小Q在进行射击气球的游戏，如果小Q在连续T枪中打爆了所有颜色的气球，将得到一只QQ公仔作为奖励。（每种颜色的气球至少被打爆一只）。这个游戏中有m种不同颜色的气球，编号1到m。小Q一共有n发子弹，然后连续开了n枪。小Q想知道在这n枪中，打爆所有颜色的气球最少用了连续几枪？

输入描述：

第一行两个空格间隔的整数数n，m。n<=1000000 m<=2000

第二行一共n个空格间隔的整数，分别表示每一枪打中的气球的颜色,0表示没打中任何颜色的气球。

输出描述：

一个整数表示小Q打爆所有颜色气球用的最少枪数。如果小Q无法在这n枪打爆所有颜色的气球，则输出-1

示例

输入：

12 5

2 5 3 1 3 2 4 1 0 5 4 3

输出：

6

test

12 5

2 5 3 1 3 2 4 1 5 0 4 3

5
*/
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
