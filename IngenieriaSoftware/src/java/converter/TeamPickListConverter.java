package converter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Logic.LogPermiso;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import org.primefaces.component.picklist.PickList;
import org.primefaces.model.DualListModel;
 
@FacesConverter("teamPickListConverter")
public class TeamPickListConverter implements Converter {
 
    //private final Logger LOG = LoggerFactory.getLogger(TeamPickListConverter.class);
    
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Logger LOG = LoggerFactory.getLogger(TeamPickListConverter.class);
        LOG.trace("String value: {}", value);
 
        return getObjectFromUIPickListComponent(component,value);
    }
 
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object object) {
        Logger LOG = LoggerFactory.getLogger(TeamPickListConverter.class);
        
        String string;
        LOG.trace("Object value: {}", object);
        if(object == null){
            string="";
        }else{
            try{
                
                string = String.valueOf(((Logic.LogPermiso)object).getCodRoles());
            }catch(ClassCastException cce){
                throw new ConverterException();
            }
        }
        return string;
    }
 
    @SuppressWarnings("unchecked")
    private Logic.LogPermiso getObjectFromUIPickListComponent(UIComponent component, String value) {
        final DualListModel<Logic.LogPermiso> dualList;
        try{
            dualList = (DualListModel<Logic.LogPermiso>) ((PickList)component).getValue();
            Logic.LogPermiso team = getObjectFromList(dualList.getSource(),Integer.valueOf(value));
            if(team==null){
               team = getObjectFromList(dualList.getTarget(),Integer.valueOf(value));
            }
             
            return team;
        }catch( Exception ex){
            throw new ConverterException();
        }
    }
 
    private Logic.LogPermiso getObjectFromList(final List<?> list, final int identifier) {
        for(final Object object:list){
            final Logic.LogPermiso team = (Logic.LogPermiso) object;
            if(team.getCodRoles().equals(identifier)){
                return team;
            }
        }
        return null;
    }
    

}