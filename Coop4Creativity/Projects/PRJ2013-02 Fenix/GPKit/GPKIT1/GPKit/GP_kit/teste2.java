import java.util.*;
import Parameters.*;
import Misc.*;
import Selection.*;
import Population.*;
import GPrun.*;
import GeneticOperators.*;
import Fitness.*;
import GeneratorFunctions.*;
import Expressions.Elements.*;
import Expressions.*;
import EndRun.*;
import Specific.*;

public class teste2 
{
    public static final double pi = 3.141592654;
    
	public static fitnessCase[] test_cases(int n)
	  {
         fitnessCase[] cases = new fitnessCase[n];
         double inc = pi / (2 * n);
         
         for (int i=0; i<n; i++)
            cases[i] = new fitnessCase(i*inc,Math.cos(i*inc));
         
         return (cases);
	  }
      
    public static void main(String args[]) 
	{
       
       
       mul m = new mul();
       add a = new add();
       pdiv d = new pdiv();
       sub s = new sub();
       
       Variable x = new Variable("x");       
       ERConstant erc = new ERConstant('[',-1.0,1.0,']',0.01);
       
       Function[] functions = {new mul(),new add(),new sub()}; 
                               
       Terminal[] terminals = {x,erc};        
       
       errorFitness err_fitness = new errorFitness(x);
       errorStandardFitness std_fitness = new errorStandardFitness();
       adjustedFitness adj_fitness = new adjustedFitness();
       proportionateFitness prop_fitness = new proportionateFitness();
       

       /* tipos de métodos de selecção */
       OverSelection overselection = new OverSelection();
       Tournament tournament = new Tournament(4);
       FitnessProportionate fproportionate = new FitnessProportionate();
      
       /* definição dos operadores genéticos e seus parametros */
       Mutation mutation = new Mutation(overselection,functions,terminals);
       CrossOver crossover = new CrossOver(tournament);
       Reproduction reproduction = new Reproduction(tournament);
      
       /* definição dos parametros da geração de população */
       int breed_phases = 3;
       double[] rates = {0.05,0.90,0.05};
       geneticOperator[] operators = {reproduction,crossover,mutation};
       paramBreed breed = new paramBreed(breed_phases,true,rates,operators);
      
      
            
       bestInd stop_run = new bestInd();
      
       GPrun regression = new GPrun (new paramGlobal(20,500),
                                     new paramInitPopulation(),
                                     breed,
                                     new paramOutput(),
                                     new paramLimits(1000,10),
                                     err_fitness,
                                     std_fitness,
                                     adj_fitness,
                                     prop_fitness,
                                     terminals,
                                     functions,
                                     test_cases(200),
                                     stop_run);

     
      System.out.println("\n\n");      
      System.out.println("(GP run)");
      System.out.println("Parametros : ");
      System.out.println();      
      System.out.println(regression);
      System.out.println("\n\n");      
      
      
      Generation g = null;
                                          
      try 
        {  g = regression.run();
           System.out.println();
           System.out.println("best -> " + g.find_best());
        }
      catch (RandomAttempsExpiredException e)
        { System.out.println(e);  }
        
        
        
	}
}
