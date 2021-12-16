/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Principal;

import static Principal.Schema.connectPostgresql;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;
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
@Route(value = "Etudiant/:userID", layout= VueE.class)
public class VueEtudiant extends HorizontalLayout {
    
    private Connection con;
    private String IDE;
    
    public VueEtudiant() throws ClassNotFoundException, SQLException {
    this.con = connectPostgresql("localhost", 5432,
                "postgres", "postgres", "passe");
    
    
    VaadinRequest vaadinRequest = VaadinService.getCurrentRequest();
        HttpServletRequest httpServletRequest = ((VaadinServletRequest) vaadinRequest).getHttpServletRequest();
        if (httpServletRequest.getRequestURL().length()==38 ){
         this.IDE = httpServletRequest.getRequestURL().subSequence(37, 38).toString();}
        else {
            this.IDE = httpServletRequest.getRequestURL().subSequence(37, 39).toString();}
    
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
