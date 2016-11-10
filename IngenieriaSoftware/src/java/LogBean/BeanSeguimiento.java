/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogBean;

import Logic.LogAdministracion;
import Logic.LogAvaluo;
import Logic.LogCliente;
import Logic.LogEmpleado;
import Logic.LogEntidad;
import Logic.LogPerito;
import Logic.LogRadicacion;
import Logic.LogSeguimiento;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

@ManagedBean(name = "MBSeguimiento")
@ViewScoped
public class BeanSeguimiento {

    /**
     * Variables utilizadas para el manejo de consultas, correos, visita etc *
     */
    private ResultSet DatObser;

    private Date fechaNueva;
    private String opcionCitaAvaluo;
    private boolean visitaRealizada;
    private String observacionReasignaCita;

    public BeanSeguimiento() {
    }

    /**
     * Variables de listas que cargan la informacion consultada*
     */
    private List<LogSeguimiento> ListSeguimiento = null;
    private List<LogRadicacion> ListSeguimientoRadicacionesConCita = null;
    private List<LogRadicacion> ListSeguimientoAsignados = null;
    private List<LogRadicacion> ListSeguimSeleccAsignados = new ArrayList<>();
    private List<LogSeguimiento> ListEmpAsignaciones = new ArrayList<>();

    /**
     * tablero de control y seguimiento*
     */
    /**
     * Estados de las opciones de los paneles*
     */
    private String opcionTableroControlSeguim;
    private boolean estadoPanelMisActivid;
    private boolean estadoPanelMisRecordat;
    private boolean estadoPanelActividAsign;
    private boolean estadoPanelRecordatAsign;
    private String estadoTituloDialogActivRecordat;
    private int CodAvaluo;
    private int CodigoSeguimiento;
    private int CodigoBienSeguimiento;

    /**
     * Crear y asignar actividades y recordatorios
     * -----------------------------------------------------------------
     * actividades
     *
     */
    private ArrayList<SelectItem> CargaActividadesCombo = new ArrayList<>();
    /**
     * recordatorios*
     */
    private ArrayList<SelectItem> CargaRecordatoriosCombo = new ArrayList<>();

    /**
     * variables que comparten las dos*
     */
    Calendar fecha = new GregorianCalendar();
    Date fecha1 = new Date();
    private int CodigoActivORecor;
    private String NombreActivORecor;
    private String DescripcionActivORecor;
    private String PioridadActivORecor;
    SimpleDateFormat FormatFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    /**
     * Variables que se conectan a las diferentes clases de el proyecto
     * NegociosISA > Logic*
     */
    LogSeguimiento Seg = new LogSeguimiento();
    LogRadicacion Rad = new LogRadicacion();
    LogRadicacion Radi = new LogRadicacion();
    LogCliente CliSeguim = new LogCliente();
    LogEntidad EntiSeguim = new LogEntidad();
    LogPerito PerSeguim = new LogPerito();
    LogEmpleado EmpSeguim = new LogEmpleado();
    LogAvaluo Aval = new LogAvaluo();

    /**
     * Variables implicitas*
     */
    private String TipoPersona;
    private String TipoPersonaExterno;
    private String PersonaExterno;
    private ArrayList<SelectItem> CargaPersonaExterna = new ArrayList<>();
    private String PersonaInterno;
    private ArrayList<SelectItem> CargaPersonaInterna = new ArrayList<>();

    private List<LogSeguimiento> ListAccionesSeg = new ArrayList<>();
    private List<LogSeguimiento> ListActiviAsig = new ArrayList<>();
    private List<LogSeguimiento> ListActiviAsigExter = new ArrayList<>();
    private List<LogSeguimiento> listaActividadesExterRealiz = new ArrayList<>();
    private List<LogSeguimiento> listaRecordarAsigExterRealiz = new ArrayList<>();
    LogSeguimiento SegActivid = new LogSeguimiento();
    LogSeguimiento SegActividAsig = new LogSeguimiento();
    LogSeguimiento SegRecordaAsig = new LogSeguimiento();

    private List<LogSeguimiento> ListaRecordatoriosRealizados;
    private List<LogSeguimiento> ListaRecordatorios;
    private List<LogSeguimiento> ListRecordatAsig = new ArrayList<>();
    private List<LogSeguimiento> ListRecordatAsigExter = new ArrayList<>();

