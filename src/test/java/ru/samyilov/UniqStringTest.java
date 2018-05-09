package ru.samyilov;

import com.beust.jcommander.JCommander;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;


class UniqStringTest {
    @Test
    void loadStrings() {
        UniqString us = new UniqString("src\\test\\resources\\input1.txt");
        File inputFile = null;
        try {
            inputFile = new File("src\\test\\resources\\input1.txt");
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
        File cmmd = new File("src\\test\\resources\\cmd.txt");
        Scanner cmd = null;
        int i = 1;
        try {
            cmd = new Scanner(cmmd);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (cmd.hasNextLine()) {
            Args cla = new Args();

            try {
                String[] args = cmd.nextLine().split(" ");
                JCommander.newBuilder().addObject(cla).build().parse(args);
                UniqString us = new UniqString(cla.file);
                ArrayList<String> actual = us.strUnion(cla.i, cla.s);
                File exp = new File("src\\test\\resources\\output" + i + ".txt");
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
                    assertTrue(iteratorExpected.next().equals(iteratorActual.next()), "test № " + i + " Fail");
                }
                System.out.println("test №" + i + " pass");
            } catch (Exception e) {
                e.printStackTrace();
            }
            i += 1;
        }
    }

    @Test
    void makeUniq() {
        File cmmd = new File("src\\test\\resources\\MakeUniqCMD.txt");
        Scanner cmd = null;
        try {
            cmd = new Scanner(cmmd);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int i = 1;
        while (cmd.hasNextLine()) {
            Args cla = new Args();
            try {
                String[] args = cmd.nextLine().split(" ");
                JCommander.newBuilder().addObject(cla).build().parse(args);
                UniqString us = new UniqString(cla.file);
                us.strUnion(cla.i, cla.s);
                ArrayList<String> actual = null;
                if (cla.u) {
                    actual = us.makeUniq(cla.s);
                }
                File exp = new File("src\\test\\resources\\MakeUniqOutput" + i + ".txt");
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
                    assertTrue(iteratorExpected.next().equals(iteratorActual.next()), "test № " + i + " Fail");
                }
                System.out.println("test №" + i + " pass");
            } catch (Exception e) {
                e.printStackTrace();

            }
            i += 1;
        }

    }

    @Test
    void save() {
        File cmmd = new File("src\\test\\resources\\SaveCMD.txt");
        Scanner cmd = null;
        try {
            cmd = new Scanner(cmmd);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int i = 1;
        while (cmd.hasNext()) {
            Args cla = new Args();
            try {
                String[] args = cmd.nextLine().split(" ");
                JCommander.newBuilder().addObject(cla).build().parse(args);
                UniqString us = new UniqString(cla.file);
                us.strUnion(cla.i, cla.s);
                if (cla.u) {
                    us.makeUniq(cla.s);
                }
                if (cla.o != null) {
                    us.save(cla.o, cla.c);
                }
                File actualOutput = new File("savetestout.txt");
                assertTrue(actualOutput.exists());
                Scanner actualScan = new Scanner(actualOutput);
                ArrayList<String> actual = new ArrayList<>();
                while (actualScan.hasNext()) {
                    actual.add(actualScan.nextLine());
                }
                File exp = new File("src\\test\\resources\\SaveOutput" + i + ".txt");
                ArrayList<String> expected = new ArrayList<>();
                try {
                    Scanner inp = new Scanner(exp);
                    while (inp.hasNext()) {
                        expected.add(inp.nextLine());
                    }
                    inp.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                assertEquals(expected.size(), actual.size(), "length is't eqals");
                ListIterator iteratorActual = actual.listIterator();
                ListIterator iteratorExpected = expected.listIterator();
                while (iteratorActual.hasNext()) {
                    assertTrue(iteratorExpected.next().equals(iteratorActual.next()), "test № " + i + " Fail");
                }
                System.out.println("test №" + i + " pass");
            } catch (Exception e) {
                e.printStackTrace();
            }
            i++;
        }
    }
}