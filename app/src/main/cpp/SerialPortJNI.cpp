//
// Created by r220996 on 2022/4/2.
//
#include <jni.h>
#include <string>
#include "SerialPortJNI.h"
#include "SerialPortLog.h"
#include "SerialPort.h"

/* Header for class com_serial_jni_SerialPortJNI */
SerialPort serialPort;
/*
 * Class:
 * Method:    openPort
 * Signature: (Ljava/lang/String;IIIII)I
 */
extern "C" JNIEXPORT jint JNICALL Java_com_serial_jni_SerialPortJNI_openPort
        (JNIEnv *env, jclass jclazz, jstring path, jint baudRate, jint dataBits, jint stopBits, jchar parity){

    try {
        SerialPortConfig config;
        config = SerialPortConfig();
        config.baudrate = baudRate;
        config.databits = dataBits;
        config.stopbits = stopBits;
        config.parity = parity;
        serialPort = SerialPort(env->GetStringUTFChars(path, 0));
        return serialPort.openSerialPort(config);
    } catch (char *exception) {
        LOGE("Open device is error! Message:%s", exception);
        return FALSE;
    }
}

/*
 * Class:
 * Method:    readPort
 * Signature: (I)[B
 */
extern "C" JNIEXPORT jbyteArray JNICALL Java_com_serial_jni_SerialPortJNI_readPort
        (JNIEnv * env, jclass jclazz, jint maxSize){
    BYTE buf[maxSize];
    int len;
    len = serialPort.readData(buf, maxSize);
    if(len < 1) return NULL;
    jbyteArray byteArray;
    jbyte *bytes = reinterpret_cast<jbyte *>(buf);
    byteArray = env->NewByteArray(len);
    env->SetByteArrayRegion(byteArray, 0, len, bytes);
    return byteArray;
}

/*
 * Class:
 * Method:    writePort
 * Signature: ([B)V
 */
extern "C" JNIEXPORT void JNICALL Java_com_serial_jni_SerialPortJNI_writePort
        (JNIEnv *env, jclass jclazz, jbyteArray data){
    jbyte *array = env->GetByteArrayElements(data, 0);
    BYTE *bytes = reinterpret_cast<BYTE *>(array);
    jsize arrayLength = env->GetArrayLength(data);
    serialPort.writeData(bytes, (int)arrayLength);
}

/*
 * Class:
 * Method:    setMode
 * Signature: (I)I
 */
//extern "C" JNIEXPORT jint JNICALL Java_com_serial_jni_SerialPortJNI_setMode
//        (JNIEnv *env, jclass, jint mode){
//    return serialPort.setMode(mode);
//}


/*
 * Class:
 * Method:    closePort
 * Signature: ()V
 */
extern "C" JNIEXPORT void JNICALL Java_com_serial_jni_SerialPortJNI_closePort
    (JNIEnv *, jclass){
    serialPort.closePort();
    serialPort = NULL;
}


//static const JNINativeMethod gMethods[] = {
//        {"openPort", "(Ljava/lang/String;IIIB)I;", (jint *)openPort},
//        {"readPort","(I)[B;",(jbyteArray *)readPort},
//        {"writePort", "([B)V;", (void *)writePort},
//        {"setMode", "(I)I;", (jint *)setMode},
//        {"closePort", "()V;", (void *)setMode}
//};
//
//
//static jclass serialHelper;
//// 这里是java调用C的存在Native方法的类路径
//static const char* const className="com/serial/jni/SerialPortJNI";
//JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM* vm, void* reserved){
//    LOGI("jni onload called");
//    JNIEnv* env = NULL; //注册时在JNIEnv中实现的，所以必须首先获取它
//    jint result = -1;
//    if(vm->GetEnv((void **) &env, JNI_VERSION_1_4) != JNI_OK) { //从JavaVM获取JNIEnv，一般使用1.4的版本
//        return -1;
//    }
//    // 获取映射的java类
//    serialHelper = env->FindClass(className);
//    if(serialHelper == NULL)
//    {
//        printf("cannot get class:%s\n", className);
//        return -1;
//    }
//    // 通过RegisterNatives方法动态注册
//    if(env->RegisterNatives(serialHelper, gMethods, sizeof(gMethods)/sizeof(gMethods[0])) < 0)
//    {
//        printf("register native method failed!\n");
//        return -1;
//    }
//    LOGI("jni onload called end...");
//    return JNI_VERSION_1_4; //这里很重要，必须返回版本，否则加载会失败。
//}