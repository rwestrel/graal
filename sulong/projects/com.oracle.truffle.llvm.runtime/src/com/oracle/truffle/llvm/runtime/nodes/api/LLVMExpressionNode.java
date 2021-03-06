/*
 * Copyright (c) 2016, 2018, Oracle and/or its affiliates.
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are
 * permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of
 * conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of
 * conditions and the following disclaimer in the documentation and/or other materials provided
 * with the distribution.
 *
 * 3. Neither the name of the copyright holder nor the names of its contributors may be used to
 * endorse or promote products derived from this software without specific prior written
 * permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS
 * OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
 * COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE
 * GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED
 * AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.oracle.truffle.llvm.runtime.nodes.api;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.instrumentation.GenerateWrapper;
import com.oracle.truffle.api.instrumentation.InstrumentableNode;
import com.oracle.truffle.api.instrumentation.ProbeNode;
import com.oracle.truffle.api.interop.TruffleObject;
import com.oracle.truffle.api.nodes.UnexpectedResultException;
import com.oracle.truffle.llvm.runtime.LLVMIVarBit;
import com.oracle.truffle.llvm.runtime.floating.LLVM80BitFloat;
import com.oracle.truffle.llvm.runtime.interop.LLVMInternalTruffleObject;
import com.oracle.truffle.llvm.runtime.pointer.LLVMManagedPointer;
import com.oracle.truffle.llvm.runtime.pointer.LLVMNativePointer;
import com.oracle.truffle.llvm.runtime.pointer.LLVMPointer;
import com.oracle.truffle.llvm.runtime.vector.LLVMDoubleVector;
import com.oracle.truffle.llvm.runtime.vector.LLVMFloatVector;
import com.oracle.truffle.llvm.runtime.vector.LLVMI16Vector;
import com.oracle.truffle.llvm.runtime.vector.LLVMI1Vector;
import com.oracle.truffle.llvm.runtime.vector.LLVMI32Vector;
import com.oracle.truffle.llvm.runtime.vector.LLVMI64Vector;
import com.oracle.truffle.llvm.runtime.vector.LLVMI8Vector;
import com.oracle.truffle.llvm.runtime.vector.LLVMPointerVector;

/**
 * An expression node is a node that returns a result, e.g., a local variable read, or an addition
 * operation.
 */
@GenerateWrapper
public abstract class LLVMExpressionNode extends LLVMNode implements InstrumentableNode {

    @Override
    public WrapperNode createWrapper(ProbeNode probe) {
        return new LLVMExpressionNodeWrapper(this, probe);
    }

    @GenerateWrapper.OutgoingConverter
    Object convertOutgoing(@SuppressWarnings("unused") Object object) {
        return null;
    }

    @Override
    public boolean isInstrumentable() {
        return getSourceLocation() != null;
    }

    public static final LLVMExpressionNode[] NO_EXPRESSIONS = {};

    public abstract Object executeGeneric(VirtualFrame frame);

    public LLVM80BitFloat executeLLVM80BitFloat(VirtualFrame frame) throws UnexpectedResultException {
        return LLVMTypesGen.expectLLVM80BitFloat(executeGeneric(frame));
    }

    public LLVMPointer executeLLVMPointer(VirtualFrame frame) throws UnexpectedResultException {
        return LLVMTypesGen.expectLLVMPointer(executeGeneric(frame));
    }

    public LLVMNativePointer executeLLVMNativePointer(VirtualFrame frame) throws UnexpectedResultException {
        return LLVMTypesGen.expectLLVMNativePointer(executeGeneric(frame));
    }

    public LLVMManagedPointer executeLLVMManagedPointer(VirtualFrame frame) throws UnexpectedResultException {
        return LLVMTypesGen.expectLLVMManagedPointer(executeGeneric(frame));
    }

    public TruffleObject executeTruffleObject(VirtualFrame frame) throws UnexpectedResultException {
        return LLVMTypesGen.expectTruffleObject(executeGeneric(frame));
    }

    public byte[] executeByteArray(VirtualFrame frame) throws UnexpectedResultException {
        return LLVMTypesGen.expectByteArray(executeGeneric(frame));
    }

    public double executeDouble(VirtualFrame frame) throws UnexpectedResultException {
        return LLVMTypesGen.expectDouble(executeGeneric(frame));
    }

    public float executeFloat(VirtualFrame frame) throws UnexpectedResultException {
        return LLVMTypesGen.expectFloat(executeGeneric(frame));
    }

    public short executeI16(VirtualFrame frame) throws UnexpectedResultException {
        return LLVMTypesGen.expectShort(executeGeneric(frame));
    }

    public boolean executeI1(VirtualFrame frame) throws UnexpectedResultException {
        return LLVMTypesGen.expectBoolean(executeGeneric(frame));
    }

    public int executeI32(VirtualFrame frame) throws UnexpectedResultException {
        return LLVMTypesGen.expectInteger(executeGeneric(frame));
    }

    public long executeI64(VirtualFrame frame) throws UnexpectedResultException {
        return LLVMTypesGen.expectLong(executeGeneric(frame));
    }

    public LLVMIVarBit executeLLVMIVarBit(VirtualFrame frame) throws UnexpectedResultException {
        return LLVMTypesGen.expectLLVMIVarBit(executeGeneric(frame));
    }

    public byte executeI8(VirtualFrame frame) throws UnexpectedResultException {
        return LLVMTypesGen.expectByte(executeGeneric(frame));
    }

    public LLVMI8Vector executeLLVMI8Vector(VirtualFrame frame) throws UnexpectedResultException {
        return LLVMTypesGen.expectLLVMI8Vector(executeGeneric(frame));
    }

    public LLVMI64Vector executeLLVMI64Vector(VirtualFrame frame) throws UnexpectedResultException {
        return LLVMTypesGen.expectLLVMI64Vector(executeGeneric(frame));
    }

    public LLVMI32Vector executeLLVMI32Vector(VirtualFrame frame) throws UnexpectedResultException {
        return LLVMTypesGen.expectLLVMI32Vector(executeGeneric(frame));
    }

    public LLVMI1Vector executeLLVMI1Vector(VirtualFrame frame) throws UnexpectedResultException {
        return LLVMTypesGen.expectLLVMI1Vector(executeGeneric(frame));
    }

    public LLVMI16Vector executeLLVMI16Vector(VirtualFrame frame) throws UnexpectedResultException {
        return LLVMTypesGen.expectLLVMI16Vector(executeGeneric(frame));
    }

    public LLVMFloatVector executeLLVMFloatVector(VirtualFrame frame) throws UnexpectedResultException {
        return LLVMTypesGen.expectLLVMFloatVector(executeGeneric(frame));
    }

    public LLVMDoubleVector executeLLVMDoubleVector(VirtualFrame frame) throws UnexpectedResultException {
        return LLVMTypesGen.expectLLVMDoubleVector(executeGeneric(frame));
    }

    public LLVMPointerVector executeLLVMPointerVector(VirtualFrame frame) throws UnexpectedResultException {
        return LLVMTypesGen.expectLLVMPointerVector(executeGeneric(frame));
    }

    public String getSourceDescription() {
        return getRootNode().getName();
    }

    public static boolean notLLVM(TruffleObject object) {
        return !(object instanceof LLVMInternalTruffleObject) && !LLVMPointer.isInstance(object);
    }
}
