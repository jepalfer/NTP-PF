package Conjunto

class Conjunto[A] (val elementos: List[A], LIMITE: BigInt) {
  def apply(value: A): Boolean = ???
  override def toString(): String = ???
  def conjuntoUnElemento(value: A): Conjunto[A] = ???
  def union(A: Conjunto[A], B: Conjunto[A]): Conjunto[A] = ???
  def interseccion(A: Conjunto[A], B: Conjunto[A]): Conjunto[A] = ???
  def diferencia(A: Conjunto[A], B: Conjunto[A]): Conjunto[A] = ???
  def filtrar(value: A)(filtro: A => Boolean): Conjunto[A] = ???
  def paraTodo(filtro: A => Boolean): Boolean = ???
  def existe(valor: A)(filtro: A => Boolean): Boolean = ???
  def map(transformacion: A => A): Conjunto[A] = ???
}
