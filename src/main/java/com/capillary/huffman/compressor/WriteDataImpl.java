package com.capillary.huffman.compressor;

import com.capillary.huffman.mydefines.HuffmanData;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Map;

public class WriteDataImpl implements IWriteData {
    @Override
    public void write(String destination, HuffmanData huffmanData, Map<String, Integer> writableMap,String key) {
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
            //System.out.println(ans.length+"lol");
//            lookupMap.

//             lookupMap.get('\n');


            String s="";
            for (Map.Entry<?, ?> entry:writableMap.entrySet()){
                s= String.valueOf(entry.getKey().getClass().getSimpleName());
                break;
            }
//            System.out.println(s);

            //30
            //142284
            //256208
//        oStream.write(ans);

            objectOutputStream.writeObject(ans);

//            if (s.equals("Byte")){
//                objectOutputStream.writeObject((byte)0);
////                oStream.write((byte)0);
//            }else{
//                objectOutputStream.writeObject((byte)1);
////            oStream.write((byte)1);
//            }
//            objectOutputStream.writeObject(writableMap);
            objectOutputStream.writeObject(key);
//            oStream.write(huffmanData.getCounter());
            objectOutputStream.writeObject(huffmanData.getCounter());
            oStream.close();
            objectOutputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
