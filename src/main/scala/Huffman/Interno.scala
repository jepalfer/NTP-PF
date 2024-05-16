package Huffman

class Interno(var hijoI: Nodo, var hijoD: Nodo) extends Nodo{
  override def calcularPeso:Int =
    var pesoI = 0
    var pesoD = 0
    if (hijoI != null) pesoI = hijoI.calcularPeso

    if (hijoD != null) pesoD = hijoD.calcularPeso

    pesoI + pesoD

  override def obtenerCaracteres: String =

    var caracteresI = ""
    var caracteresD = ""

    if (hijoI != null) caracteresI = hijoI.obtenerCaracteres
    if (hijoD != null) caracteresD = hijoD.obtenerCaracteres

    caracteresI += caracteresD
    caracteresI
}

//def apply(hijoI: Nodo, hijoD: Nodo): Interno = new Interno(hijoI, hijoD)

def calculaOcurrencias(cadenaInicial: String): Map[Char, Int] =
  def go(index: Int, nuevaTabla: Map[Char, Int]): Map[Char, Int] =
    if (index == cadenaInicial.length) nuevaTabla
    else {
      if (nuevaTabla.contains(cadenaInicial(index))) {
        val nuevaEntrada = nuevaTabla + (cadenaInicial(index) -> (nuevaTabla(cadenaInicial(index)) + 1))
        go(index + 1, nuevaEntrada)
      }
      else {
        val nuevaEntrada = nuevaTabla + (cadenaInicial(index) -> 1)
        go(index + 1, nuevaEntrada)
      }
    }

  val tabla: Map[Char, Int] = Map()
  go(0, tabla)

def ordenaOcurrenciasNodos(ocurrencias: Map[String, Int]): List[(String, Int)] =
  val listaOcurrencias: List[(String, Int)] = ocurrencias.toList
  val listaOrdenada: List[(String, Int)] = listaOcurrencias.sortBy(_._2)
  listaOrdenada

def generaArbol(cadenaInicial: String): Nodo =

  var tabla = calculaOcurrencias(cadenaInicial)
  val tablaConvertida = tabla.map{case (char, count) => char.toString -> count}
  var listaOrdenada = ordenaOcurrenciasNodos(tablaConvertida)

  var listaNodos: List[Nodo] = List.empty

  for (elemento <- listaOrdenada.reverse) {
    listaNodos = Hoja(elemento._1, elemento._2) :: listaNodos
  }

  def go(nuevaLista: List[Nodo]): Nodo =
    if (nuevaLista.length == 2) {
      Interno(nuevaLista(0), nuevaLista(1))
    }
    else {
      println(nuevaLista(0).obtenerCaracteres + " => " + nuevaLista(0).calcularPeso + " || " +
        nuevaLista(1).obtenerCaracteres + " => " + nuevaLista(1).calcularPeso)
      val padre = Interno(nuevaLista(0), nuevaLista(1))
      val eliminaNodos = nuevaLista.drop(2)
      val padreAñadido = padre :: eliminaNodos
      go(padreAñadido.sortBy(_.calcularPeso))
    }

  go(listaNodos)