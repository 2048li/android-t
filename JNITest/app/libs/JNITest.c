//
// Created by shentanli on 3/5/16.
//

#include "com_example_shentanli_jnitest_JNITest.h"

//#define LOG_TAG "JNITest"
//#undef LOG

#include <utils/log.h>

JNIEXPORT jstring JNICALL Java_com_example_shentanli_jnitest_JNITest_GetTest(JNIEnv * env, jobject obj)
{
    return (*env)->NewStringUTF(env, (char *)"JNITest Native String");
    LOGD("Hello LIb!\n");
}
