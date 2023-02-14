package com.capillary.huffman.compressor;

import com.capillary.huffman.mydefines.Container;
import com.capillary.huffman.mydefines.Node;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class HuffmanCompressionImpl implements ICompressor{


    private Map<Byte,String> lookupMap=new HashMap<>();
    Container huffmanContainer;



    @Override
    public void compress(byte[] b, String destination) {
        IReadAndWrite rw=new ReadAndWriteImpl();
        ICompressionUtils utils=new CompressionUtils();


        if (b.length==0){
            huffmanContainer=new Container(b, (byte) 0);
            rw.write(destination,huffmanContainer,lookupMap);
            return;
        }

        Node root=utils.createHuffmanTree(b);

        utils.buildLookupRecursive(root,"",lookupMap);

        huffmanContainer=utils.createCompressedArray(b,lookupMap);

        rw.write(destination,huffmanContainer,lookupMap);
    }



}
