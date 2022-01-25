/* -------------------------------------------------------------------------- */
/* Classe    : Mutation                                                       */
/* Descrição : Classe para a implementação do operador genético Mutação.      */
/* -------------------------------------------------------------------------- */
package GeneticOperators;

import Expressions.*;
import Expressions.Elements.*;
import Misc.*;
import Population.*;
import Selection.*;
import GeneratorFunctions.*;

public class Mutation extends geneticOperator
{

/* ------------------------- constantes de classe --------------------------- */

  static final boolean DEFAULT_mutation_keeptrying = false;
  static final double DEFAULT_mutation_internal = 0.9;
  static final double DEFAULT_mutation_external = 0.1;    
  static final generateIndividual DEFAULT_mutation_generation_method = 
         new generateGROW();
  static final depthRamp DEFAULT_mutation_depth = new depthRamp(0,4);          
  
/* ----------------------------- constructores ------------------------------ */

public Mutation(selectMethod s,
                boolean kt,                
                double i,
                double e,
                generateIndividual m,
                depthRamp dr,
                Function[] f,
                Terminal[] t)
  { this.setName("mutation");
    this.setNumReturnInd(1);
    this.setSelect(s);
    this.setKeepTrying(kt);
    this.setInternal(i); 
    this.setExternal(e);
    this.setGenerateMethod(m);
    this.setDepthRamp(dr);
    setTerminalSet(t);
    setFunctionSet(f);  }

                                     
public Mutation(selectMethod s,Function[] f,Terminal[] t)
  {  this(s,
          DEFAULT_mutation_keeptrying,
          DEFAULT_mutation_internal,            
          DEFAULT_mutation_external,
          DEFAULT_mutation_generation_method,
          DEFAULT_mutation_depth,f,t);  }

/* ------------------------- variáveis de instânçia ------------------------- */

