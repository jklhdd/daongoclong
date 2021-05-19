package com.daongoclong.daongoclong;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface FeedbackDao {
    @Insert
    long insert(Feedback feedback);

    @Query("SELECT COUNT(*) FROM feedback")
    int count();
}
