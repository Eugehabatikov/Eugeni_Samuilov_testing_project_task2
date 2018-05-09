package ru.samyilov;

import com.beust.jcommander.JCommander;
import java.io.File;


public class App 
{
    public static void main( String[] args )
    {
        Args cla = new Args();
        JCommander.newBuilder().addObject(cla).build().parse(args);

        UniqString us = new UniqString();
        File inputFile = null;
        if(cla.file != null){
            inputFile = new File(cla.file);
        }
        us.loadStrings(inputFile);
        us.strUnion(cla.i, cla.s);
        if(cla.u){
            us.makeUniq(cla.s);
        }
        us.save(cla.o, cla.c);
    }
}
