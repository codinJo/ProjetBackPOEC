
import javax.ejb.EJB;
import javax.ws.rs.Path;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author imperath
 */
@Path("manager")
public class PdfManager {
    @EJB
    AddCover addCover;
}
