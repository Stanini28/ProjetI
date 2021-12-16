/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Principal;

import static Principal.Schema.connectPostgresql;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
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
    private Tab CréatEtud;
    private Tab CreatMod;
    private Connection con;
    private String IDA;
    private Tab GM;
    private Tab CreatSem;
    
    private H1 Vue1;
    
    

    public VueA() throws ClassNotFoundException, SQLException {

        this.CréatEtud = new Tab("Création d'Étudiant");
        this.CreatMod = new Tab("Création d'un Module");
        this.con = connectPostgresql("localhost", 5432,
                "postgres", "postgres", "passe");
        this.CreatSem= new Tab("Création d'un Semestre");
        this.GM= new Tab("Groupe de Modules");
        
        VaadinRequest vaadinRequest = VaadinService.getCurrentRequest();
        HttpServletRequest httpServletRequest = ((VaadinServletRequest) vaadinRequest).getHttpServletRequest();
        if (httpServletRequest.getRequestURL().length()==38 ){
         this.IDA = httpServletRequest.getRequestURL().subSequence(37, 38).toString();}
        else {
            this.IDA = httpServletRequest.getRequestURL().subSequence(37, 39).toString();}
        
        
        
        Tabs tab = new Tabs(this.CreatMod, this.CréatEtud, this.CreatSem, this.GM);
        
        

       
        tab.setOrientation(Tabs.Orientation.VERTICAL);
        tab.setHeight("240px");
        tab.setWidth("240px");

      
        addToDrawer(createDrawerContent(tab));
        addToNavbar(true, createHeaderContent());
      
    }
    
    
     private Component createHeaderContent() {
        HorizontalLayout layout = new HorizontalLayout();

        layout.setId("header");
        layout.getThemeList().set("dark", true);
        layout.setWidthFull();
        layout.setSpacing(false);
        layout.setAlignItems(FlexComponent.Alignment.CENTER);

        
        layout.add(new DrawerToggle());

        Vue1 = new H1();
        layout.add(Vue1);

       

        return layout;
    }
    
    private Component createDrawerContent(Tabs menu) {
        VerticalLayout layout = new VerticalLayout();

   
        layout.setSizeFull();
        layout.setPadding(false);
        layout.setSpacing(false);
        layout.getThemeList().set("spacing-s", true);
        layout.setAlignItems(FlexComponent.Alignment.STRETCH);

        
        HorizontalLayout logoLayout = new HorizontalLayout();
        logoLayout.setId("logo");
        logoLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        logoLayout.add(new H1("My Project"));

    
        layout.add(logoLayout, menu);
        return layout;
    }
    
    
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

