package io.github.kituin.kungfu.config;

import com.electronwill.nightconfig.core.file.FileConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class MiJiConfig {
    public static Path path = FMLPaths.CONFIGDIR.get().resolve("kungfu");
    public static FileConfig fileconfig = FileConfig.of(path.resolve("miji_settings.toml"));
    private static final Logger LOGGER = LogManager.getLogger();
    public MiJiConfig() {
        File dir = new File(String.valueOf(path));
        if (!dir.exists()) {
            dir.mkdir();
        }
        fileconfig.load();
        if (fileconfig.isEmpty()){
            LOGGER.info("create miji default config!");
            this.mijiSet("yijinjing","neigong",5,
                100,100,
                100F,100F,
                0.025F,0F,
                0.4F,0F,
                0F,0F,
                1.0F,0F);
        //System.out.println(fileconfig.valueMap());
            fileconfig.save();
        }
    }
    public void mijiSet(String name ,String type, int level ,
                        int proficiency,int proficiencyPer,
                        double qi,double qiPer,
                        double recuperation,double recuperationPer,
                        double sprintSpeed,double sprintSpeedPer,
                        double healthCureSpeed,double healthCureSpeedPer,
                        double mineSpeed,double mineSpeedPer){
        fileconfig.set(name+".name","kungfu.miji."+name);
        fileconfig.set(name+".type",type);
        fileconfig.set(name+".proficiency",proficiency);
        fileconfig.set(name+".proficiencyPer",proficiencyPer);
        fileconfig.set(name+".qi",qi);
        fileconfig.set(name+".qiPer",qiPer);
        fileconfig.set(name+".recuperation",recuperation);
        fileconfig.set(name+".recuperationPer",recuperationPer);
        fileconfig.set(name+".sprintSpeed",sprintSpeed);
        fileconfig.set(name+".sprintSpeedPer",sprintSpeedPer);
        fileconfig.set(name+".healthCureSpeed",healthCureSpeed);
        fileconfig.set(name+".healthCureSpeedPer",healthCureSpeedPer);
        fileconfig.set(name+".mineSpeed",mineSpeed);
        fileconfig.set(name+".mineSpeedPer",mineSpeedPer);
        fileconfig.set(name+".level",level);
    }

}
