#include <stdio.h>
#include <dlfcn.h>

void* getrealaddr(const char* sl, const char* s2)
{
  void* lib= dlopen("libc.so", RTLD_NOW|RTLD_GLOBAL);
  void* symbol;
  if (lib==NULL)
  {
    fprintf(stderr,"could not open self-executable with dlopen(NULL)!:%s\n",dlerror());
    return -1;
  }
  symbol=dlsym(lib,"strcmp");
  fprintf(stderr, "the real strcmp address:0x%8x\n",symbol);
  dlclose(lib);
  return symbol;
}

int strcmp(const char* s1,const char* s2)
{
  fprintf(stderr,"the first arg is :%s, the second is:%s\n",s1,s2);
  getrealaddr(s1,s2);
  return 0;
}
