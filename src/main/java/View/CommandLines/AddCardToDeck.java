package View.CommandLines;//package com.company;
import java.io.IOException;
import java.io.InputStream;


import opennlp.tools.sentdetect.SentenceDetector;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;

import com.beust.jcommander.*;

public class AddCardToDeck extends CommandLine {

    @Parameter(names ={"--deck","-d"}, description = "the deck where you want to add card to",arity=1)
    public String deckName;
    @Parameter(names ={"--card","-c"}, description = "card name which you want to add",arity=1)
    public String cardName;
    @Parameter(names = {"--side","-s"}, description = "specify your deck side",arity=0,required = false)
    public boolean side;
}

