# android-play

HOOK by using LD_PRELODA environment variable 
write app.c & hook.c
the app wrote for registration(only the fixed number can be registered successfully)
there is strcmp() in app.c
and in the hook.c hook the strcmp(),allowing any number to register successfully
using ndk-build 
adb push the app & libhook.so to emulator device(need chmod 777)
run app with number
then export LD_PRELOCD=path(the place where you put the libhook.so)
and then successful
