package com.capillary;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

public interface IZipper {
    void compression(String source,String destination) throws IOException, ExecutionException, InterruptedException, SQLException, NoSuchAlgorithmException, ClassNotFoundException;

    void decompression(String source,String destination);
}
