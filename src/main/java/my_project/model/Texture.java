package my_project.model;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;
import my_project.view.MainView;

public class Texture extends GraphicalObject {
    /** CLASS IS CURRENTLY INCOMPLETE! DO NOT USE YET please :)
     *
     * @param texture path to the Imagefile
     */
    public Texture(String texture) {
        setNewImage(texture);
    }
    /** CLASS IS CURRENTLY INCOMPLETE! DO NOT USE YET please :)
     */
    protected Texture() {}

    public void draw(DrawTool drawTool, double x, double y, double scale) {
        drawTool.drawTransformedImage(getMyImage(), x, y, 0, scale);
    }

    public void autoDraw(DrawTool drawTool, double x, double y, double width) {
        drawTool.drawTransformedImage(getMyImage(), MainView.translateAndScaleX(x), MainView.translateAndScaleY(y), 0, MainView.scale(width/getMyImage().getWidth()));
    }

    public void drawToWidth(DrawTool drawTool, double x, double y, double width) {
        drawTool.drawTransformedImage(getMyImage(), x, y, 0, width/getMyImage().getWidth());
    }

    public double getHeightRelativeToWidth(double width){
        return getMyImage().getHeight()*(width/getMyImage().getWidth());
    }
}
