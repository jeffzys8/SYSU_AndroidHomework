package com.example.lab4;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class AddCartEvent {
    int id;
    AddCartEvent(int i){id = i;}
}
