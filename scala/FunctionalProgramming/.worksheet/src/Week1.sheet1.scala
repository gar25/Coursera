package Week1

object sheet1 {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(75); 

  def abs(x: Double) = if (x < 0) -x else x;System.out.println("""abs: (x: Double)Double""");$skip(130); 

  def sqrtIter(guess: Double, x: Double): Double =
    if (isGoodEnough(guess, x)) guess
    else sqrtIter(improve(guess, x), x);System.out.println("""sqrtIter: (guess: Double, x: Double)Double""");$skip(87); 

  def isGoodEnough(guess: Double, x: Double) =
    abs(guess * guess - x) / 2 < 0.001;System.out.println("""isGoodEnough: (guess: Double, x: Double)Boolean""");$skip(71); 

  def improve(guess: Double, x: Double) =
    (guess + x / guess) / 2;System.out.println("""improve: (guess: Double, x: Double)Double""");$skip(16); val res$0 = 

  Math.sqrt(2);System.out.println("""res0: Double = """ + $show(res$0));$skip(15); val res$1 = 
  Math.sqrt(4);System.out.println("""res1: Double = """ + $show(res$1));$skip(20); val res$2 = 
  sqrtIter(2.0,2.0);System.out.println("""res2: Double = """ + $show(res$2))}

}
