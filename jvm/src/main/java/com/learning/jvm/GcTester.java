package com.learning.jvm;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangzhen
 * @date 2020-07-09
 */
public class GcTester {
    public Object instance = null;

    private static Byte[] bytes = new Byte[2 * 1024 * 1024];

    public static void testGc() {
        GcTester gcTesterA = new GcTester();
        GcTester gcTesterB = new GcTester();
        gcTesterA.instance = gcTesterB;
        gcTesterB.instance = gcTesterA;

        gcTesterA = null;
        gcTesterB = null;
        System.gc();
    }

    public static void testRef() {
        SoftReference<String> stringSoftReference = new SoftReference<String>("stringSoftReference");
        WeakReference<String> weakReference = new WeakReference<String>("weakReference");
        PhantomReference<String> phantomReference = new PhantomReference<String>("phantomReference", new ReferenceQueue<String>());
        System.out.println(stringSoftReference.get());
        System.out.println(weakReference.get());
        System.out.println(phantomReference.get());
        System.gc();
        System.out.println(stringSoftReference.get());
        System.out.println(weakReference.get());
        System.out.println(phantomReference.get());
    }

    static class OOMObject {
        public byte[] placeholder = new byte[64 * 1024];
    }

    public static void fillHeap(int num) throws InterruptedException {
        List<OOMObject> list = new ArrayList<OOMObject>();
        for (int i = 0; i < num; i++) {
            // 稍作延时， 令监视曲线的变化更加明显
            Thread.sleep(50);
            list.add(new OOMObject());
        }
        System.gc();
    }

    public static void main(String[] args) throws Exception {
        fillHeap(1000);
    }
}
