package friki.tienda.com.tienda.controladores.carrito;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.codehaus.jettison.json.JSONObject;


public class Pago extends Action {

	public ActionForward execute(ActionMapping mapping, 
			ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		// establecemos tipo de respuesta json
		response.setContentType("application/json");
				
		// recuperamos los par�metros de la request y creamos el objeto
		String numTarjeta = request.getParameter("numTarjeta");
		String fechaCad = request.getParameter("fechaCad");
		String numSecreto = request.getParameter("numSecreto");
		
		// creamos objeto json que enviaremos en la response
		JSONObject js = new JSONObject();
		
		String err = preValidar(numTarjeta, fechaCad, numSecreto);
		
		js.accumulate("errores",err);
		
		PrintWriter pw = response.getWriter();
		pw.write(err);
		pw.flush();
		pw.close();
		
		if (err == ""){
			return mapping.findForward("finPago");
		}else{
			return mapping.findForward("pago");
		}

	}

	public String preValidar(String numTarjeta, String fechaCad, String numSecreto) {
		String errores = "";
		
		if (numTarjeta == null || numTarjeta.equals("")) {
			errores += "indique el n�mero de la tarjeta";
		}

		if (fechaCad == null || fechaCad.equals("")) {
			errores += "indique la fecha de caducidad";
		}
				
		if (numSecreto == null || numSecreto.equals("")) {
			errores += "indique el n�mero secreto";
		}
		return errores;
	}
	
	
}
