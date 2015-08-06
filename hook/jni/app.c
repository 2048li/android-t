#include <stdio.h>
#include <string.h>
#define bool int

bool checkSN(const char* sn)
{
  char realcode[] = "android";
  return !(strcmp(realcode,sn));
}

void main(int argc, char **argv)
{
  if (argc<2)
  {
    printf("please input sn:");
    return;
  }
  if(!checkSN(argv[1]))
  {
    printf("sn was invalid!\n");
    return;
  }
  printf("thanks for registion!\n");

}
