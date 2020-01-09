package com.example.movieapp.ui.discover


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.movieapp.R
import com.example.movieapp.databinding.DiscoverFragmentBinding
import com.example.movieapp.model.Result
import org.koin.androidx.viewmodel.ext.android.viewModel

class DiscoverFragment : Fragment() {
    val viewModel: DiscoverViewModel by viewModel()

    private lateinit var binding: DiscoverFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= DataBindingUtil.inflate(inflater, R.layout.discover_fragment,container,false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.popularAll.setOnClickListener {
            val action =DiscoverFragmentDirections.actionDiscoverFragmentToPopularMoviesFragment()
            it.findNavController().navigate(action)
        }

        binding.upComingAll.setOnClickListener {
            val action =DiscoverFragmentDirections.actionDiscoverFragmentToUpcomingMoviesFragment()
            it.findNavController().navigate(action)
        }
        binding.nowPlayingAll.setOnClickListener {
            val action =DiscoverFragmentDirections.actionDiscoverFragmentToNowplayingMoviesFragment()
            it.findNavController().navigate(action)
        }

        initSearchInputListener()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.popularMovies.observe(viewLifecycleOwner, Observer {
            Log.i("size"," "+it?.size)
            binding.popularInclude.popularMoviesRecyclerView.adapter =PopularMoviesAdapter(it as List<Result>)
        })

        viewModel.upcomingMovies.observe(viewLifecycleOwner, Observer {
            Log.i("size"," "+it?.size)
            binding.upcomingInclude.upcomingMoviesRecyclerView.adapter =UpcomingMoviesAdapter(it as List<Result>)
        })

        viewModel.nowPlayingMovies.observe(viewLifecycleOwner, Observer {
            Log.i("size"," "+it?.size)
            binding.nowplayingInclude.nowPlayingMoviesRecyclerView.adapter =NowPlayingMoviesAdapter(it as List<Result>)
        })
    }

    private fun initSearchInputListener() {

        binding.textInputLayout.setEndIconOnClickListener {
            doSearch(it)
        }

        binding.searchTextField.setOnEditorActionListener { view: View, actionId: Int, _: KeyEvent? ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                doSearch(view)
                true
            } else {
                false
            }
        }

        binding.searchTextField.setOnKeyListener { view: View, keyCode: Int, event: KeyEvent ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                doSearch(view)
                true
            } else {
                false
            }
        }
    }

    private fun doSearch(view: View) {
        closeKeyboard()
        val action =DiscoverFragmentDirections.actionDiscoverFragmentToSearchMovieFragment(binding.searchTextField.text.toString())
        view.findNavController().navigate(action)

    }

    private fun closeKeyboard() {

        val view = activity?.currentFocus
        view?.let { v ->
            val imm =
                activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(v.windowToken, 0)


        }
    }


    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.hide()
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity).supportActionBar?.show()
    }



}
