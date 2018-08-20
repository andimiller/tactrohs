package net.andimiller.tactrohs.conversions

import cats._, cats.data._, cats.syntax._

import scala.language.implicitConversions

object EitherTConversions {
  implicit def fromEitherT[M[_], L, R](i: EitherT[M, L, R]): M[Either[L, R]] = i.value
  implicit def toEitherT[M[_], L, R](i: M[Either[L, R]]): EitherT[M, L, R] = EitherT(i)
}

object OptionTConversions {
  implicit def fromOptionT[M[_], O](i: OptionT[M, O]): M[Option[O]] = i.value
  implicit def toOptionT[M[_], O](i: M[Option[O]]): OptionT[M, O] = OptionT(i)
}
