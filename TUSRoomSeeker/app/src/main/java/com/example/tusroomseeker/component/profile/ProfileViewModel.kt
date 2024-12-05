package com.example.tusroomseeker.component.profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.tusroomseeker.R


class ProfileViewModel(application: Application) : AndroidViewModel(application) {

    fun loadProfiles(): List<Profile>{
        return listOf(
            Profile(1,R.drawable.female1, "kayla person","female","kperson@gmail.com","k00276142",0),
            Profile(2,R.drawable.female1, "Maria constable","female","mconstable@gmail.com","k00276147",1),
            Profile(3,R.drawable.man1, "Bob Caveman","male","livesincave@gmail.com","k00276178",0),
            Profile(4,R.drawable.man1, "Mark Admin","male","markisking@gmail.com","k00276145",1),


            )
    }

}