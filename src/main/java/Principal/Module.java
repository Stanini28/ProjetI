/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 *
 * @author racha
 */
public class Module {
    
    public static final String[][] Module = new String[][]{
        {"1", "Espagnol","jsp", "34", "1"},
        {"2", "Negociate to succeed", "jsp", "27","1"},
        {"3", "Allemand 2LF", "jsp", "28", "1"},
        {"4", "Tutorat", "jsp", "20", "2"},
        {"5", "Expression théatrale", "jsp", "32", "2"},
        {"6", "PRAP", "jsp", "35", "3"},
        {"7", "Histoire du design","jsp", "28", "2"},
        {"8", "Maquette numérique", "jsp", "28", "3"},
        {"9", "Satellites", "jsp", "28", "3"}
    };
    
    public static List<String> intitule() {
        return Arrays.stream(Module).map((t) -> {
            return t[1];
        }).distinct().toList();
    }
    
    public static List<String> description() {
        return Arrays.stream(Module).map((t) -> {
            return t[2];
        }).distinct().toList();
    }
    
    public static List<String> nbrplaces() {
        return Arrays.stream(Module).map((t) -> {
            return t[3];
        }).distinct().toList();
    }
    
    /*public static List<String> idgroupemodule() {
        return Arrays.stream(Module).map((t) -> {
            return t[4];
        }).unordered().distinct().toList();
    }*/
    
    public static void afficheTousLesModules(Connection con) throws SQLException {
        try(Statement st = con.createStatement()){
           ResultSet res = st.executeQuery("select * from Modules");
           while (res.next()){
               int id  = res.getInt("id");
               String nomModules = res.getString("Noms des modules");
               String description = res.getString("description");
               String nbrPlace = res.getString("nbrPlace");
               String idgroupemodule = res.getString("idgroupemodule");
               // String date = res.getString("date");
               // java.sql.Date dn = res.getDate("6");
               // System.out.println(id+" : "+ nom + " né le "+ dn);
           }
       }
    }
}