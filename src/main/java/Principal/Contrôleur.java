/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Principal;

import static Principal.VuePrincipale.inscriptionExistsE;
import static Principal.VuePrincipale.inscriptionExistsA;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author stanislasallouche
 */
public class Contrôleur {
    
    private int état; 
    private VuePrincipale vue;
    
    public Contrôleur(VuePrincipale vue){
        this.vue = vue; 
    }
    
    public void changeEtat(int etat){
        if (etat == 1){
            //TOUS LES BOUTONS SAUF CONNEXION DESACTIVÉS';
            this.vue.getGroupe4().setVisible(false);
            this.vue.getGroupe1().setVisible(false);
            this.vue.getGroupe2().setVisible(false);
            this.vue.getGroupe3().setVisible(false);
            this.vue.getLogout().setVisible(false);
            
            
            
                    
        }
        
        if (etat == 10 ){
            //Partie ADMIN
        }
        
        if(etat == 200){
            //PARTIE Élève
        }
       
    }
    
    
    public void BoutonConnex(ActionEvent t){
        this.changeEtat(1);
    }
    
    public void ConnexAdmin(ActionEvent t){
        this.changeEtat(10);
    }
    
    public void ConnexÉle(ActionEvent t){
        this.changeEtat(200);
    }
    
    
    
    public void VérifConn(String Email, String MDP) throws SQLException{
        
            Connection con = ConnectSGBD.connectionLocalPostgresql();
            
            
                if (inscriptionExistsE(con, Email, MDP) == true ){
                    this.changeEtat(200);
                }else if(inscriptionExistsA(con, Email, MDP)== true){
                    this.changeEtat(10);
                }
                    
           
                
            
                
            
        
    }
    
}
