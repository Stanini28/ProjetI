/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Principal;

import static Principal.Schema.connectPostgresql;
import static com.sun.el.lang.ELArithmetic.add;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.server.VaadinRequest;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.VaadinServletRequest;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author stanislasallouche
 */
public class VueA extends AppLayout {
    
    private Connection con;
    private String IDA;
    
    private H1 Vue1;
    
	
    
    

    public VueA() throws ClassNotFoundException, SQLException {

        
        this.con = connectPostgresql("localhost", 5432,
                "postgres", "postgres", "passe");
        
        VaadinRequest vaadinRequest = VaadinService.getCurrentRequest();
        HttpServletRequest httpServletRequest = ((VaadinServletRequest) vaadinRequest).getHttpServletRequest();
        if (httpServletRequest.getRequestURL().length()==38 ){
         this.IDA = httpServletRequest.getRequestURL().subSequence(37, 38).toString();}
        else {
            this.IDA = httpServletRequest.getRequestURL().subSequence(37, 39).toString();}
        
        
        
        
        
        

       
        

      
        
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
        logoLayout.add(new H1("Administrateur/Administratrice: " + Nom(this.IDA, con)));
        
        layout.add(logoLayout);

        Vue1 = new H1();
        layout.add(Vue1);

       

        return layout;
    }
    
//    private Component createDrawerContent(Tabs menu) {
//        VerticalLayout layout = new VerticalLayout();
//
//   
//        layout.setSizeFull();
//        layout.setPadding(false);
//        layout.setSpacing(false);
//        layout.getThemeList().set("spacing-s", true);
//        layout.setAlignItems(FlexComponent.Alignment.STRETCH);
//
//        
//        
//
//    
//        layout.add( menu);
//        return layout;
//    }
    
    
    public String Nom(String ID, Connection con) throws SQLException{
        String strql= "SELECT * from administrateur WHERE id ='"+ ID + "'";
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

