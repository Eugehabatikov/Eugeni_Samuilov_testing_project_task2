package ru.samyilov;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

/**
 class allows you to process lines, remove all the same, as well as on request:
 ignore case
 when comparing, ignore the first N characters
 count the number of identical rows
 leave only unique lines
 read from any files parameters and data, as well as output the result to any file
 */
public class unicString {
    private ArrayList<String> store;
    private ArrayList<Integer> counts;

    /**
     * checks the incoming line, and,
     * depending on the incoming data,
     * selects where to read
     * @param inputFile if !=null - scan in this ,else scan in command line
     * @return data in array "store"
     */
    public ArrayList<String> loadStrings(File inputFile) {
        Scanner input;
        store = new ArrayList<>();
        counts = new ArrayList<>();
        try {
            if (inputFile != null) {
                input = new Scanner(inputFile);
            } else {
                input = new Scanner(System.in);
            }
            while (input.hasNextLine()) {
                store.add(input.nextLine());
                counts.add(1);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return store;
    }

    /**
     * checks for the presence of the -i and -s parameters and implements them
     * @param i if this parameter exists, then the register is ignored
     * @param s number of characters to ignore when comparingn
     * @return data  in array "store"
     */
    public ArrayList<String> strUnion(boolean i, int s) {
        String str1 = "";
        int count = 0;
        ArrayList<String> tmpStore = new ArrayList<>();
        ArrayList<Integer> tmpCounts = new ArrayList<>();
        ListIterator<String> iterator = store.listIterator();
        if (iterator.hasNext()) {
            str1 = iterator.next();
            count++;
            tmpStore.add(str1);
            tmpCounts.add(1);
        }
        while (iterator.hasNext()) {
            String str2 = iterator.next();
            String str12;
            String str22;
            int startIndex;
            if (s != 0) {
                startIndex = s;
                str12 = str1.substring(startIndex);
                str22 = str2.substring(startIndex);// нужно проверить что стартиндех Б длины строки
            } else {
                str12 = str1;
                str22 = str2;
            }
            if (i) {
                str12 = str12.toLowerCase();
                str22 = str22.toLowerCase();
            }
            if (str12.equals(str22)) {
                tmpCounts.set(count - 1, tmpCounts.get(count - 1) + 1);
            } else {
                str1 = str2;
                tmpStore.add(str1);
                tmpCounts.add(1);
                count++;
            }
        }
        store = tmpStore;
        counts = tmpCounts;
        return store;
    }

    /**
     * implements the -u option
     * @param s number of characters to ignore when comparing
     * @return data  in array "store"
     */
    public ArrayList<String> makeUnic(int s) {
        ArrayList<String> temp = new ArrayList<>();
        ListIterator<String> iteratorStore1 = store.listIterator();
        while (iteratorStore1.hasNext()) {
            String s11 = iteratorStore1.next();
            String s12;
            if (s != 0) {
                s12 = s11.substring(s);
            } else {
                s12 = s11;
            }
            int k = 0;
            ListIterator<String> iteratorStore2 = store.listIterator();
            while (iteratorStore2.hasNext()) {
                String s21 = iteratorStore2.next();
                String s22;
                if (s != 0) {
                    s22 = s21.substring(s);
                } else {
                    s22 = s21;
                }
                if (s12.equals(s22)) {
                    k++;
                }
            }
            if (k == 1) {
                temp.add(s11);
            }
        }
        store = temp;
        return store;
    }

    /**
     * outputs data to a file or to the console, counts the number of summed lines
     * @param outputFile name file of output
     * @param c implements the -c option
     */
    public void save(File outputFile, boolean c) {
        if (outputFile != null) {
            try {
                System.setOut(new PrintStream(new FileOutputStream(outputFile)));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            ListIterator iterator = store.listIterator();
            ListIterator iteratorCount = counts.listIterator();
            while (iterator.hasNext()) {
                if (c) {
                    System.out.println(iteratorCount.next() + " " + iterator.next());
                } else {
                    System.out.println(iterator.next());
                }
            }
        }
    }
}