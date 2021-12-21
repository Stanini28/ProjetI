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
        
        {"1", "Will", "Myers", "Will.myers@insa-strasbourg.fr", "GE2", "5/7/2002"},
        {"2", "David", "Dortmund", "David.dortmund@insa-strasbourg.fr","STH", "11/1/2003"},
        {"3", "Aurore", "Benjamin", "Aurore.benjamin@insa-strasbourg.fr","STH ", "10/8/2003"},
        {"4", "Dominic", "Rochefort", "Dominic.rochefort@insa-strasbourg.fr","GM3" ,"7/8/2001"},
        {"5", "Gérard", "Panteau", "Gerard.parenteau@insa-strasbourg.fr", "GM2", "6/9/2002"},
        {"6", "Melusina", "Simon", "Melusina.simon@insa-strasbourg.fr","STH", "1/2/2003"},
        {"7", "Calandre", "Béland", "Calandre.beland@insa-strasbourg.fr","STH", "3/2/2002"},
        {"8", "Édith", "Caya", "Edith.caya@insa-strasbourg.fr", "GC5", "3/2/1999"},
        {"9", "Stanislas", "Allouche", "Stanislas.allouche@insa-strasbourg.fr", "GE2", "26/02/2002"},
        {"10", "Searlas", "Guay", "Searlas.guay@insa-strasbourg.fr", "GC3",  "1/8/2001"},
        {"11", "Pauline", "Gadbois", "Pauline.gadbois@insa-strasbourg.fr","GC2", "5/4/2002"},
        {"12", "Pablo", "Gutierrez", "Pablo.gutierrez@insa-strasbourg.fr", "GE2", "5/8/2002"},
        {"13", "Sarah", "Benhaboud", "Sarah.benhaboud@insa-strasbourg.fr", "PL2", "10/3/2002"},
        {"14", "Pierpont", "Dumont", "Pierpont.dumont@insa-strasbourg.fr", "PL4", "11/5/2000"},
        {"15", "Racha", "Gil antunes", "racha.gil_antunes@insa-strasbourg.fr", "GE2", "20/12/2002"},
        {"16", "Elena", "Gilbert", "Elena.gilbert@insa-strasbourg.fr", "GCE2", "10/8/2002"},
        {"17", "Christien", "Laderoute", "Christien.laderoute@insa-strasbourg.fr", "GCE5", "5/9/1999"},
        {"18", "Lance", "Gosselin", "Lance.gosselin@insa-strasbourg.fr", "PL4", "10/1/2000"},
        {"19", "Somerville", "Brisebois", "Somerville.brisebois@insa-strasbourg.fr", "G5", "8/5/1999"},
        {"20", "Auriville", "Beauchemin", "Auriville.beauchemin@insa-strasbourg.fr", "G2", "3/3/2002"},
        {"21", "Hugues", "Houle", "Hugues.houle@insa-strasbourg.fr", "STH", "10/6/2003"},
        {"22", "Ogier", "Josseaume", "Ogier.josseaume@insa-strasbourg.fr", "MIQ3", "3/7/2001"},
        {"23", "Armina", "Massé", "Armina.masse@insa-strasbourg.fr", "MIQ5", "4/8/1999"},
        {"24", "Océane", "Lamare", "Oceane.lamare@insa-strasbourg.fr", "MIQ4", "4/5/2000"},
        {"25", "Beltane", "Pariseau", "Beltane.pariseau@insa-strasbourg.fr", "G4", "1/8/2000"},
        {"26", "Tiffany", "Guibord", "Tiffany.guibord@insa-strasbourg.fr", "G2", "1/1/2002"},
        {"27", "Émilie", "Desnoyers", "Emilie.desnoyers@insa-strasbourg.fr", "GM5", "7/5/1999"},
        {"28", "Melville", "Duplessis", "Melville.duplessis@insa-strasbourg.fr",  "PL3", "6/6/2001"},
        {"29", "Michel", "Baril", "Michel.baril@insa-strasbourg.fr", "STH", "11/4/2002"},
        {"30", "Bertrand", "Turgeon", "Bertrand.turgeon@insa-strasbourg.fr", "GC3", "3/12/2001"},
        {"31", "Landers", "Lemieux", "Landers.lemieux@insa-strasbourg.fr", "MIQ2", "3/5/2002"},
        {"32", "Spencer", "Hastings", "Spencer.hastings@insa-strasbourg.fr", "GCE4", "12/3/2000"},
        {"33", "Serge", "Givry", "Serge.givry@insa-strasbourg.fr", "GCE2", "6/6/2001"},
        {"34", "Ahmed", "Qatari", "Ahmed.qatari@insa-strasbourg.fr", "GE4", "19/2/2000"},
        {"35", "Aleron", "Fréchette", "Aleron.frechette@insa-strasbourg.fr", "GE3", "10/5/2001"},
        {"36", "Mohammed", "Sadi", "Mohammed.sadi@insa-strasbourg.fr", "PL5", "2/8/1999"},
        {"37", "Fusberta", "Dufour", "Fusberta.dufour@insa-strasbourg.fr", "PL2", "11/3/2002"},
        {"38", "Merle", "Dupont", "Merle.dupont@insa-strasbourg.fr",  "G3", "1/9/2000"},
        {"39", "Ferrau", "Déziel", "Ferrau.deziel@insa-strasbourg.fr", "GM2", "3/4/2001"},
        {"40", "Joseph", "Laforge", "Joseph.laforge@insa-strasbourg.fr", "GM4", "10/8/1999"},
        {"41", "David", "Laderoute", "David.laderoute@insa-strasbourg.fr",  "G2", "5/4/2002"},
        {"42", "Eugène", "Busson", "Eugene.busson@insa-strasbourg.fr",  "MIQ2", "12/10/2002"},
        {"44", "Ambra", "Bourget", "Ambra.bourget@@insa-strasbourg.fr",  "GC3", "3/9/2001"},
        {"45", "Carla", "Lapointe", "Carla.lapointe@insa-strasbourg.fr", "PL5", "8/2/1998"},
        {"46", "Arthur", "Sevier", "Arthur.sevier@insa-strasbourg.fr",  "G2", "5/4/2001"},
        {"47", "Aria", "Montgomery", "Aria.montgomery@insa-strasbourg.fr", "GC3", "8/8/2001"},
        {"48", "Aubrey", "Gauthier", "Aubrey.gauthier@insa-strasbourg.fr", "GE3", "2/9/2001"},
        {"49", "Timothé", "Gies", "timothé.gies@insa-strasbourg.fr", "GC2", "5/8/2002"},
        {"50", "Rim", "El haouari", "rim.el_haouari@insa-strasbourg.fr", "MIQ5", "4/3/1999"},
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
        
