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

        if (paquete.getPeso()  <= 0 ||
            paquete.getLargo() <= 0 ||
            paquete.getAncho() <= 0 ||
            paquete.getAlto()  <= 0) {
            return sb.append("   ❌ Peso y dimensiones deben ser mayores que cero.\n")
                     .toString();
        }

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
