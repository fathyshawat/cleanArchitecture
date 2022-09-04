package com.example.fawrytask.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entites.MovieInfo
import com.example.fawrytask.common.ClickListenerMovies
import com.example.fawrytask.common.Constants
import com.example.fawrytask.databinding.ItemMovieBinding
import com.squareup.picasso.Picasso

class MovieAdapter(private val items: List<MovieInfo>, private val posGenre: Int) :
    RecyclerView.Adapter<MovieAdapter.MoviesViewHolder>() {
    private var viewClick: ClickListenerMovies? = null

    class MoviesViewHolder(private val itemBinding: ItemMovieBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(movieInfo: MovieInfo, posGenre: Int, viewClick: ClickListenerMovies?) {
            val url = Constants.URL_IMG + movieInfo.backdropPath
            url?.let {
                Picasso.get().load(url).into(itemBinding.movieImage)
            }
            itemBinding.main.setOnClickListener { v ->
                viewClick?.click(v, posGenre, adapterPosition)

            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val itemBinding =
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(items[position], posGenre, viewClick)
    }

    override fun getItemCount(): Int = items.size

    fun setClickListener(clickListener: ClickListenerMovies) {
        viewClick = clickListener
    }


}