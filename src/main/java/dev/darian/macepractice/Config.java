package dev.darian.macepractice;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File; import java.io.FileReader; import java.io.FileWriter;

public class Config {
    public boolean hudEnabled = true;
    public double fallingVyThreshold = -0.01;
    public float cooldownReady = 1.0f;
    public int flashDurationTicks = 12;
    public int hudX = 10, hudY = 10;
    public int successWindowTicks = 6;

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static File cfgFile;
    public static Config INSTANCE = new Config();

    public static void load(File gameDir) {
        try {
            File cfgDir = new File(gameDir, "config");
            if (!cfgDir.exists()) cfgDir.mkdirs();
            cfgFile = new File(cfgDir, "macepractice.json");
            if (cfgFile.exists()) try (FileReader r = new FileReader(cfgFile)) {
                INSTANCE = GSON.fromJson(r, Config.class);
            } else save();
        } catch (Exception e) { e.printStackTrace(); }
    }
    public static void save() {
        try (FileWriter w = new FileWriter(cfgFile)) { GSON.toJson(INSTANCE, w); }
        catch (Exception e) { e.printStackTrace(); }
    }
}
