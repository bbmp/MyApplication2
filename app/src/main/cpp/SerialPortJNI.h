//
// Created by r220996 on 2022/4/2.
//

#include <jni.h>
/* Header for class _SerialPortJNI */

#ifndef _SerialPortJNI
#define _SerialPortJNI
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:
 * Method:    openPort
 * Signature: (Ljava/lang/String;IIICI)I
 */
JNIEXPORT jint JNICALL Java_com_serial_jni_SerialPortJNI_openPort
        (JNIEnv *, jclass, jstring, jint, jint, jint, jchar);

/*
 * Class:
 * Method:    readPort
 * Signature: (I)[B
 */
JNIEXPORT jbyteArray JNICALL Java_com_serial_jni_SerialPortJNI_readPort
        (JNIEnv *, jclass, jint);

/*
 * Class:
 * Method:    writePort
 * Signature: ([B)V
 */
JNIEXPORT void JNICALL Java_com_serial_jni_SerialPortJNI_writePort
(JNIEnv *, jclass, jbyteArray);

/*
 * Class:
 * Method:    setMode
 * Signature: (I)I
 */
//JNIEXPORT jint JNICALL Java_com_serial_jni_SerialPortJNI_setMode
//        (JNIEnv *, jclass, jint);


/*
 * Class:
 * Method:    closePort
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_serial_jni_SerialPortJNI_closePort
(JNIEnv *, jclass);

#ifdef __cplusplus
}
#endif
#endif
