/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;
import static Principal.Schema.connectPostgresql;
import static Principal.Schema.createExemple;
import static Principal.Schema.schema;
import com.vaadin.flow.component.notification.Notification;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.io.*;
/**
 *
 * @author Admin
 */

public class SauvegardeTxt {
   
    public static void CreationFichier(String NomFichier,String chemin){
        try{
            String route=chemin+NomFichier+".txt";
            File file=new File(route);
            //si le fichier n'existe pas il est créé
            if(!file.exists()){
                file.createNewFile();
            }
        }catch(Exception e){
            System.out.println("probleme pendant création fichier");
        }
    }
     
public static void CopyModules(Connection con,String NomFichier,String chemin) throws SQLException {
        try (Statement st = con.createStatement()) {
   st.execute("copy (SELECT modules.id,modules.idgroupesmodules from modules) to '"+chemin+NomFichier+".txt' with delimiter ';'");
        }catch(SQLException ex){
            System.out.println("ERROR : problem during CopyModules");
            throw ex;
    } 
    }

public static void SauvegardeModules(Connection con)throws SQLException{
    String chemin="C:\\Users\\Admin\\Desktop\\Sauvegarde\\InformationSup\\";
    try(Statement st=con.createStatement()){ 
           CreationFichier("Modules",chemin);
           CopyModules(con,"Modules",chemin); 
    }catch(SQLException ex){
            System.out.println("ERROR : problem during sauvegardeModules");
            throw ex;
    }
}
    
public static void CopyVoeuxId(Connection con,String NomFichier, String chemin) throws SQLException {
        try (Statement st = con.createStatement()) {
   st.execute("copy (Select voeux.idetudiant, (voeux.idvoeu1gm1||','||voeux.idvoeu2gm1),(voeux.idvoeu1gm2||',' ||voeux.idvoeu2gm2), (voeux.idvoeu1gm3||','||voeux.idvoeu2gm3 ) from voeux) to '"+chemin+NomFichier+".txt' with delimiter ';'");
        }catch(SQLException ex){
            System.out.println("ERROR : problem during CopyVoeuxId");
            throw ex;
        } 
    }

public static void SauvegardeVoeuxId(Connection con)throws SQLException{
     String chemin="C:\\Users\\Admin\\Desktop\\Sauvegarde\\InformationSup\\";
    try(Statement st=con.createStatement()){ 
           CreationFichier("VoeuxId",chemin);
           CopyVoeuxId(con,"VoeuxId",chemin); 
    }catch(SQLException ex){
            System.out.println("ERROR : problem during sauvegardevoeuxId");
            throw ex;
    }
}

public static void CopyEtudiants(Connection con,String NomFichier,String chemin) throws SQLException {
        try (Statement st = con.createStatement()) {
   st.execute("copy etudiants to '"+chemin+NomFichier+".txt' with delimiter '|' csv header quote ','");
        }catch(SQLException ex){
            System.out.println("ERROR : problem during CopyEtudiants");
            throw ex;
        } 
    }

public static void SauvegardeEtudiants(Connection con)throws SQLException{
      String chemin="C:\\Users\\Admin\\Desktop\\Sauvegarde\\InformationSup\\";
    try(Statement st=con.createStatement()){ 
           CreationFichier("Etudiants",chemin);
           CopyEtudiants(con,"Etudiants",chemin); 
    }catch(SQLException ex){
            System.out.println("ERROR : problem during sauvegardeEtudiants");
            throw ex;
    }
}
//select (etudiants.nom||' '||etudiants.prenom),modules.intitule FROM etudiants JOIN voeux ON etudiants.id=voeux.idetudiant JOIN modules ON (voeux.idvoeu1gm1=modules.id or voeux.idvoeu2gm1=modules.id or voeux.idvoeu1gm2=modules.id or voeux.idvoeu2gm2=modules.id or voeux.idvoeu1gm3=modules.id or voeux.idvoeu2gm3=modules.id) 
public static void CopyVoeuxNoms(Connection con,String NomFichier,String chemin) throws SQLException {
        try (Statement st = con.createStatement()) {
   st.execute("copy (select (etudiants.nom||' '||etudiants.prenom)as NomPrenom,modules.intitule,modules.idgroupesmodules,voeux.idsemestre FROM etudiants JOIN voeux ON etudiants.id=voeux.idetudiant JOIN modules ON (voeux.idvoeu1gm1=modules.id or voeux.idvoeu2gm1=modules.id or voeux.idvoeu1gm2=modules.id or voeux.idvoeu2gm2=modules.id or voeux.idvoeu1gm3=modules.id or voeux.idvoeu2gm3=modules.id)) to '"+chemin+NomFichier+".txt' with delimiter '|' csv header quote ','");
        }catch(SQLException ex){
            System.out.println("ERROR : problem during CopyEtudiants");
            throw ex;
        } 
    }

public static void SauvegardeVoeuxNoms(Connection con)throws SQLException{
      String chemin="C:\\Users\\Admin\\Desktop\\Sauvegarde\\InformationSup\\";
    try(Statement st=con.createStatement()){ 
           CreationFichier("VoeuxNoms",chemin);
           CopyVoeuxNoms(con,"VoeuxNoms",chemin); 
    }catch(SQLException ex){
            System.out.println("ERROR : problem during sauvegardeEtudiants");
            throw ex;
    }
}

public static void CopyNombreGM(Connection con,String NomFichier,String chemin) throws SQLException {
        try (Statement st = con.createStatement()) {
   st.execute("copy (select count(groupedemodules.id) from groupedemodules) to '"+chemin+NomFichier+".txt'");
        }catch(SQLException ex){
            System.out.println("ERROR : problem during CopyNombreGM");
            throw ex;
        } 
    }

public static void SauvegardeNombreGM(Connection con)throws SQLException{
      String chemin="C:\\Users\\Admin\\Desktop\\Sauvegarde\\InformationSup\\";
    try(Statement st=con.createStatement()){ 
           CreationFichier("NombreGM",chemin);
           CopyNombreGM(con,"NombreGM",chemin); 
    }catch(SQLException ex){
            System.out.println("ERROR : problem during sauvegardeNombreGM");
            throw ex;
    }
}

public static void CopyNombreM(Connection con,String NomFichier,String chemin) throws SQLException {
        try (Statement st = con.createStatement()) {
   st.execute("copy (select count(modules.id) from modules) to '"+chemin+NomFichier+".txt'");
        }catch(SQLException ex){
            System.out.println("ERROR : problem during CopyNombreM");
            throw ex;
        } 
    }

public static void SauvegardeNombreM(Connection con)throws SQLException{
      String chemin="C:\\Users\\Admin\\Desktop\\Sauvegarde\\InformationSup\\";
    try(Statement st=con.createStatement()){ 
           CreationFichier("NombreM",chemin);
           CopyNombreM(con,"NombreM",chemin); 
    }catch(SQLException ex){
            System.out.println("ERROR : problem during sauvegardeNombreM");
            throw ex;
    }
}

