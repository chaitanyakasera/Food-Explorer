package com.cnm.recipeapp.data.model


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class RmMeal(
    @SerializedName("meals")
    val meals: List<Meal>
)