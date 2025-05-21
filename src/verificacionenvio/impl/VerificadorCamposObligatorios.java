/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package verificacionenvio.impl;

import verificacionenvio.dto.Paquete;
import verificacionenvio.spec.Verificador;

public class VerificadorCamposObligatorios implements Verificador {
    private Verificador siguiente;

    @Override
    public void setSiguiente(Verificador siguiente) {
        this.siguiente = siguiente;
    }

    @Override
    public String verificar(Paquete paquete) {
        StringBuilder sb = new StringBuilder();
        sb.append("🔍 [Campos Obligatorios] → Verificando datos...\n");

        if (paquete.getContenido() == null || paquete.getContenido().trim().isEmpty()) {
            return sb.append("   ❌ El contenido no puede estar vacío.\n").toString();
        }
        if (paquete.getPaisDestino() == null || paquete.getPaisDestino().trim().isEmpty()) {
            return sb.append("   ❌ El país de destino no puede estar vacío.\n").toString();
        }

        sb.append("   ✔ Todos los campos obligatorios están completos.\n");
        if (siguiente != null) {
            sb.append(siguiente.verificar(paquete));
        }
        return sb.toString();
    }
}
