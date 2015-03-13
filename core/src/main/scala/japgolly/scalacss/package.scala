package japgolly

import scalaz.{Equal, NonEmptyList}

package object scalacss extends japgolly.scalacss.ScalaPlatform.Implicits {

  type Env = EnvF[Option]

  /**
   * A CSS value, like `"none"`, `"solid 3px black"`.
   */
  type Value = String

  final case class AV(attr: Attr, value: Value)

  type AVs = NonEmptyList[AV]

  final case class ClassName(value: String)

  /**
   * Describes the context of a number of CSS attribute-value pairs.
   *
   * Examples: `"div"`, `".debug"`, `"h3.bottom"`, `"a:visited"`.
   */
  type CssSelector = String

  /**
   * A CSS attribute and its corresponding value.
   *
   * Example: `CssKV("margin-bottom", "12px")`
   */
  final case class CssKV(key: String, value: String)
  object CssKV {
    implicit val equality: Equal[CssKV] = Equal.equalA
  }

  /**
   * A stylesheet in its entirety. Normally turned into a `.css` file or a `&lt;style&gt;` tag.
   */
  type Css = Stream[(CssSelector, NonEmptyList[CssKV])]

  final case class Warning(cond: Cond, desc: String)

  /**
   * Applicable style.
   *
   * A style that needs no more processing and can be applied to some target.
   */
  final case class StyleA(className: ClassName, style: StyleS)

  abstract class Mutex {
    @inline def apply[A](f: => A): A
  }
}