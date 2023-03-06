package com.capillary.huffman.compressor;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

public interface ICompressor {
     void compress(Byte[] b, String destination) throws IOException, ExecutionException, InterruptedException, SQLException, NoSuchAlgorithmException, ClassNotFoundException;
}
