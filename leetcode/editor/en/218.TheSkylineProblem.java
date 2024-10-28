import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
A city's skyline is the outer contour of the silhouette formed by all the 
buildings in that city when viewed from a distance. Given the locations and heights 
of all the buildings, return the skyline formed by these buildings collectively. 

 The geometric information of each building is given in the array buildings 
where buildings[i] = [lefti, righti, heighti]: 

 
 lefti is the x coordinate of the left edge of the iᵗʰ building. 
 righti is the x coordinate of the right edge of the iᵗʰ building. 
 heighti is the height of the iᵗʰ building. 
 

 You may assume all buildings are perfect rectangles grounded on an absolutely 
flat surface at height 0. 

 The skyline should be represented as a list of "key points" sorted by their x-
coordinate in the form [[x1,y1],[x2,y2],...]. Each key point is the left 
endpoint of some horizontal segment in the skyline except the last point in the list, 
which always has a y-coordinate 0 and is used to mark the skyline's termination 
where the rightmost building ends. Any ground between the leftmost and rightmost 
buildings should be part of the skyline's contour. 

 Note: There must be no consecutive horizontal lines of equal height in the 
output skyline. For instance, [...,[2 3],[4 5],[7 5],[11 5],[12 7],...] is not 
acceptable; the three lines of height 5 should be merged into one in the final 
output as such: [...,[2 3],[4 5],[12 7],...] 

 
 Example 1: 
 
 
Input: buildings = [[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]
Output: [[2,10],[3,15],[7,12],[12,0],[15,10],[20,8],[24,0]]
Explanation:
Figure A shows the buildings of the input.
Figure B shows the skyline formed by those buildings. The red points in figure 
B represent the key points in the output list.
 

 Example 2: 

 
Input: buildings = [[0,2,3],[2,5,3]]
Output: [[0,3],[5,0]]
 

 
 Constraints: 

 
 1 <= buildings.length <= 10⁴ 
 0 <= lefti < righti <= 2³¹ - 1 
 1 <= heighti <= 2³¹ - 1 
 buildings is sorted by lefti in non-decreasing order. 
 

 Related Topics Array Divide and Conquer Binary Indexed Tree Segment Tree Line 
Sweep Heap (Priority Queue) Ordered Set 👍 5906 👎 266

*/
       
/*
 2024-08-15 22:20:09
 The Skyline Problem
Category	Difficulty	Likes	Dislikes
algorithms	Hard (42.66%)	5906	266
Tags
divide-and-conquer | heap | binary-indexed-tree | segment-tree | line-sweep

Companies
facebook | google | microsoft | twitter | yelp
*/

class TheSkylineProblem {
      public static void main(String[] args) {
           Solution solution = new TheSkylineProblem().new Solution();
      }
        
      //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
          //scan line + priority queue（heap最大堆）
          // 1参考天上飞机数题目，将build房子的的左，右边界进行切分，左边界类比起飞，右边界类比降落，左边界相等时按照左边界降序排序，高度降序排序
             // 找到天上飞机数（高度）发生变化的key point点，收集这些key point点即可
          //天际线可以想象成高楼的影子场景，由于较高房子的高度影子会遮挡较矮房子的高度，
          // 因此需要实时维护一个最大高度，可以使用优先队列（堆）
          // 所以我们使用priority queue（heap最大堆）存build房子的高度，每次取最大高度与pre进行比较，当前queue最大高度与前高度pre发生时，即为key point点
          //TC:On(n2)
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> result = new ArrayList<>();
        List<int[]> hights = new ArrayList<>();
        for (int[] building : buildings) {
            //房子左边界，即设起点高度为负，这样便于sort后，可优先被访问
            //房子右边界，即设终点高度为正，这样便于sort后，可后被访问
            hights.add(new int[]{building[0], -building[2]});
            hights.add(new int[]{building[1], building[2]});
        }
        Collections.sort(hights,(a,b)->a[0]==b[0]?a[1]-b[1]:a[0]-b[0]);
        //维护一个最大堆，存房子高度
        PriorityQueue<Integer> heightQueue = new PriorityQueue<>((a,b)->b-a);
        //初始化，第0个房子高度为0
        heightQueue.add(0);
        int prevMaxHeight = 0;
        for (int[] hight : hights) {
            //遇到新房子的起点（飞机起飞），高度加入队列heightQueue，高度会降序排序
            if(hight[1]<0){
                heightQueue.add(-hight[1]);
            } else {
                //遇到旧房子（飞机降落）结束，所以需要从队列中remove
                //TC:On(n)
                heightQueue.remove(hight[1]);
            }
            int curMaxHeight = heightQueue.peek();
            //房子高度发生变化时，类比天际线房子的影子会发生变化，即为所求key point，收集即可
            if(curMaxHeight!=prevMaxHeight){
                //注意key point的高度不一定是当前房子的高度，而是队列中房子的最大高度，即curMaxHeight是最大堆heap的peek，即最大值，
                result.add(List.of(hight[0], curMaxHeight));
                prevMaxHeight = curMaxHeight;
            }
        }
        return result;
    }
}

    class Solution2 {
        //scan line + segment tree
        //TC:On(nlogn)
//        public List<List<Integer>> getSkyline(int[][] buildings) {
//
//        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}