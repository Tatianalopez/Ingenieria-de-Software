/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogBean;

import Logic.LogAdministracion;
import Logic.LogPerito;
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
 * <p>
 * <b>Managed bean / clase que se utiliza para crear todos los atributos y
 * metodos necesarios para el proceso y manejo avaluadores o peritos </b></p>
 *
 * @author Jhojan Stiven Rodriguez
 * @author Maira Alejandra Carvajal
 * @version 2.0.0
 * @since 01-11-2014 1.0.0
 *
 */
@ManagedBean(name = "MBPerito")
@ViewScoped
@SessionScoped
public class BeanPerito implements Serializable {

    public BeanPerito() {

    }

    /**
     * Variables para Redicacion*
     */
    private List<LogPerito> ListPeritAvaluadoresSeleccionadosForm = new ArrayList<>();
    private List<LogPerito> ListPeritAvaluadoresSeleccionadosTabla = new ArrayList<>();
    private List<LogPerito> ListPeritAvaluo = new ArrayList<>();
    private List<LogPerito> listPeritSeleccRad = new ArrayList<>();
    private List<LogPerito> ListPeritRadGenerar = new ArrayList<>();
    private boolean estadoRadioAgregarTarifaPer1;
    private boolean estadoRadioAgregarTarifaPer2;
    private boolean estadoRadioAgregarTarifaPer3;
    private boolean estadoRadioAgregarTarifaPer4;
    private boolean estadoRadioAgregarTarifaPer5;
    private String opcionRadioTarifasPactadosPer1;
    private String opcionRadioTarifasPactadosPer2;
    private String opcionRadioTarifasPactadosPer3;
    private String opcionRadioTarifasPactadosPer4;
    private String opcionRadioTarifasPactadosPer5;

    private boolean estadoTarifasPactPer1 = false;
    private boolean estadoTarifasPactPer2 = false;
    private boolean estadoTarifasPactPer3 = false;
    private boolean estadoTarifasPactPer4 = false;
    private boolean estadoTarifasPactPer5 = false;

    private String tipoTarifaPerito;
    private String valorTarifaPerito;

    private String tipoTarifaPeri1;
    private String valorTarifaPer1;

    private String tipoTarifaPeri2;
    private String valorTarifaPer2;

    private String tipoTarifaPeri3;
    private String valorTarifaPer3;

    private String tipoTarifaPeri4;
    private String valorTarifaPer4;

    private String tipoTarifaPeri5;
    private String valorTarifaPer5;

    private boolean estadoCambioAvaluador = false;

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
     * Variable tipo BeanArchivos para traer los atributos y metodos de el
     * ManagedBean BeanArchivos.java
     *
     *
     * @see BeanArchivos.java
     */
    @ManagedProperty("#{MBArchivos}")
    private BeanArchivos mbArchivos;

    public BeanArchivos getMbArchivos() {
        return mbArchivos;
    }

