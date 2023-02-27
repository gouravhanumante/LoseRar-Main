package com.capillary;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public interface IZipper {
    void compression(String source,String destination) throws IOException, ExecutionException, InterruptedException;

    void decompression(String source,String destination);
}
