package com.example.myapplication.bean;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class SteamOvenViewModel extends ViewModel {
    /**
     * 电源板状态
     */
    private SteamOven steamOven = SteamOven.getInstance();

    public SteamOven getSteamOven() {
        return steamOven;
    }
}
