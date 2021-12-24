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
public class GroupeDeModules {
    
    public static final String[][] GroupeDeModules = new String[][]{
        {"1","Langues","30","1"},
        {"2","Litteraire","30","1"},
        {"3","Autres", "30", "1"},
        {"4","Science", "30","2"},
        {"5","Découverte", "30","2"},
        {"6","Autres 2", "30","2"}
    };
    
    public static List<String> Nom() {
        return Arrays.stream(GroupeDeModules).map((t) -> {
            return t[1];
        }).toList();
    }
    
    public static List<String> nbretudiants() {
        return Arrays.stream(GroupeDeModules).map((t) -> {
            return t[2];
        }).toList();
    }
    
    public static List<String> idSemestre() {
        return Arrays.stream(GroupeDeModules).map((t) -> {
            return t[3];
        }).toList();
    }
   
   
    public static void afficheTousLesGroupesDeModules(Connection con) throws SQLException {
        try(Statement st = con.createStatement()){
           ResultSet res = st.executeQuery("select * from GroupeDeModules");
           while (res.next()){
               int id  = res.getInt("id");
               String nom = res.getString("Noms des groupes de module");
           }
       }
    }
}
