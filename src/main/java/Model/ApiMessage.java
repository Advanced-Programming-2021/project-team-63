package Model;
import java.lang.*;

public class ApiMessage {
    private String type;
    private String message;
    public ApiMessage(String type, String message) throws Exception{
        if(type != "error" && type != "successful"){
            throw new Exception("wrong initialize in apiMessage");
        }
        this.type = type;
        this.message = message;
    }
    
    public String getType(){
        return this.type;
    }
    
    public String getMessage(){
        return this.message;
    }
}
