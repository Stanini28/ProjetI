package Principal;

import static Principal.Schema.connectPostgresql;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinRequest;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.VaadinServletRequest;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author stanislasallouche
 */
@Route(value = "Administrateur/:userID")
public class VueAdministrateur extends Div {

    private HorizontalLayout HL;
    private VerticalLayout VL;
    private Button CréatEtud;
    private Button CreatMod;
            private Connection con;
    private String IDA;

    public VueAdministrateur() throws ClassNotFoundException, SQLException {

        this.HL = new HorizontalLayout();
        this.CréatEtud = new Button("Création d'Étudiant");
        this.CreatMod = new Button("Création d'un Module");
        this.con = connectPostgresql("localhost", 5432,
                "postgres", "postgres", "passe");
        
        VaadinRequest vaadinRequest = VaadinService.getCurrentRequest();
        HttpServletRequest httpServletRequest = ((VaadinServletRequest)vaadinRequest).getHttpServletRequest();
         this.IDA = httpServletRequest.getRequestURL().subSequence(37, 39).toString();
        

        
        this.HL.add(CréatEtud, CreatMod);
        add(HL);

        this.CreatMod.addClickListener(event -> {

            System.out.println(this.IDA);
        });

        this.CréatEtud.addClickListener(event -> {

            System.out.println(IDA);
        });

    }


}
