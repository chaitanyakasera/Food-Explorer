package com.cnm.recipeapp.data.network

import com.cnm.recipeapp.data.model.Meal
import com.cnm.recipeapp.data.model.RmMeal
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json

object MealApiClient {
    private val apiClient = HttpClient(CIO) {
        install(ContentNegotiation) {
            json()
        }
    }

    suspend fun getRandomRecipe(): List<Meal> {
        val url = "https://www.themealdb.com/api/json/v1/1/random.php"
        val response = apiClient.get(url).body() as RmMeal
        return response.meals
    }

    suspend fun getSearchedRecipe(queryText: String): List<Meal> {
        val url = "https://www.themealdb.com/api/json/v1/1/search.php?s=$queryText"
        val response = apiClient.get(url).body() as RmMeal
        return response.meals
    }

}