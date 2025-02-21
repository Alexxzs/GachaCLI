package com.alexxzs.gachacli;

public class GachaItem {

    private String name;
    private int rarity;

    public GachaItem(String name, int rarity) {
        this.name = name;
        this.rarity = rarity;
    }


    public static final GachaItem[] STANDARD_FIVE_STAR_POOL = { new GachaItem("Qiqi", 5),
            new GachaItem("Dehya", 5),
            new GachaItem("Diluc", 5),
            new GachaItem("Jean", 5),
            new GachaItem("Keqing", 5),
            new GachaItem("Mona", 5),
            new GachaItem("Tighnari", 5)}; //il y a 7 personnages 5* standard

    public static final GachaItem[] LIMITED_FIVE_STAR_POOL = { new GachaItem("Nahida", 5),
            new GachaItem("Mavuika", 5),
            new GachaItem("Zhongli", 5),
            new GachaItem("Venti", 5),
            new GachaItem("Shogun Raiden", 5),
            new GachaItem("Furina", 5)}; // 6 personnages 5* limités

    public static final GachaItem[] FOUR_STAR_POOL = { new GachaItem("Amber", 4),
            new GachaItem("Barbara", 4),
            new GachaItem("Bennett", 4),
            new GachaItem("Fischl", 4),
            new GachaItem("Lisa", 4),
            new GachaItem("Xiangling", 4)}; //il y a 6 personnages 4*

    public static final GachaItem[] THREE_STAR_POOL = { new GachaItem("Basic book", 3),
            new GachaItem("Some great sword", 3),
            new GachaItem("Excellent claymore", 3),
            new GachaItem("Incredible book", 3),
            new GachaItem("Unbelievable spear", 3)}; //il y a 5 armes 3*


    public String getName() {
        return name;
    }

    public int getRarity() {
        return rarity;
    }
}
