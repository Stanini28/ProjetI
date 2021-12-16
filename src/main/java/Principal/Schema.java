/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import fr.insa.beuvron.utils.ConsoleFdB;


/**
 *
 * @author Admin
 */
public class Schema {
    
    //méthode de connection a postgres
    
    public static Connection connectPostgresql(String host,int port,
String database,String user,String pass)
throws ClassNotFoundException,SQLException{
// teste la présence du driver postgresql
Class.forName("org.postgresql.Driver");
Connection con = DriverManager.getConnection(
"jdbc:postgresql://"+host+":"+port+"/"+database, database, pass);
// fixe le plus haut degré d'isolation entre transactions
con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
return con;
}
  
    public static void main(String[] args) throws ClassNotFoundException, SQLException {    
try ( Connection con = connectPostgresql("localhost", 5432,
"postgres", "postgres", "pass")) {
    //schema(con);
    //createExemple(con);
    //test1(con);
    test2(con);
    }
}

 public static void schema(Connection con) throws SQLException {
try ( Statement st = con.createStatement()) {
st.executeUpdate(
"""
create table Etudiants(
id integer primary key generated always as identity,
Nom varchar(50) not null,
Prenom varchar(50) not null,
email varchar(50) not null,
specialite varchar(20) not null,
dateNaissance date,
mdp varchar(50)
)
""");

st.executeUpdate(
"""
create table Modules(
id integer primary key generated always as identity,
Intitule varchar(50) not null,
Description varchar(100) not null,
nbrPlaces varchar(20)
)
""");

st.executeUpdate(
"""
create table GroupeDeModules(
id integer primary key generated always as identity,
Nom varchar(50) not null,
nbretudiants integer
)
""");

st.executeUpdate(
"""
create table Administrateur(
id integer primary key generated always as identity,
Nom varchar(50) not null,
Prenom varchar(50) not null,
email varchar(50) not null,
dateNaissance date,
mdp varchar(50)
)
""");

st.executeUpdate(
"""
create table Semestre(
id integer primary key generated always as identity,
Semestre integer,
Année integer,
NumeroSem integer
)
""");

/*
//Début partie relationnel
st.executeUpdate(
"""
alter table Etudiants
add constraint FKSemetre
foreign key (idSemestre)
references Semestre(id)
""");*/

st.executeUpdate(
"""
alter table Etudiants
add column idSemestre integer,
add constraint FKSemetre
foreign key (idSemestre)
references Semestre(id)
""");

st.executeUpdate(
"""
alter table Modules
add column idGroupesModules integer,
add constraint FKGroupesModules
foreign key (idGroupesModules)
references GroupeDeModules(id)
""");
}

}
    public static void createEtudiantAlea(Connection con, int nbr,
            Random r) throws SQLException {
        List<String> noms = EtudiantAlea.noms();
        List<String> prenoms = EtudiantAlea.prenoms();
        List<String> specialite = EtudiantAlea.specialite();
        List<String> email = EtudiantAlea.email();
        //List<String> date = EtudiantAlea.date();
        try (PreparedStatement pst = con.prepareStatement(
                """
               INSERT INTO Etudiants (nom, prenom, email, specialite)
                 VALUES (?,?,?,?)
               """)) {
            con.setAutoCommit(false);

            for (int i = 0; i < nbr; i++) {
                pst.setString(1, noms.get(r.nextInt(noms.size())));
                pst.setString(2, prenoms.get(r.nextInt(prenoms.size())));
                pst.setString(4, specialite.get(r.nextInt(specialite.size())));
                pst.setString(3, email.get(r.nextInt(email.size())));
                // pst.setString(3, date.get(r.nextInt(date.size())));
                // Date asDate = Date.valueOf(dalea);
                // pst.setDate(3, asDate);
                // LocalDate minNaissance, LocalDate maxNaissance
                pst.executeUpdate();
            }
            con.commit();
        } catch (SQLException ex) {
            con.rollback();
            System.out.println("ERROR : problem during createEtudiantAlea");
            throw ex;
        }
    }
    /*public static LocalDate dateAleaBetween(LocalDate min, LocalDate max, Random r) {
        long minDay = min.toEpochDay();
        long maxDay = max.toEpochDay();
        long delta = (long) (r.nextDouble() * (maxDay - minDay + 1));
        return min.plusDays(delta);
     }*/
    public static void createModules(Connection con, int nbr,
            Random r) throws SQLException {
        List<String> intitule = Module.intitule();
        List<String> description = Module.description();
        List<String> nbrplaces = Module.nbrplaces();
        try (PreparedStatement pst = con.prepareStatement(
                """
               INSERT INTO Modules (intitule, description, nbrplaces)
                 VALUES (?, ?, ?)
               """)) {
            con.setAutoCommit(false);

            for (int i = 0; i < nbr; i++) {
                pst.setString(1, intitule.get(r.nextInt(intitule.size())));
                pst.setString(2, description.get(r.nextInt(description.size())));
                pst.setString(3, nbrplaces.get(r.nextInt(nbrplaces.size())));
                pst.executeUpdate();
            }
            con.commit();
        } catch (SQLException ex) {
            con.rollback();
            System.out.println("ERROR : problem during createEtudiantAlea");
            throw ex;
        }
    }
    
