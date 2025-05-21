/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package verificacionenvio.spec;

import verificacionenvio.dto.Paquete;

public interface Verificador {
    void setSiguiente(Verificador siguiente);
    String verificar(Paquete paquete);
}