  /* método de selecção do indivíduo. */
  private selectMethod select;
  /* especifica o que fazer quando uma mutação produz uma árvore que viola
     os limites de nº nodos/profundidade. */
  private boolean keep_trying;
  /* especifica com que frequênçia são escolhidos nodos interiores (das árvores
     de expressões) para serem pontos de crossover. */
  private double internal; 
  /* especifica com que frequençia são escolhidos nodos exteriores para serem 
     pontos de crossover. */
  private double external;
  /* especifica que método é utilizado para gerar a árvore substituta. */
  private generateIndividual generate_method;
  /* especifica o intervalo das profundidades para gerar a árvore substituta. */
  private depthRamp depth;
  /* grupo de terminais */
  private Terminal[] tset;
  /* grupo de funcoes */
  private Function[] fset;  
  
/* ----------------------- métodos selectores (get/set) --------------------- */
  
public selectMethod getSelect()
  {  return (this.select);  }

public boolean getKeepTrying()
  {  return (this.keep_trying);  }

public double getInternal()
  {  return (this.internal);  }

public double getExternal()
  {  return (this.external);  }

public generateIndividual getGenerateMethod()
  {  return (this.generate_method);  }

public depthRamp getDepthRamp()
  {  return (this.depth);  }

public void setSelect(selectMethod s)
  {  this.select = (selectMethod) s.clone();  }

public void setKeepTrying(boolean kt)
  {  this.keep_trying = kt;  }

public void setInternal(double i)
  {  this.internal = i;  }

public void setExternal(double e)
  {  this.external = e;  }

public void setGenerateMethod(generateIndividual m)
  {  this.generate_method = (generateIndividual) m.clone();  }

public void setDepthRamp(depthRamp dr)
  {  this.depth = (depthRamp) dr.clone();  }

private void setTerminalSet(Terminal[] tset)
  { Object[] aux = Misc.returnArrayUnRepeated (tset);            
    this.tset = new Terminal[aux.length];
    for (int i=0; i < aux.length; i++)
        this.tset[i] = (Terminal) ((Terminal) aux[i]).clone();  }

private void setFunctionSet(Function[] fset)
  { Object[] aux = Misc.returnArrayUnRepeated (fset);            
    this.fset = new Function[aux.length];
    for (int i=0; i < aux.length; i++)
        this.fset[i] = (Function) ((Function) aux[i]).clone();  }

/* ----------------------- métodos de instânçia ----------------------------- */

/** */  
public Individual[] doOperation(Generation g)
  { Individual[] i = new Individual[this.getNumReturnInd()]; 

    this.select.setGenerator(this.generator);
    
    expressionTree mutated;
    expressionTree subtree;

      
    int mutate_point;

    boolean done = false;        
    boolean func_node;
  
    boolean ok = false;
      
    do
      {
        i[0] = this.select.doSelection(g);

        mutated = (expressionTree) i[0].getGeneticCode().clone();

        /* escolhe o ponto de mutação baseado nas probabilidades dos nodos.*/
        do 
         {                
           if (this.generator.nextDouble() < this.internal)
               func_node = true;
           else
               func_node = false;
  
           mutate_point = (int) 
                          (mutated.size() * this.generator.nextDouble()); 
  
           subtree = (expressionTree) 
                      mutated.subTree(mutate_point).clone();
  
           if (func_node)
              if (subtree instanceof functionNode || mutated.size() == 1)
                  done = true;  
           else
              if (subtree instanceof terminalNode)
                  done = true;
         } while(!done);
          
                             
        /* gerar uma subárvore aleatória para inserir no ponto de mutação */   
        
        int aux_depth = this.depth.getInferiorLimit() + 
                        this.generator.nextInt(this.depth.getSuperiorLimit());
                        
        subtree = this.generate_method.generate
                     (fset,
                      tset,
                      aux_depth,
                      this.generator).getGeneticCode();    
                    
             
        /* aplicar a mutação */
        if (mutate_point == 0)
           mutated = subtree;
        else    
           mutated.replaceTree(mutate_point-1,subtree);
          

        if ( (mutated.size() > this.getLimits().getMaxNodes()) ||
             (mutated.depth() > this.getLimits().getMaxDepth()) )
            {
              if (!this.keep_trying)
                { mutated = i[0].getGeneticCode();
                  ok = true;  }
              else
                  ok = false;    
                     
            }
          else
            ok = true;  
            
        } while (!ok);
        
        i[0] = new Individual(mutated);
    return (i);
  }

/* ----------------------- métodos das interfaces --------------------------- */

public String toString()
  { String str_aux;
    
    str_aux =  "(" + this.getName() + "," +
               this.getSelect().toString() + "," +      
               "keep_trying=" + this.getKeepTrying() + "," +                 
               "internal=" + this.getInternal() + "," +                      
               "external=" + this.getExternal() + "," +
               this.getGenerateMethod().toString() + "," +
               this.getDepthRamp().toString() +
               ")";  
    return (str_aux);          
  }

public boolean equals(Object o)
  {
    boolean equal = false;
    Mutation mut_aux;
    
    if (o instanceof Mutation)
      {
        mut_aux = (Mutation) o;
        equal= this.getName().equals(mut_aux.getName()) &&
               (this.getNumReturnInd() == mut_aux.getNumReturnInd()) &&
               this.getSelect().equals(mut_aux.getSelect()) &&
               (this.getKeepTrying() == mut_aux.getKeepTrying()) &&         
               (this.getInternal() == mut_aux.getInternal()) &&
               (this.getExternal() == mut_aux.getExternal()) &&
               (this.getGenerateMethod().equals(mut_aux.getGenerateMethod())) &&
               (this.getDepthRamp().equals(mut_aux.getDepthRamp()));
      }
    return (equal);
  }
    
public Object clone()
  {  return (new Mutation(this.getSelect(),
                          this.getKeepTrying(),
                          this.getInternal(),
                          this.getExternal(),
                          this.getGenerateMethod(),
                          this.getDepthRamp(),
                          this.fset,
                          this.tset));  }
}

/* -------------------------------------------------------------------------- */