    public void setMbArchivos(BeanArchivos mbArchivos) {
        this.mbArchivos = mbArchivos;
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
     * Variables que se conectan a las diferentes clases de el proyecto
     * NegociosISA > Logic*
     */
    LogPerito Per = new LogPerito();
    LogAdministracion Adm = new LogAdministracion();
    LogUbicacion Ubi = new LogUbicacion();

    /**
     * Variables implicitas, listas, string, int etc
     *
     */
    private int num_perito;
    private int cod_preRadic;
    private int cod_avaluo;
    ResultSet Dat = null;
    String Mens;
    private ArrayList<SelectItem> CargTipDocPer = new ArrayList<>();
    private ArrayList<SelectItem> CargEstPer = new ArrayList<>();
    private ArrayList<SelectItem> CargRegPer = new ArrayList<>();
    private ArrayList<SelectItem> CargCalifiPer = new ArrayList<>();

    private ArrayList<SelectItem> CargaCiu = new ArrayList<>();
    private List<LogPerito> SelectPerito;
    private List<LogPerito> ListPerit = null;

    private int CodPerito;
    private String NumDocPerito;
    private String NombrePerito;
    private String ApellidoPerito;
    private String TelefonoPerito;
    private String CelularPerito;
    private String DireccionPerito;
    private String EmailPerito;
    private String NumCuentaPerito;
    private int EstadoPerito;//Llave foranea de la tabla de Estado
    private int TipDocuPerito;//Llava foranea de la tabla de tipo de documento

    private String numDepto;
    private String numCiudad;

    private String numRegimen;
    private String numtipoRegimen;

    private boolean estadoRegistro = true;
    private boolean estadoActualizar = false;

    /**
     * Metodos get y set de todas las variables / atributos, listas, etc que se
     * utilizaran para enviar y traer datos a las respectivas variables
     * -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     */
    /**
     *
     * @return
     */
    public List<LogPerito> getListPeritSeleccRad() {
        return listPeritSeleccRad;
    }

    public void setListPeritSeleccRad(List<LogPerito> listPeritSeleccRad) {
        this.listPeritSeleccRad = listPeritSeleccRad;
    }

    
    
    public LogUbicacion getUbi() {
        return Ubi;
    }

    public void setUbi(LogUbicacion Ubi) {
        this.Ubi = Ubi;
    }

    public int getNum_perito() {
        return num_perito;
    }

    public void setNum_perito(int num_perito) {
        this.num_perito = num_perito;
    }

    public int getCod_preRadic() {
        return cod_preRadic;
    }

    public void setCod_preRadic(int cod_preRadic) {
        this.cod_preRadic = cod_preRadic;
    }

    public int getCod_avaluo() {
        return cod_avaluo;
    }

    public void setCod_avaluo(int cod_avaluo) {
        this.cod_avaluo = cod_avaluo;
    }

    public List<LogPerito> getListPeritAvaluadoresSeleccionadosForm() {
        return ListPeritAvaluadoresSeleccionadosForm;
    }

    public void setListPeritAvaluadoresSeleccionadosForm(List<LogPerito> ListPeritAvaluadoresSeleccionadosForm) {
        this.ListPeritAvaluadoresSeleccionadosForm = ListPeritAvaluadoresSeleccionadosForm;
    }

    public List<LogPerito> getListPeritAvaluadoresSeleccionadosTabla() {
        return ListPeritAvaluadoresSeleccionadosTabla;
    }

    public void setListPeritAvaluadoresSeleccionadosTabla(List<LogPerito> ListPeritAvaluadoresSeleccionadosTabla) {
        this.ListPeritAvaluadoresSeleccionadosTabla = ListPeritAvaluadoresSeleccionadosTabla;
    }

    public List<LogPerito> getListPeritAvaluo() {
        return ListPeritAvaluo;
    }

    public void setListPeritAvaluo(List<LogPerito> ListPeritAvaluo) {
        this.ListPeritAvaluo = ListPeritAvaluo;
    }

    public List<LogPerito> getListPeritRadGenerar() {
        return ListPeritRadGenerar;
    }

    public void setListPeritRadGenerar(List<LogPerito> ListPeritRadGenerar) {
        this.ListPeritRadGenerar = ListPeritRadGenerar;
    }

    public boolean isEstadoRadioAgregarTarifaPer1() {
        return estadoRadioAgregarTarifaPer1;
    }

    public void setEstadoRadioAgregarTarifaPer1(boolean estadoRadioAgregarTarifaPer1) {
        this.estadoRadioAgregarTarifaPer1 = estadoRadioAgregarTarifaPer1;
    }

    public boolean isEstadoRadioAgregarTarifaPer2() {
        return estadoRadioAgregarTarifaPer2;
    }

    public void setEstadoRadioAgregarTarifaPer2(boolean estadoRadioAgregarTarifaPer2) {
        this.estadoRadioAgregarTarifaPer2 = estadoRadioAgregarTarifaPer2;
    }

    public boolean isEstadoRadioAgregarTarifaPer3() {
        return estadoRadioAgregarTarifaPer3;
    }

    public void setEstadoRadioAgregarTarifaPer3(boolean estadoRadioAgregarTarifaPer3) {
        this.estadoRadioAgregarTarifaPer3 = estadoRadioAgregarTarifaPer3;
    }

    public boolean isEstadoRadioAgregarTarifaPer4() {
        return estadoRadioAgregarTarifaPer4;
    }

    public void setEstadoRadioAgregarTarifaPer4(boolean estadoRadioAgregarTarifaPer4) {
        this.estadoRadioAgregarTarifaPer4 = estadoRadioAgregarTarifaPer4;
    }

    public boolean isEstadoRadioAgregarTarifaPer5() {
        return estadoRadioAgregarTarifaPer5;
    }

    public void setEstadoRadioAgregarTarifaPer5(boolean estadoRadioAgregarTarifaPer5) {
        this.estadoRadioAgregarTarifaPer5 = estadoRadioAgregarTarifaPer5;
    }

    public String getOpcionRadioTarifasPactadosPer1() {
        return opcionRadioTarifasPactadosPer1;
    }

    public void setOpcionRadioTarifasPactadosPer1(String opcionRadioTarifasPactadosPer1) {
        this.opcionRadioTarifasPactadosPer1 = opcionRadioTarifasPactadosPer1;
    }

    public String getOpcionRadioTarifasPactadosPer2() {
        return opcionRadioTarifasPactadosPer2;
    }

    public void setOpcionRadioTarifasPactadosPer2(String opcionRadioTarifasPactadosPer2) {
        this.opcionRadioTarifasPactadosPer2 = opcionRadioTarifasPactadosPer2;
    }

    public String getOpcionRadioTarifasPactadosPer3() {
        return opcionRadioTarifasPactadosPer3;
    }

    public void setOpcionRadioTarifasPactadosPer3(String opcionRadioTarifasPactadosPer3) {
        this.opcionRadioTarifasPactadosPer3 = opcionRadioTarifasPactadosPer3;
    }

    public String getOpcionRadioTarifasPactadosPer4() {
        return opcionRadioTarifasPactadosPer4;
    }

    public void setOpcionRadioTarifasPactadosPer4(String opcionRadioTarifasPactadosPer4) {
        this.opcionRadioTarifasPactadosPer4 = opcionRadioTarifasPactadosPer4;
    }

    public String getOpcionRadioTarifasPactadosPer5() {
        return opcionRadioTarifasPactadosPer5;
    }

    public void setOpcionRadioTarifasPactadosPer5(String opcionRadioTarifasPactadosPer5) {
        this.opcionRadioTarifasPactadosPer5 = opcionRadioTarifasPactadosPer5;
    }

    public boolean isEstadoTarifasPactPer1() {
        return estadoTarifasPactPer1;
    }

    public void setEstadoTarifasPactPer1(boolean estadoTarifasPactPer1) {
        this.estadoTarifasPactPer1 = estadoTarifasPactPer1;
    }

    public boolean isEstadoTarifasPactPer2() {
        return estadoTarifasPactPer2;
    }

    public void setEstadoTarifasPactPer2(boolean estadoTarifasPactPer2) {
        this.estadoTarifasPactPer2 = estadoTarifasPactPer2;
    }

    public boolean isEstadoTarifasPactPer3() {
        return estadoTarifasPactPer3;
    }

    public void setEstadoTarifasPactPer3(boolean estadoTarifasPactPer3) {
        this.estadoTarifasPactPer3 = estadoTarifasPactPer3;
    }

    public boolean isEstadoTarifasPactPer4() {
        return estadoTarifasPactPer4;
    }

    public void setEstadoTarifasPactPer4(boolean estadoTarifasPactPer4) {
        this.estadoTarifasPactPer4 = estadoTarifasPactPer4;
    }

    public boolean isEstadoTarifasPactPer5() {
        return estadoTarifasPactPer5;
    }

    public void setEstadoTarifasPactPer5(boolean estadoTarifasPactPer5) {
        this.estadoTarifasPactPer5 = estadoTarifasPactPer5;
    }

    public String getTipoTarifaPerito() {
        return tipoTarifaPerito;
    }

    public void setTipoTarifaPerito(String tipoTarifaPerito) {
        this.tipoTarifaPerito = tipoTarifaPerito;
    }

    public String getValorTarifaPerito() {
        return valorTarifaPerito;
    }

    public void setValorTarifaPerito(String valorTarifaPerito) {
        this.valorTarifaPerito = valorTarifaPerito;
    }

    public String getTipoTarifaPeri1() {
        return tipoTarifaPeri1;
    }

    public void setTipoTarifaPeri1(String tipoTarifaPeri1) {
        this.tipoTarifaPeri1 = tipoTarifaPeri1;
    }

    public String getValorTarifaPer1() {
        return valorTarifaPer1;
    }

    public void setValorTarifaPer1(String valorTarifaPer1) {
        this.valorTarifaPer1 = valorTarifaPer1;
    }

    public String getTipoTarifaPeri2() {
        return tipoTarifaPeri2;
    }

    public void setTipoTarifaPeri2(String tipoTarifaPeri2) {
        this.tipoTarifaPeri2 = tipoTarifaPeri2;
    }

    public String getValorTarifaPer2() {
        return valorTarifaPer2;
    }

    public void setValorTarifaPer2(String valorTarifaPer2) {
        this.valorTarifaPer2 = valorTarifaPer2;
    }

    public String getTipoTarifaPeri3() {
        return tipoTarifaPeri3;
    }

    public void setTipoTarifaPeri3(String tipoTarifaPeri3) {
        this.tipoTarifaPeri3 = tipoTarifaPeri3;
    }

    public String getValorTarifaPer3() {
        return valorTarifaPer3;
    }

    public void setValorTarifaPer3(String valorTarifaPer3) {
        this.valorTarifaPer3 = valorTarifaPer3;
    }

    public String getTipoTarifaPeri4() {
        return tipoTarifaPeri4;
    }

    public void setTipoTarifaPeri4(String tipoTarifaPeri4) {
        this.tipoTarifaPeri4 = tipoTarifaPeri4;
    }

    public String getValorTarifaPer4() {
        return valorTarifaPer4;
    }

    public void setValorTarifaPer4(String valorTarifaPer4) {
        this.valorTarifaPer4 = valorTarifaPer4;
    }

    public String getTipoTarifaPeri5() {
        return tipoTarifaPeri5;
    }

    public void setTipoTarifaPeri5(String tipoTarifaPeri5) {
        this.tipoTarifaPeri5 = tipoTarifaPeri5;
    }

    public String getValorTarifaPer5() {
        return valorTarifaPer5;
    }

    public void setValorTarifaPer5(String valorTarifaPer5) {
        this.valorTarifaPer5 = valorTarifaPer5;
    }

    public boolean isEstadoCambioAvaluador() {
        return estadoCambioAvaluador;
    }

    public void setEstadoCambioAvaluador(boolean estadoCambioAvaluador) {
        this.estadoCambioAvaluador = estadoCambioAvaluador;
    }

    public LogPerito getPer() {
        return Per;
    }

    public void setPer(LogPerito Per) {
        this.Per = Per;
    }

    public LogAdministracion getAdm() {
        return Adm;
    }

    public void setAdm(LogAdministracion Adm) {
        this.Adm = Adm;
    }

    public ArrayList<SelectItem> getCargTipDocPer() {
        return CargTipDocPer;
    }

    public void setCargTipDocPer(ArrayList<SelectItem> CargTipDocPer) {
        this.CargTipDocPer = CargTipDocPer;
    }

    public ArrayList<SelectItem> getCargEstPer() {
        return CargEstPer;
    }

    public void setCargEstPer(ArrayList<SelectItem> CargEstPer) {
        this.CargEstPer = CargEstPer;
    }

    public ArrayList<SelectItem> getCargRegPer() {
        return CargRegPer;
    }

    public void setCargRegPer(ArrayList<SelectItem> CargRegPer) {
        this.CargRegPer = CargRegPer;
    }

    public ArrayList<SelectItem> getCargCalifiPer() {
        return CargCalifiPer;
    }

    public void setCargCalifiPer(ArrayList<SelectItem> CargCalifiPer) {
        this.CargCalifiPer = CargCalifiPer;
    }

    public ArrayList<SelectItem> getCargaCiu() {
        return CargaCiu;
    }

    public void setCargaCiu(ArrayList<SelectItem> CargaCiu) {
        this.CargaCiu = CargaCiu;
    }

    public List<LogPerito> getSelectPerito() {
        return SelectPerito;
    }

    public void setSelectPerito(List<LogPerito> SelectPerito) {
        this.SelectPerito = SelectPerito;
    }

    public List<LogPerito> getListPerit() {
        return ListPerit;
    }

    public void setListPerit(List<LogPerito> ListPerit) {
        this.ListPerit = ListPerit;
    }

    public int getCodPerito() {
        return CodPerito;
    }

    public void setCodPerito(int CodPerito) {
        this.CodPerito = CodPerito;
    }

    public String getNumDocPerito() {
        return NumDocPerito;
    }

    public void setNumDocPerito(String NumDocPerito) {
        this.NumDocPerito = NumDocPerito;
    }

    public String getNombrePerito() {
        return NombrePerito;
    }

    public void setNombrePerito(String NombrePerito) {
        this.NombrePerito = NombrePerito;
    }

    public String getApellidoPerito() {
        return ApellidoPerito;
    }

    public void setApellidoPerito(String ApellidoPerito) {
        this.ApellidoPerito = ApellidoPerito;
    }

    public String getTelefonoPerito() {
        return TelefonoPerito;
    }

    public void setTelefonoPerito(String TelefonoPerito) {
        this.TelefonoPerito = TelefonoPerito;
    }

    public String getCelularPerito() {
        return CelularPerito;
    }

    public void setCelularPerito(String CelularPerito) {
        this.CelularPerito = CelularPerito;
    }

    public String getDireccionPerito() {
        return DireccionPerito;
    }

    public void setDireccionPerito(String DireccionPerito) {
        this.DireccionPerito = DireccionPerito;
    }

    public String getEmailPerito() {
        return EmailPerito;
    }

    public void setEmailPerito(String EmailPerito) {
        this.EmailPerito = EmailPerito;
    }

    public String getNumCuentaPerito() {
        return NumCuentaPerito;
    }

    public void setNumCuentaPerito(String NumCuentaPerito) {
        this.NumCuentaPerito = NumCuentaPerito;
    }

    public int getEstadoPerito() {
        return EstadoPerito;
    }

    public void setEstadoPerito(int EstadoPerito) {
        this.EstadoPerito = EstadoPerito;
    }

    public int getTipDocuPerito() {
        return TipDocuPerito;
    }

    public void setTipDocuPerito(int TipDocuPerito) {
        this.TipDocuPerito = TipDocuPerito;
    }

    public String getNumDepto() {
        return numDepto;
    }

    public void setNumDepto(String numDepto) {
        this.numDepto = numDepto;
    }

    public String getNumCiudad() {
        return numCiudad;
    }

    public void setNumCiudad(String numCiudad) {
        this.numCiudad = numCiudad;
    }

    public String getNumRegimen() {
        return numRegimen;
    }

    public void setNumRegimen(String numRegimen) {
        this.numRegimen = numRegimen;
    }

    public String getNumtipoRegimen() {
        return numtipoRegimen;
    }

    public void setNumtipoRegimen(String numtipoRegimen) {
        this.numtipoRegimen = numtipoRegimen;
    }

    public boolean isEstadoRegistro() {
        return estadoRegistro;
    }

    public void setEstadoRegistro(boolean estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

    public boolean isEstadoActualizar() {
        return estadoActualizar;
    }

    public void setEstadoActualizar(boolean estadoActualizar) {
        this.estadoActualizar = estadoActualizar;
    }

    /**
     * FIN Metodos get y set de todas las variables / atributos, listas, etc que
     * se utilizaran para enviar y traer datos a las respectivas variables
     * -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     */
    /**
     * Metodos de funcionalidad de la clase
     * -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     */
    /**
     * Metodo para cargar los Tipos Documento de los avaluadores de la tabla
     * Tipo_Documento
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Stiven Rodriguez
     * @return ArrayList con la informacion de los tipos de documentos
     * @since 01-11-2014
     */
    public ArrayList<SelectItem> getConsulTipDocPer() {
        try {
            Iterator<LogAdministracion> Ite;
            Ite = Adm.getTipDocumento().iterator();
            while (Ite.hasNext()) {
                LogAdministracion MBAdm = Ite.next();
                this.CargTipDocPer.add(new SelectItem(MBAdm.getCodTipDocumento(), MBAdm.getNomTipDocumento()));
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".getConsulTipDocPer()' causado por: " + e.getMessage());
            mbTodero.error();
        }
        return this.CargTipDocPer;

    }

    /**
     * Metodo Para Cargar los Tipos de Estados de la tabla Estados.
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Stiven Rodriguez
     * @return ArrayList con la informacion de los tipos de estados
     * @since 01-11-2014
     */
    public ArrayList<SelectItem> getConsulEstPer() {
        try {
            Iterator<LogAdministracion> Ite;
            Ite = Adm.getEstado().iterator();
            while (Ite.hasNext()) {
                LogAdministracion MBAdm = Ite.next();
                this.CargEstPer.add(new SelectItem(MBAdm.getCodEstado(), MBAdm.getNombreEstado()));
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".getConsulEstPer()' causado por: " + e.getMessage());
            mbTodero.error();
        }

        return CargEstPer;
    }

    /**
     * Metodo para Cargar los Tipos de Regimenes de la tabla Regimen.
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Stiven Rodriguez
     * @return ArrayList con la informacion de los tipos de regimen
     * @since 01-11-2014
     */
    //Metodo 
    public ArrayList<SelectItem> getConsulRegimPer() {
        try {
            Iterator<LogAdministracion> Ite;
            Ite = Adm.getRegimen().iterator();
            while (Ite.hasNext()) {
                LogAdministracion MBAdm = Ite.next();
                this.CargRegPer.add(new SelectItem(MBAdm.getCodRegimen(), MBAdm.getNombreRegimen()));
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".getConsulRegimPer()' causado por: " + e.getMessage());
            mbTodero.error();
        }

        return CargRegPer;

    }

    /**
     * Metodo para cargar los Tipos de Calificacion de Regimenes de la tabla
     * Calificacion_Regimen.
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Stiven Rodriguez
     * @return ArrayList con la informacion de los tipos de calificacion_regimen
     * @since 01-11-2014
     */
    public ArrayList<SelectItem> getConsulCalifiRegPer() {
        try {

            Iterator<LogAdministracion> Ite;
            Ite = Adm.getCalificacion(Integer.parseInt(this.numRegimen)).iterator();
            while (Ite.hasNext()) {
                LogAdministracion MBAdm = Ite.next();
                this.CargCalifiPer.add(new SelectItem(MBAdm.getCodCalificacion(), MBAdm.getNombreCalificacion()));
            }
        } catch (NumberFormatException e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".getConsulCalifiRegPer()' causado por: " + e.getMessage());
            mbTodero.error();
        }
        return CargCalifiPer;

    }

    /**
     * Metodo tipo ajax para verificar que tipo de Calificacion_Regimen se desea
     * cargar
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @since 01-11-2014
     */
    //Metodo 
    public void onCalifiReg() {
        try {
            if (this.numRegimen != null || this.numRegimen.equals("")) {
                CargCalifiPer.clear();
                getConsulCalifiRegPer();
            }

        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".onCalifiReg()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo para cargar las ciudades donde se ubican los avaluadores
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @return ArrayList con la informacion de las ciudades
     * @since 01-11-2014
     */
    public ArrayList<SelectItem> cargCiudad() {
        try {
            Iterator<LogUbicacion> Ite;
            Ite = Ubi.getCiudad(Integer.parseInt(this.numDepto)).iterator();
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

    /**
     * Metodo tipo ajax para llamar al metodo que carga las ciudades donde se
     * ubican los avaluadores
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @since 01-11-2014
     */
    public void onCiudad() {
        try {
            if (this.numDepto != null && !this.numDepto.equals("")) {
                CargaCiu.clear();
                cargCiudad();
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".onCiudad()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo para insertar / actualizar un avaluador en el sistema
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @param tipo int que recibe como valor un numero para realizar lo
     * siguiente= 1: insertar avaluador 2: modificar avaluador
     * @since 01-11-2014
     */
    public void inserPerito(int tipo) {
        try {
            if (tipo == 1) {
                Per = new LogPerito();
                if (TipDocuPerito <= 0) {
                    mbTodero.setMens("Debe llenar el campo 'Tipo de documento'");
                    mbTodero.warn();
                } else if ("".equals(NumDocPerito)) {
                    mbTodero.setMens("Debe llenar el campo 'N° de documento'");
                    mbTodero.warn();
                } else if (Per.ConsulPeritoVerif(1, 0, NumDocPerito) == true) {
                    mbTodero.setMens("Ya existe un avaluador con este tipo y numero de documento");
                    mbTodero.warn();
                } else if ("".equals(NombrePerito)) {
                    mbTodero.setMens("Debe llenar el campo 'Nombres' ");
                    mbTodero.warn();
                } else if ("".equals(ApellidoPerito)) {
                    mbTodero.setMens("Debe llenar el campo 'Apellidos'");
                    mbTodero.warn();
                } else if ("".equals(TelefonoPerito)) {
                    mbTodero.setMens("Debe llenar el campo 'Telefono'");
                    mbTodero.warn();
                } else if ("".equals(CelularPerito)) {
                    mbTodero.setMens("Debe llenar el campo 'Celular'");
                    mbTodero.warn();
                } else if ("".equals(DireccionPerito)) {
                    mbTodero.setMens("Debe llenar el campo 'Direccion' ");
                    mbTodero.warn();
                } else if ("".equals(EmailPerito)) {
                    mbTodero.setMens("Debe llenar el campo 'Email'");
                    mbTodero.warn();
                } else if ("".equals(NumCuentaPerito)) {
                    mbTodero.setMens("Debe llenar el campo 'Numero de cuenta'");
                    mbTodero.warn();
                } else if (Per.ConsulPeritoVerifcuenta(1, 0, NumCuentaPerito) == true) {
                    mbTodero.setMens("Ya existe un perito con este numero de cuenta");
                    mbTodero.warn();
                } else if ("0".equals(numDepto)) {
                    mbTodero.setMens("Debe seleccionar un 'Departamento'");
                    mbTodero.warn();
                } else if ("0".equals(numCiudad)) {
                    mbTodero.setMens("Debe seleccionar una 'Ciudad'");
                    mbTodero.warn();
                } else if ("0".equals(numRegimen)) {
                    mbTodero.setMens("Debe seleccionar un 'Regimen'");
                    mbTodero.warn();
                } else if ("0".equals(numtipoRegimen)) {
                    mbTodero.setMens("Debe seleccionar un 'Tipo de Regimen'");
                    mbTodero.warn();
                } else {
                    Per = new LogPerito();
                    Per.setNumDocPerito(NumDocPerito);
                    Per.setNombrePerito(NombrePerito);
                    Per.setApellidoPerito(ApellidoPerito);
                    Per.setTelefonoPerito(TelefonoPerito);
                    Per.setCelularPerito(CelularPerito);
                    Per.setDireccionPerito(DireccionPerito);
                    Per.setEmailPerito(EmailPerito);
                    Per.setNumCuentaPerito(NumCuentaPerito);
                    Per.setCiudad(Integer.parseInt(numCiudad));
                    Per.setEstadoPerito(1);
                    Per.setTipDocuPerito(TipDocuPerito);
                    Per.setTipRegimen(Integer.parseInt(this.numtipoRegimen));
                    Per.InserPerito(mBsesion.codigoMiSesion());
                    mbTodero.setMens("El registro se ha realizado correctamente");
                    mbTodero.info();
                    this.ListPerit.clear();
                    mbTodero.resetTable("InfPerito:PeritosTable");
                    this.ListPerit = Per.ConsulAllPerito(0);
                    clearComponents();
                }
            } else if (tipo == 2) {
                Per = new LogPerito();
                if (TipDocuPerito <= 0) {
                    mbTodero.setMens("Debe llenar el campo 'Tipo de documento'");
                    mbTodero.warn();
                } else if ("".equals(NumDocPerito)) {
                    mbTodero.setMens("Debe llenar el campo 'N° de documento'");
                    mbTodero.warn();
                } else if (Per.ConsulPeritoVerif(2, CodPerito, NumDocPerito) == true) {
                    mbTodero.setMens("Ya existe un perito con este Numero de Documento");
                    mbTodero.warn();
                } else if ("".equals(NombrePerito)) {
                    mbTodero.setMens("Debe llenar el campo 'Nombres' ");
                    mbTodero.warn();
                } else if ("".equals(ApellidoPerito)) {
                    mbTodero.setMens("Debe llenar el campo 'Apellidos'");
                    mbTodero.warn();
                } else if ("".equals(TelefonoPerito)) {
                    mbTodero.setMens("Debe llenar el campo 'Telefono'");
                    mbTodero.warn();
                } else if ("".equals(CelularPerito)) {
                    mbTodero.setMens("Debe llenar el campo 'Celular'");
                    mbTodero.warn();
                } else if ("".equals(DireccionPerito)) {
                    mbTodero.setMens("Debe llenar el campo 'Direccion' ");
                    mbTodero.warn();
                } else if ("".equals(EmailPerito)) {
                    mbTodero.setMens("Debe llenar el campo 'Email'");
                    mbTodero.warn();
                } else if ("".equals(NumCuentaPerito)) {
                    mbTodero.setMens("Debe llenar el campo 'Numero de cuenta'");
                    mbTodero.warn();
                } else if (Per.ConsulPeritoVerifcuenta(2, CodPerito, NumCuentaPerito) == true) {
                    mbTodero.setMens("Ya existe un perito con este Numero de Cuenta");
                    mbTodero.warn();
                } else if ("0".equals(numDepto)) {
                    mbTodero.setMens("Debe seleccionar un Departamento");
                    mbTodero.warn();
                } else if ("0".equals(numCiudad)) {
                    mbTodero.setMens("Debe seleccionar una Ciudad");
                    mbTodero.warn();
                } else if ("0".equals(numRegimen)) {
                    mbTodero.setMens("Debe seleccionar un Regimen para el Perito");
                    mbTodero.warn();
                } else if ("0".equals(numtipoRegimen)) {
                    mbTodero.setMens("Debe seleccionar un Tipo de Regimen para el Perito");
                    mbTodero.warn();
                } else {
                    Per = new LogPerito();
                    Per.setNumDocPerito(NumDocPerito);
                    Per.setNombrePerito(NombrePerito);
                    Per.setApellidoPerito(ApellidoPerito);
                    Per.setTelefonoPerito(TelefonoPerito);
                    Per.setCelularPerito(CelularPerito);
                    Per.setDireccionPerito(DireccionPerito);
                    Per.setEmailPerito(EmailPerito);
                    Per.setNumCuentaPerito(NumCuentaPerito);
                    Per.setCiudad(Integer.parseInt(numCiudad));
                    Per.setEstadoPerito(EstadoPerito);
                    Per.setTipDocuPerito(TipDocuPerito);
                    Per.setTipRegimen(Integer.parseInt(this.numtipoRegimen));
                    Per.ActualizarPerito(CodPerito, mBsesion.codigoMiSesion());//falta variable de secion
                    mbTodero.setMens("Actualización realizada");
                    mbTodero.info();
                    this.ListPerit.clear();
                    this.ListPerit = Per.ConsulAllPerito(0);
                    mbTodero.resetTable("InfPerito:PeritosTable");
                    clearComponents();
                }
            }
        } catch (NumberFormatException e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".inserPerito()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo para limpiar los componentes que se utilizan para crear, consultar
     * y modificar la información de los avaluadores
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     *
     * @since 01-11-2014
     */
    public void clearComponents() {
        try {
            NumDocPerito = "";
            NombrePerito = "";
            ApellidoPerito = "";
            TelefonoPerito = "";
            CelularPerito = "";
            DireccionPerito = "";
            EmailPerito = "";
            NumCuentaPerito = "";
            numDepto = "0";
            numCiudad = "0";
            EstadoPerito = 0;
            TipDocuPerito = 0;
            numRegimen = "0";
            numtipoRegimen = "0";
            this.estadoActualizar = false;
            this.estadoRegistro = true;
            this.Per = null;
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".clearComponents()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo para abrir un dialog con una tabla que contiene los registros de
     * todos los avaluadores existentes en el form de radicacion
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Stiven Rodriguez
     * @since 01-11-2014
     */
    public void abrirTodosPeritosRadic() {
        try {
            mbTodero.resetTable("FormRadicacion:TablaAvaluadoresSeleccion");
            ListPeritAvaluadoresSeleccionadosTabla = new ArrayList<>();
            RequestContext.getCurrentInstance().execute("PF('DlgConsultaPeritosSelec').show()");
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".abrirTodosPeritosRadic()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo para validar la seleccion de los avaluadores en el form de
     * radicacion
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Stiven Rodriguez
     * @since 01-11-2014
     */
    public void validarSeleccion() {
        try {
            if (ListPeritAvaluadoresSeleccionadosTabla.size() <= 0 || ListPeritAvaluadoresSeleccionadosTabla == null) {
                mbTodero.setMens("Debe seleccionar por lo menos un perito");
                mbTodero.warn();
            } else if (ListPeritAvaluadoresSeleccionadosTabla.size() > 5) {
                mbTodero.setMens("El numero de peritos maximos a seleccionar son 5");
                mbTodero.warn();
            } else {
                ListPeritAvaluadoresSeleccionadosForm = ListPeritAvaluadoresSeleccionadosTabla;
                RequestContext.getCurrentInstance().execute("PF('DlgConsultaPeritosSelec').hide()");
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".validarSeleccion()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo para validar la seleccion de avaluadores cuando se realize un
     * cambio de los mismos, en el form de radicación
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Stiven Rodriguez
     * @since 01-11-2014
     */
    public void validarCambioAvaluador() {
        try {
            if (ListPeritAvaluadoresSeleccionadosTabla.size() <= 0 || ListPeritAvaluadoresSeleccionadosTabla == null) {
                mbTodero.setMens("Debe seleccionar por lo menos un avaluador");
                mbTodero.warn();
            } else if (ListPeritAvaluadoresSeleccionadosTabla.size() > 5) {
                mbTodero.setMens("El numero de avaluadores maximos a seleccionar son 5");
                mbTodero.warn();
            } else {
                RequestContext.getCurrentInstance().execute("PF('DialogCambioAvaluador').show()");
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".validarCambioAvaluador()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo para confirmar y aceptar que el avaluador sea cambiado en el form
     * de radicación
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Stiven Rodriguez
     * @since 01-11-2014
     */
    public void aceptarCambioAvaluador() {
        try {
            ListPeritAvaluadoresSeleccionadosForm = ListPeritAvaluadoresSeleccionadosTabla;
            RequestContext.getCurrentInstance().update("InfoPeritos");

            estadoCambioAvaluador = true;

            estadoRadioAgregarTarifaPer1 = false;
            estadoTarifasPactPer1 = false;
            opcionRadioTarifasPactadosPer1 = "";
            tipoTarifaPeri1 = "";
            valorTarifaPer1 = "";

            estadoRadioAgregarTarifaPer2 = false;
            estadoTarifasPactPer2 = false;
            opcionRadioTarifasPactadosPer2 = "";
            tipoTarifaPeri2 = "";
            valorTarifaPer2 = "";

            estadoRadioAgregarTarifaPer3 = false;
            estadoTarifasPactPer3 = false;
            opcionRadioTarifasPactadosPer3 = "";
            tipoTarifaPeri3 = "";
            valorTarifaPer3 = "";

            estadoRadioAgregarTarifaPer4 = false;
            estadoTarifasPactPer4 = false;
            opcionRadioTarifasPactadosPer4 = "";
            tipoTarifaPeri4 = "";
            valorTarifaPer4 = "";

            estadoRadioAgregarTarifaPer5 = false;
            estadoTarifasPactPer5 = false;
            opcionRadioTarifasPactadosPer5 = "";
            tipoTarifaPeri5 = "";
            valorTarifaPer5 = "";
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".aceptarCambioAvaluador()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo para remplazar lista cuando no se no acepta que un avaluador sea
     * remplazado en el form de radicacion
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Stiven Rodriguez
     * @since 01-11-2014
     */
    public void noaceptarCambioAvaluador() {
        try {
            ListPeritAvaluadoresSeleccionadosForm = ListPeritAvaluo;
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".noaceptarCambioAvaluador()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo para eliminar unb avaluador dentro del proceso de radicacion, en
     * el form de radicacion
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Stiven Rodriguez
     * @param num_perito int que contiene el numero del avaluador que se
     * encuentre en una de las 5 posiciones disponibles
     *
     * @since 01-11-2014
     */
    public void elimirarPeritosRadicacion(int num_perito) {
        try {
            if (num_perito == 1) {
                ListPeritAvaluadoresSeleccionadosForm.remove(0);
                estadoTarifasPactPer1 = false;
                opcionRadioTarifasPactadosPer1 = "";
                tipoTarifaPeri1 = "";
                valorTarifaPer1 = "";
            }
            if (num_perito == 2) {
                ListPeritAvaluadoresSeleccionadosForm.remove(1);
                estadoTarifasPactPer2 = false;
                opcionRadioTarifasPactadosPer2 = "";
                tipoTarifaPeri2 = "";
                valorTarifaPer2 = "";
            }
            if (num_perito == 3) {
                ListPeritAvaluadoresSeleccionadosForm.remove(2);
                estadoTarifasPactPer3 = false;
                opcionRadioTarifasPactadosPer3 = "";
                tipoTarifaPeri3 = "";
                valorTarifaPer3 = "";
            }
            if (num_perito == 4) {
                ListPeritAvaluadoresSeleccionadosForm.remove(3);
                estadoTarifasPactPer4 = false;
                opcionRadioTarifasPactadosPer4 = "";
                tipoTarifaPeri4 = "";
                valorTarifaPer4 = "";
            }
            if (num_perito == 5) {
                ListPeritAvaluadoresSeleccionadosForm.remove(4);
                estadoTarifasPactPer5 = false;
                opcionRadioTarifasPactadosPer5 = "";
                tipoTarifaPeri5 = "";
                valorTarifaPer5 = "";
            }

        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".elimirarPeritosRadicacion()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo para cargar las tarifas pactadas a los avaluadores si se desea
     * aplicar Tipo_Documento
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Stiven Rodriguez
     * @param num_perito int que contiene el numero del avaluador que se
     * encuentre en una de las 5 posiciones disponibles
     * @since 01-11-2014
     */
    public void agregarTarifasPactadas(int num_perito) {
        try {
            this.num_perito = num_perito;

            if (num_perito == 1) {
                switch (opcionRadioTarifasPactadosPer1) {
                    case "Si_Perito1":
                        RequestContext.getCurrentInstance().execute("PF('DlgSelectTarifaPeritos').show()");
                        tipoTarifaPerito = tipoTarifaPeri1;
                        valorTarifaPerito = valorTarifaPer1;
                        break;
                    case "No_Perito1":
                        estadoTarifasPactPer1 = false;
                        tipoTarifaPeri1 = "";
                        valorTarifaPer1 = "";
                        tipoTarifaPerito = "";
                        valorTarifaPerito = "";
                        RequestContext.getCurrentInstance().execute("PF('DlgSelectTarifaPeritos').hide()");
                        break;
                }
            }

            if (num_perito == 2) {
                switch (opcionRadioTarifasPactadosPer2) {
                    case "Si_Perito2":
                        tipoTarifaPerito = tipoTarifaPeri2;
                        valorTarifaPerito = valorTarifaPer2;
                        RequestContext.getCurrentInstance().execute("PF('DlgSelectTarifaPeritos').show()");
                        break;
                    case "No_Perito2":
                        estadoTarifasPactPer2 = false;
                        tipoTarifaPeri2 = "";
                        valorTarifaPer2 = "";
                        tipoTarifaPerito = "";
                        valorTarifaPerito = "";
                        RequestContext.getCurrentInstance().execute("PF('DlgSelectTarifaPeritos').hide()");
                        break;
                }
            }

            if (num_perito == 3) {
                switch (opcionRadioTarifasPactadosPer3) {
                    case "Si_Perito3":
                        tipoTarifaPerito = tipoTarifaPeri3;
                        valorTarifaPerito = valorTarifaPer3;
                        RequestContext.getCurrentInstance().execute("PF('DlgSelectTarifaPeritos').show()");
                        break;
                    case "No_Perito3":
                        estadoTarifasPactPer3 = false;
                        tipoTarifaPeri3 = "";
                        valorTarifaPer3 = "";
                        tipoTarifaPerito = "";
                        valorTarifaPerito = "";
                        RequestContext.getCurrentInstance().execute("PF('DlgSelectTarifaPeritos').hide()");
                        break;
                }
            }

            if (num_perito == 4) {
                switch (opcionRadioTarifasPactadosPer4) {
                    case "Si_Perito4":
                        RequestContext.getCurrentInstance().execute("PF('DlgSelectTarifaPeritos').show()");
                        tipoTarifaPerito = tipoTarifaPeri4;
                        valorTarifaPerito = valorTarifaPer4;
                        break;
                    case "No_Perito4":
                        estadoTarifasPactPer4 = false;
                        tipoTarifaPeri4 = "";
                        valorTarifaPer4 = "";
                        tipoTarifaPerito = "";
                        valorTarifaPerito = "";
                        RequestContext.getCurrentInstance().execute("PF('DlgSelectTarifaPeritos').hide()");
                        break;
                }
            }

            if (num_perito == 5) {
                switch (opcionRadioTarifasPactadosPer5) {
                    case "Si_Perito5":
                        RequestContext.getCurrentInstance().execute("PF('DlgSelectTarifaPeritos').show()");
                        tipoTarifaPerito = tipoTarifaPeri5;
                        valorTarifaPerito = valorTarifaPer5;
                        break;
                    case "No_Perito5":
                        estadoTarifasPactPer5 = false;
                        tipoTarifaPeri5 = "";
                        valorTarifaPer5 = "";
                        tipoTarifaPerito = "";
                        valorTarifaPerito = "";
                        RequestContext.getCurrentInstance().execute("PF('DlgSelectTarifaPeritos').hide()");
                        break;
                }
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".agregarTarifasPactadas()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo para cargar los avaluadores que pertenezcan a una radicación a
     * avaluo
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Stiven Rodriguez
     * @param cod_Avaluo int que contiene el numero de avaluo sobre el cual se
     * va a consultar la informacion del avaluador(es) que corresponda a el
     * avaluo
     * @since 01-11-2014
     */
    public void cargainfoRadicCarta(int cod_Avaluo) {
        try {
            Per = new LogPerito();
            ListPeritRadGenerar = Per.ConsulPeritoRadic(cod_Avaluo);
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".cargainfoRadicCarta()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo agregar los honorarios pactados a la informacion que se muestra en
     * el form de radicación
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Stiven Rodriguez
     * @since 01-11-2014
     */
    public void agregarTarifasPeritos() {
        try {
            if (("".equals(tipoTarifaPerito) || tipoTarifaPerito == null) && ("".equals(valorTarifaPerito) || valorTarifaPerito == null)) {
                mbTodero.setMens("Debe llenar los campos para el tipo de tarifa");
                mbTodero.warn();
            } else if (("".equals(tipoTarifaPerito) || tipoTarifaPerito == null)) {
                mbTodero.setMens("Debe llenar el campo Tipo de tarifa");
                mbTodero.warn();
            } else if (("".equals(valorTarifaPerito) || valorTarifaPerito == null)) {
                mbTodero.setMens("Debe llenar el campo Valor de tarifa");
                mbTodero.warn();
            } else {
                if (num_perito == 1) {
                    estadoTarifasPactPer1 = true;
                    tipoTarifaPeri1 = tipoTarifaPerito;
                    valorTarifaPer1 = valorTarifaPerito;
                }
                if (num_perito == 2) {
                    estadoTarifasPactPer2 = true;
                    tipoTarifaPeri2 = tipoTarifaPerito;
                    valorTarifaPer2 = valorTarifaPerito;
                }
                if (num_perito == 3) {
                    estadoTarifasPactPer3 = true;
                    tipoTarifaPeri3 = tipoTarifaPerito;
                    valorTarifaPer3 = valorTarifaPerito;
                }
                if (num_perito == 4) {
                    estadoTarifasPactPer4 = true;
                    tipoTarifaPeri4 = tipoTarifaPerito;
                    valorTarifaPer4 = valorTarifaPerito;
                }
                if (num_perito == 5) {
                    estadoTarifasPactPer5 = true;
                    tipoTarifaPeri5 = tipoTarifaPerito;
                    valorTarifaPer5 = valorTarifaPerito;
                }
                tipoTarifaPerito = "";
                valorTarifaPerito = "";
                RequestContext.getCurrentInstance().execute("PF('DlgSelectTarifaPeritos').hide()");
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".agregarTarifasPeritos()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo para consultar y mostrar la informacion de los avaluadores que
     * sean asignados a un avaluo en especifico
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Stiven Rodriguez
     * @since 01-11-2014
     */
    public void CargarInfoPeritosRadic() {
        try {
            Per = new LogPerito();
            ListPeritAvaluadoresSeleccionadosForm = Per.ConsulPeritoRadic(cod_avaluo);
            ListPeritAvaluo = ListPeritAvaluadoresSeleccionadosForm;
            for (int i = 0; i <= ListPeritAvaluadoresSeleccionadosForm.size() - 1; i++) {
                if ((ListPeritAvaluadoresSeleccionadosForm.get(i).getTipoTarifa() == null || "".equals(ListPeritAvaluadoresSeleccionadosForm.get(i).getTipoTarifa()))
                        && (ListPeritAvaluadoresSeleccionadosForm.get(i).getValorPactado() == null || "".equals(ListPeritAvaluadoresSeleccionadosForm.get(i).getValorPactado()))) {
                } else {
                    if (i == 0) {
                        estadoTarifasPactPer1 = true;
                        opcionRadioTarifasPactadosPer1 = "Si_Perito1";
                        estadoRadioAgregarTarifaPer1 = true;
                        tipoTarifaPeri1 = ListPeritAvaluadoresSeleccionadosForm.get(i).getTipoTarifa();
                        valorTarifaPer1 = ListPeritAvaluadoresSeleccionadosForm.get(i).getValorPactado();
                    } else if (i == 1) {
                        tipoTarifaPeri2 = ListPeritAvaluadoresSeleccionadosForm.get(i).getTipoTarifa();
                        estadoTarifasPactPer2 = true;
                        opcionRadioTarifasPactadosPer2 = "Si_Perito2";
                        estadoRadioAgregarTarifaPer2 = true;
                        valorTarifaPer2 = ListPeritAvaluadoresSeleccionadosForm.get(i).getValorPactado();
                    } else if (i == 2) {
                        tipoTarifaPeri3 = ListPeritAvaluadoresSeleccionadosForm.get(i).getTipoTarifa();
                        estadoTarifasPactPer3 = true;
                        estadoRadioAgregarTarifaPer3 = true;
                        opcionRadioTarifasPactadosPer3 = "Si_Perito3";
                        valorTarifaPer3 = ListPeritAvaluadoresSeleccionadosForm.get(i).getValorPactado();
                    } else if (i == 3) {
                        estadoTarifasPactPer4 = true;
                        estadoRadioAgregarTarifaPer4 = true;
                        opcionRadioTarifasPactadosPer4 = "Si_Perito4";
                        tipoTarifaPeri4 = ListPeritAvaluadoresSeleccionadosForm.get(i).getTipoTarifa();
                        valorTarifaPer4 = ListPeritAvaluadoresSeleccionadosForm.get(i).getValorPactado();
                    } else if (i == 4) {
                        estadoTarifasPactPer5 = true;
                        opcionRadioTarifasPactadosPer5 = "Si_Perito5";
                        estadoRadioAgregarTarifaPer5 = true;
                        tipoTarifaPeri5 = ListPeritAvaluadoresSeleccionadosForm.get(i).getTipoTarifa();
                        valorTarifaPer5 = ListPeritAvaluadoresSeleccionadosForm.get(i).getValorPactado();
                    }

                }
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".CargarInfoPeritosRadic()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Método para ordenar la lista de números de los avaluadores ingresados en
     * el form de radicacion
     *
     * @author Maira Alejandra Carvajal
     *
     * @author Jhojan Stiven Rodriguez
     * @since 01-11-2014
     */
    private void ordenaLista() {
        try {
            boolean movimiento = true;
            //Contador que nos indica cuantas rondas comparando parejas llevamos en el bucle
            int contRondas = 0;
            //Mientras que haya movimiento, comprobaremos las posiciones
            int aux;

            while (movimiento) {
                /* Iniciamos el boleano como falso, y si cambia durante el bucle, es que ha habido un movimiento */
                movimiento = false;
                /*comenzamos el bucle en 1, y comparamos con el anterior para no salirnos de los lÃ­mites
                 de la array */
                for (int i = 1; i <= ListPeritAvaluadoresSeleccionadosForm.size() - 1; i++) {
                    /* Si el número de la derecha es menor que el de la izquierda, los intercambia */

                }
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".ordenaLista()' causado por: " + e.getMessage());
            mbTodero.error();
        }
        //Variable que nos permite saber si ha habido movimiento durante la ronda
        //Si en una ronda no hay movimiento, el programa sale, ya que ya estÃ¡ la lista ordenada

    }

    /**
     * Metodo para cargar la informacion de un avaluador seleccionado en el form
     * de gestión de avaluadores de administración
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Stiven Rodriguez
     * @since 01-11-2014
     */
    public void cargaInfo() {
        try {
            if (this.Per == null) {
                mbTodero.setMens("Debe seleccionar un avaluador");
                mbTodero.warn();
            } else {
                RequestContext.getCurrentInstance().execute("PF('ConsultaPerito').hide()");
                CodPerito = Per.getCodPerito();
                TipDocuPerito = Per.getTipDocuPerito();
                NumDocPerito = Per.getNumDocPerito();
                NombrePerito = Per.getNombrePerito();
                ApellidoPerito = Per.getApellidoPerito();
                TelefonoPerito = Per.getTelefonoPerito();
                CelularPerito = Per.getCelularPerito();
                DireccionPerito = Per.getDireccionPerito();
                EmailPerito = Per.getEmailPerito();
                NumCuentaPerito = Per.getNumDocPerito();

                numDepto = Per.getNumDepto();
                cargCiudad();
                numCiudad = String.valueOf(Per.getCiudad());

                numRegimen = String.valueOf(Per.getNumRegimen());
                getConsulCalifiRegPer();
                numtipoRegimen = String.valueOf(Per.getTipRegimen());

                EstadoPerito = Per.getEstadoPerito();

                this.estadoActualizar = true;
                this.estadoRegistro = false;

                this.Per = null;
                mbTodero.resetTable("InfPerito:PeritosTable");

            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".cargaInfo()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo para consultar y cargar en una tabla la informacion de los
     * avaluadores exixtentes
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Stiven Rodriguez
     * @param opcion int que contiene el tipo de proceso donde se entuentran las
     * tablas a donde se cargara la información
     * @since 01-11-2014
     */
    public void CargaTablaPerito(int opcion) {
        try {
            Per = new LogPerito();
            if (opcion == 2) {
                Per.setOp(2);
                mbTodero.resetTable("FormRadicacion:PeritosTableRadic");

                this.ListPerit.clear();
                this.ListPerit = Per.ConsulAllPerito(0);
                RequestContext.getCurrentInstance().execute("PF('DlgConsultaPeritosSelec').show()");
            } else if (opcion == 1) {
                Per.setOp(1);
                mbTodero.resetTable("InfPerito:PeritosTable");

                this.ListPerit.clear();
                this.ListPerit = Per.ConsulAllPerito(0);
                RequestContext.getCurrentInstance().execute("PF('ConsultaPerito').show()");
            } else if (opcion == 3) {
                Per.setOp(2);
                mbTodero.resetTable("FRMPreRadicados:PeritosTable");

                this.ListPerit = Per.ConsulAllPerito(0);
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".CargaTablaPerito()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo para cargar informacion de los avaluadores en una lista
     * desplegable dentro de un arraylist segun el numero de avaluo relacionado
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Stiven Rodriguez
     * @param CodAva int que contiene el codigo del avaluo sobre el cual se
     * cargara la informacion
     * @return ArrayList con la informacion de los avaluadores
     * @since 01-11-2014
     */
    public ArrayList<SelectItem> getConsultPerito(int CodAva) {
        ArrayList<SelectItem> ArrayPerit = new ArrayList<>();
        try {
            try {
                Iterator<LogPerito> ItePerito;
                ItePerito = Per.ConsulPeritoCombo(CodAva).iterator();
                while (ItePerito.hasNext()) {
                    LogPerito Perit = ItePerito.next();
                    ArrayPerit.add(new SelectItem(Perit.getCodPerito(), Perit.getNombrePerito()));
                }
            } catch (Exception e) {
                mbTodero.setMens("Error " + e.getMessage());
                mbTodero.error();
            }

        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".getConsulEstPer()' causado por: " + e.getMessage());
            mbTodero.error();
        }
        return ArrayPerit;
    }

    /**
     * Metodo para generar un reporte con todos los avaluadores que se
     * encuentren registrados
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Stiven Rodriguez
     * @param nombre String que contiene el nombre del reporte
     * @param titulo String que contiene el titulo del reporte
     * @since 01-11-2014
     */
    public void GenerarReporteEstudio(String nombre, String titulo) {

        try {
            mbArchivos = new BeanArchivos();
            mbTodero.setMens(mbArchivos.GenerarReporte(2, nombre, titulo, null, null));
            mbTodero.info();
        } catch (SQLException e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".GenerarReporteEstudio()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * FIN Metodos de funcionalidad de la clase
     * -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     */
}
