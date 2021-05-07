package com.company;

import com.beust.jcommander.Parameter;

public class ChangePass extends CommandLine{

    @Parameter(names ={"--password","-p"}, description = "your account name to login",arity=0,required = true)
    public Boolean password;
    @Parameter(names ={"--current","-c"}, description = "your current pass",arity=1)
    public String current;
    @Parameter(names = {"--new","-n"}, description = "your new pass",arity=1)
    public String neww;
}
