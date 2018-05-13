package ru.samyilov;

import javax.swing.*;
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
public class UniqString {
    private ArrayList<String> store;
    private ArrayList<Integer> counts;

    public UniqString(String fileName){
        File inputFile = null;
        if(fileName != null){
            inputFile = new File(fileName);
        }
        this.loadStrings(inputFile);
    }

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
            if(inputFile != null && !inputFile.exists()){
                throw new NoSuchFieldException("File " + inputFile.getName() + " doesn't exsist");
            }
            if (inputFile != null) {
                input = new Scanner(inputFile);
                while (input.hasNextLine()) {
                    store.add(input.nextLine());
                    counts.add(1);
                }
            } else {
                input = new Scanner(System.in);
                System.out.println("Type your strings. To end input press Ctrl + D");
                while(input.hasNextLine()){
                store.add(input.nextLine());
                counts.add(1);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (NoSuchFieldException e){
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
                if(s < 0){
                    throw new IllegalArgumentException();
                }
                if (s > str1.length() || s > str2.length()){
                    throw new IllegalArgumentException();
                }
                startIndex = s;
                str12 = str1.substring(startIndex);
                str22 = str2.substring(startIndex);
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
    public ArrayList<String> makeUniq(int s) {
        ArrayList<String> temp = new ArrayList<>();
        for (String s11 : store) {
            String s12;
            if (s != 0) {
                s12 = s11.substring(s);
            } else {
                s12 = s11;
            }
            int k = 0;
            for (String s21 : store) {
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
        PrintStream ps = null;
        if (outputFile != null) {
/*
            if(outputFile.exists()){
                System.out.println("File " + outputFile.getName() + " alredy exists.");
                Scanner input = new Scanner(System.in);
                String answer = " ";
                while(!answer.equals("y") && !answer.equals("n")){
                    System.out.println("Do you want to replace it: y/n ");
                    answer = input.nextLine();
                }
                if(answer.equals("n")){
                    System.out.println("Data hasn't been save.");
                    return;
                }
            }
*/
            try {
                ps = new PrintStream(new FileOutputStream(outputFile));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        else{
            ps = new PrintStream(System.out);
        }
        ListIterator iterator = store.listIterator();
        ListIterator iteratorCount = counts.listIterator();
        while (iterator.hasNext()) {
            if (c) {
                ps.println(iteratorCount.next() + " " + iterator.next());
            } else {
                ps.println(iterator.next());
            }
        }
        ps.flush();
        ps.close();
    }
}