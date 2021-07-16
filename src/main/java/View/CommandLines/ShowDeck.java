package View.CommandLines;
import com.beust.jcommander.Parameter;


public class ShowDeck extends CommandLine {

    @Parameter(names = {"--deck-name", "-dn"}, description = "the deck where you want to add card to", arity = 1, required =false)
    public String deckName;

    @Parameter(names = {"--side", "-s"}, description = "specify your deck side", arity = 0)
    public boolean side;

    @Parameter(names = {"--cards", "-c"}, description = "specify your deck side", arity = 0, required = false)
    public boolean cards;

}