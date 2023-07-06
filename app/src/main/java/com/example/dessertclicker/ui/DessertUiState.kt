package com.example.dessertclicker.ui

import com.example.dessertclicker.data.Datasource
import com.example.dessertclicker.model.Dessert

data class DessertUiState(
    val currentDessert: Dessert = Datasource.dessertList.first(),
    val revenue: Int = 0,
    val dessertsSold: Int = 0
)
