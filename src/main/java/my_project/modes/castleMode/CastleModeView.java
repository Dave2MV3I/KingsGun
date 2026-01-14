package my_project.modes.castleMode;

import KAGO_framework.view.DrawTool;
import my_project.Config;
import my_project.model.Graphics.Texture;
import my_project.modes.ModeView;

import java.awt.*;

public class CastleModeView extends ModeView<CastleModeControl> {
    private Texture texture = new Texture("castle Screen.png");
    public CastleModeView(CastleModeControl modeControl) {
        super(modeControl);
    }
    @Override
    public void draw(DrawTool dt){
        texture.drawToWidth(dt, 0, 0, Config.WINDOW_WIDTH);
        drawCredits(dt);
    }
    private void drawCredits(DrawTool dt) {
        String[] credits = new String[]{
                createCredit("Startmode", "Joshua Becker"),
                createCredit("Mapmode", "Tomole Schuhkraft"),
                createCredit("Travelmode", "Mykhailo Badasian"),
                createCredit("Villagemode", "Tomole Schuhkraft"),
                createCredit("Dungeonmode", new String[]{"Joshua Becker", "David Greggory Glussman"}),
                createCredit("Castlemode", "Joshua Becker"),
                createCredit("Settings", "David Greggory Glussman"),
                createCredit("Backend Developer Acces", "Joshua Becker"),
                createCredit("Transitionscreens", "Joshua Becker"),
                createCredit("Grundlegende Struktur", "Joshua Becker"),
                createCredit("Organisation", "David Greggory Glussman"),
        };
        int size = 50;
        int border = 4;

        for(int i = 0; i < credits.length; i++){
            String credit = credits[i];
            dt.setCurrentColor(Color.black);
            dt.formatText("Arial", 1, size);

            dt.drawText(10-border, i*size, credit);
            dt.drawText(10+border, i*size, credit);
            dt.drawText(10, i*size-border, credit);
            dt.drawText(10, i*size+border, credit);
            dt.formatText("Arial", 1, size);
            dt.setCurrentColor(Color.white);
            dt.drawText(10, i*size, credit);
        }
    }
    private String createCredit(String role, String[] person){
        String persons = "";
        for(int i = 0; i < person.length; i++){
            persons += person[i] + ", ";
        }
        return role + ": " + person[0];
    }
    private String createCredit(String role, String person){
        return createCredit(role, new String[]{person});
    }

}
