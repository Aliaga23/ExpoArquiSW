/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package verificacionenvio.impl;

import verificacionenvio.dto.Paquete;
import verificacionenvio.spec.Verificador;

public class VerificadorDimensiones implements Verificador {
    private Verificador siguiente;

    public void setSiguiente(Verificador siguiente) {
        this.siguiente = siguiente;
    }

    public String verificar(Paquete paquete) {
        StringBuilder sb = new StringBuilder();
        sb.append("🔍 [VerificadorDimensiones] → Verificando peso y dimensiones...\n");

        double volumen = paquete.getAlto() * paquete.getAncho() * paquete.getLargo();
        if (paquete.getPeso() > 50 || volumen > 200000) {
            sb.append("   ❌ Excede límites de peso/dimensiones\n");
        } else {
            sb.append("   ✔ Peso y dimensiones dentro del límite\n");
            sb.append("   ⏭ Pasando al siguiente verificador...\n");
            if (siguiente != null) {
                sb.append(siguiente.verificar(paquete));
            }
        }
        return sb.toString();
    }
}
