package com.example.rafif_atom

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.rafif_atom.Pertemuan3.LoginActivity
import com.example.rafif_atom.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val emailIntent = intent.getStringExtra("EXTRA_EMAIL")
        binding.etEmailTerisi.setText(emailIntent)

        binding.btnSubmitRegis.setOnClickListener {
            val nama = binding.etNama.text.toString().trim()
            val username = binding.etUsernameRegis.text.toString().trim()
            val password = binding.etPasswordRegis.text.toString().trim()

            // Validasi
            if (nama.isEmpty() || username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Semua field wajib diisi!", Toast.LENGTH_SHORT).show()
            } else if (password.length < 6) {
                Toast.makeText(this, "Password minimal 6 karakter!", Toast.LENGTH_SHORT).show()
            } else if (username.contains(" ")) {
                Toast.makeText(this, "Username tidak boleh mengandung spasi!", Toast.LENGTH_SHORT).show()
            } else {
                // Simpan ke SharedPreferences
                val sharedPref = getSharedPreferences("DataUser", Context.MODE_PRIVATE)
                sharedPref.edit().apply {
                    putString("nama", nama)
                    putString("email", emailIntent)
                    putString("username", username)
                    putString("password", password)
                    apply()
                }

                // Tampilkan info berhasil
                Toast.makeText(this, "Registrasi Berhasil!", Toast.LENGTH_LONG).show()

                // Kembali ke Login
                val intent = Intent(this, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP // Menghapus tumpukan activity sebelumnya
                startActivity(intent)
                finish()
            }
        }
    }
}