import java.util.ArrayList;
import java.util.LinkedList;

class Solution {
    List<List<Integer>> result = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();
    public List<List<Integer>> combinationSum3(int k, int n) {
        backTrack( n , 1,0 );
        result result;
    }
    public void backTrack(Integer sumTarget, int begin, int sum ){
        if(path.size() > k){
            return;
        }
        if(path.size() == k && sum == sumTarget ){
            result.add(new ArrayList<>(path));
            return;
        }
        for(int i = begin; i < 9 - (k - path.size())+1; i++){
            sum += i;
            backTrack(sumTarget, i+1, sum);
            sum -= i;
        }
    }
}