package LogBean;

import Logic.LogAdministracion;
import Logic.LogAvaluo;
import Logic.LogCargueArchivos;
import Logic.LogEmpleado;
import Logic.LogEntidad;
import Logic.LogPerito;
import Logic.LogPermiso;
import Logic.LogPreRadicacion;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

@ManagedBean
@ViewScoped
public class BeanDireccionar {

    private String TemaSeleccionado;


    private String nombre_pagina;
    private String nombre_pagina_interna;
    private String url_pagina = "fondoSIAI.xhtml";
    private String tituloMenu = "Bienvenidos a la aplicación";
    private String url_pagina_Administracion;
    private String url_pagina_Pre_Radicacion;
    private String url_pagina_Radicacion;
    public String url_pagina_Seguimiento;

    private boolean opcionPNLValidacion = true;
    private boolean opcionPNLInformacion = false;
    private boolean opcionPNLRevGen = false;
    private boolean opcionPNLRevJur = false;
    private boolean opcionPNLRevTec = false;
    private int po1 = 486;
    private int po2 = 264;
    private int aquita = 0;
    private String PassValida;
    private boolean EstadotabRadicacion;
    private String tamañoPantalla1 = "auto";
    private String tamañoPantalla2 = "1%";
    ResultSet valCom = null;

    /**
     * Formatos de fechas y hora *
     */
    SimpleDateFormat Format = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat FormatCompleto = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    SimpleDateFormat FormatHora = new SimpleDateFormat("HH:mm");

    private String seleccionPreradica;

    ResultSet Dat = null;
    private boolean estadoActivacionGestor;
    private boolean ActivacionAprobacionRevision = false;
    private List<LogAdministracion> ListMenu = null;
    private LogAdministracion obcionseleccionada;

    /**
     * Variables que se conectan a las diferentes clases de el proyecto
     * NegociosISA > Logic*
     */
    LogAdministracion Adm = new LogAdministracion();
    LogAvaluo Ava = new LogAvaluo();
    LogEmpleado Emp = new LogEmpleado();
    LogPreRadicacion PreRad = new LogPreRadicacion();
    LogPermiso Perm = new LogPermiso();
    LogPerito Per = new LogPerito();
    LogCargueArchivos CarArc = new LogCargueArchivos();

    private ResultSet DatInfoGeneralRevision;

    @PostConstruct
    public void init() {
        this.aquita = 0;
        RolGestor(mBSesion.codigoMiSesion());
    }

