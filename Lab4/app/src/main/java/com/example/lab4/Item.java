package com.example.lab4;

import java.util.LinkedList;

public class Item{
    String name;
    double price;
    String infoType;
    String info;
    int picture_id;
    boolean star;
    Item(String n, double p, String t, String i, int pid){
        name = n;
        price = p;
        infoType = t;
        info = i;
        picture_id = pid;
        star = false;
    }

    static Item[] items = {
            new Item("Enchated Forest", 5.00, "作者", "Johanna Basford",R.drawable.enchated_forest),
            new Item("Arla Milk", 59.00, "产地", "德国",R.drawable.arla),
            new Item("Devondale Milk", 79.00, "产地", "澳大利亚",R.drawable.devondale),
            new Item("Kindle Oasis", 2399.00, "版本", "8GB",R.drawable.kindle),
            new Item("waitrose 早餐麦片", 179.00, "重量", "2Kg",R.drawable.waitrose),
            new Item("Mcvitie's 饼干", 14.90, "产地", "英国",R.drawable.mcvitie),
            new Item("Ferrero Rocher", 132.59, "重量", "300g",R.drawable.ferrero),
            new Item("Maltesers", 141.43, "重量", "118g",R.drawable.maltesers),
            new Item("Lindt", 139.43, "重量", "249g",R.drawable.lindt),
            new Item("Borggreve", 28.90, "重量", "640g",R.drawable.borggreve)

    };

    //商品列表
    static LinkedList<Integer> all = new LinkedList<Integer>();
    //购物车列表
    static LinkedList<Integer> cart = new LinkedList<Integer>();
}