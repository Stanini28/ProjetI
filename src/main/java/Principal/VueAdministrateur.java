package Principal;

import static Principal.Schema.connectPostgresql;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
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
@Route(value = "Administrateur/:userID")
public class VueAdministrateur extends AppLayout {

    private HorizontalLayout HL;
    private VerticalLayout VL;
    private Tab CréatEtud;
    private Tab CreatMod;
    private Connection con;
    private String IDA;

    public VueAdministrateur() throws ClassNotFoundException, SQLException {

        this.HL = new HorizontalLayout();
        this.CréatEtud = new Tab("Création d'Étudiant");
        this.CreatMod = new Tab("Création d'un Module");
        this.con = connectPostgresql("localhost", 5432,
                "postgres", "postgres", "passe");

        VaadinRequest vaadinRequest = VaadinService.getCurrentRequest();
        HttpServletRequest httpServletRequest = ((VaadinServletRequest) vaadinRequest).getHttpServletRequest();
        
        if (httpServletRequest.getRequestURL().length()== 39){
        this.IDA = httpServletRequest.getRequestURL().subSequence(37, 39).toString();
        }else if(httpServletRequest.getRequestURL().length()== 38){
        this.IDA = httpServletRequest.getRequestURL().subSequence(37, 38).toString();
        }
        
        
        Tabs tab = new Tabs(this.CreatMod, this.CréatEtud);
        ;

        tab.addSelectedChangeListener(event -> {

            Notification.show(this.IDA);
        });

        DrawerToggle toggle = new DrawerToggle();
        tab.setOrientation(Tabs.Orientation.VERTICAL);
        tab.setHeight("240px");
        tab.setWidth("240px");

        H1 title = new H1("Administrateur/Administratrice : " + this.Nom(IDA, con));
        title.getStyle()
                .set("font-size", "var(--lumo-font-size-l)")
                .set("margin", "0");
        
        addToDrawer(tab);
        this.addToNavbar(toggle, title);
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
