//package com.capillary.huffman.compressor;
//
//
//import com.capillary.huffman.mydefines.HuffmanData;
//import com.capillary.huffman.mydefines.Node;
//import org.junit.Test;
//import org.mockito.InOrder;
//import org.mockito.Mockito;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import static org.mockito.Mockito.*;
//
//public class HuffmanCompressionImplTest {
//
//
//    IReadData rwMock=mock(ReadDataImpl.class);
//    ICompressionUtils utilsMock=mock(CompressionUtils.class);
//    HuffmanCompressionImpl hc;
//
//    @Test
//    public void compress() {
//
////check this functiona
//
//        hc=new HuffmanCompressionImpl(rwMock,utilsMock);
//
//
//        Map<Byte,String > mp=new HashMap<>();
//
//        Byte[] fileData={'a',10};
//        Node root=new Node(null,2);
//        root.left=new Node((byte) 10,1);
//        root.right=new Node((byte) 97,1);
//
//        InOrder inOrder= Mockito.inOrder(rwMock,utilsMock);
//
//
//
//
//        HuffmanData huffmanData =new HuffmanData(fileData, (byte) 0);
//        String destination="/Users/gouravhanumante/IdeaProjects/Test Files/Real Test Files/testT.txt";
//
//
//        doReturn(root).when(utilsMock).createHuffmanTree(fileData);
//        doReturn(mp).when(utilsMock).buildLookupRecursive(root);
//        doReturn(huffmanData).when(utilsMock).createCompressedArray(fileData,mp);
////        doNothing().when(rwMock).write(destination, huffmanData,mp);
//
//        hc.compress(fileData,destination);
//
//
//
//        Node root2=new Node((byte) 'a',1);
//       verify(utilsMock).createHuffmanTree(fileData);
//
////        utilsMock.buildLookupRecursive(root,"",mp);
//        verify(utilsMock).buildLookupRecursive(root);
//
//        verify(utilsMock).createCompressedArray(fileData,mp);
//
////        rwMock.write(path,huffmanContainer,mp);
////        verify(rwMock).write(destination, huffmanData,mp);
//
//
//
//
//
//
//
//
//
//
//
//
//
//    }
//}