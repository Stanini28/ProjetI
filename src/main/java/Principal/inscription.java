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
 * @author Admin
 */
public class inscription {
    
    
    public static final String[][] inscription = new String[][]{
        {"1", "1","3", "5", "10","1"},
        {"2", "1", "9", "7","11","2"},
       /* {"3", "2", "", "", "","1"},
        {"4", "2", "", "", "","2"},
        {"5", "3", "", "", "","1"},
        {"6", "3", "", "", "","2"},*/
        {"3", "4","1", "13", "6","1"},
        {"4", "4", "11", "7", "8","2"},
        {"5", "4", "14", "16", "19","3"},
        {"6", "4", "20", "22", "27","4"},
        {"7", "5", "3", "5", "10","1"},
        {"8", "5", "9", "7", "8","2"},
        /*{"11", "6", "", "", "","1"},
        {"12", "6", "", "", "","2"},
        {"13", "7", "", "", "","1"},
        {"14", "7", "", "", "","2"},*/
        {"9", "8", "1", "13", "6","1"},
        {"10", "8", "9", "7", "8","2"},
        {"11", "8", "15", "17", "18","3"},
        {"12", "8", "21", "22", "26","4"},
        {"13", "8", "25", "28", "30","5"},
        {"14", "9", "3", "5", "10","1"},
        {"15", "9", "4", "11", "7","2"},
        {"16", "10","1", "13", "6","1"},
        {"17", "10", "11", "7", "8","2"},
        {"18", "10", "14", "16", "19","3"},
        {"19", "10", "21", "23", "27","4"},
    };
    
    public static List<String> idEtudiant() {
        return Arrays.stream(inscription).map((t) -> {
            return t[1];
        })/*.distinct()*/.toList();
    }
    
    public static List<String> idModuleGM1() {
        return Arrays.stream(inscription).map((t) -> {
            return t[2];
        })/*.distinct()*/.toList();
    }
    
    public static List<String> idModuleGM2() {
        return Arrays.stream(inscription).map((t) -> {
            return t[3];
        })/*.distinct()*/.toList();
    }
    
    public static List<String> idModuleGM3() {
        return Arrays.stream(inscription).map((t) -> {
            return t[4];
        })/*.unordered().distinct()*/.toList();
    }
    
    public static List<String> idSemestre() {
        return Arrays.stream(inscription).map((t) -> {
            return t[5];
        })/*.unordered().distinct()*/.toList();
    }
    /*
    public static void afficheTousLesModules(Connection con) throws SQLException {
        try(Statement st = con.createStatement()){
           ResultSet res = st.executeQuery("select * from Modules");
           while (res.next()){
               int id  = res.getInt("id");
               String nomModules = res.getString("Noms des modules");
               String description = res.getString("description");
               String nbrPlace = res.getString("nbrPlace");
               String idgroupemodules = res.getString("idgroupemodule");
           }
       }
    }
*/
}

