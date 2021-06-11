package View.CommandLines;
import com.beust.jcommander.Parameter;
import sun.tools.jar.CommandLine;

public class ShowDeck extends CommandLine {

    @Parameter(names = {"--deck-name", "-dn"}, description = "the deck where you want to add card to", arity = 1, required = true)
    public String deckName;

    @Parameter(names = {"--side", "-s"}, description = "specify your deck side", arity = 0, required = false)
    public boolean side;
}