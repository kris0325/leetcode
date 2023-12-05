import java.util.Scanner;
import java.util.*;
public class Main {

/*
腾讯一面 
气球游戏 求最小连续枪数命中所有颜色气球
> https://blog.csdn.net/TangXiaoPang/article/details/88376013

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
	
	
	/*
	思路：首先有几个关键点

1 何时更新最短子串长度？
2 如何判定已包含所有数字？
3 什么时候滑动窗口？

接着我先说明下几个参数
1 开枪的总次数 total
2 气球颜色数量 target
3 滑动窗口的左右指针 left right
4 最短子串长度 min

何时更新最短子串长度？

最短子串长度初始值为开枪的总次数 total，如果没有比total小的（最小值为 min+1 = right - left + 1 ），说明不满足要求，得不到QQ公仔。
当子串第一次包含所有的数字时，更新最短子串长度min。
那么第二个问题来了，如何判定已包含所有数字？

需要一个hashMap来统计每个颜色的数量，这题每种颜色只需要打一枪，但是可以延伸不同颜色不同数量的题目。
打掉了一个颜色的气球，就相应的在hashMap中KEY值对应value减一
同时还需要一个count来统计剩余颜色数量
如果打掉的颜色气球已经打满足要求，不需要再打了，此时的count就不减。因为count只记录剩下需要打的颜色
什么时候滑动窗口？ 既然要算最短长度，那么肯定要遍历数组的所有情况。那什么时候开始滑呢？ 如果我第一次滑动之前right指针往右移，
第一次满足了我已经打满了所有颜色的气球的情况 即剩余颜色数量count=0时，这时候我们需要判断现在是不是最短的子串长度，那么left往右移，开始滑动窗口



	*/
	
    public static void main(String[] args) {
	    
    HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
		Scanner scanner = new Scanner(System.in);
	  // 
		int total = scanner.nextInt();
		int target = scanner.nextInt();
		int[] gun = new int[total];
		for(int i =0;i<total;i++) {
			gun[i]=scanner.nextInt();			
		}
	    // 初始化 气球颜色 值为1 ， 打中减1
		for(int j=0;j<target;j++) {
			map.put(j+1, 1);
		}
		
		int left=0;
	    // count 剩余需要打中颜色的气球数
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
						// 滑动窗口左指针右移，这时需跟新map 中对应 key value +1，同时剩余颜色气球数 count +1
 						map.put(gun[left], map.get(gun[left])+1);
						if(map.get(gun[left])>0) count ++;
					}
					left++;
				}						
		}		
		System.out.println(min==total?-1:min+1);

    }
    
    }
