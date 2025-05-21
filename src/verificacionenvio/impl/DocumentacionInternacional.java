/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package verificacionenvio.impl;

import verificacionenvio.dto.Paquete;
import verificacionenvio.spec.Verificador;
/*
public class DocumentacionInternacional implements Verificador {
    private Verificador siguiente;

    public void setSiguiente(Verificador siguiente) {
        this.siguiente = siguiente;
    }

    public String verificar(Paquete paquete) {
        StringBuilder sb = new StringBuilder();
        sb.append("🔍 [DocumentacionInternacional] → Verificando documentación...\n");

        if (!paquete.isDocumentacionCompleta()) {
            sb.append("   ❌ Documentación incompleta\n");
        } else {
            sb.append("   ✔ Documentación completa\n");
            sb.append("   ⏭ Pasando al siguiente verificador...\n");
            if (siguiente != null) {
                sb.append(siguiente.verificar(paquete));
            }
        }
        return sb.toString();
    }
}
*/