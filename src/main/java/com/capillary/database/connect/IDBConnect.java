package com.capillary.database.connect;

import java.sql.Connection;
import java.sql.SQLException;

public interface IDBConnect {
    public Connection connect() throws SQLException;

}
