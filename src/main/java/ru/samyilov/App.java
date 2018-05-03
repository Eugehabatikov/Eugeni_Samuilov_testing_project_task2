package ru.samyilov;

import com.beust.jcommander.JCommander;

import java.io.File;


public class App 
{
    public static void main( String[] args )
    {
        Args cla = new Args();
        JCommander.newBuilder().addObject(cla).build().parse(args);

        unicString us = new unicString();
        us.loadStrings(new File(cla.file));
        us.strUnion(cla.i, cla.s);
        if(cla.u){
            us.makeUnic(cla.s);
        }
        us.save(cla.o, cla.c);
    }
}
