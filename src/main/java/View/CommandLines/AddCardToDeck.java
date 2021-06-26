package View.CommandLines;//package com.company;
import com.beust.jcommander.*;

import java.util.ArrayList;
import java.util.List;

public class AddCardToDeck extends CommandLine {

    @Parameter(names ={"--deck","-d"}, description = "the deck where you want to add card to",arity=1,required = true)
    public String deckName;
    @Parameter(names ={"--card","-c"}, description = "card name which you want to add",variableArity = true,required = true)
    public List<String> cardName = new ArrayList<String>();
    @Parameter(names = {"--side","-s"}, description = "specify your deck side",arity=0,required = false)
    public boolean side;
}

