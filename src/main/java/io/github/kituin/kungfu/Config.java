package io.github.kituin.kungfu;


import net.minecraftforge.common.ForgeConfigSpec;

public class Config {
    public static ForgeConfigSpec COMMON_CONFIG;
    public static ForgeConfigSpec.IntValue VALUE;
    public static ForgeConfigSpec.DoubleValue QIHUDX ;
    public static ForgeConfigSpec.DoubleValue QIHUDY ;
    static {

        ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
        COMMON_BUILDER.comment("General settings").push("general");
        VALUE = COMMON_BUILDER.comment("Test config value").defineInRange("value", 0, 0, Integer.MAX_VALUE);
        COMMON_BUILDER.pop();
        COMMON_BUILDER.comment("Position settings").push("position");
        QIHUDX = COMMON_BUILDER.comment("Qi HUD").defineInRange("qihudx", 0, 0, Double.MAX_VALUE);
        QIHUDY = COMMON_BUILDER.defineInRange("qihudy", 0, 0, Double.MAX_VALUE);
        COMMON_BUILDER.pop();
        COMMON_CONFIG = COMMON_BUILDER.build();
    }

}