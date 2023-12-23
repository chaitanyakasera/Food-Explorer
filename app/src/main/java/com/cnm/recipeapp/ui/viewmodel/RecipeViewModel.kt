package com.cnm.recipeapp.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cnm.recipeapp.data.network.MealApiClient
import kotlinx.coroutines.launch

class RecipeViewModel : ViewModel() {

    private val _state = mutableStateOf<RecipeViewState>(RecipeViewState.Loading)
    val state: State<RecipeViewState> = _state

    fun processIntent(intent: RecipeViewIntent) {
        when (intent) {
            is RecipeViewIntent.LoadRandomReceipe -> loadRandomRecipe()
            is RecipeViewIntent.SearchRecipes -> searchRecipe(intent.query)
        }
    }

    private fun searchRecipe(query: String) {
        viewModelScope.launch {
            _state.value = RecipeViewState.Loading
            try {
                _state.value = RecipeViewState.Success(
                    MealApiClient.getSearchedRecipe(query)
                )
            } catch (e: Exception) {
                Log.e("TAG", "searchRecipe: ${e.message}")
                _state.value = RecipeViewState.Error("Error fetching data")
            }
        }
    }

    private fun loadRandomRecipe() {
        viewModelScope.launch {
            _state.value = RecipeViewState.Loading
            try {
                _state.value = RecipeViewState.Success(
                    MealApiClient.getRandomRecipe()
                )
            } catch (e: Exception) {
                Log.e("TAG", "loadRandomRecipe: ${e.message}")
                _state.value = RecipeViewState.Error("Error Fetching Recipe")
            }
        }
    }


}