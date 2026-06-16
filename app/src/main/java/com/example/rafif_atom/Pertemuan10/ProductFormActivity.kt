package com.example.rafif_atom.Pertemuan10

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.rafif_atom.data.AppDatabase
import com.example.rafif_atom.data.entity.ProductEntity
import com.example.rafif_atom.databinding.ActivityProductFormBinding
import com.example.rafif_atom.utils.NotificationHelper
import com.example.rafif_atom.utils.PermissionHelper
import com.example.rafif_atom.utils.ReminderHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Calendar

class ProductFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductFormBinding
    private lateinit var db: AppDatabase

    // Launcher untuk meminta izin notifikasi dari pengguna
    private val notificationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                Toast.makeText(this, "Notifikasi UMKM diizinkan", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Notifikasi UMKM ditolak", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getInstance(this)

        // 1. Cek dan minta izin Notifikasi saat halaman form dibuka
        if (PermissionHelper.isNotificationPermissionRequired()) {
            val permission = Manifest.permission.POST_NOTIFICATIONS
            if (!PermissionHelper.hasPermission(this, permission)) {
                PermissionHelper.requestPermission(
                    notificationPermissionLauncher,
                    permission
                )
            }
        }

        binding.btnSaveProduct.setOnClickListener {
            val name = binding.etProductName.text.toString()
            val price = binding.etProductPrice.text.toString()
            val desc = binding.etProductDesc.text.toString()

            if (name.isNotBlank() && price.isNotBlank() && desc.isNotBlank()) {
                lifecycleScope.launch(Dispatchers.IO) {
                    val product = ProductEntity(
                        name = name,
                        price = price,
                        description = desc
                    )
                    db.productDao().insertProduct(product)

                    withContext(Dispatchers.Main) {
                        // 2. Tampilkan Local Notification secara instan
                        val intent = Intent(this@ProductFormActivity, UmkmCatalogActivity::class.java)
                        NotificationHelper.showNotification(
                            this@ProductFormActivity,
                            "Produk Berhasil Disimpan! \uD83D\uDCE6",
                            "Produk $name telah berhasil ditambahkan ke katalog UMKM.",
                            intent
                        )

                        // 3. Set Reminder untuk 1 menit ke depan
                        val calendar = Calendar.getInstance().apply {
                            add(Calendar.MINUTE, 1) // Tambah 1 menit dari waktu simpan
                        }

                        ReminderHelper.setReminder(
                            context = this@ProductFormActivity,
                            hour = calendar.get(Calendar.HOUR_OF_DAY),
                            minute = calendar.get(Calendar.MINUTE),
                            title = "Pengingat Stok UMKM \uD83D\uDCDD",
                            message = "Jangan lupa untuk memantau ketersediaan stok produk $name Anda hari ini.",
                            targetActivity = UmkmCatalogActivity::class.java
                        )

                        Toast.makeText(this@ProductFormActivity, "Produk disimpan! Tunggu 1 menit untuk reminder.", Toast.LENGTH_LONG).show()

                        // Tutup halaman setelah selesai
                        finish()
                    }
                }
            } else {
                Toast.makeText(this, "Isi semua detail produk!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}