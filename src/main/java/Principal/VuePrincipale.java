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
    private Button Groupe1;
    private Button Groupe2;
    private Button Groupe3;
    private Button Groupe4;
    private Button Logout;

    public VuePrincipale() throws IOException {

        Dialog dialog = new Dialog();
        dialog.getElement().setAttribute("aria-label", "Create new employee");
        Button Connection = new Button("Connection", e -> dialog.open());
        VerticalLayout dialogLayout = Choix(dialog);
        dialog.add(dialogLayout);
        add(dialog, Connection);

        Button Groupe1 = new Button("Groupe1");
        Button Groupe2 = new Button("Groupe2");
        Button Groupe3 = new Button("Groupe3");
        Button Groupe4 = new Button("Groupe4");

        HorizontalLayout HL = new HorizontalLayout();
        HL.add(Groupe1, Groupe2, Groupe3, Groupe4);
        add(HL);

    }

    public VerticalLayout Choix(Dialog dialog) throws IOException {

        H2 headline = new H2("Vous êtes: ");
        headline.getStyle().set("margin", "var(--lumo-space-m) 0 0 0")
                .set("font-size", "1.5em").set("font-weight", "bold");

        Button Élève = new Button("Un Élève");
        Button Administrateur = new Button("Un Administrateur");

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
            try {
                Choix(dialog);
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

        Button cancelButton = new Button("Cancel");

        cancelButton.addClickListener((event -> {
            try {
                dialog.removeAll();

                VerticalLayout H = Choix(dialog);
                dialog.add(H);
            } catch (IOException ex) {
                Logger.getLogger(VuePrincipale.class.getName()).log(Level.SEVERE, null, ex);
            }
        })
        );

        Button saveButton = new Button("Login");
        //FAIRE LE CLICKLISTENER POUR VÉRIFIER SI C'EST BON !
        
        saveButton.addClickListener((event -> {
            Connection con = ConnectSGBD.connectionLocalPostgresql();
            try {
                if (inscriptionExistsA(con, Email, MDP)== false){
                    fieldLayout.add("Erreur sur le login ou le MDP");
                };
            } catch (SQLException ex) {
                Logger.getLogger(VuePrincipale.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }));

        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        HorizontalLayout buttonLayout = new HorizontalLayout(cancelButton,
                saveButton);
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
        
        

        Button cancelButton = new Button("Cancel");

        cancelButton.addClickListener((event -> {
            try {
                dialog.removeAll();

                VerticalLayout H = Choix(dialog);
                dialog.add(H);
            } catch (IOException ex) {
                Logger.getLogger(VuePrincipale.class.getName()).log(Level.SEVERE, null, ex);
            }
        })
        );

        Button saveButton = new Button("Login");
        
        saveButton.addClickListener((event -> {
            Connection con = ConnectSGBD.connectionLocalPostgresql();
            try {
                if (inscriptionExistsE(con, Email, MDP)== false){
                    fieldLayout.add("Erreur sur le login ou le MDP");
                };
            } catch (SQLException ex) {
                Logger.getLogger(VuePrincipale.class.getName()).log(Level.SEVERE, null, ex);
            }
        }));
               
               
                
                

        //FAIRE LE CLICKLISTENER POUR VÉRIFIER SI C'EST BON !
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        HorizontalLayout buttonLayout = new HorizontalLayout(cancelButton,
                saveButton);
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
        try ( Statement st = con.createStatement();  ResultSet test = st.executeQuery("select * from inscription where "
                + "etudiant = " + email
                + " and mdp = " + mdp)) {
            return test.next();
        }
    }
    
    public static boolean inscriptionExistsA(Connection con, String email , String mdp) throws SQLException {
        try ( Statement st = con.createStatement();  ResultSet test = st.executeQuery("select * from inscription where "
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

}
