package com.tms.webshop.dao;

import com.tms.webshop.dao.utils.ConnectionPool;

public interface BaseRepository {
    ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
}
