package io.github.kituin.kungfu.config;

import com.electronwill.nightconfig.core.CommentedConfig;
import com.electronwill.nightconfig.core.Config;
import com.electronwill.nightconfig.core.file.FileConfig;

import com.electronwill.nightconfig.core.io.ReaderInput;
import com.electronwill.nightconfig.toml.TomlParser;
import com.electronwill.nightconfig.toml.TomlWriter;
import com.google.gson.Gson;

import io.github.kituin.kungfu.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.loading.FMLPaths;


import java.io.*;
import java.nio.file.Path;

public class HUDsettings {
    private Gson gson;


    public HUDsettings() {
        FileConfig fileconfig = FileConfig.of(FMLPaths.CONFIGDIR.get().resolve("hud_settings.toml"));

        fileconfig.load();

        fileconfig.set("hello","yes");

        fileconfig.save();

        fileconfig.close();

    }

}
