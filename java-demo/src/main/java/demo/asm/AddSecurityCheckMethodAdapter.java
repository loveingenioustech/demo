package demo.asm;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * Created by Administrator on 2017-08-09.
 */
class AddSecurityCheckMethodAdapter extends MethodVisitor {
    public AddSecurityCheckMethodAdapter(MethodVisitor mv) {
        super(Opcodes.ASM5,mv);
    }

    public void visitCode() {
        visitMethodInsn(Opcodes.INVOKESTATIC, "demo/asm/SecurityChecker",
                "checkSecurity", "()Z");
        super.visitCode();
    }

}
