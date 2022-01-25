package gplib;

import java.util.Random;

public class GPrun {

    public static final String rnd_attemps_error = "random attemps expired.";

    /* gerador de numeros aleat�rios */
    protected Random generator;
    /* crit�rio de paragem */
    protected EndRun end_run;

    public GPrun(paramGlobal gi,
            paramInitPopulation ip,
            paramBreed b,
            paramOutput oi,
            paramLimits l,
            paramAplication pa,
            rawFitness rf,
            standardFitness sf,
            adjustedFitness af,
            proportionateFitness pf,
            Terminal[] tset,
            Function[] fset,
            fitnessCase[] tc,
            EndRun end) {
        this.init_info = (paramInitPopulation) ip.clone();
        this.breeding_info = (paramBreed) b.clone();
        this.global_info = (paramGlobal) gi.clone();
        this.output_info = (paramOutput) oi.clone();
        this.limits_info = (paramLimits) l.clone();
        this.app_info = (paramAplication) pa.clone();
        this.raw_fitness_function = (rawFitness) rf.clone();
        this.standard_fitness_function = (standardFitness) sf.clone();
        this.adjusted_fitness_function = (adjustedFitness) af.clone();
        this.proportionate_fitness_function = (proportionateFitness) pf.clone();
        this.setTerminalSet(tset);
        this.setFunctionSet(fset);
        this.setFitnessCases(tc);
        this.end_run = end;
        this.generator = new Random();
    }

    public GPrun(paramGlobal gi,
            paramInitPopulation ip,
            paramBreed b,
            paramOutput oi,
            paramLimits l,
            paramAplication pa,
            rawFitness rf,
            standardFitness sf,
            adjustedFitness af,
            proportionateFitness pf,
            Terminal[] tset,
            Function[] fset,
            fitnessCase[] tc) {
        this(gi, ip, b, oi, l, pa, rf, sf, af, pf, tset, fset, tc, null);
    }
    /* ------------------------- vari�veis de inst�n�ia ------------------------- */

    /* informa��o sobre a gera��o da popula��o inicial */
    private paramInitPopulation init_info;
    /* informa��o sobre a gera��o de novas popula��es */
    private paramBreed breeding_info;
    /* informa��o global sobre o run em geral */
    private paramGlobal global_info;
    /* informa��o sobre os outputs do run */
    private paramOutput output_info;
    /* limits das �rvores de express�o */
    private paramLimits limits_info;
    /* parametros da aplica��o */
    private paramAplication app_info;
    /* refer�ncia da fun��o utilizada para c�lculo da raw-fitness */
    private rawFitness raw_fitness_function;
    /* refer�ncia da fun��o utilizada para c�lculo da standard-fitness */
    private standardFitness standard_fitness_function;
    /* refer�ncia da fun��o utilizada para c�lculo da adjusted-fitness */
    private adjustedFitness adjusted_fitness_function;
    /* refer�ncia da fun��o utilizada para c�lculo da proportionate-fitness */
    private proportionateFitness proportionate_fitness_function;
    /* tabela com os casos de teste, se aplic�vel */
    private fitnessCase[] test_cases;
    /* conjunto das fun��es */
    private Function[] function_set;
    /* conjunto dos terminais */
    private Terminal[] terminal_set;

    /* ----------------------- m�todos selectores (get/set) --------------------- */
    private void setFitnessCases(fitnessCase[] tc) {
        Object[] aux = Misc.returnArrayUnRepeated(tc);
        this.test_cases = new fitnessCase[aux.length];
        for (int i = 0; i < aux.length; i++) {
            this.test_cases[i] = (fitnessCase) ((fitnessCase) aux[i]).clone();
        }
    }

    private void setTerminalSet(Terminal[] tset) {
        Object[] aux = Misc.returnArrayUnRepeated(tset);
        this.terminal_set = new Terminal[aux.length];
        for (int i = 0; i < aux.length; i++) {
            this.terminal_set[i] = (Terminal) ((Terminal) aux[i]).clone();
        }
    }

    private void setFunctionSet(Function[] fset) {
        Object[] aux = Misc.returnArrayUnRepeated(fset);
        this.function_set = new Function[aux.length];
        for (int i = 0; i < aux.length; i++) {
            this.function_set[i] = (Function) ((Function) aux[i]).clone();
        }
    }

    public Generation generateInitialPopulation()
            throws RandomAttempsExpiredException {
        Generation g;
        Population p;

        generatePopulation genp = this.init_info.getMethod();

        genp.setDepthRamp(this.init_info.getDepth());
        genp.setRandomAttemps(this.init_info.getRandomAttemps());
        genp.setLimits(this.limits_info);
        genp.setGenerator(this.generator);

        p = genp.generate(global_info.getPopulationSize(),
                this.function_set,
                this.terminal_set);
        g = new Generation(p, 1);
        g.computeRawFitness(this.raw_fitness_function, this.test_cases);
        g.computeStandardFitness(this.standard_fitness_function);
        g.computeAdjustedFitness(this.adjusted_fitness_function);
        g.computeProportionateFitness(this.proportionate_fitness_function);
        g.computeStatistics();
        return (g);
    }

