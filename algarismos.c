//i = atoi(v);
#include <stdio.h>
#include <stdlib.h>

void main(){

	long n1;
	int n2 = 0, n3, n4, n5, cont, i, aux;

	printf("Digite um número inteiro: \n");
	scanf("%ld", &n1);
	aux = n1;
	
	while(aux != 0){
		aux = aux/10;
		cont++;
	}
	
	aux = 0;
	
	while(n1 != 0){
		n2 = n1%10;
		n3 = n1/10;
		for(i = 0; i <= cont; i++){
			n4 = n3%10;
			n3 = n3/10;
			if(n4 == n2 && n4>0){
				aux = 1;
			}
		}
		n1 /= 10;
		cont--;
	}
	if(aux == 1){
		printf("Tem! \n");
	}
	else{
		printf("Nao! \n");
	}
		
}
