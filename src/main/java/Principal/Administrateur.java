/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author racha
 */
public class Administrateur {
    
    public static final String[][] Administrateur = new String[][]{
    
        {"1", "Curtis", "Mainville", "Curtis.mainville@insa-strasbourg.fr", "2/10/1969", "123456789"},
        {"2", "Viollette", "Bélanger", "Viollette.elanger@insa-strasbourg.fr", "6/10/1974","viollettebelanger"},
        {"3", "Warrane", "Raymond", "Warrane.raymond@insa-strasbourg.fr", "11/11/1987", "warrane1111"},
        {"4", "Rosemarie", "Roux", "Rosemarie.roux@insa-strasbourg.fr", "7/6/1996", "987654321"},
        {"5", "Bradamate", "Rossignol", "Bradamate.rossignol@insa-strasbourg.fr", "6/25/1974", "25061974"},
        {"6", "Céline", "Bertrand", "Celine.bertrand@insa-strasbourg.fr", "12/5/1988", "celinebertrand"},
        {"7", "Françoise", "Raymond", "Francoise.raymond@insa-strasbourg.fr", "4/17/2000", "17042000"},
        {"8", "Granville", "Baron", "Granville.baron@insa-strasbourg.fr", "8/6/1965","1965"},
        {"9", "Serge", "Caya", "Serge.caya@insa-strasbourg.fr", "3/1/1957", "01031957"},
        {"10", "Dixie", "Couet", "Dixie.couet@insa-strasbourg.fr", "6/21/1956", "dixiecouet21"},
        {"11", "Melusina", "Barrientos", "Melusina.barrientos@insa-strasbourg.fr", "3/8/1986", "0000"},
        {"12", "Isaac", "Auclair", "Isaac.auclair@insa-strasbourg.fr", "6/22/1953", "1234"},
        {"13", "Jeannine", "Poulin", "Jeannine.poulin@insa-strasbourg.fr", "1/1/1952", "jeanninepoulin"},
        {"14", "Maurice", "Pirouet", "Maurice.pirouet@insa-strasbourg.fr", "10/28/1963", "28101963"},
        {"15", "Marc", "Meunier", "Marc.meunier@insa-strasbourg.fr", "9/3/1947", "mamanjetaime"}
        
    };
    
    public static List<String> nom() {
        return Arrays.stream(Administrateur).map((t) -> {
            return t[2];
        })/*.distinct()*/.toList();
    }

    public static List<String> prenom() {
        return Arrays.stream(Administrateur).map((t) -> {
            return t[1];
        })/*.distinct()*/.toList();
    }
    
    public static List<String> email() {
        return Arrays.stream(Administrateur).map((t) -> {
            return t[3];
        }).distinct().toList();
    }
    public static List<String> dateNaissance() {
        return Arrays.stream(Administrateur).map((t) -> {
            return t[4];
        })/*.distinct()*/.toList();
    }
    public static List<String> mdp() {
        return Arrays.stream(Administrateur).map((t) -> {
            return t[5];
        })/*.distinct()*/.toList();
    }
        
    public static void afficheTousLesAdministrateurs(Connection con) throws SQLException {
        try(Statement st = con.createStatement()){
           ResultSet res = st.executeQuery("select * from Administrateur");
           while (res.next()){
               int id  = res.getInt("id");
               String prenom = res.getString("prenom");
               String nom = res.getString("nom");
               String email = res.getString("email");
               String mdp = res.getString("mot de passe");
               String date = res.getString("date de naissance");
           }
       }
    }
        /*public static void CreationUnEtudiant (Connection con, String noms, String prenoms, String email, String specialite) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
        """
        insert into Etudiants (noms, prenoms, email, specialite)
        values (?,?,?,?)
        """)){
            pst.setString(1, noms);
            pst.setString(2, prenoms);
            pst.setString(3, email);
            pst.setString(4, specialite);
            pst.executeUpdate();
        }
    }*/
    
    /*public static void CreationUnModule (Connection con, String intitule, String description, String nbrplaces) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
        """
        insert into Modules (intitule, description, nbrplaces)
        values (?,?,?)
        """)){
            pst.setString(1, intitule);
            pst.setString(2, description);
            pst.setString(3, nbrplaces);
            pst.executeUpdate();
        }
    }*/
    
    /*public static void CreationUnSemestre (Connection con, int Année, int Semestre, int NumeroSemestre) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
        """
        insert into Semestre (Année,Semestre,NumeroSem)
        values (?,?,?)
        """)){
            pst.setInt(1, Année);
            pst.setInt(2, Semestre);
            pst.setInt(3,NumeroSemestre);
            pst.executeUpdate();
        }
    }*/ 
}