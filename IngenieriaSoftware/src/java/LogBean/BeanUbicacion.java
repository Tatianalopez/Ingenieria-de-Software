/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogBean;

import Logic.LogAdministracion;
import Logic.LogUbicacion;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Ilejaam
 */
@ManagedBean(name = "MBUbicacion")
@ViewScoped
@SessionScoped
public class BeanUbicacion implements Serializable {

    /**
     * Variables que se conectan a las diferentes clases de el proyecto
     * NegociosISA > Logic*
     */
    LogUbicacion Ubi = new LogUbicacion();
    LogUbicacion UbiDepto = new LogUbicacion();
    LogUbicacion UbiCiudad = new LogUbicacion();
    LogUbicacion UbiPiso = new LogUbicacion();

    /**
     * variables para alamacenar los resultados de las consultas*
     */
    ResultSet Dat = null;
    private ArrayList<SelectItem> CargaDep = new ArrayList<>();
    private ArrayList<SelectItem> CargaCiu = new ArrayList<>();
    private List<LogUbicacion> ListDepto = null;
    private List<LogUbicacion> ListCiudad = null;
    private List<LogUbicacion> listPisos = null;

    /**
     * Variables para el proceso de crear y modificar las ubicaciones utilizadas
     * en el sistema, en el proceso de administración *
     */
    private String Informacion;
    private String estadoRadioSeleccion;
    private boolean EstDepto = false;
    private boolean EstCiu = false;
    private boolean EstPisos = false;
    private boolean Exentos = false;

    private String TituloDialogDepto;
    private String TituloDialogCiudad;
    private String TituloDialogPiso;

    private boolean DireccionSinupot = false;

    /**
     * Variable tipo BeanSesion para traer los atributos y metodos de el
     * ManagedBean BeanSesion.java
     *
     *
     * @see BeanSesion.java
     */
    @ManagedProperty("#{MBSesion}")
    private BeanSesion mBsesion;

    public BeanSesion getmBsesion() {
        return mBsesion;
    }

    public void setmBsesion(BeanSesion mBsesion) {
        this.mBsesion = mBsesion;
    }

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

    /**
     * Variables*
     */
    /**
     * Departamento *
     */
    private String IdDep;
    private String NomDep;
    /**
     * Ciudad*
     */
    private int IdCiu;
    private String NomCiu;
    private String FkCodDepto;

    private int IdPiso;
    private String NomPiso;

    /**
     * Estructura de la Direcci�n*
     */
    private String DireccGeneral = "";
    private String[] ArrayDirecc = new String[12];
    private String NomUbic;
    private String Num1 = "";
    private String Letr1;
    private boolean Bis = false;
    private String Letr2;
    private boolean Sur1 = false;
    private boolean Este1 = false;
    private String Num2 = "";
    private String Letr3;
    private String Num3 = "";
    private boolean Sur2 = false;
    private boolean Este2 = false;
    private String AnexDirecc = "";
    private String centroPoblado = "";
    private boolean AgregarCentroPoblado = false;
    private ArrayList<SelectItem> CargaCentrosPoblados = new ArrayList<>();

    public BeanUbicacion() {

    }

    /**
     * Metodos get y set de todas las variables / atributos, listas, etc que se
     * utilizaran para enviar y traer datos a las respectivas variables
     * -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     */
    /**
     *
     * @return
     */
    public String[] getArrayDirecc() {
        return ArrayDirecc;
    }

    public void setArrayDirecc(String[] ArrayDirecc) {
        this.ArrayDirecc = ArrayDirecc;
    }

    public boolean isExentos() {
        return Exentos;
    }

    public void setExentos(boolean Exentos) {
        this.Exentos = Exentos;
    }

    public String getInformacion() {
        return Informacion;
    }

    public void setInformacion(String Informacion) {
        this.Informacion = Informacion;
    }

    public ArrayList<SelectItem> getCargaDep() {
        return CargaDep;
    }

    public void setCargaDep(ArrayList<SelectItem> CargaDep) {
        this.CargaDep = CargaDep;
    }

    public ArrayList<SelectItem> getCargaCiu() {
        return CargaCiu;
    }

    public void setCargCiu(ArrayList<SelectItem> CargaCiu) {
        this.CargaCiu = CargaCiu;
    }

    public String getIdDep() {
        return IdDep;
    }

    public void setIdDep(String IdDep) {
        this.IdDep = IdDep;
    }

    public String getNomDep() {
        return NomDep;
    }

