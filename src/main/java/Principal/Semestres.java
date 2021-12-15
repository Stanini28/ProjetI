/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;
import static fr.insa.beuvron.cours.m3New.tds.TD1.BdDTD1.foreignKeys;
import static fr.insa.beuvron.cours.m3New.tds.TD1.BdDTD1.tables;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Admin
 */
public class Semestres {
    /*
      public static void createSemestres(Connection con,int maxAnnee) 
              throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                """
                   public static void createSemestres(Connection con,int maxAnnee) 
                 INSERT INTO Semestre (annee,semestre,NumeroSem)
                 VALUES (?,?,?)
               """)) {
            con.setAutoCommit(false);
            int NumeroSemestre=1;
            for (int i = 1 ; i <= maxAnnee; i++) {
                for (int s = 1; s <= 2; s++) {
                    pst.setInt(1, i);
                    pst.setInt(2, s);
                    pst.setInt(3,NumeroSemestre);
                    pst.executeUpdate();
                    NumeroSemestre=NumeroSemestre+1;
                }
            }
            con.commit();
        } catch (SQLException ex) {
            con.rollback();
            System.out.println("ERROR : problem during createSemestres");
            throw ex;
        }
    }
      */      
}
