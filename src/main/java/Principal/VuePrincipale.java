/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Principal;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import java.io.IOException;
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

public class VuePrincipale extends VerticalLayout {

    private Button Connection;
    private Button Élève;
    private Button Administrateur;
    private Button cancelA;
    private Button cancelE;
    private Button LoginE;
    private Button LoginA;
    private Button Groupe1;
    private Button Groupe2;
    private Button Groupe3;
    private Button Groupe4;
    private Button Logout;
    private Contrôleur Controleur;
    private HorizontalLayout HL;
    private HorizontalLayout HLA;
    private HorizontalLayout HLE;
    
    private int etat;

    public VuePrincipale(int etat) throws IOException {

        Dialog dialog = new Dialog();
        dialog.getElement().setAttribute("aria-label", "Create new employee");
        Button Connection = new Button("Connection", e -> dialog.open());
        VerticalLayout dialogLayout = Choix(dialog);
        dialog.add(dialogLayout);
        add(dialog, Connection);

        
        this.Groupe1 = new Button("Groupe1");
        this.Groupe2 = new Button("Groupe2");
        this.Groupe3 = new Button("Groupe3");
        this.Groupe4 = new Button("Groupe4");
        this.Connection= new Button("Connexion");
        this.LoginA= new Button("Login");
        this.LoginE= new Button("Login");
        this.cancelA= new Button("Cancel");
        this.cancelE= new Button("Cancel");
        this.Logout= new Button ("Déconnexion");
        this.HLE= new HorizontalLayout();
        this.HLA= new HorizontalLayout();
        
        HLA.add(Logout);
        HLE.add(Logout);
        
          this.HL = new HorizontalLayout();
        HL.add(Groupe1, Groupe2, Groupe3, Groupe4);
        add(HL, HLE, HLA);
        
        
        this.Controleur = new Contrôleur(this);
        this.Controleur.changeEtat(etat);
        
        
        
    }
    
    public VuePrincipale() throws IOException{
        this(1);
    }
    
    
    
    
    

