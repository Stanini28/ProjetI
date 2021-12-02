/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Principal;

import java.awt.event.ActionEvent;

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
    
}
