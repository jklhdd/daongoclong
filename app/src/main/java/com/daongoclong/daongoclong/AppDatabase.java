package com.daongoclong.daongoclong;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Feedback.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract FeedbackDao feedbackDao();

    private static AppDatabase appDatabase;

    public static AppDatabase getAppDatabase(Context context) {
        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder(context, AppDatabase.class, "Feedback").allowMainThreadQueries().build();
        }
        return appDatabase;
    }
}
