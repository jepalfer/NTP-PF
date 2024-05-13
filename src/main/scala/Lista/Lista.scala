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
def apply[A](elementos : A*) : Lista[A] = ???

/**
 * Obtiene la longitud de una lista
 * @param lista 
 * @param A
 * @return
 */
def longitud[A](lista : Lista[A]) : Int = ???

/**
 * Metodo para sumar los valores de una lista de enteros
 * @param enteros
 * @return
 */
def sumaEnteros(enteros : Lista[Int]) : Double = ???


/**
 * Metodo para multiplicar los valores de una lista de enteros
 * @param enteros
 * @return
 */
def productoEnteros(enteros : Lista[Int]) : Double = ???

/**
 * Metodo para agregar el contenido de dos listas
 * @param lista1
 * @param lista2
 * @param A
 * @return
 */
def concatenar[A](lista1: Lista[A], lista2: Lista[A]): Lista[A] = ???

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
def asignarCabeza[A](lista : Lista[A], cabezaNueva : A) : Lista[A] = ???

/**
 * Devuelve el primer elemento de la lista
 * (si no esta vacia). Por eso se devuelve Option
 * @param lista
 * @tparam A
 * @return
 */
def head[A](lista : Lista[A]) : Option[A] = ???

/**
 * Elimina el elemento cabeza de la lista
 * @param lista
 * @param A
 * @return
 */
def tail[A](lista : Lista[A]): Lista[A] = ???

/**
 * Elimina los n primeros elementos de una lista
 * @param lista lista con la que trabajar
 * @param n numero de elementos a eliminar
 * @param A tipo de datos
 * @return
 */
def eliminar[A](lista : Lista[A], n: Int) : Lista[A] = ???

/**
 * Elimina elementos mientra se cumple la condicion pasada como
 * argumento
 * @param lista lista con la que trabajar
 * @param criterio predicado a considerar para continuar con el borrado
 * @param A tipo de datos a usar
 * @return
 */
def eliminarMientras[A](lista : Lista[A], criterio: A => Boolean) : Lista[A] = ???


/**
 115 * Elimina el ultimo elemento de la lista. Aqui no se pueden compartir
 116 * datos en los objetos y hay que generar una nueva lista copiando
 117 * datos
 118 * @param lista lista con la que trabajar
 119 * @param A tipo de datos de la lista
 120 * @return
 121 */
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