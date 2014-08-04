import scala.collection.TraversableLike

/**
 * Makes a filterOut method available on collections which can be used
 * as a replacement for filterNot.. since I find the filterOUT name more
 * intuitive
 */

object CollectionImplicits {

  /**
   *   Adds a filterOut() on ALL collections (since we wrote it on TraversableLike)
   *   "A" represents the object type in the collection
   *   "Repr" represents the shape of the collection
   */
   implicit class RichCollection[A, Repr] (val xs: TraversableLike[A, Repr]) {
     def filterOut(predicate: (A) => Boolean): Repr = {
       xs.filterNot(predicate)
     }
   }

  /**
   * Adds a filterOut() method ONLY on Seq shapes
   */
   implicit class RichCollection1[A] (val xs: Seq[A]) {
     def filterOut(predicate: (A) => Boolean) = {
       xs.filterNot(predicate)
     }
   }
 }
