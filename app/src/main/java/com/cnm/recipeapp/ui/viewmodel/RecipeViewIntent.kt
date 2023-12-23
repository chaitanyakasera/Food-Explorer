package com.cnm.recipeapp.ui.viewmodel

sealed class RecipeViewIntent {
    object LoadRandomReceipe : RecipeViewIntent()

    data class SearchRecipes(val query: String) : RecipeViewIntent()
}