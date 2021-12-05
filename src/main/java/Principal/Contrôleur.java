/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Principal;

import static Principal.VuePrincipale.inscriptionExistsE;
import static Principal.VuePrincipale.inscriptionExistsA;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author stanislasallouche
 */
public class Contrôleur {

    private int état;
    private VuePrincipale vue;

    public Contrôleur(VuePrincipale vue) {
        this.vue = vue;
        
        
    }

    public void changeEtat(int etat) {
        if (etat == 1) {
           this.vue.getHL().setVisible(false);
           this.vue.getHLA().setVisible(false);
           this.vue.getHLE().setVisible(false);
        }

        if (etat == 10) {
            this.vue.getHLA().setVisible(true);
        }

        if (etat == 200) {
            this.vue.getHL().setVisible(true);
            this.vue.getHLE().setVisible(true);
        }

    };
    
    
    

}
