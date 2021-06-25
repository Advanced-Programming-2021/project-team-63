
package View.CommandLines;
import com.beust.jcommander.Parameter;

public class DuelNewGame extends CommandLine {

    @Parameter(names ={"--round","-r"}, description = "the deck where you want to add card to",arity=1)
    public int round;
    @Parameter(names ={"--second-player","-sp"}, description = "card name which you want to add",arity=1,required = false)
    public String secondPlayerUsername;
    @Parameter(names = {"--new","-n"}, description = "specify your deck side",arity=0,required = false)
    public boolean neww;
    @Parameter(names ={"--ai","-a"}, description = "card name which you want to add",arity=0)
    public boolean ai;
}
