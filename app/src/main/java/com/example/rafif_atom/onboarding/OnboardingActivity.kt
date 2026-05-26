package com.example.rafif_atom.onboarding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rafif_atom.databinding.ActivityOnboardingBinding

class OnboardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragmentsList = listOf(
            Onboarding1Fragment(),
            Onboarding2Fragment(),
            Onboarding3Fragment()
        )

        val adapter = OnboardingAdapter(this, fragmentsList)
        binding.viewPagerOnboarding.adapter = adapter
        binding.dotsIndicator.attachTo(binding.viewPagerOnboarding)
    }
}