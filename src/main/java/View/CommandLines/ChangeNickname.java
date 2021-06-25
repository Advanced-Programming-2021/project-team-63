
package View.CommandLines;
import com.beust.jcommander.Parameter;


public class ChangeNickname extends CommandLine {


    @Parameter(names ={"--nickname","-nn"}, description = "your name to be shown to others",arity=1,required = false )
    public String nickname;


    @Parameter(names ={"--password","-p"}, description = "your account name to login",required = false,arity = 0)
    public boolean password;
    @Parameter(names ={"--current","-c"}, description = "your current pass",arity=1,required = false)
    public String current;
    @Parameter(names = {"--new","-n"}, description = "your new pass",arity=1,required = false)
    public String neww;


}
