#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>
#include <stdio.h>
int main(){
    char f1[100],f2[100];

    printf("Enter file name along with location you want to copy from: ");
	
	gets(f1);

    printf("Enter file name along with location you want to copy To: ");

    gets(f2);

    int fd=open(f1,O_RDONLY);

	char info[100];

    read(fd,info,100);
	
	puts(info);
	
	printf("%d\n",sizeof(info));

    int fd1=open(f2,O_CREAT | O_WRONLY);

    write(fd1,info,sizeof(info));

    printf("Successfully copied\n");

    return 0;
}
