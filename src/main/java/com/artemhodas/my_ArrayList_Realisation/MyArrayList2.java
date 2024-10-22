package com.artemhodas.my_ArrayList_Realisation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.SortedMap;

public class MyArrayList2{



        public static void main(String[] args) {
            MyList<String> list = new MyArrayList<>();
            list.addElement("Bob");
            list.addElement("Jhon");
            list.addElement("Adam");
            list.addElement("Connor");
            list.addElement("George");
            list.addElement("James");
            Comparator<String> comparator = new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return o1.compareTo(o2);
                }
            };
            System.out.println(list);
            list.quickSort(comparator);
            System.out.println("Отсортированный список: " + list);
        }
};

