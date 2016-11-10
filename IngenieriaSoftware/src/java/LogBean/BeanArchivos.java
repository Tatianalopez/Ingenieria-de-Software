package LogBean;

import Conexion.Conexion;

import Logic.LogAvaluo;
import Logic.LogCargueArchivos;
import Logic.LogCliente;
import Logic.LogEntidad;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.primefaces.model.DualListModel;
import org.primefaces.model.UploadedFile;

import javax.servlet.http.Part;


@ManagedBean(name = "MBArchivos")
@ViewScoped
@SessionScoped
public class BeanArchivos implements Serializable {

    private ArrayList<LogCargueArchivos> CargaArchivos;
    private ArrayList<LogCargueArchivos> CargaArchivosAsig;

    //Constructor
    public BeanArchivos() {

    }

    /**
     * Post-Constructor*
     */
    @PostConstruct
    public void init() {
    }

    private ArrayList<SelectItem> CargListaVerifiArchivos = new ArrayList<>();
    private ArrayList<SelectItem> CargaAsignados = new ArrayList<>();
    private List SeleccionArc;
    private DualListModel<LogCargueArchivos> SeleccionArcDual;
    private List<LogAvaluo> ListaAvaAnt = new ArrayList<>();
    SimpleDateFormat Format = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * -------------- Variables Implicitas *
     */
    LogCargueArchivos Arch = new LogCargueArchivos();
    LogCliente Clie = new LogCliente();
    LogAvaluo Ava = new LogAvaluo();
    ResultSet Dat = null;
    String Mens = "";
    String NomArchiResult = "";
    private String Path = "";
    private final String Directorio = "";
    private String BaseArc = "DBARCHIVOS";
    JasperReport JReporte = null;
    JasperPrint JPrint = null;
    private boolean ArchivoCreado = false, Result = false;
    private int ResInsertat = 0;
    private int N_bien = 0;
    private int Segmto = 0;
    private int ValorAct = 0;

    public int getSegmto() {
        return Segmto;
    }

    public void setSegmto(int Segmto) {
        this.Segmto = Segmto;
    }

    public int getN_bien() {
        return N_bien;
    }

    public void setN_bien(int N_bien) {
        this.N_bien = N_bien;
    }

    private boolean estadoModifica = false;

    public boolean isEstadoModifica() {
        return estadoModifica;
    }

    public void setEstadoModifica(boolean estadoModifica) {
        this.estadoModifica = estadoModifica;
    }

    public int getValorAct() {
        return ValorAct;
    }

    public void setValorAct(int ValorAct) {
        this.ValorAct = ValorAct;
    }



    public int getResInsertat() {
        return ResInsertat;
    }

    public void setResInsertat(int ResInsertat) {
        this.ResInsertat = ResInsertat;
    }
    private String Archiv = "";
    UploadedFile Archivo;

    /**
     * Variables para el manejo de los archivos que se necesiten
     *
     */
    private UploadedFile NombreArchivo;

    public String getNameArch() {
        return NameArch;
    }

    public void setNameArch(String NameArch) {
        this.NameArch = NameArch;
    }

    public String getAprobadoPor() {
        return AprobadoPor;
    }

    public void setAprobadoPor(String AprobadoPor) {
        this.AprobadoPor = AprobadoPor;
    }

    private String AprobadoPor;
    private String NameArch;
    private int codTipArchivo;
    private int NAvaluo;
    private String RParam;
    private int NAnticipo;
    private String TipoAvaluo;
    private int NBien;
    private String[] ListFile;
    private File[] ObjFile;
    private String Name;
    private String Fecha;
    private long Tamano;
    private int NumEmp;
    private int Numero;
    private int NumCliente;
    private double ValorAvaluo;
    private String BaseArcEmpres = File.separator + BaseArc + File.separator + "DBISA" + File.separator;
    private ArrayList<SelectItem> CargArchivos = new ArrayList<>();
    private boolean EstadoCargueArch = true;
    private String OpcValorantiavalu = "0";

    public boolean isEstadoCargueArch() {
        return EstadoCargueArch;
    }

    public String getRParam() {
        return RParam;
    }

    public void setRParam(String RParam) {
        this.RParam = RParam;
    }

    public void setEstadoCargueArch(boolean EstadoCargueArch) {
        this.EstadoCargueArch = EstadoCargueArch;
    }

    public String getOpcValorantiavalu() {
        return OpcValorantiavalu;
    }

    public void setOpcValorantiavalu(String OpcValorantiavalu) {
        this.OpcValorantiavalu = OpcValorantiavalu;
    }

    private Part fileUpload;

    public Part getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(Part fileUpload) {
        this.fileUpload = fileUpload;
    }


    /**
     * Variables tipo ist Listas que cargan la informacion en componentes de
     * lista desplegable (selectedOneItem o combobox, Datatable) *
     */
    private ArrayList<LogCargueArchivos> ListaArchivosDesdePreRadic = new ArrayList<>();
    private List<BeanArchivos> ListaArchivosAva = new ArrayList<>();
    private List<BeanArchivos> ListaArchivosCli = new ArrayList<>();
    private List<BeanArchivos> ListaArchivosAnt = new ArrayList<>();
    private List<BeanArchivos> ListaArchivosAntClient = new ArrayList<>();
    private List<BeanArchivos> ListaArchivosAvaEnt = new ArrayList<>();
    private List<BeanArchivos> ListaArchivosAll = new ArrayList<>();
    private List<BeanArchivos> ListaArchivosAvaRevision = new ArrayList<>();
    private List<BeanArchivos> ListaArchivosFact = new ArrayList<>();
    private List<BeanArchivos> ListaArchivosRadiCorreo = new ArrayList<>();
    private List<BeanArchivos> ListaArchivosAdmin = new ArrayList<>();
    private List<BeanArchivos> ListaArchivosAvaAnteriorSol = new ArrayList<>();
    private List<BeanArchivos> ListaArchivosAvaAnteriorInf = new ArrayList<>();

    private List<LogCliente> ListClientesPersonasAFacturarAntic = new ArrayList<>();
    private List<LogEntidad> ListEntidadesPersonasAFacturarAntic = new ArrayList<>();

    BeanArchivos Archi;
    BeanArchivos Archi2;
    private boolean EstadoPanelArchivoCli = false;
    private boolean EstadoPanelArchivoAva = false;
    private boolean EstadoPanelArchivoAnti = false;
    private String OpcionPanel = "";
    private String mensajeReporte;
    private Integer progress;
    private boolean validarTodo;

    public List<BeanArchivos> getListaArchivosAll() {
        return ListaArchivosAll;
    }

    public void setListaArchivosAll(List<BeanArchivos> ListaArchivosAll) {
        this.ListaArchivosAll = ListaArchivosAll;
    }

    public String getNombreProc() {
        return NombreProc;
    }

    public void setNombreProc(String NombreProc) {
        this.NombreProc = NombreProc;
    }
    private String NombreProc = "";

    public String getCodClienteAnti() {
        return CodClienteAnti;
    }

    public void setCodClienteAnti(String CodClienteAnti) {
        this.CodClienteAnti = CodClienteAnti;
    }

    public String getValorClienteAnti() {
        return ValorClienteAnti;
    }

    public void setValorClienteAnti(String ValorClienteAnti) {
        this.ValorClienteAnti = ValorClienteAnti;
    }

    public String CodClienteAnti = "";
    public String ValorClienteAnti = "";

    public String CodEntidAnti;
    public String ValorEntdAnti;

    public String getCodEntidAnti() {
        return CodEntidAnti;
    }

    public void setCodEntidAnti(String CodEntidAnti) {
        this.CodEntidAnti = CodEntidAnti;
    }

    public String getValorEntdAnti() {
        return ValorEntdAnti;
    }

    public void setValorEntdAnti(String ValorEntdAnti) {
        this.ValorEntdAnti = ValorEntdAnti;
    }

    public ArrayList<SelectItem> getArray() {
        return Array;
    }

    public void setArray(ArrayList<SelectItem> Array) {
        this.Array = Array;
    }

    ArrayList<SelectItem> Array = new ArrayList<>();

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
     */
    /**
     *
     * @return List tipo CargListaVerifiArchivos
     */
    public ArrayList<SelectItem> getCargListaVerifiArchivos() {
        return CargListaVerifiArchivos;
    }

    public void setCargListaVerifiArchivos(ArrayList<SelectItem> CargListaVerifiArchivos) {
        this.CargListaVerifiArchivos = CargListaVerifiArchivos;
    }

    public List<LogAvaluo> getListaAvaAnt() {
        return ListaAvaAnt;
    }

