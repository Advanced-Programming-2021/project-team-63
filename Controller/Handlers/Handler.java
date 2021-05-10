package Controller.Handlers;

public abstract class Handler {
    private Handler nextHandler;

    public setNextHandler(Handler nextHandler){
        this.nextHandler = nextHandler;
    }
    
    public abstract void handle();
}
