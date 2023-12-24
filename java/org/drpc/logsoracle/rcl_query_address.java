// Generated by jextract

package org.drpc.logsoracle;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
/**
 * {@snippet :
 * struct rcl_query_address {
 *     uint64_t _hash;
 *     rcl_address_t _data;
 *     char* encoded;
 * };
 * }
 */
public class rcl_query_address {

    static final StructLayout $struct$LAYOUT = MemoryLayout.structLayout(
        Constants$root.C_LONG_LONG$LAYOUT.withName("_hash"),
        MemoryLayout.sequenceLayout(20, Constants$root.C_CHAR$LAYOUT).withName("_data"),
        MemoryLayout.paddingLayout(32),
        Constants$root.C_POINTER$LAYOUT.withName("encoded")
    ).withName("rcl_query_address");
    public static MemoryLayout $LAYOUT() {
        return rcl_query_address.$struct$LAYOUT;
    }
    static final VarHandle _hash$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("_hash"));
    public static VarHandle _hash$VH() {
        return rcl_query_address._hash$VH;
    }
    /**
     * Getter for field:
     * {@snippet :
     * uint64_t _hash;
     * }
     */
    public static long _hash$get(MemorySegment seg) {
        return (long)rcl_query_address._hash$VH.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * uint64_t _hash;
     * }
     */
    public static void _hash$set(MemorySegment seg, long x) {
        rcl_query_address._hash$VH.set(seg, x);
    }
    public static long _hash$get(MemorySegment seg, long index) {
        return (long)rcl_query_address._hash$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void _hash$set(MemorySegment seg, long index, long x) {
        rcl_query_address._hash$VH.set(seg.asSlice(index*sizeof()), x);
    }
    public static MemorySegment _data$slice(MemorySegment seg) {
        return seg.asSlice(8, 20);
    }
    static final VarHandle encoded$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("encoded"));
    public static VarHandle encoded$VH() {
        return rcl_query_address.encoded$VH;
    }
    /**
     * Getter for field:
     * {@snippet :
     * char* encoded;
     * }
     */
    public static MemorySegment encoded$get(MemorySegment seg) {
        return (java.lang.foreign.MemorySegment)rcl_query_address.encoded$VH.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * char* encoded;
     * }
     */
    public static void encoded$set(MemorySegment seg, MemorySegment x) {
        rcl_query_address.encoded$VH.set(seg, x);
    }
    public static MemorySegment encoded$get(MemorySegment seg, long index) {
        return (java.lang.foreign.MemorySegment)rcl_query_address.encoded$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void encoded$set(MemorySegment seg, long index, MemorySegment x) {
        rcl_query_address.encoded$VH.set(seg.asSlice(index*sizeof()), x);
    }
    public static long sizeof() { return $LAYOUT().byteSize(); }
    public static MemorySegment allocate(SegmentAllocator allocator) { return allocator.allocate($LAYOUT()); }
    public static MemorySegment allocateArray(long len, SegmentAllocator allocator) {
        return allocator.allocate(MemoryLayout.sequenceLayout(len, $LAYOUT()));
    }
    public static MemorySegment ofAddress(MemorySegment addr, SegmentScope scope) { return RuntimeHelper.asArray(addr, $LAYOUT(), 1, scope); }
}


