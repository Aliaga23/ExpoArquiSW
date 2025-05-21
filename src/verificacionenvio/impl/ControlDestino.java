/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package verificacionenvio.impl;

import java.util.Arrays;
import java.util.List;
import verificacionenvio.dto.Paquete;
import verificacionenvio.spec.Verificador;

public class ControlDestino implements Verificador {
    private static final List<String> PAISES_RESTRINGIDOS = Arrays.asList("Corea del Norte", "Bolivia", "Siria");

    public void setSiguiente(Verificador siguiente) {
        // Último en la cadena
    }

    public String verificar(Paquete paquete) {
        StringBuilder sb = new StringBuilder();
        sb.append("🔍 [ControlDestino] → Verificando país de destino...\n");

        if (PAISES_RESTRINGIDOS.contains(paquete.getPaisDestino())) {
            sb.append("   ❌ Envío prohibido al país: ").append(paquete.getPaisDestino()).append("\n");
        } else {
            sb.append("   ✔ País permitido: ").append(paquete.getPaisDestino()).append("\n");
            sb.append("✅ Resultado final: El paquete fue aprobado exitosamente.\n");
        }
        return sb.toString();
    }
}
