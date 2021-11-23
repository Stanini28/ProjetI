/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Principal;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author stanislasallouche
 */
@Route("")
@PageTitle("Coucou")
    public class VuePrincipale extends VerticalLayout{
    private Button vbCoucou;
    private Button Test;
    private Button H;
    private Dialog Q;
   
    
    public VuePrincipale(){
        Button vbCoucou = new Button ("Test");
        Button Test= new Button("Connection");
        
        add(vbCoucou, Test);
        
        Test.addClickListener((t) ->{
        
       Dialog dialog = new Dialog();
        dialog.getElement().setAttribute("aria-label", "Create new employee");

        VerticalLayout dialogLayout = null;
            try {
                dialogLayout = createDialogLayout(dialog);
            } catch (IOException ex) {
                Logger.getLogger(VuePrincipale.class.getName()).log(Level.SEVERE, null, ex);
            }
        dialog.add(dialogLayout);
        
        
        
        Button button1 = new Button("Administrateur", e -> dialog.open());
        this.add(dialog, button1);
        
        Button button2 = new Button("Élève", e -> dialog.open());
        this.add(dialog, button2);
        
        this.Test.setVisible(false);
       
        
    });
        
    }
    
    public static VerticalLayout createDialogLayout(Dialog dialog) throws IOException {
        
        H2 headline = new H2("Create new employee");
        headline.getStyle().set("margin", "var(--lumo-space-m) 0 0 0")
                .set("font-size", "1.5em").set("font-weight", "bold");

        TextField firstNameField = new TextField("First name");
        TextField lastNameField = new TextField("Last name");
        VerticalLayout fieldLayout = new VerticalLayout(firstNameField,
                lastNameField);
        fieldLayout.setSpacing(false);
        fieldLayout.setPadding(false);
        fieldLayout.setAlignItems(FlexComponent.Alignment.STRETCH);

        Button cancelButton = new Button("Cancel", e -> dialog.close());
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
