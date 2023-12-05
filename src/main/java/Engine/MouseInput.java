package Engine;

import Editor.Main;
import org.joml.Vector2d;
import org.joml.Vector2f;
import org.lwjgl.glfw.GLFW;

public class MouseInput {
    private final Vector2d previousPos,currentPos;
    private final Vector2f displayVec;

    private boolean inWindow = false, leftButtonPress = false, rightButtonPress;

    public MouseInput (){
        previousPos = new Vector2d(-1,-1);
        currentPos = new Vector2d(0,0);
        displayVec = new Vector2f();
    }

    public void init(){
        GLFW.glfwSetCursorPosCallback(Main.getWindow().getWindowHandle(),(window,xpos,ypos)->{
            currentPos.x = xpos;
            currentPos.y = ypos;
        });

        GLFW.glfwSetCursorEnterCallback(Main.getWindow().getWindowHandle(),((window, entered) -> {
            inWindow = entered;
        }));
        GLFW.glfwSetMouseButtonCallback(Main.getWindow().getWindowHandle(),((window, button, action, mods) -> {
            leftButtonPress = button==GLFW.GLFW_MOUSE_BUTTON_1 && action == GLFW.GLFW_PRESS;
            rightButtonPress = button==GLFW.GLFW_MOUSE_BUTTON_2 && action == GLFW.GLFW_PRESS;
        }));
    }

    public void input(){
//        displayVec.x= 0;
//        displayVec.y= 0;
        if(previousPos.x>0 && previousPos.y>0 && inWindow){
            double x = currentPos.x - previousPos.x;
            double y = currentPos.y - previousPos.y;
//            System.out.println("cursor pos "+x+" "+y);
            boolean rotateX = x != 0;
            boolean rotateY = y != 0;
            if(rotateX)
                displayVec.y = (float) x;
            if(rotateY)
                displayVec.x = (float) y;
        }
        previousPos.x = currentPos.x;
        previousPos.y = currentPos.y;
    }

    public void setDisplayVec(float x, float y) {
        displayVec.x = x;
        displayVec.y = y;
    }
    public Vector2f getDisplayVec() {
        return displayVec;
    }

    public boolean isLeftButtonPress() {
        return leftButtonPress;
    }

    public boolean isRightButtonPress() {
        return rightButtonPress;
    }

}
