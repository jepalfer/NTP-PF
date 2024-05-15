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

  override def generaArbol: List[Nodo] = ???

}

def apply(hijoI: Nodo, hijoD: Nodo): Interno = new Interno(hijoI, hijoD)
