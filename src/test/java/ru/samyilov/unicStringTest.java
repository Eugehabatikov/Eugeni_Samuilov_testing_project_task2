package ru.samyilov;

import com.beust.jcommander.JCommander;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class unicStringTest {

    @Test
    void loadStrings() {
        unicString us = new unicString();
        File inputFile = null;
        try {
            inputFile = new File("input1.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<String> actual = null;
        try {
            actual = us.loadStrings(inputFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertNotNull(actual, "store is null");
        ArrayList<String> expected = new ArrayList<>();
        Scanner scanner;
        try {
            scanner = new Scanner(inputFile);
            while (scanner.hasNextLine()) {
                expected.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assertEquals(actual.size(), expected.size(), "scann is wrong");
        ListIterator<String> actualIterator = actual.listIterator();
        ListIterator<String> expectedIterator = actual.listIterator();
        while (actualIterator.hasNext()) {
            assertEquals(actualIterator.next(), expectedIterator.next());
        }
    }

    @Test
    void strUnion() {
        Args cla = new Args();
        for (int i = 1; i < 9; i++) {
            File cmmd = new File("src\\test\\java\\ru\\samyilov\\cmd" + i + ".txt");
            try {
                Scanner cmd = new Scanner(cmmd);
                ArrayList<String> argsList = new ArrayList<String>();
                while (cmd.hasNext()) {
                    argsList.add(cmd.nextLine());
                }
                String[] args = new String[argsList.size()];
                ListIterator<String> iteratorArgs = argsList.listIterator();
                while (iteratorArgs.hasNext()) {
                    args[iteratorArgs.nextIndex()] = iteratorArgs.next();
                }
                JCommander.newBuilder().addObject(cla).build().parse(args);
                unicString us = new unicString();
                us.loadStrings(new File(cla.file));
                ArrayList<String> actual = us.strUnion(cla.i, cla.s);
                File exp = new File("src\\test\\java\\ru\\samyilov\\output" + i + ".txt");
                ArrayList<String> expected = new ArrayList<>();
                try {
                    Scanner inp = new Scanner(exp);
                    while (inp.hasNext()) {
                        expected.add(inp.nextLine());
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                assertEquals(expected.size(), actual.size(), "length is't eqals on test №" + i);
                ListIterator iteratorActual = actual.listIterator();
                ListIterator iteratorExpected = expected.listIterator();
                while (iteratorActual.hasNext()) {
                    assertTrue(iteratorExpected.next().equals(iteratorActual.next()),"test № " + i + " Fail");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    void makeUnic() {
        Args cla = new Args();
        for (int i = 1; i < 7; i++) {
            File cmmd = new File("src\\test\\java\\ru\\samyilov\\MakeUnicCMD" + i + ".txt");
            try {
                Scanner cmd = new Scanner(cmmd);
                ArrayList<String> argsList = new ArrayList<String>();
                while (cmd.hasNext()) {
                    argsList.add(cmd.nextLine());
                }
                String[] args = new String[argsList.size()];
                ListIterator<String> iteratorArgs = argsList.listIterator();
                while (iteratorArgs.hasNext()) {
                    args[iteratorArgs.nextIndex()] = iteratorArgs.next();
                }
                JCommander.newBuilder().addObject(cla).build().parse(args);
                unicString us = new unicString();
                us.loadStrings(new File(cla.file));
                us.strUnion(cla.i, cla.s);
                ArrayList<String> actual = null;
                if(cla.u){
                    actual = us.makeUnic(cla.s);
                }
                File exp = new File("src\\test\\java\\ru\\samyilov\\MakeUnicOutput" + i + ".txt");
                ArrayList<String> expected = new ArrayList<>();
                try {
                    Scanner inp = new Scanner(exp);
                    while (inp.hasNext()) {
                        expected.add(inp.nextLine());
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                assertEquals(expected.size(), actual.size(), "length is't eqals" + " № " + i);
                ListIterator iteratorActual = actual.listIterator();
                ListIterator iteratorExpected = expected.listIterator();
                while (iteratorActual.hasNext()) {
                    assertTrue(iteratorExpected.next().equals(iteratorActual.next()),"test № " + i + " Fail");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Test
    void save() {
        Args cla = new Args();
        for (int i = 1; i < 5 ; i++) {
            File cmmd = new File("src\\test\\java\\ru\\samyilov\\MakeUnicCMD" + i + ".txt");
            try {
                Scanner cmd = new Scanner(cmmd);
                ArrayList<String> argsList = new ArrayList<String>();
                while (cmd.hasNext()) {
                    argsList.add(cmd.nextLine());
                }
                String[] args = new String[argsList.size()];
                ListIterator<String> iteratorArgs = argsList.listIterator();
                while (iteratorArgs.hasNext()) {
                    args[iteratorArgs.nextIndex()] = iteratorArgs.next();
                }
                JCommander.newBuilder().addObject(cla).build().parse(args);
                unicString us = new unicString();
                us.loadStrings(new File(cla.file));
                us.strUnion(cla.i, cla.s);
                if(cla.u){
                    us.makeUnic(cla.s);
                }
                if(cla.o != null){
                    us.save(cla.o, cla.c);
                }
                File actualOutput= new File("src\\test\\java\\ru\\samyilov\\test.txt");
                assertTrue(actualOutput.exists());
                Scanner actualScan  = new Scanner(actualOutput);
                ArrayList<String> actual = null;
                while (actualScan.hasNext()){
                    actual.add(actualScan.nextLine());
                }
                File exp = new File("src\\test\\java\\ru\\samyilov\\MakeUnicOutput" + "i" + ".txt");
                ArrayList<String> expected = new ArrayList<>();
                try {
                    Scanner inp = new Scanner(exp);
                    while (inp.hasNext()) {
                        expected.add(inp.nextLine());
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                assertEquals(expected.size(), actual.size(), "length is't eqals");
                ListIterator iteratorActual = actual.listIterator();
                ListIterator iteratorExpected = expected.listIterator();
                while (iteratorActual.hasNext()) {
                    assertTrue(iteratorExpected.next().equals(iteratorActual.next()),"test № " + i + " Fail");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

     }
    }
}