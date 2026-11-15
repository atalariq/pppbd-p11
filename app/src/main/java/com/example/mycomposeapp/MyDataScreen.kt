package com.example.mycomposeapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mycomposeapp.ui.theme.MyComposeAppTheme

@Composable
fun MyDataApp(
    viewModel: MyDataViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    MyDataScreen(
        uiState = uiState,
        onNameChange = { viewModel.onNameChange(it) },
        onAgeChange = { viewModel.onAgeChange(it) }
    )
}

@Composable
fun MyDataScreen(
    uiState: MyData,
    onNameChange: (String) -> Unit,
    onAgeChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Form Data Diri",
            style = MaterialTheme.typography.headlineMedium
        )

        OutlinedTextField(
            value = uiState.name,
            onValueChange = onNameChange,
            label = { Text("Masukkan Nama") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = uiState.age,
            onValueChange = onAgeChange,
            label = { Text("Masukkan Umur") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        ) {
            Text(
                text = "Nama saya \"${uiState.name}\" dan umur saya \"${uiState.age}\", Salam kenal!",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MyDataScreenPreview() {
    MyComposeAppTheme {
        MyDataScreen(
            uiState = MyData(name = "Atalariq Barra Hadinugraha", age = "20"),
            onNameChange = {},
            onAgeChange = {}
        )
    }
}
