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
        {"1", "Espagnol","Esp", "34", "1"},
        {"2", "Negociate to succeed", "NTS", "27","6"},
        {"3", "Allemand 2LF", "A2LF", "28", "1"},
        {"4", "Tutorat", "TUT", "20", "5"},
        {"5", "Expression théatrale", "jsp", "32", "2"},
        {"6", "PRAP", "PRA", "35", "3"},
        {"7", "Histoire du design","HDD", "28", "5"},
        {"8", "Maquette numérique", "MQN", "28", "6"},
        {"9", "Satellites", "STL", "28", "4"},
        {"10", "Init Plasturgie", "IPL", "33", "3"},
        {"11", "Python", "PYT", "40", "4"},
        {"12", "Portugais", "POR", "33", "1"},
        {"13", "Expression Ecrite", "EEC", "16", "2"},
            
        {"14", "Cinéma","CMA", "20", "7"},
        {"15", "Photographie", "PHGP", "20", "7"},
        {"16", "Botanique", "BTN", "30", "8"},
        {"17", "Risques pour l'environnement","RPE", "28", "8"},
        {"18", "Commission humanitaire", "CH", "15", "9"},
        {"19", "Cours particulier", "CP", "15", "9"},
        {"20", "Sécurité routière","SR", "19", "10"},
        {"21", "Les règles d'un recyclage réussi", "RRR", "12", "10"},
        {"22", "Art plastiques", "", "28", "11"},
        {"23", "Sports","EPS", "28", "11"},
        {"24", "Arabe", "AR", "28", "13"},
        {"25", "Anglais LV1", "ANG", "28", "13"},
        {"26", "Chimie", "CH", "25", "12"},
        {"27", "l'Informatique dans l'Ingénierie ", "II", "22", "12"},
        {"28", "Mathématiques approfondi", "MA", "30", "14"},
        {"29", "Physique théorique", "PT", "40", "14"},
        {"30", "Découverte des métiers", "DM", "40", "15"},
        {"31", "Biologie", "BIO", "20", "15"},
    };
    
    public static List<String> intitule() {
        return Arrays.stream(Module).map((t) -> {
            return t[1];
        })/*.distinct()*/.toList();
    }
    
    public static List<String> description() {
        return Arrays.stream(Module).map((t) -> {
            return t[2];
        })/*.distinct()*/.toList();
    }
    
    public static List<String> nbrplaces() {
        return Arrays.stream(Module).map((t) -> {
            return t[3];
        })/*.distinct()*/.toList();
    }
    
    public static List<String> idgroupemodules() {
        return Arrays.stream(Module).map((t) -> {
            return t[4];
        })/*.unordered().distinct()*/.toList();
    }
    
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
}