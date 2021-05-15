
import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;

import java.util.Scanner;

public class CommandLine

{



    public Object run(String b) throws ParameterException {
        String[] a=b.split(" ");

        JCommander.newBuilder()
                .addObject(this)
                .build()
                .parse(a);
        return this;

    }
}
