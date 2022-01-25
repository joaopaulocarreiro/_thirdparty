/* -------------------------------------------------------------------------- */
/* Classe    : app                                                            */
/* Descri��o : Esta classe implementa uma aplica��o de previs�o se s�ries tem-*/
/*             -porais atrav�s dum algoritmo de programa��o gen�tica.         */
/* -------------------------------------------------------------------------- */
package Aplication.bin;

import GPkit.Parameters.*;
import GPkit.Misc.*;
import GPkit.Selection.*;
import GPkit.Population.*;
import GPkit.GPrun.*;
import GPkit.GeneticOperators.*;
import GPkit.Fitness.*;
import GPkit.GeneratorFunctions.*;
import GPkit.Expressions.Elements.*;
import GPkit.Expressions.*;
import GPkit.EndRun.*;
import Aplication.Classes.Aritmetic.*;
import Aplication.Classes.Fitness.*;
import Aplication.Classes.FitnessCase.*;
import Aplication.Classes.Parameters.*;
import Aplication.Classes.Value.*;
import Aplication.Classes.EndRun.*;
import java.util.Vector;
import java.util.Enumeration;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.EOFException;

public class pred 
{
    
  /* m�todo que calcula os valores do melhor individuo para os valores da 
     s�rie usados para previs�o. ----------------------------------------- */  
  public static double  evaluateVerification(Individual ind, 
                                             Variable[] v,   
                                             fitnessCase[] tc,
                                             fitnessCase[] vc)
    {  int window_size = v.length;
       int size_prediction = vc.length;   
       Value[] results = new Value[ size_prediction ];
       Value[] values = new Value[ window_size + size_prediction ];
       Value[] eval = new Value[ window_size ]; 
       double ei = 0.0;
       double fp = 0.0;
           
       int j=0;
       for (int i=tc.length - window_size; i<tc.length; i++)
         { values[j] = new Value(tc[i].getNum());
           j++;  } 
       for (int i=0; i<size_prediction; i++)
         { values[j] = new Value(vc[i].getNum());
           j++;  }
           
    
       for (int i=0; i<size_prediction-1; i++)
         {
            for (int k=0; k<window_size; k++)
                 eval[k] = values[i + k];
            results[i] = ind.evaluate(v,eval);                  
            
            ei = Math.abs ( vc[i].getNum() - results[i].getValue() );
            fp = fp + ei;
         }
             
       return (fp / size_prediction); 
    }
    
  /* m�todo que mostra uma mensagem --------------------------------------- */
  public static void displayMessage(String m) 
    {  System.out.println(m);  }

  /* m�todo que mostra uma mensagem e sai da aplica��o -------------------- */
  public static void displayMessageAndExit(String m)
    {  System.out.println(m);
       System.exit(1);  }
    
  /* m�todo para ler dum ficheiro uma s�rie temporal ------------------------
     Uma s�rie temporal � definida por uma sequ�n�ia de numeros, ou seja o
     o formato do ficheiro � o seguinte :
            n�mero�\n�
            n�mero�\n�
            ...
     at� que seja atingido o fim do ficheiro.
     ---------------------------------------------------------------------- */
  public static fitnessCase[] read_TimeSerie(String filename)
   throws IOException, NumberFormatException
   {  fitnessCase[] serie;
      Vector serie_aux;  
      boolean eof = false;  

      FileReader fr = new FileReader(filename);
      BufferedReader br = new BufferedReader(fr);    
       
      serie_aux = new Vector();
      while (!eof)
        {  try
            {  String number_str = br.readLine();
               if ( (number_str == null) || (Misc.isBlankString(number_str)) )
                 { eof = true;  }
               else
                 {  try
                      {  
                         Double d_aux = new Double(number_str);
                         serie_aux.addElement(d_aux);
                      } catch (NumberFormatException e1)
                          { br.close();  
                            throw new NumberFormatException(number_str);  }
                 }
            } catch (EOFException e1)
               {  eof = true;  }
        }  
   
      br.close();
      
      serie = new fitnessCase[serie_aux.size()];
      Enumeration e = serie_aux.elements();
      for (int i=0; e.hasMoreElements(); i++)
        {  double n = ((Double) e.nextElement()).doubleValue();
           serie[i] = new fitnessCase(n);  
        }
           
      return(serie);
   }
    
     
  /* ---------------------------------------------------------------------- */
  /* |                  P R O G R A M A  P R I N C I P A L                | */
  /* ---------------------------------------------------------------------- */
    
