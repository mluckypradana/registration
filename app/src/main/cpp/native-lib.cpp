#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_luc_base_helper_JNIUtil_apiEndpoint(JNIEnv *env, jobject) {
    return env->NewStringUTF(
#ifdef MOCK
            "http://private-0e175-mluckypradana.apiary-mock.com/"
#endif
#ifdef DEV
            "http://3.0.59.247/"
#endif
#ifdef PROD
            "http://staging"
#endif
    );
}
