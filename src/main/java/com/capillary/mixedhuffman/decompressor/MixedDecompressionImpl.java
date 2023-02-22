package com.capillary.mixedhuffman.decompressor;

import com.capillary.huffman.compressor.CompressionUtils;
import com.capillary.huffman.compressor.ICompressionUtils;
import com.capillary.huffman.compressor.ITreeCreationUtils;
import com.capillary.huffman.compressor.TreeCreationUtils;
import com.capillary.huffman.decompressor.DecompressionUtilsImpl;
import com.capillary.huffman.decompressor.HuffmanDecompressionImpl;
import com.capillary.huffman.decompressor.IDecompressionUtils;
import com.capillary.huffman.decompressor.IDecompressor;
import com.capillary.huffman.mydefines.Node;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class MixedDecompressionImpl implements IDecompressor {
    @Override
    public <T> void decompress(String source, String destination) {

//        IDecompressor decompressor=new HuffmanDecompressionImpl();
//        decompressor.decompress(source,destination);
        try {
            FileInputStream iStream=new FileInputStream(source);
            ObjectInputStream objectInputStream=new ObjectInputStream(iStream);

            OutputStream oStream=new FileOutputStream(destination);
//            ObjectOutputStream objectOutputStream=new ObjectOutputStream(oStream);

            byte[] huffmanBytes= (byte[]) objectInputStream.readObject();
//            System.out.println(Arrays.toString(huffmanBytes));

            if (huffmanBytes.length==1 && huffmanBytes[0]==-1){
                oStream.write(0);
                objectInputStream.close();
                oStream.close();
                iStream.close();
                return;
            }

            byte type=(byte)objectInputStream.readObject();

            Map<String,Integer> lookupMap = (Map<String, Integer>) objectInputStream.readObject();

//            if (type==0){
//                lookupMap= (Map<String, Integer>) objectInputStream.readObject();
//            }else{
//                lookupMap= (Map<String, Integer>) objectInputStream.readObject();
//            }

            byte counter=(byte) objectInputStream.readObject();

            ITreeCreationUtils treeCreationUtils=new TreeCreationUtils();
            Node root = treeCreationUtils.createTreeUsingMinHeap(lookupMap);
            ICompressionUtils compressionUtils=new CompressionUtils();
            Map<String, String> huffCodes = compressionUtils.buildLookupRecursive(root);


//            byte[] finalRes=getDecompressedData(huffmanBytes,lookupMap,counter);
            IDecompressionUtils util = new DecompressionUtilsImpl();
            byte[] finalRes= util.decompress(huffmanBytes,huffCodes,counter);
//            System.out.println(Arrays.toString(finalRes));

//            System.out.println(Arrays.toString(finalRes));



//            T[] fr=new T[finalRes.length];
//            int i=0;
//            for (T b:finalRes){
//                fr[i++]=b;
//            }


//            System.out.println(Arrays.toString(finalRes));
            oStream.write(finalRes);
//            o

//            objectOutputStream.close();
            objectInputStream.close();
            oStream.close();
            iStream.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
