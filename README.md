# Cadena de Responsabilidad para la Verificación de Envíos

> Proyecto de ejemplo en **Java + Swing** que muestra cómo aplicar el patrón **Chain of Responsibility** para validar envíos internacionales de paquetes.

---

## Índice
1. [Contexto](#contexto)
2. [Diagrama UML e Interfaz](#diagrama-uml-e-interfaz)
3. [Estructura del código](#estructura-del-código)
4. [Cómo ejecutar](#cómo-ejecutar)
5. [Explicación de la auto‑asociación](#explicación-de-la-auto‑asociación)
6. [Relaciones UML ↔ Código](#relaciones-uml-↔-código)
7. [Detalle de clases](#detalle-de-cada-clase)
8. [Flujo del patrón](#patrón-cadena-de-responsabilidad—flujo-ilustrado)
9. [Créditos](#créditos)

---

## Contexto
Un envío internacional debe pasar por una serie de comprobaciones antes de ser aprobado:

1. **Campos obligatorios** (peso, dimensiones, contenido…).
2. **Dimensiones permitidas**.
3. **Aduana local**.
4. **Control de destino** (países restringidos).

Cada verificador aplica su regla y, de superarla, delega la petición al **siguiente** verificador de la cadena.

---

## Diagrama UML e Interfaz

| Descripción | Imagen |
|-------------|--------|
| **Diagrama de Clases** – Representa la interfaz `Verificador`, los handlers concretos, la auto‑asociación `siguiente`, la clase `Main` y el DTO `Paquete`. | ![Diagrama de clases](assets/diagrama-clases.png) |
| **Interfaz Swing** – Ventana `FormularioEnvio` donde se ingresan los datos del paquete y se muestra el resultado de la verificación. | ![Interfaz Swing](assets/interfaz-swing.png) |

*(Coloca tus capturas en la carpeta **assets/** con esos nombres para que el README las muestre correctamente).*  

---

## Estructura del código
```
src/
 └─ verificacionenvio/
     ├─ dto/
     │   └─ Paquete.java
     ├─ spec/
     │   └─ Verificador.java
     ├─ impl/
     │   ├─ VerificadorCamposObligatorios.java
     │   ├─ VerificadorDimensiones.java
     │   ├─ AduanaLocal.java
     │   └─ ControlDestino.java
     ├─ ui/
     │   └─ FormularioEnvio.java
     └─ main/
         └─ Main.java
```

### Puntos clave
* `Verificador` – interfaz con `setSiguiente()` y `verificar()`.
* Cada **handler** implementa `Verificador` y posee `private Verificador siguiente;`.
* `Main` lanza la interfaz Swing (`FormularioEnvio`).
* `FormularioEnvio` arma la cadena (`campos → aduana → dimensiones → destino`) y muestra el resultado.

---

## Cómo ejecutar
1. **Clonar** el repositorio.
2. Compilar con Maven o Gradle (o directamente con `javac`). Ejemplo:
   ```bash
   javac -d out $(find src -name "*.java")
   java -cp out verificacionenvio.main.Main
   ```
3. Se abre la ventana "Verificación de Envío". Introduce datos y pulsa **Verificar Envío**.

---

## Explicación de la auto‑asociación
| Símbolo | Significado | Relación con el código |
|---------|-------------|------------------------|
| Rombo blanco (`o--`) | **Agregación compartida**: los handlers forman una **cadena** pero cada uno puede vivir por separado. | Destruir un handler no destruye el resto. |
| Línea continua | **Asociación** (atributo persistente). | `private Verificador siguiente;` vive mientras viva el handler. |
| Multiplicidad `0..1` → `1` | Puede no haber siguiente (último nodo); si existe, es exactamente uno. | Cuando `siguiente` es `null`, se termina la cadena. |

---

## Relaciones UML ↔ Código

| # | Relación (diagrama) | Símbolo UML | Código que la genera | Explicación |
|---|---------------------|-------------|----------------------|-------------|
| 1 | Handlers → Verificador | Realización | `implements Verificador` | Cada handler cumple el contrato. |
| 2 | Auto‑asociación `siguiente` | Rombo blanco + línea continua | `private Verificador siguiente;` | Forma la cadena. |
| 3 | Verificador ..> Paquete | Dependencia | `verificar(Paquete p)` | Usa el DTO sin guardarlo. |
| 4 | Main ..> Verificador | Dependencia | creación de handlers | Cliente construye y usa la cadena. |

---

## Detalle de cada clase

| Clase | Rol | Principal responsabilidad |
|-------|-----|---------------------------|
| `Verificador` | Interfaz | Contrato de la cadena. |
| `VerificadorCamposObligatorios` | Handler 1 | Comprueba que no falte ningún dato. |
| `VerificadorDimensiones` | Handler 2 | Valida dimensiones máximas. |
| `AduanaLocal` | Handler 3 | Simula reglas de aduana nacional. |
| `ControlDestino` | Handler 4 | Bloquea países prohibidos. |
| `Paquete` | DTO | Contiene datos del envío. |
| `FormularioEnvio` | UI Swing | Recoge datos, crea `Paquete`, arma la cadena, muestra resultado. |
| `Main` | Entrada | Lanza la interfaz. |

---

## Patrón Cadena de Responsabilidad — Flujo ilustrado

```text
     Paquete (datos del formulario)
           │
           ▼
+--------------------------------------+
| VerificadorCamposObligatorios        |
+--------------------------------------+
  │✔ pasa                 │✖ "Faltan datos"
  │                       └── Fin (mensaje de error)
  ▼
+--------------------------------------+
| VerificadorDimensiones               |
+--------------------------------------+
  │✔ pasa                 │✖ "Fuera de rango"
  │                       └── Fin (mensaje de error)
  ▼
+--------------------------------------+
| AduanaLocal                          |
+--------------------------------------+
  │✔ pasa                 │✖ "Contenido restringido"
  │                       └── Fin (mensaje de error)
  ▼
+--------------------------------------+
| ControlDestino                       |
+--------------------------------------+
  │✔ pasa                 │✖ "Destino prohibido"
  │                       └── Fin (mensaje de error)
  ▼
        Aprobado
```


---

## Créditos
Proyecto elaborado por **Arturo Aliaga**.
