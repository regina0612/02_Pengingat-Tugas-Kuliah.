package tampilan

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import model.Course
import model.Task
import model.TaskStatus
import ui.components.TaskItemCard
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.material3.TextButton

@Composable
fun TaskListScreen(onKembali: () -> Unit = {})  {

    var kataKunci by remember { mutableStateOf("") }
    var filterStatus by remember { mutableStateOf("Semua") }

    val daftarTugas = remember {
        listOf(
            Task("T01", "Laporan Pengembangan Aplikasi Mobile",
                Course("MK01", "Pengembangan Aplikasi Mobile", "IF25-22017"),
                "25 Okt 2026", TaskStatus.Pending),

            Task("T02", "Membuat UI Aplikasi",
                Course("MK01", "Pengembangan Aplikasi Mobile", "IF25-22017"),
                "26 Okt 2026", TaskStatus.InProgress),

            Task("T03", "Laporan Jaringan Komputer",
                Course("MK02", "Jaringan Komputer", "IF25-22018"),
                "27 Okt 2026", TaskStatus.Completed),

            Task("T04", "Latihan Struktur Data",
                Course("MK03", "Struktur Data", "IF25-22019"),
                "28 Okt 2026", TaskStatus.Pending),

            Task("T05", "Tugas Basis Data",
                Course("MK04", "Basis Data", "IF25-22020"),
                "29 Okt 2026", TaskStatus.InProgress),

            Task("T06", "Praktikum Pemrograman",
                Course("MK05", "Pemrograman", "IF25-22021"),
                "30 Okt 2026", TaskStatus.Pending),

            Task("T07", "Rangkuman Sistem Operasi",
                Course("MK06", "Sistem Operasi", "IF25-22022"),
                "31 Okt 2026", TaskStatus.Completed),

            Task("T08", "Membuat Diagram UML",
                Course("MK07", "Rekayasa Perangkat Lunak", "IF25-22023"),
                "1 Nov 2026", TaskStatus.InProgress),

            Task("T09", "Tugas Matematika Diskrit",
                Course("MK08", "Matematika Diskrit", "IF25-22024"),
                "2 Nov 2026", TaskStatus.Pending),

            Task("T10", "Membuat Presentasi Proyek",
                Course("MK09", "Manajemen Proyek", "IF25-22025"),
                "3 Nov 2026", TaskStatus.Completed)
        )
    }

    val tugasTerfilter = daftarTugas.filter { tugas ->
        val cocokJudul = tugas.title.contains(
            kataKunci, ignoreCase = true
        )

        val cocokStatus = when (filterStatus) {
            "Belum Selesai" ->
                tugas.status == TaskStatus.Pending

            "Sedang Dikerjakan" ->
                tugas.status == TaskStatus.InProgress

            "Selesai" ->
                tugas.status == TaskStatus.Completed

            else -> true
        }

        cocokJudul && cocokStatus
    }

    // =========================================================================
    // Porsi Tugas 3: Layout Utama & FAB (Raymond Kolose Nathanael Simbolon)
    // =========================================================================
    var tampilDialogTambah by remember { mutableStateOf(false) }

    if (tampilDialogTambah) {
        AlertDialog(
            onDismissRequest = { tampilDialogTambah = false },
            title = {
                Text(
                    text = "Tambah Tugas Baru",
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF567568)
                )
            },
            text = {
                Text(
                    text = "Aksi utama penambahan tugas (FAB) berhasil diimplementasikan sesuai ketentuan Modul 3. Formulir input data akan aktif pada modul berikutnya."
                )
            },
            confirmButton = {
                TextButton(onClick = { tampilDialogTambah = false }) {
                    Text("Mengerti", color = Color(0xFF567568), fontWeight = FontWeight.Bold)
                }
            },
            shape = RoundedCornerShape(16.dp),
            containerColor = Color.White
        )
    }

    Scaffold(
        containerColor = Color(0xFFF6F3EA),
        floatingActionButton = {
            FloatingActionButton(
                containerColor = Color(0xFF567568),
                contentColor = Color.White,
                shape = RoundedCornerShape(16.dp),
                elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 4.dp),
                onClick = {
                    // Porsi Tugas 3: FAB siap digunakan
                    tampilDialogTambah = true
                }
            ) {
                Text(
                    text = "+",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            TextButton(
                onClick = onKembali,
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                Text("← Kembali ke TemanTugas", color = Color(0xFF567568), fontWeight = FontWeight.SemiBold)
            }
            Text(
                text = "Daftar Tugas Kuliah",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF567568),
                modifier = Modifier.padding(16.dp)
            )

            TextField(
                value = kataKunci,
                onValueChange = { kataKunci = it },
                label = { Text("Cari tugas...") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                listOf(
                    "Semua",
                    "Belum Selesai",
                    "Sedang Dikerjakan",
                    "Selesai"
                ).forEach { status ->

                    FilterChip(
                        selected = filterStatus == status,
                        onClick = { filterStatus = status },
                        label = { Text(status) }
                    )
                }
            }

            Text(
                text = "${tugasTerfilter.size} tugas ditemukan",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(16.dp)
            )

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 80.dp)
            ) {
                items(
                    items = tugasTerfilter,
                    key = { it.id }
                ) { tugas ->
                    TaskItemCard(task = tugas)
                }
            }

        }
    }
}
