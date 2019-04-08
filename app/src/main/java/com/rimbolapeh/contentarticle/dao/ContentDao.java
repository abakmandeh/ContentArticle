package com.rimbolapeh.contentarticle.dao;

import android.arch.persistence.room.Insert;

import com.rimbolapeh.contentarticle.model.room.Content;

public interface ContentDao {

    @Insert
    void insert (Content content);
}
