package com.example.balaqai.traditions

import android.R
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.balaqai.databinding.ActivityVideoTrBinding
import com.example.balaqai.utils.TraditionsData
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView


class VideoTrActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVideoTrBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoTrBinding.inflate(layoutInflater)
        setContentView(binding.root)


        if (!intent.hasExtra(EXTRA_NAME_OF_TRADITION) || !intent.hasExtra(EXTRA_NAME_TRADITION_GROUP)){
            finish()
            return
        }
        val nameTradition = intent.getStringExtra(EXTRA_NAME_OF_TRADITION)
        val nameOfTrGroup = intent.getStringExtra(EXTRA_NAME_TRADITION_GROUP)
        if (nameOfTrGroup != null) {
            TraditionsData.getTraditionsGroup(nameOfTrGroup)
        }

        val youTubePlayerView = binding.youtubePlayerView
        lifecycle.addObserver(youTubePlayerView)

        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                val videoId = InfoTrActivity.selectedTraditionInfo.video
                youTubePlayer.loadVideo(videoId, 0f)
            }
        })





        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
        binding.buttonNext.setOnClickListener {
            val intent = QuestionActivity.newIntent(this@VideoTrActivity,"SOMETHING")
            startActivity(intent)
        }

        binding.quesAnsBtn.setOnClickListener {
            val intent = QuestionActivity.newIntent(this@VideoTrActivity,"SOMETHING")
            startActivity(intent)
        }
        binding.maglumatBtn.setOnClickListener {
            val intent = InfoTrActivity.newIntent(this@VideoTrActivity,nameTradition.toString(),nameOfTrGroup.toString())
            startActivity(intent)
        }
    }
    companion object{
        private const val EXTRA_NAME_OF_TRADITION = "nameTrad"
        private const val EXTRA_NAME_TRADITION_GROUP = "nameOfTraditionGroup"

        fun newIntent(context: Context, nameTradition: String,nameTraditionGroup: String): Intent {
            val intent = Intent(context,VideoTrActivity::class.java)
            intent.putExtra(EXTRA_NAME_OF_TRADITION,nameTradition)
            intent.putExtra(EXTRA_NAME_TRADITION_GROUP,nameTraditionGroup)
            return intent
        }
    }
}