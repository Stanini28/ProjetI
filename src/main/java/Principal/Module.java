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

/**
 *
 * @author racha
 */
public class Module {
    
    public static final String[][] Module = new String[][]{
        {"1", "Espagnol","jsp", "34", "3"},
        {"2", "Negociate to succeed", "jsp", "27", "3"},
        {"3", "Allemand 2LF", "jsp", "28", "2"},
        {"4", "Tutorat", "jsp", "20", "3"},
        {"5", "Expression théatrale", "jsp", "32", "1"},
        {"6", "PRAP", "jsp", "35", "2"},
        {"7", "Histoire du design","jsp", "28","2"},
        {"8", "Maquette numérique", "jsp", "28", "1"},
        {"9", "Satellites", "jsp", "28", "2"}
    };
    
    public static List<String> intitule() {
        return Arrays.stream(Module).map((t) -> {
            return t[1];
        }).unordered().distinct().toList();
    }
    
    public static List<String> description() {
        return Arrays.stream(Module).map((t) -> {
            return t[2];
        }).unordered().distinct().toList();
    }
    
    public static List<String> nbrplaces() {
        return Arrays.stream(Module).map((t) -> {
            return t[3];
        }).unordered().distinct().toList();
    }
    
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