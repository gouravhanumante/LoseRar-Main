package com.capillary.huffman.decompressor;

import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class HuffmanDecompressionImplTest {

    @Test
    public void decompress() {
        IDecompressionUtils utils=mock(DecompressionUtilsImpl.class);
        HuffmanDecompressionImpl decompress=new HuffmanDecompressionImpl(utils);

        InOrder inOrder= Mockito.inOrder(utils);


        String source="/Users/gouravhanumante/IdeaProjects/Test Files/Real Test Files/compressedTest.txt";
        String destination="/Users/gouravhanumante/IdeaProjects/Test Files/Real Test Files/rrrrr.txt";

        Map<Byte,String> mp=new HashMap<>();
        mp.put((byte) 'a',"1");

        byte[] b={1};
        byte[] arr= {97};

        doReturn(arr).when(utils).decompress(b,mp, (byte) 0);


        decompress.decompress(source,destination);

        inOrder.verify(utils).decompress(b,mp, (byte) 0);




    }
}