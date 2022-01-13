/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Principal;

import static Principal.Schema.connectPostgresql;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.notification.Notification;
import static com.vaadin.flow.component.notification.Notification.Position.MIDDLE;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.router.RouteParameters;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.material.Material;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author stanislasallouche
 */
@Route("")

@Theme(value = Material.class)
public class Login extends Composite<LoginOverlay> {
    
    private String IDA;
   public String IDE;
   private String Login;
   private String MDP;
   private VueAdministrateur VA;
   private int Etat;
   

    public Login() throws ClassNotFoundException, SQLException {
        Connection con = connectPostgresql("localhost", 5432,
                "postgres", "postgres", "passe");
        
        
        
        LoginOverlay loginoverlay = getContent();
        loginoverlay.setOpened(true);

        loginoverlay.addLoginListener(event -> {
            this.Login= event.getUsername();
            this.MDP = event.getPassword();
            
          
            
            try {
                if(ExistenceE(con, Login, MDP)){
                    Notification.show("AHBFDUEIZK");
                    this.IDE= IDe(con, Login, MDP);
                    
                    UI.getCurrent().navigate(VueEtudiant.class, new RouteParameters("userID",this.IDE));
                }else
                    if(ExistenceA(con, Login, MDP)){
                    this.IDA= IDa(con, Login, MDP);
                    UI.getCurrent().navigate(VueAdministrateur.class, new RouteParameters("userID",this.IDA));
                }else {
                       Notification.show("Problème sur la Connexion!", 500, MIDDLE);
                       
                       
                     
                    }
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                Notification.show("dez");
            }

        });
    }
    
    public static boolean ExistenceA(Connection con, String login, String MDP) throws SQLException{
        String strql= "SELECT * from Administrateur WHERE email ='"+ login +"'AND mdp ='" +
                MDP + "'";
        Statement st = con.createStatement(); 
            ResultSet resultset = st.executeQuery(strql);
            return resultset.next();
    }
    
    
    public static boolean ExistenceE(Connection con, String login, String MDP) throws SQLException{
        String strql= "SELECT * from etudiants WHERE email ='"+ login +"'AND mdp ='" +
                MDP + "'";
        Statement st = con.createStatement(); 
            ResultSet resultset = st.executeQuery(strql);
            
            return resultset.next();
    }

        
     public static String IDe(Connection con, String login, String MDP) throws SQLException{
        String strql= "SELECT * from etudiants WHERE email ='"+ login +"'AND mdp ='" +
                MDP + "'";
        Statement st = con.createStatement(); 
            ResultSet resultset = st.executeQuery(strql);
            String id ="";
            while(resultset.next()){
                id = resultset.getString("id");
            }
            return id;
    }

    public String getLogin() {
        return Login;
    }

    public String getMDP() {
        return MDP;
    }
    
     public static String IDa(Connection con, String login, String MDP) throws SQLException{
        String strql= "SELECT * from administrateur WHERE email ='"+ login +"'AND mdp ='" +
                MDP + "'";
        Statement st = con.createStatement(); 
            ResultSet resultset = st.executeQuery(strql);
            String id="";
            while(resultset.next()){
                id = resultset.getString("id");
                
            }
            return id;
    }

    public String getIDA() {
        return IDA;
    }
    
   
    
    

    

  
     
   
   
    
}
    

    

