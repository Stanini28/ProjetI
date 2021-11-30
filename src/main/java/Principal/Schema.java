/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

    public static void main(String[] args) {
try ( Connection con = connectPostgresql("localhost", 5432,
"postgres", "postgres", "pass")) {
schema(con); // ici le programme
} catch (ClassNotFoundException | SQLException ex) {
throw new Error(ex);
}

}

 public static void schema(Connection con) throws SQLException {
try ( Statement st = con.createStatement()) {
st.executeUpdate(
"""
create table Etudiants(
idEtudiant integer primary key generated always as identity,
Nom varchar(50) not null,
Prenom varchar(50) not null,
email varchar(50) not null,
dateNaissance date,
mdp varchar(50)
)
""");

st.executeUpdate(
"""
create table Modules(
idModule integer primary key generated always as identity,
Intitule varchar(50) not null,
Description varchar(100) not null,
nbrPlaces integer
)
""");

st.executeUpdate(
"""
create table GroupeDeModules(
idGroupeModule integer primary key generated always as identity,
Nom varchar(50) not null,
nbretudiants integer
)
""");

st.executeUpdate(
"""
create table Administrateur(
idAdmin integer primary key generated always as identity,
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
idSemestre integer primary key generated always as identity,
Année integer,
NumeroSem integer
)
""");
}

}
}
    
    
    
      
    

    
    

