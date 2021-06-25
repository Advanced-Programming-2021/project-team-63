package View.CommandLines;

import com.beust.jcommander.Parameter;

public class IncreaseLife extends CommandLine{
    @Parameter(names = {"--LP", "-L"}, description = "card position", arity = 1, required = true)
    public int LP;

}
