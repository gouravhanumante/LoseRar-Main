package com.capillary.huffman.compressor;

import com.capillary.huffman.mydefines.Container;
import com.capillary.huffman.mydefines.Node;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class HuffmanCompressionImplTest {


    IReadAndWrite rwMock=mock(ReadAndWriteImpl.class);
    ICompressionUtils utilsMock=mock(CompressionUtils.class);
    HuffmanCompressionImpl hc;

    @Test
    public void compress() {



        hc=new HuffmanCompressionImpl(rwMock,utilsMock);


        Map<Byte,String > mp=new HashMap<>();

        byte[] fileData={'a',10};
        Node root=new Node(null,2);
        root.left=new Node((byte) 10,1);
        root.right=new Node((byte) 97,1);

        InOrder inOrder= Mockito.inOrder(rwMock,utilsMock);




        Container huffmanContainer=new Container(fileData, (byte) 0);
        String destination="/home/gauravhanumante/Files/testT.txt";


        doReturn(root).when(utilsMock).createHuffmanTree(fileData);
        doNothing().when(utilsMock).buildLookupRecursive(root,"",mp);
        doReturn(huffmanContainer).when(utilsMock).createCompressedArray(fileData,mp);
        doNothing().when(rwMock).write(destination,huffmanContainer,mp);

        hc.compress(fileData,destination);



        Node root2=new Node((byte) 'a',1);
       verify(utilsMock).createHuffmanTree(fileData);

//        utilsMock.buildLookupRecursive(root,"",mp);
        verify(utilsMock).buildLookupRecursive(root,"",mp);

        verify(utilsMock).createCompressedArray(fileData,mp);

//        rwMock.write(path,huffmanContainer,mp);
        verify(rwMock).write(destination,huffmanContainer,mp);













    }
}