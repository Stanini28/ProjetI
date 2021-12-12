/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Principal;

import static Principal.Schema.connectPostgresql;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
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
public class Login extends Composite<LoginOverlay> {

    public Login() throws ClassNotFoundException, SQLException {
        Connection con = connectPostgresql("localhost", 5432,
                "postgres", "postgres", "passe");
        
        LoginOverlay loginoverlay = getContent();
        loginoverlay.setOpened(true);

        loginoverlay.addLoginListener(event -> {

          
            
            try {
                if(Test(con, event.getUsername(), event.getPassword())){
                    Notification.show("AHBFDUEIZK");
                    UI.getCurrent().navigate("Principale");
                }else{
                    Notification.show("NON");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                Notification.show("dez");
            }

        });
    }

//    public static Notification inscriptionExistsE(Connection con, String email, String mdp) throws SQLException {
//        try ( Statement st = con.createStatement();  ResultSet test = st.executeQuery("select * from etudiants where "
//                + "email = " + email
//                + " and mdp = " + mdp)) {
//            if (!test.next()) {
//                Notification.show("Problème dans le mot de passe ou le login");
//            } else {
//                Notification.show("C'est bon!");
//            }
//
//        }
//        return
//    }

////    public static boolean inscriptionExists(Connection con, String EM, String MDP) throws SQLException {
////        try (Statement st = con.createStatement();
////                ResultSet test = st.executeQuery("select nom,mdp from Administrateur where "
////                        + " nom = " + EM
////                        + " and mdp = " + MDP)) {
////            return test.next();
////        }
////    }
    
    public static boolean Test(Connection con, String login, String MDP) throws SQLException{
        String strql= "SELECT * from Administrateur WHERE email ='"+ login +"'AND mdp ='" +
                MDP + "'";
        Statement st = con.createStatement(); 
            ResultSet resultset = st.executeQuery(strql);
            return resultset.next();
    }

}
