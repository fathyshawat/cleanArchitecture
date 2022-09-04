package com.example.fawrytask.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entites.MoviesWithGenre
import com.example.fawrytask.common.ClickListenerMovies
import com.example.fawrytask.databinding.ItemMoviesGenreBinding

class MoviesGenreAdapter(private val context: Context, private val items: List<MoviesWithGenre>) :
    RecyclerView.Adapter<MoviesGenreAdapter.MoviesViewHolder>() {
    private var viewClick: ClickListenerMovies? = null

    class MoviesViewHolder(private val itemBinding: ItemMoviesGenreBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(
            context: Context,
            moviesWithGenre: MoviesWithGenre,
            viewClick: ClickListenerMovies
        ) {
            itemBinding.genre.text = moviesWithGenre.name
            val adapterMovieAdapter = MovieAdapter(moviesWithGenre.movies, adapterPosition)
            itemBinding.recyclerMovies.adapter = adapterMovieAdapter
            itemBinding.recyclerMovies.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true)
            adapterMovieAdapter.setClickListener(viewClick)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val itemBinding =
            ItemMoviesGenreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(context, items[position], viewClick!!)
    }

    override fun getItemCount(): Int = items.size

    fun setClickListener(clickListener: ClickListenerMovies) {
        viewClick = clickListener
    }


}