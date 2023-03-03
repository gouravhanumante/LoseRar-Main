package com.capillary.database.connect;

import java.io.*;
import java.sql.*;
import java.util.Map;

public class CRUDImpl implements ICRUD{

    @Override
    public boolean storeFreqMap(Map<String, Integer> frequencyMap, Connection connection, String key) throws IOException, SQLException {
        ByteArrayOutputStream obj=new ByteArrayOutputStream();
        byte[] bytarr= null;
        ObjectOutputStream op=new ObjectOutputStream(obj);
        op.writeObject(frequencyMap);
        op.flush();
        op.close();
        bytarr=obj.toByteArray();
        PreparedStatement prepareStatement = connection.prepareStatement("insert into fileTable values(?,?)");
        prepareStatement.setString(1, key);
        prepareStatement.setBytes(2,bytarr);
        prepareStatement.executeUpdate();
        return true;
    }

    public Map<String, Integer> retreiveFreqMap(String key, Statement stm) throws SQLException, IOException, ClassNotFoundException {
        String qry="select * from fileTable where md5=\""+key+"\"";
        System.out.println(qry);
        ResultSet rs=stm.executeQuery(qry);
        rs.next();
        System.out.println(rs.getString(1));
        byte[] arr=rs.getBytes(2);
        if(arr == null) return null;
//        System.out.println(“-------------------“);
        ObjectInputStream ip=null;
        try {
            ip=new ObjectInputStream(new ByteArrayInputStream(arr));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Map<String,Integer> frequencyMap=(Map<String, Integer>) ip.readObject();
//                for(Map.Entry<Object,Integer> entry:frequencyMap.entrySet())
//                {
//                    System.out.println(entry.getKey()+”   “+entry.getValue());
//                }
        return frequencyMap;


    }
}
