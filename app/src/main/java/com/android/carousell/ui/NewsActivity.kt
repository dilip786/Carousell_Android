package com.android.carousell.ui

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.carousell.R
import com.android.carousell.viewmodels.NewsViewModel
import kotlinx.android.synthetic.main.news_activity.*


class NewsActivity : AppCompatActivity() {

    lateinit var mViewModel: NewsViewModel
    lateinit var newsAdapter : NewsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.news_activity)
        initializeViewModel()
        initializeRecyclerView()
        initObservers()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    private fun initializeViewModel() {
        mViewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
        mViewModel.getNewsFromServer()
    }

    private fun initObservers() {
        mViewModel.handleNewsData.observe(this, {
            newsAdapter.refreshList(it.toMutableList())
        })

        mViewModel.handleError.observe(this, {
            showErrorMessage(it)
        })

         mViewModel.handleLoader.observe(this, {
             updateView(it)
        })
    }

    private fun initializeRecyclerView(){
        newsAdapter = NewsAdapter(mutableListOf()) {
            Toast.makeText(this,"Tapped on News : ${it.id}", Toast.LENGTH_LONG).show()
        }
        newsList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = newsAdapter
        }
    }

    private fun updateView(showLoader: Boolean){
        tvError.visibility = View.INVISIBLE
        if(showLoader){
            progressBar.visibility = View.VISIBLE
            newsList.visibility = View.INVISIBLE
        }
        else{
            progressBar.visibility = View.INVISIBLE
            newsList.visibility = View.VISIBLE
        }
    }

    private fun showErrorMessage(error: NewsViewModel.Errors){
        val message = when(error){
            NewsViewModel.Errors.NetworkError -> "No network, Please enable internet and try again."
            NewsViewModel.Errors.ServerError -> "Some issue at backend, Please try after sometime"
        }
        progressBar.visibility = View.INVISIBLE
        newsList.visibility = View.INVISIBLE
        tvError.visibility = View.VISIBLE
        tvError.text = message
    }
}