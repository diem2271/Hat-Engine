package Editor;


import Engine.EngineManager;
import Engine.ILogic;
import Engine.WindowManager;
import Engine.utils.Consts;
import org.lwjgl.Version;

public class Main {
    private static WindowManager window;
    private static TestGame game;

    public static ILogic getGame() {
        return game;
    }


    public static void main(String[] args) {
        System.out.println(Version.getVersion());
        window = new WindowManager(Consts.TITLE,1600,900,false);
        game = new TestGame();
        EngineManager engine = new EngineManager();
        try {
            engine.start();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static WindowManager getWindow() {
        return window;
    }
}
