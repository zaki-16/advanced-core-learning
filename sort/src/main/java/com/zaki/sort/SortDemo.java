package com.zaki.sort;

import com.google.common.collect.Lists;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * @author wangzhen
 * @date 2019-12-20
 */
public class SortDemo {
    @Data
    @Builder
    private static class Student {
        private String name;
        private int age;
        private int grade;
    }

    private static List<Student> init(int n) {
        ArrayList<Student> list = Lists.newArrayList();
        for (int i = 0; i < n; i++) {
            Student student = Student.builder().age(i + 20).name("test" + i).grade(new Random().nextInt(100)).build();
            list.add(student);
        }
        return list;
    }

    public static void printList(List<Student> list) {
        list.forEach(System.out::println);
        System.out.println("------------------------------------------------");
    }

    public static void main(String[] args) {
        // 初始化list
        List<Student> list = init(5);

        // 1.单字段排序.Comparator 默认升序
        list.sort(Comparator.comparing(Student::getGrade));
        // 逆序
        list.sort(Comparator.comparing(Student::getGrade).reversed());
        // 2. 多字段排序,优先判断前面，一样的再按后面的规则排序
        list.sort(Comparator.comparing(Student::getGrade).thenComparing(Student::getAge));
        // 3. 对null值友好排序
        list.sort(Comparator.nullsFirst(Comparator.comparing(Student::getGrade)));
    }


}
