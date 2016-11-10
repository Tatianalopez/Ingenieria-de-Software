package LogBean;

import Conexion.Conexion;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import Logic.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "MBLogin")
@SessionScoped
@ViewScoped
public class BeanLogin {

    public BeanLogin() {
    }

    private HttpServletRequest HttpServletRequest;
    private FacesContext FContext;
    LogEmpleado Emp = new LogEmpleado();
    ResultSet Dat = null;
    ResultSet Dat1 = null;
    ResultSet Tab = null;
    FacesContext Redir;
    private String[] Rol;
    private String Mens, thisIp = "";
    int CodEmp;
    int cc = 0;
  
    /**
     * Variables utilizadas para el manejo de la sesion de los usuarios*
     */
    private String NomUsuario;
    private String NomUsuario1;
    private String PassUsuario;
    private String DocEmp;
    private String DocEmp1;
    private String DocEmp2;
    private String PassOld;
    private String PassOne;
    private String PassTwo;
    private String Novedad;
    private String resulto;
    int CodEntrada;
    private String Foto_emp; //GCH - AGO2016
    
    /**
     * Variable tipo BeanTodero para traer los atributos y metodos de el
     * ManagedBean BeanTodero.java
     *
     *
     * @see BeanTodero.java
     */
    @ManagedProperty("#{MBTodero}")
    private BeanTodero mbTodero;

    public BeanTodero getMbTodero() {
        return mbTodero;
    }

    public void setMbTodero(BeanTodero mbTodero) {
        this.mbTodero = mbTodero;
    }

  
    public String getNomUsuario() {
        return NomUsuario;
    }

    public void setNomUsuario(String NomUsuario) {
        this.NomUsuario = NomUsuario;
    }

    public String getNomUsuario1() {
        return NomUsuario1;
    }

    public void setNomUsuario1(String NomUsuario1) {
        this.NomUsuario1 = NomUsuario1;
    }

    public String getPassUsuario() {
        return PassUsuario;
    }

    public void setPassUsuario(String PassUsuario) {
        this.PassUsuario = PassUsuario;
    }

    public String getDocEmp() {
        return DocEmp;
    }

    public void setDocEmp(String DocEmp) {
        this.DocEmp = DocEmp;
    }

    public String getDocEmp1() {
        return DocEmp1;
    }

    public void setDocEmp1(String DocEmp1) {
        this.DocEmp1 = DocEmp1;
    }

    public String getDocEmp2() {
        return DocEmp2;
    }

    public void setDocEmp2(String DocEmp2) {
        this.DocEmp2 = DocEmp2;
    }

    public int getCodEmp() {
        return CodEmp;
    }

    public void setCodEmp(int CodEmp) {
        this.CodEmp = CodEmp;
    }

    public String getPassOld() {
        return PassOld;
    }

    public void setPassOld(String PassOld) {
        this.PassOld = PassOld;
    }

    public String getPassOne() {
        return PassOne;
    }

    public void setPassOne(String PassOne) {
        this.PassOne = PassOne;
    }

    public String getPassTwo() {
        return PassTwo;
    }

    public void setPassTwo(String PassTwo) {
        this.PassTwo = PassTwo;
    }

    public String getNovedad() {
        return Novedad;
    }

    public void setNovedad(String Novedad) {
        this.Novedad = Novedad;
    }

    public String getResulto() {
        return resulto;
    }

    public void setResulto(String resulto) {
        this.resulto = resulto;
    }

    //GCH - AGO2016
    public String getFoto_emp() {
        return Foto_emp;
    }

    public void setFoto_emp(String Foto_emp) {
        this.Foto_emp = Foto_emp;
    }

    public String login() {
        try {
            if (CodEntrada < 3) {
                if ("".equals(this.NomUsuario) || "".equals(this.PassUsuario)) {
                    mbTodero.setMens("Falta informacion para realizar el proceso");
                    mbTodero.warn();
                    this.resulto = "mal";
                } else {
                    Emp.setUsuarEmp(NomUsuario);
                    Emp.setPassEmp(PassUsuario);
                    Dat1 = null;
                    Dat1 = Emp.ConsultaTipoPassword();
                    if (Dat1.next()) {
                        if ("1".equals(Dat1.getString("Temporal_Empleados"))) {
                            CodEmp = Integer.parseInt(Dat1.getString("Cod_Empleados"));
                            Map<String, Object> options = new HashMap<>();
                            options.put("modal", true);
                            options.put("draggable", false);
                            options.put("resizable", false);
                            options.put("contentHeight", 280);
                            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("UsuarPass", Dat1.getString("Cod_Empleados"));
                            RequestContext.getCurrentInstance().openDialog("XHTML/Cuerpo/CambioPassword.xhtml", options, null);
                        } else {
                            Dat = Emp.ConsulRolUusario();
                            if (Dat.next()) {
                                int codEmp = Integer.parseInt(Dat.getString("Cod_Empleados"));
                                String nombre_rol = Dat.getString("Nombre_Rol");
                                String nombres_apellidos = Dat1.getString("Nombre_Empleados") + " " + Dat1.getString("Apellido_empleados");
                                String documento = Dat.getString("Documento_Empleados");
                                String foto = Dat.getString("Foto_Empleados"); //GCH - ago2016
                                //Establecer variables de sesion
                                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("sessionRol", nombre_rol);
                                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("sessionCodEmp", codEmp);
                                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("sessionDoc", documento);
                                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("sessionNombresEmpl", nombres_apellidos);
                                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("sessionFoto", foto); //GCH - ago2016
                                return "CuerpoSiai.xhtml";

                            } else {
                                mbTodero.setMens("El usuario con el que desea ingresar a la aplicación no se encuentra activo o no se tiene registro");
                                mbTodero.warn();
                                this.resulto = "mal";
                                return "index.xhtml";
                            }
					
                        }
                    } else {
                        mbTodero.setMens("Usuario y/o contraseña incorrectos");
                        mbTodero.warn();
                        this.resulto = "mal";
                        CodEntrada++;
                        return "index.xhtml";
                    }
                    Conexion.CloseCon();
                }
            } else {
                mbTodero.setMens("Ha realizado mas de tres intentos favor realice alguna de las opciones de Ingreso al sistema o contacte al administrador");
                mbTodero.warn();
                this.resulto = "mal";
                CodEntrada = 0;
                //FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
                return "index.xhtml";
            }
        } catch (SQLException | NumberFormatException e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".login()' causado por: " + e.getMessage());
            mbTodero.error();
        }

        return "../index.xhtml";
    }

}
