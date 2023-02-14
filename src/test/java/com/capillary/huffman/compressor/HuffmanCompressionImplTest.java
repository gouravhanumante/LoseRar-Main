package com.capillary.huffman.compressor;

import com.capillary.huffman.mydefines.Container;
import com.capillary.huffman.mydefines.Node;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class HuffmanCompressionImplTest {

    HuffmanCompressionImpl hc=new HuffmanCompressionImpl();

    @Test
    public void compress() {
        IReadAndWrite rwMock=mock(ReadAndWriteImpl.class);
        ICompressionUtils utilsMock=mock(CompressionUtils.class);

        byte[] b={97,98,99,10};
        String path="/home/gauravhanumante/Files/TestFiles/you.txt";
        Map<Byte,String > mp=new HashMap<>();


        hc.compress(b,path);

        Node root=utilsMock.createHuffmanTree(b);
        verify(utilsMock).createHuffmanTree(b);

        utilsMock.buildLookupRecursive(root,"",mp);
        verify(utilsMock).buildLookupRecursive(root,"",mp);

        Container huffmanContainer=utilsMock.createCompressedArray(b,mp);
        verify(utilsMock).createCompressedArray(b,mp);

        rwMock.write(path,huffmanContainer,mp);
        verify(rwMock).write(path,huffmanContainer,mp);













    }
}