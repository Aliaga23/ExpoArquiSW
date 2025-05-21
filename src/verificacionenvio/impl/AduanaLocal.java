/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package verificacionenvio.impl;

import verificacionenvio.dto.Paquete;
import verificacionenvio.spec.Verificador;

public class AduanaLocal implements Verificador {
    private Verificador siguiente;

    public void setSiguiente(Verificador siguiente) {
        this.siguiente = siguiente;
    }

    public String verificar(Paquete paquete) {
        StringBuilder sb = new StringBuilder();
        sb.append("🔍 [AduanaLocal] → Revisando contenido...\n");

        if (paquete.getContenido().equalsIgnoreCase("armas") || paquete.getContenido().equalsIgnoreCase("drogas") || paquete.getContenido().equalsIgnoreCase("químicos peligrosos")) {
            return sb.append("   ❌ Contenido ilegal: ").append(paquete.getContenido()).append("\n").toString();
        } else {
            sb.append("   ✔ Contenido legal: ").append(paquete.getContenido()).append("\n");
            sb.append("   ⏭ Pasando al siguiente verificador...\n");
            if (siguiente != null) {
                sb.append(siguiente.verificar(paquete));
            }
            return sb.toString();
        }
    }
}
