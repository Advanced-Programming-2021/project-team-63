package com.company;

import com.beust.jcommander.Parameter;

public class ChangeNickname extends CommandLine{


    @Parameter(names ={"--nickname","-n"}, description = "your name to be shown to others",arity=1)
    public String nickname;


}
