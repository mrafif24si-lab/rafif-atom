package com.example.rafif_atom

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rafif_atom.RegisterActivity
import com.example.rafif_atom.databinding.ActivityInputEmailBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class InputEmailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInputEmailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputEmailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLanjutRegis.setOnClickListener {
            val email = binding.etEmailOnly.text.toString().trim()

            if (email.isEmpty()) {
                showError("Email tidak boleh kosong!")
            } else if (!email.endsWith("@gmail.com")) {
                showError("Email harus menggunakan domain @gmail.com")
            } else {
                // Valid: Arahkan ke halaman Registrasi bawa data email
                val intent = Intent(this, RegisterActivity::class.java)
                intent.putExtra("EXTRA_EMAIL", email)
                startActivity(intent)
            }
        }
    }

    private fun showError(pesan: String) {
        MaterialAlertDialogBuilder(this)
            .setTitle("Validasi Error")
            .setMessage(pesan)
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .show()
    }
}