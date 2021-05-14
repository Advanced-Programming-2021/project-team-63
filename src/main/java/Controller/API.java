package Controller;

public class API {
    private static boolean justOneObject = false;
    private GameController gameController;
    private ProgramController programController;
    private String request;

    public API(){
        assert !justOneObject;
        justOneObject = true;
        gameController = new GameController();
        programController = new ProgramController();
    }
}
