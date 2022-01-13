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
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.material.Material;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author stanislasallouche
 */
@Theme(value = Material.class)
public class VueE extends AppLayout {
    private Connection con;
    private String IDA;
    
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