    public VerticalLayout Choix(Dialog dialog) throws IOException {

        H2 headline = new H2("Vous êtes: ");
        headline.getStyle().set("margin", "var(--lumo-space-m) 0 0 0")
                .set("font-size", "1.5em").set("font-weight", "bold");

        this.Élève = new Button("Un Élève");
        this.Administrateur = new Button("Un Administrateur");

        Élève.addClickListener(event -> {
            try {
                dialog.removeAll();
                Élève.addThemeVariants(ButtonVariant.LUMO_SMALL);
                VerticalLayout M = ConnexionE(dialog);

                dialog.add(M);
                Élève.setVisible(false);
                Administrateur.setVisible(false);

            } catch (IOException ex) {
                Logger.getLogger(VuePrincipale.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            Élève.setVisible(true);
            Administrateur.setVisible(true);
        });

        Administrateur.addClickListener(event -> {
            try {
                dialog.removeAll();
                Élève.setVisible(false);
                Administrateur.setVisible(false);
                Administrateur.addThemeVariants(ButtonVariant.LUMO_SMALL);
                VerticalLayout H = ConnexionA(dialog);
                dialog.add(H);
            } catch (IOException ex) {
                Logger.getLogger(VuePrincipale.class.getName()).log(Level.SEVERE, null, ex);
            }

            Élève.setVisible(true);
            Administrateur.setVisible(true);
        });

        HorizontalLayout buttonLayout = new HorizontalLayout(Élève,
                Administrateur);

        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.END);

        VerticalLayout dialogLayout = new VerticalLayout(headline,
                buttonLayout);
        dialogLayout.setPadding(false);
        dialogLayout.setAlignItems(FlexComponent.Alignment.STRETCH);
        dialogLayout.getStyle().set("width", "300px").set("max-width", "100%");

        return dialogLayout;
    }

    public VerticalLayout ConnexionA(Dialog dialog) throws IOException {

        H2 headline = new H2("Connexion d'un Administrateur: ");
        headline.getStyle().set("margin", "var(--lumo-space-m) 0 0 0")
                .set("font-size", "1.5em").set("font-weight", "bold");

        TextField firstNameField = new TextField("Login");
        TextField lastNameField = new TextField("Password");
        String Email = firstNameField.getValue();
        String MDP= lastNameField.getValue();
        VerticalLayout fieldLayout = new VerticalLayout(firstNameField,
                lastNameField);
        fieldLayout.setSpacing(false);
        fieldLayout.setPadding(false);
        fieldLayout.setAlignItems(FlexComponent.Alignment.STRETCH);

        

        this.cancelA.addClickListener((event -> {
            try {
                dialog.removeAll();

                VerticalLayout H = Choix(dialog);
                dialog.add(H);
            } catch (IOException ex) {
                Logger.getLogger(VuePrincipale.class.getName()).log(Level.SEVERE, null, ex);
            }
        })
        );

        
        //FAIRE LE CLICKLISTENER POUR VÉRIFIER SI C'EST BON !
        
        this.LoginA.addClickListener((event -> {
            Connection con = ConnectSGBD.connectionLocalPostgresql();
            try {
                if (inscriptionExistsA(con, Email, MDP)== false){
                    fieldLayout.add("Erreur sur le login ou le MDP");
                } else if (inscriptionExistsA(con, Email, MDP) ==true){
                    this.Controleur.changeEtat(10);
                }
            } catch (SQLException ex) {
                Logger.getLogger(VuePrincipale.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }));

        this.LoginA.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        HorizontalLayout buttonLayout = new HorizontalLayout(this.cancelA,
                this.LoginA);
        buttonLayout
                .setJustifyContentMode(FlexComponent.JustifyContentMode.END);

        VerticalLayout dialogLayout = new VerticalLayout(headline, fieldLayout,
                buttonLayout);
        dialogLayout.setPadding(false);
        dialogLayout.setAlignItems(FlexComponent.Alignment.STRETCH);
        dialogLayout.getStyle().set("width", "300px").set("max-width", "100%");

        return dialogLayout;
    }

    public VerticalLayout ConnexionE(Dialog dialog) throws IOException {

        H2 headline = new H2("Connexion d'un Élève");
        headline.getStyle().set("margin", "var(--lumo-space-m) 0 0 0")
                .set("font-size", "1.5em").set("font-weight", "bold");

        TextField firstNameField = new TextField("Login");
        TextField lastNameField = new TextField("Password");
        String Email= firstNameField.getValue();
        String MDP= lastNameField.getValue();
        
        VerticalLayout fieldLayout = new VerticalLayout(firstNameField,
                lastNameField);
        fieldLayout.setSpacing(false);
        fieldLayout.setPadding(false);
        fieldLayout.setAlignItems(FlexComponent.Alignment.STRETCH);
        
        

        

        this.cancelE.addClickListener((event -> {
            try {
                dialog.removeAll();

                VerticalLayout H = Choix(dialog);
                dialog.add(H);
            } catch (IOException ex) {
                Logger.getLogger(VuePrincipale.class.getName()).log(Level.SEVERE, null, ex);
            }
        })
        );

        
        
        this.LoginE.addClickListener((event -> {
            Connection con = ConnectSGBD.connectionLocalPostgresql();
            try {
                if (inscriptionExistsE(con, Email, MDP)== false){
                    fieldLayout.add("Erreur sur le login ou le MDP");
                } else if (inscriptionExistsE(con, Email, MDP) == true) {
                    this.Controleur.changeEtat(200);
                }
            } catch (SQLException ex) {
                Logger.getLogger(VuePrincipale.class.getName()).log(Level.SEVERE, null, ex);
            }
        }));
               
               
                
                

        //FAIRE LE CLICKLISTENER POUR VÉRIFIER SI C'EST BON !
        this.LoginE.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        HorizontalLayout buttonLayout = new HorizontalLayout(this.cancelE,
                this.LoginE);
        buttonLayout
                .setJustifyContentMode(FlexComponent.JustifyContentMode.END);

        VerticalLayout dialogLayout = new VerticalLayout(headline, fieldLayout,
                buttonLayout);
        dialogLayout.setPadding(false);
        dialogLayout.setAlignItems(FlexComponent.Alignment.STRETCH);
        dialogLayout.getStyle().set("width", "300px").set("max-width", "100%");

        return dialogLayout;
    }

    public static boolean inscriptionExistsE(Connection con, String email , String mdp) throws SQLException {
        try ( Statement st = con.createStatement();  ResultSet test = st.executeQuery("select * from etudiants where "
                + "etudiant = " + email
                + " and mdp = " + mdp)) {
            return test.next();
        }
    }
    
    public static boolean inscriptionExistsA(Connection con, String email , String mdp) throws SQLException {
        try ( Statement st = con.createStatement();  ResultSet test = st.executeQuery("select * from administrateur where "
                + "Administrateur = " + email
                + " and mdp = " + mdp)) {
            return test.next();
        }
    }

    
   
    
    
    
    
    
    public Button getConnection() {
        return Connection;
    }

    public Button getÉlève() {
        return Élève;
    }

    public Button getAdministrateur() {
        return Administrateur;
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

    public HorizontalLayout getHLA() {
        return HLA;
    }

    public HorizontalLayout getHLE() {
        return HLE;
    }

    
    
    
}
