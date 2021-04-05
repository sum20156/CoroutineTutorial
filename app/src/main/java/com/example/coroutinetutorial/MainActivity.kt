
package com.example.coroutinetutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.*
import java.lang.Runnable
import java.util.*

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    //lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       // textView = findViewById(R.id.textView)

        //  startMultipleCoroutines()

        // startMultipleCoroutines()



        lifecycleScope.launchWhenStarted {
            Log.i(TAG, "Coroutine: "+this)
            performNetworkOperation()
            Log.i(TAG, "NetworkOperationCompleted")
        }

        lifecycleScope.launchWhenStarted {
            Log.i(TAG, "Coroutine: "+this)
            performNetworkOperation()
            Log.i(TAG, "NetworkOperationCompleted")
        }


        //  startRxJavaLoop()

        /* textView.setOnClickListener(View.OnClickListener {
            // startMultipleThreads()
         })*/


    }

    private fun startRxJavaLoop() {

        val list = listOf(1,2,3,4,5,6,7,8,9,10)
        Observable.fromIterable(list)
            .subscribeOn(Schedulers.io())
            .subscribe {
                Log.i(TAG, "startRxJavaLoop: "+Thread.currentThread().name)
                Thread.sleep(2000)

                /*if (it==8){
                    runOnUiThread {
                        val alertDialog = AlertDialog.Builder(this).create()
                        alertDialog.show()
                    }

                }*/
            }
    }

    fun startMultipleThreads() {
        Thread(Runnable {
            while (true){
                Thread(Runnable {
                    Log.i(TAG, "startMultipleThreads: "+Thread.currentThread().name)
                    Thread.sleep(100000)


                }).start()
            }
        }).start()

    }

    fun startMultipleCoroutines() {

        lifecycleScope.launchWhenStarted  {
            while (true){
                delay(1000)
                Log.i(TAG, "startMultipleCoroutines: "+Thread.currentThread().name)
                // do some background work
            }
        }

    }

    suspend fun performNetworkOperation(){
        delay(5000)
        Log.i(TAG, "performNetworkOperation: "+Thread.currentThread().name)

    }
}