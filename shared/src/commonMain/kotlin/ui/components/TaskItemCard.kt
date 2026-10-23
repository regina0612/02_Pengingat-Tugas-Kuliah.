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

// Card UI untuk nampilin 1 item tugas kuliah
@Composable
fun TaskItemCard(task: Task) {
    // Nentuin label & warna badge berdasarkan status tugas dari sealed class Modul 2
    val (statusLabel, statusColor) = when (task.status) {
        TaskStatus.Completed -> Pair("Selesai", Color(0xFF4CAF50))       // Hijau kalau udah beres
        TaskStatus.Pending -> Pair("Belum Selesai", Color(0xFFFFA000))   // Kuning/Oranye kalau pending
        TaskStatus.InProgress -> Pair("Terlambat", Color(0xFFF44336))    // Merah kalau terlambat/dalam proses
    }

    // Komponen utama berupa Card dengan sudut melengkung dan sedikit bayangan
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Judul tugas
            Text(
                text = task.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF212121)
            )

            // Nama matkul (diambil dari objek Course)
            Text(
                text = task.course.name,
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF757575)
            )

            // Info deadline tugas
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

            // Garis pembatas
            HorizontalDivider()

            // Badge status tugas di pojok kanan bawah
            Row(
                horizontalArrangement = Arrangement.End,
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