    /**
     * Constructor de la clase que carga segun el usuario los menus a los que
     * tiene acceso
     *
     *
     * @throws java.lang.Exception
     */
    public BeanDireccionar() throws Exception {
        try {
            BeanSesion MBSes = new BeanSesion();
            Adm.setCodEmp(Integer.parseInt(MBSes.getCod_empleado()));
            this.ListMenu = Adm.ConsulProcesEmple();
        } catch (IOException | NumberFormatException ex) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        }
    }

    /**
     * Variable tipo BeanEmpleado para traer los atributos y metodos de el
     * ManagedBean BeanEmpleado.java
     *
     *
     * @see BeanEmpleado.java
     */
    @ManagedProperty("#{MBEmpleado}")
    private BeanEmpleado mBEmpleado;

    public BeanEmpleado getmBEmpleado() {
        return mBEmpleado;
    }

    public void setmBEmpleado(BeanEmpleado mBEmpleado) {
        this.mBEmpleado = mBEmpleado;
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

    /**
     * Variable tipo BeanAdministracion para traer los atributos y metodos de el
     * ManagedBean BeanAdministracion.java
     *
     *
     * @see BeanAdministracion.java
     */
    @ManagedProperty("#{MBAdministacion}")
    private BeanAdministracion mBAdministacion;

    public BeanAdministracion getmBAdministacion() {
        return mBAdministacion;
    }

    public void setmBAdministacion(BeanAdministracion mBAdministacion) {
        this.mBAdministacion = mBAdministacion;
    }

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
    private BeanUbicacion MBUbicacion;

    public BeanUbicacion getMBUbicacion() {
        return MBUbicacion;
    }

    public void setMBUbicacion(BeanUbicacion MBUbicacion) {
        this.MBUbicacion = MBUbicacion;
    }

    /**
     * Variable tipo BeanSesion para traer los atributos y metodos de el
     * ManagedBean BeanSesion.java
     *
     *
     * @see BeanSesion.java
     */
    @ManagedProperty("#{MBSesion}")
    private BeanSesion mBSesion;

    public BeanSesion getmBSesion() {
        return mBSesion;
    }

    public void setmBSesion(BeanSesion mBSesion) {
        this.mBSesion = mBSesion;
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
     * Variable tipo BeanAvaluo para traer los atributos y metodos de el
     * ManagedBean BeanAvaluo.java
     *
     *
     * @see BeanAvaluo.java
     */
    @ManagedProperty("#{MBAvaluo}")
    private BeanAvaluo mbAvaluo;

    public BeanAvaluo getMbAvaluo() {
        return mbAvaluo;
    }

    public void setMbAvaluo(BeanAvaluo mbAvaluo) {
        this.mbAvaluo = mbAvaluo;
    }

    /**
     * Variable tipo BeanPermiso para traer los atributos y metodos de el
     * ManagedBean BeanPermiso.java
     *
     *
     * @see BeanPermiso.java
     */
    @ManagedProperty("#{MBPermiso}")
    private BeanPermiso mbPermiso;

    public BeanPermiso getMbPermiso() {
        return mbPermiso;
    }

    public void setMbPermiso(BeanPermiso mbPermiso) {
        this.mbPermiso = mbPermiso;
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
     * Variable tipo BeanRegimen para traer los atributos y metodos de el
     * ManagedBean BeanRegimen.java
     *
     *
     * @see BeanRegimen.java
     */
    @ManagedProperty("#{MBRegimen}")
    private BeanRegimen mbRegimen;

    public BeanRegimen getMbRegimen() {
        return mbRegimen;
    }

    public void setMbRegimen(BeanRegimen mbRegimen) {
        this.mbRegimen = mbRegimen;
    }

    /**
     * Variable tipo BeanSeguimiento para traer los atributos y metodos de el
     * ManagedBean BeanSeguimiento.java
     *
     *
     * @see BeanSeguimiento.java
     */
    @ManagedProperty("#{MBSeguimiento}")
    private BeanSeguimiento mbSeguimiento;

    public BeanSeguimiento getMbSeguimiento() {
        return mbSeguimiento;
    }

    public void setMbSeguimiento(BeanSeguimiento mbSeguimiento) {
        this.mbSeguimiento = mbSeguimiento;
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

    public void setmBCliente(BeanCliente mBCliente) {
        this.mBCliente = mBCliente;
    }

    public LogAdministracion getAdm() {
        return Adm;
    }

    public void setAdm(LogAdministracion Adm) {
        this.Adm = Adm;
    }

    public String getTemaSeleccionado() {
        return TemaSeleccionado;
    }

    public void setTemaSeleccionado(String TemaSeleccionado) {
        this.TemaSeleccionado = TemaSeleccionado;
    }

    public String getNombre_pagina() {
        return nombre_pagina;
    }

    public void setNombre_pagina(String nombre_pagina) {
        this.nombre_pagina = nombre_pagina;
    }

    public String getNombre_pagina_interna() {
        return nombre_pagina_interna;
    }

    public void setNombre_pagina_interna(String nombre_pagina_interna) {
        this.nombre_pagina_interna = nombre_pagina_interna;
    }

    public String getUrl_pagina() {
        return url_pagina;
    }

    public void setUrl_pagina(String url_pagina) {
        this.url_pagina = url_pagina;
    }

    public String getTituloMenu() {
        return tituloMenu;
    }

    public void setTituloMenu(String tituloMenu) {
        this.tituloMenu = tituloMenu;
    }

    public String getUrl_pagina_Administracion() {
        return url_pagina_Administracion;
    }

    public void setUrl_pagina_Administracion(String url_pagina_Administracion) {
        this.url_pagina_Administracion = url_pagina_Administracion;
    }

    public String getUrl_pagina_Pre_Radicacion() {
        return url_pagina_Pre_Radicacion;
    }

    public void setUrl_pagina_Pre_Radicacion(String url_pagina_Pre_Radicacion) {
        this.url_pagina_Pre_Radicacion = url_pagina_Pre_Radicacion;
    }

    public String getUrl_pagina_Radicacion() {
        return url_pagina_Radicacion;
    }

    public void setUrl_pagina_Radicacion(String url_pagina_Radicacion) {
        this.url_pagina_Radicacion = url_pagina_Radicacion;
    }

    public String getUrl_pagina_Seguimiento() {
        return url_pagina_Seguimiento;
    }

    public void setUrl_pagina_Seguimiento(String url_pagina_Seguimiento) {
        this.url_pagina_Seguimiento = url_pagina_Seguimiento;
    }

    public boolean isEstadoActivacionGestor() {
        return estadoActivacionGestor;
    }

    public void setEstadoActivacionGestor(boolean estadoActivacionGestor) {
        this.estadoActivacionGestor = estadoActivacionGestor;
    }

    public boolean isActivacionAprobacionRevision() {
        return ActivacionAprobacionRevision;
    }

    public void setActivacionAprobacionRevision(boolean ActivacionAprobacionRevision) {
        this.ActivacionAprobacionRevision = ActivacionAprobacionRevision;
    }

    public void asignar_url(String url_inicial) {
        this.nombre_pagina = url_inicial;
    }

    public List<LogAdministracion> getListMenu() {
        return ListMenu;
    }

    public void setListMenu(List<LogAdministracion> ListMenu) {
        this.ListMenu = ListMenu;
    }

    public LogAdministracion getObcionseleccionada() {
        return obcionseleccionada;
    }

    public void setObcionseleccionada(LogAdministracion obcionseleccionada) {
        this.obcionseleccionada = obcionseleccionada;
    }

    public boolean isOpcionPNLValidacion() {
        return opcionPNLValidacion;
    }

    public void setOpcionPNLValidacion(boolean opcionPNLValidacion) {
        this.opcionPNLValidacion = opcionPNLValidacion;
    }

    public boolean isOpcionPNLInformacion() {
        return opcionPNLInformacion;
    }

    public void setOpcionPNLInformacion(boolean opcionPNLInformacion) {
        this.opcionPNLInformacion = opcionPNLInformacion;
    }

    public boolean isOpcionPNLRevGen() {
        return opcionPNLRevGen;
    }

    public void setOpcionPNLRevGen(boolean opcionPNLRevGen) {
        this.opcionPNLRevGen = opcionPNLRevGen;
    }

    public boolean isOpcionPNLRevJur() {
        return opcionPNLRevJur;
    }

    public void setOpcionPNLRevJur(boolean opcionPNLRevJur) {
        this.opcionPNLRevJur = opcionPNLRevJur;
    }

    public boolean isOpcionPNLRevTec() {
        return opcionPNLRevTec;
    }

    public void setOpcionPNLRevTec(boolean opcionPNLRevTec) {
        this.opcionPNLRevTec = opcionPNLRevTec;
    }

    public int getPo1() {
        return po1;
    }

    public void setPo1(int po1) {
        this.po1 = po1;
    }

    public int getPo2() {
        return po2;
    }

    public void setPo2(int po2) {
        this.po2 = po2;
    }

    public int getAquita() {
        return aquita;
    }

    public void setAquita(int aquita) {
        this.aquita = aquita;
    }

    public String getPassValida() {
        return PassValida;
    }

    public void setPassValida(String PassValida) {
        this.PassValida = PassValida;
    }

    public boolean isEstadotabRadicacion() {
        return EstadotabRadicacion;
    }

    public void setEstadotabRadicacion(boolean EstadotabRadicacion) {
        this.EstadotabRadicacion = EstadotabRadicacion;
    }

    public String getTamañoPantalla1() {
        return tamañoPantalla1;
    }

    public void setTamañoPantalla1(String tamañoPantalla1) {
        this.tamañoPantalla1 = tamañoPantalla1;
    }

    public String getTamañoPantalla2() {
        return tamañoPantalla2;
    }

    public void setTamañoPantalla2(String tamañoPantalla2) {
        this.tamañoPantalla2 = tamañoPantalla2;
    }

    public String getSeleccionPreradica() {
        return seleccionPreradica;
    }

    public void setSeleccionPreradica(String seleccionPreradica) {
        this.seleccionPreradica = seleccionPreradica;
    }


    private void RolGestor(int Id) {
        try {
            Logic.LogPermiso Permi = new LogPermiso();
            Dat = Permi.consulRolEmpleado(Id, "Gestor");
            if (Dat.next()) {
                this.estadoActivacionGestor = true;
            } else if (!Dat.next()) {
                this.estadoActivacionGestor = false;
            }
            Conexion.Conexion.CloseCon();
        } catch (SQLException e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".RolGestor()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }


    private void lim() {
        try {
            this.estadoActivacionGestor = false;
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".lim()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    public void direccionarpormodulo(String Modulo, String url_interna) {
        try {
            Logic.LogPermiso Permis = new LogPermiso();
            Dat = Permis.consulRolEmpleadoEspe(mBSesion.codigoMiSesion(), "Radicador");
            if (Dat.next()) {
                seleccionar_url_menuprincipal(Modulo);
                this.url_Menu_Radica(url_interna);
            } else {
                this.seleccionar_url_menuprincipal("Inicio");
            }
            Conexion.Conexion.CloseCon();
        } catch (SQLException e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".direccionarpormodulo()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    public void seleccionar_url_menuprincipal(String url_inicial) {
        try {
            this.nombre_pagina = "";
            this.nombre_pagina = url_inicial;
            switch (this.nombre_pagina) {
                case "Administracion":
                    this.tituloMenu = "Administracion";
                    this.url_pagina = "../Menus/Administracion/Menu_Administrativo.xhtml";
                    break;
                case "Pre-Radicacion":
                    this.tituloMenu = "Pre-Radicacion";
                    this.url_pagina = "../Menus/Radicacion/Menu_Pre_Radicacion.xhtml";
                    break;
                case "Radicacion":
                    this.tituloMenu = "Radicacion";
                    this.url_pagina = "../Menus/Radicacion/Menu_Radicacion.xhtml";
                    break;
                case "Archivos":
                    this.tituloMenu = "Archivos";
                    this.url_pagina = "../Menus/Archivos/Menu_Archivos.xhtml";
                    break;
                case "Seguimiento":
                    this.tituloMenu = "Seguimiento";
                    this.url_pagina = "../Menus/Seguimiento/Menu_Seguimiento.xhtml";
                    break;
                case "Inicio":
                    this.tituloMenu = "";
                    this.url_pagina = "fondoSIAI.xhtml";
                    break;

                default:

                    this.tituloMenu = "";
                    this.url_pagina = "../NoAlcansada.xhtml";
                    break;
            }
            this.url_pagina_Pre_Radicacion = "../Blanco.xhtml";
            this.url_pagina_Administracion = "../Blanco.xhtml";
            this.url_pagina_Radicacion = "../Blanco.xhtml";
            this.url_pagina_Seguimiento = "../Blanco.xhtml";

            ListMenu.clear();
            ListMenu = Adm.ConsulProcesEmple();
            this.tamañoPantalla1 = "auto";
            this.tamañoPantalla2 = "auto";
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".seleccionar_url_menuprincipal()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    public void url_MenusInternos(String url_interna) {
        try {
            this.nombre_pagina_interna = "";
            this.nombre_pagina_interna = url_interna;
            switch (this.nombre_pagina_interna) {


                case "Gestion_Empleado":
                    mBEmpleado.clearResetComponets();

                    mBEmpleado.getTipDocEmp().clear();
                    mBEmpleado.setTipDocEmp(mBEmpleado.getConsulTipDocEmp());

                    mBEmpleado.getCargaDep().clear();
                    mBEmpleado.cargDepto();

                    mBEmpleado.getCargEmp().clear();
                    mBEmpleado.setCargEmp(mBEmpleado.getConsulCargEmp());

                    mBEmpleado.getCargpisos().clear();
                    mBEmpleado.setCargpisos(mBEmpleado.getConsulPisos());

                    mBEmpleado.getEstEmp().clear();
                    mBEmpleado.setEstEmp(mBEmpleado.getConsulEstEmp());

                    mBEmpleado.getTipDocEmp().clear();
                    mBEmpleado.setTipDocEmp(mBEmpleado.getConsulTipDocEmp());

                    mBEmpleado.getCargJefes().clear();
                    mBEmpleado.setCargJefes(mBEmpleado.getCargJefeEmp());

                    mBEmpleado.setListEmp(new ArrayList<>());
                    mBEmpleado.setListEmp(mBEmpleado.Emp.ConsulAllEmpleados());

                    mBEmpleado.setCargEmpSeguimiento(mBEmpleado.getCargEmplSeguimiento());

                    mBEmpleado.getcargaRol();

                    MBUbicacion.setListDepto(MBUbicacion.Ubi.ConsulDepto());

                    MBUbicacion.getCargaDep().clear();
                    MBUbicacion.cargDepto();

                    mBEmpleado.getCargaCiu().clear();

                    mbPermiso.getcargaProEnt().clear();
                    mbPermiso.getcargaProEnt();

                    this.url_pagina_Administracion = "../Persona/GestionEmpleados.xhtml";
                    break;

                case "Gestion_Peritos":
                    mBPerito.clearComponents();
                    mBPerito.setListPerit(Per.ConsulAllPerito(0));

                    mBPerito.getCargTipDocPer().clear();
                    mBPerito.setCargTipDocPer(mBPerito.getConsulTipDocPer());

                    mBPerito.getCargEstPer().clear();
                    mBPerito.setCargEstPer(mBPerito.getConsulEstPer());

                    mBPerito.getCargRegPer().clear();
                    mBPerito.setCargRegPer(mBPerito.getConsulRegimPer());

                    MBUbicacion.getCargaDep().clear();
                    MBUbicacion.setListDepto(MBUbicacion.Ubi.ConsulDepto());

                    mBPerito.getCargaCiu().clear();

                    MBUbicacion.cargDepto();
                    this.url_pagina_Administracion = "../Persona/GestionAvaluadores.xhtml";
                    break;

                case "Gestion_Estados":
                    mBAdministacion.limpiarAdministracion();
                    mbTodero.resetTable("formEst:EstTable");
                    mBAdministacion.setListEstado(mBAdministacion.Adm.ConsulEstad());
                    Conexion.Conexion.CloseCon();
                    mBAdministacion.getListEstado().clear();
                    mBAdministacion.setListEstado(mBAdministacion.Adm.ConsulEstad());
                    Conexion.Conexion.CloseCon();
                    if (mBAdministacion.getListEstado().size() > 0) {
                        mBAdministacion.AdmEstados.setCodEstado(mBAdministacion.Adm.ConsulCodigoPrincipal("Cod_Estado", "Estado"));
                    }
                    this.url_pagina_Administracion = "../Administracion/GestionEstados.xhtml";
                    break;

                case "GCargos":
                    mBAdministacion.limpiarAdministracion();
                    mbTodero.resetTable("FRMGesCargo:CargoTable");
                    mBAdministacion.setListCargos(mBAdministacion.Adm.ConsulCargo());
                    mBAdministacion.getListCargos().clear();
                    mBAdministacion.setListCargos(mBAdministacion.Adm.ConsulCargo());
                    if (mBAdministacion.getListCargos().size() > 0) {
                        mBAdministacion.AdmCargos.setCodCargo(mBAdministacion.Adm.ConsulCodigoPrincipal("Cod_Cargo", "Cargo"));
                    }
                    this.url_pagina_Administracion = "../Administracion/GestionCargos.xhtml";
                    break;

                case "ConsultaProductoEntidad":
                    mBAdministacion.limpiarAdministracion();
                    mBAdministacion.setProEntAll(mBAdministacion.getConsulProEntAll());
                    this.url_pagina_Administracion = "../Administracion/GestionProductoEntidad.xhtml";
                    break;

                case "ConsultaRoles":
                    mBAdministacion.limpiarAdministracion();
                    mbTodero.resetTable("FrmRol:RolTable");
                    mBAdministacion.setListRoles(mBAdministacion.Adm.ConsulRoles());
                    mBAdministacion.getListRoles().clear();
                    mBAdministacion.setListRoles(mBAdministacion.Adm.ConsulRoles());
                    if (mBAdministacion.getListRoles().size() > 0) {
                        mBAdministacion.AdmRoles.setCodRol(mBAdministacion.Adm.ConsulCodigoPrincipal("Cod_Roles", "Roles"));
                    }
                    mbPermiso.setCargProcesos(mbPermiso.cargaProcesos());
                    this.url_pagina_Administracion = "../Administracion/GestionRoles.xhtml";
                    break;

                case "ConsultaRegimen":
                    mBAdministacion.limpiarAdministracion();
                    mbRegimen.setCargaReg(mbRegimen.cargaTipRegimen());
                    this.url_pagina_Administracion = "../Administracion/GestionRegimen.xhtml";
                    break;

                case "ConsultaTipoDocumento":
                    mBAdministacion.limpiarAdministracion();
                    mbTodero.resetTable("formTipDoc:TipDocTable");
                    mBAdministacion.setListTipDocumento(mBAdministacion.Adm.ConsulTipDoc());
                    mBAdministacion.getListTipDocumento().clear();
                    mBAdministacion.setListTipDocumento(mBAdministacion.Adm.ConsulTipDoc());
                    if (mBAdministacion.getListTipDocumento().size() > 0) {
                        mBAdministacion.AdmTipDoc.setCodTipDocumento(mBAdministacion.Adm.ConsulCodigoPrincipal("Cod_TipDocumento", "Tipo_Documento"));
                    }

                    this.url_pagina_Administracion = "../Administracion/GestionTipoDocumento.xhtml";
                    break;

                case "GestionEntidades":
                    mBAdministacion.limpiarAdministracion();
                    mBEntidad.limpiar();
                    mBEntidad.limpiarCajasAsesor();
                    mBEntidad.limpiarCajasEntidad();
                    mBEntidad.limpiarCajasSucursales();

                    MBUbicacion.getCargaDep().clear();
                    MBUbicacion.cargDepto();

                    mBPerito.getCargRegPer().clear();
                    mBPerito.setCargRegPer(mBPerito.getConsulRegimPer());

                    this.url_pagina_Administracion = "../Administracion/GestionEntidades.xhtml";
                    break;

                case "GestionTipoBienes":
                    mbAvaluo.setEstadoRadioSeleccionTipBien("");
                    this.url_pagina_Administracion = "../Administracion/GestionTiposBienes.xhtml";
                    break;

                default:
                    this.url_pagina_Administracion = "../NoAlcansada.xhtml";
                    break;
            }
            this.url_pagina_Pre_Radicacion = "../Blanco.xhtml";
            this.url_pagina_Radicacion = "../Blanco.xhtml";
            this.url_pagina_Seguimiento = "../Blanco.xhtml";
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".url_MenusInternos()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    public void addMessage(String summary) {
        try {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".seleccionar_url_menuprincipal()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    public void url_Menu_prRadica(String url_interna_preradica) {
        try {
            this.nombre_pagina_interna = "";
            this.nombre_pagina_interna = url_interna_preradica;
            Calendar fecha = new GregorianCalendar();
            Date fecha1 = new Date();
            switch (this.nombre_pagina_interna) {
                case "Registro":
                    mBPreRadicacion.cleear();
                    fecha1 = fecha.getTime();
                    mBPreRadicacion.setFecha_actual(Format.format(fecha1));
                    mbAvaluo.setFechaActual(fecha1.toString());
                    //mBPreRadicacion.setListPreRadicados(mBPreRadicacion.PreRad.ConsulPendientesRadicar(mBSesion.codigoMiSesion()));

                    MBUbicacion.cargDepto();
                    mBPreRadicacion.setListAllMisAsignados(mBPreRadicacion.PreRad.getConsultarAllAnalistas(mBAdministacion.Adm.getCodProEnt(), 1));

                    //Cargar lo de Entidades
                    LogEntidad Ent = new LogEntidad();
                    mBEntidad.setCargaEntTodo(new ArrayList<>());
                    mBEntidad.setCargaEntTodo(Ent.getEntidad());
                    LogEntidad EntEnt = new LogEntidad();
                    EntEnt.setCodEntidad(Adm.ConsulCodigoPrincipal("Cod_Entidad", "Entidad"));

                    mBEntidad.setCodEntidad1("0");
                    mBEntidad.setCodEntidad2("0");
                    mBEntidad.setCodEntidad3("0");
                    mBEntidad.setCodEntidad4("0");
                    mBEntidad.setCodEntidad5("0");
                    mBEntidad.cargEntRadic(1);

                    //Cargar sucursales 
                    mBEntidad.setCargaSucurTodo(new ArrayList<>());
                    mBEntidad.setCargaSucurTodo(Ent.getSucursalAll());
                    LogEntidad Entsucu = new LogEntidad();
                    Entsucu.setCodSucursal(Adm.ConsulCodigoPrincipal("Cod_Sucursal", "Sucursal"));

                    //Cargar Asesores
                    mBEntidad.setCargaAsesTodo(new ArrayList<>());
                    mBEntidad.setCargaAsesTodo(Ent.getAsesorAll());
                    LogEntidad EntAse = new LogEntidad();
                    EntAse.setCodAsesor(Adm.ConsulCodigoPrincipal("Cod_Asesor", "Asesor"));

                    this.url_pagina_Pre_Radicacion = "../Radicacion/FormPR-Registro.xhtml";
                    break;

                case "MisAsignados":
                    mbTodero.resetTable("FormMisAsignados:DtgMisAsignados");
                    mBPreRadicacion.setListPreRadicados(PreRad.ConsulPendientesRadicar(mBSesion.codigoMiSesion()));

                    mBPreRadicacion.setListMisAginadosPreRad(new ArrayList<>());
                    mBPreRadicacion.setListMisAginadosPreRad(PreRad.ConsulMisRegistrosAsignados(mBSesion.codigoMiSesion()));
                    if (mBPreRadicacion.getListMisAginadosPreRad().isEmpty()) {
                        mBPreRadicacion.setTamañoTablas("90px");
                    } else {
                        mBPreRadicacion.setTamañoTablas("50px");
                    }
                    mbTodero.resetTable("FormMisAsignados:DtgMisAsignados");
                    mBPreRadicacion.cleear();

                    this.url_pagina_Pre_Radicacion = "../Radicacion/FormPR-MisAsignadosRegistro.xhtml";
                    break;

                case "PreRadica":

                    if (!"".equals(mBPreRadicacion.preRadRegiMisAsig.getCodPreRadica())) {
                        this.seleccionPreradica = "PreRadica";
                        mBPreRadicacion.setEstadotabsPreRadica(true);
                        mBPreRadicacion.setPreRadicacionModificacion(false);
                        mBPreRadicacion.setPreRadicacionRegistro(true);
                        mBCliente.getTipDocEmp().clear();
                        mBCliente.setTipDocEmp(mBCliente.getConsulTipDocEmp());
                        mBPerito.getCargRegPer().clear();
                        mBPerito.setCargRegPer(mBPerito.getConsulRegimPer());
                        mBAdministacion.getCargaTipoPredios().clear();
                        mBAdministacion.setCargaTipoPredios(mBAdministacion.getConsTipoPredios());
                        mBAdministacion.getCargaTipoMaquinaria().clear();
                        mBAdministacion.setCargaTipoMaquinaria(mBAdministacion.getConsTipoMaquinaria());
                        MBUbicacion.cargDepto();
                        mbAvaluo.getCargaTipAvaluo().clear();
                        mbAvaluo.setCargaTipAvaluo(mbAvaluo.cargTipAvaluo());
                        mBPreRadicacion.getCargEnviarA().clear();
                        mBPreRadicacion.getConsulEnviarA();
                        mbAvaluo.setListPredio(mbAvaluo.Ava.getConsultarAllPredios());
                        mbAvaluo.setListMaquinaria(mbAvaluo.Ava.getConsultarAllMaquinaria());
                        mbAvaluo.setListEnser(mbAvaluo.Ava.getConsultarAllEnser());

                        mBPreRadicacion.setCargaAnali(mBPreRadicacion.cargAnalista());

                        mBPreRadicacion.setListAllMisAsignados(mBPreRadicacion.PreRadi.getConsultarAllAnalistas(1, 1));
                        mBPreRadicacion.setListAllMisAsignadosRadicacion(mBPreRadicacion.PreRadi.getConsultarAllAnalistas(1, 2));
                        mBPreRadicacion.rolGestor(mBSesion.codigoMiSesion());
                        fecha1 = fecha.getTime();
                        mBPreRadicacion.setFecha_actual(Format.format(fecha1));
                        mbAvaluo.setFechaActual(fecha1.toString());
                        mBEntidad.setCargaEnt(mBEntidad.cargEntidad());
                        this.url_pagina_Pre_Radicacion = "../Radicacion/FormPR-PreRadicacion.xhtml";
                    }

                    break;

                case "pendientes_Radicar":
                    mBPreRadicacion.tablas();
                    this.url_pagina_Pre_Radicacion = "../Radicacion/Pre_Radicados.xhtml";
                    break;

            }
            this.url_pagina_Administracion = "../Blanco.xhtml";
            this.url_pagina_Radicacion = "../Blanco.xhtml";
            this.url_pagina_Seguimiento = "../Blanco.xhtml";

        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".url_Menu_prRadica()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    public void url_Menu_Radica(String url_interna_radica) {
        try {
            this.nombre_pagina_interna = "";
            this.nombre_pagina_interna = url_interna_radica;
            Calendar fecha = new GregorianCalendar();
            Date fecha12 = new Date();
            switch (this.nombre_pagina_interna) {
                case "Radicacion":
                    //Variables de cliente
                    if (mBRadicacion.PreRad != null) {
                        this.EstadotabRadicacion = true;
                        mBCliente.getTipDocEmp().clear();
                        mBCliente.setTipDocEmp(mBCliente.getConsulTipDocEmp());
                        mBPerito.getCargRegPer().clear();
                        mBPerito.setCargRegPer(mBPerito.getConsulRegimPer());

                        mbAvaluo.getCargaTipAvaluo().clear();
                        RequestContext.getCurrentInstance().execute("PF('tabRadicacion').select(0)");
                        RolGestor(mBSesion.codigoMiSesion());

                        mBRadicacion.setCargEmpSeguimiento(mBRadicacion.Radi.getConsultarAllAnalistas(1));

                        mBAdministacion.getCargaTipoPredios().clear();
                        mBAdministacion.setCargaTipoPredios(mBAdministacion.getConsTipoPredios());

                        mBAdministacion.getCargaTipoMaquinaria().clear();
                        mBAdministacion.setCargaTipoMaquinaria(mBAdministacion.getConsTipoMaquinaria());

                        mBEntidad.setCargaEnt(mBEntidad.cargEntidad());
                        MBUbicacion.cargDepto();
                        this.url_pagina_Radicacion = "../Radicacion/FormR-Radicacion.xhtml";
                    }

                    break;
                case "MisAsignados":
                    mbTodero.resetTable("FormMisAsignados:DtgMisAsignados");
                    mBRadicacion.limpiarRadicacionGeneral();
                    mBPreRadicacion.setListMisAginadosPreRadica(new ArrayList<>());
                    mBPreRadicacion.setListMisAginadosPreRadica(mBPreRadicacion.PreRad.ConsulMisPreRadicaAsignados(mBSesion.codigoMiSesion(), 1));
                    if (mBPreRadicacion.getListMisAginadosPreRadica().isEmpty()) {
                        mBPreRadicacion.setTamañoTablas("70px");
                    } else {
                        mBPreRadicacion.setTamañoTablas("50px");
                    }
                    mbTodero.resetTable("FormMisAsignados:DtgMisAsignados");
                    this.url_pagina_Radicacion = "../Radicacion/FormR-MisAsignadosPreRadica.xhtml";
                    break;
                case "Radicacion1":

                    //Variables de cliente
                    if (mBRadicacion.PreRad != null) {
                        this.EstadotabRadicacion = true;
                        mBCliente.getTipDocEmp().clear();
                        mBCliente.setTipDocEmp(mBCliente.getConsulTipDocEmp());
                        mBPerito.getCargRegPer().clear();
                        mBPerito.setCargRegPer(mBPerito.getConsulRegimPer());
                        MBUbicacion.cargDepto();
                        mbAvaluo.getCargaTipAvaluo().clear();
                        mbAvaluo.setCargaTipAvaluo(mbAvaluo.cargTipAvaluo());
                        RequestContext.getCurrentInstance().execute("PF('tabRadicacion').select(0)");
                        RolGestor(mBSesion.codigoMiSesion());

                        mBRadicacion.setCargEmpSeguimiento(new ArrayList<>());
                        mBAdministacion.getCargaTipoPredios().clear();
                        mBAdministacion.setCargaTipoPredios(mBAdministacion.getConsTipoPredios());

                        mBAdministacion.getCargaTipoMaquinaria().clear();
                        mBAdministacion.setCargaTipoMaquinaria(mBAdministacion.getConsTipoMaquinaria());

                        mBAdministacion.getCargarBancos().clear();
                        mBAdministacion.setCargarBancos(mBAdministacion.OnBancos());

                        mBEntidad.setCargaEnt(mBEntidad.cargEntidad());
                        MBUbicacion.cargDepto();
                        this.url_pagina_Radicacion = "../Radicacion/FormR-Radicacion.xhtml";
                    }

                    break;

                default:
                    this.url_pagina_Radicacion = "../NoAlcansada.xhtml";
                    break;

            }
            this.url_pagina_Administracion = "../Blanco.xhtml";
            this.url_pagina_Pre_Radicacion = "../Blanco.xhtml";
            this.url_pagina_Seguimiento = "../Blanco.xhtml";
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".url_Menu_Radica()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    public void url_Menu_Seguimiento(String url_interna_Seguim) {
        try {
            this.nombre_pagina_interna = "";
            this.nombre_pagina_interna = url_interna_Seguim;
            Calendar fecha = new GregorianCalendar();
            Date fecha1 = new Date();
            switch (this.nombre_pagina_interna) {
                case "SeguimientoMisAsignados":
                    mBArchivo.setSegmto(2);
                    mBRadicacion.limpiarRadicacionGeneral();
                    mbSeguimiento.limpiarCamposActividadesYRecord();
                    mbTodero.resetTable("FormMisAsignados:RadicadosSegTable");
                    mbSeguimiento.consultaRadicacionesSegumiento();
                    if (mbSeguimiento.getListSeguimientoRadicacionesConCita().isEmpty()) {
                        mBPreRadicacion.setTamañoTablas("70px");
                    } else {
                        mBPreRadicacion.setTamañoTablas("50px");
                    }
                    mBAdministacion.getCargarBancos().clear();
                    mBAdministacion.setCargarBancos(mBAdministacion.OnBancos());
                    // mbSeguimiento.setListSeguimiento(mbSeguimiento.Seg.ConsulSeguimAvaluos(mBSesion.codigoMiSesion()));
                    this.url_pagina_Seguimiento = "../Seguimiento/FormS-MisAsignadosSeguimiento.xhtml";
                    break;
                case "ControlSeguimiento":
                    this.url_pagina_Seguimiento = "../Seguimiento/Seguimiento_avaluo.xhtml";
                    break;
                case "RadicacionFormSeguimiento":
                    // mBRadicacion.limpiarRadicacionGeneral();
                    if (mBRadicacion.getRadi() != null) {
                        fecha1 = fecha.getTime();
                        mbSeguimiento.setFecha_actual(FormatCompleto.format(fecha1));

                        MBUbicacion.getCargaDep().clear();
                        MBUbicacion.cargDepto();
                        mbSeguimiento.setOpcionTableroControlSeguim("");
                        mBRadicacion.setEstadoTituloRadicacion(true);
                        mBRadicacion.setEstadoLabelsClienteEnti(true);
                        mBRadicacion.setRadicacionModificacion(true);
                        mBRadicacion.setRadicacionRegistro(false);
                        mbSeguimiento.setEstadoPanelMisActivid(false);
                        mbSeguimiento.setEstadoPanelMisRecordat(false);
                        mbSeguimiento.setEstadoPanelActividAsign(false);
                        mbSeguimiento.setEstadoPanelRecordatAsign(false);
                        this.EstadotabRadicacion = false;

                        if (!"Ninguna".equals(mBRadicacion.getRadi().getObservacionDocument())) {

                            mbTodero.setMens("Observaciones a tener en cuenta: \n" + mBRadicacion.getRadi().getObservacionDocument());
                            mbTodero.warn();
                        }

                        this.url_pagina_Seguimiento = "../Seguimiento/FormS-RadicacionSeguim.xhtml";
                    }

                    break;

                default:
                    this.url_pagina_Seguimiento = "../NoAlcansada.xhtml";
                    break;
            }

            this.url_pagina_Administracion = "../Blanco.xhtml";
            this.url_pagina_Pre_Radicacion = "../Blanco.xhtml";
            this.url_pagina_Radicacion = "../Blanco.xhtml";
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".url_Menu_Seguimiento()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

    public void verificaPassword() {
        try {
            if ("".equals(this.PassValida)) {
                mbTodero.setMens("Ingrese la constraseña de su cuenta");
                mbTodero.warn();
                RequestContext.getCurrentInstance().execute("PF('DlgPassword').show()");
            } else {
                Emp.setCodEmp(mBSesion.codigoMiSesion());
                Emp.setPassEmp(PassValida);
                boolean Result = Emp.ConsulPass();
                if (Result == true) {
                    RequestContext.getCurrentInstance().execute("PF('DlgPassword').hide()");
                    opcionPNLValidacion = false;
                    po1 = 290;
                    po2 = 145;
                    RequestContext.getCurrentInstance().execute("PF('DlgPassword').show()");
                    opcionPNLInformacion = true;
                    Emp.setCodEmp(mBSesion.codigoMiSesion());
                    try {
                        Dat = Emp.ConsulEmp();
                        if (Dat.next()) {
                            mBEmpleado.getTipDocEmp().clear();
                            mBEmpleado.getConsulTipDocEmp();
                            MBUbicacion.getCargaDep().clear();
                            MBUbicacion.cargDepto();
                            mBEmpleado.setNombreEmp(Dat.getString("Nombre_Empleados"));
                            mBEmpleado.setApellidoEmp(Dat.getString("Apellido_Empleados"));
                            mBEmpleado.setTipDoc(Dat.getString("FK_Cod_TipDocumento"));
                            mBEmpleado.setDocEmp(Dat.getString("Documento_Empleados"));
                            mBEmpleado.setTelPerEmp(Dat.getString("Telefo_Personal_Empleados"));
                            mBEmpleado.setCelPerEmp(Dat.getString("Celular_Personal_Empleados"));
                            mBEmpleado.setMailPerEmp(Dat.getString("Correo_Personal_Empleados"));
                            mBEmpleado.setNombreDepto(Dat.getString("Cod_Departamento"));
                            mBEmpleado.cargCiudad();
                            mBEmpleado.setCuidad(Dat.getString("E.FK_Cod_Ciudad"));
                            mBEmpleado.setNumContacEmp(Dat.getString("Numero_Contacto_Empleados"));
                            mBEmpleado.setNomContacEmp(Dat.getString("Nombre_Contacto_Empleados"));

                            mBEmpleado.setEstado(Dat.getString("Nombre_Estado"));
                            mBEmpleado.setCargo(Dat.getString("Nombre_Cargo"));
                            mBEmpleado.setExtEmp(Dat.getString("Extension_Empleados"));
                            mBEmpleado.setCelEmp(Dat.getString("Celular_Empresa_Empleados"));
                            mBEmpleado.setUbicaEmp(Dat.getString("Resultado_Parametro"));
                            if ("".equals(Dat.getString("Correo_Corporativo_Empleados")) || Dat.getString("Correo_Corporativo_Empleados") == null) {
                                mBEmpleado.setMailEmp("No registra");
                            } else {
                                mBEmpleado.setMailEmp(Dat.getString("Correo_Corporativo_Empleados"));
                            }
                            mBEmpleado.setEmpleado(Dat.getString("Jefe"));
                            mBEmpleado.setUsuarEmp(Dat.getString("Username_Empleados"));

                            mbPermiso.setListPermisosAsig(Perm.consultaRolesAsignados(mBSesion.codigoMiSesion()));

                            mbPermiso.setListProdEntiAsig(Perm.getProEntAsignadoooo(mBSesion.codigoMiSesion()));

                        }
                        Conexion.Conexion.CloseCon();
                    } catch (SQLException e) {
                        mbTodero.setMens("Error: " + e.getMessage());
                        mbTodero.fatal();
                    }
                } else {
                    this.PassValida = "";
                    mbTodero.setMens("La contraseña no es la correcta , favor ingresela nuevamente");
                    mbTodero.warn();
                    RequestContext.getCurrentInstance().execute("PF('DlgPassword').show()");
                }
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".verificaPassword()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }

    public void llamarBeanEmp() {
        try {
            this.PassValida = "";
            mBEmpleado.actualizacionEmp();
            this.opcionPNLInformacion = false;
            this.opcionPNLValidacion = true;
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".llamarBeanEmp()' causado por: " + e.getMessage());
            mbTodero.error();
        }
    }

}
