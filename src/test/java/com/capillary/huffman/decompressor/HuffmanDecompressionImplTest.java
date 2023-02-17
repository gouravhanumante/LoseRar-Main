//package com.capillary.huffman.decompressor;
//
//import org.junit.Test;
//import org.mockito.InOrder;
//import org.mockito.Mockito;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import static org.junit.Assert.*;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.doReturn;
//import static org.mockito.Mockito.mock;
//
//public class HuffmanDecompressionImplTest {
//
//    @Test
//    public void decompress() {
//        IDecompressionUtils utils=mock(DecompressionUtilsImpl.class);
//        HuffmanDecompressionImpl decompress=new HuffmanDecompressionImpl(utils);
//
//        InOrder inOrder= Mockito.inOrder(utils);
//
//
//        String source="/home/gauravhanumante/Files/compressedTest.txt";
//        String destination="/home/gauravhanumante/Files/rrrrr.txt";
//
//        Map<Byte,String> mp=new HashMap<>();
//        mp.put((byte) 'a',"1");
//        mp.put((byte) 10,"0");
//
//        byte[] b={2};
//
//        doReturn(b).when(utils).decompress(b,mp, (byte) 0);
//
//
//        decompress.decompress(source,destination);
//
//        inOrder.verify(utils).decompress(b,mp, (byte) 0);
//
//
//
//
//    }
//}