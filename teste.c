#include <stdio.h>
#include <string.h>
#include <stdlib.h>

void insertionSort(int* vetor, int tam){
    int i, j, aux;

    for(i = 0; i < tam; i++){
        aux = vetor[i];
        for(j = i-1; (j >= 0)&&(aux < vetor[j]); j--){
            vetor[j+1] = vetor[j];
        }
        vetor[j+1] = aux;
    }
}

void main(){
    int vetor[10], i;

    for(i = 0; i < 10; i++){
        printf("Digite um número: ");
        scanf("%d", &vetor[i]);
    }

    printf("Vetor: ");
    for(i = 0; i < 10; i++){
        printf("%d ", vetor[i]);
    }

    insertionSort(vetor, 10);

    printf("\nVetor ordenado: ");
    for(i = 0; i < 10; i++){
        printf("%d ", vetor[i]);
    }
}
