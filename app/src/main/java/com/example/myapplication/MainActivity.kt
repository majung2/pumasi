package com.example.myapplication


import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.FileNotFoundException
import java.nio.charset.Charset
import java.util.logging.Logger

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val clotList = arrayOf("TOPTEN", "H&M", "자라", "폴로")
        val cosmeList = arrayOf("에뛰드", "네이쳐 리퍼블릭", "이니스프리", "더 페이스샵")
        val sporList = arrayOf("나이키", "아디다스", "언더아머", "퓨마")
        var moveList = mutableListOf<String>()
        clotButton.setOnClickListener {
            val builder = AlertDialog.Builder(this@MainActivity)

            builder.setTitle("일반의류")

            builder.setItems(clotList, { builder, which ->
                val selected = clotList[which]
                moveList.add(selected)
                Toast.makeText(applicationContext, "$selected 브랜드를 선택!", Toast.LENGTH_SHORT).show()
            })
            val dialog = builder.create()
            dialog.show()
        }
        cosmeButton.setOnClickListener {
            val builder = AlertDialog.Builder(this)

            builder.setTitle("화장품")
            builder.setItems(cosmeList, { builder, which ->
                val selected = cosmeList[which]
                moveList.add(selected)
                Toast.makeText(applicationContext, "$selected 브랜드를 선택!", Toast.LENGTH_SHORT).show()
            })
            val dialog = builder.create()
            dialog.show()
        }
        sportsButton.setOnClickListener {
            val builder = AlertDialog.Builder(this@MainActivity)

            builder.setTitle("스포츠")
            builder.setItems(sporList, { builder, which ->
                val selected = sporList[which]
                moveList.add((selected))
                Toast.makeText(applicationContext, "$selected 브랜드를 선택!", Toast.LENGTH_SHORT).show()
            })
            val dialog = builder.create()
            dialog.show()
        }
        finButton.setOnClickListener {
            val builder = AlertDialog.Builder(this@MainActivity)
            builder.setTitle("추천 동선")
            builder.setMessage(result(moveList))
            builder.setPositiveButton("확인") { _, which ->
             val file = "saveData1.txt"
             val text = moveList.toString()
          //   val route = getFilesDir()
             when {
                    TextUtils.isEmpty(text) -> {
                        Toast.makeText(applicationContext, "저장할 내용이 없습니다", Toast.LENGTH_SHORT)
                            .show()
                    }
                    else -> {
                        /*Toast.makeText(applicationContext, route.toString(), Toast.LENGTH_SHORT)
                            .show()*/
                        save(text, file)
                        moveList = mutableListOf<String>()
                    }
                }
            }
            builder.show()
        }
    }

    fun result(s: MutableList<String>): CharSequence {
        var temp = ""
        for (spot in s) {
            if (s.size < 4) {
                var tmp = spot + " -> "
                temp = temp + tmp
            } else {
                var tmp = "\n" + spot + " -> "
                temp = temp + tmp
            }
            if(spot.equals(s[s.size-1])){
                var tmp = spot
                temp = temp + tmp
            }
        }
        var result = """
                선택하신 매장들을 바탕으로한 제일 빠른 쇼핑동선은
                $temp 입니다.
            """.trimIndent()
        return result
    }

    fun save(text: String, saveFile: String) {
        val fileOut = openFileOutput(saveFile, Context.MODE_PRIVATE)

        fileOut.write(text.toByteArray())
        fileOut.close()
    }
}