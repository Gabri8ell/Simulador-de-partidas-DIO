package com.example.simulator.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.simulator.databinding.ActivityDetailBinding
import com.example.simulator.domain.Match

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    object Extras {
        const val MATCH = "EXTRA_MATCH"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        loadMatchFromExtra()

    }

    private fun loadMatchFromExtra(){
        intent?.extras?.getParcelable<Match>(Extras.MATCH)?.let {
            Glide.with(this).load(it.place.image).into(binding.ivPlace)
            supportActionBar?.title = it.place.name

            Glide.with(this).load(it.homeTeam.image).into(binding.ivHomeTeam)
            binding.tvHomeTeamName.text = it.homeTeam.name
            binding.rbHomeTeamStars.rating = it.homeTeam.stars.toFloat()

            Glide.with(this).load(it.awayTeam.image).into(binding.ivAwayTeam)
            binding.tvAwayTeamName.text = it.awayTeam.name
            binding.rbAwayTeamStars.rating = it.awayTeam.stars.toFloat()

            binding.tvDescription.text = it.description


            if ((it.homeTeam.score != null) && (it.awayTeam.score != null)){
                binding.tvHomeTeamScore.text = it.homeTeam.score.toString()

                binding.tvAwayTeamScore.text = it.awayTeam.score.toString()
            }
        }
    }
}