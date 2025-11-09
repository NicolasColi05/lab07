package it.unibo.nestedenum;

import java.util.Comparator;
import java.util.Locale;
import java.util.Objects;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {

    public enum Month{
        JANUARY(31),
        FEBRUARY(28),
        MARCH(31),
        APRIL(30),
        MAY(31),
        JUNE(30),
        JULY(31),
        AUGUST(31),
        SEPTEMBER(30),
        OCTOBER(31),
        NOVEMBER(30),
        DECEMBER(31);

        final static private Locale LOCALE = Locale.ITALY;
        private final int numberDay;

        private Month(int day){
            this.numberDay = day;
        }
        
        public static Month fromString(String s){
            Month result = null;
            int count = 0;
            for (Month month : Month.values()) {
                if(month.toString().toUpperCase(LOCALE).startsWith(s.toUpperCase(LOCALE))){
                    count ++;
                    if(count == 1){
                        result = month;
                    }else{
                        throw new IllegalArgumentException(" the " + s + " is ambiguos ");
                    }
                }
            }
            if(result == null){
                throw new IllegalArgumentException("no month with such name [" + s + "]");
            }
            return result;
        }
    }

    public static class SortByDate implements Comparator<String>{

        @Override
        public int compare(String s1 , String s2){
            final Month m1 = Month.fromString(s1);
            final Month m2 = Month.fromString(s2);
            if(m1.numberDay < m2.numberDay){
                return -1;
            }if(m1.numberDay > m2.numberDay){
                return 1;
            }else{
                return 0;
            }
        }
        
    }  

    public static class SortByMonthOrder implements Comparator<String>{

        @Override
        public int compare(String s1, String s2) {
            final Month m1 = Month.fromString(s1);
            final Month m2 = Month.fromString(s2);
            return m1.compareTo(m2); 
        }

    }

    @Override
    public Comparator<String> sortByDays() {
        return new SortByDate();
    }

    @Override
    public Comparator<String> sortByOrder() {
        return new SortByMonthOrder();
    }
}
