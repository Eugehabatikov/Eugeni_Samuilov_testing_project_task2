package ru.samyilov;

import com.beust.jcommander.IStringConverter;

import java.io.File;

public class fileConverter implements IStringConverter<File> {
    @Override
    public File convert(String value) {
        return new File(value);
    }
}
