package friki.tienda.com.backoffice.accions;

//import operaciones.*;
import org.apache.struts.action.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import friki.tienda.com.backoffice.formBeans.AdministradorBean;


public class EditarAdministradors extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse resp) {
		
		resp.setContentType("application/json");
		AdministradorBean adminB =(AdministradorBean) form;

		//conectar amb el DAO i fer un  update del admin
		
		// Get the printwriter object from response to write the required json object to the output stream      
		PrintWriter out;
		try {
			out = resp.getWriter();
		
				// Assuming your json object is **jsonObject**, perform the following, it will return your json object
		String jsonObject = generarJson(adminB.getIdUsuario(),adminB.getEmail(),adminB.getContrasenya(),adminB.getRol());
		
		out.print(jsonObject);
		out.flush();
		
		System.out.println(jsonObject);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;	//(això es necessari perquè si no retornem null, passaria el control de nou al servlet d'struts);

	}
		
		

private String generarJson(int idUsuario, String email, String contrasenya,
		String rol) {
		
		StringBuilder builder = 
			new StringBuilder().
				append("{\"idUsuario\":\"").
					append(idUsuario).					
				append("\",\"email\":").
					append(email).									
				append("\",\"contrasenya\":").
					append(contrasenya).
					append("\",\"rol\":").
					append(rol).	
				append("}");				
		
		return builder.toString();
	}
}