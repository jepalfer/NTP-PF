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
   * Creación de un conjunto dado por un unico elemento.
   * @param value Valor con el que se crea el conjunto.
   * @return
   */
  def conjuntoUnElemento(value: A): Conjunto[A] = new Conjunto(List(value))

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
   * @param value Valor al cual se compara
   * @return Flag booleano que indica si se cumple la condición para todo elemento (true) o no (false).
   */
  def paraTodo(filtro: (A, A) => Boolean)(value: A, LIMITE: Int): Boolean = ???

  /**
   * Determina si un conjunto contiene al menos un elemento para el que se cumple
   * un cierto predicado. Debe basarse en el método anterior.
   * @param valor Valor para el que se busca si existe o no.
   * @param filtro Filtro aplicado.
   * @return Flag booleano que indica si existe un valor tal que se cumpla el predicado (true) o no (false).
   */
  def existe(valor: A)(filtro: A => Boolean): Boolean = ???

  /**
   * Transforma un conjunto en otro aplicando una cierta función.
   * @param mult Factor a aplicar.
   * @return Conjunto transformado.
   */
  def map(operacion: (A, A) => A)(mult: A): Conjunto[A] =
    val nuevosElementos = elementos.map(e => operacion(e, mult))

    new Conjunto(nuevosElementos)
}

object filtros {
  def esMayorQue[A](x: A, y: A)(implicit ord: Ordering[A]) = ord.gt(x, y)
}

object transformaciones {
  def multiplica[A](x: A, multiplicador: A)(implicit numeric: Numeric[A]): A = numeric.times(x, multiplicador)
}

object prueba extends App{
  val conjunto1 = new Conjunto(List(-5, 1, 2, 3, 5, 6))
  val conjunto2 = new Conjunto(List(3, 4, 5, 6))

  println("Conjunto1")
  println("------------")
  println(conjunto1.toString(10))
  println("Conjunto2")
  println("------------")
  println(conjunto2.toString(10))
  val union = conjunto1.union(conjunto2)

  val interseccion = conjunto1.interseccion(conjunto2)

  val diferencia = conjunto1.diferencia(conjunto2)

  val filtrado = conjunto1.filtrar(filtros.esMayorQue)(3)

  val paratodo = conjunto1.paraTodo(filtros.esMayorQue)(3, 10)

  //val existencia = conjunto1.existe()

  val transformado = conjunto1.map(transformaciones.multiplica)(3)

  println("Unión")
  println("------------")
  println(union.toString(10))
  println("Intersección")
  println("------------")
  println(interseccion.toString(10))
  println("Diferencia")
  println("------------")
  println(diferencia.toString(10))
  println("Filtrado")
  println("------------")
  println(filtrado.toString(10))
  println("Para todo")
  println("------------")
  println(paratodo.toString)
  println("Existencia")
  println("------------")
//  println(existencia.toString(10))
  println("Transformado")
  println("------------")
  println(transformado.toString(15))
}
