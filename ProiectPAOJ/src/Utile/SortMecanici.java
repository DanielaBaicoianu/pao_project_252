package Utile;

import Entitati.Mecanic;

import java.util.Comparator;

public class SortMecanici implements Comparator {
    public int compare(Object o1, Object o2) {
        Mecanic m1 = (Mecanic) o1;
        Mecanic m2 = (Mecanic) o2;
        if (m1.getDataAngajarii().isAfter(m2.getDataAngajarii())) return -1;
        if (m2.getDataAngajarii().isAfter(m1.getDataAngajarii())) return 1;
        return 0;
    }
}
