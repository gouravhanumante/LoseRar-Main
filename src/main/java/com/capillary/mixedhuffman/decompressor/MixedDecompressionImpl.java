package com.capillary.mixedhuffman.decompressor;

import com.capillary.database.connect.CRUDImpl;
import com.capillary.database.connect.Connect;
import com.capillary.database.connect.ICRUD;
import com.capillary.database.connect.IDBConnect;
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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
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

//            byte type=(byte)objectInputStream.readObject();

            String key=(String)objectInputStream.readObject();
            byte counter=(byte) objectInputStream.readObject();
            IDBConnect createConnection=new Connect();
            Connection connection=createConnection.connect();

            ICRUD crud=new CRUDImpl();
            Statement stm=connection.createStatement();
            Map<String,Integer> freqMap= crud.retreiveFreqMap(key,stm);
            IDecompressionUtils util = new DecompressionUtilsImpl();
            byte[] finalRes= util.decompress(huffmanBytes,freqMap,counter);
            oStream.write(finalRes);
            objectInputStream.close();
            oStream.close();
            iStream.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
