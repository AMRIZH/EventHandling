package com.example.eventhandling

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.eventhandling.model.RegistrationData
import com.example.eventhandling.ui.screens.DetailsScreen
import com.example.eventhandling.ui.screens.RegistrationScreen
import com.example.eventhandling.ui.theme.EventHandlingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EventHandlingTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ExtracurricularRegistrationApp()
                }
            }
        }
    }
}

@Composable
fun ExtracurricularRegistrationApp() {
    var registrationData by remember { mutableStateOf<RegistrationData?>(null) }
    
    val navController = rememberNavController()
    
    NavHost(
        navController = navController,
        startDestination = "registration"
    ) {
        composable("registration") {
            RegistrationScreen(
                onSubmit = { data ->
                    registrationData = data
                    navController.navigate("details")
                }
            )
        }
        
        composable("details") {
            // Only show details screen if we have registration data
            registrationData?.let { data ->
                DetailsScreen(
                    registrationData = data,
                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}