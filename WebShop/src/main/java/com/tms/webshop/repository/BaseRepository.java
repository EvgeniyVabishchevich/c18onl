package com.tms.webshop.repository;

import com.tms.webshop.repository.utils.ConnectionPool;

public interface BaseRepository {
    ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
}
