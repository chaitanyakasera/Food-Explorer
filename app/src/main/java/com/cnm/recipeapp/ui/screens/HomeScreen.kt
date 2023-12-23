package com.cnm.recipeapp.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import com.cnm.recipeapp.ui.components.ErrorComponent
import com.cnm.recipeapp.ui.components.LoadingComponent
import com.cnm.recipeapp.ui.components.SuccessComponent
import com.cnm.recipeapp.ui.viewmodel.RecipeViewIntent
import com.cnm.recipeapp.ui.viewmodel.RecipeViewModel
import com.cnm.recipeapp.ui.viewmodel.RecipeViewState
//Thanks to @YoursSohail
@Composable
fun HomeScreen(viewModel: RecipeViewModel) {
    val state by viewModel.state
    when (state) {
        is RecipeViewState.Loading -> LoadingComponent()
        is RecipeViewState.Success -> {
            val recipes = (state as RecipeViewState.Success).recipes
            SuccessComponent(recipes) {
                viewModel.processIntent(RecipeViewIntent.SearchRecipes(it))
            }
        }

        is RecipeViewState.Error -> {
            val message = (state as RecipeViewState.Error).message
            ErrorComponent(message = message) {
                viewModel.processIntent(RecipeViewIntent.LoadRandomReceipe)
            }
        }
    }
    LaunchedEffect(key1 = Unit, block = {
        viewModel.processIntent(RecipeViewIntent.LoadRandomReceipe)
    }
    )
}