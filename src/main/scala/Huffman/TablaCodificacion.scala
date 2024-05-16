package Huffman

type TablaCodificacion=Map[Char, String]

def codificarConTabla(tabla : TablaCodificacion)(caracter : Char) : String = tabla(caracter)

/*
if (nodoActual.isInstanceOf[Hoja]) {
  val nuevaEntrada = nuevaTabla + (nodoActual.obtenerCaracteres(nodoActual.obtenerCaracteres.head) -> secuencia)
  nuevaEntrada
}
else {
  var tablaIzq = nuevaTabla
  var tablaDer = nuevaTabla
  if (nodoActual.asInstanceOf[Interno].hijoI != null)
    tablaIzq = go(nodoActual.asInstanceOf[Interno].hijoI, nuevaTabla)(secuencia + "0")

  if (nodoActual.asInstanceOf[Interno].hijoD != null)
    tablaDer = go(nodoActual.asInstanceOf[Interno].hijoD, nuevaTabla)(secuencia + "1")

  tablaIzq ++ tablaDer
}*/
/*
def convertirArbolTabla(arbolCodificacion: Nodo): TablaCodificacion =
  var tabla: TablaCodificacion = Map()

  def go(nodoActual: Nodo, nuevaTabla: TablaCodificacion)(secuencia: String): TablaCodificacion = {
    nodoActual match {
      case hoja: Hoja =>
        val nuevaEntrada = nuevaTabla + (hoja.obtenerCaracteres.head -> secuencia)
        nuevaEntrada
      case interno: Interno =>
        var tablaIzquierda = nuevaTabla
        var tablaDerecha = nuevaTabla
        if (interno.hijoI != null)
          tablaIzquierda = go(interno.hijoI, nuevaTabla)(secuencia + "0")
        if (interno.hijoD != null)
          tablaDerecha = go(interno.hijoD, nuevaTabla)(secuencia + "1")
        tablaIzquierda ++ tablaDerecha
    }
  }

  if (arbolCodificacion.isInstanceOf[Interno]) {
    val interno = arbolCodificacion.asInstanceOf[Interno]
    if (interno.hijoI != null)
      tabla = go(interno.hijoI, tabla)("0")
    if (interno.hijoD != null)
      tabla = go(interno.hijoD, tabla)("1")
  }

  tabla
 */
def convertirArbolTabla(arbolCodificacion: Nodo): TablaCodificacion =
  var tabla: TablaCodificacion = Map()

  def go(nodoActual: Nodo, nuevaTabla: TablaCodificacion)(secuencia: String): TablaCodificacion =
    nodoActual match {
      case hoja: Hoja =>
        val nuevaEntrada = nuevaTabla + (hoja.obtenerCaracteres.head -> secuencia)
        nuevaEntrada
      case interno: Interno =>
        var tablaIzquierda = nuevaTabla
        var tablaDerecha = nuevaTabla
        if (interno.hijoI != null)
          tablaIzquierda = go(interno.hijoI, nuevaTabla)(secuencia + "0")
        if (interno.hijoD != null)
          tablaDerecha = go(interno.hijoD, nuevaTabla)(secuencia + "1")
        tablaIzquierda ++ tablaDerecha
    }

  if (arbolCodificacion.isInstanceOf[Interno]) {
    val interno = arbolCodificacion.asInstanceOf[Interno]
    if (interno.hijoI != null) tabla = go(interno.hijoI, tabla)("0")
    if (interno.hijoD != null) tabla = go(interno.hijoD, tabla)("1")
  }

  tabla