package org.graalvm.compiler.core.aarch64.test;

import jdk.vm.ci.aarch64.AArch64Kind;
import org.graalvm.compiler.lir.LIR;
import org.graalvm.compiler.lir.LIRInstruction;
import org.graalvm.compiler.lir.aarch64.AArch64Unary;
import org.junit.Test;

public class Aarch64SignAndZeroExtendTest extends AArch64MatchRuleTest {
    static short shortValue;
    static volatile short volatileShortValue;

    public static long testSignExtensionSnippet() {
        return shortValue;
    }

    @Test
    public void testSignExtension() {
        compile(getResolvedJavaMethod("testSignExtensionSnippet"), null);
        LIR lir = getLIR();
        boolean found = false;
        for (LIRInstruction ins : lir.getLIRforBlock(lir.codeEmittingOrder()[0])) {
            if (ins instanceof AArch64Unary.MemoryOp && ((AArch64Unary.MemoryOp)ins).isSigned()) {
                ins.visitEachOutput((value, mode, flags) -> assertTrue(value.getPlatformKind().toString(), value.getPlatformKind().equals(AArch64Kind.QWORD)));
                assertFalse("sign extending load expected only once in first block", found);
                found = true;
            }
        }
        assertTrue("sign extending load must be in the LIR", found);
    }

    public static long testVolatileSignExtensionSnippet() {
        return volatileShortValue;
    }

    @Test
    public void testVolatileSignExtension() {
        compile(getResolvedJavaMethod("testVolatileSignExtensionSnippet"), null);
        LIR lir = getLIR();
        boolean found = false;
        for (LIRInstruction ins : lir.getLIRforBlock(lir.codeEmittingOrder()[0])) {
            if (ins instanceof AArch64Unary.MemoryOp && ((AArch64Unary.MemoryOp)ins).isSigned()) {
                ins.visitEachOutput((value, mode, flags) -> assertTrue(value.getPlatformKind().toString(), value.getPlatformKind().equals(AArch64Kind.QWORD)));
                assertFalse("sign extending load expected only once in first block", found);
                found = true;
            }
        }
        assertTrue("sign extending load must be in the LIR", found);
    }

    public static long testVolatileSignExtensionDifferentBlocksSnippet(boolean flag) {
        short v = volatileShortValue;
        if (flag) {
            return v;
        }
        return 0;
    }

    @Test
    public void testVolatileSignExtensionDifferentBlocks() {
        compile(getResolvedJavaMethod("testVolatileSignExtensionDifferentBlocksSnippet"), null);
        LIR lir = getLIR();
        boolean found = false;
        for (LIRInstruction ins : lir.getLIRforBlock(lir.codeEmittingOrder()[0])) {
            if (ins instanceof AArch64Unary.MemoryOp && ((AArch64Unary.MemoryOp)ins).isSigned()) {
                ins.visitEachOutput((value, mode, flags) -> assertTrue(value.getPlatformKind().toString(), value.getPlatformKind().equals(AArch64Kind.QWORD)));
                assertFalse("sign extending load expected only once in first block", found);
                found = true;
            }
        }
        assertTrue("sign extending load must be in the LIR", found);
    }

    static char charValue;
    static volatile char volatileCharValue;

    public static long testZeroExtensionSnippet() {
        return charValue;
    }

    @Test
    public void testZeroExtension() {
        compile(getResolvedJavaMethod("testZeroExtensionSnippet"), null);
        LIR lir = getLIR();
        boolean found = false;
        for (LIRInstruction ins : lir.getLIRforBlock(lir.codeEmittingOrder()[0])) {
            if (ins instanceof AArch64Unary.MemoryOp && !((AArch64Unary.MemoryOp)ins).isSigned()) {
                ins.visitEachOutput((value, mode, flags) -> assertTrue(value.getPlatformKind().toString(), value.getPlatformKind().equals(AArch64Kind.QWORD)));
                assertFalse("zero extending load expected only once in first block", found);
                found = true;
            }
        }
        assertTrue("zero extending load must be in the LIR", found);
    }

    public static long testVolatileZeroExtensionSnippet() {
        return volatileCharValue;
    }

    @Test
    public void testVolatileZeroExtension() {
        compile(getResolvedJavaMethod("testVolatileZeroExtensionSnippet"), null);
        LIR lir = getLIR();
        boolean found = false;
        for (LIRInstruction ins : lir.getLIRforBlock(lir.codeEmittingOrder()[0])) {
            if (ins instanceof AArch64Unary.MemoryOp && !((AArch64Unary.MemoryOp)ins).isSigned()) {
                ins.visitEachOutput((value, mode, flags) -> assertTrue(value.getPlatformKind().toString(), value.getPlatformKind().equals(AArch64Kind.QWORD)));
                assertFalse("zero extending load expected only once in first block", found);
                found = true;
            }
        }
        assertTrue("zero extending load must be in the LIR", found);
    }

    public static long testVolatileZeroExtensionDifferentBlocksSnippet(boolean flag) {
        char v = volatileCharValue;
        if (flag) {
            return v;
        }
        return 0;
    }

    @Test
    public void testVolatileZeroExtensionDifferentBlocks() {
        compile(getResolvedJavaMethod("testVolatileZeroExtensionDifferentBlocksSnippet"), null);
        LIR lir = getLIR();
        boolean found = false;
        for (LIRInstruction ins : lir.getLIRforBlock(lir.codeEmittingOrder()[0])) {
            if (ins instanceof AArch64Unary.MemoryOp && !((AArch64Unary.MemoryOp)ins).isSigned()) {
                ins.visitEachOutput((value, mode, flags) -> assertTrue(value.getPlatformKind().toString(), value.getPlatformKind().equals(AArch64Kind.QWORD)));
                assertFalse("zero extending load expected only once in first block", found);
                found = true;
            }
        }
        assertTrue("zero extending load must be in the LIR", found);
    }
}
