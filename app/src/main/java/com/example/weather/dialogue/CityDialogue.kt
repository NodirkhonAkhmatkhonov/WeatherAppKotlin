package com.example.weather.dialogue

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.example.weather.R
import com.example.weather.databinding.LayoutDialogueBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.launch

class CityDialogue : DialogFragment() {

    lateinit var binding: LayoutDialogueBinding

    lateinit var broadcastChannel: BroadcastChannel<String>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.setBackgroundDrawableResource(R.drawable.bg_dialogue);
        binding = LayoutDialogueBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.add.setOnClickListener {
            GlobalScope.launch {
                broadcastChannel.send(binding.etCity.text.toString())
            }
            dismiss()
        }
    }

    fun setListener(broadcastChannel: BroadcastChannel<String>) {
        this.broadcastChannel = broadcastChannel
    }
}