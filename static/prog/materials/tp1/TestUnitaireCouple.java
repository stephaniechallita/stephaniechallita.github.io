package util;

// les outils externes
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

// les types du projet
import types.Rationnel;
import rationnel.RationnelSimple;
import util.Couple;

public class TestUnitaireCouple
{
  // ------------------------------------------------
  // composantes de 2 types différents
  // ------------------------------------------------
  @Test(timeout=20)
  // test get
  public void test_get_composantes_types_differents()
  {
    String s1 = new String("Albert le chat");
    Double d1 = new Double(Math.PI);
    Couple<String, Double> c1 =
      new Couple<String, Double>(s1, d1);
					
    Assert.assertTrue(c1.getFirst()  == s1);
    Assert.assertTrue(c1.getSecond() == d1);
  }

  @Test(timeout=20)
  // test set
  public void test_set_composantes_types_differents()
  {
    String s1 = new String("Albert le chat");
    Double d1 = new Double(Math.PI);
    Couple<String, Double> c1 =
      new Couple<String, Double>(s1, d1);

    // devrait être correct
    Assert.assertTrue(c1.getFirst()  == s1);
    Assert.assertTrue(c1.getSecond() == d1);

    // modifier la première composante
    String s2 = new String("Sous-marin jaune");
    c1.setFirst(s2);
    Assert.assertTrue(c1.getFirst()  == s2);
    Assert.assertTrue(c1.getSecond() == d1);

    // modifier la deuxième composante
    Double d2 = new Double(Math.E);
    c1.setSecond(d2);
    Assert.assertTrue(c1.getFirst()  == s2);
    Assert.assertTrue(c1.getSecond() == d2);
  }

  @Test(timeout=20)
  // test equals
  public void test_equals_types_differents()
  {
    String s1 = new String("Albert le chat");
    String s2 = new String("Albert le chat");
    Assert.assertTrue(s1.equals(s2));

    Double d1 = new Double(Math.PI);
    Double d2 = new Double(Math.PI);
    Assert.assertTrue(d1.equals(d2));

    Couple<String, Double> c1 =
      new Couple<String, Double>(s1, d1);
    Couple<String, Double> c2 =
      new Couple<String, Double>(s2, d2);

    Assert.assertTrue(c1.getFirst().equals(c2.getFirst()));
    Assert.assertTrue(c1.getSecond().equals(c2.getSecond()));
    Assert.assertTrue(c1.equals(c2));
  }

  // ------------------------------------------------
  // composantes de même type
  // ------------------------------------------------
  @Test(timeout=20)
  // test get
  public void test_get_composantes_meme_type()
  {
    String s1 = new String("Albert le chat");
    String s2 = new String("Fritz ze cat");
    Couple<String, String> c1 =
      new Couple<String, String>(s1, s2);
					
    Assert.assertTrue(c1.getFirst()  == s1);
    Assert.assertTrue(c1.getSecond() == s2);
  }

  @Test(timeout=20)
  // test set
  public void test_set_composantes_meme_type()
  {
    String s1 = new String("Albert le chat");
    String s2 = new String("Sous-marin jaune");
    Couple<String, String> c1 =
      new Couple<String, String>(s1, s2);
    // devrait être correct
    Assert.assertTrue(c1.getFirst()  == s1);
    Assert.assertTrue(c1.getSecond() == s2);

    // modifier la première composante
    String s3 = new String("Fritz ze cat");
    c1.setFirst(s3);
    Assert.assertTrue(c1.getFirst()  == s3);
    Assert.assertTrue(c1.getSecond() == s2);

    // modifier la deuxième composante
    String s4 = new String("Yellow submarine");
    c1.setSecond(s4);
    Assert.assertTrue(c1.getFirst()  == s3);
    Assert.assertTrue(c1.getSecond() == s4);
  }

  @Test(timeout=20)
  // test equals
  public void test_equals_meme_type()
  {
    String s1 = new String("Albert le chat");
    String s2 = new String("Albert le chat");
    Assert.assertTrue(s1.equals(s2));

    String s3 = new String("Fritz ze cat");
    String s4 = new String("Fritz ze cat");
    Assert.assertTrue(s3.equals(s4));

    Couple<String, String> c1 =
      new Couple<String, String>(s1, s3);
    Couple<String, String> c2 =
      new Couple<String, String>(s2, s4);

    Assert.assertTrue(c1.getFirst().equals(c2.getFirst()));
    Assert.assertTrue(c1.getSecond().equals(c2.getSecond()));
    Assert.assertTrue(c1.equals(c2));
  }

}