    /**
     * Actividades *
     */
    private Date fechaActividadComplet;
    private String Fecha_actual;
    private boolean estadoActividadComplet;
    private String observaActividadComplet;
    int ValidarInserionActividad;

    private ArrayList<SelectItem> CargArchivos = new ArrayList<>();

    private String opcionMostrarAnalistaSeguim;

    //Para la Reasignacion de Persona Encargada del Seguimiento
    private String CodProEnt;
    private boolean EstadoOpcionAsignado = false;

    /**
     * Variable tipo BeanActividadyrecordatorios para traer los atributos y
     * metodos de el ManagedBean BeanActividRecord.java
     *
     *
     * @see BeanActividRecord.java
     */
    /*
    @ManagedProperty("#{MBActivRecor}")
    private BeanActividRecord BeanActivd;

    public BeanActividRecord getBeanActivd() {
        return BeanActivd;
    }

    public void setBeanActivd(BeanActividRecord BeanActivd) {
        this.BeanActivd = BeanActivd;
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
     * Variable tipo BeanAdministracion para traer los atributos y metodos de el
     * ManagedBean BeanAdministracion.java
     *
     *
     * @see BeanAdministracion.java
     */
    @ManagedProperty("#{MBAdministacion}")
    private BeanAdministracion mBAdmin;

    public BeanAdministracion getmBAdmin() {
        return mBAdmin;
    }

    public void setmBAdmin(BeanAdministracion mBAdmin) {
        this.mBAdmin = mBAdmin;
    }

    /**
     * Variable tipo BeanRadicacion para traer los atributos y metodos de el
     * ManagedBean BeanRadicacion.java
     *
     *
     * @see BeanRadicacion.java
     */
    @ManagedProperty("#{MBRadicacion}")
    private BeanRadicacion mBRadicacion;

    public BeanRadicacion getmBRadicacion() {
        return mBRadicacion;
    }

    public void setmBRadicacion(BeanRadicacion mBRadicacion) {
        this.mBRadicacion = mBRadicacion;
    }

    /**
     * Variable tipo BeanArchivos para traer los atributos y metodos de el
     * ManagedBean BeanArchivos.java
     *
     *
     * @see BeanArchivos.java
     */
    @ManagedProperty("#{MBArchivos}")
    private BeanArchivos mBArchivos;

    public BeanArchivos getmBArchivos() {
        return mBArchivos;
    }

