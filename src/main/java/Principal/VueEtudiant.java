/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Principal;

import static Principal.Schema.connectPostgresql;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.checkbox.CheckboxGroupVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinRequest;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.VaadinServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author stanislasallouche
 */
@Route(value = "Etudiant/:userID", layout = VueE.class)
public class VueEtudiant extends Div {

    private Connection con;
    private String IDE;
    private Tab Module;
    private Tabs tabs;
    private Tab Choix;
    private Tab Historique;

    private int Semestre;

    private VerticalLayout VL1;

    private HorizontalLayout content;
    private Tab Sem;

    public VueEtudiant() throws ClassNotFoundException, SQLException {
        this.con = connectPostgresql("localhost", 5432,
                "postgres", "postgres", "passe");
        this.VL1 = new VerticalLayout();
        this.Module = new Tab("Description des Modules");
        this.Choix = new Tab("Voeux pour les Modules");
        this.Sem = new Tab("Semestre");
        this.Historique = new Tab("Historique des Modules");
        this.Choix.setVisible(false);
        this.Module.setVisible(false);
        this.Historique.setVisible(false);

        VaadinRequest vaadinRequest = VaadinService.getCurrentRequest();
        HttpServletRequest httpServletRequest = ((VaadinServletRequest) vaadinRequest).getHttpServletRequest();
        if (httpServletRequest.getRequestURL().length() == 32) {
            this.IDE = httpServletRequest.getRequestURL().subSequence(31, 32).toString();
        } else {
            this.IDE = httpServletRequest.getRequestURL().subSequence(31, 33).toString();
        }

        this.tabs = new Tabs(this.Sem, this.Module, this.Choix, this.Historique);

        add(tabs);

        tabs.addSelectedChangeListener(event
                -> {
            try {
                setContent(event.getSelectedTab());
            } catch (SQLException ex) {
                Logger.getLogger(VueEtudiant.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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

    public void setContent(Tab tab) throws SQLException {
        content.removeAll();
        Dialog dialog = new Dialog();
        dialog.getElement().setAttribute("aria-label", "Create new employee");
        if (tab.equals(this.Choix)) {
            content.add(Voeux(dialog));

        } else if (tab.equals(this.Module)) {
            content.add(AffichageInfo(dialog));
//		

        } else if (tab.equals(this.Sem)) {
            content.add(test());

        } else if (tab.equals(this.Historique)) {
            content.add(Historique(dialog));
        }
    }

    public String Nom(String ID, Connection con) throws SQLException {
        String strql = "SELECT * from etudiants WHERE id ='" + ID + "'";
        Statement st = con.createStatement();
        ResultSet resultset = st.executeQuery(strql);
        String P = "";
        String N = "";

        while (resultset.next()) {
            P = resultset.getString("prenom");
            N = resultset.getString("nom");
        }

        return P + " " + N;
    }

    public HorizontalLayout AffichageInfo(Dialog dialog) throws SQLException {
        VerticalLayout Vl1 = new VerticalLayout();
        VerticalLayout Vl2 = new VerticalLayout();
        VerticalLayout Vl3 = new VerticalLayout();

        Vl1.setSizeFull();

        HorizontalLayout Vl = new HorizontalLayout();
        try ( Statement st = con.createStatement()) {
            ResultSet res = st.executeQuery("select * from Modules JOIN groupedeModules ON Modules.idgroupesmodules="
                    + "groupedemodules.id where groupedemodules.idsemestre=" + this.Semestre);
            while (res.next()) {

                String nomModules = res.getString("Intitule");
                String description = res.getString("description");
                String nbrPlace = res.getString("nbrPlaces");
                String idgroupemodule = res.getString("idgroupesmodules");

                Button H = new Button(nomModules);
                if (Math.floorMod(Integer.parseInt(idgroupemodule), 3) == 1) {
                    Vl1.add(H);
                    H.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
                } else if (Math.floorMod(Integer.parseInt(idgroupemodule), 3) == 2) {
                    Vl2.add(H);
                    H.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
                } else if (Math.floorMod(Integer.parseInt(idgroupemodule), 3) == 0) {
                    Vl3.add(H);
                    H.addThemeVariants(ButtonVariant.LUMO_ERROR);
                }

                H.addClickListener(event -> {
                    Notification L = Notification.show("Description: " + description);
                    Notification M = Notification.show("Nombre de places disponibles:" + nbrPlace);
                    Notification N = Notification.show("Id du Groupe de Module : " + idgroupemodule);
                    N.setPosition(Notification.Position.MIDDLE);
                    M.setPosition(Notification.Position.MIDDLE);
                    L.setPosition(Notification.Position.MIDDLE);

                });
            }
            Vl.add(Vl1, Vl2, Vl3);
        }

        Vl.setPadding(false);
        return Vl;
    }

    public HorizontalLayout test() throws SQLException {
        HorizontalLayout HL = new HorizontalLayout();
        Statement st = con.createStatement();
        ResultSet res = st.executeQuery("select * from semestre");
        while (res.next()) {
            String Num = res.getString("numerosem");

            Button M = new Button("S" + Num);
            HL.add(M);

            M.addClickListener(event -> {
                HL.setVisible(false);
                this.Sem.setVisible(false);
                this.Semestre = Integer.parseInt(Num);
                this.Choix.setVisible(true);
                this.Module.setVisible(true);
                this.Historique.setVisible(true);
            });
        }

        return HL;
    }
    
    public VerticalLayout Voeux(Dialog dialog) throws SQLException {

        ComboBox<String> comboBox1 = new ComboBox<>("Voeux 1 pour le module du Groupe 1");
        ComboBox<String> comboBox2 = new ComboBox<>("Voeux 1 pour le module du Groupe 2");
        ComboBox<String> comboBox3 = new ComboBox<>("Voeux 1 pour le module du Groupe 3");
        ComboBox<String> comboBox4 = new ComboBox<>("Voeux 2 pour le module du Groupe 1");
        ComboBox<String> comboBox5 = new ComboBox<>("Voeux 2 pour le module du Groupe 2");
        ComboBox<String> comboBox6 = new ComboBox<>("Voeux 2 pour le module du Groupe 3");

        List<String> L1 = new ArrayList<>();
        List<String> L2 = new ArrayList<>();
        List<String> L3 = new ArrayList<>();

        HorizontalLayout Vl = new HorizontalLayout();
        HorizontalLayout VL2 = new HorizontalLayout();
        VerticalLayout HL = new VerticalLayout();
        
        try ( Statement st = con.createStatement()) {

            ResultSet res = st.executeQuery("select intitule, idgroupesmodules from Modules"
                    + " JOIN groupedemodules ON Modules.idgroupesmodules = groupedemodules.id"
                    + " where groupedemodules.idsemestre =" + this.Semestre + " EXCEPT select intitule, idgroupesmodules from Modules JOIN inscription ON Modules.id = inscription.idmodulegm1 where inscription.idetudiant=" + this.IDE);

            while (res.next()) {

                String nomModules = res.getString("Intitule");
                String idgroupemodule = res.getString("idgroupesmodules");

                if (Math.floorMod(Integer.parseInt(idgroupemodule), 3) == 1) {

                    L1.add(nomModules);
                }
            }

        }

        try ( Statement st = con.createStatement()) {

            ResultSet res = st.executeQuery("select intitule, idgroupesmodules from Modules"
                    + " JOIN groupedemodules ON Modules.idgroupesmodules = groupedemodules.id"
                    + " where groupedemodules.idsemestre =" + this.Semestre + " EXCEPT select intitule, idgroupesmodules from Modules JOIN inscription ON Modules.id = inscription.idmodulegm2 where inscription.idetudiant=" + this.IDE);

            while (res.next()) {

                String nomModules = res.getString("Intitule");
                String idgroupemodule = res.getString("idgroupesmodules");

                if (Math.floorMod(Integer.parseInt(idgroupemodule), 3) == 2) {

                    L2.add(nomModules);
                }
            }

        }
        try ( Statement st = con.createStatement()) {

            ResultSet res = st.executeQuery("select intitule, idgroupesmodules from Modules"
                    + " JOIN groupedemodules ON Modules.idgroupesmodules = groupedemodules.id"
                    + " where groupedemodules.idsemestre =" + this.Semestre + " EXCEPT select intitule, idgroupesmodules from Modules JOIN inscription ON Modules.id = inscription.idmodulegm3 where inscription.idetudiant=" + this.IDE);

            while (res.next()) {

                String nomModules = res.getString("Intitule");
                String idgroupemodule = res.getString("idgroupesmodules");

                if (Math.floorMod(Integer.parseInt(idgroupemodule), 3) == 0) {

                    L3.add(nomModules);
                }
            }

        }

        comboBox1.setItems(L1);

        comboBox2.setItems(L2);
        comboBox3.setItems(L3);
        comboBox4.setItems(L1);

        comboBox5.setItems(L2);
        comboBox6.setItems(L3);
        Vl.add(comboBox1, comboBox2, comboBox3);
        VL2.add(comboBox4, comboBox5, comboBox6);
        Button Save = new Button("Sauvegarder ces choix!");
        HL.add(Vl, VL2, Save);
        HL.setPadding(false);
        HL.setAlignItems(FlexComponent.Alignment.END);
        Save.addClickListener(event -> {
            
        });
        
        
        return HL;

    }

    public HorizontalLayout Historique(Dialog dialog) throws SQLException {
        Statement st = con.createStatement();
        VerticalLayout HL1 = new VerticalLayout();
        VerticalLayout HL2 = new VerticalLayout();
        VerticalLayout HL3 = new VerticalLayout();
        HorizontalLayout HL = new HorizontalLayout();
        System.out.println(this.IDE);

        ResultSet res1 = st.executeQuery("Select intitule from Modules JOIN inscription ON Modules.id = inscription.idmodulegm1"
                + " where inscription.idetudiant=" + this.IDE);
        while (res1.next()) {
            String S = res1.getString("intitule");
            HL1.add(S + "\n");

        }
        ResultSet Res2 = st.executeQuery("Select intitule from Modules JOIN inscription ON Modules.id = inscription.idmodulegm2"
                + " where inscription.idetudiant=" + this.IDE);
        while (Res2.next()) {
            String S = Res2.getString("intitule");
            HL2.add(S + "\n");

        }
        ResultSet Res3 = st.executeQuery("Select intitule from Modules JOIN inscription ON Modules.id = inscription.idmodulegm3"
                + " where inscription.idetudiant=" + this.IDE);
        while (Res3.next()) {
            String S = Res3.getString("intitule");
            HL3.add(S + "\n");

        }
        HL.add(HL1, HL2, HL3);
        return HL;
    }

}
