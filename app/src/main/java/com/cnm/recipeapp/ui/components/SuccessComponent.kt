package com.cnm.recipeapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cnm.recipeapp.data.model.Meal

@Composable
fun SuccessComponent(
    recipes: List<Meal>,
    onSearchClick: (query: String) -> Unit
) {
    Column {
        Text(
            text = "Recipe Explorer",
            fontWeight = FontWeight(900),
            fontFamily = FontFamily.Cursive,
            fontSize = 32.sp,
            modifier = Modifier.padding(8.dp)
        )
        SearchComponent(click = onSearchClick)
        RecipeList(recipes = recipes)
    }

}

@Composable
fun SearchComponent(click: (query: String) -> Unit) {
    var query by remember {
        mutableStateOf("")
    }
    var errorMessage by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = query,
            onValueChange = {
                if (it.isNotBlank()) {
                    errorMessage = ""
                }
                query = it
            }, label = { Text(text = "Search") },
            singleLine = true,
            isError = errorMessage.isNotBlank(),
            trailingIcon = {
                IconButton(onClick = {
                    if (query.isNotBlank()) {
                        click(query)
                    } else {
                        errorMessage = "Enter a query first"
                    }
                }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = Color.Gray
                    )
                }
            }
        )
    }
    if (errorMessage.isNotBlank()) {
        Text(
            text = errorMessage,
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier.padding(16.dp)
        )
    }
}
