package my_project.model.Graphics;

import KAGO_framework.model.*;
import KAGO_framework.view.*;
import my_project.view.*;

public class Texture extends GraphicalObject {
    /** Creates a Texture Object
     *
     * @param texture path to the Imagefile relative from src/main/resources/graphic/
     */
    public Texture(String texture) {
        setNewImage("src/main/resources/graphic/" + texture);
    }
    /** create a Texture without Image (Only for easier implementation of Spritesheet)
     */
    protected Texture() {}

    public void draw(DrawTool drawTool, double x, double y, double scale) {
        drawTool.drawTransformedImage(getMyImage(), x, y, 0, scale);
    }

    public void autoDraw(DrawTool drawTool, double x, double y, double width, double rotation) {
        //draw(drawTool, x, y, 1);
        drawTool.drawTransformedImage(getMyImage(), MainView.translateAndScaleX(x), MainView.translateAndScaleY(y), rotation, MainView.scale(width/this.width));

    }
    public void autoDraw(DrawTool drawTool, double x, double y, double width) {
        //draw(drawTool, x, y, 1);
        autoDraw(drawTool, x, y, width, 0);
    }
    public void autoDraw(DrawTool drawTool, double x, double y) {
        autoDraw(drawTool, x, y, this.width, 0);
    }

    public void drawToWidth(DrawTool drawTool, double x, double y, double width) {
        drawTool.drawTransformedImage(getMyImage(), x, y, 0, width/this.width);
    }

    public double getHeightRelativeToWidth(double width){
        return getMyImage().getHeight()*(width/this.width);
    }

    public double getScaleRelativeToWidth(double width){
            return width/this.width;
    }
}
