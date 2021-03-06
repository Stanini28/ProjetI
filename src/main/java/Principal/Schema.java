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
import java.sql.ResultSet;


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
"postgres", "postgres", "passe")) {
    schema(con);
    createExemple(con);
    //test1(con);
    //test2(con);
    //test3(con);
    //deleteSchema(con);
    //ModuleSemestre(con);
    //ModuleSuivi(con);
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

st.executeUpdate(
"""
create table Voeux(
id integer primary key generated always as identity,
idEtudiant integer,
idVoeu1GM1 integer,
idVoeu2GM1 integer,
idVoeu1GM2 integer,
idVoeu2GM2 integer,
idVoeu1GM3 integer,
idVoeu2GM3 integer,
idSemestre integer,

constraint FK0 foreign key (idEtudiant) references Etudiants(id),
constraint FK1 foreign key (idVoeu1GM1) references Modules(id),
constraint FK2 foreign key (idVoeu2GM1) references Modules(id),
constraint FK3 foreign key (idVoeu1GM2) references Modules(id),
constraint FK4 foreign key (idVoeu2GM2) references Modules(id),
constraint FK5 foreign key (idVoeu1GM3) references Modules(id),
constraint FK6 foreign key (idVoeu2GM3) references Modules(id)
)
""");

st.executeUpdate(
"""
create table Inscription(
id integer primary key generated always as identity,
idEtudiant integer,
idModuleGM1 integer,
idModuleGM2 integer,
idModuleGM3 integer,
idSemestre integer,

constraint FK0 foreign key (idEtudiant) references Etudiants(id),
constraint FK1 foreign key (idModuleGM1) references Modules(id),
constraint FK2 foreign key (idModuleGM2) references Modules(id),
constraint FK3 foreign key (idModuleGM3) references Modules(id),
constraint FKS foreign key (idSemestre) references Semestre(id)
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

st.executeUpdate(
"""
alter table GroupeDeModules
add column idSemestre integer,
add constraint FKSemetre
foreign key (idSemestre)
references Semestre(id)
""");
/*
st.executeUpdate(
"""
alter table Inscription

add constraint FK1
foreign key (idVoeu1GM1)
references Modules(id)

add constraint FK2
foreign key (idVoeu2GM1)
references Modules(id)
""");
*/
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
           //int inti=0;
           //int desc=0;
           //int nbrp=0;
           //int idGM=0;
           //while((inti<intitule.size())&&(desc<description.size())&&(nbrp<nbrplaces.size())&&(idGM<idgroupemodules.size())){
            for (int i = 0; i < intitule.size(); i++) {    
                pst.setString(1, intitule.get(i));
                pst.setString(2, description.get(i));
                pst.setString(3, nbrplaces.get(i));
                pst.setInt(4,Integer.valueOf(idgroupemodules.get(i)));
                pst.executeUpdate();
                //inti=inti+1;
                //desc=desc+1;
                //nbrp=nbrp+1;
                //idGM=idGM+1;
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
     
    public static void createGroupeDeModules(Connection con) throws SQLException {
        List<String> Nom = GroupeDeModules.Nom();
        List<String> nbretudiants = GroupeDeModules.nbretudiants();
        List<String> idSemestre = GroupeDeModules.idSemestre();
        try (PreparedStatement pst = con.prepareStatement(
                """
               INSERT INTO GroupeDeModules (Nom, nbretudiants, idSemestre)
                 VALUES (?, ?, ?)
               """)) {
            con.setAutoCommit(false);
            //int N = 0;
            //int idS = 0;
            for (int i = 0; i < Nom.size(); i++) {
                pst.setString(1, Nom.get(i));
                pst.setInt(2,Integer.valueOf(nbretudiants.get(i)));
                pst.setInt(3,Integer.valueOf(idSemestre.get(i)));
                pst.executeUpdate();
                //N = N+1;
                //idS = idS+1;
            }
            con.commit();
        } catch (SQLException ex) {
            con.rollback();
            System.out.println("ERROR : problem during createGroupeDeModules");
            throw ex;
        }
    }
    
    public static void createInscription(Connection con) throws SQLException {
        List<String> idetudiants = inscription.idEtudiant();
        List<String> idModuleGM1 = inscription.idModuleGM1();
        List<String> idModuleGM2 = inscription.idModuleGM2();
        List<String> idModuleGM3 = inscription.idModuleGM3();
        List<String> idSemestre = inscription.idSemestre();
        try (PreparedStatement pst = con.prepareStatement(
                """
               INSERT INTO Inscription (idetudiant, idmodulegm1,idmodulegm2,idmodulegm3, idSemestre)
                 VALUES (?,?,?,?,?)
               """)) {
            con.setAutoCommit(false);
           //int idM1=0;
           //int idM2=0;
           //int idM3=0;
            for (int i = 0; i < idetudiants.size(); i++) {
                
                pst.setInt(1, Integer.valueOf(idetudiants.get(i)));
                pst.setInt(2,Integer.valueOf(idModuleGM1.get(i)));
                pst.setInt(3,Integer.valueOf(idModuleGM2.get(i)));
                pst.setInt(4,Integer.valueOf(idModuleGM3.get(i)));
                pst.setInt(5,Integer.valueOf(idSemestre.get(i)));
                pst.executeUpdate();
            }
            con.commit();
        } catch (SQLException ex) {
            con.rollback();
            System.out.println("ERROR : problem during createInscription");
            throw ex;
        }
    }
         
    public static void createExemple(Connection con) throws SQLException {
    Random r = new Random(999999999);
    createEtudiantAlea(con);
    createAdministrateur(con);
    createSemestres(con,5);
    createGroupeDeModules(con);
    createModules(con);
    createInscription(con);
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
    
    public static void ModuleSemestre (Connection con) throws SQLException {
        List<String> Nom = GroupeDeModules.Nom();
        List<String> nbretudiants = GroupeDeModules.nbretudiants();
        int semestre = ConsoleFdB.entreeInt("Quel est votre semestre ?");
        // si c'est l'id (= c'est le 1 du S1) du semestre 1 alors on affiche les caractéristiques des groupes de modules du S1
        if (semestre == 1){
            for (int i = 0; i < 3; i++){
            System.out.println(Nom.get(i));
            System.out.println(nbretudiants.get(i));
            }
        }
        // si c'est l'id du semestre 2 alors on affiche les caractéristiques des groupes de modules du S2
        else if (semestre == 2){
            for (int i = 3; i < 6; i++){
            System.out.println(Nom.get(i));
            System.out.println(nbretudiants.get(i));
            }
        }
    }
    
    public static void ModuleSuivi (Connection con) throws SQLException {
        String module = ConsoleFdB.entreeString("Quel est le module recherchée ? ");
        int semestre = ConsoleFdB.entreeInt("Pour quel semestre ?");
        try (Statement st = con.createStatement()) {
         ResultSet rs = st.executeQuery("select nom, prenom, specialite from etudiants"+ 
         "join inscription on etudiants.id = inscription.idetudiant" +
         "join modules on modules.id = idmodulegm1 or modules.id = idmodulegm2 or modules.id = idmodulegm3"+
         "where intitule =" + module + "and inscription.idsemestre =" + semestre);
                while(rs.next()){
                    String nom = rs.getString("nom");
                    String prenom = rs.getString("prenom");
                    String spe = rs.getString("specialite");
                    System.out.println(nom + prenom + spe);
                }
            }
        }
}