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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        this.Choix.setVisible(false);
        this.Module.setVisible(false);

        this.tabs = new Tabs(this.Sem, this.Module, this.Choix);

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
            content.add(Voeux2(dialog));

        } else if (tab.equals(this.Module)) {
            content.add(AffichageInfo(dialog));
//		

        } else if (tab.equals(this.Sem)) {
            content.add(test());

        }
    }

    public String Nom(String ID, Connection con) throws SQLException {
        String strql = "SELECT * from administrateur WHERE id ='" + ID + "'";
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
                    + "groupedemodules.id where groupedemodules.idsemestre=" + this.Semestre );
            while (res.next()) {

                String nomModules = res.getString("Intitule");
                String description = res.getString("description");
                String nbrPlace = res.getString("nbrPlaces");
                String idgroupemodule = res.getString("idgroupesmodules");

                Button H = new Button(nomModules);
                if (Math.floorMod(Integer.parseInt(idgroupemodule),3) == 1 ) {
                    Vl1.add(H);
                    H.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
                } else if (Math.floorMod(Integer.parseInt(idgroupemodule),3 ) == 2) {
                    Vl2.add(H);
                    H.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
                } else if (Math.floorMod(Integer.parseInt(idgroupemodule), 3 ) == 0) {
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

    public CheckboxGroup test() {
        CheckboxGroup<String> checkboxGroup = new CheckboxGroup<>();
        checkboxGroup.setLabel("Quel est votre Semestre?");
        checkboxGroup.setItems("S1", "S2", "S3", "S4", "S5", "S6", "S7", "S8", "S9", "S10");
        checkboxGroup.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);
        checkboxGroup.setSizeFull();

        checkboxGroup.addSelectionListener(event -> {
            if (checkboxGroup.isSelected("S1")) {
                checkboxGroup.setVisible(false);
                this.Sem.setVisible(false);
                this.Semestre = 1;
                this.Choix.setVisible(true);
                this.Module.setVisible(true);
            }
            if (checkboxGroup.isSelected("S2") == true) {
                checkboxGroup.setVisible(false);
                this.Semestre = 2;
                this.Sem.setVisible(false);
                this.Choix.setVisible(true);
                this.Module.setVisible(true);
            }
            if (checkboxGroup.isSelected("S3") == true) {
                checkboxGroup.setVisible(false);
                this.Semestre = 3;
                this.Sem.setVisible(false);
                this.Choix.setVisible(true);
                this.Module.setVisible(true);
                
            }
            if (checkboxGroup.isSelected("S4") == true) {
                checkboxGroup.setVisible(false);
                this.Semestre = 4;
                this.Sem.setVisible(false);
                this.Choix.setVisible(true);
                this.Module.setVisible(true);
            }
            if (checkboxGroup.isSelected("S5") == true) {
                checkboxGroup.setVisible(false);
                this.Semestre = 5;
                this.Sem.setVisible(false);
                this.Choix.setVisible(true);
                this.Module.setVisible(true);
            }
            if (checkboxGroup.isSelected("S6") == true) {
                checkboxGroup.setVisible(false);
                this.Semestre = 6;
                this.Sem.setVisible(false);
                this.Choix.setVisible(true);
                this.Module.setVisible(true);
            }
            if (checkboxGroup.isSelected("S7") == true) {
                checkboxGroup.setVisible(false);
                this.Semestre = 7;
                this.Sem.setVisible(false);
                this.Choix.setVisible(true);
                this.Module.setVisible(true);
            }
            if (checkboxGroup.isSelected("S8") == true) {
                checkboxGroup.setVisible(false);
                this.Semestre = 8;
                this.Sem.setVisible(false);
                this.Choix.setVisible(true);
                this.Module.setVisible(true);
            }
            if (checkboxGroup.isSelected("S9") == true) {
                checkboxGroup.setVisible(false);
                this.Semestre = 9;
                this.Sem.setVisible(false);
                this.Choix.setVisible(true);
                this.Module.setVisible(true);
            }
            if (checkboxGroup.isSelected("S10") == true) {
                checkboxGroup.setVisible(false);
                this.Semestre = 10;
                this.Sem.setVisible(false);
                this.Choix.setVisible(true);
                this.Module.setVisible(true);
            }

        });

        return checkboxGroup;
    }

//    public VerticalLayout AffichageGM(Dialog dialog) throws SQLException {
//        VerticalLayout Vl = new VerticalLayout();
//
//        try ( Statement st = con.createStatement()) {
//            ResultSet res = st.executeQuery("select * from Modules JOIN groupedeModules ON Modules.idgroupesmodules="
//                    + "groupedemodules.id where groupedemodules.idsemestre=" + this.Semestre );
//            while (res.next()) {
//                HorizontalLayout HL = new HorizontalLayout();
//
//                String nomModules = res.getString("Intitule");
//                String description = res.getString("description");
//                String nbrPlace = res.getString("nbrPlaces");
//                String idgroupemodule = res.getString("idgroupesmodules");
//
//                
//                Button H = new Button(nomModules);
//
//                H.addClickListener(event -> {
//                    Notification L = Notification.show("Description: " + description);
//                    Notification M = Notification.show("Nombre de places disponibles:" + nbrPlace);
//                    Notification N = Notification.show("Id du Groupe de Module : " + idgroupemodule);
//                    N.setPosition(Notification.Position.MIDDLE);
//                    M.setPosition(Notification.Position.MIDDLE);
//                    L.setPosition(Notification.Position.MIDDLE);
//
//                });
//                HL.add(H);
//                Vl.add(HL);
//            }
//
//        }
//
//        Vl.setPadding(false);
//        return Vl;
//    }

    public VerticalLayout Voeux2(Dialog dialog) throws SQLException {

        ComboBox<String> comboBox1 = new ComboBox<>("Voeux 2 pour le module du Groupe 1");
        ComboBox<String> comboBox2 = new ComboBox<>("Voeux 2 pour le module du Groupe 2");
        ComboBox<String> comboBox3 = new ComboBox<>("Voeux 2 pour le module du Groupe 3");

        List<String> L1 = new ArrayList<>();
        List<String> L2 = new ArrayList<>();
        List<String> L3 = new ArrayList<>();

        HorizontalLayout Vl = new HorizontalLayout();
        HorizontalLayout VL = new HorizontalLayout();

        VerticalLayout VLT = new VerticalLayout();

        VL.add(Voeux1(dialog));

        try ( Statement st = con.createStatement()) {
            ResultSet res = st.executeQuery("select * from Modules");

            while (res.next()) {

                String nomModules = res.getString("Intitule");
                String idgroupemodule = res.getString("idgroupesmodules");

                if (Integer.parseInt(idgroupemodule) == 1) {
                    L1.add(nomModules);

                } else if (Integer.parseInt(idgroupemodule) == 2) {
                    L2.add(nomModules);

                } else if (Integer.parseInt(idgroupemodule) == 3) {
                    L3.add(nomModules);
                }

                comboBox1.setItems(L1);
                comboBox2.setItems(L2);
                comboBox3.setItems(L3);
            }

            Vl.add(comboBox1, comboBox2, comboBox3);
        }

        VLT.add(VL, Vl);

        Vl.setPadding(false);
        return VLT;
    }

    public HorizontalLayout Voeux1(Dialog dialog) throws SQLException {

        ComboBox<String> comboBox1 = new ComboBox<>("Voeux 1 pour le module du Groupe 1");
        ComboBox<String> comboBox2 = new ComboBox<>("Voeux 1 pour le module du Groupe 2");
        ComboBox<String> comboBox3 = new ComboBox<>("Voeux 1 pour le module du Groupe 3");

        List<String> L1 = new ArrayList<>();
        List<String> L2 = new ArrayList<>();
        List<String> L3 = new ArrayList<>();

        HorizontalLayout Vl = new HorizontalLayout();

        try ( Statement st = con.createStatement()) {
            ResultSet res = st.executeQuery("select * from Modules");

            while (res.next()) {

                String nomModules = res.getString("Intitule");
                String idgroupemodule = res.getString("idgroupesmodules");

                if (Integer.parseInt(idgroupemodule) == 1) {
                    L1.add(nomModules);

                } else if (Integer.parseInt(idgroupemodule) == 2) {
                    L2.add(nomModules);

                } else if (Integer.parseInt(idgroupemodule) == 3) {
                    L3.add(nomModules);
                }

                comboBox1.setItems(L1);
                comboBox2.setItems(L2);
                comboBox3.setItems(L3);
            }

            Vl.add(comboBox1, comboBox2, comboBox3);
        }

        Button Save = new Button("Sauvegarder ces choix!");
        Vl.add(Save);
        Vl.setPadding(false);
        Vl.setAlignItems(FlexComponent.Alignment.END);

        return Vl;
    }

}
