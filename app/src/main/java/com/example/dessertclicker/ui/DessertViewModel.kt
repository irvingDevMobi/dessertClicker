package com.example.dessertclicker.ui

import androidx.lifecycle.ViewModel
import com.example.dessertclicker.data.Datasource
import com.example.dessertclicker.model.Dessert
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class DessertViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(DessertUiState())
    val uiState: StateFlow<DessertUiState> get() = _uiState

    fun dessertSold(dessertPrice: Int) {
        _uiState.update { currentState ->
            val newDessertsSold = currentState.dessertsSold.inc()
            val dessertToShow = determineDessertToShow(Datasource.dessertList, newDessertsSold)
            currentState.copy(
                currentDessert = dessertToShow,
                revenue = currentState.revenue + dessertPrice,
                dessertsSold = newDessertsSold
            )
        }
    }

    /**
     * Determine which dessert to show.
     */
    private fun determineDessertToShow(
        desserts: List<Dessert>,
        dessertsSold: Int
    ): Dessert {
        var dessertToShow = desserts.first()
        for (dessert in desserts) {
            if (dessertsSold >= dessert.startProductionAmount) {
                dessertToShow = dessert
            } else {
                // The list of desserts is sorted by startProductionAmount. As you sell more desserts,
                // you'll start producing more expensive desserts as determined by startProductionAmount
                // We know to break as soon as we see a dessert who's "startProductionAmount" is greater
                // than the amount sold.
                break
            }
        }

        return dessertToShow
    }
}
