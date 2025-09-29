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