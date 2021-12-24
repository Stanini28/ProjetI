/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
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
    test1(con);
    //test2(con);
    //test3(con);
    //deleteSchema(con);
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
dateNaissance varchar(20),
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
dateNaissance varchar(20),
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
 
 
   public static void deleteSchema(Connection con) throws SQLException {
        try (Statement st = con.createStatement()) {
   st.executeUpdate(
   """
   DROP SCHEMA public CASCADE;
   CREATE SCHEMA public;
   """
   );    
        } catch (SQLException ex) {
            con.rollback();
            System.out.println("ERROR : problem during deleteSchema");
            throw ex;
        }
    }
 
    public static void createEtudiantAlea(Connection con) throws SQLException {
        List<String> Nom = EtudiantAlea.Nom();
        List<String> Prenom = EtudiantAlea.Prenom();
        List<String> specialite = EtudiantAlea.specialite();
        List<String> email = EtudiantAlea.email();
        List<String> mdp = EtudiantAlea.mdp();
        List<String> dateNaissance = EtudiantAlea.dateNaissance();
        try (PreparedStatement pst = con.prepareStatement(
                """
               INSERT INTO Etudiants (Nom, Prenom, email, specialite, mdp, dateNaissance)
                 VALUES (?,?,?,?,?,?)
               """)) {
            con.setAutoCommit(false);

            for (int i = 0; i < Nom.size(); i++) {
                pst.setString(1, Nom.get(i));
                pst.setString(2, Prenom.get(i));
                pst.setString(4, specialite.get(i));
                pst.setString(3, email.get(i));
                pst.setString(5, mdp.get(i));
                pst.setString(6, dateNaissance.get(i));
                pst.executeUpdate();
            }
            con.commit();
        } catch (SQLException ex) {
            con.rollback();
            System.out.println("ERROR : problem during createEtudiantAlea");
            throw ex;
        }
    }
    
    public static void createModules(Connection con) throws SQLException {
        List<String> intitule = Module.intitule();
        List<String> description = Module.description();
        List<String> nbrplaces = Module.nbrplaces();
        List<String> idgroupemodules = Module.idgroupemodules();
        try (PreparedStatement pst = con.prepareStatement(
                """
               INSERT INTO Modules (intitule, description, nbrplaces,idgroupesmodules)
                 VALUES (?, ?, ?,?)
               """)) {
            con.setAutoCommit(false);
           int inti=0;
           int desc=0;
           int nbrp=0;
           int idGM=0;
            while((inti<intitule.size())&&(desc<description.size())&&(nbrp<nbrplaces.size())&&(idGM<idgroupemodules.size())){
                
                pst.setString(1, intitule.get(inti));
                pst.setString(2, description.get(desc));
                pst.setString(3, nbrplaces.get(nbrp));
                pst.setInt(4,Integer.valueOf(idgroupemodules.get(idGM)));
                pst.executeUpdate();
                inti=inti+1;
                desc=desc+1;
                nbrp=nbrp+1;
                idGM=idGM+1;
            }
            con.commit();
        } catch (SQLException ex) {
            con.rollback();
            System.out.println("ERROR : problem during createModules");
            throw ex;
        }
    }

    
    public static void createAdministrateur(Connection con) throws SQLException {
        List<String> nom = Administrateur.nom();
        List<String> prenom = Administrateur.prenom();
        List<String> mdp = Administrateur.mdp();
        List<String> email = Administrateur.email();
        List<String> dateNaissance = Administrateur.dateNaissance();
        try (PreparedStatement pst = con.prepareStatement(
                """
               INSERT INTO Administrateur (nom, prenom, email, mdp, dateNaissance)
                 VALUES (?,?,?,?,?)
               """)) {
            con.setAutoCommit(false);

            for (int i = 0; i < nom.size(); i++) {
                pst.setString(1, nom.get(i));
                pst.setString(2, prenom.get(i));
                pst.setString(4, mdp.get(i));
                pst.setString(3, email.get(i));
                pst.setString(5, dateNaissance.get(i));
                pst.executeUpdate();
            }
            con.commit();
        } catch (SQLException ex) {
            con.rollback();
            System.out.println("ERROR : problem during createAdministrateur");
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
     
     public static void createGroupeDeModules(Connection con, int nbr) throws SQLException {
        List<String> Nom = GroupeDeModules.Nom();
        try (PreparedStatement pst = con.prepareStatement(
                """
               INSERT INTO GroupeDeModules (Nom)
                 VALUES (?)
               """)) {
            con.setAutoCommit(false);

            for (int i = 0; i < nbr; i++) {
                pst.setString(1, Nom.get(i));
                pst.executeUpdate();
            }
            con.commit();
        } catch (SQLException ex) {
            con.rollback();
            System.out.println("ERROR : problem during createGroupeDeModules");
            throw ex;
        }
    }
         
    public static void createExemple(Connection con) throws SQLException {
    Random r = new Random(999999999);
    createEtudiantAlea(con);
    createAdministrateur(con);
    createGroupeDeModules(con, 3);
    createModules(con);
    createSemestres(con,5);
    }  
    
    public static void CreationUnEtudiant (Connection con, String nom, String prenom, String email, String specialite, String mdp, String dateNaissance) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
        """
        insert into Etudiants (nom, prenom, email, specialite, mdp, dateNaissance)
        values (?,?,?,?,?, ?)
        """)){
            pst.setString(1, nom);
            pst.setString(2, prenom);
            pst.setString(3, email);
            pst.setString(4, specialite);
            pst.setString(5, mdp);
            pst.setString(6, dateNaissance);
            pst.executeUpdate();
        }
    }
    
    public static void CreationUnModule (Connection con, String intitule, String description, String nbrplaces, int idGroupesModules) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
        """
        insert into Modules (intitule, description, nbrplaces, idGroupesModules)
        values (?,?,?, ?)
        """)){
            pst.setString(1, intitule);
            pst.setString(2, description);
            pst.setString(3, nbrplaces);
            pst.setInt(4, idGroupesModules);
            pst.executeUpdate();
        }
    }
    
    public static void CreationUnSemestre (Connection con, int Année, int Semestre, int NumeroSem) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
        """
        insert into Semestre (Année,Semestre,NumeroSem)
        values (?,?,?)
        """)){
            pst.setInt(1, Année);
            pst.setInt(2, Semestre);
            pst.setInt(3,NumeroSem);
            pst.executeUpdate();
        }
    } 
    
    public static void test1 (Connection con) throws SQLException{
        String nom = ConsoleFdB.entreeString("nom :");
        String prenom = ConsoleFdB.entreeString("prenom :");
        String email = ConsoleFdB.entreeString("email :");
        String specialite = ConsoleFdB.entreeString("specialite :");
        String mdp = ConsoleFdB.entreeString("mdp :");
        String dateNaissance = ConsoleFdB.entreeString("dateNaissance :");
        CreationUnEtudiant(con, nom, prenom, email, specialite, mdp, dateNaissance);
    }
    
    public static void test2(Connection con) throws SQLException{
        String intitule = ConsoleFdB.entreeString("intitule du module:");
        String description = ConsoleFdB.entreeString("description :");
        String nbrplaces = ConsoleFdB.entreeString("Nombre de places :");
        int idGroupesModules = ConsoleFdB.entreeInt("Entrez un idGroupesModules compris entre 1 et 3 :");
        CreationUnModule(con, intitule, description, nbrplaces, idGroupesModules);
}

    
    public static void test3(Connection con) throws SQLException{
        int Année = ConsoleFdB.entreeInt("Année du sesmetre:");
        int Semestre = ConsoleFdB.entreeInt("Semestre :");
        int NumeroSem = ConsoleFdB.entreeInt("Numéro du semestre :");
        CreationUnSemestre(con, Année, Semestre, NumeroSem);
    }
}