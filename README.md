# 🚀 Data Structures and Algorithms in Java
### *Laboratorio de Estructura de Datos — Ingeniería de Software*

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Paradigm](https://img.shields.io/badge/Paradigma-POO%20Puro-blue?style=for-the-badge)
![Status](https://img.shields.io/badge/Estado-Completado-success?style=for-the-badge)
![License](https://img.shields.io/badge/Licencia-Académica-lightgrey?style=for-the-badge)

---

## 📌 Descripción General

Este repositorio contiene una colección completa, robusta y modular de **Estructuras de Datos y Algoritmos implementados desde cero en Java**, desarrollada como parte del curso de **Laboratorio de Estructura de Datos** (4to Semestre de Ingeniería de Software).

A diferencia del uso convencional de las colecciones estándar (`java.util`), este proyecto implementa la lógica interna de almacenamiento, gestión de memoria dinámica mediante nodos enlazados, arreglos continuos redimensionables, matrices multidimensionales, árboles avanzados y grafos ponderados/dirigidos. Además, integra **aplicaciones prácticas y casos de estudio reales** que demuestran el impacto y la utilidad de cada estructura en problemas de ingeniería de software.

---

## 📑 Tabla de Contenidos

- [Características Principales](#-características-principales)
- [Estructura del Proyecto](#-estructura-del-proyecto)
- [Módulos y Estructuras Implementadas](#-módulos-y-estructuras-implementadas)
  - [1. Estructuras Lineales](#1-estructuras-lineales-estructuraslineales)
  - [2. Estructuras No Lineales](#2-estructuras-no-lineales-estructurasnolineales)
  - [3. Algoritmos y Utilidades](#3-algoritmos-y-utilidades-utils)
- [Casos de Estudio y Aplicaciones Reales](#-casos-de-estudio-y-aplicaciones-reales-registros--aviacionbalasco--verificador)
- [Suite de Pruebas](#-suite-de-pruebas-pruebas)
- [Instalación y Ejecución](#-instalación-y-ejecución)
- [Autor](#-autor)

---

## ✨ Características Principales

- **Diseño Orientado a Objetos (POO):** Polimorfismo, interfaces (`ListaInfo`, `VectorListaInfo`, `LoteInfo`), herencia y desacoplamiento.
- **Implementaciones Estáticas y Dinámicas:** Colecciones basadas en arreglos finitos y estructuras dinámicas basadas en nodos enlazados simples y dobles.
- **Estructuras Multidimensionales y Tensores:** Matrices 2D genéricas, matrices numéricas con álgebra lineal avanzada y matrices 3D.
- **Árboles y Almacenamiento en Disco:** Árboles Binarios (AB), Árboles Binarios de Búsqueda (ABB), Montículos (Heaps), Árboles de Expresiones Aritméticas (AEA) y un Árbol Binario de Búsqueda sobre Disco (`ArbolBBD`) con acceso aleatorio a archivos (`RandomAccessFile`).
- **Grafos y Optimización de Rutas:** Representación por matriz y listas de adyacencia, Algoritmo de Dijkstra para caminos mínimos, Ordenamiento Topológico, Recorridos BFS y DFS, y análisis de propiedades topológicas (conexidad, ciclos, pseudógrafos).
- **Procesamiento de Señales e Imágenes:** Modificación de archivos de audio WAV en el dominio del tiempo y procesamiento digital de imágenes bitmap mediante matrices bidimensionales.
- **Análisis Sintáctico y Compilador:** Parser y verificador de errores sintácticos (balance de delimitadores, comentarios, alcance de variables) usando pilas.

---

## 📂 Estructura del Proyecto

```text
src/
├── AviacionBalasco/             # Sistema de gestión para aerolíneas y vuelos
│   ├── AviacionBalasco.java
│   └── Entidades/               # Modelos: Aeropuerto, Avion, Piloto, Vuelo
│
├── entradasalida/               # Abstracción para I/O por consola y archivos de texto
│   ├── EntradaPorDefecto.java
│   ├── SalidaPorDefecto.java
│   └── archivos/ArchivoTexto.java
│
├── estructuraslineales/         # Estructuras de datos lineales (listas, pilas, colas)
│   ├── ListaInfo.java           # Contrato/interfaz principal para listas
│   ├── ListaInfoEstatica.java
│   ├── ListaInfoEstaticaNumerica.java
│   ├── ListaInfoEstaticaOrdenada.java
│   ├── ListaInfoDinamica.java
│   ├── ListaInfoDinamicaClave.java
│   ├── ListaInfoDinamicaOrdenada.java
│   ├── ListaEnlazadaIndexada.java
│   ├── PilaInfoEstatica.java / PilaInfoDinamica.java
│   ├── ColaInfoEstatica.java / ColaInfoDinamica.java
│   ├── ColaInfoEstaticaPrioritaria.java
│   └── auxiliares/              # Nodos simples, dobles y clave-valor
│
├── estructurasnolineales/       # Estructuras jerárquicas y no lineales
│   ├── MatrizInfo2.java         # Matriz bidimensional genérica
│   ├── MatrizInfo2Numerica.java # Matriz 2D con álgebra lineal
│   ├── MatrizInfo3.java         # Matriz tridimensional (tensor)
│   ├── ArbolBinario.java
│   ├── ArbolBinarioBusqueda.java
│   ├── ArbolBinarioMonticulo.java (Heap)
│   ├── ArbolExpresionesA.java   # Árbol de expresiones aritméticas
│   ├── ArbolBBD.java            # Árbol de búsqueda en disco (Base de datos)
│   ├── GrafoEstatico.java       # Grafo con matriz de adyacencia (Dijkstra, DFS, BFS)
│   ├── GrafoDinamico.java       # Grafo con listas dinámicas (Topología, ciclos)
│   └── auxiliares/              # Vértices, etiquetas y nodos de árbol
│
├── utils/                       # Utilidades transversales
│   ├── comunes/                 # Enumeradores de configuración
│   ├── matematicas/             # Notación infija/posfija/prefija, polinomios y monomios
│   ├── ordenacion/              # QuickSort iterativo
│   └── ComparadorObjetos.java / Menu.java
│
├── verificador/                 # Analizador sintáctico y compilador con pilas
│   ├── Compilador.java
│   ├── VerificadorDeErrores.java
│   └── SignoPosicion.java
│
├── registros/                   # Casos de estudio y proyectos aplicados
│   ├── arrocera/                # Control de cosechas arroceras
│   ├── audioClases/             # Procesamiento y ecualización de audio WAV
│   ├── binarios/                # Verificación y conversión de números binarios
│   ├── calculadora/             # Calculadora de expresiones aritméticas
│   ├── cosechas/                # Análisis multidimensional de producción agrícola
│   ├── cripto/                  # Cifrado y descifrado de mensajes
│   ├── diccionario/             # Diccionario y glosario de conceptos
│   ├── hexadecimal/             # Conversión a base hexadecimal
│   ├── imagen/                  # Procesamiento digital de imágenes (filtros y giros)
│   ├── indices/                 # Sistema de indexación por términos y subtérminos
│   ├── Markov/                  # Modelos Ocultos de Markov (MOM)
│   └── ventas/                  # Sistema de ventas, sucursales y facturación
│
├── tablasDB/                    # Datasets de prueba en CSV y TXT
└── pruebas/                     # Suites de pruebas ejecutables (38+ clases de prueba)
```

---

## 🧱 Módulos y Estructuras Implementadas

### 1. Estructuras Lineales (`estructuraslineales`)

| Clase / Interfaz | Tipo | Descripción |
| :--- | :---: | :--- |
| `ListaInfo` | *Interface* | Contrato con operaciones de inserción, eliminación, búsqueda, inversión, clonación, sublistas y validación. |
| `ListaInfoEstatica` | Estática | Arreglo contiguo con capacidad fija, operaciones de inserción al inicio/final, ordenamiento y eliminación. |
| `ListaInfoEstaticaNumerica` | Estática | Especialización para números: cálculo de promedios, varianza, desviación estándar, producto punto, escalares y normalización. |
| `ListaInfoEstaticaOrdenada` | Estática | Mantiene los elementos siempre ordenados y optimiza búsquedas mediante **Búsqueda Binaria** ($O(\log n)$). |
| `ListaInfoDinamica` | Dinámica | Lista simplemente enlazada gestionada mediante punteros (`Nodo`), sin límite de capacidad. |
| `ListaInfoDinamicaClave` | Dinámica | Implementación de tabla asociativa clave-valor sobre nodos enlazados (`NodoClaveValor`). |
| `ListaInfoDinamicaOrdenada`| Dinámica | Lista enlazada que inserta elementos preservando el orden de manera eficiente. |
| `ListaEnlazadaIndexada` | Dinámica | Lista enlazada que expone indexación posicional y navegación por índices. |
| `PilaInfoEstatica` / `PilaInfoDinamica` | LIFO | Pilas basadas en arreglo fijo o nodos enlazados (`push`, `pop`, `verTope`, `vacio`, `lleno`). |
| `ColaInfoEstatica` / `ColaInfoDinamica` | FIFO | Colas lineales basadas en arreglo circular o nodos enlazados (`encolar`, `desencolar`). |
| `ColaInfoEstaticaPrioritaria` | Prioridad | Cola de prioridad ordenada por nivel de urgencia (`ObjetoPriorizable`). |

---

### 2. Estructuras No Lineales (`estructurasnolineales`)

#### 🔲 Matrices Multidimensionales
- **`MatrizInfo2`**: Matriz genérica bidimensional. Incluye operaciones de transposición, rotación, adición dinámica de filas y columnas, y conversión a vectores.
- **`MatrizInfo2Numerica`**: Matriz bidimensional especializada en álgebra matricial:
  - Suma y multiplicación de matrices.
  - Multiplicación y suma de escalares (individuales o por vector).
  - Potencia de matrices y aplicación de funciones logarítmicas.
  - Verificación de matrices diagonales, diagonales superiores e inferiores.
  - Doblado de filas y columnas.
- **`MatrizInfo3`**: Estructura tridimensional (tensor) de dimensiones $(R \times C \times P)$, ideal para análisis de datos volumétricos o temporales (ej. Campesino $\times$ Producto $\times$ Año).

#### 🌲 Árboles
- **`ArbolBinario`**:
  - Recorridos en profundidad: **Preorden**, **Inorden**, **Postorden** (versiones recursivas e iterativas con pila).
  - Recorridos por niveles / en anchura (**BFS** con cola).
  - Cálculo de altura, nivel de un nodo, conteo de nodos por nivel y relaciones de parentesco (hermanos, tipo de nodo).
- **`ArbolBinarioBusqueda` (ABB)**: Inserción, búsqueda y eliminación preservando el invariante de búsqueda binaria.
- **`ArbolBinarioMonticulo` (Binary Heap)**: Implementación de Montículos Min/Max para colas de prioridad eficientes.
- **`ArbolExpresionesA`**: Construcción y evaluación de árboles sintácticos a partir de expresiones infijas, prefijas y postfijas.
- **`ArbolBBD` (Árbol de Base de Datos en Disco)**:
  - Árbol binario de búsqueda persistente que indexa registros almacenados en archivos físicos usando `RandomAccessFile`.
  - Permite búsquedas binarias directas por ID en disco sin cargar el archivo completo a memoria RAM.

#### 🕸️ Grafos
- **`GrafoEstatico` (Matriz de Adyacencia)**:
  - **Algoritmo de Dijkstra**: Cálculo del camino más corto y métrica óptima entre nodos origen y destino.
  - **Ordenamiento Topológico**: Resolución de dependencias en grafos dirigidos acíclicos (DAGs).
  - **Recorridos**: Recorrido en Profundidad (**DFS**) con pila y en Anchura (**BFS**) con cola.
- **`GrafoDinamico` (Listas de Adyacencia Dinámicas)**:
  - Verificación de propiedades estructurales: ¿Es conexo? ¿Es dirigido? ¿Es árbol? ¿Es pseudografo o multigrafo?
  - Detección de ciclos, caminos cerrados y caminos simples.
  - Cálculo del grado de vértices e inserción/eliminación dinámica de aristas y vértices.

---

### 3. Algoritmos y Utilidades (`utils`)

- **`ExpresionesAritmeticas`**:
  - Conversión de expresiones de notación **Infija a Postfija** (Algoritmo Shunting-yard simplificado) e **Infija a Prefija**.
  - Evaluación numérica de expresiones postfijas y prefijas con soporte para variables parametrizadas.
- **`QuickSortIterativo`**: Algoritmo de ordenación rápida implementado de forma iterativa empleando pilas para controlar los subrangos de partición.
- **`Polinomio` y `Monomio`**: Representación y cálculo simbólico de expresiones algebraicas multivariables ($x, y$).

---

## 💡 Casos de Estudio y Aplicaciones Reales (`registros` / `AviacionBalasco` / `verificador`)

El repositorio no se limita a estructuras abstractas; cada estructura se pone a prueba en sistemas funcionales completos:

| Proyecto / Módulo | Estructuras Empleadas | Funcionalidad Implementada |
| :--- | :--- | :--- |
| **✈️ AviacionBalasco** | Listas y Menús interactivos | Sistema integral de aviación comercial: gestión de aeronaves, asignación de pilotos calificados, aeropuertos y programación de vuelos por origen/destino. |
| **🎧 Procesamiento de Audio WAV (`audioClases`)** | `ListaInfoEstaticaNumerica`, `WavFile` | Edición digital de audio en crudo: aumento/disminución de volumen, aceleración y desaceleración temporal, inversión temporal ($X$), inversión de amplitud ($Y$), filtro de pre-énfasis, cálculo de energía vocal y supresión de silencios. |
| **🖼️ Procesamiento Digital de Imágenes (`imagen`)** | `MatrizInfo2Numerica`, Pixel buffers | Edición y filtrado de imágenes: conversión a escala de grises, ajuste de brillo, espejado horizontal/vertical, rotación a 90°, 180° y 270°, temperatura de color, marcos decorativos y redimensionamiento. |
| **🔍 Compilador y Verificador Sintáctico (`verificador`)** | `PilaInfoDinamica`, `ListaInfoDinamica` | Analizador léxico/sintáctico estático para código fuente: validación de balanceo de paréntesis `()`, corchetes `[]` y llaves `{}`, detección de comentarios sin cerrar y verificación de declaración de variables con reporte de línea y columna. |
| **🎲 Modelos Ocultos de Markov (`Markov`)** | `ColaInfoDinamica`, Matrices de probabilidad | Modelado probabilístico estocástico (HMM) para calcular la probabilidad de secuencias de estados y observaciones. |
| **🌾 Rendimiento Agrícola (`cosechas` & `arrocera`)** | `MatrizInfo3` (Tensores 3D) | Modelado y consulta multidimensional de cosechas evaluando tres dimensiones: Campesinos $\times$ Tipos de Producto $\times$ Años. |
| **🔐 Criptografía (`cripto`)** | `ListaInfoEstatica` | Algoritmo de transposición y cifrado posicional de mensajes de texto con funciones reversibles de desencriptado. |
| **📚 Gestor de Índices y Diccionario (`indices` / `diccionario`)** | Listas ordenadas dinámicas | Generación automática de índices alfabéticos por términos principales y subtérminos jerárquicos con números de página asociados. |
| **🛒 Sistema de Ventas (`ventas`)** | Listas y Objetos | Gestión comercial completa de sucursales: registro de clientes, vendedores, catálogo de productos con stock y cálculo de totales con emisión de comprobantes. |
| **🔢 Conversor Numérico (`binarios` & `hexadecimal`)** | `PilaInfoEstatica` | Conversión de bases numéricas y verificación de cadenas binarias/hexadecimales usando la naturaleza LIFO de las pilas. |

---

## 🧪 Suite de Pruebas (`pruebas`)

El paquete `pruebas` contiene más de **35 clases con método `main`** listas para verificar el correcto funcionamiento unitario y de integración:

```bash
# Ejemplos de pruebas disponibles:
PruebaAB.java                   # Árbol Binario tradicional
PruebaABB.java                  # Árbol Binario de Búsqueda
PruebaABBD.java                 # Árbol de Base de Datos en Disco
PruebaABM.java                  # Árbol Binario Montículo (Heap)
PruebaAEA.java                  # Árbol de Expresiones Aritméticas
PruebaGrafoM.java / PruebaGrafoRP.java / PruebaGrafoDinamico.java
PruebaDijkstra.java             # Algoritmo de Dijkstra sobre grafos
PruebaOT.java                   # Ordenamiento Topológico
PruebaPila.java / PruebaCola.java / PruebaListaInfoEstatica.java
PruebaAudio.java                # Manipulación de archivos de audio .wav
PruebaImagen.java               # Filtros y transformaciones de imágenes
PruebaMOM.java                  # Modelos de Markov
PruebaCalculadora.java          # Parser de expresiones
PruebaCosechas.java             # Tensor 3D de cosechas
```

---

## 🛠️ Instalación y Ejecución

### Prerrequisitos
- **Java Development Kit (JDK)** versión 8 o superior.
- Git instalado en su sistema.

### 1. Clonar el repositorio
```bash
git clone https://github.com/YairRT45/data-structures-and-algorithms-java.git
cd data-structures-and-algorithms-java
```

### 2. Compilar el proyecto

#### En Windows (PowerShell):
```powershell
# Crear directorio para los binarios compilados
New-Item -ItemType Directory -Force -Path "bin"

# Compilar todos los archivos Java con codificación UTF-8
javac -encoding UTF-8 -d bin (Get-ChildItem -Filter *.java -Recurse | Where-Object { $_.FullName -notmatch '\\.git\\' } | Select-Object -ExpandProperty FullName)
```

#### En Linux / macOS / Git Bash:
```bash
mkdir -p bin
find . -name "*.java" > fuentes.txt
javac -encoding UTF-8 -d bin @fuentes.txt
```

### 3. Ejecutar una prueba o módulo

Para ejecutar cualquier clase con `main`, basta con indicar el classpath a la carpeta `bin`:

```bash
# Probar el algoritmo de Dijkstra en grafos
java -cp bin pruebas.PruebaDijkstra

# Probar el Árbol Binario de Búsqueda
java -cp bin pruebas.PruebaABB

# Probar el verificador de sintaxis y compilador
java -cp bin verificador.Compilador

# Probar el procesamiento digital de audio
java -cp bin pruebas.PruebaAudio

# Ejecutar el sistema de Aviación Balasco
java -cp bin AviacionBalasco
```

---

## 👨‍💻 Autor

- **Yair RT** ([@YairRT45](https://github.com/YairRT45))
- Estudiante de **Ingeniería de Software**
- Materia: **Laboratorio de Estructura de Datos (4to Semestre)**

---
> 💡 *Proyecto desarrollado con fines académicos y de formación profesional en algoritmia y estructuras de datos fundamentales.*
