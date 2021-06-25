package View.CommandLines;

import com.beust.jcommander.Parameter;

public class IncreaseMoney extends CommandLine{

    @Parameter(names = {"--money", "-m"}, description = "card position", arity = 1, required = true)
    public int amount;

}
