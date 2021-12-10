/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Principal;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author stanislasallouche
 */
public class Login extends VerticalLayout {
    
    private VuePrincipale VP;
    
    
    public Login(VuePrincipale VP, Button Log, LoginOverlay LG){
        
        add(LG);
        Log.addClickListener(event -> LG.setOpened(true));
        
        
        
}
    
    public static void inscriptionExistsE(Connection con, String email, String mdp) throws SQLException {
        try ( Statement st = con.createStatement();  
                ResultSet test = st.executeQuery("select * from etudiants where "
                + "email = " + email
                + " and mdp = " + mdp)) {
            if (!test.next()){
                Notification.show("Problème dans le mot de passe ou le login");
            }else{
                Notification.show("C'est bon!");
            }
            
        }
    }

    public static void inscriptionExistsA(Connection con, String email, String mdp) throws SQLException {
        try ( Statement st = con.createStatement();  
                ResultSet test = st.executeQuery("select * from etudiants where "
                + "email = " + email
                + " and mdp = " + mdp)) {
            if (!test.next()){
                Notification.show("Problème dans le mot de passe ou le login");
            }else{
                Notification.show("C'est bon!");
            }
            
        }
    }

    
    
    
        
}
    
    

