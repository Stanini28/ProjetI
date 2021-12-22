/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import java.sql.*;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author racha
 */
public class EtudiantAlea {
    
    public static final String[][] EtudiantAlea = new String[][]{
        
        {"1", "Will", "Myers", "Will.myers@insa-strasbourg.fr", "GE2", "5/7/2002", "coucou"},
        {"2", "David", "Dortmund", "David.dortmund@insa-strasbourg.fr","STH", "11/1/2003", "boujour"},
        {"3", "Aurore", "Benjamin", "Aurore.benjamin@insa-strasbourg.fr","STH ", "10/8/2003", "12345"},
        {"4", "Dominic", "Rochefort", "Dominic.rochefort@insa-strasbourg.fr","GM3" ,"7/8/2001", "987654"},
        {"5", "Gérard", "Panteau", "Gerard.parenteau@insa-strasbourg.fr", "GM2", "6/9/2002", "gerard"},
        {"6", "Melusina", "Simon", "Melusina.simon@insa-strasbourg.fr","STH", "1/2/2003", "simon"},
        {"7", "Calandre", "Béland", "Calandre.beland@insa-strasbourg.fr","STH", "3/2/2002", "beland"},
        {"8", "Édith", "Caya", "Edith.caya@insa-strasbourg.fr", "GC5", "3/2/1999", "caya"},
        {"9", "Stanislas", "Allouche", "Stanislas.allouche@insa-strasbourg.fr", "GE2", "26/02/2002", "2002"},
        {"10", "Searlas", "Guay", "Searlas.guay@insa-strasbourg.fr", "GC3",  "1/8/2001", "0108"},
        {"11", "Pauline", "Gadbois", "Pauline.gadbois@insa-strasbourg.fr","GC2", "5/4/2002", "gadbois"},
        {"12", "Pablo", "Gutierrez", "Pablo.gutierrez@insa-strasbourg.fr", "GE2", "5/8/2002", "PabloGutierrez"},
        {"13", "Sarah", "Benhaboud", "Sarah.benhaboud@insa-strasbourg.fr", "PL2", "10/3/2002", "benhaboud"},
        {"14", "Pierpont", "Dumont", "Pierpont.dumont@insa-strasbourg.fr", "PL4", "11/5/2000", "dumont"},
        {"15", "Racha", "Gil antunes", "racha.gil_antunes@insa-strasbourg.fr", "GE2", "20/12/2002", "0000"},
        {"16", "Elena", "Gilbert", "Elena.gilbert@insa-strasbourg.fr", "GCE2", "10/8/2002", "123456789"},
        {"17", "Christien", "Laderoute", "Christien.laderoute@insa-strasbourg.fr", "GCE5", "5/9/1999", "laderoute"},
        {"18", "Lance", "Gosselin", "Lance.gosselin@insa-strasbourg.fr", "PL4", "10/1/2000", "gosselin"},
        {"19", "Somerville", "Brisebois", "Somerville.brisebois@insa-strasbourg.fr", "G5", "8/5/1999", "maman"},
        {"20", "Auriville", "Beauchemin", "Auriville.beauchemin@insa-strasbourg.fr", "G2", "3/3/2002", "Beauchemin"},
        {"21", "Hugues", "Houle", "Hugues.houle@insa-strasbourg.fr", "STH", "10/6/2003", "Houle"},
        {"22", "Ogier", "Josseaume", "Ogier.josseaume@insa-strasbourg.fr", "MIQ3", "3/7/2001", "123456"},
        {"23", "Armina", "Massé", "Armina.masse@insa-strasbourg.fr", "MIQ5", "4/8/1999", "Massé"},
        {"24", "Océane", "Lamare", "Oceane.lamare@insa-strasbourg.fr", "MIQ4", "4/5/2000", "Lamare"},
        {"25", "Rim", "El haouari", "rim.el_haouari@insa-strasbourg.fr", "MIQ5", "4/3/1999", "rim1999"},
    };
    
        public static List<String> noms() {
        return Arrays.stream(EtudiantAlea).map((t) -> {
            return t[2];
        }).distinct().toList();
    }

    public static List<String> prenoms() {
        return Arrays.stream(EtudiantAlea).map((t) -> {
            return t[1];
        }).distinct().toList();
    }
    
    public static List<String> specialite() {
        return Arrays.stream(EtudiantAlea).map((t) -> {
            return t[4];
        }).distinct().toList();
    }
    
    public static List<String> email() {
        return Arrays.stream(EtudiantAlea).map((t) -> {
            return t[3];
        }).distinct().toList();
    }
    public static List<String> date() {
        return Arrays.stream(EtudiantAlea).map((t) -> {
            return t[5];
        }).distinct().toList();
    }
    public static List<String> mdp() {
        return Arrays.stream(EtudiantAlea).map((t) -> {
            return t[6];
        }).distinct().toList();
    }
    
    public static void afficheTousLesEtudiants(Connection con) throws SQLException {
        try(Statement st = con.createStatement()){
           ResultSet res = st.executeQuery("select * from Etudiants");
           while (res.next()){
               int id  = res.getInt("id");
               String prenom = res.getString("prenom");
               String nom = res.getString("nom");
               String email = res.getString("email");
               String specialite = res.getString("specialité");
               // String date = res.getString("date");
               // java.sql.Date dn = res.getDate("6");
               // System.out.println(id+" : "+ nom + " né le "+ dn);
           }
       }
    }  
}
        
