package com.capillary.database.connect;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

public interface ICRUD {

    public boolean storeFreqMap(Map<String, Integer> freqMap, Connection connection, String key) throws IOException, SQLException;

    public Map<String, Integer> retreiveFreqMap(String key, Statement stm) throws SQLException, IOException, ClassNotFoundException;

}
