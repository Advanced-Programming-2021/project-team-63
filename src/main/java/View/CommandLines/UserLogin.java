import java.io.IOException;
import java.io.InputStream;

package View.CommandLines;
import opennlp.tools.sentdetect.SentenceDetector;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import com.beust.jcommander.Parameter;

public class UserLogin extends CommandLine {

    @Parameter(names ={"--username","-u"}, description = "your account name to login",arity=1)
    public String username;
    @Parameter(names = {"--password","-p"}, description = "your login pass",arity=1)
    public String password;
}
