package com.learning.core;

import lombok.Data;

/**
 * @author wangzhen
 * @date 2020-07-23
 */
@Data
public class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}
