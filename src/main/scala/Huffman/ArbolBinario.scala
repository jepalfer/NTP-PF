package Huffman

sealed trait BinaryTree

def obtieneDescodificado(arbol: Nodo, codigo: String): String =
  def go(nodoActual: Nodo, indice: Int, cadena: String): String =
    var nuevaCadena = cadena
    if (nodoActual.isInstanceOf[Hoja]){
      nuevaCadena += nodoActual.asInstanceOf[Hoja].obtenerCaracteres.head
      if (indice == codigo.length) nuevaCadena
      else go(arbol, indice, nuevaCadena)
    }
    else{
      //10001010
      if (codigo(indice) == '0') {
        go(nodoActual.asInstanceOf[Interno].hijoI, indice + 1, nuevaCadena)
      }
      else {
        go(nodoActual.asInstanceOf[Interno].hijoD, indice + 1, nuevaCadena)
      }
    }


  val cadenaFinal = ""
  go(arbol, 0, cadenaFinal)


def obtieneCodificado(arbol: Nodo, mensaje: String): String =
  def go(nodoActual: Nodo, indice: Int, cadena: String): String = ???


  val cadenaFinal = ""
  go(arbol, 0, cadenaFinal)


object ArbolBinario extends App{
  /*
  val hojaI = Hoja('A', 2)
  val hojaD = Hoja('B', 4)
  val interno1 = Interno(hojaI, hojaD)

  println(interno1.calcularPeso)

  val secuenciaInterno1 = interno1.obtenerCaracteres
  println(secuenciaInterno1.length)
  for (i <- secuenciaInterno1.indices){
    print(secuenciaInterno1(i))
  }*/
  val hojaA = Hoja('A', 8)
  val hojaH = Hoja('H', 3)
  val hojaD = Hoja('D', 1)
  val hojaG = Hoja('G', 1)
  val hojaC = Hoja('C', 1)
  val hojaE = Hoja('E', 1)
  val hojaF = Hoja('F', 1)
  val hojaB = Hoja('B', 1)

  val internoHD = Interno(hojaH, hojaD)
  val internoGC = Interno(hojaG, hojaC)
  val internoEF = Interno(hojaE, hojaF)
  val internoHDGC = Interno(internoHD, internoGC)
  val internoEFB = Interno(internoEF, hojaB)
  val internoBCDEFGH = Interno(internoHDGC, internoEFB)
  val raiz = Interno(hojaA, internoBCDEFGH)

  println(raiz.obtenerCaracteres)
  println(raiz.calcularPeso)

  println(raiz.hijoD.obtenerCaracteres)

  println(obtieneDescodificado(raiz, "011110111001"))
}