    public void setListaAvaAnt(List<LogAvaluo> ListaAvaAnt) {
        this.ListaAvaAnt = ListaAvaAnt;
    }

    public ArrayList<SelectItem> getCargaAsignados() {
        return CargaAsignados;
    }

    public void setCargaAsignados(ArrayList<SelectItem> CargaAsignados) {
        this.CargaAsignados = CargaAsignados;
    }

    public List<Integer> getSeleccionArc() {
        return SeleccionArc;
    }

    public void setSeleccionArc(List<Integer> SeleccionArc) {
        this.SeleccionArc = SeleccionArc;
    }

    public DualListModel<LogCargueArchivos> getSeleccionArcDual() {
        return SeleccionArcDual;
    }

    public void setSeleccionArcDual(DualListModel<LogCargueArchivos> SeleccionArcDual) {
        this.SeleccionArcDual = SeleccionArcDual;
    }

    public LogCargueArchivos getArch() {
        return Arch;
    }

    public void setArch(LogCargueArchivos Arch) {
        this.Arch = Arch;
    }

    public String getPath() {
        return Path;
    }

    public void setPath(String Path) {
        this.Path = Path;
    }

    public String getBaseArc() {
        return BaseArc;
    }

    public void setBaseArc(String BaseArc) {
        this.BaseArc = BaseArc;
    }

    public boolean isArchivoCreado() {
        return ArchivoCreado;
    }

    public void setArchivoCreado(boolean ArchivoCreado) {
        this.ArchivoCreado = ArchivoCreado;
    }

    public String getArchiv() {
        return Archiv;
    }

    public void setArchiv(String Archiv) {
        this.Archiv = Archiv;
    }

    public UploadedFile getArchivo() {
        return Archivo;
    }

    public void setArchivo(UploadedFile Archivo) {
        this.Archivo = Archivo;
    }

    public UploadedFile getNombreArchivo() {
        return NombreArchivo;
    }

    public void setNombreArchivo(UploadedFile NombreArchivo) {
        this.NombreArchivo = NombreArchivo;
    }

    public int getCodTipArchivo() {
        return codTipArchivo;
    }

    public void setCodTipArchivo(int codTipArchivo) {
        this.codTipArchivo = codTipArchivo;
    }

    public int getNAvaluo() {
        return NAvaluo;
    }

    public void setNAvaluo(int NAvaluo) {
        this.NAvaluo = NAvaluo;
    }

    public int getNAnticipo() {
        return NAnticipo;
    }

    public void setNAnticipo(int NAnticipo) {
        this.NAnticipo = NAnticipo;
    }

    public String getTipoAvaluo() {
        return TipoAvaluo;
    }

    public void setTipoAvaluo(String TipoAvaluo) {
        this.TipoAvaluo = TipoAvaluo;
    }

    public int getNBien() {
        return NBien;
    }

    public void setNBien(int NBien) {
        this.NBien = NBien;
    }

    public String[] getListFile() {
        return ListFile;
    }

    public void setListFile(String[] ListFile) {
        this.ListFile = ListFile;
    }

    public File[] getObjFile() {
        return ObjFile;
    }

