package View.CommandLines;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
public class CommandLine

{



    public Object run(String b) throws ParameterException {
        String[] a=b.split("[\\s]+");

        JCommander.newBuilder()
                .addObject(this)
                .build()
                .parse(a);
        return this;

    }
}
