package Conjunto

import scala.annotation.tailrec

/**
 * Clase que implementa un conjunto numérico.
 * @param elementos Lista de elementos del conjunto
 * @tparam A Tipo genérico.
 */
class Conjunto[A] (val elementos: List[A]) {
  /**
   * Indica si un valor pertenece o no al conjunto.
   * @param value Valor.
   * @return Flag booleano que indica si pertenece (true) o no (false).
   */
  def apply(value: A): Boolean = elementos.contains(value)

  /**
   * Ofrece una visión del contenido del conjunto. Para visualizar el conjunto se
   * asume que se itera sobre un rango de valores dado por una constante llamada LIMITE
   * (desde -LIMITE hasta +LIMITE) y se muestran aquellos que pertenecen al conjunto.
   * @return String conformado por los valores que pertenecen al conjunto.
   */
  def toString(LIMITE: A)(implicit numeric: Numeric[A]): String =
    import numeric._

    @tailrec
    def go(cadena: String, nuevoLimite: A): String =
      var nuevaCadena = cadena
      if (apply(nuevoLimite)) {
        if (nuevaCadena.eq("")) nuevaCadena += "Pertenecen al conjunto los elementos: " + nuevoLimite
        else nuevaCadena += ", " + nuevoLimite.toString
      }

      if (nuevoLimite > LIMITE) nuevaCadena
      else go(nuevaCadena, nuevoLimite + one)

    val cadena = ""
    go(cadena, -LIMITE)


  /**
   * Dados dos objetos de la clase Conjunto produce su unión.
   * @param B Segundo conjunto.
   * @return Conjunto resultado de la operación union entre A y B
   */
  def union(B: Conjunto[A]): Conjunto[A] = new Conjunto(elementos.concat(B.elementos).distinct)

  /**
   * Intersección de dos objetos.
   * @param B Segundo conjunto.
   * @return Conjunto resultado de la operación interseccion entre A y B
   */
  def interseccion(B: Conjunto[A]): Conjunto[A] = new Conjunto(elementos.intersect(B.elementos).distinct)

  /**
   * Diferencia de dos objetos (el conjunto resultante est´a formado por aquellos
   * valores que pertenecen al primer conjunto, pero no al segundo).
   * @param B Segundo conjunto.
   * @return Conjunto resultado de la operación diferencia entre A y B
   */
  def diferencia(B: Conjunto[A]): Conjunto[A] = new Conjunto(elementos.diff(B.elementos).distinct)

  /**
   * Dado un conjunto y una función tipo Int => Boolean, devuelve como resultado
   * un conjunto con los elementos que cumplen la condición indicada.
   * @param value Valor al que se compara
   * @param filtro Filtro aplicado al conjunto.
   * @return Conjunto filtrado.
   */
  def filtrar(filtro: (A, A) => Boolean)(value: A): Conjunto[A] =
    new Conjunto(elementos.filter(elemento => filtro(elemento, value)))

  /**
   * Comprueba si un determinado predicado se cumple para todos los elementos
   * del conjunto. Esta función debe implementarse de forma recursiva, definiendo una
   * función auxiliar, ya que hay que iterar sobre el rango de valores dado por LIMITE.
   * @param filtro Filtro aplicado al conjunto.
   * @param valor Valor al cual se compara
   * @return Flag booleano que indica si se cumple la condición para todo elemento (true) o no (false).
   */
  def paraTodo(filtro: (A, A) => Boolean)(valor: A, LIMITE: Int): Boolean =
    def go(actual: Int): Boolean =
      if (actual < LIMITE) {
        if (apply(actual.asInstanceOf[A])) {
          if (filtro(actual.asInstanceOf[A], valor)) go(actual + 1)
          else false
        }
        else go(actual + 1)
      }
      else true

    go (-LIMITE)

  /**
   * Determina si un conjunto contiene al menos un elemento para el que se cumple
   * un cierto predicado. Debe basarse en el método anterior.
   * @param valor Valor para el que se busca si existe o no.
   * @param filtro Filtro aplicado.
   * @return Flag booleano que indica si existe un valor tal que se cumpla el predicado (true) o no (false).
   */
  def existe(filtro: (A, A) => Boolean)(valor: A, LIMITE: Int): Boolean =
    def go(actual: Int): Boolean =
      if (actual < LIMITE) {
        if (apply(actual.asInstanceOf[A])) {
          if (filtro(actual.asInstanceOf[A], valor)) true
          else go(actual + 1)
        }
        else go(actual + 1)
      }
      else false

    go(-LIMITE)

  /**
   * Transforma un conjunto en otro aplicando una cierta función.
   * @param factor Factor a aplicar.
   * @return Conjunto transformado.
   */
  def map(operacion: (A, A) => A)(factor: A): Conjunto[A] =
    val nuevosElementos = elementos.map(e => operacion(e, factor))

    new Conjunto(nuevosElementos)
}

/**
 * Creación de un conjunto dado por un unico elemento.
 *
 * @param value Valor con el que se crea el conjunto.
 * @return
 */
def conjuntoUnElemento[A](value: A): Conjunto[A] = new Conjunto(List(value))

object filtros {
  def esMayorQue[A](x: A, y: A)(implicit ord: Ordering[A]) = ord.gt(x, y)
}

object transformaciones {
  def multiplicaPor[A](x: A, multiplicador: A)(implicit numeric: Numeric[A]): A = numeric.times(x, multiplicador)
}

object prueba extends App{
  val conjunto1 = new Conjunto(List(-5, 1, 2, 3, 5, 6))
  val conjunto2 = new Conjunto(List(3, 4, 5, 6))
  val unico = conjuntoUnElemento(3)
  println("Conjunto1 => " + conjunto1.toString(10))

  println("Conjunto2 => " + conjunto2.toString(10))

  println("Conjunto con único elemento => " + unico.toString(10))

  val union = conjunto1.union(conjunto2)

  val interseccion = conjunto1.interseccion(conjunto2)

  val diferencia = conjunto1.diferencia(conjunto2)

  val filtrado = conjunto1.filtrar(filtros.esMayorQue)(3)

  val paratodo = conjunto1.paraTodo(filtros.esMayorQue)(-10, 10)

  val existencia = conjunto1.existe(filtros.esMayorQue)(8, 10)

  val transformado = conjunto1.map(transformaciones.multiplicaPor)(3)

  println("Unión (c1, c2) => " + union.toString(10))

  println("Intersección (c1, c2) => " + interseccion.toString(10))

  println("Diferencia (c1, c2) => " + diferencia.toString(10))

  println("Filtrado (c1) => " + filtrado.toString(10))

  println("Para todo (c1 > -10) => " + paratodo.toString)

  println("Existe (c1, > 8) => " + existencia.toString)

  println("Transformado (c1 * 3) => " + transformado.toString(15))
}
