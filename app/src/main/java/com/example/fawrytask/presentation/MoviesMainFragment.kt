package com.example.fawrytask.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.entites.MoviesWithGenre
import com.example.fawrytask.R
import com.example.fawrytask.common.ClickListenerMovies
import com.example.fawrytask.common.Constants.MOVIE_DATA
import com.example.fawrytask.databinding.MoviesGenreListBinding
import com.example.fawrytask.presentation.adapter.MoviesGenreAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesMainFragment : Fragment(), ClickListenerMovies {

    private lateinit var binding: MoviesGenreListBinding
    private lateinit var navController: NavController
    private lateinit var moviesGenreAdapter: MoviesGenreAdapter
    private lateinit var moviesLS: List<MoviesWithGenre>

    private val mainViewModel by viewModels<MoviesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MoviesGenreListBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        mainViewModel.getMovies()
        mainViewModel.dataLoading.observe(viewLifecycleOwner) { loading ->
            when (loading) {
                true -> binding.progress.visibility = View.VISIBLE
                false -> binding.progress.visibility = View.GONE
            }
        }

        mainViewModel.moviesLv.observe(viewLifecycleOwner) { movies ->
            moviesLS = movies
            moviesGenreAdapter = MoviesGenreAdapter(activity!!, movies)
            moviesGenreAdapter.setClickListener(MoviesMainFragment@ this)
            binding.moviesGenreRec.apply {
                layoutManager = LinearLayoutManager(activity)
                adapter = moviesGenreAdapter

            }

        }

        mainViewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(
                requireContext(),
                it,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun click(view: View, positionGenre: Int, positionMovie: Int) {

        when (view.id) {

            R.id.main -> {
                val bundle = bundleOf(MOVIE_DATA to moviesLS[positionGenre].movies[positionMovie])
                navController.navigate(R.id.action_move_to_details, bundle)
            }
        }
    }
}