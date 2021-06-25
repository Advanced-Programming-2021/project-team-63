package View.CommandLines;

import com.beust.jcommander.Parameter;

public class Set extends CommandLine {


    @Parameter(names = {"--position", "-p"}, description = "card position", arity = 1, required = true)
    public String position;



}
