package org.uestc1123.decades

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity

class MainActivity: Activity(){
    private var images= intArrayOf(R.drawable.ic_launcher_background,R.drawable.ic_launcher_foreground)
    private var currentImage = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val main = findViewById<RelativeLayout>(R.id.root)
        val image=ImageView(this)
        main.addView(image)
        image.setImageResource(images[0])
        image.setOnClickListener{
            image.setImageResource(images[++currentImage%images.size])
        }
    }
}
