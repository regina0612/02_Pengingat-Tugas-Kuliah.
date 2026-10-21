package model

// Entitas Pendukung
data class Course(
    val id: String,
    val name: String,
    val code: String
)

// Sealed Class untuk Status Tugas
sealed class TaskStatus {
    object Pending : TaskStatus()
    object InProgress : TaskStatus()
    object Completed : TaskStatus()
}

// Entitas Utama
data class Task(
    val id: String,
    val title: String,
    val course: Course,
    val deadline: String,
    val status: TaskStatus,
    val description: String? = null // Nullable type untuk deskripsi opsional
)
