/* -------------------------------------------------------------------------- */
/* Classe    : paramBreed                                                     */
/* Descrição : Classe que comporta a informação sobre parametros para as dife-*/
/*             rentes fases de geração (breed) das várias gerações.           */
/* -------------------------------------------------------------------------- */
package Parameters;

import Misc.*;
import GeneticOperators.*;

public class paramBreed implements Cloneable,
                                    Standard
{

/* ------------------------- constantes de classe --------------------------- */

  static final boolean DEFAULT_probabilistic_operators = true;  

/* ----------------------------- constructores ------------------------------ */
                                      
public paramBreed(int bp, boolean po, double rates[], geneticOperator go[])
  {  
     this.setBreedPhases(bp);     
     this.setProbOperators(po);
     this.setRatesOperators(bp,rates,go);
  }

public paramBreed(int bp, double rates[], geneticOperator operators[])
  {  this (bp,DEFAULT_probabilistic_operators,rates,operators);  }
  
/* ------------------------- variáveis de instânçia ------------------------- */

  /* O nº de fases de breed. */
  private int breed_phases;  
  /* define se as fases são selecionadas por acaso. */
  private boolean probabilistic_operators;  
  /* informação sobre o rate e operador da fase i. */
  private rate_and_operator[] breed;

/* ----------------------- métodos selectores (get/set) --------------------- */
  
public int getBreedPhases()
  {  return(this.breed_phases);  }

public boolean getProbOperators()
  {  return(this.probabilistic_operators);  }

public double getRate(int index)
  {  return (breed[index].getRate());  }

public geneticOperator getOperator(int index)
  {  return (breed[index].getOperator());  }  

public double[] getRates()
  { int bphases = this.getBreedPhases();
    double double_aux[] = new double[bphases];

    for (int i=0; i<bphases; i++)
        double_aux[i] = this.getRate(i);
    return(double_aux);
  }    

public geneticOperator[] getOperators()
  { 
    int bphases = this.getBreedPhases();
    geneticOperator int_aux[] = new geneticOperator[bphases];

    for (int i=0; i<bphases; i++)
        int_aux[i] = this.getOperator(i);
    return(int_aux);
  }    

private void setBreedPhases(int bp)
  {  this.breed_phases = bp;  }

public void setProbOperators(boolean po)
  {  this.probabilistic_operators = po;  }

private void setRatesOperators(int bp,double[] rates, geneticOperator[] ops)
  { double tot_rates = 0;

    for (int i=0; i<rates.length; i++)
      tot_rates += rates[i];
        
    if ( (tot_rates == 1.0) && (rates.length == bp) && (ops.length == bp))
      {
         this.breed = new rate_and_operator[breed_phases];
         for (int i=0; i < bp; i++)
          { rate_and_operator ro = new rate_and_operator(rates[i],ops[i]);
            breed[i] = ro;  }
      }
    else
      {
         this.setBreedPhases(ops.length);
         this.breed = new rate_and_operator[ops.length];
         double std_rate = 1 / (double) ops.length; 
         for (int i=0; i < ops.length; i++)
          { rate_and_operator ro = new rate_and_operator(std_rate,ops[i]);
            breed[i] = ro;  }
      }    
  }
/* --------------------------- métodos privados ----------------------------- */

private rate_and_operator[] getBreed()
  { int bphases = this.getBreedPhases(); 
    rate_and_operator[] aux = new rate_and_operator[bphases];
    
    for (int i=0; i<bphases; i++)
      aux[i] = this.breed[i];
    return (aux);  
  }

private rate_and_operator getRateOp(int i)
  {  return (this.breed[i]);  }

private boolean equalBreed(rate_and_operator[] breed_aux)
  {
    boolean equal = false;
    
    if (this.breed.length == breed_aux.length)
      { equal = true;
        for (int i=0; i<this.getBreedPhases() && equal; i++)
            equal = this.breed[i].equals(breed_aux[i]);  }
      
    return (equal);  
  }  

/* ----------------------- métodos das interfaces --------------------------- */

public String toString()
  { String str_ro = new String();
    int bphases = this.getBreedPhases();
    
    for (int i=0; i<bphases; i++)
    {
       str_ro += "breed[" + i + "].rate=" + this.getRateOp(i).getRate()+"\n";
       str_ro += "breed[" + i + "].operator=" + 
                 this.getRateOp(i).getOperator().toString()+"\n";
    }

    String str_aux = 
      "breed.breed_phases=" + this.getBreedPhases() + "\n" + 
      "breed.probabitistic_operators=" + this.getProbOperators() + "\n" +
      str_ro;                

    return(str_aux);
  }

public boolean equals(Object o)
  {
    paramBreed param_aux;
    boolean equal = false;
    
    if (o instanceof paramBreed)
      { param_aux = (paramBreed) o;	
        equal = (this.getBreedPhases() == param_aux.getBreedPhases()) &&
                (this.getProbOperators() == param_aux.getProbOperators() &&
                (this.equalBreed(param_aux.getBreed())) );  }        
    return(equal);
  }

public Object clone()
  {  return (new paramBreed(this.getBreedPhases(),
                            this.getProbOperators(),
                            this.getRates(),
                            this.getOperators()));  }

}

/* -------------------------------------------------------------------------- */
/* Classe Auxiliar : rate_and_operator                                        */
/* Descrição       : Classe que implementa o par rate / operador genético     */
/* -------------------------------------------------------------------------- */

class rate_and_operator implements Cloneable,
                                   Standard
{

/* ----------------------------- constructores ------------------------------ */

public rate_and_operator(double r, geneticOperator o)
  { this.setRate(r);
    this.setOperator(o);  }

/* ------------------------- variáveis de instânçia ------------------------- */
  
  /* rate da fase */
  private double rate;
  /* operador genético da fase */
  private geneticOperator operator;
  
/* ----------------------- métodos selectores (get/set) --------------------- */
  
public double getRate()
  {  return (this.rate);  }

public geneticOperator getOperator()
  {  return (this.operator);  }

public void setRate(double rate)
  {  this.rate = rate;  }

public void setOperator(geneticOperator operator)
  {  this.operator = (geneticOperator) operator.clone();  }

/* ----------------------- métodos das interfaces --------------------------- */

public String toString()    
  { String str_aux;

    str_aux  = "(" +
               this.getRate() + "," +
               this.getOperator().toString() +
               ")";
    return (str_aux);  
  }

public boolean equals(Object o)
  { rate_and_operator aux;
    boolean equal = false;
    
    if (o instanceof rate_and_operator)
      { aux = (rate_and_operator) o;
        equal = this.getRate() == aux.getRate() &&
                this.getOperator().equals(aux.getOperator());  }
    return (equal);  
  }

public Object clone()
  {  return (new rate_and_operator(this.getRate(),
                                   this.getOperator()));  }
  
}  

/* -------------------------------------------------------------------------- */