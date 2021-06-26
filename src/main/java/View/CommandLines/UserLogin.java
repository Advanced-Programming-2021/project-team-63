package View.CommandLines;
import com.beust.jcommander.Parameter;

public class UserLogin extends CommandLine {

    @Parameter(names ={"--username","-u"}, description = "your account name to login",arity=1,required = true)
    public String username;
    @Parameter(names = {"--password","-p"}, description = "your login pass",arity=1)
    public String password;
}
