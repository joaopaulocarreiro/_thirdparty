/* -------------------------------------------------------------------------- */
/* Interface : Standard                                                       */
/* Descrição : Esta interface obriga a que as classes que a implementem cons- */
/*             -truam os métodos habituais a todos os objectos.               */
/* -------------------------------------------------------------------------- */
package GPkit.Misc;
 
public interface Standard 
{
   public String toString();
   public boolean equals(Object o);
   public Object clone();
}

/* -------------------------------------------------------------------------- */