    public void setmBArchivos(BeanArchivos mBArchivos) {
        this.mBArchivos = mBArchivos;
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
     * Metodos get y set de todas las variables / atributos, listas, etc que se
     * utilizaran para enviar y traer datos a las respectivas variables
     * -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     *
     */
    /**
     *
     * @return
     */
    public List<LogSeguimiento> getListSeguimiento() {
        return ListSeguimiento;
    }

    public void setListSeguimiento(List<LogSeguimiento> ListSeguimiento) {
        this.ListSeguimiento = ListSeguimiento;
    }

    public List<LogRadicacion> getListSeguimientoRadicacionesConCita() {
        return ListSeguimientoRadicacionesConCita;
    }

    public void setListSeguimientoRadicacionesConCita(List<LogRadicacion> ListSeguimientoRadicacionesConCita) {
        this.ListSeguimientoRadicacionesConCita = ListSeguimientoRadicacionesConCita;
    }

    public List<LogRadicacion> getListSeguimientoAsignados() {
        return ListSeguimientoAsignados;
    }

    public void setListSeguimientoAsignados(List<LogRadicacion> ListSeguimientoAsignados) {
        this.ListSeguimientoAsignados = ListSeguimientoAsignados;
    }

    public List<LogRadicacion> getListSeguimSeleccAsignados() {
        return ListSeguimSeleccAsignados;
    }

    public void setListSeguimSeleccAsignados(List<LogRadicacion> ListSeguimSeleccAsignados) {
        this.ListSeguimSeleccAsignados = ListSeguimSeleccAsignados;
    }

    public List<LogSeguimiento> getListEmpAsignaciones() {
        return ListEmpAsignaciones;
    }

    public void setListEmpAsignaciones(List<LogSeguimiento> ListEmpAsignaciones) {
        this.ListEmpAsignaciones = ListEmpAsignaciones;
    }

    public Date getFechaNueva() {
        return fechaNueva;
    }

    public void setFechaNueva(Date fechaNueva) {
        this.fechaNueva = fechaNueva;
    }

    public String getOpcionCitaAvaluo() {
        return opcionCitaAvaluo;
    }

    public void setOpcionCitaAvaluo(String opcionCitaAvaluo) {
        this.opcionCitaAvaluo = opcionCitaAvaluo;
    }

    public boolean getVisitaRealizada() {
        return visitaRealizada;
    }

    public void setVisitaRealizada(boolean visitaRealizada) {
        this.visitaRealizada = visitaRealizada;
    }

    public String getObservacionReasignaCita() {
        return observacionReasignaCita;
    }

    public void setObservacionReasignaCita(String observacionReasignaCita) {
        this.observacionReasignaCita = observacionReasignaCita;
    }

    public String getOpcionTableroControlSeguim() {
        return opcionTableroControlSeguim;
    }

    public void setOpcionTableroControlSeguim(String opcionTableroControlSeguim) {
        this.opcionTableroControlSeguim = opcionTableroControlSeguim;
    }

    public boolean isEstadoPanelMisActivid() {
        return estadoPanelMisActivid;
    }

    public void setEstadoPanelMisActivid(boolean estadoPanelMisActivid) {
        this.estadoPanelMisActivid = estadoPanelMisActivid;
    }

    public boolean isEstadoPanelMisRecordat() {
        return estadoPanelMisRecordat;
    }

    public void setEstadoPanelMisRecordat(boolean estadoPanelMisRecordat) {
        this.estadoPanelMisRecordat = estadoPanelMisRecordat;
    }

    public boolean isEstadoPanelActividAsign() {
        return estadoPanelActividAsign;
    }

    public void setEstadoPanelActividAsign(boolean estadoPanelActividAsign) {
        this.estadoPanelActividAsign = estadoPanelActividAsign;
    }

    public boolean isEstadoPanelRecordatAsign() {
        return estadoPanelRecordatAsign;
    }

    public void setEstadoPanelRecordatAsign(boolean estadoPanelRecordatAsign) {
        this.estadoPanelRecordatAsign = estadoPanelRecordatAsign;
    }

    public String getEstadoTituloDialogActivRecordat() {
        return estadoTituloDialogActivRecordat;
    }

    public void setEstadoTituloDialogActivRecordat(String estadoTituloDialogActivRecordat) {
        this.estadoTituloDialogActivRecordat = estadoTituloDialogActivRecordat;
    }

    public List<LogSeguimiento> getListAccionesSeg() {
        return ListAccionesSeg;
    }

    public void setListAccionesSeg(List<LogSeguimiento> ListAccionesSeg) {
        this.ListAccionesSeg = ListAccionesSeg;
    }

    public LogSeguimiento getSegActivid() {
        return SegActivid;
    }

    public void setSegActivid(LogSeguimiento SegActivid) {
        this.SegActivid = SegActivid;
    }

    public String getFecha_actual() {
        return Fecha_actual;
    }

    public void setFecha_actual(String Fecha_actual) {
        this.Fecha_actual = Fecha_actual;
    }

    public Date getFechaActividadComplet() {
        return fechaActividadComplet;
    }

    public void setFechaActividadComplet(Date fechaActividadComplet) {
        this.fechaActividadComplet = fechaActividadComplet;
    }

    public boolean isEstadoActividadComplet() {
        return estadoActividadComplet;
    }

    public void setEstadoActividadComplet(boolean estadoActividadComplet) {
        this.estadoActividadComplet = estadoActividadComplet;
    }

    public String getObservaActividadComplet() {
        return observaActividadComplet;
    }

    public int getValidarInserionActividad() {
        return ValidarInserionActividad;
    }

    public void setValidarInserionActividad(int ValidarInserionActividad) {
        this.ValidarInserionActividad = ValidarInserionActividad;
    }

    public void setObservaActividadComplet(String observaActividadComplet) {
        this.observaActividadComplet = observaActividadComplet;
    }

    public LogSeguimiento getSegActividAsig() {
        return SegActividAsig;
    }

    public void setSegActividAsig(LogSeguimiento SegActividAsig) {
        this.SegActividAsig = SegActividAsig;
    }

    public LogSeguimiento getSegRecordaAsig() {
        return SegRecordaAsig;
    }

    public void setSegRecordaAsig(LogSeguimiento SegRecordaAsig) {
        this.SegRecordaAsig = SegRecordaAsig;
    }

    public ArrayList<SelectItem> getCargaActividadesCombo() {
        return CargaActividadesCombo;
    }

    public void setCargaActividadesCombo(ArrayList<SelectItem> CargaActividadesCombo) {
        this.CargaActividadesCombo = CargaActividadesCombo;
    }

    public ArrayList<SelectItem> getCargaRecordatoriosCombo() {
        return CargaRecordatoriosCombo;
    }

    public void setCargaRecordatoriosCombo(ArrayList<SelectItem> CargaRecordatoriosCombo) {
        this.CargaRecordatoriosCombo = CargaRecordatoriosCombo;
    }

    public ArrayList<SelectItem> getCargaPersonaExterna() {
        return CargaPersonaExterna;
    }

    public void setCargaPersonaExterna(ArrayList<SelectItem> CargaPersonaExterna) {
        this.CargaPersonaExterna = CargaPersonaExterna;
    }

    public ArrayList<SelectItem> getCargaPersonaInterna() {
        return CargaPersonaInterna;
    }

    public void setCargaPersonaInterna(ArrayList<SelectItem> CargaPersonaInterna) {
        this.CargaPersonaInterna = CargaPersonaInterna;
    }

    public int getCodigoActivORecor() {
        return CodigoActivORecor;
    }

    public void setCodigoActivORecor(int CodigoActivORecor) {
        this.CodigoActivORecor = CodigoActivORecor;
    }

    public String getNombreActivORecor() {
        return NombreActivORecor;
    }

    public void setNombreActivORecor(String NombreActivORecor) {
        this.NombreActivORecor = NombreActivORecor;
    }

    public String getDescripcionActivORecor() {
        return DescripcionActivORecor;
    }

    public void setDescripcionActivORecor(String DescripcionActivORecor) {
        this.DescripcionActivORecor = DescripcionActivORecor;
    }

    public String getPioridadActivORecor() {
        return PioridadActivORecor;
    }

    public void setPioridadActivORecor(String PioridadActivORecor) {
        this.PioridadActivORecor = PioridadActivORecor;
    }

    public String getTipoPersona() {
        return TipoPersona;
    }

    public void setTipoPersona(String TipoPersona) {
        this.TipoPersona = TipoPersona;
    }

    public String getTipoPersonaExterno() {
        return TipoPersonaExterno;
    }

    public void setTipoPersonaExterno(String TipoPersonaExterno) {
        this.TipoPersonaExterno = TipoPersonaExterno;
    }

    public String getPersonaExterno() {
        return PersonaExterno;
    }

    public void setPersonaExterno(String PersonaExterno) {
        this.PersonaExterno = PersonaExterno;
    }

    public String getPersonaInterno() {
        return PersonaInterno;
    }

    public void setPersonaInterno(String PersonaInterno) {
        this.PersonaInterno = PersonaInterno;
    }

    public List<LogSeguimiento> getListaRecordatoriosRealizados() {
        return ListaRecordatoriosRealizados;
    }

    public void setListaRecordatoriosRealizados(List<LogSeguimiento> ListaRecordatoriosRealizados) {
        this.ListaRecordatoriosRealizados = ListaRecordatoriosRealizados;
    }

    public List<LogSeguimiento> getListaRecordatorios() {
        return ListaRecordatorios;
    }

    public void setListaRecordatorios(List<LogSeguimiento> ListaRecordatorios) {
        this.ListaRecordatorios = ListaRecordatorios;
    }

    public List<LogSeguimiento> getListActiviAsig() {
        return ListActiviAsig;
    }

    public void setListActiviAsig(List<LogSeguimiento> ListActiviAsig) {
        this.ListActiviAsig = ListActiviAsig;
    }

    public List<LogSeguimiento> getListActiviAsigExter() {
        return ListActiviAsigExter;
    }

    public void setListActiviAsigExter(List<LogSeguimiento> ListActiviAsigExter) {
        this.ListActiviAsigExter = ListActiviAsigExter;
    }

    public List<LogSeguimiento> getListaActividadesExterRealiz() {
        return listaActividadesExterRealiz;
    }

    public void setListaActividadesExterRealiz(List<LogSeguimiento> listaActividadesExterRealiz) {
        this.listaActividadesExterRealiz = listaActividadesExterRealiz;
    }

    public List<LogSeguimiento> getListRecordatAsig() {
        return ListRecordatAsig;
    }

    public void setListRecordatAsig(List<LogSeguimiento> ListRecordatAsig) {
        this.ListRecordatAsig = ListRecordatAsig;
    }

    public List<LogSeguimiento> getListRecordatAsigExter() {
        return ListRecordatAsigExter;
    }

    public void setListRecordatAsigExter(List<LogSeguimiento> ListRecordatAsigExter) {
        this.ListRecordatAsigExter = ListRecordatAsigExter;
    }

    public List<LogSeguimiento> getListaRecordarAsigExterRealiz() {
        return listaRecordarAsigExterRealiz;
    }

    public void setListaRecordarAsigExterRealiz(List<LogSeguimiento> listaRecordarAsigExterRealiz) {
        this.listaRecordarAsigExterRealiz = listaRecordarAsigExterRealiz;
    }

    public String getOpcionMostrarAnalistaSeguim() {
        return opcionMostrarAnalistaSeguim;
    }

    public void setOpcionMostrarAnalistaSeguim(String opcionMostrarAnalistaSeguim) {
        this.opcionMostrarAnalistaSeguim = opcionMostrarAnalistaSeguim;
    }

    public LogSeguimiento getSeg() {
        return Seg;
    }

    public void setSeg(LogSeguimiento Seg) {
        this.Seg = Seg;
    }

    public ArrayList<SelectItem> getCargArchivos() {
        return CargArchivos;
    }

    public void setCargArchivos(ArrayList<SelectItem> CargArchivos) {
        this.CargArchivos = CargArchivos;
    }

    public LogRadicacion getRadi() {
        return Radi;
    }

    public void setRadi(LogRadicacion Radi) {
        this.Radi = Radi;
    }

    public String getCodProEnt() {
        return CodProEnt;
    }

    public void setCodProEnt(String CodProEnt) {
        this.CodProEnt = CodProEnt;
    }

    public boolean isEstadoOpcionAsignado() {
        return EstadoOpcionAsignado;
    }

    public void setEstadoOpcionAsignado(boolean EstadoOpcionAsignado) {
        this.EstadoOpcionAsignado = EstadoOpcionAsignado;
    }

    public void capturarNumAvaluo(int Op) {
        try {
            if (Seg.getCodAvaluo() == 0) {
                mbTodero.setMens("Seleccione un numero de Radicacion");
                mbTodero.error();
            } else if (Op == 1) {
                RequestContext.getCurrentInstance().execute("PF('DlgInfSeguimiento').show()");
            } else if (Op == 2) {
                mBArchivos.setNAvaluo(Seg.getCodAvaluo());
                mBArchivos.setNBien(Seg.getNumeroBienSeguimiento());
                switch (Seg.getTipAvaluo()) {
                    case "Predio":
                        mBArchivos.setTipoAvaluo("Predio");
                        mBArchivos.Limp(1);
                        break;
                    case "Maquinaria":
                        mBArchivos.setTipoAvaluo("Maquinaria");
                        mBArchivos.Limp(1);
                        break;
                    default:
                        mBArchivos.setTipoAvaluo("Enser");
                        mBArchivos.Limp(1);
                        break;
                }
                mBArchivos.setEstadoPanelArchivoAva(false);
                mBArchivos.setEstadoPanelArchivoCli(false);
                mBArchivos.Limp(4);
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".capturarNumAvaluo()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo que verifica el cambio de estado, si es impedimento o anulacion
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Stiven Rodriguez
     * @param op int que define si se abre el dialgo para anular o impedir
     * @since 01-05-2015
     */
    public void verificaCambioEstado(int op) {
        try {
            if (Seg.getCodAvaluo() == 0) {
                mbTodero.setMens("Seleccione un numero de Radicacion");
                mbTodero.warn();
            } else {
                mBRadicacion.Radi.setCodAvaluo(Seg.getCodAvaluo());
                if (op == 1) {
                    RequestContext.getCurrentInstance().execute("PF('DlgEstAvaluo').show()");
                } else if (op == 2) {
                    RequestContext.getCurrentInstance().execute("PF('DLGAnuAvaluo').show()");
                }

            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".verificaCambioEstado()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo para realizar el cambio de estado de la PreRadicacion
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Stiven Rodriguez
     * @since 01-05-2015
     */
    //Metodo 
    public void cambioEstadAvaluo() {
        try {
            if ("".equals(mBRadicacion.Radi.getObservacionAvaluo()) || "".equals(mBRadicacion.Radi.getEstadoAvaluo())) {
                mbTodero.setMens("Falta informacion por Llenar");
                mbTodero.warn();
            } else {
                mBRadicacion.Radi.CambioEstRad(mBsesion.codigoMiSesion());
                mbTodero.setMens("La informacion ha sido guardada correctamente");
                mbTodero.info();
                mbTodero.resetTable("FRMSeguimiento:SeguimientoTable");
                RequestContext.getCurrentInstance().execute("PF('DlgEstAvaluo').hide()");
                ListSeguimiento = Seg.ConsulSeguimAvaluos(mBsesion.codigoMiSesion());//VARIABLES DE SESION
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".cambioEstadAvaluo()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo que permite realizar la anulacion de la radicacion
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Stiven Rodriguez
     * @since 01-05-2015
     */
    public void anulaAvaluo() {
        try {
            if ("".equals(mBRadicacion.Radi.getObservacionAnulaAvaluo())) {
                mbTodero.setMens("Falta informacion por Llenar");
                mbTodero.warn();
            } else {
                int CodRad = mBRadicacion.Radi.getCodAvaluo();
                mBRadicacion.Radi.AnulaRadicacion(mBsesion.codigoMiSesion());
                mbTodero.setMens("El Avaluo N*: " + CodRad + " ha sido anulada");
                mbTodero.info();
                mbTodero.resetTable("FRMSeguimiento:SeguimientoTable");
                mbTodero.resetTable("FormMisAsignados:RadicadosSegTable");
                RequestContext.getCurrentInstance().execute("PF('DLGAnuAvaluo').hide()");
                ListSeguimiento = Seg.ConsulSeguimAvaluos(mBsesion.codigoMiSesion());//VARIABLES DE SESION
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".anulaAvaluo()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    /**
     * Metodo que consulta las radicaciones/avaluos que estan en el proceso de
     * seguimiento
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Stiven Rodriguez
     * @since 01-05-2015
     */
    public void consultaRadicacionesSegumiento() {
        try {
            ListSeguimientoRadicacionesConCita = Rad.ConsultasRadicacionYSeguimiento(2, mBsesion.codigoMiSesion());
            Conexion.Conexion.CloseCon();
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".consultaRadicacionesSegumiento()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que captura numero del pre-radicacion y consulta las observaciones
     * sobre este
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Stiven Rodriguez
     * @since 01-05-2015
     */
    public void capturarNumPreRadica() {
        try {
            if (mBRadicacion.getRadi() == null) {
                mbTodero.setMens("Debe seleccionar un registro de la tabla");
                mbTodero.warn();
            } else {
                DatObser = Rad.consultaObserRadicacion(String.valueOf(mBRadicacion.getRadi().getCodAvaluo()), mBRadicacion.getRadi().getCodSeguimiento());
                mBRadicacion.setListObserRadicados(new ArrayList<LogRadicacion>());

                while (DatObser.next()) {
                    LogRadicacion RadObs = new LogRadicacion();
                    RadObs.setObservacionRadic(DatObser.getString("Obser"));
                    RadObs.setFechaObservacionRadic(DatObser.getString("Fecha"));
                    RadObs.setAnalistaObservacionRadic(DatObser.getString("empleado"));
                    mBRadicacion.getListObserRadicados().add(RadObs);
                }
                Conexion.Conexion.CloseCon();
                opcionCitaAvaluo = "";
                visitaRealizada = false;
                fechaNueva = null;
                observacionReasignaCita = "";
                RequestContext.getCurrentInstance().execute("PF('DlgInfRadicacion').show()");
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".capturarNumPreRadica()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que consulta los avaluos que estan en seguimiento, por un tipo de
     * parametro= Cita: Avaluos que tienen cita programada pero no verificada
     * Entrega: Avaluos que estan pendientes de entrega de informe por parte del
     * avaluador
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Stiven Rodriguez
     * @since 01-05-2015
     */
    public void consultasSeguimiento() {
        try {
            switch (opcionMostrarAnalistaSeguim) {
                case "Cita":
                    ListSeguimientoRadicacionesConCita = Rad.ConsultasRadicacionYSeguimiento(2, mBsesion.codigoMiSesion());
                    break;
                case "Entrega":
                    ListSeguimientoRadicacionesConCita = Rad.ConsultasRadicacionYSeguimiento(3, mBsesion.codigoMiSesion());
                    break;
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".consultasSeguimiento()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que permite limpiar los campos utilizados en las citas en el form
     * de seguimierto
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Stiven Rodriguez
     * @since 01-05-2015
     */
    public void limpiarCamposCita() {
        try {
            visitaRealizada = false;
            fechaNueva = null;
            observacionReasignaCita = "";
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".limpiarCamposCita()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    /**
     * Metodo que limoia los campos utilizados en las actividades y
     * recordatorios
     *
     * @author Maira Alejandra Carvajal
     * @author Jhojan Stiven Rodriguez
     * @since 01-05-2015
     */
    public void limpiarCamposActividadesYRecord() {
        try {
            opcionTableroControlSeguim = "";
            estadoPanelMisActivid = false;
            estadoPanelMisRecordat = false;
            estadoPanelActividAsign = false;
            estadoPanelRecordatAsign = false;
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".limpiarCamposCita()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }


  
    public void consultaReasignaAvaluo() {
        try {
            if (!"".equals(CodProEnt)) {
                this.ListSeguimientoAsignados = new ArrayList<>();
                mbTodero.resetTable("FRMAsigSegui:SeguiAsigTable");
                this.EstadoOpcionAsignado = true;
                ListSeguimientoAsignados = Rad.ConsultaAvaluoReAsigna(Integer.parseInt(CodProEnt));
            } else {
                this.EstadoOpcionAsignado = false;
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".consultaReasignaAvaluo()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }


    public void seleccionListaReAsignacion() {
        try {
            if (this.ListSeguimSeleccAsignados.isEmpty()) {
                mbTodero.setMens("Debe seleccionar por lo menos un avaluo para realizar la reasignacion de responsable");
                mbTodero.warn();
            } else {
                Seg = new LogSeguimiento();
                mbTodero.resetTable("FRMAsigSegui:EmpAsignadosTable");
                this.ListEmpAsignaciones = new ArrayList<>();
                this.ListEmpAsignaciones = Seg.ConsultaTotalEmp(Integer.parseInt(CodProEnt));
                Seg.setCodEmp(ListEmpAsignaciones.get(0).getCodEmp());
                RequestContext.getCurrentInstance().execute("PF('DlgReAsigSeguim').show()");
            }
        } catch (NumberFormatException e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".seleccionListaReAsignacion()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

 
    public void generarReAsignacion() {
        try {
            if (Seg.getCodEmp() <= 0) {
                mbTodero.setMens("No ha seleccionado un analista para generar la Asignacion de Registros");
                mbTodero.warn();
            } else {
                for (int i = 0; i < ListSeguimSeleccAsignados.size(); i++) {
                    Seg.setCodSeguimiento(ListSeguimSeleccAsignados.get(i).getCodSeguimiento());
                }
                mbTodero.resetTable("FRMAsigSegui:SeguiAsigTable");
                mbTodero.setMens("Se ha generado la Re-Asignacion Correctamente de los Avaluos");
                mbTodero.info();
                RequestContext.getCurrentInstance().execute("PF('DlgReAsigSeguim').hide()");
                this.ListSeguimSeleccAsignados.clear();
                this.ListEmpAsignaciones = null;
                this.ListSeguimientoAsignados = null;
                this.EstadoOpcionAsignado = false;
                this.CodProEnt = "";
            }
        } catch (NumberFormatException e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".generarReAsignacion()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    public void onRowSelect(SelectEvent event) {
        mbTodero.setMens("Ok");
        mbTodero.info();
    }

    /**
     * FIN Metodos de funcionalidad de la clase
     * -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     */
}
