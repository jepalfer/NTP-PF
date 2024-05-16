package Huffman

import scala.io.Source

class Lector {

}
/**
 * metodo de lectura de archivo que devuelve una cadena
 * con todos los caracteres alfabeticos a considerar
 * @param nombreArchivo
 * @param filtrar indica si se hace filtrado o se lee
 * el archivo como tal
 * @return
 */
def leerArchivo(nombreArchivo : String, filtrar : Boolean) : String = {
  val contenido = Source.fromFile(nombreArchivo).getLines().mkString
  if (filtrar == true) {
    contenido.filter(caracter => (caracter >= 'a' && caracter <= 'z') ||
      (caracter >= 'A' && caracter <= 'Z')).mkString
    }

  else contenido
}
