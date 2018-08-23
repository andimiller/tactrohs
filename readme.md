# tactrohs

This is a library intended to be used for dangerous shorthand to be used with https://github.com/typelevel/cats

## Okay so what do you have?

### Implicit conversions for EitherT and OptionT

So, in practice `EitherT` and `OptionT` are just extra syntax to be used with normal `M[Either[L,R]]` and `M[Option[O]]`, why not just make the conversions implicit and see what breaks?

```scala
import cats._ cats.data._, cats.implicits._, cats.effect._
import net.andimiller.tactrohs.conversions.EitherTConversions._

def program(i: Int) = IO.pure(42.asRight[String]) // start off with a normal IO
  .flatMap(i => EitherT.cond[IO](i%2==0, i, "it wasn't even")) // map it into an EitherT, which gets converted back to IO
  .leftSemiflatMap(e => IO { println(e); e }) // use an actual EitherT method and get an EitherT
  .unsafeRunSync() // convert back to IO to run it
```

### Automatic merging of `EitherT`s

This means I can write the body of an `http4s` route which returns an `EitherT[F, Response[F], Response[F]]` and it'll automatically be inferred as an `F[Response[F]]`, which saves me a lot of hassle.

### Why would you do this?

I got fed up of wrapping everything in `EitherT()` and calling `.value` on everything.

### Should I do this?

Probably not tbh