    public void setNomDep(String NomDep) {
        this.NomDep = NomDep;
    }

    public int getIdCiu() {
        return IdCiu;
    }

    public void setIdCiu(int IdCiu) {
        this.IdCiu = IdCiu;
    }

    public String getNomCiu() {
        return NomCiu;
    }

    public void setNomCiu(String NomCiu) {
        this.NomCiu = NomCiu;
    }

    public String getFkCodDepto() {
        return FkCodDepto;
    }

    public void setFkCodDepto(String FkCodDepto) {
        this.FkCodDepto = FkCodDepto;
    }

    public int getIdPiso() {
        return IdPiso;
    }

    public void setIdPiso(int IdPiso) {
        this.IdPiso = IdPiso;
    }

    public String getNomPiso() {
        return NomPiso;
    }

    public void setNomPiso(String NomPiso) {
        this.NomPiso = NomPiso;
    }

    public List<LogUbicacion> getListDepto() {
        return ListDepto;
    }

    public void setListDepto(List<LogUbicacion> ListDepto) {
        this.ListDepto = ListDepto;
    }

    public List<LogUbicacion> getListCiudad() {
        return ListCiudad;
    }

    public void setListCiudad(List<LogUbicacion> ListCiudad) {
        this.ListCiudad = ListCiudad;
    }

    public List<LogUbicacion> getListPisos() {
        return listPisos;
    }

    public void setListPisos(List<LogUbicacion> listPisos) {
        this.listPisos = listPisos;
    }

    public LogUbicacion getUbi() {
        return Ubi;
    }

    public void setUbi(LogUbicacion Ubi) {
        this.Ubi = Ubi;
    }

    public LogUbicacion getUbiDepto() {
        return UbiDepto;
    }

    public void setUbiDepto(LogUbicacion UbiDepto) {
        this.UbiDepto = UbiDepto;
    }

    public LogUbicacion getUbiCiudad() {
        return UbiCiudad;
    }

    public void setUbiCiudad(LogUbicacion UbiCiudad) {
        this.UbiCiudad = UbiCiudad;
    }

    public LogUbicacion getUbiPiso() {
        return UbiPiso;
    }

    public void setUbiPiso(LogUbicacion UbiPiso) {
        this.UbiPiso = UbiPiso;
    }

    public String getTituloDialogDepto() {
        return TituloDialogDepto;
    }

    public void setTituloDialogDepto(String TituloDialogDepto) {
        this.TituloDialogDepto = TituloDialogDepto;
    }

    public String getTituloDialogCiudad() {
        return TituloDialogCiudad;
    }

    public void setTituloDialogCiudad(String TituloDialogCiudad) {
        this.TituloDialogCiudad = TituloDialogCiudad;
    }

    public String getTituloDialogPiso() {
        return TituloDialogPiso;
    }

    public void setTituloDialogPiso(String TituloDialogPiso) {
        this.TituloDialogPiso = TituloDialogPiso;
    }

    public boolean isDireccionSinupot() {
        return DireccionSinupot;
    }

    public void setDireccionSinupot(boolean DireccionSinupot) {
        this.DireccionSinupot = DireccionSinupot;
    }

    public boolean isEstDepto() {
        return EstDepto;
    }

    public void setEstDepto(boolean EstDepto) {
        this.EstDepto = EstDepto;
    }

    public boolean isEstCiu() {
        return EstCiu;
    }

    public void setEstCiu(boolean EstCiu) {
        this.EstCiu = EstCiu;
    }

    public boolean isEstPisos() {
        return EstPisos;
    }

    public void setEstPisos(boolean EstPisos) {
        this.EstPisos = EstPisos;
    }

    public String getEstadoRadioSeleccion() {
        return estadoRadioSeleccion;
    }

    public void setEstadoRadioSeleccion(String estadoRadioSeleccion) {
        this.estadoRadioSeleccion = estadoRadioSeleccion;
    }

    public String getDireccGeneral() {
        return DireccGeneral;
    }

    public void setDireccGeneral(String DireccGeneral) {
        this.DireccGeneral = DireccGeneral;
    }

    public String getNomUbic() {
        return NomUbic;
    }

    public void setNomUbic(String NomUbic) {
        this.NomUbic = NomUbic;
    }

    public String getNum1() {
        return Num1;
    }

    public void setNum1(String Num1) {
        this.Num1 = Num1;
    }

    public String getLetr1() {
        return Letr1;
    }

