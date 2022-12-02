package tasks.collections;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class FilterLinkedList {
    static void filterList(LinkedList<String> list, String arg) {
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String currentElement = iterator.next();
            if (currentElement.startsWith(arg)) {
                iterator.remove();
                System.out.printf("Removing %s from the list%n", currentElement);
            }
        }
    }

    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>(List.of("thou","weight","roof","third","feathers","center",
                                                   "long","connected","pilot","alive","dear","minerals",
                                                   "church","married","fairly","dark","everywhere","relationship",
                                                   "told","task","secret","basket","do","tube","funny","gun","program",
                                                   "bigger","ride","doing","its","want","struggle","horn","copy",
                                                   "does","paper","correctly","ring","actually","bite","whispered"));
        filterList(list, "r");
    }
}
