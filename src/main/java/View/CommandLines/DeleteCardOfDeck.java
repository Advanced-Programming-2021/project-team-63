import com.beust.jcommander.Parameter;

public class DeleteCardOfDeck extends CommandLine{

    @Parameter(names ={"--deck","-d"}, description = "the deck where you want to add card to",arity=0,required = true)
    public String deckName;
    @Parameter(names ={"--card","-c"}, description = "card name which you want to add",arity=1)
    public String cardName;
    @Parameter(names = {"--side","-s"}, description = "specify your deck side",arity=0)
    public Boolean side;
}
