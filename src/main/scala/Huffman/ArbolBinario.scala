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
      if (codigo(indice) == '0') go(nodoActual.asInstanceOf[Interno].hijoI, indice + 1, nuevaCadena)
      else go(nodoActual.asInstanceOf[Interno].hijoD, indice + 1, nuevaCadena)
    }


  val cadenaFinal = ""
  go(arbol, 0, cadenaFinal)


def obtieneCodificado(tabla: TablaCodificacion)(mensaje: String): String =
  def go(indice: Int, cadena: String): String =
    var nuevaCadena = cadena
    if (indice == mensaje.length) nuevaCadena
    else {
      nuevaCadena += codificarConTabla(tabla)(mensaje(indice))
      go(indice + 1, nuevaCadena)
    }

  val cadenaFinal = ""
  go(0, cadenaFinal)


object ArbolBinario extends App{
  val textoPrueba = "AAAAAAAABBBCDEFGH"
  /*
  val hojaA = Hoja("A", 8)
  val hojaH = Hoja("H", 3)
  val hojaD = Hoja("D", 1)
  val hojaG = Hoja("G", 1)
  val hojaC = Hoja("C", 1)
  val hojaE = Hoja("E", 1)
  val hojaF = Hoja("F", 1)
  val hojaB = Hoja("B", 1)

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

  val tabla: TablaCodificacion = Map(
    'A' -> "0",
    'B' -> "111",
    'C' -> "1011",
    'D' -> "1001",
  )
  println(obtieneCodificado(tabla)("ABCD"))
  println(obtieneCodificado(convertirArbolTabla(raiz))("ABCD"))
  println(obtieneCodificado(convertirArbolTabla(raiz))("HG"))

  val arbol = generaArbol(textoPrueba)
  println(arbol.obtenerCaracteres + " || " + arbol.calcularPeso)

  val tablaCodificacion = convertirArbolTabla(arbol)

  println(convertirArbolTabla(raiz))
  println(tablaCodificacion)
  */
  println("=======PRUEBA DE ARBOL=======")
  var mensajeSecretoCaps = "CAPSTRAELAACASA"
  var binaryTreeCaps = generaArbol(mensajeSecretoCaps)
  var encodeCaps = obtieneCodificado(convertirArbolTabla(binaryTreeCaps))(mensajeSecretoCaps)
  println(obtieneDescodificado(binaryTreeCaps, encodeCaps))


  var mensajeSecretoABCD = "ABCD"
  var binaryTreeABCD = generaArbol(mensajeSecretoABCD)
  var encodeABCD = obtieneCodificado(convertirArbolTabla(binaryTreeABCD))(mensajeSecretoABCD)
  println(obtieneDescodificado(binaryTreeABCD, encodeABCD))

  val mensajeGuion = "AAAAAAAABBBCDEFGH"
  val arbolGuion = generaArbol(mensajeGuion)

  val palabra = "ABCD"
  val codificada = obtieneCodificado(convertirArbolTabla(arbolGuion))(palabra)
  val descodificada = obtieneDescodificado(arbolGuion, codificada)
  println( palabra + " => " + descodificada + " || " + codificada)

}
