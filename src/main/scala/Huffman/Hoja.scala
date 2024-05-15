package Huffman

class Hoja (caracter:Char, valor: Int) extends Nodo{

  override def calcularPeso: Int = valor

  override def obtenerCaracteres: List[Char] = List(caracter)

  override def generaArbol: List[Nodo] = ???
}

def apply(caracter: Char, valor: Int): Hoja = new Hoja (caracter, valor)
