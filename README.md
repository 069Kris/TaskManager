# Gestor de Tareas con Historial

Aplicación de consola en Java para gestionar tareas con soporte de deshacer/rehacer y puntos de restauración. Desarrollada aplicando los patrones de diseño **Command** y **Memento**.

---

## Patrones de diseño utilizados

### Command
Cada acción del usuario (agregar, eliminar, completar, editar) se encapsula como un objeto que implementa la interfaz `Comando`. Esto permite:
- Ejecutar acciones de forma uniforme desde el historial
- Deshacer (`undo`) la última acción realizada
- Rehacer (`redo`) una acción deshecha

```
Comando (interface)
├── AgregarTareaComando
├── EliminarTareaComando
├── CompletarTareaComando
└── EditarTareaComando
```

### Memento
Permite guardar una fotografía completa del estado de las tareas en cualquier momento, para restaurarlo después de golpe sin necesidad de deshacer acción por acción.

```
CuidadorEstado  →  guarda/restaura  →  MementoEstado (snapshot)
```

---

## Estructura del proyecto

```
src/taskmanager/
├── Main.java
├── model/
│   └── Tarea.java
├── manager/
│   └── GestorTareas.java
├── command/
│   ├── Comando.java
│   ├── AgregarTareaComando.java
│   ├── EliminarTareaComando.java
│   ├── CompletarTareaComando.java
│   └── EditarTareaComando.java
├── history/
│   └── HistorialComandos.java
└── memento/
    ├── MementoEstado.java
    └── CuidadorEstado.java
```

---

## Requisitos

- Java JDK 11 o superior
- No requiere dependencias externas

---

## Ejecución

### Opción 1 — VS Code
Abre la carpeta del proyecto en VS Code con el [Extension Pack for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack) instalado y presiona **Run** sobre el método `main` en `Main.java`.

El proyecto incluye `.vscode/settings.json` con la configuración de source path lista.

### Opción 2 — CMD (Windows)

Desde la raíz del proyecto:

```cmd
mkdir out
javac -d out src\taskmanager\model\Tarea.java src\taskmanager\manager\GestorTareas.java src\taskmanager\command\Comando.java src\taskmanager\command\AgregarTareaComando.java src\taskmanager\command\EliminarTareaComando.java src\taskmanager\command\CompletarTareaComando.java src\taskmanager\command\EditarTareaComando.java src\taskmanager\history\HistorialComandos.java src\taskmanager\memento\MementoEstado.java src\taskmanager\memento\CuidadorEstado.java src\taskmanager\Main.java
java -cp out taskmanager.Main
```

### Opción 3 — Terminal Linux/Mac

```bash
mkdir out
javac -d out $(find src -name "*.java")
java -cp out taskmanager.Main
```

---

## Funcionalidades

| Opción | Acción |
|--------|--------|
| 1 | Ver todas las tareas |
| 2 | Agregar tarea |
| 3 | Eliminar tarea por ID |
| 4 | Marcar tarea como completada |
| 5 | Editar título de una tarea |
| 6 | Deshacer última acción (undo) |
| 7 | Rehacer acción deshecha (redo) |
| 8 | Ver historial de acciones |
| 9 | Guardar punto de restauración |
| 10 | Restaurar un punto guardado |
| 0 | Salir |

---

## Flujo de ejemplo

```
Agregar "Estudiar Java"     →  tarea #1 creada
Agregar "Leer documentación" →  tarea #2 creada
Completar tarea #1          →  [✓] #1
Guardar punto "checkpoint"  →  snapshot guardado
Eliminar tarea #2           →  tarea #2 eliminada
Deshacer                    →  tarea #2 restaurada
Restaurar "checkpoint"      →  vuelve al estado con ambas tareas, #1 completada
```
