package com.capillary.huffman.compressor;

import com.capillary.huffman.mydefines.HuffmanData;
import com.capillary.huffman.mydefines.Node;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class HuffmanCompressionImpl implements ICompressor{



    //
    IReadData rw;
    ICompressionUtils utils;


    public HuffmanCompressionImpl(IReadData rw, ICompressionUtils utils) {
        this.rw=rw;
        this.utils=utils;
    }
    public HuffmanCompressionImpl() {
        this.rw=new ReadDataImpl();
        this.utils=new CompressionUtils();
    }


    public <Byte> void compress(Byte[] b, String destination) {

        HuffmanData huffmanData;

        Map<java.lang.Byte, String> huffmanCodes=new HashMap<>();
        if (b.length==0){
            huffmanData =new HuffmanData((java.lang.Byte[]) b, (byte) 0);
            write(destination, huffmanData,huffmanCodes);
            return;
        }

        Node root=utils.createHuffmanTree((java.lang.Byte[]) b);

        huffmanCodes= (Map<java.lang.Byte, String>) utils.buildLookupRecursive(root);

        huffmanData =utils.createCompressedArray(b,huffmanCodes);

        int size=huffmanData.getHuffmanByte().length;
        System.out.println("size " +size);
        size=size*8;

        long div=new File("/home/gauravhanumante/Files2/input.txt").length();
        System.out.println("msg"+(float) size/div);


//        rw.write(destination, huffmanData,lookupMap);
        write(destination,huffmanData,huffmanCodes);
    }


    public void write(String destination, HuffmanData huffmanData, Map<Byte,String> lookupMap) {
        try {
            OutputStream oStream = new FileOutputStream(destination);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(oStream);

            if (huffmanData.getHuffmanByte().length == 0) {

                Byte[] x = new Byte[1];
                x[0] = -1;

                objectOutputStream.writeObject(x);
                oStream.close();
                objectOutputStream.close();
                return;
            }


            objectOutputStream.writeObject(huffmanData.getHuffmanByte());
            objectOutputStream.writeObject(lookupMap);
            objectOutputStream.writeObject(huffmanData.getCounter());

            oStream.close();
            objectOutputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
