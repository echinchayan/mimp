# Automatizacion con Selenium

El proyecto consiste en un ejemplo de automatización de pruebas web usando framework de automatización de pruebas basado en Selenium WebDriver que sirve como punto de partida en cualquier proyecto de automatización de pruebas web UI.

Este framework provee todas las funcionalidades necesarias para abordar la automatización de pruebas web con Selenium de una forma estándar, reduciendo tiempos de configuración y construcción, minimizando la curva de aprendizaje y el nivel técnico requerido para su uso.

A su vez, encapsula lógica en común y brinda un marco de trabajo fácilmente configurable y extendible según la necesidad de cada proyecto.

## Comenzando 🚀

Estas instrucciones te permitirán obtener una copia local del proyecto en funcionamiento para propósitos de desarrollo y pruebas.

### Pre-requisitos 📋

Requisitos necesarios para el correcto funcionamiento del template y cómo instalarlos.

* Java
* IDE de preferencia como por ejemplo, [Eclipse IDE](https://www.eclipse.org/)
* JUnit5
* apache-maven-3.6.3


### Instalación 🔧

A continuación se describen los pasos para descargar e instalar el template en tu IDE de preferencia.

1. Descargar una copia o clonar el código del repositorio desde GitHub.
2. Importar el proyecto en el IDE, recordar hacerlo como proyecto de tipo “Maven project”.
3. Al finalizar la importación, el proyecta está listo para usarse.

## Automatización de Ministerio de la Mujer ⚙️

A continuación se presenta el feature realizado en cucumber para las pruebas automatizadas

### Primeros pasos


```cucumber
Feature: HU013 Registro de Evaluacion NNA

  Scenario Outline: Validar cabeceras de grilla al ingresar al módulo "Evaluación NNA"
    Given cargamos la pagina SISNARA
    When ingresamos al modulo EVALUACION, submodulo Evaluacion NNA
    Then debe validar <elementos> segun valor esperado <valor>
    Examples:
      | elementos | valor                                                                                                               |
      | cabeceras | "Nro,Fecha de Ingreso,Nro Expediente,Nombres y Apellidos,Estado,Condición,Fecha,Responsable actual,Tiempo,Opciones" |

  Scenario: Validar opciones bandeja y derivar en grilla al ingresar al módulo "Evaluación NNA"
    Given cargamos la pagina SISNARA
    When ingresamos al modulo EVALUACION, submodulo Evaluacion NNA
    Then debe validar que cada fila tiene Bandeja de documentos y Derivar

  Scenario Outline: Validar liste documentos asociados a Evaluación NNA
    Given cargamos la pagina SISNARA
    When ingresamos al modulo EVALUACION, submodulo Evaluacion NNA
    And busco el expediente "<expediente>" y abro su Bandeja de documentos
    Then el nombre en la Bandeja debe coincidir con el de la lista
    And debe validar que liste documentos asociados
    And validar que exista opcion Ficha NNA
    And cada elemento de la bandeja debe tener las opciones Editar, Descargar, Ver y Eliminar

    Examples:
      | expediente              |
      | 002581-2025-MIMP/DA-NNA|

  Scenario Outline: Validar Derivar Expediente
    Given cargamos la pagina SISNARA
    When ingresamos al modulo EVALUACION, submodulo Evaluacion NNA
    And busco el expediente "<expediente>" y selecciono Derivar Expediente
    And selecciono especialista "<especialista>" a derivar
    And selecciono derivar
    Then validar que especialista seleccionado es igual al que lista del expediente "<expediente>"

    Examples:
      | expediente              | especialista                                     |
      | 002581-2025-MIMP/DA-NNA | Flora Tapia Tintaya  -  ESPECIALISTA_PSICOSOCIAL |
```

**Instrucciones para ejecutar**

* Clonar proyecto del github
* Importar las librerias maven
* Ejecutar por consola : mvn clean verify
* Luego que concluya generará reporte serenity
  SERENITY REPORTS
  Buscar luego de ejecutar la automatización la ruta generada, es parecida a esta ruta:
  [INFO]   - Full Report: file:///C:/Users/Erick/Documents/git/mimp/target/site/serenity/index.html

## Autores ✒️

* Erick Chinchayan

## Contacto 📢

erickc1902@gmail.com