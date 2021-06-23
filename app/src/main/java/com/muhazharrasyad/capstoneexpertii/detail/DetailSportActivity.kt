package com.muhazharrasyad.capstoneexpertii.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.muhazharrasyad.capstoneexpertii.R
import com.muhazharrasyad.capstoneexpertii.core.domain.model.Sport
import com.muhazharrasyad.capstoneexpertii.databinding.ActivityDetailSportBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailSportActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private val detailSportViewModel: DetailSportViewModel by viewModel()
    private lateinit var binding: ActivityDetailSportBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailSportBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val detailSport = intent.getParcelableExtra<Sport>(EXTRA_DATA)
        showDetailSport(detailSport)
    }

    private fun showDetailSport(detailSport: Sport?) {
        detailSport?.let {
            supportActionBar?.title = detailSport.strSport
            binding.content.tvDetailDescription.text = detailSport.strSportDescription
            Glide.with(this@DetailSportActivity)
                .load(detailSport.strSportThumb)
                .into(binding.ivDetailImage)

            var statusFavorite = detailSport.isFavorite
            setStatusFavorite(statusFavorite)
            binding.fab.setOnClickListener {
                statusFavorite = !statusFavorite
                detailSportViewModel.setFavoriteSport(detailSport, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_white))
        } else {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_not_favorite_white))
        }
    }
}
