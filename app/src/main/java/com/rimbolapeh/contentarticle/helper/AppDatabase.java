package com.rimbolapeh.contentarticle.helper;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.rimbolapeh.contentarticle.dao.ContentDao;
import com.rimbolapeh.contentarticle.model.room.Content;

@Database(entities = (Content.class), version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ContentDao contentDao();
}