    public static void createAdministrateur(Connection con, int nbr,
            Random r) throws SQLException {
        List<String> noms = Administrateur.noms();
        List<String> prenoms = Administrateur.prenoms();
        List<String> mdp = Administrateur.mdp();
        List<String> email = Administrateur.email();
        //List<String> date = Administrateur.date();
        try (PreparedStatement pst = con.prepareStatement(
                """
               INSERT INTO Administrateur (nom, prenom, email, mdp)
                 VALUES (?,?,?,?)
               """)) {
            con.setAutoCommit(false);

            for (int i = 0; i < nbr; i++) {
                pst.setString(1, noms.get(r.nextInt(noms.size())));
                pst.setString(2, prenoms.get(r.nextInt(prenoms.size())));
                pst.setString(4, mdp.get(r.nextInt(mdp.size())));
                pst.setString(3, email.get(r.nextInt(email.size())));
                // pst.setString(3, date.get(r.nextInt(date.size())));
                // Date asDate = Date.valueOf(dalea);
                // pst.setDate(3, asDate);
                // LocalDate minNaissance, LocalDate maxNaissance
                pst.executeUpdate();
            }
            con.commit();
        } catch (SQLException ex) {
            con.rollback();
            System.out.println("ERROR : problem during createEtudiantAlea");
            throw ex;
        }
    }
    
     public static void createSemestres(Connection con,int maxAnnee) 
              throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                """
                 INSERT INTO Semestre (Année,Semestre,NumeroSem)
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
         
    public static void createExemple(Connection con) throws SQLException {
    Random r = new Random(885214156);
    createEtudiantAlea(con, 50, r);
    createAdministrateur(con, 15,r);
    createModules(con, 9, r);
    createSemestres(con,5);
    }  
    
    public static void CreationUnEtudiant (Connection con, String noms, String prenoms, String email, String specialite, String mdp) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
        """
        insert into Etudiants (nom, prenom, email, specialite, mdp)
        values (?,?,?,?,?)
        """)){
            pst.setString(1, noms);
            pst.setString(2, prenoms);
            pst.setString(3, email);
            pst.setString(4, specialite);
            pst.setString(5, mdp);
            pst.executeUpdate();
        }
    }
    
    public static void CreationUnModule (Connection con, String intitule, String description, String nbrplaces) throws SQLException {
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
    }
    
    public static void test1 (Connection con) throws SQLException{
        String noms = ConsoleFdB.entreeString("nom :");
        String prenoms = ConsoleFdB.entreeString("prenom :");
        String email = ConsoleFdB.entreeString("email :");
        String specialite = ConsoleFdB.entreeString("specialite :");
        String mdp = ConsoleFdB.entreeString("mdp :");
        CreationUnEtudiant(con, noms, prenoms, email, specialite, mdp);
    }
    
    public static void test2(Connection con) throws SQLException{
        String intitule = ConsoleFdB.entreeString("intitule du module:");
        String description = ConsoleFdB.entreeString("description :");
        String nbrplaces = ConsoleFdB.entreeString("Nombre de places :");
        CreationUnModule(con, intitule, description, nbrplaces);
    }
}