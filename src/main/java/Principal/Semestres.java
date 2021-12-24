/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

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
