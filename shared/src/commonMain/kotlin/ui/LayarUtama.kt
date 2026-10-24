package ui

import androidx.compose.runtime.Composable
import tampilan.TaskListScreen

/**
 * =========================================================================
 * Tugas 3: Layout Utama & FAB
 * Anggota Penanggung Jawab: Raymond Kolose Nathanael Simbolon (124140186)
 * =========================================================================
 *
 * Mengimplementasikan ketentuan Modul 3 (Langkah terakhir & Checkpoint Minggu 3):
 * 1. Kerangka Layar Utama (Scaffold) di folder `commonMain/ui`.
 * 2. Menyediakan slot untuk FloatingActionButton (FAB) sebagai aksi utama tambah data.
 * 3. Mengintegrasikan daftar tugas (Tugas 2) dan kartu entitas (Tugas 1) ke dalam satu layout utuh.
 */
@Composable
fun LayarUtama(
    onKembali: () -> Unit = {}
) {
    // Memanggil layar daftar tugas dengan kerangka Scaffold dan FAB yang telah dikonfigurasi
    TaskListScreen(onKembali = onKembali)
}