    public int RandomBreedOperator() {
        int[] rates = new int[breeding_info.getBreedPhases()];

        for (int i = 0; i < breeding_info.getBreedPhases(); i++) {
            rates[i] = new Double(breeding_info.getRate(i) * 1000000000).intValue();
        }

        // gera um n� aleat�rio
        int random_index = generator.nextInt(1000000000);
        int rate_index = 0;
        int tot_weigth = 0;
        boolean endfor = false;

        // seleciona o rate
        for (int i = 0; (i < rates.length) && (!endfor); i++) {
            tot_weigth += rates[i];
            if (random_index < tot_weigth) {
                rate_index = i;
                endfor = true;
            }
        }

        // retorna o indice do operador que será aplicado.
        return (rate_index);
    }

    public Generation[] probabilistic_run() throws RandomAttempsExpiredException {
        Generation[] g = new Generation[global_info.getMaxGenerations() + 1];
        Population p;
        int random_attemps;
        int ngenerations;
        int v_pop_size = 0;
        int num_ind = 0;
        boolean runnable = true;

        g[0] = generateInitialPopulation();
        ngenerations = g[0].getGenerationNumber();


        while (runnable) {
            p = new Population(global_info.getPopulationSize());

            while (p.getPopSize() < global_info.getPopulationSize()) {
                /* escolher um operador gen�tico baseado nos seus rates */
                int gen_op_index = this.RandomBreedOperator();
                geneticOperator gen_op = breeding_info.getOperator(gen_op_index);
                gen_op.setGenerator(this.generator);
                gen_op.setLimits(this.limits_info);

                /* selecionar, e aplicar as opera��es gen�ticas ao individuos. */
                boolean endwhile = false;
                random_attemps = init_info.getRandomAttemps();

                while ((!endwhile) && (random_attemps > 0)) {
                    Individual[] i = gen_op.doOperation(g[ngenerations - 1]);
                    num_ind = gen_op.getNumReturnInd();

                    /* Se o numero de individuos depois de inseridos aumentam a popula��o
                    para al�m do n� de individuos m�ximo entao cortamos o excesso. */
                    v_pop_size = p.getPopSize() + gen_op.getNumReturnInd();
                    if (v_pop_size > global_info.getPopulationSize()) {
                        num_ind = num_ind - v_pop_size - global_info.getPopulationSize();
                    }

                    /* inserir os individuos */
                    try {
                        p.addIndividual(i, num_ind);
                        endwhile = true;
                    } catch (PopulationRepeatedIndividualException e1) {
                        random_attemps--;
                    }

                    if (random_attemps == 0) {
                        throw new RandomAttempsExpiredException("GPrun.run():" + rnd_attemps_error);
                    }
                }
            }

            ngenerations++;
            g[ngenerations - 1] = new Generation(p, ngenerations);
            g[ngenerations - 1].computeRawFitness(this.raw_fitness_function,
                    this.test_cases);
            g[ngenerations - 1].computeStandardFitness(this.standard_fitness_function);
            g[ngenerations - 1].computeAdjustedFitness(this.adjusted_fitness_function);
            g[ngenerations - 1].computeProportionateFitness(this.proportionate_fitness_function);
            g[ngenerations - 1].computeStatistics();

            /* testar as condi��es de paragem */
            if (ngenerations >= global_info.getMaxGenerations()) {
                runnable = false;
            }
            if (end_run != null) {
                if (end_run.stopCriteria(g[ngenerations - 1])) {
                    runnable = false;
                }
            }

        }
        return (g);
    }

    public Generation[] run() throws RandomAttempsExpiredException {

        return (this.probabilistic_run());
    }

    /* ----------------------- m�todos das interfaces --------------------------- */
    public String toString() {
        String str_aux;

        str_aux =
                "terminal set = " + Misc.toStringArray(this.terminal_set) + "\n"
                + "function set = " + Misc.toStringArray(this.function_set) + "\n"
                + this.global_info.toString()
                + this.limits_info.toString()
                + this.init_info.toString()
                + this.breeding_info.toString()
                + this.output_info.toString()
                + "raw fitness=" + this.raw_fitness_function.toString() + "\n"
                + "standard fitness=" + this.standard_fitness_function.toString() + "\n"
                + this.app_info.toString();

        return (str_aux);
    }
}

/* -------------------------------------------------------------------------- */