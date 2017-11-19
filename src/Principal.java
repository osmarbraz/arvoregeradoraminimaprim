/*
 * Universidade Federal de Santa Catarina - UFSC
 * Departamento de Informática e Estatística - INE
 * Programa de Pós-Graduação em Ciências da Computação - PROPG
 * Disciplinas: Projeto e Análise de Algoritmos
 * Prof Alexandre Gonçalves da Silva 
 *
 * Baseado nos slides 95 da aula do dia 20/10/2017  
 *
 * Página 461 Thomas H. Cormen 3a Ed
 *
 * Árvore Geradora Mínima(MST) com o Algoritmo de Prim
 */

/**
 * @author Osmar de Oliveira Braz Junior
 */

public class Principal {
        
    final static int BRANCO = 0;//Vértice não visitado. Inicialmente todos os vértices são brancos
    final static int CINZA = 1; //Vértice visitado 

    //Vetor dos pais de um vértice
    static int pi[];
    //Valor chave do vértice
    static int chave[];
    //Armazena os vértices visitados
    static int[] cor;

    /**
     * Troca um número que representa a posição pela vértice do grafo.
     *
     * @param i Posição da letra
     * @return Uma String com a letra da posição i
     */
    public static String trocar(int i) {
        String letras = "abcdefghi";
        return letras.charAt(i) + "";
    }

    /**
     * Troca a letra pela posição na matriz de adjacência
     *
     * @param v Letra a ser troca pela posição
     * @return Um inteiro com a posição da letra no grafo
     */
    public static int destrocar(char v) {
        String letras = "abcdefghi";
        int pos = -1;
        for (int i = 0; i < letras.length(); i++) {
            if (letras.charAt(i) == v) {
                pos = i;
            }
        }
        return pos;
    }

    /**
     * Retorna o índice do vértice com o menor peso da aresta ainda não visitado.
     *
     * @param n Quantidade de vértices a ser pesquisados
     * @return O índice do menor vértice
     */
    public static int extractMin(int n) {
        int menorValor = Integer.MAX_VALUE;
        int indiceMenor = -1;
        for (int i = 0; i < n; i++) {
            if (cor[i] == BRANCO && chave[i] < menorValor) {
                indiceMenor = i;
                menorValor = chave[i];
            }
        }
        return indiceMenor;
    }

    /**
     * Exibe o caminho a ser percorrido no Grafo e o custo
     *
     * @param G Grafo a ser percorrido para mostrar o caminho e o custo
     */
    public static void mostrarCaminho(int[][] G) {
        //Quantidade de vértices do grafo
        int n = G.length;
        //Guarda o custo do caminho
        int custo = 0;
        //Percorre os vértices apartir de pi
        //O laço começa em 1 pois na posição 0 temos -1 do início
        for (int v = 1; v < n; v++) {
            System.out.println(trocar(pi[v]) + "->" + trocar(v) + " custo:" + G[pi[v]][v]);
            custo = custo + G[pi[v]][v];
        }
        System.out.println("Custo Total:" + custo);
    }

    /**
     * Executa o algoritmo de Prim para Ãrvore geradorda Mínima
     *
     * @param G Matriz de indicência da árvore
     * @param r Raiz da árvore
     */
    public static void algoritmoPrim(int[][] G, int r) {
        //Quantidade de vértices
        int n = G.length;
        //Instancia os vetores
        chave = new int[n];
        pi = new int[n];
        cor = new int[n];
        //Inicializa os vetores
        for (int u = 0; u < n; u++) {
            chave[u] = Integer.MAX_VALUE;
            pi[u] = -1;
            cor[u] = BRANCO;
        }
        //Raiz do grafo inicializa a chave com 0
        chave[r] = 0;
        
        for (int i = 0; i < n; i++) {
            //extranctMin remove o vértice com a menor chave de Q
            int u = extractMin(n);
            //Marca como visitado o vértice u
            cor[u] = CINZA;
            for (int v = 0; v < n; v++) {                
                //Somente com os adjancentes ao vértice u
                if (G[u][v] != 0) {
                    //v não pode ter sido visitado e o peso da aresta menor que o valor de chave de v
                    if (cor[v] == BRANCO && G[u][v] < chave[v]) {
                        pi[v] = u;
                        chave[v] = G[u][v];
                    }
                }
            }
        }
    }

    public static void main(String args[]) {
        //Matriz de incidência para um grafo direcionado     
        //Grafo do slide 56 de 20/10/2017
       
        int G[][] =
               //a  b  c  d  e  f  g  h  i 
               {{0, 4, 0, 0, 0, 0, 0, 8, 0}, //a
                {4, 0, 8, 0, 0, 0, 0, 11,0}, //b
                {0, 8, 0, 7, 0, 4, 0, 0, 2}, //c
                {0, 0, 7, 0, 9, 14,0, 0, 0}, //d
                {0, 0, 0, 9, 0, 10,0, 0, 0}, //e
                {0, 0, 4, 14,10,0, 2, 0, 0}, //f
                {0, 0, 0, 0, 0, 2, 0, 1, 6}, //g
                {8, 11,0, 0, 0, 0, 1, 0, 7}, //h
                {0, 0, 2, 0, 0, 0, 6, 7, 0}};//i

        System.out.println("Árvore Geradora Minima - Algoritmo de Prim");

        //Raiz da árvore
        int r = destrocar('a');

        //Executa o algoritmo
        algoritmoPrim(G, r);

        //Mostra o menor caminho
        mostrarCaminho(G);        
    }
}