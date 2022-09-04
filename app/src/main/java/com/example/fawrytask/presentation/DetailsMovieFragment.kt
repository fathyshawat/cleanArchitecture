package com.example.fawrytask.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.domain.entites.MovieInfo
import com.example.domain.entites.MoviesWithGenre
import com.example.fawrytask.common.Constants
import com.example.fawrytask.databinding.MovieDetailsBinding
import com.squareup.picasso.Picasso

class DetailsMovieFragment : Fragment() {

    private lateinit var binding: MovieDetailsBinding
    private lateinit var movieInfo: MovieInfo

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MovieDetailsBinding.inflate(inflater, container, false)

        movieInfo = arguments!!.getParcelable(Constants.MOVIE_DATA)!!

        binding.desc.text = movieInfo.overview
        binding.title.text = movieInfo.title
        binding.year.text = movieInfo.year
        val urlImg = Constants.URL_IMG + movieInfo.backdropPath
        urlImg.let {
            Picasso.get().load(urlImg)
                .into(binding.poster)
        }

        return binding.root
    }
}