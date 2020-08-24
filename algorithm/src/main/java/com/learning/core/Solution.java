package com.learning.core;

import java.util.*;
import java.util.concurrent.ExecutionException;

/**
 * @author wangzhen
 * @date 2020-07-23
 */
public class Solution {
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode fast = pre, slow = pre;
        while (n != 0) {
            fast = fast.next;
            n--;
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return pre.next;
    }

    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == target - nums[i]) {
                    return new int[]{i, j};
                }
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    public static String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        String[] split = s.split("");
        StringBuilder builder = new StringBuilder();
        int len = s.length();
        int index = 0;
        int col = 0;
        int asc = 0, desc = numRows - 2;
        String[][] arr = new String[numRows][len];
        while (index < len) {
            while (asc < numRows && index < len) {
                arr[asc++][col] = split[index++];
            }
            asc = 0;
            col++;
            while (desc > 0 && index < len) {
                arr[desc--][col] = split[index++];
            }
            desc = numRows - 2;
            col++;
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] != null) {
                    builder.append(arr[i][j]);
                }
            }
        }
        return builder.toString();
    }

    public static boolean divisorGame(int N) {
        return countMod(N, 0) % 2 == 1;
    }

    private static int countMod(int N, int xs) {
        if (N <= 1 || N > 1000) {
            return xs;
        }
        int x = N - 1;
        while (x > 0) {
            if (N % x == 0) {
                xs++;
                break;
            }
            x--;
        }
        return countMod(N - x, xs);
    }

    public static int foo(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        int res = 0;
        int pre = 1;
        int next = 2;
        for (int i = 3; i <= n; i++) {
            res = pre + next;
            pre = next;
            next = res;
        }
        return res;
    }

    private static int[][] triangle = {
            {2, 0, 0, 0},
            {3, 4, 0, 0},
            {6, 5, 7, 0},
            {4, 1, 8, 3}
    };


    public int jump(int[] nums) {
        int length = nums.length;
        int end = 0;
        int maxPosition = 0;
        int steps = 0;
        for (int i = 0; i < length - 1; i++) {
            maxPosition = Math.max(maxPosition, i + nums[i]);
            if (i == end) {
                end = maxPosition;
                steps++;
            }
        }
        return steps;
    }


    public int integerBreak(int n) {
        if (n <= 3) {
            return n - 1;
        }
        int a = n / 3;
        int b = n % 3;
        if (b == 0) {
            return (int) Math.pow(3, a);
        } else if (b == 1) {
            return (int) Math.pow(3, a - 1) * 4;
        } else {
            return (int) Math.pow(3, a) * b;
        }
    }

    public int maxArea(int[] height) {
        int maxArea = 0;
        int i = 0, j = height.length - 1;
        while (i < j) {
            maxArea = Math.max(maxArea, Math.min(height[i], height[j]) * (j - i));
            if (height[i] <= height[j]) {
                i++;
            } else {
                j--;
            }
        }
        return maxArea;
    }


    public static String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private static int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }

    public static int lengthOfLongestSubstring(String s) {
        int length = s.length();
        if (length <= 1) {
            return length;
        }
        Map<Character, Integer> visitedMap = new HashMap<Character, Integer>(length);
        int ans = 0, i = 0, j = i;
        while (j < length) {
            if (visitedMap.containsKey(s.charAt(j))) {
                visitedMap.put(s.charAt(j), j);
                ans = Math.max(ans, visitedMap.size());
                j++;
            } else {
                // 出现过重复字符
                visitedMap.clear();
                i++;
                j = i;
            }
        }
        return ans;
    }

    public int 遍历(String s) {
        // 哈希集合，记录每个字符是否出现过
        Set<Character> occ = new HashSet<Character>();
        int n = s.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                occ.remove(s.charAt(i - 1));
            }
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                // 不断地移动右指针
                occ.add(s.charAt(rk + 1));
                ++rk;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }

    public int lengthOfLongestSubsdtring(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for (int end = 0, start = 0; end < n; end++) {
            char alpha = s.charAt(end);
            if (map.containsKey(alpha)) {
                start = Math.max(map.get(alpha) + 1, start);
            }
            ans = Math.max(ans, end - start + 1);
            map.put(s.charAt(end), end);
        }
        return ans;
    }

    public static boolean isValid(String s) {
        if (s == null || s.length() == 1) return false;
        Stack<String> stack = new Stack<String>();
        String left = "({[";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("(", ")");
        map.put("{", "}");
        map.put("[", "]");
        String[] split = s.split("");
        for (int i = 0; i < s.length(); i++) {
            if (left.contains(split[i])) {
                stack.push(split[i]);
            } else {
                if (stack.isEmpty()) return false;
                String peek = stack.peek();
                if (split[i].equals(map.get(peek))) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    // 层序遍历
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return ans;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int h = 0;
        while (!queue.isEmpty()) {
            // 每层个数
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            while (size-- > 0) {
                TreeNode pop = queue.pop();
                if (pop != null) {
                    level.add(pop.val);
                    if (pop.left != null) {
                        queue.add(pop.left);
                    }
                    if (pop.right != null) {
                        queue.add(pop.right);
                    }
                }
            }
            if (++h % 2 == 0) {
                Collections.reverse(level);
            }
            ans.add(level);
        }
        return ans;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {


    }

    private static boolean isListSymmetric(List<Integer> lists) {
        int i = 0, j = lists.size() - 1;
        boolean ans = true;
        while (i <= j) {
            if (!Objects.equals(lists.get(i), lists.get(j))) {
                ans = false;
                break;
            }
            i++;
            j--;
        }
        return ans;
    }


}
