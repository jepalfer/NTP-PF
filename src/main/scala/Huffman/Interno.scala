package Huffman

class Interno(var hijoI: Nodo, var hijoD: Nodo) extends Nodo{
  override def calcularPeso:Int =
    var pesoI = 0
    var pesoD = 0
    if (hijoI != null) pesoI = hijoI.calcularPeso

    if (hijoD != null) pesoD = hijoD.calcularPeso

    pesoI + pesoD

  override def obtenerCaracteres: List[Char] =

    var caracteresI = List[Char]()
    var caracteresD = List[Char]()

    if (hijoI != null) caracteresI = hijoI.obtenerCaracteres
    if (hijoD != null) caracteresD = hijoD.obtenerCaracteres

    caracteresI ++ caracteresD
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

def ordenaOcurrenciasNodos(ocurrencias: Map[Char, Int]): List[(Char, Int)] =
  val listaOcurrencias: List[(Char, Int)] = ocurrencias.toList
  val listaOrdenada: List[(Char, Int)] = listaOcurrencias.sortBy(_._2)
  listaOrdenada

def generaArbol(cadenaInicial: String): Nodo =

  var tabla = calculaOcurrencias(cadenaInicial)
  var listaOrdenada = ordenaOcurrenciasNodos(tabla)



  def go(nuevaLista: List[(Char, Int)]): Nodo =
    if (nuevaLista.length == 2) {
      println(nuevaLista.mkString("\n"))
      println("============")
      val hojaIzq = Hoja(nuevaLista(0)._1, nuevaLista(0)._2)
      val hojaDer = Hoja(nuevaLista(1)._1, nuevaLista(1)._2)
      Interno(hojaIzq, hojaDer)
    }
    else {
      println(nuevaLista.mkString("\n"))
      println("============")
      val hojaIzq = Hoja(nuevaLista(0)._1, nuevaLista(0)._2)
      val hojaDer = Hoja(nuevaLista(1)._1, nuevaLista(1)._2)
      val padre = Interno(hojaIzq, hojaDer)
      go(('D', padre.calcularPeso) :: nuevaLista.drop(2).sortBy(_._2))
    }

  go(listaOrdenada)