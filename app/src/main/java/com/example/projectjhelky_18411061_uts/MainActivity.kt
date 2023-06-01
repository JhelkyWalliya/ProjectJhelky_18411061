package com.example.projectjhelky_18411061_uts

import android.content.AbstractThreadedSyncAdapter
import android.content.Intent
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectjhelky_18411061_uts.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var wisataRecyclerView: RecyclerView
    private lateinit var wisataList: MutableList<Image>
    private lateinit var wisataAdapter: ImageAdpater
    private lateinit var binding: ActivityMainBinding

    private var mStorage: FirebaseStorage? = null
    private var mDatabaseRef: DatabaseReference? = null
    private var mDBListener: ValueEventListener? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        wisataRecyclerView = findViewById(R.id.imageRecycleView)
        wisataRecyclerView.setHasFixedSize(true)
        wisataRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

        wisataList = ArrayList()
        wisataAdapter = ImageAdpater(this@MainActivity, wisataList)
        wisataRecyclerView.adapter = wisataAdapter

        mStorage = FirebaseStorage.getInstance()
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("kopi")
        mDBListener = mDatabaseRef!!.addValueEventListener(object : ValueEventListener {

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@MainActivity, error.message, Toast.LENGTH_SHORT).show()

            }

            override fun onDataChange(snapshot: DataSnapshot) {
                wisataList.clear()
                for (teacherSnapshot in snapshot.children) {
                    val upload = teacherSnapshot.getValue(Image::class.java)
                    upload!!.key = teacherSnapshot.key
                    wisataList.add(upload)
                }
                wisataAdapter.notifyDataSetChanged()


            }
        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mymenu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logout -> {
                FirebaseAuth.getInstance().signOut()
                Intent(this, LoginActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        }
        return true
    }
}
