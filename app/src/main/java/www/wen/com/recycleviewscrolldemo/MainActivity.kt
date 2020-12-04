package www.wen.com.recycleviewscrolldemo

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import java.util.*

class MainActivity : AppCompatActivity(), RecycleAdapter.CallBack,View.OnClickListener {

    private lateinit var recyclerView:RecyclerView
    private lateinit var etPosition:EditText
    private lateinit var etPx:EditText
    private var isHorizontal:Boolean = true

    var changePosition = 0
    var changePx = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecycleView()
    }

    private fun initRecycleView() {
        recyclerView = findViewById(R.id.recycleView)
        etPosition = findViewById(R.id.etPosition)
        etPx = findViewById(R.id.etPX)

        etPosition.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val results: Boolean = s.toString().matches(Regex("^-?[1-9]\\d*$"))
                if (!results) return
                changePosition = s.toString().toInt()
                Toast.makeText(this@MainActivity, "position为${changePosition}", Toast.LENGTH_SHORT)
                    .show()
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })

        etPx.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val results: Boolean = s.toString().matches(Regex("^-?[1-9]\\d*$"))
                if (!results) return
                changePx = s.toString().toInt()
                Toast.makeText(this@MainActivity, "Px为${changePx}", Toast.LENGTH_SHORT)
                    .show()
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })

        findViewById<Button>(R.id.button1).setOnClickListener(this)
        findViewById<Button>(R.id.button2).setOnClickListener(this)
        findViewById<Button>(R.id.button3).setOnClickListener(this)
        findViewById<Button>(R.id.button4).setOnClickListener(this)
        findViewById<Button>(R.id.button5).setOnClickListener(this)
        findViewById<Button>(R.id.switchLayoutManage).setOnClickListener(this)

        val ints: MutableList<Int> = ArrayList<Int>()
        for (index in 1..100) {
            ints.add(index)
        }
        recyclerView.layoutManager = LinearLayoutManager(this, HORIZONTAL, false)
        recyclerView.adapter = RecycleAdapter(this, ints)
    }

    override fun call(view: View, position: Int, data: Int) {
        Toast.makeText(this, "点击了第$position 个", Toast.LENGTH_SHORT).show()
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.button1 -> {
                recyclerView.smoothScrollToPosition(changePosition)
            }
            R.id.button2 -> {
                scrollBy()
            }
            R.id.button3 -> {
                recyclerView.scrollToPosition(changePosition)
            }
            R.id.button4 -> {
                (recyclerView.layoutManager as LinearLayoutManager?)!!.scrollToPositionWithOffset(
                    changePosition,
                    changePx
                )
            }
            R.id.button5 -> {
                smoothScrollBy()
            }
            R.id.switchLayoutManage -> {
                if(isHorizontal){
                    recyclerView.layoutManager = LinearLayoutManager(this, VERTICAL, false)
                    isHorizontal = false
                }else{
                    recyclerView.layoutManager = LinearLayoutManager(this, HORIZONTAL, false)
                    isHorizontal = true
                }
            }

        }
    }

    private fun isHorizontal():Boolean{
        return (recyclerView.layoutManager as LinearLayoutManager).orientation == HORIZONTAL
    }

    private fun scrollBy(){
         if (isHorizontal())
             recyclerView.scrollBy(changePx, 0)
         else
             recyclerView.scrollBy(0, changePx)
    }

    private fun smoothScrollBy(){
        if (isHorizontal())
            recyclerView.smoothScrollBy(changePx, 0)
        else
            recyclerView.smoothScrollBy(0, changePx)
    }

}
