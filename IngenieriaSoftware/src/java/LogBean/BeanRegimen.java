/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogBean;

import Logic.LogRegimen;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

/**
 * <p>
 * <b>Managed bean / clase que se utiliza para crear todos los atributos y
 * metodos necesarios para el proceso y manejo de los regimen y calficacion
 * regimen que se maneja  </b></p>
 *
 * @author Jhojan Stiven Rodriguez
 * @author Maira Alejandra Carvajal
 * @version 2.0.0
 * @since 01-12-2014 1.0.0
 *
 */
@ManagedBean(name = "MBRegimen")
@ViewScoped
public class BeanRegimen {

    /**
     * Variables que se conectan a las diferentes clases de el proyecto
     * NegociosISA > Logic*
     */
    Logic.LogRegimen Reg = new LogRegimen();
    /**
     * vriables de lista que cargan la información consultada*
     */
    ResultSet Dat = null;
    private ArrayList<SelectItem> CargaReg = new ArrayList<>();
    private ArrayList<SelectItem> CargaCaliReg = new ArrayList<>();
    //

    //Variables
    /**
     * Regimen*
     */
    private int CodReg;
    private String NomReg;

    /**
     * Calificativo Regimen*
     */
    private int CodCalifiReg;
    private String NomCalifiReg;
    private int EstCalifiReg;

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
     * @return
     */
    public ArrayList<SelectItem> getCargaReg() {
        return CargaReg;
    }

    public void setCargaReg(ArrayList<SelectItem> CargaReg) {
        this.CargaReg = CargaReg;
    }

    public ArrayList<SelectItem> getCargaCaliReg() {
        return CargaCaliReg;
    }

    public void setCargaCaliReg(ArrayList<SelectItem> CargaCaliReg) {
        this.CargaCaliReg = CargaCaliReg;
    }

    public int getCodReg() {
        return CodReg;
    }

    public void setCodReg(int CodReg) {
        this.CodReg = CodReg;
    }

    public String getNomReg() {
        return NomReg;
    }

    public void setNomReg(String NomReg) {
        this.NomReg = NomReg;
    }

    public int getCodCalifiReg() {
        return CodCalifiReg;
    }

    public void setCodCalifiReg(int CodCalifiReg) {
        this.CodCalifiReg = CodCalifiReg;
    }

    public String getNomCalifiReg() {
        return NomCalifiReg;
    }

    public void setNomCalifiReg(String NomCalifiReg) {
        this.NomCalifiReg = NomCalifiReg;
    }

    public int getEstCalifiReg() {
        return EstCalifiReg;
    }

    public void setEstCalifiReg(int EstCalifiReg) {
        this.EstCalifiReg = EstCalifiReg;
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
     * Metodo que sirve para cargar informacion de los regimen en un
     * SelectOneMenu de control y seguimiento
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @return
     * @since 01-05-2015
     */
    public ArrayList<SelectItem> cargaTipRegimen() {
        try {
            Iterator<LogRegimen> Ite;
            Ite = Reg.getRegimen().iterator();
            while (Ite.hasNext()) {
                LogRegimen MBReg = Ite.next();
                this.CargaReg.add(new SelectItem(MBReg.getCodReg(), MBReg.getNomReg()));
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".cargaTipRegimen()' causado por: " + e.getMessage());
            mbTodero.error();
        }
        return CargaReg;
    }

    /**
     * Metodo que sirve para cargar informacion de las calificaciones de regimen
     * o tipos de regimen en un SelectOneMenu de control y seguimiento
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @return ArrayList con lis tipos de regimen
     * @since 01-05-2015
     */
    public ArrayList<SelectItem> cargCalifiReg() {
        try {
            Iterator<LogRegimen> Ite;
            Ite = Reg.getCalifiReg(Integer.parseInt(this.NomReg)).iterator();
            while (Ite.hasNext()) {
                LogRegimen MBReg = Ite.next();
                this.CargaCaliReg.add(new SelectItem(MBReg.getCodCalifiReg(), MBReg.getNomCalifiReg()));
            }
        } catch (NumberFormatException e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".cargCalifiReg()' causado por: " + e.getMessage());
            mbTodero.error();
        }
        return CargaCaliReg;
    }

    /**
     * Metodo tipo ajax para llamar al metodo cargCalifiReg() para cargar los
     * tipos de regimen
     *
     * @author Jhojan Stiven Rodriguez
     * @author Maira Alejandra Carvajal
     * @since 01-05-2015
     */
    public void onCharge() {
        try {
            if (NomReg != null && !NomReg.equals("")) {
                CargaCaliReg.clear();
                cargCalifiReg();
            } else {
                System.out.println("No funciona");
            }
        } catch (Exception e) {
            mbTodero.setMens("Error en el metodo '" + this.getClass() + ".onCharge()' causado por: " + e.getMessage());
            mbTodero.error();
        }

    }
    
            /**
     * FIN Metodos de funcionalidad de la clase
     * -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     */

}
