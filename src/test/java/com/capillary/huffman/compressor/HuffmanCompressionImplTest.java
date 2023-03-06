package com.capillary.huffman.compressor;


import com.capillary.huffman.mydefines.HuffmanData;
import com.capillary.huffman.mydefines.Node;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static org.mockito.Mockito.*;

public class HuffmanCompressionImplTest {


    IReadData rwMock=mock(ReadDataImpl.class);
    ICompressionUtils utilsMock=mock(CompressionUtils.class);
    HuffmanCompressionImpl hc;
    ITreeCreationUtils treeCreationUtilsMock = mock(TreeCreationUtils.class);

    @Test
    public void compress() throws ExecutionException, InterruptedException {

        hc=new HuffmanCompressionImpl(rwMock,utilsMock, treeCreationUtilsMock);

        Map<String,String > mp=new HashMap<>();

        Byte[] fileData1={'a',10};
        Node root=new Node(null,2);
        root.left=new Node("\n",1);
        root.right=new Node("a",1);

        InOrder inOrder= Mockito.inOrder(rwMock,utilsMock);




        HuffmanData huffmanData =new HuffmanData(fileData1, (byte) 0);
        String destination="/home/sarthakjain/IdeaProjects/files/Test Files/test1.txt";
        Map<String, Integer> freqMap = new HashMap<>();
        String[] fileData = {"a", "\n"};
        doReturn(freqMap).when(treeCreationUtilsMock).createFrequencyMap(fileData);
        doReturn(root).when(utilsMock).createHuffmanTree(fileData);
        doReturn(mp).when(utilsMock).buildLookupRecursive(root);
        doReturn(huffmanData).when(utilsMock).createCompressedArray(fileData,mp);
//        doNothing().when(rwMock).write(destination, huffmanData,mp);

        hc.compress(fileData1,destination);



        Node root2=new Node("a",1);
       verify(utilsMock).createHuffmanTree(fileData);

//        utilsMock.buildLookupRecursive(root,"",mp);
        verify(utilsMock).buildLookupRecursive(root);

        verify(utilsMock).createCompressedArray(fileData,mp);

//        rwMock.write(path,huffmanContainer,mp);
//        verify(rwMock).write(destination, huffmanData,mp);













    }
}