//
// Created by shentanli on 3/5/16.
//

#include "NAME.h"

#define LOG_TAG "JNITest"
#undef LOG

#include <utils/log.h>

JNIEXPORT jstring JNICALL Java_NAME_GetTest(JNIEnv * env, jobject obj)
{
    return (*env)->NewStringUTF(env, (char *)"JNITest Native String");
    LOGD("Hello LIb!\n");
}
