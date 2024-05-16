package Huffman

class Hoja (caracter:String, valor: Int) extends Nodo{

  override def calcularPeso: Int = valor

  override def obtenerCaracteres: String = caracter
}
def apply(caracter: String, valor: Int): Hoja = new Hoja (caracter, valor)
