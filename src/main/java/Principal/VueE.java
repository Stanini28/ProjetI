/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Principal;

import static Principal.Schema.connectPostgresql;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.server.VaadinRequest;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.VaadinServletRequest;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.checkbox.CheckboxGroupVariant;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author stanislasallouche
 */
public class VueE extends AppLayout {
    private Connection con;
    private String IDA;
    private String Semestre;
    
    private H1 Vue1;
    
    
	
    
    

    public VueE() throws ClassNotFoundException, SQLException {

        this.con = connectPostgresql("localhost", 5432,
                "postgres", "postgres", "passe");
        
        VaadinRequest vaadinRequest = VaadinService.getCurrentRequest();
        HttpServletRequest httpServletRequest = ((VaadinServletRequest) vaadinRequest).getHttpServletRequest();
        if (httpServletRequest.getRequestURL().length()==32 ){
         this.IDA = httpServletRequest.getRequestURL().subSequence(31, 32).toString();}
        else {
            this.IDA = httpServletRequest.getRequestURL().subSequence(31, 33).toString();}

        
        addToNavbar(true, createHeaderContent());
        
        CheckboxGroup<String> checkboxGroup = new CheckboxGroup<>();
        checkboxGroup.setLabel("Quel est votre Semestre?");
        checkboxGroup.setItems("S1", "S2", "S3", "S4","S5", "S6", "S7", "S8","S9","S10");
        checkboxGroup.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);
        checkboxGroup.setSizeFull();
        this.addToDrawer(checkboxGroup);
        
       checkboxGroup.addSelectionListener(event ->{
            if (checkboxGroup.isSelected("S1")){
                checkboxGroup.setVisible(false);
                this.Semestre="S1";
            }
            if (checkboxGroup.isSelected("S2")==true){
                checkboxGroup.setVisible(false);
                this.Semestre="S2";
            }
            if (checkboxGroup.isSelected("S3")==true){
                checkboxGroup.setVisible(false);
                this.Semestre="S3";
            }
            if (checkboxGroup.isSelected("S4")==true){
                checkboxGroup.setVisible(false);
                this.Semestre="S4";
            }
            if (checkboxGroup.isSelected("S5")==true){
                checkboxGroup.setVisible(false);
                this.Semestre="S5";
            }
            if (checkboxGroup.isSelected("S6")==true){
                checkboxGroup.setVisible(false);
                this.Semestre="S6";
            }
            if (checkboxGroup.isSelected("S7")==true){
                checkboxGroup.setVisible(false);
                this.Semestre="S7";
            }
            if (checkboxGroup.isSelected("S8")==true){
                checkboxGroup.setVisible(false);
                this.Semestre="S8";
            }
            if (checkboxGroup.isSelected("S9")==true){
                checkboxGroup.setVisible(false);
                this.Semestre="S9";
            }
            if (checkboxGroup.isSelected("S10")==true){
                checkboxGroup.setVisible(false);
                this.Semestre="S10";
            }
            
        });
    }

    
    
    
     private Component createHeaderContent() throws SQLException {
        HorizontalLayout layout = new HorizontalLayout();

        layout.setId("header");
        layout.getThemeList().set("dark", true);
        layout.setWidthFull();
        layout.setSpacing(false);
        layout.setAlignItems(FlexComponent.Alignment.CENTER);

        HorizontalLayout logoLayout = new HorizontalLayout();
        
        logoLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        logoLayout.add(new H1("Etudiant/Etudiante: " + Nom(this.IDA, con)));
        
        layout.add(logoLayout);

        Vue1 = new H1();
        layout.add(Vue1);

       

        return layout;
    }
    
    
    public String Nom(String ID, Connection con) throws SQLException{
        String strql= "SELECT * from etudiants WHERE id ='"+ ID + "'";
         Statement st = con.createStatement(); 
            ResultSet resultset = st.executeQuery(strql);
            String P="";
            String N="";
            
            while(resultset.next()){
                P = resultset.getString("prenom");
                N = resultset.getString("nom");
            }
            
            return P + " " + N;
    }
    
    
    
}
