package com.example.testdemodubai.view

import android.app.Dialog
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testdemodubai.R
import com.example.testdemodubai.databinding.ActivityMainBinding
import com.example.testdemodubai.model.api.ApiHelperImpl
import com.example.testdemodubai.model.api.RetroInstance
import com.example.testdemodubai.model.data.BaseClass
import com.example.testdemodubai.utils.Status
import com.example.testdemodubai.utils.ViewModelFactory
import com.example.testdemodubai.view.adapter.AdapterItemClick
import com.example.testdemodubai.view.adapter.MainAdapter
import com.example.testdemodubai.viewmodel.MainViewModel


class MainActivity : AppCompatActivity() {
    var pd : ProgressDialog ? = null

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pd = ProgressDialog(this)
        pd?.setMessage("Please wait. News loading...!")

        val viewmodel = ViewModelProvider(this, ViewModelFactory(ApiHelperImpl(RetroInstance.apiService))).get(MainViewModel::class.java)
        viewmodel.getPapularNews().observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    pd?.dismiss()
                    populateRecyclerView(it.data!!)
                }
                Status.LOADING -> {
                    pd?.show()
                }
                Status.ERROR -> {
                    pd?.dismiss()
                    val ed=errorDialog(viewmodel)
                    ed.show()
                  //  Toast.makeText(this, "ERROR "+it.message, Toast.LENGTH_LONG).show()
                }
            }
        }

    }

    private fun errorDialog(viewmodel: MainViewModel): Dialog {
        val dialog=Dialog(this,android.R.style.ThemeOverlay)
       dialog.setContentView(R.layout.error_dialog)
        dialog.findViewById<View>(R.id.try_again).setOnClickListener{
            viewmodel.makeNetworkCall()
            dialog.dismiss()
        }
        dialog.findViewById<View>(R.id.close_bt).setOnClickListener{
            dialog.dismiss()
            finish()
        }
        return dialog
    }

    private fun populateRecyclerView(baseClass: BaseClass) {
        binding.mainRv.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = MainAdapter(this@MainActivity,baseClass.results,object : AdapterItemClick{
                override fun onClick(pos: Int) {
                    startActivity(Intent(this@MainActivity,DetailActivity::class.java)
                        .putExtra("url",baseClass.results[pos].url))
                }
            })

        }
    }
}