package com.myname.mymod.config;

import com.google.gson.*;
import com.myname.mymod.MyModConstants;
import java.io.*;
import java.nio.file.*;

public class ConfigAPI<T> {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private Path configPath;
    private final Class<T> configClass;
    private T configData;

    public ConfigAPI(Class<T> configClass) {
        this.configClass = configClass;
    }


    private boolean hadErrorLoading = false;

    public void load() {
        hadErrorLoading = false;
        try {
            if (!Files.exists(configPath)) {
                configData = configClass.getDeclaredConstructor().newInstance();
                save();
            } else {
                try (Reader reader = Files.newBufferedReader(configPath)) {
                    T loaded = GSON.fromJson(reader, configClass);
                    T defaults = configClass.getDeclaredConstructor().newInstance();
                    if (loaded == null) {
                        configData = defaults;
                    }
                    else {
                        JsonObject defaultJson = GSON.toJsonTree(defaults).getAsJsonObject();
                        JsonObject loadedJson = GSON.toJsonTree(loaded).getAsJsonObject();
                        for (String key : loadedJson.keySet()) {
                            defaultJson.add(key, loadedJson.get(key));
                        }
                        configData = GSON.fromJson(defaultJson, configClass);
                    }
                }
            }
            save();
        } catch (Exception e) {
            hadErrorLoading = true;
            MyModConstants.MOD_LOGGER.error("Config: Error loading config-{}: {}", configPath.getFileName(), e.toString());
            try {
                configData = configClass.getDeclaredConstructor().newInstance();
            } catch (Exception ignored) {}
        }
    }

    private boolean hadErrorSaving = false;

    public void save() {
        hadErrorSaving = false;
        try {
            if (configPath.getParent() != null)
                Files.createDirectories(configPath.getParent());
            try (Writer writer = Files.newBufferedWriter(configPath)) {
                GSON.toJson(configData, writer);
            }
        } catch (IOException e) {
            hadErrorSaving = true;
            MyModConstants.MOD_LOGGER.error("Config: Error saving config-{}: {}", configPath.getFileName(), e.toString());
        }
    }

    public boolean isHadErrorLoading() {
        return hadErrorLoading;
    }

    public boolean isHadErrorSaving() {
        return hadErrorSaving;
    }


    public T get() {
        return configData;
    }

    public void reload() {
        load();
    }


    public void register(String configType) {
        this.configPath = Paths.get("config", MyModConstants.MOD_ID+"-"+configType+".json");
        load();
    }

}