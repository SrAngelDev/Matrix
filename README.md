# Simulación de Matrix

Bienvenidos al mundo real...

## Introducción
La historia comienza con un secuestro. Éramos programadores en la nave *Nebuchadnezzar*, tan buenos que las máquinas nos han secuestrado. Nos necesitan para desarrollar una simulación, ya que solo un humano es capaz de captar pequeñas sutilezas que las máquinas no pueden percibir.

Las máquinas están preocupadas por la evolución del virus *Smith*, la influencia de *Neo* en el sistema y la tendencia de los personajes a morir inesperadamente.

## Especificaciones de los Personajes
En *Matrix*, los personajes tienen los siguientes atributos:
- **ID**
- **Nombre** (Pepe, Juan, Ana, Sonia, Pedro, Chiquito, Elena)
- **Localización** (latitud, longitud y ciudad de nacimiento: Nueva York, Pekín, Roma, París, Londres, Caracuel)
- **Edad**
- **Fecha de creación en el sistema**

Además, algunos personajes tienen atributos especiales:
- **Agente Smith**: Capacidad de infectar (porcentaje)
- **Neo**: Indica si se cree que es el elegido
- **Personajes genéricos**: Probabilidad de morir (porcentaje)

Todos los personajes deben implementar los métodos:
- `generar()`: Generación automática de personajes
- `pedir()`: Entrada de datos
- `mostrar()`: Visualización de información

## Generación de Personajes
Inicialmente, se crean **200 personajes**, que se almacenarán en una estructura. Se usarán para poblar la simulación de *Matrix*, excepto *Neo* y *Smith*, que se generarán de manera independiente.

## Simulación de Matrix
La simulación de *Matrix* consiste en una **matriz de 5x5** (parametrizable), que contendrá:
- 1 *Neo*
- 1 *Smith*
- 23 personajes normales

### Reglas del Ecosistema
- **Cada segundo**: Se evalúa la probabilidad de morir de cada personaje:
  - Si es inferior al 30%, muere y es reemplazado.
  - En caso contrario, su probabilidad de morir disminuye un 10%.
- **Cada 2 segundos**: *Smith* infecta personajes adyacentes según su capacidad de infectar. No infecta a *Neo*.
- **Cada 5 segundos**:
  - *Neo* decide si es el elegido (50% de probabilidad):
    - Si lo es, elimina a todos los *Smith* cercanos.
    - Si no, se mueve a una posición aleatoria.
  - Si en la nueva posición hay un *Smith*, lo elimina; si hay un personaje, intercambian posiciones.
  - Los *Smith* eliminados se almacenan en un depósito de virus (ordenado por fecha ascendente).
  - Se imprime el estado de *Matrix* con los siguientes símbolos:
    - `o`: Casilla vacía
    - `s`: Agente *Smith*
    - `n`: *Neo*
    - `p`: Personaje
- **Cada 10 segundos**: Se añaden 5 personajes del almacén si hay espacio.
- **Cada 30 segundos**: Se permite la creación manual de un nuevo personaje genérico.

### Finalización de la Simulación
La simulación termina cuando:
- Pasan **5 minutos**.
- Todos los personajes han sido infectados por *Smith*.

Al final, se muestra:
- Estado final de *Matrix*.
- Posición de *Neo*.
- Lista de personajes generados (ordenada por ID).
- Lista de *Smith* eliminados (ordenada por fecha descendente).

## Requisitos Técnicos
- Implementación de una **clase auxiliar** para la lectura de valores y generación de números aleatorios.
- Control de errores para garantizar la **robustez** del programa.
- Diagrama de clases y gráfico explicativo del funcionamiento.

## Recompensa
Si todo funciona, las máquinas nos darán a elegir entre la **pastilla azul**... o un **buen aprobado**. 😉

---
“Programar sin una arquitectura o diseño en mente es como explorar una gruta sólo con una linterna: no sabes dónde estás, dónde has estado ni hacia dónde vas”  
— *Danny Thorpe*

