package View.CommandLines;

import com.beust.jcommander.Parameter;

public class Zone extends {
    @Parameter(names ={"--opponent","-o"}, description = "your account name to login",arity=0)
    public boolean opponent;
    @Parameter(names ={"--monster","-c"}, description = "your current pass",arity=1,required = false)
    public int monster;
    @Parameter(names = {"--spell","-s"}, description = "your new pass",arity=1,required = false)
    public int spell;
    @Parameter(names ={"--field","-f"}, description = "your current pass",arity=0,required = false)
    public boolean field;
    @Parameter(names = {"--hand","-h"}, description = "your new pass",arity=1,required = false)
    public int hand;
}
}