    public void setObjFile(File[] ObjFile) {
        this.ObjFile = ObjFile;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public long getTamano() {
        return Tamano;
    }

    public void setTamano(long Tamano) {
        this.Tamano = Tamano;
    }

    public int getNumEmp() {
        return NumEmp;
    }

    public void setNumEmp(int NumEmp) {
        this.NumEmp = NumEmp;
    }

    public int getNumero() {
        return Numero;
    }

    public void setNumero(int Numero) {
        this.Numero = Numero;
    }

    public int getNumCliente() {
        return NumCliente;
    }

    public void setNumCliente(int NumCliente) {
        this.NumCliente = NumCliente;
    }

    public double getValorAvaluo() {
        return ValorAvaluo;
    }

    public void setValorAvaluo(double ValorAvaluo) {
        this.ValorAvaluo = ValorAvaluo;
    }

    public String getBaseArcEmpres() {
        return BaseArcEmpres;
    }

    public void setBaseArcEmpres(String BaseArcEmpres) {
        this.BaseArcEmpres = BaseArcEmpres;
    }

    public ArrayList<SelectItem> getCargArchivos() {
        return CargArchivos;
    }

    public void setCargArchivos(ArrayList<SelectItem> CargArchivos) {
        this.CargArchivos = CargArchivos;
    }

    public ArrayList<LogCargueArchivos> getListaArchivosDesdePreRadic() {
        return ListaArchivosDesdePreRadic;
    }

    public void setListaArchivosDesdePreRadic(ArrayList<LogCargueArchivos> ListaArchivosDesdePreRadic) {
        this.ListaArchivosDesdePreRadic = ListaArchivosDesdePreRadic;
    }

    public List<BeanArchivos> getListaArchivosAva() {
        return ListaArchivosAva;
    }

    public void setListaArchivosAva(List<BeanArchivos> ListaArchivosAva) {
        this.ListaArchivosAva = ListaArchivosAva;
    }

    public List<BeanArchivos> getListaArchivosCli() {
        return ListaArchivosCli;
    }

    public void setListaArchivosCli(List<BeanArchivos> ListaArchivosCli) {
        this.ListaArchivosCli = ListaArchivosCli;
    }

    public List<BeanArchivos> getListaArchivosAnt() {
        return ListaArchivosAnt;
    }

    public void setListaArchivosAnt(List<BeanArchivos> ListaArchivosAnt) {
        this.ListaArchivosAnt = ListaArchivosAnt;
    }

    public List<BeanArchivos> getListaArchivosAntClient() {
        return ListaArchivosAntClient;
    }

    public void setListaArchivosAntClient(List<BeanArchivos> ListaArchivosAntClient) {
        this.ListaArchivosAntClient = ListaArchivosAntClient;
    }

    public List<BeanArchivos> getListaArchivosAvaEnt() {
        return ListaArchivosAvaEnt;
    }

    public void setListaArchivosAvaEnt(List<BeanArchivos> ListaArchivosAvaEnt) {
        this.ListaArchivosAvaEnt = ListaArchivosAvaEnt;
    }

    public List<BeanArchivos> getListaArchivosAvaRevision() {
        return ListaArchivosAvaRevision;
    }

    public void setListaArchivosAvaRevision(List<BeanArchivos> ListaArchivosAvaRevision) {
        this.ListaArchivosAvaRevision = ListaArchivosAvaRevision;
    }

    public List<BeanArchivos> getListaArchivosFact() {
        return ListaArchivosFact;
    }

    public void setListaArchivosFact(List<BeanArchivos> ListaArchivosFact) {
        this.ListaArchivosFact = ListaArchivosFact;
    }

    public List<BeanArchivos> getListaArchivosRadiCorreo() {
        return ListaArchivosRadiCorreo;
    }

    public void setListaArchivosRadiCorreo(List<BeanArchivos> ListaArchivosRadiCorreo) {
        this.ListaArchivosRadiCorreo = ListaArchivosRadiCorreo;
    }

    public List<BeanArchivos> getListaArchivosAdmin() {
        return ListaArchivosAdmin;
    }

    public void setListaArchivosAdmin(List<BeanArchivos> ListaArchivosAdmin) {
        this.ListaArchivosAdmin = ListaArchivosAdmin;
    }

    public List<BeanArchivos> getListaArchivosAvaAnteriorSol() {
        return ListaArchivosAvaAnteriorSol;
    }

    public void setListaArchivosAvaAnteriorSol(List<BeanArchivos> ListaArchivosAvaAnteriorSol) {
        this.ListaArchivosAvaAnteriorSol = ListaArchivosAvaAnteriorSol;
    }

    public List<BeanArchivos> getListaArchivosAvaAnteriorInf() {
        return ListaArchivosAvaAnteriorInf;
    }

    public void setListaArchivosAvaAnteriorInf(List<BeanArchivos> ListaArchivosAvaAnteriorInf) {
        this.ListaArchivosAvaAnteriorInf = ListaArchivosAvaAnteriorInf;
    }

    public List<LogCliente> getListClientesPersonasAFacturarAntic() {
        return ListClientesPersonasAFacturarAntic;
    }

    public void setListClientesPersonasAFacturarAntic(List<LogCliente> ListClientesPersonasAFacturarAntic) {
        this.ListClientesPersonasAFacturarAntic = ListClientesPersonasAFacturarAntic;
    }

    public List<LogEntidad> getListEntidadesPersonasAFacturarAntic() {
        return ListEntidadesPersonasAFacturarAntic;
    }

    public void setListEntidadesPersonasAFacturarAntic(List<LogEntidad> ListEntidadesPersonasAFacturarAntic) {
        this.ListEntidadesPersonasAFacturarAntic = ListEntidadesPersonasAFacturarAntic;
    }

    public boolean isEstadoPanelArchivoCli() {
        return EstadoPanelArchivoCli;
    }

    public void setEstadoPanelArchivoCli(boolean EstadoPanelArchivoCli) {
        this.EstadoPanelArchivoCli = EstadoPanelArchivoCli;
    }

    public boolean isEstadoPanelArchivoAva() {
        return EstadoPanelArchivoAva;
    }

    public void setEstadoPanelArchivoAva(boolean EstadoPanelArchivoAva) {
        this.EstadoPanelArchivoAva = EstadoPanelArchivoAva;
    }

    public boolean isEstadoPanelArchivoAnti() {
        return EstadoPanelArchivoAnti;
    }

    public void setEstadoPanelArchivoAnti(boolean EstadoPanelArchivoAnti) {
        this.EstadoPanelArchivoAnti = EstadoPanelArchivoAnti;
    }

    public String getOpcionPanel() {
        return OpcionPanel;
    }

    public void setOpcionPanel(String OpcionPanel) {
        this.OpcionPanel = OpcionPanel;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public boolean isValidarTodo() {
        return validarTodo;
    }

    public void setValidarTodo(boolean validarTodo) {
        this.validarTodo = validarTodo;
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
     * Metodo que consulta los tipos de archivos segun la seleccion del panel,
     * que se haga en el proceso de radicacion y el tipo de avaluo que se haya
     * registrado
     *
     * @param Op =Si el panel es de avaluo, anticipo,cliente, o subidda de
     * archivos de informe
     * @return ArrayList
     */
    public ArrayList<SelectItem> getConsulArchivos(int Op) {
        CargArchivos = new ArrayList<>();
        try {
            Iterator<LogCargueArchivos> Ite;
            Ite = Arch.getArchivos(Op).iterator();
            while (Ite.hasNext()) {
                LogCargueArchivos MBAdm = Ite.next();
                this.CargArchivos.add(new SelectItem(MBAdm.getCodTipArchivo(), MBAdm.getNombreArchivo()));
            }
        } catch (Exception e) {
            System.err.println(" CLASE : " + this.getClass() + ".ConsultaArchivos()");
            System.err.println(" ERROR : " + e.getMessage());
        }
        return this.CargArchivos;
    }

    /**
     * Metodo que consulta los archivos que se ingresarons como lista desde la
     * preradicacion, y fueron seleccionadas
     */
    public void consultaArchivosDesdePreRad() {
        ListaArchivosDesdePreRadic = Arch.consultaArchivosDesdePreRadi(getNAvaluo(), 1);
    }

    public int ValorMod = 0;

    public void consultaArchivo(int Tipo) throws IOException, Exception {
        int ValorCodTipoArch = codTipArchivo;
        mbTodero.setMens("CodArchivo" + ValorCodTipoArch);
        mbTodero.info();
        if (NombreArchivo == null || codTipArchivo <= 0) {
            mbTodero.setMens("Falta Informacion para el cargue del Archivo");
            mbTodero.warn();
        } else if (NombreArchivo.getSize() > 20971520) {
            mbTodero.setMens("El tamanoo del archivo es superior a lo establecido (20 MB), varifique y vuelva a generar el cargue");
            mbTodero.warn();
        } else {
            switch (Tipo) {
                case 1:
                    // Si los Archivos son Para Solicitud
                    Solicitud();//Se llama al metodo que se encarga de realizar cargue de archivos de solicitud
                    break;
                case 2:

                    // Si los Archivos son Para Informe
                    Informe();//Se llama al metodo que se encarga de realizar cargue de archivos de Informe
                    break;
                case 3:
                    // Si los Archivos son Para Cliente
                    Cliente();//Se llama al metodo que se encarga de realizar cargue de archivos de Cliente
                    break;
                default:
                    break;
            }
        }
    }


    public void Nuevopath(String Directorio) {
        File Ruta = new File(Directorio);
        if (!Ruta.exists()) {
            Ruta.mkdirs();
        }
    }

    private void Solicitud() throws SQLException {
        boolean Op = false;
        if (!NombreArchivo.getFileName().startsWith(String.valueOf(NAvaluo))) {
            mbTodero.setMens("El archivo no inicia con el numero del avalúo, por favor verifique");
            mbTodero.warn();
        } else {
            int Paso = 0;//Para realizar paso si va a registrar el archivo
            int NumTipAva = NumerAvaluo(TipoAvaluo);
            Archiv = Arch.ConsulDirectorioAvaluo(NumTipAva, NAvaluo, NBien, "DBSolicitud" + File.separator);//Path para el arhivo que contenga la ruta de la carpeta
            Path = File.separator + BaseArc + File.separator + Archiv;//Crea el path del Archivo con el Directorio Base
            ArchivoCreado = Arch.PathExistente(Path);// Si esta creado el path hasta la carpeta correspondiente a este numero de avaluo
            if (ArchivoCreado == false) {//Si el path no se encuentra se procede a crearse
                //Genera la creacion del Path si no se Encuentra
                Nuevopath(Path);
                //Path = Archiv + File.separator + NombreArchivo.getFileName().toString();//Carga el path completo mas el nombre Archivo
                Path = Path + NombreArchivo.getFileName();//Carga el path completo mas el nombre Archivo
                Paso = 1;//Esta variables guardara la informacion para identificar que si se hara cargue de archivo en el path creado
            } else {
                Path = Path + NombreArchivo.getFileName();//Carga el path completo mas el nombre Archivo
                ArchivoCreado = Arch.PathExistente(Path);
                if (ArchivoCreado == true) {//Si el path se encuentra mas el archivo
                    Paso = 2;
                    mbTodero.setMens("El archivo ya se encuentra creado");
                    mbTodero.warn();
                } else {
                    Paso = 1;
                }
            }
            if (Paso == 1) {
                Op = CargaPath();
                if (Op == true) {
                    Arch.setCodTipArchivo(codTipArchivo);
                    Arch.setPath(Path);
                    try {
                        Result = Arch.InserArchivoBD(2, NAvaluo, NBien, 0, 0, mBSesion.codigoMiSesion());
                        if (Result == true) {
                            ListaArchivosAva = new ArrayList<>();
                            ListaArchivosAva = MostrarArchivos(1, File.separator + BaseArc + File.separator + Archiv);
                            mbTodero.setMens("El archivo ha sido subido correctamente");
                            mbTodero.info();
                        } else {
                            mbTodero.setMens("Error en la carga del Archivo");
                            mbTodero.error();
                        }
                    } catch (SQLException e) {
                        mbTodero.setMens("Error: " + e.getMessage());
                        mbTodero.fatal();
                    }
                } else {
                    mbTodero.setMens("Error en la carga del Archivo");
                    mbTodero.error();
                }
            }
        }
    }

    /**
     * Metodo que cargara la informacion de los archivos en el momento que
     * lleguen al area de entrega, Estara en el path de DBInforme.
     */
    private void Informe() {
        boolean Op = false;

        if (!NombreArchivo.getFileName().startsWith(String.valueOf(NAvaluo))) {
            mbTodero.setMens("El archivo no comienza con el número del Avaluo");
            mbTodero.warn();
        } else {
            int Paso = 0;
            int NumTipAva = 0;
            try {
                NumTipAva = NumerAvaluo(TipoAvaluo);
            } catch (SQLException e) {
                System.err.println("Error: " + e.getMessage());
            }
            Archiv = Arch.ConsulDirectorioAvaluo(NumTipAva, NAvaluo, NBien, "DBInforme" + File.separator);//Path para el archivo que contenga la ruta de la carpeta
            Path = File.separator + BaseArc + File.separator + Archiv;
            ArchivoCreado = Arch.PathExistente(Path);// Si esta creado el path hasta la carpeta correspondiente a este numero de avaluo
            if (ArchivoCreado == false) {//Si el path no se encuentra se procede a crearse
                //Genera la creacion del Path si no se Encuentra
                Nuevopath(Path);
                //Path = Archiv + File.separator + NombreArchivo.getFileName().toString();//Carga el path completo mas el nombre Archivo
                Path = Path + NombreArchivo.getFileName();//Carga el path completo mas el nombre Archivo
                Paso = 1;//Esta variables guardara la informacion para identificar que si se hara cargue de archivo en el path creado
            } else {
                Path = Path + NombreArchivo.getFileName();//Carga el path completo mas el nombre Archivo
                ArchivoCreado = Arch.PathExistente(Path);
                if (ArchivoCreado == true) {//Si el path se encuentra mas el archivo
                    Paso = 2;
                    mbTodero.setMens("El archivo ya se encuentra creado");
                    mbTodero.warn();
                } else {
                    Paso = 1;
                }
            }
            //Si toda la información se encuentra correcta, puede realizar el ingreso del archivo tanto al filesystem comoo a bd
            if (Paso == 1) {
                Op = CargaPath();
                if (Op == true) {
                    Arch.setCodTipArchivo(codTipArchivo);
                    Arch.setPath(Path);
                    try {
                        //Genera el registro en la base de datos del documento
                        Arch.setValorAnt(ValorAvaluo);
                        Result = Arch.InserArchivoBD(6, NAvaluo, NBien, 0, 0, mBSesion.codigoMiSesion());
                        if (Result == true) {
                            ListaArchivosAvaEnt = new ArrayList<>();
                            ListaArchivosAvaEnt = MostrarArchivos(3, File.separator + BaseArc + File.separator + Archiv);
                            mbTodero.setMens("El archivo ha sido subido correctamente");
                            mbTodero.info();
                        } else {
                            mbTodero.setMens("Error en la carga del Archivo");
                            mbTodero.error();
                        }
                    } catch (SQLException e) {
                        mbTodero.setMens("Error: " + e.getMessage());
                        mbTodero.fatal();
                    }
                } else {
                    mbTodero.setMens("Error en la carga del Archivo");
                    mbTodero.error();
                }
            }
        }
    }

    public void subirArchivoReasig() {

        boolean Op = false;

        if (!NombreArchivo.getFileName().startsWith(String.valueOf(NAvaluo))) {
            mbTodero.setMens("El archivo no comienza con el número del Avaluo");
            mbTodero.warn();
        } else {
            int Paso = 0;
            int NumTipAva = 0;
            try {
                NumTipAva = NumerAvaluo(TipoAvaluo);
            } catch (SQLException e) {
                System.err.println("Error: " + e.getMessage());
            }
            Archiv = Arch.ConsulDirectorioAvaluo(NumTipAva, NAvaluo, NBien, "DBInforme" + File.separator);//Path para el archivo que contenga la ruta de la carpeta
            Path = File.separator + BaseArc + File.separator + Archiv;
            ArchivoCreado = Arch.PathExistente(Path);// Si esta creado el path hasta la carpeta correspondiente a este numero de avaluo
            if (ArchivoCreado == false) {//Si el path no se encuentra se procede a crearse
                //Genera la creacion del Path si no se Encuentra
                Nuevopath(Path);
                //Path = Archiv + File.separator + NombreArchivo.getFileName().toString();//Carga el path completo mas el nombre Archivo
                Path = Path + NombreArchivo.getFileName();//Carga el path completo mas el nombre Archivo
                Paso = 1;//Esta variables guardara la informacion para identificar que si se hara cargue de archivo en el path creado
            } else {
                Path = Path + NombreArchivo.getFileName();//Carga el path completo mas el nombre Archivo
                ArchivoCreado = Arch.PathExistente(Path);
                if (ArchivoCreado == true) {//Si el path se encuentra mas el archivo
                    Paso = 2;
                    mbTodero.setMens("El archivo ya se encuentra creado");
                    mbTodero.warn();
                } else {
                    Paso = 1;
                }
            }
            //Si toda la información se encuentra correcta, puede realizar el ingreso del archivo tanto al filesystem comoo a bd
            if (Paso == 1) {
                Op = CargaPath();
                if (Op == true) {
                    Arch.setCodTipArchivo(codTipArchivo);
                    Arch.setPath(Path);
                    try {
                        //Genera el registro en la base de datos del documento
                        Arch.setValorAnt(ValorAvaluo);
                        Result = Arch.InserArchivoBD(6, NAvaluo, NBien, 0, 0, mBSesion.codigoMiSesion());
                        if (Result == true) {
                            ListaArchivosAvaEnt = new ArrayList<>();
                            ListaArchivosAvaEnt = MostrarArchivos(3, File.separator + BaseArc + File.separator + Archiv);
                            mbTodero.setMens("El archivo ha sido subido correctamente");
                            mbTodero.info();
                        } else {
                            mbTodero.setMens("Error en la carga del Archivo");
                            mbTodero.error();
                        }
                    } catch (SQLException e) {
                        mbTodero.setMens("Error: " + e.getMessage());
                        mbTodero.fatal();
                    }
                } else {
                    mbTodero.setMens("Error en la carga del Archivo");
                    mbTodero.error();
                }
            }
        }
    }

    /**
     * Metodo que cargara los archivos que sean necesarios del cliente, como ,es
     * el RUT Se encuentra en el path de DBCliente.
     */
    private void Cliente() {
        boolean Op = false;
        if (mBCliente.getCod_cliente() == 0) {
            mbTodero.setMens("Falta seleccionar el Cliente al cual se le Asocia el Archivo");
            mbTodero.warn();
        } else {
            int Var = 0, Paso = 0;
            Var = NombreArchivo.getFileName().length();
            if (Var != 0) {
                char Letra = NombreArchivo.getFileName().charAt(Var - 5);
                if (Letra >= '0' && Letra <= '9') {
                    try {
                        String ValCli = Clie.ConsulCliente(mBCliente.getCod_cliente(), "Numero_DocCliente");
                        if (!NombreArchivo.getFileName().startsWith(ValCli)) {
                            mbTodero.setMens("El Archivo no inicia con el numero de Documento del Cliente");
                            mbTodero.warn();
                        } else {
                            Archiv = File.separator + BaseArc + File.separator + "DBCliente" + File.separator + Letra + File.separator + ValCli;
                            ArchivoCreado = Arch.PathExistente(Archiv);
                            if (ArchivoCreado == false) {
                                //Genera la creacion del Path si no se Encuentra
                                Nuevopath(Archiv);
                                Path = Archiv + File.separator + NombreArchivo.getFileName();//Carga el path completo mas el nombre Archivo
                                Paso = 1;//Esta variables guardara la informacion para identificar que si se hara cargue de archivo en el path creado
                            } else {
                                ArchivoCreado = Arch.PathExistente(Archiv + File.separator + NombreArchivo.getFileName());
                                if (ArchivoCreado == true) {
                                    mbTodero.setMens("El archivo ya se encuentra creado");
                                    mbTodero.warn();
                                    Paso = 2;
                                } else {
                                    Path = Archiv + File.separator + NombreArchivo.getFileName();//Carga el path completo mas el nombre Archivo
                                    Paso = 1;
                                }
                            }
                            if (Paso == 1) {
                                Op = CargaPath();
                                if (Op == true) {
                                    Arch.setCodTipArchivo(codTipArchivo);
                                    Arch.setPath(Path);
                                    try {
                                        Result = Arch.InserArchivoBD(3, NAvaluo, NBien, 0, mBCliente.getCod_cliente(), mBSesion.codigoMiSesion());
                                        if (Result == true) {
                                            ListaArchivosCli = new ArrayList<>();
                                            ListaArchivosCli = MostrarArchivos(2, File.separator + BaseArc + File.separator + "DBCliente");
                                            mbTodero.setMens("El archivo ha sido subido correctamente");
                                            mbTodero.info();
                                        } else {
                                            mbTodero.setMens("Error en la carga del Archivo");
                                            mbTodero.error();
                                        }
                                    } catch (SQLException e) {
                                        mbTodero.setMens("Error: " + e.getMessage());
                                        mbTodero.fatal();
                                    }
                                } else {
                                    mbTodero.setMens("Error en la carga del Archivo");
                                    mbTodero.error();
                                }
                            }
                        }
                    } catch (SQLException e) {
                        mbTodero.setMens("Error: " + e.getMessage());
                        mbTodero.fatal();
                    }
                } else {
                    mbTodero.setMens("El nombre del archivo no termina en un valor númerico, favor verifique");
                    mbTodero.error();
                }

            }

        }
    }



    public void ProcGnrlAnti() {

        try {

            String Fech = Format.format(Arch.getFechaSol());
            Arch.setFechaAntConsigna(Fech);
            int Paso = 0;
            Arch.setCodTipArchivo(codTipArchivo);
            Arch.setPath(NombreArchivo.getFileName());
            try {
                //Se asigno el campo de Nbien para que tomara el estado del anticipo si va gha multiple avaluo

                Result = Arch.InserArchivoBD(4, 0, Integer.valueOf(OpcValorantiavalu), 0, mBCliente.Cli.getCodCliente(), mBSesion.codigoMiSesion());
                if (Result == true) {
                    Path = File.separator + BaseArc + File.separator + "DBAnticipos" + File.separator + Arch.getPath();
                    Path = Path.replace("/", File.separator);
                    int NumAnt = Arch.getCodAnt();
                    String Year = Arch.getAnio(), Month = Arch.getMes();
                    Arch.setCodTipArchivo(NumAnt);
                    ResInsertat = Arch.InsertAnticpCliente(NAvaluo, NumAnt, NBien, this.getListClientesPersonasAFacturarAntic().size(), this.getListEntidadesPersonasAFacturarAntic().size(), this.getCodClienteAnti(), this.getCodEntidAnti(), getValorClienteAnti(), this.getValorEntdAnti());
                    if (ResInsertat == 1) {
                        ArchivoCreado = Arch.PathExistente(Path);
                        if (ArchivoCreado == true) {
                            Path = Path + NombreArchivo.getFileName();
                            ArchivoCreado = Arch.PathExistente(Path);
                            if (ArchivoCreado == true) {
                                Clear();
                                mbTodero.setMens("El archivo ya se encuentra creado, favor verifique");
                                mbTodero.warn();
                                Arch.ActualArchivos(4, NAvaluo, NBien, mBSesion.codigoMiSesion());
                                Arch.AlterTable(NumAnt);
                                Paso = 0;

                            } else {
                                Path = Path + NombreArchivo.getFileName();
                                Paso = 1;
                            }
                        } else {
                            Nuevopath(Path);
                            Path = Path + NombreArchivo.getFileName();
                            Paso = 1;
                        }
                        if (Paso == 1) {
                            Result = CargaPath();
                            if (Result == true) {
                                ListaArchivosAnt = new ArrayList<>();
                                ListaArchivosAnt = MostrarArchivos(5, File.separator + BaseArc + File.separator + "DBAnticipos" + File.separator + Year + File.separator + Month);
                                mbTodero.setMens("La información del anticipo ha sido cargada correctamente");
                                mbTodero.info();
                                Clear();

                            } else {
                                Clear();
                                mbTodero.setMens("Error en la subida del archivo del anticipo");
                                mbTodero.error();
                                /*Esto es para validar la informacion que ya se creo del anticipo y reversarla*/
                                Arch.ActualArchivos(4, NAvaluo, NBien, mBSesion.codigoMiSesion());
                                Arch.AlterTable(NumAnt);

                            }
                        }
                    } else {
                        Clear();
                        mbTodero.setMens("Error en la subida de información del anticipo");
                        mbTodero.error();
                        /*Esto es para validar la informacion que ya se creo del anticipo y reversarla*/
                        Arch.ActualArchivos(4, NAvaluo, NBien, mBSesion.codigoMiSesion());
                        Arch.AlterTable(NumAnt);

                    }
                } else {
                    Clear();
                    mbTodero.setMens("Error en la subida de información del anticipo");
                    mbTodero.error();

                }
            } catch (SQLException ex) {
                Clear();
                Logger.getLogger(BeanArchivos.class.getName()).log(Level.SEVERE, null, ex);
                mbTodero.setMens("Error: " + ex.getMessage());
                mbTodero.fatal();

            }
        } catch (NumberFormatException e) {
            mbTodero.setMens("Error");
            mbTodero.info();
        }
    }

    /**
     * Metodo para ingresar los archivos empresariales o plantillas, segun el
     * modulo en el cual se deseen cargar.
     *
     * @param CodM = Parametro que me contenera el codigo al modulo al cual
     * deseo ingresar los archivoss
     */
    public void docEmpre(String CodM) {
        if (NombreArchivo == null || "".equals(CodM)) {
            mbTodero.setMens("Falta informacion para asociar la plantilla, favor verifique");
            mbTodero.warn();
        } else if (NombreArchivo.getSize() > 20971520) {
            mbTodero.setMens("El archivos supera el tamaño establecido (20 MB) favor verifique");
            mbTodero.warn();
        } else {
            int Paso = 0;
            //El name traera el nombre del Modulo
            Path = BaseArcEmpres + "DBAdjuntos" + File.separator + Name + File.separator;
            ArchivoCreado = Arch.PathExistente(Path);
            if (ArchivoCreado == false) {
                Nuevopath(Path);
                Path = Path + NombreArchivo.getFileName();
                Paso = 1;
            } else {
                ArchivoCreado = Arch.PathExistente(Path + NombreArchivo.getFileName());
                if (ArchivoCreado == true) {
                    mbTodero.setMens("El archivos ya se encuentra Creado");
                    mbTodero.warn();
                    Paso = 0;
                } else {
                    Path = Path + NombreArchivo.getFileName();
                    Paso = 1;
                }
            }
            boolean Op = false;
            if (Paso == 1) {
                Op = CargaPath();
                if (Op == true) {
                    ListaArchivosAdmin = new ArrayList<>();
                    ListaArchivosAdmin = MostrarArchivos(7, BaseArcEmpres + "DBAdjuntos" + File.separator + Name + File.separator);
                    mbTodero.setMens("El archivo ha sido subido correctamente");
                    mbTodero.info();
                } else {
                    mbTodero.setMens("Error en la carga del Archivo");
                    mbTodero.error();
                }
            }
        }
    }

    /**
     * Metodo que cargara el archivo dentro del filsystem y enviara un resultado
     * de respuesta para validar si el archivo se ha subido correctamente.
     *
     * @return boolean true si se subio correctamente, false si genero algun
     * tipo de error
     */
    public boolean CargaPath() {
        boolean SubeArc = false;
        InputStream input = null;
        OutputStream output = null;
        try {
            output = new FileOutputStream(new File(Path));
            input = this.NombreArchivo.getInputstream();
            int read;
            byte[] bytes = new byte[1024];
            while ((read = input.read(bytes)) != -1) {
                output.write(bytes, 0, read);
            }
            SubeArc = true;

        } catch (FileNotFoundException ex) {
            Logger.getLogger(BeanArchivos.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BeanArchivos.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return SubeArc;
    }

    public boolean CargaPath2(String Path) {
        boolean SubeArc = false;
        InputStream input = null;
        OutputStream output = null;
        try {
            output = new FileOutputStream(new File(Path));
            input = this.NombreArchivo.getInputstream();
            int read;
            byte[] bytes = new byte[1024];
            while ((read = input.read(bytes)) != -1) {
                output.write(bytes, 0, read);
            }
            SubeArc = true;

        } catch (FileNotFoundException ex) {
            Logger.getLogger(BeanArchivos.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BeanArchivos.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return SubeArc;
    }

    public List<BeanArchivos> MostrarArchivos(int Tipo, String DirecPrin) {
        List<BeanArchivos> Lista = new ArrayList<>();

        String Ruta = "";
        switch (Tipo) {

            case 1:
                // Si los Archivos son Para Avaluo-Solicitud
                ListFile = getFileLista(DirecPrin);//Verifica si hay archivos dentro del path ingresado
                if (ListFile != null) {//Si el direcotorio no se encuentra vacio procede a llenarlo
                    ObjFile = getFileObjLista(DirecPrin);
                    for (int i = 0; i < ListFile.length; i++) {
                        Archi = new BeanArchivos();
                        Archi.setPath(DirecPrin + ListFile[i]);
                        Archi.setName(ListFile[i]);
                        Archi.setNAvaluo(NAvaluo);
                        Archi.setNumero(i);
                        Archi.setFecha(DateFormat.getInstance().format(new Date(ObjFile[i].lastModified())));
                        Archi.setTamano(ObjFile[i].length());
                        Lista.add(Archi);
                    }
                }
                break;
            case 2:
                // Si los Archivos son Para Cliente
                try {
                    int CanCli = mBCliente.getCargaClienteFac().size(), CCli = 0;
                    String ValCli = "";
                    //Archi = new BeanArchivos();
                    for (int i = 0; i < CanCli; i++) {
                        ValCli = Clie.ConsulCliente((int) mBCliente.getCargaClienteFac().get(i).getValue(), "Numero_DocCliente");//Trae la informacion del documento del Cliente
                        int CanNum = ValCli.length();//Numero de caracteres que trae el documento del Cliente
                        char NumDoc = ValCli.charAt(CanNum - 1);//Trae el ultimo caracter que tenga el documento del Cliente
                        Ruta = DirecPrin + File.separator + NumDoc + File.separator + ValCli;
                        ListFile = getFileLista(Ruta);
                        ObjFile = getFileObjLista(Ruta);
                        if (ListFile != null) {
                            Archi = new BeanArchivos();
                            Archi.setNumero(i);
                            Archi.setPath(Ruta + File.separator + ListFile[0]);
                            Archi.setName(ListFile[0]);//Nombre del Archivo como se encuentra dentro del Path de Archivos de Cliente
                            Archi.setFecha(DateFormat.getInstance().format(new Date(ObjFile[0].lastModified())));//Fecha en la cual se hizo la subida del Archivo
                            Archi.setTamano(ObjFile[0].length());// Tamaño en KB de almacenamiento de Archivo
                            Lista.add(Archi);
                        }
                    }
                } catch (Exception e) {
                    mbTodero.setMens("Error: tatiana" + e.getMessage());
                    mbTodero.fatal();
                }
                break;
            case 3:
                // Si los Archivos son Para Avaluo-Informe
                ListFile = getFileLista(DirecPrin);//Verifica si hay archivos dentro del path ingresado
                if (ListFile != null) {//Si el directorio no se encuentra vacio procede a llenarlo
                    ObjFile = getFileObjLista(DirecPrin);
                    for (int i = 0; i < ListFile.length; i++) {

                        Archi = new BeanArchivos();
                        Archi.setPath(DirecPrin + ListFile[i]);
                        Archi.setName(ListFile[i]);
                        Archi.setNAvaluo(NAvaluo);
                        try {
                            Archi.setRParam(Arch.ConsultaResuParametro(NAvaluo, ListFile[i]));
                        } catch (SQLException ex) {
                            Logger.getLogger(BeanArchivos.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        Archi.setNumero(i);
                        Archi.setFecha(DateFormat.getInstance().format(new Date(ObjFile[i].lastModified())));
                        Archi.setTamano(ObjFile[i].length());
                        Lista.add(Archi);
                    }
                }
                break;
            // Si los Archivos son Para Factura
            case 4:
                break;
            case 5:
                // Si los Archivos son Para Anticipos
                try {
                    while (Dat.next()) {
                        Ruta = DirecPrin + File.separator + Dat.getString("Cod_Anticipo") + File.separator;
                        ListFile = getFileLista(Ruta);//Verifica si hay archivos dentro del path ingresado
                        if (ListFile != null) {
                            ObjFile = getFileObjLista(Ruta);
                            for (int i = 0; i < ListFile.length; i++) {
                                Archi = new BeanArchivos();
                                Archi.setPath(Ruta + ListFile[i]);
                                Archi.setName(ListFile[i]);
                                Archi.setNAvaluo(NAvaluo);
                                Archi.setNumero(i);
                                Archi.setFecha(DateFormat.getInstance().format(new Date(ObjFile[i].lastModified())));
                                Archi.setTamano(ObjFile[i].length());
                                Lista.add(Archi);
                            }
                        }
                    }
                } catch (SQLException e) {
                    System.err.println("Error: " + e.getMessage());
                }
                break;
            case 6:
                //Si son los archivos adjuntos que se van a enviar dentro del proceso de los correos del perito,(Archivos empresariales) en el proceso de PreRadicacion
                ListFile = getFileLista(DirecPrin);//Verifica si hay archivos dentro del path ingresado
                if (ListFile != null) {//Si el directorio no se encuentra vacio procede a llenarlo
                    ObjFile = getFileObjLista(DirecPrin);
                    for (int i = 0; i < ListFile.length; i++) {
                        Archi = new BeanArchivos();
                        Archi.setPath(DirecPrin + ListFile[i]);
                        Archi.setName(ListFile[i]);
                        Archi.setNAvaluo(NAvaluo);
                        Archi.setFecha(DateFormat.getInstance().format(new Date(ObjFile[i].lastModified())));//Fecha en la cual se hizo la subida del Archivo
                        Archi.setTamano(ObjFile[i].length());// Tamaño en KB de almacenamiento de Archivo
                        Archi.setNumero(i);
                        Lista.add(Archi);
                    }
                }
                break;
            case 7:
                //Para Cargar las Listas en la Administracion
                ListFile = getFileLista(DirecPrin);//Verifica si hay archivos dentro del path ingresado
                if (ListFile != null) {//Si el directorio no se encuentra vacio procede a llenarlo
                    ObjFile = getFileObjLista(DirecPrin);
                    for (int i = 0; i < ListFile.length; i++) {
                        Archi = new BeanArchivos();
                        Archi.setPath(DirecPrin + ListFile[i]);
                        Archi.setName(ListFile[i]);
                        Archi.setFecha(DateFormat.getInstance().format(new Date(ObjFile[i].lastModified())));//Fecha en la cual se hizo la subida del Archivo
                        Archi.setTamano(ObjFile[i].length());// Tamaño en KB de almacenamiento de Archivo
                        Archi.setNumero(i);
                        Lista.add(Archi);
                    }
                }
                break;
 

            default:
                break;
        }

        return Lista;
    }


    public String[] getFileLista(String path) {
        String[] fileL;
        File f = new File(path);
        fileL = f.list();
        return fileL;
    }

  
    public File[] getFileObjLista(String path) {
        File[] file;
        File f = new File(path);
        file = f.listFiles();
        return file;
    }


  
    public void seleccionPanel(int Numero) {
        try {
            switch (this.OpcionPanel) {
                case "":
                    mbTodero.setMens("Seleccione una opcion para el cargue de Archivos");
                    mbTodero.info();
                    break;
                case "SelCli":
                    this.EstadoPanelArchivoCli = true;
                    this.EstadoPanelArchivoAva = false;
                    this.EstadoPanelArchivoAnti = false;
                    mBCliente.CargClienteFact(Numero);
                    Limp(4);
                    ListaArchivosCli.clear();
                    ListaArchivosCli = MostrarArchivos(2, File.separator + BaseArc + File.separator + "DBCliente");
                    break;
                case "SelAva": {
                    this.EstadoPanelArchivoCli = false;
                    this.EstadoPanelArchivoAva = true;
                    this.EstadoPanelArchivoAnti = false;
                    int NumTipAva = NumerAvaluo(TipoAvaluo);
                    Limp(NumTipAva);
                    Archiv = Arch.ConsulDirectorioAvaluo(NumTipAva, NAvaluo, NBien, "DBSolicitud" + File.separator);//Path para el arhivo que contenga la ruta de la carpeta
                    ListaArchivosAva.clear();
                    ListaArchivosAva = MostrarArchivos(1, File.separator + BaseArc + File.separator + Archiv);
                    break;
                }

                case "SelEntrega"://Esta Informacion es para la Carga desde Entrega (Informe pendiente de entrega del perito)
                    ListaArchivosAvaEnt = new ArrayList<>();
                    ListaArchivosAvaEnt = MostrarArchivos(3, Path);
                    //mBSesion.setCod_empleado(String.valueOf(Numero));
                    break;
            }
        } catch (SQLException e) {
            mbTodero.setMens("Error " + e.getClass());
            mbTodero.error();
        }

    }

   
  
    public void Limp(int Num) {
        this.CargArchivos = new ArrayList<>();
        this.CargArchivos = getConsulArchivos(Num);
    }

 
    public int NumerAvaluo(String TipAvaluo) throws SQLException {
        int Numer = Arch.ConsultaTipAval(TipAvaluo);
        return Numer;
    }

   
    public void getcargArchivosLista() {
        try {
            //CargListaVerifiArchivos.clear();
            CargaAsignados = new ArrayList<>();
            for (int j = 0; j <= this.SeleccionArc.size() - 1; j++) {
                String codigo = SeleccionArc.get(j).toString();
                Arch.setCodTipArchivo(Integer.parseInt(codigo));
                String OPciones = Arch.ConsulNombreArchivo();
                CargaAsignados.add(new SelectItem(codigo, OPciones));
            }
        } catch (NumberFormatException | SQLException e) {
            mbTodero.setMens("Error " + e.getClass());
            mbTodero.error();
        }
    }


    public ArrayList<SelectItem> getVerificacArchivos(int Op) {
        try {
            SeleccionArc = new ArrayList();
            CargListaVerifiArchivos.clear();
            CargaAsignados.clear();
            Iterator<LogCargueArchivos> Ite;
            Ite = Arch.getArchivos(Op).iterator();
            while (Ite.hasNext()) {
                LogCargueArchivos MBAdm = Ite.next();
                this.CargListaVerifiArchivos.add(new SelectItem(MBAdm.getCodTipArchivo(), MBAdm.getNombreArchivo()));
            }
        } catch (Exception e) {
            System.err.println(" CLASE : " + this.getClass() + ".ConsultaArchivos()");
            System.err.println(" ERROR : " + e.getMessage());
        }
        return this.CargListaVerifiArchivos;
    }

   
   
    public String GenerarReporte(int Tipo, String NomReporte, String Titulo, HashMap Parametro, ActionEvent event) throws SQLException {
        try {
            //Obtiene la ruta de donde se encuentra nuestro reporte
            /*sContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
             Path = sContext.getRealPath(File.separator + "REPORTES");*/
            Path = File.separator + "DBARCHIVOS" + File.separator + "DBISA" + File.separator + "REPORTES";
            Path = Path + File.separator + NomReporte + ".jasper";
            File PFile = new File(Path);
            Conexion.Conectar();
            ByteArrayOutputStream Teste = new ByteArrayOutputStream();
            JReporte = (JasperReport) JRLoader.loadObject(PFile);
            JPrint = new JasperPrint();
            int Paso = 0;
            try {
                JPrint = JasperFillManager.fillReport(JReporte, Parametro, Conexion.ConectarArchivos());
                Paso = 1;
            } catch (JRException e) {
                mensajeReporte = "Mensaje de Error: " + e.getMessage();
                Paso = 0;
            }
            if (Paso == 1) {
                switch (Tipo) {
                    case 1:
                        if ("".equals(JPrint.getName()) || JPrint.getName() == null) {
                            mensajeReporte = "No se tiene información sobre el cliente ingresado";
                        } else {
                  
                        }
                        break;
                    case 2:
                       
                        break;
                }
            }
        } catch (HeadlessException ex) {
            mensajeReporte = "Mensaje de Error: " + ex.getMessage();

        } catch (JRException ex) {
            Logger.getLogger(BeanArchivos.class
                    .getName()).log(Level.SEVERE, null, ex);
            mensajeReporte = "Mensaje de Error " + ex.getMessage();
        }

        return mensajeReporte;
    }

   
    public void descargarArchAva(int in, int TipLista) throws IOException, JRException {
        List<BeanArchivos> Lista = new ArrayList<>();
        switch (TipLista) {
            case 1://Para Archivos Avaluo, primer lista
                Lista = ListaArchivosAva;
                break;
            case 2://´Para la lista de archivos del cliente
                Lista = ListaArchivosCli;
                break;
            case 3:// Para lista de archivos de entrega 
                Lista = ListaArchivosAvaEnt;
                break;
            case 4:// Para lista de archivos de revision
                Lista = ListaArchivosAvaRevision;
                break;
            case 5://Para documentacion de anticipos
                Lista = ListaArchivosAnt;
                break;
            case 7://Para la lista de la administracion de plantillas
                Lista = ListaArchivosAdmin;
                break;

            case 8:
                Lista = ListaArchivosFact;
                break;
        }
        for (int i = 0; i < Lista.size(); i++) {
            if (i == in) {
                File FileArc = new File(Lista.get(i).getPath());
                FileInputStream FStream = new FileInputStream(FileArc);
                byte[] bytes = new byte[1024];
                int read = 0;
                FacesContext fContext = FacesContext.getCurrentInstance();
                ExternalContext eContext = fContext.getExternalContext();
                HttpServletResponse Respuest = (HttpServletResponse) eContext.getResponse();
                if (!fContext.getResponseComplete()) {
                    String cType = ExtendNombre(Lista.get(i).getName());
                    Respuest = (HttpServletResponse) eContext.getResponse();
                    Respuest.setContentType("application/" + cType);
                    Respuest.setHeader("Content-Disposition", "attachment;filename=\"" + Lista.get(i).getName() + "\"");
                    try (ServletOutputStream OutPut = Respuest.getOutputStream()) {
                        while ((read = FStream.read(bytes)) != -1) {
                            OutPut.write(bytes, 0, read);
                        }
                        OutPut.flush();
                    }
                    fContext.responseComplete();
                }
            }
        }
    }


    public void eliminarAch(int Op, int In) {

        List<BeanArchivos> Lista = new ArrayList<>();
        String Cadena = "";
        int NumTipAva = 0;
        switch (Op) {//Para las listas de plantillas que se encuentran en Administracion
            case 1://Para la elimincacion de documentos de solicitud del avaluo
                Lista = ListaArchivosAva;
                try {
                    NumTipAva = NumerAvaluo(TipoAvaluo);
                } catch (SQLException e) {
                    System.out.println("Error: " + e.getMessage());
                }
                Archiv = Arch.ConsulDirectorioAvaluo(NumTipAva, NAvaluo, NBien, "DBSolicitud" + File.separator);//Path para el arhivo que contenga la ruta de la carpeta
                Cadena = File.separator + BaseArc + File.separator + Archiv;//Crea el path del Archivo con el Directorio Base
                break;
            case 2:// Documentos para la eliminacion de Cliente
                Lista = ListaArchivosCli;
                Cadena = File.separator + BaseArc + File.separator + "DBCliente";
                break;
            case 3:// Para lista de archivos de entrega y revision}
                Lista = ListaArchivosAvaEnt;
                try {
                    NumTipAva = NumerAvaluo(TipoAvaluo);
                } catch (SQLException e) {
                    System.out.println("Error: " + e.getMessage());
                }
                Archiv = Arch.ConsulDirectorioAvaluo(NumTipAva, NAvaluo, NBien, "DBInforme" + File.separator);//Path para el arhivo que contenga la ruta de la carpeta
                Cadena = File.separator + BaseArc + File.separator + Archiv;//Crea el path del Archivo con el Directorio Base
                break;
            case 5://Documentos de Anticipo
                Lista = ListaArchivosAnt;
                Cadena = File.separator + BaseArc + File.separator + "DBAnticipos";
                break;
            case 7://Documentacion de Plantilla
                Lista = ListaArchivosAdmin;
                Cadena = BaseArcEmpres + "DBAdjuntos" + File.separator + Name + File.separator;
                break;
        }
        for (int i = 0; i < Lista.size(); i++) {
            if (i == In) {
                File FileArc = new File(Lista.get(i).getPath());
                //FileArc =new File(Lista.get(i).getPath());
                Arch.setPath(Lista.get(i).getPath());
                Arch.ActualArchivos(Op, NAvaluo, NBien, mBSesion.codigoMiSesion());
                FileArc.delete();
                if (!FileArc.exists()) {
                    mbTodero.setMens("El archivo se ha eliminado correctamente");
                    mbTodero.info();
                }
                switch (Op) {
                    case 1:
                        ListaArchivosAva = new ArrayList<>();
                        ListaArchivosAva = MostrarArchivos(Op, Cadena);
                        break;
                    case 2:
                        ListaArchivosCli = new ArrayList<>();
                        ListaArchivosCli = MostrarArchivos(Op, Cadena);
                        break;
                    case 3:
                        ListaArchivosAvaEnt = new ArrayList<>();
                        ListaArchivosAvaEnt = MostrarArchivos(Op, Cadena);
                        break;
                    case 5:
                        ListaArchivosAnt = new ArrayList<>();
                        ListaArchivosAnt = MostrarArchivos(Op, Cadena);
                        break;
                    case 7:
                        ListaArchivosAdmin = new ArrayList<>();
                        ListaArchivosAdmin = MostrarArchivos(Op, Cadena);
                        break;
                }
            }
        }
    }

    /**
     * Metodo que identifica que extension tiene el archivo para agregarlo sobre
     * el contentype del response del archivo
     *
     * @param Tipo = Reemplazo de extension por tipo
     * @return Cadena de String
     */
    private String ExtendNombre(String Tipo) {
        String Extension = "";
        //Extension = FilenameUtils.getExtension(Tipo);
        Extension = Tipo.substring(Tipo.lastIndexOf("."));
        Extension = Extension.replace(".", "");
        return Extension;
    }

    /**
     * Metodo para realizar el exportado de reportes a un archivo de excel.
     */
    public void exportadoXLS() {
        File Rutas = new File(File.separator + "DBARCHIVOS" + File.separator + "ConsultaPreRadica.xls");
        int Row = 0;
        //Escribir el tipo de formato de fuente que va a tener el archivo de Excel
        WritableFont ExcFont = new WritableFont(WritableFont.ARIAL, 12, WritableFont.NO_BOLD);
        WritableCellFormat CellFormat = new WritableCellFormat(ExcFont);
        //Presentacion o forma grafica de la hojoa de calculo
        WritableSheet ExcSheet = null;
        WritableWorkbook WorkBook = null;
        //Informacion regional de la hoja de calculo que se va a crear
        WorkbookSettings WbSettings = new WorkbookSettings();
        WbSettings.setLocale(new Locale("es", "CO"));

        if (Rutas.exists()) {//Valida si el directorio del archivo existe, lo borra y lo crea nuevamente
            Rutas.delete();
            try {
                Rutas.createNewFile();

            } catch (IOException ex) {
                Logger.getLogger(BeanArchivos.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }

        try {
            WorkBook = Workbook.createWorkbook(Rutas, WbSettings);
            //Hoja con el nombre del Cual se le da a asignar
            WorkBook.createSheet("ConsultaPreRadica", 0);
            ExcSheet = WorkBook.getSheet(0);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        try {
            Dat = Arch.prueba1();
            while (Dat.next()) {
                Label CodPreRad;
                Label FechaReg;
                Label FechaPreRadic;
                Label FechaSol;
                Label NumH;
                Label EnviarA;
                Label Estado;
                Label Cotizacion;
                Label ProEnt;
                Label Ubica;
                Label Solic;
                Label Entid;
                if (Row == 0) {
                    CodPreRad = new Label(0, Row, "N* PreRadicacion", CellFormat);
                    FechaReg = new Label(1, Row, "FechaRegistro", CellFormat);
                    FechaPreRadic = new Label(2, Row, "FechaPreRadicado", CellFormat);
                    FechaSol = new Label(3, Row, "FechaSolicitud", CellFormat);
                    NumH = new Label(4, Row, "N* Hojas", CellFormat);
                    EnviarA = new Label(5, Row, "EnviarA", CellFormat);
                    Estado = new Label(6, Row, "Estado", CellFormat);
                    Cotizacion = new Label(7, Row, "Cotizacion", CellFormat);
                    ProEnt = new Label(8, Row, "Producto Entidad", CellFormat);
                    Ubica = new Label(9, Row, "Departamento-Ciudad", CellFormat);
                    Solic = new Label(10, Row, "Solicitante", CellFormat);
                    Entid = new Label(11, Row, "Entidad", CellFormat);
                    Row++;
                } else {
                    CodPreRad = new Label(0, Row, Dat.getString("Cod_PreRadica"), CellFormat);
                    FechaReg = new Label(1, Row, String.valueOf(Dat.getDate("Fecha_Registro")), CellFormat);
                    FechaPreRadic = new Label(2, Row, Dat.getString("FechaPreRadica"), CellFormat);
                    FechaSol = new Label(3, Row, Dat.getString("FechaSolicitud"), CellFormat);
                    NumH = new Label(4, Row, String.valueOf(Dat.getInt("NumeroHojas")), CellFormat);
                    EnviarA = new Label(5, Row, Dat.getString("EnvioInformacion"), CellFormat);
                    Estado = new Label(6, Row, Dat.getString("Estado"), CellFormat);
                    Cotizacion = new Label(7, Row, Dat.getString("Cotizacion"), CellFormat);
                    ProEnt = new Label(8, Row, Dat.getString("ProductoEntidad"), CellFormat);
                    Ubica = new Label(9, Row, Dat.getString("Ubicacion"), CellFormat);
                    Solic = new Label(10, Row, Dat.getString("Solicitante"), CellFormat);
                    Entid = new Label(11, Row, Dat.getString("Entidad"), CellFormat);
                    Row++;
                }
                try {
                    ExcSheet.addCell(CodPreRad);
                    ExcSheet.addCell(FechaReg);
                    ExcSheet.addCell(FechaPreRadic);
                    ExcSheet.addCell(FechaSol);
                    ExcSheet.addCell(NumH);
                    ExcSheet.addCell(EnviarA);
                    ExcSheet.addCell(Estado);
                    ExcSheet.addCell(Cotizacion);
                    ExcSheet.addCell(ProEnt);
                    ExcSheet.addCell(Ubica);
                    ExcSheet.addCell(Solic);
                    ExcSheet.addCell(Entid);
                } catch (WriteException ex) {
                    System.err.println(ex.getMessage());
                }
            }
            //Dat.close();
            Conexion.CloseCon();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        //Escribe el archivo excel en disco
        try {
            WorkBook.write();
            WorkBook.close();
            //Genero la descarga del archivo para el usuario
            DescarArcIndi(String.valueOf(Rutas), "ConsultaPreRadica", "xls");
            Rutas.delete();
        } catch (IOException | WriteException ex) {
            System.err.println(ex.getMessage());
        }
    }

 
    public void DescarArcIndi(String UrlArc, String NombArc, String ExtArch) throws FileNotFoundException {
        File SrcArc = new File(UrlArc);
        FileInputStream FStream = new FileInputStream(SrcArc);
        byte[] bytes = new byte[1024];
        int read = 0;
        FacesContext fContext = FacesContext.getCurrentInstance();
        ExternalContext eContext = fContext.getExternalContext();
        HttpServletResponse Respuest = (HttpServletResponse) eContext.getResponse();
        if (!fContext.getResponseComplete()) {
            String cType = ExtendNombre(NombArc + "." + ExtArch);
            Respuest = (HttpServletResponse) eContext.getResponse();
            Respuest.setContentType("application/" + cType);
            Respuest.setHeader("Content-Disposition", "attachment;filename=\"" + NombArc + "." + ExtArch + "\"");
            try {
                try (ServletOutputStream OutPut = Respuest.getOutputStream()) {
                    while ((read = FStream.read(bytes)) != -1) {
                        OutPut.write(bytes, 0, read);
                    }
                    OutPut.flush();
                }
                fContext.responseComplete();
            } catch (IOException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }
   


    public List<BeanArchivos> ConstruirDescargue(String DirecPrin, int codAva, int nBien, int nAnti) {
        List<BeanArchivos> Lista = new ArrayList<>();
        try {
            String Ruta = "";

            ListFile = null;
            Ruta = "";
            ObjFile = null;

            while (Dat.next()) {

                Ruta = DirecPrin + File.separator + nAnti + File.separator;
                ListFile = getFileLista(Ruta);//Verifica si hay archivos dentro del path ingresado
                if (ListFile != null) {
                    ObjFile = getFileObjLista(Ruta);
                    for (int i = 0; i < ListFile.length; i++) {
                        Archi = new BeanArchivos();
                        Archi.setPath(Ruta + ListFile[i]);
                        Archi.setName(ListFile[i]);
                        Archi.setNAvaluo(codAva);
                        Archi.setNumero(i);
                        Archi.setFecha(DateFormat.getInstance().format(new Date(ObjFile[i].lastModified())));
                        Archi.setTamano(ObjFile[i].length());
                        Lista.add(Archi);
                    }
                }
            }
            Conexion.CloseCon();
        } catch (SQLException e) {
            mbTodero.setMens("Error: " + e.getMessage());
            mbTodero.fatal();
        }
        return Lista;
    }

    public void Clear() {
        this.NombreArchivo = null;
        this.OpcValorantiavalu = "0";
        this.NombreArchivo = null;
        Arch.setFechaSol(null);
        Arch.setFk_CuentaBanco("");
        Arch.setValorAnt(0);
        Arch.setValorParaPerFact1(0);
        Arch.setValorParaPerFact2(0);
        Arch.setValorParaPerFact3(0);
        Arch.setValorParaPerFact4(0);
        Arch.setValorParaPerFact5(0);
        CodClienteAnti = "";
        ValorClienteAnti = "";
        Arch.setValorAval(0);
        Arch.setValorAnt(0);
        Arch.setValorTotalAnti(0);
        setOpcValorantiavalu("0");
        this.NameArch = "";
    }

    public void CleanRepartValor() {
        Arch.setValorAnt(0);
        Arch.setValorParaPerFact1(0);
        Arch.setValorParaPerFact2(0);
        Arch.setValorParaPerFact3(0);
        Arch.setValorParaPerFact4(0);
        Arch.setValorParaPerFact5(0);
    }

    public String GenerarRemision(String nombre_reporte, String titulo, HashMap parametros) throws SQLException {
        try {
            //Obtiene la ruta de donde se encuentra nuestro reporte
            //  sContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            Path = File.separator + "DBARCHIVOS" + File.separator + "DBISA" + File.separator + "REPORTES";
            Path = Path + File.separator + nombre_reporte + ".jasper";
            File PFile = new File(Path);
            Conexion.Conectar();
            ByteArrayOutputStream Teste = new ByteArrayOutputStream();
            JReporte = (JasperReport) JRLoader.loadObject(PFile);
            JPrint = new JasperPrint();
            try {
                JPrint = JasperFillManager.fillReport(JReporte, parametros, Conexion.ConectarArchivos());
            } catch (JRException e) {
                System.err.println("Error: " + e.getMessage());
            }

            //new JREmptyDataSource();
        } catch (HeadlessException ex) {
            mensajeReporte = "Mensaje de Error " + ex.getMessage();

        } catch (JRException ex) {
            Logger.getLogger(BeanArchivos.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return Path;
    }

}
