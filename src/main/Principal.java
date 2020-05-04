package main;

import connection.ConnectionFactory;
import connection.Data;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

class Main {
    public static void main(String[] args) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        ArrayList<Data> list = new ArrayList();
        String sql = "SELECT tr.trackid, tr.name, tr.composer, ge.name as genre FROM tracks as tr INNER JOIN genres as ge ON ge.genreid = tr.genreid group by tr.name limit 200";
        Statement stmt = con.createStatement();
        ResultSet result = stmt.executeQuery(sql);
        
        while(result.next()) {
            list.add(trataDados(result));
        }
         
        Iterator<Data> it_list = list.iterator();
        while(it_list.hasNext()) {
            System.out.println("***************************************");
            System.out.println("ID da Track: " + it_list.next().getId());
            System.out.println("Nome.......: " + it_list.next().getName());
            System.out.println("Compositor.: " + it_list.next().getComposer());
            System.out.println("Gênero.....: " + it_list.next().getGenre());
        }
    }
    
    
    private static Data trataDados(ResultSet result) throws SQLException {
        Data data = new Data(); 
        data.setId(result.getInt("trackid"));
        data.setName(result.getString("name"));
        data.setComposer(result.getString("composer"));
        data.setGenre(result.getString("genre"));
        return data;
    }
    
}