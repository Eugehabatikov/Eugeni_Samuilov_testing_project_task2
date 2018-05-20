package ru.samyilov;

import java.util.Comparator;

public class StringComparator implements Comparator<String> {

    boolean i;
    int s;

    public StringComparator(boolean i, int s) {
        this.i = i;
        this.s = s;
    }

    @Override
    public int compare(String o1, String o2) {
        if (this.i) {
            o1 = o1.toLowerCase();
            o2 = o2.toLowerCase();
        }
        if (s > 0) {
            o1 = o1.substring(s);
            o2 = o2.substring(s);
        }
        return o1.compareTo(o2);
    }
}