 public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {    
try ( Connection con = connectPostgresql("localhost", 5432,
"postgres", "postgres", "pass")) {
  // SauvegardeModules(con);
  //SauvegardeVoeuxId(con);
  // SauvegardeEtudiants(con);
  // SauvegardeVoeuxNoms(con);
   FichierCalcul(con);
    }catch(SQLException ex){
            System.out.println("ERROR : problem during main");
            throw ex;
    }
}

 public static Notification FichierCalcul(Connection con)throws SQLException, IOException{
      String NomFichier="FichierCalcul";
      String cheminFichierCalcul="C:\\Users\\Admin\\Desktop\\Sauvegarde\\"; 
      String FichierCalculTexte="C:\\Users\\Admin\\Desktop\\Sauvegarde\\FichierCalcul.txt";
      String FichierTexteNg="C:\\Users\\Admin\\Desktop\\Sauvegarde\\InformationSup\\NombreGM.txt";
      String FichierModulesGMTexte="C:\\Users\\Admin\\Desktop\\Sauvegarde\\InformationSup\\Modules.txt";
      String FichierVoeuxTexte="C:\\Users\\Admin\\Desktop\\Sauvegarde\\InformationSup\\VoeuxId.txt";
      
      File FichierCalcul;
      FileWriter w;
      BufferedWriter bw;
      PrintWriter pw;
      
    try(Statement st=con.createStatement()){ 
        //String route=cheminFichierCalcul+NomFichier+".txt";
           SauvegardeModules(con);
           SauvegardeNombreGM(con);
           SauvegardeNombreM(con);
           SauvegardeVoeuxId(con);
           
           
           CreationFichier(NomFichier,cheminFichierCalcul);
           
          File FichierVoeux=new File(FichierVoeuxTexte);
           File FichierNg=new File(FichierTexteNg);
           FichierCalcul=new File(FichierCalculTexte);
           
           
           w=new FileWriter(FichierCalcul);
           pw=new PrintWriter(w);
           
           FileReader frNg = new FileReader (FichierNg);
           BufferedReader brNg = new BufferedReader(frNg);
           
           String line;
           
           line=brNg.readLine();
           System.out.println(line);
           pw.println(line);
           pw.println("2");
           pw.println("MODULES");
 
if(null!=frNg){
    frNg.close();
}

           File FichierModulesGM=new File(FichierModulesGMTexte);
           
           FileReader frMGM = new FileReader (FichierModulesGM);
           BufferedReader brMGM = new BufferedReader(frMGM);
           
           while((line=brMGM.readLine())!=null){
            pw.println(line);     
      }
    pw.println("FIN MODULES");
    if(null!=frMGM){
    frMGM.close();
}
    pw.println("CHOIX");
    
    FileReader frVoeuxId = new FileReader (FichierVoeux);
    BufferedReader brVoeuxId = new BufferedReader(frVoeuxId);
    
    while((line=brVoeuxId.readLine())!=null){
            pw.println(line);     
      }
    pw.println("FINCHOIX");
    
  if(null!=w){
      w.close();
  }         
   
  
  return Notification.show("Sauvegarde Effectuée");
           
         
    }catch(SQLException ex){
            System.out.println("ERROR : problem during sauvegardeNombreM");
            throw ex;
    }
}
   /* public static void main(String ars[]){
        try {
            String ruta = "/C:\\Users\\Admin\\Desktop\\Sauvegarde/filename.txt";
            String contenido = "Contenido de ejemplo";
            File file = new File(ruta);
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(contenido);
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    */


}