    public void setLetr1(String Letr1) {
        this.Letr1 = Letr1;
    }

    public boolean isBis() {
        return Bis;
    }

    public void setBis(boolean Bis) {
        this.Bis = Bis;
    }

    public String getLetr2() {
        return Letr2;
    }

    public void setLetr2(String Letr2) {
        this.Letr2 = Letr2;
    }

    public boolean isSur1() {
        return Sur1;
    }

    public void setSur1(boolean Sur1) {
        this.Sur1 = Sur1;
    }

    public boolean isEste1() {
        return Este1;
    }

    public void setEste1(boolean Este1) {
        this.Este1 = Este1;
    }

    public String getNum2() {
        return Num2;
    }

    public void setNum2(String Num2) {
        this.Num2 = Num2;
    }

    public String getLetr3() {
        return Letr3;
    }

    public void setLetr3(String Letr3) {
        this.Letr3 = Letr3;
    }

    public String getNum3() {
        return Num3;
    }

    public void setNum3(String Num3) {
        this.Num3 = Num3;
    }

    public boolean isSur2() {
        return Sur2;
    }

    public void setSur2(boolean Sur2) {
        this.Sur2 = Sur2;
    }

    public boolean isEste2() {
        return Este2;
    }

    public void setEste2(boolean Este2) {
        this.Este2 = Este2;
    }

    public String getAnexDirecc() {
        return AnexDirecc;
    }

    public void setAnexDirecc(String AnexDirecc) {
        this.AnexDirecc = AnexDirecc;
    }

    public String getCentroPoblado() {
        return centroPoblado;
    }

    public void setCentroPoblado(String centroPoblado) {
        this.centroPoblado = centroPoblado;
    }

    public boolean isAgregarCentroPoblado() {
        return AgregarCentroPoblado;
    }

    public void setAgregarCentroPoblado(boolean AgregarCentroPoblado) {
        this.AgregarCentroPoblado = AgregarCentroPoblado;
    }

    public ArrayList<SelectItem> getCargaCentrosPoblados() {
        return CargaCentrosPoblados;
    }

    public void setCargaCentrosPoblados(ArrayList<SelectItem> CargaCentrosPoblados) {
        this.CargaCentrosPoblados = CargaCentrosPoblados;
    }

    public ArrayList<SelectItem> cargDepto() {
        try {
            Iterator<LogUbicacion> Ite;
            Ite = Ubi.getDepartamento().iterator();
            while (Ite.hasNext()) {
                LogUbicacion MBUbi = Ite.next();
                this.CargaDep.add(new SelectItem(MBUbi.getIdDep(), MBUbi.getNomDep()));
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".cargDepto()' causado por: " + e.getMessage());
            mbTodero.error();
        }
        return CargaDep;
    }


