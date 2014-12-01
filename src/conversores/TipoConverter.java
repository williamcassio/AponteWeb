package conversores;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import rn.TipoRN;
import entity.Tipo;

public class TipoConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String string) {
		if (string == null || string.equals("Selecione um Tipo")){
			return null;
		}
		TipoRN tipoRN = new TipoRN();
		return tipoRN.carregar(Integer.parseInt(string));
		
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
		if (obj == null){
			return null;
		}
		Tipo retorno = (Tipo) obj;
		return retorno.getId().toString();
	}

}
