package com.example.listviewdemo.enums;

import java.util.Arrays;
import java.util.EnumMap;

public enum EMoveLearnMethod {
    LEVEL_UP(1),
    EGG(2),
    TUTOR(3),
    MACHINE(4),
    STADIUM_SURFING_PIKACHU(5),
    LIGHT_BALL_EGG(6),
    COLOSSEUM_PURIFICATION(7),
    XD_SHADOW(8),
    XD_PURIFICATION(9),
    FORM_CHANGE(10);

    private final int id;

    EMoveLearnMethod(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static EMoveLearnMethod getFromId(int id) {
        return Arrays.asList(EMoveLearnMethod.values()).stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .get();
    }
//    id,identifier
//1,level-up
//2,egg
//3,tutor
//4,machine
//5,stadium-surfing-pikachu
//6,light-ball-egg
//7,colosseum-purification
//8,xd-shadow
//9,xd-purification
//10,form-change
}
