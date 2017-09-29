//
// Created by LiBB on 2017/9/4.
//
#include <jni.h>  //jni库
#include <string> //string ku

using namespace std; //std命名空间

extern "C" //按照C的规则翻译函数名
JNIEXPORT jstring JNICALL
//格式是java_包名_native所在的文件_native方法
Java_cn_yefeidd_hellojni_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str()); //返回hello from c++ 字符串
}
