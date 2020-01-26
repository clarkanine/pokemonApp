package com.example.listviewdemo.util;


public class VersionGroupManager {
    private static VersionGroupManager instance = null;

    private VersionGroupManager() {} //util

    public static VersionGroupManager instance() {
        if(instance == null) {
            instance = new VersionGroupManager();
        }
        return instance;
    }

    public int getVersionGroupId() {
        return 0;
    }

    public int getPokedexId() {
        return 1;
    }
}
