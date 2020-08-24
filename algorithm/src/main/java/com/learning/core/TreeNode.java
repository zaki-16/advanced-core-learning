package com.learning.core;

import lombok.Data;

/**
 * @author wangzhen
 * @date 2020-08-21
 */
@Data
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}