    public ArrayList<SelectItem> cargCiudad() {
        try {
            Iterator<LogUbicacion> Ite;
            Ite = Ubi.getCiudad(Integer.parseInt(this.NomDep)).iterator();
            while (Ite.hasNext()) {
                LogUbicacion MBUbi = Ite.next();
                this.CargaCiu.add(new SelectItem(MBUbi.getIdCiu(), MBUbi.getNomCiu()));
            }
        } catch (NumberFormatException e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".cargCiudad()' causado por: " + e.getMessage());
            mbTodero.error();

        }
        return CargaCiu;
    }

    public ArrayList<SelectItem> cargCentrosPoblados(int cod_ciudad) {
        try {
            Iterator<LogUbicacion> Ite;
            Ite = Ubi.getCentrosPoblados(cod_ciudad).iterator();
            while (Ite.hasNext()) {
                LogUbicacion MBUbi = Ite.next();
                this.CargaCentrosPoblados.add(new SelectItem(MBUbi.getIdCentroPobl(), MBUbi.getNomCentroPobl()));
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".cargCentrosPoblados()' causado por: " + e.getMessage());
            mbTodero.error();
        }
        return CargaCentrosPoblados;
    }

    /**
     * Metodo tipo ajax que sirve para llamar el metodo cargCiudad() y cargar
     * las ciudades en la respectiva lista
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @since 01-05-2015
     */
    public void onCiudad() {
        try {
            if (NomDep != null && !NomDep.equals("")) {
                //JOptionPane.showMessageDialog(null, this.NomDep);
                CargaCiu.clear();
                cargCiudad();
            } else {
                System.out.println("No funciona");
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".onCiudad()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo para registrar un departamento dentro de la base de datos en el
     * modulo de adiminstracion
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @since 01-05-2015
     */
    public void regisDepto() {
        try {
            LogAdministracion Adm = new LogAdministracion();
            try {
                mbTodero.resetTable("FrmGestionUbi:DeptoTable");
                if ("".equals(this.NomDep)) {
                    mbTodero.setMens("Favor ingrese  la informacion correspondiente");
                    mbTodero.warn();
                } else {
                    int val = 0;
                    LogUbicacion Ubic = new LogUbicacion();
                    Ubic.setNomDep(NomDep);
                    val = Ubic.InserDepto(mBsesion.codigoMiSesion());
                    this.NomDep = "";
                    mbTodero.setMens((val > 0) ? "Registro creado correctamente" : "El nombre ingresado ya se encuentra creado");
                    mbTodero.info();
                    mbTodero.resetTable("FrmGestionUbi:DeptoTable");
                    this.ListDepto.clear();
                    this.ListDepto = Ubi.ConsulDepto();
                    UbiDepto.setIdDep(Adm.ConsulCodigoPrincipal("Cod_Departamento", "Departamento"));
                    this.CargaDep = new ArrayList<>();
                    this.CargaDep = this.cargDepto();
                    RequestContext.getCurrentInstance().execute("PF('DlgDepto').hide()");
                }
            } catch (Exception e) {
                mbTodero.setMens(e.getMessage());
                mbTodero.fatal();
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".regisDepto()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que permite realizar la modificacion de departamento el el modulo
     * de andimistracion
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @since 01-05-2015
     */
    public void modifiDepto() { //ok
        try {
            mbTodero.resetTable("FrmGestionUbi:DeptoTable");
            LogAdministracion Adm = new LogAdministracion();
            if ("".equals(NomDep)) {
                mbTodero.setMens("Favor ingrese la informacion correspondiente");
                mbTodero.warn();
            } else {
                int val = 0;
                LogUbicacion Ubic = new LogUbicacion();
                Ubic.setIdDep(Integer.parseInt(IdDep));
                Ubic.setNomDep(NomDep);
                val = Ubic.ActualizaDepto(mBsesion.codigoMiSesion());
                mbTodero.setMens((val > 0) ? "Informacion actualizada correctamente" : "El nombre ingresado ya se encuentra creado");
                mbTodero.info();
                this.cargDepto();
                mbTodero.resetTable("FrmGestionUbi:DeptoTable");
                this.ListDepto.clear();
                this.ListDepto = Ubi.ConsulDepto();
                Ubi.setIdDep(Adm.ConsulCodigoPrincipal("Cod_Departamento", "Departamento"));
                RequestContext.getCurrentInstance().execute("PF('DlgDepto').hide()");
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".modifiDepto()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que permite registrar una ciudad dentro de la Base de Datos, en el
     * modulo de administración
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @since 01-05-2015
     */
    public void regisCiudad() throws SQLException {
        LogAdministracion Adm = new LogAdministracion();
        try {
            mbTodero.resetTable("FrmGestionUbi:CiudadTable");
            if ("".equals(this.NomCiu) && "".equals(this.FkCodDepto)) {
                mbTodero.setMens("Favor ingrese  la informacion correspondiente");
                mbTodero.warn();
            } else if ("".equals(this.FkCodDepto)) {
                mbTodero.setMens("Debe llenar el campo 'Departamento perteneciente'");
                mbTodero.warn();
            } else if ("".equals(this.NomCiu)) {
                mbTodero.setMens("Debe llenar el campo 'Nombre'");
                mbTodero.warn();
            } else {
                int val = 0;
                LogUbicacion Ubic = new LogUbicacion();
                Ubic.setNomCiu(NomCiu);
                Ubic.setIdDep(Integer.parseInt(FkCodDepto));
                val = Ubic.InserCiudad(mBsesion.codigoMiSesion(), (Exentos) ? "S" : "N");
                RequestContext.getCurrentInstance().execute("PF('DlgCiudad').hide()");
                this.NomCiu = "";
                this.IdDep = "";
                mbTodero.setMens((val > 0) ? "Registro creado correctamente" : "El nombre y departamento ingresados ya se encuentran creados");
                mbTodero.info();
                mbTodero.resetTable("FrmGestionUbi:CiudadTable");
                this.ListCiudad.clear();
                this.ListCiudad = Ubic.ConsulCiudad();
                Ubi.setIdCiu(Adm.ConsulCodigoPrincipal("Cod_Ciudad", "Ciudad"));
            }
        } catch (NumberFormatException e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".regisCiudad()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo que permite realizar la modificacon de una ciudad, en el modulo de
     * administración
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @since 01-05-2015
     */
    public void modifiCiudad() {
        try {
            LogAdministracion Adm = new LogAdministracion();
            mbTodero.resetTable("FrmGestionUbi:CiudadTable");
            if ("".equals(this.NomCiu) && "".equals(this.FkCodDepto)) {
                mbTodero.setMens("Favor ingrese  la informacion correspondiente");
                mbTodero.warn();
            } else if ("".equals(this.FkCodDepto)) {
                mbTodero.setMens("Debe llenar el campo 'Departamento perteneciente'");
                mbTodero.warn();
            } else if ("".equals(this.NomCiu)) {
                mbTodero.setMens("Debe llenar el campo 'Nombre'");
                mbTodero.warn();
            } else {
                int val = 0;
                LogUbicacion Ubic = new LogUbicacion();
                Ubic.setIdCiu(IdCiu);
                Ubic.setNomCiu(NomCiu);
                Ubic.setFkCodDepto(FkCodDepto);
                val = Ubic.ActualizaCiudad(mBsesion.codigoMiSesion(), (Exentos) ? "S" : "N");
                mbTodero.setMens((val > 0) ? "Registro actualizado correctamente" : "El nombre y departamento ingresados ya se encuentran creados");
                mbTodero.info();
                mbTodero.resetTable("FrmGestionUbi:CiudadTable");
                this.ListCiudad.clear();
                this.ListCiudad = Ubic.ConsulCiudad();
                //  Ubi.setIdCiu(Adm.ConsulCodigoPrincipal("Cod_Ciudad", "Ciudad"));
                RequestContext.getCurrentInstance().execute("PF('DlgCiudad').hide()");
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".modifiCiudad()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }


 
    public void tipoDireccion() {
        try {
            if ("Urbano".equals(this.NomPiso)) {
                RequestContext.getCurrentInstance().execute("PF('DLGDireccion').show()");
            } else {
                RequestContext.getCurrentInstance().execute("PF('DLGDireccion').hide()");
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".tipoDireccion()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que permite realizar el llenado de la informacion que se ingresa
     * de todo el formulario de la direccion, une todo lo ingresado como una
     * cadena y lo agrega a una caja de texto
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @param Tipo
     * @since 01-05-2015
     */
    public void llenadoDireccion(int Tipo) {
        try {
            if (Tipo == 1) {
                Este1 = false;
                Este2 = false;
                Sur1 = false;
                Sur2 = false;
                this.Letr2 = "";
            }
            ArrayDirecc = new String[12];
            DireccGeneral = "";
            ArrayDirecc[0] = NomUbic;
            ArrayDirecc[1] = Num1;
            ArrayDirecc[2] = Letr1;
            if (this.Bis == true) {
                ArrayDirecc[3] = "BIS";
                ArrayDirecc[4] = Letr2;
            }
            if (this.Este1 == true) {
                ArrayDirecc[5] = "ESTE";
            } else if (this.Sur1 == true) {
                ArrayDirecc[5] = "SUR";
            }
            ArrayDirecc[6] = String.valueOf(Num2);
            ArrayDirecc[7] = Letr3;
            ArrayDirecc[8] = String.valueOf(Num3);
            if (this.Este2 == true) {
                ArrayDirecc[9] = "ESTE";
            } else if (this.Sur2 == true) {
                ArrayDirecc[9] = "SUR";
            }
            ArrayDirecc[10] = AnexDirecc;
            for (int j = 0; j <= CargaCentrosPoblados.size() - 1; j++) {

                if (centroPoblado.equals(CargaCentrosPoblados.get(j).getValue().toString())) {
                    ArrayDirecc[11] = CargaCentrosPoblados.get(j).getLabel().toString();
                }
            }

            for (int i = 0; i < ArrayDirecc.length; i++) {
                if (!"".equals(ArrayDirecc[i]) && ArrayDirecc[i] != null && !"0".equals(ArrayDirecc[i])) {
                    DireccGeneral = DireccGeneral + ArrayDirecc[i] + " ";
                }
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".llenadoDireccion()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    public void onCheckCentroPoblado() {
        try {
            if (AgregarCentroPoblado == false) {
                centroPoblado = "";
                llenadoDireccion(0);
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".onCheckCentroPoblado()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * FIN Metodos de funcionalidad de la clase
     * -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     */
}
//

