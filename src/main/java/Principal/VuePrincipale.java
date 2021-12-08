package Principal;

import static Principal.Login.inscriptionExistsA;
import static Principal.Login.inscriptionExistsE;
import static Principal.Schema.connectPostgresql;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author stanislasallouche
 */
@Route("")

public class VuePrincipale extends VerticalLayout {

    private Button Connection;
    private Button Élève;
    private Button Groupe1;
    private Button Groupe2;
    private Button Groupe3;
    private Button Groupe4;
    private Button Logout;
    private Contrôleur Controleur;
    private HorizontalLayout HL;
    private Connection con;
    private Login Log;
    private int etat;
    private LoginOverlay LG;

    
    
    public VuePrincipale(int etat) throws IOException, ClassNotFoundException, SQLException {

        

        this.Groupe1 = new Button("Groupe1");
        this.Groupe2 = new Button("Groupe2");
        this.Groupe3 = new Button("Groupe3");
        this.Groupe4 = new Button("Groupe4");
        this.Connection= new Button("Connexion");
        this.LG= new LoginOverlay();
        
        
        this.Connection.addClickListener((t)->{
            this.add(new Login(this, Connection, this.LG));
        });

        List<String> mdp = Administrateur.mdp();
        List<String> email = Administrateur.email();
        
        this.LG.addLoginListener(event ->{
            int j=0;
            for (int i=0; i< email.size(); i++){
                if ( event.getUsername() == email.get(i) && event.getPassword()== mdp.get(i)){
                    Notification.show("aaaa");
                    j=j+1;
                }
               if (j>0){
                   Notification.show("Erreur dans la connexion!");
               }
               
               
            }
           
        });
        
        this.Logout = new Button("Déconnexion");

        this.HL = new HorizontalLayout();

        this.Controleur = new Contrôleur(this);
        this.Controleur.changeEtat(etat);

        this.con = connectPostgresql("localhost", 5432,
                "postgres", "postgres", "passe");

        HL.add(Groupe1, Groupe2, Groupe3, Groupe4);
        add(HL, Connection);
        
        

        
        
        
    }

    public VuePrincipale() throws IOException, ClassNotFoundException, SQLException {
        this(1);
    }

  
    

    

    public Button getÉlève() {
        return Élève;
    }

    

    public Button getGroupe1() {
        return Groupe1;
    }

    public Button getGroupe2() {
        return Groupe2;
    }

    public Button getGroupe3() {
        return Groupe3;
    }

    public Button getGroupe4() {
        return Groupe4;
    }

    public Button getLogout() {
        return Logout;
    }

    public HorizontalLayout getHL() {

        return HL;
    }

}
