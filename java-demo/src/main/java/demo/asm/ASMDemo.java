package demo.asm;

import org.apache.commons.io.IOUtils;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

import java.io.File;
import java.io.FileOutputStream;

import static org.objectweb.asm.Opcodes.*;

/**
 * Created by Administrator on 2017-08-09.
 */
public class ASMDemo {

    public static void main(String args[]) throws Exception{
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS|ClassWriter.COMPUTE_FRAMES);
        cw.visit(V1_7, ACC_PUBLIC, "AsmExample", null, "java/lang/Object", null);
        cw.visitSource("AsmExample.java", null);

        MethodVisitor mw = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null,  null);
        mw.visitVarInsn(ALOAD, 0);  //this 入栈
        mw.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V");
        mw.visitInsn(RETURN);
        mw.visitMaxs(0, 0);
        mw.visitEnd();
        mw = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "main",  "([Ljava/lang/String;)V", null, null);
        mw.visitFieldInsn(GETSTATIC, "java/lang/System", "out",  "Ljava/io/PrintStream;");
        mw.visitLdcInsn("Hello world!");
        mw.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println",  "(Ljava/lang/String;)V");
        mw.visitInsn(RETURN);
        mw.visitMaxs(0,0);
        mw.visitEnd();
        byte[] code = cw.toByteArray();

        IOUtils.write(code, new FileOutputStream(new File("D:/tmp/AsmExample.class")));
        // javap -verbose AsmExample.class

        // 通过自定义的ClassLoader 加载运行
        // CustomClassLoader loader = new CustomClassLoader();
//        Class exampleClass = loader.defineClass("Example", code, 0, code.length);
//        exampleClass.getMethods()[0].invoke(null, new Object[] { null });


    }

}
