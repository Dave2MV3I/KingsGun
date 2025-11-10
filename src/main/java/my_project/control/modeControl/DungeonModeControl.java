package my_project.control.modeControl;

import my_project.model.DungeonMode.Dungeon;

public class DungeonModeControl extends ModeControl {
    Dungeon dungeon;
    @Override
    protected void deactivate() {
    }
    @Override
    protected void activate() {
    }
    public void setDungeon(Dungeon dungeon){
        this.dungeon = dungeon;
    }
    public Dungeon getDungeon(){
        return dungeon;
    }
}
