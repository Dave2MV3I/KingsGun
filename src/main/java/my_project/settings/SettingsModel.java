package my_project.settings;

/**
 * SettingModel is a container class for all settings in the game. <br>
 * David Glusmann
 */
public class SettingsModel {

    public SettingsModel() {

    }

    // sound
    private static float _soundVolume; // 0-1
    private static float _musicVolume; // 0-1

    // image
    private static float _brightness = 1; // 0-1

    // getter and setter methods
    public static float getSoundVolume() {return _soundVolume;}

    public static void setSoundVolume(float newSoundVolume) {_soundVolume = newSoundVolume;}

    public static float getMusicVolume() {return _musicVolume;}

    public static void setMusicVolume(float newMusicVolume) {_musicVolume = newMusicVolume;}

    public static float getBrightness() {return _brightness;}

    public static void setBrightness(float newBrightness) {_brightness = newBrightness;}
}
