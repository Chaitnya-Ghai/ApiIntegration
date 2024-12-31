package com.example.apiintegration

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apiintegration.adapters.RecyclerAdapter
import com.example.apiintegration.adapters.RecyclerInterface
import com.example.apiintegration.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), RecyclerInterface {
    lateinit var binding:ActivityMainBinding
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var vmClass:VModel
    var array= arrayListOf<ResponseModel.Data>()
    var recyclerAdapter:RecyclerAdapter=RecyclerAdapter(this,array,this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        linearLayoutManager=LinearLayoutManager(this)
        binding.recyclerView.layoutManager=linearLayoutManager
        binding.recyclerView.adapter=recyclerAdapter
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initViews()
        vmClass.apiHit()
    }
    private fun initViews(){
        vmClass=ViewModelProvider(this)[VModel::class.java]
        vmClass.getRes.observe(this){
            when(it){
                is SealedClass.Error->{
                    Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
                }
                is SealedClass.Loading->{
                    Toast.makeText(this, "loadinggg", Toast.LENGTH_LONG).show()
                }
                is SealedClass.Success->{
                    Toast.makeText(this, "success", Toast.LENGTH_SHORT).show()
                    it.data?.data?.forEach{
                        array.add(it!!)
                    }
                    recyclerAdapter.notifyDataSetChanged()
                }

                null ->{
                    Toast.makeText(this, "null", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun delete(position: Int) {
        array.removeAt(position)
        recyclerAdapter.notifyDataSetChanged()
    }

    override fun update(position: Int, oldModel: ResponseModel.Data) {
        array[position]=ResponseModel.Data()
    }

    override fun itemClick(position: Int, model: ResponseModel.Data) {
        Toast.makeText(this, "chl rha hai ${position}", Toast.LENGTH_SHORT).show()
        val intent= Intent(this,NextActivity::class.java)
        intent.putExtra("first_name",model.first_name)
        intent.putExtra("last_name",model.last_name)
        intent.putExtra("email",model.email)
        startActivity(intent)
    }
}