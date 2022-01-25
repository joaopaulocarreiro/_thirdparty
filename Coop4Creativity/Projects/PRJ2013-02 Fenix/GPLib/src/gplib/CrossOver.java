/**
 * GPLib
 * CrossOver
 */
package gplib;

public class CrossOver extends geneticOperator
{

/* ------------------------- constantes de classe --------------------------- */

  static final boolean DEFAULT_crossover_keeptrying = false;
  static final double DEFAULT_crossover_internal = 0.9;
  
/* ----------------------------- constructores ------------------------------ */

public CrossOver(selectMethod s1,
                 selectMethod s2,
                 boolean kt,
                 double i)
  {                                                                        
    this.setName("crossover");
    this.setNumReturnInd(2);
    this.setSelect1(s1);
    this.setSelect2(s2);
    this.setKeepTrying(kt);
    this.setInternal(i); 
  }

public CrossOver(selectMethod s1)
  {  this(s1,
          s1,
          DEFAULT_crossover_keeptrying,
          DEFAULT_crossover_internal);  }

/* ------------------------- vari�veis de inst�n�ia ------------------------- */
  
  /* m�todo de selec��o do primeiro progenitor. */
  private selectMethod select1;
  /* m�todo de selec��o do segundo progenitor. */
  private selectMethod select2;
  /* especifica o que fazer quando um crossover produz uma �rvore que viola
     os limites de n� nodos/profundidade. */
  private boolean keep_trying;
  /* especifica com que frequ�n�ia s�o escolhidos nodos interiores (das �rvores
     de express�es) para serem pontos de crossover. */
  private double internal; 

/* ----------------------- m�todos selectores (get/set) --------------------- */
  
public selectMethod getSelect1()
  {  return (this.select1);  }

public selectMethod getSelect2()
  {  return (this.select2);  }

public boolean getKeepTrying()
  {  return (this.keep_trying);  }

public double getInternal()
  {  return (this.internal);  }

public void setSelect1(selectMethod s)
  {  this.select1 = (selectMethod) s.clone();  }

public void setSelect2(selectMethod s)
  {  this.select2 = (selectMethod) s.clone();  }

public void setKeepTrying(boolean kt)
  {  this.keep_trying = kt;  }

public void setInternal(double i)
  {  this.internal = i;  }

/* ----------------------- m�todos de inst�n�ia ----------------------------- */

/** */  
public Individual[] doOperation(Generation g)
  { Individual[] i = new Individual[this.getNumReturnInd()];
    
    
    this.select1.setGenerator(this.generator);
    this.select2.setGenerator(this.generator);    

    expressionTree parent1;
    expressionTree parent2;
    
    expressionTree subtree1;
    expressionTree subtree2;        
    
    int cross_point_p1;
    int cross_point_p2;
    boolean done = false;        
    boolean func_node;

    boolean ok = false;
    
    do
      {
        i[0] = this.select1.doSelection(g);
        i[1] = this.select2.doSelection(g);

        parent1 = (expressionTree) i[0].getGeneticCode().clone();
        parent2 = (expressionTree) i[1].getGeneticCode().clone();

        do 
          {                
           if (this.generator.nextDouble() < this.internal)
             func_node = true;
           else
             func_node = false;

           cross_point_p1 = (int) 
                            (parent1.size() * this.generator.nextDouble()); 

           subtree1 = (expressionTree) 
                      parent1.subTree(cross_point_p1).clone();

           if (func_node)
              if (subtree1 instanceof functionNode || i[0].size() == 1)
                  done = true;  
           else
              if (subtree1 instanceof terminalNode)
                  done = true;
          } while(!done);

        done = false;
        
        do
          {
            if (this.generator.nextDouble() < this.internal)
              func_node = true;
            else
              func_node = false;

            cross_point_p2 = (int) 
                             (parent2.size() * this.generator.nextDouble()); 
                    
                          
            subtree2 = (expressionTree) 
                       parent2.subTree(cross_point_p2).clone();

            if (func_node)
            {
               if (subtree2 instanceof functionNode || i[1].size() == 1)
                 done = true;
            } 
            else
               if (subtree2 instanceof terminalNode)
                 done = true;
                 
          } while (!done);


                              
        /* aplicar a transforma��o no primeiro progenitor */
        if (cross_point_p1 == 0)
            parent1 = subtree2;
        else    
            parent1.replaceTree(cross_point_p1-1,subtree2);
        
        /* aplicar a transforma��o no segundo progenitor */    
        if (cross_point_p2 == 0)
           parent2 = subtree1;
        else    
           parent2.replaceTree(cross_point_p2-1,subtree1);

        if ( (parent1.size() > this.getLimits().getMaxNodes()) ||
             (parent1.depth() > this.getLimits().getMaxDepth()) || 
             (parent2.size() > this.getLimits().getMaxNodes()) ||
             (parent2.depth() > this.getLimits().getMaxDepth()) )
          {
            if (!this.keep_trying)
              { parent1 = i[0].getGeneticCode();
                parent2 = i[1].getGeneticCode();  
                ok = true;  }
            else
                ok = false;    
                   
          }
        else
          ok = true;  
          
      } while (!ok);
      
        i[0] = new Individual(parent1);
        i[1] = new Individual(parent2);;
          
    return (i);
  }

/* ----------------------- m�todos das interfaces --------------------------- */
    
public String toString()
  {
    String str_aux;
    
    str_aux =  "(" + this.getName() + "," +
               this.getSelect1().toString() + "," +      
               this.getSelect2().toString() + "," +           
               "keep_trying=" + this.getKeepTrying() + "," +                 
               "internal=" + this.getInternal() + "," +                      
               ")";  
    return (str_aux);          
  }

public boolean equals(Object o)
  {
    boolean equal = false;
    CrossOver cross_aux;
    
    if (o instanceof CrossOver)
      {
        cross_aux = (CrossOver) o;
        equal = this.getName().equals(cross_aux.getName()) &&
                (this.getNumReturnInd() == cross_aux.getNumReturnInd()) &&
                this.getSelect1().equals(cross_aux.getSelect1()) &&
                this.getSelect2().equals(cross_aux.getSelect2()) &&
                (this.getKeepTrying() == cross_aux.getKeepTrying()) &&         
                (this.getInternal() == cross_aux.getInternal());
      }
    return (equal);
  }
    
public Object clone()
  {  return (new CrossOver(this.getSelect1(),
                           this.getSelect2(),
                           this.getKeepTrying(),
                           this.getInternal()));  }
}

/* -------------------------------------------------------------------------- */