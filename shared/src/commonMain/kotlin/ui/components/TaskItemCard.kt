package ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import model.Task
import model.TaskStatus

/**
 * Composable [TaskItemCard] menampilkan satu kartu tugas (Task) dalam bentuk Card.
 *
 * Digunakan untuk menampilkan detail tugas kepada pengguna, yaitu:
 * - Judul tugas
 * - Nama mata kuliah
 * - Tanggal/Deadline pengumpulan
 * - Badge status tugas dengan warna latar sesuai kondisi (Selesai / Belum Selesai / Terlambat)
 *
 * @param task objek data [Task] yang berisi informasi tugas lengkap (dari Modul 2)
 */
@Composable
fun TaskItemCard(task: Task) {
    // =========================================================================
    // 1. Tentukan warna dan teks badge berdasarkan sealed class TaskStatus.
    //    Karena TaskStatus adalah sealed class, saat menggunakan 'when' Kotlin
    //    memaksa kita menangani semua kemungkinan (Pending, InProgress, Completed)
    //    agar kompilasi berhasil. Ini menjamin tidak ada status yang tertinggal.
    // =========================================================================
    val (statusLabel, statusColor) = when (task.status) {
        // Task sudah selesai dikerjakan -> tampilkan "Selesai" dengan warna hijau
        TaskStatus.Completed -> Pair("Selesai", Color(0xFF4CAF50))
        // Task masih menunggu dikerjakan -> tampilkan "Belum Selesai" dengan warna kuning/oranye
        TaskStatus.Pending -> Pair("Belum Selesai", Color(0xFFFFA000))
        // Task sedang dikerjakan / terlambat -> tampilkan "Terlambat" dengan warna merah
        TaskStatus.InProgress -> Pair("Terlambat", Color(0xFFF44336))
    }

    // =========================================================================
    // 2. Card adalah komponen Material3 yang membuat kotak dengan sudut melengkung
    //    dan bayangan (elevasi), cocok untuk menampilkan satu entitas tugas
    //    terpisah dari tugas lainnya. Kita bungkus isi dengan Column agar tiap
    //    informasi (judul, mata kuliah, deadline, badge) tertata vertikal.
    // =========================================================================
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        // elevation memberikan efek bayangan agar kartu terlihat "terapung"
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        // shape menentukan sudut kartu; RoundedCornerShape(12.dp) membuat sudut melengkung
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            // -----------------------------------------------------------------
            // Judul tugas: ditampilkan dengan teks tebal dan ukuran besar agar
            // informasi paling penting terlihat terlebih dahulu.
            // -----------------------------------------------------------------
            Text(
                text = task.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF212121)
            )

            // -----------------------------------------------------------------
            // Nama mata kuliah: diambil dari task.course (objek Course dari
            // Modul 2). Ditampilkan dengan warna abu-abu agar tidak bersaing
            // visually dengan judul.
            // -----------------------------------------------------------------
            Text(
                text = task.course.name,
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF757575)
            )

            // -----------------------------------------------------------------
            // Tanggal/Deadline: ditampilkan dengan ikon kalender kecil di depan
            // agar pengguna segera mengenal ini adalah batas waktu pengumpulan.
            // -----------------------------------------------------------------
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "📅 Deadline: ${task.deadline}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF616161)
                )
            }

            // -----------------------------------------------------------------
            // Divider pemisah antar informasi utama dan badge status, membuat
            // tampilan kartu lebih rapi dan terstruktur.
            // -----------------------------------------------------------------
            HorizontalDivider()

            // -----------------------------------------------------------------
            // Badge/Label status: kotak kecil di pojok kanan bawah kartu yang
            // menunjukkan kondisi tugas. Kita pakai Box sebagai kontainer dengan
            // warna latar (background) sesuai status, lalu letakkan teks label
            // di tengah Box-nya.
            // -----------------------------------------------------------------
            Row(
                horizontalArrangement = Arrangement.End, // dorong badge ke pojok kanan
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .padding(vertical = 4.dp)
                        .background(color = statusColor, shape = RoundedCornerShape(8.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = statusLabel,
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    )
                }
            }
        }
    }
}
