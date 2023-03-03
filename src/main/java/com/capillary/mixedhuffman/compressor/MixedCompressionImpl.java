package com.capillary.mixedhuffman.compressor;




import com.capillary.database.connect.CRUDImpl;
import com.capillary.database.connect.Connect;
import com.capillary.database.connect.ICRUD;
import com.capillary.database.connect.IDBConnect;
import com.capillary.huffman.compressor.*;
import com.capillary.huffman.mydefines.HuffmanData;
import com.capillary.huffman.mydefines.Node;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MixedCompressionImpl implements ICompressor
{
    HuffmanData huffmanData;
    IWriteData writeData =new WriteDataImpl();

    IMapCreationUtils mapCreationUtils=new MapCreationUtilsImpl();

    ITreeCreationUtils treeCreationUtils=new TreeCreationUtils();

    MixedCreationUtils mixedCreationUtils =new MixedCreationUtils();



    ICompressionUtils compressionUtils=new CompressionUtils();
    ICRUD crud = new CRUDImpl();
    GenerateHash generateHash = new GenerateHash();


//    Helper helper=new Helper();
    @Override
    public void compress(Byte[] fileData, String destination) throws IOException, ExecutionException, InterruptedException, SQLException, NoSuchAlgorithmException, ClassNotFoundException {
        Map<String,Integer> temp=new HashMap<>();
        if (fileData.length==0){
            huffmanData =new HuffmanData( fileData, (byte) 0);
            writeData.write(destination, huffmanData,temp,"");
            return;
        }
        //playgroudn
        String[] words = mixedCreationUtils.createWordsArray(fileData);
        IDBConnect createConnection = new Connect();
        Connection connection = createConnection.connect();
        Statement s = connection.createStatement();
//        ResultSet rs = s.executeQuery("use huffmanDB");
//        ResultSet rs = s.executeQuery("select * from fileTable");
        Map<String, Integer> bestMap = null;
        bestMap = checkIfMapIsPresentInDB(fileData,connection);
        if(bestMap == null) {
            Map<String, Integer> fMap = mapCreationUtils.getFrequencyMap(words);
            ExecutorService service= Executors.newFixedThreadPool(5);
            CalcBestPercent[] tasks=new CalcBestPercent[5];
            List<Future<Long>> sizes=new ArrayList<>();
            long st=System.currentTimeMillis();

            for (int i=0;i<5;i++){
                tasks[i]=new CalcBestPercent(words,fMap,i);
                sizes.add(service.submit(tasks[i]));
            }

            Long bestSize=Long.MAX_VALUE;
            for (int i=0;i<5;i++){
                long size=sizes.get(i).get().longValue();
                if (size<bestSize){
                    bestSize=size;
                    bestMap=tasks[i].getResultFreqMap();
                }
            }
            service.shutdown();
            long et=System.currentTimeMillis();
            String key = generateHash.getHashValue(fileData);
            crud.storeFreqMap(bestMap,connection,key);
        }

        //







        long val1 = System.currentTimeMillis();
//        String[] words = mixedCreationUtils.createWordsArray(fileData);
        long val2 = System.currentTimeMillis();
        //System.out.println(val2 - val1 + "createWordsArray");

        val1 = System.currentTimeMillis();



        //map creation normal
//        Map<String,Integer> initialMap=mapCreationUtils.getFrequencyMap(words);


        //multi

        //System.out.println("st-et:" +(et-st));
//        System.out.println("bestSize:"+bestSize);


        val2 = System.currentTimeMillis();
//        System.out.println(val2 - val1 + "CalcBestPercent");

        val1 = System.currentTimeMillis();
//        String[] finalFileData=mixedCreationUtils.getFinalFileData(words,bestMap);
        val2 = System.currentTimeMillis();
//        System.out.println(val2 - val1 + "getFinalFileData after best percent");

        Node root=treeCreationUtils.createTreeUsingMinHeap(bestMap);
        val1 = System.currentTimeMillis();
//        System.out.println(val1 - val2 + "createTreeUsingMinHeap");

        Map<String,String> huffmanCodes=compressionUtils.buildLookupRecursive(root);
        val2 = System.currentTimeMillis();
//        System.out.println(val2 - val1 + "buildLookupRecursive");

//        System.out.println("best map size h yaha par "+helper.getMapSize(bestMap));

        huffmanData = compressionUtils.createCompressedArray(words,huffmanCodes);
        val1 = System.currentTimeMillis();
//        System.out.println(val1 - val2 + "createCompressedArray");

        GenerateHash hash=new GenerateHash();
        String key=hash.getHashValue(fileData);

        writeData.write(destination, huffmanData, bestMap,key);
        val2 = System.currentTimeMillis();
        System.out.println(val2 - val1 + "write");


    }

    public Map<String,Integer> checkIfMapIsPresentInDB(Byte[] fileData, Connection connection) throws SQLException, IOException, ClassNotFoundException, NoSuchAlgorithmException {

        String checkSum = generateHash.getHashValue(fileData);
        Statement s = connection.createStatement();
        Map<String,Integer> freqMap = crud.retreiveFreqMap(checkSum, s);
        return freqMap;
    }
}