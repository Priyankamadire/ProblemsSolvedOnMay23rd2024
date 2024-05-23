/*
2597. The Number of Beautiful Subsets
Solved
Medium
Topics
Companies
Hint
You are given an array nums of positive integers and a positive integer k.

A subset of nums is beautiful if it does not contain two integers with an absolute difference equal to k.

Return the number of non-empty beautiful subsets of the array nums.

A subset of nums is an array that can be obtained by deleting some (possibly none) elements from nums. Two subsets are different if and only if the chosen indices to delete are different.

 

Example 1:

Input: nums = [2,4,6], k = 2
Output: 4
Explanation: The beautiful subsets of the array nums are: [2], [4], [6], [2, 6].
It can be proved that there are only 4 beautiful subsets in the array [2,4,6].
Example 2:

Input: nums = [1], k = 1
Output: 1
Explanation: The beautiful subset of the array nums is [1].
It can be proved that there is only 1 beautiful subset in the array [1].
 

Constraints:

1 <= nums.length <= 20
1 <= nums[i], k <= 1000

*/
class  POTD{
    public int beautifulSubsets(int[] nums, int k) {
        /*Bruteforce
        int cnt = 0;
        int n = nums.length;
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                if(Math.abs(nums[i]-nums[j])!=k){
                    cnt++;
                }
            }
        }
        return cnt+n;*/
        // return helper(nums,0,k,new HashSet<>());
        Map<Integer,Integer> hm = new HashMap<>();
        return   help(nums,k,0,hm)-1;
    }
    /*
    public int helper(int nums[],int i,int k,Set<Integer> hs){
        int n = nums.length;
        if(i==n){
           return  hs.isEmpty()?0:1;
        }
        int cnt = helper(nums,i+1,k,hs);
        boolean flag = true;
        for(int num:hs){
            if(Math.abs(num-nums[i])==k){
                flag=false;
                break;
            }
        }
        if(flag){
            hs.add(nums[i]);
            cnt+=helper(nums,i+1,k,hs);
            hs.remove(nums[i]);
        }
        return cnt;
    }*/
    public int help(int nums[],int k,int st,Map<Integer,Integer> hm){
        int cnt=1;
        int n = nums.length;
        for(int i=st;i<n;i++){
            int num = nums[i];
            if(hm.getOrDefault(num-k,0)>0 || hm.getOrDefault(num+k,0)<0){
                continue;
            }
            hm.put(num,hm.getOrDefault(num,0)+1);
            cnt+=help(nums,k,i+1,hm);
            hm.put(num,hm.get(num)-1);
            if(hm.get(num)==0){
                hm.remove(num);
            }
        }
        return cnt;
    }
}
