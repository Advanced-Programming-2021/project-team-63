package View.CommandLines;

import com.beust.jcommander.Parameter;

public class SelectHandForce extends CommandLine{

    @Parameter(names ={"--hand","-h"}, description = "your forced card",arity=1,required = true)
    public String cardName;
    @Parameter(names = {"--force","-f"}, description = "your login pass",required = true)
    public boolean force;
}
