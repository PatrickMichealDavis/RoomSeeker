package com.example.tusroomseeker.component.messages

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class ViewMessageModel(application: Application) : AndroidViewModel(application) {

    fun loadMessages(): List<Message>{
        return listOf(
            Message(1,2,1,"Hi is the room still available"),
            Message(2,1,2,"Yes when can you move in"),
            Message(3,2,1,"Today?"),
            Message(4,1,2,"Sorry I am busy today")

        )
    }
}