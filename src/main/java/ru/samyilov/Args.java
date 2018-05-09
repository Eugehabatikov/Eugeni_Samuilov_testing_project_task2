package ru.samyilov;

import com.beust.jcommander.Parameter;
import java.io.File;


public class Args {
    @Parameter(names = {"-s"}, description = " означает, что при сравнении" +
            " строк следует игнорировать первые N символов каждой строки. Выводить нужно первую строку.")
    public Integer s = 0;

    @Parameter(names = {"-o"}, description = "задаёт имя выходного файла. Если параметр отсутствует," +
            " следует выводить результаты на консоль.")
    public File o = null;

    @Parameter(names = {"-i"}, description = "означает, что при сравнении строк следует не учитывать регистр символов.")
    public boolean i = false;

    @Parameter(names = {"-u"}, description = "означает, что следует выводить в качестве результата " +
            "только уникальные строки.")
    public boolean u = false;

    @Parameter(names = {"-c"}, description = "означает, что перед каждой строкой вывода следует вывести количество" +
            " строк, которые были заменены данной (т.е. если во входных данных было 2 одинаковые строки," +
            " в выходных данных должна быть одна с префиксом “2”)")
    public boolean c = false;

    @Parameter(description = "задает имя входного файла")
    public String file = null;

}
