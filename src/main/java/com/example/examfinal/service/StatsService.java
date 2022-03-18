package com.example.examfinal.service;

import com.example.examfinal.model.view.StatsView;

public interface StatsService {
    void onRequest();
    StatsView getStats();
}
