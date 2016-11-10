/* * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogBean;

import Logic.*;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;
import org.primefaces.model.UploadedFile;

@ManagedBean(name = "MBRadicacion")
@ViewScoped
public class BeanRadicacion {

    private String Seleccion;

    private List<LogRadicacion> ListRadicados = null;
    private List<LogRadicacion> ListRadicadosGestor = null;
    private List<LogAdministracion> ListaReglaArcRad = null;

    public ArrayList<SelectItem> getCargEnviarA() {
        return CargEnviarA;
    }

    public void setCargEnviarA(ArrayList<SelectItem> CargEnviarA) {
        this.CargEnviarA = CargEnviarA;
    }

    /**
     * LO QUE PUSE YO
     */
    private ArrayList<SelectItem> CargEnviarA = new ArrayList<>();

    LogRadicacion Radi = new LogRadicacion();
    LogPermiso Perm = new LogPermiso();
    LogPreRadicacion PreRad = new LogPreRadicacion();
    LogCargueArchivos Archi = new LogCargueArchivos();
    LogRadicacion Rad = new LogRadicacion();
    LogEmpleado Emp = new LogEmpleado();
    LogCliente Cli = new LogCliente();
    LogPerito Per = new LogPerito();
    LogUbicacion Ubi = new LogUbicacion();
    LogAvaluo Aval = new LogAvaluo();
    LogEntidad Enti = new LogEntidad();
    LogCargueArchivos CarArc = new LogCargueArchivos();

    int cont = 0;
    private String fecha_actual;
    private String fechaSoli;
    private Date HoraSol;
    private String HoraSolisitud;
    SimpleDateFormat Format = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat FormatFechaYHora = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    SimpleDateFormat FormatHora = new SimpleDateFormat("HH:mm");
    private Date fechaSolicRadic;
    private String numeroHojas = "1";
    private String solicitante;
    private String codEnvioInformacion;
    private String envioInformacion;
    private boolean requierUbicacion;
    private boolean provieneCotizacion;

    /**
     * Bloqueo de cajas y componentes (disabled) (si viene la informacion desde
     * pre-radicacion)*
     */
    private boolean estadoComponentesRadicPreRad;

    /**
     * Producto entidad*
     */
    LogAdministracion Adm = new LogAdministracion();
    private ArrayList<SelectItem> TipProEnt = new ArrayList<>();
    private String codProEnt;
    private String nombreProEnt;

    /**
     * Tipo producto entidad*
     */
    private String codTipProEnt;
    private String nombreTipProEnt;

    private boolean estadoObservacionRadic;
    private String observacionInicio;
    private String observacion;
    private String ObservaRadicGeneral = "";
    private ArrayList<LogPreRadicacion> ListObserPreRadicados;
    private ArrayList<LogRadicacion> ListObserRadicados;
    private String estadoPreRad;
    private String estadoRad;
    private String estadoFormVerInformacion;

    /**
     * Archivos*
     */
    private ArrayList<SelectItem> CargaListaArchivos = new ArrayList<>();
    private List<LogCargueArchivos> CargaListaArchivosSelect = new ArrayList<>();
    private ArrayList<String> CargaListaArchivosSelectttt = new ArrayList<>();
    private List<LogRadicacion> ListRadicaAsignados = null;
    private String opcionesCambioAnalistaRadica;
    private List<LogRadicacion> ListEmpAsignaciones = null;
    private List<LogRadicacion> ListRadicaSeleccAsignados = new ArrayList<>();
    private List<LogAdministracion> ListaArcRegla = new ArrayList<>();
    private List<BeanArchivos> ListaArchivosRadiCorreo = new ArrayList<>();

    private String[] ListaArchivosSelecRadiCorreo;
    private String[] ListaArchivosSelecAvaluo;

    private UploadedFile NombreArchivoAvalAnterior;
    private String codTipArchivo;
    private String nombreTipArchivo;
    private UploadedFile nombreArchivoSubir;

    /**
     * Asociar pre-radicacion*
     */
    private boolean estadoRadiosOpcionesRadicacion = true;
    private String opcionAsosiarPreRadic;
    private boolean estadoPanelGeneral = false;

    /**
     * Estado de panel reasigancion radicacion*
     */
    private boolean EstadoOpcionAsignado = false;
    private boolean EstadoOpcionEmpAsignado = false;
    private String TipoProceso;

    /**
     * Responsable*
     */
    private List<LogRadicacion> CargEmpSeguimiento = null;
    List<LogCliente> CargaListaClientesTempo = new ArrayList<>();

    /**
     * Variables de Capa de Presentacion JSF - Prime Faces*
     */
    private int codEmp;
    private String nombreEmp = "";

    /**
     * cliente temporal*
     */
    private String cod_cli_Temp;

    private int cod_predio_matriculas;
    private ResultSet Dat;
    private ResultSet DatObser;
    private boolean estadoValidacionInfoClienteNo = true;
    private boolean estadoValidacionInfoEntidadNo = true;
    private ResultSet DatDepContacto;
    private ResultSet DatCiudadContacto;
    private final String[] pathCartaPerito = new String[5];
    private String nombre_carta;
    private String TextoClieEntidad;

    /**
     * Datos de envio*
     */
    private boolean enviarDocumentosEmpreYArchAval;
    private String correoAnalista;
    private String extensionAnalista;
    private String fechaCorreo;
    private String direccion;
    private String ubicacion;
    private String analista;
    private String Fecha_Visita;
    private String nombreResponsableSeguimiento;
    private String correoResponsableSeguimiento;
    private String nombreAvaluador;
    private String documentoAvaluador;
    private String telefonoAvaluador;
    private ResultSet DatAnalista;
    //Tatiana 11/04/2016
    private String Barrio;
    private String TipoBien;
    private double newValReasig;
    private int filtroNumSeg;
    private String obsReasig = "";
    /**
     * titulo*
     */
    private boolean estadoTituloRadicacion;

    /**
     * Cambio estado*
     */
    private int codMotivoAnulacion;
    private String ObserCambioEstadoRadic;
    private String ObserCambioEstadoImpedirRadic;
    private String ObserCambioEstadoLiberarImp;
    private boolean todoOkRadicacion;
    private boolean radicacionModificacion;
    private boolean radicacionRegistro;
    private boolean radicacionVerInfo;

    /**
     * Visita realizada y re-asignacion de cita, fecha de carta del avaluador*
     */
    private boolean visitaRealizada;
    private String fechaVistoCitaAsig;
    private String fechaCartaAsig;

    private Date fechaNueva;
    private ResultSet DatInfoAvalAnterior;

    /**
     * Fechas*
     */
    Calendar fecha = new GregorianCalendar();
    Date fecha1 = new Date();

    /**
     * Mostrar informacion (verificar informacion)*
     */
    private ResultSet DatoProdEnt;
    private ResultSet DatoTipoProdEnt;
    private ResultSet DatoDeptoSolic;
    private ResultSet DatoCiudadSolic;

    /**
     * Validaciones*
     */
    private boolean estadoValidacionInfoGeneral;
    private boolean estadoValidacionInfoBien;
    private boolean estadoValidacionInfoCliente = true;
    private boolean estadoValidacionInfoEntidad = true;
    private boolean estadoValidacionInfoPerito;
    private boolean estadoValidacionInfoResponsa;
    private boolean estadoValidacionInfoContacto;
    private boolean estadoValidacionInfoCita;
    private boolean estadoValidacionInfoArchivos;

    /**
     * Contacto*
     */
    private String nombreContacto;
    private String celularContacto;
    private String telefonoContacto;
    private String observacionContacto;
    private String observacionContactoEntidCli;
    private ArrayList<SelectItem> CargCiudadesContac = new ArrayList<>();
    private String codDeptoContacto;
    private String DeptoContacto;
    private String codCiudContacto;
    private String CiudContacto;
    private String opcionRadioContacto;
    private boolean estadoLabelsClienteEnti;
    private ArrayList<LogCliente> CargClientesSelecc = new ArrayList<>();
    LogCliente ClieConta = new LogCliente();

    /**
     * Cita perito*
     */
    private Date fechaCita;
    private String fechaCitaAsig;
    private Date HoraCita;
    private String horaCitaAsig;
    /**
     * CartaPerito*
     */
    private boolean enviarPeritoCarta;
    private boolean enviarClienteCarta;
    private boolean enviarEntidadCarta;

    /**
     * Actividades y recordatorios*
     */
    private String opcionModeloCarta;
    private ArrayList<LogEntidad> CargEntidSelecc = new ArrayList<>();
    LogEntidad EntiConta = new LogEntidad();

    /**
     * Registro*
     */
    private int cod_preRadica;
    private ResultSet Dato;

    private int cod_avaluo;
    private int cod_bien_seguimiento;
    private int cod_seguimiento;
    private int cod_persona;
    private String tipo_Persona;
    private String ValorPerito;
    private long ValorComercAvaluo;
    private String ValComeAva;
    private String UltValComAva;

    private int tarifas_pactadas;
    private String tipo_tarifa_pactadas;
    private double valor_tarifas_pactadas;
    private String observacionRegistro;

    /**
     * Variable para la activaciones de opciones si tiene el rol de Gestor*
     */
    private boolean estadoActivacionGestor = false;

    /**
     * Variable tipo BeanEntidad para traer los atributos y metodos de el
     * ManagedBean BeanEntidad.java
     *
     *
     * @see BeanEntidad.java
     */
    @ManagedProperty("#{MBEntidad}")
    private BeanEntidad mBEntidad;

    public BeanEntidad getmBEntidad() {
        return mBEntidad;
    }

    public void setmBEntidad(BeanEntidad mBEntidad) {
        this.mBEntidad = mBEntidad;
    }

    /**
     * Variable tipo BeanUbicacion para traer los atributos y metodos de el
     * ManagedBean BeanUbicacion.java
     *
     *
     * @see BeanUbicacion.java
     */
    @ManagedProperty("#{MBUbicacion}")
    private BeanUbicacion mBUbicacion;

    public BeanUbicacion getmBUbicacion() {
        return mBUbicacion;
    }

    public void setmBUbicacion(BeanUbicacion mBUbicacion) {
        this.mBUbicacion = mBUbicacion;
    }

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
     * Variable tipo BeanArchivos para traer los atributos y metodos de el
     * ManagedBean BeanArchivos.java
     *
     *
     * @see BeanArchivos.java
     */
    @ManagedProperty("#{MBArchivos}")
    private BeanArchivos mBArchivo;

    public BeanArchivos getmBArchivo() {
        return mBArchivo;
    }

    public void setmBArchivo(BeanArchivos mBArchivo) {
        this.mBArchivo = mBArchivo;
    }

    /**
     * Variable tipo BeanCliente para traer los atributos y metodos de el
     * ManagedBean BeanCliente.java
     *
     *
     * @see BeanCliente.java
     */
    @ManagedProperty("#{MBCliente}")
    private BeanCliente mBCliente;

    public BeanCliente getmBCliente() {
        return mBCliente;
    }

    public void setmBCliente(BeanCliente mBEntidad) {
        this.mBCliente = mBEntidad;
    }

    /**
     * Variable tipo BeanPerito para traer los atributos y metodos de el
     * ManagedBean BeanPerito.java
     *
     *
     * @see BeanPerito.java
     */
    @ManagedProperty("#{MBPerito}")
    private BeanPerito mBPerito;

    public BeanPerito getmBPerito() {
        return mBPerito;
    }

    public void setmBPerito(BeanPerito mBPerito) {
        this.mBPerito = mBPerito;
    }

    /**
     * Variable tipo BeanAvaluo para traer los atributos y metodos de el
     * ManagedBean BeanAvaluo.java
     *
     *
     * @see BeanAvaluo.java
     */
    @ManagedProperty("#{MBAvaluo}")
    private BeanAvaluo mBAvaluo;

    public BeanAvaluo getmBAvaluo() {
        return mBAvaluo;
    }

    public void setmBAvaluo(BeanAvaluo mBAvaluo) {
        this.mBAvaluo = mBAvaluo;
    }

    /**
     * Variable tipo BeanPreRadicacion para traer los atributos y metodos de el
     * ManagedBean BeanPreRadicacion.java
     *
     *
     * @see BeanPreRadicacion.java
     */
    @ManagedProperty("#{MBPreRadicacion}")
    private BeanPreRadicacion mBPreRadicacion;

    public BeanPreRadicacion getmBPreRadicacion() {
        return mBPreRadicacion;
    }

    public void setmBPreRadicacion(BeanPreRadicacion mBPreRadicacion) {
        this.mBPreRadicacion = mBPreRadicacion;
    }

    public String getBarrio() {
        return Barrio;
    }

    public void setBarrio(String Barrio) {
        this.Barrio = Barrio;
    }

    public String getTipoBien() {
        return TipoBien;
    }

    //Tatiana 11/04/2016
    public void setTipoBien(String TipoBien) {
        this.TipoBien = TipoBien;
    }

    public LogCliente getCli() {
        return Cli;
    }

    public String getValComeAva() {
        return ValComeAva;
    }

    public String getUltValComAva() {
        return UltValComAva;
    }

    public void setUltValComAva(String UltValComAva) {
        this.UltValComAva = UltValComAva;
    }

    public void setValComeAva(String ValComeAva) {
        this.ValComeAva = ValComeAva;
    }

    public void setCli(LogCliente Cli) {
        this.Cli = Cli;
    }

    public int getFiltroNumSeg() {
        return filtroNumSeg;
    }

    public void setFiltroNumSeg(int filtroNumSeg) {
        this.filtroNumSeg = filtroNumSeg;
    }

    public boolean isEstadoTituloRadicacion() {
        return estadoTituloRadicacion;
    }

    public void setEstadoTituloRadicacion(boolean estadoTituloRadicacion) {
        this.estadoTituloRadicacion = estadoTituloRadicacion;
    }

    public int getCodMotivoAnulacion() {
        return codMotivoAnulacion;
    }

    public void setCodMotivoAnulacion(int codMotivoAnulacion) {
        this.codMotivoAnulacion = codMotivoAnulacion;
    }

    public String getObserCambioEstadoRadic() {
        return ObserCambioEstadoRadic;
    }

    public void setObserCambioEstadoRadic(String ObserCambioEstadoRadic) {
        this.ObserCambioEstadoRadic = ObserCambioEstadoRadic;
    }

    public String getObserCambioEstadoImpedirRadic() {
        return ObserCambioEstadoImpedirRadic;
    }

    public void setObserCambioEstadoImpedirRadic(String ObserCambioEstadoImpedirRadic) {
        this.ObserCambioEstadoImpedirRadic = ObserCambioEstadoImpedirRadic;
    }

    public String getObserCambioEstadoLiberarImp() {
        return ObserCambioEstadoLiberarImp;
    }

    public void setObserCambioEstadoLiberarImp(String ObserCambioEstadoLiberarImp) {
        this.ObserCambioEstadoLiberarImp = ObserCambioEstadoLiberarImp;
    }

    public boolean isTodoOkRadicacion() {
        return todoOkRadicacion;
    }

    public void setTodoOkRadicacion(boolean todoOkRadicacion) {
        this.todoOkRadicacion = todoOkRadicacion;
    }

    public boolean isRadicacionModificacion() {
        return radicacionModificacion;
    }

    public void setRadicacionModificacion(boolean radicacionModificacion) {
        this.radicacionModificacion = radicacionModificacion;
    }

    public boolean isRadicacionRegistro() {
        return radicacionRegistro;
    }

    public void setRadicacionRegistro(boolean radicacionRegistro) {
        this.radicacionRegistro = radicacionRegistro;
    }

    public boolean isRadicacionVerInfo() {
        return radicacionVerInfo;
    }

    public void setRadicacionVerInfo(boolean radicacionVerInfo) {
        this.radicacionVerInfo = radicacionVerInfo;
    }

    public boolean isVisitaRealizada() {
        return visitaRealizada;
    }

    public void setVisitaRealizada(boolean visitaRealizada) {
        this.visitaRealizada = visitaRealizada;
    }

    public String getFechaVistoCitaAsig() {
        return fechaVistoCitaAsig;
    }

    public void setFechaVistoCitaAsig(String fechaVistoCitaAsig) {
        this.fechaVistoCitaAsig = fechaVistoCitaAsig;
    }

    public String getFechaCartaAsig() {
        return fechaCartaAsig;
    }

    public void setFechaCartaAsig(String fechaCartaAsig) {
        this.fechaCartaAsig = fechaCartaAsig;
    }

    public Date getFechaNueva() {
        return fechaNueva;
    }

    public void setFechaNueva(Date fechaNueva) {
        this.fechaNueva = fechaNueva;
    }

    public String getSeleccion() {
        return Seleccion;
    }

    public void setSeleccion(String Seleccion) {
        this.Seleccion = Seleccion;
    }

    public List<LogRadicacion> getListRadicados() {
        return ListRadicados;
    }

    public void setListRadicados(List<LogRadicacion> ListRadicados) {
        this.ListRadicados = ListRadicados;
    }

    public LogRadicacion getRadi() {
        return Radi;
    }

    public List<LogRadicacion> getListRadicadosGestor() {
        return ListRadicadosGestor;
    }

    public void setListRadicadosGestor(List<LogRadicacion> ListRadicadosGestor) {
        this.ListRadicadosGestor = ListRadicadosGestor;
    }

    public List<LogAdministracion> getListaReglaArcRad() {
        return ListaReglaArcRad;
    }

    public void setListaReglaArcRad(List<LogAdministracion> ListaReglaArcRad) {
        this.ListaReglaArcRad = ListaReglaArcRad;
    }

    public void setRadi(LogRadicacion Radi) {
        this.Radi = Radi;
    }

    public LogPermiso getPerm() {
        return Perm;
    }

    public void setPerm(LogPermiso Perm) {
        this.Perm = Perm;
    }

    public LogPreRadicacion getPreRad() {
        return PreRad;
    }

    public void setPreRad(LogPreRadicacion PreRad) {
        this.PreRad = PreRad;
    }

    public LogCargueArchivos getArchi() {
        return Archi;
    }

    public void setArchi(LogCargueArchivos Archi) {
        this.Archi = Archi;
    }

    public LogRadicacion getRad() {
        return Rad;
    }

    public void setRad(LogRadicacion Rad) {
        this.Rad = Rad;
    }

    public LogEmpleado getEmp() {
        return Emp;
    }

    public void setEmp(LogEmpleado Emp) {
        this.Emp = Emp;
    }

    public LogPerito getPer() {
        return Per;
    }

    public void setPer(LogPerito Per) {
        this.Per = Per;
    }

    public LogUbicacion getUbi() {
        return Ubi;
    }

    public void setUbi(LogUbicacion Ubi) {
        this.Ubi = Ubi;
    }

    public LogAvaluo getAval() {
        return Aval;
    }

    public void setAval(LogAvaluo Aval) {
        this.Aval = Aval;
    }

    public LogEntidad getEnti() {
        return Enti;
    }

    public void setEnti(LogEntidad Enti) {
        this.Enti = Enti;
    }

    public int getCont() {
        return cont;
    }

    public void setCont(int cont) {
        this.cont = cont;
    }

    public String getFecha_actual() {
        return fecha_actual;
    }

    public void setFecha_actual(String fecha_actual) {
        this.fecha_actual = fecha_actual;
    }

    public String getFechaSoli() {
        return fechaSoli;
    }

    public void setFechaSoli(String fechaSoli) {
        this.fechaSoli = fechaSoli;
    }

    public Date getHoraSol() {
        return HoraSol;
    }

    public void setHoraSol(Date HoraSol) {
        this.HoraSol = HoraSol;
    }

    public String getHoraSolisitud() {
        return HoraSolisitud;
    }

    public void setHoraSolisitud(String HoraSolisitud) {
        this.HoraSolisitud = HoraSolisitud;
    }

    public Date getFechaSolicRadic() {
        return fechaSolicRadic;
    }

    public void setFechaSolicRadic(Date fechaSolicRadic) {
        this.fechaSolicRadic = fechaSolicRadic;
    }

    public String getNumeroHojas() {
        return numeroHojas;
    }

    public void setNumeroHojas(String numeroHojas) {
        this.numeroHojas = numeroHojas;
    }

    public String getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(String solicitante) {
        this.solicitante = solicitante;
    }

    public String getCodEnvioInformacion() {
        return codEnvioInformacion;
    }

    public void setCodEnvioInformacion(String codEnvioInformacion) {
        this.codEnvioInformacion = codEnvioInformacion;
    }

    public String getEnvioInformacion() {
        return envioInformacion;
    }

    public void setEnvioInformacion(String envioInformacion) {
        this.envioInformacion = envioInformacion;
    }

    public boolean isRequierUbicacion() {
        return requierUbicacion;
    }

    public void setRequierUbicacion(boolean requierUbicacion) {
        this.requierUbicacion = requierUbicacion;
    }

    public boolean isProvieneCotizacion() {
        return provieneCotizacion;
    }

    public void setProvieneCotizacion(boolean provieneCotizacion) {
        this.provieneCotizacion = provieneCotizacion;
    }

    public boolean isEstadoComponentesRadicPreRad() {
        return estadoComponentesRadicPreRad;
    }

    public void setEstadoComponentesRadicPreRad(boolean estadoComponentesRadicPreRad) {
        this.estadoComponentesRadicPreRad = estadoComponentesRadicPreRad;
    }

    public LogAdministracion getAdm() {
        return Adm;
    }

    public void setAdm(LogAdministracion Adm) {
        this.Adm = Adm;
    }

    public ArrayList<SelectItem> getTipProEnt() {
        return TipProEnt;
    }

    public void setTipProEnt(ArrayList<SelectItem> TipProEnt) {
        this.TipProEnt = TipProEnt;
    }

    public String getCodProEnt() {
        return codProEnt;
    }

    public void setCodProEnt(String codProEnt) {
        this.codProEnt = codProEnt;
    }

    public String getNombreProEnt() {
        return nombreProEnt;
    }

    public void setNombreProEnt(String nombreProEnt) {
        this.nombreProEnt = nombreProEnt;
    }

    public String getCodTipProEnt() {
        return codTipProEnt;
    }

    public void setCodTipProEnt(String codTipProEnt) {
        this.codTipProEnt = codTipProEnt;
    }

    public String getNombreTipProEnt() {
        return nombreTipProEnt;
    }

    public void setNombreTipProEnt(String nombreTipProEnt) {
        this.nombreTipProEnt = nombreTipProEnt;
    }

    public boolean isEstadoObservacionRadic() {
        return estadoObservacionRadic;
    }

    public void setEstadoObservacionRadic(boolean estadoObservacionRadic) {
        this.estadoObservacionRadic = estadoObservacionRadic;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getObservaRadicGeneral() {
        return ObservaRadicGeneral;
    }

    public void setObservaRadicGeneral(String ObservaRadicGeneral) {
        this.ObservaRadicGeneral = ObservaRadicGeneral;
    }

    public String getObservacionInicio() {
        return observacionInicio;
    }

    public void setObservacionInicio(String observacionInicio) {
        this.observacionInicio = observacionInicio;
    }

    public ArrayList<LogPreRadicacion> getListObserPreRadicados() {
        return ListObserPreRadicados;
    }

    public void setListObserPreRadicados(ArrayList<LogPreRadicacion> ListObserPreRadicados) {
        this.ListObserPreRadicados = ListObserPreRadicados;
    }

    public ArrayList<LogRadicacion> getListObserRadicados() {
        return ListObserRadicados;
    }

    public void setListObserRadicados(ArrayList<LogRadicacion> ListObserRadicados) {
        this.ListObserRadicados = ListObserRadicados;
    }

    public String getEstadoFormVerInformacion() {
        return estadoFormVerInformacion;
    }

    public void setEstadoFormVerInformacion(String estadoFormVerInformacion) {
        this.estadoFormVerInformacion = estadoFormVerInformacion;
    }

    public Date getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(Date fechaCita) {
        this.fechaCita = fechaCita;
    }

    public String getFechaCitaAsig() {
        return fechaCitaAsig;
    }

    public void setFechaCitaAsig(String fechaCitaAsig) {
        this.fechaCitaAsig = fechaCitaAsig;
    }

    public Date getHoraCita() {
        return HoraCita;
    }

    public void setHoraCita(Date HoraCita) {
        this.HoraCita = HoraCita;
    }

    public String getHoraCitaAsig() {
        return horaCitaAsig;
    }

    public void setHoraCitaAsig(String horaCitaAsig) {
        this.horaCitaAsig = horaCitaAsig;
    }

    public boolean isEnviarPeritoCarta() {
        return enviarPeritoCarta;
    }

    public void setEnviarPeritoCarta(boolean enviarPeritoCarta) {
        this.enviarPeritoCarta = enviarPeritoCarta;
    }

    public boolean isEnviarClienteCarta() {
        return enviarClienteCarta;
    }

    public void setEnviarClienteCarta(boolean enviarClienteCarta) {
        this.enviarClienteCarta = enviarClienteCarta;
    }

    public boolean isEnviarEntidadCarta() {
        return enviarEntidadCarta;
    }

    public void setEnviarEntidadCarta(boolean enviarEntidadCarta) {
        this.enviarEntidadCarta = enviarEntidadCarta;
    }

    public String getOpcionModeloCarta() {
        return opcionModeloCarta;
    }

    public void setOpcionModeloCarta(String opcionModeloCarta) {
        this.opcionModeloCarta = opcionModeloCarta;
    }

    public String getEstadoPreRad() {
        return estadoPreRad;
    }

    public void setEstadoPreRad(String estadoPreRad) {
        this.estadoPreRad = estadoPreRad;
    }

    public String getEstadoRad() {
        return estadoRad;
    }

    public void setEstadoRad(String estadoRad) {
        this.estadoRad = estadoRad;
    }

    public ArrayList<SelectItem> getCargaListaArchivos() {
        return CargaListaArchivos;
    }

    public void setCargaListaArchivos(ArrayList<SelectItem> CargaListaArchivos) {
        this.CargaListaArchivos = CargaListaArchivos;
    }

    public List<LogCargueArchivos> getCargaListaArchivosSelect() {
        return CargaListaArchivosSelect;
    }

    public void setCargaListaArchivosSelect(List<LogCargueArchivos> CargaListaArchivosSelect) {
        this.CargaListaArchivosSelect = CargaListaArchivosSelect;
    }

    public ArrayList<String> getCargaListaArchivosSelectttt() {
        return CargaListaArchivosSelectttt;
    }

    public void setCargaListaArchivosSelectttt(ArrayList<String> CargaListaArchivosSelectttt) {
        this.CargaListaArchivosSelectttt = CargaListaArchivosSelectttt;
    }

    public List<LogRadicacion> getListRadicaAsignados() {
        return ListRadicaAsignados;
    }

    public void setListRadicaAsignados(List<LogRadicacion> ListRadicaAsignados) {
        this.ListRadicaAsignados = ListRadicaAsignados;
    }

    public String getOpcionesCambioAnalistaRadica() {
        return opcionesCambioAnalistaRadica;
    }

    public void setOpcionesCambioAnalistaRadica(String opcionesCambioAnalistaRadica) {
        this.opcionesCambioAnalistaRadica = opcionesCambioAnalistaRadica;
    }

    public List<LogRadicacion> getListEmpAsignaciones() {
        return ListEmpAsignaciones;
    }

    public void setListEmpAsignaciones(List<LogRadicacion> ListEmpAsignaciones) {
        this.ListEmpAsignaciones = ListEmpAsignaciones;
    }

    public List<LogRadicacion> getListRadicaSeleccAsignados() {
        return ListRadicaSeleccAsignados;
    }

    public void setListRadicaSeleccAsignados(List<LogRadicacion> ListRadicaSeleccAsignados) {
        this.ListRadicaSeleccAsignados = ListRadicaSeleccAsignados;
    }

    public List<LogAdministracion> getListaArcRegla() {
        return ListaArcRegla;
    }

    public void setListaArcRegla(List<LogAdministracion> ListaArcRegla) {
        this.ListaArcRegla = ListaArcRegla;
    }

    public List<BeanArchivos> getListaArchivosRadiCorreo() {
        return ListaArchivosRadiCorreo;
    }

    public void setListaArchivosRadiCorreo(List<BeanArchivos> ListaArchivosRadiCorreo) {
        this.ListaArchivosRadiCorreo = ListaArchivosRadiCorreo;
    }

    public String[] getListaArchivosSelecRadiCorreo() {
        return ListaArchivosSelecRadiCorreo;
    }

    public void setListaArchivosSelecRadiCorreo(String[] ListaArchivosSelecRadiCorreo) {
        this.ListaArchivosSelecRadiCorreo = ListaArchivosSelecRadiCorreo;
    }

    public String[] getListaArchivosSelecAvaluo() {
        return ListaArchivosSelecAvaluo;
    }

    public void setListaArchivosSelecAvaluo(String[] ListaArchivosSelecAvaluo) {
        this.ListaArchivosSelecAvaluo = ListaArchivosSelecAvaluo;
    }

    public UploadedFile getNombreArchivoAvalAnterior() {
        return NombreArchivoAvalAnterior;
    }

    public void setNombreArchivoAvalAnterior(UploadedFile NombreArchivoAvalAnterior) {
        this.NombreArchivoAvalAnterior = NombreArchivoAvalAnterior;
    }

    public String getCodTipArchivo() {
        return codTipArchivo;
    }

    public void setCodTipArchivo(String codTipArchivo) {
        this.codTipArchivo = codTipArchivo;
    }

    public String getNombreTipArchivo() {
        return nombreTipArchivo;
    }

    public double getNewValReasig() {
        return newValReasig;
    }

    public void setNewValReasig(double newValReasig) {
        this.newValReasig = newValReasig;
    }

    public void setNombreTipArchivo(String nombreTipArchivo) {
        this.nombreTipArchivo = nombreTipArchivo;
    }

    public UploadedFile getNombreArchivoSubir() {
        return nombreArchivoSubir;
    }

    public void setNombreArchivoSubir(UploadedFile nombreArchivoSubir) {
        this.nombreArchivoSubir = nombreArchivoSubir;
    }

    public boolean isEstadoRadiosOpcionesRadicacion() {
        return estadoRadiosOpcionesRadicacion;
    }

    public void setEstadoRadiosOpcionesRadicacion(boolean estadoRadiosOpcionesRadicacion) {
        this.estadoRadiosOpcionesRadicacion = estadoRadiosOpcionesRadicacion;
    }

    public String getOpcionAsosiarPreRadic() {
        return opcionAsosiarPreRadic;
    }

    public void setOpcionAsosiarPreRadic(String opcionAsosiarPreRadic) {
        this.opcionAsosiarPreRadic = opcionAsosiarPreRadic;
    }

    public boolean isEstadoPanelGeneral() {
        return estadoPanelGeneral;
    }

    public void setEstadoPanelGeneral(boolean estadoPanelGeneral) {
        this.estadoPanelGeneral = estadoPanelGeneral;
    }

    public boolean isEstadoOpcionAsignado() {
        return EstadoOpcionAsignado;
    }

    public void setEstadoOpcionAsignado(boolean EstadoOpcionAsignado) {
        this.EstadoOpcionAsignado = EstadoOpcionAsignado;
    }

    public boolean isEstadoOpcionEmpAsignado() {
        return EstadoOpcionEmpAsignado;
    }

    public void setEstadoOpcionEmpAsignado(boolean EstadoOpcionEmpAsignado) {
        this.EstadoOpcionEmpAsignado = EstadoOpcionEmpAsignado;
    }

    public String getTipoProceso() {
        return TipoProceso;
    }

    public void setTipoProceso(String TipoProceso) {
        this.TipoProceso = TipoProceso;
    }

    public List<LogRadicacion> getCargEmpSeguimiento() {
        return CargEmpSeguimiento;
    }

    public void setCargEmpSeguimiento(List<LogRadicacion> CargEmpSeguimiento) {
        this.CargEmpSeguimiento = CargEmpSeguimiento;
    }

    public List<LogCliente> getCargaListaClientesTempo() {
        return CargaListaClientesTempo;
    }

    public void setCargaListaClientesTempo(List<LogCliente> CargaListaClientesTempo) {
        this.CargaListaClientesTempo = CargaListaClientesTempo;
    }

    public int getCodEmp() {
        return codEmp;
    }

    public void setCodEmp(int codEmp) {
        this.codEmp = codEmp;
    }

    public String getNombreEmp() {
        return nombreEmp;
    }

    public void setNombreEmp(String nombreEmp) {
        this.nombreEmp = nombreEmp;
    }

    public String getCod_cli_Temp() {
        return cod_cli_Temp;
    }

    public void setCod_cli_Temp(String cod_cli_Temp) {
        this.cod_cli_Temp = cod_cli_Temp;
    }

    public int getCod_predio_matriculas() {
        return cod_predio_matriculas;
    }

    public void setCod_predio_matriculas(int cod_predio_matriculas) {
        this.cod_predio_matriculas = cod_predio_matriculas;
    }

    public boolean isEstadoValidacionInfoGeneral() {
        return estadoValidacionInfoGeneral;
    }

    public void setEstadoValidacionInfoGeneral(boolean estadoValidacionInfoGeneral) {
        this.estadoValidacionInfoGeneral = estadoValidacionInfoGeneral;
    }

    public boolean isEstadoValidacionInfoBien() {
        return estadoValidacionInfoBien;
    }

    public void setEstadoValidacionInfoBien(boolean estadoValidacionInfoBien) {
        this.estadoValidacionInfoBien = estadoValidacionInfoBien;
    }

    public boolean isEstadoValidacionInfoCliente() {
        return estadoValidacionInfoCliente;
    }

    public void setEstadoValidacionInfoCliente(boolean estadoValidacionInfoCliente) {
        this.estadoValidacionInfoCliente = estadoValidacionInfoCliente;
    }

    public boolean isEstadoValidacionInfoEntidad() {
        return estadoValidacionInfoEntidad;
    }

    public void setEstadoValidacionInfoEntidad(boolean estadoValidacionInfoEntidad) {
        this.estadoValidacionInfoEntidad = estadoValidacionInfoEntidad;
    }

    public boolean isEstadoValidacionInfoPerito() {
        return estadoValidacionInfoPerito;
    }

    public void setEstadoValidacionInfoPerito(boolean estadoValidacionInfoPerito) {
        this.estadoValidacionInfoPerito = estadoValidacionInfoPerito;
    }

    public boolean isEstadoValidacionInfoResponsa() {
        return estadoValidacionInfoResponsa;
    }

    public void setEstadoValidacionInfoResponsa(boolean estadoValidacionInfoResponsa) {
        this.estadoValidacionInfoResponsa = estadoValidacionInfoResponsa;
    }

    public String getNombreContacto() {
        return nombreContacto;
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }

    public String getCelularContacto() {
        return celularContacto;
    }

    public void setCelularContacto(String celularContacto) {
        this.celularContacto = celularContacto;
    }

    public String getTelefonoContacto() {
        return telefonoContacto;
    }

    public void setTelefonoContacto(String telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }

    public String getObservacionContacto() {
        return observacionContacto;
    }

    public void setObservacionContacto(String observacionContacto) {
        this.observacionContacto = observacionContacto;
    }

    public String getObservacionContactoEntidCli() {
        return observacionContactoEntidCli;
    }

    public void setObservacionContactoEntidCli(String observacionContactoEntidCli) {
        this.observacionContactoEntidCli = observacionContactoEntidCli;
    }

    public ArrayList<SelectItem> getCargCiudadesContac() {
        return CargCiudadesContac;
    }

    public void setCargCiudadesContac(ArrayList<SelectItem> CargCiudadesContac) {
        this.CargCiudadesContac = CargCiudadesContac;
    }

    public String getCodDeptoContacto() {
        return codDeptoContacto;
    }

    public void setCodDeptoContacto(String codDeptoContacto) {
        this.codDeptoContacto = codDeptoContacto;
    }

    public String getDeptoContacto() {
        return DeptoContacto;
    }

    public void setDeptoContacto(String DeptoContacto) {
        this.DeptoContacto = DeptoContacto;
    }

    public String getCiudContacto() {
        return CiudContacto;
    }

    public void setCiudContacto(String CiudContacto) {
        this.CiudContacto = CiudContacto;
    }

    public String getCodCiudContacto() {
        return codCiudContacto;
    }

    public void setCodCiudContacto(String codCiudContacto) {
        this.codCiudContacto = codCiudContacto;
    }

    public String getOpcionRadioContacto() {
        return opcionRadioContacto;
    }

    public void setOpcionRadioContacto(String opcionRadioContacto) {
        this.opcionRadioContacto = opcionRadioContacto;
    }

    public boolean isEstadoLabelsClienteEnti() {
        return estadoLabelsClienteEnti;
    }

    public void setEstadoLabelsClienteEnti(boolean estadoLabelsClienteEnti) {
        this.estadoLabelsClienteEnti = estadoLabelsClienteEnti;
    }

    public ArrayList<LogCliente> getCargClientesSelecc() {
        return CargClientesSelecc;
    }

    public void setCargClientesSelecc(ArrayList<LogCliente> CargClientesSelecc) {
        this.CargClientesSelecc = CargClientesSelecc;
    }

    public LogCliente getClieConta() {
        return ClieConta;
    }

    public void setClieConta(LogCliente ClieConta) {
        this.ClieConta = ClieConta;
    }

    public ArrayList<LogEntidad> getCargEntidSelecc() {
        return CargEntidSelecc;
    }

    public void setCargEntidSelecc(ArrayList<LogEntidad> CargEntidSelecc) {
        this.CargEntidSelecc = CargEntidSelecc;
    }

    public LogEntidad getEntiConta() {
        return EntiConta;
    }

    public void setEntiConta(LogEntidad EntiConta) {
        this.EntiConta = EntiConta;
    }

    public int getCod_preRadica() {
        return cod_preRadica;
    }

    public void setCod_preRadica(int cod_preRadica) {
        this.cod_preRadica = cod_preRadica;
    }

    public int getCod_avaluo() {
        return cod_avaluo;
    }

    public int getCod_bien_seguimiento() {
        return cod_bien_seguimiento;
    }

    public void setCod_bien_seguimiento(int cod_bien_seguimiento) {
        this.cod_bien_seguimiento = cod_bien_seguimiento;
    }

    public void setCod_avaluo(int cod_avaluo) {
        this.cod_avaluo = cod_avaluo;
    }

    public int getCod_seguimiento() {
        return cod_seguimiento;
    }

    public void setCod_seguimiento(int cod_seguimiento) {
        this.cod_seguimiento = cod_seguimiento;
    }

    public int getCod_persona() {
        return cod_persona;
    }

    public void setCod_persona(int cod_persona) {
        this.cod_persona = cod_persona;
    }

    public String getTipo_Persona() {
        return tipo_Persona;
    }

    public void setTipo_Persona(String tipo_Persona) {
        this.tipo_Persona = tipo_Persona;
    }

    public String getValorPerito() {
        return ValorPerito;
    }

    public void setValorPerito(String ValorPerito) {
        this.ValorPerito = ValorPerito;
    }

    public long getValorComercAvaluo() {
        return ValorComercAvaluo;
    }

    public void setValorComercAvaluo(long ValorComercAvaluo) {
        this.ValorComercAvaluo = ValorComercAvaluo;
    }

    public int getTarifas_pactadas() {
        return tarifas_pactadas;
    }

    public void setTarifas_pactadas(int tarifas_pactadas) {
        this.tarifas_pactadas = tarifas_pactadas;
    }

    public String getTipo_tarifa_pactadas() {
        return tipo_tarifa_pactadas;
    }

    public void setTipo_tarifa_pactadas(String tipo_tarifa_pactadas) {
        this.tipo_tarifa_pactadas = tipo_tarifa_pactadas;
    }

    public double getValor_tarifas_pactadas() {
        return valor_tarifas_pactadas;
    }

    public void setValor_tarifas_pactadas(double valor_tarifas_pactadas) {
        this.valor_tarifas_pactadas = valor_tarifas_pactadas;
    }

    public String getObservacionRegistro() {
        return observacionRegistro;
    }

    public void setObservacionRegistro(String observacionRegistro) {
        this.observacionRegistro = observacionRegistro;
    }

    public boolean isEstadoActivacionGestor() {
        return estadoActivacionGestor;
    }

    public void setEstadoActivacionGestor(boolean estadoActivacionGestor) {
        this.estadoActivacionGestor = estadoActivacionGestor;
    }

    public String getTextoClieEntidad() {
        return TextoClieEntidad;
    }

    public void setTextoClieEntidad(String TextoClieEntidad) {
        this.TextoClieEntidad = TextoClieEntidad;
    }

    public boolean isEnviarDocumentosEmpreYArchAval() {
        return enviarDocumentosEmpreYArchAval;
    }

    public void setEnviarDocumentosEmpreYArchAval(boolean enviarDocumentosEmpreYArchAval) {
        this.enviarDocumentosEmpreYArchAval = enviarDocumentosEmpreYArchAval;
    }

    public String getCorreoAnalista() {
        return correoAnalista;
    }

    public void setCorreoAnalista(String correoAnalista) {
        this.correoAnalista = correoAnalista;
    }

    public String getExtensionAnalista() {
        return extensionAnalista;
    }

    public void setExtensionAnalista(String extensionAnalista) {
        this.extensionAnalista = extensionAnalista;
    }

    public String getFechaCorreo() {
        return fechaCorreo;
    }

    public void setFechaCorreo(String fechaCorreo) {
        this.fechaCorreo = fechaCorreo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public String getObsReasig() {
        return obsReasig;
    }

    public void setObsReasig(String obsReasig) {
        this.obsReasig = obsReasig;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getAnalista() {
        return analista;
    }

    public void setAnalista(String analista) {
        this.analista = analista;
    }

    public String getFecha_Visita() {
        return Fecha_Visita;
    }

    public void setFecha_Visita(String Fecha_Visita) {
        this.Fecha_Visita = Fecha_Visita;
    }

    public String getNombreResponsableSeguimiento() {
        return nombreResponsableSeguimiento;
    }

    public void setNombreResponsableSeguimiento(String nombreResponsableSeguimiento) {
        this.nombreResponsableSeguimiento = nombreResponsableSeguimiento;
    }

    public String getCorreoResponsableSeguimiento() {
        return correoResponsableSeguimiento;
    }

    public void setCorreoResponsableSeguimiento(String correoResponsableSeguimiento) {
        this.correoResponsableSeguimiento = correoResponsableSeguimiento;
    }

    public String getNombreAvaluador() {
        return nombreAvaluador;
    }

    public void setNombreAvaluador(String nombreAvaluador) {
        this.nombreAvaluador = nombreAvaluador;
    }

    public String getDocumentoAvaluador() {
        return documentoAvaluador;
    }

    public void setDocumentoAvaluador(String documentoAvaluador) {
        this.documentoAvaluador = documentoAvaluador;
    }

    public String getTelefonoAvaluador() {
        return telefonoAvaluador;
    }

    public void setTelefonoAvaluador(String telefonoAvaluador) {
        this.telefonoAvaluador = telefonoAvaluador;
    }

    public BeanRadicacion() {

    }

    @PostConstruct
    private void init() {
        try {
            fecha1 = fecha.getTime();
            this.fecha_actual = Format.format(fecha1);
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".init()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    public void onTipProEnt() {
        try {
            if (Integer.parseInt(codProEnt) != 0) {
                this.TipProEnt.clear();
                getConsulTipProEnt();
            } else {
                System.out.println("No funciona");
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".onTipProEnt()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    public ArrayList<SelectItem> getConsulTipProEnt() {
        try {
            Iterator<LogAdministracion> Ite;
            Ite = Adm.getTipoProducEntidad(Integer.parseInt(codProEnt), 1).iterator();
            while (Ite.hasNext()) {
                LogAdministracion MBAdm = Ite.next();
                this.TipProEnt.add(new SelectItem(MBAdm.getCodTipProEnt(), MBAdm.getNombre_TipProEnt()));
            }
        } catch (NumberFormatException e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".getConsulTipProEnt()' causado por: " + e.getMessage());
            mbTodero.error();
        }
        return this.TipProEnt;
    }

    public void consultaNombreArchivo() {
        try {
            this.CargaListaArchivosSelectttt.add(nombreArchivoSubir.getFileName());
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".consultaNombreArchivo()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    public void verifiFec() {
        try {
            Calendar FecActual = Calendar.getInstance();
            Date infor = FecActual.getTime();
            //String fechaActua = Format.format(infor);         
            if (!fechaSolicRadic.before(infor)) {
                mbTodero.setMens("La fecha de solicitud no puede ser mayor a la fecha actual");
                mbTodero.warn();
            } else {
                fechaSoli = Format.format(fechaSolicRadic);
                mbTodero.setMens("OK " + fechaSoli);
                mbTodero.info();
            }

        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".verifiFec()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que abre los datos de la consulta de las pre-radicaciones
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @since 01-11-2014
     */
    public void mostrarInfoPreRadicacion() {
        try {
            switch (this.opcionAsosiarPreRadic) {
                case "Si_Asociar":
                    PreRad = new LogPreRadicacion();
                    mbTodero.resetTable("FormRadicacion:PreRadicadosTable");

                    mBPreRadicacion.getListPreRadicados().clear();
                    mBPreRadicacion.setListPreRadicados(PreRad.ConsulPendientesRadicar(mBsesion.codigoMiSesion()));
                    estadoPanelGeneral = false;
                    RequestContext.getCurrentInstance().execute("PF('InfoPreRadicaciones').show()");
                    break;
                case "No_asociar":
                    estadoPanelGeneral = true;
                    estadoRadiosOpcionesRadicacion = false;
                    limpiarRadicacionGeneral();
                    break;
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".mostrarInfoPreRadicacion()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que carga todos los datos de la pre-radicacion en la radicacion
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @since 01-11-2014
     */
    //Metodo 
    public void agregarDatosPreRadic() {

        radicacionModificacion = false;
        radicacionRegistro = true;
        radicacionVerInfo = false;
        try {
            if (PreRad == null) {
                mbTodero.setMens("Seleccione un numero de Pre-Radicacion");
                mbTodero.warn();
            } else {
                //inhabilita loos componentes que no se van a poder modificar
                estadoComponentesRadicPreRad = true;
                estadoRadiosOpcionesRadicacion = false;
                //Carga la informacion de la pre-radicacion
                Dat = PreRad.ConsultaPreRadicacion(PreRad.getCodPreRadica());
                if (Dat.next()) {
                    cod_preRadica = PreRad.getCodPreRadica();
                    fechaSolicRadic = Dat.getDate("Fecha_Solicitud_Preradica");
                    HoraSol = Dat.getTime("Hora_Preradica");
                    numeroHojas = Dat.getString("NumHojas_Preradica");
                    solicitante = Dat.getString("Solicitante_PreRadica");
                    if ("Otro".equals(Dat.getString("Resultado_Parametro"))) {
                        envioInformacion = Dat.getString("DetalleEnvio_PreRadica");
                    } else {
                        envioInformacion = Dat.getString("Resultado_Parametro");
                    }
                    codEnvioInformacion = Dat.getString("EnvioInformacion_Preradica");
                    nombreProEnt = Dat.getString("Nombre_ProEnt");
                    codProEnt = Dat.getString("Cod_ProEnt");
                    getConsulTipProEnt();
                    nombreTipProEnt = Dat.getString("Nombre_TipProEnt");
                    codTipProEnt = Dat.getString("Fk_Cod_TipProEnt");
                    provieneCotizacion = Dat.getBoolean("Cotizacion_PreRadica");
                    estadoObservacionRadic = true;
                    DatObser = PreRad.ConsultaObservacionesPreRad(String.valueOf(cod_preRadica));
                    ListObserPreRadicados = new ArrayList<>();
                    while (DatObser.next()) {
                        LogPreRadicacion PreRadObs = new LogPreRadicacion();
                        PreRadObs.setObservacionPreRadica(DatObser.getString("Obser"));
                        PreRadObs.setFechaObsPreRadica(DatObser.getString("Fecha"));
                        PreRadObs.setEmpleObservacionPreRadica(DatObser.getString("empleado"));
                        ListObserPreRadicados.add(PreRadObs);
                    }
                    Conexion.Conexion.CloseCon();
                    //observacion = Dat.getString("Observacion_Preradica");
                    estadoPreRad = Dat.getString("Estado_Preradica");
                    if ("P".equals(estadoPreRad)) {
                        estadoPreRad = "En Proceso";
                    }
                    if ("".equals(Dat.getString("Fk_Cod_Ciudad")) || Dat.getString("Fk_Cod_Ciudad") == null) {
                        requierUbicacion = false;
                    } else {
                        requierUbicacion = true;
                        mBAvaluo.setNombreDeptoSol(Dat.getString("Nombre_Departamento"));
                        mBAvaluo.setCodDeptoSol(Dat.getString("Fk_Cod_Departamento"));
                        mBAvaluo.cargCiudadSolic();
                        mBAvaluo.setNombreCiudadSol(Dat.getString("Nombre_ciudad"));
                        mBAvaluo.setCodCiudadSol(Dat.getString("Fk_Cod_Ciudad"));
                    }

                }

                Conexion.Conexion.CloseCon();
                if ("En Proceso".equals(estadoPreRad)) {

                    //Consulta si exixten clientes temporales
                    /*
                     mBCliente.setCargaListaClientesTempo(Cli.consultaClientesTempor(PreRad.getCodPreRadica()));
                     //Si trae clientes temporales 
                     if (mBCliente.getCargaListaClientesTempo().size() > 0) {
                     mBCliente.setValidarClienteTempo(true);
                     mBCliente.setCod_preRadic(PreRad.getCodPreRadica());
                     mBCliente.setEstadoTablaClientesTempo(true);
                     mBCliente.setEstadoClosableClientesRegistro(false);
                     RequestContext.getCurrentInstance().execute("PF('RegistroClientes').show()");
                     } else {
                     mbTodero.setMens( "No hay clientes temporales, cargar resto de información";
                     mbTodero.info();*/
                    //Carga la informacion de los clientes                        
                    mBCliente.setCod_preRadic(PreRad.getCodPreRadica());
                    mBCliente.cargarClientesEnRadicacion(1);
                    //}

                    //Carga la informacion de la entidad  
                    mBEntidad.setCod_preRadic(PreRad.getCodPreRadica());
                    mBEntidad.cargarEntidadesPreRadic(1);

                    //Carga la informacion de los peritos       
                    //mBPerito.setCod_preRadic(PreRad.getCodPreRadica());
                    //mBPerito.CargarInfoPeritosRadic();
                    //Carga la informacion del avaluo
                    mBAvaluo.getNombreTipoBienRadic();
                    mBAvaluo.setCod_preRadica(PreRad.getCodPreRadica());
                    mBAvaluo.cargarAvaluoRadicacion(1);
                    mBAvaluo.getNombreTipoBienRadic();

                    //Pone estado true el panel general de todos los datos
                    estadoPanelGeneral = true;

                    //Cierra el dialog que tiene las pre-radicaciones
                    //RequestContext.getCurrentInstance().execute("PF('InfoPreRadicaciones').hide()");
                } else {
                    mbTodero.setMens("La Pre-Radicación seleccionada no se puede asociar a una Radicación por el estado en que se encuentra");
                    mbTodero.warn();
                }

            }
        } catch (SQLException e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".agregarDatosPreRadic()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    public void cargarPaneles(int tipo) {
        try {
            if (tipo == 1) {
                switch (this.mBCliente.getOpcionMostrarCliSolicitante()) {
                    case "Si":
                        mBCliente.setEstadoPanelClienteGeneral(true);
                        mBCliente.setEstadoSeparadorClienteEnti(false);
                        break;
                    case "No":
                        limpiarClientes();
                        break;
                }
            } else if (tipo == 2) {
                switch (this.mBEntidad.getOpcionMostrarEntidad()) {
                    case "Si1":
                        this.mBEntidad.setEstPanelEntidad(true);
                        break;
                    case "No1":
                        this.mBEntidad.setEstPanelEntidad(false);
                        limpiarEntidades();
                        break;
                }
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".cargarPaneles()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que muestra los paneles de contacto
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @since 01-11-2014
     */
    public void mostrarInfoContacto() {
        try {
            estadoLabelsClienteEnti = false;
            switch (opcionRadioContacto) {
                case "Cliente":
                    limpiarContacto();
                    String telefono = "";
                    int codCiudad = 0;
                    String lbciudad = "";
                    String lbdepto = "";
                    if ("".equals(mBCliente.getNombreCliente1()) && "".equals(mBCliente.getNumeroDoccliente1())) {
                        mbTodero.setMens("Debe llenar la información del cliente");
                        mbTodero.warn();
                    } else {
                        CargClientesSelecc.clear();
                        LogCliente Clie = new LogCliente();
                        Dat = Clie.ConsulCodCli(mBCliente.getNumeroDoccliente1());
                        if (Dat.next()) {
                            telefono = Dat.getString("telefono_cliente");
                            codCiudad = Dat.getInt("Fk_Cod_Ciudad");
                            lbciudad = Dat.getString("Nombre_Ciudad");
                            lbdepto = Dat.getString("Nombre_Departamento");
                        }
                        Conexion.Conexion.CloseCon();
                        Clie.setNumeroDoccliente(mBCliente.getNumeroDoccliente1());
                        Clie.setNombreCliente(mBCliente.getNombreCliente1());
                        Clie.setTelefonoCliente(telefono);
                        Clie.setFK_CodCiudad(codCiudad);
                        Clie.setCiudadCliente(lbciudad);
                        Clie.setDeptoCliente(lbdepto);
                        CargClientesSelecc.add(Clie);

                        if (mBCliente.isEstadocajasAgregarF1()) {
                            Clie = new LogCliente();
                            Dat = Clie.ConsulCodCli(mBCliente.getNumeroDoccliente2());
                            if (Dat.next()) {
                                telefono = Dat.getString("telefono_cliente");
                                codCiudad = Dat.getInt("Fk_Cod_Ciudad");
                                lbciudad = Dat.getString("Nombre_Ciudad");
                                lbdepto = Dat.getString("Nombre_Departamento");
                            }
                            Conexion.Conexion.CloseCon();
                            Clie.setNumeroDoccliente(mBCliente.getNumeroDoccliente2());
                            Clie.setNombreCliente(mBCliente.getNombreCliente2());
                            Clie.setTelefonoCliente(telefono);
                            Clie.setFK_CodCiudad(codCiudad);
                            Clie.setCiudadCliente(lbciudad);
                            Clie.setDeptoCliente(lbdepto);
                            CargClientesSelecc.add(Clie);
                        }
                        if (mBCliente.isEstadocajasAgregarF2()) {
                            Clie = new LogCliente();
                            Dat = Clie.ConsulCodCli(mBCliente.getNumeroDoccliente3());
                            if (Dat.next()) {
                                telefono = Dat.getString("telefono_cliente");
                                codCiudad = Dat.getInt("Fk_Cod_Ciudad");
                                lbciudad = Dat.getString("Nombre_Ciudad");
                                lbdepto = Dat.getString("Nombre_Departamento");
                            }
                            Conexion.Conexion.CloseCon();
                            Clie.setNumeroDoccliente(mBCliente.getNumeroDoccliente3());
                            Clie.setNombreCliente(mBCliente.getNombreCliente3());
                            Clie.setTelefonoCliente(telefono);
                            Clie.setFK_CodCiudad(codCiudad);
                            Clie.setCiudadCliente(lbciudad);
                            Clie.setDeptoCliente(lbdepto);
                            CargClientesSelecc.add(Clie);
                        }
                        if (mBCliente.isEstadocajasAgregarF3()) {
                            Clie = new LogCliente();
                            Dat = Clie.ConsulCodCli(mBCliente.getNumeroDoccliente4());
                            if (Dat.next()) {
                                telefono = Dat.getString("telefono_cliente");
                                codCiudad = Dat.getInt("Fk_Cod_Ciudad");
                                lbciudad = Dat.getString("Nombre_Ciudad");
                                lbdepto = Dat.getString("Nombre_Departamento");
                            }
                            Conexion.Conexion.CloseCon();
                            Clie.setNumeroDoccliente(mBCliente.getNumeroDoccliente4());
                            Clie.setNombreCliente(mBCliente.getNombreCliente4());
                            Clie.setTelefonoCliente(telefono);

                            Clie.setFK_CodCiudad(codCiudad);
                            Clie.setCiudadCliente(lbciudad);
                            Clie.setDeptoCliente(lbdepto);
                            CargClientesSelecc.add(Clie);
                        }
                        if (mBCliente.isEstadocajasAgregarF4()) {
                            Clie = new LogCliente();
                            Dat = Clie.ConsulCodCli(mBCliente.getNumeroDoccliente5());
                            if (Dat.next()) {
                                telefono = Dat.getString("telefono_cliente");
                                codCiudad = Dat.getInt("Fk_Cod_Ciudad");
                                lbciudad = Dat.getString("Nombre_Ciudad");
                                lbdepto = Dat.getString("Nombre_Departamento");
                            }
                            Conexion.Conexion.CloseCon();
                            Clie.setNumeroDoccliente(mBCliente.getNumeroDoccliente5());
                            Clie.setNombreCliente(mBCliente.getNombreCliente5());
                            Clie.setTelefonoCliente(telefono);

                            Clie.setFK_CodCiudad(codCiudad);
                            Clie.setCiudadCliente(lbciudad);
                            Clie.setDeptoCliente(lbdepto);
                            CargClientesSelecc.add(Clie);
                        }
                        RequestContext.getCurrentInstance().execute("PF('DlgSelectClieContacto').show()");
                    }
                    break;
                case "Entidad":
                    limpiarContacto();
                    if ("".equals(mBEntidad.getCodEntidad1()) && "".equals(mBEntidad.getCodSucursal1()) && "".equals(mBEntidad.getCodAsesor1())) {
                        mbTodero.setMens("Debe llenar la información de la entidad");
                        mbTodero.warn();
                    } else {
                        CargEntidSelecc.clear();
                        EntiConta = null;
                        LogEntidad Entid = new LogEntidad();
                        Enti.setCodEntidad(Integer.parseInt(mBEntidad.getCodEntidad1()));
                        Enti.setCodSucursal(Integer.parseInt(mBEntidad.getCodSucursal1()));
                        Enti.setCodAsesor(Integer.parseInt(mBEntidad.getCodAsesor1()));
                        Dat = Enti.ConsulInfEntAsesor();
                        if (Dat.next()) {
                            Entid.setNombreEntidad(Dat.getString("nombre_entidad"));
                            Entid.setNombreOfic(Dat.getString("nombre_oficina"));
                            Entid.setTelefonoOfic(Dat.getString("telefono_oficina"));
                            Entid.setNombreAsesor(Dat.getString("nombre_asesor"));
                            Entid.setTelefonoAsesor(Dat.getString("telefono_asesor"));
                            Entid.setCodDeptoSucursal(Dat.getString("Fk_Cod_Ciudad"));
                            Entid.setNombreCiudadSucursal(Dat.getString("Nombre_Ciudad"));
                            Entid.setNombreDeptoSucursal(Dat.getString("Nombre_Departamento"));
                        }
                        CargEntidSelecc.add(Entid);
                        Conexion.Conexion.CloseCon();
                        if (mBEntidad.isEstadoAgregarFilasEntidad2()) {
                            Entid = new LogEntidad();
                            Enti.setCodEntidad(Integer.parseInt(mBEntidad.getCodEntidad2()));
                            Enti.setCodSucursal(Integer.parseInt(mBEntidad.getCodSucursal2()));
                            Enti.setCodAsesor(Integer.parseInt(mBEntidad.getCodAsesor2()));
                            Dat = Enti.ConsulInfEntAsesor();
                            if (Dat.next()) {
                                Entid.setNombreEntidad(Dat.getString("nombre_entidad"));
                                Entid.setNombreOfic(Dat.getString("nombre_oficina"));
                                Entid.setTelefonoOfic(Dat.getString("telefono_oficina"));
                                Entid.setNombreAsesor(Dat.getString("nombre_asesor"));
                                Entid.setTelefonoAsesor(Dat.getString("telefono_asesor"));
                                Entid.setCodDeptoSucursal(Dat.getString("Fk_Cod_Ciudad"));
                                Entid.setNombreCiudadSucursal(Dat.getString("Nombre_Ciudad"));
                                Entid.setNombreDeptoSucursal(Dat.getString("Nombre_Departamento"));
                            }
                            CargEntidSelecc.add(Entid);
                            Conexion.Conexion.CloseCon();
                        }
                        if (mBEntidad.isEstadoAgregarFilasEntidad3()) {
                            Entid = new LogEntidad();
                            Enti.setCodEntidad(Integer.parseInt(mBEntidad.getCodEntidad3()));
                            Enti.setCodSucursal(Integer.parseInt(mBEntidad.getCodSucursal3()));
                            Enti.setCodAsesor(Integer.parseInt(mBEntidad.getCodAsesor3()));
                            Dat = Enti.ConsulInfEntAsesor();
                            if (Dat.next()) {
                                Entid.setNombreEntidad(Dat.getString("nombre_entidad"));
                                Entid.setNombreOfic(Dat.getString("nombre_oficina"));
                                Entid.setTelefonoOfic(Dat.getString("telefono_oficina"));
                                Entid.setNombreAsesor(Dat.getString("nombre_asesor"));
                                Entid.setTelefonoAsesor(Dat.getString("telefono_asesor"));

                                Entid.setCodDeptoSucursal(Dat.getString("Fk_Cod_Ciudad"));
                                Entid.setNombreCiudadSucursal(Dat.getString("Nombre_Ciudad"));
                                Entid.setNombreDeptoSucursal(Dat.getString("Nombre_Departamento"));
                            }
                            CargEntidSelecc.add(Entid);
                            Conexion.Conexion.CloseCon();
                        }
                        if (mBEntidad.isEstadoAgregarFilasEntidad4()) {
                            Entid = new LogEntidad();
                            Enti.setCodEntidad(Integer.parseInt(mBEntidad.getCodEntidad4()));
                            Enti.setCodSucursal(Integer.parseInt(mBEntidad.getCodSucursal4()));
                            Enti.setCodAsesor(Integer.parseInt(mBEntidad.getCodAsesor4()));
                            Dat = Enti.ConsulInfEntAsesor();
                            if (Dat.next()) {
                                Entid.setNombreEntidad(Dat.getString("nombre_entidad"));
                                Entid.setNombreOfic(Dat.getString("nombre_oficina"));
                                Entid.setTelefonoOfic(Dat.getString("telefono_oficina"));
                                Entid.setNombreAsesor(Dat.getString("nombre_asesor"));
                                Entid.setTelefonoAsesor(Dat.getString("telefono_asesor"));

                                Entid.setCodDeptoSucursal(Dat.getString("Fk_Cod_Ciudad"));
                                Entid.setNombreCiudadSucursal(Dat.getString("Nombre_Ciudad"));
                                Entid.setNombreDeptoSucursal(Dat.getString("Nombre_Departamento"));
                            }
                            CargEntidSelecc.add(Entid);
                            Conexion.Conexion.CloseCon();
                        }
                        if (mBEntidad.isEstadoAgregarFilasEntidad5()) {
                            Entid = new LogEntidad();
                            Enti.setCodEntidad(Integer.parseInt(mBEntidad.getCodEntidad5()));
                            Enti.setCodSucursal(Integer.parseInt(mBEntidad.getCodSucursal5()));
                            Enti.setCodAsesor(Integer.parseInt(mBEntidad.getCodAsesor5()));
                            Dat = Enti.ConsulInfEntAsesor();
                            if (Dat.next()) {
                                Entid.setNombreEntidad(Dat.getString("nombre_entidad"));
                                Entid.setNombreOfic(Dat.getString("nombre_oficina"));
                                Entid.setTelefonoOfic(Dat.getString("telefono_oficina"));
                                Entid.setNombreAsesor(Dat.getString("nombre_asesor"));
                                Entid.setTelefonoAsesor(Dat.getString("telefono_asesor"));

                                Entid.setCodDeptoSucursal(Dat.getString("Fk_Cod_Ciudad"));
                                Entid.setNombreCiudadSucursal(Dat.getString("Nombre_Ciudad"));
                                Entid.setNombreDeptoSucursal(Dat.getString("Nombre_Departamento"));
                            }
                            CargEntidSelecc.add(Entid);
                            Conexion.Conexion.CloseCon();
                        }
                        RequestContext.getCurrentInstance().execute("PF('DlgSelectEntiContacto').show()");

                    }
                    break;
                case "Otro":
                    limpiarContacto();
                    break;
            }
        } catch (NumberFormatException | SQLException e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".mostrarInfoContacto()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo que agrega la informacion seleccionada a los paneles de contacto,
     * cuando es cliente
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @since 01-11-2014
     */
    public void agregarContacClie() {
        try {
            if (ClieConta == null) {
                mbTodero.setMens("Debe seleccionar un cliente");
                mbTodero.warn();
            } else {
                nombreContacto = ClieConta.getNombreCliente();
                telefonoContacto = ClieConta.getTelefonoCliente();
                DeptoContacto = ClieConta.getDeptoCliente();
                codCiudContacto = String.valueOf(ClieConta.getFK_CodCiudad());
                CiudContacto = ClieConta.getCiudadCliente();
                RequestContext.getCurrentInstance().execute("PF('DlgSelectClieContacto').hide()");
                estadoLabelsClienteEnti = true;
            }

        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".agregarContacClie()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo que agrega la informacion seleccionada a los paneles de contacto,
     * cuando es entidad
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @since 01-11-2014
     */
    public void agregarContacEnti() {
        try {
            if (EntiConta == null) {
                mbTodero.setMens("Debe seleccionar una entidad");
                mbTodero.warn();
            } else {
                nombreContacto = EntiConta.getNombreAsesor();
                telefonoContacto = EntiConta.getTelefonoOfic();
                celularContacto = EntiConta.getTelefonoAsesor();
                DeptoContacto = EntiConta.getNombreDeptoSucursal();
                codCiudContacto = String.valueOf(EntiConta.getCodCiudadSucursal());
                CiudContacto = EntiConta.getNombreCiudadSucursal();
                RequestContext.getCurrentInstance().execute("PF('DlgSelectEntiContacto').hide()");
                estadoLabelsClienteEnti = true;
            }

        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".agregarContacEnti()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo tipo ajax que llama al metodo cargCiudadContac() para cargar la
     * ciudad del contacto
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @since 01-11-2014
     */
    public void onCiudadContacto() {
        try {
            if (codDeptoContacto != null && !codDeptoContacto.equals("")) {
                CargCiudadesContac.clear();
                cargCiudadContac();
            } else {
                mbTodero.setMens("No sirve");
                mbTodero.error();
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".onCiudadContacto()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que carga la ciudad del contacto en un selectedOneMenu
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @return ArrayList que retorna una lista con las ciudades
     * @since 01-11-2014
     */
    public ArrayList<SelectItem> cargCiudadContac() {
        try {
            Iterator<LogUbicacion> Ite;
            Ite = Ubi.getCiudad(Integer.parseInt(this.codDeptoContacto)).iterator();
            while (Ite.hasNext()) {
                LogUbicacion MBUbi = Ite.next();
                this.CargCiudadesContac.add(new SelectItem(MBUbi.getIdCiu(), MBUbi.getNomCiu()));
            }
        } catch (NumberFormatException e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".cargCiudadContac()' causado por: " + e.getMessage());
            mbTodero.error();
        }
        return CargCiudadesContac;
    }

    /**
     * Metodo que limpian la informacion de los paneles de contacto
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @since 01-11-2014
     */
    public void limpiarContacto() {
        try {
            nombreContacto = "";
            celularContacto = "";
            telefonoContacto = "";
            observacionContacto = "";
            ClieConta = null;
            EntiConta = null;
            codDeptoContacto = "";
            CargCiudadesContac.clear();
            codCiudContacto = "";
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".limpiarContacto()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    private void limpiarClientes() {
        try {
            mBCliente.setEstadoPanelClienteGeneral(false);
            mBCliente.setEstadoSeparadorClienteEnti(true);

            //Fila 1
            mBCliente.setClientFact1(false);
            mBCliente.setFuncionario1(false);
            mBCliente.setNumeroDoccliente1("");
            mBCliente.setNombreCliente1("");
            mBCliente.setTelefonoCliente1("");
            mBCliente.setMensajeConsultaCliente1("");
            mBCliente.setEstadoBtnRegistroF1(false);
            mBCliente.setEstadoConsultaCliente1(false);
            mBCliente.setEditar1(false);
            mBCliente.setAnalizar1(true);

            mBCliente.setEstadoAgregarTarifasPactCli1(false);
            mBCliente.setEstadoTarifasPactCli1(false);
            mBCliente.setOpcionRadioTarifasPactadosCli1("");
            mBCliente.setValorTarifaCli1("");
            mBCliente.setTipoTarifaCli1("");

            //Fila 2
            mBCliente.setEstadocajasAgregarF1(false);
            mBCliente.setClientFact2(false);
            mBCliente.setFuncionario2(false);
            mBCliente.setNumeroDoccliente2("");
            mBCliente.setNombreCliente2("");
            mBCliente.setTelefonoCliente2("");
            mBCliente.setMensajeConsultaCliente2("");
            mBCliente.setEstadoBtnRegistroF2(false);
            mBCliente.setEstadoConsultaCliente2(false);

            mBCliente.setEstadoAgregarTarifasPactCli2(false);
            mBCliente.setEstadoTarifasPactCli2(false);
            mBCliente.setOpcionRadioTarifasPactadosCli2("");
            mBCliente.setValorTarifaCli2("");
            mBCliente.setTipoTarifaCli2("");

            //Fila 3
            mBCliente.setEstadocajasAgregarF2(false);
            mBCliente.setClientFact3(false);
            mBCliente.setFuncionario3(false);
            mBCliente.setNumeroDoccliente3("");
            mBCliente.setNombreCliente3("");
            mBCliente.setTelefonoCliente3("");
            mBCliente.setMensajeConsultaCliente3("");
            mBCliente.setEstadoBtnRegistroF3(false);
            mBCliente.setEstadoConsultaCliente3(false);

            mBCliente.setEstadoAgregarTarifasPactCli3(false);
            mBCliente.setEstadoTarifasPactCli3(false);
            mBCliente.setOpcionRadioTarifasPactadosCli3("");
            mBCliente.setValorTarifaCli3("");
            mBCliente.setTipoTarifaCli3("");

            //Fila 4
            mBCliente.setEstadocajasAgregarF3(false);
            mBCliente.setClientFact4(false);
            mBCliente.setFuncionario4(false);
            mBCliente.setNumeroDoccliente4("");
            mBCliente.setNombreCliente4("");
            mBCliente.setTelefonoCliente4("");
            mBCliente.setMensajeConsultaCliente4("");
            mBCliente.setEstadoBtnRegistroF4(false);
            mBCliente.setEstadoConsultaCliente4(false);

            mBCliente.setEstadoAgregarTarifasPactCli4(false);
            mBCliente.setEstadoTarifasPactCli4(false);
            mBCliente.setOpcionRadioTarifasPactadosCli4("");
            mBCliente.setValorTarifaCli4("");
            mBCliente.setTipoTarifaCli4("");

            //Fila 5
            mBCliente.setEstadocajasAgregarF4(false);
            mBCliente.setClientFact5(false);
            mBCliente.setFuncionario5(false);
            mBCliente.setNumeroDoccliente5("");
            mBCliente.setNombreCliente5("");
            mBCliente.setTelefonoCliente5("");
            mBCliente.setMensajeConsultaCliente5("");
            mBCliente.setEstadoBtnRegistroF5(false);
            mBCliente.setEstadoConsultaCliente5(false);

            mBCliente.setEstadoAgregarTarifasPactCli5(false);
            mBCliente.setEstadoTarifasPactCli5(false);
            mBCliente.setOpcionRadioTarifasPactadosCli5("");
            mBCliente.setValorTarifaCli5("");
            mBCliente.setTipoTarifaCli5("");

            mBCliente.setFila1(false);
            mBCliente.setFila2(false);
            mBCliente.setFila3(false);
            mBCliente.setFila4(false);
            mBCliente.setFila5(false);

            mBCliente.setEditar1(true);
            mBCliente.setEditar2(false);
            mBCliente.setEditar3(false);
            mBCliente.setEditar4(false);
            mBCliente.setEditar5(false);

            mBCliente.setAnalizar1(true);
            mBCliente.setAnalizar2(false);
            mBCliente.setAnalizar3(false);
            mBCliente.setAnalizar4(false);
            mBCliente.setAnalizar5(false);

            mBCliente.setAceptar1(true);
            mBCliente.setAceptar2(false);
            mBCliente.setAceptar3(false);
            mBCliente.setAceptar4(false);
            mBCliente.setAceptar5(false);

            mBCliente.setQuitar1(true);
            mBCliente.setQuitar2(false);
            mBCliente.setQuitar3(false);
            mBCliente.setQuitar4(false);
            mBCliente.setQuitar5(false);

            mBCliente.setOtro1(true);
            mBCliente.setOtro2(false);
            mBCliente.setOtro3(false);
            mBCliente.setOtro4(false);

            mBCliente.setFuncionario1(false);
            mBCliente.setFuncionario2(false);
            mBCliente.setFuncionario3(false);
            mBCliente.setFuncionario4(false);
            mBCliente.setFuncionario5(false);
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".limpiarClientes()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    private void limpiarEntidades() {
        try {
            mBEntidad.setEstPanelEntidad(false);

            //Fila 1
            mBEntidad.setEntidadFact1(false);
            mBEntidad.setCodEntidad1("");
            mBEntidad.setNombreEntidad1("");
            mBEntidad.setCodSucursal1("");
            mBEntidad.setNombreSucursal1("");
            mBEntidad.setCodAsesor1("");
            mBEntidad.setNombreAsesor1("");

            mBEntidad.setMailAsesor1("");
            mBEntidad.setTelefonoAsesor1("");

            mBEntidad.setOpcionRadioTarifasPactadosEnti1("");
            mBEntidad.setTipoTarifaEnti1("");
            mBEntidad.setValorTarifaEnti1("");
            mBEntidad.setEstadoAgregarTarifasPactEnti1(false);
            mBEntidad.setEstadoTarifasPactEnti1(false);

            //Fila 2
            mBEntidad.setEstadoAgregarFilasEntidad2(false);
            mBEntidad.setEntidadFact2(false);
            mBEntidad.setCodEntidad2("");
            mBEntidad.setNombreEntidad2("");
            mBEntidad.setCodSucursal2("");
            mBEntidad.setNombreSucursal2("");
            mBEntidad.setCodAsesor2("");
            mBEntidad.setNombreAsesor2("");

            mBEntidad.setMailAsesor2("");
            mBEntidad.setTelefonoAsesor2("");

            mBEntidad.setOpcionRadioTarifasPactadosEnti2("");
            mBEntidad.setTipoTarifaEnti2("");
            mBEntidad.setValorTarifaEnti2("");
            mBEntidad.setEstadoAgregarTarifasPactEnti2(false);
            mBEntidad.setEstadoTarifasPactEnti2(false);

            //Fila 3
            mBEntidad.setEstadoAgregarFilasEntidad3(false);
            mBEntidad.setEntidadFact3(false);
            mBEntidad.setCodEntidad3("");
            mBEntidad.setNombreEntidad3("");
            mBEntidad.setCodSucursal3("");
            mBEntidad.setNombreSucursal3("");
            mBEntidad.setCodAsesor3("");
            mBEntidad.setNombreAsesor3("");

            mBEntidad.setMailAsesor3("");
            mBEntidad.setTelefonoAsesor3("");

            mBEntidad.setOpcionRadioTarifasPactadosEnti3("");
            mBEntidad.setTipoTarifaEnti3("");
            mBEntidad.setValorTarifaEnti3("");
            mBEntidad.setEstadoAgregarTarifasPactEnti3(false);
            mBEntidad.setEstadoTarifasPactEnti3(false);

            //Fila 4
            mBEntidad.setEstadoAgregarFilasEntidad4(false);
            mBEntidad.setEntidadFact4(false);
            mBEntidad.setCodEntidad4("");
            mBEntidad.setNombreEntidad4("");
            mBEntidad.setCodSucursal4("");
            mBEntidad.setNombreSucursal4("");
            mBEntidad.setCodAsesor4("");
            mBEntidad.setNombreAsesor4("");

            mBEntidad.setMailAsesor4("");
            mBEntidad.setTelefonoAsesor4("");

            mBEntidad.setOpcionRadioTarifasPactadosEnti4("");
            mBEntidad.setTipoTarifaEnti4("");
            mBEntidad.setValorTarifaEnti4("");
            mBEntidad.setEstadoAgregarTarifasPactEnti4(false);
            mBEntidad.setEstadoTarifasPactEnti4(false);

            //Fila 5
            mBEntidad.setEstadoAgregarFilasEntidad5(false);
            mBEntidad.setEntidadFact5(false);
            mBEntidad.setCodEntidad5("");
            mBEntidad.setNombreEntidad5("");
            mBEntidad.setCodSucursal5("");
            mBEntidad.setNombreSucursal5("");
            mBEntidad.setCodAsesor5("");
            mBEntidad.setNombreAsesor5("");

            mBEntidad.setMailAsesor5("");
            mBEntidad.setTelefonoAsesor5("");

            mBEntidad.setOpcionRadioTarifasPactadosEnti5("");
            mBEntidad.setTipoTarifaEnti5("");
            mBEntidad.setValorTarifaEnti5("");
            mBEntidad.setEstadoAgregarTarifasPactEnti5(false);
            mBEntidad.setEstadoTarifasPactEnti5(false);

            mBEntidad.setEstadoInfoAsesor1(false);
            mBEntidad.setEstadoInfoAsesor2(false);
            mBEntidad.setEstadoInfoAsesor3(false);
            mBEntidad.setEstadoInfoAsesor4(false);
            mBEntidad.setEstadoInfoAsesor5(false);

            mBEntidad.setAceptarFila1(false);
            mBEntidad.setAceptarFila2(false);
            mBEntidad.setAceptarFila3(false);
            mBEntidad.setAceptarFila4(false);
            mBEntidad.setAceptarFila5(false);

            mBEntidad.setAgregarFila1(true);
            mBEntidad.setAgregarFila2(false);
            mBEntidad.setAgregarFila3(false);
            mBEntidad.setAgregarFila4(false);

            mBEntidad.setQuitarFila1(true);
            mBEntidad.setQuitarFila2(false);
            mBEntidad.setQuitarFila3(false);
            mBEntidad.setQuitarFila4(false);
            mBEntidad.setQuitarFila5(false);

            mBEntidad.setEditarFila1(false);
            mBEntidad.setEditarFila2(false);
            mBEntidad.setEditarFila3(false);
            mBEntidad.setEditarFila4(false);
            mBEntidad.setEditarFila5(false);

            mBEntidad.setDeshabilitarAsesor1(false);
            mBEntidad.setDeshabilitarAsesor2(false);
            mBEntidad.setDeshabilitarAsesor3(false);
            mBEntidad.setDeshabilitarAsesor4(false);
            mBEntidad.setDeshabilitarAsesor5(false);

        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".limpiarEntidades()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    public void verificarInfoRadic() {
        try {

            if ("".equals(numeroHojas) || numeroHojas == null) {
                mbTodero.setMens("Debe llenar del campo 'Numero hojas'");
                mbTodero.warn();
                estadoValidacionInfoGeneral = false;
            } else if ("".equals(solicitante) || solicitante == null) {
                mbTodero.setMens("Debe seleccionar información del campo 'Solicitante'");
                mbTodero.warn();
                estadoValidacionInfoGeneral = false;
            } else if ("".equals(envioInformacion) || envioInformacion == null) {
                mbTodero.setMens("Debe seleccionar información del campo 'Enviar a'");
                mbTodero.warn();
                estadoValidacionInfoGeneral = false;
            } else if ("".equals(codProEnt) || codProEnt == null) {
                mbTodero.setMens("Debe seleccionar información del campo 'Producto Entidad'");
                mbTodero.warn();
                estadoValidacionInfoGeneral = false;
            } else if ("".equals(codTipProEnt) || codTipProEnt == null) {
                mbTodero.setMens("Debe seleccionar información del campo 'Tipo Producto Entidad'");
                mbTodero.warn();
                estadoValidacionInfoGeneral = false;
            } else if (fechaSolicRadic == null) {
                mbTodero.setMens("Debe seleccionar información del campo 'Fecha Solicitud'");
                mbTodero.warn();
                estadoValidacionInfoGeneral = false;
            } else if (fechaSolicRadic == null) {
                mbTodero.setMens("Debe seleccionar información del campo 'Fecha Solicitud'");
                mbTodero.warn();
                estadoValidacionInfoGeneral = false;
            } else if (requierUbicacion == true && ("".equals(mBAvaluo.getCodDeptoSol()) || mBAvaluo.getCodDeptoSol() == null)) {
                mbTodero.setMens("Debe seleccionar información del campo 'Departamento (Solicitud)'");
                mbTodero.warn();
                estadoValidacionInfoGeneral = false;
            } else if (requierUbicacion == true && ("".equals(mBAvaluo.getCodCiudadSol()) || mBAvaluo.getCodCiudadSol() == null)) {
                mbTodero.setMens("Debe seleccionar información del campo 'Ciudad (Solicitud)'");
                mbTodero.warn();
                estadoValidacionInfoGeneral = false;
            } else {
                estadoValidacionInfoGeneral = true;

            }

            //validaciones bien
            if (mBAvaluo.isEstadoPanelAvalRadic() == false) {
                mbTodero.setMens("Debe agregar un bien");
                estadoValidacionInfoBien = false;
                mbTodero.warn();
            } else {
                estadoValidacionInfoBien = true;
            }

            if (mBCliente.isEstadoPanelClienteGeneral() == false && mBEntidad.isEstPanelEntidad() == false) {
                mbTodero.setMens("Debe haber informacion en cliente o entidad");
                mbTodero.warn();
                estadoValidacionInfoCliente = false;
            }
            if ((mBCliente.isClientFact1() == true || mBCliente.isClientFact2() == true || mBCliente.isClientFact3() == true || mBCliente.isClientFact4() == true || mBCliente.isClientFact5() == true)
                    && (mBEntidad.isEntidadFact1() == true || mBEntidad.isEntidadFact2() == true || mBEntidad.isEntidadFact3() == true || mBEntidad.isEntidadFact4() == true || mBEntidad.isEntidadFact5() == true)) {
                mbTodero.setMens("No puede haber persona a facturar en cliente y entidad");
                estadoValidacionInfoEntidad = false;
                estadoValidacionInfoCliente = false;
                mbTodero.warn();
                return;
            } //validar persona solicitante            
            if (mBCliente.isEstadoPanelClienteGeneral() == true) { //validar si el panel es clientes es visible
                //validar la fila 1
                if ("".equals(mBCliente.getNombreCliente1()) && "".equals(mBCliente.getNumeroDoccliente1())) {
                    mbTodero.setMens("Debe agregar la informacion del cliente");
                    mbTodero.warn();
                    estadoValidacionInfoCliente = false;
                } else if ("".equals(mBCliente.getNumeroDoccliente1())) {
                    mbTodero.setMens("Debe llenar el campo 'N° Documento' (Persona solicitante)");
                    mbTodero.warn();
                    estadoValidacionInfoCliente = false;
                } else if ("".equals(mBCliente.getNombreCliente1())) {
                    mbTodero.setMens("Debe llenar el campo 'Nombre' (Persona solicitante)");
                    mbTodero.warn();
                    estadoValidacionInfoCliente = false;
                } else if ("".equals(mBCliente.getTelefonoCliente1())) {
                    mbTodero.setMens("Debe llenar el campo 'Telefono' (Persona solicitante)");
                    mbTodero.warn();
                    estadoValidacionInfoCliente = false;
                } else if ("".equals(mBCliente.getMensajeConsultaCliente1()) || mBCliente.getMensajeConsultaCliente1() == null) {
                    mbTodero.setMens("Debe analizar la información ingresada");
                    mbTodero.warn();
                    estadoValidacionInfoCliente = false;
                } else if ("N/A".equals(mBCliente.getMensajeConsultaCliente1())) {
                    mbTodero.setMens("Debe registrar el cliente");
                    mbTodero.warn();
                    estadoValidacionInfoCliente = false;
                } else if ((("".equals(mBCliente.getTipoTarifaCli1()) || mBCliente.getTipoTarifaCli1() == null) && "Si_Cli1".equals(mBCliente.getOpcionRadioTarifasPactadosCli1()) && mBCliente.isClientFact1())
                        && (("".equals(mBCliente.getValorTarifaCli1()) || mBCliente.getValorTarifaCli1() == null) && "Si_Cli1".equals(mBCliente.getOpcionRadioTarifasPactadosCli1()) && mBCliente.isClientFact1())) {
                    mbTodero.setMens("Debe llenar los campos de la tarifa (Persona solicitante)");
                    mbTodero.warn();
                    estadoValidacionInfoCliente = false;
                } else if (("".equals(mBCliente.getTipoTarifaCli1()) || mBCliente.getTipoTarifaCli1() == null) && "Si_Cli1".equals(mBCliente.getOpcionRadioTarifasPactadosCli1()) && mBCliente.isClientFact1()) {
                    mbTodero.setMens("Debe llenar el campo 'Tipo tarifa' (Persona solicitante)");
                    mbTodero.warn();
                    estadoValidacionInfoCliente = false;
                } else if (("".equals(mBCliente.getValorTarifaCli1()) || mBCliente.getValorTarifaCli1() == null) && "Si_Cli1".equals(mBCliente.getOpcionRadioTarifasPactadosCli1()) && mBCliente.isClientFact1()) {
                    mbTodero.setMens("Debe llenar el campo 'Valor tarifa' (Persona solicitante)");
                    mbTodero.warn();
                    estadoValidacionInfoCliente = false;

                } else {
                    estadoValidacionInfoCliente = true;
                    Dat = Cli.getConsultarAllClientesMostrarinfo(mBCliente.getNumeroDoccliente1());
                    if (Dat.next()) {
                        mBCliente.setTipoDoccliente1(Dat.getString("Nombre_Documento"));
                        mBCliente.setNumeroDoccliente1(Dat.getString("Numero_DocCliente"));
                        mBCliente.setNombreCliente1(Dat.getString("Nombre_Cliente"));
                        mBCliente.setCorreoCliente1(Dat.getString("Mail_Cliente"));
                        mBCliente.setDireccionCliente1(Dat.getString("Direccion_Cliente"));
                        mBCliente.setTelefonoCliente1(Dat.getString("Telefono_Cliente"));
                        mBCliente.setUbicacionCliente1(Dat.getString("Ubicacion"));
                        mBCliente.setCodRegimenCli(Dat.getString("regimen"));
                    }
                    Conexion.Conexion.CloseCon();
                }
                //validar fila 2
                if (mBCliente.isEstadocajasAgregarF1() == true) {
                    if ("".equals(mBCliente.getNombreCliente2()) && "".equals(mBCliente.getNumeroDoccliente2())) {
                        mbTodero.setMens("Debe agregar la informacion del cliente");
                        mbTodero.warn();
                        estadoValidacionInfoCliente = false;
                    } else if ("".equals(mBCliente.getNumeroDoccliente2())) {
                        mbTodero.setMens("Debe llenar el campo 'N° Documento' (Persona solicitante)");
                        mbTodero.warn();
                        estadoValidacionInfoCliente = false;
                    } else if ("".equals(mBCliente.getNombreCliente2())) {
                        mbTodero.setMens("Debe llenar el campo 'Nombre' (Persona solicitante)");
                        mbTodero.warn();
                        estadoValidacionInfoCliente = false;
                    } else if ("".equals(mBCliente.getTelefonoCliente2())) {
                        mbTodero.setMens("Debe llenar el campo 'Telefono' (Persona solicitante)");
                        mbTodero.warn();
                        estadoValidacionInfoCliente = false;
                    } else if ("".equals(mBCliente.getMensajeConsultaCliente2()) || mBCliente.getMensajeConsultaCliente2() == null) {
                        mbTodero.setMens("Debe analizar la información ingresada");
                        mbTodero.warn();
                        estadoValidacionInfoCliente = false;
                    } else if ("N/A".equals(mBCliente.getMensajeConsultaCliente2())) {
                        mbTodero.setMens("Debe registrar el cliente");
                        mbTodero.warn();
                        estadoValidacionInfoCliente = false;
                    } else if ((("".equals(mBCliente.getTipoTarifaCli2()) || mBCliente.getTipoTarifaCli2() == null) && "Si_Cli2".equals(mBCliente.getOpcionRadioTarifasPactadosCli2()) && mBCliente.isClientFact2())
                            && (("".equals(mBCliente.getValorTarifaCli2()) || mBCliente.getValorTarifaCli2() == null) && "Si_Cli2".equals(mBCliente.getOpcionRadioTarifasPactadosCli2()) && mBCliente.isClientFact2())) {
                        mbTodero.setMens("Debe llenar los campos de la tarifa (Persona solicitante)");
                        mbTodero.warn();
                        estadoValidacionInfoCliente = false;
                    } else if (("".equals(mBCliente.getTipoTarifaCli2()) || mBCliente.getTipoTarifaCli2() == null) && "Si_Cli2".equals(mBCliente.getOpcionRadioTarifasPactadosCli2()) && mBCliente.isClientFact2()) {
                        mbTodero.setMens("Debe llenar el campo 'Tipo tarifa' (Persona solicitante)");
                        mbTodero.warn();
                        estadoValidacionInfoCliente = false;
                    } else if (("".equals(mBCliente.getValorTarifaCli2()) || mBCliente.getValorTarifaCli2() == null) && "Si_Cli2".equals(mBCliente.getOpcionRadioTarifasPactadosCli2()) && mBCliente.isClientFact2()) {
                        mbTodero.setMens("Debe llenar el campo 'Valor tarifa' (Persona solicitante)");
                        mbTodero.warn();
                        estadoValidacionInfoCliente = false;
                    } else {
                        estadoValidacionInfoCliente = true;
                        Dat = Cli.getConsultarAllClientesMostrarinfo(mBCliente.getNumeroDoccliente2());
                        if (Dat.next()) {
                            mBCliente.setTipoDoccliente2(Dat.getString("Nombre_Documento"));
                            mBCliente.setNumeroDoccliente2(Dat.getString("Numero_DocCliente"));
                            mBCliente.setNombreCliente2(Dat.getString("Nombre_Cliente"));
                            mBCliente.setCorreoCliente2(Dat.getString("Mail_Cliente"));
                            mBCliente.setDireccionCliente2(Dat.getString("Direccion_Cliente"));
                            mBCliente.setTelefonoCliente2(Dat.getString("Telefono_Cliente"));
                            mBCliente.setUbicacionCliente2(Dat.getString("Ubicacion"));
                        }
                        Conexion.Conexion.CloseCon();
                    }
                } //validar fila 3
                if (mBCliente.isEstadocajasAgregarF2() == true) {
                    if ("".equals(mBCliente.getNombreCliente3()) && "".equals(mBCliente.getNumeroDoccliente3())) {
                        mbTodero.setMens("Debe agregar la informacion del cliente");
                        mbTodero.warn();
                        estadoValidacionInfoCliente = false;
                    } else if ("".equals(mBCliente.getNumeroDoccliente3())) {
                        mbTodero.setMens("Debe llenar el campo 'N° Documento' (Persona solicitante)");
                        mbTodero.warn();
                        estadoValidacionInfoCliente = false;
                    } else if ("".equals(mBCliente.getNombreCliente3())) {
                        mbTodero.setMens("Debe llenar el campo 'Nombre' (Persona solicitante)");
                        mbTodero.warn();
                        estadoValidacionInfoCliente = false;
                    } else if ("".equals(mBCliente.getTelefonoCliente3())) {
                        mbTodero.setMens("Debe llenar el campo 'Telefono' (Persona solicitante)");
                        mbTodero.warn();
                        estadoValidacionInfoCliente = false;
                    } else if ("".equals(mBCliente.getMensajeConsultaCliente3()) || mBCliente.getMensajeConsultaCliente3() == null) {
                        mbTodero.setMens("Debe analizar la información ingresada");
                        mbTodero.warn();
                        estadoValidacionInfoCliente = false;
                    } else if ("N/A".equals(mBCliente.getMensajeConsultaCliente3())) {
                        mbTodero.setMens("Debe registrar el cliente");
                        mbTodero.warn();
                        estadoValidacionInfoCliente = false;
                    } else if ((("".equals(mBCliente.getTipoTarifaCli3()) || mBCliente.getTipoTarifaCli3() == null) && "Si_Cli3".equals(mBCliente.getOpcionRadioTarifasPactadosCli3()) && mBCliente.isClientFact3())
                            && ("".equals(mBCliente.getValorTarifaCli3()) || mBCliente.getValorTarifaCli3() == null) && "Si_Cli3".equals(mBCliente.getOpcionRadioTarifasPactadosCli3()) && mBCliente.isClientFact3()) {
                        mbTodero.setMens("Debe llenar los campos de la tarifa (Persona solicitante)");
                        mbTodero.warn();
                        estadoValidacionInfoCliente = false;
                    } else if (("".equals(mBCliente.getTipoTarifaCli3()) || mBCliente.getTipoTarifaCli3() == null) && "Si_Cli3".equals(mBCliente.getOpcionRadioTarifasPactadosCli3()) && mBCliente.isClientFact3()) {
                        mbTodero.setMens("Debe llenar el campo 'Tipo tarifa' (Persona solicitante)");
                        mbTodero.warn();
                        estadoValidacionInfoCliente = false;
                    } else if (("".equals(mBCliente.getValorTarifaCli3()) || mBCliente.getValorTarifaCli3() == null) && "Si_Cli3".equals(mBCliente.getOpcionRadioTarifasPactadosCli3()) && mBCliente.isClientFact3()) {
                        mbTodero.setMens("Debe llenar el campo 'Valor tarifa' (Persona solicitante)");
                        mbTodero.warn();
                        estadoValidacionInfoCliente = false;
                    } else {
                        estadoValidacionInfoCliente = true;
                        Dat = Cli.getConsultarAllClientesMostrarinfo(mBCliente.getNumeroDoccliente3());
                        if (Dat.next()) {
                            mBCliente.setTipoDoccliente3(Dat.getString("Nombre_Documento"));
                            mBCliente.setNumeroDoccliente3(Dat.getString("Numero_DocCliente"));
                            mBCliente.setNombreCliente3(Dat.getString("Nombre_Cliente"));
                            mBCliente.setCorreoCliente3(Dat.getString("Mail_Cliente"));
                            mBCliente.setDireccionCliente3(Dat.getString("Direccion_Cliente"));
                            mBCliente.setTelefonoCliente3(Dat.getString("Telefono_Cliente"));
                            mBCliente.setUbicacionCliente3(Dat.getString("Ubicacion"));
                        }
                        Conexion.Conexion.CloseCon();
                    }
                } //validar fila 4
                if (mBCliente.isEstadocajasAgregarF3() == true) {
                    if ("".equals(mBCliente.getNombreCliente4()) && "".equals(mBCliente.getNumeroDoccliente4())) {
                        mbTodero.setMens("Debe agregar la informacion del cliente");
                        mbTodero.warn();
                        estadoValidacionInfoCliente = false;
                    } else if ("".equals(mBCliente.getNumeroDoccliente4())) {
                        mbTodero.setMens("Debe llenar el campo 'N° Documento' (Persona solicitante)");
                        mbTodero.warn();
                        estadoValidacionInfoCliente = false;
                    } else if ("".equals(mBCliente.getNombreCliente4())) {
                        mbTodero.setMens("Debe llenar el campo 'Nombre' (Persona solicitante)");
                        mbTodero.warn();
                        estadoValidacionInfoCliente = false;
                    } else if ("".equals(mBCliente.getTelefonoCliente4())) {
                        mbTodero.setMens("Debe llenar el campo 'Telefono' (Persona solicitante)");
                        mbTodero.warn();
                        estadoValidacionInfoCliente = false;
                    } else if ("".equals(mBCliente.getMensajeConsultaCliente4()) || mBCliente.getMensajeConsultaCliente4() == null) {
                        mbTodero.setMens("Debe analizar la información ingresada");
                        mbTodero.warn();
                        estadoValidacionInfoCliente = false;
                    } else if ("N/A".equals(mBCliente.getMensajeConsultaCliente4())) {
                        mbTodero.setMens("Debe registrar el cliente");
                        mbTodero.warn();
                        estadoValidacionInfoCliente = false;
                    } else if ((("".equals(mBCliente.getTipoTarifaCli4()) || mBCliente.getTipoTarifaCli4() == null) && "Si_Cli4".equals(mBCliente.getOpcionRadioTarifasPactadosCli4()) && mBCliente.isClientFact4())
                            && (("".equals(mBCliente.getValorTarifaCli4()) || mBCliente.getValorTarifaCli4() == null) && "Si_Cli4".equals(mBCliente.getOpcionRadioTarifasPactadosCli4()) && mBCliente.isClientFact4())) {
                        mbTodero.setMens("Debe llenar los campos de la tarifa (Persona solicitante)");
                        mbTodero.warn();
                        estadoValidacionInfoCliente = false;
                    } else if (("".equals(mBCliente.getTipoTarifaCli4()) || mBCliente.getTipoTarifaCli4() == null) && "Si_Cli4".equals(mBCliente.getOpcionRadioTarifasPactadosCli4()) && mBCliente.isClientFact4()) {
                        mbTodero.setMens("Debe llenar el campo 'Tipo tarifa' (Persona solicitante)");
                        mbTodero.warn();
                        estadoValidacionInfoCliente = false;
                    } else if (("".equals(mBCliente.getValorTarifaCli4()) || mBCliente.getValorTarifaCli4() == null) && "Si_Cli4".equals(mBCliente.getOpcionRadioTarifasPactadosCli4()) && mBCliente.isClientFact4()) {
                        mbTodero.setMens("Debe llenar el campo 'Valor tarifa' (Persona solicitante)");
                        mbTodero.warn();
                        estadoValidacionInfoCliente = false;
                    } else {
                        estadoValidacionInfoCliente = true;
                        Dat = Cli.getConsultarAllClientesMostrarinfo(mBCliente.getNumeroDoccliente4());
                        if (Dat.next()) {
                            mBCliente.setTipoDoccliente4(Dat.getString("Nombre_Documento"));
                            mBCliente.setNumeroDoccliente4(Dat.getString("Numero_DocCliente"));
                            mBCliente.setNombreCliente4(Dat.getString("Nombre_Cliente"));
                            mBCliente.setCorreoCliente4(Dat.getString("Mail_Cliente"));
                            mBCliente.setDireccionCliente4(Dat.getString("Direccion_Cliente"));
                            mBCliente.setTelefonoCliente4(Dat.getString("Telefono_Cliente"));
                            mBCliente.setUbicacionCliente4(Dat.getString("Ubicacion"));
                        }
                        Conexion.Conexion.CloseCon();
                    }
                } //validar fila 5
                if (mBCliente.isEstadocajasAgregarF4() == true) {
                    if ("".equals(mBCliente.getNombreCliente5()) && "".equals(mBCliente.getNumeroDoccliente5())) {
                        mbTodero.setMens("Debe agregar la informacion del cliente");
                        mbTodero.warn();
                        estadoValidacionInfoCliente = false;
                    } else if ("".equals(mBCliente.getNumeroDoccliente5())) {
                        mbTodero.setMens("Debe llenar el campo 'N° Documento' (Persona solicitante)");
                        mbTodero.warn();
                        estadoValidacionInfoCliente = false;
                    } else if ("".equals(mBCliente.getNombreCliente5())) {
                        mbTodero.setMens("Debe llenar el campo 'Nombre' (Persona solicitante)");
                        mbTodero.warn();
                        estadoValidacionInfoCliente = false;
                    } else if ("".equals(mBCliente.getTelefonoCliente5())) {
                        mbTodero.setMens("Debe llenar el campo 'Telefono' (Persona solicitante)");
                        mbTodero.warn();
                        estadoValidacionInfoCliente = false;
                    } else if ("".equals(mBCliente.getMensajeConsultaCliente5()) || mBCliente.getMensajeConsultaCliente5() == null) {
                        mbTodero.setMens("Debe analizar la información ingresada");
                        mbTodero.warn();
                        estadoValidacionInfoCliente = false;
                    } else if ("N/A".equals(mBCliente.getMensajeConsultaCliente5())) {
                        mbTodero.setMens("Debe registrar el cliente");
                        mbTodero.warn();
                        estadoValidacionInfoCliente = false;
                    } else if ((("".equals(mBCliente.getTipoTarifaCli5()) || mBCliente.getTipoTarifaCli5() == null) && "Si_Cli5".equals(mBCliente.getOpcionRadioTarifasPactadosCli5()) && mBCliente.isClientFact5())
                            && (("".equals(mBCliente.getValorTarifaCli5()) || mBCliente.getValorTarifaCli5() == null) && "Si_Cli5".equals(mBCliente.getOpcionRadioTarifasPactadosCli5()) && mBCliente.isClientFact5())) {
                        mbTodero.setMens("Debe llenar los campos de la tarifa (Persona solicitante)");
                        mbTodero.warn();
                        estadoValidacionInfoCliente = false;
                    } else if (("".equals(mBCliente.getTipoTarifaCli5()) || mBCliente.getTipoTarifaCli5() == null) && "Si_Cli5".equals(mBCliente.getOpcionRadioTarifasPactadosCli5()) && mBCliente.isClientFact5()) {
                        mbTodero.setMens("Debe llenar el campo 'Tipo tarifa' (Persona solicitante)");
                        mbTodero.warn();
                        estadoValidacionInfoCliente = false;
                    } else if (("".equals(mBCliente.getValorTarifaCli5()) || mBCliente.getValorTarifaCli5() == null) && "Si_Cli5".equals(mBCliente.getOpcionRadioTarifasPactadosCli5()) && mBCliente.isClientFact5()) {
                        mbTodero.setMens("Debe llenar el campo 'Valor tarifa' (Persona solicitante)");
                        mbTodero.warn();
                        estadoValidacionInfoCliente = false;
                        Dat = Cli.getConsultarAllClientesMostrarinfo(mBCliente.getNumeroDoccliente5());
                        if (Dat.next()) {
                            mBCliente.setTipoDoccliente5(Dat.getString("Nombre_Documento"));
                            mBCliente.setNumeroDoccliente5(Dat.getString("Numero_DocCliente"));
                            mBCliente.setNombreCliente5(Dat.getString("Nombre_Cliente"));
                            mBCliente.setCorreoCliente5(Dat.getString("Mail_Cliente"));
                            mBCliente.setDireccionCliente5(Dat.getString("Direccion_Cliente"));
                            mBCliente.setTelefonoCliente5(Dat.getString("Telefono_Cliente"));
                            mBCliente.setUbicacionCliente5(Dat.getString("Ubicacion"));
                        }
                        Conexion.Conexion.CloseCon();
                    }
                }

            } //fin validar persona solicitante
            //validar entidades            
            if (mBEntidad.isEstPanelEntidad()) {

                if ("".equals(mBEntidad.getCodEntidad1()) && "".equals(mBEntidad.getCodSucursal1()) && "".equals(mBEntidad.getCodAsesor1())) {
                    mbTodero.setMens("Debe llenar los datos de la entidad correspondiente");
                    mbTodero.warn();
                    estadoValidacionInfoEntidad = false;
                } else if ("".equals(mBEntidad.getCodEntidad1())) {
                    mbTodero.setMens("Debe seleccionar información para el campo 'Entidad'");
                    mbTodero.warn();
                    estadoValidacionInfoEntidad = false;
                } else if ("".equals(mBEntidad.getCodSucursal1())) {
                    mbTodero.setMens("Debe seleccionar información para el campo 'Sucursal'");
                    mbTodero.warn();
                    estadoValidacionInfoEntidad = false;
                } else if ("".equals(mBEntidad.getCodAsesor1())) {
                    mbTodero.setMens("Debe seleccionar información para el campo 'Asesor'");
                    mbTodero.warn();
                    estadoValidacionInfoEntidad = false;
                } else if ((("".equals(mBEntidad.getTipoTarifaEnti1()) || mBEntidad.getTipoTarifaEnti1() == null) && "Si_Enti1".equals(mBEntidad.getOpcionRadioTarifasPactadosEnti1()) && mBEntidad.isEntidadFact1())
                        && (("".equals(mBEntidad.getValorTarifaEnti1()) || mBEntidad.getValorTarifaEnti1() == null) && "Si_Enti1".equals(mBEntidad.getOpcionRadioTarifasPactadosEnti1()) && mBEntidad.isEntidadFact1())) {
                    mbTodero.setMens("Debe llenar los campos de la tarifa (Entidad)");
                    mbTodero.warn();
                    estadoValidacionInfoEntidad = false;
                } else if (("".equals(mBEntidad.getTipoTarifaEnti1()) || mBEntidad.getTipoTarifaEnti1() == null) && "Si_Enti1".equals(mBEntidad.getOpcionRadioTarifasPactadosEnti1()) && mBEntidad.isEntidadFact1()) {
                    mbTodero.setMens("Debe llenar un tipo de tarifa (Entidad) ");
                    mbTodero.warn();
                    estadoValidacionInfoEntidad = false;
                } else if (("".equals(mBEntidad.getValorTarifaEnti1()) || mBEntidad.getValorTarifaEnti1() == null) && "Si_Enti1".equals(mBEntidad.getOpcionRadioTarifasPactadosEnti1()) && mBEntidad.isEntidadFact1()) {
                    mbTodero.setMens("Debe llenar un valor de tarifa (Entidad)");
                    mbTodero.warn();
                    estadoValidacionInfoEntidad = false;

                } else {
                    estadoValidacionInfoEntidad = true;
                    Enti.setCodEntidad(Integer.parseInt(mBEntidad.getCodEntidad1()));
                    Enti.setCodSucursal(Integer.parseInt(mBEntidad.getCodSucursal1()));
                    Enti.setCodAsesor(Integer.parseInt(mBEntidad.getCodAsesor1()));
                    Dat = Enti.ConsulInfEntAsesor();
                    if (Dat.next()) {
                        mBEntidad.setNombreEntidad1(Dat.getString("Nombre_Entidad"));
                        mBEntidad.setDocumentoEntidad1(Dat.getString("Documento_Entidad"));
                        mBEntidad.setNombreSucursal1(Dat.getString("Nombre_Oficina"));
                        mBEntidad.setTelefonoSucursal1(Dat.getString("Telefono_Oficina"));
                        mBEntidad.setNombreAsesor1(Dat.getString("Nombre_Asesor"));
                        mBEntidad.setCargoAsesor1(Dat.getString("Cargo_Asesor"));
                        mBEntidad.setMailAsesor1(Dat.getString("Mail_Asesor"));
                    }
                    Conexion.Conexion.CloseCon();
                }

                if (mBEntidad.isEstadoAgregarFilasEntidad2()) {
                    if ("".equals(mBEntidad.getCodEntidad2()) && "".equals(mBEntidad.getCodSucursal2()) && "".equals(mBEntidad.getCodAsesor2())) {
                        mbTodero.setMens("Debe llenar los datos de la entidad correspondiente");
                        mbTodero.warn();
                        estadoValidacionInfoEntidad = false;
                    } else if ("".equals(mBEntidad.getCodEntidad2())) {
                        mbTodero.setMens("Debe seleccionar información para el campo 'Entidad'");
                        mbTodero.warn();
                        estadoValidacionInfoEntidad = false;
                    } else if ("".equals(mBEntidad.getCodSucursal2())) {
                        mbTodero.setMens("Debe seleccionar información para el campo 'Sucursal'");
                        mbTodero.warn();
                        estadoValidacionInfoEntidad = false;
                    } else if ("".equals(mBEntidad.getCodAsesor2())) {
                        mbTodero.setMens("Debe seleccionar información para el campo 'Asesor'");
                        mbTodero.warn();
                        estadoValidacionInfoEntidad = false;
                    } else if ((("".equals(mBEntidad.getTipoTarifaEnti2()) || mBEntidad.getTipoTarifaEnti2() == null) && "Si_Enti2".equals(mBEntidad.getOpcionRadioTarifasPactadosEnti2()) && mBEntidad.isEntidadFact2())
                            && (("".equals(mBEntidad.getValorTarifaEnti2()) || mBEntidad.getValorTarifaEnti2() == null) && "Si_Enti2".equals(mBEntidad.getOpcionRadioTarifasPactadosEnti2()) && mBEntidad.isEntidadFact2())) {
                        mbTodero.setMens("Debe llenar los campos de la tarifa (Entidad)");
                        mbTodero.warn();
                        estadoValidacionInfoEntidad = false;
                    } else if (("".equals(mBEntidad.getTipoTarifaEnti2()) || mBEntidad.getTipoTarifaEnti2() == null) && "Si_Enti2".equals(mBEntidad.getOpcionRadioTarifasPactadosEnti2()) && mBEntidad.isEntidadFact2()) {
                        mbTodero.setMens("Debe llenar un tipo de tarifa (Entidad) ");
                        mbTodero.warn();
                        estadoValidacionInfoEntidad = false;
                    } else if (("".equals(mBEntidad.getValorTarifaEnti2()) || mBEntidad.getValorTarifaEnti2() == null) && "Si_Enti2".equals(mBEntidad.getOpcionRadioTarifasPactadosEnti2()) && mBEntidad.isEntidadFact2()) {
                        mbTodero.setMens("Debe llenar un valor de tarifa (Entidad) ");
                        mbTodero.warn();
                        estadoValidacionInfoEntidad = false;
                    } else {
                        estadoValidacionInfoEntidad = true;
                        Enti.setCodEntidad(Integer.parseInt(mBEntidad.getCodEntidad2()));
                        Enti.setCodSucursal(Integer.parseInt(mBEntidad.getCodSucursal2()));
                        Enti.setCodAsesor(Integer.parseInt(mBEntidad.getCodAsesor2()));
                        Dat = Enti.ConsulInfEntAsesor();
                        if (Dat.next()) {
                            mBEntidad.setNombreEntidad2(Dat.getString("Nombre_Entidad"));
                            mBEntidad.setDocumentoEntidad2(Dat.getString("Documento_Entidad"));
                            mBEntidad.setNombreSucursal2(Dat.getString("Nombre_Oficina"));
                            mBEntidad.setTelefonoSucursal2(Dat.getString("Telefono_Oficina"));
                            mBEntidad.setNombreAsesor2(Dat.getString("Nombre_Asesor"));
                            mBEntidad.setCargoAsesor2(Dat.getString("Cargo_Asesor"));
                            mBEntidad.setMailAsesor2(Dat.getString("Mail_Asesor"));
                        }
                        Conexion.Conexion.CloseCon();
                    }

                }
                if (mBEntidad.isEstadoAgregarFilasEntidad3()) {
                    if ("".equals(mBEntidad.getCodEntidad3()) || "".equals(mBEntidad.getCodSucursal3()) || "".equals(mBEntidad.getCodAsesor3())) {
                        mbTodero.setMens("Debe llenar los datos de la entidad correspondiente");
                        mbTodero.warn();
                        estadoValidacionInfoEntidad = false;
                    } else if ("".equals(mBEntidad.getCodEntidad3())) {
                        mbTodero.setMens("Debe seleccionar información para el campo 'Entidad'");
                        mbTodero.warn();
                        estadoValidacionInfoEntidad = false;
                    } else if ("".equals(mBEntidad.getCodSucursal3())) {
                        mbTodero.setMens("Debe seleccionar información para el campo 'Sucursal'");
                        mbTodero.warn();
                        estadoValidacionInfoEntidad = false;
                    } else if ("".equals(mBEntidad.getCodAsesor3())) {
                        mbTodero.setMens("Debe seleccionar información para el campo 'Asesor'");
                        mbTodero.warn();
                        estadoValidacionInfoEntidad = false;
                    } else if ((("".equals(mBEntidad.getTipoTarifaEnti3()) || mBEntidad.getTipoTarifaEnti3() == null) && "Si_Enti3".equals(mBEntidad.getOpcionRadioTarifasPactadosEnti3()) && mBEntidad.isEntidadFact3())
                            && (("".equals(mBEntidad.getValorTarifaEnti3()) || mBEntidad.getValorTarifaEnti3() == null) && "Si_Enti3".equals(mBEntidad.getOpcionRadioTarifasPactadosEnti3()) && mBEntidad.isEntidadFact3())) {
                        mbTodero.setMens("Debe llenar los campos de la tarifa (Entidad)");
                        mbTodero.warn();
                        estadoValidacionInfoEntidad = false;
                    } else if (("".equals(mBEntidad.getTipoTarifaEnti3()) || mBEntidad.getTipoTarifaEnti3() == null) && "Si_Enti3".equals(mBEntidad.getOpcionRadioTarifasPactadosEnti3()) && mBEntidad.isEntidadFact3()) {
                        mbTodero.setMens("Debe llenar un tipo de tarifa (Entidad) ");
                        mbTodero.warn();
                        estadoValidacionInfoEntidad = false;
                    } else if (("".equals(mBEntidad.getValorTarifaEnti3()) || mBEntidad.getValorTarifaEnti3() == null) && "Si_Enti3".equals(mBEntidad.getOpcionRadioTarifasPactadosEnti3()) && mBEntidad.isEntidadFact3()) {
                        mbTodero.setMens("Debe llenar un valor de tarifa (Entidad) ");
                        mbTodero.warn();
                        estadoValidacionInfoEntidad = false;
                    } else {
                        estadoValidacionInfoEntidad = true;
                        Enti.setCodEntidad(Integer.parseInt(mBEntidad.getCodEntidad3()));
                        Enti.setCodSucursal(Integer.parseInt(mBEntidad.getCodSucursal3()));
                        Enti.setCodAsesor(Integer.parseInt(mBEntidad.getCodAsesor3()));
                        Dat = Enti.ConsulInfEntAsesor();
                        if (Dat.next()) {
                            mBEntidad.setNombreEntidad3(Dat.getString("Nombre_Entidad"));
                            mBEntidad.setDocumentoEntidad3(Dat.getString("Documento_Entidad"));
                            mBEntidad.setNombreSucursal3(Dat.getString("Nombre_Oficina"));
                            mBEntidad.setTelefonoSucursal3(Dat.getString("Telefono_Oficina"));
                            mBEntidad.setNombreAsesor3(Dat.getString("Nombre_Asesor"));
                            mBEntidad.setCargoAsesor3(Dat.getString("Cargo_Asesor"));
                            mBEntidad.setMailAsesor3(Dat.getString("Mail_Asesor"));
                        }
                        Conexion.Conexion.CloseCon();
                    }

                }
                if (mBEntidad.isEstadoAgregarFilasEntidad4()) {
                    if ("".equals(mBEntidad.getCodEntidad4()) || "".equals(mBEntidad.getCodSucursal4()) || "".equals(mBEntidad.getCodAsesor4())) {
                        mbTodero.setMens("Debe llenar los datos de la entidad correspondiente");
                        mbTodero.warn();
                        estadoValidacionInfoEntidad = false;
                    } else if ("".equals(mBEntidad.getCodEntidad4())) {
                        mbTodero.setMens("Debe seleccionar información para el campo 'Entidad'");
                        mbTodero.warn();
                        estadoValidacionInfoEntidad = false;
                    } else if ("".equals(mBEntidad.getCodSucursal4())) {
                        mbTodero.setMens("Debe seleccionar información para el campo 'Sucursal'");
                        mbTodero.warn();
                        estadoValidacionInfoEntidad = false;
                    } else if ("".equals(mBEntidad.getCodAsesor4())) {
                        mbTodero.setMens("Debe seleccionar información para el campo 'Asesor'");
                        mbTodero.warn();
                        estadoValidacionInfoEntidad = false;
                    } else if ((("".equals(mBEntidad.getTipoTarifaEnti4()) || mBEntidad.getTipoTarifaEnti4() == null) && "Si_Enti4".equals(mBEntidad.getOpcionRadioTarifasPactadosEnti4()) && mBEntidad.isEntidadFact4())
                            && (("".equals(mBEntidad.getValorTarifaEnti4()) || mBEntidad.getValorTarifaEnti4() == null) && "Si_Enti4".equals(mBEntidad.getOpcionRadioTarifasPactadosEnti4()) && mBEntidad.isEntidadFact4())) {
                        mbTodero.setMens("Debe llenar los campos de la tarifa (Entidad)");
                        mbTodero.warn();
                        estadoValidacionInfoEntidad = false;
                    } else if (("".equals(mBEntidad.getTipoTarifaEnti4()) || mBEntidad.getTipoTarifaEnti4() == null) && "Si_Enti4".equals(mBEntidad.getOpcionRadioTarifasPactadosEnti4()) && mBEntidad.isEntidadFact4()) {
                        mbTodero.setMens("Debe llenar un tipo de tarifa (Entidad) ");
                        mbTodero.warn();
                        estadoValidacionInfoEntidad = false;
                    } else if (("".equals(mBEntidad.getValorTarifaEnti4()) || mBEntidad.getValorTarifaEnti4() == null) && "Si_Enti4".equals(mBEntidad.getOpcionRadioTarifasPactadosEnti4()) && mBEntidad.isEntidadFact4()) {
                        mbTodero.setMens("Debe llenar un valor de tarifa (Entidad) ");
                        mbTodero.warn();
                        estadoValidacionInfoEntidad = false;
                    } else {
                        estadoValidacionInfoEntidad = true;
                        Enti.setCodEntidad(Integer.parseInt(mBEntidad.getCodEntidad4()));
                        Enti.setCodSucursal(Integer.parseInt(mBEntidad.getCodSucursal4()));
                        Enti.setCodAsesor(Integer.parseInt(mBEntidad.getCodAsesor4()));
                        Dat = Enti.ConsulInfEntAsesor();
                        if (Dat.next()) {
                            mBEntidad.setNombreEntidad4(Dat.getString("Nombre_Entidad"));
                            mBEntidad.setDocumentoEntidad4(Dat.getString("Documento_Entidad"));
                            mBEntidad.setNombreSucursal4(Dat.getString("Nombre_Oficina"));
                            mBEntidad.setTelefonoSucursal4(Dat.getString("Telefono_Oficina"));
                            mBEntidad.setNombreAsesor4(Dat.getString("Nombre_Asesor"));
                            mBEntidad.setCargoAsesor4(Dat.getString("Cargo_Asesor"));
                            mBEntidad.setMailAsesor4(Dat.getString("Mail_Asesor"));
                        }
                        Conexion.Conexion.CloseCon();
                    }
                }
                if (mBEntidad.isEstadoAgregarFilasEntidad5()) {
                    if ("".equals(mBEntidad.getCodEntidad5()) || "".equals(mBEntidad.getCodSucursal5()) || "".equals(mBEntidad.getCodAsesor5())) {
                        mbTodero.setMens("Debe llenar los datos de la entidad correspondiente");
                        mbTodero.warn();
                        estadoValidacionInfoEntidad = false;
                    } else if ("".equals(mBEntidad.getCodEntidad5())) {
                        mbTodero.setMens("Debe seleccionar información para el campo 'Entidad'");
                        mbTodero.warn();
                        estadoValidacionInfoEntidad = false;
                    } else if ("".equals(mBEntidad.getCodSucursal5())) {
                        mbTodero.setMens("Debe seleccionar información para el campo 'Sucursal'");
                        mbTodero.warn();
                        estadoValidacionInfoEntidad = false;
                    } else if ("".equals(mBEntidad.getCodAsesor5())) {
                        mbTodero.setMens("Debe seleccionar información para el campo 'Asesor'");
                        mbTodero.warn();
                        estadoValidacionInfoEntidad = false;
                    } else if ((("".equals(mBEntidad.getTipoTarifaEnti5()) || mBEntidad.getTipoTarifaEnti5() == null) && "Si_Enti5".equals(mBEntidad.getOpcionRadioTarifasPactadosEnti5()) && mBEntidad.isEntidadFact5())
                            && (("".equals(mBEntidad.getValorTarifaEnti5()) || mBEntidad.getValorTarifaEnti5() == null) && "Si_Enti5".equals(mBEntidad.getOpcionRadioTarifasPactadosEnti5()) && mBEntidad.isEntidadFact5())) {
                        mbTodero.setMens("Debe llenar los campos de la tarifa (Entidad)");
                        mbTodero.warn();
                        estadoValidacionInfoEntidad = false;
                    } else if (("".equals(mBEntidad.getTipoTarifaEnti5()) || mBEntidad.getTipoTarifaEnti5() == null) && "Si_Enti5".equals(mBEntidad.getOpcionRadioTarifasPactadosEnti5()) && mBEntidad.isEntidadFact5()) {
                        mbTodero.setMens("Debe llenar un tipo de tarifa (Entidad) ");
                        mbTodero.warn();
                        estadoValidacionInfoEntidad = false;
                    } else if (("".equals(mBEntidad.getValorTarifaEnti5()) || mBEntidad.getValorTarifaEnti5() == null) && "Si_Enti5".equals(mBEntidad.getOpcionRadioTarifasPactadosEnti5()) && mBEntidad.isEntidadFact5()) {
                        mbTodero.setMens("Debe llenar un valor de tarifa (Entidad) ");
                        mbTodero.warn();
                        estadoValidacionInfoEntidad = false;
                    } else {
                        estadoValidacionInfoEntidad = true;
                        Enti.setCodEntidad(Integer.parseInt(mBEntidad.getCodEntidad5()));
                        Enti.setCodSucursal(Integer.parseInt(mBEntidad.getCodSucursal5()));
                        Enti.setCodAsesor(Integer.parseInt(mBEntidad.getCodAsesor5()));
                        Dat = Enti.ConsulInfEntAsesor();
                        if (Dat.next()) {
                            mBEntidad.setNombreEntidad(Dat.getString("Nombre_Entidad"));
                            mBEntidad.setDocumentoEntidad5(Dat.getString("Documento_Entidad"));
                            mBEntidad.setNombreSucursal5(Dat.getString("Nombre_Oficina"));
                            mBEntidad.setTelefonoSucursal5(Dat.getString("Telefono_Oficina"));
                            mBEntidad.setNombreAsesor5(Dat.getString("Nombre_Asesor"));
                            mBEntidad.setCargoAsesor5(Dat.getString("Cargo_Asesor"));
                            mBEntidad.setMailAsesor5(Dat.getString("Mail_Asesor"));
                        }
                        Conexion.Conexion.CloseCon();
                    }
                }

            } //fin validar entidades

            //validar peritos
            if (mBPerito.getListPeritAvaluadoresSeleccionadosForm().isEmpty()) {
                mbTodero.setMens("Debe seleccionar a menos un perito ");
                mbTodero.warn();
                estadoValidacionInfoPerito = false;
            } else if (mBPerito.getListPeritAvaluadoresSeleccionadosForm().size() > 0 && mBPerito.getListPeritAvaluadoresSeleccionadosForm().size() < 6) {
                for (int i = 0; i <= mBPerito.getListPeritAvaluadoresSeleccionadosForm().size() - 1; i++) {

                    if (i == 0) {
                        if ((("".equals(mBPerito.getTipoTarifaPeri1()) || mBPerito.getTipoTarifaPeri1() == null) && "Si_Perito1".equals(mBPerito.getOpcionRadioTarifasPactadosPer1()))
                                && (("".equals(mBPerito.getValorTarifaPer1()) || mBPerito.getValorTarifaPer1() == null) && "Si_Perito1".equals(mBPerito.getOpcionRadioTarifasPactadosPer1()))) {
                            mbTodero.setMens("Debe llenar los campos de la tarifa (Perito)");
                            mbTodero.warn();
                            estadoValidacionInfoPerito = false;
                        } else if (("".equals(mBPerito.getTipoTarifaPeri1()) || mBPerito.getTipoTarifaPeri1() == null) && "Si_Perito1".equals(mBPerito.getOpcionRadioTarifasPactadosPer1())) {
                            mbTodero.setMens("Debe elegir un tipo de tarifa (Perito) ");
                            mbTodero.warn();
                            estadoValidacionInfoPerito = false;
                        } else if (("".equals(mBPerito.getValorTarifaPer1()) || mBPerito.getValorTarifaPer1() == null) && "Si_Perito1".equals(mBPerito.getOpcionRadioTarifasPactadosPer1())) {
                            mbTodero.setMens("Debe elegir un valor de tarifa (Perito) ");
                            mbTodero.warn();
                            estadoValidacionInfoPerito = false;
                        } else {
                            estadoValidacionInfoPerito = true;
                        }

                    }

                    if (i == 1) {
                        if ((("".equals(mBPerito.getTipoTarifaPeri2()) || mBPerito.getTipoTarifaPeri2() == null) && "Si_Perito2".equals(mBPerito.getOpcionRadioTarifasPactadosPer2()))
                                && (("".equals(mBPerito.getValorTarifaPer2()) || mBPerito.getValorTarifaPer2() == null) && "Si_Perito2".equals(mBPerito.getOpcionRadioTarifasPactadosPer2()))) {
                            mbTodero.setMens("Debe llenar los campos de la tarifa (Perito)");
                            mbTodero.warn();
                            estadoValidacionInfoPerito = false;
                        } else if (("".equals(mBPerito.getTipoTarifaPeri2()) || mBPerito.getTipoTarifaPeri2() == null) && "Si_Perito2".equals(mBPerito.getOpcionRadioTarifasPactadosPer2())) {
                            mbTodero.setMens("Debe elegir un tipo de tarifa (Perito) ");
                            mbTodero.warn();
                            estadoValidacionInfoPerito = false;
                        } else if (("".equals(mBPerito.getValorTarifaPer2()) || mBPerito.getValorTarifaPer2() == null) && "Si_Perito2".equals(mBPerito.getOpcionRadioTarifasPactadosPer2())) {
                            mbTodero.setMens("Debe elegir un valor de tarifa (Perito) ");
                            mbTodero.warn();
                            estadoValidacionInfoPerito = false;
                        } else {
                            estadoValidacionInfoPerito = true;
                        }

                    }

                    if (i == 2) {
                        if ((("".equals(mBPerito.getTipoTarifaPeri3()) || mBPerito.getTipoTarifaPeri3() == null) && "Si_Perito3".equals(mBPerito.getOpcionRadioTarifasPactadosPer3()))
                                && (("".equals(mBPerito.getValorTarifaPer3()) || mBPerito.getValorTarifaPer3() == null) && "Si_Perito3".equals(mBPerito.getOpcionRadioTarifasPactadosPer3()))) {
                            mbTodero.setMens("Debe llenar los campos de la tarifa (Perito)");
                            mbTodero.warn();
                            estadoValidacionInfoPerito = false;
                        } else if (("".equals(mBPerito.getTipoTarifaPeri3()) || mBPerito.getTipoTarifaPeri3() == null) && "Si_Perito3".equals(mBPerito.getOpcionRadioTarifasPactadosPer3())) {
                            mbTodero.setMens("Debe elegir un tipo de tarifa (Perito) ");
                            mbTodero.warn();
                            estadoValidacionInfoPerito = false;
                        } else if (("".equals(mBPerito.getValorTarifaPer3()) || mBPerito.getValorTarifaPer3() == null) && "Si_Perito3".equals(mBPerito.getOpcionRadioTarifasPactadosPer3())) {
                            mbTodero.setMens("Debe elegir un valor de tarifa (Perito) ");
                            mbTodero.warn();
                            estadoValidacionInfoPerito = false;
                        } else {
                            estadoValidacionInfoPerito = true;
                        }

                    }

                    if (i == 3) {
                        if ((("".equals(mBPerito.getTipoTarifaPeri4()) || mBPerito.getTipoTarifaPeri4() == null) && "Si_Perito4".equals(mBPerito.getOpcionRadioTarifasPactadosPer4()))
                                && (("".equals(mBPerito.getValorTarifaPer4()) || mBPerito.getValorTarifaPer4() == null) && "Si_Perito4".equals(mBPerito.getOpcionRadioTarifasPactadosPer4()))) {
                            mbTodero.setMens("Debe llenar los campos de la tarifa (Perito)");
                            mbTodero.warn();
                            estadoValidacionInfoPerito = false;
                        } else if (("".equals(mBPerito.getTipoTarifaPeri4()) || mBPerito.getTipoTarifaPeri4() == null) && "Si_Perito4".equals(mBPerito.getOpcionRadioTarifasPactadosPer4())) {
                            mbTodero.setMens("Debe elegir un tipo de tarifa (Perito) ");
                            mbTodero.warn();
                            estadoValidacionInfoPerito = false;
                        } else if (("".equals(mBPerito.getValorTarifaPer4()) || mBPerito.getValorTarifaPer4() == null) && "Si_Perito4".equals(mBPerito.getOpcionRadioTarifasPactadosPer4())) {
                            mbTodero.setMens("Debe elegir un valor de tarifa (Perito) ");
                            mbTodero.warn();
                            estadoValidacionInfoPerito = false;
                        } else {
                            estadoValidacionInfoPerito = true;
                        }

                    }

                    if (i == 4) {

                        if ((("".equals(mBPerito.getTipoTarifaPeri5()) || mBPerito.getTipoTarifaPeri5() == null) && "Si_Perito5".equals(mBPerito.getOpcionRadioTarifasPactadosPer5()))
                                && (("".equals(mBPerito.getValorTarifaPer5()) || mBPerito.getValorTarifaPer5() == null) && "Si_Perito5".equals(mBPerito.getOpcionRadioTarifasPactadosPer5()))) {
                            mbTodero.setMens("Debe llenar los campos de la tarifa (Perito)");
                            mbTodero.warn();
                            estadoValidacionInfoPerito = false;
                        } else if (("".equals(mBPerito.getTipoTarifaPeri5()) || mBPerito.getTipoTarifaPeri5() == null) && "Si_Perito5".equals(mBPerito.getOpcionRadioTarifasPactadosPer5())) {
                            mbTodero.setMens("Debe elegir un tipo de tarifa (Perito) ");
                            mbTodero.warn();
                            estadoValidacionInfoPerito = false;
                        } else if (("".equals(mBPerito.getValorTarifaPer5()) || mBPerito.getValorTarifaPer5() == null) && "Si_Perito5".equals(mBPerito.getOpcionRadioTarifasPactadosPer5())) {
                            mbTodero.setMens("Debe elegir un valor de tarifa (Perito) ");
                            mbTodero.warn();
                            estadoValidacionInfoPerito = false;
                        } else {
                            estadoValidacionInfoPerito = true;
                        }

                    }

                }
            }

            if (codEmp == 0) {
                mbTodero.setMens("Debe seleccionar un responsable ");
                mbTodero.warn();
                estadoValidacionInfoResponsa = false;
            } else {
                estadoValidacionInfoResponsa = true;
            }
            if ("".equals(opcionRadioContacto)) {
                mbTodero.setMens("Debe seleccionar un contacto");
                mbTodero.warn();
                estadoValidacionInfoContacto = false;

            } else if ("".equals(nombreContacto)) {
                mbTodero.setMens("Debe llenar el nombre del contacto ");
                mbTodero.warn();
                estadoValidacionInfoContacto = false;
            } else if ("".equals(telefonoContacto) && "".equals(celularContacto)) {
                mbTodero.setMens("Debe llenar algun numero del contacto");
                mbTodero.warn();
                estadoValidacionInfoContacto = false;
            } else if (("".equals(codDeptoContacto) || codDeptoContacto == null) && ("Otro".equals(opcionRadioContacto))) {
                mbTodero.setMens("Debe seleccionar un departamento de ubicación de la persona de contacto");
                mbTodero.warn();
                estadoValidacionInfoContacto = false;
            } else if (("".equals(codCiudContacto) || codCiudContacto == null) && ("Otro".equals(opcionRadioContacto))) {
                mbTodero.setMens("Debe seleccionar una ciudad de ubicación de la persona de contacto");
                mbTodero.warn();
                estadoValidacionInfoContacto = false;
            } else {
                estadoValidacionInfoContacto = true;
            }

            /*
             if (fechaCita == null && HoraCita == null) {
             mbTodero.setMens("Debe asignar una fecha completa de cita");
             mbTodero.warn();
             estadoValidacionInfoCita = false;
             } else if (fechaCita == null) {
             mbTodero.setMens("Debe asignar una fecha de cita");
             mbTodero.warn();
             estadoValidacionInfoCita = false;
             } else if (HoraCita == null) {
             mbTodero.setMens("Debe asignar una hora de cita");
             mbTodero.warn();
             estadoValidacionInfoCita = false;
             } else {
             estadoValidacionInfoCita = true;
             }*/
            if (estadoValidacionInfoGeneral == true && estadoValidacionInfoBien == true && (estadoValidacionInfoCliente == true || estadoValidacionInfoEntidad == true)
                    && estadoValidacionInfoPerito == true && estadoValidacionInfoResponsa == true && estadoValidacionInfoContacto == true /*&&estadoValidacionInfoCita == true*/) {

                estadoFormVerInformacion = "Radicar";
                RequestContext.getCurrentInstance().execute("PF('DialogVerifiInfoRadic').show()");

                fechaSoli = Format.format(fechaSolicRadic);

                if (fechaCita == null) {
                    fechaCitaAsig = "";
                    horaCitaAsig = "";
                } else {
                    fechaCitaAsig = Format.format(fechaCita);
                    horaCitaAsig = FormatHora.format(HoraCita);
                }

                DatoProdEnt = Adm.getProducNombreEntidad(Integer.parseInt(codProEnt));
                if (DatoProdEnt.next()) {
                    nombreProEnt = DatoProdEnt.getString("Nombre_ProEnt");
                }
                Conexion.Conexion.CloseCon();
                DatoTipoProdEnt = Adm.getNombreTipoProducEntidad(Integer.parseInt(codTipProEnt));
                if (DatoTipoProdEnt.next()) {
                    nombreTipProEnt = DatoTipoProdEnt.getString("Nombre_TipProEnt");
                }
                Conexion.Conexion.CloseCon();
                if (requierUbicacion == true) {
                    DatoDeptoSolic = Ubi.ConsulNombreDepto(Integer.parseInt(mBAvaluo.getCodDeptoSol()));
                    if (DatoDeptoSolic.next()) {
                        mBAvaluo.setNombreDeptoSol(DatoDeptoSolic.getString("Nombre_Departamento"));
                    }
                    Conexion.Conexion.CloseCon();
                    DatoCiudadSolic = Ubi.ConsulNombreCiudad(Integer.parseInt(mBAvaluo.getCodCiudadSol()));
                    if (DatoCiudadSolic.next()) {
                        mBAvaluo.setNombreCiudadSol(DatoCiudadSolic.getString("Nombre_Ciudad"));
                    }
                    Conexion.Conexion.CloseCon();
                }

                Dat = Emp.getSeguimientoEmpNombre(codEmp);

                if (Dat.next()) {
                    nombreEmp = Dat.getString("NombreEmp");
                }
                Conexion.Conexion.CloseCon();
                if ("Otro".equals(opcionRadioContacto)) {

                    DatDepContacto = Ubi.ConsulNombreDepto(Integer.parseInt(codDeptoContacto));

                    if (DatDepContacto.next()) {
                        DeptoContacto = DatDepContacto.getString("Nombre_Departamento");
                    }
                    Conexion.Conexion.CloseCon();
                    DatCiudadContacto = Ubi.ConsulNombreCiudad(Integer.parseInt(codCiudContacto));

                    if (DatCiudadContacto.next()) {
                        CiudContacto = DatCiudadContacto.getString("Nombre_Ciudad");
                    }
                    Conexion.Conexion.CloseCon();
                }

                estadoValidacionInfoBien = false;
                estadoValidacionInfoGeneral = false;
                estadoValidacionInfoCliente = false;
                estadoValidacionInfoEntidad = false;
                estadoValidacionInfoPerito = false;
                estadoValidacionInfoResponsa = false;
                estadoValidacionInfoContacto = false;
                estadoValidacionInfoCita = false;

            } //end if validaciones (correctamente)

        } catch (SQLException e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".verificarInfoRadic()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    public void capturarNumPreRadica() {
        try {
            if (Radi == null) {
                mbTodero.setMens("Seleccione un numero de radicación");
                mbTodero.error();
            } else {
                DatObser = Rad.consultaObserRadicacion(String.valueOf(Radi.getCodAvaluo()), Radi.getCodSeguimiento());
                ListObserRadicados = new ArrayList<>();

                while (DatObser.next()) {
                    LogRadicacion RadObs = new LogRadicacion();
                    RadObs.setObservacionRadic(DatObser.getString("Obser"));
                    RadObs.setFechaObservacionRadic(DatObser.getString("Fecha"));
                    RadObs.setAnalistaObservacionRadic(DatObser.getString("empleado"));
                    ListObserRadicados.add(RadObs);
                }
                Conexion.Conexion.CloseCon();
                RequestContext.getCurrentInstance().execute("PF('DlgInfRadicacion').show()");
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".capturarNumPreRadica()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    public void RolGestor(int Id) {
        try {
            Dat = Perm.consulRolEmpleado(Id, "Gestor");
            this.estadoActivacionGestor = Dat.next();
            Conexion.Conexion.CloseCon();
        } catch (SQLException e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".RolGestor()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    public void abrirDialogModificarAvaluador() {
        try {
            mbTodero.resetTable("FormRadicacion:TablaAvaluadoresSeleccion");
            mBPerito.setListPeritAvaluadoresSeleccionadosTabla(new ArrayList<LogPerito>());
            Per.setOp(2);
            mBPerito.setListPerit(new ArrayList<LogPerito>());
            mBPerito.setListPerit(Per.ConsulAllPerito(Integer.parseInt(mBAvaluo.getCodCiudadAvalRadic())));
            RequestContext.getCurrentInstance().execute("PF('DlgConsultaPeritosSelec').show()");
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".abrirDialogModificarAvaluador()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    public void validarFormTabs(String form_num) {
        try {
            int paso = 0;

            paso = 0;
            if ("AdelanteTb0".equals(form_num)) {
                //RequestContext.getCurrentInstance().execute("PF('tabRadicacion').disable(0)");
                RequestContext.getCurrentInstance().execute("PF('tabRadicacion').enable(1)");
                RequestContext.getCurrentInstance().execute("PF('tabRadicacion').select(1)");
                //   estadoTituloRadicacion = true;
            }
            if ("AtrasTb1".equals(form_num)) {
                RequestContext.getCurrentInstance().execute("PF('tabRadicacion').disable(1)");
                RequestContext.getCurrentInstance().execute("PF('tabRadicacion').enable(0)");
                RequestContext.getCurrentInstance().execute("PF('tabRadicacion').select(0)");
            }
            if ("AdelanteTb1".equals(form_num)) {

                //Validaciones radicacion, informacion general
                if (fechaSolicRadic == null) {
                    mbTodero.setMens("Debe seleccionar información del campo 'Fecha Solicitud'");
                    mbTodero.warn();
                    estadoValidacionInfoGeneral = false;
                } else if (HoraSol == null) {
                    mbTodero.setMens("Debe seleccionar información del campo 'Hora Solicitud'");
                    mbTodero.warn();
                    estadoValidacionInfoGeneral = false;
                } else if ("".equals(numeroHojas) || numeroHojas == null) {
                    mbTodero.setMens("Debe llenar del campo 'Numero hojas'");
                    mbTodero.warn();
                    estadoValidacionInfoGeneral = false;
                } else if ("".equals(solicitante) || solicitante == null) {
                    mbTodero.setMens("Debe seleccionar información del campo 'Remitido por'");
                    mbTodero.warn();
                    estadoValidacionInfoGeneral = false;
                } else if ("".equals(envioInformacion) || envioInformacion == null) {
                    mbTodero.setMens("Debe seleccionar información del campo 'Enviar a'");
                    mbTodero.warn();
                    estadoValidacionInfoGeneral = false;
                } else if ("".equals(codProEnt) || codProEnt == null) {
                    mbTodero.setMens("Debe seleccionar información del campo 'Producto Entidad'");
                    mbTodero.warn();
                    estadoValidacionInfoGeneral = false;
                } else if ("".equals(codTipProEnt) || codTipProEnt == null) {
                    mbTodero.setMens("Debe seleccionar información del campo 'Tipo Producto Entidad'");
                    mbTodero.warn();
                    estadoValidacionInfoGeneral = false;
                } else if (requierUbicacion == true && ("".equals(mBAvaluo.getCodDeptoSol()) || mBAvaluo.getCodDeptoSol() == null)) {
                    mbTodero.setMens("Debe seleccionar información del campo 'Departamento (Solicitud)'");
                    mbTodero.warn();
                    estadoValidacionInfoGeneral = false;
                } else if (requierUbicacion == true && ("".equals(mBAvaluo.getCodCiudadSol()) || mBAvaluo.getCodCiudadSol() == null)) {
                    mbTodero.setMens("Debe seleccionar información del campo 'Ciudad (Solicitud)'");
                    mbTodero.warn();
                    estadoValidacionInfoGeneral = false;
                } else {
                    estadoValidacionInfoGeneral = true;

                }

                //Validacion de cliente
                if (mBCliente.isEstadoPanelClienteGeneral() == false && mBEntidad.isEstPanelEntidad() == false) {
                    mbTodero.setMens("Debe haber informacion en cliente o entidad");
                    mbTodero.warn();
                    estadoValidacionInfoClienteNo = false;
                    estadoValidacionInfoEntidadNo = false;
                }
                if (mBCliente.isEstadoPanelClienteGeneral() == true) {
                    estadoValidacionInfoClienteNo = true;
                }
                if (mBEntidad.isEstPanelEntidad() == true) {
                    estadoValidacionInfoEntidadNo = true;
                }
                if ((mBCliente.isClientFact1() == true || mBCliente.isClientFact2() == true || mBCliente.isClientFact3() == true || mBCliente.isClientFact4() == true || mBCliente.isClientFact5() == true)
                        && (mBEntidad.isEntidadFact1() == true || mBEntidad.isEntidadFact2() == true || mBEntidad.isEntidadFact3() == true || mBEntidad.isEntidadFact4() == true || mBEntidad.isEntidadFact5() == true)) {
                    mbTodero.setMens("No puede haber persona a facturar en cliente y entidad");
                    estadoValidacionInfoCliente = false;
                    estadoValidacionInfoEntidad = false;
                    mbTodero.warn();
                    return;
                } //validar persona solicitante            
                if (mBCliente.isEstadoPanelClienteGeneral() == true) { //validar si el panel es clientes es visible
                    //validar la fila 1
                    if ("".equals(mBCliente.getNombreCliente1()) && "".equals(mBCliente.getNumeroDoccliente1())) {
                        mbTodero.setMens("Debe agregar la informacion del cliente");
                        mbTodero.warn();
                        estadoValidacionInfoCliente = false;
                    } else if ("".equals(mBCliente.getNumeroDoccliente1())) {
                        mbTodero.setMens("Debe llenar el campo 'N° Documento' (Persona solicitante)");
                        mbTodero.warn();
                        estadoValidacionInfoCliente = false;
                    } else if ("".equals(mBCliente.getNombreCliente1())) {
                        mbTodero.setMens("Debe llenar el campo 'Nombres' (Persona solicitante)");
                        mbTodero.warn();
                        estadoValidacionInfoCliente = false;
                    } else if ("".equals(mBCliente.getTelefonoCliente1())) {
                        mbTodero.setMens("Debe llenar el campo 'Telefono' (Persona solicitante)");
                        mbTodero.warn();
                        estadoValidacionInfoCliente = false;
                    } else if ("".equals(mBCliente.getMensajeConsultaCliente1()) || mBCliente.getMensajeConsultaCliente1() == null) {
                        mbTodero.setMens("Debe analizar la información ingresada");
                        mbTodero.warn();
                        estadoValidacionInfoCliente = false;
                    } else if ("N/A".equals(mBCliente.getMensajeConsultaCliente1())) {
                        mbTodero.setMens("Debe registrar el cliente");
                        mbTodero.warn();
                        estadoValidacionInfoCliente = false;
                    } else if (("".equals(mBCliente.getOpcionRadioTarifasPactadosCli1()) && mBCliente.isClientFact1())) {
                        mbTodero.setMens("Debe elegir una opcion para facturar al cliente (SI/No) (Persona solicitante)");
                        mbTodero.warn();
                        estadoValidacionInfoCliente = false;
                    } else if ((("".equals(mBCliente.getTipoTarifaCli1()) || mBCliente.getTipoTarifaCli1() == null) && "Si_Cli1".equals(mBCliente.getOpcionRadioTarifasPactadosCli1()) && mBCliente.isClientFact1())
                            && (("".equals(mBCliente.getValorTarifaCli1()) || mBCliente.getValorTarifaCli1() == null) && "Si_Cli1".equals(mBCliente.getOpcionRadioTarifasPactadosCli1()) && mBCliente.isClientFact1())) {
                        mbTodero.setMens("Debe llenar los campos de la tarifa (Persona solicitante)");
                        mbTodero.warn();
                        estadoValidacionInfoCliente = false;
                    } else if (("".equals(mBCliente.getTipoTarifaCli1()) || mBCliente.getTipoTarifaCli1() == null) && "Si_Cli1".equals(mBCliente.getOpcionRadioTarifasPactadosCli1()) && mBCliente.isClientFact1()) {
                        mbTodero.setMens("Debe llenar el campo 'Tipo tarifa' (Persona solicitante)");
                        mbTodero.warn();
                        estadoValidacionInfoCliente = false;
                    } else if (("".equals(mBCliente.getValorTarifaCli1()) || mBCliente.getValorTarifaCli1() == null) && "Si_Cli1".equals(mBCliente.getOpcionRadioTarifasPactadosCli1()) && mBCliente.isClientFact1()) {
                        mbTodero.setMens("Debe llenar el campo 'Valor tarifa' (Persona solicitante)");
                        mbTodero.warn();
                        estadoValidacionInfoCliente = false;

                    } else {
                        estadoValidacionInfoCliente = true;
                    }
                    //validar fila 2
                    if (mBCliente.isEstadocajasAgregarF1() == true && estadoValidacionInfoCliente) {
                        if ("".equals(mBCliente.getNombreCliente2()) && "".equals(mBCliente.getNumeroDoccliente2())) {
                            mbTodero.setMens("Debe agregar la informacion del cliente");
                            mbTodero.warn();
                            estadoValidacionInfoCliente = false;
                        } else if ("".equals(mBCliente.getNumeroDoccliente2())) {
                            mbTodero.setMens("Debe llenar el campo 'N° Documento' (Persona solicitante)");
                            mbTodero.warn();
                            estadoValidacionInfoCliente = false;
                        } else if ("".equals(mBCliente.getNombreCliente2())) {
                            mbTodero.setMens("Debe llenar el campo 'Nombres' (Persona solicitante)");
                            mbTodero.warn();
                            estadoValidacionInfoCliente = false;
                        } else if ("".equals(mBCliente.getTelefonoCliente2())) {
                            mbTodero.setMens("Debe llenar el campo 'Telefono' (Persona solicitante)");
                            mbTodero.warn();
                            estadoValidacionInfoCliente = false;
                        } else if ("".equals(mBCliente.getMensajeConsultaCliente2()) || mBCliente.getMensajeConsultaCliente2() == null) {
                            mbTodero.setMens("Debe analizar la información ingresada");
                            mbTodero.warn();
                            estadoValidacionInfoCliente = false;
                        } else if ("N/A".equals(mBCliente.getMensajeConsultaCliente2())) {
                            mbTodero.setMens("Debe registrar el cliente");
                            mbTodero.warn();
                            estadoValidacionInfoCliente = false;
                        } else if (("".equals(mBCliente.getOpcionRadioTarifasPactadosCli2()) && mBCliente.isClientFact2())) {
                            mbTodero.setMens("Debe elegir una opcion para facturar al cliente (SI/No) (Persona solicitante)");
                            mbTodero.warn();
                            estadoValidacionInfoCliente = false;
                        } else if (mBCliente.isClientFact1() && mBCliente.isClientFact2() && paso == 0 && !mBCliente.getOpcionRadioTarifasPactadosCli2().substring(0, 6).equalsIgnoreCase(mBCliente.getOpcionRadioTarifasPactadosCli1().substring(0, 6))) {
                            mbTodero.setMens("Todos los clientes deben tener el mismo tipo de tarifa ");
                            mbTodero.warn();
                            paso++;
                            estadoValidacionInfoCliente = false;
                        } else if ((("".equals(mBCliente.getTipoTarifaCli2()) || mBCliente.getTipoTarifaCli2() == null) && "Si_Cli2".equals(mBCliente.getOpcionRadioTarifasPactadosCli2()) && mBCliente.isClientFact2())
                                && (("".equals(mBCliente.getValorTarifaCli2()) || mBCliente.getValorTarifaCli2() == null) && "Si_Cli2".equals(mBCliente.getOpcionRadioTarifasPactadosCli2()) && mBCliente.isClientFact2())) {
                            mbTodero.setMens("Debe llenar los campos de la tarifa (Persona solicitante)");
                            mbTodero.warn();
                            estadoValidacionInfoCliente = false;
                        } else if (("".equals(mBCliente.getTipoTarifaCli2()) || mBCliente.getTipoTarifaCli2() == null) && "Si_Cli2".equals(mBCliente.getOpcionRadioTarifasPactadosCli2()) && mBCliente.isClientFact2()) {
                            mbTodero.setMens("Debe llenar el campo 'Tipo tarifa' (Persona solicitante)");
                            mbTodero.warn();
                            estadoValidacionInfoCliente = false;
                        } else if (("".equals(mBCliente.getValorTarifaCli2()) || mBCliente.getValorTarifaCli2() == null) && "Si_Cli2".equals(mBCliente.getOpcionRadioTarifasPactadosCli2()) && mBCliente.isClientFact2()) {
                            mbTodero.setMens("Debe llenar el campo 'Valor tarifa' (Persona solicitante)");
                            mbTodero.warn();
                            estadoValidacionInfoCliente = false;
                        } else {
                            estadoValidacionInfoCliente = true;
                        }
                    } //validar fila 3
                    if (mBCliente.isEstadocajasAgregarF2() == true && estadoValidacionInfoCliente) {
                        if ("".equals(mBCliente.getNombreCliente3()) && "".equals(mBCliente.getNumeroDoccliente3())) {
                            mbTodero.setMens("Debe agregar la informacion del cliente");
                            mbTodero.warn();
                            estadoValidacionInfoCliente = false;
                        } else if ("".equals(mBCliente.getNumeroDoccliente3())) {
                            mbTodero.setMens("Debe llenar el campo 'N° Documento' (Persona solicitante)");
                            mbTodero.warn();
                            estadoValidacionInfoCliente = false;
                        } else if ("".equals(mBCliente.getNombreCliente3())) {
                            mbTodero.setMens("Debe llenar el campo 'Nombres' (Persona solicitante)");
                            mbTodero.warn();
                            estadoValidacionInfoCliente = false;
                        } else if ("".equals(mBCliente.getTelefonoCliente3())) {
                            mbTodero.setMens("Debe llenar el campo 'Telefono' (Persona solicitante)");
                            mbTodero.warn();
                            estadoValidacionInfoCliente = false;
                        } else if ("".equals(mBCliente.getMensajeConsultaCliente3()) || mBCliente.getMensajeConsultaCliente3() == null) {
                            mbTodero.setMens("Debe analizar la información ingresada");
                            mbTodero.warn();
                            estadoValidacionInfoCliente = false;
                        } else if ("N/A".equals(mBCliente.getMensajeConsultaCliente3())) {
                            mbTodero.setMens("Debe registrar el cliente");
                            mbTodero.warn();
                            estadoValidacionInfoCliente = false;
                        } else if (("".equals(mBCliente.getOpcionRadioTarifasPactadosCli3()) && mBCliente.isClientFact3())) {
                            mbTodero.setMens("Debe elegir una opcion para facturar al cliente (SI/No) (Persona solicitante)");
                            mbTodero.warn();
                            estadoValidacionInfoCliente = false;
                        } else if (mBCliente.isClientFact2() && mBCliente.isClientFact3() && paso == 0 && !mBCliente.getOpcionRadioTarifasPactadosCli3().substring(0, 6).equalsIgnoreCase(mBCliente.getOpcionRadioTarifasPactadosCli2().substring(0, 6))) {

                            mbTodero.setMens("Todos los clientes deben tener el mismo tipo de tarifa ");
                            mbTodero.warn();
                            paso++;
                            estadoValidacionInfoCliente = false;
                        } else if (mBCliente.isClientFact1() && mBCliente.isClientFact3() && paso == 0 && !mBCliente.getOpcionRadioTarifasPactadosCli3().substring(0, 6).equalsIgnoreCase(mBCliente.getOpcionRadioTarifasPactadosCli1().substring(0, 6))) {

                            mbTodero.setMens("Todos los clientes deben tener el mismo tipo de tarifa ");
                            mbTodero.warn();
                            paso++;
                            estadoValidacionInfoCliente = false;
                        } else if ((("".equals(mBCliente.getTipoTarifaCli3()) || mBCliente.getTipoTarifaCli3() == null) && "Si_Cli3".equals(mBCliente.getOpcionRadioTarifasPactadosCli3()) && mBCliente.isClientFact3())
                                && ("".equals(mBCliente.getValorTarifaCli3()) || mBCliente.getValorTarifaCli3() == null) && "Si_Cli3".equals(mBCliente.getOpcionRadioTarifasPactadosCli3()) && mBCliente.isClientFact3()) {
                            mbTodero.setMens("Debe llenar los campos de la tarifa (Persona solicitante)");
                            mbTodero.warn();
                            estadoValidacionInfoCliente = false;
                        } else if (("".equals(mBCliente.getTipoTarifaCli3()) || mBCliente.getTipoTarifaCli3() == null) && "Si_Cli3".equals(mBCliente.getOpcionRadioTarifasPactadosCli3()) && mBCliente.isClientFact3()) {
                            mbTodero.setMens("Debe llenar el campo 'Tipo tarifa' (Persona solicitante)");
                            mbTodero.warn();
                            estadoValidacionInfoCliente = false;
                        } else if (("".equals(mBCliente.getValorTarifaCli3()) || mBCliente.getValorTarifaCli3() == null) && "Si_Cli3".equals(mBCliente.getOpcionRadioTarifasPactadosCli3()) && mBCliente.isClientFact3()) {
                            mbTodero.setMens("Debe llenar el campo 'Valor tarifa' (Persona solicitante)");
                            mbTodero.warn();
                            estadoValidacionInfoCliente = false;
                        } else {
                            estadoValidacionInfoCliente = true;
                        }
                    } //validar fila 4
                    if (mBCliente.isEstadocajasAgregarF3() == true && estadoValidacionInfoCliente) {
                        if ("".equals(mBCliente.getNombreCliente4()) && "".equals(mBCliente.getNumeroDoccliente4())) {
                            mbTodero.setMens("Debe agregar la informacion del cliente");
                            mbTodero.warn();
                            estadoValidacionInfoCliente = false;
                        } else if ("".equals(mBCliente.getNumeroDoccliente4())) {
                            mbTodero.setMens("Debe llenar el campo 'N° Documento' (Persona solicitante)");
                            mbTodero.warn();
                            estadoValidacionInfoCliente = false;
                        } else if ("".equals(mBCliente.getNombreCliente4())) {
                            mbTodero.setMens("Debe llenar el campo 'Nombres' (Persona solicitante)");
                            mbTodero.warn();
                            estadoValidacionInfoCliente = false;
                        } else if ("".equals(mBCliente.getTelefonoCliente4())) {
                            mbTodero.setMens("Debe llenar el campo 'Telefono' (Persona solicitante)");
                            mbTodero.warn();
                            estadoValidacionInfoCliente = false;
                        } else if ("".equals(mBCliente.getMensajeConsultaCliente4()) || mBCliente.getMensajeConsultaCliente4() == null) {
                            mbTodero.setMens("Debe analizar la información ingresada");
                            mbTodero.warn();
                            estadoValidacionInfoCliente = false;
                        } else if ("N/A".equals(mBCliente.getMensajeConsultaCliente4())) {
                            mbTodero.setMens("Debe registrar el cliente");
                            mbTodero.warn();
                            estadoValidacionInfoCliente = false;
                        } else if (("".equals(mBCliente.getOpcionRadioTarifasPactadosCli4()) && mBCliente.isClientFact4())) {
                            mbTodero.setMens("Debe elegir una opcion para facturar al cliente (SI/No) (Persona solicitante)");
                            mbTodero.warn();
                            estadoValidacionInfoCliente = false;
                        } else if (mBCliente.isClientFact3() && mBCliente.isClientFact4() && paso == 0 && !mBCliente.getOpcionRadioTarifasPactadosCli3().substring(0, 6).equalsIgnoreCase(mBCliente.getOpcionRadioTarifasPactadosCli4().substring(0, 6))) {
                            mbTodero.setMens("Todos los clientes deben tener el mismo tipo de tarifa ");
                            mbTodero.warn();
                            paso++;
                            estadoValidacionInfoCliente = false;

                        } else if (mBCliente.isClientFact2() && mBCliente.isClientFact4() && paso == 0 && !mBCliente.getOpcionRadioTarifasPactadosCli2().substring(0, 6).equalsIgnoreCase(mBCliente.getOpcionRadioTarifasPactadosCli4().substring(0, 6))) {
                            mbTodero.setMens("Todos los clientes deben tener el mismo tipo de tarifa ");
                            mbTodero.warn();
                            paso++;
                            estadoValidacionInfoCliente = false;

                        } else if (mBCliente.isClientFact1() && mBCliente.isClientFact4() && paso == 0 && !mBCliente.getOpcionRadioTarifasPactadosCli1().substring(0, 6).equalsIgnoreCase(mBCliente.getOpcionRadioTarifasPactadosCli4().substring(0, 6))) {
                            mbTodero.setMens("Todos los clientes deben tener el mismo tipo de tarifa ");
                            mbTodero.warn();
                            paso++;
                            estadoValidacionInfoCliente = false;

                        } else if ((("".equals(mBCliente.getTipoTarifaCli4()) || mBCliente.getTipoTarifaCli4() == null) && "Si_Cli4".equals(mBCliente.getOpcionRadioTarifasPactadosCli4()) && mBCliente.isClientFact4())
                                && (("".equals(mBCliente.getValorTarifaCli4()) || mBCliente.getValorTarifaCli4() == null) && "Si_Cli4".equals(mBCliente.getOpcionRadioTarifasPactadosCli4()) && mBCliente.isClientFact4())) {
                            mbTodero.setMens("Debe llenar los campos de la tarifa (Persona solicitante)");
                            mbTodero.warn();
                            estadoValidacionInfoCliente = false;
                        } else if (("".equals(mBCliente.getTipoTarifaCli4()) || mBCliente.getTipoTarifaCli4() == null) && "Si_Cli4".equals(mBCliente.getOpcionRadioTarifasPactadosCli4()) && mBCliente.isClientFact4()) {
                            mbTodero.setMens("Debe llenar el campo 'Tipo tarifa' (Persona solicitante)");
                            mbTodero.warn();
                            estadoValidacionInfoCliente = false;
                        } else if (("".equals(mBCliente.getValorTarifaCli4()) || mBCliente.getValorTarifaCli4() == null) && "Si_Cli4".equals(mBCliente.getOpcionRadioTarifasPactadosCli4()) && mBCliente.isClientFact4()) {
                            mbTodero.setMens("Debe llenar el campo 'Valor tarifa' (Persona solicitante)");
                            mbTodero.warn();
                            estadoValidacionInfoCliente = false;
                        } else {
                            estadoValidacionInfoCliente = true;
                        }
                    } //validar fila 5
                    if (mBCliente.isEstadocajasAgregarF4() == true && estadoValidacionInfoCliente) {
                        if ("".equals(mBCliente.getNombreCliente5()) && "".equals(mBCliente.getNumeroDoccliente5())) {
                            mbTodero.setMens("Debe agregar la informacion del cliente");
                            mbTodero.warn();
                            estadoValidacionInfoCliente = false;
                        } else if ("".equals(mBCliente.getNumeroDoccliente5())) {
                            mbTodero.setMens("Debe llenar el campo 'N° Documento' (Persona solicitante)");
                            mbTodero.warn();
                            estadoValidacionInfoCliente = false;
                        } else if ("".equals(mBCliente.getNombreCliente5())) {
                            mbTodero.setMens("Debe llenar el campo 'Nombres' (Persona solicitante)");
                            mbTodero.warn();
                            estadoValidacionInfoCliente = false;
                        } else if ("".equals(mBCliente.getTelefonoCliente5())) {
                            mbTodero.setMens("Debe llenar el campo 'Telefono' (Persona solicitante)");
                            mbTodero.warn();
                            estadoValidacionInfoCliente = false;
                        } else if ("".equals(mBCliente.getMensajeConsultaCliente5()) || mBCliente.getMensajeConsultaCliente5() == null) {
                            mbTodero.setMens("Debe analizar la información ingresada");
                            mbTodero.warn();
                            estadoValidacionInfoCliente = false;
                        } else if ("N/A".equals(mBCliente.getMensajeConsultaCliente5())) {
                            mbTodero.setMens("Debe registrar el cliente");
                            mbTodero.warn();
                            estadoValidacionInfoCliente = false;
                        } else if (("".equals(mBCliente.getOpcionRadioTarifasPactadosCli5()) && mBCliente.isClientFact5())) {
                            mbTodero.setMens("Debe elegir una opcion para facturar al cliente (SI/No) (Persona solicitante)");
                            mbTodero.warn();
                            estadoValidacionInfoCliente = false;
                        } else if (mBCliente.isClientFact4() && mBCliente.isClientFact5() && paso == 0 && !mBCliente.getOpcionRadioTarifasPactadosCli5().substring(0, 6).equalsIgnoreCase(mBCliente.getOpcionRadioTarifasPactadosCli4().substring(0, 6))) {
                            mbTodero.setMens("Todos los clientes deben tener el mismo tipo de tarifa ");
                            mbTodero.warn();
                            paso++;
                            estadoValidacionInfoCliente = false;
                        } else if (mBCliente.isClientFact3() && mBCliente.isClientFact5() && paso == 0 && !mBCliente.getOpcionRadioTarifasPactadosCli5().substring(0, 6).equalsIgnoreCase(mBCliente.getOpcionRadioTarifasPactadosCli3().substring(0, 6))) {
                            mbTodero.setMens("Todos los clientes deben tener el mismo tipo de tarifa ");
                            mbTodero.warn();
                            paso++;
                            estadoValidacionInfoCliente = false;
                        } else if (mBCliente.isClientFact2() && mBCliente.isClientFact5() && paso == 0 && !mBCliente.getOpcionRadioTarifasPactadosCli5().substring(0, 6).equalsIgnoreCase(mBCliente.getOpcionRadioTarifasPactadosCli2().substring(0, 6))) {
                            mbTodero.setMens("Todos los clientes deben tener el mismo tipo de tarifa ");
                            mbTodero.warn();
                            paso++;
                            estadoValidacionInfoCliente = false;
                        } else if (mBCliente.isClientFact1() && mBCliente.isClientFact5() && paso == 0 && !mBCliente.getOpcionRadioTarifasPactadosCli5().substring(0, 6).equalsIgnoreCase(mBCliente.getOpcionRadioTarifasPactadosCli1().substring(0, 6))) {
                            mbTodero.setMens("Todos los clientes deben tener el mismo tipo de tarifa ");
                            mbTodero.warn();
                            paso++;
                            estadoValidacionInfoCliente = false;
                        } else if ((("".equals(mBCliente.getTipoTarifaCli5()) || mBCliente.getTipoTarifaCli5() == null) && "Si_Cli5".equals(mBCliente.getOpcionRadioTarifasPactadosCli5()) && mBCliente.isClientFact5())
                                && (("".equals(mBCliente.getValorTarifaCli5()) || mBCliente.getValorTarifaCli5() == null) && "Si_Cli5".equals(mBCliente.getOpcionRadioTarifasPactadosCli5()) && mBCliente.isClientFact5())) {
                            mbTodero.setMens("Debe llenar los campos de la tarifa (Persona solicitante)");
                            mbTodero.warn();
                            estadoValidacionInfoCliente = false;
                        } else if (("".equals(mBCliente.getTipoTarifaCli5()) || mBCliente.getTipoTarifaCli5() == null) && "Si_Cli5".equals(mBCliente.getOpcionRadioTarifasPactadosCli5()) && mBCliente.isClientFact5()) {
                            mbTodero.setMens("Debe llenar el campo 'Tipo tarifa' (Persona solicitante)");
                            mbTodero.warn();
                            estadoValidacionInfoCliente = false;
                        } else if (("".equals(mBCliente.getValorTarifaCli5()) || mBCliente.getValorTarifaCli5() == null) && "Si_Cli5".equals(mBCliente.getOpcionRadioTarifasPactadosCli5()) && mBCliente.isClientFact5()) {
                            mbTodero.setMens("Debe llenar el campo 'Valor tarifa' (Persona solicitante)");
                            mbTodero.warn();
                            estadoValidacionInfoCliente = false;
                        }
                    }

                } //fin validar persona solicitante

                //validar entidades            
                if (mBEntidad.isEstPanelEntidad()) {

                    if ("".equals(mBEntidad.getCodEntidad1()) && "".equals(mBEntidad.getCodSucursal1()) && "".equals(mBEntidad.getCodAsesor1())) {
                        mbTodero.setMens("Debe llenar los datos de la entidad correspondiente");
                        mbTodero.warn();
                        estadoValidacionInfoEntidad = false;
                    } else if ("".equals(mBEntidad.getCodEntidad1())) {
                        mbTodero.setMens("Debe seleccionar información para el campo 'Entidad'");
                        mbTodero.warn();
                        estadoValidacionInfoEntidad = false;
                    } else if ("".equals(mBEntidad.getCodSucursal1())) {
                        mbTodero.setMens("Debe seleccionar información para el campo 'Sucursal'");
                        mbTodero.warn();
                        estadoValidacionInfoEntidad = false;
                    } else if ("".equals(mBEntidad.getCodAsesor1())) {
                        mbTodero.setMens("Debe seleccionar información para el campo 'Asesor'");
                        mbTodero.warn();
                        estadoValidacionInfoEntidad = false;
                    } else if (("".equals(mBEntidad.getOpcionRadioTarifasPactadosEnti1()) && mBEntidad.isEntidadFact1())) {
                        mbTodero.setMens("Debe elegir una opcion para facturar a la entidad (SI/No) (Entidad)");
                        mbTodero.warn();
                        estadoValidacionInfoEntidad = false;
                    } else if ((("".equals(mBEntidad.getTipoTarifaEnti1()) || mBEntidad.getTipoTarifaEnti1() == null) && "Si_Enti1".equals(mBEntidad.getOpcionRadioTarifasPactadosEnti1()) && mBEntidad.isEntidadFact1())
                            && (("".equals(mBEntidad.getValorTarifaEnti1()) || mBEntidad.getValorTarifaEnti1() == null) && "Si_Enti1".equals(mBEntidad.getOpcionRadioTarifasPactadosEnti1()) && mBEntidad.isEntidadFact1())) {
                        mbTodero.setMens("Debe llenar los campos de la tarifa (Entidad)");
                        mbTodero.warn();
                        estadoValidacionInfoEntidad = false;
                    } else if (("".equals(mBEntidad.getTipoTarifaEnti1()) || mBEntidad.getTipoTarifaEnti1() == null) && "Si_Enti1".equals(mBEntidad.getOpcionRadioTarifasPactadosEnti1()) && mBEntidad.isEntidadFact1()) {
                        mbTodero.setMens("Debe llenar un tipo de tarifa (Entidad) ");
                        mbTodero.warn();
                        estadoValidacionInfoEntidad = false;
                    } else if (("".equals(mBEntidad.getValorTarifaEnti1()) || mBEntidad.getValorTarifaEnti1() == null) && "Si_Enti1".equals(mBEntidad.getOpcionRadioTarifasPactadosEnti1()) && mBEntidad.isEntidadFact1()) {
                        mbTodero.setMens("Debe llenar un valor de tarifa (Entidad)");
                        mbTodero.warn();
                        estadoValidacionInfoEntidad = false;

                    } else {
                        estadoValidacionInfoEntidad = true;
                    }

                    //Entidad dos
                    if (mBEntidad.isEstadoAgregarFilasEntidad2() && estadoValidacionInfoEntidad) {
                        if ("".equals(mBEntidad.getCodEntidad2()) && "".equals(mBEntidad.getCodSucursal2()) && "".equals(mBEntidad.getCodAsesor2())) {
                            mbTodero.setMens("Debe llenar los datos de la entidad correspondiente");
                            mbTodero.warn();
                            estadoValidacionInfoEntidad = false;
                        } else if ("".equals(mBEntidad.getCodEntidad2())) {
                            mbTodero.setMens("Debe seleccionar información para el campo 'Entidad'");
                            mbTodero.warn();
                            estadoValidacionInfoEntidad = false;
                        } else if ("".equals(mBEntidad.getCodSucursal2())) {
                            mbTodero.setMens("Debe seleccionar información para el campo 'Sucursal'");
                            mbTodero.warn();
                            estadoValidacionInfoEntidad = false;
                        } else if ("".equals(mBEntidad.getCodAsesor2())) {
                            mbTodero.setMens("Debe seleccionar información para el campo 'Asesor'");
                            mbTodero.warn();
                            estadoValidacionInfoEntidad = false;
                        } else if (("".equals(mBEntidad.getOpcionRadioTarifasPactadosEnti2()) && mBEntidad.isEntidadFact2())) {
                            mbTodero.setMens("Debe elegir una opcion para facturar a la entidad (SI/No) (Entidad)");
                            mbTodero.warn();
                            estadoValidacionInfoEntidad = false;
                        } else if (mBEntidad.isEntidadFact1() && mBEntidad.isEntidadFact2() && paso == 0 && !mBEntidad.getOpcionRadioTarifasPactadosEnti2().substring(0, 6).equalsIgnoreCase(mBEntidad.getOpcionRadioTarifasPactadosEnti1().substring(0, 6))) {
                            mbTodero.setMens("Todos los clientes deben tener el mismo tipo de tarifa ");
                            paso++;
                            mbTodero.warn();
                            estadoValidacionInfoEntidad = false;
                        } else if ((("".equals(mBEntidad.getTipoTarifaEnti2()) || mBEntidad.getTipoTarifaEnti2() == null) && "Si_Enti2".equals(mBEntidad.getOpcionRadioTarifasPactadosEnti2()) && mBEntidad.isEntidadFact2())
                                && (("".equals(mBEntidad.getValorTarifaEnti2()) || mBEntidad.getValorTarifaEnti2() == null) && "Si_Enti2".equals(mBEntidad.getOpcionRadioTarifasPactadosEnti2()) && mBEntidad.isEntidadFact2())) {
                            mbTodero.setMens("Debe llenar los campos de la tarifa (Entidad)");
                            mbTodero.warn();
                            estadoValidacionInfoEntidad = false;
                        } else if (("".equals(mBEntidad.getTipoTarifaEnti2()) || mBEntidad.getTipoTarifaEnti2() == null) && "Si_Enti2".equals(mBEntidad.getOpcionRadioTarifasPactadosEnti2()) && mBEntidad.isEntidadFact2()) {
                            mbTodero.setMens("Debe llenar un tipo de tarifa (Entidad) ");
                            mbTodero.warn();
                            estadoValidacionInfoEntidad = false;
                        } else if (("".equals(mBEntidad.getValorTarifaEnti2()) || mBEntidad.getValorTarifaEnti2() == null) && "Si_Enti2".equals(mBEntidad.getOpcionRadioTarifasPactadosEnti2()) && mBEntidad.isEntidadFact2()) {
                            mbTodero.setMens("Debe llenar un valor de tarifa (Entidad) ");
                            mbTodero.warn();
                            estadoValidacionInfoEntidad = false;
                        } else {
                            estadoValidacionInfoEntidad = true;
                        }

                    }
                    //entidad 3
                    if (mBEntidad.isEstadoAgregarFilasEntidad3() && estadoValidacionInfoEntidad) {
                        if ("".equals(mBEntidad.getCodEntidad3()) || "".equals(mBEntidad.getCodSucursal3()) || "".equals(mBEntidad.getCodAsesor3())) {
                            mbTodero.setMens("Debe llenar los datos de la entidad correspondiente");
                            mbTodero.warn();
                            estadoValidacionInfoEntidad = false;
                        } else if ("".equals(mBEntidad.getCodEntidad3())) {
                            mbTodero.setMens("Debe seleccionar información para el campo 'Entidad'");
                            mbTodero.warn();
                            estadoValidacionInfoEntidad = false;
                        } else if ("".equals(mBEntidad.getCodSucursal3())) {
                            mbTodero.setMens("Debe seleccionar información para el campo 'Sucursal'");
                            mbTodero.warn();
                            estadoValidacionInfoEntidad = false;
                        } else if ("".equals(mBEntidad.getCodAsesor3())) {
                            mbTodero.setMens("Debe seleccionar información para el campo 'Asesor'");
                            mbTodero.warn();
                            estadoValidacionInfoEntidad = false;
                        } else if (("".equals(mBEntidad.getOpcionRadioTarifasPactadosEnti3()) && mBEntidad.isEntidadFact3())) {
                            mbTodero.setMens("Debe elegir una opcion para facturar a la entidad (SI/No) (Entidad)");
                            mbTodero.warn();
                            estadoValidacionInfoEntidad = false;
                        } else if (mBEntidad.isEntidadFact2() && mBEntidad.isEntidadFact3() && paso == 0 && !mBEntidad.getOpcionRadioTarifasPactadosEnti2().substring(0, 6).equalsIgnoreCase(mBEntidad.getOpcionRadioTarifasPactadosEnti3().substring(0, 6))) {
                            mbTodero.setMens("Todos los clientes deben tener el mismo tipo de tarifa ");
                            mbTodero.warn();
                            paso++;
                            estadoValidacionInfoEntidad = false;
                        } else if (mBEntidad.isEntidadFact1() && mBEntidad.isEntidadFact3() && paso == 0 && !mBEntidad.getOpcionRadioTarifasPactadosEnti1().substring(0, 6).equalsIgnoreCase(mBEntidad.getOpcionRadioTarifasPactadosEnti3().substring(0, 6))) {
                            mbTodero.setMens("Todos los clientes deben tener el mismo tipo de tarifa ");
                            mbTodero.warn();
                            paso++;
                            estadoValidacionInfoEntidad = false;
                        } else if ((("".equals(mBEntidad.getTipoTarifaEnti3()) || mBEntidad.getTipoTarifaEnti3() == null) && "Si_Enti3".equals(mBEntidad.getOpcionRadioTarifasPactadosEnti3()) && mBEntidad.isEntidadFact3())
                                && (("".equals(mBEntidad.getValorTarifaEnti3()) || mBEntidad.getValorTarifaEnti3() == null) && "Si_Enti3".equals(mBEntidad.getOpcionRadioTarifasPactadosEnti3()) && mBEntidad.isEntidadFact3())) {
                            mbTodero.setMens("Debe llenar los campos de la tarifa (Entidad)");
                            mbTodero.warn();
                            estadoValidacionInfoEntidad = false;
                        } else if (("".equals(mBEntidad.getTipoTarifaEnti3()) || mBEntidad.getTipoTarifaEnti3() == null) && "Si_Enti3".equals(mBEntidad.getOpcionRadioTarifasPactadosEnti3()) && mBEntidad.isEntidadFact3()) {
                            mbTodero.setMens("Debe llenar un tipo de tarifa (Entidad) ");
                            mbTodero.warn();
                            estadoValidacionInfoEntidad = false;
                        } else if (("".equals(mBEntidad.getValorTarifaEnti3()) || mBEntidad.getValorTarifaEnti3() == null) && "Si_Enti3".equals(mBEntidad.getOpcionRadioTarifasPactadosEnti3()) && mBEntidad.isEntidadFact3()) {
                            mbTodero.setMens("Debe llenar un valor de tarifa (Entidad) ");
                            mbTodero.warn();
                            estadoValidacionInfoEntidad = false;
                        } else {
                            estadoValidacionInfoEntidad = true;
                        }

                    }
                    //entidad 4
                    if (mBEntidad.isEstadoAgregarFilasEntidad4() && estadoValidacionInfoEntidad) {
                        if ("".equals(mBEntidad.getCodEntidad4()) || "".equals(mBEntidad.getCodSucursal4()) || "".equals(mBEntidad.getCodAsesor4())) {
                            mbTodero.setMens("Debe llenar los datos de la entidad correspondiente");
                            mbTodero.warn();
                            estadoValidacionInfoEntidad = false;
                        } else if ("".equals(mBEntidad.getCodEntidad4())) {
                            mbTodero.setMens("Debe seleccionar información para el campo 'Entidad'");
                            mbTodero.warn();
                            estadoValidacionInfoEntidad = false;
                        } else if ("".equals(mBEntidad.getCodSucursal4())) {
                            mbTodero.setMens("Debe seleccionar información para el campo 'Sucursal'");
                            mbTodero.warn();
                            estadoValidacionInfoEntidad = false;
                        } else if ("".equals(mBEntidad.getCodAsesor4())) {
                            mbTodero.setMens("Debe seleccionar información para el campo 'Asesor'");
                            mbTodero.warn();
                            estadoValidacionInfoEntidad = false;
                        } else if (("".equals(mBEntidad.getOpcionRadioTarifasPactadosEnti4()) && mBEntidad.isEntidadFact4())) {
                            mbTodero.setMens("Debe elegir una opcion para facturar a la entidad (SI/No) (Entidad)");
                            mbTodero.warn();
                            estadoValidacionInfoEntidad = false;
                        } else if (mBEntidad.isEntidadFact3() && mBEntidad.isEntidadFact4() && paso == 0 && !mBEntidad.getOpcionRadioTarifasPactadosEnti4().substring(0, 6).equalsIgnoreCase(mBEntidad.getOpcionRadioTarifasPactadosEnti3().substring(0, 6))) {
                            mbTodero.setMens("Todos los clientes deben tener el mismo tipo de tarifa ");
                            mbTodero.warn();
                            paso++;
                            estadoValidacionInfoEntidad = false;
                        } else if (mBEntidad.isEntidadFact2() && mBEntidad.isEntidadFact4() && paso == 0 && !mBEntidad.getOpcionRadioTarifasPactadosEnti4().substring(0, 6).equalsIgnoreCase(mBEntidad.getOpcionRadioTarifasPactadosEnti2().substring(0, 6))) {
                            mbTodero.setMens("Todos los clientes deben tener el mismo tipo de tarifa ");
                            mbTodero.warn();
                            paso++;
                            estadoValidacionInfoEntidad = false;
                        } else if (mBEntidad.isEntidadFact1() && mBEntidad.isEntidadFact4() && paso == 0 && !mBEntidad.getOpcionRadioTarifasPactadosEnti4().substring(0, 6).equalsIgnoreCase(mBEntidad.getOpcionRadioTarifasPactadosEnti1().substring(0, 6))) {
                            mbTodero.setMens("Todos los clientes deben tener el mismo tipo de tarifa ");
                            mbTodero.warn();
                            paso++;
                            estadoValidacionInfoEntidad = false;
                        } else if ((("".equals(mBEntidad.getTipoTarifaEnti4()) || mBEntidad.getTipoTarifaEnti4() == null) && "Si_Enti4".equals(mBEntidad.getOpcionRadioTarifasPactadosEnti4()) && mBEntidad.isEntidadFact4())
                                && (("".equals(mBEntidad.getValorTarifaEnti4()) || mBEntidad.getValorTarifaEnti4() == null) && "Si_Enti4".equals(mBEntidad.getOpcionRadioTarifasPactadosEnti4()) && mBEntidad.isEntidadFact4())) {
                            mbTodero.setMens("Debe llenar los campos de la tarifa (Entidad)");
                            mbTodero.warn();
                            estadoValidacionInfoEntidad = false;
                        } else if (("".equals(mBEntidad.getTipoTarifaEnti4()) || mBEntidad.getTipoTarifaEnti4() == null) && "Si_Enti4".equals(mBEntidad.getOpcionRadioTarifasPactadosEnti4()) && mBEntidad.isEntidadFact4()) {
                            mbTodero.setMens("Debe llenar un tipo de tarifa (Entidad) ");
                            mbTodero.warn();
                            estadoValidacionInfoEntidad = false;
                        } else if (("".equals(mBEntidad.getValorTarifaEnti4()) || mBEntidad.getValorTarifaEnti4() == null) && "Si_Enti4".equals(mBEntidad.getOpcionRadioTarifasPactadosEnti4()) && mBEntidad.isEntidadFact4()) {
                            mbTodero.setMens("Debe llenar un valor de tarifa (Entidad) ");
                            mbTodero.warn();
                            estadoValidacionInfoEntidad = false;
                        } else {
                            estadoValidacionInfoEntidad = true;
                        }
                    }
                    if (mBEntidad.isEstadoAgregarFilasEntidad5() && estadoValidacionInfoEntidad) {
                        if ("".equals(mBEntidad.getCodEntidad5()) || "".equals(mBEntidad.getCodSucursal5()) || "".equals(mBEntidad.getCodAsesor5())) {
                            mbTodero.setMens("Debe llenar los datos de la entidad correspondiente");
                            mbTodero.warn();
                            estadoValidacionInfoEntidad = false;
                        } else if ("".equals(mBEntidad.getCodEntidad5())) {
                            mbTodero.setMens("Debe seleccionar información para el campo 'Entidad'");
                            mbTodero.warn();
                            estadoValidacionInfoEntidad = false;
                        } else if ("".equals(mBEntidad.getCodSucursal5())) {
                            mbTodero.setMens("Debe seleccionar información para el campo 'Sucursal'");
                            mbTodero.warn();
                            estadoValidacionInfoEntidad = false;
                        } else if ("".equals(mBEntidad.getCodAsesor5())) {
                            mbTodero.setMens("Debe seleccionar información para el campo 'Asesor'");
                            mbTodero.warn();
                            estadoValidacionInfoEntidad = false;
                        } else if (("".equals(mBEntidad.getOpcionRadioTarifasPactadosEnti5()) && mBEntidad.isEntidadFact5())) {
                            mbTodero.setMens("Debe elegir una opcion para facturar a la entidad (SI/No) (Entidad)");
                            mbTodero.warn();
                            estadoValidacionInfoEntidad = false;
                        } else if (mBEntidad.isEntidadFact4() && mBEntidad.isEntidadFact5() && paso == 0 && !mBEntidad.getOpcionRadioTarifasPactadosEnti4().substring(0, 6).equalsIgnoreCase(mBEntidad.getOpcionRadioTarifasPactadosEnti5().substring(0, 6))) {
                            mbTodero.setMens("Todos los clientes deben tener el mismo tipo de tarifa ");
                            mbTodero.warn();
                            paso++;
                            estadoValidacionInfoEntidad = false;
                        } else if (mBEntidad.isEntidadFact3() && mBEntidad.isEntidadFact5() && paso == 0 && !mBEntidad.getOpcionRadioTarifasPactadosEnti3().substring(0, 6).equalsIgnoreCase(mBEntidad.getOpcionRadioTarifasPactadosEnti5().substring(0, 6))) {
                            mbTodero.setMens("Todos los clientes deben tener el mismo tipo de tarifa ");
                            mbTodero.warn();
                            paso++;
                            estadoValidacionInfoEntidad = false;
                        } else if (mBEntidad.isEntidadFact2() && mBEntidad.isEntidadFact5() && paso == 0 && !mBEntidad.getOpcionRadioTarifasPactadosEnti2().substring(0, 6).equalsIgnoreCase(mBEntidad.getOpcionRadioTarifasPactadosEnti5().substring(0, 6))) {
                            mbTodero.setMens("Todos los clientes deben tener el mismo tipo de tarifa ");
                            mbTodero.warn();
                            paso++;
                            estadoValidacionInfoEntidad = false;
                        } else if (mBEntidad.isEntidadFact1() && mBEntidad.isEntidadFact5() && paso == 0 && !mBEntidad.getOpcionRadioTarifasPactadosEnti1().substring(0, 6).equalsIgnoreCase(mBEntidad.getOpcionRadioTarifasPactadosEnti5().substring(0, 6))) {
                            mbTodero.setMens("Todos los clientes deben tener el mismo tipo de tarifa ");
                            mbTodero.warn();
                            paso++;
                            estadoValidacionInfoEntidad = false;
                        } else if ((("".equals(mBEntidad.getTipoTarifaEnti5()) || mBEntidad.getTipoTarifaEnti5() == null) && "Si_Enti5".equals(mBEntidad.getOpcionRadioTarifasPactadosEnti5()) && mBEntidad.isEntidadFact5())
                                && (("".equals(mBEntidad.getValorTarifaEnti5()) || mBEntidad.getValorTarifaEnti5() == null) && "Si_Enti5".equals(mBEntidad.getOpcionRadioTarifasPactadosEnti5()) && mBEntidad.isEntidadFact5())) {
                            mbTodero.setMens("Debe llenar los campos de la tarifa (Entidad)");
                            mbTodero.warn();
                            estadoValidacionInfoEntidad = false;
                        } else if (("".equals(mBEntidad.getTipoTarifaEnti5()) || mBEntidad.getTipoTarifaEnti5() == null) && "Si_Enti5".equals(mBEntidad.getOpcionRadioTarifasPactadosEnti5()) && mBEntidad.isEntidadFact5()) {
                            mbTodero.setMens("Debe llenar un tipo de tarifa (Entidad) ");
                            mbTodero.warn();
                            estadoValidacionInfoEntidad = false;
                        } else if (("".equals(mBEntidad.getValorTarifaEnti5()) || mBEntidad.getValorTarifaEnti5() == null) && "Si_Enti5".equals(mBEntidad.getOpcionRadioTarifasPactadosEnti5()) && mBEntidad.isEntidadFact5()) {
                            mbTodero.setMens("Debe llenar un valor de tarifa (Entidad) ");
                            mbTodero.warn();
                        } else {
                            estadoValidacionInfoEntidad = true;
                        }
                    }

                }
                //fin validar entidades
                //Esta condicion permite realizar la validacion de cada dato ingresado, para verificar si ya se encuentra true todo puede pasar el proceso
                if (paso == 0 && estadoValidacionInfoGeneral == true && estadoValidacionInfoCliente == true && estadoValidacionInfoEntidad == true && (estadoValidacionInfoClienteNo == true || estadoValidacionInfoEntidadNo == true)) {
                    RequestContext.getCurrentInstance().execute("PF('tabRadicacion').disable(1)");
                    RequestContext.getCurrentInstance().execute("PF('tabRadicacion').enable(2)");
                    RequestContext.getCurrentInstance().execute("PF('tabRadicacion').select(2)");
                }

            }
            //Tab 2 atras (Informacion de bienes)
            if ("AtrasTb2".equals(form_num)) {
                RequestContext.getCurrentInstance().execute("PF('tabRadicacion').disable(2)");
                RequestContext.getCurrentInstance().execute("PF('tabRadicacion').enable(1)");
                RequestContext.getCurrentInstance().execute("PF('tabRadicacion').select(1)");
            }
            //Tab 2 adelante (Informacion de bienes)
            if ("AdelanteTb2".equals(form_num)) {
                //validaciones bien
                if (mBAvaluo.isEstadoPanelAvalRadic() == false) {
                    mbTodero.setMens("Debe agregar un bien");
                    estadoValidacionInfoBien = false;
                    mbTodero.warn();
                } else if ("".equals(mBAvaluo.getFkCodTipAvaluoRadic()) && ("".equals(mBAvaluo.getNombreTipAvaluoRadic()) || mBAvaluo.getNombreTipAvaluoRadic() == null)) {
                    mbTodero.setMens("Debe agregar un Tipo de bien");
                    mbTodero.warn();
                    estadoValidacionInfoBien = false;
                    //Predio
                } else if (("1".equals(mBAvaluo.getFkCodTipAvaluoRadic())) && ("".equals(mBAvaluo.getNombreTipoBienRadic()) || mBAvaluo.getNombreTipoBienRadic() == null)) {
                    mbTodero.setMens("Debe agregar un Tipo de predio");
                    mbTodero.warn();
                    estadoValidacionInfoBien = false;
                    //Maquinaria
                } else if ("1".equals(mBAvaluo.getFkCodTipAvaluoRadic()) && ("".equals(mBAvaluo.getMatriculaAvalRadic()) || mBAvaluo.getMatriculaAvalRadic() == null)) {
                    mbTodero.setMens("Debe agregar una Matricula");
                    mbTodero.warn();
                    estadoValidacionInfoBien = false;
                } else if ("2".equals(mBAvaluo.getFkCodTipAvaluoRadic()) && ("".equals(mBAvaluo.getNombreTipoBienRadic()) || mBAvaluo.getNombreTipoBienRadic() == null)) {
                    mbTodero.setMens("Debe agregar un Tipo de maquinaria");
                    mbTodero.warn();
                    estadoValidacionInfoBien = false;
                } else if ("2".equals(mBAvaluo.getFkCodTipAvaluoRadic()) && ("".equals(mBAvaluo.getMarcaMaquinRadic()) || mBAvaluo.getMarcaMaquinRadic() == null)) {
                    mbTodero.setMens("Debe agregar una Marca");
                    mbTodero.warn();
                    estadoValidacionInfoBien = false;
                } else if ("2".equals(mBAvaluo.getFkCodTipAvaluoRadic()) && ("".equals(mBAvaluo.getModeloMaquinRadic()) || mBAvaluo.getModeloMaquinRadic() == null)) {
                    mbTodero.setMens("Debe agregar un Modelo");
                    mbTodero.warn();
                    estadoValidacionInfoBien = false;
                } else if ("2".equals(mBAvaluo.getFkCodTipAvaluoRadic()) && ("".equals(mBAvaluo.getSerieAvalRadic()) || mBAvaluo.getSerieAvalRadic() == null)) {
                    mbTodero.setMens("Debe agregar una Serie");
                    mbTodero.warn();
                    estadoValidacionInfoBien = false;
                } else if ("2".equals(mBAvaluo.getFkCodTipAvaluoRadic()) && ("".equals(mBAvaluo.getObservacMaquinRadic()) || mBAvaluo.getObservacMaquinRadic() == null)) {
                    mbTodero.setMens("Debe agregar una observación de la maquinaria");
                    mbTodero.warn();
                    estadoValidacionInfoBien = false;
                } else if ("3".equals(mBAvaluo.getFkCodTipAvaluoRadic()) && ("".equals(mBAvaluo.getObservacAvalRadic()) || mBAvaluo.getObservacAvalRadic() == null)) {
                    mbTodero.setMens("Debe agregar una observación del mueble");
                    mbTodero.warn();
                    estadoValidacionInfoBien = false;
                } else if ("".equals(mBAvaluo.getDireccionAvalRadic()) || mBAvaluo.getDireccionAvalRadic() == null) {
                    mbTodero.setMens("Debe agregar una dirección");
                    mbTodero.warn();
                    estadoValidacionInfoBien = false;
                } else if ("".equals(mBAvaluo.getNombreDeptoAvalRadic()) || mBAvaluo.getNombreDeptoAvalRadic() == null) {
                    mbTodero.setMens("Debe agregar un departamento");
                    mbTodero.warn();
                    estadoValidacionInfoBien = false;
                } else if ("".equals(mBAvaluo.getNombreCiudAvalRadic()) || mBAvaluo.getNombreCiudAvalRadic() == null) {
                    mbTodero.setMens("Debe agregar una ciudad");
                    mbTodero.warn();
                    estadoValidacionInfoBien = false;
                } else {
                    estadoValidacionInfoBien = true;
                }

                if (estadoValidacionInfoBien) {
                    RequestContext.getCurrentInstance().execute("PF('tabRadicacion').disable(2)");
                    RequestContext.getCurrentInstance().execute("PF('tabRadicacion').enable(3)");
                    RequestContext.getCurrentInstance().execute("PF('tabRadicacion').select(3)");
                    Per.setOp(2);
                    mBPerito.setListPerit(new ArrayList<LogPerito>());
                    mBPerito.setListPerit(Per.ConsulAllPerito(Integer.parseInt(mBAvaluo.getCodCiudadAvalRadic())));
                    // para prueba solamente: mBAvaluo.setCod_AvaluoRadic(String.valueOf(0));
                    consultaAvaluoPerito();
                }
            }

            //Tab 3 atras (Agisnacion de perito)
            if ("AtrasTb3".equals(form_num)) {
                RequestContext.getCurrentInstance().execute("PF('tabRadicacion').disable(3)");
                RequestContext.getCurrentInstance().execute("PF('tabRadicacion').enable(2)");
                RequestContext.getCurrentInstance().execute("PF('tabRadicacion').select(2)");
            }
            //Tab 3 adelante (Agisnacion de perito)

            if ("AdelanteTb3".equals(form_num)) {
                //validar peritos
                if (mBPerito.getListPeritAvaluadoresSeleccionadosForm().isEmpty()) {
                    mbTodero.setMens("Debe seleccionar a menos un perito ");
                    mbTodero.warn();
                    estadoValidacionInfoPerito = false;
                } else if (mBPerito.getListPeritAvaluadoresSeleccionadosForm().size() > 0 && mBPerito.getListPeritAvaluadoresSeleccionadosForm().size() < 6) {
                    for (int i = 0; i <= mBPerito.getListPeritAvaluadoresSeleccionadosForm().size() - 1; i++) {

                        if (i == 0) {

                            if ("".equals(mBPerito.getOpcionRadioTarifasPactadosPer1())) {
                                mbTodero.setMens("Debe seleccionar una opcion para facturar al perito [Si / No]");
                                mbTodero.warn();
                                estadoValidacionInfoPerito = false;
                            } else if ((("".equals(mBPerito.getTipoTarifaPeri1()) || mBPerito.getTipoTarifaPeri1() == null) && "Si_Perito1".equals(mBPerito.getOpcionRadioTarifasPactadosPer1()))
                                    && (("".equals(mBPerito.getValorTarifaPer1()) || mBPerito.getValorTarifaPer1() == null) && "Si_Perito1".equals(mBPerito.getOpcionRadioTarifasPactadosPer1()))) {
                                mbTodero.setMens("Debe llenar los campos de la tarifa (Perito)");
                                mbTodero.warn();
                                estadoValidacionInfoPerito = false;
                            } else if (("".equals(mBPerito.getTipoTarifaPeri1()) || mBPerito.getTipoTarifaPeri1() == null) && "Si_Perito1".equals(mBPerito.getOpcionRadioTarifasPactadosPer1())) {
                                mbTodero.setMens("Debe elegir un tipo de tarifa (Perito) ");
                                mbTodero.warn();
                                estadoValidacionInfoPerito = false;
                            } else if (("".equals(mBPerito.getValorTarifaPer1()) || mBPerito.getValorTarifaPer1() == null) && "Si_Perito1".equals(mBPerito.getOpcionRadioTarifasPactadosPer1())) {
                                mbTodero.setMens("Debe elegir un valor de tarifa (Perito) ");
                                mbTodero.warn();
                                estadoValidacionInfoPerito = false;
                            } else {
                                estadoValidacionInfoPerito = true;
                            }

                        }

                        if (i == 1) {

                            if ("".equals(mBPerito.getOpcionRadioTarifasPactadosPer2())) {
                                mbTodero.setMens("Debe seleccionar una opcion para facturar al perito [Si / No]");
                                mbTodero.warn();
                                estadoValidacionInfoPerito = false;
                            } else if ((("".equals(mBPerito.getTipoTarifaPeri2()) || mBPerito.getTipoTarifaPeri2() == null) && "Si_Perito2".equals(mBPerito.getOpcionRadioTarifasPactadosPer2()))
                                    && (("".equals(mBPerito.getValorTarifaPer2()) || mBPerito.getValorTarifaPer2() == null) && "Si_Perito2".equals(mBPerito.getOpcionRadioTarifasPactadosPer2()))) {
                                mbTodero.setMens("Debe llenar los campos de la tarifa (Perito)");
                                mbTodero.warn();
                                estadoValidacionInfoPerito = false;
                            } else if (("".equals(mBPerito.getTipoTarifaPeri2()) || mBPerito.getTipoTarifaPeri2() == null) && "Si_Perito2".equals(mBPerito.getOpcionRadioTarifasPactadosPer2())) {
                                mbTodero.setMens("Debe elegir un tipo de tarifa (Perito) ");
                                mbTodero.warn();
                                estadoValidacionInfoPerito = false;
                            } else if (("".equals(mBPerito.getValorTarifaPer2()) || mBPerito.getValorTarifaPer2() == null) && "Si_Perito2".equals(mBPerito.getOpcionRadioTarifasPactadosPer2())) {
                                mbTodero.setMens("Debe elegir un valor de tarifa (Perito) ");
                                mbTodero.warn();
                                estadoValidacionInfoPerito = false;
                            } else {
                                estadoValidacionInfoPerito = true;
                            }

                        }

                        if (i == 2) {

                            if ("".equals(mBPerito.getOpcionRadioTarifasPactadosPer3())) {
                                mbTodero.setMens("Debe seleccionar una opcion para facturar al perito [Si / No]");
                                mbTodero.warn();
                                estadoValidacionInfoPerito = false;
                            } else if ((("".equals(mBPerito.getTipoTarifaPeri3()) || mBPerito.getTipoTarifaPeri3() == null) && "Si_Perito3".equals(mBPerito.getOpcionRadioTarifasPactadosPer3()))
                                    && (("".equals(mBPerito.getValorTarifaPer3()) || mBPerito.getValorTarifaPer3() == null) && "Si_Perito3".equals(mBPerito.getOpcionRadioTarifasPactadosPer3()))) {
                                mbTodero.setMens("Debe llenar los campos de la tarifa (Perito)");
                                mbTodero.warn();
                                estadoValidacionInfoPerito = false;
                            } else if (("".equals(mBPerito.getTipoTarifaPeri3()) || mBPerito.getTipoTarifaPeri3() == null) && "Si_Perito3".equals(mBPerito.getOpcionRadioTarifasPactadosPer3())) {
                                mbTodero.setMens("Debe elegir un tipo de tarifa (Perito) ");
                                mbTodero.warn();
                                estadoValidacionInfoPerito = false;
                            } else if (("".equals(mBPerito.getValorTarifaPer3()) || mBPerito.getValorTarifaPer3() == null) && "Si_Perito3".equals(mBPerito.getOpcionRadioTarifasPactadosPer3())) {
                                mbTodero.setMens("Debe elegir un valor de tarifa (Perito) ");
                                mbTodero.warn();
                                estadoValidacionInfoPerito = false;
                            } else {
                                estadoValidacionInfoPerito = true;
                            }

                        }

                        if (i == 3) {

                            if ("".equals(mBPerito.getOpcionRadioTarifasPactadosPer4())) {
                                mbTodero.setMens("Debe seleccionar una opcion para facturar al perito [Si / No]");
                                mbTodero.warn();
                                estadoValidacionInfoPerito = false;
                            } else if ((("".equals(mBPerito.getTipoTarifaPeri4()) || mBPerito.getTipoTarifaPeri4() == null) && "Si_Perito4".equals(mBPerito.getOpcionRadioTarifasPactadosPer4()))
                                    && (("".equals(mBPerito.getValorTarifaPer4()) || mBPerito.getValorTarifaPer4() == null) && "Si_Perito4".equals(mBPerito.getOpcionRadioTarifasPactadosPer4()))) {
                                mbTodero.setMens("Debe llenar los campos de la tarifa (Perito)");
                                mbTodero.warn();
                                estadoValidacionInfoPerito = false;
                            } else if (("".equals(mBPerito.getTipoTarifaPeri4()) || mBPerito.getTipoTarifaPeri4() == null) && "Si_Perito4".equals(mBPerito.getOpcionRadioTarifasPactadosPer4())) {
                                mbTodero.setMens("Debe elegir un tipo de tarifa (Perito) ");
                                mbTodero.warn();
                                estadoValidacionInfoPerito = false;
                            } else if (("".equals(mBPerito.getValorTarifaPer4()) || mBPerito.getValorTarifaPer4() == null) && "Si_Perito4".equals(mBPerito.getOpcionRadioTarifasPactadosPer4())) {
                                mbTodero.setMens("Debe elegir un valor de tarifa (Perito) ");
                                mbTodero.warn();
                                estadoValidacionInfoPerito = false;
                            } else {
                                estadoValidacionInfoPerito = true;
                            }

                        }

                        if (i == 4) {

                            if ("".equals(mBPerito.getOpcionRadioTarifasPactadosPer5())) {
                                mbTodero.setMens("Debe seleccionar una opcion para facturar al perito [Si / No]");
                                mbTodero.warn();
                                estadoValidacionInfoPerito = false;
                            } else if ((("".equals(mBPerito.getTipoTarifaPeri5()) || mBPerito.getTipoTarifaPeri5() == null) && "Si_Perito5".equals(mBPerito.getOpcionRadioTarifasPactadosPer5()))
                                    && (("".equals(mBPerito.getValorTarifaPer5()) || mBPerito.getValorTarifaPer5() == null) && "Si_Perito5".equals(mBPerito.getOpcionRadioTarifasPactadosPer5()))) {
                                mbTodero.setMens("Debe llenar los campos de la tarifa (Perito)");
                                mbTodero.warn();
                                estadoValidacionInfoPerito = false;
                            } else if (("".equals(mBPerito.getTipoTarifaPeri5()) || mBPerito.getTipoTarifaPeri5() == null) && "Si_Perito5".equals(mBPerito.getOpcionRadioTarifasPactadosPer5())) {
                                mbTodero.setMens("Debe elegir un tipo de tarifa (Perito) ");
                                mbTodero.warn();
                                estadoValidacionInfoPerito = false;
                            } else if (("".equals(mBPerito.getValorTarifaPer5()) || mBPerito.getValorTarifaPer5() == null) && "Si_Perito5".equals(mBPerito.getOpcionRadioTarifasPactadosPer5())) {
                                mbTodero.setMens("Debe elegir un valor de tarifa (Perito) ");
                                mbTodero.warn();
                                estadoValidacionInfoPerito = false;
                            } else {
                                estadoValidacionInfoPerito = true;
                            }

                        }
                        observacion = observacionInicio;

                    }
                }
                if (estadoValidacionInfoPerito) {
                    RequestContext.getCurrentInstance().execute("PF('tabRadicacion').disable(3)");
                    RequestContext.getCurrentInstance().execute("PF('tabRadicacion').enable(4)");
                    RequestContext.getCurrentInstance().execute("PF('tabRadicacion').select(4)");
                }

            }

            //Tab 4 atras (Responsable), contacto
            if ("AtrasTb4".equals(form_num)) {
                RequestContext.getCurrentInstance().execute("PF('tabRadicacion').disable(4)");
                RequestContext.getCurrentInstance().execute("PF('tabRadicacion').enable(3)");
                RequestContext.getCurrentInstance().execute("PF('tabRadicacion').select(3)");
            }

            //Tab 4 Adelante (Responsable), contacto
            if ("AdelanteTb5".equals(form_num)) {
                int TipoA = 0;
                String ValArch = "";
                if (ListaArcRegla.size() <= 0) {
                    estadoValidacionInfoArchivos = true;
                } else {
                    switch (mBAvaluo.getNombreTipAvaluoRadic()) {
                        case "Predio":
                            ValArch = "Sol_Pre";
                            break;
                        case "Maquinaria":
                            ValArch = "Sol_Maq";
                            break;
                        case "Enser":
                            ValArch = "Sol_Ens";
                            break;
                    }
                    List<LogCargueArchivos> ListaRadica = new ArrayList<>();
                    ListaRadica = Archi.ConsultaArchivAvaluoRadica(1, cod_avaluo, cod_bien_seguimiento);
                    int C = 0;
                    for (int i = 0; i <= ListaArcRegla.size() - 1; i++) {
                        for (int j = 0; j <= ListaRadica.size() - 1; j++) {
                            if (ListaArcRegla.get(i).getCodigoParametro() == ListaRadica.get(j).getCodTipArchivo()) {
                                C += 1;
                            }
                        }
                    }
                    if (C != ListaArcRegla.size()) {
                        mbTodero.setMens("Hay archivos obligatorios del proceso que faltan por subir; favor verifique nuevamente y realice el posterior cargue de lo(s) documento(s)");
                        mbTodero.warn();
                        estadoValidacionInfoArchivos = false;
                    } else {
                        estadoValidacionInfoArchivos = true;
                    }
                }
                if (estadoValidacionInfoArchivos == true) {
                    ListaArchivosRadiCorreo = new ArrayList<>();
                    ListaArchivosRadiCorreo = mBArchivo.MostrarArchivos(6, File.separator + "DBARCHIVOS" + File.separator + "DBISA" + File.separator + "DBAdjuntos" + File.separator + "Radicacion" + File.separator);
                    mBArchivo.setNAvaluo(cod_avaluo);
                    mBArchivo.setNBien(cod_bien_seguimiento);
                    mBArchivo.setTipoAvaluo(mBAvaluo.getNombreTipAvaluoRadic());
                    mBArchivo.setOpcionPanel("SelAva");//Seleccional del tipo de panel para enviar documentos al perito en radicacion
                    mBArchivo.seleccionPanel(cod_avaluo);//Para vargar la lista de los Archivos los cuales van a estar dentro de las listas que se envian de documentos del perito
                    mBPerito.cargainfoRadicCarta(cod_avaluo);
                    RequestContext.getCurrentInstance().execute("PF('tabRadicacion').disable(5)");
                    RequestContext.getCurrentInstance().execute("PF('tabRadicacion').enable(6)");
                    RequestContext.getCurrentInstance().execute("PF('tabRadicacion').select(6)");
                }
            }

            if ("AtrasTb6".equals(form_num)) {
                mBArchivo.setOpcionPanel("");
                mBArchivo.setEstadoPanelArchivoAnti(false);
                mBArchivo.setEstadoPanelArchivoAva(false);
                mBArchivo.setEstadoPanelArchivoCli(false);
                RequestContext.getCurrentInstance().execute("PF('tabRadicacion').disable(6)");
                RequestContext.getCurrentInstance().execute("PF('tabRadicacion').enable(5)");
                RequestContext.getCurrentInstance().execute("PF('tabRadicacion').select(5)");

            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".validarFormTabs()' causado por: " + e.getMessage());
            mbTodero.error();

        }

    }

    public void consultaAvaluoPerito() {
        try {
            Aval = new LogAvaluo();
            if (mBAvaluo.getCod_AvaluoRadic() == 0) {
                mBAvaluo.setCod_AvaluoRadic(0);
            } else {
                DatInfoAvalAnterior = Aval.ConsulAvaPerito(mBAvaluo.getCod_AvaluoRadic());
                if (DatInfoAvalAnterior.next()) {
                    mBPerito.setNumDocPerito(DatInfoAvalAnterior.getString("NumDoc_Perito"));
                    mBPerito.setNombrePerito(DatInfoAvalAnterior.getString("Nombre_Perito") + " " + (DatInfoAvalAnterior.getString("Apellido_Perito")));
                    mBPerito.setTelefonoPerito(DatInfoAvalAnterior.getString("Telefono_Perito"));
                    mBPerito.setCelularPerito(DatInfoAvalAnterior.getString("Celular_Perito"));
                }
                Conexion.Conexion.CloseCon();
            }

        } catch (NumberFormatException | SQLException e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".consultaAvaluoPerito()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    public void verCli(int fila) {
        try {
            Cli = new LogCliente();
            if (fila == 1) {
                Dat = Cli.getConsultarAllClientesMostrarinfo(mBCliente.getNumeroDoccliente1());
            }

            if (fila == 2) {
                Dat = Cli.getConsultarAllClientesMostrarinfo(mBCliente.getNumeroDoccliente2());
            }
            if (fila == 3) {
                Dat = Cli.getConsultarAllClientesMostrarinfo(mBCliente.getNumeroDoccliente3());
            }
            if (fila == 4) {
                Dat = Cli.getConsultarAllClientesMostrarinfo(mBCliente.getNumeroDoccliente4());
            }
            if (fila == 5) {
                Dat = Cli.getConsultarAllClientesMostrarinfo(mBCliente.getNumeroDoccliente5());
            }
            if (Dat.next()) {
                mBCliente.setTipoDocclienteInfo(Dat.getString("Nombre_Documento"));
                mBCliente.setNumeroDocclienteInfo(Dat.getString("Numero_DocCliente"));
                mBCliente.setNombreClienteInfo(Dat.getString("Nombre_Cliente"));
                mBCliente.setCorreoClienteInfo(Dat.getString("Mail_Cliente"));
                mBCliente.setDireccionClienteInfo(Dat.getString("Direccion_Cliente"));
                mBCliente.setTelefonoClienteInfo(Dat.getString("Telefono_Cliente"));
                mBCliente.setUbicacionClienteInfo(Dat.getString("Ubicacion"));
                mBCliente.setCodRegimenCli(Dat.getString("regimen"));
                RequestContext.getCurrentInstance().execute("PF('InfoCliente').show()");
            } else {
                mbTodero.setMens("El cliente no se encuentra registrado");
                mbTodero.info();
            }
            Conexion.Conexion.CloseCon();

        } catch (SQLException e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".verCli()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    public void registrarRadicacion() {
        try {

            mBAvaluo.getFkCodTipAvaluoRadic();
            Rad.setCodPreRadica(cod_preRadica);
            HoraSolisitud = FormatHora.format(HoraSol);
            Rad.setFechaSolicRadic(fechaSoli + " " + HoraSolisitud);
            Rad.getAval().setDireccion(mBAvaluo.getDireccionAvalRadic());
            Rad.getAval().setTipoDireccion(mBAvaluo.getTipoDireccionAvalRadic());
            Rad.getAval().setBarrio(mBAvaluo.getBarrioAvalRadic());
            Rad.setObservacionRadic(observacion + " - Radicación");
            if (requierUbicacion == true) {
                Rad.getAval().setCiudadSolic(mBAvaluo.getCodCiudadSol());
            } else {
                Rad.getAval().setCiudadSolic("0");
            }

            Rad.getAval().setCiudad(mBAvaluo.getCodCiudadAvalRadic());
            Rad.getAval().setCodTipAvaluo(Integer.parseInt(mBAvaluo.getFkCodTipAvaluoRadic()));
            Rad.setCod_tipo_prodEnti(Integer.parseInt(codTipProEnt));

            Rad.setNombreContacto(nombreContacto);
            Rad.setCelularContacto(celularContacto);
            Rad.setTelefonoContacto(telefonoContacto);
            Rad.setObservaContacto(observacionContacto);
            Rad.setFk_CiudadContacto(codCiudContacto);

            if (fechaCita == null) {
                Rad.setFechaCita("");
            } else {
                Rad.setFechaCita(fechaCitaAsig + " " + horaCitaAsig);
            }

            //predio
            switch (mBAvaluo.getFkCodTipAvaluoRadic()) {
                case "1":
                    Rad.getAval().setCodTipBien(Integer.parseInt(mBAvaluo.getCodTipoBienRadic()));
                    mBAvaluo.setMatriculasPredio(mBAvaluo.getMatriculaAvalRadic().split(" "));
                    int tamañoMatric = mBAvaluo.getMatriculasPredio()[0].length();
                    /* for (int i = 0; i <= mBAvaluo.getMatriculasPredio().length - 1; i++) {
                     if (mBAvaluo.getMatriculasPredio()[i].length() > tamañoMatric) {
                     int j = i + 1;
                     mbTodero.setMens("El tamaño de la matricula " + j + " es mayor a la primera registrada");

                     mbTodero.info();
                     return;
                     } else if (mBAvaluo.getMatriculasPredio()[i].length() < tamañoMatric) {
                     int j = i + 1;
                     mbTodero.setMens("El tamaño de la matricula " + j + " es menor a la primera registrada");
                     mbTodero.info();
                     return;
                     }
                     }*/
                    Rad.getAval().setMarcaMaquinaria("");
                    Rad.getAval().setModeloMaquinaria("");
                    Rad.getAval().setSerieMaquinaria("");
                    Rad.getAval().setObservacionMaquinaria("");
                    Rad.getAval().setObservacionMueble("");
                    break;
                case "2":
                    Rad.getAval().setCodTipPredio(0);
                    Rad.getAval().setCodTipBien(Integer.parseInt(mBAvaluo.getCodTipoBienRadic()));

                    Rad.getAval().setMarcaMaquinaria(mBAvaluo.getMarcaMaquinRadic());
                    Rad.getAval().setModeloMaquinaria(mBAvaluo.getModeloMaquinRadic());
                    Rad.getAval().setSerieMaquinaria(mBAvaluo.getSerieAvalRadic());
                    Rad.getAval().setObservacionMaquinaria(mBAvaluo.getObservacMaquinRadic());
                    Rad.getAval().setObservacionMueble("");
                    break;
                case "3":
                    Rad.getAval().setCodTipBien(0);
                    Rad.getAval().setCodTipPredio(0);
                    Rad.getAval().setMarcaMaquinaria("");
                    Rad.getAval().setModeloMaquinaria("");
                    Rad.getAval().setSerieMaquinaria("");
                    Rad.getAval().setObservacionMaquinaria("");
                    Rad.getAval().setObservacionMueble(mBAvaluo.getObservacAvalRadic());
                    break;
            }
            if (cod_preRadica == 0) {

            } else {
                opcionAsosiarPreRadic = "Si_Asociar";
            }

            if ("Si_Asociar".equals(opcionAsosiarPreRadic)) {
                Dato = Rad.InsertarRadic(1, codEmp, mBsesion.codigoMiSesion());
            } else {
                Dato = Rad.InsertarRadic(0, codEmp, mBsesion.codigoMiSesion());
            }
            if (Dato.next()) {
                cod_avaluo = Dato.getInt("Cod_Avaluo");
                cod_bien_seguimiento = Dato.getInt("Numero_Bien_Seguimiento");
                estadoRad = Dato.getString("Estado_Avaluo");

                if ("1".equals(mBAvaluo.getFkCodTipAvaluoRadic())) {
                    cod_predio_matriculas = Dato.getInt("Cod_Predio");
                }

            }
            Conexion.Conexion.CloseCon();
            if ("1".equals(mBAvaluo.getFkCodTipAvaluoRadic())) {
                for (int i = 0; i <= mBAvaluo.getMatriculasPredio().length - 1; i++) {
                    Rad.InsertarMatriculasPredio(mBAvaluo.getMatriculasPredio()[i], cod_predio_matriculas);
                }
            }

            //Registro de clientes solicitante
            if (mBCliente.isEstadoPanelClienteGeneral()) {
                if ("".equals(mBCliente.getNumeroDoccliente1()) || mBCliente.getNumeroDoccliente1() == null) {

                } else {
                    Dat = Cli.ConsulCodCli(mBCliente.getNumeroDoccliente1());
                    if (Dat.next()) {
                        cod_persona = Dat.getInt("Cod_Cliente");
                    }
                    Conexion.Conexion.CloseCon();
                    if (mBCliente.isClientFact1()) {
                        tipo_Persona = "T";
                        if ("Si_Cli1".equals(mBCliente.getOpcionRadioTarifasPactadosCli1())) {
                            tarifas_pactadas = 1;
                            observacionRegistro = "Valor pactado con el cliente: " + cod_persona + " asociado con el avaluo " + cod_avaluo;
                            if ("Porcentaje".equals(mBCliente.getTipoTarifaCli1())) {
                                tipo_tarifa_pactadas = "P";
                            } else {
                                tipo_tarifa_pactadas = "V";
                            }
                            if (mBCliente.getValorTarifaCli1().contains(".")) {
                                valor_tarifas_pactadas = Double.parseDouble(mBCliente.getValorTarifaCli1().replace(".", ""));
                            } else {
                                valor_tarifas_pactadas = Double.parseDouble(mBCliente.getValorTarifaCli1().replace(",", ""));
                            }

                        } else {
                            observacionRegistro = "";
                            tipo_tarifa_pactadas = "";
                            valor_tarifas_pactadas = 0;
                            tarifas_pactadas = 0;
                        }
                    } else {
                        tipo_Persona = "S";
                    }
                    Rad.InsertarInfoAval(1, tarifas_pactadas, cod_avaluo, tipo_Persona, cod_persona, tipo_tarifa_pactadas, valor_tarifas_pactadas, observacionRegistro, mBsesion.codigoMiSesion());
                    limpiarVariablesRegistroRadic();

                }

                if ("".equals(mBCliente.getNumeroDoccliente2()) || mBCliente.getNumeroDoccliente2() == null) {

                } else {
                    Dat = Cli.ConsulCodCli(mBCliente.getNumeroDoccliente2());
                    if (Dat.next()) {
                        cod_persona = Dat.getInt("Cod_Cliente");
                    }
                    Conexion.Conexion.CloseCon();
                    if (mBCliente.isClientFact2()) {
                        tipo_Persona = "T";
                        if ("Si_Cli2".equals(mBCliente.getOpcionRadioTarifasPactadosCli2())) {
                            tarifas_pactadas = 1;
                            observacionRegistro = "Valor pactado con el cliente: " + cod_persona + " asociado con el avaluo " + cod_avaluo;
                            if ("Porcentaje".equals(mBCliente.getTipoTarifaCli2())) {
                                tipo_tarifa_pactadas = "P";
                            } else {
                                tipo_tarifa_pactadas = "V";
                            }
                            if (mBCliente.getValorTarifaCli2().contains(".")) {
                                valor_tarifas_pactadas = Double.parseDouble(mBCliente.getValorTarifaCli2().replace(".", ""));
                            } else {
                                valor_tarifas_pactadas = Double.parseDouble(mBCliente.getValorTarifaCli2().replace(",", ""));
                            }

                        } else {
                            observacionRegistro = "";
                            tipo_tarifa_pactadas = "";
                            valor_tarifas_pactadas = 0;
                            tarifas_pactadas = 0;
                        }
                    } else {
                        tipo_Persona = "S";
                    }
                    Rad.InsertarInfoAval(1, tarifas_pactadas, cod_avaluo, tipo_Persona, cod_persona, tipo_tarifa_pactadas, valor_tarifas_pactadas, observacionRegistro, mBsesion.codigoMiSesion());
                    limpiarVariablesRegistroRadic();
                }

                if ("".equals(mBCliente.getNumeroDoccliente3()) || mBCliente.getNumeroDoccliente3() == null) {

                } else {
                    Dat = Cli.ConsulCodCli(mBCliente.getNumeroDoccliente3());
                    if (Dat.next()) {
                        cod_persona = Dat.getInt("Cod_Cliente");
                    }
                    Conexion.Conexion.CloseCon();
                    if (mBCliente.isClientFact3()) {
                        tipo_Persona = "T";
                        if ("Si_Cli3".equals(mBCliente.getOpcionRadioTarifasPactadosCli3())) {
                            tarifas_pactadas = 1;
                            observacionRegistro = "Valor pactado con el cliente: " + cod_persona + " asociado con el avaluo " + cod_avaluo;
                            if ("Porcentaje".equals(mBCliente.getTipoTarifaCli3())) {
                                tipo_tarifa_pactadas = "P";
                            } else {
                                tipo_tarifa_pactadas = "V";
                            }
                            if (mBCliente.getValorTarifaCli3().contains(".")) {
                                valor_tarifas_pactadas = Double.parseDouble(mBCliente.getValorTarifaCli3().replace(".", ""));
                            } else {
                                valor_tarifas_pactadas = Double.parseDouble(mBCliente.getValorTarifaCli3().replace(",", ""));
                            }

                        } else {
                            observacionRegistro = "";
                            tipo_tarifa_pactadas = "";
                            valor_tarifas_pactadas = 0;
                            tarifas_pactadas = 0;
                        }
                    } else {
                        tipo_Persona = "S";
                    }
                    Rad.InsertarInfoAval(1, tarifas_pactadas, cod_avaluo, tipo_Persona, cod_persona, tipo_tarifa_pactadas, valor_tarifas_pactadas, observacionRegistro, mBsesion.codigoMiSesion());
                    limpiarVariablesRegistroRadic();
                }

                if ("".equals(mBCliente.getNumeroDoccliente4()) || mBCliente.getNumeroDoccliente4() == null) {

                } else {
                    Dat = Cli.ConsulCodCli(mBCliente.getNumeroDoccliente4());
                    if (Dat.next()) {
                        cod_persona = Dat.getInt("Cod_Cliente");
                    }
                    Conexion.Conexion.CloseCon();
                    if (mBCliente.isClientFact4()) {
                        tipo_Persona = "T";
                        if ("Si_Cli4".equals(mBCliente.getOpcionRadioTarifasPactadosCli4())) {
                            tarifas_pactadas = 1;
                            observacionRegistro = "Valor pactado con el cliente: " + cod_persona + " asociado con el avaluo " + cod_avaluo;
                            if ("Porcentaje".equals(mBCliente.getTipoTarifaCli4())) {
                                tipo_tarifa_pactadas = "P";
                            } else {
                                tipo_tarifa_pactadas = "V";
                            }
                            if (mBCliente.getValorTarifaCli4().contains(".")) {
                                valor_tarifas_pactadas = Double.parseDouble(mBCliente.getValorTarifaCli4().replace(".", ""));
                            } else {
                                valor_tarifas_pactadas = Double.parseDouble(mBCliente.getValorTarifaCli4().replace(",", ""));
                            }

                        } else {
                            observacionRegistro = "";
                            tipo_tarifa_pactadas = "";
                            valor_tarifas_pactadas = 0;
                            tarifas_pactadas = 0;
                        }
                    } else {
                        tipo_Persona = "S";
                    }
                    Rad.InsertarInfoAval(1, tarifas_pactadas, cod_avaluo, tipo_Persona, cod_persona, tipo_tarifa_pactadas, valor_tarifas_pactadas, observacionRegistro, mBsesion.codigoMiSesion());
                    limpiarVariablesRegistroRadic();
                }

                if ("".equals(mBCliente.getNumeroDoccliente5()) || mBCliente.getNumeroDoccliente5() == null) {

                } else {
                    Dat = Cli.ConsulCodCli(mBCliente.getNumeroDoccliente5());
                    if (Dat.next()) {
                        cod_persona = Dat.getInt("Cod_Cliente");
                    }
                    Conexion.Conexion.CloseCon();
                    if (mBCliente.isClientFact5()) {
                        tipo_Persona = "T";
                        if ("Si_Cli5".equals(mBCliente.getOpcionRadioTarifasPactadosCli5())) {
                            tarifas_pactadas = 1;
                            observacionRegistro = "Valor pactado con el cliente: " + cod_persona + " asociado con el avaluo " + cod_avaluo;
                            if ("Porcentaje".equals(mBCliente.getTipoTarifaCli5())) {
                                tipo_tarifa_pactadas = "P";
                            } else {
                                tipo_tarifa_pactadas = "V";
                            }
                            if (mBCliente.getValorTarifaCli5().contains(".")) {
                                valor_tarifas_pactadas = Double.parseDouble(mBCliente.getValorTarifaCli5().replace(".", ""));
                            } else {
                                valor_tarifas_pactadas = Double.parseDouble(mBCliente.getValorTarifaCli5().replace(",", ""));
                            }

                        } else {
                            observacionRegistro = "";
                            tipo_tarifa_pactadas = "";
                            valor_tarifas_pactadas = 0;
                            tarifas_pactadas = 0;
                        }
                    } else {
                        tipo_Persona = "S";
                    }
                    Rad.InsertarInfoAval(1, tarifas_pactadas, cod_avaluo, tipo_Persona, cod_persona, tipo_tarifa_pactadas, valor_tarifas_pactadas, observacionRegistro, mBsesion.codigoMiSesion());
                    limpiarVariablesRegistroRadic();
                }

            }
            //Registro de entidad
            if (mBEntidad.isEstPanelEntidad()) {

                if ("".equals(mBEntidad.getCodAsesor1()) || mBEntidad.getCodAsesor1() == null) {

                } else {
                    cod_persona = Integer.parseInt(mBEntidad.getCodAsesor1());
                    if (mBEntidad.isEntidadFact1()) {
                        tipo_Persona = "T";
                        if ("Si_Enti1".equals(mBEntidad.getOpcionRadioTarifasPactadosEnti1())) {
                            tarifas_pactadas = 1;
                            observacionRegistro = "Valor pactado con la entidad: " + cod_persona + " asociado con el avaluo " + cod_avaluo;
                            if ("Porcentaje".equals(mBEntidad.getTipoTarifaEnti1())) {
                                tipo_tarifa_pactadas = "P";
                            } else {
                                tipo_tarifa_pactadas = "V";
                            }
                            if (mBEntidad.getValorTarifaEnti1().contains(".")) {
                                valor_tarifas_pactadas = Double.parseDouble(mBEntidad.getValorTarifaEnti1().replace(".", ""));
                            } else {
                                valor_tarifas_pactadas = Double.parseDouble(mBEntidad.getValorTarifaEnti1().replace(",", ""));
                            }

                        } else {
                            observacionRegistro = "";
                            tipo_tarifa_pactadas = "";
                            valor_tarifas_pactadas = 0;
                            tarifas_pactadas = 0;
                        }
                    } else {
                        tipo_Persona = "S";
                    }
                    Rad.InsertarInfoAval(2, tarifas_pactadas, cod_avaluo, tipo_Persona, cod_persona, tipo_tarifa_pactadas, valor_tarifas_pactadas, observacionRegistro, mBsesion.codigoMiSesion());
                    limpiarVariablesRegistroRadic();
                }

                if ("".equals(mBEntidad.getCodAsesor2()) || mBEntidad.getCodAsesor2() == null) {

                } else {
                    cod_persona = Integer.parseInt(mBEntidad.getCodAsesor2());
                    if (mBEntidad.isEntidadFact2()) {
                        tipo_Persona = "T";
                        if ("Si_Enti2".equals(mBEntidad.getOpcionRadioTarifasPactadosEnti2())) {
                            tarifas_pactadas = 1;
                            observacionRegistro = "Valor pactado con la entidad: " + cod_persona + " asociado con el avaluo " + cod_avaluo;
                            if ("Porcentaje".equals(mBEntidad.getTipoTarifaEnti2())) {
                                tipo_tarifa_pactadas = "P";
                            } else {
                                tipo_tarifa_pactadas = "V";
                            }
                            if (mBEntidad.getValorTarifaEnti2().contains(".")) {
                                valor_tarifas_pactadas = Double.parseDouble(mBEntidad.getValorTarifaEnti2().replace(".", ""));
                            } else {
                                valor_tarifas_pactadas = Double.parseDouble(mBEntidad.getValorTarifaEnti2().replace(",", ""));
                            }

                        } else {
                            observacionRegistro = "";
                            tipo_tarifa_pactadas = "";
                            valor_tarifas_pactadas = 0;
                            tarifas_pactadas = 0;
                        }
                    } else {
                        tipo_Persona = "S";
                    }
                    Rad.InsertarInfoAval(2, tarifas_pactadas, cod_avaluo, tipo_Persona, cod_persona, tipo_tarifa_pactadas, valor_tarifas_pactadas, observacionRegistro, mBsesion.codigoMiSesion());
                    limpiarVariablesRegistroRadic();
                }

                if ("".equals(mBEntidad.getCodAsesor3()) || mBEntidad.getCodAsesor3() == null) {

                } else {
                    cod_persona = Integer.parseInt(mBEntidad.getCodAsesor3());
                    if (mBEntidad.isEntidadFact3()) {
                        tipo_Persona = "T";
                        if ("Si_Enti3".equals(mBEntidad.getOpcionRadioTarifasPactadosEnti3())) {
                            tarifas_pactadas = 1;
                            observacionRegistro = "Valor pactado con la entidad: " + cod_persona + " asociado con el avaluo " + cod_avaluo;
                            if ("Porcentaje".equals(mBEntidad.getTipoTarifaEnti3())) {
                                tipo_tarifa_pactadas = "P";
                            } else {
                                tipo_tarifa_pactadas = "V";
                            }
                            if (mBEntidad.getValorTarifaEnti3().contains(".")) {
                                valor_tarifas_pactadas = Double.parseDouble(mBEntidad.getValorTarifaEnti3().replace(".", ""));
                            } else {
                                valor_tarifas_pactadas = Double.parseDouble(mBEntidad.getValorTarifaEnti3().replace(",", ""));
                            }

                        } else {
                            observacionRegistro = "";
                            tipo_tarifa_pactadas = "";
                            valor_tarifas_pactadas = 0;
                            tarifas_pactadas = 0;
                        }
                    } else {
                        tipo_Persona = "S";
                    }
                    Rad.InsertarInfoAval(2, tarifas_pactadas, cod_avaluo, tipo_Persona, cod_persona, tipo_tarifa_pactadas, valor_tarifas_pactadas, observacionRegistro, mBsesion.codigoMiSesion());
                    limpiarVariablesRegistroRadic();
                }

                if ("".equals(mBEntidad.getCodAsesor4()) || mBEntidad.getCodAsesor4() == null) {

                } else {
                    cod_persona = Integer.parseInt(mBEntidad.getCodAsesor4());
                    if (mBEntidad.isEntidadFact4()) {
                        tipo_Persona = "T";
                        if ("Si_Enti4".equals(mBEntidad.getOpcionRadioTarifasPactadosEnti4())) {
                            tarifas_pactadas = 1;
                            observacionRegistro = "Valor pactado con la entidad: " + cod_persona + " asociado con el avaluo " + cod_avaluo;
                            if ("Porcentaje".equals(mBEntidad.getTipoTarifaEnti3())) {
                                tipo_tarifa_pactadas = "P";
                            } else {
                                tipo_tarifa_pactadas = "V";
                            }
                            if (mBEntidad.getValorTarifaEnti4().contains(".")) {
                                valor_tarifas_pactadas = Double.parseDouble(mBEntidad.getValorTarifaEnti4().replace(".", ""));
                            } else {
                                valor_tarifas_pactadas = Double.parseDouble(mBEntidad.getValorTarifaEnti4().replace(",", ""));
                            }

                        } else {
                            observacionRegistro = "";
                            tipo_tarifa_pactadas = "";
                            valor_tarifas_pactadas = 0;
                            tarifas_pactadas = 0;
                        }
                    } else {
                        tipo_Persona = "S";
                    }
                    Rad.InsertarInfoAval(2, tarifas_pactadas, cod_avaluo, tipo_Persona, cod_persona, tipo_tarifa_pactadas, valor_tarifas_pactadas, observacionRegistro, mBsesion.codigoMiSesion());
                    limpiarVariablesRegistroRadic();
                }

                if ("".equals(mBEntidad.getCodAsesor5()) || mBEntidad.getCodAsesor5() == null) {

                } else {
                    cod_persona = Integer.parseInt(mBEntidad.getCodAsesor5());
                    if (mBEntidad.isEntidadFact5()) {
                        tipo_Persona = "T";
                        if ("Si_Enti5".equals(mBEntidad.getOpcionRadioTarifasPactadosEnti5())) {
                            tarifas_pactadas = 1;
                            observacionRegistro = "Valor pactado con la entidad: " + cod_persona + " asociado con el avaluo " + cod_avaluo;
                            if ("Porcentaje".equals(mBEntidad.getTipoTarifaEnti5())) {
                                tipo_tarifa_pactadas = "P";
                            } else {
                                tipo_tarifa_pactadas = "V";
                            }
                            if (mBEntidad.getValorTarifaEnti5().contains(".")) {
                                valor_tarifas_pactadas = Double.parseDouble(mBEntidad.getValorTarifaEnti5().replace(".", ""));
                            } else {
                                valor_tarifas_pactadas = Double.parseDouble(mBEntidad.getValorTarifaEnti5().replace(",", ""));
                            }

                        } else {
                            observacionRegistro = "";
                            tipo_tarifa_pactadas = "";
                            valor_tarifas_pactadas = 0;
                            tarifas_pactadas = 0;
                        }
                    } else {
                        tipo_Persona = "S";
                    }
                    Rad.InsertarInfoAval(2, tarifas_pactadas, cod_avaluo, tipo_Persona, cod_persona, tipo_tarifa_pactadas, valor_tarifas_pactadas, observacionRegistro, mBsesion.codigoMiSesion());
                    limpiarVariablesRegistroRadic();
                }

            }
            //Registro de perito
            if (mBPerito.getListPeritAvaluadoresSeleccionadosForm().size() > 0) {

                for (int i = 0; i <= mBPerito.getListPeritAvaluadoresSeleccionadosForm().size() - 1; i++) {
                    try {
                        Dat = Per.ConsultaCodPerito(2, mBPerito.getListPeritAvaluadoresSeleccionadosForm().get(i).getNumDocPerito());
                        if (Dat.next()) {
                            cod_persona = Dat.getInt("Cod_Perito");
                        }
                        Conexion.Conexion.CloseCon();
                        if (i == 0) {
                            if ("Si_Perito1".equals(mBPerito.getOpcionRadioTarifasPactadosPer1())) {
                                tarifas_pactadas = 1;
                                observacionRegistro = "Valor pactado con el avaluador: " + cod_persona + " asociado con el avaluo " + cod_avaluo;
                                if ("Porcentaje".equals(mBPerito.getTipoTarifaPeri1())) {
                                    tipo_tarifa_pactadas = "P";
                                } else {
                                    tipo_tarifa_pactadas = "V";
                                }
                                if (mBPerito.getValorTarifaPer1().contains(".")) {
                                    valor_tarifas_pactadas = Double.parseDouble(mBPerito.getValorTarifaPer1().replace(".", ""));
                                } else {
                                    valor_tarifas_pactadas = Double.parseDouble(mBPerito.getValorTarifaPer1().replace(",", ""));
                                }

                            } else {
                                observacionRegistro = "";
                                tipo_tarifa_pactadas = "";
                                valor_tarifas_pactadas = 0;
                                tarifas_pactadas = 0;
                            }
                        } else if (i == 1) {
                            if ("Si_Perito2".equals(mBPerito.getOpcionRadioTarifasPactadosPer2())) {
                                tarifas_pactadas = 1;
                                observacionRegistro = "Valor pactado con el avaluador: " + cod_persona + " asociado con el avaluo " + cod_avaluo;
                                if ("Porcentaje".equals(mBPerito.getTipoTarifaPeri2())) {
                                    tipo_tarifa_pactadas = "P";
                                } else {
                                    tipo_tarifa_pactadas = "V";
                                }
                                if (mBPerito.getValorTarifaPer2().contains(".")) {
                                    valor_tarifas_pactadas = Double.parseDouble(mBPerito.getValorTarifaPer2().replace(".", ""));
                                } else {
                                    valor_tarifas_pactadas = Double.parseDouble(mBPerito.getValorTarifaPer2().replace(",", ""));
                                }

                            } else {
                                observacionRegistro = "";
                                tipo_tarifa_pactadas = "";
                                valor_tarifas_pactadas = 0;
                                tarifas_pactadas = 0;
                            }
                        } else if (i == 2) {
                            if ("Si_Perito3".equals(mBPerito.getOpcionRadioTarifasPactadosPer3())) {
                                tarifas_pactadas = 1;
                                observacionRegistro = "Valor pactado con el avaluador: " + cod_persona + " asociado con el avaluo " + cod_avaluo;
                                if ("Porcentaje".equals(mBPerito.getTipoTarifaPeri3())) {
                                    tipo_tarifa_pactadas = "P";
                                } else {
                                    tipo_tarifa_pactadas = "V";
                                }
                                if (mBPerito.getValorTarifaPer3().contains(".")) {
                                    valor_tarifas_pactadas = Double.parseDouble(mBPerito.getValorTarifaPer3().replace(".", ""));
                                } else {
                                    valor_tarifas_pactadas = Double.parseDouble(mBPerito.getValorTarifaPer3().replace(",", ""));
                                }

                            } else {
                                observacionRegistro = "";
                                tipo_tarifa_pactadas = "";
                                valor_tarifas_pactadas = 0;
                                tarifas_pactadas = 0;
                            }
                        } else if (i == 3) {
                            if ("Si_Perito4".equals(mBPerito.getOpcionRadioTarifasPactadosPer4())) {
                                tarifas_pactadas = 1;
                                observacionRegistro = "Valor pactado con el avaluador: " + cod_persona + " asociado con el avaluo " + cod_avaluo;
                                if ("Porcentaje".equals(mBPerito.getTipoTarifaPeri4())) {
                                    tipo_tarifa_pactadas = "P";
                                } else {
                                    tipo_tarifa_pactadas = "V";
                                }
                                if (mBPerito.getValorTarifaPer4().contains(".")) {
                                    valor_tarifas_pactadas = Double.parseDouble(mBPerito.getValorTarifaPer4().replace(".", ""));
                                } else {
                                    valor_tarifas_pactadas = Double.parseDouble(mBPerito.getValorTarifaPer4().replace(",", ""));
                                }

                            } else {
                                observacionRegistro = "";
                                tipo_tarifa_pactadas = "";
                                valor_tarifas_pactadas = 0;
                                tarifas_pactadas = 0;
                            }
                        } else if (i == 4) {
                            if ("Si_Perito5".equals(mBPerito.getOpcionRadioTarifasPactadosPer5())) {
                                tarifas_pactadas = 1;
                                observacionRegistro = "Valor pactado con el avaluador: " + cod_persona + " asociado con el avaluo " + cod_avaluo;
                                if ("Porcentaje".equals(mBPerito.getTipoTarifaPeri5())) {
                                    tipo_tarifa_pactadas = "P";
                                } else {
                                    tipo_tarifa_pactadas = "V";
                                }
                                if (mBPerito.getValorTarifaPer5().contains(".")) {
                                    valor_tarifas_pactadas = Double.parseDouble(mBPerito.getValorTarifaPer5().replace(".", ""));
                                } else {
                                    valor_tarifas_pactadas = Double.parseDouble(mBPerito.getValorTarifaPer5().replace(",", ""));
                                }

                            } else {
                                observacionRegistro = "";
                                tipo_tarifa_pactadas = "";
                                valor_tarifas_pactadas = 0;
                                tarifas_pactadas = 0;
                            }
                        }
                    } catch (Exception e) {
                        mbTodero.setMens("Error Registro Perito" + e);
                        mbTodero.info();
                    }

                    Rad.InsertarInfoAval(3, tarifas_pactadas, cod_avaluo, "", cod_persona, tipo_tarifa_pactadas, valor_tarifas_pactadas, observacionRegistro, mBsesion.codigoMiSesion());
                    limpiarVariablesRegistroRadic();

                }
                mbTodero.setMens("Radicación Ingresada");
                mbTodero.info();
                mbTodero.setMens("Nº de Avaluo: " + cod_avaluo);
                mbTodero.info();

                //Variables para cargue_archivos y carta_perito
                mBArchivo.setNAvaluo(cod_avaluo);
                mBArchivo.consultaArchivosDesdePreRad();
                mBArchivo.setTipoAvaluo(mBAvaluo.getNombreTipAvaluoRadic());
                mBArchivo.setNBien(cod_bien_seguimiento);
                mBArchivo.NumerAvaluo(mBAvaluo.getNombreTipAvaluoRadic());
                mBArchivo.Limp(Integer.parseInt(mBAvaluo.getFkCodTipAvaluoRadic()));
                estadoTituloRadicacion = true;
                this.ListaArcRegla = new ArrayList<>();
                try {
                    ListaArcRegla = Adm.ConsulListaReglasProceso(1, Integer.parseInt(codTipProEnt), mBArchivo.NumerAvaluo(mBAvaluo.getNombreTipAvaluoRadic()), "Radicacion");
                } catch (SQLException ex) {
                    Logger.getLogger(BeanRadicacion.class.getName()).log(Level.SEVERE, null, ex);
                }
                //limpiarRadicacionGeneral();
                RequestContext.getCurrentInstance().execute("PF('DialogVerifiInfoRadic').hide()");

                RequestContext.getCurrentInstance().execute("PF('tabRadicacion').disable(0)");
                RequestContext.getCurrentInstance().execute("PF('tabRadicacion').disable(1)");
                RequestContext.getCurrentInstance().execute("PF('tabRadicacion').disable(2)");
                RequestContext.getCurrentInstance().execute("PF('tabRadicacion').disable(3)");
                RequestContext.getCurrentInstance().execute("PF('tabRadicacion').disable(4)");
                RequestContext.getCurrentInstance().execute("PF('tabRadicacion').enable(5)");
                RequestContext.getCurrentInstance().execute("PF('tabRadicacion').select(5)");

            }

        } catch (SQLException e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".registrarRadicacion()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    public void consultaArchivosSubidos() {
        int TipoA = 0;
        String ValArch = "";
        try {
            if (ListaArcRegla.size() <= 0) {
                estadoValidacionInfoArchivos = true;
            } else {
                switch (mBAvaluo.getNombreTipAvaluoRadic()) {
                    case "Predio":
                        ValArch = "Sol_Pre";
                        break;
                    case "Maquinaria":
                        ValArch = "Sol_Maq";
                        break;
                    case "Enser":
                        ValArch = "Sol_Ens";
                        break;
                }
                List<LogCargueArchivos> ListaRadica = new ArrayList<>();
                ListaRadica = Archi.ConsultaArchivAvaluoRadica(1, cod_avaluo, cod_bien_seguimiento);
                int C = 0;
                for (int i = 0; i <= ListaArcRegla.size() - 1; i++) {
                    for (int j = 0; j <= ListaRadica.size() - 1; j++) {
                        if (ListaArcRegla.get(i).getCodigoParametro() == ListaRadica.get(j).getCodTipArchivo()) {
                            C += 1;
                        }
                    }
                }
                if (C != ListaArcRegla.size()) {
                    mbTodero.setMens("Hay archivos obligatorios del proceso que faltan por subir; favor verifique nuevamente y realice el posterior cargue de lo(s) documento(s)");
                    mbTodero.warn();
                    enviarDocumentosEmpreYArchAval = false;
                    estadoValidacionInfoArchivos = false;
                } else {
                    estadoValidacionInfoArchivos = true;
                }
            }
            if (estadoValidacionInfoArchivos == true) {

                ListaArchivosRadiCorreo = new ArrayList<>();
                ListaArchivosRadiCorreo = mBArchivo.MostrarArchivos(6, File.separator + "DBARCHIVOS" + File.separator + "DBISA" + File.separator + "DBAdjuntos" + File.separator + "Radicacion" + File.separator);
                mBArchivo.setNAvaluo(cod_avaluo);
                mBArchivo.setNBien(cod_bien_seguimiento);
                mBArchivo.setTipoAvaluo(mBAvaluo.getNombreTipAvaluoRadic());
                mBArchivo.setOpcionPanel("SelAva");//Seleccional del tipo de panel para enviar documentos al perito en radicacion
                mBArchivo.seleccionPanel(cod_avaluo);//Para vargar la lista de los Archivos los cuales van a estar dentro de las listas que se envian de documentos del perito
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".consultaArchivosSubidos()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    public void limpiarRadicacionGeneral() {
        try {
            //otras variables
            radicacionRegistro = false;
            radicacionModificacion = false;
            radicacionVerInfo = false;

            //Limpiar titulos
            estadoTituloRadicacion = false;

            cod_avaluo = 0;
            estadoRad = "";

            cod_preRadica = 0;
            estadoPreRad = "";

            //tab observaciones
            ListObserPreRadicados = new ArrayList<>();
            observacionInicio = "";

            //tab informacion de solicitud , cliente y entidad
            fechaSoli = "";
            fechaSolicRadic = null;
            HoraSol = null;
            HoraSolisitud = "";
            provieneCotizacion = false;
            estadoComponentesRadicPreRad = false;

            numeroHojas = "1";
            solicitante = "";
            envioInformacion = "";
            codProEnt = "";
            nombreProEnt = "";
            codTipProEnt = "";
            nombreTipProEnt = "";
            TipProEnt.clear();
            requierUbicacion = false;

            mBAvaluo.setEstadoVisibleCombosUbiSolic(false);
            requierUbicacion = false;
            mBAvaluo.setCodDeptoSol("");
            mBAvaluo.setNombreDeptoSol("");
            mBAvaluo.setCodCiudadSol("");
            mBAvaluo.setNombreCiudadSol("");
            mBAvaluo.getCargaCiuSolic().clear();

            //Cliente
            mBCliente.setOpcionMostrarCliSolicitante("");
            mBCliente.setEstadoSeparadorClienteEnti(true);
            mBCliente.setEstadoPanelRadiosCli(true);

            mBCliente.setEstadoPanelClienteGeneral(false);

            //Todas las cajas de cliente
            limpiarClientes();

            //dialog que muestra los clientes
            mBCliente.setListClient(new ArrayList<LogCliente>());
            mBCliente.setCli(new LogCliente());

            //dialog para registro de clientes
            mBCliente.limpiarCajasRegistroCli();

            //mBCliente.getTipDocEmp().clear();
            mBCliente.setEstadoNumDocCliTemp(false);

            mBCliente.getCargaCiuClie().clear();

            mBCliente.getCargaRegimenCli().clear();

            mBCliente.setTipoTarifaCli("");
            mBCliente.setValorTarifaCli("");

            //Temporal
            mBCliente.setEstadoTablaClientesTempo(false);
            mBCliente.setCliTemp(new LogCliente());
            mBCliente.setValidarClienteTempo(false);

            mBCliente.setEstadoNumDocCliTemp(false);

            /// fin clientes ----------------------------------------- ----------------------------
            // entidades ----------------/-------------------------/------------
            //Entidad
            mBEntidad.setOpcionRadiosMostrarEntidad(true);
            mBEntidad.setOpcionMostrarEntidad("");
            mBEntidad.setEstPanelEntidad(false);

            //todas las entidades
            limpiarEntidades();

            /// fin entidades --------\--------------------\-----------------------
            // tab de informacion de bien
            mBAvaluo.setEstadoTipoAvalRadic(false);
            mBAvaluo.setEstadoPanelAvalRadic(false);
            // mBAvaluo.setCodTipAvaluo(0);
            //   mBAvaluo.limpiarCajasRegistroBien();        

            mBEntidad.setEstadoVisibleEnti1(false);
            mBEntidad.setEstadoVisibleEnti2(false);
            mBEntidad.setEstadoVisibleEnti3(false);
            mBEntidad.setEstadoVisibleEnti4(false);
            mBEntidad.setEstadoVisibleEnti5(false);

            mBAvaluo.setCod_AvaluoRadic(0);

            //Predio
            mBAvaluo.setEstadoInfoPred(false);

            mBAvaluo.setNombreTipoPredioRadic("");
            mBAvaluo.setValorAreaTerrenoRadic("");
            mBAvaluo.setNombreTipoPredioRadic("");

            //Maquinaria       
            mBAvaluo.setEstadoInfoMaqui(false);
            mBAvaluo.setMarcaMaquinRadic("");
            mBAvaluo.setModeloMaquinRadic("");
            mBAvaluo.setObservacMaquinRadic("");

            //Mueble
            mBAvaluo.setEstadoInfoResulEnser(false);
            mBAvaluo.setObservacAval("");

            //generales bienes
            mBAvaluo.setDireccionAvalRadic("");
            mBAvaluo.setNombreDeptoAvalRadic("");
            mBAvaluo.setNombreCiudAvalRadic("");
            mBAvaluo.setBarrioAvalRadic("");

            //Bien temporal
            mBAvaluo.setEstadoTablaAvaluosTempo(false);
            mBAvaluo.setAvalTempo(new LogAvaluo());

            //consulta der bienes
            mBAvaluo.setEstadoRadiosInfoConsultaSeleccion(false);
            mBAvaluo.setOpcionConsultaBienes("");
            mBAvaluo.setEstadoPanelConsultaBienes(false);
            mBAvaluo.setEstadoRadioSeleccionConsulta("");

            mBAvaluo.setEstadoTablaConsultaPredios(false);
            mBAvaluo.setEstadoTablaConsultaMaquin(false);
            mBAvaluo.setEstadoTablaConsultaMueble(false);
            mBAvaluo.setBienPredio(new LogAvaluo());
            mBAvaluo.setBienMaquin(new LogAvaluo());
            mBAvaluo.setBienMueble(new LogAvaluo());

            //agregar/modificar bien
            mBAvaluo.setCodTipAvaluo(0);
            mBAvaluo.setEstadoCmbTipoAval(false);

            mBAvaluo.setEstadoPanel(false);
            mBAvaluo.limpiarCamposBienPredio();
            mBAvaluo.limpiarCamposBienMaquin();
            mBAvaluo.limpiarCamposBienMueble();

            //Peritos
            mBPerito.setListPeritAvaluadoresSeleccionadosForm(new ArrayList<LogPerito>());

            mBPerito.setOpcionRadioTarifasPactadosPer1("");
            mBPerito.setOpcionRadioTarifasPactadosPer2("");
            mBPerito.setOpcionRadioTarifasPactadosPer3("");
            mBPerito.setOpcionRadioTarifasPactadosPer4("");
            mBPerito.setOpcionRadioTarifasPactadosPer5("");

            mBPerito.setEstadoRadioAgregarTarifaPer1(false);
            mBPerito.setEstadoRadioAgregarTarifaPer2(false);
            mBPerito.setEstadoRadioAgregarTarifaPer3(false);
            mBPerito.setEstadoRadioAgregarTarifaPer4(false);
            mBPerito.setEstadoRadioAgregarTarifaPer5(false);

            mBPerito.setEstadoTarifasPactPer1(false);
            mBPerito.setEstadoTarifasPactPer2(false);
            mBPerito.setEstadoTarifasPactPer3(false);
            mBPerito.setEstadoTarifasPactPer4(false);
            mBPerito.setEstadoTarifasPactPer5(false);

            mBPerito.setTipoTarifaPeri1("");
            mBPerito.setTipoTarifaPeri2("");
            mBPerito.setTipoTarifaPeri3("");
            mBPerito.setTipoTarifaPeri4("");
            mBPerito.setTipoTarifaPeri5("");

            mBPerito.setValorTarifaPer1("");
            mBPerito.setValorTarifaPer2("");
            mBPerito.setValorTarifaPer3("");
            mBPerito.setValorTarifaPer4("");
            mBPerito.setValorTarifaPer5("");

            mBArchivo.getListaArchivosAvaEnt();

            mBPerito.setEstadoCambioAvaluador(false);

            //Contacto, cita y analista seguim
            opcionRadioContacto = "";
            limpiarContacto();
            CargClientesSelecc = new ArrayList<>();
            CargEntidSelecc = new ArrayList<>();

            fechaCitaAsig = "";
            horaCitaAsig = "";
            fechaCita = null;
            HoraCita = null;

            nombreEmp = "";
            observacion = "";

            //Cargue de archivos
            mBArchivo.setListaArchivosDesdePreRadic(new ArrayList<LogCargueArchivos>());
            ListaArcRegla = new ArrayList<>();

            mBArchivo.setOpcionPanel("");
            mBArchivo.setEstadoPanelArchivoCli(false);
            mBArchivo.setEstadoPanelArchivoAva(false);
            mBArchivo.setEstadoPanelArchivoAnti(false);
            mBCliente.getCargaCiuClie().clear();

            mBArchivo.getCargArchivos().clear();
            mBArchivo.setNombreArchivo(null);

            mBArchivo.getArch().setValorAnt(0);
            mBArchivo.getArch().setFechaSol(null);
            mBArchivo.getArch().setFk_CuentaBanco("");
            mBArchivo.setListaAvaAnt(new ArrayList<LogAvaluo>());

            //Envio de información
            opcionModeloCarta = "";
            mBPerito.setListPeritRadGenerar(new ArrayList<LogPerito>());

            mBCliente.setEnviarClienteCarta(false);
            mBCliente.setEnviarCartaCli1(false);
            mBCliente.setEnviarCartaCli2(false);
            mBCliente.setEnviarCartaCli3(false);
            mBCliente.setEnviarCartaCli4(false);
            mBCliente.setEnviarCartaCli5(false);
            mBCliente.setCargaListaClientesRadic(new ArrayList<LogCliente>());

            mBEntidad.setEnviarEntidadCarta(false);
            mBEntidad.setEnviarCartaEnti1(false);
            mBEntidad.setEnviarCartaEnti2(false);
            mBEntidad.setEnviarCartaEnti3(false);
            mBEntidad.setEnviarCartaEnti4(false);
            mBEntidad.setEnviarCartaEnti5(false);
            mBEntidad.setCargaEntidCarta(new ArrayList<LogEntidad>());

            enviarDocumentosEmpreYArchAval = false;

            if (ListaArchivosSelecRadiCorreo != null) {
                for (int i = 0; i <= ListaArchivosSelecRadiCorreo.length - 1; i++) {
                    ListaArchivosSelecRadiCorreo[i] = null;
                }
            }
            if (ListaArchivosSelecAvaluo != null) {
                for (int i = 0; i <= ListaArchivosSelecAvaluo.length - 1; i++) {
                    ListaArchivosSelecAvaluo[i] = null;
                }
            }

            NombreArchivoAvalAnterior = null;

            todoOkRadicacion = false;
            codMotivoAnulacion = 0;
            ObserCambioEstadoRadic = "";
            ObserCambioEstadoImpedirRadic = "";
            ObserCambioEstadoLiberarImp = "";
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".limpiarRadicacionGeneral()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    public void limpiarBotonRadicacion() {
        try {
            opcionAsosiarPreRadic = "";
            estadoRadiosOpcionesRadicacion = true;
            estadoPanelGeneral = false;
            limpiarRadicacionGeneral();
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".limpiarBotonRadicacion()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    public void limpiarVariablesRegistroRadic() {
        try {
            tarifas_pactadas = 0;
            tipo_Persona = "";
            cod_persona = 0;
            tipo_tarifa_pactadas = "";
            valor_tarifas_pactadas = 0;
            observacionRegistro = "";
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".limpiarVariablesRegistroRadic()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    public void abrirDialogRegistroEnti() {
        try {
            mBEntidad.limpiar();
            mBEntidad.limpiarCajasAsesor();
            mBEntidad.limpiarCajasEntidad();
            mBEntidad.limpiarCajasSucursales();
            mBUbicacion.cargDepto();
            Map<String, Object> options = new HashMap<>();
            options.put("modal", true);
            options.put("resizable", false);
            options.put("contentHeight", 500);
            options.put("contentWidth", 800);
            RequestContext.getCurrentInstance().openDialog("XHTML/Radicacion/SubForm-EntidadesRadic.xhtml", options, null);
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".abrirDialogRegistroEnti()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    public void validarSolicitado() {
        try {
            switch (this.solicitante) {
                case "Entidad":
                    mBEntidad.setOpcionMostrarEntidad("Si1");
                    mBEntidad.setEstPanelEntidad(true);
                    mBEntidad.setCodEntidad1("");
                    mBEntidad.setCodEntidad2("");
                    mBEntidad.setCodEntidad3("");
                    mBEntidad.setCodEntidad4("");
                    mBEntidad.setCodEntidad5("");
                    mBEntidad.setCodSucursal1("");
                    mBEntidad.setCodSucursal2("");
                    mBEntidad.setCodSucursal3("");
                    mBEntidad.setCodSucursal4("");
                    mBEntidad.setCodSucursal5("");
                    mBEntidad.setCodAsesor1("");
                    mBEntidad.setCodAsesor2("");
                    mBEntidad.setCodAsesor3("");
                    mBEntidad.setCodAsesor4("");
                    mBEntidad.setCodAsesor5("");
                    mBEntidad.setEntidadFact1(false);
                    mBEntidad.setEntidadFact2(false);
                    mBEntidad.setEntidadFact3(false);
                    mBEntidad.setEntidadFact4(false);
                    mBEntidad.setEntidadFact5(false);
                    mBCliente.setOpcionMostrarCliSolicitante("");
                    mBCliente.setEstadoPanelClienteGeneral(false);
                    break;
                case "Cliente":
                    mBCliente.setOpcionMostrarCliSolicitante("Si");
                    mBCliente.setEstadoPanelClienteGeneral(true);
                    /*mBCliente.setEstadoPanelRadiosCli(false);*/
                    mBEntidad.setOpcionMostrarEntidad("");
                    mBEntidad.setEstPanelEntidad(false);
                    /*mBEntidad.setEstPanelEntidad(false);*/
                    break;
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".validarSolicitado()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    public void cambioEstadoRad() {
        try {
            if ("".equals(Radi.getObservacionAvaluo()) || "".equals(Radi.getEstadoAvaluo())) {
                mbTodero.setMens("Falta informacion por Llenar");
                mbTodero.warn();
            } else {
                Radi.CambioEstRad(mBsesion.codigoMiSesion());
                mbTodero.setMens("La informacion ha sido guardada correctamente");
                mbTodero.info();
                mbTodero.resetTable("FRMRadicados:RadicadosTable");
                RequestContext.getCurrentInstance().execute("PF('DlgEstRadica').hide()");
                ListRadicados = Radi.ConsultasRadicacionYSeguimiento(1, mBsesion.codigoMiSesion());//VARIABLES DE SESION
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".cambioEstadoRad()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    public void agregarDatosRadic() {
        try {
            if (PreRad.getCodPreRadica() == 0) {
                mbTodero.setMens("Seleccione un numero de Pre-Radicacion");
                mbTodero.warn();
            } else {
                //inhabilita loos componentes que no se van a poder modificar
                estadoComponentesRadicPreRad = true;
                estadoRadiosOpcionesRadicacion = false;
                //Carga la informacion de la pre-radicacion
                Dat = PreRad.ConsultaPreRadicacion(PreRad.getCodPreRadica());
                if (Dat.next()) {
                    cod_preRadica = PreRad.getCodPreRadica();
                    fechaSolicRadic = Dat.getDate("Fecha_Solicitud_Preradica");
                    HoraSol = Dat.getTime("Hora_Preradica");
                    numeroHojas = Dat.getString("NumHojas_Preradica");
                    solicitante = Dat.getString("Solicitante_PreRadica");
                    if ("Otro".equals(Dat.getString("Resultado_Parametro"))) {
                        envioInformacion = Dat.getString("DetalleEnvio_PreRadica");
                    } else {
                        envioInformacion = Dat.getString("Resultado_Parametro");
                    }
                    codEnvioInformacion = Dat.getString("EnvioInformacion_Preradica");
                    nombreProEnt = Dat.getString("Nombre_ProEnt");
                    codProEnt = Dat.getString("Cod_ProEnt");
                    getConsulTipProEnt();
                    nombreTipProEnt = Dat.getString("Nombre_TipProEnt");
                    codTipProEnt = Dat.getString("Fk_Cod_TipProEnt");
                    provieneCotizacion = Dat.getBoolean("Cotizacion_PreRadica");
                    estadoObservacionRadic = true;
                    DatObser = PreRad.ConsultaObservacionesPreRad(String.valueOf(cod_preRadica));
                    ListObserPreRadicados = new ArrayList<>();
                    while (DatObser.next()) {
                        LogPreRadicacion PreRadObs = new LogPreRadicacion();
                        PreRadObs.setObservacionPreRadica(DatObser.getString("Obser"));
                        PreRadObs.setFechaObsPreRadica(DatObser.getString("Fecha"));
                        PreRadObs.setEmpleObservacionPreRadica(DatObser.getString("empleado"));
                        ListObserPreRadicados.add(PreRadObs);
                    }
                    Conexion.Conexion.CloseCon();
                    //observacion = Dat.getString("Observacion_Preradica");
                    estadoPreRad = Dat.getString("Estado_Avaluo");
                    if ("P".equals(estadoPreRad)) {
                        estadoPreRad = "En Proceso";
                    }
                    if ("".equals(Dat.getString("Fk_Cod_Ciudad")) || Dat.getString("Fk_Cod_Ciudad") == null) {
                        requierUbicacion = false;
                    } else {
                        requierUbicacion = true;
                        mBAvaluo.setNombreDeptoSol(Dat.getString("Nombre_Departamento"));
                        mBAvaluo.setCodDeptoSol(Dat.getString("Fk_Cod_Departamento"));
                        mBAvaluo.cargCiudadSolic();
                        mBAvaluo.setNombreCiudadSol(Dat.getString("Nombre_ciudad"));
                        mBAvaluo.setCodCiudadSol(Dat.getString("Fk_Cod_Ciudad"));
                    }

                }
                Conexion.Conexion.CloseCon();
                if ("En Proceso".equals(estadoPreRad)) {

                    //Carga la informacion de los clientes                        
                    mBCliente.setCod_preRadic(Rad.getCodPreRadica());
                    mBCliente.cargarClientesEnRadicacion(2);
                    //}

                    //Carga la informacion de la entidad  
                    mBEntidad.setCod_preRadic(Rad.getCodPreRadica());
                    mBEntidad.cargarEntidadesPreRadic(2);

                    //Carga la informacion de los peritos       
                    //mBPerito.setCod_preRadic(PreRad.getCodPreRadica());
                    //mBPerito.CargarInfoPeritosRadic();
                    //Carga la informacion del avaluo
                    mBAvaluo.setCod_preRadica(Rad.getCodPreRadica());
                    mBAvaluo.cargarAvaluoRadicacion(2);

                    //Pone estado true el panel general de todos los datos
                    estadoPanelGeneral = true;

                    //Cierra el dialog que tiene las pre-radicaciones
                    //RequestContext.getCurrentInstance().execute("PF('InfoPreRadicaciones').hide()");
                } else {
                    mbTodero.setMens("La Pre-Radicación seleccionada no se puede asociar a una Radicación por el estado en que se encuentra");
                    mbTodero.warn();
                }

            }
        } catch (SQLException e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".agregarDatosRadic()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    public void seleccionarAvaluo(int proceso, int num_avaluo, int num_seguimiento) {
        try {
            if (Radi == null) {
                mbTodero.setMens("Debe seleccionar un avalúo");
                mbTodero.warn();
                mbTodero.resetTable("FormMisAsignados:RadicadosSegTable");
                mbTodero.resetTable("FRMRadicados:RadicadosTable");
            } else {
                verInformacion(proceso, num_avaluo, num_seguimiento);
            }
        } catch (ParseException e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".seleccionarAvaluo()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    public void verInformacion(int proceso, int num_avaluo, int num_seguimiento) throws ParseException {
        try {

            LogRadicacion RadVerInformacion = new LogRadicacion();
            cod_avaluo = num_avaluo;
            cod_seguimiento = num_seguimiento;
            switch (proceso) {
                case 1:
                    radicacionModificacion = false;
                    radicacionRegistro = false;
                    radicacionVerInfo = true;
                    break;
                case 2:
                    radicacionModificacion = true;
                    radicacionRegistro = false;
                    radicacionVerInfo = false;
                    estadoTituloRadicacion = true;
                    break;
                case 3:
                    // Para la informacion que se desea cargar en el formulario de facturacion
                    radicacionModificacion = true;
                    radicacionRegistro = false;
                    radicacionVerInfo = false;
                    estadoTituloRadicacion = true;

                    break;
                default:
                    break;
            }

            //Carga la informacion de la radicacion
            Dat = RadVerInformacion.consultaRadicacion(cod_avaluo, cod_seguimiento);
            //verifica si la consulta retorna datos                    
            if (Dat.next()) {
                System.out.println("VALOR: " + Dat.getString("Nombre_Contacto"));

                Conexion.Conexion.CloseCon();
                mBArchivo.setNAvaluo(cod_avaluo);
                cod_bien_seguimiento = Dat.getInt("Numero_Bien_Seguimiento");
                mBArchivo.setTipoAvaluo(Dat.getString("Nombre_TipAvaluo"));
                cod_bien_seguimiento = Dat.getInt("Numero_Bien_Seguimiento");
                mBArchivo.setNBien(Dat.getInt("Numero_Bien_Seguimiento"));
                mBArchivo.NumerAvaluo(Dat.getString("Nombre_TipAvaluo"));
                mBArchivo.Limp(Dat.getInt("Fk_Cod_TipAvaluo"));
                //Valores comerciales del avaluo
                ValorPerito = Dat.getString("ValorPerito");
                ValorComercAvaluo = Dat.getLong("ValorAvaluo");
                ValComeAva = Dat.getString("valAva");
                fechaSolicRadic = Dat.getDate("Fecha_Solicitud");
                HoraSol = Dat.getTime("Hora_PreRadica");
                numeroHojas = Dat.getString("NumHojas_PreRadica");
                solicitante = Dat.getString("Solicitante_PreRadica");
                if ("Otro".equals(Dat.getString("Resultado_Parametro"))) {
                    envioInformacion = Dat.getString("DetalleEnvio_PreRadica");
                } else {
                    envioInformacion = Dat.getString("Resultado_Parametro");
                }
                codEnvioInformacion = Dat.getString("EnvioInformacion_Preradica");
                nombreProEnt = Dat.getString("Nombre_ProEnt");
                codProEnt = Dat.getString("Cod_ProEnt");
                getConsulTipProEnt();
                nombreTipProEnt = Dat.getString("Nombre_TipProEnt");
                codTipProEnt = Dat.getString("Fk_Cod_TipProEnt");
                provieneCotizacion = Dat.getBoolean("Cotizacion_PreRadica");
                estadoObservacionRadic = true;

                estadoRad = Dat.getString("Estado_Avaluo");
                ListObserRadicados = new ArrayList<>();

                DatObser = RadVerInformacion.consultaObserAvaAnulado(String.valueOf(cod_avaluo), cod_seguimiento);
                if ("ANULADO".equals(estadoRad)) {
                    LogRadicacion RadObs = new LogRadicacion();
                    RadObs.setObservacionRadic(DatObser.getString("Obser"));
                    RadObs.setFechaObservacionRadic(DatObser.getString("Fecha"));
                    RadObs.setAnalistaObservacionRadic(DatObser.getString("empleado"));
                    ListObserRadicados.add(RadObs);
                }
                Conexion.Conexion.CloseCon();
                DatObser = RadVerInformacion.consultaObserRadicacion(String.valueOf(cod_avaluo), cod_seguimiento);
                while (DatObser.next()) {
                    LogRadicacion RadObs = new LogRadicacion();
                    RadObs.setObservacionRadic(DatObser.getString("Obser"));
                    RadObs.setFechaObservacionRadic(DatObser.getString("Fecha"));
                    RadObs.setAnalistaObservacionRadic(DatObser.getString("empleado"));
                    ListObserRadicados.add(RadObs);
                }
                Conexion.Conexion.CloseCon();
                DatObser = RadVerInformacion.consultaObserRadicaPreRadica(String.valueOf(cod_avaluo), cod_seguimiento);
                while (DatObser.next()) {
                    LogRadicacion RadObs = new LogRadicacion();
                    RadObs.setObservacionRadic(DatObser.getString("Obser"));
                    RadObs.setFechaObservacionRadic(DatObser.getString("Fecha"));
                    RadObs.setAnalistaObservacionRadic(DatObser.getString("empleado"));
                    ListObserRadicados.add(RadObs);
                }
                Conexion.Conexion.CloseCon();
                //observacion = Dat.getString("Observacion_Preradica");
                nombreEmp = Dat.getString("Empleado_Seguimiento");
                if ("".equals(Dat.getString("Fk_Cod_Ciudad")) || Dat.getString("Fk_Cod_Ciudad") == null) {
                    requierUbicacion = false;
                } else {
                    requierUbicacion = true;
                    mBAvaluo.setNombreDeptoSol(Dat.getString("Nombre_Departamento"));
                    mBAvaluo.setCodDeptoSol(Dat.getString("Cod_Departamento"));
                    mBAvaluo.cargCiudadSolic();
                    mBAvaluo.setNombreCiudadSol(Dat.getString("Nombre_ciudad"));
                    mBAvaluo.setCodCiudadSol(Dat.getString("Fk_Cod_Ciudad"));
                }

                if ("EXCEDENTE".equals(estadoRad) || "EN PROCESO".equals(estadoRad) || "IMPEDIDO".equals(estadoRad) || "DEVUELTO".equals(estadoRad) || "FACTURACION".equals(estadoRad)) {
                    //Carga la informacion de los clientes                        
                    mBCliente.setCod_avaluo(cod_avaluo);
                    mBCliente.cargarClientesEnRadicacion(2);

                    //Carga la informacion de la entidad  
                    mBEntidad.setCod_avaluo(cod_avaluo);
                    mBEntidad.cargarEntidadesPreRadic(2);

                    //Carga la informacion del avaluo
                    mBAvaluo.setCod_avaluo(cod_avaluo);
                    mBAvaluo.setNum_bien_Seguimiento(cod_bien_seguimiento);
                    mBAvaluo.cargarAvaluoRadicacion(2);

                    //Carga la informacion de los peritos       
                    mBPerito.setCod_avaluo(cod_avaluo);
                    consultaAvaluoPerito();
                    mBPerito.CargarInfoPeritosRadic();

                    try {
                        System.out.println("VALOR: " + Dat.getString("Nombre_Contacto"));
                        System.out.println("VALOR 2: " + mBCliente.getNombreCliente());
                        if (Dat.getString("Nombre_Contacto").equals(mBCliente.getNombreCliente1())
                                || Dat.getString("Nombre_Contacto").equals(mBCliente.getNombreCliente2())
                                || Dat.getString("Nombre_Contacto").equals(mBCliente.getNombreCliente3())
                                || Dat.getString("Nombre_Contacto").equals(mBCliente.getNombreCliente4())
                                || Dat.getString("Nombre_Contacto").equals(mBCliente.getNombreCliente5())) {
                            opcionRadioContacto = "Cliente";
                            estadoLabelsClienteEnti = true;
                        } else if (Dat.getString("Nombre_Contacto").equals(mBEntidad.getNombreAsesor1())
                                || Dat.getString("Nombre_Contacto").equals(mBEntidad.getNombreAsesor2())
                                || Dat.getString("Nombre_Contacto").equals(mBEntidad.getNombreAsesor3())
                                || Dat.getString("Nombre_Contacto").equals(mBEntidad.getNombreAsesor4())
                                || Dat.getString("Nombre_Contacto").equals(mBEntidad.getNombreAsesor5())) {
                            opcionRadioContacto = "Entidad";
                            estadoLabelsClienteEnti = true;
                        } else {
                            opcionRadioContacto = "Otro";
                            estadoLabelsClienteEnti = false;
                        }
                    } catch (SQLException e) {
                        System.err.println("ERROR :: " + e.getMessage());
                    }

                    nombreContacto = Dat.getString("Nombre_Contacto");
                    celularContacto = Dat.getString("Celular_Contacto");
                    telefonoContacto = Dat.getString("Telefono_Contacto");
                    observacionContacto = Dat.getString("Observacion_Contacto");
                    observacionContacto = Dat.getString("Observacion_Contacto");
                    if ("Otro".equals(opcionRadioContacto)) {
                        codDeptoContacto = Dat.getString("Cod_Depto_Cont");
                        onCiudadContacto();
                        codCiudContacto = Dat.getString("Fk_Cod_Ciudad");
                    }

                    DeptoContacto = Dat.getString("Nombre_Depto_Contacto");
                    CiudContacto = Dat.getString("Nombre_Ciu_Contacto");

                    fechaCita = Dat.getDate("Fecha_Cita");

                    if (Dat.getString("Hora_Cita") == null) {
                        HoraCita = null;
                    } else {
                        HoraCita = FormatHora.parse(Dat.getString("Hora_Cita"));
                    }

                    fechaCitaAsig = Dat.getString("Fecha_Cita");
                    fechaVistoCitaAsig = Dat.getString("Visto_Cita");
                    horaCitaAsig = Dat.getString("Hora_Cita");
                    fechaCartaAsig = Dat.getString("Fecha_Carta");

                    //Carga la info del perito al que se le envio innformacion
                    mBPerito.cargainfoRadicCarta(cod_avaluo);

                    //Lista de archivos desde pre-radicacion y obligatorios
                    mBArchivo.setNAvaluo(cod_avaluo);
                    mBArchivo.consultaArchivosDesdePreRad();
                    mBArchivo.setTipoAvaluo(mBAvaluo.getNombreTipAvaluoRadic());
                    mBArchivo.setNBien(cod_bien_seguimiento);
                    mBArchivo.NumerAvaluo(mBAvaluo.getNombreTipAvaluoRadic());
                    mBArchivo.Limp(Integer.parseInt(mBAvaluo.getFkCodTipAvaluoRadic()));
                    estadoTituloRadicacion = true;
                    this.ListaArcRegla = new ArrayList<>();
                    try {
                        ListaArcRegla = Adm.ConsulListaReglasProceso(1, Integer.parseInt(codTipProEnt), mBArchivo.NumerAvaluo(mBAvaluo.getNombreTipAvaluoRadic()), "Radicacion");
                    } catch (SQLException ex) {
                        Logger.getLogger(BeanRadicacion.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    //Carga los archivos que se encuentran de clientes
                    mBCliente.CargClienteFact(cod_avaluo);
                    mBArchivo.getListaArchivosCli().clear();
                    mBArchivo.setListaArchivosCli(mBArchivo.MostrarArchivos(2, File.separator + mBArchivo.getBaseArc() + File.separator + "DBCliente"));

                    //Carga los archivos que se encuentran de avaluos
                    int NumTipAva = mBArchivo.NumerAvaluo(mBAvaluo.getNombreTipAvaluoRadic());
                    mBArchivo.setArchiv(mBArchivo.Arch.ConsulDirectorioAvaluo(NumTipAva, cod_avaluo, cod_bien_seguimiento, "DBSolicitud" + File.separator));//Path para el arhivo que contenga la ruta de la carpeta
                    mBArchivo.getListaArchivosAva().clear();
                    mBArchivo.setListaArchivosAva(mBArchivo.MostrarArchivos(1, File.separator + mBArchivo.getBaseArc() + File.separator + mBArchivo.getArchiv()));

                    mBArchivo.setListaArchivosAnt(new ArrayList<BeanArchivos>());
                    if (Dat.next()) {
                        mBArchivo.setListaArchivosAnt(mBArchivo.MostrarArchivos(5, File.separator + mBArchivo.getBaseArc() + File.separator + "DBAnticipos" + File.separator + Dat.getString("Ano") + File.separator + Dat.getString("Mes")));

                    }
                    Conexion.Conexion.CloseCon();
                    //Carga los archivos que se encuentren de infoirme-entrega 
                    mBArchivo.getListaArchivosAvaEnt().clear();
                    mBArchivo.setArchiv(mBArchivo.Arch.ConsulDirectorioAvaluo(NumTipAva, cod_avaluo, cod_bien_seguimiento, "DBInforme" + File.separator));//Path para el archivo que contenga la ruta de la carpeta
                    mBArchivo.setListaArchivosAvaEnt(mBArchivo.MostrarArchivos(3, File.separator + mBArchivo.getBaseArc() + File.separator + mBArchivo.getArchiv()));
                    String path = "";
                    //Remisiones Facturación

                    mBArchivo.setListaArchivosFact(mBArchivo.MostrarArchivos(1, path));
                }

            }
            Conexion.Conexion.CloseCon();
        } catch (SQLException e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".verInformacion()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    public void verificaCambioEstado(int op) {
        try {
            if (Radi.getCodAvaluo() == 0) {
                mbTodero.setMens("Seleccione un numero de Radicación");
                mbTodero.warn();
            } else if (op == 1) {
                RequestContext.getCurrentInstance().execute("PF('DlgEstAvaluo').show()");
            } else if (op == 2) {
                RequestContext.getCurrentInstance().execute("PF('DLGAnuAvaluo').show()");
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".verificaCambioEstado()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    public void consultasRadicacion(int tipoConsulta) {
        try {
            Radi = new LogRadicacion();
            if (tipoConsulta == 1) {
                this.ListRadicados = new ArrayList<>();
                this.ListRadicados = Radi.ConsultasRadicacionYSeguimiento(1, mBsesion.codigoMiSesion());
            } else if (tipoConsulta == 2) {
                this.ListRadicadosGestor = new ArrayList<>();
                this.ListRadicadosGestor = Radi.ConsultasRadicacionYSeguimiento(5, mBsesion.codigoMiSesion());
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".consultasRadicacion()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    public void onSelectAnalistaSeg() {
        try {
            mbTodero.resetTable("FormRadicacion:AnalistaSegTable");
            int codigo;
            Radi = new LogRadicacion();
            codigo = Integer.parseInt(codProEnt);
            this.CargEmpSeguimiento.clear();
            this.CargEmpSeguimiento = this.Radi.getConsultarAllAnalistas(codigo);
//            mbTodero.setMens("Lista cargada correctamente");
//            mbTodero.info();
            RequestContext.getCurrentInstance().execute("PF('DlgAnalistaSeg').show()");
        } catch (NumberFormatException e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".onSelectAnalistaSeg()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    public void selectAnalistaSeg() {
        try {
            if (Radi == null) {
                mbTodero.setMens("Debe seleccionar un analista");
                mbTodero.warn();
            } else {
                this.codEmp = this.Radi.getCodigo();
                this.nombreEmp = this.Radi.getNombre();
                mbTodero.setMens("Analista seleccionado correctamente");
                mbTodero.info();
                RequestContext.getCurrentInstance().execute("PF('DlgAnalistaSeg').hide()");
            }

        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".selectAnalistaSeg()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    public void consultaReasignaAvaluo() {
        try {
            if (!"".equals(codProEnt)) {
                this.ListRadicaAsignados = new ArrayList<>();
                mbTodero.resetTable("FRMAsigRadic:RadicaAsigTable");
                this.EstadoOpcionAsignado = true;
                ListRadicaAsignados = Radi.ConsultaAvaluoReAsigna(Integer.parseInt(codProEnt));
            } else {
                this.EstadoOpcionAsignado = false;
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".consultaReasignaAvaluo()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    public void onChangeCambioAnalista() {
        try {
            codProEnt = "";
            EstadoOpcionAsignado = false;
            mBPreRadicacion.getmBAdmi().getAdm().setCodProEnt(0);
            mBPreRadicacion.setEstadoOpcionAsignado(false);
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".onChangeCambioAnalista()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    public void agregarObservacionRadicacion() {
        try {
            if ("".equals(ObservaRadicGeneral) || ObservaRadicGeneral == null) {
                mbTodero.setMens("Debe llenar información del campo 'Observaciones'");
                mbTodero.warn();
            } else {
                PreRad.setObservacionPreRadicaGeneral(ObservaRadicGeneral + " -RADICACIÓN");
                PreRad.setCodPreRadica(PreRad.getCodPreRadica());
                PreRad.agregarObservacionPreRadicacion(mBsesion.codigoMiSesion());
                mbTodero.setMens("Observación realizada correctamente");
                mbTodero.info();
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".agregarObservacionPreRadicacion()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    public void agregarObservacionSeguimiento() {
        try {
            if ("".equals(observacion) || observacion == null) {
                mbTodero.setMens("Debe llenar información del campo 'Observaciones'");
                mbTodero.warn();
            } else {
                Radi = new LogRadicacion();
                Radi.setObservacionRadic(observacion + " -SEGUIMIENTO");
                Radi.setCodPreRadica(Radi.getCodSeguimiento());
                PreRad.agregarObservacionSeguimiento(mBsesion.codigoMiSesion(), observacion, Radi.getCodSeguimiento());
                mbTodero.setMens("Observación realizada correctamente");
                mbTodero.info();
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".agregarObservacionSeguimiento()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * FIN Metodos de funcionalidad de la clase
     * -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     */
}
