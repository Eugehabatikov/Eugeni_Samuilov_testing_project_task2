package ru.samyilov;

import com.beust.jcommander.JCommander;

public class App
{
    public static void main( String[] args )
    {
        Args cla = new Args();
        JCommander.newBuilder().addObject(cla).build().parse(args);

        UniqString us = new UniqString(cla.file);
        us.strUnion(cla.i, cla.s);
        if(cla.u){
            us.makeUniq(cla.s);
        }
        us.save(cla.o, cla.c);
    }
}
