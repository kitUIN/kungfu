package io.github.kituin.kungfu.config;

import com.electronwill.nightconfig.core.file.FileConfig;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.File;
import java.nio.file.Path;


public class MiJiConfig {
    public static Path path = FMLPaths.CONFIGDIR.get().resolve("kungfu");
    public static FileConfig fileconfig = FileConfig.of(path.resolve("miji_settings.toml"));
    public MiJiConfig() {
        File dir = new File(String.valueOf(path));
        if (!dir.exists()) {// 判断目录是否存在
            dir.mkdir();
        }
        fileconfig.load();
        this.mijiSet("yijinjing",5,
                100,100,
                100,100,
                0.025F,0F,
                0.4F,0F,
                0F,0F,
                1.0F,0F);
        //System.out.println(fileconfig.valueMap());
        fileconfig.save();
        fileconfig.close();
    }
    public void mijiSet(String name , int level ,
                        int proficiency,int proficiencyPer,
                        double qi,double qiPer,
                        double recuperation,double recuperationPer,
                        double sprintSpeed,double sprintSpeedPer,
                        double healthCureSpeed,double healthCureSpeedPer,
                        double mineSpeed,double mineSpeedPer){
        fileconfig.set(name+".name","kungfu.miji."+name);
        fileconfig.set(name+".level",level);
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
    }

}
