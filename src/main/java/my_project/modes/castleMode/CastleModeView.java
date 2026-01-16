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
                createCredit("Dungeonmode", new String[]{"Joshua Becker", "David Gregory Glusmann"}),
                createCredit("Castlemode", "Joshua Becker"),
                "",
                createCredit("Settings", "David Gregory Glusmann"),
                createCredit("Backend Developer Acces", "Joshua Becker"),
                createCredit("Transitionscreens", "Joshua Becker"),
                createCredit("Grundlegende Struktur", "Joshua Becker"),
                "",
                createCredit("Organisation", new String[]{"David Gregory Glusmann","Joshua Becker"}),
                createCredit("Graphic Design", new String[]{"Joshua Becker", "Tomole Schuhkraft"}),
                createCredit("Sound Design", new String[]{"David Gregory Glusmann"}),
        };
        int size = 50;
        int border = 4;

        double time = modeControl.getTimer();
        double x = 18;
        double y = time * 90 - 1500;
        double lineHeight = 40;
        System.out.println(time + " " + y);
        for(int i = 0; i < credits.length; i++){
            String credit = credits[credits.length - i -1];
            dt.setCurrentColor(Color.black);
            dt.formatText("Arial", 1, size);

            dt.drawText(x-border, y + i*size + i*lineHeight, credit);
            dt.drawText(x+border, y + i*size + i*lineHeight, credit);
            dt.drawText(x, y + i*size-border + i*lineHeight, credit);
            dt.drawText(x, y + i*size+border + i*lineHeight, credit);
            dt.formatText("Arial", 1, size);
            dt.setCurrentColor(Color.white);
            dt.drawText(x, y + i*size + i*lineHeight, credit);
            System.out.println(credit);
        }
    }
    private String createCredit(String role, String[] person){
        String persons = "";
        for(int i = 0; i < person.length; i++){
            if (i+1 == person.length){
                persons += person[i];
            }else persons += person[i] + ", ";


        }
        return role + ": " + persons;
    }
    private String createCredit(String role, String person){
        return createCredit(role, new String[]{person});
    }

    public boolean creditsBelowScreen(){
        return modeControl.getTimer() * 90 - 2000 > Config.WINDOW_HEIGHT;
    }
}
