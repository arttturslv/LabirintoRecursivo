import java.io.*;
public class MazeTeste {
    public static void main (String[] args) throws IOException {
        String path = "LabirintoTXT/mapa5.txt";  File arq = new File(path);  
    
        int qntColuna=0, qntLinha=0, cont=-1;
        int EntradaLin=0, EntradaCol=0, startx, starty; 
        char [] vet = new char[100];

        /* Pega as linhas do arquivo e conta a qnt de linhas e colunas. */
        try {
            FileReader fileReader = new FileReader(arq);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String linha = "";
                while ( ( linha = bufferedReader.readLine() ) != null) {
                    System.out.println(linha); // imprime o arquivo, frase por frase.
                        qntColuna = linha.length(); 
                        qntLinha++;
                }
                fileReader.close();
                bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /* Declaração da matriz */
        String matrizMaze [] [] = new String [qntLinha] [qntColuna]; 
            fileToArray(matrizMaze, arq); // Prenchimento da matriz

        /* Encontra a entrada da matriz - Posições iniciais p/ começar */    
        for (int i = 0; i < matrizMaze.length; i++) {
            for (int j = 0; j < matrizMaze[i].length; j++) {
                if (matrizMaze[i][j].equalsIgnoreCase("E")) {
                    EntradaLin = i; EntradaCol = j;
                }
            }
        }
        startx = EntradaCol; starty = EntradaLin;
        labirintoRecursivo(EntradaCol, EntradaLin, matrizMaze, qntColuna, qntLinha, vet, cont); // Parte principal
            
        //antes de imprimir, passo a posição de entrada p/ ficar mais visivel
            matrizMaze[starty][startx] = "E";
            System.out.println("\n\n"); printArray(matrizMaze); // Imprime a matriz
            
            /* Imprime valores de saída armazenados no vetor */                
            System.out.println("\nResultado: ");
            if ((vet[0]!='C') && (vet[0]!='E') && (vet[0]!='B') && (vet[0]!='D')) { //se nao tem nada preenchido
                System.out.print("Não há caminhos possiveis.");
            } else {
                for (int i = 0; i < vet.length; i++) {
                    System.out.print(vet[i]);
                }
            }
    }

    public static int labirintoRecursivo (int EntradaCol,int EntradaLin, String matrizMaze [][], int qntColuna, int qntLinha, char vet[], int cont) {
        cont++;

            /* Se a posição da pessoa ultrapassa a matriz */
            if ( EntradaLin >= qntLinha || EntradaLin < 0 || EntradaCol >= qntColuna || EntradaCol < 0){
                System.out.println("Saiu do labirinto");
                return 0;
            } 

            String posicaoAtual = matrizMaze[EntradaLin][EntradaCol]; // Recebe o valor da posição e não a localização

            /* Se a posição é S => Encontrou a saída */
            if (posicaoAtual.equalsIgnoreCase("S")) {
                System.out.println("\n\nSaida! posição: "+EntradaLin+", "+EntradaCol);
                System.out.println("\tValor = "+matrizMaze[EntradaLin][EntradaCol]);
                System.out.println("\tPosicao = "+EntradaLin+", "+EntradaCol);
                return 1;
            }

            /* Se tentar ir para uma posição já marcada, retorna */
            if (posicaoAtual.equalsIgnoreCase("x") || posicaoAtual.equals(">") || posicaoAtual.equals("<") || posicaoAtual.equals("v") || posicaoAtual.equals("^")) {
                System.out.println("\n\nObstaculo");
                System.out.println("Valor = "+matrizMaze[EntradaLin][EntradaCol]);
                System.out.println("Posicao = "+EntradaLin+", "+EntradaCol);
                return 0;
            }   
    
        /* RECURSÃO PARA AVANÇAR NO LABIRINTO */

        matrizMaze[EntradaLin][EntradaCol] = "^";
        if (labirintoRecursivo(EntradaCol, EntradaLin - 1, matrizMaze, qntColuna, qntLinha, vet, cont) !=0){
            vet[cont] += 'C';
             return 1;
        }
        matrizMaze[EntradaLin][EntradaCol] = "v";
        if (labirintoRecursivo(EntradaCol, EntradaLin + 1, matrizMaze, qntColuna, qntLinha, vet, cont) !=0){
            vet[cont] += 'B';
            return 1;
       }

        matrizMaze[EntradaLin][EntradaCol] = "<";
        if (labirintoRecursivo(EntradaCol - 1, EntradaLin, matrizMaze, qntColuna, qntLinha, vet, cont) !=0){
            vet[cont] += 'E';
            return 1;
       }

        matrizMaze[EntradaLin][EntradaCol] = ">";
        if (labirintoRecursivo(EntradaCol + 1, EntradaLin, matrizMaze, qntColuna, qntLinha, vet, cont) !=0){
            vet[cont] += 'D';
            return 1;
       }

        matrizMaze[EntradaLin][EntradaCol] = " ";   
            return 0;

    }

    public static void fileToArray (String matrizMaze[][], File arq ) {
        int i=0;
        char caracter;

        try {
            FileReader fileReader = new FileReader(arq);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String linha = "";
                while ( ( linha = bufferedReader.readLine() ) != null) {
                    for (int j = 0; j < linha.length(); j++) { //cada vez q rece a linha completa, percorro e registro na 
                        caracter = linha.charAt(j);
                        matrizMaze [i][j] = String.valueOf(caracter);
                    }
                    i++;
                }
                fileReader.close();
                bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printArray (String matrizMaze[][]) {
        for (int i = 0; i < matrizMaze.length; i++) {
            for (int j = 0; j < matrizMaze[i].length; j++) {
                System.out.print(matrizMaze [i][j]);
            }
            System.out.print("\n");
        }
    }
}








/*










 */