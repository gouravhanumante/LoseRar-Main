package com.capillary;

import java.io.IOException;

public interface IZipper {
    void compression(String source,String destination) throws IOException;

    void decompression(String source,String destination);
}
