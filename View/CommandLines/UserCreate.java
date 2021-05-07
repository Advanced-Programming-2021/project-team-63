package com.company;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;

import java.util.ArrayList;
import java.util.Scanner;

public class UserCreate extends CommandLine {


    @Parameter(names ={"--username","-u"}, description = "your account name to login",arity=1)
    public String username;
    @Parameter(names ={"--nickname","-n"}, description = "your name to be shown to others",arity=1)
    public String nickname;
    @Parameter(names = {"--password","-p"}, description = "your login pass",arity=1)
    public String password;


}

