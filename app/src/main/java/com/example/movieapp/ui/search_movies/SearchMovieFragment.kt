package com.example.movieapp.ui.search_movies

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.movieapp.R
import kotlinx.android.synthetic.main.fragment_search_movie.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchMovieFragment : Fragment() {

    val viewModel: SearchMovieViewModel by viewModel()
    private val safeArgs : SearchMovieFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search_movie, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSearchInputListener()
        initRecyclerView()
        if (safeArgs.movieName!=null){
            viewModel.searchMovie(safeArgs.movieName!!)
            searchEditText.setText(safeArgs.movieName)


        }
    }


    private fun initRecyclerView(){


        viewModel.pagedMovieList.observe(viewLifecycleOwner, Observer {
                movieList->


            searchMovieRecyclerView.apply {



                adapter= SearchMoviePagedAdapter().apply {


                    submitList(movieList)
                    notifyDataSetChanged()
                }


            }
        })




    }



    private fun initSearchInputListener() {

        textInputLayout.setEndIconOnClickListener {
            doSearch()
        }

        searchEditText.setOnEditorActionListener { _: View, actionId: Int, _: KeyEvent? ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                doSearch()
                true
            } else {
                false
            }
        }

        searchEditText.setOnKeyListener { view: View, keyCode: Int, event: KeyEvent ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                doSearch()
                true
            } else {
                false
            }
        }
    }

    private fun doSearch() {
        closeKeyboard()
        viewModel.searchMovie(searchEditText.text.toString())

    }

    private fun closeKeyboard() {

        val view = activity?.currentFocus
        view?.let { v ->
            val imm =
                activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(v.windowToken, 0)


        }
    }

}