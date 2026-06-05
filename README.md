# Proyecto Laberinto con Dijkstra
---

## 👥 Integrantes

- Cristian Santiago Narvaez Bolaños
- Juan Camilo Sanchez Trujillo
- Manuel Santiago Arroyave Zambrano
- Cristian Alexis Lucio Narvaez
- Eric Santiago Ruano Macias

---

## Descripción

Este proyecto implementa un sistema de generación y resolución de laberintos en Java utilizando estructuras de datos desarrolladas manualmente y el algoritmo de Dijkstra para encontrar el camino mínimo entre la entrada y la salida.

El programa permite generar laberintos aleatorios mediante semillas, resolverlos automáticamente, almacenar resultados en caché y gestionar un ranking de partidas.

---

## Objetivos

- Aplicar estructuras de datos implementadas desde cero.
- Utilizar el algoritmo de Dijkstra para encontrar caminos mínimos.
- Generar laberintos reproducibles mediante semillas.
- Implementar una arquitectura modular siguiendo principios de Clean Code.
- Analizar el rendimiento de algoritmos de búsqueda.

---

## Funcionalidades

### Generación de Laberintos
- Generación aleatoria mediante el algoritmo Recursive Backtracker (DFS).
- Uso de semillas para reproducir exactamente el mismo laberinto.
- Diferentes tamaños de laberinto.

### Resolución de Laberintos
- Búsqueda del camino mínimo mediante el algoritmo de Dijkstra.
- Visualización de la ruta encontrada.
- Medición del tiempo de ejecución.

### Caché de Laberintos
- Almacenamiento de laberintos generados utilizando una Tabla Hash.
- Recuperación rápida de laberintos previamente generados.

### Ranking de Partidas
- Registro de mejores resultados.
- Organización mediante un Árbol Binario de Búsqueda.

---


## 🗂️ Estructura del proyecto

```
ProyectoLaberintoDijkstra/
└── src/
    ├── Main.java                          # Punto de entrada y lógica del menú principal
    ├── algoritmos/
    │   ├── Dijkstra.java                  # Algoritmo de camino mínimo
    │   └── GeneradorLaberinto.java        # Generación con Recursive Backtracker (DFS)
    ├── estructuras/
    │   ├── ColaPrioridad.java             # Cola de prioridad mínima (lista ordenada)
    │   ├── ListaEnlazada.java             # Lista enlazada simple genérica
    │   ├── ArbolBinario.java              # BST para el ranking de partidas
    │   └── TablaHash.java                 # Tabla hash con encadenamiento separado
    ├── modelo/
    │   ├── EntradaHash.java               # Entrada clave-valor para la tabla hash
    │   ├── NodoColaPrioridad.java         # Nodo de la cola de prioridad
    │   ├── NodoLista.java                 # Nodo de la lista enlazada
    │   └── ResultadoPartida.java          # Resultado de una partida para el ranking
    ├── interfaz/
    │   ├── ImpresorLaberinto.java         # Impresión del laberinto con colores ANSI
    │   └── Menu.java                      # Pantallas de bienvenida y menú
    └── utilidades/
        └── EntradaTeclado.java            # Lectura segura de datos por consola
```

---

## ⚙️ Estructuras de datos implementadas

| Estructura | Uso en el proyecto |
|---|---|
| `ListaEnlazada<T>` | Almacena el camino solución devuelto por Dijkstra |
| `ColaPrioridad<T>` | Cola de prioridad mínima usada internamente por Dijkstra |
| `TablaHash` | Caché de laberintos ya generados, indexados por semilla |
| `ArbolBinario` (BST) | Ranking de mejores partidas ordenado por cantidad de pasos |

---

## 🔬 Algoritmos implementados

### Generador de laberintos — Recursive Backtracker
Implementado en `GeneradorLaberinto.java`. Usa DFS con backtracking para tallar caminos desde una celda inicial, mezclando las direcciones con **Fisher-Yates** para garantizar aleatoriedad reproducible. La misma semilla siempre produce el mismo laberinto.

### Dijkstra
Implementado en `Dijkstra.java`. Encuentra el camino de menor distancia desde la entrada (`S`) hasta la salida (`E`) usando la `ColaPrioridad` propia. Cada celda tiene costo unitario. Al finalizar, reconstruye el camino completo recorriendo los punteros `padre` de cada nodo.

---

## 🖥️ Menú principal

```
╔══════════════════════════════════════╗
║    LABERINTO ALEATORIO — Dijkstra    ║
╚══════════════════════════════════════╝

── MENÚ ──
  1) Generar laberinto
  2) Resolver con Dijkstra (camino mínimo)
  3) Ver ranking top-5
  4) Ver caché de semillas
  5) Salir
```

**Opción 1** — Pide una semilla y un tamaño (11×11, 15×15 o 21×21). Si la semilla ya fue generada antes, recupera el laberinto desde la caché.

**Opción 2** — Ejecuta Dijkstra sobre el laberinto activo, muestra pasos, distancia, tiempo de ejecución y visualiza el camino marcado con `·` en amarillo.

**Opción 3** — Muestra las 5 partidas con menor cantidad de pasos (recorrido inOrden del BST).

**Opción 4** — Lista todas las semillas almacenadas en la caché.

---

## 🎨 Visualización en consola

| Símbolo | Significado |
|---|---|
| `S` (verde) | Punto de inicio |
| `E` (rojo) | Salida / meta |
| `·` (amarillo) | Camino encontrado por Dijkstra |
| `█` | Pared |
| ` ` | Pasillo libre |

---

## 🚀 Compilación y ejecución

```bash
# Desde la carpeta src/
javac -d . Main.java algoritmos/*.java estructuras/*.java modelo/*.java interfaz/*.java utilidades/*.java

# Ejecutar
java Main
```

> Requiere **Java 8** o superior.
> También puede ejecutarse directamente desde un IDE como Visual Studio Code, IntelliJ IDEA o Eclipse.

---

## Conceptos Aplicados

- Algoritmos de grafos.
- Caminos mínimos.
- Generación procedural.
- Estructuras de datos dinámicas.
- Optimización de búsquedas.
- Programación modular.
- Análisis de complejidad algorítmica.

---

## Uso de Inteligencia Artificial

Durante el desarrollo de este proyecto se utilizó **Qwen** como herramienta de apoyo para:

- Consultas sobre conceptos y algoritmos.
- Resolución de dudas relacionadas con Java.
- Revisión de la estructura del código.
- Apoyo en la documentación del proyecto.

La implementación, validación, pruebas y adaptación final del código fueron realizadas por los integrantes del equipo.


---
## 🎥 Manual de Usuario

Para conocer el funcionamiento del sistema, la generación de laberintos, la resolución mediante el algoritmo de Dijkstra y las diferentes funcionalidades disponibles, consulta el video del manual de usuario:

🔗 **Video en YouTube:**  
https://www.youtube.com/watch?v=tQfnoxZGOYI
---
## Licencia

Este proyecto se distribuye bajo la licencia MIT con fines académicos y educativos.

---

## Información Académica

**Asignatura:** Estructuras de Datos Lineales 
**Programa:** Ingeniería Informática
**Docente:** MANUEL ARTURO MELO LEGARDA   
**Universidad:** Universidad Colegio Mayor del Cauca  
**Año:** 2026