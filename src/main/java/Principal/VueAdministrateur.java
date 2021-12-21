package Principal;

import static Principal.Schema.connectPostgresql;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextField;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author stanislasallouche
 */
@Route(value = "Administrateur/:userID", layout = VueA.class)
public class VueAdministrateur extends Div {

    private Connection con;
    private HorizontalLayout content;
    private Tab CréatEtud;
    private Tab CreatMod;
    private VerticalLayout VL1;

    private Tab GM;
    private Tab CreatSem;
    private Tabs tabs;

    public VueAdministrateur() throws ClassNotFoundException, SQLException {
        this.con = connectPostgresql("localhost", 5432,
                "postgres", "postgres", "pass");

        this.VL1 = new VerticalLayout();

        this.CréatEtud = new Tab("Création d'Étudiant");
        this.CreatMod = new Tab("Création d'un Module");
        this.con = connectPostgresql("localhost", 5432,
                "postgres", "postgres", "pass");
        this.CreatSem = new Tab("Création d'un Semestre");
        this.GM = new Tab("Groupe de Modules");

        this.tabs = new Tabs(this.CreatMod, this.CreatSem, this.CréatEtud, this.GM);

        tabs.addSelectedChangeListener(event
                -> setContent(event.getSelectedTab())
        );

        content = new HorizontalLayout();
        content.setSpacing(false);
        setContent(tabs.getSelectedTab());

        this.VL1.add(content);

        VL1.setSpacing(false);
        VL1.setAlignItems(FlexComponent.Alignment.CENTER);
        add(tabs, VL1);

        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        tabs.setHeight("240px");
        tabs.setWidth("240px");

    }

    public void setContent(Tab tab) {
        content.removeAll();
        Dialog dialog = new Dialog();
        dialog.getElement().setAttribute("aria-label", "Create new employee");

        VerticalLayout dialogLayout = CM(dialog);
        dialog.add(dialogLayout);

        if (tab.equals(this.CreatMod)) {
            content.add(CM(dialog));

        } else if (tab.equals(this.CréatEtud)) {
            content.add(CE(dialog));
//		} else {
//			content.add(new Paragraph("This is the Shipping tab"));
//                        		}
        }
    }

    public VerticalLayout CM(Dialog dialog) {
        H2 headline = new H2("Création d'un nouveau module");
        headline.getStyle().set("margin", "var(--lumo-space-m) 0 0 0")
                .set("font-size", "1.5em").set("font-weight", "bold");

        TextField Intitule = new TextField("Nom du Module : ");
        TextField Desc = new TextField("Description du Module : ");
        TextField NBR = new TextField("Nombre de Places disponibles: ");
        VerticalLayout fieldLayout = new VerticalLayout(Intitule,
                Desc, NBR);
        fieldLayout.setSpacing(false);
        fieldLayout.setPadding(false);
        fieldLayout.setAlignItems(FlexComponent.Alignment.STRETCH);

        Button cancelButton = new Button("Cancel", e -> dialog.close());
        Button saveButton = new Button("Save", e -> {
            try {
                CreationUnModule(this.con, Intitule.getValue(), Desc.getValue(), NBR.getValue());
            } catch (SQLException ex) {
                Logger.getLogger(VueAdministrateur.class.getName()).log(Level.SEVERE, null, ex);
            }

            dialog.close();
        });
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

    public static void CreationUnModule(Connection con, String intitule, String description, String nbrplaces) throws SQLException {
        try ( PreparedStatement pst = con.prepareStatement(
                """
        insert into Modules (intitule, description, nbrplaces)
        values (?,?,?)
        """)) {
            pst.setString(1, intitule);
            pst.setString(2, description);
            pst.setString(3, nbrplaces);
            pst.executeUpdate();
        }
    }

    public VerticalLayout CE(Dialog dialog) {
        H2 headline = new H2("Création d'un nouveau étudiant");
        headline.getStyle().set("margin", "var(--lumo-space-m) 0 0 0")
                .set("font-size", "1.5em").set("font-weight", "bold");

        TextField Nom = new TextField("Nom : ");
        TextField Prenom = new TextField("Prénom : ");
        TextField Email = new TextField("Email : ");
        TextField Spe = new TextField("Spécialité : ");
        TextField MDP = new TextField("Mot de Passe : ");
        VerticalLayout fieldLayout = new VerticalLayout(Nom,
                Prenom, Spe, Email, MDP);
        fieldLayout.setSpacing(false);
        fieldLayout.setPadding(false);
        fieldLayout.setAlignItems(FlexComponent.Alignment.STRETCH);

        Button cancelButton = new Button("Cancel", e -> dialog.close());
        Button saveButton = new Button("Save", e -> {
            try {
                CreationUnEtudiant(this.con, Nom.getValue(), Prenom.getValue(), Email.getValue(), Spe.getValue(),
                        MDP.getValue());
            } catch (SQLException ex) {
                Logger.getLogger(VueAdministrateur.class.getName()).log(Level.SEVERE, null, ex);
            }

            dialog.close();
        });
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

    public static void CreationUnEtudiant(Connection con, String noms, String prenoms, String email, String specialite, String mdp) throws SQLException {
        try ( PreparedStatement pst = con.prepareStatement(
                """
        insert into Etudiants (nom, prenom, email, specialite, mdp)
        values (?,?,?,?,?)
        """)) {
            pst.setString(1, noms);
            pst.setString(2, prenoms);
            pst.setString(3, email);
            pst.setString(4, specialite);
            pst.setString(5, mdp);
            pst.executeUpdate();
        }
    }
}
