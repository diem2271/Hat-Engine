package Editor;

import Engine.*;
import Engine.entity.Entity;
import Engine.entity.Model;
import Engine.entity.Texture;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;



public class TestGame implements ILogic {

    private static final float CAMERA_MOVE_SPEED = 0.05f;
    private final RenderManager renderer;
    private final ObjectLoader loader;
    private final WindowManager window;
    private Entity entity;
    private Camera camera;

    Vector3f cameraInc;

    public TestGame(){
        renderer = new RenderManager();
        window = Main.getWindow();
        loader = new ObjectLoader();
        camera = new Camera();
        cameraInc = new Vector3f(0,0,0);
    }


    @Override
    public void init() throws Exception {
        renderer.init();

        float[] vertices = new float[] {
                -0.5f,  0.5f, -0.5f,
                -0.5f, -0.5f, -0.5f,
                0.5f, -0.5f, -0.5f,
                0.5f,  0.5f, -0.5f

        };
        float[] textCoords = new float[]{
                0f,1f,
                0f,0f,
                1f,0f,
                1f,1f
        };
        int[] indices = new int[]{
                0, 1, 2,
                2, 3, 0
        };

        Model model = loader.loadModel(vertices,textCoords,indices);
        model.setTexture(new Texture(loader.loadTexture("textures/grassblock.png")));
        entity = new Entity(model, new Vector3f(0,0,5), new Vector3f(0,0,0),1);
    }

    @Override
    public void input() {
        cameraInc.set(0,0,0);
        if(window.isKeyPressed(GLFW.GLFW_KEY_W))
            cameraInc.z = -1;
        if(window.isKeyPressed(GLFW.GLFW_KEY_S))
            cameraInc.z = 1;

        if(window.isKeyPressed(GLFW.GLFW_KEY_A))
            cameraInc.x = -1;
        if(window.isKeyPressed(GLFW.GLFW_KEY_D))
            cameraInc.x = 1;

        if(window.isKeyPressed(GLFW.GLFW_KEY_Z))
            cameraInc.y = -1;
        if(window.isKeyPressed(GLFW.GLFW_KEY_X))
            cameraInc.y = 1;
    }

    @Override
    public void update() {
        camera.movePosition(cameraInc.x * CAMERA_MOVE_SPEED,cameraInc.y * CAMERA_MOVE_SPEED,cameraInc.z * CAMERA_MOVE_SPEED);

//        entity.incRotation(0.0f,0.5f,0.1f);
    }

    @Override
    public void render() {
        if(window.isResize()){
            GL11.glViewport(0,0,window.getWidth(),window.getHeight());
            window.setResize(true);
        }

        window.setClearColour(1.0f,0.0f,0.0f,0.0f);
        renderer.render(entity,camera);
    }

    @Override
    public void cleanup() {
        renderer.cleanup();
        loader.cleanup();
    }
}
