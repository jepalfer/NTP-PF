package Lista

/**
 * Interfaz generica para la lista
 * @param A
 */
sealed trait Lista[+A]

/**
 * Objeto para definir lista vacia
 */
case object Nil extends Lista[Nothing]


/**
 * Clase para definir la lista como compuesta por elemento inicial
 * (cabeza) y resto (cola)
 * @param cabeza
 * @param cola
 * @param A
 */
case class Cons[+A](cabeza : A, cola : Lista[A]) extends Lista[A]

/**
 * Metodo para permitir crear listas sin usar new
 * @param elementos secuencia de elementos a incluir en la lista
 * @param A
 * @return
 */
def apply[A](elementos : A*) : Lista[A] =
  if (elementos.isEmpty) Nil
  else Cons(elementos.head, apply(elementos.tail: _*))

/**
 * Obtiene la longitud de una lista
 * @param lista 
 * @param A
 * @return
 */
def longitud[A](lista : Lista[A]) : Int =
  lista match
    case Nil => 0
    case Cons(_, cola) => 1 + longitud(cola)
/**
 * Metodo para sumar los valores de una lista de enteros
 * @param enteros
 * @return
 */
def sumaEnteros(enteros : Lista[Int]) : Double =
  enteros match
    case Nil => 0
    case Cons(cabeza, cola) => cabeza + sumaEnteros(cola)


/**
 * Metodo para multiplicar los valores de una lista de enteros
 * @param enteros
 * @return
 */
def productoEnteros(enteros : Lista[Int]) : Double =
  enteros match
    case Nil => 1
    case Cons(cabeza, cola) => cabeza * productoEnteros(cola)

/**
 * Metodo para agregar el contenido de dos listas
 * @param lista1
 * @param lista2
 * @param A
 * @return
 */
def concatenar[A](lista1: Lista[A], lista2: Lista[A]): Lista[A] =
  lista1 match
    case Nil =>
      lista2 match
        case Nil => Nil
        case Cons(_, _) => lista2

    case Cons(cabezaLista1, colaLista1) =>
      lista2 match
        case Nil => lista1
        case Cons(_, _) => Cons(cabezaLista1, concatenar(colaLista1, lista2))

/**
 * Funcion de utilidad para aplicar una funcion de forma sucesiva a los
 * elementos de la lista con asociatividad por la derecha
 * @param lista
 * @param neutro
 * @param funcion
 * @param A
 * @param B
 * @return
 */
def foldRight[A, B](lista : Lista[A], neutro : B)(funcion : (A, B) => B): B = ???

/**
 * Suma mediante foldRight
 * @param listaEnteros
 * @return
 */
def sumaFoldRight(listaEnteros : Lista[Int]) : Double = ???

/**
 * Producto mediante foldRight
 * @param listaEnteros
 * @return
 */
def productoFoldRight(listaEnteros : Lista[Int]) : Double = ???

/**
 * Reemplaza la cabeza por nuevo valor. Se asume que si la lista esta vacia
 * se devuelve una lista con el nuevo elemento
 * @param lista
 * @param cabezaNueva
 * @param A
 * @return
 */
def asignarCabeza[A](lista : Lista[A], cabezaNueva : A) : Lista[A] =
  lista match
    case Nil => Cons(cabezaNueva, Nil)
    case Cons(_, cola) => Cons(cabezaNueva, cola)

/**
 * Devuelve el primer elemento de la lista
 * (si no esta vacia). Por eso se devuelve Option
 * @param lista
 * @tparam A
 * @return
 */
def head[A](lista : Lista[A]) : Option[A] =
  lista match
    case Nil => None
    case Cons(cabeza, _) => Some(cabeza)

/**
 * Elimina el elemento cabeza de la lista
 * @param lista
 * @param A
 * @return
 */
def tail[A](lista : Lista[A]): Lista[A] =
  lista match
    case Nil => Nil
    case Cons(_, cola) => cola

/**
 * Elimina los n primeros elementos de una lista
 * @param lista lista con la que trabajar
 * @param n numero de elementos a eliminar
 * @param A tipo de datos
 * @return
 */
def eliminar[A](lista : Lista[A], n: Int) : Lista[A] =

  @annotation.tailrec
  def go(nuevaLista: Lista[A], index: Int): Lista[A] =
    if (index == n) nuevaLista
    else {
      nuevaLista match
        case Nil => Nil
        case Cons(_, cola) => go(cola, index + 1)
    }
  go(lista, 0)

/**
 * Elimina elementos mientra se cumple la condicion pasada como
 * argumento
 * @param lista lista con la que trabajar
 * @param criterio predicado a considerar para continuar con el borrado
 * @param A tipo de datos a usar
 * @return
 */
//def eliminarMientras[A](lista : Lista[A], criterio: A => Boolean) : Lista[A] = ???
def eliminarMientras[A](lista: Lista[A], criterio: (A, A) => Boolean)(valor: A): Lista[A] =
  lista match
    case Nil => Nil
    case Cons(cabeza, cola) => {
      if (!criterio(cabeza, valor)) lista
      else eliminarMientras(cola, criterio)(valor)
    }


/**
 * Elimina el ultimo elemento de la lista. Aqui no se pueden compartir
 * datos en los objetos y hay que generar una nueva lista copiando
 * datos
 * @param lista lista con la que trabajar
 * @param A tipo de datos de la lista
 * @return
 */
def eliminarUltimo[A](lista : Lista[A]) : Lista[A] = ???

/**
 * foldLeft con recursividad tipo tail
 * @param lista lista con la que trabajar
 * @param neutro elemento neutro
 * @param funcion funcion a aplicar
 * @param A parametros de tipo de elementos de la lista
 * @param B parametro de tipo del elemento neutro
 * @return
 */
//@annotation.tailrec
def foldLeft[A, B](lista : Lista[A], neutro: B)(funcion : (B, A) => B): B = ???

object criterios{
  def mayorQue[A](x: A, y: A)(implicit ord: Ordering[A]): Boolean = ord.gt(x, y)
  def mayorIgual[A](x: A, y: A)(implicit ord: Ordering[A]): Boolean = ord.gteq(x, y)
}

object prueba extends App{
  val miLista = Lista(1, 2, 3, 4)
  println("============")
  println("Suma enteros")
  println("============")
  println(sumaEnteros(miLista))

  println("============")
  println("Producto enteros")
  println("============")
  println(productoEnteros(miLista))

  println("============")
  println("head sin modificar")
  println("============")
  println(head(miLista))

  val nuevaLista = asignarCabeza(miLista, 2)

  println("============")
  println("head modificado")
  println("============")
  println(head(nuevaLista))

  println("============")
  println("longitud miLista")
  println("============")
  println(longitud(miLista))

  println("============")
  println("eliminar elementos")
  println("============")
  val miSegundaLista = eliminar(miLista, 4)
  println(miSegundaLista)

  println("============")
  println("eliminar mientras")
  println("============")
  val listaDescendente = Lista(4, 3, 2, 1)
  val miTerceraLista = eliminarMientras(listaDescendente, criterios.mayorIgual)(3)
  println(miTerceraLista)

  println("============")
  println("concatenar")
  println("============")
  val A = Lista(1, 2, 3)
  val B = Lista (4, 5)
  println(concatenar(A, B))
}