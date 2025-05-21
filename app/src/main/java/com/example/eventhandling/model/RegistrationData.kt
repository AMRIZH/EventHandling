package com.example.eventhandling.model

import java.time.LocalDate
import java.util.Date

data class RegistrationData(
    val name: String = "",
    val nis: String = "",
    val selectedClass: String = "",
    val birthDate: LocalDate? = null,
    val gender: Gender = Gender.NONE,
    val selectedExtracurriculars: List<String> = emptyList(),
    val activityTimeSlot: String = ""
)

enum class Gender {
    MALE, FEMALE, NONE
}

// List of available extracurricular activities
val availableExtracurriculars = listOf(
    "Basketball",
    "Soccer",
    "Volleyball",
    "Robotics",
    "Science Club",
    "English Club",
    "Chess Club",
    "Art Club",
    "Music Club",
    "Theater Club",
    "Debate Club",
    "Photography Club",
    "Entrepreneurship Club",
    "Computer Programming",
    "Mathematics Club"
)

// List of available classes
val availableClasses = listOf(
    "X MIPA 1",
    "X MIPA 2",
    "X MIPA 3",
    "X IPS 1",
    "X IPS 2",
    "XI MIPA 1",
    "XI MIPA 2",
    "XI MIPA 3",
    "XI IPS 1",
    "XI IPS 2",
    "XII MIPA 1",
    "XII MIPA 2",
    "XII MIPA 3",
    "XII IPS 1",
    "XII IPS 2"
)

// List of available time slots
val availableTimeSlots = listOf(
    "Senin, 14:00 - 16:00",
    "Senin, 16:00 - 18:00",
    "Selasa, 14:00 - 16:00",
    "Selasa, 16:00 - 18:00", 
    "Rabu, 14:00 - 16:00",
    "Rabu, 16:00 - 18:00",
    "Kamis, 14:00 - 16:00",
    "Kamis, 16:00 - 18:00",
    "Jumat, 14:00 - 16:00",
    "Jumat, 16:00 - 18:00",
    "Sabtu, 08:00 - 10:00",
    "Sabtu, 10:00 - 12:00"
) 