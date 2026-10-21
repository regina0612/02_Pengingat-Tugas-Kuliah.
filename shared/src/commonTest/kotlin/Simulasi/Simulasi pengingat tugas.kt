
package Simulasi

import data.TaskRepository
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import model.TaskStatus
import kotlin.test.Test
import kotlin.test.assertEquals

class SimulasiPengingatTugas {

    @Test
    fun simulasiPerubahanStatusTugas() = runBlocking {

        // Membuat repository untuk data tugas
        val repository = TaskRepository()

        // Mengambil seluruh perubahan status tugas
        val daftartugas = repository.pantau("T01").toList()

        // Menampilkan hasil simulasi ke console
        for (task in daftartugas) {

            val status = when (task.status) {
                TaskStatus.Pending -> "Pending"
                TaskStatus.InProgress -> "In Progress"
                TaskStatus.Completed -> "Completed"
            }

            println("ID Tugas    : ${task.id}")
            println("Judul       : ${task.title}")
            println("Mata Kuliah : ${task.course.name}")
            println("Deadline    : ${task.deadline}")
            println("Status      : $status")
        }

        // Memastikan ada tiga perubahan status
        assertEquals(3, daftartugas.size)

        // Memastikan urutan status benar
        assertEquals(
            TaskStatus.Pending,
            daftartugas[0].status
        )

        assertEquals(
            TaskStatus.InProgress,
            daftartugas[1].status
        )

        assertEquals(
            TaskStatus.Completed,
            daftartugas[2].status
        )

        println("Pengujian berhasil!")
    }
}