  public static void main(String args[]) 
    {
       
       String serie_filename = ".\\data\\123.ts";
       int num_runs = 1;
              
       /* tratamento dos parametros da aplica��o -------------------------- */
   /*  if (args.length == 3)
         {  serie_filename = args[1];
            //num_runs = new Integer(args[2]);
         }
       else
         if (args.lenght == 2)
           {  serie_filename = args[1];
              num_runs = 1;  }  
       else
         displayMessageAndExit("use : app <serie filename> [n� of runs]");
       
       /* ler uma s�rie temporal, dum ficheiro especificado --------------- */  
       serie_filename = ".\\data\\anderson13.ts";
       
       /*lista com os valores da s�rie*/
       fitnessCase[] all_cases = null;      
        
       try
         {
            displayMessage("reading file : " + serie_filename);
            all_cases = read_TimeSerie(serie_filename);
            displayMessage("n� of runs : " + num_runs);
            displayMessage("");
         }
        catch (IOException ioe)
          {  displayMessageAndExit("Error reading file : " + 
                                    ioe.getMessage() + ".");  
          }
        catch (NumberFormatException ne)  
          {  displayMessageAndExit("Error when reading file : " +
                                   "'" + ne.getMessage() + "' " +
                                   "is not a number.");  }
          

       /* - parametros especificos desta aplica��o "gen�tica" ------------ */  
       double test_percentage = 0.80;
       double window_size = 0.10;
       String output_filename = "default.dat";
       paramAplication pA = new paramAplication(test_percentage,
                                                window_size,
                                                output_filename);
                                                                               

       /* aqui vamos calcular quantas vari�veis � que s�o necess�rias 
          baseado no tamanho da janela e no tamanho total da s�rie. ------ */
       int size_serie = all_cases.length;
       int num_vars = (int) Math.round (size_serie * pA.getWindowSize());   
       int num_testcases = (int) Math.round 
                                       (size_serie * pA.getTestPercentage());
                                            
       // lista com os casos de teste                               
       fitnessCase[] test_cases = new fitnessCase[num_testcases];
       for (int i=0; i<num_testcases; i++)
         test_cases[i] = new fitnessCase( all_cases[i] );

       // lista com os restantes casos (usados para verifica��o)
       fitnessCase[] verification_cases = 
                                 new fitnessCase[size_serie - num_testcases];
       for (int i=num_testcases; i<size_serie; i++)
         verification_cases[i - num_testcases] = 
                                 new fitnessCase(all_cases[i]);
                                    
                                           
       // construir o conjunto das variaveis
       Variable[] variable_set = new Variable[ num_vars ];
       for (int i=0; i<num_vars; i++)
         variable_set[i] = new Variable("x" + i);   
   
      
       /* conjunto das constantes usadas nos individuos ------------------- */
       ERConstant erc = new ERConstant('[',-10,10,']');

       Terminal[] constant_set = {erc};
       
       // juntar no conjunto dos terminais as constantes e variaveis.
       Object[] aux_set = Misc.attachArrays(variable_set, constant_set);
       Terminal[] terminal_set = new Terminal[ aux_set.length ]; 
       for (int i=0; i<aux_set.length; i++)
          terminal_set[i] = (Terminal) aux_set[ i ];        

       /* defini��o dos opera��es matem�ticas desejadas ------------------- */

         mul  product = new mul();
         add  adition = new add();
         pdiv division = new pdiv();
         sub  subtraction = new sub();
       
      
       /* conjunto das fun��es dos individuos ----------------------------- */
       Function[] function_set = {product, adition, division, subtraction}; 

      
       /* defini��o das fun��es de calculo das fitness -------------------- */
     
           // - raw fitness
              rawFitness err_fitness = 
                                    new errorFitness(variable_set);

           // - standard fitness       
              standardFitness std_fitness = 
                                         new errorStandardFitness();

           // - fitness ajustada              
              adjustedFitness adj_fitness = 
                                         new adjustedFitness();
              
           // - fitness proporcional              
              proportionateFitness prop_fitness = 
                                               new proportionateFitness();
       
       /* defini��o dos m�todos de selec��o ------------------------------- */
       
           // - overselection
              OverSelection overselection = new OverSelection();

           // - tournament
              Tournament tournament = new Tournament(4);
   
           // - proportionate fitness       
              FitnessProportionate fproportionate = new FitnessProportionate();
      
       /* defini��o dos operadores gen�ticos e seus parametros */

           // - muta��o
              Mutation  mutation  = new Mutation(overselection,
                                                 function_set,
                                                 terminal_set);
           // - crossover                                      
              CrossOver crossover = new CrossOver(tournament);
       
           // - reprodu��o       
              Reproduction reproduction = new Reproduction(tournament);
      
       /* defini��o dos parametros necess�rios ---------------------------- */
       
           // - globais.
              int max_generations = 1;
              int population_size = 500;
              paramGlobal pG = new paramGlobal(max_generations,
                                               population_size);
       
           // - inicializa��o da popula��o.
              generatePopulation gen_init_pop = new rampedHalf_and_Half();
              depthRamp depth = new depthRamp(2,6);
              int random_atps = 100;
              paramInitPopulation pIP = new paramInitPopulation (gen_init_pop,
                                                                 depth,
                                                                 random_atps); 
           // - limites.
              int max_nodes = 1000;
              int max_depth = 10;
              paramLimits pL = new paramLimits(max_nodes,
                                               max_depth);

           // defini��o dos parametros da gera��o de popula��o.
              int breed_phases = 3;
              double[] rates = {0.05,0.90,0.05};
              boolean probabilistic_run = true;
              geneticOperator[] operators = {reproduction,crossover,mutation};
              paramBreed pB = new paramBreed(breed_phases,
                                             probabilistic_run,
                                             rates,
                                             operators);
 
           // - output.  
              int bestn = 2;
              paramOutput pO = new paramOutput(bestn);
         
                                                       
       /* defini��o dos crit�rios de paragem ------------------------------ */
            
       EndRun stop_run = new bestInd();
      
       /* construir um "run" com a informa��o necess�ria ------------------ */
       GPrun time_series = new GPrun (pG,
                                      pIP,
                                      pB,
                                      pO,
                                      pL,
                                      pA,
                                      err_fitness,
                                      std_fitness,
                                      adj_fitness,
                                      prop_fitness,
                                      terminal_set,
                                      function_set,
                                      test_cases,
                                      stop_run);

     
       displayMessage("--- Parameters ---");
       displayMessage("");       
       
       displayMessage(time_series.toString());
 
      
       for (int j=0; j<num_runs; j++)
         {
            displayMessage("--- run [" + (j+1) + "] ---" + "\n");
            Generation[] g = null;
            int num_generations = 0;
                                          
            try 
             {  g = time_series.run();
                num_generations = g.length;  }
            catch (RandomAttempsExpiredException e)
              { displayMessage("Error :" + e.getMessage());  }
        
            //mostrar os melhores individuos de cada gera��o.  
            displayMessage("generation [i] best individual.");
            for (int i=0; i<num_generations; i++)
              displayMessage("["+ (i + 1) +"]" + g[i].find_best());
            displayMessage("");
            
            //mostrar a performance do melhor individuo em rela��o aos casos 
            //de previs�o.
            Individual best_ind = g[num_generations - 1].find_best();
                       
            displayMessage("performance of the best individual of the run.");
            displayMessage("individual : " + best_ind);

                                  
            double average_err = evaluateVerification(best_ind,
                                                      variable_set,
                                                      test_cases,
                                                      verification_cases);
                                                        
          
            displayMessage("average error of best individual : " + 
                           average_err);

         }
        
	}
}
 
