package com.example.weatherapp.composable
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SearchBar(
    cityText: String,
    onTextChange: (String) -> Unit,
    onSearchClick: () -> Unit,
    suggestions: List<String>
) {
    var isError by remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = cityText,
            onValueChange = {
                onTextChange(it)
                isError = false
                expanded = it.isNotEmpty() && suggestions.isNotEmpty()
            },
            isError = isError,
            label = { Text("Search the city") },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = {
                if (cityText.isEmpty()) {
                    isError = true
                } else {
                    onSearchClick()
                    expanded = false
                }
            }),
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors()
        )


        if (expanded) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent)
                    .border(1.dp, Color.Gray)
                    .heightIn(max = 300.dp) // max height for dropdown
            ) {
                items(suggestions) { suggestion ->
                    Text(
                        text = suggestion,
                        style = TextStyle(
                            fontSize = 18.sp
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onTextChange(suggestion)
                                expanded = false
                                onSearchClick()
                            }
                            .padding(8.dp)
                    )
                }
            }
        }
    }
}
