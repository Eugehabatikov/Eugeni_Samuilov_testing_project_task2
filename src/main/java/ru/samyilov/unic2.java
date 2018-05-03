package ru.samyilov;

import com.beust.jcommander.JCommander;
import java.io.*;
import java.util.*;

public  class unic2 {
    static public void main(String[] args) {
        Args cla = new Args();
        try {
            JCommander.newBuilder()
                    .addObject(cla)
                    .build()
                    .parse(args);
            ArrayList<String> store = new ArrayList<>();
            ArrayList<Integer> counts = new ArrayList<>();
            int count = 0;
            Scanner input;
            if (cla.file != null) {
                input = new Scanner(cla.file);
            } else {
                input = new Scanner(System.in);
            }
            if (cla.o != null) {
                System.setOut(new PrintStream(new FileOutputStream(cla.o)));
            }

            String str1 = "";
            if (input.hasNextLine()) {
                str1 = input.nextLine();
                count++;
                store.add(str1);
                counts.add(1);
            }
            while (input.hasNextLine()) {
                String str2 = input.nextLine();
                String str12;
                String str22;
                int startIndex;
                if (cla.s != 0) {
                    startIndex = cla.s;
                    str12 = str1.substring(startIndex);
                    str22 = str2.substring(startIndex);// нужно проверить что стартиндех Б длины строки
                } else {
                    str12 = str1;
                    str22 = str2;
                }
                if (cla.i) {
                    str12 = str12.toLowerCase();
                    str22 = str22.toLowerCase();
                }
                if (str12.equals(str22)) {
                    counts.set(count - 1, counts.get(count - 1) + 1);
                } else {
                    str1 = str2;
                    store.add(str1);
                    counts.add(1);
                    count++;
                }
            }
            if (cla.u) {
                ArrayList<String> temp = new ArrayList<>();
                ListIterator<String> iteratorStore1 = store.listIterator();
                while (iteratorStore1.hasNext()) {
                    String s11 = iteratorStore1.next();
                    String s12;
                    if (cla.s != 0) {
                        s12 = s11.substring(cla.s);
                    } else {
                        s12 = s11;
                    }
                    int k = 0;
                    ListIterator<String> iteratorStore2 = store.listIterator();
                    while (iteratorStore2.hasNext()) {
                        String s21 = iteratorStore2.next();
                        String s22;
                        if (cla.s != 0) {
                            s22 = s21.substring(cla.s);
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
            }
            ListIterator<String> iteratorStore = store.listIterator();
            ListIterator<Integer> iteratorCounts = counts.listIterator();
            while (iteratorStore.hasNext()) {
                if (cla.c) {
                    System.out.println(iteratorCounts.next() + " " + iteratorStore.next());
                } else {
                    System.out.println(iteratorStore.next());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка: неправильный формат парметров приложения.");
        }
    }

    }

