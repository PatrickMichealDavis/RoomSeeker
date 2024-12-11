package com.example.tusroomseeker.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.tusroomseeker.R
import com.example.tusroomseeker.component.listings.Listing
import com.example.tusroomseeker.component.listings.ListingDAO
import com.example.tusroomseeker.component.messages.Message
import com.example.tusroomseeker.component.messages.MessageDAO
import com.example.tusroomseeker.component.profile.Profile
import com.example.tusroomseeker.component.profile.ProfileDAO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Listing::class, Profile::class,Message::class], version = 3)
@TypeConverters(ArrayListConverter::class)
abstract class RoomSeekerRoomDatabase : RoomDatabase() {
    abstract fun listingDAO(): ListingDAO
    abstract fun profileDAO(): ProfileDAO
    abstract fun messageDAO(): MessageDAO



    companion object {
        private var instance: RoomSeekerRoomDatabase? = null
        private val coroutineScope = CoroutineScope(Dispatchers.IO)

        @Synchronized
        fun getDatabase(context: Context, clearDatabase: Boolean = false): RoomSeekerRoomDatabase? {

            if (clearDatabase) {
                context.deleteDatabase("roomseeker_database")
                instance = null
            }

            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    RoomSeekerRoomDatabase::class.java,
                    "roomseeker_database"
                )
                    .addCallback(roomDatabaseCallback(context))
                    .fallbackToDestructiveMigration()
                    .build()
               // clearAndSeedDatabase(context)
            }
            return instance
        }

        fun deleteDatabase(context: Context) {
            context.deleteDatabase("roomseeker_database")
            instance = null
        }

        private fun clearAndSeedDatabase(context: Context) {
            coroutineScope.launch {
                val dbInstance = getDatabase(context)
                //dbInstance?.listingDAO()?.clearAllListings()
               // dbInstance?.profileDAO()?.clearAllProfiles()
                //dbInstance?.messageDAO()?.clearAllMessages()
                seedDatabase(context, dbInstance)
            }
        }

        private fun roomDatabaseCallback(context: Context): Callback {
            return object : Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    coroutineScope.launch {

                        //seedDatabase(context, getDatabase(context))
                    }
                }
            }
        }



        private suspend fun seedDatabase(context: Context, dbInstance: RoomSeekerRoomDatabase?) {


            val listingList = listOf(
                Listing(
                    title="Single Room",
                    image="images/bedroom1.jpg",
                    address = "20 William street",
                    price = 199.99,
                    description = "Room for rent, price is per week. Looking for a quiet tenant",
                    eircode = "V95t2p7",
                    userId = 2),
                Listing(
                    title="Double Room",
                    image ="images/bedroom2.jpg",
                    address = "20 Knoxs street",
                    price = 880.99,
                    description = "Room price per month very reasonable. looking for non smoker",
                    eircode = "V95t2p7",
                    userId = 2),
                Listing(
                    title = "Single Room",
                    image="images/bedroom3.jpg",
                    address = "45 Quin street",
                    price=125.00,
                    description = "Room price per week",
                    eircode = "V95t2p7",
                    userId = 2),
                Listing(
                    title = "Double Room",
                    image="images/bedroom4.jpg",
                    address = "36 Fake street",
                    price=175.00,
                    description = "Room price per week, looking for part animal must smoke no one boring",
                    eircode = "V95t2p7",
                    userId = 2),
                Listing(
                    title = "Single Room",
                    image="images/bedroom5.jpg",
                    address = "123 yellow brick road",
                    price=900.00,
                    description = "Room price per month, looking for female tenant must love cats",
                    eircode = "V95t2p7",
                    userId = 2),
                Listing(
                    title = "Double Room",
                    image="images/bedroom6.jpg",
                    address = "89 Green street",
                    price=450.00,
                    description = "Room price per fortnight, looking for a clean tenant must be a student",
                    eircode = "V95t2p7",
                    userId = 2),
                Listing(
                    title = "Single Room",
                    image="images/bedroom7.jpg",
                    address = "56 Blue street",
                    price=1200.00,
                    description = "Room price per month, looking for a tenant who knows what class is. Must be a professional",
                    eircode = "V95t2p7",
                    userId = 2),
                Listing(
                    title = "Double Room",
                    image="images/bedroom8.jpg",
                    address = "90 Red street",
                    price=215.00,
                    description = "Room price per week, looking for a tenant who is a night owl",
                    eircode = "V95t2p7",
                    userId = 2),
                Listing(
                    title = "Single Room",
                    image="images/bedroom9.jpg",
                    address = "28 Purple street",
                    price=149.00,
                    description = "Room price per week, looking for a tenant who is a student must love eating out",
                    eircode = "V95t2p7",
                    userId = 2),
                Listing(
                    title = "Double Room",
                    image="images/bedroom10.jpg",
                    address = "58 Orange street",
                    price=799.00,
                    description = "Room price per month, looking for a tenant who is a software developer",
                    eircode = "V95t2p7",
                    userId = 2),

                )

            instance?.listingDAO()?.saveMultipleListings(listingList)


            val profileList = listOf(
                Profile(
                    name = "John Doe",
                    email = "johndoe@gmail.com",
                    gender="male",
                    knum="k00276151",
                    userType=0),
                Profile(
                    name = "Sarah Smith",
                    email = "sarahsmith@gmail.com",
                    gender="female",
                    knum="k00276178",
                    userType=1),
                Profile(
                    name = "Bruce Wayne",
                    email = "brucewayne@gmail.com",
                    gender="male",
                    knum="k00276119",
                    userType=0),
                Profile(
                    name = "Scarlett Johansson",
                    email = "scarjo@gmail.com",
                    gender="female",
                    knum="k00276145",
                    userType=0)
            )


            instance?.profileDAO()?.saveMultipleProfiles(profileList)
        }
    }

}


