/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Principal;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.springframework.web.servlet.mvc.method.annotation.SseEmitter.event;

/**
 *
 * @author stanislasallouche
 */
@Route("")

    public class VuePrincipale extends VerticalLayout{
    private Button Connection;
    private Button H;
    private Dialog Q;
    private Button Élève;
    private Button Administrateur;
   
    
    public VuePrincipale() throws IOException{
        
        
        
        Dialog dialog = new Dialog();
        dialog.getElement().setAttribute("aria-label", "Create new employee");
        Button Connection= new Button("Connection", e-> dialog.open());
        VerticalLayout dialogLayout = Choix(dialog);
        dialog.add(dialogLayout);
        add(dialog, Connection);
        
        
        
    }
    
    
    
    
    
    public static VerticalLayout Choix(Dialog dialog) throws IOException {
        
        H2 headline = new H2("Vous êtes: ");
        headline.getStyle().set("margin", "var(--lumo-space-m) 0 0 0")
                .set("font-size", "1.5em").set("font-weight", "bold");
        
       

        Button Élève = new Button("Un Élève");
        Button Administrateur = new Button("Un Administrateur");
        
        Élève.addClickListener(event -> { try {
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
        
        
        Administrateur.addClickListener(event -> { try {
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
        buttonLayout
                .setJustifyContentMode(FlexComponent.JustifyContentMode.END);

        VerticalLayout dialogLayout = new VerticalLayout(headline,
                buttonLayout);
        dialogLayout.setPadding(false);
        dialogLayout.setAlignItems(FlexComponent.Alignment.STRETCH);
        dialogLayout.getStyle().set("width", "300px").set("max-width", "100%");
        
       
        
        return dialogLayout;
    } 
    
    
    
    
    
    
    
    
    
    
    public static VerticalLayout ConnexionA(Dialog dialog)throws IOException{
        
        
        
        H2 headline = new H2("Connexion d'un Administrateur: ");
        headline.getStyle().set("margin", "var(--lumo-space-m) 0 0 0")
                .set("font-size", "1.5em").set("font-weight", "bold");

        TextField firstNameField = new TextField("Login");
        TextField lastNameField = new TextField("Password");
        VerticalLayout fieldLayout = new VerticalLayout(firstNameField,
                lastNameField);
        fieldLayout.setSpacing(false);
        fieldLayout.setPadding(false);
        fieldLayout.setAlignItems(FlexComponent.Alignment.STRETCH);
        
        
        
        Button cancelButton = new Button("Cancel");
        
        cancelButton.addClickListener((event -> { try {
            dialog.removeAll();
            
           
            VerticalLayout H = Choix(dialog);
            dialog.add(H);
            } catch (IOException ex) {
                Logger.getLogger(VuePrincipale.class.getName()).log(Level.SEVERE, null, ex);
            }
        })
);
        
        
        
        
        
        Button saveButton = new Button("Save", e -> dialog.close());
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
    
    
    
    
    
    
    
    public static VerticalLayout ConnexionE(Dialog dialog)throws IOException{
        
        
        H2 headline = new H2("Connexion d'un Élève");
        headline.getStyle().set("margin", "var(--lumo-space-m) 0 0 0")
                .set("font-size", "1.5em").set("font-weight", "bold");

        TextField firstNameField = new TextField("Login");
        TextField lastNameField = new TextField("Password");
        VerticalLayout fieldLayout = new VerticalLayout(firstNameField,
                lastNameField);
        fieldLayout.setSpacing(false);
        fieldLayout.setPadding(false);
        fieldLayout.setAlignItems(FlexComponent.Alignment.STRETCH);

        Button cancelButton = new Button("Cancel");
        
       cancelButton.addClickListener((event -> { try {
            dialog.removeAll();
            
           
            VerticalLayout H = Choix(dialog);
            dialog.add(H);
            } catch (IOException ex) {
                Logger.getLogger(VuePrincipale.class.getName()).log(Level.SEVERE, null, ex);
            }
        })
);
        
        Button saveButton = new Button("Save", e -> dialog.close());
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
      
    
    
}
