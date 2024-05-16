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

  println("=======PRUEBA DE ARBOL=======")
  var mensajeSecretoCaps = "CAPSTRAELAACASA"
  var binaryTreeCaps = generaArbol(mensajeSecretoCaps)
  var encodeCaps = obtieneCodificado(convertirArbolTabla(binaryTreeCaps))(mensajeSecretoCaps)
  println(obtieneDescodificado(binaryTreeCaps, encodeCaps))


  var mensajeSecretoABCD = "ABCD"
  var binaryTreeABCD = generaArbol(mensajeSecretoABCD)
  var encodeABCD = obtieneCodificado(convertirArbolTabla(binaryTreeABCD))(mensajeSecretoABCD)
  println(obtieneDescodificado(binaryTreeABCD, encodeABCD))
  */
  val mensajeGuion = "AAAAAAAABBBCDEFGH"
  val arbolGuion = generaArbol(mensajeGuion)

  val palabra = "ABCD"
  val codificada = obtieneCodificado(convertirArbolTabla(arbolGuion))(palabra)
  val descodificada = obtieneDescodificado(arbolGuion, codificada)
  println( palabra + " => " + descodificada + " || " + codificada)

  println("=======REGENTA=======")
  val regenta = leerArchivo("src/main/scala/Huffman/codificacionHuffman/regenta.txt", true)
  var binaryTreeRegenta = generaArbol(regenta)
  var tablaRegenta = convertirArbolTabla(binaryTreeRegenta)
  val mensajeSecreto = leerArchivo("src/main/scala/Huffman/codificacionHuffman/mensajeSecreto", false)
  val mensajeSecretoDescodificado = obtieneDescodificado(binaryTreeRegenta, mensajeSecreto)
  val mensajeSecretoCodificado = obtieneCodificado(tablaRegenta)(mensajeSecretoDescodificado)

  println(mensajeSecretoDescodificado + " => " + mensajeSecretoCodificado)
  if (mensajeSecretoCodificado.equals(mensajeSecreto)) println("Codificado correctamente")
}
