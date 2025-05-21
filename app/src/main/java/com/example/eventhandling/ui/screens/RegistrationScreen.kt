package com.example.eventhandling.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eventhandling.model.Gender
import com.example.eventhandling.model.RegistrationData
import com.example.eventhandling.model.availableClasses
import com.example.eventhandling.model.availableExtracurriculars
import com.example.eventhandling.model.availableTimeSlots
import com.example.eventhandling.ui.components.ClassSpinner
import com.example.eventhandling.ui.components.DatePickerComponent
import com.example.eventhandling.ui.components.ExtracurricularCheckboxes
import com.example.eventhandling.ui.components.FormTextField
import com.example.eventhandling.ui.components.GenderRadioGroup
import com.example.eventhandling.ui.components.TimeSlotSearchView

@Composable
fun RegistrationScreen(
    onSubmit: (RegistrationData) -> Unit
) {
    // Form state
    var name by remember { mutableStateOf("") }
    var nis by remember { mutableStateOf("") }
    var selectedClass by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf<java.time.LocalDate?>(null) }
    var gender by remember { mutableStateOf(Gender.NONE) }
    var selectedExtracurriculars by remember { mutableStateOf(listOf<String>()) }
    var selectedTimeSlot by remember { mutableStateOf("") }
    
    // Scroll state for the form
    val scrollState = rememberScrollState()
    
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(16.dp)
        ) {
            // Header
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                MaterialTheme.colorScheme.primary,
                                MaterialTheme.colorScheme.primaryContainer
                            )
                        )
                    )
                    .padding(24.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.School,
                        contentDescription = "School Icon",
                        tint = Color.White,
                        modifier = Modifier.height(64.dp)
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    Text(
                        text = "SMA MUHAMMADIYAH",
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                    
                    Spacer(modifier = Modifier.height(4.dp))
                    
                    Text(
                        text = "Pendaftaran Ekstrakurikuler",
                        color = Color.White,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Form Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 4.dp
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp)
                ) {
                    Text(
                        text = "Formulir Pendaftaran",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    
                    Spacer(modifier = Modifier.height(20.dp))
                    
                    // Form fields
                    FormTextField(
                        value = name,
                        onValueChange = { name = it },
                        label = "Nama Lengkap"
                    )
                    
                    FormTextField(
                        value = nis,
                        onValueChange = { nis = it },
                        label = "Nomor Induk Siswa (NIS)",
                        keyboardType = KeyboardType.Number
                    )
                    
                    ClassSpinner(
                        selectedClass = selectedClass,
                        onClassSelected = { selectedClass = it },
                        classes = availableClasses
                    )
                    
                    DatePickerComponent(
                        birthDate = birthDate,
                        onDateSelected = { birthDate = it }
                    )
                    
                    GenderRadioGroup(
                        selectedGender = gender,
                        onGenderSelected = { gender = it }
                    )
                    
                    ExtracurricularCheckboxes(
                        selectedExtracurriculars = selectedExtracurriculars,
                        onExtracurricularToggle = { activity, isChecked ->
                            selectedExtracurriculars = if (isChecked) {
                                selectedExtracurriculars + activity
                            } else {
                                selectedExtracurriculars - activity
                            }
                        },
                        extracurriculars = availableExtracurriculars
                    )
                    
                    TimeSlotSearchView(
                        selectedTimeSlot = selectedTimeSlot,
                        onTimeSlotSelected = { selectedTimeSlot = it },
                        timeSlots = availableTimeSlots
                    )
                    
                    Spacer(modifier = Modifier.height(24.dp))
                    
                    // Submit button
                    Button(
                        onClick = {
                            // Create registration data object
                            val registrationData = RegistrationData(
                                name = name,
                                nis = nis,
                                selectedClass = selectedClass,
                                birthDate = birthDate,
                                gender = gender,
                                selectedExtracurriculars = selectedExtracurriculars,
                                activityTimeSlot = selectedTimeSlot
                            )
                            
                            // Pass the data to the callback
                            onSubmit(registrationData)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        enabled = isFormValid(
                            name, nis, selectedClass, birthDate, 
                            gender, selectedExtracurriculars, selectedTimeSlot
                        ),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            disabledContainerColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.3f)
                        )
                    ) {
                        Text(
                            text = "SIMPAN",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

private fun isFormValid(
    name: String,
    nis: String,
    selectedClass: String,
    birthDate: java.time.LocalDate?,
    gender: Gender,
    selectedExtracurriculars: List<String>,
    selectedTimeSlot: String
): Boolean {
    return name.isNotBlank() && 
           nis.isNotBlank() && 
           selectedClass.isNotBlank() && 
           birthDate != null && 
           gender != Gender.NONE && 
           selectedExtracurriculars.isNotEmpty() && 
           selectedTimeSlot.isNotBlank()
} 