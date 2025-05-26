/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package verificacionenvio.dto;

public class Paquete {
    private double peso;
    private double largo;
    private double ancho;
    private double alto;
    private String contenido;
    //private boolean documentacionCompleta;
    private String paisDestino;
    ///private static final double PESO_MAXIMO = 30.0; // Peso máximo en kg
    public Paquete(double peso, double largo, double ancho, double alto, String contenido, String paisDestino) {
        this.peso = peso;
        this.largo = largo;
        this.ancho = ancho;
        this.alto = alto;
        this.contenido = contenido;
        //this.documentacionCompleta = documentacionCompleta;
        this.paisDestino = paisDestino;
    }

    public double getPeso() { return peso; }
    public double getLargo() { return largo; }
    public double getAncho() { return ancho; }
    public double getAlto() { return alto; }
    public String getContenido() { return contenido; }
    //public boolean isDocumentacionCompleta() { return documentacionCompleta; }
    public String getPaisDestino() { return paisDestino; }
}
