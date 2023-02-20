package com.capillary.huffman.compressor;

import com.capillary.huffman.mydefines.HuffmanData;
import com.capillary.huffman.mydefines.Node;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Arrays;
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


    public <T> void compress(Byte[] b, String destination) {

        HuffmanData huffmanData;

        Map<T, String> huffmanCodes=new HashMap<>();
        if (b.length==0){
            huffmanData =new HuffmanData( b, (byte) 0);
            write(destination, huffmanData,huffmanCodes);
            return;
        }

        Node root=utils.createHuffmanTree(b);

        huffmanCodes= (Map<T, String>) utils.buildLookupRecursive(root);

        huffmanData =utils.createCompressedArray(b,huffmanCodes);
//        System.out.println(Arrays.toString(huffmanData.getHuffmanByte()));
//        System.out.println(huffmanData.getHuffmanByte());


//        int size=huffmanData.getHuffmanByte().length;
//        System.out.println("size " +size);
//        size=size*8;
//
//        long div=new File("/home/gauravhanumante/Files2/input.txt").length();
//        System.out.println("msg"+(float) size/div);


//        rw.write(destination, huffmanData,lookupMap);
        write(destination,huffmanData,huffmanCodes);
    }


    public void write(String destination, HuffmanData huffmanData, Map<?,String> lookupMap) {
        try {
            OutputStream oStream = new FileOutputStream(destination);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(oStream);

            if (huffmanData.getHuffmanByte().length == 0) {

                byte[] x = new byte[1];
                x[0] = -1;

                objectOutputStream.writeObject(x);
                oStream.close();
                objectOutputStream.close();
                return;
            }

            byte []ans=new byte[huffmanData.getHuffmanByte().length];
            int l=0;
            for (Byte x:huffmanData.getHuffmanByte()){
                ans[l++]= x;
            }

//            lookupMap.

//             lookupMap.get('\n');


            String s="";
            for (Map.Entry<?, String> entry:lookupMap.entrySet()){
               s= String.valueOf(entry.getKey().getClass().getSimpleName());
                break;
            }
//            System.out.println(s);


            objectOutputStream.writeObject(ans);

            if (s.equals("Byte")){
                objectOutputStream.writeObject((byte)0);
            }else{
                objectOutputStream.writeObject((byte)1);
            }
            objectOutputStream.writeObject(lookupMap);
            objectOutputStream.writeObject(huffmanData.getCounter());

            oStream.close();
            objectOutputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
